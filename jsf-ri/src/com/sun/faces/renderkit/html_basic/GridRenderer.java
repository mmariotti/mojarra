/*
 * $Id: GridRenderer.java,v 1.50.4.3 2007/09/04 16:32:30 rlubke Exp $
 */

/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
 * 
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License. You can obtain
 * a copy of the License at https://glassfish.dev.java.net/public/CDDL+GPL.html
 * or glassfish/bootstrap/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at glassfish/bootstrap/legal/LICENSE.txt.
 * Sun designates this particular file as subject to the "Classpath" exception
 * as provided by Sun in the GPL Version 2 section of the License file that
 * accompanied this code.  If applicable, add the following below the License
 * Header, with the fields enclosed by brackets [] replaced by your own
 * identifying information: "Portions Copyrighted [year]
 * [name of copyright owner]"
 * 
 * Contributor(s):
 * 
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package com.sun.faces.renderkit.html_basic;


import java.io.IOException;
import java.util.Iterator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import com.sun.faces.renderkit.AttributeManager;

/**
 * <B>GridRenderer</B> is a class that renders <code>UIPanel</code> component
 * as a "Grid".
 */

public class GridRenderer extends BaseTableRenderer {

    private static final String[] ATTRIBUTES =
          AttributeManager.getAttributes(AttributeManager.Key.PANELGRID);

    // ---------------------------------------------------------- Public Methods


    @Override
    public void encodeBegin(FacesContext context, UIComponent component)
          throws IOException {

        rendererParamsNotNull(context, component);

        if (!shouldEncode(component)) {
            return;
        }

        // Render the beginning of this panel
        ResponseWriter writer = context.getResponseWriter();
        renderTableStart(context, component, writer, ATTRIBUTES);

        // render the caption facet (if present)
        renderCaption(context, component, writer);

        // Render the header facet (if any)
        renderHeader(context, component, writer);

        // Render the footer facet (if any)
        renderFooter(context, component, writer);

    }


    @Override
    public void encodeChildren(FacesContext context, UIComponent component)
          throws IOException {

        rendererParamsNotNull(context, component);

        if (!shouldEncodeChildren(component)) {
            return;
        }

        // Set up the variables we will need
        ResponseWriter writer = context.getResponseWriter();
        TableMetaInfo info = getMetaInfo(component);
        int columnCount = info.columns.size();
        boolean open = false;
        int i = 0;

        // Render our children, starting a new row as needed
        renderTableBodyStart(component, writer);
        for (Iterator<UIComponent> kids = getChildren(component);
             kids.hasNext();) {
            UIComponent child = kids.next();
            if (!child.isRendered()) {
                continue;
            }
            if ((i % columnCount) == 0) {
                if (open) {
                    renderRowEnd(component, writer);
                    open = false;
                }
                renderRowStart(component, writer);
                open = true;
                info.columnStyleCounter = 0;
            }
            renderRow(context, component, child, writer);
            i++;
        }
        if (open) {
           renderRowEnd(component, writer);
        }
        renderTableBodyEnd(component, writer);
    }


    @Override
    public void encodeEnd(FacesContext context, UIComponent component)
          throws IOException {

        rendererParamsNotNull(context, component);

        if (!shouldEncode(component)) {
            return;
        }

        // Render the ending of this panel
        renderTableEnd(component, context.getResponseWriter());

        clearMetaInfo(component);

    }


    @Override
    public boolean getRendersChildren() {

        return true;

    }


    // ------------------------------------------------------- Protected Methods


    protected void renderRow(FacesContext context,
                             UIComponent table,
                             UIComponent child,
                             ResponseWriter writer)
    throws IOException {

        TableMetaInfo info = getMetaInfo(table);
        writer.startElement("td", table);
        if (info.columnClasses.length > 0) {
            writer.writeAttribute("class",
                                  info.getCurrentColumnClass(),
                                  "columns");
        }
        encodeRecursive(context, child);
        writer.endElement("td");
        writer.writeText("\n", table, null);

    }


    protected void renderHeader(FacesContext context,
                                UIComponent table,
                                ResponseWriter writer) throws IOException {

        TableMetaInfo info = getMetaInfo(table);
        UIComponent header = getFacet(table, "header");
        String headerClass =
              (String) table.getAttributes().get("headerClass");
        if (header != null) {
            writer.startElement("thead", table);
            writer.writeText("\n", table, null);
            writer.startElement("tr", header);
            writer.startElement("th", header);
            if (headerClass != null) {
                writer.writeAttribute("class", headerClass, "headerClass");
            }
            writer.writeAttribute("colspan",
                                  String.valueOf(info.columns.size()),
                                  null);
            writer.writeAttribute("scope", "colgroup", null);
            encodeRecursive(context, header);
            writer.endElement("th");
            writer.endElement("tr");
            writer.writeText("\n", table, null);
            writer.endElement("thead");
            writer.writeText("\n", table, null);
        }

    }


    protected void renderFooter(FacesContext context,
                                UIComponent table,
                                ResponseWriter writer) throws IOException {

        TableMetaInfo info = getMetaInfo(table);
        UIComponent footer = getFacet(table, "footer");
        String footerClass =
              (String) table.getAttributes().get("footerClass");
        if (footer != null) {
            writer.startElement("tfoot", table);
            writer.writeText("\n", table, null);
            writer.startElement("tr", footer);
            writer.startElement("td", footer);
            if (footerClass != null) {
                writer.writeAttribute("class", footerClass, "footerClass");
            }
            writer.writeAttribute("colspan",
                                  String.valueOf(info.columns.size()),
                                  null);
            encodeRecursive(context, footer);
            writer.endElement("td");
            writer.endElement("tr");
            writer.writeText("\n", table, null);
            writer.endElement("tfoot");
            writer.writeText("\n", table, null);
        }
        
    }

}

/*
 * $Id: ResponseWriterBase.java,v 1.4 2004/02/26 20:31:20 eburns Exp $
 */

/*
 * Copyright 2004 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package javax.faces.webapp;


import java.io.IOException;
import java.io.Writer;
import javax.faces.context.ResponseWriter;


/**
 * <p>Private abstract base class for {@link JspResponseWriter} and
 * {@link ServletResponseWriter}.  This class is <strong>NOT</strong>
 * part of the public API of JavaServer Faces.</p>
 *
 * <p>Concrete subclasses must implement the low-level methods inherited
 * from the <code>java.io.Writer</code> base class.</p>
 */

abstract class ResponseWriterBase extends ResponseWriter {


    /**
     * <p>Write whatever text should begin a response.</p>
     *
     * @exception IOException if an input/output error occurs
     */
    public void startDocument() throws IOException {

        throw new UnsupportedOperationException();

    }


    /**
     * <p>Write whatever text should end a response.  If there is an open
     * element that has been created by a call to <code>startElement()</code>,
     * that element will be closed first.</p>
     *
     * @exception IOException if an input/output error occurs
     */
    public void endDocument() throws IOException {

        throw new UnsupportedOperationException();

    }


    /**
     * <p>Write the start of an element, up to and including the
     * element name.  Once this method has been called, clients can
     * call <code>writeAttribute()</code> or <code>writeURIAttribute()</code>
     * method to add attributes and corresponding values.  The starting
     * element will be closed (that is, the trailing '>' character added)
     * on any subsequent call to <code>startElement()</code>,
     * <code>writeComment()</code>,
     * <code>writeText()</code>, <code>endElement()</code>, or
     * <code>endDocument()</code>.</p>
     *
     * @param name Name of the element to be started
     *
     * @exception IOException if an input/output error occurs
     * @exception NullPointerException if <code>name</code>
     *  is <code>null</code>
     */
    public void startElement(String name) throws IOException {

        throw new UnsupportedOperationException();

    }


    /**
     * <p>Write the end of an element, after closing any open element
     * created by a call to <code>startElement()</code>.
     *
     * @param name Name of the element to be ended
     *
     * @exception IOException if an input/output error occurs
     * @exception NullPointerException if <code>name</code>
     *  is <code>null</code>
     */
    public void endElement(String name) throws IOException {

        throw new UnsupportedOperationException();

    }


    /**
     * <p>Write an attribute name and corresponding value (after converting
     * that text to a String if necessary), after escaping it properly.
     * This method may only be called after a call to
     * <code>startElement()</code>, and before the opened element has been
     * closed.</p>
     *
     * @param name Attribute name to be added
     * @param value Attribute value to be added
     *
     * @exception IllegalStateException if this method is called when there
     *  is no currently open element
     * @exception IOException if an input/output error occurs
     * @exception NullPointerException if <code>name</code> or
     *  <code>value</code> is <code>null</code>
     */
    public void writeAttribute(String name, Object value)
        throws IOException {

        throw new UnsupportedOperationException();

    }


    /**
     * <p>Write a URI attribute name and corresponding value (after converting
     * that text to a String if necessary), after encoding it properly
     * (for example, '%' encoded for HTML).
     * This method may only be called after a call to
     * <code>startElement()</code>, and before the opened element has been
     * closed.</p>
     *
     * @param name Attribute name to be added
     * @param value Attribute value to be added
     *
     * @exception IllegalStateException if this method is called when there
     *  is no currently open element
     * @exception IOException if an input/output error occurs
     * @exception NullPointerException if <code>name</code> or
     *  <code>value</code> is <code>null</code>
     */
    public void writeURIAttribute(String name, Object value)
        throws IOException {

        throw new UnsupportedOperationException();

    }


    /**
     * <p>Write a comment containing the specified text, after converting
     * that text to a String if necessary.  If there is an open element
     * that has been created by a call to <code>startElement()</code>,
     * that element will be closed first.</p>
     *
     * @param comment Text content of the comment
     *
     * @exception IOException if an input/output error occurs
     * @exception NullPointerException if <code>comment</code>
     *  is <code>null</code>
     */
    public void writeComment(Object comment) throws IOException {

        throw new UnsupportedOperationException();

    }


    /**
     * <p>Write an object (after converting it to a String, if necessary),
     * after escaping it properly.  If there is an open element
     * that has been created by a call to <code>startElement()</code>,
     * that element will be closed first.</p>
     *
     * @param text Text to be written
     *
     * @exception IOException if an input/output error occurs
     * @exception NullPointerException if <code>text</code>
     *  is <code>null</code>
     */
    public void writeText(Object text) throws IOException {

        throw new UnsupportedOperationException();

    }


    /**
     * <p>Write a single character, after escaping it properly.  If there
     * is an open element that has been created by a call to
     * <code>startElement()</code>, that element will be closed first.</p>
     *
     * @param text Text to be written
     *
     * @exception IOException if an input/output error occurs
     */
    public void writeText(char text) throws IOException {

        throw new UnsupportedOperationException();

    }


    /**
     * <p>Write text from a character array, after escaping it properly.
     * This is equivalent to calling <code>writeText(c, 0, c.length)</code>.
     * If there is an open element that has been created by a call to
     * <code>startElement()</code>, that element will be closed first.</p>
     * </p>
     *
     * @param text Text to be written
     *
     * @exception IOException if an input/output error occurs
     * @exception NullPointerException if <code>text</code>
     *  is <code>null</code>
     */
    public void writeText(char text[]) throws IOException {

        throw new UnsupportedOperationException();

    }


    /**
     * <p>Write text from a character array, after escaping it properly
     * for this method.  If there is an open element that has been
     * created by a call to <code>startElement()</code>, that element
     * will be closed first.</p>
     *
     * @param text Text to be written
     * @param off Starting offset (zero-relative)
     * @param len Number of characters to be written
     *
     * @exception IndexOutOfBoundsException if the calculated starting or
     *  ending position is outside the bounds of the character array
     * @exception IOException if an input/output error occurs
     * @exception NullPointerException if <code>text</code>
     *  is <code>null</code>
     */
    public void writeText(char text[], int off, int len)
        throws IOException {

        throw new UnsupportedOperationException();

    }


}

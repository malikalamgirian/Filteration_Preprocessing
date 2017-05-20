/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.malikalamgirian.fyp;


import java.io.File;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;

/**
 *
 * @author Wasif
 */
public class GetXMLDocument {

    public static Document getBlankXMLDocument() {
        Document doc;
        
        try {
            //Create instance of DocumentBuilderFactory   
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            //Get the DocumentBuilder 
            DocumentBuilder parser = factory.newDocumentBuilder();

            //Create blank DOM  Document
            doc = parser.newDocument();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return doc;
    }

    public static Document getXMLDocument(String xml_File_URL) {
        Document doc;

        try {
            //Create instance of DocumentBuilderFactory   
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            //Get the DocumentBuilder 
            DocumentBuilder parser = factory.newDocumentBuilder();

            //Create blank DOM  Document
            doc = parser.parse(new File(xml_File_URL));

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return doc;
    }

    public static void TransformXML(Document doc, Result destination) throws Exception {

        TransformerFactory tranFactory = TransformerFactory.newInstance();
        Transformer aTransformer = tranFactory.newTransformer();

        aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");

        /* 5.1 Describes the domain of the indent-amount parameter (apache xst specific, non JAXP generic). */
        aTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        /* 5.2 Set source and destination */
        Source src = new DOMSource(doc);
        //Result dest = new StreamResult(System.out);
        //aTransformer.transform(src, dest);
        Result dest = destination;

        aTransformer.transform(src, dest);     

    }

}



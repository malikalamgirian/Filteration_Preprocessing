/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.malikalamgirian.fyp;

import java.io.File;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

/**
 *
 * @author Wasif
 */
public class PosFilter {

    

    /* Declarations */
    String string_To_Get_Filtered,
            content_Vector;
    NodeList pair,
            string_Pair;

    public boolean applyFilter(String xml_File_To_Filter_URL) throws Exception {
        /* Here we have to filter the provided xml file by using "Rule provided
         * for filteration in paper
         * 
         * 1. Get XML Document
         * 2. Get Document Element
         * 3. Get All Pairs
         * 4. Traverse Each Pair, and Apply filteration Rule.
         * 5. Transform File to filtered_File_URL
         */

        try{

        String filtered_File_URL;

        /* Set output file URL */
        filtered_File_URL = xml_File_To_Filter_URL.substring(
                0, xml_File_To_Filter_URL.indexOf(".")) + "_filtered.xml";

        /* 1.Get Blank XML Document */
        Document doc = com.malikalamgirian.fyp.GetXMLDocument.getXMLDocument(xml_File_To_Filter_URL);
        
        /* 2. Get Document Element */
        Element root = doc.getDocumentElement();

            /* 2.1 rename Document Element as  Filtered Document Properly */
            doc.renameNode(root, null , root.getNodeName() + "_filtered");

        /* 3. Get all pairs */
        pair = root.getChildNodes();

        /* 4. Traverse each pair */
        for (int i = 0; i < pair.getLength(); i++) {

            /* 4.1 Get each String out of each pair
             * 4.2 filter each string (out of String1, String2) by applying rule
             */

            /* 4.1 */
            /* 4.1.1 Get String pair out of Pair */
            string_Pair = pair.item(i).getChildNodes();

            for (int j = 0; j < string_Pair.getLength(); j++) {

                /* 4.1.2 Get string */
                string_To_Get_Filtered = string_Pair.item(j).getTextContent();

                /* 4.2*/

                /* 4.2.1 Tokenize the string */
                String[] term = string_To_Get_Filtered.split(" ");

                content_Vector = "";

                /* 4.2.2 Check each term's POS and apply rule */
                for (int k = 0; k < term.length; k++) {
                    /* 4.2.3 Here we call method that checks whether the term
                     * is to be included in content vector or not
                     */
                    if (Is_Pos_Constraint_Satisfied(term[k])) {
                        /* If POS constraint is satisfied
                         * append term to content_Vector,
                         * in comma separated format
                         */
                        term[k] = term[k].replaceAll("_", "/");
                        content_Vector += term[k] + ",";

                    }
                }

                /* 4.2.3 Format content vector */
                if (content_Vector.endsWith(",")) {
                    content_Vector = content_Vector.substring(
                            0, content_Vector.length() - 1);
                }

                /* 4.2.4 Set content_Vector generated as text content of node */
                string_Pair.item(j).setTextContent(content_Vector);

            }
        }



        /* 5. Transform File to outputFileURL */
        root.normalize();

        Result dest = new StreamResult(new File(filtered_File_URL));
        
        GetXMLDocument.TransformXML(doc, dest);

        }
        catch(Exception ex){
            throw new Exception( "ApplyFilter has gotten some exceptional case." + ex );
        }

        return true;
        
    }

    private boolean Is_Pos_Constraint_Satisfied(String term) {
        String pos;

        pos = term.substring(term.indexOf("_") + 1, term.length());

        if (
            /* Verb sub-classes */
            pos.equalsIgnoreCase("VB") ||
                pos.equalsIgnoreCase("VBD")     ||
                pos.equalsIgnoreCase("VBG")     ||
                pos.equalsIgnoreCase("VBN")     ||
                pos.equalsIgnoreCase("VBP")     ||
                pos.equalsIgnoreCase("VBZ")     ||

            /* Noun sub-classes */
            pos.equalsIgnoreCase("NN")  ||      
                pos.equalsIgnoreCase("NNP")     ||
                pos.equalsIgnoreCase("NNPS")    ||
                pos.equalsIgnoreCase("NNS")     ||

            /* Adjective sub-classes */
            pos.equalsIgnoreCase("JJ")  ||      
                pos.equalsIgnoreCase("JJR")     ||
                pos.equalsIgnoreCase("JJS")     ||

            /* Adverb sub-classes */
            pos.equalsIgnoreCase("RB")  ||      
                pos.equalsIgnoreCase("RBR")     ||
                pos.equalsIgnoreCase("RBS")     ||
                pos.equalsIgnoreCase("WRB")
            ){
            return true;
        }        
        return false;
    }
}

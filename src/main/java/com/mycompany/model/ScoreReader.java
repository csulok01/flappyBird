package com.mycompany.model;

/*-
 * #%L
 * flappyBird
 * %%
 * Copyright (C) 2018 Debreceni Egyetem
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 * #L%
 */

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ScoreReader {
        private String first = null;
        private String second = null;
        private String third = null;
        private String fourth = null;
        private String fifth = null;
        private String sixth = null;
        private static final Logger logger = Logger.getLogger( ScoreReader.class.getName() );
        /**
         * Visszaadja a beolvasott eredmenyek kozul az elsot.
         * @return az eredmeny
         */
    public String getFirst(){
        return first;
    }
        /**
         * Visszaadja a beolvasott eredmenyek kozul a masodikat.
         * @return az eredmeny
         */
    public String getSecond(){
        return second;
    }
        /**
         * Visszaadja a beolvasott eredmenyek kozul a harmadikat.
         * @return az eredmeny
         */
    public String getThird(){
        return third;
    }
        /**
         * Visszaadja a beolvasott eredmenyek kozul a negyediket.
         * @return az eredmeny
         */ 
    public String getFourth(){
        return fourth;
    }
        /**
         * Visszaadja a beolvasott eredmenyek kozul az otodiket.
         * @return az eredmeny
         */ 
    public String getFifth(){
        return fifth;
    }
        /**
         * Visszaadja a beolvasott eredmenyek kozul a hatodikat.
         * @return az eredmeny
         */ 
    public String getSisth(){
        return sixth;
    }
    /**
     * Kiolvassa az eredmenyeket az xml-bol.
     * @return visszaadja, hogy a kiolvasas sikeres volt-e
     */
    public boolean readXML() {
        //URL url = getClass().getClassLoader().getResource("score.xml");
        //String path = String.valueOf(url);
        //path = path.substring(path.indexOf("/")+1, path.lastIndexOf("/"));
	//path = path.substring(0, path.lastIndexOf("/"));
        //path+="/classes/score.xml";
        String path = (new File(".")).getAbsolutePath() ;
        path=path.substring(0,path.length()-1);
        File isPath = new File(path + "target/classes");
        if(isPath.isDirectory()){
                path += "target/classes/score.xml";
        }else{
        path += "classes/score.xml";
        }
        Document dom;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            dom = db.parse(path);
            Element doc = dom.getDocumentElement();

            first = getTextValue(first, doc, "first");
            second = getTextValue(second, doc, "second");
            third = getTextValue(third, doc, "third");
            fourth = getTextValue(fourth, doc, "fourth");
            fifth = getTextValue(fifth, doc, "fifth");
            sixth = getTextValue(sixth, doc, "sixth");
            logger.info("helyezesek beolvasva");
            return true;

        } catch (ParserConfigurationException | SAXException pce) {
            System.out.println(pce.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }

        return false;
    }
    /**
     * Kinyeri az adott node erteket.
     * @param def az adott note-ot tarolo string
     * @param doc a beolvasott adatok
     * @param tag az adott note
     * @return a kiolvasott node erteke
     */
    private String getTextValue(String def, Element doc, String tag) {
    String value = def;
    NodeList nl;
    nl = doc.getElementsByTagName(tag);
    if (nl.getLength() > 0 && nl.item(0).hasChildNodes()) {
        value = nl.item(0).getFirstChild().getNodeValue();
    }
    return value;
    }
}

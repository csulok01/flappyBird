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
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class scoreSaver {
    private static final Logger logger = Logger.getLogger( scoreSaver.class.getName() );
    /**
     * Lementi az eredmenyt az adott xml file-ba.
     * @param scores a menteni kivant eredmeny
     * @throws org.xml.sax.SAXException exception
     * @throws javax.xml.transform.TransformerException exception
     */
    public void saveToXML(List<Integer> scores) throws SAXException, TransformerException {
    	   try {
                //URL url = getClass().getClassLoader().getResource("score.xml");
                //String path = String.valueOf(url);
                //path = path.substring(path.indexOf("/")+1, path.lastIndexOf("/"));
		//path = path.substring(0, path.lastIndexOf("/"));
                //path+="/classes/score.xml";
                String path = (new File(".")).getAbsolutePath() ;
                path=path.substring(0,path.length()-1);
                path += "target/classes/score.xml";
                int score_counter=0;
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                Document doc = docBuilder.parse(this.getClass().getClassLoader().getResourceAsStream("score.xml"));
		Node score = doc.getElementsByTagName("score").item(0);
		NodeList list = score.getChildNodes();
                for(int i =0;i<list.getLength();i++){
                    if(list.item(i).getNodeType() == Node.ELEMENT_NODE){
                        list.item(i).setTextContent(String.valueOf(scores.get(score_counter)));
                        score_counter++;
                    }
                }
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new FileOutputStream(path));
		transformer.transform(source, result);
                
	   } catch (ParserConfigurationException | TransformerException | IOException | SAXException pce) {
	   }
    }
}

package com.hcl.bpm.dcworks.util;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class DCWorksUtil {

	public static File getXmlFilePath(String filePath,String fileName) {
		//"/Users/rajnishtomar/Desktop/bpm/"
		String path = filePath + fileName + ".xml";
		File file = new File(path);
		return file;
	}
	
	public static Document getXMLFileDom(String filePath,String fileName) throws Exception {
		File xmlFile = getXmlFilePath(filePath,fileName);
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			return doc;
		}catch(Exception e) {
			throw e;
		}	
	}
}

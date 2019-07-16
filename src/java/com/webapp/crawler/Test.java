/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.crawler;


import java.io.FileReader;
import java.util.ArrayList;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.XMLEvent;
import com.webapp.dto.PairProductLinks;
/**
 *
 * @author stephen
 */
public class Test {
	
	public static void main(String[] args) throws Exception {
		 BaseCrawler b = new BaseCrawler();
		 //String detail = b.getHtmlDocsBody("https://jadiny.vn/ao-ghile-xanh-den-cham_p1684.html", "<div id=\"single-product\" class=\"inner-top-50\">", "<div class=\"container inner-top-sm\">");
		 
		 //detail = detail.replaceAll("&(?!amp;)", "");
//		 String res = java.util.regex.Pattern.compile("(#x([0-9A-Fa-f]+);)")
//						 .matcher(detail)
//						 .replaceAll(x -> new String(Character.toChars(Integer.parseInt(x.group(2), 16))));
//		 System.out.println(detail);
		ArrayList<PairProductLinks> p = getListProductDetail("web/XML/jadinyOutputLinks.xml");
		p.forEach(t -> {
		  System.out.println("sex: " + t.getSex());
			t.getLinks().forEach(link -> {
				System.out.println("link: " + link + ", sex: " + t.getSex());
			});
		});
	}
	
	private static ArrayList getListProductDetail(String file) throws Exception {
			ArrayList<PairProductLinks> res = new ArrayList();
			XMLInputFactory factory = XMLInputFactory.newInstance();
			XMLEventReader reader = factory.createXMLEventReader(new FileReader(file));
			ArrayList<String> lists = null;
			String sex = "";
			while (reader.hasNext()) {
				XMLEvent eventReader = reader.nextEvent();
				if (eventReader.isStartElement()) {
					String start = eventReader.asStartElement().getName().getLocalPart();
					switch (start) {
						case "Links": 
							lists = new ArrayList<String>();
							break;
						case "Link":
							eventReader = (XMLEvent) reader.next();
							String character = eventReader.asCharacters().getData();
							lists.add(character);
							break;
						case "Sex":
							eventReader = (XMLEvent) reader.next();
							sex = eventReader.asCharacters().getData();
							break;
					}
				}
				if (eventReader.isEndElement() && eventReader.asEndElement().getName().getLocalPart().equals("Product")) {
					res.add(new PairProductLinks(sex, lists));
				}
			}
			return res;
	}
	
	
}

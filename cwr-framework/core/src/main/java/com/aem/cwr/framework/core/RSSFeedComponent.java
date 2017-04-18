package com.aem.cwr.framework.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class RSSFeedComponent {
	public static final String COMPONENT_PROPERTIES = "rssFeedComponentProperties";
	// Component Constants
	public static final String RSS_ITEM_ATTRIBUE = "item";

	// Component Value/Property constants
	public static final String RSS_FEED_ULR = "http://suryakand-shinde.blogspot.com/feeds/posts/default?alt=rss";
	public static final String RSS_MAX_FEED_COUNT = "5";

	// Components (Configurable) Attributes
	private String rssFeedUrl;
	private String maxFeedCount;
	private static final Logger LOG = LoggerFactory
			.getLogger(RSSFeedComponent.class);
	public ArrayList<HashMap<String, String>> tagsarraylist = new ArrayList<HashMap<String, String>>();

	public HashMap<String, String> tags_xml = new HashMap<String, String>();

	public RSSFeedComponent(String rssFeedUrl, String maxFeedCount,
			String[] xmltag, int k) {

		tags_xml.clear();

		tagsarraylist.clear();

		for (int i = 0; i < k; i++) {
			String s = xmltag[i];
			LOG.error("tag list1111111111  " + s);

			tags_xml.put(s, s);

		}

		LOG.error("inside rssfeed comp");
		this.rssFeedUrl = rssFeedUrl;
		this.maxFeedCount = maxFeedCount;

		if (rssFeedUrl.equals("D:\\newxml.xml")) {
			LOG.error("inside if that is same");
		}

		else {

			LOG.error("inside if that is not same" + rssFeedUrl
					+ "D:\\newxml.xml");
		}
	}

	public ArrayList<HashMap<String, String>> getRssFeeds() {

		ArrayList<HashMap<String, String>> feeds = new ArrayList<HashMap<String, String>>();

		try {

			LOG.error("inside rssfeed comp feed url" + rssFeedUrl);
			rssFeedUrl = rssFeedUrl.trim();
			File fXmlFile = new File(rssFeedUrl);

			InputStream inputStream = new FileInputStream(fXmlFile);
			Reader reader = new InputStreamReader(inputStream, "UTF-8");

			InputSource is = new InputSource(reader);
			is.setEncoding("UTF-8");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();

			LOG.error("after doc");
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

			LOG.error("after doc1");
			Document doc = dBuilder.parse(is);

			LOG.error("after doc2");
			/*
			 * DocumentBuilder builder =
			 * DocumentBuilderFactory.newInstance().newDocumentBuilder(); URL u
			 * = new URL(this.rssFeedUrl); Document doc =
			 * builder.parse(u.openStream());
			 */
			NodeList nodes = doc.getElementsByTagName(RSS_ITEM_ATTRIBUE);

			if (nodes != null) {
				// feeds = new ArrayList();
				for (int feedIndex = 0; feedIndex < Integer
						.parseInt(this.maxFeedCount); feedIndex++) {
					LOG.error("items it should give k value "
							+ Integer.parseInt(this.maxFeedCount));

					if (feedIndex < Integer.parseInt(this.maxFeedCount)) {
						Node element = nodes.item(feedIndex);

						LOG.error("items it should give "
								+ element.getNodeName());

						feeds.add(getElementValue(element, tags_xml));

					}

					else {
						LOG.error("coming isde elseeeeeeeeeee");
					}
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return feeds;
	}

	public HashMap<String, String> getElementValue(Node parent,HashMap<String, String> tags_xml_method)
	{
		HashMap<String, String> tags_xml_plz = new HashMap<String, String>();
		// System.out.println("inside get element method "
		// +parent.getElementsByTagName(label).item(0).getTextContent());
	
		if (parent.getNodeType() == Node.ELEMENT_NODE)
		{

			Element eElement = (Element) parent;

			// NodeList nodes_tocheck=eElement.getChildNodes();

			NodeList children = eElement.getChildNodes();
			
			
			for (int i = 0; i < children.getLength(); i++) 
			
			{
				if (tags_xml_method.get(children.item(i).getNodeName()) != null) {

					NamedNodeMap attributes = children.item(i).getAttributes();

					// get the number of nodes in this map

					int numAttrs = attributes.getLength();

					for (int j = 0; j < numAttrs; j++) {

						Attr attr = (Attr) attributes.item(j);

						String attrName = attr.getNodeName();

						String attrValue = attr.getNodeValue();

						if (attrName.equals("url")) {

							tags_xml_plz.put("url", attrValue);
						}

						LOG.error("Found attribute: " + attrName
								+ " with value: " + attrValue);

					}

					LOG.error("First Name : "
							+ eElement.getElementsByTagName(children.item(i).getNodeName()).item(0)
									.getTextContent());
					tags_xml_plz.put(children.item(i).getNodeName(), eElement.getElementsByTagName(children.item(i).getNodeName()).item(0)
							.getTextContent());
				}
			}

		}
		return tags_xml_plz;
	}
}

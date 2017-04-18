package com.aem.cwr.framework.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.PropertyIterator;
import javax.jcr.Session;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.commons.scheduler.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUse;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
/**
 * Sling Model for Navigation component.
 *
 * This model reads component configuration and provides the list of pages for the breadcrumb
 */
/**
 * @author Cognizant
 * @version 1.0
 * @since 1 January 2017
 */

public class RelatedNewsModel extends WCMUse {
	private static final Logger LOG = LoggerFactory
			.getLogger(RelatedNewsModel.class);
	private static final long serialVersionUID = -2;

	public String urlpath;
	public String limitdata;
	public String interval;
	List<String> tagList = null;
	
	
	public ArrayList<HashMap<String, String>> childList = new ArrayList<HashMap<String, String>>();
	@Reference
	private Scheduler scheduler;

	@Reference
	private ResourceResolverFactory resolverFactory;

	@Override
	public void activate() throws Exception {
		 String title;
		 String link;
		 String publishDate;
		 String author;
		 String description;
		 String media;
		 String category;
		LOG.info("Executing job2 it will execute wow its inside activate methods");

		ResourceResolver resourceResolver = getResourceResolver();
		Session session = resourceResolver.adaptTo(Session.class);
		PageManager pageManager = resourceResolver.adaptTo(PageManager.class);

		String path;
		
		Page currentPage = getCurrentPage();

		Node pageNode = currentPage.getContentResource().adaptTo(Node.class);

		LOG.info("Executing job2 it will execute wow its inside activate methods 123"
				+ getResource().getPath());

		Node compnode = getResource().adaptTo(Node.class);

		compnode.addMixin("cq:PollConfig");

		String source_feed = "myimporter:";

		urlpath = getProperties().get("rssurl", "");

		limitdata = getProperties().get("limitdata", "");

		interval = getProperties().get("timeinterval", "");

		source_feed = source_feed.concat(interval);

		compnode.setProperty("target", getResource().getPath());

		compnode.setProperty("source", source_feed);

		// compnode.setProperty("login", urlpath);

		// compnode.setProperty("password", limitdata);
		compnode.setProperty("interval", interval);
		compnode.getSession().save();

		String s = getResource().getPath();

		Resource res = resourceResolver.getResource(s);

		Node node = res.adaptTo(Node.class);

		NodeIterator nodes = node.getNodes();

		while (nodes.hasNext())
		
		{
			HashMap<String, String> map_tags = new HashMap<String, String>();
			Node item_node = nodes.nextNode();

			PropertyIterator propertyIterator = item_node.getProperties();
			
			
			while (propertyIterator.hasNext()) 
			{
			  
				
				
				String prop_name = propertyIterator.nextProperty().getName().toString();
			    String prop_value=item_node.getProperty(prop_name).getValue().getString();
			    LOG.info("prop_name   "+prop_name +"prop_value   "+prop_value);
			    
			    if(!prop_name.equals("jcr:primaryType"))
			    {
			    	map_tags.put(prop_name, prop_value);
			    }
			
			
			}
			
			childList.add(map_tags);
			LOG.info("Executing job2 it will execute wow its inside activate methods 1234"+childList);
		}

		session.save();

	}

	public String getPrint() {
		return "vamshik";
	}

	public ArrayList<HashMap<String, String>> getChildList() {
		return childList;
	}

}

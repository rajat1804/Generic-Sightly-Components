package com.aem.cwr.framework.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import com.day.cq.commons.jcr.JcrUtil;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.AccessDeniedException;
import javax.jcr.InvalidItemStateException;
import javax.jcr.ItemExistsException;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.PathNotFoundException;
import javax.jcr.ReferentialIntegrityException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.Value;
import javax.jcr.ValueFormatException;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.nodetype.NoSuchNodeTypeException;
import javax.jcr.version.VersionException;

import org.apache.felix.scr.annotations.Property;

import com.day.cq.polling.importer.ImportException;
import com.day.cq.polling.importer.Importer;

/**
 * Just a simple DS Component
 */
@Component(metatype = false, enabled = true, label = "Custom importer")
@Service
@Property(name = Importer.SCHEME_PROPERTY, value = "myimporter")
public class SimpleDSComponent implements Runnable, Importer {
	// public class SimpleDSComponent implements Runnable{
	@Reference
	private ResourceResolverFactory resolverFactory;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	public String title;
	private BundleContext bundleContext;

	public void run() {
		logger.info("Running...");
	}

	protected void activate(ComponentContext ctx) {
		this.bundleContext = ctx.getBundleContext();
	}

	protected void deactivate(ComponentContext ctx) {
		this.bundleContext = null;

	}

	public void importData(String scheme, String dataSource, Resource target,
			String login, String password) throws ImportException {

		logger.info("Date : " + new Date().toString());
		logger.info("scheme : " + scheme);
		logger.info("datasource : " + dataSource);
		logger.info("target big: " + target.getPath());
		logger.info("url big: " + login);
		logger.info("item big: " + password);
		String s = target.getPath();
		Session session = null;
		ResourceResolver resourceResolver = null;

		try {
			resourceResolver = resolverFactory
					.getAdministrativeResourceResolver(null);
		} catch (LoginException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		session = resourceResolver.adaptTo(Session.class);

		Resource res = resourceResolver.getResource(s);

		Node node = res.adaptTo(Node.class);

		try {
			if (node.hasNodes()) {

				NodeIterator nodes = node.getNodes();
				StringBuilder output = new StringBuilder();
				while (nodes.hasNext()) {
					Node node1 = nodes.nextNode();
					node1.remove();
				}

			}
			node.getSession().save();
		} catch (AccessDeniedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (VersionException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (LockException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (ConstraintViolationException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (RepositoryException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		if (target != null) {

			logger.info("banni sir is not null plz kano plzzzzzzzzzz");

		}
		
		
		String[] xml_tags=new String[100];
		
		 int k=0;
		
		try {
			Value[] values = node.getProperty("xmltag").getValues();
			
			for (Value v : values) {
			    logger.error("Property Name =  ; Property Value= "+v.getString());
			    xml_tags[k++]=v.getString();
			   
			}
			
			
		} catch (ValueFormatException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (PathNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (RepositoryException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		

		String url_rss = null;
		String limit_rss = null;

		try {
			url_rss = node.getProperty("rssurl").getValue().toString();

			logger.info("rss urlllllllllllllll" + url_rss);

			limit_rss = node.getProperty("limitdata").getValue().toString();

			logger.info("limit dataaaaaaaaaaaaaaa" + limit_rss);

		} catch (ValueFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (PathNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RepositoryException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		RSSFeedComponent rssfeed = new RSSFeedComponent(url_rss, limit_rss,xml_tags,k);

		ArrayList<HashMap<String, String>> feedop = rssfeed.getRssFeeds();
		logger.info("hash map which has content"+feedop.toString());
		int i=0;
		for (HashMap<String,String> map : feedop) 
		
		{
			  System.out.println(map.get("id_grade_curricular"));
			  i++;
			  
				Node item_node = null;
				try {
					item_node = node.addNode("item" + i);
					logger.info("new node is added    " + item_node.getPath());

				
					
					for(Entry<String, String> m:map.entrySet()){  
						
						if (m.getValue().length() > 1) {

							item_node.setProperty(m.getKey(), m.getValue());
							
							
							logger.error("Link  vamshik values   " + m.getValue());
						}
						item_node.getSession().save();
						    
						  }  
		
					item_node.getSession().save();

					node.getSession().save();

				} catch (ItemExistsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (PathNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchNodeTypeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LockException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (VersionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ConstraintViolationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (RepositoryException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			  
			  
			  
			}
		
		
		
		
		
		
		
		


		try {
			node.getSession().save();
			session.save();
		} catch (AccessDeniedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ItemExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ReferentialIntegrityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConstraintViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidItemStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (VersionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LockException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchNodeTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void importData(String scheme, String dataSource, Resource target)
			throws ImportException {
		// this is my importer
		logger.info("Date : " + new Date().toString());
		logger.info("scheme : " + scheme);
		logger.info("datasource : " + dataSource);
		logger.info("target : small" + target.getPath());

	}

}
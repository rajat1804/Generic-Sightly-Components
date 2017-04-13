package com.aem.cwr.framework.core;

import com.adobe.cq.sightly.WCMUsePojo;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.Iterable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import java.util.ArrayList;
import java.util.HashMap;

public class News extends WCMUsePojo{
	Logger logger = LoggerFactory.getLogger(News.class);

	List<NewsProperties> entries= new ArrayList<NewsProperties>();
	
	@Override
	public void activate() throws Exception {

		
		
		try {
			String[] content= getProperties().get("autocontentMap", String[].class);
			logger.debug("Inside News"+ content.length);
			for (String props: content) {
				NewsProperties newsProperties= new NewsProperties();
				JSONObject object= new JSONObject(props);
				String finalProp= object.getString("autonews");
				logger.debug("Path" + finalProp);
				
				newsProperties.setNewsPath(finalProp);
				logger.debug("array element" + newsProperties.newsPath);




				Resource res = getResourceResolver().getResource(finalProp+"/jcr:content");
				logger.debug("resource" + res.getName());

				if(res.adaptTo(ValueMap.class).containsKey(Constants.TITLE)) {
					String dTitle = res.adaptTo(ValueMap.class).get(Constants.TITLE,"");
					logger.debug("title " + dTitle);
					newsProperties.setTitle(dTitle);
					
				}

				if(res.adaptTo(ValueMap.class).containsKey(Constants.DESCRIPTION)) {
					String dDescription = res.adaptTo(ValueMap.class).get(Constants.DESCRIPTION,"");
					logger.debug("desc " + dDescription);
					newsProperties.setDescription(dDescription);
				}

				if(res.adaptTo(ValueMap.class).containsKey(Constants.IMAGEPATH)) {
					String dImagePath = res.adaptTo(ValueMap.class).get(Constants.IMAGEPATH,"");
					logger.debug("image " + dImagePath);
					newsProperties.setImagePath(dImagePath);
				}
				
				entries.add(newsProperties);
			}
		} catch (Exception e) {
			logger.error("Error occured"+ e);
		}
	

	}

	public List<NewsProperties> getEntries() {
		return entries;
	}

	

}


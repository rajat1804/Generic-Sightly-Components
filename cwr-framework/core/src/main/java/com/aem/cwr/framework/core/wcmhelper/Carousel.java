package com.aem.cwr.framework.core.wcmhelper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUsePojo;
import com.day.cq.wcm.api.Page;

public class Carousel extends WCMUsePojo{
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	 	private String parentPagePath;
	 	List<CarouselSlide> listObject=new ArrayList<CarouselSlide>();
	    String limit;
	    Integer value;
	    int count=1;
	    
	@Override
	public void activate() throws Exception {
		
		parentPagePath = get("path", String.class);
		limit =get("slideLimit", String.class);
		Resource resource=getResourceResolver().getResource(parentPagePath);
		 
		 Page page=resource.adaptTo(Page.class);
		 
		 Iterator<Page> itr=page.listChildren();
		if(limit!=null)
		{
				value = Integer.parseInt(limit);
				while(itr.hasNext()&& count<=value)
				{
					CarouselSlide slide = new CarouselSlide(itr.next());
					listObject.add(slide);
					count++;
				}
		}
		else
		{
				while(itr.hasNext())
		        {
		            CarouselSlide slide = new CarouselSlide(itr.next());
		            listObject.add(slide);
		            count++;
				}
		}
		
		 	  
		      
	}
	
	public List<CarouselSlide> getListObject()
	{
		
	return listObject;
	
	}
}

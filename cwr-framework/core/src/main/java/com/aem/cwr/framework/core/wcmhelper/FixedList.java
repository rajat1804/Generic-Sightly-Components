package com.aem.cwr.framework.core.wcmhelper;
import java.util.ArrayList;
import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUsePojo;
import com.day.cq.wcm.api.Page;


public class FixedList extends WCMUsePojo{
	
	 private String[] fixedListPages;
	 int i;
	 String limit;
	 Integer value;
	 int count=1;
	 List<CarouselSlide> fixedListObject=new ArrayList<CarouselSlide>();
	 
	@Override
	public void activate() throws Exception {
		Logger log = LoggerFactory.getLogger(this.getClass());
		
		
		 fixedListPages=get("fixedPages",String[].class);
		 limit =get("slideLimit", String.class);
			if(limit!=null)
			{
			value = Integer.parseInt(limit);
			}
			else
			{
				value=fixedListPages.length;
			}
		
		
		while(i<fixedListPages.length && count<=value)
		{
			 
		 Resource resource=getResourceResolver().getResource(fixedListPages[i]);
		 Page page=resource.adaptTo(Page.class);
		 CarouselSlide slide1 = new CarouselSlide(page);
		 fixedListObject.add(slide1);
		 i++;
		 count++;
		
		}
		 
	}
	public List<CarouselSlide> getFixedListObject(){
		return fixedListObject;
		}

}

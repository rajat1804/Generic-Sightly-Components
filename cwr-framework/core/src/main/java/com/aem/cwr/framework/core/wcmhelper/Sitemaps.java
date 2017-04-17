package com.aem.cwr.framework.core.wcmhelper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Queue;
import java.util.Iterator;
import java.util.List;
import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.adobe.cq.sightly.WCMUsePojo;
import com.day.cq.wcm.api.Page;

public class Sitemaps extends WCMUsePojo{


	Logger log = LoggerFactory.getLogger(this.getClass());
	List <Slide> listObject=new ArrayList<Slide>(); 
	private String rootPagePath;
	String checkbox;
	String limit;
	String currentPage;
	int depth;




	@Override
	public void activate() throws Exception {

		rootPagePath = get("path", String.class);
		checkbox = get("showAll", String.class);
		limit = get("limit", String.class);
		currentPage = get("current", String.class);
		log.error("The value of checkbox is 1111111"+checkbox);
		log.error("The parent page is 2222222222"+rootPagePath);
		log.error("The value of limit is 3333333333"+limit);

		Resource resource=getResourceResolver().getResource(rootPagePath);
		Page page=resource.adaptTo(Page.class);
		String name = page.getName();
		log.error("The name of the page isssssssss"+name);
		log.error("The name of the current page isssssssss"+currentPage);
		
		
		if(currentPage.equalsIgnoreCase(name)){
			
			 
			log.error("inside ifffffffffffffffffffffffffffffffffff");
			
			
		      }
		else{
			
			Slide slide1 = new Slide(page);
			listObject.add(slide1);
			
		}
		
		
		
		
		


		if(("true").equalsIgnoreCase(checkbox)){
			log.error("inside   ***********  checkbox true");
			
			Iterator<Page> itr=page.listChildren(null, true);	

			while(itr.hasNext())
			{
				
				
				Page childPage =    itr.next();
				String name1 = childPage.getName();
				if(currentPage.equalsIgnoreCase(name1)){
					
					 
					log.error("inside ifffffffffffffffffffffffffffffffffff");
					
					
				      }
				
				else{
				
			
				Slide slide = new Slide(childPage);
                  
				listObject.add(slide);
				}
			}
			}
		 


		else if(limit != "null"){
			depth = Integer.parseInt(limit);
			
            	
            	iterateOnChildren(page,depth);	
           
		}

	}


	
	void iterateOnChildren(Page page, int level)
	{
		//Slide slide1 = new Slide(page);
		//listObject.add(slide1);
	Iterator<Page> itr=page.listChildren();//Men /Women//Coat,Pant

	while(itr.hasNext())
	{

	    Page childpage=itr.next();
	    String name2=childpage.getName();
	    if(currentPage.equalsIgnoreCase(name2)){
			
			 
			log.error("inside ifffffffffffffffffffffffffffffffffff");
			
			
		      }
		
	    else{
	    Slide slide=new Slide(childpage);
	    listObject.add(slide);
	    }
	if(level!=0)
		iterateOnChildren(childpage,level-1);
	}
	}
	
	
	public List<Slide> getListObject()
	{

		return listObject;

	}
}
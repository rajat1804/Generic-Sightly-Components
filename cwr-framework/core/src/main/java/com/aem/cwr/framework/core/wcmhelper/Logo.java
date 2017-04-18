package com.aem.cwr.framework.core.wcmhelper;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;

import com.adobe.cq.sightly.WCMUsePojo;
//import com.day.cq.commons.Doctype;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.foundation.Image;
import com.day.jcr.vault.util.Text;

public class Logo extends WCMUsePojo{


private Resource currResource;
private String home;
private PrintWriter out;
private SlingHttpServletRequest request;
	@Override
	public void activate() throws Exception {
		
		int absParentLevel =getCurrentStyle().get("absParent", 2L).intValue();
		
		Page homePage = getCurrentPage().getAbsoluteParent(absParentLevel);
		if(homePage==null)
		{

	        homePage=getCurrentPage();
	    }
		
		 home = homePage != null ? homePage.getPath() : Text.getAbsoluteParent(getCurrentPage().getPath(), absParentLevel);
		 currResource = getCurrentStyle().getDefiningResource("imageReference");
		 
		 if (currResource == null) 
		 {
			 currResource = getCurrentStyle().getDefiningResource("image");

		 }
		
		 HttpServletResponse response = (HttpServletResponse) getResponse();
		 out = response.getWriter(); 
		 out.println("<a href='"+home+".html'>");
		 if(currResource == null) 
		 {
			 out.println("LogoComponent");
		 } 
		 else 
		 {
			request=getRequest();
	        Image img = new Image(currResource);
	        img.setItemName(Image.NN_FILE, "image");
	        img.setItemName(Image.PN_REFERENCE, "imageReference");
	        img.setSelector("img");
	       //img.setDoctype(Doctype.fromRequest(request));
	        img.setAlt("Home");
	        img.draw(out);
		 }
		 out.println("</a>");
		   
	}

}

package com.aem.cwr.framework.core;

import com.adobe.cq.sightly.WCMUsePojo;
import com.day.cq.wcm.api.Page;

public class TopNav extends WCMUsePojo {
	String pagePath = "";
	Page pageResource;
	int pageDepth;
	String currentPageResource;

	public int getPageDepth() {
		return pageDepth;
	}


	public Page getPageResource() {
		pageResource = this.getPageManager().getPage(pagePath);
		return pageResource;
	}
	
	
	@Override
	public void activate() {
		pagePath = get("path", String.class);
		currentPageResource= get("currentPageParam", String.class);
	}

}

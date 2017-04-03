package com.aem.cwr.framework.core;

import com.adobe.cq.sightly.WCMUsePojo;
import com.day.cq.wcm.api.Page;

public class TopNav extends WCMUsePojo {
	String pagePath = "";
	Page pageResource;
	int pageDepth;
	Page currentPageResource;

	public int getPageDepth() {
		pageDepth = currentPageResource.getDepth();
		return pageDepth;
	}

	public Page getPageResource() {
		pageResource = this.getPageManager().getPage(pagePath);
		return pageResource;
	}

	@Override
	public void activate() {
		pagePath = get("path", String.class);
		currentPageResource = this.getPageManager().getPage(get("currentPageParam", String.class));
	}

}

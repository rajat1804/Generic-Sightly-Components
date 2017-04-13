package com.aem.cwr.framework.core.wcmhelper;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;

import com.day.cq.wcm.api.Page;

public class CarouselSlide {
	 private final Page page;
     private String image = "";
     private String title = "";
     private String name = "";
     private String desc = "";
     private String path = "";

     public CarouselSlide(Page page) {
         this.page = page;
         title = page.getTitle();
         desc = page.getDescription();
         if (desc == null) {
             desc = "";
         }
         path = page.getPath();
         // currently we just check if "image" resource is present
         Resource r = page.getContentResource("image");
         if (r != null) {
				ValueMap property = r.adaptTo(ValueMap.class);
         	image = property.get("fileReference","");
         }
         name = page.getName();
     }
     

     public Page getPage() {
         return page;
     }

     public String getImage() {
         return image;
     }

     public String getTitle() {
         return title;
     }

     public String getName() {
         return name;
     }

     public String getDesc() {
         return desc;
     }

     public String getPath() {
         return path;
     }
}

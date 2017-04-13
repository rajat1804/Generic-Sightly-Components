package com.aem.cwr.framework.core.wcmhelper;




import com.day.cq.wcm.api.Page;

public class Slide {
private final Page page;
     private String image = "";
     private String title = "";
     private String name = "";
     private String desc = "";
     private String path = "";
     private int depth;
     private String parentPage;

     public Slide(Page page) {
         this.page = page;
         title = page.getTitle();
         desc = page.getDescription();
         if (desc == null) {
             desc = "";
         }
         path = page.getPath();
         name = page.getName();
         depth = page.getDepth();
         parentPage = page.getParent().getName();
         
     }
     

     public Page getPage() {
         return page;
     }

     public String getImage() {
    System.out.println("$$$$$$$$$$$$$$$$$"+image);
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
     public int getDepth() {
         return depth;
     }
     
}
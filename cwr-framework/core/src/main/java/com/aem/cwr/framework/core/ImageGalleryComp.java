package com.aem.cwr.framework.core;

import java.util.ArrayList;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.PathNotFoundException;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.jcr.Value;
import javax.jcr.ValueFormatException;

import org.apache.sling.api.resource.ResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.adobe.cq.sightly.WCMUsePojo;

public class ImageGalleryComp extends WCMUsePojo {

	ImageGalleryBean img_bean = null;
	ArrayList<String> imagePathList = new ArrayList<String>();
	ArrayList<String> folderImgPathList = new ArrayList<String>();
	private static final Logger log = LoggerFactory
			.getLogger(ImageGalleryComp.class);

	@Override
	public void activate() throws Exception {
		
		img_bean = new ImageGalleryBean();
		Node rootNode = getResource().adaptTo(Node.class);
		String imgPerRow = rootNode.getProperty("imgPerRow").getString();
		log.info("imgperRow " + imgPerRow);
		img_bean.setImgPerRow(imgPerRow);
		String mode = rootNode.getProperty("mode").getString();			//mode of image uplaod
		if (mode.equalsIgnoreCase("advanced")) {				//checks if image upload mode is (manual/folderwise)
			Property propVal = rootNode.getProperty("imagefolder");
			if (propVal.isMultiple()) {				
				Value[] values = propVal.getValues();
				for (Value val : values) {
					extractFolderImages(val);		//extracts all images from the folder path given by user

				}
			} else {
				Value val = propVal.getValue();
				extractFolderImages(val);

			}
			img_bean.setFolderList(folderImgPathList);

		} else if (mode.equalsIgnoreCase("image")) {		//checks if image upload mode (manual/folderwise)
			Property propVal = rootNode.getProperty("images");
			if (propVal.isMultiple()) {
				Value[] values = propVal.getValues();
				for (Value val : values) {
					imagePathList.add(val.getString());
					log.info("image list " + imagePathList);
				}
			} else {
				Value val = propVal.getValue();
				imagePathList.add(val.getString());
				
			}
			img_bean.setImageList(imagePathList);
		}
	}
	
	/**
	 * @param val
	 * @throws ValueFormatException
	 * @throws RepositoryException
	 * @throws PathNotFoundException
	 */
	private void extractFolderImages(Value val) throws ValueFormatException,
			RepositoryException, PathNotFoundException {
		ResourceResolver resourceResolver = getResourceResolver();
		Node imgNode = resourceResolver.getResource(val.getString()).adaptTo(
				Node.class);
		NodeIterator imgChildrenNodes = imgNode.getNodes();
		while (imgChildrenNodes.hasNext()) {
			Node childNode = imgChildrenNodes.nextNode();
			String imagePath = childNode.getProperty("jcr:primaryType")
					.getString();
			if (imagePath.equals("dam:Asset")) {
				folderImgPathList.add(childNode.getPath().toString());
				log.info("folderimage path list: " + folderImgPathList);
			}
		}
	}

	public ImageGalleryBean getImg_bean() {
		return img_bean;
	}

}

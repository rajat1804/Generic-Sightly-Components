package apps.cwr_framework.components.content.news-scroller;

import com.adobe.cq.sightly.WCMUsePojo;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.Iterable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class News extends WCMUsePojo{

	List<String> title= new ArrayList<String>();
	List<String> description= new ArrayList<String>();
	List<String> imagePath= new ArrayList<String>();
	List<String> newsPath= new ArrayList<String>();

	JSONArray array= new JSONArray();


	@Override
	public void activate() throws Exception {

		array= getProperties().get("autocontentMap","");
		System.out.println(array.length());
		for (int i = 0; i < array.length(); i++) {
			JSONObject jsonobject = array.getJSONObject(i);
			String dNewsPath = jsonobject.getString("autonews");
			newsPath.add(dNewsPath);

			String headl = jsonobject.getString("autoheadline");
			String desc = jsonobject.getString("autodesc");
			String imgs = jsonobject.getString("autoimage");


			Resource res = getResourceResolver().getResource(newsPath+"/jcr:content");


			if(res.adaptTo(ValueMap.class).containsKey(headl)) {
				String dTitle = res.adaptTo(ValueMap.class).get(headl,"");
				title.add(dTitle);
			}

			if(res.adaptTo(ValueMap.class).containsKey(desc)) {
				String dDescription = res.adaptTo(ValueMap.class).get(desc,"");
				description.add(dDescription);
			}

			if(res.adaptTo(ValueMap.class).containsKey(imgs)) {
				String dImagePath = res.adaptTo(ValueMap.class).get(imgs,"");
				imagePath.add(dImagePath);
			}
			System.out.println(title+"title" + description+"desc" + imagePath+"imgpath");
		}

	}

	public List<String> getTitle() {
		return title;
	}

	public List<String> getDescription() {
		return description;
	}

	public List<String> getImagePath() {
		return imagePath;
	}

	public List<String> getNewsPath() {
		return newsPath;
	}




}


package utils;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ExportJsonFormat {

	public static String export(List<TagAntenna> tagsSource){
		JsonArray jsonArray = new JsonArray();	
		for(TagAntenna tag: tagsSource){
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("antenna", tag.getAntenna());
			jsonObject.addProperty("tag", tag.getTagRFID());
			jsonArray.add(jsonObject);
		}
		
		return jsonArray.toString();
	}
}

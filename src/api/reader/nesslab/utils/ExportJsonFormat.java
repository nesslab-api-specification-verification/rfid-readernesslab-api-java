package api.reader.nesslab.utils;

import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ExportJsonFormat {
	
	private static /*@ spec_public @*/ final String TAG_ANTENNA = "antenna";
	//@ public constraint TAG_ANTENNA == \old(TAG_ANTENNA);
	private static /*@ spec_public @*/ final String TAG_RFID = "tag";
	//@ public constraint TAG_RFID == \old(TAG_RFID);

	private /*@ spec_public @*/static  JsonArray jsonArray = new JsonArray();	
	


	
	/* @ assignable jsonArray; 
	@ ensures (\forall int i; 
	@				i >= 0 && i < (tagsSource.keySet().size());
	@			  		jsonArray.get(i) != null
	@			);
	@*/
	public static String exportListTags(Map<String, TagAntenna> tagsSource){
		jsonArray = new JsonArray();	
		for(String key: tagsSource.keySet()){
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty(TAG_ANTENNA, tagsSource.get(key).getAntenna());
			jsonObject.addProperty(TAG_RFID, tagsSource.get(key).getTagRFID());
			jsonArray.add(jsonObject);
		}	
		
		return jsonArray.toString();
	}
	
	public static String exportTag(TagAntenna tagAntennaSource){
		JsonObject json = new JsonObject();
		json.addProperty(TAG_ANTENNA, tagAntennaSource.getAntenna());
		json.addProperty(TAG_RFID, tagAntennaSource.getTagRFID());
		return json.toString();
	}
}

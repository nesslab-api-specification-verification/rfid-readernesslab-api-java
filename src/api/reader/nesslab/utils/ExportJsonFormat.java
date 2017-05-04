package api.reader.nesslab.utils;

import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ExportJsonFormat {
	
	private static final String TAG_ANTENNA = "antenna";
	private static final String TAG_RFID = "tag";

	public static String exportListTags(List<TagAntenna> tagsSource){
		JsonArray jsonArray = new JsonArray();	
		for(TagAntenna tag: tagsSource){
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty(TAG_ANTENNA, tag.getAntenna());
			jsonObject.addProperty(TAG_RFID, tag.getTagRFID());
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

package api.reader.nesslab.utils;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import api.reader.nesslab.exceptions.SessionFullException;
/**
 * This class centralizes all functions for to encapsulate 
 * tags RFID in Objects or JSON representations. 
 * */
public class CaptureTagsRepresentation {

	private static /*@ spec_public nullable@*/ Map<String, TagAntenna> tags;
	private static /*@ spec_public nullable@*/ TagAntenna tag;
	private static /*@ spec_public nullable@*/ final String CODE_SUCESS_INVENTORY = "9C01";
	private static /*@ spec_public nullable@*/ final String CODE_ERRO_DUPLICATION_EXECUTION = "9C91";
	private static /*@ spec_public nullable@*/ final String CODE_ERRO_STOP_FORCE = "9S00";
	
	
	private static /*@ spec_public @*/ String jsonRepresentation = "";
	private static /*@ spec_public @*/ String jsonTagUnique = "";
	
	//@ assignable tags;
	//@ ensures tags!=null && \result == tags;
	private static Map<String, TagAntenna> getInstanceTags(){
		if(tags == null){
			tags = new HashMap<>();
		}
		return tags;
	}
	
	//@ ensures \result == tags.containsKey(key);
	public static /*@ pure @*/boolean verifyTagExists(String key){
		return getInstanceTags().containsKey(key);
	}

	/*@
	 @  public normal_behaviour
	 @   requires !response.equals(CODE_SUCESS_INVENTORY) && !response.equals(CODE_ERRO_DUPLICATION_EXECUTION) && !response.equals(CODE_ERRO_STOP_FORCE);
	 @   assignable jsonTagUnique, tag, jsonRepresentation;
	 @   ensures verifyTagExists(new TagAntenna(response).getTagRFID());
	 @ also
	 @  public exceptional_behaviour
	 @   requires response.equals(CODE_SUCESS_INVENTORY) || response.equals(CODE_ERRO_DUPLICATION_EXECUTION) || response.equals(CODE_ERRO_STOP_FORCE);
	 @   signals_only SessionFullException;
	 @*/
	public static void getObjectRepresentation(String response)
			throws UnknownHostException, IOException, SessionFullException {
		TagAntenna tmp;
		tmp = new TagAntenna(response);
		if (response.equals(CODE_SUCESS_INVENTORY) 
				|| response.equals(CODE_ERRO_DUPLICATION_EXECUTION)
				|| response.equals(CODE_ERRO_STOP_FORCE)) {
			throw new SessionFullException("Session memory is full.");
		} else {
			if (verifyTagExists(tmp.getTagRFID())) {
				tmp = getInstanceTags().get(tmp.getTagRFID());
				tmp.setCountReader(tmp.getCountReader() + 1L);
				jsonTagUnique= "";
			} else {
				getInstanceTags().put(tmp.getTagRFID(), tmp);
				jsonTagUnique = ExportJsonFormat.exportTag(tmp);
				tag = tmp;
				jsonRepresentation = ExportJsonFormat.exportListTags(getInstanceTags());
			}
		}

	}
	
	public static void clearMemory(int timeSecondsRange){
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				getInstanceTags().clear();
				
			}
		}, 0, timeSecondsRange*1000L);
	}

	//@ensures \result.equals(jsonRepresentation);
	public static /*@ pure @*/String getJsonRepresentation() {
		return jsonRepresentation;
	}

	public static List<TagAntenna> getTags() {
	    List<TagAntenna> newList = new ArrayList<TagAntenna>();
		for(String key: getInstanceTags().keySet()){
			newList.add(getInstanceTags().get(key));
		}
		
		return newList;
	}
	
	//@ensures \result == !jsonTagUnique.equals("");
	public static /*@ pure @*/boolean haveNewTag(){
		return !jsonTagUnique.equals("");
	}

	//@ensures \result == jsonTagUnique;
	public static /*@ pure @*/String getJsonTagUnique() {
		return jsonTagUnique;
	}

	//@ assignable CaptureTagsRepresentation.jsonTagUnique;
	//@ ensures CaptureTagsRepresentation.jsonTagUnique.equals(jsonTagUnique);
	public static void setJsonTagUnique(String jsonTagUnique) {
		CaptureTagsRepresentation.jsonTagUnique = jsonTagUnique;
	}
	
	//@ensures \result == tag;
	public /*@ pure @*/static TagAntenna getTag(){
		return tag;
	}
	
	

}

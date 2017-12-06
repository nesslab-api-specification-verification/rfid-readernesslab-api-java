package api.reader.nesslab.utils;


/**
 * This class should translate all responses of requests in reader.
 * */
public class TranslateResponse {

	
	/*@
	 @  requires response != null && response.contains(">b");
	 @  ensures \result.contains("the power is ");
	 @ also
	 @  requires response != null && response.contains(">c");
	 @  ensures \result.contains("continue mode ");
	 @ also
	 @  requires response != null && response.contains(">e");
	 @  ensures \result.equals("invalid response.") || \result.contains("antenna port enable: ");
	 @ also
	 @  requires response != null && response.contains(">p");
	 @  ensures \result.contains("the power is ");
	 @ also
	 @  requires response != null && response.contains(">t");
	 @  ensures \result.contains("scan time is ");
	 @ also
	 @  requires response!=null && !response.contains(">b")&& !response.contains(">c")&& !response.contains(">e") && !response.contains(">p") && !response.contains(">t");
	 @  ensures \result.equals(response);
	 @ also
	 @  requires response == null;
	 @  ensures \result.equals(response);
	 @*/
	public static String translate(String response){
		if(response != null){
			if(response.contains(">b")){
				return buzzerResponse(response);
			} else if (response.contains(">c")){
				return modeResonse(response);
			} else if(response.contains(">e")){
				return antennaStatusResponse(response);
			} else if(response.contains(">p")){
				return powerResponse(response);
			} else if(response.contains(">t")){
				return scanTimeResponse(response);
			} else {
				return response;
			}
		} else {
			return response;
		}
	}
	
	/*@ requires response.split("p")!=null && response.split("p").length>=2;
	 @ ensures \result.equals("the power is "+ response.split("p")[1] + "dBm");
	 @*/
	private static String powerResponse(String response){
		String[] message = response.split("p");
		return "the power is "+ message[1] + "dBm"; 
	}
	
	/*@
	 @  requires response.split("b")!=null && response.split("b").length>=2 && response.split("b")[1].equals("1");
	 @  ensures \result.equals("buzzer is enabled");
	 @ also
	 @  requires response.split("b")!=null && response.split("b").length>=2 && !response.split("b")[1].equals("1");
	 @  ensures \result.equals("buzzer is disabled");
	 @*/
	private static String buzzerResponse(String response){
		String[] message = response.split("b");
		if(message[1].equals("1")){
			return "buzzer is enabled";
		} else {
			return "buzzer is disabled";
		}
	}
	
	/*@
	 @  requires response.split("t")!=null && response.split("t").length>=2;
	 @  ensures \result.equals("scan time is " + response.split("t")[1] + "ms");
	 @*/
	private static String scanTimeResponse(String response){
		String[] message = response.split("t");
		return "scan time is " + message[1] + "ms";
	}
	
	/*@ 
	 @  requires response.split("e")!=null && response.split("e").length>=2 && response.split("e")[1].equals("1");
	 @  ensures \result.equals("antenna port enable: 4");
	 @ also
	 @  requires response.split("e")!=null && response.split("e").length>=2 && response.split("e")[1].equals("2");
	 @  ensures \result.equals("antenna port enable: 3");
	 @ also
	 @  requires response.split("e")!=null && response.split("e").length>=2 && response.split("e")[1].equals("3");
	 @  ensures \result.equals("antenna port enable: 3 and 4");
	 @ also
	 @  requires response.split("e")!=null && response.split("e").length>=2 && response.split("e")[1].equals("4");
	 @  ensures \result.equals("antenna port enable: 2");
	 @ also
	 @  requires response.split("e")!=null && response.split("e").length>=2 && response.split("e")[1].equals("5");
	 @  ensures \result.equals("antenna port enable: 2 and 4");
	 @ also
	 @  requires response.split("e")!=null && response.split("e").length>=2 && response.split("e")[1].equals("6");
	 @  ensures \result.equals("antenna port enable: 2 and 3");
	 @ also
	 @  requires response.split("e")!=null && response.split("e").length>=2 && response.split("e")[1].equals("7");
	 @  ensures \result.equals("antenna port enable: 2, 3 and 4");
	 @ also
	 @  requires response.split("e")!=null && response.split("e").length>=2 && response.split("e")[1].equals("8");
	 @  ensures \result.equals("antenna port enable: 1");
	 @ also
	 @  requires response.split("e")!=null && response.split("e").length>=2 && response.split("e")[1].equals("9");
	 @  ensures \result.equals("antenna port enable: 1 and 4");
	 @ also
	 @  requires response.split("e")!=null && response.split("e").length>=2 && response.split("e")[1].equals("10");
	 @  ensures \result.equals("antenna port enable: 1 and 3");
	 @ also
	 @  requires response.split("e")!=null && response.split("e").length>=2 && response.split("e")[1].equals("11");
	 @  ensures \result.equals("antenna port enable: 1, 3, and 4");
	 @ also
	 @  requires response.split("e")!=null && response.split("e").length>=2 && response.split("e")[1].equals("12");
	 @  ensures \result.equals("antenna port enable: 1 and 2");
	 @ also
	 @  requires response.split("e")!=null && response.split("e").length>=2 && response.split("e")[1].equals("13");
	 @  ensures \result.equals("antenna port enable: 1, 2, and 4");
	 @ also
	 @  requires response.split("e")!=null && response.split("e").length>=2 && response.split("e")[1].equals("14");
	 @  ensures \result.equals("antenna port enable: 1, 2 and 3");
	 @ also
	 @  requires response.split("e")!=null && response.split("e").length>=2 && response.split("e")[1].equals("15");
	 @  ensures \result.equals("antenna port enable: 1, 2, 3, and 4");
	 @ also
	 @  requires response.split("e")!=null && response.split("e").length>=2 && 
	 @		!response.split("e")[1].equals("1") && !response.split("e")[1].equals("2") && !response.split("e")[1].equals("3") && 
	 @		!response.split("e")[1].equals("4") && !response.split("e")[1].equals("5") && !response.split("e")[1].equals("6") && 
	 @ 		!response.split("e")[1].equals("7") && !response.split("e")[1].equals("8") && !response.split("e")[1].equals("9") && 
	 @		!response.split("e")[1].equals("10") && !response.split("e")[1].equals("11") && !response.split("e")[1].equals("12") && 
	 @		!response.split("e")[1].equals("13") && !response.split("e")[1].equals("14") && !response.split("e")[1].equals("15");
	 @  ensures \result.equals("invalid response.");
	 @*/
	private static String antennaStatusResponse(String response){
		String[] message = response.split("e");
		switch (message[1]) {
			case "1":
				return "antenna port enable: 4";			
			case "2":
				return "antenna port enable: 3";
			case "3":
				return "antenna port enable: 3 and 4";
			case "4":
				return "antenna port enable: 2";
			case "5":
				return "antenna port enable: 2 and 4";
			case "6":
				return "antenna port enable: 2 and 3";
			case "7":
				return "antenna port enable: 2, 3 and 4";
			case "8":
				return "antenna port enable: 1";
			case "9":
				return "antenna port enable: 1 and 4";
			case "10":
				return "antenna port enable: 1 and 3";
			case "11":
				return "antenna port enable: 1, 3, and 4";
			case "12":
				return "antenna port enable: 1 and 2";
			case "13":
				return "antenna port enable: 1, 2, and 4";
			case "14":
				return "antenna port enable: 1, 2 and 3";
			case "15":
				return "antenna port enable: 1, 2, 3, and 4";
			default:
				return "invalid response.";
			}
	}

	/*@
	 @  requires response.split("c")!=null && response.split("c").length>=2 && response.split("c")[1].equals("1");
	 @  ensures \result.equals("continue mode on");
	 @ also
	 @  requires response.split("c")!=null && response.split("c").length>=2 && !response.split("c")[1].equals("1");
	 @  ensures \result.equals("continue mode off");
	 @*/
	private static String modeResonse(String response){
		String[] message = response.split("c");
		if(message[1].equals("1")){
			return "continue mode on";
		} else {
			return "continue mode off";
		}
	}
	
	
}

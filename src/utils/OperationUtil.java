package utils;

public class OperationUtil {
	
	public static final String BUZZER_ON = ">x b 1 /r/n";
	public static final String BUZZER_OFF = ">x b 0 /r/n";
	public static final String BUZZER_STATUS = ">y b /r/n";
	public static final String ANTENNA_STATUS = ">y e /r/n";
	public static final String SCANTIME_STATUS = ">y t /r/n";
	public static final String POWER_CONTROL_STATUS = ">y p /r/n";
	public static final String READ_TAGS = "read antenna\r\n";
	public static final String READ_TAGS_END_MESSAGE = ">f \r\n";
	public static final String IP_READER_NESSLAB = "192.168.0.231";
	public static final int PORT_READER_NESSLAB = 5578;
	
	
	public static String setScanTime(long time){
		String timeMS = String.valueOf(time);
		return ">x t " + timeMS + "/r/n";
	}	
	
	public static String setPowerControl(String power){
		return ">x p "+ power + "\r\n";
	}
	
	
	//pattern: 0 0 0 0
	public static String enableAntennas(String antennas){
		switch (antennas) {
		case "0 0 0 1":
			return ">x e 1 /r/n";			
		case "0 0 1 0":
			return ">x e 2 /r/n";
		case "0 0 1 1":
			return ">x e 3 /r/n";
		case "0 1 0 0":
			return ">x e 4 /r/n";
		case "0 1 0 1":
			return ">x e 5 /r/n";
		case "0 1 1 0":
			return ">x e 6 /r/n";
		case "0 1 1 1":
			return ">x e 7 /r/n";
		case "1 0 0 0":
			return ">x e 8 /r/n";
		case "1 0 0 1":
			return ">x e 9 /r/n";
		case "1 0 1 0":
			return ">x e 10 /r/n";
		case "1 0 1 1":
			return ">x e 11 /r/n";
		case "1 1 0 0":
			return ">x e 12 /r/n";
		case "1 1 0 1":
			return ">x e 13 /r/n";
		case "1 1 1 0":
			return ">x e 14 /r/n";
		case "1 1 1 1":
			return ">x e 15 /r/n";
		default:
			return "invalid command.";
		}
	}
}

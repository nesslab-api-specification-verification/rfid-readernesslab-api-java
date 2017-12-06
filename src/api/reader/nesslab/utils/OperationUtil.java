package api.reader.nesslab.utils;

public class OperationUtil {

	public static final String BUZZER_ON = ">x b 1 /r/n";
	//@ public constraint BUZZER_ON == \old(BUZZER_ON);
	public static final String BUZZER_OFF = ">x b 0 /r/n";
	//@ public constraint BUZZER_OFF == \old(BUZZER_OFF);
	public static final String BUZZER_STATUS = ">y b /r/n";
	//@ public constraint BUZZER_STATUS == \old(BUZZER_STATUS);
	public static final String ANTENNA_STATUS = ">y e /r/n";
	//@ public constraint ANTENNA_STATUS == \old(ANTENNA_STATUS);
	public static final String SCANTIME_STATUS = ">y t /r/n";
	//@ public constraint SCANTIME_STATUS == \old(SCANTIME_STATUS);
	public static final String POWER_CONTROL_STATUS = ">y p /r/n";
	//@ public constraint POWER_CONTROL_STATUS == \old(POWER_CONTROL_STATUS);
	public static final String READ_TAGS_INVENTORY = ">f \r\n";
	//@ public constraint READ_TAGS_INVENTORY == \old(READ_TAGS_INVENTORY);
	public static final String READ_TAGS = ">r \r\n";
	//@ public constraint READ_TAGS == \old(READ_TAGS);
	public static final String CONTINUE_MODE_ON = ">x c 1 \r\n";
	//@ public constraint CONTINUE_MODE_ON == \old(CONTINUE_MODE_ON);
	public static final String CONTINUE_MODE_OFF = ">x c 0 \r\n";
	//@ public constraint CONTINUE_MODE_OFF == \old(CONTINUE_MODE_OFF);
	public static final String CONTINUE_MODE_STATUS = ">y c \r\n";
	//@ public constraint CONTINUE_MODE_STATUS == \old(CONTINUE_MODE_STATUS);
	public static final String IP_READER_NESSLAB_DEFAULT = "192.168.10.91";
	//@ public constraint IP_READER_NESSLAB_DEFAULT == \old(IP_READER_NESSLAB_DEFAULT);
	public static final String GET_IP_VALUE = ">y r / r/ n ";
	//@ public constraint GET_IP_VALUE == \old(GET_IP_VALUE);
	public static final String FORCE_STOP_READER = ">3 / r/n";
	//@ public constraint FORCE_STOP_READER == \old(FORCE_STOP_READER);
	public static final String MULTI_TAG_READ_RESET = ">g /r /n";
	//@ public constraint MULTI_TAG_READ_RESET == \old(MULTI_TAG_READ_RESET);
	public static final String ONE_TAG_READ = ">e /r /n";
	//@ public constraint ONE_TAG_READ == \old(ONE_TAG_READ);
	public static final int PORT_READER_NESSLAB = 5578;
	//@ public constraint PORT_READER_NESSLAB == \old(PORT_READER_NESSLAB);

	private static /*@ spec_public @*/ String ipReaderNessLab = "";

	public static String setScanTime(long time) {
		String timeMS = String.valueOf(time);
		return ">x t " + timeMS + "/r/n";
	}

	public static String setPowerControl(String power) {
		return ">x p " + power + "/r/n";
	}

	public static String setIpAdress(String ip) {
		return ">x r" + ip + "/r/n";
	}

	// pattern: 0 0 0 0
	public static String enableAntennas(String antennas) {
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

	/*@requires ip != null;
	 @ensures ipReaderNessLab == ip; 
	 @*/
	public static void setIpReaderNesslab(String ip) {
		ipReaderNessLab = ip;
	}

	// @ pure
	public static String getIpReaderNesslab() {
		return ipReaderNessLab;
	}
}

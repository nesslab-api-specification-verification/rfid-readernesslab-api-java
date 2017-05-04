	
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;
import com.google.gson.Gson;

import api.reader.nesslab.commands.CloseConnection;
import api.reader.nesslab.commands.DisableBuzzer;
import api.reader.nesslab.commands.DisableContinueMode;
import api.reader.nesslab.commands.EnableBuzzer;
import api.reader.nesslab.commands.EnableContinueMode;
import api.reader.nesslab.commands.ReaderTags;
import api.reader.nesslab.commands.ReaderTagsReset;
import api.reader.nesslab.commands.RequestStatusAntenna;
import api.reader.nesslab.commands.RequestStatusMode;
import api.reader.nesslab.commands.RequestStatusPowerAntenna;
import api.reader.nesslab.commands.ResquestStatusBuzzer;
import api.reader.nesslab.commands.SetPowerControl;
import api.reader.nesslab.exceptions.SessionFullException;
import api.reader.nesslab.facade.ApiReaderNesslab;
import api.reader.nesslab.interfaces.ApiReaderFacade;
import api.reader.nesslab.utils.*;
public class RFIDMain {
	
	public static void main(String[] args) {		
		try {
			
			/* The class ApiReaderNesslab to be instantiated, a 
			 * new connection with the Nesslab is opened. */
			ApiReaderFacade api = new ApiReaderNesslab("192.168.1.231");
			
//			api.executeAction(new SetScanTime(0L));
//			api.executeAction(new RequestStatusScanTime());
//			System.out.println(api.getTranslatedResponse());
			api.executeAction(new DisableBuzzer());		
			api.executeAction(new SetPowerControl("250"));
			api.executeAction(new EnableContinueMode());
////			
//			
			api.executeAction(new ResquestStatusBuzzer());
			System.out.println(api.getTranslatedResponse());
			
			api.executeAction(new RequestStatusAntenna());
			System.out.println(api.getTranslatedResponse());
			
			api.executeAction(new RequestStatusPowerAntenna());
			System.out.println(api.getTranslatedResponse());
			
			api.executeAction(new RequestStatusMode());
			System.out.println(api.getTranslatedResponse());
			
			
			api.executeAction(new ReaderTags());
			
			while (api.hasResponse()) {
				/* tags is printed in pattern: Antenna : 9 Tag: 00000002*/
				try {
					api.captureTagsObject();
					if(api.hasNewTag()){
						System.out.println(api.getTagUniqueJsonRepresentation());
					}
					
				} catch (SessionFullException e) {
					api.executeAction(new ReaderTagsReset());
				}
			}
			
			api.executeAction(new CloseConnection());
		} catch (UnknownHostException e) {
			System.err.println("Host not found: " + OperationUtil.getIpReaderNesslab());
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Don't possible the conection: "+ OperationUtil.getIpReaderNesslab());
			System.exit(1);
		}
	}
}
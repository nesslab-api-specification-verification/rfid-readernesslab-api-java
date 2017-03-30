	
import java.io.IOException;
import java.net.UnknownHostException;

import commands.CloseConnection;
import commands.EnableBuzzer;
import commands.EnableContinueMode;
import commands.ReaderTags;
import commands.RequestStatusAntenna;
import commands.RequestStatusMode;
import commands.RequestStatusPowerAntenna;
import commands.ResquestStatusBuzzer;
import commands.SetPowerControl;
import facade.ApiReaderNesslab;
import interfaces.ApiReaderFacade;
import utils.*;


public class RFIDMain {
	
	public static void main(String[] args) {		
		try {
			
			/* The class ApiReaderNesslab to be instantiated, a 
			 * new connection with the Nesslab is opened. */
			ApiReaderFacade api = new ApiReaderNesslab();

			api.executeAction(new EnableBuzzer());		
			api.executeAction(new SetPowerControl("250"));
			api.executeAction(new EnableContinueMode());
			
			api.executeAction(new ResquestStatusBuzzer());
			System.out.println("status buzzer: " + api.getResponse());
			
			api.executeAction(new RequestStatusAntenna());
			System.out.println("status antennas enable: "+ api.getResponse());
			
			api.executeAction(new RequestStatusPowerAntenna());
			System.out.println("status scan time enable: "+ api.getResponse());
			
			api.executeAction(new RequestStatusMode());
			System.out.println("continue mode: "+ api.getResponse());
			
			
			api.executeAction(new ReaderTags());
			
			while (api.hasResponse()) {
				/* tags is printed in pattern: Antenna : 9 Tag: 00000002*/
				api.getTagStringRepresentation();	
			}
			
			api.executeAction(new CloseConnection());

		} catch (UnknownHostException e) {
			System.err.println("Não encontro o host: " + OperationUtil.IP_READER_NESSLAB);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Não foi possível a ligação a: "+ OperationUtil.IP_READER_NESSLAB);
			System.exit(1);
		}
	}

}

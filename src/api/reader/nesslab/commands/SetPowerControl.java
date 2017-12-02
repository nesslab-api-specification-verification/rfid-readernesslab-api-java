package api.reader.nesslab.commands;

import java.io.IOException;
import java.net.UnknownHostException;

import api.reader.nesslab.interfaces.Command;
import api.reader.nesslab.utils.ConnectReader;
import api.reader.nesslab.utils.OperationUtil;

public class SetPowerControl implements Command {
	
	private /*@ spec_public nullable @*/ ConnectReader connectReader;
	private /*@ spec_public @*/ String power = "";
	
	/*@
	 @requires power!=null;
	 @assignable power;
	 @ensures this.power == power;
	 @*/
	public SetPowerControl(String power) {
		this.power = power; 
	}

	@Override
	public void execute() throws UnknownHostException, IOException {
		connectReader = ConnectReader.getInstance(OperationUtil.getIpReaderNesslab(), OperationUtil.PORT_READER_NESSLAB);
		connectReader.send(OperationUtil.setPowerControl(power));
		
	}

}

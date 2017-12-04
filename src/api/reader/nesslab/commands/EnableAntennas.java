package api.reader.nesslab.commands;

import java.io.IOException;
import java.net.UnknownHostException;

import api.reader.nesslab.interfaces.Command;
import api.reader.nesslab.utils.ConnectReader;
import api.reader.nesslab.utils.OperationUtil;

public class EnableAntennas implements Command {
	
	private /*@ spec_public nullable @*/ ConnectReader connectReader; //@ in cR;
	//@ protected represents cR <- connectReader;
	private /*@ spec_public @*/String pattern = "";
	
	//@ensures pattern == p;
	public EnableAntennas(String p) {
		this.pattern = p;
	}

	@Override
	public void execute() throws UnknownHostException, IOException {
		connectReader = ConnectReader.getInstance(OperationUtil.getIpReaderNesslab(), OperationUtil.PORT_READER_NESSLAB);
		connectReader.send(OperationUtil.enableAntennas(pattern));
		
	}

}

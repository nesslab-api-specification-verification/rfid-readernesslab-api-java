package commands;

import java.io.IOException;
import java.net.UnknownHostException;

import interfaces.Command;
import utils.ConnectReader;
import utils.OperationUtil;

public class SetPowerControl implements Command {
	
	private ConnectReader connectReader;
	private String power;
	
	public SetPowerControl(String power) {
		this.power = power; 
	}

	@Override
	public void execute() throws UnknownHostException, IOException {
		connectReader = ConnectReader.getInstance(OperationUtil.getIpReaderNesslab(), OperationUtil.PORT_READER_NESSLAB);
		connectReader.send(OperationUtil.setPowerControl(power));
		
	}

}

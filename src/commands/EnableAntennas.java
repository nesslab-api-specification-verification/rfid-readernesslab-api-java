package commands;

import java.io.IOException;
import java.net.UnknownHostException;

import interfaces.Command;
import utils.ConnectReader;
import utils.OperationUtil;

public class EnableAntennas implements Command {
	
	private ConnectReader connectReader;
	private String pattern;
	
	public EnableAntennas(String pattern) {
		this.pattern = pattern;
	}

	@Override
	public void execute() throws UnknownHostException, IOException {
		connectReader = ConnectReader.getInstance(OperationUtil.getIpReaderNesslab(), OperationUtil.PORT_READER_NESSLAB);
		connectReader.send(OperationUtil.enableAntennas(pattern));
		
	}

}

package commands;

import java.io.IOException;
import java.net.UnknownHostException;

import interfaces.Command;
import utils.ConnectReader;
import utils.OperationUtil;

public class SetScanTime implements Command {
	
	private long time;
	private ConnectReader connectReader;
	
	public SetScanTime(long time) {
		this.time = time;
	}

	@Override
	public void execute() throws UnknownHostException, IOException {
		this.connectReader = ConnectReader.getInstance(OperationUtil.getIpReaderNesslab(), OperationUtil.PORT_READER_NESSLAB);
		this.connectReader.send(OperationUtil.setScanTime(time));
	}

}

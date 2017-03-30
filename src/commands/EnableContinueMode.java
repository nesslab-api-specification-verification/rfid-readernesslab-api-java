package commands;

import java.io.IOException;
import java.net.UnknownHostException;

import interfaces.Command;
import utils.ConnectReader;
import utils.OperationUtil;

public class EnableContinueMode implements Command {
	
	private ConnectReader connectReader;

	@Override
	public void execute() throws UnknownHostException, IOException {
		this.connectReader = ConnectReader.getInstance(OperationUtil.IP_READER_NESSLAB, OperationUtil.PORT_READER_NESSLAB);
		this.connectReader.send(OperationUtil.CONTINUE_MODE_ON);
	}

}

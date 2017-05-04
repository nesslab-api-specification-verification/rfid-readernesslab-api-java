package api.reader.nesslab.interfaces;

import java.io.IOException;
import java.net.UnknownHostException;

public interface Command {
	
	void execute() throws UnknownHostException, IOException;
}

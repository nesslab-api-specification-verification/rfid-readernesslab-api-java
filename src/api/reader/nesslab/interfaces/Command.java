package api.reader.nesslab.interfaces;

import java.io.IOException;
import java.net.UnknownHostException;

public interface Command {
	
	//@ public model instance nullable ConnectReader cR;
	
	//@ ensures cR != null; 
	void execute() throws UnknownHostException, IOException;
}

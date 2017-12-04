package api.reader.nesslab.commands;

import java.io.IOException;
import java.net.UnknownHostException;

import api.reader.nesslab.interfaces.Command;
import api.reader.nesslab.utils.ConnectReader;
import api.reader.nesslab.utils.OperationUtil;

public class SetScanTime implements Command {
	
	private /*@ spec_public @*/long time;
	
	
	
	private /*@ spec_public nullable@*/ConnectReader connectReader; //@ in cR;
	
	//@ public represents cR <- connectReader;
	
	/* @requires t >= 0;
	 @assert time >= 0;
	 @ensures time == t;
	 @*/
	public SetScanTime(long t) {
		this.time = t;
	}

	/*
	  also
	   public exceptional_behaviour
	    signals
	 */
	public void execute() throws UnknownHostException, IOException {
		this.connectReader = ConnectReader.getInstance(OperationUtil.getIpReaderNesslab(), OperationUtil.PORT_READER_NESSLAB);
		this.connectReader.send(OperationUtil.setScanTime(time));
	}

}

package api.reader.nesslab.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
//@ model import java.net.NoRouteToHostException;

/**
 * The class is responsible for open connection and to send messages for the hardware Reader Nesslab. 
 * */
public class ConnectReader {
	
	private /*@ spec_public nullable@*/Socket echo	;
	private /*@ spec_public nullable@*/PrintWriter out;
	private /*@ spec_public nullable@*/BufferedReader in;
	private /*@ spec_public nullable @*/static ConnectReader connectReader;
	
	private ConnectReader() {
	}
	
	/**
	 * Method used for to return a connection with the reader.
	 * The method is used exclusively for API Facade. 
	 * @param ip is the IP of Reader.
	 * @param port is the port of connection with o Reader, by default is 5578.
	 * @throws UnknownHostException Is trown when the host not found.
	 * @throws IOException Is trown when any failure I/O ocurred.
	 * */
	/* Singleton */
	/*@
	 @  assignable connectReader; 
	 @  ensures connectReader!=null;
	 @*/
	public synchronized static /*@ nullable @*/ConnectReader getInstance(String ip, int port) 
			throws UnknownHostException, IOException{
		if(connectReader == null){
			connectReader = new ConnectReader();
			connectReader.setEcho(new Socket(ip, port));
			connectReader.setIn(new BufferedReader(new InputStreamReader(connectReader.getConnection().
					getInputStream())));
			connectReader.setOut(new PrintWriter(connectReader.getConnection()
					.getOutputStream(), true));
			System.out.println("The reader is connected.");
			
			return connectReader;
		} else {
			return connectReader;
		}
	}
	/**
	 * Method used for to send a message for the reader, following the specification of proprietary protocol.
	 * The method is used exclusively for API Facade. 
	 * @param message is the message according with the protocol. 
	 * **/
	public /*@ pure @*/void send(String message){
		this.out.println(message);
	}
	
	/**
	 * Method used for to return a response of hardware reader.
	 * @return response of hardware reader. 
	 * */
	public /*@ pure @*/String getResponse() throws IOException{
			return in.readLine();
	}
	
	/**
	 * Method used for to return if exists any response in buffer of reader.
	 * @return true if exists any response, false if not exist. 
	 * @throws IOException Is trown when any failure I/O ocurred.
	 * */
	public /*@ pure @*/boolean hasResponse() throws IOException{
		return in.read() > 1? true: false;
	}
	
	/**
	 * Method used for to return the socket of connection with the reader.
	 * @return Socket connection with reader.  
	 * */
	//@ ensures \result==echo;
	public /*@ pure @*/Socket getConnection(){
		return echo;
	}
	
	/**
	 * Method used for to close connection with the hardware reader.
	 * @throws IOException Is trown when any failure I/O ocurred.
	 * */
	//@ensures echo.isClosed();
	public void closeConnection() throws IOException{
		out.close();
		in.close();
		echo.close();
	}
	
	/*@
	 @ assignable this.echo;
	 @ ensures this.echo == echo; 
	 @*/
	private void setEcho(Socket echo){
		this.echo = echo;
	}

	/*@
	 @ assignable this.out;
	 @ ensures this.out == out; 
	 @*/
	private void setOut(PrintWriter out){
		this.out = out;
	}
	
	//@assignable this.in;
	//@ ensures this.in == in;
	private void setIn(BufferedReader in){
		this.in = in;
	}
	
	
	
	
}

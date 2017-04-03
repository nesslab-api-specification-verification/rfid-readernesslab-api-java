package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectReader {
	
	private Socket echo	;
	private PrintWriter out;
	private BufferedReader in;
	private static ConnectReader connectReader;
	
	private ConnectReader() {
	}
	
	/* Singleton */
	public synchronized static ConnectReader getInstance(String ip, int port) 
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
	
	public void send(String message){
		this.out.println(message);
	}
	
	public String getResponse() throws IOException{
		if(hasResponse()){
			return in.readLine();
		} else {
			return "No answer was found.";
		}
	}
	
	public boolean hasResponse() throws IOException{
		return in.read() > 1? true: false;
	}
	
	public Socket getConnection(){
		return echo;
	}
	
	public void closeConnection() throws IOException{
		out.close();
		in.close();
		echo.close();
	}
	
	private void setEcho(Socket echo){
		this.echo = echo;
	}
	
	public void setOut(PrintWriter out){
		this.out = out;
	}
	
	public void setIn(BufferedReader in){
		this.in = in;
	}
	
	
	
	
}

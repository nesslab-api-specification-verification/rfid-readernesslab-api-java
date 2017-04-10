package facade;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import exceptions.SessionFullException;
import interfaces.ApiReaderFacade;
import interfaces.Command;
import utils.ConnectReader;
import utils.OperationUtil;
import utils.TagAntenna;
import utils.TranslateResponse;

public class ApiReaderNesslab implements ApiReaderFacade {
	
	private static List<TagAntenna> tags = new ArrayList<>();
	private final String CODE_SUCESS_INVENTORY = "9C01";
	private final String CODE_SUCESS_READ = "9C91";
	
	
	public ApiReaderNesslab() {
		OperationUtil.setIpReaderNesslab(OperationUtil.IP_READER_NESSLAB_DEFAULT);
	}
	
	public ApiReaderNesslab(String ip){
		OperationUtil.setIpReaderNesslab(ip);
	}
	

	@Override
	public void executeAction(Command command) throws UnknownHostException, IOException {
		command.execute();
	}

	@Override
	public String getResponse() throws UnknownHostException, IOException {
		ConnectReader connector = ConnectReader.getInstance(OperationUtil.getIpReaderNesslab(), 
				OperationUtil.PORT_READER_NESSLAB);
		return connector.getResponse();
	}

	@Override
	public boolean hasResponse() throws UnknownHostException, IOException {
		ConnectReader connector = ConnectReader.getInstance(OperationUtil.getIpReaderNesslab(), 
				OperationUtil.PORT_READER_NESSLAB);
		return connector.hasResponse();
	}


	@Override
	public void getTagStringRepresentation() throws UnknownHostException, IOException, 
	SessionFullException {
		TagAntenna tmp;
		String response = this.getResponse();
		
		if(response.equals(CODE_SUCESS_INVENTORY)){
			throw new SessionFullException("Session memory is full.");
		} else {
			tmp = new TagAntenna(response);			
			if (tags.contains(tmp)) {
				int index = tags.indexOf(tmp);
				tmp = tags.get(index);
				tmp.setCountReader(tmp.getCountReader()+ 1L);
			} else if (tmp.getAntenna() != null){
				tags.add(tmp);
				System.out.println("Antenna: "+ tmp.getAntenna() + " TAG: " + tmp.getTagRFID());
			}
		} 
		
	}

	@Override
	public List<TagAntenna> getTagsList() {		
		return tags;
	}

	@Override
	public void captureTagsObject() throws UnknownHostException, IOException, SessionFullException {
		TagAntenna tmp;
		String response = this.getResponse();
		tmp = new TagAntenna(response);
		if(response.equals(CODE_SUCESS_INVENTORY)){
			throw new SessionFullException("Session memory is full.");
		} else {
			if (tags.contains(tmp)) {
				int index = tags.indexOf(tmp);
				tmp = tags.get(index);
				tmp.setCountReader(tmp.getCountReader()+ 1L);
			} else {
				tags.add(tmp);
			}
		} 
		
	}

	@Override
	public String getTranslatedResponse() throws UnknownHostException, IOException {
		return TranslateResponse.translate(this.getResponse());
	}

}

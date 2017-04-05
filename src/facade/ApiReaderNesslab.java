package facade;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import exceptions.SessionReaderException;
import interfaces.ApiReaderFacade;
import interfaces.Command;
import utils.ConnectReader;
import utils.OperationUtil;
import utils.TagAntenna;
import utils.TranslateResponse;

public class ApiReaderNesslab implements ApiReaderFacade {
	
	private static List<TagAntenna> tags = new ArrayList<>();
	private final String CODE_SUCESS_INVENTORY = "9C01";
	
	
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
	public void getTagStringRepresentation() throws UnknownHostException, IOException, SessionReaderException {
		TagAntenna tmp;
		String response = this.getResponse();
		//System.out.println(this.getResponse());
		if(!response.equals(CODE_SUCESS_INVENTORY)){
			tmp = new TagAntenna(response);			
			if (tags.contains(tmp)) {
				int index = tags.indexOf(tmp);
				tmp = tags.get(index);
				tmp.setCountReader(tmp.getCountReader()+ 1L);
			} else {
				tags.add(tmp);
				System.out.println("Antenna: "+ tmp.getAntenna() + " TAG: " + tmp.getTagRFID());
			}
		} else {
			System.out.println("lancou!");
			throw new SessionReaderException("Session memory is full.");
		}
		
	}

	@Override
	public List<TagAntenna> getTagsList() {		
		return tags;
	}

	@Override
	public void captureTagsObject() throws UnknownHostException, IOException, SessionReaderException {
		TagAntenna tmp;
		String response = this.getResponse();
		tmp = new TagAntenna(response);
		if(!response.equals(CODE_SUCESS_INVENTORY)){
			if (tags.contains(tmp)) {
				int index = tags.indexOf(tmp);
				tmp = tags.get(index);
				tmp.setCountReader(tmp.getCountReader()+ 1L);
			} else {
				tags.add(tmp);
			}
		} else {
			throw new SessionReaderException("Session memory is full.");
		}
		
	}

	@Override
	public String getTranslatedResponse() throws UnknownHostException, IOException {
		return TranslateResponse.translate(this.getResponse());
	}

}

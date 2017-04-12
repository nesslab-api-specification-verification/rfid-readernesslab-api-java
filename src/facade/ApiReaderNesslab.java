package facade;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import exceptions.SessionFullException;
import interfaces.ApiReaderFacade;
import interfaces.Command;
import utils.ConnectReader;
import utils.OperationUtil;
import utils.TagAntenna;
import utils.TranslateResponse;

public class ApiReaderNesslab implements ApiReaderFacade {
	
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
<<<<<<< HEAD
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
		
=======
		String response = getResponse();
		utils.CaptureTagsRepresentation.getStringRepresentation(response);
>>>>>>> 65d55ee8f1ac3131306926094fe7a2abc97ec1a0
	}

	@Override
	public List<TagAntenna> getTagsList() {		
		return utils.CaptureTagsRepresentation.getTags();
	}

	@Override
	public void captureTagsObject() throws UnknownHostException, IOException, SessionFullException {
		String response = getResponse();
		utils.CaptureTagsRepresentation.getObjectRepresentation(response);
	}

	@Override
	public String getTranslatedResponse() throws UnknownHostException, IOException {
		return TranslateResponse.translate(this.getResponse());
	}

	@Override
	public String getJsonRepresentation() {
		return utils.CaptureTagsRepresentation.getJsonRepresentation();
	}

}

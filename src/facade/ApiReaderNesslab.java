package facade;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import exceptions.SessionFullException;
import interfaces.ApiReaderFacade;
import interfaces.Command;
import utils.CaptureTagsRepresentation;
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

	/**
	 * This executeAction is responsible for executing all commands.
	 * @param command is a command of API.
	 * @throws UnknownHostException Is trown when the host not found.
	 * */
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
		String response = getResponse();
		utils.CaptureTagsRepresentation.getStringRepresentation(response);
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

	@Override
	public boolean hasNewTag() {
		return CaptureTagsRepresentation.haveNewTag();
	}

	@Override
	public String getTagUniqueJsonRepresentation() {
		return CaptureTagsRepresentation.getJsonTagUnique();
	}

}

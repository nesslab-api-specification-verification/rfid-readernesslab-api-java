package api.reader.nesslab.facade;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import api.reader.nesslab.commands.DisableBuzzer;
import api.reader.nesslab.commands.EnableContinueMode;
import api.reader.nesslab.commands.SetPowerControl;
import api.reader.nesslab.commands.SetScanTime;
import api.reader.nesslab.exceptions.SessionFullException;
import api.reader.nesslab.interfaces.ApiReaderFacade;
import api.reader.nesslab.interfaces.Command;
import api.reader.nesslab.utils.CaptureTagsRepresentation;
import api.reader.nesslab.utils.ConnectReader;
import api.reader.nesslab.utils.OperationUtil;
import api.reader.nesslab.utils.TagAntenna;
import api.reader.nesslab.utils.TranslateResponse;

/**
 * Layer access to methods of communication and configuration with the RFID Reader Nesslab RF1000.   
 * */
public class ApiReaderNesslab implements ApiReaderFacade {
	
	/**
	 * Default constructor, the IP is set according with specification(192.168.10.91). 
	 * **/
	public ApiReaderNesslab() {
		OperationUtil.setIpReaderNesslab(OperationUtil.IP_READER_NESSLAB_DEFAULT);
	}
	
	/**
	 * Contructor with ip parameter, when the ip is different of default ip.
	 * @param command is a command of API.
	*/
	public ApiReaderNesslab(/*@ non_null @*/String ip){
		OperationUtil.setIpReaderNesslab(ip);
	}

	/**
	 * Is responsible for executing all commands.
	 * @param command is a command of API.
	 * @throws UnknownHostException Is trown when the host not found.
	 * @throws IOException Is trown when any failure I/O ocurred.
	 */
	@Override
	public void executeAction(Command command) throws UnknownHostException, IOException {
		command.execute();
	}

	/**
	 * Is responsible for to return the response of commands.
	 * @return Response of reader.
	 * @throws UnknownHostException Is trown when the host not found.
	 * @throws IOException Is trown when any failure I/O ocurred.
	 * */
	@Override
	public String getResponse() throws UnknownHostException, IOException {
		ConnectReader connector = ConnectReader.getInstance(OperationUtil.getIpReaderNesslab(), 
				OperationUtil.PORT_READER_NESSLAB);
		return connector.getResponse();
	}

	/**
	 * Check if exists any response of Reader.
	 * @return True if exists response or false if not exist.
	 * @throws UnknownHostException Is trown when the host not found.
	 * @throws IOException Is trown when any failure I/O ocurred.
	 * */
	@Override
	public boolean hasResponse() throws UnknownHostException, IOException {
		ConnectReader connector = ConnectReader.getInstance(OperationUtil.getIpReaderNesslab(), 
				OperationUtil.PORT_READER_NESSLAB);
		return connector.hasResponse();
	}


	/**
	 * To return all tags captured by methods getTagStringRepresentation() and captureTagsObject().
	 * @return Set tag read by methods. 
	 * @throws UnknownHostException Is trown when the host not found.
	 * @throws IOException Is trown when any failure I/O ocurred.
	 * */
	@Override
	public List<TagAntenna> getTagsList() {		
		return api.reader.nesslab.utils.CaptureTagsRepresentation.getTags();
	}

	/**
	 * Captures and encapsulates the tag in a TagAntenna object and adds to the list of tags.
	 * @throws UnknownHostException Is trown when the host not found.
	 * @throws IOException Is trown when any failure I/O ocurred.
	 * */
	@Override
	public void captureTagsObject() throws UnknownHostException, IOException, SessionFullException {
		String response = getResponse();
		api.reader.nesslab.utils.CaptureTagsRepresentation.getObjectRepresentation(response);
	}

	/**
	 * Translates the response of reader by a understandable pattern.
	 * @return Response translated.
	 * @throws UnknownHostException Is trown when the host not found.
	 * @throws IOException Is trown when any failure I/O ocurred.
	 * */
	@Override
	public String getTranslatedResponse() throws UnknownHostException, IOException {
		return TranslateResponse.translate(this.getResponse());
	}

	/**
	 * To return a json representation of one reading.
	 * @return Set of tags read.
	 * */
	@Override
	public /*@ pure @*/ String getJsonRepresentation() {
		return api.reader.nesslab.utils.CaptureTagsRepresentation.getJsonRepresentation();
	}

	/**
	 * To return a json representation of one reading.
	 * @return Set of tags read.
	 * */
	@Override
	public boolean hasNewTag() {
		return CaptureTagsRepresentation.haveNewTag();
	}

	/**
	 * To return a json representation of each tag read.
	 * @return Tag read.
	 * */
	@Override
	public String getTagUniqueJsonRepresentation() {
		return CaptureTagsRepresentation.getJsonTagUnique();
	}

	/**
	 * Temporarily clean memory of API for recapture tags.
	 * @param secondsPeriod is the period witch cleaning must be executed intermittently.  
	 * */
	@Override
	public void clearTemporaryMemory(int secondsPeriod) {
		CaptureTagsRepresentation.clearMemory(secondsPeriod);
		
	}
	
	/**
	 * To configure api with everyone necessary configuration for default run. 
	 * The configurarions are: 1ms of scan time, buzzer disabled, power control with 250dbm and continue mode enabled.
	 * @throws UnknownHostException Is trown when the host not found.
	 * @throws IOException Is trown when any failure I/O ocurred.
	 * */
	@Override
	public final void defaultConfiguration() throws UnknownHostException, IOException{
		executeAction(new SetScanTime(1L));
		executeAction(new DisableBuzzer());		
		executeAction(new SetPowerControl("250"));
		executeAction(new EnableContinueMode());
	}

	/**
	 * To return a object representation of each tag read, if haveNewTag() return false, this return to be null.
	 * @return Tag read.
	 * */
	@Override
	public TagAntenna getTag() {
		return CaptureTagsRepresentation.getTag();
	}

}

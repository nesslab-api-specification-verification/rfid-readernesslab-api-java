package facade;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import interfaces.ApiReaderFacade;
import interfaces.Command;
import utils.ConnectReader;
import utils.OperationUtil;
import utils.TagAntenna;

public class ApiReaderNesslab implements ApiReaderFacade {
	
	private Command command;
	private static List<TagAntenna> tags = new ArrayList<>();

	@Override
	public void executeAction(Command command) throws UnknownHostException, IOException {
		this.command.execute();
	}

	@Override
	public String getResponse() throws UnknownHostException, IOException {
		ConnectReader connector = ConnectReader.getInstance(OperationUtil.IP_READER_NESSLAB, 
				OperationUtil.PORT_READER_NESSLAB);
		return connector.getResponse();
	}

	@Override
	public boolean hasResponse() throws UnknownHostException, IOException {
		ConnectReader connector = ConnectReader.getInstance(OperationUtil.IP_READER_NESSLAB, 
				OperationUtil.PORT_READER_NESSLAB);
		return connector.hasResponse();
	}


	@Override
	public void getTagStringRepresentation() throws UnknownHostException, IOException {
		TagAntenna tmp;
		tmp = new TagAntenna(this.getResponse());
		
		if (tags.contains(tmp)) {
			int index = tags.indexOf(tmp);
			tmp = tags.get(index);
			tmp.setCountReader(tmp.getCountReader()+ 1L);
		} else {
			tags.add(tmp);
			System.out.println("Antenna: "+ tmp.getAntenna() + " TAG: " + tmp.getTagRFID());
		}
		
	}

	@Override
	public List<TagAntenna> getTagsList() {		
		return tags;
	}

	@Override
	public void captureTagsObject() throws UnknownHostException, IOException {
		TagAntenna tmp;
		tmp = new TagAntenna(this.getResponse());
		
		if (tags.contains(tmp)) {
			int index = tags.indexOf(tmp);
			tmp = tags.get(index);
			tmp.setCountReader(tmp.getCountReader()+ 1L);
		} else {
			tags.add(tmp);
		}
		
	}

}

package api.reader.nesslab.utils;

public class TagAntenna {

	private /*@ spec_public nullable @*/ String uniqID;
	private /*@ spec_public nullable @*/ String antenna;	
	private /*@ spec_public nullable @*/ String tagRFID;
	private /*@ spec_public@*/ long countReader;
	
	public TagAntenna(String uniqID) {
		this.uniqID = uniqID;
		String[] tagANDantena = this.uniqID.split("T");
		if(tagANDantena.length == 2){
			this.antenna = tagANDantena[0];
			this.tagRFID = tagANDantena[1];
			this.countReader = 1;
		}
	}

	public /*@ pure @*/String getAntenna() {
		return antenna;
	}
	
	//@ assignable this.antenna;
	//@ ensures this.antenna == antenna;
	public void setAntenna(String antenna) {
		this.antenna = antenna;
	}

	public String getTagRFID() {
		return tagRFID;
	}

	public void setTagRFID(String tagRFID) {
		this.tagRFID = tagRFID;
	}

	public long getCountReader() {
		return countReader;
	}

	public void setCountReader(long countReader) {
		this.countReader = countReader;
	}
	
	public void incrementCounter() {
		this.setCountReader(this.getCountReader() + 1); 
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TagAntenna other = (TagAntenna) obj;
		if (tagRFID == null) {
			if (other.tagRFID != null)
				return false;
		} else if (!tagRFID.equals(other.tagRFID))
			return false;
		return true;
	}
	
	
	
}

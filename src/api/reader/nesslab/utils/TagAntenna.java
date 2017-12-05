package api.reader.nesslab.utils;

public class TagAntenna {

	private /*@ spec_public nullable @*/ String uniqID;
	private /*@ spec_public nullable @*/ String antenna;	
	private /*@ spec_public nullable @*/ String tagRFID;
	private /*@ spec_public@*/ long countReader;
	
	/*@
	 @  requires !(uniqID.split("T").length == 2);
	 @  assignable this.uniqID;
	 @  ensures this.uniqID.equals(uniqID);
	 @ also
	 @  requires (uniqID.split("T").length == 2);
	 @  assignable antenna, tagRFID, countReader;
	 @  ensures this.uniqID.equals(uniqID) && antenna.equals(uniqID.split("T")[0]) && tagRFID.equals(uniqID.split("T")[1]) && countReader == 1;
	 @*/
	public TagAntenna(String uniqID) {
		this.uniqID = uniqID;
		String[] tagANDantena = this.uniqID.split("T");
		if(tagANDantena.length == 2){
			this.antenna = tagANDantena[0];
			this.tagRFID = tagANDantena[1];
			this.countReader = 1;
		}
	}

	//@ ensures \result == this.antenna;
	public /*@ pure @*/ String getAntenna() {
		return antenna;
	}
	
	//@ assignable this.antenna;
	//@ ensures this.antenna == antenna;
	public void setAntenna(String antenna) {
		this.antenna = antenna;
	}

	//@ ensures \result == this.tagRFID;
	public /*@ pure @*/String getTagRFID() {
		return tagRFID;
	}

	//@ assignable this.tagRFID;
	//@ ensures this.tagRFID == tagRFID;
	public void setTagRFID(String tagRFID) {
		this.tagRFID = tagRFID;
	}

	//@ ensures \result == this.countReader;
	public /*@ pure @*/long getCountReader() {
		return countReader;
	}

	//@ assignable countReader;
	//@ ensures this.countReader == countReader;
	public void setCountReader(long countReader) {
		this.countReader = countReader;
	}
	
	//@ assignable countReader;
	//@ ensures this.countReader == \old(this.countReader)+1;
	public void incrementCounter() {
		this.setCountReader(this.getCountReader() + 1); 
	}

	@Override
	/*@also
	 @  requires this == obj || (tagRFID == null && ((TagAntenna) obj).tagRFID==null) || (tagRFID != null && tagRFID.equals(((TagAntenna) obj).tagRFID));
	 @  ensures \result == true;
	 @ also
	 @  requires obj == null || (getClass() != obj.getClass()) || (tagRFID == null && ((TagAntenna) obj).tagRFID!=null) || (tagRFID != null && !tagRFID.equals(((TagAntenna) obj).tagRFID));
	 @  ensures \result == false;
	 @*/
	public /*@ pure @*/ boolean equals(Object obj) {
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
			else
				return true;
		} else if (!tagRFID.equals(other.tagRFID))
			return false;
		else
			return true;
	}
	
	
	
}

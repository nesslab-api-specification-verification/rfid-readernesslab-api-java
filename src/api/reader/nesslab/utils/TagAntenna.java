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
	public String getTagRFID() {
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
	/*@ 
	 @ also
	 @  requires this == obj || (tagRFID==null && ((TagAntenna) obj).tagRFID==null);
	 @  ensures \result == true;
	 @ also
	 @  requires obj == null || getClass() != obj.getClass() || (tagRFID==null && ((TagAntenna) obj).tagRFID!=null) || (tagRFID!=null && !tagRFID.equals(((TagAntenna) obj).tagRFID) );
	 @  ensures \result == false;
	 @*/
	public /*@ pure @*/ boolean equals(Object obj) {
		boolean teste = true;
		if (this == obj)
			teste = true;
		if (obj == null)
			teste = false;
		if (getClass() != obj.getClass())
			teste = false;
		TagAntenna other = (TagAntenna) obj;
		if (tagRFID == null) {
			if (other.tagRFID != null)
				teste = false;
		} else if (!tagRFID.equals(other.tagRFID))
			teste = false;
		else
			teste = true;
		return teste;
	}
	
	
	
}

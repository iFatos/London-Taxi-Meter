package com.conjure.LondonTaxiMeter;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;



public class MyHandler extends DefaultHandler{
	private boolean kml_PlaceMark = false;
	private boolean kml_name = false;
	private boolean kml_description = false;
	
	private ParsedData data = new ParsedData();
	
	public ParsedData getParsedData(){
		return this.data;
	}
	
	@Override
    public void startElement(String namespaceURI, String localName,String qName, Attributes atts) throws SAXException {
		if(localName.equalsIgnoreCase("PlaceMark")){
			
			this.kml_PlaceMark = true;
		}else if(localName.equalsIgnoreCase("name")){
			
			this.kml_name = true;
		}else if(localName.equalsIgnoreCase("description")){
			
			this.kml_description = true;
		}
    }
	
	@Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
		if(localName.equalsIgnoreCase("PlaceMark")){
			
			this.kml_PlaceMark = false;
		}else if(localName.equalsIgnoreCase("name")){
			
			this.kml_name = false;
		}else if(localName.equalsIgnoreCase("description")){
			
			this.kml_description = false;
		}
    }
	
	@Override
    public void characters(char ch[], int start, int length) {   
           if(this.kml_description){ 		
               	data.setParsedString(new String(ch,start,length));   			
           }           
    }
	
	
	

}

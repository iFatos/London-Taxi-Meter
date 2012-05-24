package com.conjure.LondonTaxiMeter;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.util.Log;

public class KMLParser {
    /** Called when the activity is first created. */
	private String urlS = null;
	int distance = 0;
	int minutes = 0;
	String journeyTime;
    
    public KMLParser(String urlS){
    	this.urlS = urlS;
    	 try{
         	URL url = new URL(this.urlS);
         	SAXParserFactory spf = SAXParserFactory.newInstance();
             SAXParser sp = spf.newSAXParser();
             MyHandler handler = new MyHandler();
             XMLReader xr = sp.getXMLReader();
             xr.setContentHandler(handler);
             xr.parse(new InputSource(url.openStream()));
             
             ParsedData data = handler.getParsedData();
             
             distance = getDistance(data.getParsedString());
             
         }catch(Exception e){
         	Log.e("Internet", "is down down down down and distance is: " + distance);
         }
    }
    
    
    public int getDistanceInMetres(){
    	return distance;
    }
    public int getTimeOfTrip(){
    	return minutes;
    }
    public String getJourneyTime(){
    	return journeyTime;
    }
    public int getDistance(String description){
		double distance = 0;
		String[] arrTokens = description.split(" ");
		System.out.println("arrTokens: " + arrTokens.toString());
		
		String distanceText = arrTokens[1];
		String[] measure = distanceText.split(";");
		String[] distNum = measure[0].split("&#");
		for(int m=0;m<arrTokens.length;m++){
			System.out.println("arrTokens:" + arrTokens[m]);
		}
		
		System.out.println("distanceText: " + distanceText.toString());
		System.out.println("distNum: " + distNum.toString());
		
		if(measure[1].equalsIgnoreCase("km")){
			distance = Double.parseDouble(distNum[0]) * 1000;
		}else if(measure[1].equalsIgnoreCase("mi")){
			distance = Double.parseDouble(distNum[0]) * 1609.344;
		}
		if(arrTokens[4].contains("mins")){
			journeyTime = arrTokens[3];
		}else if(arrTokens[4].contains("hour") || arrTokens[4].contains("hours")){
			journeyTime = arrTokens[3] + " " + arrTokens[4] + " " + arrTokens[5];
		}
	    minutes = Integer.parseInt(arrTokens[3]);
		return (int)distance;
	}
}
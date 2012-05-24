package com.conjure.LondonTaxiMeter;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class CustomTimePicker extends Dialog{
	String timeStr;
	String ampm;
	public TextView hourText, minText, timeTitle, ampmText;
	public Button hourPlus, hourMinus;
	public Button minPlus,minMinus;
	public Button btn_Set, btn_Cancel;
	
	public int hour,minute;
	public Calendar calendar;
	
	public CustomTimePicker(Context context){
		super(context);
	}
	
	public CustomTimePicker(Context context,Calendar calendar, int theme){
		super(context,theme);
		this.calendar = calendar;
		
	}
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_timepicker);
        
        btn_Set = (Button)this.findViewById(R.id.btn_Set);
        btn_Cancel =(Button)this.findViewById(R.id.btn_Cancel);
        timeTitle = (TextView)this.findViewById(R.id.timeTextBox);
        
        hourText = (TextView)findViewById(R.id.hourText);
        minText = (TextView)findViewById(R.id.minText);
        ampmText = (TextView)findViewById(R.id.ampmText);
        
        hourPlus = (Button)findViewById(R.id.hourPlus);
        hourMinus = (Button)findViewById(R.id.hourMinus);
        minPlus = (Button)findViewById(R.id.minPlus);
        minMinus = (Button)findViewById(R.id.minMinus);
        
       
        
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        timeStr = pad(hour) + (":")+ pad(minute) + (hour >= 12 ? " PM" : " AM");
        ampm = (hour >= 12 ? " PM" : " AM");
        Log.e("CTPhour","hour is: " + hour);
        Log.e("CTPminute","minute is: " + minute);
        
        hourText.setText("" + hour);
        minText.setText("" + minute);
        ampmText.setText(ampm);
        timeTitle.setText(timeStr);
      
        
        
        btn_Set.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v){   	
        		FareCalculator.hourOfDay = hour;      				
        		hourText.setText(""+pad(hour));
        	    minText.setText(""+pad(minute));
        	    ampmText.setText("" + (hour >= 12 ? " PM" : " AM"));        	    
        	    TaxiMeterActivity.calendarTime = calendar;
        	    TaxiMeterActivity.time.setText(""+ timeStr);
        		dismiss();
        	}
        });
        btn_Cancel.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v){   		
        		dismiss();
        	}
        });
        
        
        
        
        hourPlus.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v){         		
        		if(hour >= 23 && minute >= 0){
        			hour = 0;
        			hourText.setText(""+pad(hour));
        		}else{
        			hour = hour + 1;
        			hourText.setText(""+pad(hour));
        		}      		
        		timeStr = pad(hour) + (":")+ pad(minute) + (hour >= 12 ? " PM" : " AM");
        		timeTitle.setText(timeStr);
        		ampm = (hour >= 12 ? " PM" : " AM");
        		ampmText.setText(ampm);      		
        		calendar = new GregorianCalendar(TaxiMeterActivity.calDay,TaxiMeterActivity.calMonth,TaxiMeterActivity.calYear,hour,minute);
        	}
        });
        hourMinus.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v){   		
        		if(hour <= 0){
        			hour = 23;
        			hourText.setText(""+pad(hour));
        		}else{
        			hour = hour - 1;
        			hourText.setText(""+pad(hour));
        		}
        		timeStr = pad(hour) + (":")+ pad(minute) + (hour >= 12 ? " PM" : " AM");
        		timeTitle.setText(timeStr);
        		ampm = (hour >= 12 ? " PM" : " AM");
        		ampmText.setText(ampm);
        		calendar = new GregorianCalendar(TaxiMeterActivity.calDay,TaxiMeterActivity.calMonth,TaxiMeterActivity.calYear,hour,minute);           	
        	}
        });
        minPlus.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v){   		
        		if(minute >= 59){
        			minute = 0;
        			minText.setText("" + pad(minute));
        			hourCalcPlus();
        		}else{
        			minute = minute + 1;
        			minText.setText("" + pad(minute));
        			
        		}
        		timeStr = pad(hour) + (":")+ pad(minute) + (hour >= 12 ? " PM" : " AM");
        		timeTitle.setText(timeStr);
        		ampm = (hour >= 12 ? " PM" : " AM");
        		ampmText.setText(ampm);      		
        		calendar = new GregorianCalendar(TaxiMeterActivity.calDay,TaxiMeterActivity.calMonth,TaxiMeterActivity.calYear,hour,minute);
 
        	}
        	
        	public void hourCalcPlus(){
        		if(hour == 23){
        			hour = 0;
        			hourText.setText(""+pad(hour));
        		}else{
        			hour = hour + 1;
        			hourText.setText(""+pad(hour));
        		}      		
        		
        	}
        	
        	
        });
        minMinus.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v){   		
        		if(minute <= 0){
        			minute = 59;
        			minText.setText("" + pad(minute));
        			hourCalcMinus();
        		}else{
        			minute = minute - 1;
        			minText.setText("" + pad(minute));
        		}
        		timeStr = pad(hour) + (":")+ pad(minute) + (hour >= 12 ? " PM" : " AM");
        		timeTitle.setText(timeStr);
        		ampm = (hour >= 12 ? " PM" : " AM");
        		ampmText.setText(ampm);
        		calendar = new GregorianCalendar(TaxiMeterActivity.calDay,TaxiMeterActivity.calMonth,TaxiMeterActivity.calYear,hour,minute);
            	
        	}
        	public void hourCalcMinus(){
        		if(hour <= 0){
        			hour = 23;
        			hourText.setText(""+hour);
        		}else{
        			hour = hour - 1;
        			hourText.setText(""+pad(hour));
        		}
        		
        	}
        });
        
        
        
    }
    
   
    
    public String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    @Override
	public void show(){		
		super.show();
	}
    
}

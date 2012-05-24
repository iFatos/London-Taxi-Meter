package com.conjure.LondonTaxiMeter;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CustomDatePicker extends Dialog{
	
	String date;
	String dayOfWeekTaxi;
	String dayTaxi;
	String monthTaxi;
	Button btn_Set,btn_Cancel;
	Calendar calendar;
	TextView dateTitle;
	private int mYear;
    private int mMonth;
    private int mDay;
    
	//declaring TextViews
	public EditText dayText, monthText, yearText;
	//declaring Plus Button 
	public Button dayPlus, dayMinus;
	public Button monthPlus, monthMinus;
	public Button yearPlus, yearMinus;
	
	public CustomDatePicker(Context context){		
		super(context);
	}
	public CustomDatePicker(Context context, Calendar calendar, int theme){	
		super(context,theme);
		this.calendar = calendar;		
	}
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_datepicker);  
        
        btn_Set = (Button)this.findViewById(R.id.btn_Set);
        btn_Cancel =(Button)this.findViewById(R.id.btn_Cancel);
        dateTitle = (TextView)this.findViewById(R.id.dateTextBox);
        
        dayText = (EditText)this.findViewById(R.id.dateText);
        monthText = (EditText)this.findViewById(R.id.monthText);
        yearText = (EditText)this.findViewById(R.id.yearText);
        
        dayPlus = (Button)this.findViewById(R.id.datePlus);
        dayMinus = (Button)this.findViewById(R.id.dateMinus);
        monthPlus = (Button)this.findViewById(R.id.monthPlus);
        monthMinus = (Button)this.findViewById(R.id.monthMinus);
        yearPlus = (Button)this.findViewById(R.id.yearPlus);
        yearMinus = (Button)this.findViewById(R.id.yearMinus);
        
       
        
        
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        updateDateDisplay(calendar.get(Calendar.DAY_OF_WEEK));
        dayText.setText(""+pad(mDay));     
        monthText.setText(""+pad(mMonth+1));
        yearText.setText(""+pad(mYear));
        Log.e("CurrentDate","CurrentDate is : " + date);
        dateTitle.setText(date);
        
        Log.e("mDay","mDay is +==================" + mDay);
        btn_Set.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v){
        		TaxiMeterActivity.date.setText(dayOfWeekTaxi + "," + dayTaxi + " " + monthTaxi);
        		FareCalculator.dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        		FareCalculator.dayOfYear = mDay;
        		FareCalculator.monthOfYear = mMonth;
        		TaxiMeterActivity.calendar = calendar;
        		Log.e("DayOfWeek","the day of the selected week is: " + FareCalculator.dayOfWeek);
        		dismiss();
        		
        	}
        });
        btn_Cancel.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v){   		
        		dismiss();
        	}
        });
        
        dayText.setOnKeyListener(new View.OnKeyListener(){
        	public boolean onKey(View view, int keyCode,KeyEvent event) {
        		if(keyCode == KeyEvent.KEYCODE_ENTER){
        			mDay = Integer.parseInt(dayText.getText().toString());
        			calendar = new GregorianCalendar(mYear,mMonth,mDay);
            		updateDateDisplay(calendar.get(Calendar.DAY_OF_WEEK));
            		dateTitle.setText(date);
        		}
        		return false;
        	}
        });
        monthText.setOnKeyListener(new View.OnKeyListener(){
        	public boolean onKey(View view, int keyCode,KeyEvent event) {
        		if(keyCode == KeyEvent.KEYCODE_ENTER){
        			mMonth = Integer.parseInt(monthText.getText().toString());
        			calendar = new GregorianCalendar(mYear,mMonth,mDay);
            		updateDateDisplay(calendar.get(Calendar.DAY_OF_WEEK));
            		dateTitle.setText(date);
        		}
        		return false;
        	}
        });
        yearText.setOnKeyListener(new View.OnKeyListener(){
        	public boolean onKey(View view, int keyCode,KeyEvent event) {
        		if(keyCode == KeyEvent.KEYCODE_ENTER){
        			mYear = Integer.parseInt(yearText.getText().toString());
        			calendar = new GregorianCalendar(mYear,mMonth,mDay);
            		updateDateDisplay(calendar.get(Calendar.DAY_OF_WEEK));
            		dateTitle.setText(date);
        		}
        		return false;
        	}
        });
        
        dayPlus.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v){
        		String dayTemp = dayText.getText().toString();
        		int temp = Integer.valueOf(dayTemp);
        		//countDaysPlus(temp);
        		
        
        		if(temp >= calendar.getActualMaximum(Calendar.DAY_OF_MONTH)){
        			if(mMonth >= 11){
        				
    	    	    		mYear = mYear + 1;
    	    	    		yearText.setText("" + pad(mYear));
            			
        	    		mMonth = 0;
        	    		monthText.setText("" + pad(mMonth+1));
        	    	}else{
        	    		mMonth = mMonth + 1;
        	    		monthText.setText("" + pad(mMonth+1));
        	    	}
            
            		mDay = 1;
            		dayText.setText("" + pad(mDay));
            	}else{
            		mDay = mDay + 1;
            		dayText.setText("" + pad(mDay));
            	}
        		calendar = new GregorianCalendar(mYear,mMonth,mDay);
        		updateDateDisplay(calendar.get(Calendar.DAY_OF_WEEK));
        		dateTitle.setText(date);
        		
        		
        	}
        });
        dayMinus.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v){
        		String dayTemp = dayText.getText().toString();
        		int temp = Integer.valueOf(dayTemp);
        		//countDaysMinus(temp);
        		
        		if(temp <= 1){    				
            		//countMonthsMinus(mMonth);
        			if(mMonth <= 0){
        				if(mYear <= 2011){
            				
            			}else{
    	    	    		mYear = mYear - 1;
    	    	    		yearText.setText("" + pad(mYear));
            			}
        	    		mMonth = 11;
        	    		monthText.setText("" + pad(mMonth+1));
        	    	}else{
        	    		mMonth = mMonth - 1;
        	    		monthText.setText("" + pad(mMonth+1));
        	    	}
        			calendar = new GregorianCalendar(mYear,mMonth,mDay);
        			mDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);            		
            		dayText.setText(""+mDay);         		
            	}else{
            		mDay = mDay - 1;
            		dayText.setText("" + pad(mDay));
            	}
        		calendar = new GregorianCalendar(mYear,mMonth,mDay);
        		updateDateDisplay(calendar.get(Calendar.DAY_OF_WEEK));
        		dateTitle.setText(date);
        	}
        });
        monthPlus.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v){
        		String dayTemp = monthText.getText().toString();
        		int temp = Integer.valueOf(dayTemp);
        		if(temp >= 12){
        			
	    	    		mYear = mYear + 1;
	    	    		yearText.setText("" + pad(mYear));
        		
    	    		mMonth = 0;
    	    		monthText.setText("" + pad(mMonth+1));
    	    	}else{
    	    		mMonth = mMonth + 1;
    	    		monthText.setText("" + pad(mMonth+1));  	    		
    	    	}
        		calendar = new GregorianCalendar(mYear,mMonth,mDay);
        		updateDateDisplay(calendar.get(Calendar.DAY_OF_WEEK));
        		dateTitle.setText(date);
        	}
        });
        monthMinus.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v){
        		String dayTemp = monthText.getText().toString();
        		int temp = Integer.valueOf(dayTemp);
        		//countMonthsMinus(temp);
        		if(temp <= 1){
        			if(mYear <= 2011){
        				
        			}else{
	    	    		mYear = mYear - 1;
	    	    		yearText.setText("" + pad(mYear));
        			}
    	    		mMonth = 11;
    	    		monthText.setText("" + pad(mMonth+1));
    	    	}else{
    	    		mMonth = mMonth - 1;
    	    		monthText.setText("" + pad(mMonth+1));
    	    	}
        		calendar = new GregorianCalendar(mYear,mMonth,mDay);
        		updateDateDisplay(calendar.get(Calendar.DAY_OF_WEEK));
        		dateTitle.setText(date);
        	}
        });
        yearPlus.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v){
        		String dayTemp = yearText.getText().toString();
        		int temp = Integer.valueOf(dayTemp);
        		
	        		yearText.setText(""+pad((temp+1)));
	        		mYear = temp+1;
        		
        		calendar = new GregorianCalendar(mYear,mMonth,mDay);
        		updateDateDisplay(calendar.get(Calendar.DAY_OF_WEEK));
        		dateTitle.setText(date);
        	}
        });
        yearMinus.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v){
        		String dayTemp = yearText.getText().toString();
        		int temp = Integer.valueOf(dayTemp);
        		if(temp <= 2011){
        			
        		}else{
	        		yearText.setText(""+(temp-1));
	        		mYear = temp-1;
        		}
        		calendar = new GregorianCalendar(mYear,mMonth,mDay);
        		updateDateDisplay(calendar.get(Calendar.DAY_OF_WEEK));
        		dateTitle.setText(date);
        	}
        	
        });
   
        
	}
	
	public void setDate(int day,int month,int year){
		StringBuffer temp = new StringBuffer();
		calendar = new GregorianCalendar(year,month,day);
        temp.append(" " + getDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK))).append(",").append(day).append(" ")
                .append(getMonthName(month)).append(" ").append(year);
        date = temp.toString();
        
        dayText.setText(day);
        monthText.setText(month);
        yearText.setText(year);
	}
	
	
	public String getDayOfWeek(int day){
    	switch(day){
    		case 1:
    			return "Sun";
    		case 2:
    			return "Mon";
    		case 3:
    			return "Tue";
    		case 4:
    			return "Wed";
    		case 5:
    			return "Thur";
    		case 6:
    			return "Fri";
    		case 7:
    			return "Sat";
    	}
    	return null;
    }
    public String getMonthName(int month){
    	switch(month){
    	case 0:
    		return "Jan";
    	case 1: 
    		return "Feb";
    	case 2:
    		return "Mar";
    	case 3:
    		return "Apr";
    	case 4:
    		return "May";
    	case 5:
    		return "Jun";
    	case 6:
    		return "Jul";
    	case 7:
    		return "Aug";
    	case 8:
    		return "Sep";
    	case 9: 
    		return "Oct";
    	case 10:
    		return "Nov";
    	case 11:
    		return "Dec";
    	}
    	return null;
    }

   public static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }
 
    
    
    
    
    public String getDateText(){
    	return date;
    }
	
    
    private void updateDateDisplay(int dayOfWeek) {   	
        StringBuffer temp = new StringBuffer();
       
            temp.append(" " + getDayOfWeek(dayOfWeek)).append(",").append(mDay).append(" ")
                    .append(getMonthName(mMonth)).append(" ").append(mYear);
        
        date = temp.toString();
        dayOfWeekTaxi = getDayOfWeek(dayOfWeek);
        dayTaxi = ""+mDay;
        monthTaxi = getMonthName(mMonth);
        TaxiMeterActivity.calDay = mDay;
        TaxiMeterActivity.calMonth = mMonth;
        TaxiMeterActivity.calYear = mYear;
    }
	
	
	@Override
	public void show(){		
		super.show();
	}
	
}

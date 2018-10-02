package com.incometaxcalculator.cit;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Incometax extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_incometax);
		
		
		setContentView(R.layout.activity_incometax);
		 Button next = (Button) findViewById(R.id.btchkincrement);
		 Button onlyincometax = (Button) findViewById(R.id.btinctxcal);
         next.setOnClickListener(this);
         onlyincometax.setOnClickListener(this);
	}

	
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		
		switch(arg0.getId())
		{
		case R.id.btchkincrement:
			 
			
			Intent intentmain = new Intent("com.incometaxcalculator.cit.TAX_MAIN");
		    startActivity(intentmain);	
			
			
			break;
 
			
		case R.id.btinctxcal:
			
			Intent intentincometax = new Intent("com.incometaxcalculator.cit.ONLYTAX");
			startActivity(intentincometax);
			
			break;
			
			
		}
		
	}
	
	
	 

}

package com.incometaxcalculator.cit;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class OnlyTax extends Activity implements OnClickListener {

	String usercategory;
	String[] category = new String[] { "Male", "Female",
			"Senior Citizen(>60 years)", "Super Senior(>80 years)" };
	Spinner Spcategory;
	double incomed = 0.0;
	double deductionsd = 0.0;
	double aftertaxd = 0.0;
	double salaryd = 0.0;
	String incomes = "0.0";
	String deductionss = "0.0";
	double gettaxamount = 0.0;
	String aftertaxs = "";

	
	Double malelowerlimit = 250000.00;
	Double femalelowerlimit = 250000.00;
	Double senior60lowerlimit = 300000.00;
	Double senior80lowerlimit = 500000.00;

	Double ceilinglimit1 = 500000.00;
	Double ceilinglimit2 = 1000000.00;

	Double tax = 0.00;
	EditText income;
	EditText deductions;
	EditText aftertax;
	EditText incometax;
	double tax1 = 0.0;
	double tax2 = 0.0;
	double tax3 = 0.0;
	double taxableincomed = 0.0;
	double totaltaxd = 0.0;
	double incaftincd = 0.0;
	double incperd = 0.0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.onlytaxxml);
		income = (EditText) findViewById(R.id.etoincome);
		deductions = (EditText) findViewById(R.id.etodeductions);
		aftertax = (EditText) findViewById(R.id.etoinaftertax);
		
		Spcategory = (Spinner) findViewById(R.id.spocategory);

		incometax = (EditText) findViewById(R.id.etotax);
		Button checktax = (Button) findViewById(R.id.btochecktax);

		ArrayAdapter<String> adaptcat = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, category);
		Spcategory.setAdapter(adaptcat);

		checktax.setOnClickListener(this);

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

		switch (arg0.getId())

		{
		case R.id.btochecktax:
			if ((Spcategory.getSelectedItem()) != null) {

				usercategory = Spcategory.getSelectedItem().toString();

				getvalues();
				gettaxamount = calculate(incomed, deductionsd, usercategory);
				displayvalues();

			}

		}
	}

	public void getvalues() {
		try {
			incomes = income.getText().toString();
			deductionss = deductions.getText().toString();
	

			incomed = Double.valueOf(incomes);
			deductionsd = Double.valueOf(deductionss);
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

	}

	public void displayvalues()

	{
		aftertax.setText(String.valueOf(taxableincomed - gettaxamount));
		incometax.setText(String.valueOf(gettaxamount));

	}

	public double calculate(double incomed, double deductionsd,
			String usercategory) {

taxableincomed = (incomed-deductionsd);
		
		
		switch(usercategory)
		{
		
		case "Male":
			
		 
		 
		  
			
			 
			 if(taxableincomed >ceilinglimit2)
     		 {
				 
				 tax3  = (taxableincomed-ceilinglimit2) *30/100;
				 tax2= (ceilinglimit2-ceilinglimit1)*20/100;
				 tax1= (ceilinglimit1-malelowerlimit)*10/100;
				 
				 totaltaxd = tax1+tax2+tax3;
				 
				 
				 
			 }
			 
			 else if( taxableincomed >ceilinglimit1)
			 {
				 
				 tax2= (taxableincomed-ceilinglimit1)*20/100;
				 tax1= (ceilinglimit1-malelowerlimit)*10/100;
				 
				 totaltaxd = tax1+tax2;
			 }
			 
			 else if(taxableincomed>malelowerlimit)
			 {
				 tax1= (taxableincomed-malelowerlimit)*10/100;
				 totaltaxd = tax1;
				  			 
			 }
			
			 else
			 {
				 totaltaxd=0.0;
			 }
			 
		 
		 
		
			
			 
			 	 
		 
			
			break;
			
		case "Female":
			
			 if(taxableincomed >ceilinglimit2)
     		 {
				 
				 tax3  = (taxableincomed-ceilinglimit2) *30/100;
				 tax2= (ceilinglimit2-ceilinglimit1)*20/100;
				 tax1= (ceilinglimit1-femalelowerlimit)*10/100;
				 
				 totaltaxd = tax1+tax2+tax3;
			 }
			 
			 else if( taxableincomed >ceilinglimit1)
			 {
				 
				 tax2= (taxableincomed-ceilinglimit1)*20/100;
				 tax1= (ceilinglimit1-femalelowerlimit)*10/100;
				 
				 totaltaxd = tax1+tax2;
			 }
			 
			 else if(taxableincomed>femalelowerlimit)
			 {
				 tax1= (taxableincomed-femalelowerlimit)*10/100;
				 totaltaxd = tax1;
				  			 
			 }
			
			 else
			 {
				 totaltaxd=0.0;
			 }
			 
		 
		 break;
		
				 
			
		case "Senior Citizen(>60 years)":
		
			 
			 if(taxableincomed >ceilinglimit2)
     		 {
				 
				 tax3  = (taxableincomed-ceilinglimit2) *30/100;
				 tax2= (ceilinglimit2-ceilinglimit1)*20/100;
				 tax1= (ceilinglimit1-senior60lowerlimit)*10/100;
				 
				 totaltaxd = tax1+tax2+tax3;
			 }
			 
			 else if( taxableincomed >ceilinglimit1)
			 {
				 
				 tax2= (taxableincomed-ceilinglimit1)*20/100;
				 tax1= (ceilinglimit1-senior60lowerlimit)*10/100;
				 
				 totaltaxd = tax1+tax2;
			 }
			 
			 else if(taxableincomed>senior60lowerlimit)
			 {
				 tax1= (taxableincomed-senior60lowerlimit)*10/100;
				 totaltaxd = tax1;
				  			 
			 }
			
			 else
			 {
				 totaltaxd=0.0;
			 }
			 
			 break;
			 
		case "Super Senior(>80 years)":
			
			 
			 
			 if(taxableincomed >ceilinglimit2)
    		 {
				 
				 tax3  = (taxableincomed-ceilinglimit2) *30/100;
				 tax2= (ceilinglimit2-ceilinglimit1)*20/100;
				 tax1= (ceilinglimit1-senior80lowerlimit)*10/100;
				 
				 totaltaxd = tax1+tax2+tax3;
			 }
			 
			 else if( taxableincomed >ceilinglimit1)
			 {
				 
				 tax2= (taxableincomed-ceilinglimit1)*20/100;
				 tax1= (ceilinglimit1-senior80lowerlimit)*10/100;
				 
				 totaltaxd = tax1+tax2;
			 }
			 
			 else if(taxableincomed>senior80lowerlimit)
			 {
				 tax1= (taxableincomed-senior80lowerlimit)*10/100;
				 totaltaxd = tax1;
				  			 
			 }
			
			 else
			 {
				 totaltaxd=0.0;
			 }
			 
			
			break;
		
		
		}
		
	
		
		return (totaltaxd*(.03) + totaltaxd);

	}

}

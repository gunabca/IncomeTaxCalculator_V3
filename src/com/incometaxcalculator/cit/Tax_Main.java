package com.incometaxcalculator.cit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Tax_Main extends Activity implements OnClickListener {
	String usercategory;
	String[] category = new String[] { "Male", "Female",
			"Senior Citizen(>60 years)", "Super Senior(>80 years)" };
	Spinner Spcategory;
	double incomed = 0.0;
	double deductionsd = 0.0;
	double aftertaxd = 0.0;
	double salaryd = 0.0;
	String incomes = "";
	String deductionss = "";
	double gettaxamount = 0.0;
	String aftertaxs = "";
	String salarys = "";

	String incpers = "";

	Double malelowerlimit = 250000.00;
	Double femalelowerlimit = 250000.00;
	Double senior60lowerlimit = 300000.00;
	Double senior80lowerlimit = 500000.00;

	Double ceilinglimit1 = 500000.00;
	Double ceilinglimit2 = 1000000.00;
	Double ceilinglimit3 = 10000000.00;

	Double tax = 0.00;
	EditText income;
	EditText incincome;
//	EditText incincomeaftertax;
	EditText deductions;
//	EditText aftertax;
	EditText salary, incper,salaryinc;
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
		setContentView(R.layout.tax_mainxml);
	
		income = (EditText) findViewById(R.id.etincome);
		deductions = (EditText) findViewById(R.id.etdeductions);
//		aftertax = (EditText) findViewById(R.id.etincomeaftertax);
		salary = (EditText) findViewById(R.id.etsalarypm);
		incincome = (EditText) findViewById(R.id.etincincome);
		incper = (EditText) findViewById(R.id.etincrpercent);
		salaryinc = (EditText) findViewById(R.id.etsalafterinc);
//		incincomeaftertax = (EditText) findViewById(R.id.etincomeaftertaxinc);
		
		Button checksalary = (Button) findViewById(R.id.btchecksalary);
		Button checkinc = (Button) findViewById(R.id.btincrement);

		
		
		Spcategory = (Spinner) findViewById(R.id.spcategory);

		ArrayAdapter<String> adaptcat = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, category);
		Spcategory.setAdapter(adaptcat);

		checksalary.setOnClickListener(this);
		checkinc.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

		switch (arg0.getId())

		{
		case R.id.btchecksalary:
			InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			im.hideSoftInputFromInputMethod(getCurrentFocus().getWindowToken(),
					InputMethodManager.HIDE_NOT_ALWAYS);

			im.hideSoftInputFromWindow((null == getCurrentFocus()) ? null
					: getCurrentFocus().getWindowToken(),
					InputMethodManager.HIDE_NOT_ALWAYS);
			
			if ((Spcategory.getSelectedItem()) != null) {

				usercategory = Spcategory.getSelectedItem().toString();

				getvalues();
				gettaxamount = calculate(incomed, deductionsd, usercategory);
				displayvalues();

			}
			
			break;

		case R.id.btincrement: {

			usercategory = Spcategory.getSelectedItem().toString();
			getvalues();
			calincrement(incperd);
			displayvaluesinc();

		}

		}
	}

	public void getvalues() {
		try {
			incomes = income.getText().toString();
			deductionss = deductions.getText().toString();
			incpers = incper.getText().toString();

			incomed = Double.valueOf(incomes);
			deductionsd = Double.valueOf(deductionss);
			incperd = Double.valueOf(incpers);

		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

	}

	public void displayvaluesinc()

	{
	//	incincomeaftertax.setText(String.valueOf(taxableincomed - gettaxamount));
		salaryinc.setText(String.valueOf((taxableincomed - gettaxamount) / 12));
		incincome.setText(String.valueOf(incomed));
		deductions.setText(String.valueOf(deductionsd));

	}

	public void displayvalues()

	{
		//aftertax.setText(String.valueOf(taxableincomed - gettaxamount));
		salary.setText(String.valueOf((taxableincomed - gettaxamount) / 12));

	}

	public double calculate(double incomed, double deductionsd,
			String usercategory) {

		taxableincomed = (incomed - deductionsd);

		switch (usercategory) {

		case "Male":

			if (taxableincomed > ceilinglimit2) {

				tax3 = (taxableincomed - ceilinglimit2) * 30 / 100;
				tax2 = (ceilinglimit2 - ceilinglimit1) * 20 / 100;
				tax1 = (ceilinglimit1 - malelowerlimit) * 10 / 100;

				totaltaxd = tax1 + tax2 + tax3;

			}

			else if (taxableincomed > ceilinglimit1) {

				tax2 = (taxableincomed - ceilinglimit1) * 20 / 100;
				tax1 = (ceilinglimit1 - malelowerlimit) * 10 / 100;

				totaltaxd = tax1 + tax2;
			}

			else if (taxableincomed > malelowerlimit) {
				tax1 = (taxableincomed - malelowerlimit) * 10 / 100;
				totaltaxd = tax1;

			}

			else {
				totaltaxd = 0.0;
			}

			break;

		case "Female":

			if (taxableincomed > ceilinglimit2) {

				tax3 = (taxableincomed - ceilinglimit2) * 30 / 100;
				tax2 = (ceilinglimit2 - ceilinglimit1) * 20 / 100;
				tax1 = (ceilinglimit1 - femalelowerlimit) * 10 / 100;

				totaltaxd = tax1 + tax2 + tax3;
			}

			else if (taxableincomed > ceilinglimit1) {

				tax2 = (taxableincomed - ceilinglimit1) * 20 / 100;
				tax1 = (ceilinglimit1 - femalelowerlimit) * 10 / 100;

				totaltaxd = tax1 + tax2;
			}

			else if (taxableincomed > femalelowerlimit) {
				tax1 = (taxableincomed - femalelowerlimit) * 10 / 100;
				totaltaxd = tax1;

			}

			else {
				totaltaxd = 0.0;
			}

			break;

		case "Senior Citizen(>60 years)":

			if (taxableincomed > ceilinglimit2) {

				tax3 = (taxableincomed - ceilinglimit2) * 30 / 100;
				tax2 = (ceilinglimit2 - ceilinglimit1) * 20 / 100;
				tax1 = (ceilinglimit1 - senior60lowerlimit) * 10 / 100;

				totaltaxd = tax1 + tax2 + tax3;
			}

			else if (taxableincomed > ceilinglimit1) {

				tax2 = (taxableincomed - ceilinglimit1) * 20 / 100;
				tax1 = (ceilinglimit1 - senior60lowerlimit) * 10 / 100;

				totaltaxd = tax1 + tax2;
			}

			else if (taxableincomed > senior60lowerlimit) {
				tax1 = (taxableincomed - senior60lowerlimit) * 10 / 100;
				totaltaxd = tax1;

			}

			else {
				totaltaxd = 0.0;
			}

			break;

		case "Super Senior(>80 years)":

			if (taxableincomed > ceilinglimit2) {

				tax3 = (taxableincomed - ceilinglimit2) * 30 / 100;
				tax2 = (ceilinglimit2 - ceilinglimit1) * 20 / 100;
				tax1 = (ceilinglimit1 - senior80lowerlimit) * 10 / 100;

				totaltaxd = tax1 + tax2 + tax3;
			}

			else if (taxableincomed > ceilinglimit1) {

				tax2 = (taxableincomed - ceilinglimit1) * 20 / 100;
				tax1 = (ceilinglimit1 - senior80lowerlimit) * 10 / 100;

				totaltaxd = tax1 + tax2;
			}

			else if (taxableincomed > senior80lowerlimit) {
				tax1 = (taxableincomed - senior80lowerlimit) * 10 / 100;
				totaltaxd = tax1;

			}

			else {
				totaltaxd = 0.0;
			}

			break;

		}

		return (totaltaxd * (.03) + totaltaxd);

	}

	public void calincrement(double incperd) {
		incomed = incomed + (incomed * incperd / 100);
	//	deductionsd = deductionsd + (deductionsd * incperd / 100);
		gettaxamount = calculate(incomed, deductionsd, usercategory);

	}
}

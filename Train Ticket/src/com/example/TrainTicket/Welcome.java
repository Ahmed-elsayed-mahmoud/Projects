package com.example.TrainTicket;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Welcome extends Activity implements View.OnClickListener {

	Intent i = null;
	Button book, ret, display, logout;
	String phone;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		i = getIntent();
		phone = (String) i.getSerializableExtra("phone");

		book = (Button) findViewById(R.id.bookTicket);
		ret = (Button) findViewById(R.id.returnTicket);
		display = (Button) findViewById(R.id.displayTicket);
		logout = (Button) findViewById(R.id.logout);

		book.setOnClickListener(this);
		display.setOnClickListener(this);
		ret.setOnClickListener(this);
		logout.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.logout:
			i = new Intent(this, MainActivity.class);
			startActivityForResult(i, 500);
			overridePendingTransition(R.anim.slide_in_top,
					R.anim.slide_out_bottom);
			finish();
			break;
		case R.id.bookTicket:
			i = new Intent(this, BookTicket.class);
			i.putExtra("phone", phone);
			startActivityForResult(i, 500);
			overridePendingTransition(R.anim.slide_in_top,
					R.anim.slide_out_bottom);
			finish();
			break;
		case R.id.displayTicket:
			i = new Intent(this, DisplayTicket.class);
			i.putExtra("phone", phone);
			startActivityForResult(i, 500);
			overridePendingTransition(R.anim.slide_in_top,
					R.anim.slide_out_bottom);
			finish();
			break;
		case R.id.returnTicket:
			i = new Intent(this, ReturnTicket.class);
			i.putExtra("phone", phone);
			startActivityForResult(i, 500);
			overridePendingTransition(R.anim.slide_in_top,
					R.anim.slide_out_bottom);
			finish();
			break;
		}
	}

}

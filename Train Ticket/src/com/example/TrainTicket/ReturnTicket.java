package com.example.TrainTicket;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ReturnTicket extends Activity implements View.OnClickListener {

	Intent i = null, j = null;
	Button back, logout, bdel;
	TextView message;
	EditText delId;
	String phone;

	MySQLiteHelpe d;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.returnticket);

		j = getIntent();
		phone = (String) j.getSerializableExtra("phone");

		back = (Button) findViewById(R.id.back);
		logout = (Button) findViewById(R.id.logout);
		bdel = (Button) findViewById(R.id.bdel);
		message = (TextView) findViewById(R.id.message);
		delId = (EditText) findViewById(R.id.id);
		delId.setVisibility(View.INVISIBLE);
		bdel.setVisibility(View.INVISIBLE);

		back.setOnClickListener(this);
		logout.setOnClickListener(this);
		bdel.setOnClickListener(this);

		d = new MySQLiteHelpe(this);

		try {
			List<String> t = d.getTicket(phone);
			if (t.get(0) != null) {
				delId.setVisibility(View.VISIBLE);
				bdel.setVisibility(View.VISIBLE);
				String word = "", str = "";
				String[] arr;
				String[][] para = new String[t.size()][7];
				for (int i = 0; i < t.size(); i++) {
					word = t.get(i);
					str = "";
					arr = word.split(" ");
					for (int j = 0; j < arr.length; j++) {
						if (arr[j] != "" || arr[j] != " ") {
							para[i][j] = arr[j];
						}
					}
				}
				String f = "";
				for (int i = 0; i < t.size(); i++) {
					f = f + "ID = " + para[i][0] + "\tName = " + para[i][1]
							+ "\nFrom = " + para[i][3] + "\tTo = " + para[i][4]
							+ "\nDate = " + para[i][5] + "\tClass = "
							+ para[i][6] + "\n" + "====================\n";

				}
				message.setText(f.toString());
			}
		} catch (Exception e) {

		}
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
		case R.id.back:
			i = new Intent(this, Welcome.class);
			startActivityForResult(i, 500);
			overridePendingTransition(R.anim.slide_in_top,
					R.anim.slide_out_bottom);
			finish();
			break;
		case R.id.bdel:
			String ID = delId.getText().toString();
			d.deleteTicket(Integer.parseInt(ID));
			i = new Intent(this, ReturnTicket.class);
			startActivityForResult(i, 500);
			overridePendingTransition(R.anim.slide_in_top,
					R.anim.slide_out_bottom);
			finish();
			break;
		}

	}

}

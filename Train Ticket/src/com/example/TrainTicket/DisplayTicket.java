package com.example.TrainTicket;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class DisplayTicket extends Activity implements View.OnClickListener {
	Intent i = null, j = null;
	Button back, logout;
	TextView message, t1, t2, t3, t4, t5, f1, f2, f3, f4, f5, ti1, ti2, ti3,
			ti4, ti5, c1, c2, c3, c4, c5, n1, n2, n3, n4, n5;
	EditText delId;
	String phone;

	MySQLiteHelpe d;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display_ticket);

		j = getIntent();
		phone = (String) j.getSerializableExtra("phone");

		back = (Button) findViewById(R.id.back);
		logout = (Button) findViewById(R.id.logout);

		n1 = (TextView) findViewById(R.id.n1);
		n2 = (TextView) findViewById(R.id.n2);
		n3 = (TextView) findViewById(R.id.n3);
		n4 = (TextView) findViewById(R.id.n4);
		n5 = (TextView) findViewById(R.id.n5);
		t1 = (TextView) findViewById(R.id.t1);
		t2 = (TextView) findViewById(R.id.t2);
		t3 = (TextView) findViewById(R.id.t3);
		t4 = (TextView) findViewById(R.id.t4);
		t5 = (TextView) findViewById(R.id.t5);
		ti1 = (TextView) findViewById(R.id.ti1);
		ti2 = (TextView) findViewById(R.id.ti2);
		ti3 = (TextView) findViewById(R.id.ti3);
		ti4 = (TextView) findViewById(R.id.ti4);
		ti5 = (TextView) findViewById(R.id.ti5);
		c1 = (TextView) findViewById(R.id.c1);
		c2 = (TextView) findViewById(R.id.c2);
		c3 = (TextView) findViewById(R.id.c3);
		c4 = (TextView) findViewById(R.id.c4);
		c5 = (TextView) findViewById(R.id.c5);
		f1 = (TextView) findViewById(R.id.f1);
		f2 = (TextView) findViewById(R.id.f2);
		f3 = (TextView) findViewById(R.id.f3);
		f4 = (TextView) findViewById(R.id.f4);
		f5 = (TextView) findViewById(R.id.f5);
		back.setOnClickListener(this);
		logout.setOnClickListener(this);

		d = new MySQLiteHelpe(this);
		try {
			List<String> t = d.getTicket("phone");
			if (t.get(0) != null) {
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
				int k = 1;
				for (int i = t.size() - 1; i >= 0; i--) {

					if (k == 1) {
						n1.setText(para[i][1].toString());
						t1.setText(para[i][4].toString());
						c1.setText(para[i][6].toString());
						f1.setText(para[i][3].toString());
						ti1.setText(para[i][5].toString());
					} else if (k == 2) {
						n2.setText(para[i][1].toString());
						t2.setText(para[i][4].toString());
						c2.setText(para[i][6].toString());
						f2.setText(para[i][3].toString());
						ti2.setText(para[i][5].toString());
					} else if (k == 3) {
						n3.setText(para[i][1].toString());
						t3.setText(para[i][4].toString());
						c3.setText(para[i][6].toString());
						f3.setText(para[i][3].toString());
						ti3.setText(para[i][5].toString());
					} else if (k == 4) {
						n4.setText(para[i][1].toString());
						t4.setText(para[i][4].toString());
						c4.setText(para[i][6].toString());
						f4.setText(para[i][3].toString());
						ti4.setText(para[i][5].toString());
					} else if (k == 5) {
						n5.setText(para[i][1].toString());
						t5.setText(para[i][4].toString());
						c5.setText(para[i][6].toString());
						f5.setText(para[i][3].toString());
						ti5.setText(para[i][5].toString());
					} else {
						break;
					}
					k++;
				}

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
		}
	}
}

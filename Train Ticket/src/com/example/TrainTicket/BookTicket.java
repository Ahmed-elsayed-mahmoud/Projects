package com.example.TrainTicket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BookTicket extends Activity {

	boolean flagName = false, flagClass = false, flagF = false, flagT = false,
			flagTime = false;
	String countryFrom, countryTo;
	MySQLiteHelpe d;
	Intent i = null;
	String phone, Name, Clas, From, To, Time;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.book_ticket);
		d = new MySQLiteHelpe(this);
		i = getIntent();
		phone = (String) i.getSerializableExtra("phone");
		final Button classDeg = (Button) findViewById(R.id.Bdeg);
		final Button to = (Button) findViewById(R.id.Bto);
		final Button from = (Button) findViewById(R.id.Bfrom);
		final Button time = (Button) findViewById(R.id.Btime);
		final Button finish = (Button) findViewById(R.id.Bfinish);
		final EditText name = (EditText) findViewById(R.id.edName);

		classDeg.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Creating the instance of PopupMenu
				PopupMenu popup = new PopupMenu(BookTicket.this, classDeg);
				// Inflating the Popup using xml file
				popup.getMenuInflater().inflate(R.menu.deg_menu,
						popup.getMenu());
				// registering popup with OnMenuItemClickListener
				popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
					public boolean onMenuItemClick(MenuItem item) {
						flagClass = true;
						Toast.makeText(BookTicket.this,
								"You Clicked : " + item.getTitle(),
								Toast.LENGTH_SHORT).show();
						classDeg.setText(item.getTitle());
						Clas = classDeg.getText().toString();
						return true;
					}

				});

				popup.show();// showing popup menu
			}
		});
		from.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Creating the instance of PopupMenu
				PopupMenu popup = new PopupMenu(BookTicket.this, from);
				// Inflating the Popup using xml file
				popup.getMenuInflater().inflate(R.menu.from_menu,
						popup.getMenu());
				// registering popup with OnMenuItemClickListener
				popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
					public boolean onMenuItemClick(MenuItem item) {
						flagF = true;
						Toast.makeText(BookTicket.this,
								"You Clicked : " + item.getTitle(),
								Toast.LENGTH_SHORT).show();
						from.setText(item.getTitle());
						From = from.getText().toString();
						countryFrom = (String) item.getTitle();
						return true;
					}
				});
				popup.show();// showing popup menu
			}
		});
		to.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Creating the instance of PopupMenu
				PopupMenu popup = new PopupMenu(BookTicket.this, to);
				// Inflating the Popup using xml file
				popup.getMenuInflater().inflate(R.menu.from_menu,
						popup.getMenu());
				// registering popup with OnMenuItemClickListener
				popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
					public boolean onMenuItemClick(MenuItem item) {
						flagT = true;
						Toast.makeText(BookTicket.this,
								"You Clicked : " + item.getTitle(),
								Toast.LENGTH_SHORT).show();
						to.setText(item.getTitle());
						To = to.getText().toString();
						countryTo = (String) item.getTitle();
						return true;
					}
				});
				popup.show();// showing popup menu
			}
		});
		time.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Creating the instance of PopupMenu
				PopupMenu popup = new PopupMenu(BookTicket.this, time);
				// Inflating the Popup using xml file
				popup.getMenuInflater().inflate(R.menu.time_menu,
						popup.getMenu());
				// registering popup with OnMenuItemClickListener
				popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
					public boolean onMenuItemClick(MenuItem item) {
						flagTime = true;
						Toast.makeText(BookTicket.this,
								"You Clicked : " + item.getTitle(),
								Toast.LENGTH_SHORT).show();
						time.setText(item.getTitle());
						Time = time.getText().toString();
						return true;
					}
				});
				popup.show();// showing popup menu
			}
		});
		finish.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (name.getText().toString() != "Enter your name"
						&& name.getText().toString() != "") {
					Name = name.getText().toString();
					flagName = true;
				}
				if (flagClass && flagF && flagT && flagTime && flagName) {

					if (countryFrom.equals(countryTo)) {
						Toast.makeText(BookTicket.this,
								"You can't choose the same country",
								Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(BookTicket.this, "You have booked it ",
								Toast.LENGTH_SHORT).show();
						d.addTicket(new Ticket(Name, phone));
						d.addTicket(new Ticket(From, To));
						d.addTicket(new Ticket(Time, Clas));
						finish();
					}
				} else {
					Toast.makeText(BookTicket.this, "You must fill all !!",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
}

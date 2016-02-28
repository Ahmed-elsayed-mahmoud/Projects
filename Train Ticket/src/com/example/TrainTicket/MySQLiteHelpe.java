package com.example.TrainTicket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelpe extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "TicketDB";

	public MySQLiteHelpe(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// SQL statement to create ticket table
		String CREATE_BOOK_TABLE = "CREATE TABLE books ( "
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "name TEXT, "
				+ "phone TEXT, " + "from TEXT )";

		// create tickets table
		db.execSQL(CREATE_BOOK_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older books table if existed
		db.execSQL("DROP TABLE IF EXISTS books");

		// create fresh books table
		this.onCreate(db);
	}

	// ---------------------------------------------------------------------
	// Tickets table name
	private static final String TABLE_TICKETS = "tickets";

	// Tickets Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name";
	private static final String KEY_PHONE = "phone";
	private static final String KEY_FROM = "from";
	private static final String KEY_TO = "to";
	private static final String KEY_TIME = "time";
	private static final String KEY_CLASS = "class";

	private static final String[] COLUMNS = { KEY_ID, KEY_NAME, KEY_PHONE };

	public void addTicket(Ticket ticket) {
		Log.d("addTicket", ticket.toString());
		// 1. get reference to writable DB
		SQLiteDatabase db1 = this.getWritableDatabase();

		// 2. create ContentValues to add key "column"/value
		ContentValues values = new ContentValues();
		values.put(KEY_NAME, ticket.getName());
		values.put(KEY_PHONE, ticket.getPhone());

		// 3. insert
		db1.insert(TABLE_TICKETS, null, values); // key/value -> keys = column
													// names/ values = column
													// values

		// 4. close
		db1.close();
	}

	public List<String> getTicket(String phone) {
		List<String> tickets = new LinkedList<String>();
		// 1. get reference to readable DB
		SQLiteDatabase db1 = this.getReadableDatabase();
		Cursor cursor = db1.query(TABLE_TICKETS, // a. table
				COLUMNS, // b. column names
				" phone = ?", // c. selections
				new String[] { phone }, // d. selections args
				null, // e. group by
				null, // f. having
				null, // g. order by
				null); // h. limit

		if (cursor.moveToFirst()) {
			do {
				// 2. build query

				// 4. build ticket object
				Ticket ticket = new Ticket();
				int id = Integer.parseInt(cursor.getString(0));
				ticket.setId(id);
				ticket.setName(cursor.getString(1));
				ticket.setPhone(cursor.getString(2));

				Log.d("getTicket(" + phone + ")", ticket.toString());
				String s = "";
				s = ticket.toString();
				s += getTicket(id + 1);
				s += getTicket(id + 2);
				tickets.add(s);
			} while (cursor.moveToNext());
		}
		if (cursor == null)
			return null;
		// 5. return ticket
		return tickets;
	}

	public String getTicket(int id) {

		// 1. get reference to readable DB
		SQLiteDatabase db1 = this.getReadableDatabase();

		// 2. build query
		Cursor cursor = db1.query(TABLE_TICKETS, // a. table
				COLUMNS, // b. column names
				" id = ?", // c. selections
				new String[] { String.valueOf(id) }, // d. selections args
				null, // e. group by
				null, // f. having
				null, // g. order by
				null); // h. limit

		// 3. if we got results get the first one
		if (cursor != null)
			cursor.moveToFirst();

		// 4. build ticket object
		Ticket ticket = new Ticket();
		ticket.setId(Integer.parseInt(cursor.getString(0)));
		ticket.setName(cursor.getString(1));
		ticket.setPhone(cursor.getString(2));

		Log.d("getTicket(" + id + ")", ticket.toString());

		// 5. return ticket
		return ticket.toString();
	}

	// Get All Tickets
	public List<Ticket> getAllTickets() {
		List<Ticket> tickets = new LinkedList<Ticket>();

		// 1. build the query
		String query = "SELECT  * FROM " + TABLE_TICKETS;

		// 2. get reference to writable DB
		SQLiteDatabase db1 = this.getWritableDatabase();
		Cursor cursor = db1.rawQuery(query, null);

		// 3. go over each row, build book and add it to list
		Ticket ticket = null;
		if (cursor.moveToFirst()) {
			do {
				ticket = new Ticket();
				ticket.setId(Integer.parseInt(cursor.getString(0)));
				ticket.setName(cursor.getString(1));
				ticket.setPhone(cursor.getString(2));

				tickets.add(ticket);
			} while (cursor.moveToNext());
		}

		Log.d("getAllTickets()", tickets.toString());

		// return books
		return tickets;
	}

	// Updating single ticket
	public int updateBook(Ticket ticket) {

		// 1. get reference to writable DB
		SQLiteDatabase db1 = this.getWritableDatabase();

		// 2. create ContentValues to add key "column"/value
		ContentValues values = new ContentValues();
		values.put("name", ticket.getName());
		values.put("phone", ticket.getPhone());

		// 3. updating row
		int i = db1.update(TABLE_TICKETS, // table
				values, // column/value
				KEY_ID + " = ?", // selections
				new String[] { String.valueOf(ticket.getId()) }); // selection
																	// args

		// 4. close
		db1.close();

		return i;

	}

	// Deleting single ticket
	public void deleteTicket(/* Ticket ticket */int id) {

		// 1. get reference to writable DB
		SQLiteDatabase db = this.getWritableDatabase();

		// 2. delete
		db.delete(TABLE_TICKETS, KEY_ID + " = ?",
				new String[] { String.valueOf(id) });

		// 3. close
		db.close();

		// Log.d("deleteTicket", ticket.toString());

	}

}

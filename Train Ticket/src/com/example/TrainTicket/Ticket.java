package com.example.TrainTicket;

public class Ticket {

	private int id;
	private String name;
	private String phone;
	private String from;
	private String to;
	private String time;
	private String clas;

	public Ticket() {
	}

	public static int flag = 0;

	public Ticket(String name, String phone) {
		super();
		this.name = name;
		this.phone = phone;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getClas() {
		return clas;
	}

	public void setClas(String clas) {
		this.clas = clas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		String s = "";
		if (flag % 3 == 1) {
			s += id + ", ";
		}
		s += name + ", " + phone + ", ";
		flag++;
		return s;
		// return "id=" + id + ", " + name + ", " + phone+ " ";
	}

}

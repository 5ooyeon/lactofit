package com.ssafit.model.dto;

import java.sql.Timestamp;

public class Point {
	private int point_id;
	private int user_id;
	private int point_amount;
	private String point_desc;
	private Timestamp point_regDate;

	public Point() {
	}

	public Point(int point_id, int user_id, int point_amount, String point_desc, Timestamp point_regDate) {
		this.point_id = point_id;
		this.user_id = user_id;
		this.point_amount = point_amount;
		this.point_desc = point_desc;
		this.point_regDate = point_regDate;
	}

	public int getPoint_id() {
		return point_id;
	}

	public void setPoint_id(int point_id) {
		this.point_id = point_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getPoint_amount() {
		return point_amount;
	}

	public void setPoint_amount(int point_amount) {
		this.point_amount = point_amount;
	}

	public String getPoint_desc() {
		return point_desc;
	}

	public void setPoint_desc(String point_desc) {
		this.point_desc = point_desc;
	}

	public Timestamp getPoint_regDate() {
		return point_regDate;
	}

	public void setPoint_regDate(Timestamp point_regDate) {
		this.point_regDate = point_regDate;
	}

	@Override
	public String toString() {
		return "Point [point_id=" + point_id + ", user_id=" + user_id + ", point_amount=" + point_amount
				+ ", point_desc=" + point_desc + ", point_regDate=" + point_regDate + "]";
	};

}

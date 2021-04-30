package com.rahul.todo.dtos;

import java.util.Date;

public class TodoDTO {

	private long id;
	private String userName;
	private String description;
	private Date targetDate;
	private boolean isDone;

	public TodoDTO() {

	}

	public TodoDTO(long id, String userName, String description, Date targetDate, boolean isDone) {
		super();
		this.id = id;
		this.userName = userName;
		this.description = description;
		this.targetDate = targetDate;
		this.isDone = isDone;
	}

	public long getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public String getDescription() {
		return description;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TodoDTO other = (TodoDTO) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TodoDTO [id=" + id + ", userName=" + userName + ", description=" + description + ", targetDate="
				+ targetDate + ", isDone=" + isDone + "]";
	}
}

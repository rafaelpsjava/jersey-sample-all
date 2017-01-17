package br.com.web.sample.jersey.ws.infra.body;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {

	private Date created;
	private String message;
	private int uniqueId;

	public Message() {
	}

	public Message(Date created, String message, int uniqueId) {
		this.created = created;
		this.message = message;
		this.uniqueId = uniqueId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(int uniqueId) {
		this.uniqueId = uniqueId;
	}

}
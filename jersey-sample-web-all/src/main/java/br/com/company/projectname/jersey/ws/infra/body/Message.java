package br.com.company.projectname.jersey.ws.infra.body;

import java.util.Date;

public class Message {

	private Date created;
	private String message;
	private int uniqueId;

	public Message(Date created, String message, int uniqueId) {
		this.created = created;
		this.message = message;
		this.uniqueId = uniqueId;
	}

	public int getUniqueId() {
		return uniqueId;
	}

	@Override
	public String toString() {
		return "<span class='created'>CREATED: " + created + "</span> <span class='uniqueId'>ID: " + uniqueId + "</span> <span class='message'>MESSAGE: " + message + "</span>";
	}
}
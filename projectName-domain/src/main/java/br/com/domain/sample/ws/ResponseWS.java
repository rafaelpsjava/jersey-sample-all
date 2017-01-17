package br.com.domain.sample.ws;

import java.io.Serializable;
import java.util.List;

/**
 * ResponseWS
 * 
 * @author Rafael
 *
 */
@SuppressWarnings("serial")
public class ResponseWS implements Serializable {

	private int status;
	private String content;
	private List<Object> contents;

	public ResponseWS() {
	}

	public ResponseWS(String content) {
		this(content, HttpStatus.SC_OK.get());
	}

	public ResponseWS(String content, int status) {
		this.content = content;
		this.status = status;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Object> getContents() {
		return contents;
	}

	public void setContents(List<Object> contents) {
		this.contents = contents;
	}

	@Override
	public String toString() {
		return getContent();
	}

}

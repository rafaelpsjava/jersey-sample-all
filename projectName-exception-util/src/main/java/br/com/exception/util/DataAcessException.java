package br.com.exception.util;

public class DataAcessException extends Throwable {

	public DataAcessException() {
	}

	public DataAcessException(String errorMsg) {
		super(errorMsg);
	}

	public DataAcessException(Throwable e) {
		super(e);
	}
}

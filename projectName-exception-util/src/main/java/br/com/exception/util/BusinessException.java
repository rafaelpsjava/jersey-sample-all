package br.com.exception.util;

public class BusinessException extends Throwable {

	public BusinessException() {
	}

	public BusinessException(String errorMsg) {
		super(errorMsg);
	}

	public BusinessException(DataAcessException e) {
		super(e);
	}
}

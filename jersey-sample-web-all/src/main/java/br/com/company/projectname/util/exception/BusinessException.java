package br.com.company.projectname.util.exception;

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

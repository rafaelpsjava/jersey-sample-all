package br.com.company.projectname.util.exception;

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

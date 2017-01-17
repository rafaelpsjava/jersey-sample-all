package br.com.company.projectname.util.exception;

public class ServiceException extends Exception {
	public ServiceException() {
	}

	public ServiceException(String errorMsg) {
		super(errorMsg);
	}

	public ServiceException(BusinessException e) {
		super(e);
	}
}

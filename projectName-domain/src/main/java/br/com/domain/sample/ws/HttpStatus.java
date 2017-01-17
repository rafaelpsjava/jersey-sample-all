package br.com.domain.sample.ws;

public enum HttpStatus {

	 SC_OK ( 200),
	 SC_CREATED ( 201),
	 SC_ACCEPTED ( 202),
	 SC_NON_AUTHORITATIVE_INFORMATION ( 203),
	 SC_BAD_REQUEST ( 400),
	 SC_UNAUTHORIZED ( 401),
	 SC_PAYMENT_REQUIRED ( 402),
	 SC_FORBIDDEN ( 403),
	 SC_NOT_FOUND ( 404),
	 SC_METHOD_NOT_ALLOWED ( 405),
	 SC_NOT_ACCEPTABLE ( 406),
	 SC_REQUEST_TIMEOUT ( 408),
	 SC_INTERNAL_SERVER_ERROR ( 500),
	 SC_NOT_IMPLEMENTED ( 501),
	 SC_BAD_GATEWAY ( 502),
	 SC_SERVICE_UNAVAILABLE ( 503),
	 SC_GATEWAY_TIMEOUT ( 504);
	 
	 private int number;
	 
	 private HttpStatus(int number) {
		 this.number = number;
	 }
	 
	 public int get() {
		return number;
	}
}
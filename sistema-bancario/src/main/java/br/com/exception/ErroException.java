package br.com.exception;

public class ErroException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	
	public ErroException(String msg) {
		super(msg);
	}

}

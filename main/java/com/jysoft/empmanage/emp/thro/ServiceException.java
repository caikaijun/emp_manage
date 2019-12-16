package com.jysoft.empmanage.emp.thro;

/**
 * 自定义的异常类,用于一些业务范畴的异常
 * @author lenovo
 */
public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = 5843835376260549700L;
	public ServiceException() {
		super();
	}
	public ServiceException(String message) {
		super(message);
	}
	public ServiceException(Throwable cause) {
		super(cause);
	}
	public ServiceException(String msg,Throwable cause) {
		super(msg, cause);
	}

}

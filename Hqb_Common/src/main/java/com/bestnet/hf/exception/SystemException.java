package com.bestnet.hf.exception;
/**
 * 说明：系统运行时异常
 *
 * 作者：hzg
 *
 * 时间：2019-06-18
 *
 */
public class SystemException extends RuntimeException {
	protected int errorCode = -1;
	private static final long serialVersionUID = 6004997746553695524L;
	public SystemException(){
		super("An error occured while system processing");
	}
	public SystemException(String message){
		super(message);
	}
	public SystemException(int errorCode, String message){
		super(message);
		this.errorCode = errorCode;
	}
	public int getErrorCode() {
		return errorCode;
	}
}

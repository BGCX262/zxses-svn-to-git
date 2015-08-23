package com.agilefly.commons;

public class SystemException extends RuntimeException {
	//异常代码
	private String key;
	
	//定义参数
	private Object[] values;
	
	public SystemException(){
		super();
	}

	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}

	public SystemException(String message) {
		super(message);
	}

	public SystemException(Throwable cause) {
		super(cause);
	}
	
	/**
	 * 扩展struts异常处理器，增加自定义的key值设定
	 * @param message
	 * @param key
	 */
	public SystemException(String message,String key){
		super(message);
		this.key = key;
	}
	
	/**
	 * 扩展struts异常处理器，增加自定义的key值设置和参数的设置(带一个参数)
	 * @param message
	 * @param key 属性文件中的key值
	 * @param value 属性文件中的value值
	 */
	public SystemException(String message,String key,Object value){
		super(message);
		this.key = key;
		this.values = new Object[]{value};
	}
	
	/**
	 * 带多个参数
	 * @param message
	 * @param key 属性文件中的key值
	 * @param values 属性文件中的value值
	 */
	public SystemException(String message,String key,Object[] values){
		super(message);
		this.key = key;
		this.values = values;
	}
	
	public String getKey(){
		return key;
	}
	
	public Object[] getValues(){
		return values;
	}
}

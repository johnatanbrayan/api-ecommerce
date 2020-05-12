package br.com.johnatanbrayan.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;
	
	List<FieldMessage> erros = new ArrayList<>();
	
	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status,msg,timeStamp);
	}
	
	public List<FieldMessage> getErros() { return this.erros; }
	public void addErros(String fieldName, String message) {
		erros.add(new FieldMessage(fieldName,message));
	}
}

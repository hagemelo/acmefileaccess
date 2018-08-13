package br.com.acmefileaccess.acmefileaccess.modelo;

import org.springframework.http.HttpStatus;

public class Response {
	
	private String mensagem;
	
	private HttpStatus status;

	public Response(String mensagem, HttpStatus status) {
		
		this.mensagem = mensagem;
		this.status = status;
	}

	public String getMensagem() {
		return mensagem;
	}

	public HttpStatus getStatus() {
		return status;
	}
	
}

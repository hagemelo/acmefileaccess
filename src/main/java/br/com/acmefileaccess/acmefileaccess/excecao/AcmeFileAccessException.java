package br.com.acmefileaccess.acmefileaccess.excecao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;

/**
 * 
 * @author Alexsander Melo
 * @since 10/08/2018
 *
 */

public class AcmeFileAccessException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private static final String DEFAULT= "ERRRO_ACAO_ABRUPTA";

	public Logger inicializarLogger() {
		return LogManager.getLogger(AcmeFileAccessException.class);
	}

	public AcmeFileAccessException() {
		super(DEFAULT);
		this.inicializarLogger().error(DEFAULT);
	}
	
	public AcmeFileAccessException(String message) {
		super(message);
		this.inicializarLogger().error(message);
	}

	public AcmeFileAccessException(String message, final AmazonServiceException cause) {

		super(DEFAULT, cause);
		this.inicializarLogger().error(
				DEFAULT + " " + cause.getMessage());
		
		this.inicializarLogger().info(message);
		this.inicializarLogger().info("Error Message:    " + cause.getMessage());
		this.inicializarLogger().info("HTTP Status Code: " + cause.getStatusCode());
		this.inicializarLogger().info("AWS Error Code:   " + cause.getErrorCode());
		this.inicializarLogger().info("Error Type:       " + cause.getErrorType());
		this.inicializarLogger().info("Request ID:       " + cause.getRequestId());
	}

	public AcmeFileAccessException(String message, final AmazonClientException cause) {

		super(DEFAULT, cause);
		this.inicializarLogger().error(
				DEFAULT + " " + cause.getMessage());
		
		this.inicializarLogger().info(message);
		this.inicializarLogger().info("Error Message:    " + cause.getMessage());
	}
	
	public AcmeFileAccessException(final Throwable cause) {

		super(DEFAULT);
		this.inicializarLogger().error(
				DEFAULT + " " + cause.getMessage());
	}

	public ResponseEntity<RespostaErro> respostaErro(){
		
		return new ResponseEntity<RespostaErro>(new RespostaErro(this.getMessage()),  HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	
	
	
	@Override
	public String getMessage() {

		String message = super.getMessage();
		if (message == null || message.isEmpty()) {
			message = DEFAULT;
		}
		return message;
	}

}

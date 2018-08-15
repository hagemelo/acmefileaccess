package br.com.acmefileaccess.acmefileaccess.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.acmefileaccess.acmefileaccess.excecao.AcmeFileAccessException;
import br.com.acmefileaccess.acmefileaccess.modelo.Response;

/**
 * 
 * @author Alexsander Melo
 * @since 10/08/2018
 *
 */

abstract class DefaultController {
	
	@ExceptionHandler(AcmeFileAccessException.class)
	public ResponseEntity<Response> campoObrigatorioExceptionHandler(AcmeFileAccessException ex){
		
		return ex.respostaErro();
	}

}

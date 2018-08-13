package br.com.acmefileaccess.acmefileaccess.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.acmefileaccess.acmefileaccess.excecao.AcmeFileAccessException;
import br.com.acmefileaccess.acmefileaccess.modelo.Arquivo;
import br.com.acmefileaccess.acmefileaccess.modelo.KeyFile;
import br.com.acmefileaccess.acmefileaccess.modelo.Response;
import br.com.acmefileaccess.acmefileaccess.service.AWSService;

/**
 * 
 * @author Alexsander Melo
 * @since 11/08/2018
 *
 */
@CrossOrigin(origins = {"http://localhost:8081"}, maxAge = 3000)
@RestController
public class AcmeFileAccessController extends DefaultController{
	
	@Autowired
	private AWSService service;
	
	@GetMapping(path= "/listdiretorios")
	public @ResponseBody Set<Arquivo> listFiles() throws AcmeFileAccessException {

		return service.list();
	}
	

	@PostMapping(path= "/uploadarquivo")
	public @ResponseBody Response upload(@RequestPart(value = "file") MultipartFile file) throws AcmeFileAccessException{

		return service.uploadFile(file);
	}
	
	@PostMapping(path= "/renamearquivo")
	public @ResponseBody String rename(@RequestBody KeyFile keyFile) throws AcmeFileAccessException {
		
		service.rename(keyFile);
		return "";
	}
	
	
}

package br.com.acmefileaccess.acmefileaccess;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.acmefileaccess.acmefileaccess.helper.Helper;
import br.com.acmefileaccess.acmefileaccess.modelo.KeyFile;
import br.com.acmefileaccess.acmefileaccess.modelo.Response;

/**
 * 
 * @author Alexsander Melo
 * @since 11/08/2018
 *
 */
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=AcmeFileAccessApplication.class, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AcmeFileAccessControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private Helper helper;
	private final int RESPONSE_CODE = 200;
	private final static String LIST_DIRETORIOS = "/listdiretorios";
	private final static String RENOMEAR_ARQUIVOS =  "/renamearquivo";
	private final RandomString randomString;
	
	public AcmeFileAccessControllerTest() {
		
		this.randomString = new RandomString();
	}
	
	
	@Test
	public void deveListarTodosOsArquivos() {

		ResponseEntity<Set> response = testRestTemplate.getForEntity(LIST_DIRETORIOS, Set.class);
		assertTrue(!helper.enulo(response));
		assertFalse(response.getBody().isEmpty());
	}


	@Test
	public void deveAlrerarNomeDoArquivo() {

		KeyFile keyFile;
		String keyalvo = "90dummy.txt";
		String keyrenomear = this.randomString.nextString() + ".txt";
		
		keyFile = new KeyFile(keyalvo, keyrenomear);
		ResponseEntity<Response> responseEntity = testRestTemplate.postForEntity(RENOMEAR_ARQUIVOS, keyFile, Response.class);
		assertTrue(responseEntity.getStatusCodeValue() == RESPONSE_CODE);
		
		keyFile = new KeyFile(keyrenomear, keyalvo);
		responseEntity = testRestTemplate.postForEntity(RENOMEAR_ARQUIVOS, keyFile, Response.class);
		assertTrue(responseEntity.getStatusCodeValue() == RESPONSE_CODE);
	}

}
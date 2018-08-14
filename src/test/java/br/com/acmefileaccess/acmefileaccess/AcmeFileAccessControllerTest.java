package br.com.acmefileaccess.acmefileaccess;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import br.com.acmefileaccess.acmefileaccess.helper.Helper;
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
	
	
//	@Ignore
//	@Test
//	public void deveExistirMetodoGetDeListagemDeDiretorios() {
//
//		String url = "/listdiretorios";
//		ResponseEntity<String> response = testRestTemplate.getForEntity(url, String.class);
//		assertTrue(!helper.enulo(response));
//	}

	@Test
	public void deveExistirMetodoPostDeUploadArquivo() {

		
		ClassPathResource resource = new ClassPathResource("default-soapui-workspace.xml", getClass());
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("file", resource);
		
		String url = "/uploadarquivo";
		ResponseEntity<Response> response = testRestTemplate.postForEntity(url, map, Response.class);
		assertTrue(response.getStatusCodeValue() == RESPONSE_CODE);
	}

//	@Ignore
//	@Test
//	public void deveExistirMetodoPostDeRenameArquivo() {
//
//		String url = "/renamearquivo";
//		ResponseEntity<String> response = testRestTemplate.getForEntity(url, String.class);
//		assertTrue(response.getStatusCodeValue() == RESPONSE_CODE);
//	}

}

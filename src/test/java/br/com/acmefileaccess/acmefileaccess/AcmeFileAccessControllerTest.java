package br.com.acmefileaccess.acmefileaccess;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import br.com.acmefileaccess.acmefileaccess.helper.Helper;

/**
 * 
 * @author Alexsander Melo
 * @since 11/08/2018
 *
 */

 @Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
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

		File file = new File("C:\\temp\\ReuniaoComOAdriano.txt");
		FileInputStream input;
		MultipartFile multipartFile = null;
		try {
			input = new FileInputStream(file);
			multipartFile = new MockMultipartFile(file.getName(), file.getName(), "text/plain", input);

		} catch (IOException e) {
			e.printStackTrace();
		}

		String url = "/uploadarquivo";
		ResponseEntity<String> response = testRestTemplate.postForEntity(url, multipartFile, String.class);
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

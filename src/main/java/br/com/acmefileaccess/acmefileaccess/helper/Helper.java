package br.com.acmefileaccess.acmefileaccess.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.acmefileaccess.acmefileaccess.excecao.AcmeFileAccessException;

@Component
public class Helper{

	public boolean naoenulo(Object object) {

		return !enulo(object);
	}

	public boolean enulo(Object object) {

		return object == null ? true : false;
	}

	public File convertMultiPartToFile(MultipartFile file) throws AcmeFileAccessException {

		File convFile = null;
		try {
			convFile = new File(file.getOriginalFilename());
			FileOutputStream fos;
			fos = new FileOutputStream(convFile);
			fos.write(file.getBytes());
			fos.close();
		} catch (IOException e) {
			
			throw new AcmeFileAccessException("Erro Ao Converter Aquivo");
		}
		return convFile;
	}

}

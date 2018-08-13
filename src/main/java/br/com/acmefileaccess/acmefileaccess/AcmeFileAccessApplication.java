package br.com.acmefileaccess.acmefileaccess;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author Alexsander Melo
 * @since 10/08/2018
 *
 */

@SpringBootApplication
public class AcmeFileAccessApplication implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments args) throws Exception {
			
		System.in.read();
	}

	public static void main(String[] args) {
		SpringApplication.run(AcmeFileAccessApplication.class, args);
	}

}

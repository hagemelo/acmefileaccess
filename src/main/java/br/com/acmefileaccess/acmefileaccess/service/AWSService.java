package br.com.acmefileaccess.acmefileaccess.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;

import br.com.acmefileaccess.acmefileaccess.excecao.AcmeFileAccessException;
import br.com.acmefileaccess.acmefileaccess.helper.Helper;
import br.com.acmefileaccess.acmefileaccess.modelo.Arquivo;
import br.com.acmefileaccess.acmefileaccess.modelo.KeyFile;
import br.com.acmefileaccess.acmefileaccess.modelo.Response;

/**
 * 
 * @author Alexsander Melo
 * @since 11/08/2018
 *
 */

@Service
public class AWSService {
	
	private Logger logger = LogManager.getLogger(AWSService.class); 
	
	static final String ARQUIVO_TEXT = "Arquivo '";
	
	@Autowired
	private AmazonS3 s3client;
 
	@Value("${jsa.s3.bucket}")
	private String bucketName;
	
	@Autowired
	private Helper helper;
	
	private Set<Arquivo> listaArquivos;
	
	public AWSService() {
		
		this.listaArquivos = new HashSet<>();
	}
	
	public Response uploadFile(MultipartFile multipartfile) throws AcmeFileAccessException{
		
		try {
			
			File file = helper.convertMultiPartToFile(multipartfile);
	        s3client.putObject(new PutObjectRequest(bucketName, file.getName(), file));
	        logger.info("===================== Upload File - Done! =====================");
	        StringBuilder result = new StringBuilder(ARQUIVO_TEXT)
					.append(file.getName())
					.append("' carregado com SUCESSO!");
	        return new Response(result.toString(), HttpStatus.OK); 
		} catch (AmazonServiceException ase) {

			throw new AcmeFileAccessException("Caught an AmazonServiceException from PUT requests, rejected reasons ", ase);
        } catch (AmazonClientException ace) {

        	throw new AcmeFileAccessException("Caught an AmazonClientException: ", ace);
        }
	}
	
	public Set<Arquivo> list() throws AcmeFileAccessException {

		try {
			
			ListObjectsRequest listObjectsRequest = new ListObjectsRequest()
					.withBucketName(bucketName)
					.withMaxKeys(10);
			
			ObjectListing objectListing;
			objectListing = s3client.listObjects(listObjectsRequest);
			convert(objectListing);
			return this.listaArquivos;
		} catch (AmazonServiceException ase) {
			
			throw new AcmeFileAccessException("Erro ao Listar Aquivos", ase);
		} catch (AmazonClientException ace) {
			
			throw new AcmeFileAccessException("Erro ao Listar Aquivos", ace);
		}
	}
	
	
	public Response rename(KeyFile keyFile) throws AcmeFileAccessException{
		
		this.copyFile(keyFile);
		this.delete(keyFile.getOriginName());
		
		StringBuilder result = new StringBuilder(ARQUIVO_TEXT)
					.append(keyFile.getOriginName())
					.append("' renomeado para '")
					.append(keyFile.getTargetName())
					.append("' com SUCESSO!");
		return new Response(result.toString(), HttpStatus.OK); 
	}
	
	public Response delete(String fileName) throws AcmeFileAccessException{
		
		try {
			s3client.deleteObject(new DeleteObjectRequest(bucketName, fileName));
	        logger.info("===================== delete File - Done! =====================");
	        
			StringBuilder result = new StringBuilder(ARQUIVO_TEXT)
					.append(fileName)
					.append("' deletado com SUCESSO!");
			return new Response(result.toString(), HttpStatus.OK);
		} catch (AmazonServiceException ase) {
			
			throw new AcmeFileAccessException("Erro ao deletar arquivo ", ase);
        } catch (AmazonClientException ace) {
        	
            throw new AcmeFileAccessException("Erro ao Deletar arquivo", ace);
        }
	}
		
	public Response copyFile(KeyFile keyFile) throws AcmeFileAccessException{
		
		try {
			CopyObjectRequest copyObjRequest = new CopyObjectRequest(bucketName, 
					keyFile.getOriginName(), bucketName, keyFile.getTargetName());
			s3client.copyObject(copyObjRequest);
	        logger.info("===================== copy File - Done! =====================");
	        StringBuilder result = new StringBuilder(ARQUIVO_TEXT)
					.append(keyFile.getOriginName())
					.append("' copiado para '")
					.append(keyFile.getTargetName())
					.append("' com SUCESSO!");
	        return new Response(result.toString(), HttpStatus.OK); 
		} catch (AmazonServiceException ase) {

			throw new AcmeFileAccessException("Erro ao Copiar arquivo ", ase);
        } catch (AmazonClientException ace) {

        	throw new AcmeFileAccessException("Erro ao Copiar arquivo", ace);
        }
	}
	
	private void convert(ObjectListing  listObjects) {
		
		this.listaArquivos.clear();
		listObjects.getObjectSummaries().stream().forEach(obj -> this.listaArquivos.add(new Arquivo(obj.getKey(), obj.getSize(), obj.getLastModified())));
	}

}

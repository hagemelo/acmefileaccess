package br.com.acmefileaccess.acmefileaccess.modelo;

import java.io.Serializable;

/**
 * 
 * @author Alexsander Melo
 * @since 11/08/2018
 *
 */

public class KeyFile implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String originName;
	
	private String targetName;
	
	public KeyFile(String originName, String targetName) {
		
		this.originName = originName;
		this.targetName = targetName;
	}
	
	public KeyFile() {
		
	}

	public String getOriginName() {
		return originName;
	}

	public String getTargetName() {
		return targetName;
	}
	
}

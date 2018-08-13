package br.com.acmefileaccess.acmefileaccess.modelo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Alexsander Melo
 * @since 11/08/2018
 *
 */

public class Arquivo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String nome;
	
	private Long tamanho;
	
	@JsonFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	private Date ultimaModificacao;
	
	public Arquivo(String nome, Long tamanho, Date ultimaModificacao) {
		
		this.nome = nome;
		this.tamanho = tamanho;
		this.ultimaModificacao = ultimaModificacao;
	}

	public String getNome() {
		return nome;
	}

	public Long getTamanho() {
		return tamanho;
	}
	
	@JsonProperty
	public Double getTamanhoKB() {
		
		return new Double(tamanho)/1024;
	}
	
	public Date getUltimaModificacao() {
		return ultimaModificacao;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.nome == null) ? 0 : this.nome.hashCode());
		result = prime * result + ((this.tamanho == null) ? 0 : this.tamanho.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Arquivo)) {
			return false;
		}
		Arquivo other = (Arquivo) obj;

		return super.equals(obj) && this.nome.equals(other.nome) && this.tamanho.equals(other.tamanho);
	}
	
}

package com.minsait.avaliacao.entity;

import javax.persistence.Embeddable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Embeddable
public class Endereco {
	
	@NotBlank(message = "campo nao informado")
	private String rua;
	@Min(value = 1, message = "numero invalido")
	private int numero;
	@NotBlank(message = "campo nao informado")
	private String cep;

	
	
	public String getRua() {
		return rua;
	}
	
	public void setRua(String rua) {
		this.rua = rua;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public String getCep() {
		return cep;
	}
	
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	
}

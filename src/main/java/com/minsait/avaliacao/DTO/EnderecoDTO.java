package com.minsait.avaliacao.DTO;

import javax.persistence.Embeddable;

@Embeddable
public class EnderecoDTO {
		private String rua;
		private int numero;
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
		public EnderecoDTO(String rua, int numero, String cep) {
			this.rua = rua;
			this.numero = numero;
			this.cep = cep;
		}
		public EnderecoDTO() {
		}
		
		
}

package com.minsait.avaliacao.DTO;

import java.math.BigDecimal;

import com.minsait.avaliacao.entity.Cliente;

public class ClienteDTO {
		private long cpf;
		private String nome;
		private BigDecimal rendimentoMensal;
		private String telefone;
		private EnderecoDTO endereco;
		
		public long getCpf() {
			return cpf;
		}
		public void setCpf(long cpf) {
			this.cpf = cpf;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public BigDecimal getRendimentoMensal() {
			return rendimentoMensal;
		}
		public void setRendimentoMensal(BigDecimal rendimentoMensal) {
			this.rendimentoMensal = rendimentoMensal;
		}
		public String getTelefone() {
			return telefone;
		}
		public void setTelefone(String telefone) {
			this.telefone = telefone;
		}
		public EnderecoDTO getEndereco() {
			return endereco;
		}
		public void setEndereco(EnderecoDTO endereco) {
			this.endereco = endereco;
		}
		
		public ClienteDTO(Cliente cliente) {
			this.cpf = cliente.getCpf();
			this.nome = cliente.getNome();
			this.rendimentoMensal = cliente.getRendimentoMensal();
			this.telefone = cliente.getTelefone();
			EnderecoDTO enderecoDTO = new EnderecoDTO();
			enderecoDTO.setCep(cliente.getEndereco().getCep());
			enderecoDTO.setNumero(cliente.getEndereco().getNumero());
			enderecoDTO.setRua(cliente.getEndereco().getRua());
		}
		public ClienteDTO() {
		}
		
		
}

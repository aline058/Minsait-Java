package com.minsait.avaliacao.entity;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Table(name = "cliente")
@Entity
public class Cliente {
	
	@Id
	@Column(name = "cpf")
	@NotNull(message = "campo invalido")
	private Long cpf;

	
	@NotBlank(message = "campo nao informado")
	private String nome;
	@NotNull
	private BigDecimal rendimentoMensal;
	@NotBlank(message = "campo nao informado")
	private String telefone;
	
	@Valid
	private Endereco endereco;

	@OneToMany(cascade = CascadeType.ALL, mappedBy="cliente") 
	private List<Emprestimo> emprestimo;

	public Cliente() {
	}

	public List<Emprestimo> getEmprestimo() {
		return emprestimo;
	}

	public void setEmprestimo(List<Emprestimo> emprestimo) {
		this.emprestimo = emprestimo;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Endereco getEndereco() { return endereco; }
	  
	public void setEndereco(Endereco endereco) { this.endereco = endereco; }
	
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
	
	
	
}

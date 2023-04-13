package com.minsait.avaliacao.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.minsait.avaliacao.relacionamento.Relacionamento;

@Table(name = "emprestimo")
@Entity
public class Emprestimo {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@NotNull(message = "campo nao informado")
	private Long cpfCliente;
	@NotNull(message = "campo nao informado")
	private BigDecimal valorInicial;
	private BigDecimal valorFinal;
	
	private Date dataInicial;
	private Date dataFinal;
	
	
	private Relacionamento relacionamento;
	
	@ManyToOne
	@JsonIgnore
	private Cliente cliente;
	
	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public Emprestimo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public Long getCpfCliente() { return cpfCliente; }
	  
	public void setCpfCliente(Long cpfCliente) { 
		  this.cpfCliente = cpfCliente;
	  }
	 

	public BigDecimal getValorInicial() {
		return valorInicial;
	}

	public void setValorInicial(BigDecimal valorInicial) {
		this.valorInicial = valorInicial;
	}

	public BigDecimal getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal() {
		if(this.valorInicial != null) {
			this.valorFinal = this.relacionamento.retornaValorFinal(valorInicial, cliente);
		}
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Relacionamento getRelacionamento() {
		return relacionamento;
	}

	public void setRelacionamento(Relacionamento relacionamento) {
		this.relacionamento = relacionamento;
	}
	
	
	
	
}

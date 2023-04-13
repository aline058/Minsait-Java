package com.minsait.avaliacao.relacionamento;

import java.math.BigDecimal;
import java.math.MathContext;

import com.minsait.avaliacao.entity.Cliente;



public enum Relacionamento {
		BRONZE(1){
			@Override
			public BigDecimal retornaValorFinal(BigDecimal valorInicial, Cliente cliente) {
				BigDecimal fator = new BigDecimal("1.80");
				BigDecimal valorFinal = valorInicial.multiply(fator, MathContext.DECIMAL32);
				return valorFinal;
			}
		},
		
		PRATA(2){
			@Override
			public BigDecimal retornaValorFinal(BigDecimal valorInicial, Cliente cliente) {
				BigDecimal parametro = new BigDecimal("5000");
				if(valorInicial.compareTo(parametro)==1) {
					BigDecimal fator = new BigDecimal("1.40");
					BigDecimal valorFinal = valorInicial.multiply(fator, MathContext.DECIMAL32);
					return valorFinal;
				}else {
					BigDecimal fator = new BigDecimal("1.60");
					BigDecimal valorFinal = valorInicial.multiply(fator, MathContext.DECIMAL32);
					return valorFinal;
				}
			}
		},
		
		OURO(3){
			@Override
			public BigDecimal retornaValorFinal(BigDecimal valorInicial, Cliente cliente) {
				int quantidadeEmprestimos = cliente.getEmprestimo().size();
				if(quantidadeEmprestimos<=1) {
					BigDecimal fator = new BigDecimal("1.20");
					BigDecimal valorFinal = valorInicial.multiply(fator, MathContext.DECIMAL32);
					return valorFinal;
				}else {
					BigDecimal fator = new BigDecimal("1.30");
					BigDecimal valorFinal = valorInicial.multiply(fator, MathContext.DECIMAL32);
					return valorFinal;
				}
			}
	
			};
	
	  	private int codigo;

	    private Relacionamento(int codigo) {
	        this.codigo = codigo;
	    }
	 
	    public int getCodigo() { 
	        return this.codigo;
	    }
	    
	    

		public abstract BigDecimal retornaValorFinal(BigDecimal valorInicial, Cliente cliente);
		
		
}

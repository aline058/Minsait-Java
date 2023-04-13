package com.minsait.avaliacao.service;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.minsait.avaliacao.entity.Cliente;
import com.minsait.avaliacao.entity.Emprestimo;
import com.minsait.avaliacao.exception.ClienteNaoEncontrado;
import com.minsait.avaliacao.exception.EmprestimoNaoEncontrado;
import com.minsait.avaliacao.repository.ClienteRepository;
import com.minsait.avaliacao.repository.EmprestimoRepository;

@Service
public class EmprestimoService {

	private final EmprestimoRepository emprestimoRepository;

	 private final ClienteRepository clienteRepository; 

	@Autowired
	public EmprestimoService(EmprestimoRepository emprestimoRepository , ClienteRepository clienteRepository ) {
		this.emprestimoRepository = emprestimoRepository;
		 this.clienteRepository = clienteRepository; 
	}

	

	public List<Emprestimo> retornarTodosEmprestimos() {
		return this.emprestimoRepository.findAll();
	}


	public Emprestimo cadastrarEmprestimo(@Valid Emprestimo emprestimo) throws ClienteNaoEncontrado {
		if(this.clienteRepository.existsById(emprestimo.getCpfCliente())) {
			Cliente cliente = clienteRepository.findById(emprestimo.getCpfCliente()).get();
			emprestimo.setCliente(cliente);
			
			BigDecimal parametro = new BigDecimal("10"); 
			BigDecimal valorMaximo = cliente.getRendimentoMensal().multiply(parametro); 
			BigDecimal viNovoEmprestimo = emprestimo.getValorInicial();
			BigDecimal sum = new BigDecimal("0"); 
			
			  for(Emprestimo vi : cliente.getEmprestimo()) { 
				  sum = (sum.add(vi.getValorInicial())).add(viNovoEmprestimo); 
				  
			  } 
			  
			  if(sum.compareTo(valorMaximo)==-1&&viNovoEmprestimo.compareTo(valorMaximo)==-1){
				 emprestimo.setValorFinal();
				 cliente.getEmprestimo().add(emprestimo);
				return this.emprestimoRepository.save(emprestimo);
		  }
			  throw new IllegalArgumentException("valor nao permitido");
		}
		throw new ClienteNaoEncontrado(emprestimo.getCpfCliente());
	}



	public Emprestimo retornarEmprestimo(Long id) throws EmprestimoNaoEncontrado {
		if(this.emprestimoRepository.existsById(id)) {
			return this.emprestimoRepository.findById(id).get();
		}
		throw new EmprestimoNaoEncontrado(id);
	}



	public MensagemDeSucesso apagarEmprestimo(Long id) throws EmprestimoNaoEncontrado {
		if(this.emprestimoRepository.existsById(id)) {
			this.emprestimoRepository.deleteById(id);
			MensagemDeSucesso mensagem = new MensagemDeSucesso();
			mensagem.setMensagem("Emprestimo apagado com sucesso");
			return mensagem;
			
		}
		throw new EmprestimoNaoEncontrado(id);
	}

}

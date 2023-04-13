	package com.minsait.avaliacao.service;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.minsait.avaliacao.entity.Cliente;
import com.minsait.avaliacao.exception.ClienteNaoEncontrado;
import com.minsait.avaliacao.repository.ClienteRepository;


@Service
public class ClienteService {
	
	private final ClienteRepository clienteRepository;
	
	
	
	@Autowired
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	public Cliente cadastrarCliente(Cliente cliente) {
		return this.clienteRepository.save(cliente);
	}

	public List<Cliente> retornaTodosClientes() {
		return this.clienteRepository.findAll();
	}


	public Cliente retornarCliente(Long cpf) throws ClienteNaoEncontrado {
		if(this.clienteRepository.existsById(cpf)) {
			return this.clienteRepository.findById(cpf).get();
		}
		throw new ClienteNaoEncontrado(cpf);
		
	}

	public Cliente alterarCliente(Long cpf, @Valid Cliente cliente) throws ClienteNaoEncontrado {
		if(this.clienteRepository.existsById(cpf)) {
			Cliente clienteNaoAlterado = this.clienteRepository.findById(cpf).get();
			Long cpfRecuperado = clienteNaoAlterado.getCpf();
			cliente.setCpf(cpfRecuperado);
			return this.clienteRepository.save(cliente);
		}
		throw new ClienteNaoEncontrado(cpf);
	}

	public MensagemDeSucesso apagarCliente(Long cpf) throws ClienteNaoEncontrado {
		if(this.clienteRepository.existsById(cpf)) {
			this.clienteRepository.deleteById(cpf);
			MensagemDeSucesso mensagem = new MensagemDeSucesso();
			mensagem.setMensagem("Cliente apagado com sucesso");
			return mensagem;
		}
		throw new ClienteNaoEncontrado(cpf);
	}
	

}

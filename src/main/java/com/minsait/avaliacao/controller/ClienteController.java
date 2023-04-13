package com.minsait.avaliacao.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.minsait.avaliacao.entity.Cliente;
import com.minsait.avaliacao.exception.ClienteNaoEncontrado;
import com.minsait.avaliacao.service.ClienteService;
import com.minsait.avaliacao.service.MensagemDeSucesso;

@RestController
@RequestMapping("clientes")
public class ClienteController {

	private ClienteService clienteService;
	
	@Autowired
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}


	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente cadastrarCliente(@Valid @RequestBody Cliente cliente) {
		return this.clienteService.cadastrarCliente(cliente);
	}
	
	
	@GetMapping
	public List<Cliente> retornarTodosClientes() {
		return this.clienteService.retornaTodosClientes();
	}
	
	
	@GetMapping("/{cpf}")
	public Cliente retornarCliente(@PathVariable Long cpf) throws ClienteNaoEncontrado {
		return this.clienteService.retornarCliente(cpf);
	}
	
	@PutMapping("/{cpf}")
	public Cliente alterarCliente(@Valid @RequestBody Cliente cliente, @PathVariable Long cpf) throws ClienteNaoEncontrado {
		return this.clienteService.alterarCliente(cpf, cliente);
	}
	
	@DeleteMapping("/{cpf}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public MensagemDeSucesso apagarCliente(@Valid @PathVariable Long cpf) throws ClienteNaoEncontrado {
		return this.clienteService.apagarCliente(cpf);
	}
	
	
	
	
	//exibir mensagens de erro das validacoes
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationException(MethodArgumentNotValidException ex){
		Map<String, String> errors = new HashMap<>();
		
			ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
			return errors;
	}
	
	
}

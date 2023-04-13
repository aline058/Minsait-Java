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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.minsait.avaliacao.entity.Emprestimo;
import com.minsait.avaliacao.exception.ClienteNaoEncontrado;
import com.minsait.avaliacao.exception.EmprestimoNaoEncontrado;
import com.minsait.avaliacao.service.EmprestimoService;
import com.minsait.avaliacao.service.MensagemDeSucesso;

@RestController
@RequestMapping("clientes/{cpf}/emprestimos")
public class EmprestimoController {

	private EmprestimoService emprestimoService;

	@Autowired
	public EmprestimoController(EmprestimoService emprestimoService) {
		this.emprestimoService = emprestimoService;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Emprestimo cadastrarEmprestimo(@Valid @RequestBody Emprestimo emprestimo) throws ClienteNaoEncontrado{
		return this.emprestimoService.cadastrarEmprestimo(emprestimo);
	}


	@GetMapping
	public List<Emprestimo> retornarTodosEmprestismos() {
		return this.emprestimoService.retornarTodosEmprestimos();
	}
	
	@GetMapping("/{id}")
	public Emprestimo retornarEmprestimo(@PathVariable Long id) throws EmprestimoNaoEncontrado {
		return this.emprestimoService.retornarEmprestimo(id);
		
	}
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public MensagemDeSucesso apagarEmprestimo(@Valid @PathVariable Long id) throws EmprestimoNaoEncontrado {
		return this.emprestimoService.apagarEmprestimo(id);
	}
	
	//exibir mensagem de erro das validacoes
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

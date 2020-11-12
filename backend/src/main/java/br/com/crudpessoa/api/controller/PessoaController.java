package br.com.crudpessoa.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.crudpessoa.api.model.Pessoa;
import br.com.crudpessoa.api.service.PessoaService;


@RestController
@RequestMapping(path = "/")
public class PessoaController {


	@Autowired
	private PessoaService pessoaService;
	
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(path = "/pessoas")
	public @ResponseBody Iterable<Pessoa> getPessoas() {
		return pessoaService.getPessoas();
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(path = "/pessoas")
	public Pessoa Post(@RequestBody Pessoa pessoa)
	{
		return pessoaService.save(pessoa);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping(value = "/pessoas/{id}")
	public ResponseEntity<Pessoa> put(@PathVariable(value = "id") long id, @RequestBody Pessoa newPessoa)
	{
		Optional<Pessoa> oldPessoa = pessoaService.findById(id);
		if(oldPessoa.isPresent()){
			Pessoa pessoa = oldPessoa.get();
			pessoa.setNome(newPessoa.getNome());
			pessoa.setEmail(newPessoa.getEmail());
			pessoa.setCpf(newPessoa.getCpf());
			pessoa.setTelefonePrincipal(newPessoa.getTelefonePrincipal());
			pessoa.setTelefoneRecado(newPessoa.getTelefoneRecado());
			pessoaService.save(pessoa);
			return new ResponseEntity<Pessoa>(pessoa, HttpStatus.OK);
		}
		else
			return new ResponseEntity<Pessoa>(HttpStatus.NOT_FOUND);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping(value = "/pessoas/{id}")
	public ResponseEntity<Object> delete(@PathVariable(value = "id") long id)
	{
		Optional<Pessoa> pessoa = pessoaService.findById(id);
		if(pessoa.isPresent()){
			pessoaService.delete(pessoa.get());
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
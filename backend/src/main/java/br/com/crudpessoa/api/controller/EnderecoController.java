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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.crudpessoa.api.model.Endereco;
import br.com.crudpessoa.api.service.EnderecoService;

@RestController
public class EnderecoController {

	@Autowired
	private EnderecoService enderecoService;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(path = "/enderecos")
	public @ResponseBody Iterable<Endereco> getEnderecos() {
		return enderecoService.getEnderecos();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(path = "/enderecos/{id}")
	public @ResponseBody Iterable<Endereco> getEnderecosByPessoa(@PathVariable(value = "id") long id) {
		return enderecoService.getEnderecosByPessoa(id);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(path = "/enderecos/{id}")
	public @ResponseBody Iterable<Endereco> Post(@PathVariable(value = "id") long id,@RequestBody Endereco endereco)
	{
		endereco.setPessoa_id(id);
		enderecoService.save(endereco);
		return enderecoService.getEnderecosByPessoa(id);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping(value = "/enderecos/{id}")
	public ResponseEntity<Endereco> put(@PathVariable(value = "id") long id, @RequestBody Endereco newEndereco)
	{
		Optional<Endereco> oldEndereco = enderecoService.findById(id);
		if(oldEndereco.isPresent()){
			Endereco endereco = oldEndereco.get();
			endereco.setLogradouro(newEndereco.getLogradouro());
			endereco.setCep(newEndereco.getCep());
			endereco.setComplemento(newEndereco.getComplemento());
			endereco.setCidade(newEndereco.getCidade());
			endereco.setEstado(newEndereco.getEstado());
			endereco.setNumero(newEndereco.getNumero());
			endereco.setBairro(newEndereco.getBairro());
			enderecoService.save(endereco);
			return new ResponseEntity<Endereco>(endereco, HttpStatus.OK);
		}
		else
			return new ResponseEntity<Endereco>(HttpStatus.NOT_FOUND);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping(value = "/enderecos/{id}")
	public ResponseEntity<Object> delete(@PathVariable(value = "id") long id)
	{
		Optional<Endereco> endereco = enderecoService.findById(id);
		if(endereco.isPresent()){
			enderecoService.delete(endereco.get());
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}

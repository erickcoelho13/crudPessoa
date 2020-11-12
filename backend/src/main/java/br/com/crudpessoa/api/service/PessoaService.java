package br.com.crudpessoa.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crudpessoa.api.model.Pessoa;
import br.com.crudpessoa.api.repository.PessoaRepository;

@Component("pessoaService")
public class PessoaService {
	@Autowired 
	PessoaRepository pessoaRepository;
	public Iterable<Pessoa> getPessoas() {
		return pessoaRepository.findAll();
	}
	
	public Pessoa save(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}

	public Optional<Pessoa> findById(long id) {
		return pessoaRepository.findById(id);
	}

	public void delete(Pessoa pessoa) {
		pessoaRepository.delete(pessoa);
	}
	
}

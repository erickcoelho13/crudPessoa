package br.com.crudpessoa.api.service;

import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crudpessoa.api.model.Endereco;
import br.com.crudpessoa.api.repository.EnderecoRepository;

@Component("enderecoService")
public class EnderecoService {
	@Autowired
	EnderecoRepository enderecoRepository;

	public Iterable<Endereco> getEnderecos() {
		return enderecoRepository.findAll();
	}
	
	public Iterable<Endereco> getEnderecosByPessoa(long id) {
		Iterable<Endereco> list = () -> StreamSupport.stream(enderecoRepository.findAll().spliterator(), false)
		        .filter(endereco -> endereco.getPessoa_id().longValue() == id)
		        .iterator();
		return list;
	}

	public Endereco save(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}

	public Optional<Endereco> findById(long id) {
		return enderecoRepository.findById(id);
	}

	public void delete(Endereco endereco) {
		enderecoRepository.delete(endereco);
	}

}

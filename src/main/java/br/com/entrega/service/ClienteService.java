package br.com.entrega.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.entrega.model.Cliente;
import br.com.entrega.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;

	public List<Cliente> listar() {
		return repository.findAll();
	}

	public List<Cliente> buscaPorNome(String nome) {
		return repository.findByNomeContaining(nome);
	}

	public Optional<Cliente> buscaPorId(Long id) {
		return repository.findById(id);
	}

	public Cliente adiciona(Cliente cliente) {
		return repository.save(cliente);
	}

	public void excluir(Long id) {
		repository.deleteById(id);
	}

}

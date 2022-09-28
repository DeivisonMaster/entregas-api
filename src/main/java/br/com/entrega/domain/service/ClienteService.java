package br.com.entrega.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.entrega.domain.exception.NegocioException;
import br.com.entrega.domain.model.Cliente;
import br.com.entrega.domain.repository.ClienteRepository;

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

	@Transactional
	public Cliente adiciona(Cliente cliente) throws NegocioException {
		Optional<Cliente> clienteOptional = repository.findByEmail(cliente.getEmail());
		if(clienteOptional.isPresent() && !clienteOptional.get().equals(cliente)) {
			throw new NegocioException("Já existe um cliente cadastrado com este email");
		}
		return repository.save(cliente);
	}

	@Transactional
	public void excluir(Long id) {
		repository.deleteById(id);
	}

}

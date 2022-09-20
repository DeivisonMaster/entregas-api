package br.com.entrega.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.entrega.model.Cliente;
import br.com.entrega.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;

	public List<Cliente> listar() {
		Cliente c = new Cliente();
		c.setId(1L);
		c.setNome("João");
		return Arrays.asList(c);
	}

}

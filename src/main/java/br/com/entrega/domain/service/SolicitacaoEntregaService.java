package br.com.entrega.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.entrega.domain.exception.NegocioException;
import br.com.entrega.domain.model.Cliente;
import br.com.entrega.domain.model.Entrega;
import br.com.entrega.domain.model.StatusEntrega;
import br.com.entrega.domain.repository.EntregaRepository;

@Service
public class SolicitacaoEntregaService {
	
	@Autowired
	private EntregaRepository repository;
	
	@Autowired
	private CatalagoClienteService clienteService;
	
	public Entrega solicitar(Entrega entrega) throws NegocioException {
		Cliente cliente = clienteService.buscar(entrega.getCliente().getId());
		
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(OffsetDateTime.now());
		entrega.setCliente(cliente);
		
		return repository.save(entrega);
	}
	
}

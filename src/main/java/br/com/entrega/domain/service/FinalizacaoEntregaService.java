package br.com.entrega.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.entrega.domain.exception.NegocioException;
import br.com.entrega.domain.model.Entrega;
import br.com.entrega.domain.repository.EntregaRepository;

@Service
public class FinalizacaoEntregaService {

	@Autowired
	private BuscaEntregaService buscaEntregaService;
	
	@Autowired
	private EntregaRepository entregaRepository;
	
	public void finalizar(Long idEntrega) throws NegocioException {
		Entrega entrega = buscaEntregaService.busca(idEntrega);
		entrega.finalizar();
		entregaRepository.save(entrega);
	}
}

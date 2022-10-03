package br.com.entrega.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.entrega.domain.exception.NegocioException;
import br.com.entrega.domain.model.Entrega;
import br.com.entrega.domain.repository.EntregaRepository;

@Service
public class BuscaEntregaService {
	
	@Autowired
	private EntregaRepository entregaRepository;

	public Entrega busca(Long id) throws NegocioException {
		return entregaRepository.findById(id)
				.orElseThrow(() -> new NegocioException("Entrega não encontrada"));
	}

}

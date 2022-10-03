package br.com.entrega.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.entrega.domain.exception.NegocioException;
import br.com.entrega.domain.model.Entrega;
import br.com.entrega.domain.model.Ocorrencia;

@Service
public class RegistroOcorrenciaService {
	
	@Autowired
	private BuscaEntregaService buscaEntregaService;
	
	@Transactional
	public Ocorrencia registraOcorrenciaDeEntrega(Long id, String descricao) throws NegocioException {
		Entrega entrega = buscaEntregaService.busca(id);
		return entrega.adicionaOcorrencia(descricao);
	}
}

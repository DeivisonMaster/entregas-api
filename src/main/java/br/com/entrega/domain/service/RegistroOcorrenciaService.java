package br.com.entrega.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.entrega.domain.exception.NegocioException;
import br.com.entrega.domain.model.Entrega;
import br.com.entrega.domain.model.Ocorrencia;
import br.com.entrega.domain.repository.OcorrenciaRepository;

@Service
public class RegistroOcorrenciaService {
	
	@Autowired
	private BuscaEntregaService buscaEntregaService;
	
	@Autowired
	private OcorrenciaRepository ocorrenciaRepository;
	
	@Transactional
	public Ocorrencia registraOcorrenciaDeEntrega(Long id, String descricao) throws NegocioException {
		Entrega entrega = buscaEntregaService.busca(id);
		return entrega.adicionaOcorrencia(descricao);
	}

	@Transactional
	public Ocorrencia atualiza(Long idEntrega,
			Long idOcorrencia, String descricao) throws NegocioException {
		buscaEntregaService.busca(idEntrega);
		
		Optional<Ocorrencia> ocorrenciaOptional = ocorrenciaRepository.findById(idOcorrencia);
		if(!ocorrenciaOptional.isPresent()) {
			throw new NegocioException("Ocorrência não encontrada!");
		}
		Ocorrencia ocorrencia = ocorrenciaOptional.get();
		ocorrencia.setId(idOcorrencia);
		ocorrencia.setDescricao(descricao);
		return ocorrenciaRepository.save(ocorrencia);
	}

	public List<Ocorrencia> listar() {
		return ocorrenciaRepository.findAll();
	}
}

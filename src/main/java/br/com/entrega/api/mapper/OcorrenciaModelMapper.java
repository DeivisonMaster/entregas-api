package br.com.entrega.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.entrega.api.model.OcorrenciaDTOSaida;
import br.com.entrega.domain.model.Entrega;
import br.com.entrega.domain.model.Ocorrencia;

@Component
public class OcorrenciaModelMapper {
	
	
	@Autowired
	private ModelMapper modelMapper;
	
	public OcorrenciaDTOSaida obtemOcorrenciaSaidaPorOcorrencia(Ocorrencia ocorrencia) {
		return modelMapper.map(ocorrencia, OcorrenciaDTOSaida.class);
	}

	public List<OcorrenciaDTOSaida> obtemListaOcorenciaSaidaPorEntrega(Entrega entrega) {
		return entrega.getOcorrencias().stream()
				.map(this::obtemOcorrenciaSaidaPorOcorrencia).collect(Collectors.toList());
	}

	public List<OcorrenciaDTOSaida> obtemListaOcorencia(List<Ocorrencia> ocorrencias) {
		return ocorrencias.stream().map(this::obtemOcorrenciaSaidaPorOcorrencia).collect(Collectors.toList());
	}
	
}

package br.com.entrega.api.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.entrega.api.model.OcorrenciaDTOSaida;
import br.com.entrega.domain.model.Ocorrencia;

@Component
public class OcorrenciaModelMapper {
	
	
	@Autowired
	private ModelMapper modelMapper;
	
	public OcorrenciaDTOSaida obtemOcorrenciaSaidaPorOcorrencia(Ocorrencia ocorrencia) {
		return modelMapper.map(ocorrencia, OcorrenciaDTOSaida.class);
	}
}

package br.com.entrega.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.entrega.api.model.EntregaDTO;
import br.com.entrega.api.model.EntregaDTOentrada;
import br.com.entrega.domain.model.Entrega;

@Component
public class EntregaModelMapper {
	
	@Autowired
	private ModelMapper modelMapper;
			
	public EntregaDTO obtemEntregaDTO(Entrega entrega) {
		return modelMapper.map(entrega, EntregaDTO.class);
	}

	public List<EntregaDTO> obtemListaEntregaDTO(List<Entrega> entregas) {
		return entregas.stream().map(this::obtemEntregaDTO).collect(Collectors.toList());
	}

	public Entrega obtemEntregaPorEntregaDTOEntrada(EntregaDTOentrada entregaDTOEntrada) {
		return modelMapper.map(entregaDTOEntrada, Entrega.class);
	}
}

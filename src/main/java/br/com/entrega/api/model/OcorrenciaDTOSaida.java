package br.com.entrega.api.model;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OcorrenciaDTOSaida {
	
	private Long id;
	private String descricao;
	private OffsetDateTime dataRegistro;
}

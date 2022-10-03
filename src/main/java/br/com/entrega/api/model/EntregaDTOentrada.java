package br.com.entrega.api.model;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntregaDTOentrada {
	
	@Valid
	@NotNull
	private ClienteDTO cliente;
	
	@Valid
	@NotNull
	private DestinatarioDTOEntrada destinatario;
	
	@NotNull
	private BigDecimal taxa;
	
}

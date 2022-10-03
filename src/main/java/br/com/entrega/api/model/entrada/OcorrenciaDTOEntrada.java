package br.com.entrega.api.model.entrada;


import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OcorrenciaDTOEntrada {
	
	@NotBlank
	private String descricao;
}

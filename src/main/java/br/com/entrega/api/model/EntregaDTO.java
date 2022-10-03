package br.com.entrega.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import br.com.entrega.domain.model.StatusEntrega;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntregaDTO {
	private Long id;
	private BigDecimal taxa;
	private OffsetDateTime dataPedido;
	private OffsetDateTime dataFinalizacao;
	private ClienteDTOResumo cliente;
	private DestinatarioDTO destinatario;
	private StatusEntrega status;
}

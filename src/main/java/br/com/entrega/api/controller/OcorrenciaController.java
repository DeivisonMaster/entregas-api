package br.com.entrega.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.entrega.api.mapper.OcorrenciaModelMapper;
import br.com.entrega.api.model.OcorrenciaDTOSaida;
import br.com.entrega.api.model.entrada.OcorrenciaDTOEntrada;
import br.com.entrega.domain.exception.NegocioException;
import br.com.entrega.domain.model.Ocorrencia;
import br.com.entrega.domain.service.RegistroOcorrenciaService;

@RestController
@RequestMapping("/entregas/{id}/ocorrencias")
public class OcorrenciaController {
	
	@Autowired
	private RegistroOcorrenciaService  registroOcorrenciaService;
	
	@Autowired
	private OcorrenciaModelMapper ocorrenciaModelMapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OcorrenciaDTOSaida registrar(@PathVariable Long id, 
			@Valid @RequestBody OcorrenciaDTOEntrada ocorrenciaEntrada) throws NegocioException {
		Ocorrencia ocorrenciaRegistrada = registroOcorrenciaService.registraOcorrenciaDeEntrega(id, ocorrenciaEntrada.getDescricao());
		return ocorrenciaModelMapper.obtemOcorrenciaSaidaPorOcorrencia(ocorrenciaRegistrada);
	}
}

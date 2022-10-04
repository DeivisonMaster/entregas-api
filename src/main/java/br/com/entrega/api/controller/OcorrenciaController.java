package br.com.entrega.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.entrega.api.mapper.OcorrenciaModelMapper;
import br.com.entrega.api.model.OcorrenciaDTOSaida;
import br.com.entrega.api.model.entrada.OcorrenciaDTOEntrada;
import br.com.entrega.domain.exception.NegocioException;
import br.com.entrega.domain.model.Entrega;
import br.com.entrega.domain.model.Ocorrencia;
import br.com.entrega.domain.service.BuscaEntregaService;
import br.com.entrega.domain.service.RegistroOcorrenciaService;

@RestController
@RequestMapping("/entregas/{id}/ocorrencias")
public class OcorrenciaController {
	
	@Autowired
	private RegistroOcorrenciaService  registroOcorrenciaService;
	
	@Autowired
	private OcorrenciaModelMapper ocorrenciaModelMapper;
	
	@Autowired
	private BuscaEntregaService buscaEntregaService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OcorrenciaDTOSaida registrar(@PathVariable Long id, 
			@Valid @RequestBody OcorrenciaDTOEntrada ocorrenciaEntrada) throws NegocioException {
		Ocorrencia ocorrenciaRegistrada = registroOcorrenciaService.registraOcorrenciaDeEntrega(id, ocorrenciaEntrada.getDescricao());
		return ocorrenciaModelMapper.obtemOcorrenciaSaidaPorOcorrencia(ocorrenciaRegistrada);
	}
	
	@PutMapping("/{idOcorrencia}")
	public ResponseEntity<OcorrenciaDTOSaida> atualiza(@PathVariable Long id, 
			@PathVariable Long idOcorrencia, @Valid @RequestBody OcorrenciaDTOEntrada ocorrenciaEntrada) throws NegocioException{
		Ocorrencia ocorrenciaAtualizada = registroOcorrenciaService.atualiza(id, idOcorrencia, ocorrenciaEntrada.getDescricao());
		return ResponseEntity.ok(ocorrenciaModelMapper.obtemOcorrenciaSaidaPorOcorrencia(ocorrenciaAtualizada));
	}
	
	@GetMapping
	public List<OcorrenciaDTOSaida> listarPorId(@PathVariable Long id) throws NegocioException{
		Entrega entrega = buscaEntregaService.busca(id);
		return ocorrenciaModelMapper.obtemListaOcorenciaSaidaPorEntrega(entrega);
	}
	
	@GetMapping("/listar")
	public List<OcorrenciaDTOSaida> listar(@PathVariable Long id) throws NegocioException{
		List<Ocorrencia> ocorrencias = registroOcorrenciaService.listar();
		return ocorrenciaModelMapper.obtemListaOcorencia(ocorrencias);
	}
}

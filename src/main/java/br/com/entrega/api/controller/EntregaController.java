package br.com.entrega.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.entrega.domain.exception.NegocioException;
import br.com.entrega.domain.model.Entrega;
import br.com.entrega.domain.repository.EntregaRepository;
import br.com.entrega.domain.service.SolicitacaoEntregaService;

@RestController
@RequestMapping("/entregas")
public class EntregaController {
	
	@Autowired
	private SolicitacaoEntregaService entregaService;
	
	@Autowired
	private EntregaRepository entregaRepository;
	
	@GetMapping
	public List<Entrega> listar(){
		return entregaRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Entrega solicitar(@Valid @RequestBody Entrega entrega) throws NegocioException {
		return entregaService.solicitar(entrega);
	}
	
	
}

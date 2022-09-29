package br.com.entrega.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/{id}")
	public ResponseEntity<Entrega> buscarPorId(@PathVariable Long id){
		Optional<Entrega> entregaOptional = entregaRepository.findById(id);
		if(!entregaOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(entregaOptional.get());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id){
		Optional<Entrega> entregaOptional = entregaRepository.findById(id);
		if(!entregaOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		entregaRepository.delete(entregaOptional.get());
		return ResponseEntity.ok().build();
	}
	
}

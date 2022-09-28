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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.entrega.domain.exception.NegocioException;
import br.com.entrega.domain.model.Cliente;
import br.com.entrega.domain.service.CatalagoClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private CatalagoClienteService service;
	
	@GetMapping
	public List<Cliente> listar() {
		return service.listar();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscaPorId(@PathVariable Long id) {
		Optional<Cliente> cliente = service.buscaPorId(id);
		if(!cliente.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cliente.get());
	}
	
	@GetMapping("/porNome/{nome}")
	public List<Cliente> buscaPorNome(@PathVariable String nome){
		return service.buscaPorNome(nome);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Cliente adiciona(@Valid @RequestBody Cliente cliente) throws NegocioException {
		return service.adiciona(cliente);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualiza(@Valid @PathVariable Long id, @RequestBody Cliente cliente) throws NegocioException {
		Optional<Cliente> clienteOptional = service.buscaPorId(id);
		if(!clienteOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		cliente.setId(id);
		cliente = service.adiciona(cliente);
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id){
		Optional<Cliente> clienteOptional = service.buscaPorId(id);
		if(!clienteOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		service.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
	
}

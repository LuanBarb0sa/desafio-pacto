package br.com.pacto.desafio.resources;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.pacto.desafio.dto.ContaDTO;
import br.com.pacto.desafio.entities.Conta;
import br.com.pacto.desafio.services.ContaService;

@RestController
@RequestMapping("/conta")
public class ContaResource {

	@Autowired
	private ContaService contaService;

	@PostMapping
	public ResponseEntity<Conta> cadastrarConta(@RequestBody ContaDTO contaDTO) {
		try {
			// alterar
			Conta conta = Conta.fromDTO(contaDTO);
			contaService.incluirConta(conta);
			return ResponseEntity.ok().build();

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Conta> obterContaPorId(@PathVariable Long id) {
		try {
			Conta conta = contaService.obterPorId(id);
			return ResponseEntity.ok(conta);
		} catch (Exception e) { 
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	}
	
//	@PutMapping("/{id}")
//	public ResponseEntity<String> depositar(@PathVariable Long id,
//			@RequestParam BigDecimal valorDeposito) {
//		
//		try {
//			
//			contaService.depositar(id, valorDeposito);
//			return ResponseEntity.ok("Depósito realizado com sucesso.");
//		} catch (Exception e) {
//			e.printStackTrace();
//			return ResponseEntity.internalServerError().build();
//		}
//		
//	}

}

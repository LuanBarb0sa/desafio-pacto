package br.com.pacto.desafio.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pacto.desafio.dto.TransacaoDTO;
import br.com.pacto.desafio.entities.Transacao;
import br.com.pacto.desafio.services.TransacaoService;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/transacao")
public class TransacaoResource {

	@Autowired
	private TransacaoService transacaoService;

	@PostMapping
	public ResponseEntity incluirTransacao(@RequestBody TransacaoDTO transacaoDTO) {
		try {
			transacaoService.incluirTransacao(transacaoDTO);
			return ResponseEntity.ok().build();
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Conta não encontrada com o ID fornecido.");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor.");
		}
		
	}

	@GetMapping("/transacoes")
	public ResponseEntity<List<Transacao>> listarTransacoes() {
		try {
			List<Transacao> transacoes = transacaoService.listarTodasTransacoes();
			return ResponseEntity.ok(transacoes);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}

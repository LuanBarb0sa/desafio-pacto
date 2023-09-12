package br.com.pacto.desafio.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pacto.desafio.entities.Conta;
import br.com.pacto.desafio.repositories.ContaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ContaService {
	
	@Autowired
	private ContaRepository contaRepository;
	
	private static final Logger LOGGER = Logger.getLogger(ContaService.class.getName());
			
	@Transactional
	public void incluirConta(Conta conta) {
		LOGGER.info("Incluindo conta Nº" + conta);
		contaRepository.save(conta);
	}

	public Conta obterPorId(Long id) {
		LOGGER.info("Obter conta por id:" + id);
		return contaRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Conta não encontrada."));
	}

	@Transactional
	public void atualizarSaldoConta(Long id, BigDecimal novoLimite) {
		Conta conta = contaRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Conta não encontrada."));
		
		conta.setDataLimite(LocalDateTime.now());
		conta.setLimiteDisponivel(novoLimite);
		contaRepository.save(conta);
	}

//	@Transactional
//	public void depositar(Long id, BigDecimal valorDeposito) {
//		Conta conta = contaRepository.findById(id)
//				.orElseThrow(() -> new EntityNotFoundException("Conta não encontrada."));
//		
//		if(valorDeposito.compareTo(BigDecimal.ZERO) <= 0) {
//			throw new IllegalArgumentException("Valor de depósito inválido.");
//		}
//		
//		BigDecimal novoLimite = conta.getLimiteDisponivel().add(valorDeposito);
//		conta.setLimiteDisponivel(novoLimite);
//		conta.setDataLimite(LocalDateTime.now());
//		contaRepository.save(conta);
//	}

}

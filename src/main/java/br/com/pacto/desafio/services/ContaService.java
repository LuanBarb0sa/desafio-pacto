package br.com.pacto.desafio.services;

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

}

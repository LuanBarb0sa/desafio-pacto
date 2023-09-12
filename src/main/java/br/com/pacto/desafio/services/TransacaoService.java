package br.com.pacto.desafio.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pacto.desafio.dto.TransacaoDTO;
import br.com.pacto.desafio.entities.Conta;
import br.com.pacto.desafio.entities.Transacao;
import br.com.pacto.desafio.repositories.TransacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class TransacaoService {
	
	@Autowired
	private TransacaoRepository transacaoRepository;
	
	@Autowired
	private ContaService contaService;
	
	@Transactional
	public void incluirTransacao(TransacaoDTO transacaoDTO) {
		try {
			if (transacaoDTO == null) {
				throw new IllegalArgumentException("TransacaoDTO não pode ser nulo.");
			}

			if (transacaoDTO.getContaId() == null) {
				throw new IllegalArgumentException("ID da conta não pode ser nulo.");
			}

			Conta conta = contaService.obterPorId(transacaoDTO.getContaId());
			if (conta == null) {
				throw new EntityNotFoundException("Conta não encontrada com o ID: " + transacaoDTO.getContaId());
			}

			Transacao obj = new Transacao();
			obj.setConta(conta);
			obj.setValor(transacaoDTO.getValor());
			obj.setTipoOperacaoId(transacaoDTO.getTipoOperacao().getId());
			obj.setDataTransacao(LocalDateTime.now());
			transacaoRepository.save(obj);

		} catch (IllegalArgumentException | EntityNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao incluir a transação.", e);
		}
	}

	public List<Transacao> listarTodasTransacoes() {
        List<Transacao> transacoes = transacaoRepository.findAll();
        return transacoes;
    }

}

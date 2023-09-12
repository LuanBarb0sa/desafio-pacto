package br.com.pacto.desafio.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pacto.desafio.dto.TransacaoDTO;
import br.com.pacto.desafio.entities.Conta;
import br.com.pacto.desafio.entities.Transacao;
import br.com.pacto.desafio.enumerated.TipoOperacao;
import br.com.pacto.desafio.repositories.TransacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class TransacaoService {

	private static final Logger LOGGER = Logger.getLogger(TransacaoService.class.getName());

	@Autowired
	private TransacaoRepository transacaoRepository;

	@Autowired
	private ContaService contaService;

	@Transactional
	public void incluirTransacao(TransacaoDTO transacaoDTO) {

		LOGGER.info("Incluindo transacao" + transacaoDTO);

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

			if (transacaoDTO.getTipoOperacao() == TipoOperacao.PAGAMENTO) {
				BigDecimal novoLimite = conta.getLimiteDisponivel().add(transacaoDTO.getValor());
				contaService.atualizarSaldoConta(conta.getId(), novoLimite);
			} else {
				BigDecimal novoLimite = conta.getLimiteDisponivel().subtract(transacaoDTO.getValor());
				if (novoLimite.compareTo(BigDecimal.ZERO) < 0) {
					throw new IllegalArgumentException("Operação não permitida, a conta não tem saldo suficiente.");
				}
				conta.setLimiteDisponivel(novoLimite);
				contaService.atualizarSaldoConta(conta.getId(), novoLimite);
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

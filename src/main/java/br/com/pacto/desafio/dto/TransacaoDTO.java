package br.com.pacto.desafio.dto;

import java.math.BigDecimal;

import br.com.pacto.desafio.enumerated.TipoOperacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized @Builder
public class TransacaoDTO {
	
	private Long contaId;
	private TipoOperacao tipoOperacao;
	private BigDecimal valor;

}

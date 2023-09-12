package br.com.pacto.desafio.entities;

import java.io.Serializable;

import br.com.pacto.desafio.dto.ContaDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_conta")
public class Conta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;

	@Column(name = "conta", nullable = false)
	private Integer numeroConta;
	
	
	public static Conta fromDTO(ContaDTO contaDTO) {
		Conta conta = new Conta();
		conta.setNumeroConta(contaDTO.getNumeroConta());
		return conta;
	}

}

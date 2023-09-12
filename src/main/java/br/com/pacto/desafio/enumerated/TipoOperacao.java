package br.com.pacto.desafio.enumerated;

public enum TipoOperacao {

	COMPRA_A_VISTA(1),
	COMPRA_PARCELADA(2),
	SAQUE(3),
	PAGAMENTO(4);
	
	private final int id;
	
	TipoOperacao(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
}

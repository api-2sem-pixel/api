package dto;

public class IntegrantesCrDTO {

	private String integrante;
	private String descricao;

	public IntegrantesCrDTO(String integrante, String descricao) {
		this.integrante = integrante;
		this.descricao = descricao;
	}

	public String getIntegrante() {
		return integrante;
	}

	public void setIntegrante(String integrante) {
		this.integrante = integrante;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}

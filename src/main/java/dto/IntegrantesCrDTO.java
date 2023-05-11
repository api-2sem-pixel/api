package dto;

public class IntegrantesCrDTO {

	private String integrante;
	private String descricao;
	private int idUsuario;
	private int idCr;
	
	public IntegrantesCrDTO(String integrante, String descricao, int idUsuario, int idCr) {
		this.integrante = integrante;
		this.descricao = descricao;
		this.idUsuario = idUsuario;
		this.idCr = idCr;
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

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdCr() {
		return idCr;
	}

	public void setIdCr(int idCr) {
		this.idCr = idCr;
	}

}

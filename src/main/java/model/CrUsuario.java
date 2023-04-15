package model;

public class CrUsuario {
	
	private Integer idUsuario;
	private Integer idCr;
	
	public CrUsuario(Integer idUsuario, Integer idCr) {
		this.idUsuario = idUsuario;
		this.idCr = idCr;
	}
	
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Integer getIdCr() {
		return idCr;
	}
	public void setIdCr(Integer idCr) {
		this.idCr = idCr;
	}
	
	
}

package dto;

import java.util.List;

public class CrDTO {

	private String nomeCR;
	private Integer idGestor;
	
	
	public CrDTO(String nomeCR, Integer idGestor) {
		super();
		this.nomeCR = nomeCR;
		this.idGestor = idGestor;
	}
	public String getNomeCR() {
		return nomeCR;
	}
	public void setNomeCR(String nomeCR) {
		this.nomeCR = nomeCR;
	}
	public Integer getIdGestor() {
		return idGestor;
	}
	public void setIdGestor(Integer idGestor) {
		this.idGestor = idGestor;
	}
	
	@Override
	public String toString() {
		return this.getNomeCR();
	}
}

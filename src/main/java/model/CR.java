package model;

public class CR {

	private Integer id;
	private Integer id_gestor;
	private String nomeCR;
	
	public CR(Integer id, Integer id_gestor, String nomeCR) {
		this.id = id;
		this.id_gestor = id_gestor;
		this.nomeCR = nomeCR;
	}
	
	public CR(Integer id_gestor, String nomeCR) {
		this.id_gestor = id_gestor;
		this.nomeCR = nomeCR;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId_gestor() {
		return id_gestor;
	}
	public void setId_gestor(Integer id_gestor) {
		this.id_gestor = id_gestor;
	}
	public String getNomeCR() {
		return nomeCR;
	}
	public void setNomeCR(String nomeCR) {
		this.nomeCR = nomeCR;
	}
	
	
}

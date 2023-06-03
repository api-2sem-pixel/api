package model;

public class RelatorioModel {

	private String matricula;
	private String nome;
	private String verba;
	private Double quantidadeHoras;
	
	public RelatorioModel(String matricula, String nome, String verba, Double quantidadeHoras) {
		this.matricula = matricula;
		this.nome = nome;
		this.verba = verba;
		this.quantidadeHoras = quantidadeHoras;
	}
	
	public RelatorioModel() {
		
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getVerba() {
		return verba;
	}

	public void setVerba(String verba) {
		this.verba = verba;
	}

	public Double getQuantidadeHoras() {
		return quantidadeHoras;
	}

	public void setQuantidadeHoras(Double quantidadeHoras) {
		this.quantidadeHoras = quantidadeHoras;
	}
	
}

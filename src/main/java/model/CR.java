package model;

public class CR {

	private Integer id;
	private String nome;
	private String sigla;
	private String codigo;
	
	public CR(String nome, String sigla, String codigo) {
		this.nome = nome;
		this.sigla = sigla;
		this.codigo = codigo;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
		
	
}

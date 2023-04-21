package dto;

public class CrDTO {

	private Integer id;
	private String nome;
	private String sigla;
	private String codigo;

	public CrDTO(Integer id, String nome, String sigla, String codigo) {
		this.id = id;
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

	@Override
	public String toString() {
		return this.codigo + " - " + this.getNome() + " - " + this.getSigla();
	}
}

package dto;

import enums.TipoUsuario;

public class UsuarioDTO {

	private String nome;
	private int id;
	private String email;
    private String cpf_cnpj;
    private TipoUsuario idTipoUsuario;
	
	public UsuarioDTO(String nome, int id) {
		super();
		this.nome = nome;
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return this.getNome();
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCpf_cnpj() {
		return cpf_cnpj;
	}
	public void setCpf_cnpj(String cpf_cnpj) {
		this.cpf_cnpj = cpf_cnpj;
	}
	public TipoUsuario getIdTipoUsuario() {
		return idTipoUsuario;
	}
	public void setIdTipoUsuario(TipoUsuario idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}
}

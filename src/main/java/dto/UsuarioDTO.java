package dto;

import enums.TipoUsuario;

public class UsuarioDTO {

	private String nome;
	private int id;
	private String email;
    private String cpf_cnpj;
    private TipoUsuario idTipoUsuario;
	
	public UsuarioDTO(String nome, int id, String email, String cpf_cnpj, int idTipoUsuario) {
		super();
		this.nome = nome;
		this.id = id;
		this.email = email;
		this.cpf_cnpj = cpf_cnpj;
		this.idTipoUsuario = getTipoUsuario(idTipoUsuario);
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
	
	public TipoUsuario getTipoUsuario(int id) {
		switch(id) {
        case 1:
            return TipoUsuario.Colaborador;
        case 2:
            return TipoUsuario.Gestor;
        case 3:
            return TipoUsuario.Administrador;
        }
        return null;	
	}
}
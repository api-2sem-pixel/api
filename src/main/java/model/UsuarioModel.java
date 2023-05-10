package model;

import enums.TipoUsuario;

public class UsuarioModel {
    private int id;
    private String nome;
    private String email;
    private String cpf_cnpj;
    private TipoUsuario idTipoUsuario;
    
    public UsuarioModel(int id, String nome, String email, String cpf_cnpj, TipoUsuario idTipoUsuario) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf_cnpj = cpf_cnpj;
        this.idTipoUsuario = idTipoUsuario;
    }

    public UsuarioModel() {
        super();
    }

    public int getId() {
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

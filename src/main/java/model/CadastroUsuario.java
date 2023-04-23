package model;

public class CadastroUsuario {
    private String email;
    private String nome;
    private String cpf;
    private String tel;
    private int tipo_id;

    public CadastroUsuario(String email, String nome, String cpf, String tel, int tipo) {
        this.email = email;
        this.nome = nome;
        this.cpf = cpf;
        this.tel = tel;
        this.tipo_id = tipo;
    }

    public int getId() {
        return tipo_id;
    }

    public void setId(int id) {
     this.tipo_id = id;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public boolean isValid() {
        return isValidEmail(email) && isValidCpf(cpf) && isValidTel(tel);
    }

    private boolean isValidEmail(String email) {
        // TODO: Implement email validation
        return true;
    }

    private boolean isValidCpf(String cpf) {
        // TODO: Implement CPF validation
        return true;
    }

    private boolean isValidTel(String tel) {
        // TODO: Implement telephone validation
        return true;
    }
}

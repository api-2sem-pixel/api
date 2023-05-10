package model.ComboboxModel;

public class UsuarioComboboxModel {
	private int id;
	private String nome;
	 
    public UsuarioComboboxModel(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public UsuarioComboboxModel() {
        super();
    }
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}

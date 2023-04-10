package model.ComboboxModel;

public class ProjetoComboboxModel{
    private Integer id;
    private String nome;

    public ProjetoComboboxModel() {
        super();
    }
    
    public ProjetoComboboxModel(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
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
    
    public void setNome(String descricao) {
        this.nome = descricao;
    }
}

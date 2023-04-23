package model.ComboboxModel;

public class ClienteComboboxModel {
    private Integer id;
	private String razaoSocial;

	public ClienteComboboxModel(Integer id, String razaoSocial) {
		super();
		this.id = id;
		this.razaoSocial = razaoSocial;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
}

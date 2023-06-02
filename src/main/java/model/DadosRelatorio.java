package model;

import java.util.Date;

public class DadosRelatorio {

	private String cpf;
	private String nome;
	private Date dtIni;
	private Date dtFim;
	private int userId;
	
	public DadosRelatorio(String cpf, String nome, Date dtIni, Date dtFim, int userId) {
		this.cpf = cpf;
		this.nome = nome;
		this.dtIni = dtIni;
		this.dtFim = dtFim;
		this.userId = userId;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDtIni() {
		return dtIni;
	}

	public void setDtIni(Date dtIni) {
		this.dtIni = dtIni;
	}

	public Date getDtFim() {
		return dtFim;
	}

	public void setDtFim(Date dtFim) {
		this.dtFim = dtFim;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	
}

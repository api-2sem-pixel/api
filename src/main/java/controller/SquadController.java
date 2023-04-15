package controller;

import java.sql.Connection;
import java.util.List;

import dao.CrDAO;
import factory.ConnectionFactory;
import model.SquadModel;

public class SquadController {

	private CrDAO squadDAO;

	public SquadController() {
		Connection connection = new ConnectionFactory().recuperarConexao();
		this.squadDAO = new CrDAO(connection);
	}
	
	public List<SquadModel> listar() {
		return this.squadDAO.listar();
	}
	
	public static void main(String[] args) {
		SquadController squad = new SquadController();
		List<SquadModel> squads = squad.listar();
		System.out.println(squads.get(0).getNome());
	}
}

package controller;

import java.sql.Connection;
import java.util.List;

import dao.SquadDAO;
import factory.ConnectionFactory;
import model.Squad;

public class SquadController {

	private SquadDAO squadDAO;

	public SquadController() {
		Connection connection = new ConnectionFactory().recuperarConexao();
		this.squadDAO = new SquadDAO(connection);
	}
	
	public List<Squad> listar() {
		return this.squadDAO.listar();
	}
	
	public static void main(String[] args) {
		SquadController squad = new SquadController();
		List<Squad> squads = squad.listar();
		System.out.println(squads.get(0).getNome());
	}
}

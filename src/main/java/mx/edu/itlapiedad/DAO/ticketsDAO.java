package mx.edu.itlapiedad.DAO;

import java.util.List;

import mx.edu.itlapiedad.models.tickets;

public interface ticketsDAO {

	List<tickets> consultarTickets();
	
	tickets buscar(int id);
	
	tickets insertar(tickets tickets);
	
	void actualizar(tickets tickets);

	void eliminar(int id);
	
}

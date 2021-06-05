package mx.edu.itlapiedad.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.itlapiedad.DAO.*;
import mx.edu.itlapiedad.models.tickets;

@Service
public class ticketsLogic implements ticketsService{
	@Autowired
	ticketsDAO repositorio;
	
	@Override
	public List<tickets> consultarTickets(){
		return repositorio.consultarTickets();		
	}
	
	@Override
	public tickets buscar(int id) {
		return repositorio.buscar(id);
	}
	
	@Override
	public tickets insertar(tickets tickets) {
		return repositorio.insertar(tickets);
	}
	
	@Override
	public void actualizar(tickets tickets) {
		 repositorio.actualizar(tickets);
	}

	@Override
	public void eliminar(int id) {
		repositorio.eliminar(id);
	}
}

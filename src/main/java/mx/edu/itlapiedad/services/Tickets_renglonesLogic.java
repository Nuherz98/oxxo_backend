package mx.edu.itlapiedad.services;

import java.util.List;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import mx.edu.itlapiedad.DAO.Tickets_renglonesDAO;
import mx.edu.itlapiedad.models.Tickets_renglones;
import mx.edu.itlapiedad.models.Ticket_renglones_importe;

@Service
public class Tickets_renglonesLogic implements Tickets_renglonesService {
	@Autowired
	Tickets_renglonesDAO repositorio;

	@Override
	public Tickets_renglones buscar(int id) {
		// TODO Auto-generated method stub
		return repositorio.buscar(id);
	}

	@Override
	public Tickets_renglones insertar(Tickets_renglones tickets_renglones) {
		// TODO Auto-generated method stub
		return repositorio.insertar(tickets_renglones);
	}

	@Override
	public List<Tickets_renglones> consultarTickets_renglones() {
		// TODO Auto-generated method stub
		return repositorio.consultarTickets_renglones();
	}

	@Override
	public void actualizar(Tickets_renglones ticket_renglones) {
		// TODO Auto-generated method stub
repositorio.actualizar(ticket_renglones);
	}

	@Override
	public void eliminar(int id) {
		// TODO Auto-generated method stub
repositorio.eliminar(id);
	}

	@Override
	public List<Ticket_renglones_importe> buscar_importe_cajero(int id) {
		// TODO Auto-generated method stub
		return repositorio.buscar_importe_cajero(id);
	}

	@Override
	public List<Ticket_renglones_importe> buscar_importe_cajero_fecha(int id, Timestamp fecha_hora) {
		// TODO Auto-generated method stub
		return repositorio.buscar_importe_cajero_fecha(id,fecha_hora);
	}

	@Override
	public List<Tickets_renglones> consultarTickets_renglones() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tickets_renglones buscar(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tickets_renglones insertar(Tickets_renglones Tickets_renglones) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actualizar(Tickets_renglones Tickets_renglones) {
		// TODO Auto-generated method stub
		
	}

	

	
}
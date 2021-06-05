package mx.edu.itlapiedad.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import mx.edu.itlapiedad.DAO.Tickets_renglonesDAO;
import mx.edu.itlapiedad.models.Tickets_renglones;

@Service
public class Tickets_renglonesLogic implements Tickets_renglonesService {
	
    @Autowired
    Tickets_renglonesDAO repositorio;

	@Override
	public List<Tickets_renglones> consultarTickets_renglones() {
		return repositorio.consultarTickets_renglones();
	}

	@Override
	public Tickets_renglones buscar(int id) {
		return repositorio.buscar(id);
	}
	
	@Override
	public Tickets_renglones insertar(Tickets_renglones Tickets_renglones) {
		return repositorio.insertar(Tickets_renglones);
	}
	
	@Override
	public void actualizar(Tickets_renglones  Tickets_renglones) {
		repositorio.actualizar(Tickets_renglones);
	}

	@Override
	public void eliminar(int id) {
		repositorio.eliminar(id);
	}
}
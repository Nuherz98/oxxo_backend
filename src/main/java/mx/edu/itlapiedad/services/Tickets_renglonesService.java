package mx.edu.itlapiedad.services;

import java.util.List;
import java.sql.Timestamp;

import mx.edu.itlapiedad.models.Tickets_renglones;
import mx.edu.itlapiedad.models.Ticket_renglones_importe;

public interface Tickets_renglonesService {
	List<Tickets_renglones> consultarTickets_renglones(); 
	Tickets_renglones buscar(int id);
	Tickets_renglones insertar(Tickets_renglones Tickets_renglones);
	void actualizar(Tickets_renglones Tickets_renglones);
	void eliminar(int id);
	List<Ticket_renglones_importe> buscar_importe_cajero(int id);

	List<Ticket_renglones_importe> buscar_importe_cajero_fecha(int id, Timestamp fecha_hora);
}

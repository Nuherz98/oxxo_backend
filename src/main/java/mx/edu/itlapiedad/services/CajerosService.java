package mx.edu.itlapiedad.services;


import java.util.List;

import mx.edu.itlapiedad.models.Cajeros;


public interface CajerosService {

	Cajeros buscar(int id);

	Cajeros insertar(Cajeros cajeros);

	List<Cajeros> consultarCajeros();

	void actualizar(Cajeros cajeros);

	void eliminar(int id);
	
}

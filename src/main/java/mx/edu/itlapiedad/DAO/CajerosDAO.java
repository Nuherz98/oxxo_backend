package mx.edu.itlapiedad.DAO;

import java.util.List;

import mx.edu.itlapiedad.models.Cajeros;

public interface CajerosDAO {

	List<Cajeros> consultarClientes();

	Cajeros buscar(int id);

	Cajeros insertar(Cajeros cajeros);

	void actualizar(Cajeros cajeros);

	void eliminar(int id);
	

	

	

}

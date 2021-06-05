package mx.edu.itlapiedad.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mx.edu.itlapiedad.models.Tickets_renglones;
import mx.edu.itlapiedad.models.tickets;
import mx.edu.itlapiedad.services.Tickets_renglonesService;

@RestController
@RequestMapping("/devops/Tickets_renglones")
public class Tickets_renglonesWS {
	
	@Autowired
	Tickets_renglonesService servicio;

	@GetMapping()
	public ResponseEntity<?> consultarTickets_renglones(){
		List<Tickets_renglones> resultado;
		try {
			resultado = servicio.consultarTickets_renglones();
		}catch (DataAccessException e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Tickets_renglones>>(resultado,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable int id){
		Tickets_renglones resultado;
		try {
			resultado=servicio.buscar(id);
		} catch (DataAccessException e) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Tickets_renglones>(resultado,HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<?>insertar(@RequestBody Tickets_renglones Tickets_renglones){
		Tickets_renglones resultado;
	try {
		resultado=servicio.insertar(Tickets_renglones);
	} catch (DataAccessException e) {
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}	
		return new ResponseEntity<Tickets_renglones>(resultado,HttpStatus.CREATED);
	}
	
	
	@PutMapping()
	public ResponseEntity<?> actualizar(@RequestBody Tickets_renglones Tickets_renglones){
	try {
			servicio.actualizar(Tickets_renglones);
		} catch (DataAccessException e) {
		return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
		}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?>eliminar(@PathVariable int id){
		try {
			servicio.eliminar(id);
		}catch(DataAccessException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<tickets>(HttpStatus.OK);	
	}
}

package mx.edu.itlapiedad.controladores;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
import mx.edu.itlapiedad.models.Productos;
import mx.edu.itlapiedad.services.ProductosService;
import org.springframework.web.bind.annotation.*;

	@RestController
	@RequestMapping("/devops/productos")
	
	public class ProductosWS {

		@Autowired
		ProductosService servicio;

		@GetMapping()
		public ResponseEntity<?> consultarProductos(){
			List<Productos> resultado;
			try {
				resultado = servicio.consultarProductos();
			}catch (DataAccessException e) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Productos>>(resultado,HttpStatus.OK);
		}
		@GetMapping("/{id}")
		public ResponseEntity<?> buscar(@PathVariable int id){
			Productos resultado;
			try {
				resultado=servicio.buscar(id);
			} catch (DataAccessException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<Productos>(resultado,HttpStatus.OK);
		}
		
		@PostMapping("")
		public ResponseEntity<?>insertar(@RequestBody Productos productos){
			Productos resultado;
		try {
			resultado=servicio.insertar(productos);
			
		} catch (DataAccessException e) {
			System.out.println(e);
		return new ResponseEntity<>(HttpStatus.CONFLICT);
		}	
			return new ResponseEntity<Productos>(resultado,HttpStatus.CREATED);
		
		}
		
		
		//Metodo de actualizar 
				@PutMapping()
				public ResponseEntity<?> actualizar(@RequestBody Productos productos){
				try {
						servicio.actualizar(productos);
					} catch (DataAccessException e) {
						System.out.println(e);
					return new ResponseEntity<>(HttpStatus.CONFLICT);
					}
				
					return new ResponseEntity<>(HttpStatus.CREATED);
					}

				
				@DeleteMapping("/{id}")
				public ResponseEntity<?>eliminarProducto(@PathVariable int id){
					
			
					try {
						servicio.eliminar(id);
					}catch(DataAccessException e) {
						
						return new ResponseEntity<>(HttpStatus.NO_CONTENT);
						
					}
					
					return new ResponseEntity<Productos>(HttpStatus.OK);
					
				}
}

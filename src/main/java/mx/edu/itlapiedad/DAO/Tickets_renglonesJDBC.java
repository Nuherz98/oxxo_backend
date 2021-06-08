package mx.edu.itlapiedad.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import mx.edu.itlapiedad.models.Tickets_renglones;
import mx.edu.itlapiedad.models.Ticket_renglones_importe;

@Repository
public class Tickets_renglonesJDBC implements Tickets_renglonesDAO {
	@Autowired
	JdbcTemplate conexion;

	@Override
	public Tickets_renglones buscar(int id) {
		String sql_query = "SELECT * FROM ticket_renglones WHERE id=?";
		return conexion.queryForObject(sql_query, new RowMapper<Tickets_renglones>() {
			public Tickets_renglones mapRow(ResultSet rs, int rowNum) throws SQLException {
				Tickets_renglones ticket_renglones = new Tickets_renglones();
				ticket_renglones.setId(rs.getInt("id"));
				ticket_renglones.setTicket_id(rs.getInt("TICKET_id"));
				ticket_renglones.setProducto_id(rs.getInt("PRODUCTO_id"));
				ticket_renglones.setCantidad(rs.getInt("cantidad"));
				ticket_renglones.setPrecio(rs.getFloat("precio"));
				ticket_renglones.setImporte(rs.getFloat("importe"));
				return ticket_renglones;
			}

		}, id);
	}

	@Override
	public Tickets_renglones insertar(Tickets_renglones ticket_renglones) {

		SimpleJdbcInsert insert = new SimpleJdbcInsert(conexion).withTableName("ticket_renglones")
				.usingColumns("cantidad", "precio", "TICKET_id","PRODUCTO_id").usingGeneratedKeyColumns("id");
		Map<String, Object> datos = new HashMap<>();
		datos.put("cantidad", ticket_renglones.getCantidad());
		datos.put("precio", ticket_renglones.getPrecio());
	//	datos.put("importe", ticket_renglones.getImporte());
		datos.put("TICKET_id", ticket_renglones.getTicket_id());
		datos.put("PRODUCTO_id", ticket_renglones.getProducto_id());
		
		Number id = insert.executeAndReturnKey(datos);
		ticket_renglones.setId(id.intValue());
		return ticket_renglones;
	}

	@Override
	public List<Tickets_renglones> consultarTickets_renglones() {
		// TODO Auto-generated method stub
		String sql_query = "SELECT * FROM ticket_renglones";
		return conexion.query(sql_query, new RowMapper<Tickets_renglones>() {
			public Tickets_renglones mapRow(ResultSet rs, int rowNum) throws SQLException {
				Tickets_renglones ticket_renglones = new Tickets_renglones();
				ticket_renglones.setId(rs.getInt("id"));
				ticket_renglones.setTicket_id(rs.getInt("TICKET_id"));
				ticket_renglones.setProducto_id(rs.getInt("PRODUCTO_id"));
				ticket_renglones.setCantidad(rs.getInt("cantidad"));
				ticket_renglones.setPrecio(rs.getFloat("precio"));
				ticket_renglones.setImporte(rs.getFloat("importe"));
				return ticket_renglones;

			}

		});
	}

	@Override
	public void actualizar(Tickets_renglones ticket_renglones) {
		String sql_update = "UPDATE ticket_renglones SET cantidad = ?, precio = ?, TICKET_id=?, PRODUCTO_id=? " + " WHERE id = ?";
		conexion.update(sql_update,
				ticket_renglones.getCantidad(), 
				ticket_renglones.getPrecio(),
			//	ticket_renglones.getImporte(), 
				ticket_renglones.getTicket_id(), 
				ticket_renglones.getProducto_id(), 
				ticket_renglones.getId());
		
		

	}

	@Override
	public void eliminar(int id) {
		String sql_update="Delete ticket_renglones WHERE id=?";
		conexion.update(sql_update,id);
	}

	@Override
	public List<Ticket_renglones_importe> buscar_importe_cajero(int id) {
		
		String sql_query = "SELECT  sum(importe) as importe FROM ticket_renglones  JOIN tickets ON ticket_renglones.TICKET_id = tickets.id JOIN cajeros  ON cajeros.id=tickets.CAJERO_id  WHERE  cajeros.id=?;";
		return conexion.query(sql_query, new RowMapper<Ticket_renglones_importe>() {
			public Ticket_renglones_importe mapRow(ResultSet rs, int rowNum) throws SQLException {
				Ticket_renglones_importe ticket_renglones = new Ticket_renglones_importe();
				ticket_renglones.setImporte(rs.getFloat("importe"));

				return ticket_renglones;

			
			}

		}, id);
	}

	@Override
	public List<Ticket_renglones_importe> buscar_importe_cajero_fecha(int id,Timestamp fecha_hora) {
		
		String sql_query = "SELECT  sum(importe) as importe FROM ticket_renglones  JOIN tickets ON ticket_renglones.TICKET_id = tickets.id JOIN cajeros  ON cajeros.id=tickets.CAJERO_id  WHERE  cajeros.id=? and fecha_hora=?;";
		return conexion.query(sql_query, new RowMapper<Ticket_renglones_importe>() {
			public Ticket_renglones_importe mapRow(ResultSet rs, int rowNum) throws SQLException {
				Ticket_renglones_importe ticket_renglones = new Ticket_renglones_importe();
				ticket_renglones.setImporte(rs.getFloat("importe"));

				return ticket_renglones;

			
			}

		}, id,fecha_hora);
	}
}

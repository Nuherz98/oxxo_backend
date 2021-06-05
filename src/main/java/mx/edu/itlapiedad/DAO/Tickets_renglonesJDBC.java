package mx.edu.itlapiedad.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import mx.edu.itlapiedad.models.Tickets_renglones;

@Repository
public class Tickets_renglonesJDBC implements Tickets_renglonesDAO{
	
	@Autowired
	JdbcTemplate conexion;
	
	@Override
	public List<Tickets_renglones> consultarTickets_renglones() {
		String sql_query = "SELECT * FROM ticket_renglones";
		return conexion.query(sql_query, new RowMapper<Tickets_renglones>() {
			public Tickets_renglones mapRow(ResultSet rs,int rowNum) throws SQLException {
				Tickets_renglones TR = new Tickets_renglones();
				TR.setId(rs.getInt("id"));
				TR.setTicket_id(rs.getInt("TICKET_id"));
				TR.setProducto_id(rs.getInt("PRODUCTO_id"));
				TR.setCantidad(rs.getInt("cantidad"));
				TR.setPrecio(rs.getFloat("precio"));
				TR.setImporte(rs.getFloat("importe"));
				return TR;
				
			}

			
		});
	}
	
	@Override
	public Tickets_renglones buscar(int id) {
		String sql_query = "SELECT * FROM ticket_renglones WHERE id=?";
		return conexion.queryForObject(sql_query, new RowMapper<Tickets_renglones>() {
			public Tickets_renglones mapRow(ResultSet rs, int rowNum) throws SQLException {
				Tickets_renglones TR = new Tickets_renglones();
				TR.setId(rs.getInt("id"));
				TR.setTicket_id(rs.getInt("TICKET_id"));
				TR.setProducto_id(rs.getInt("PRODUCTO_id"));
				TR.setCantidad(rs.getInt("cantidad"));
				TR.setPrecio(rs.getFloat("precio"));
				TR.setImporte(rs.getFloat("importe"));
				return TR;
			}
			
		},id);
	}
	
	@Override
	public Tickets_renglones insertar(Tickets_renglones Tickets_renglones) {
		SimpleJdbcInsert insert=new SimpleJdbcInsert(conexion).withTableName("ticket_renglones")
				.usingColumns("TICKET_id","PRODUCTO_id","cantidad","precio")
				.usingGeneratedKeyColumns("id");
		Map<String,Object> datos = new HashMap<>();
	datos.put("TICKET_id", Tickets_renglones.getTicket_id());
	datos.put("PRODUCTO_id", Tickets_renglones.getProducto_id());
	datos.put("cantidad", Tickets_renglones.getCantidad());
	datos.put("precio", Tickets_renglones.getPrecio());
	
		Number id=insert.executeAndReturnKey(datos);
		Tickets_renglones.setId(id.intValue());
		return Tickets_renglones;
	}
	
	
	@Override
	public void actualizar(Tickets_renglones Tickets_renglones) {
		String sql_update = "UPDATE ticket_renglones SET TICKET_id=?, PRODUCTO_id=?,"
				+ "cantidad=?, precio=? WHERE id=?";
		conexion.update(sql_update,
				Tickets_renglones.getTicket_id(),
				Tickets_renglones.getProducto_id(),
				Tickets_renglones.getCantidad(),
				Tickets_renglones.getPrecio(),
				Tickets_renglones.getId());
	}
	
	@Override
	public void eliminar(int id) {
		String sql_update="delete from ticket_renglones WHERE id=?";
		conexion.update(sql_update,id);
	}
}

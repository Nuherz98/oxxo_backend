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

import mx.edu.itlapiedad.models.tickets;

@Repository
public class ticketsJDBC implements ticketsDAO{
	@Autowired
	JdbcTemplate conexion;
	
	@Override
	public List<tickets> consultarTickets() {
		String sql_query = "SELECT * FROM tickets";
		return conexion.query(sql_query, new RowMapper<tickets>() {
			public tickets mapRow(ResultSet rs,int rowNum) throws SQLException {
				tickets tickets = new tickets();
				tickets.setId(rs.getInt("id"));
				tickets.setFecha_hora(rs.getString("Fecha_hora"));
				tickets.setTotal(rs.getFloat("total"));
				tickets.setCAJERO_id(rs.getInt("CAJERO_id"));
				return tickets;
			}
		});
	}
	
	@Override
	public tickets buscar(int id) {
		String sql_query = "SELECT * FROM tickets where id=?";
		return conexion.queryForObject(sql_query, new RowMapper<tickets>() {
			public tickets mapRow(ResultSet rs, int rowNum) throws SQLException {
				tickets tickets = new tickets();
				tickets.setId(rs.getInt("id"));
				tickets.setFecha_hora(rs.getString("Fecha_hora"));
				tickets.setTotal(rs.getFloat("total"));
				tickets.setCAJERO_id(rs.getInt("CAJERO_id"));
				return tickets;
			}
			
		},id);
	}
	
	@Override
	public tickets insertar(tickets tickets) {
		SimpleJdbcInsert insert=new SimpleJdbcInsert(conexion).withTableName("tickets")
				.usingColumns("Fecha_hora","total","CAJERO_id")
				.usingGeneratedKeyColumns("id");
		Map<String,Object> datos = new HashMap<>();
			datos.put("Fecha_hora", tickets.getFecha_hora());
	        datos.put("total", tickets.getTotal());
	        datos.put("CAJERO_id", tickets.getCAJERO_id());
		
		Number id=insert.executeAndReturnKey(datos);
		tickets.setId(id.intValue());
		return tickets;
	}
	
	@Override
	public void actualizar(tickets tickets) {
		String sql_update = "UPDATE tickets SET fecha_hora = ?, total = ?, "
				+ "CAJERO_id = ? WHERE id = ?";
		conexion.update(sql_update, 
				tickets.getFecha_hora(),
				tickets.getTotal(),
				tickets.getCAJERO_id(),
				tickets.getId());
			}
	
	
	@Override
	public void eliminar(int id) {
		String sql_update="delete from tickets WHERE id=?";
		conexion.update(sql_update,id);
	}


}

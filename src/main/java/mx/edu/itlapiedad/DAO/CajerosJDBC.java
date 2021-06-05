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

import mx.edu.itlapiedad.models.Cajeros;

@Repository
public class CajerosJDBC implements CajerosDAO{
	@Autowired
	JdbcTemplate conexion;
	
	
	@Override
    public List<Cajeros> consultarClientes() {
		String sql_query = "SELECT * FROM cajeros";
		return conexion.query(sql_query, new RowMapper<Cajeros>() {
			public Cajeros mapRow(ResultSet rs,int rowNum) throws SQLException {
				Cajeros cajeros = new Cajeros();
				cajeros.setId(rs.getInt("id"));
				cajeros.setNombre(rs.getString("nombre"));
				return cajeros;
				
			}

			
		});
	}
	

	@Override
	public Cajeros buscar(int id) {
		String sql_query = "SELECT * FROM cajeros WHERE id=?";
		return conexion.queryForObject(sql_query, new RowMapper<Cajeros>() {
			public Cajeros mapRow(ResultSet rs, int rowNum) throws SQLException {
				Cajeros cajeros = new Cajeros();
				cajeros.setId(rs.getInt("id"));
				cajeros.setNombre(rs.getString("nombre"));
				return cajeros;
			}
			
		},id);
	}
	

	@Override
	public Cajeros insertar(Cajeros cajeros) {

		SimpleJdbcInsert insert = new SimpleJdbcInsert(conexion).withTableName("cajeros")
				.usingColumns("nombre").usingGeneratedKeyColumns("id");
		Map<String, Object> datos = new HashMap<>();
		datos.put("nombre", cajeros.getNombre());
		
		Number id = insert.executeAndReturnKey(datos);
		cajeros.setId(id.intValue());
		return cajeros;
	}
	
	
	@Override
	public void actualizar(Cajeros cajeros) {
		String sql_update = "UPDATE cajeros SET nombre = ? WHERE id = ?";
		conexion.update(sql_update, 
		        cajeros.getNombre(),
		        cajeros.getId());
		        
			}
	
	
	@Override
	public void eliminar(int id) {
		String sql_update="delete from cajeros WHERE id=?";
		conexion.update(sql_update,id);
		
	}



}

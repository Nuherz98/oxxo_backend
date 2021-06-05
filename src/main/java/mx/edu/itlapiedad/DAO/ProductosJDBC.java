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

import mx.edu.itlapiedad.models.Productos;


	@Repository

	public class ProductosJDBC implements ProductosDAO {
		@Autowired
		JdbcTemplate conexion;
		
		
		@Override
		public List<Productos> consultarProductos() {
			String sql_query = "SELECT * FROM productos";
			return conexion.query(sql_query, new RowMapper<Productos>() {
				public Productos mapRow(ResultSet rs,int rowNum) throws SQLException {
					Productos productos = new Productos();
					productos.setId(rs.getInt("id"));
					productos.setDescripcion(rs.getString("descripcion"));
					productos.setPrecio(rs.getFloat("precio"));
					productos.setCodigo_barras(rs.getString("codigo_barras"));
					productos.setExistencia(rs.getInt("existencia"));
					return productos;
					
				}
			});
		}
		
		@Override
		public Productos buscar(int id) {
			String sql_query = "SELECT * FROM productos WHERE id=?";
			return conexion.queryForObject(sql_query, new RowMapper<Productos>() {
				public Productos mapRow(ResultSet rs, int rowNum) throws SQLException {
					Productos productos = new Productos();
					productos.setId(rs.getInt("id"));
					productos.setDescripcion(rs.getString("descripcion"));
					productos.setPrecio(rs.getFloat("precio"));
					productos.setCodigo_barras(rs.getString("codigo_barras"));
					productos.setExistencia(rs.getInt("existencia"));
					return productos;
				}
				
			},id);
		}
		
		@Override
		public Productos insertar(Productos productos) {
			
			SimpleJdbcInsert insert=new SimpleJdbcInsert(conexion).withTableName("productos")
					.usingColumns("descripcion","precio","codigo_barras","existencia")
					.usingGeneratedKeyColumns("id");
			Map<String,Object> datos = new HashMap<>();
		datos.put("descripcion", productos.getDescripcion());
		datos.put("precio", productos.getPrecio());
		datos.put("codigo_barras", productos.getCodigo_barras());
		datos.put("existencia", productos.getExistencia());
			
			Number id=insert.executeAndReturnKey(datos);
			productos.setId(id.intValue());
			return productos;
		}
		
		@Override
		public void actualizar(Productos productos) {
			String sql_update = "UPDATE productos SET descripcion=?,"
					+ "existencia=?,precio=?,codigo_barras=?"
					+ "WHERE id=?";
			conexion.update(sql_update,productos.getDescripcion(),
					productos.getExistencia(),
					productos.getPrecio(),
					productos.getCodigo_barras(),
					productos.getId());
			
		}
		
		@Override
		public void eliminar(int id) {
			String sql_update="delete from productos WHERE id=?";
			conexion.update(sql_update,id);
			
		}
		
		


}

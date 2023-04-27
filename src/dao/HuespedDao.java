package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import models.Huesped;

public class HuespedDao {

	private Connection con;

	public HuespedDao(Connection con) {
		this.con = con;
	}

	public void guardar(Huesped huesped) {

		PreparedStatement statement;

		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		String fechaNaci = formato.format(huesped.getFechaNacimiento());

		try {
			statement = con.prepareStatement(
					"INSERT INTO huespedes " + "(id_reserva,nombre, apellido,fecha_de_nacimiento,nacionalidad,telefono)"
							+ " VALUES (?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			try (statement) {

				statement.setInt(1, huesped.getReservaId());
				statement.setString(2, huesped.getNombre());
				statement.setString(3, huesped.getApellido());
				statement.setDate(4, Date.valueOf(fechaNaci));
				statement.setString(5, huesped.getNacionalidad());
				statement.setString(6, huesped.getNumeroTelefono());

				statement.execute();

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Huesped> listar() {
		List<Huesped> listado = new ArrayList<>();

		try {

			final PreparedStatement statement = con.prepareStatement("SELECT ID, ID_RESERVA, NOMBRE , APELLIDO, "
					+ "FECHA_DE_NACIMIENTO, NACIONALIDAD, TELEFONO FROM huespedes");
			try (statement) {
				statement.execute();

				final ResultSet resultSet = statement.getResultSet();

				try (resultSet) {
					while (resultSet.next()) {

						listado.add(new Huesped(resultSet.getInt("ID"), resultSet.getInt("ID_RESERVA"),
								resultSet.getString("NOMBRE"), resultSet.getString("APELLIDO"),
								resultSet.getDate("FECHA_DE_NACIMIENTO"), resultSet.getString("NACIONALIDAD"),
								resultSet.getString("TELEFONO")));
					}

				}

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return listado;

	}

	public int eliminar(int id) {

		try {

			PreparedStatement statement = con.prepareStatement("DELETE FROM huespedes WHERE ID =?");

			try (statement) {
				statement.setInt(1, id);
				statement.execute();
				int updateCount = statement.getUpdateCount();

				return updateCount;
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int modificar(int idHuesped, int idReserva, String nombre, String apellido, Date fechaNac,
			String nacionalidad, String telefono) {

		 SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
	        String nacimiento = date.format(fechaNac);

		
		try {
			final PreparedStatement statement = con
					.prepareStatement("UPDATE huespedes SET "
		                    + " id_reserva = ?, "
		                    + " nombre = ?,"
		                    + " apellido = ?,"
		                    + " fecha_de_nacimiento = ?,"
		                    + " nacionalidad = ?,"
		                    + " telefono = ?"
		                    + " WHERE id = ?"
		            );

			try (statement) {
				statement.setInt(1, idReserva);
				statement.setString(2, nombre);
				statement.setString(3, apellido);
				statement.setDate(4, Date.valueOf(nacimiento));
				statement.setString(5, nacionalidad);
				statement.setString(6, telefono);
				statement.setInt(7,idHuesped);
				statement.executeUpdate();
				int updateCount = statement.getUpdateCount();

				return updateCount;
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Huesped> listarBusquedaHuesped(String cadena) {
		
		List<Huesped> resultado = new ArrayList<>();
		
		try {
			final PreparedStatement statement= con.prepareStatement("SELECT * FROM huespedes WHERE apellido LIKE ?");
		try (statement) {
			 statement.setString(1, cadena + "%");
             statement.execute();
             
             final ResultSet resultSet = statement.getResultSet();
             
        try (resultSet) {
        	 while (resultSet.next()) {
                 resultado.add(new Huesped(
                         resultSet.getInt("id"),
                         resultSet.getInt("id_reserva"),
                         resultSet.getString("nombre"),
                         resultSet.getString("apellido"),
                         resultSet.getDate("fecha_de_nacimiento"),
                         resultSet.getString("nacionalidad"),
                         resultSet.getString("telefono")
                 ));
             }
         }
     }
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return resultado;
	}

}

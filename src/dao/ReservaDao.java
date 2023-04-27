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
import models.Reserva;

public class ReservaDao {

	private Connection con;
	private int idReserva;

	public ReservaDao(Connection con) {
		this.con = con;
	}

	public int guardar(Reserva reserva) {

		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		String fechaEntrada = formato.format(reserva.getFechaEntrada());
		String fechaSalida = formato.format(reserva.getFechaSalida());

		try {
			final PreparedStatement statement = con.prepareStatement("INSERT INTO reservas "
					+ "(fecha_entrada, fecha_salida,valor,forma_de_pago)" + " VALUES (?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			try (statement) {
				statement.setDate(1, Date.valueOf(fechaEntrada));
				statement.setDate(2, Date.valueOf(fechaSalida));
				statement.setDouble(3, reserva.getValor());
				statement.setString(4, reserva.getFormaDePago());

				statement.execute();

				final ResultSet resultSet = statement.getGeneratedKeys();
				try (resultSet) {
					while (resultSet.next()) {
						idReserva = reserva.setId(resultSet.getInt(1));

						System.out.println(String.format("Fue insertada la reserva: %s", reserva.getId()));
					}
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return idReserva;
	}

	public List<Reserva> listarReservas() {

		List<Reserva> listado = new ArrayList<>();

		try {
			final PreparedStatement statement = con
					.prepareStatement("SELECT ID,FECHA_ENTRADA,FECHA_SALIDA,VALOR,FORMA_DE_PAGO FROM reservas");
			try (statement) {
				statement.execute();

				final ResultSet resultSet = statement.getResultSet();

				try (resultSet) {
					while (resultSet.next()) {
						listado.add(new Reserva(resultSet.getInt("ID"), resultSet.getDate("FECHA_ENTRADA"),
								resultSet.getDate("FECHA_SALIDA"), resultSet.getDouble("VALOR"),
								resultSet.getString("FORMA_DE_PAGO")));
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
			final PreparedStatement statement = con.prepareStatement("DELETE FROM reservas WHERE ID=?");
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

	public int modificar(int idReserva, Date fechaEnt, Date fechaSal, double valor, String formaPago) {

		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		String fechaEntrada = formato.format(fechaEnt);
		String fechaSalida = formato.format(fechaSal);

		try {
			final PreparedStatement statement = con.prepareStatement("UPDATE reservas SET " + "FECHA_ENTRADA = ?,"
					+ "FECHA_SALIDA = ?," + "VALOR = ?," + "FORMA_DE_PAGO = ? " + "WHERE ID = ?");
			try (statement) {

				statement.setDate(1, Date.valueOf(fechaEntrada));
				statement.setDate(2, Date.valueOf(fechaSalida));
				statement.setDouble(3, valor);
				statement.setString(4, formaPago);
				statement.setInt(5, idReserva);

				statement.executeUpdate();

				int updateCount = statement.getUpdateCount();

				return updateCount;
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Reserva> buscarPorId(int idBusqueda) {

		List<Reserva> resultado = new ArrayList<>();

		try {

			final PreparedStatement statement = con.prepareStatement("SELECT * FROM reservas WHERE id =?");
			try (statement) {
				statement.setInt(1, idBusqueda);
				statement.execute();

				final ResultSet resultSet = statement.getResultSet();

				try (resultSet) {

					while (resultSet.next()) {
						resultado.add(new Reserva(resultSet.getInt("ID"), resultSet.getDate("FECHA_ENTRADA"),
								resultSet.getDate("FECHA_SALIDA"), resultSet.getDouble("VALOR"),
								resultSet.getString("FORMA_DE_PAGO")));
					}
				}

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return resultado;
	}
}

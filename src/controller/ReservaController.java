package controller;





import java.sql.Date;
import java.util.List;

import dao.ReservaDao;
import factory.ConnectionFactory;
import models.Reserva;

public class ReservaController {

	private final ReservaDao reservaDao;
	
	 public ReservaController() {
	        var factory = new ConnectionFactory();
	        this.reservaDao = new ReservaDao(factory.recuperaConexion());
	    }
	
	 public int guardar(Reserva reserva) {
		return reservaDao.guardar(reserva);
	 }
	 
	 public List<Reserva> listarReservas() {
		 return reservaDao.listarReservas();
	 }
	 
	 public int eliminarReserva(int id) {
		return reservaDao.eliminar(id);
	 }
	 
	 public int modificarReserva(int idReserva, Date fechaEnt, Date fechaSal, double valor, String formaPago) {
		 return this.reservaDao.modificar(idReserva,fechaEnt,fechaSal,valor,formaPago);
	 }
	 
	 public List<Reserva> listarPorBusquedaId(int idBusqueda) {
		 return reservaDao.buscarPorId(idBusqueda);
	 }
		}

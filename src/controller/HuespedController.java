package controller;

import java.sql.Date;
import java.util.List;

import dao.HuespedDao;
import factory.ConnectionFactory;
import models.Huesped;

public class HuespedController {
	
	HuespedDao huespedDao;
	
	 public HuespedController() {
	        var factory = new ConnectionFactory();
	        this.huespedDao = new HuespedDao(factory.recuperaConexion());
	    }
	
	 public void guardar(Huesped huesped) {
		 huespedDao.guardar(huesped);
	 }
	 
	 public List<Huesped> listarHuespedes() {
		 return huespedDao.listar();
	 }
	
	 public int eliminarHuesped(int id) {
		 return huespedDao.eliminar(id);
	 }
	 
	 public int modificarHuesped(int idHuesped, int idReserva, String nombre, String apellido, Date fechaNac,
			String nacionalidad, String telefono) {
		 return huespedDao.modificar(idHuesped, idReserva, nombre, apellido, fechaNac, nacionalidad, telefono);
	 }
	 
	 public List<Huesped> buscarHuesped(String cadena) {
		 return huespedDao.listarBusquedaHuesped(cadena);
	 }
}

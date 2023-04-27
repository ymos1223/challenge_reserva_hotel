package models;

import java.util.Date;

public class Huesped {
	
	private int id;
	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
	private String nacionalidad;
	private String numeroTelefono;
	private int reservaId;
	
	public Huesped() {
	
	}
	
	
	public Huesped(int id, int reservaId, String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String numeroTelefono) {
		this.id=id;
		this.reservaId=reservaId;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.numeroTelefono = numeroTelefono;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public String getNumeroTelefono() {
		return numeroTelefono;
	}
	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}
	public int getReservaId() {
		return reservaId;
	}
	public void setReservaId(int reservaId) {
		this.reservaId = reservaId;
	}
	
	public int getId() {
		return id;
	}
	
}

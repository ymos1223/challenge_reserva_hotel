package models;

import java.util.Date;



public class Reserva {

	
	private int id;
	private Date fechaEntrada;
	private Date fechaSalida;
	private Double valor;
	private String formaDePago;
	
	
	public Reserva() {
	}
	
	public Reserva(int id, Date fechaEntrada, Date fechaSalida,Double valor, String formaDePago) {
		this.id=id;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor=valor;
		this.formaDePago = formaDePago;
	}
	
	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	
	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getFormaDePago() {
		return formaDePago;
	}
	public void setFormaDePago(String formaDePago) {
		this.formaDePago = formaDePago;
	}
	
	public int getId() {
		return id;
	}

	public int setId(int id) {
		return this.id = id;
	}

	@Override
	public String toString() {
		return "Reserva [id=" + id + ", fechaEntrada=" + fechaEntrada + ", fechaSalida=" + fechaSalida + ", valor="
				+ valor + ", formaDePago=" + formaDePago + "]";
	}
}

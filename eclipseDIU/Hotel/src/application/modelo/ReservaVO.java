package application.modelo;

import java.util.Calendar;
import java.util.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ReservaVO implements Comparable<ReservaVO> {

	private int codigo;
	private StringProperty dni, tipohabitacion, regimen, fumador, estado;
	private Date fechaentrada, fechasalida;
	private IntegerProperty habitaciones;
	private StringProperty cod, fecentrada;
	
	//public ReservaVO() {
	//	this(0,null,null,null,0,null,null,null,null);
	//}

	public ReservaVO(int codigo, String dni, Date entrada, Date salida, int numhab, String tipo, String regimen,
			String fumador, String estado) {
		this.codigo = codigo;
		this.dni = new SimpleStringProperty(dni);
		this.habitaciones = new SimpleIntegerProperty(numhab);
		this.tipohabitacion = new SimpleStringProperty(tipo);
		this.regimen = new SimpleStringProperty(regimen);
		this.fumador = new SimpleStringProperty(fumador);
		this.estado = new SimpleStringProperty(estado);
		this.fechaentrada = entrada;
		this.fechasalida = salida;

		cod = new SimpleStringProperty("" + codigo);

		fecentrada = new SimpleStringProperty(obtenerdate(entrada));
	}

	public String obtenerdate(Date entrada) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(entrada);
		int year = cal.get(Calendar.YEAR);
		int month = 1 + cal.get(Calendar.MONTH);
		String mes, dia;
		if (month < 10) {
			mes = "0" + month;
		} else {
			mes = "" + month;
		}

		int day = cal.get(Calendar.DAY_OF_MONTH);
		if (day < 10) {
			dia = "0" + day;
		} else {
			dia = "" + day;
		}
		String fecha = year + "-" + mes + "-" + dia;
		return fecha;
	}

	public StringProperty getCod() {
		return cod;
	}

	public StringProperty getFecha() {
		return fecentrada;
	}

	public void setNombre(int codigo) {
		this.cod = new SimpleStringProperty("" + codigo);
	}

	public void setFecha(Date entrada) {
		this.fecentrada = new SimpleStringProperty(entrada + "");
	}

	public String getDni() {
		return dni.get();
	}

	public String getTipoHabitacion() {
		return tipohabitacion.get();
	}

	public int getCodigo() {
		return codigo;
	}

	public int getHabitaciones() {
		return habitaciones.get();
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public StringProperty getDniSP() {
		return dni;
	}

	public void setDniSP(StringProperty dni) {
		this.dni = dni;
	}

	public void SetDni(String dni) {
		this.dni.set(dni);
	}

	public StringProperty getTipohabitacion() {
		return tipohabitacion;
	}

	public void setTipohabitacion(StringProperty tipohabitacion) {
		this.tipohabitacion = tipohabitacion;
	}

	public void setTipo(String tipohabitacion) {
		this.tipohabitacion.set(tipohabitacion);
	}

	public StringProperty getRegimen() {
		return regimen;
	}

	public void setRegimenSP(StringProperty regimen) {
		this.regimen = regimen;
	}

	public void SetRegimen(String regimen) {
		this.regimen.set(regimen);
	}

	public StringProperty getFumador() {
		return fumador;
	}

	public void setFumador(StringProperty fumador) {
		this.fumador = fumador;
	}

	public Date getFechaentrada() {
		return fechaentrada;
	}

	public void setFechaentrada(Date fechaentrada) {
		this.fechaentrada = fechaentrada;
	}

	public Date getFechasalida() {
		return fechasalida;
	}

	public void setFechasalida(Date fechasalida) {
		this.fechasalida = fechasalida;
	}

	public IntegerProperty getHabitacionesIP() {
		return habitaciones;
	}

	public void setHabitaciones(IntegerProperty habitaciones) {
		this.habitaciones = habitaciones;
	}

	public StringProperty getEstado() {
		return estado;
	}

	public void setEstadoSP(StringProperty estado) {

		this.estado = estado;
	}

	public void setEstado(String estado) {
		this.estado.set(estado);
	}

	@Override
	public String toString() {
		String f1, f2;
		Calendar cal = Calendar.getInstance();
		cal.setTime(getFechaentrada());
		int year = cal.get(Calendar.YEAR);
		int month = 1 + cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		f1 = year + "-" + month + "-" + day;
		cal.setTime(getFechasalida());
		year = cal.get(Calendar.YEAR);
		month = 1 + cal.get(Calendar.MONTH);
		day = cal.get(Calendar.DAY_OF_MONTH);
		f2 = year + "-" + month + "-" + day;

		return "ReservaVO [codigo=" + codigo + ", dni=" + dni + ", tipohabitacion=" + tipohabitacion + ", regimen="
				+ regimen + ", fumador=" + fumador + ", fechaentrada=" + f1 + ", fechasalida=" + f2 + ", habitaciones="
				+ habitaciones + "]\n";
	}

	public int compareTo(ReservaVO r) {
		int compare;

		if (getCodigo() > r.getCodigo()) {
			compare = 1;
		} else {
			compare = -1;
		}
		return compare;
	}
}
package application.modelo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ClienteVO implements Comparable<ClienteVO> {

	private StringProperty dni, nombre, apellido, direccion, localidad, provincia;

	public ClienteVO(String dni, String nombre, String apellido, String direccion, String localidad, String provincia) {
		this.dni = new SimpleStringProperty(dni);
		this.nombre = new SimpleStringProperty(nombre);
		this.apellido = new SimpleStringProperty(apellido);
		this.direccion = new SimpleStringProperty(direccion);
		this.localidad = new SimpleStringProperty(localidad);
		this.provincia = new SimpleStringProperty(provincia);
	}

	public String getDni() {
		return dni.get();
	}

	public void setDni(StringProperty dni) {
		this.dni = dni;
	}

	public void SetDni(String dni) {
		this.dni.set(dni);
	}

	public String getNombre() {
		return nombre.get();
	}

	public void setNombre(StringProperty nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido.get();
	}

	public void setApellido(StringProperty apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return direccion.get();
	}

	public void setDireccion(StringProperty direccion) {
		this.direccion = direccion;
	}

	public StringProperty getNombreSP() {
		return nombre;
	}

	public StringProperty getApellidoSP() {
		return apellido;
	}

	@Override
	public String toString() {
		return "ClienteVO [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", direccion=" + direccion
				+ ", localidad=" + localidad + ", provincia=" + provincia + "]";
	}

	public String getLocalidad() {
		return localidad.get();
	}

	public void setLocalidad(StringProperty localidad) {
		this.localidad = localidad;
	}

	public String getProvincia() {
		return provincia.get();
	}

	public void setProvincia(StringProperty provincia) {
		this.provincia = provincia;
	}

	public int compareTo(ClienteVO c) {
		int resultado;
		resultado = getNombre().compareToIgnoreCase(c.getNombre());
		if (resultado != 0) {
			return resultado;
		}

		resultado = getApellido().compareToIgnoreCase(c.getApellido());
		if (resultado != 0) {
			return resultado;
		}
		resultado = getDni().compareToIgnoreCase(c.getDni());
		return resultado;
	}
}
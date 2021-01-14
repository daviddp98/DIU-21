package application.modelo;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Producto {
	private Integer id;
	private final StringProperty nombre;
	private final StringProperty descripcion;
	private final IntegerProperty cantidad;
	private final FloatProperty precio;

	/**
	 * Default constructor.
	 */
	public Producto() {
		this(null, null, null, 0, new Float(0f));
	}

	public Producto(Integer id, String n, String d, Integer c, Float mult) {
		this.id = id;
		this.nombre = new SimpleStringProperty(n);
		this.descripcion = new SimpleStringProperty(d);
		this.cantidad = new SimpleIntegerProperty(c);
		this.precio = new SimpleFloatProperty(mult);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public StringProperty nombreProperty() {
		return nombre;
	}

	public StringProperty descripcionProperty() {
		return descripcion;
	}

	public FloatProperty precioProperty() {
		return precio;
	}

	public IntegerProperty cantidadProperty() {
		return cantidad;
	}

	public String getNombre() {
		return nombre.get();
	}

	public void setNombre(String n) {
		this.nombre.set(n);
	}

	public String getDescripcion() {
		return descripcion.get();
	}

	public void setDescripcion(String d) {
		this.descripcion.set(d);
	}

	public Float getPrecio() {
		return precio.get();
	}

	public void setPrecio(Float nf) {
		this.precio.set(nf);
	}

	public int getCantidad() {
		return cantidad.get();
	}

	public void setCantidad(int cant) {
		this.cantidad.set(cant);
	}
}
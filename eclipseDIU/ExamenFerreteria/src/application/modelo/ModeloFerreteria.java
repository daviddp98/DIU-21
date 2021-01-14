package application.modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ModeloFerreteria {

	private final ProductoDAOJDBC daojdbc;

	private int ultimaID; // Guardo la ultima id de la base de datos
	private IntegerProperty nElementosInsertados;

	public ModeloFerreteria() {
		this.daojdbc = new ProductoDAOJDBC();
	}

	public ObservableList<Producto> cargarListaElementos() throws ProductoException {

		ObservableList<Producto> res = FXCollections.observableArrayList(daojdbc.cargarListaElementos());

		if (!res.isEmpty()) {
			this.ultimaID = res.get(res.size() - 1).getId();
			this.nElementosInsertados = new SimpleIntegerProperty(res.size());

		} else {
			this.ultimaID = 0;
			this.nElementosInsertados = new SimpleIntegerProperty(0);
		}
		return res;
	}

	public void guardarElemento(Producto p) throws ProductoException {
		daojdbc.guardarElemento(p);
		this.ultimaID++;
		this.nElementosInsertados.set(nElementosInsertados.get() + 1);
	}

	public void modificarElemento(Producto p) throws ProductoException {
		daojdbc.modificarElemento(p);
	}

	public void borrarElemento(Producto p) throws ProductoException {
		daojdbc.borrarElemento(p);
		this.nElementosInsertados.set(nElementosInsertados.get() - 1);
	}

	public Float calcularTotal(Float unidades, Producto p) {
		return unidades * p.getPrecio();
	}

	public int getUltimaID() {
		return ultimaID;
	}

	public IntegerProperty getnElementosInsertados() {
		return nElementosInsertados;
	}
}
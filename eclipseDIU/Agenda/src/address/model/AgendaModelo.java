package address.model;

import java.util.List;
import java.util.Observable;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * Modelo de funcionamiento de la aplicación. Implementa los métodos de las
 * distintas funciones que realiza la aplicación.
 *
 * @author David Delgado
 */

public class AgendaModelo extends Observable{

	private final AgendaDAOJDBC daojdbc;
	private int lastId;
	private double people_inserted;
	ObservableList<AgendaVO> plist = FXCollections.observableArrayList();

	public int getLastId() {
		return lastId;
	}

	/**
	 *
	 * Constructor del modelo. Inicializa un objeto del tipo AgendaDAOJDBC, a través
	 * del cual se realiza el acceso a los datos.
	 *
	 */
	public AgendaModelo() {
		daojdbc = new AgendaDAOJDBC();
	}

	/**
	 * @return una colección de datos de tipo List, que contiene las personas.
	 * @throws AgendaException
	 */
	public List<AgendaVO> cargarListaPersona() throws AgendaException {
		ObservableList<AgendaVO> res = FXCollections.observableArrayList(daojdbc.cargarListaPersona());
		if (!res.isEmpty()) {
			this.lastId = res.get(res.size() - 1).getId();
			this.people_inserted = res.size();
		} else {
			this.lastId = 0;
			this.people_inserted = 0;
		}
		return res;
	}

	/**
	 * Método que guarda una persona
	 * 
	 * @throws AgendaException
	 */

	public void guardarPersona(List<AgendaVO> pl) throws AgendaException {
		this.lastId++;
		pl.get(0).setId(lastId);
		daojdbc.guardarPersona(pl);
	}
	
	/**
	 * Método que actualiza una persona
	 * 
	 * @throws AgendaException
	 */

	public void actualizarPersona(List<AgendaVO> pl) throws AgendaException {
		daojdbc.actualizarPersona(pl);
		people_inserted--;
	}
	
	/**
	 * Método que elimina una persona
	 * 
	 * @throws AgendaException
	 */

	public void eliminarPersona(List<AgendaVO> pl) throws AgendaException {
		daojdbc.eliminarPersona(pl);
		people_inserted--;
	}

	public double getPeople_inserted() {
		return people_inserted;
	}
}
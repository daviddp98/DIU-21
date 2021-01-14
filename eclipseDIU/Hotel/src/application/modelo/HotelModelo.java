package application.modelo;

import java.sql.Connection;
import java.util.ArrayList;

public class HotelModelo {
	DAOJDBC jdbc = new DAOJDBC();
	private ArrayList<ClienteVO> listaclientes = new ArrayList<>();
	private ArrayList<ReservaVO> listareservas = new ArrayList<>();
	private ArrayList<ReservaVO> listareservasporpersona = new ArrayList<>();
	private int doblesolo, dobledoble, suitejr, suite, codigo;
	private Connection conn;

	/**
	 * Constructor de nuestra clase que iniciará nuestras variables y listas que
	 * utilizaremos durante toda la aplicacion
	 */
	public HotelModelo() {
		conn = jdbc.Conexion();
		if (conn != null) {
			listaclientes = jdbc.CargarPersonas();
			listareservas = jdbc.CargarReservas();
			doblesolo = 20;
			dobledoble = 80;
			suitejr = 15;
			suite = 5;
			codigo = jdbc.ObtenerUltimoCodigo();
		}
	}

	/**
	 * Método que permite modificar un cliente
	 * 
	 * @param c       ClienteVO con los datos del anterior
	 * @param cliente ClienteVO con los datos modificados
	 * @param dni     string con el dni del cliente
	 * @return booleano con el resultado de la modicacion
	 */
	public boolean Modificar(ClienteVO c, ClienteVO cliente, String dni) {
		listaclientes.remove(c);
		listaclientes.add(cliente);
		return jdbc.Modificar(cliente);
	}

	/**
	 * Método que nos permite obtener el último código
	 * 
	 * @return entero con el codigo
	 */
	public int Codigo() {
		return codigo;
	}

	/**
	 * Este método nos permite añadir un ClienteVO y una ReservaVO en la base de
	 * datos
	 * 
	 * @param cliente ClienteVO con los datos del cliente (en caso de ya existir
	 *                cliente = null)
	 * @param reserva ReservaVO con los datos de la reserva
	 * @return booleano con el resultado de la operación
	 */
	public boolean AñadirReserva(ClienteVO cliente, ReservaVO reserva) {
		codigo++;
		if (cliente != null) {
			listaclientes.add(cliente);
		}
		listareservas.add(reserva);
		return jdbc.AñadirReserva(cliente, reserva);
	}

	/**
	 * Método que nos devuelve la lista de clientes
	 * 
	 * @return ArrayList de ClienteVO
	 */
	public ArrayList<ClienteVO> CargarPersonas() {
		return listaclientes;
	}

	/**
	 * Método que nos devuelve la lista de reservas
	 * 
	 * @return ArrayList de ReservaVO
	 */
	public ArrayList<ReservaVO> CargarReservas() {
		return listareservas;
	}

	/**
	 * Método que nos devuelve la lista de reservas de un cliente
	 * 
	 * @param dni stringcon el dni del ClienteVO
	 * @return ArrayList de ReservaVO
	 */
	public ArrayList<ReservaVO> CargarReservasPorPersona(String dni) {
		listareservasporpersona = new ArrayList<>();
		for (int i = 0; i < listareservas.size(); i++) {
			ReservaVO r = listareservas.get(i);
			if (r.getDni().equalsIgnoreCase(dni)) {
				listareservasporpersona.add(r);
			}
		}
		return listareservasporpersona;
	}

	/**
	 * Método que nos permite buscar en la base de datos si existe un dni en
	 * especifico
	 * 
	 * @param dni string con el dni del ClienteVO
	 * @return booleano con el resultado de la búsqueda
	 */
	public boolean ExisteDNI(String dni) {
		return jdbc.ExisteDNI(dni.toUpperCase());
	}

	/**
	 * Método que nos devuelve el número de habitaciones dobles de uso individual
	 * 
	 * @return entero con el nº de habitaciones
	 */
	public int HabitacionDobleIndividual() {
		return doblesolo;
	}

	/**
	 * Método que nos devuelve el número de habitaciones dobles de uso compartido
	 * 
	 * @return entero con el nº de habitaciones
	 */
	public int HabitacionDobleCompartida() {
		return dobledoble;
	}

	/**
	 * Método que nos devuelve el número de habitaciones suite junior
	 * 
	 * @return entero con el nº de habitaciones
	 */
	public int HabitacionSuiteJr() {
		return suitejr;
	}

	/**
	 * Método que nos devuelve el número de habitaciones suite
	 * 
	 * @return entero con el nº de habitaciones
	 */
	public int HabitacionSuite() {
		return suite;
	}

	public Connection Conexion() {
		return conn;
	}

	/**
	 * Método que nos permite modificar una reserva
	 * 
	 * @param r       ReservaVO inicial
	 * @param reserva ReservaVO modificada
	 * @return booleano con el estado de la modificación
	 */
	public boolean ModificarReserva(ReservaVO r, ReservaVO reserva) {
		listareservas.remove(r);
		listareservas.add(reserva);
		return jdbc.ModificarReserva(reserva);
	}
}
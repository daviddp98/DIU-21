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
	 * Constructor de nuestra clase que iniciar� nuestras variables y listas que
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
	 * M�todo que permite modificar un cliente
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
	 * M�todo que nos permite obtener el �ltimo c�digo
	 * 
	 * @return entero con el codigo
	 */
	public int Codigo() {
		return codigo;
	}

	/**
	 * Este m�todo nos permite a�adir un ClienteVO y una ReservaVO en la base de
	 * datos
	 * 
	 * @param cliente ClienteVO con los datos del cliente (en caso de ya existir
	 *                cliente = null)
	 * @param reserva ReservaVO con los datos de la reserva
	 * @return booleano con el resultado de la operaci�n
	 */
	public boolean A�adirReserva(ClienteVO cliente, ReservaVO reserva) {
		codigo++;
		if (cliente != null) {
			listaclientes.add(cliente);
		}
		listareservas.add(reserva);
		return jdbc.A�adirReserva(cliente, reserva);
	}

	/**
	 * M�todo que nos devuelve la lista de clientes
	 * 
	 * @return ArrayList de ClienteVO
	 */
	public ArrayList<ClienteVO> CargarPersonas() {
		return listaclientes;
	}

	/**
	 * M�todo que nos devuelve la lista de reservas
	 * 
	 * @return ArrayList de ReservaVO
	 */
	public ArrayList<ReservaVO> CargarReservas() {
		return listareservas;
	}

	/**
	 * M�todo que nos devuelve la lista de reservas de un cliente
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
	 * M�todo que nos permite buscar en la base de datos si existe un dni en
	 * especifico
	 * 
	 * @param dni string con el dni del ClienteVO
	 * @return booleano con el resultado de la b�squeda
	 */
	public boolean ExisteDNI(String dni) {
		return jdbc.ExisteDNI(dni.toUpperCase());
	}

	/**
	 * M�todo que nos devuelve el n�mero de habitaciones dobles de uso individual
	 * 
	 * @return entero con el n� de habitaciones
	 */
	public int HabitacionDobleIndividual() {
		return doblesolo;
	}

	/**
	 * M�todo que nos devuelve el n�mero de habitaciones dobles de uso compartido
	 * 
	 * @return entero con el n� de habitaciones
	 */
	public int HabitacionDobleCompartida() {
		return dobledoble;
	}

	/**
	 * M�todo que nos devuelve el n�mero de habitaciones suite junior
	 * 
	 * @return entero con el n� de habitaciones
	 */
	public int HabitacionSuiteJr() {
		return suitejr;
	}

	/**
	 * M�todo que nos devuelve el n�mero de habitaciones suite
	 * 
	 * @return entero con el n� de habitaciones
	 */
	public int HabitacionSuite() {
		return suite;
	}

	public Connection Conexion() {
		return conn;
	}

	/**
	 * M�todo que nos permite modificar una reserva
	 * 
	 * @param r       ReservaVO inicial
	 * @param reserva ReservaVO modificada
	 * @return booleano con el estado de la modificaci�n
	 */
	public boolean ModificarReserva(ReservaVO r, ReservaVO reserva) {
		listareservas.remove(r);
		listareservas.add(reserva);
		return jdbc.ModificarReserva(reserva);
	}
}
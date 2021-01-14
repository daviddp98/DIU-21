package application.modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOJDBC {
	private ConexionJDBC conexion = new ConexionJDBC();
	private ArrayList<ClienteVO> listaclientes;
	private ArrayList<ReservaVO> listareservas;

	/**
	 * Método dao que nos permite modificar los datos del cliente
	 * 
	 * @param cliente ClienteVO con los datos modificados
	 * @return boolean con el estado de la operacion
	 */
	public boolean Modificar(ClienteVO cliente) {
		Connection conn;
		try {
			conn = conexion.conectarBD();

			Statement stmt;
			stmt = conn.createStatement();
			String sql = "UPDATE `clientes` SET `dni`= '" + cliente.getDni() + "',`nombre`= '" + cliente.getNombre()
					+ "',`apellido`= '" + cliente.getApellido() + "',`direccion`= '" + cliente.getDireccion() + "',"
					+ "`localidad`='" + cliente.getLocalidad() + "'," + "`provincia`='" + cliente.getProvincia()
					+ "' WHERE dni =  '" + cliente.getDni() + "';";
			stmt.executeUpdate(sql);

			stmt.close();
			conexion.desconectarBD(conn);
			return true;

		} catch (SQLException ex) {

		} catch (ClassNotFoundException e1) {
		}
		return false;
	}

	/**
	 * Método dao que nos permite cvargar una lista de clientes
	 * 
	 * @return array list de personas
	 */
	public ArrayList<ClienteVO> CargarPersonas() {
		listaclientes = new ArrayList<ClienteVO>();
		Connection conn;

		String dni, nombre, apellido, direccion, localidad, provincia;
		try {
			conn = conexion.conectarBD();

			Statement stmt;
			stmt = conn.createStatement();
			String sql = "SELECT * FROM clientes";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				dni = rs.getString("dni");
				nombre = rs.getString("nombre");
				apellido = rs.getString("apellido");
				direccion = rs.getString("direccion");
				localidad = rs.getString("localidad");
				provincia = rs.getString("provincia");
				ClienteVO c = new ClienteVO(dni.toUpperCase(), nombre, apellido, direccion, localidad, provincia);
				listaclientes.add(c);
			}
			stmt.close();
			conexion.desconectarBD(conn);
			return listaclientes;

		} catch (SQLException e) {
			return null;
		} catch (ClassNotFoundException e1) {
			return null;
		}
	}

	/**
	 * Método dao que nos permite comprobar si existe una persona en la base de
	 * datos
	 * 
	 * @param dni dni del cliente
	 * @return boolean con el resultado de la comprobación
	 */
	public boolean ExisteDNI(String dni) {
		Connection conn;
		try {
			conn = conexion.conectarBD();
			Statement stmt;
			stmt = conn.createStatement();
			String sql = "SELECT * FROM clientes where dni= '" + dni + "'";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				stmt.close();
				conexion.desconectarBD(conn);
				return true;
			}

			stmt.close();
			conexion.desconectarBD(conn);

			return false;

		} catch (SQLException e) {
			return false;
		} catch (ClassNotFoundException e1) {
			return false;
		}

	}

	/**
	 * Método dao que nos permite obtener el último código [Auto incremented] de la
	 * tabla reserva
	 * 
	 * @return entero con el valor del último código
	 */
	public int ObtenerUltimoCodigo() {
		Connection conn;
		int cod = -1;
		try {
			conn = conexion.conectarBD();
			Statement stmt;
			stmt = conn.createStatement();
			String sql = "SELECT id_reserva FROM reservas order by id_reserva desc limit 0,1";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				cod = rs.getInt("codigo");
				stmt.close();
				conexion.desconectarBD(conn);
				return cod;
			}
			return 0;
		} catch (SQLException e) {
			return -1;
		} catch (ClassNotFoundException e1) {
			return -1;
		}
	}

	public Connection Conexion() {
		try {
			return conexion.conectarBD();
		} catch (ClassNotFoundException e) {
			return null;
		}
	}

	/**
	 * Método dao que nos permite añadir en la base de datos una reserva y un
	 * cliente
	 * 
	 * @param cliente ClienteVO que realiza la reserva
	 * @param reserva ReservaVO con la reserva realizada
	 * @return boolean con el estado de la operacion
	 */
	public boolean AñadirReserva(ClienteVO cliente, ReservaVO reserva) {
		Connection conn;
		try {
			conn = conexion.conectarBD();
			Statement stmt;
			stmt = conn.createStatement();
			if (cliente != null) {
				String sqlcliente = "INSERT INTO clientes VALUES ('" + cliente.getDni().toUpperCase() + "','"
						+ cliente.getNombre() + "','" + cliente.getApellido() + "','" + cliente.getDireccion() + "','"
						+ cliente.getLocalidad() + "','" + cliente.getProvincia() + "');";

				stmt.executeUpdate(sqlcliente);
			}

			String sqlreserva = "INSERT INTO reservas VALUES (" + "'" + reserva.getCodigo() + "'," + "'"
					+ reserva.getDni() + "'," + "'" + convertUtilToSql(reserva.getFechaentrada()) + "'," + "'"
					+ convertUtilToSql(reserva.getFechasalida()) + "'," + "'" + reserva.getHabitaciones() + "'," + "'"
					+ reserva.getTipoHabitacion() + "'," + "'" + reserva.getFumador().get() + "'," + "'"
					+ reserva.getRegimen().get() + "')";
			stmt.executeUpdate(sqlreserva);
			stmt.close();
			conexion.desconectarBD(conn);
			return true;
		} catch (SQLException ex) {
			return false;
		} catch (ClassNotFoundException e1) {
			return false;
		}
	}

	/**
	 * Método dao que nos devuelve una lista de reservas
	 * 
	 * @return array list de reservas
	 */
	public ArrayList<ReservaVO> CargarReservas() {
		listareservas = new ArrayList<ReservaVO>();
		Connection conn;

		String dni, regimen, tipo, fumador, estado;
		java.sql.Date entrada, salida;
		int codigo, habitaciones;
		try {
			conn = conexion.conectarBD();

			Statement stmt;
			stmt = conn.createStatement();
			String sql = "SELECT * FROM reservas";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {

				codigo = rs.getInt("id_reserva");
				dni = rs.getString("dni");
				entrada = rs.getDate("fechaentrada");
				salida = rs.getDate("fechasalida");
				habitaciones = rs.getInt("habitaciones");
				tipo = rs.getString("tipo");
				regimen = rs.getString("regimen");
				fumador = rs.getString("fumador");
				estado = rs.getString("estado");

				ReservaVO reserva = new ReservaVO(codigo, dni, convertSqlToUtil(entrada), convertSqlToUtil(salida),
						habitaciones, tipo, regimen, fumador, estado);
				listareservas.add(reserva);
			}
			stmt.close();
			conexion.desconectarBD(conn);
			return listareservas;

		} catch (SQLException e) {
			return null;
		} catch (ClassNotFoundException e1) {
			return null;
		}
	}

	/**
	 * Método dao que nos permite modificar una reserva
	 * 
	 * @param reserva ReservaVO con los datos de la reserva modificada
	 * @return boolean con el estado de la actualización
	 */
	public boolean ModificarReserva(ReservaVO reserva) {
		Connection conn;
		try {
			conn = conexion.conectarBD();

			Statement stmt;
			stmt = conn.createStatement();
			
			String sql = "UPDATE reservas SET fecha_entrada = '" + convertUtilToSql(reserva.getFechaentrada())
					+ "' WHERE id_reserva = " + reserva.getCodigo();
			stmt.executeUpdate(sql);
			sql = "UPDATE reservas SET fecha_salida = '" + convertUtilToSql(reserva.getFechasalida())
					+ "' WHERE id_reserva = " + reserva.getCodigo();
			stmt.executeUpdate(sql);
			sql = "UPDATE reservas SET num_personas = '" + reserva.getHabitaciones() + "' WHERE id_reserva = "
					+ reserva.getCodigo();
			stmt.executeUpdate(sql);
			sql = "UPDATE reserva SET tipo_habitacion = '" + reserva.getTipoHabitacion() + "' WHERE id_reserva = "
					+ reserva.getCodigo();
			stmt.executeUpdate(sql);
			sql = "UPDATE reserva SET reg_alojamiento = '" + reserva.getRegimen().get() + "' WHERE id_reserva = "
					+ reserva.getCodigo();
			stmt.executeUpdate(sql);
			sql = "UPDATE reserva SET fumador = '" + reserva.getFumador().get() + "' WHERE id_reserva = "
					+ reserva.getCodigo();
			stmt.executeUpdate(sql);

			stmt.close();
			conexion.desconectarBD(conn);
			return true;

		} catch (SQLException ex) {

		} catch (ClassNotFoundException e1) {
		}
		return false;
	}

	/**
	 * Método que permite convertir un Date de Util a SQl
	 * 
	 * @param uDate date util
	 * @return Date sql
	 */
	private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
		java.sql.Date sDate = new java.sql.Date(uDate.getTime());
		return sDate;
	}

	/**
	 * Método que permite convertir un Date de SQL a Util
	 * 
	 * @param sDate date sql
	 * @return Date util
	 */
	private static java.util.Date convertSqlToUtil(java.sql.Date sDate) {
		java.util.Date uDate = new java.util.Date(sDate.getTime());
		return uDate;
	}
}
package address.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import address.util.DateUtil;

/**
 * Clase que implementa la interfaz del tipo DAO.
 *
 * @author David Delgado
 */

public class AgendaDAOJDBC implements AgendaDAO {

	public AgendaDAOJDBC() {

	}

	@Override
	public List<AgendaVO> cargarListaPersona() throws AgendaException {
		// TODO Auto-generated method stub

		List<AgendaVO> lista = new ArrayList<>();
		ConexionJDBC conex = new ConexionJDBC();
		Statement comando;

		try {
			comando = conex.getConnection().createStatement();
			ResultSet registro = comando
					.executeQuery("SELECT id, firstname,lastname, street, postalcode, city, birthday FROM persona;");

			while (registro.next() == true) {
				lista.add(new AgendaVO(registro.getInt("id"), registro.getString("firstname"),
						registro.getString("lastname"), registro.getString("street"), registro.getInt("postalcode"),
						registro.getString("city"), DateUtil.parse(registro.getString("birthday"))));
			}

		} catch (SQLException ex) {
			// TODO: handle exception
			throw new AgendaException("ERROR SQL --> Código: " + ex.getErrorCode() + " Mensaje: " + ex.getMessage());
		}
		conex.desconectar();
		return lista;
	}

	@Override
	public void guardarPersona(List<AgendaVO> pl) throws AgendaException {
		// TODO Auto-generated method stub

		ConexionJDBC conex = new ConexionJDBC();
		AgendaVO p = pl.get(0);
		Statement comando;

		try {
			comando = conex.getConnection().createStatement();
			comando.executeUpdate(
					"INSERT INTO persona(firstname, lastname, street, postalcode, city, birthday) VALUES ('"
							+ p.getFirstName() + "', '" + p.getLastName() + "', '" + p.getStreet() + "', "
							+ p.getPostalCode() + ", '" + p.getCity() + "', '" + DateUtil.format(p.getBirthday())
							+ "');");
		} catch (SQLException ex) {
			// TODO: handle exception
			throw new AgendaException("ERROR SQL --> Código: " + ex.getErrorCode() + " Mensaje: " + ex.getMessage());
		}
		conex.desconectar();
	}

	@Override
	public void actualizarPersona(List<AgendaVO> pl) throws AgendaException {
		// TODO Auto-generated method stub

		ConexionJDBC conex = new ConexionJDBC();
		Statement comando;
		AgendaVO p = pl.get(0);

		try {
			comando = conex.getConnection().createStatement();
			comando.executeUpdate("UPDATE persona SET firstname= '" + p.getFirstName() + "', lastname= '"
					+ p.getLastName() + "', street= '" + p.getStreet() + "', postalcode= " + p.getPostalCode()
					+ ", city= '" + p.getCity() + "', birthday= '" + DateUtil.format(p.getBirthday()) + "' WHERE id= "
					+ p.getId() + ";");
		} catch (SQLException ex) {
			// TODO: handle exception
			throw new AgendaException("ERROR SQL --> Código: " + ex.getErrorCode() + " Mensaje: " + ex.getMessage());
		}
		conex.desconectar();
	}

	@Override
	public void eliminarPersona(List<AgendaVO> pl) throws AgendaException {
		// TODO Auto-generated method stub

		ConexionJDBC conex = new ConexionJDBC();
		Statement comando;
		AgendaVO p = pl.get(0);

		try {
			comando = conex.getConnection().createStatement();
			comando.executeUpdate("DELETE FROM persona WHERE id= " + p.getId() + ";");
		} catch (SQLException ex) {
			// TODO: handle exception
			throw new AgendaException("ERROR SQL --> Código: " + ex.getErrorCode() + " Mensaje: " + ex.getMessage());
		}
		conex.desconectar();
	}
}
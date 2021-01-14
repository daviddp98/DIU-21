package application.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOJDBC implements ProductoDAO {

	private Connection conex = null;

	public ProductoDAOJDBC() {
		
	}

	@Override
	public List<Producto> cargarListaElementos() throws ProductoException {
		List<Producto> res = new ArrayList<>();

		openConnection();

		Statement comando;
		try {
			comando = conex.createStatement();
			ResultSet registro = comando
					.executeQuery("SELECT codigo, nombre, descripcion, cantidad, precio FROM productos;");

			while (registro.next() == true) {
				res.add(new Producto(registro.getInt("codigo"), registro.getString("nombre"),
						registro.getString("descripcion"), registro.getInt("cantidad"), registro.getFloat("precio")));
			}

		} catch (SQLException e) {
			throw new ProductoException(e.getMessage());
		}

		closeConnection();

		return res;
	}

	@Override
	public void guardarElemento(Producto p) throws ProductoException {

		openConnection();

		Statement comando;
		try {
			comando = conex.createStatement();
			comando.executeUpdate(
					"INSERT INTO productos(nombre, descripcion, cantidad, precio) VALUES ('" + p.getNombre() + "', '"
							+ p.getDescripcion() + "', '" + p.getCantidad() + "', '" + p.getPrecio() + "');");
		} catch (SQLException e) {
			throw new ProductoException(e.getErrorCode());
		}

		closeConnection();
	}

	@Override
	public void modificarElemento(Producto p) throws ProductoException {
		openConnection();

		Statement comando;
		try {
			comando = conex.createStatement();
			comando.executeUpdate("UPDATE productos SET nombre= '" + p.getNombre() + "', descripcion = '"
					+ p.getDescripcion() + "', cantidad = " + p.getCantidad() + ", precio = " + p.getPrecio()
					+ " WHERE codigo= " + p.getId() + ";");
		} catch (SQLException e) {
			throw new ProductoException(e.getErrorCode());
		}

		closeConnection();
	}

	@Override
	public void borrarElemento(Producto p) throws ProductoException {

		openConnection();

		try {
			Statement comando = conex.createStatement();
			comando.executeUpdate("DELETE FROM productos WHERE codigo= " + p.getId() + ";");
		} catch (SQLException e) {
			throw new ProductoException(e.getErrorCode());
		}
		closeConnection();
	}

	private void openConnection() throws ProductoException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conex = DriverManager.getConnection("jdbc:mysql://localhost/ferreteria", "root", "");
		} catch (SQLException e) {
			throw new ProductoException(e.getErrorCode());
		} catch (ClassNotFoundException e) {
			System.out.println(e);
			throw new ProductoException("Clase no encontrada: " + e.getMessage() + "");
		} catch (Exception e) {
			System.out.println(e);
			throw new ProductoException("Error: " + e.getMessage() + "");
		}
	}

	private void closeConnection() {
		conex = null;
	}
}
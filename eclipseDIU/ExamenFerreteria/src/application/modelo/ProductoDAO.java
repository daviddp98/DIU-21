package application.modelo;

import java.util.List;

public interface ProductoDAO {

	public List<Producto> cargarListaElementos() throws ProductoException;

	public void guardarElemento(Producto p) throws ProductoException;

	public void modificarElemento(Producto p) throws ProductoException;

	public void borrarElemento(Producto p) throws ProductoException;
}
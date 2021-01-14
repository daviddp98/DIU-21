/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package address.model;

import java.util.List;

/**
 *
 * Interfaz que define el m�todo de alta a la base de datos de un Art�culo.
 * Recibe las columnas de una BD mySQL.
 *
 * @author David Delgado
 */

public interface AgendaDAO {

	/**
	 *
	 * 
	 * @throws AgendaException
	 */
	public List<AgendaVO> cargarListaPersona() throws AgendaException;

	/**
	 *
	 * 
	 * @throws AgendaException
	 */
	public void guardarPersona(List<AgendaVO> pl) throws AgendaException;

	/**
	 *
	 * 
	 * @throws AgendaException
	 */
	public void actualizarPersona(List<AgendaVO> pl) throws AgendaException;

	/**
	 *
	 * 
	 * @throws AgendaException
	 */
	public void eliminarPersona(List<AgendaVO> pl) throws AgendaException;
}
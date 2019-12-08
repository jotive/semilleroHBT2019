package com.hbt.semillero.ejb;

import java.util.List;

import javax.ejb.Local;

import com.hbt.semillero.dto.RolDTO;

/**
 * Expone los métodos del EJB GestionarRol Las interfaces determinan una
 * especie de contrato donde se define las firmas de los metodos, define que se
 * necesita implementar pero no el como eso lo realiza la clase que la
 * implementa Palabras claves interface e implements
 * 
 * @author Jose Tirado Verbel <Jotive@gmail.com>
 *
 */
@Local
public interface IGestionarRolLocal {

	/**
	 * 
	 * Metodo encargado de crear un personaje y persistirlo
	 * 
	 * @author Jose Tirado Verbel <Jotive@gmail.com>
	 * @param rolNuevo informacion nueva a crear
	 */
	public void crearRol(RolDTO rolNuevo);

	/**
	 * Metodo encargado de consultar un rol modificarlo y guardarlo
	 * @author Jose Tirado Verbel <Jotive@gmail.com>
	 * @param rolModificar informacion nueva a modificar
	 */
	public void modificarRol(Long id, String nombre, RolDTO rolNuevo);

	/**
	 * Metodo encargado de eliminar un rol modificarlo y guardarlo
	 * @author Jose Tirado Verbel <Jotive@gmail.com>
	 * @param rolEliminar informacion a eliminar
	 */
	public void eliminarRol(Long idRol);

	/**
	 * Metodo encargado de retornar la informacion de un rol
	 * @param idRol identificador del rol a ser consultado
	 * @return rol resultado de la consulta
	 * @throws Exception si no se recibe idRol
	 */
	
	public RolDTO consultarRol(String idRol);
	/**
	 * Metodo encargado de retornar una lista de roles
	 * @return
	 */
	public List<RolDTO> consultarRoles();
}

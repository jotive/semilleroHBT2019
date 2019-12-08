package com.hbt.semillero.ejb;

import java.util.List;

import javax.ejb.Local;
import com.hbt.semillero.dto.PersonajeDTO;

/**
 * Expone los métodos del EJB GestionarPersonaje Las interfaces determinan una
 * especie de contrato donde se define las firmas de los metodos, define que se
 * necesita implementar pero no el como eso lo realiza la clase que la
 * implementa Palabras claves interface e implements
 * 
 * @author ccastano
 *
 */
@Local
public interface IGestionarPersonajeLocal {

	/**
	 * Metodo encargado de crear un personaje
	 * @param personajeNuevo informacion nueva a crear
	 */
	public void crearPersonaje(PersonajeDTO personajeDTO);

	/**
	 * Metodo encargado de consultar un personaje modificarlo y guardarlo
	 * @param personajeModificar informacion nueva a modificar
	 */
	public void actualizarPersonaje();

	/**
	 * Metodo encargado de eliminar un personaje modificarlo y guardarlo
	 * @param personajeEliminar informacion a eliminar
	 */
	public void eliminarPersonaje();

	/**
	 * Metodo encargado de retornar la informacion de un personaje
	 * @return personaje resultado de la consulta
	 * @throws Exception si no se recibe idPersonaje
	 */
	public List<PersonajeDTO> consultarPersonaje(Long idComic);

	/**
	 * Metodo encargado de retornar una lista de personajes
	 * @return
	 */
	public List<PersonajeDTO> consultarPersonajes();
}

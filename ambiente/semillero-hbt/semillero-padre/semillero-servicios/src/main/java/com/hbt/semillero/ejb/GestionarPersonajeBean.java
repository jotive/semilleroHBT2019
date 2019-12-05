/**
 * GestionarPersonajeBean.java
 */
package com.hbt.semillero.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.entidad.Personaje;

/**
 * <b>Descripción:<b> Clase que determina el bean para realizar las gestion de
 * los personajes
 * 
 * @author ccastano
 * @author Jose Eduardo Tirado Verbel <Jotive@gmail.com>
 * @version
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarPersonajeBean implements IGestionarPersonajeLocal {
	
	final static Logger logger = Logger.getLogger(GestionarPersonajeBean.class);

	/**
	 * Atributo em que se usa para interacturar con el contexto de persistencia.
	 */
	@PersistenceContext
	private EntityManager em;

	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarPersonajeLocal#crearComic(com.hbt.semillero.dto.PersonajeDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void crearPersonaje(PersonajeDTO personajeNuevo) {
		// Entidad nueva
		Personaje personaje = convertirPersonajeDTOToPersonaje(personajeNuevo);
		// Se almacena la informacion y se maneja la enidad comic
		em.persist(personaje);
	}

	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarPersonajeLocal#modificarPersonaje(com.hbt.semillero.dto.PersonajeDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void modificarPersonaje(Long id, String nombre, PersonajeDTO personajeNuevo) {
		Personaje personajeModificar ;
		if(personajeNuevo==null) {
			// Entidad a modificar
			personajeModificar = em.find(Personaje.class, id);
		}else {
			personajeModificar = convertirPersonajeDTOToPersonaje(personajeNuevo);
		}
		personajeModificar.setNombre(nombre);
		em.merge(personajeModificar);
	}

	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarPersonajeLocal#eliminarPersonaje(java.lang.Long)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarPersonaje(Long idPersonaje) {
		Personaje personajeEliminar = em.find(Personaje.class, idPersonaje);
		if (personajeEliminar != null) {
			em.remove(personajeEliminar);
		}
	}

	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarPersonajeLocal#consultarPersonaje(java.lang.String)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public PersonajeDTO consultarPersonaje(String idPersonaje) {
		Personaje personaje = null;
		personaje = new Personaje();
		personaje = em.find(Personaje.class, Long.parseLong(idPersonaje));
		PersonajeDTO personajeDTO = convertirPersonajeToPersonajeDTO(personaje);
		return personajeDTO;
	}

	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarPersonajeLocal#consultarPersonajes()
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<PersonajeDTO> consultarPersonajes() {
		logger.debug("Se ejecuta el comando consultar comics");
		List<PersonajeDTO> resultadosPersonajeDTO = new ArrayList<PersonajeDTO>();
		List<Personaje> resultados = em.createQuery("select p from Personaje p").getResultList();
		for (Personaje personaje:resultados) {
			resultadosPersonajeDTO.add(convertirPersonajeToPersonajeDTO(personaje));
		}
		return resultadosPersonajeDTO;
	}

	/**
	 * 
	 * Metodo encargado de transformar un personaje a un personajeDTO
	 * 
	 * @param comic
	 * @return
	 */
	private PersonajeDTO convertirPersonajeToPersonajeDTO(Personaje personaje) {
		PersonajeDTO personajeDTO = new PersonajeDTO();
		if(personaje.getId()!=null) {
		 personajeDTO.setId(personaje.getId().toString());
		}
		personajeDTO.setNombre(personaje.getNombre());
		personajeDTO.setEditorial(personaje.getEditorial());
		personajeDTO.setTematicaEnum(personaje.getTematicaEnum());
		personajeDTO.setColeccion(personaje.getColeccion());
		personajeDTO.setNumeroPaginas(personaje.getNumeroPaginas());
		personajeDTO.setPrecio(personaje.getPrecio());
		personajeDTO.setAutores(personaje.getAutores());
		personajeDTO.setColor(personaje.getColor());
		personajeDTO.setFechaVenta(personaje.getFechaVenta());
		personajeDTO.setEstadoEnum(personaje.getEstadoEnum());
		personajeDTO.setCantidad(personaje.getCantidad());
		return personajeDTO;
	}

	/**
	 * 
	 * Metodo encargado de transformar un personajeDTO a un personaje
	 * 
	 * @param personaje
	 * @return
	 */
	private Personaje convertirPersonajeDTOToPersonaje(PersonajeDTO personajeDTO) {
		Personaje personaje = new Personaje();
		if(personajeDTO.getId()!=null) {
			personaje.setId(Long.parseLong(personajeDTO.getId()));
		}
		personaje.setNombre(personajeDTO.getNombre());
		personaje.setEditorial(personajeDTO.getEditorial());
		personaje.setTematicaEnum(personajeDTO.getTematicaEnum());
		personaje.setColeccion(personajeDTO.getColeccion());
		personaje.setNumeroPaginas(personajeDTO.getNumeroPaginas());
		personaje.setPrecio(personajeDTO.getPrecio());
		personaje.setAutores(personajeDTO.getAutores());
		personaje.setColor(personajeDTO.getColor());
		personaje.setFechaVenta(personajeDTO.getFechaVenta());
		personaje.setEstadoEnum(personajeDTO.getEstadoEnum());
		personaje.setCantidad(personajeDTO.getCantidad());
		return personaje;
	}

}

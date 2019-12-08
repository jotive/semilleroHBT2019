/**
 * GestionarPersonajeBean.java
 */
package com.hbt.semillero.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.entidad.Comic;
import com.hbt.semillero.entidad.Personaje;

/**
 * <b>Descripción:<b> Clase que determina el bean para realizar las gestion de
 * los personajes
 * @author Jose Eduardo Tirado Verbel <Jotive@gmail.com>
 * @version
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarPersonajeBean implements IGestionarPersonajeLocal {
	
	private static final Logger logger = Logger.getLogger(GestionarPersonajeBean.class);

	/* Atributo em que se usa para interacturar con el contexto de persistencia.*/
	@PersistenceContext
	private EntityManager em;
	
	
	/*Metodo encargado de crear personaje*/
	@Override
	public void crearPersonaje(PersonajeDTO personajeDTO) {
		logger.debug("Inicia metodo CrearPersonaje");
		Personaje personaje = convertirDTOEntidad(personajeDTO);
		em.persist(personaje);
		logger.debug("Finaliza metodo CrearPersonaje");
	}
	
	
	/*Metodo encargado de actualizar personaje*/
	public void actualizarPersonaje() {
		logger.debug("Inicia metodo actualizarPersonaje");
		
		logger.debug("Finaliza metodo actualizarPersonaje");
	}
	
	
	/*Metodo encargado de eliminar personaje*/
	public void eliminarPersonaje() {
		logger.debug("Inicia metodo eliminarPersonaje");
		
		logger.debug("Finaliza metodo eliminarPersonaje");
	}
	
	/*Metodo encargado de consultar un solo personaje*/
	public List<PersonajeDTO> consultarPersonaje(Long idComic) {
		logger.debug("Inicia metodo consultarPersonaje");
		
		String query = "SELECT personaje FROM Personaje WHERE personaje.comic.id = :idComic";
		
		List<Personaje> listaPersonaje = em.createQuery(query).setParameter("idComic", idComic).getResultList();
		
		List<PersonajeDTO> listaPersonajeDTO = new ArrayList<>();
		
		for (Personaje personaje : listaPersonaje) {
			listaPersonajeDTO.add(convertirEntidadDTO(personaje));
		}
		
		logger.debug("Finaliza metodo consultarPersonaje");
		
		return listaPersonajeDTO;
	}
	
	
	/*Metodo encargado de consultar la lista de todos los personajes*/
	@SuppressWarnings({ "unchecked", "unused" })
	public List<PersonajeDTO> consultarPersonajes() {
		logger.debug("Inicia metodo consultarPersonajes");
		
		String query = "SELECT personaje FROM Personaje";
		
		List<Personaje> listaPersonaje = em.createQuery(query).getResultList();
		
		List<PersonajeDTO> listaPersonajeDTO = new ArrayList<>();
		
		for (Personaje personaje : listaPersonaje) {
			listaPersonajeDTO.add(convertirEntidadDTO(personaje));
		}
		
		logger.debug("Finaliza metodo consultarPersonajes");
		
		return listaPersonajeDTO;
	}
		

	/**
	 * Metodo encargado de transformar un personaje a un personajeDTO
	 * @param personaje DTO
	 * @return
	 */
	
	private Personaje convertirDTOEntidad(PersonajeDTO personajeDTO) {
		Personaje personaje = new Personaje();
		personaje.setId(personajeDTO.getId());
		personaje.setNombre(personajeDTO.getNombre());
		personaje.setComic(new Comic());
		personaje.getComic().setId(personajeDTO.getIdComic());
		personaje.setEstado(personajeDTO.getEstado());
		personaje.setSuperPoder(personajeDTO.getSuperPoder());
		
		return personaje;
	}
	
	private PersonajeDTO convertirEntidadDTO(Personaje personaje) {
		PersonajeDTO personajeDTO = new PersonajeDTO();
		personajeDTO.setId(personajeDTO.getId());
		personajeDTO.setNombre(personajeDTO.getNombre());
		personajeDTO.setIdComic(personaje.getComic().getId());
		personajeDTO.setEstado(personajeDTO.getEstado());
		personajeDTO.setSuperPoder(personajeDTO.getSuperPoder());
		
		return personajeDTO;
	}
	

}

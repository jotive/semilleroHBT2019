/**
 * GestionarComicBean.java
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

import com.hbt.semillero.dto.RolDTO;
import com.hbt.semillero.entidad.Rol;

/**
 * <b>Descripción:<b> Clase que determina el bean para realizar las gestion de
 * los comics
 * 
 * @author ccastano
 * @version
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarRolBean implements IGestionarRolLocal {
	
	final static Logger logger = Logger.getLogger(GestionarComicBean.class);

	/**
	 * Atributo em que se usa para interacturar con el contexto de persistencia.
	 */
	@PersistenceContext
	private EntityManager em;

	/**
	 * @see com.hbt.semillero.ejb.IGestionarRolLocal#crearRol(com.hbt.semillero.dto.RolDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void crearRol(RolDTO rolNuevo) {
		// Entidad nueva
		Rol rol = convertirRolDTOToRol(rolNuevo);
		// Se almacena la informacion y se maneja la enidad comic
		em.persist(rol);
	}

	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarRolLocal#modificarRol(com.hbt.semillero.dto.RolDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void modificarRol(Long id, String nombre, RolDTO rolNuevo) {
		Rol rolModificar ;
		if(rolNuevo==null) {
			// Entidad a modificar
			rolModificar = em.find(Rol.class, id);
		}else {
			rolModificar = convertirRolDTOToRol(rolNuevo);
		}
		rolModificar.setNombre(nombre);
		em.merge(rolModificar);
	}

	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarRolLocal#eliminarRol(java.lang.Long)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarRol(Long idRol) {
		Rol rolEliminar = em.find(Rol.class, idRol);
		if (rolEliminar != null) {
			em.remove(rolEliminar);
		}
	}

	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarRolLocal#consultarRol(java.lang.String)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public RolDTO consultarRol(String idRol) {
		Rol rol = null;
		rol = new Rol();
		rol = em.find(Rol.class, Long.parseLong(idRol));
		RolDTO rolDTO = convertirRolToRolDTO(rol);
		return rolDTO;
	}

	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarRolLocal#consultarRoles()
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<RolDTO> consultarRoles() {
		logger.debug("Se ejecuta el comando consultar roles");
		List<RolDTO> resultadosRolDTO = new ArrayList<RolDTO>();
		List<Rol> resultados = em.createQuery("select r from Rol r").getResultList();
		for (Rol rol:resultados) {
			resultadosRolDTO.add(convertirRolToRolDTO(rol));
		}
		return resultadosRolDTO;
	}

	/**
	 * 
	 * Metodo encargado de transformar un rol a un rolDTO
	 * 
	 * @param rol
	 * @return
	 */
	private RolDTO convertirRolToRolDTO(Rol rol) {
		RolDTO rolDTO = new RolDTO();
		if(rol.getId()!=null) {
		 rolDTO.setId(rol.getId().toString());
		}
		rolDTO.setNombre(rol.getNombre());
		rolDTO.setEstadoEnum(rol.getEstadoEnum());
		return rolDTO;
	}

	/**
	 * 
	 * Metodo encargado de transformar un rolDTO a un rol
	 * 
	 * @param rol
	 * @return
	 */
	private Rol convertirRolDTOToRol(RolDTO rolDTO) {
		Rol rol = new Rol();
		if(rolDTO.getId()!=null) {
			rol.setId(Long.parseLong(rolDTO.getId()));
		}
		rol.setNombre(rolDTO.getNombre());
		rol.setEstadoEnum(rolDTO.getEstadoEnum());
		return rol;
	}
}

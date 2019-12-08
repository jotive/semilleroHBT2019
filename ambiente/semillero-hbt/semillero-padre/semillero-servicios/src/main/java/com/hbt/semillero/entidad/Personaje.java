/**
 * Personaje.java
 */
package com.hbt.semillero.entidad;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * <b>Descripcion:<b> Clase que determina la entidad que permite representar la
 * tabla "DB_SEMILLERO"."PERSONAJE"
 * 
 * @author Jotive@gmail.com
 * @version
 */

@Entity
@Table(name = "PERSONAJE")
public class Personaje implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(allocationSize = 1, name = "PERSONAJE_ID_GENERATOR", sequenceName = "SEQ_PERSONAJE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSONAJE_ID_GENERATOR")
	
	/*
	 * Realizamos la definicion de propiedades
	 * */
	@Column(name = "PERS_ID")
	private Long id;
	
	@Column(name = "PERS_NOMBRE")
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name="PERS_ID_COMIC")
	private Comic comic;
	
	@Column(name = "PERS_ESTADO")
	private EstadoEnum estado;
	
	@Column(name = "PERS_SUPEPODER")
	private String superPoder;
	
	

	/*Metodo para obtener Id de la clase*/
	public Long getId() {
		return id;
	}


	/*Metodo para modificar el Id de la clase*/
	public void setId(Long id) {
		this.id = id;
	}


	/*Metodo para obtener nombre de la clase*/
	public String getNombre() {
		return nombre;
	}


	/*Metodo para modificar el nombre de la clase*/
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	/*Metodo para obtener comic de la clase*/
	public Comic getComic() {
		return comic;
	}


	/*Metodo para modificar el Comic de la clase*/
	public void setComic(Comic comic) {
		this.comic = comic;
	}


	/*Metodo para obtener Estado de la clase*/
	public EstadoEnum getEstado() {
		return estado;
	}


	/*Metodo para modificar el Estadp de la clase*/
	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}


	/*Metodo para obtener Superpoder de la clase*/
	public String getSuperPoder() {
		return superPoder;
	} 


	/*Metodo para modificar el Superpoder de la clase*/
	public void setSuperPoder(String superPoder) {
		this.superPoder = superPoder;
	}
	
	
	/**
	 * @see java.lang.Object#toString() Metodo que permite asociar al objeto un
	 *      texto representativo
	 */
	@Override
	public String toString() {
		return "Personaje [id=" + id + ", nombre=" + nombre + ", estado=" + estado + ", superpoder="
				+ superPoder + ", comic=" + comic + "]";
	}

	/**
	 * @see java.lang.Object#hashCode() Este metodo viene a complementar al metodo
	 *      equals y sirve para comparar objetos de una forma mas rapida en
	 *      estructuras Hash ya que unicamente nos devuelve un numero entero. Cuando
	 *      Java compara dos objetos en estructuras de tipo hash (HashMap, HashSet
	 *      etc) primero invoca al metodo hashcode y luego el equals
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((comic == null) ? 0 : comic.hashCode());
		result = prime * result + ((superPoder == null) ? 0 : superPoder.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object) Metodo que permite comparar
	 *      objetos
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personaje other = (Personaje) obj;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (comic == null) {
			if (other.comic != null)
				return false;
		} else if (!comic.equals(other.comic))
			return false;
		if (superPoder == null) {
			if (other.superPoder != null)
				return false;
		} else if (!superPoder.equals(other.superPoder))
			return false;
		return true;
	}

}

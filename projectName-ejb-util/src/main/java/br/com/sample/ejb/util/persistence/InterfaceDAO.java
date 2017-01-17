package br.com.sample.ejb.util.persistence;

import java.util.List;

import javax.ejb.Local;

import br.com.exception.util.DataAcessException;

@Local
public interface InterfaceDAO<T, Pk> {

	void saveOrUpdate(T object) throws DataAcessException;

	void remove(T object) throws DataAcessException;

	List<T> findAll() throws DataAcessException;

	/**
	 * must add @PostConstruct before this method decl
	 * 
	 */
	void setEntityClass();
}

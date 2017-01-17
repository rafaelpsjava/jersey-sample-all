package br.com.company.projectname.ejb.persistence.api;

import java.util.List;

import br.com.company.projectname.util.exception.DataAcessException;

public interface DAO<T, Pk> {

	public List<T> findAll() throws DataAcessException;

	void remove(T entity) throws DataAcessException;

	void saveOrUpdate(T entity) throws DataAcessException;
}

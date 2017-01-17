package br.com.company.projectname.ejb.business.api;

import java.util.List;

import br.com.company.projectname.util.exception.BusinessException;

public interface Business<T, Pk> {

	public List<T> findAll() throws BusinessException;

	void remove(T entity) throws BusinessException;

	void saveOrUpdate(T entity) throws BusinessException;
}

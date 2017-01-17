package br.com.sample.ejb.util.business;

import java.util.List;

import javax.ejb.Local;

import br.com.exception.util.BusinessException;

@Local
public interface InterfaceBusiness<T, Pk> {

	void saveOrUpdate(T object) throws BusinessException;

	void remove(T object) throws BusinessException;

	List<T> findAll() throws BusinessException;

}

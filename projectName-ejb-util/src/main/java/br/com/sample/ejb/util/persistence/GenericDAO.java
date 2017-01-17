package br.com.sample.ejb.util.persistence;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.EntityManager;

import br.com.exception.util.DataAcessException;

@Local
public interface GenericDAO {

	void saveOrUpdate(Object object) throws DataAcessException;

	void remove(Object object) throws DataAcessException;

	List<?> findAll() throws DataAcessException;

	EntityManager getEntityManager();

	void setClazz(Class<?> clazz);

}

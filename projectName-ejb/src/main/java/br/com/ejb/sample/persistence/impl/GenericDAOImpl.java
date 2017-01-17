package br.com.ejb.sample.persistence.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;

import br.com.exception.util.DataAcessException;
import br.com.sample.ejb.util.persistence.GenericDAO;

@Stateless(name = "ejb/GenericDAO")
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class GenericDAOImpl implements GenericDAO {

	@PersistenceContext(name = "jsPU", unitName = "jsPU")
	private EntityManager entityManager;

	private Class<?> clazz;

	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public void saveOrUpdate(Object entity) throws DataAcessException {
		try {
			validate(entity);

			if (entityManager.contains(entity)) {
				entity = entityManager.merge(entity);
			} else {
				entityManager.persist(entity);
			}
		} catch (Throwable throwable) {
			throw new DataAcessException(throwable);
		}
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public void remove(Object entity) throws DataAcessException {
		try {
			validate(entity);

			entityManager.remove(entity);
		} catch (Throwable throwable) {
			throw new DataAcessException(throwable);
		}
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<?> findAll() throws DataAcessException {
		try {
			Session session = (Session) entityManager.getDelegate();
			Criteria criteria = session.createCriteria(clazz);

			return criteria.list();
		} catch (Throwable throwable) {
			throw new DataAcessException(throwable);
		}
	}

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	private void validate(Object entity) {
		if (entity.getClass().equals(clazz)) {
			throw new IllegalArgumentException("the entity must be a instance of : " + clazz.getSimpleName());
		}
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

}
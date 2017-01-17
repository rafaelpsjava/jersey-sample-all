package br.com.ejb.sample.persistence.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;

import org.hibernate.Session;

import br.com.domain.sample.App;
import br.com.ejb.sample.persistence.AppDAO;
import br.com.exception.util.DataAcessException;
import br.com.sample.ejb.util.persistence.GenericDAO;

/**
 * 
 * @author Rafael
 *
 */
@Stateless(name = AppDAO.EJB_NAME)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class AppDAOImpl implements AppDAO {

	@EJB
	private GenericDAO genericDAO;

	@Override
	@PostConstruct
	public void setEntityClass() {
		genericDAO.setClazz(App.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<App> findAll() throws DataAcessException {
		try {
			return (List<App>) genericDAO.findAll();
		} catch (Throwable e) {
			throw new DataAcessException(e);
		}
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public void remove(App app) throws DataAcessException {
		try {
			genericDAO.remove(app);
		} catch (Throwable e) {
			throw new DataAcessException(e);
		}
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public void saveOrUpdate(App app) throws DataAcessException {
		try {
			genericDAO.saveOrUpdate(app);
		} catch (Throwable e) {
			throw new DataAcessException(e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<App> findByName(String appName) throws DataAcessException {
		try {
			EntityManager entityManager = genericDAO.getEntityManager();
			org.hibernate.Query findUserByName = ((Session) entityManager.getDelegate()).getNamedQuery("findAppByName");
			findUserByName.setParameter("name", appName);
			return findUserByName.list();
		} catch (Throwable e) {
			throw new DataAcessException(e);
		}
	}

}

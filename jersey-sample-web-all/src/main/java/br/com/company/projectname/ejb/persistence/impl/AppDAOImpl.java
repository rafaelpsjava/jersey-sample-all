package br.com.company.projectname.ejb.persistence.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import org.hibernate.Criteria;
import org.hibernate.Session;

import br.com.company.projectname.ejb.persistence.api.AppDAO;
import br.com.company.projectname.util.exception.DataAcessException;
import br.com.domain.sample.App;

/**
 * AppDAOImpl
 * 
 * @author Rafael
 *
 */
@Stateless(name = AppDAO.EJB_NAME)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class AppDAOImpl implements AppDAO {

	@PersistenceContext(name = "jsPU", unitName = "jsPU")
	private EntityManager entityManager;

	@PersistenceUnit
	private EntityManagerFactory entityManagerFatory;

	@Override
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<App> findAll() throws DataAcessException {
		try {
			Session session = (Session) entityManager.getDelegate();
			Criteria criteria = session.createCriteria(App.class);

			// try {
			// org.hibernate.Query findUserByName =
			// session.getNamedQuery("findUserByName");
			// System.out.println(findUserByName.list());
			// } catch (Throwable throwable) {
			// System.out.println(throwable.getMessage());
			// }

			List<App> apps = criteria.list();
			return apps;
		} catch (Throwable e) {
			throw new DataAcessException(e);
		}
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public void remove(App app) throws DataAcessException {
		try {
			entityManager.remove(app);
		} catch (Throwable e) {
			throw new DataAcessException(e);
		}
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public void saveOrUpdate(App app) throws DataAcessException {
		try {
			if (entityManager.contains(app)) {
				entityManager.merge(app);
			} else {
				entityManager.persist(app);
			}
		} catch (Throwable e) {
			throw new DataAcessException(e);
		}
	}

}

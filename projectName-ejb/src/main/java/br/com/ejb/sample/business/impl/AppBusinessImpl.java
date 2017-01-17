package br.com.ejb.sample.business.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import br.com.domain.sample.App;
import br.com.ejb.sample.business.AppBusiness;
import br.com.ejb.sample.persistence.AppDAO;
import br.com.exception.util.BusinessException;
import br.com.exception.util.DataAcessException;

/**
 * AppBusinessImpl
 * 
 * @author Rafael
 *
 */
@Stateless(name = AppBusiness.EJB_NAME)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class AppBusinessImpl implements AppBusiness {

	@EJB
	private AppDAO appDAO;

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void saveOrUpdate(App app) throws BusinessException {
		try {
			appDAO.saveOrUpdate(app);
		} catch (DataAcessException e) {
			throw new BusinessException(e);
		}
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remove(App app) throws BusinessException {
		try {
			appDAO.remove(app);
		} catch (DataAcessException e) {
			throw new BusinessException(e);
		}
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<App> findAll() throws BusinessException {
		try {
			return appDAO.findAll();
		} catch (DataAcessException e) {
			throw new BusinessException(e);
		}
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<App> findByName(String appName) throws BusinessException {
		try {
			return appDAO.findByName(appName);
		} catch (DataAcessException e) {
			throw new BusinessException(e);
		}
	}
}
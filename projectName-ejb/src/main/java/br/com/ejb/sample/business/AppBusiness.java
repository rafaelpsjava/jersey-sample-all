package br.com.ejb.sample.business;

import java.math.BigInteger;
import java.util.List;

import javax.ejb.Local;

import br.com.domain.sample.App;
import br.com.exception.util.BusinessException;
import br.com.sample.ejb.util.business.InterfaceBusiness;

@Local
public interface AppBusiness extends InterfaceBusiness<App, BigInteger> {

	public static final String EJB_NAME = "ejb/AppBusiness";

	List<App> findByName(String appName) throws BusinessException;

}
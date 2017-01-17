package br.com.ejb.sample.persistence;

import java.math.BigInteger;
import java.util.List;

import javax.ejb.Local;

import br.com.domain.sample.App;
import br.com.exception.util.DataAcessException;
import br.com.sample.ejb.util.persistence.InterfaceDAO;

@Local
public interface AppDAO extends InterfaceDAO<App, BigInteger> {

	public static final String EJB_NAME = "ejb/AppDAO";

	public List<App> findByName(String appName) throws DataAcessException;
}
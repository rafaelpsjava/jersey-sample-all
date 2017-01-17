package br.com.company.projectname.ejb.persistence.api;

import java.math.BigInteger;

import javax.ejb.Local;

import br.com.domain.sample.App;

@Local
public interface AppDAO extends DAO<App, BigInteger> {

	public static final String EJB_NAME = "ejb/AppDAO";

}
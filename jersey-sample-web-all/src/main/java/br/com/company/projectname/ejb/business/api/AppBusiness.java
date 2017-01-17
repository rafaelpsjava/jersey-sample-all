package br.com.company.projectname.ejb.business.api;

import java.math.BigInteger;

import javax.ejb.Local;

import br.com.domain.sample.App;

@Local
public interface AppBusiness extends Business<App, BigInteger> {

	public static final String EJB_NAME = "ejb/AppBusiness";

}
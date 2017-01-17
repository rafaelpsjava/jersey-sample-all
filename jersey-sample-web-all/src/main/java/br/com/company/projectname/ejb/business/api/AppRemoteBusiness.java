package br.com.company.projectname.ejb.business.api;

import java.math.BigInteger;

import javax.ejb.Remote;

import br.com.domain.sample.App;

@Remote
public interface AppRemoteBusiness extends Business<App, BigInteger> {

	public static final String EJB_NAME = "ejb/AppRemoteBusiness";

}
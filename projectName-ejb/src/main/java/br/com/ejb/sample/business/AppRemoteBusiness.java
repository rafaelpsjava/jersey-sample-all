package br.com.ejb.sample.business;

import java.math.BigInteger;

import javax.ejb.Remote;

import br.com.domain.sample.App;
import br.com.sample.ejb.util.business.InterfaceBusiness;

@Remote
public interface AppRemoteBusiness extends InterfaceBusiness<App, BigInteger> {

	public static final String EJB_NAME = "ejb/remoteInterface/AppRemoteBusiness";

}
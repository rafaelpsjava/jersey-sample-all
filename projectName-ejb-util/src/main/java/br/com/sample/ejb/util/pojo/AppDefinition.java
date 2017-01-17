package br.com.sample.ejb.util.pojo;

public class AppDefinition {
	private String applicationName;

	private AppType appType;

	private EjbContainer ejbServer;

	private Enviroment enviroment;

	public String getApplicationName() {
		if (applicationName == null) {
			throw new IllegalArgumentException("applicationName cannot be null!");
		}
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public AppType getAppType() {
		if (appType == null) {
			throw new IllegalArgumentException("appType cannot be null!");
		}
		return appType;
	}

	public void setAppType(AppType appType) {
		this.appType = appType;
	}

	public EjbContainer getEjbServer() {
		if (ejbServer == null) {
			throw new IllegalArgumentException("ejbServer cannot be null!");
		}
		return ejbServer;
	}

	public void setEjbServer(EjbContainer ejbServer) {
		this.ejbServer = ejbServer;
	}

	public Enviroment getEnviroment() {
		if (enviroment == null) {
			throw new IllegalArgumentException("enviroment cannot be null!");
		}

		return enviroment;
	}

	public void setEnviroment(Enviroment enviroment) {
		this.enviroment = enviroment;
	}

}

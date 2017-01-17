package br.com.sample.ejb.util.pojo;

public class EjbContainer {

	private JEEServer jeeServer;

	private String path;

	public EjbContainer() {
	}

	public JEEServer getJeeServer() {
		if (this.jeeServer == null) {
			throw new IllegalArgumentException("jeeServer cannot be null!");
		}
		return jeeServer;
	}

	public void setJeeServer(JEEServer jeeServerName) {
		this.jeeServer = jeeServerName;
	}

	public String getPath() {
		if (this.path == null) {
			throw new IllegalArgumentException("path cannot be null!");
		}
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}

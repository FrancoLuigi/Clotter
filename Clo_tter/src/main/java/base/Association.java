package base;

public class Association {
	public Association(String idClone, String idCommit, String version) {

		this.idClone = idClone;
		this.idCommit = idCommit;
		this.version=version;

	}

	
	public String getVersion() {
		return version;
	}


	public void setVersion(String version) {
		this.version = version;
	}


	@Override
	public String toString() {
		return "Association [idClone=" + idClone + ", idCommit=" + idCommit + "]";
	}

	public String getIdClone() {
		return idClone;
	}

	public void setIdClone(String idClone) {
		this.idClone = idClone;
	}

	public String getIdCommit() {
		return idCommit;
	}

	public void setIdCommit(String idCommit) {
		this.idCommit = idCommit;
	}

	private String idClone, idCommit;
	private String version;

}

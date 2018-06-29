package baseClass;

public class Association {
	public Association(String idClone, String idCommit) {

		this.idClone = idClone;
		this.idCommit = idCommit;

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

}

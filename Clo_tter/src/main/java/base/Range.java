package base;

public class Range {
	public Range(String id, int riga, int intervallo) {

		this.id = id;

		this.riga = riga;
		this.intervallo = intervallo;
		change = null;

	}

	public Change getChange() {
		return change;
	}

	public void setChange(Change change) {
		this.change = change;
	}

	@Override
	public String toString() {
		return "Range [id=" + id + ", riga=" + riga + ", intervallo=" + intervallo + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getRiga() {
		return riga;
	}

	public void setRiga(int riga) {
		this.riga = riga;
	}

	public int getIntervallo() {
		return intervallo;
	}

	public void setIntervallo(int intervallo) {
		this.intervallo = intervallo;
	}

	private String id;
	private int riga, intervallo;
	private Change change;

}

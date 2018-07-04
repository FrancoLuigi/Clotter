package baseClass;

public class ClassClone {

	public ClassClone(String id, int clones, int lines, int similarity, String version) {
		this.id = id;
		this.clones = clones;
		this.lines = lines;
		this.similarity = similarity;
		this.version=version;

	}

	
	public String getVersion() {
		return version;
	}


	public void setVersion(String version) {
		this.version = version;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getClones() {
		return clones;
	}

	public void setClones(int clones) {
		this.clones = clones;
	}

	public int getLines() {
		return lines;
	}

	public void setLines(int lines) {
		this.lines = lines;
	}

	public int getSimilarity() {
		return similarity;
	}

	public void setSimilarity(int similarity) {
		this.similarity = similarity;
	}

	private String id, version;
	private int clones, lines, similarity;
}

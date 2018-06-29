package baseClass;

public class ClassClone {

	public ClassClone(String id, int clones, int lines, int similarity) {
		this.id = id;
		this.clones = clones;
		this.lines = lines;
		this.similarity = similarity;

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

	private String id;
	private int clones, lines, similarity;
}

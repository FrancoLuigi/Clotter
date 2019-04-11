package base;

import java.util.HashMap;

public class ClassClone {

	public ClassClone(String id, int nclones, int lines, int similarity, String version) {
		this.id = id;
		this.nclones = nclones;
		this.lines = lines;
		this.similarity = similarity;
		this.version=version;
		
		clones = new HashMap<String, Clone>();
	}

	
	public String getVersion() {
		return version;
	}


	public void setVersion(String version) {
		this.version = version;
	}

	
	public HashMap<String, Clone> getClones() {
		return clones;
	}


	public void addClone(Clone c) {
		clones.put(c.getPcid() + c.getVersion(), c);
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getNClones() {
		return nclones;
	}

	public void setClones(int nclones) {
		this.nclones = nclones;
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

	@Override
	public String toString() {
		return "Class ID: " + id + ", #Clones: " + nclones + ", Lines: " + lines + ", Similarity: " + similarity;
	}


	private String id, version;
	private int nclones, lines, similarity;
	private HashMap<String, Clone> clones;
}

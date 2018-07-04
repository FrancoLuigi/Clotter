package baseClass;

import java.util.HashMap;

public class Clone {

	public Clone(String file, int startLine, int endLine, String pcid, String classid, String version) {
		this.file = file;
		this.startLine = startLine;
		this.endLine = endLine;
		this.pcid = pcid;
		this.classid = classid;
		this.version=version;

		commits = new HashMap<String, Commit>();

	}

	@Override
	public String toString() {
		return "Id: " + pcid + " File: " + file + ", Class id: " + classid + ", StartLine: " + startLine + ", EndLine: "
				+ endLine;
	}

	public HashMap<String, Commit> getCommits() {
		return commits;
	}

	public void setCommits(HashMap<String, Commit> commits) {
		this.commits = commits;
	}

	public void addCommit(Commit c) {
		commits.put(c.getId(), c);
	}

	public String getClassid() {
		return classid;
	}

	public void setClassid(String classid) {
		this.classid = classid;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getPcid() {
		return pcid;
	}

	public void setPcid(String pcid) {
		this.pcid = pcid;
	}

	public int getStartLine() {
		return startLine;
	}

	public void setStartLine(int startLine) {
		this.startLine = startLine;
	}

	public int getEndLine() {
		return endLine;
	}

	public void setEndLine(int endLine) {
		this.endLine = endLine;
	}
	

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}


	private String file, pcid, classid, version;
	private int startLine, endLine;
	private HashMap<String, Commit> commits;

}

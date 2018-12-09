package base;

import java.util.ArrayList;

public class Change {

	public Change(String id, String idCommit, String file, ArrayList<Range> ranges) {
		this.id = id;
		this.idCommit = idCommit;
		this.file = file;
		this.ranges = ranges;

	}

	@Override
	public String toString() {
		return "Change [file=" + file + ", idCommit=" + idCommit + ", id=" + id + ", ranges=" + ranges + "]";
	}

	public ArrayList<Range> getRanges() {
		return ranges;
	}

	public void setRanges(ArrayList<Range> ranges) {
		this.ranges = ranges;
	}

	public String getIdCommit() {
		return idCommit;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setIdCommit(String id) {
		this.idCommit = id;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	private String file, idCommit, id;
	private ArrayList<Range> ranges;

}

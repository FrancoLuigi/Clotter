package management;

import java.util.HashMap;
import java.util.Scanner;

import baseClass.Change;
import baseClass.Commit;
import baseClass.Committer;
import baseClass.File1;
import baseClass.Range;

public class GestoreCommits {
	public GestoreCommits(Scanner sc, int version) {
		commits = new HashMap<String, Commit>();
		files = new HashMap<String, File1>();
		committers = new HashMap<Integer, Committer>();
		changes = new HashMap<String, Change>();
		ranges = new HashMap<String, Range>();

		Commit c = Commit.read(sc);

		while (c != null) {
	
			if (!c.getChanges().isEmpty()) {
				
			
				c.setVersion(version);
				commits.put(c.getId(), c);

				committers.put(c.getCommitter().getEmail(), c.getCommitter());

				for (Change change : c.getChanges().values()) {

					
					changes.put(change.getId(), change);

					
					files.put(change.getFile(), new File1(change.getFile()));

					for (Range r : change.getRanges()) {

						ranges.put(r.getId(), r);
					}

				}
			}
			
			c = Commit.read(sc);

		}

		System.err.println("Commits read");

	}

	public HashMap<String, Change> getChanges() {
		return changes;
	}

	public void setChanges(HashMap<String, Change> changes) {
		this.changes = changes;
	}

	public HashMap<String, Range> getRanges() {
		return ranges;
	}

	public void setRanges(HashMap<String, Range> ranges) {
		this.ranges = ranges;
	}

	public HashMap<Integer, Committer> getCommitters() {
		return committers;
	}

	public HashMap<Integer, Committer> getCommitter() {
		return committers;
	}

	public void setCommitters(HashMap<Integer, Committer> committers) {
		this.committers = committers;
	}

	public void setCommits(HashMap<String, Commit> commits) {
		this.commits = commits;
	}

	public void setFiles(HashMap<String, File1> files) {
		this.files = files;
	}

	public Commit getCommit(String id) {
		Commit c = commits.get(id);
		return c;
	}

	public HashMap<String, File1> getFiles() {
		return this.files;
	}

	public HashMap<String, Commit> getCommits() {
		return this.commits;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((changes == null) ? 0 : changes.hashCode());
		result = prime * result + ((commits == null) ? 0 : commits.hashCode());
		result = prime * result + ((committers == null) ? 0 : committers.hashCode());
		result = prime * result + ((files == null) ? 0 : files.hashCode());
		result = prime * result + ((ranges == null) ? 0 : ranges.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GestoreCommits other = (GestoreCommits) obj;
		if (changes == null) {
			if (other.changes != null)
				return false;
		} else if (!changes.equals(other.changes))
			return false;
		if (commits == null) {
			if (other.commits != null)
				return false;
		} else if (!commits.equals(other.commits))
			return false;
		if (committers == null) {
			if (other.committers != null)
				return false;
		} else if (!committers.equals(other.committers))
			return false;
		if (files == null) {
			if (other.files != null)
				return false;
		} else if (!files.equals(other.files))
			return false;
		if (ranges == null) {
			if (other.ranges != null)
				return false;
		} else if (!ranges.equals(other.ranges))
			return false;
		return true;
	}

	private HashMap<String, Commit> commits;
	private HashMap<String, File1> files;
	private HashMap<Integer, Committer> committers;
	private HashMap<String, Change> changes;
	private HashMap<String, Range> ranges;

}

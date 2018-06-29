package management;

import java.util.HashMap;

import baseClass.Association;
import baseClass.Change;
import baseClass.Clone;
import baseClass.Commit;
import baseClass.Range;

public class GestoreAssociation {
	public GestoreAssociation(HashMap<String, Commit> commits, HashMap<String, Clone> clones) {
		this.commits = commits;
		this.clones = clones;

	}

	public HashMap<String, Association> getAssociation() {

		HashMap<String, Association> associations = new HashMap<String, Association>();

		for (Commit c : commits.values()) {
			for (Change ch : c.getChanges().values()) {

				for (Range r : ch.getRanges()) {

					for (Clone cl : clones.values()) {

						if (r.getRiga() <= cl.getStartLine() && (r.getRiga() + r.getIntervallo()) >= cl.getEndLine()
								&& (r.getChange().getFile().equals(cl.getFile()))) {
							// System.out.println(r);
							// System.out.println(cl);
							// System.out.println(r.getRiga());
							// System.out.println(r.getRiga()+r.getIntervallo()+"-"+cl.getEndLine());

							Association a = new Association(cl.getPcid(), c.getId());

							associations.put(cl.getPcid() + c.getId(), a);
							// System.out.println(cl.getPcid()+c.getId());
							// System.out.println("---");
						}

						if (cl.getStartLine() <= r.getRiga() && cl.getEndLine() >= r.getRiga() + r.getIntervallo()
								&& r.getChange().getFile().equals(cl.getFile())) {

						}
					}
				}
			}
		}

		return associations;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clones == null) ? 0 : clones.hashCode());
		result = prime * result + ((commits == null) ? 0 : commits.hashCode());
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
		GestoreAssociation other = (GestoreAssociation) obj;
		if (clones == null) {
			if (other.clones != null)
				return false;
		} else if (!clones.equals(other.clones))
			return false;
		if (commits == null) {
			if (other.commits != null)
				return false;
		} else if (!commits.equals(other.commits))
			return false;
		return true;
	}

	private HashMap<String, Commit> commits;
	private HashMap<String, Clone> clones;

}

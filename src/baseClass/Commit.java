package baseClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class Commit {
	public Commit(String id, Date data, String descrizione, String version, Committer committer, HashMap<String, Change> changes) {
		this.id = id;
		this.data = data;
		this.descrizione = descrizione;
		this.committer = committer;
		this.changes = changes;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Committer getCommitter() {
		return committer;
	}

	public HashMap<String, Change> getChanges() {
		return changes;
	}

	public void setChanges(HashMap<String, Change> changes) {
		this.changes = changes;
	}

	public void setCommitter(Committer committer) {
		this.committer = committer;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public static Commit read(Scanner sc) {

		String id;
		if (!sc.hasNextLine()) {
			return null;
		}

		id = sc.nextLine();

		if (id.contains("Commit:"))
			id = sc.nextLine();

//	System.out.println(id);
	
		String autore;
		if (!sc.hasNextLine()) {
			return null;
		}
		autore = sc.nextLine();

		String email;
		if (!sc.hasNextLine()) {
			return null;
		}
		email = sc.nextLine();

		int em = Math.abs(email.hashCode());
		Committer committer = new Committer(autore, Integer.toString(em));

		// System.out.println(autore);

		if (!sc.hasNextLine())
			return null;

		String data = sc.nextLine();

		SimpleDateFormat sd = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
		Date date;
		try {
			date = sd.parse(data);
		} catch (ParseException e) {
			System.err.println("Catturata un'eccezione nella lettura della data " + data);
			System.err.println("Alla data verra assegnato il valore della data odierna;");
			date = new Date();
		}

		// System.out.println(data);

		String descrizione;
		if (!sc.hasNextLine()) {
			return null;
		}
		descrizione = sc.nextLine();
		if (descrizione.length() > 99)
			descrizione = descrizione.substring(0, 99);
		// System.out.println(descrizione);

		int l = descrizione.length();
		while (descrizione.contains(",") || descrizione.contains("'")) {
			l--;
			descrizione = descrizione.substring(0, l);
		}

		String file;

		
		boolean exit = false;

		HashMap<String, Change> changes = new HashMap<String, Change>();
		ArrayList<Range> ranges = new ArrayList<Range>();
		while (sc.hasNextLine() && !exit) {
			file = sc.nextLine();
			//System.out.println(file);
			
		
			if(file.contains("Commit:")) {
				exit=true;
				
			}
		
			if (file.contains("++ b")&&file.contains(".java")) {
				file = file.substring(6);
				
				
				Change change = new Change(id + file, id, file, ranges);

				String a1;
				
				boolean exit1 = false;
				while (sc.hasNextLine() && !exit1) {

					a1 = sc.nextLine();

			//		System.out.println(a1);
					
					if (a1.contains("@@ ")) {
						
						int g = 0;
						String f = a1.substring(2, a1.length());

						int y = f.length();

						while (f.contains("@")) {

							f = f.substring(0, y);
							y--;

						}

						String hh = f.substring(0, f.length());
						while (f.contains("+")) {

							f = hh.substring(g);

							g++;

						}

						int i = f.length();
						String b = f.substring(0, f.length());

						while (b.contains(",") || b.contains(" ")) {

							b = f.substring(0, i);
							i--;

						}

					

						int z = f.length();

						String h = f.substring(0, f.length());

						while (h.contains("@")) {
							z--;
							h = f.substring(0, z);
						}

					
						int j = 0;

						String c = h.substring(0, h.length());
						
						while (c.contains(",")) {
							j++;
							c = h.substring(j);
						}

						int riga = Integer.parseInt(b);

						if (c.contains(" "))
							c = c.substring(0, c.length() - 1);

						int intervallo = Integer.parseInt(c);

						
						Range r = new Range(id + file + riga, riga, intervallo);

						
						r.setChange(change);
						ranges.add(r);

						change.setRanges(ranges);
						
						

					}

					else if (a1.contains("Commit:")) {
						exit = true;
						exit1 = true;
					}

					else if (a1.contains("---")) {
						exit1 = true;
					}

				}
			//	 System.out.println(riga);
				// System.out.println(intervallo);
				// System.out.println("----------");

				changes.put(id + file, change);
			}

			else if (file.contains("Commit:")) {
				exit = true;

			}

		}
		 //System.out.println("----------");
		return new Commit(id, date, descrizione, "0",committer, changes);
	}

	private String id, descrizione, version;

	@Override
	public String toString() {
		return "Commit [id=" + id + ", descrizione=" + descrizione + ", committer=" + committer + ", data=" + data
				+ ", changes=" + changes.values() + "]";
	}

	private Committer committer;
	private Date data;
	
	private HashMap<String, Change> changes;

}

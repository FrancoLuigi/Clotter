package management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import baseClass.Association;
import baseClass.Change;
import baseClass.ClassClone;
import baseClass.Clone;
import baseClass.Commit;
import baseClass.Committer;
import baseClass.File1;
import baseClass.Range;

public class GestoreDB {
	public static GestoreDB getInstance() {
		if (singleGestoreDB == null) {
			singleGestoreDB = new GestoreDB();
		}
		return singleGestoreDB;
	}

	
	public boolean connectDB(String host, String port, String user, String pass) {

		String url = "jdbc:mysql://" + host + ":" + port + "/sys?useSSL=false";
		try {
			this.connection = DriverManager.getConnection(url, user, pass);
			System.err.println("Connection database established");
		} catch (SQLException e) {
			this.connection = null;
			System.err.println("Connection database not established");
			return false;
		}
		return true;
	}


	public void createDatabase(String name) {
		try {
			Statement myStmt = connection.createStatement();
	
			String sql = "CREATE DATABASE IF NOT EXISTS "+name+" DEFAULT CHARSET=utf8; ";

	
			myStmt.executeUpdate(sql);

			System.err.println("Database created");
		} catch (Exception exc) {
			exc.printStackTrace();

		}
	}

	public void createTableCommitters(String name) {
		try {

			Statement myStmt = connection.createStatement();

			String sql1 = "USE "+name+" ;";
		

			String sql3 = "CREATE TABLE IF NOT EXISTS committers (" + "  nome varchar(50) NOT NULL, "
					+ "  email int(50) NOT NULL, " +

					"  PRIMARY KEY (email)" +

					") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

			myStmt.executeUpdate(sql1);
		
			myStmt.executeUpdate(sql3);

			System.err.println("Table Committers created");
		} catch (Exception exc) {
			exc.printStackTrace();

		}
	}

	public void createTableAssociations(String name) {
		try {

			Statement myStmt = connection.createStatement();
			String sql1 = "USE "+name+" ;";
			

			String sql3 = "CREATE TABLE IF NOT EXISTS associations (" + "  idClone varchar(15) NOT NULL, "
					+ "  idCommit varchar(50) NOT NULL, " +

					"  PRIMARY KEY (idclone,idcommit)" +

					") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

			myStmt.executeUpdate(sql1);
			
			myStmt.executeUpdate(sql3);

			System.err.println("Table Associations created");
		} catch (Exception exc) {
			exc.printStackTrace();

		}
	}

	public void createTableFiles(String name) {
		try {

			Statement myStmt = connection.createStatement();

			String sql1 = "USE "+name+" ;";
		

			String sql3 = "CREATE TABLE IF NOT EXISTS  files ("
					+ "  file varchar(250) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL, " +

					"  PRIMARY KEY (file)" +

					") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

			myStmt.executeUpdate(sql1);
		
			myStmt.executeUpdate(sql3);

			System.err.println("Table Files created");
		} catch (Exception exc) {
			exc.printStackTrace();

		}
	}

	public void createTableCommits(String name) {
		try {

			Statement myStmt = connection.createStatement();
			String sql1 = "USE "+name+" ;";
	

			String sql3 = "CREATE TABLE IF NOT EXISTS  commits (" + "  id varchar(50) NOT NULL, "
					+ "  email int(50) NOT NULL, " + "  data DATETIME NOT NULL," + " descrizione varchar(100)," + "  version int(50) NOT NULL, " +

					"  PRIMARY KEY (id)," + " FOREIGN KEY (email) REFERENCES committers(email)"
					+ " ON DELETE NO ACTION ON UPDATE NO ACTION" + ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

			myStmt.executeUpdate(sql1);
		
			myStmt.executeUpdate(sql3);

			System.err.println("Table Commits created");
		} catch (Exception exc) {
			exc.printStackTrace();

		}
	}

	public void createTableRanges(String name) {
		try {

			Statement myStmt = connection.createStatement();

			String sql1 = "USE "+name+" ;";
		

			String sql3 = "CREATE TABLE IF NOT EXISTS ranges (" + "  id varchar(200) NOT NULL, "
					+ "  idchange varchar(250) NOT NULL, " + "  riga int(15) NOT NULL," + " intervallo int (15)," +

					"  PRIMARY KEY (id)," + " FOREIGN KEY (idchange) REFERENCES changes(id)" +

					") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

			myStmt.executeUpdate(sql1);
		
			myStmt.executeUpdate(sql3);

			System.err.println("Table Ranges created");
		} catch (Exception exc) {
			exc.printStackTrace();

		}
	}

	public void createTableClones(String name) {
		try {

			Statement myStmt = connection.createStatement();

			String sql1 = "USE "+name+" ;";
		
			String sql3 = "CREATE TABLE IF NOT EXISTS clones ("
					+ "  file varchar(250)CHARACTER SET utf8 COLLATE utf8_bin NOT NULL, "
					+ "  startline int(15) NOT NULL, " + "  endline int(15) NOT NULL," + "  pcid varchar(15),"
					+ "  classid varchar(15)," + "  version int(50) NOT NULL, " +

					"  PRIMARY KEY (pcid)," + " FOREIGN KEY (file) REFERENCES files(file),"
					+ " FOREIGN KEY (classid) REFERENCES classclone(id)" + ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

			myStmt.executeUpdate(sql1);
	
			myStmt.executeUpdate(sql3);

			System.err.println("Table Clones created");
		} catch (Exception exc) {
			exc.printStackTrace();

		}
	}

	public void createTableClassClones(String name) {
		try {

			Statement myStmt = connection.createStatement();

			String sql1 = "USE "+name+" ;";
		
			String sql3 = "CREATE TABLE IF NOT EXISTS classclone (" + "  id varchar(15) NOT NULL, "
					+ "  clones int(15) NOT NULL, " + "  righe int(15) NOT NULL," + "  similarity int(15)," +

					"  PRIMARY KEY (id)" +

					") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

			myStmt.executeUpdate(sql1);
		
			myStmt.executeUpdate(sql3);

			System.err.println("Table ClassClone created");
		} catch (Exception exc) {
			exc.printStackTrace();

		}
	}

	public void createTableChanges(String name) {
		try {

			Statement myStmt = connection.createStatement();

			String sql1 = "USE "+name+" ;";
		
			String sql3 = "CREATE TABLE IF NOT EXISTS changes (" + "  id varchar(250) NOT NULL, "
					+ "  idcommit varchar(50) NOT NULL, "
					+ "  file varchar(250) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL," +

					"  PRIMARY KEY (id)," + " FOREIGN KEY (idcommit) REFERENCES commits(id),"
					+ " FOREIGN KEY (file) REFERENCES files(file)" + ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

			myStmt.executeUpdate(sql1);
		
			myStmt.executeUpdate(sql3);

			System.err.println("Table Changes created");
		} catch (Exception exc) {
			exc.printStackTrace();

		}
	}

	public void insertCommit(Commit c) {
		try {
			Statement myStmt = connection.createStatement();

			SimpleDateFormat sd = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
		
			String sql = "insert ignore into commits " + " (id,email, data, descrizione,version)" + " values ('" + c.getId()
					+ "', '" + c.getCommitter().getEmail() + "', '" + sd.format(c.getData()) + "', '"
					+ c.getDescrizione() + "', '" + c.getVersion() + "')+" + "ONLY IF there are no rows where id=" + c.getId();

			myStmt.executeUpdate(sql);

		}

		catch (Exception exc) {
			exc.printStackTrace();

		}
	}

	public void insertCommits(HashMap<String, Commit> commits) {
		try {
			Statement myStmt = connection.createStatement();

			SimpleDateFormat sd = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
		
			for (Commit c : commits.values()) {

				String sql = "insert ignore into commits " + " (id, email, data, descrizione, version)" + " values ('"
						+ c.getId() + "', '" + c.getCommitter().getEmail() + "', '" + sd.format(c.getData()) + "', '"
						+ c.getDescrizione() + "', '" + c.getVersion() + "')";

				myStmt.executeUpdate(sql);
			}
		}

		catch (Exception exc) {
			exc.printStackTrace();

		}

		System.err.println("Commits saved");
	}

	public void insertFiles(HashMap<String, File1> files) {
		try {
			Statement myStmt = connection.createStatement();
			
			for (File1 f : files.values()) {

				String sql = "insert ignore into files " + " (file)" + " values ('" + f.getNome() + "')";

				
				myStmt.executeUpdate(sql);
			}
		}

		catch (Exception exc) {
			exc.printStackTrace();

		}

		System.err.println("Files saved");
	}

	public void insertClone(Clone c) {
		try {
			Statement myStmt = connection.createStatement();

			String sql = "insert ignore into clones " + " (file, startLine, endLine, pcid, classid)" + " values ('"
					+ c.getFile() + "', '" + c.getStartLine() + "', '" + c.getEndLine() + "', '" + c.getPcid() + "', '"
					+ c.getClassid() + "')";

			myStmt.executeUpdate(sql);

		}

		catch (Exception exc) {
			exc.printStackTrace();

		}
	}

	public void insertClones(HashMap<String, Clone> clones) {
		try {
			Statement myStmt = connection.createStatement();


			for (Clone c : clones.values()) {

				
				

				String sql = "insert  ignore into clones " + " (file, startLine, endLine, pcid, classid, version)" + " values ('"
						+ c.getFile() + "', '" + c.getStartLine() + "', '" + c.getEndLine() + "', '" + c.getPcid()
						+ "', '" + c.getClassid() + "', '" + c.getVersion() + "')";

				myStmt.executeUpdate(sql);
			}
		}

		catch (Exception exc) {
			exc.printStackTrace();

		}

		System.err.println("Clones saved");
	}

	public void insertClassClone(HashMap<String, ClassClone> classClone) {
		try {
			Statement myStmt = connection.createStatement();

			for (ClassClone c : classClone.values()) {

				String sql = "insert ignore into classclone " + " (id, clones,righe,similarity)" + " values ('"
						+ c.getId() + "', '" + c.getClones() + "', '" + c.getLines() + "', '" + c.getSimilarity()
						+ "')";

				myStmt.executeUpdate(sql);
			}
		}

		catch (Exception exc) {
			exc.printStackTrace();

		}

		System.err.println("ClassClone saved");
	}

	public void insertRanges(HashMap<String, Range> ranges) {
		try {
			Statement myStmt = connection.createStatement();

			for (Range r : ranges.values()) {

				String sql = "insert ignore into ranges " + " (id, idchange, riga, intervallo)" + " values ('"
						+ r.getId() + "', '" + r.getChange().getId() + "', '" + r.getRiga() + "', '" + r.getIntervallo()
						+ "')";

				myStmt.executeUpdate(sql);
			}
		}

		catch (Exception exc) {
			exc.printStackTrace();

		}

		System.err.println("Ranges saved");
	}

	public void insertChanges(HashMap<String, Change> changes) {
		try {
			Statement myStmt = connection.createStatement();

			for (Change c : changes.values()) {

				String sql = "insert ignore into changes " + " (id, idcommit, file)" + " values ('" + c.getId() + "', '"
						+ c.getIdCommit() + "', '" + c.getFile() + "')";

				myStmt.executeUpdate(sql);
			}
		}

		catch (Exception exc) {
			exc.printStackTrace();

		}

		System.err.println("Changes saved");
	}

	public void insertCommitters(HashMap<Integer, Committer> committers) {
		try {
			Statement myStmt = connection.createStatement();

			for (Committer c : committers.values()) {

				String sql = "insert  ignore into committers " + " (nome, email)" + " values ('" + c.getNome() + "', '"
						+ c.getEmail() + "')";

				myStmt.executeUpdate(sql);
			}
		}

		catch (Exception exc) {
			exc.printStackTrace();

		}

		System.err.println("Committers saved");
	}

	public void insertAssociations(HashMap<String, Association> associations) {
		try {
			Statement myStmt = connection.createStatement();

			for (Association a : associations.values()) {

				String sql = "insert ignore into associations " + " (idclone, idcommit)" + " values ('" + a.getIdClone()
						+ "', '" + a.getIdCommit() + "')";

				myStmt.executeUpdate(sql);
			}
		}

		catch (Exception exc) {
			exc.printStackTrace();

		}

		System.err.println("Associations saved");
	}

	public HashMap<String, Clone> readAssociationsClones(String email) {
	

		Clone cl;

		HashMap<String, Clone> clones = new HashMap<String, Clone>();
		try {
			Statement myStmt = connection.createStatement();
			ResultSet myRs = myStmt.executeQuery(
					"select * from associations as a, clones as cl, commits as co, committers as c WHERE c.email='"
							+ email + "' AND  a.idclone=cl.pcid AND a.idcommit=co.id AND co.email=c.email;");

	

			while (myRs.next()) {
				if ((myRs.getString("id")) != null) {

					cl = new Clone(myRs.getString("file"), myRs.getInt("startLine"), myRs.getInt("endLine"),
							myRs.getString("pcid"), myRs.getString("classid"), myRs.getInt("version"));

					clones.put(cl.getPcid(), cl);
				} else
					System.out.println("---------- Errore nella lettura dei cloni" + myRs.getRow());
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}

		return clones;
	}

	public HashMap<String, Commit> readAssociationsCommits(String idClone) {
	

		Commit c;
		Committer committer;

		HashMap<String, Commit> commits = new HashMap<String, Commit>();
		try {
			Statement myStmt = connection.createStatement();
			ResultSet myRs = myStmt.executeQuery(
					"select * from associations as a, clones as cl, commits as co, committers as c WHERE a.idclone='"
							+ idClone + "' AND  a.idclone=cl.pcid AND a.idcommit=co.id AND co.email=c.email;");

			SimpleDateFormat sd = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");

			while (myRs.next()) {
				if ((myRs.getString("idClone")) != null) {

					committer = new Committer(myRs.getString("nome"), myRs.getInt("email"));
					c = new Commit(myRs.getString("id"), sd.parse(myRs.getString("data")),
							myRs.getString("descrizione"),  myRs.getInt("version"),committer, null);

					
					commits.put(c.getId(), c);

				} else
					System.out.println("---------- Errore nella lettura dei cloni" + myRs.getRow());
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}

		
		return commits;
	}

	public HashMap<Integer, Committer> readAssociationsCommitters() {

		Committer committer;

		HashMap<Integer, Committer> committers = new HashMap<Integer, Committer>();
		try {
			Statement myStmt = connection.createStatement();
			ResultSet myRs = myStmt.executeQuery(
					"select * from associations as a, clones as cl, commits as co, committers as c WHERE a.idclone=cl.pcid AND a.idcommit=co.id AND co.email=c.email;");

			

			while (myRs.next()) {
				if ((myRs.getString("id")) != null) {

					committer = new Committer(myRs.getString("nome"), myRs.getInt("email"));

					committers.put(committer.getEmail(), committer);

				} else
					System.out.println("---------- Errore nella lettura dei cloni" + myRs.getRow());
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}

		return committers;
	}
	public ArrayList<Clone> readAssociationsClonesCommits() {

		Clone c;

		Commit co;
		Committer committer;

		ArrayList<Clone> clones = new ArrayList<Clone>();
		SimpleDateFormat sd = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
		try {
			Statement myStmt = connection.createStatement();
			ResultSet myRs = myStmt.executeQuery(
					"select * from associations as a, clones as cl, commits as co, committers as c WHERE a.idclone=cl.pcid AND a.idcommit=co.id AND co.email=c.email;");

		

			while (myRs.next()) {
				if ((myRs.getString("id")) != null) {

					c = new Clone(myRs.getString("file"), myRs.getInt("startLine"), myRs.getInt("endLine"),
							myRs.getString("pcid"), myRs.getString("classid"), myRs.getInt("version"));
					committer = new Committer(myRs.getString("nome"), myRs.getInt("email"));
					co = new Commit(myRs.getString("id"), sd.parse(myRs.getString("data")),
							myRs.getString("descrizione"), myRs.getInt("version"), committer, null);

					
				

					c.addCommit(co);
					clones.add(c);
				} else
					System.out.println("---------- Errore nella lettura dei cloni" + myRs.getRow());
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}

		
		return clones;
	}
	
	public ArrayList<Committer> readAssociationsCommittersClones() {

		Clone c;

		
		Committer committer;
		ArrayList<Committer>committers=new ArrayList<Committer>();
	
	
		try {
			Statement myStmt = connection.createStatement();
			ResultSet myRs = myStmt.executeQuery(
					"select * from associations as a, clones as cl, commits as co, committers as c WHERE a.idclone=cl.pcid AND a.idcommit=co.id AND co.email=c.email;");

		

			while (myRs.next()) {
				if ((myRs.getString("email")) != null) {

					c = new Clone(myRs.getString("file"), myRs.getInt("startLine"), myRs.getInt("endLine"),
							myRs.getString("pcid"), myRs.getString("classid"), myRs.getInt("version"));
					committer = new Committer(myRs.getString("nome"), myRs.getInt("email"));
					committer.addClone(c);
				
					
					committers.add( committer);
					

				
					
				} else
					System.out.println("---------- Errore nella lettura dei commits" + myRs.getRow());
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}

		
		return committers;
	}

	public HashMap<String, Clone> readAssociationsClones() {

		Clone c;

		HashMap<String, Clone> clones = new HashMap<String, Clone>();
		try {
			Statement myStmt = connection.createStatement();
			ResultSet myRs = myStmt.executeQuery(
					"select * from associations as a, clones as cl, commits as co, committers as c WHERE a.idclone=cl.pcid AND a.idcommit=co.id AND co.email=c.email;");

		

			while (myRs.next()) {
				if ((myRs.getString("id")) != null) {

					c = new Clone(myRs.getString("file"), myRs.getInt("startLine"), myRs.getInt("endLine"),
							myRs.getString("pcid"), myRs.getString("classid"), myRs.getInt("version"));

					clones.put(c.getPcid(), c);
				} else
					System.out.println("---------- Errore nella lettura dei cloni" + myRs.getRow());
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}

		return clones;
	}
	
	public HashMap<String, Clone> readClones() {

		Clone c;

		HashMap<String, Clone> clones = new HashMap<String, Clone>();
		try {
			Statement myStmt = connection.createStatement();
			ResultSet myRs = myStmt.executeQuery(
					"select * from clones;");

		

			while (myRs.next()) {
				if ((myRs.getString("pcid")) != null) {

					c = new Clone(myRs.getString("file"), myRs.getInt("startLine"), myRs.getInt("endLine"),
							myRs.getString("pcid"), myRs.getString("classid"), myRs.getInt("version"));

					clones.put(c.getPcid(), c);
				} else
					System.out.println("---------- Errore nella lettura dei cloni" + myRs.getRow());
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}

		return clones;
	}
	
	public HashMap<String, ClassClone> readClassClones() {

		ClassClone c;

		HashMap<String, ClassClone> classclones = new HashMap<String, ClassClone>();
		try {
			Statement myStmt = connection.createStatement();
			ResultSet myRs = myStmt.executeQuery(
					"select * from classclone;");

		

			while (myRs.next()) {
				if ((myRs.getString("id")) != null) {

					c = new ClassClone(myRs.getString("id"), myRs.getInt("clones"), myRs.getInt("righe"),
							myRs.getInt("similarity"));

					classclones.put(c.getId(), c);
				} else
					System.out.println("---------- Errore nella lettura dei classicloni" + myRs.getRow());
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}

		return classclones;
	}

	public HashMap<String, Commit> readCommits() {

		Commit c;

		Committer committer;
		HashMap<String, Commit> commits = new HashMap<String, Commit>();
		try {
			Statement myStmt = connection.createStatement();
			ResultSet myRs = myStmt.executeQuery(
					"select * from commits as com, committers as c WHERE com.email=c.email;");

		

			while (myRs.next()) {
				if ((myRs.getString("id")) != null) {
					committer = new Committer(myRs.getString("nome"), myRs.getInt("email"));

					SimpleDateFormat sd = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
					c = new Commit(myRs.getString("id"), sd.parse(myRs.getString("data")),
							myRs.getString("descrizione"),  myRs.getInt("version"),committer, null);

					
					commits.put(c.getId(), c);
				} else
					System.out.println("---------- Errore nella lettura dei commits" + myRs.getRow());
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}

		return commits;
	}
	
	
	
	public HashMap<Integer, Committer> readCommitters() {

		

		Committer committer;
		HashMap<Integer, Committer> committers = new HashMap<Integer, Committer>();
		try {
			Statement myStmt = connection.createStatement();
			ResultSet myRs = myStmt.executeQuery(
					"select * from committers ;");

		

			while (myRs.next()) {
				if ((myRs.getString("email")) != null) {
					committer = new Committer(myRs.getString("nome"), myRs.getInt("email"));

					
					
					committers.put(committer.getEmail(), committer);
				} else
					System.out.println("---------- Errore nella lettura dei committers" + myRs.getRow());
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}

		return committers;
	}

	public ArrayList<Association> readAssociation() {
		Association a;
		ArrayList<Association> associations = new ArrayList<Association>();
		try {
			Statement myStmt = connection.createStatement();
			ResultSet myRs = myStmt.executeQuery("select * from associations");

			while (myRs.next()) {
				if ((myRs.getString("idClone")) != null) {
					a = new Association(myRs.getString("idClone"), myRs.getString("idCommit"));
					associations.add(a);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return associations;
	}

	public boolean checkConnectionAndReconnect() {

		try {
			Statement myStmt = connection.createStatement();
			myStmt.executeQuery("select 1");
		} catch (Exception exc) {

			if (connectDB(DBSettings.host, DBSettings.port, DBSettings.user, DBSettings.pass))
				return true;
			else
				return false;

		}

		return true;
	}

	public boolean isConnected() {

		try {
			Statement myStmt = connection.createStatement();
			myStmt.executeQuery("select 1");
		} catch (Exception exc) {
			return false;
		}

		return true;
	}

	
	public Connection connection;
	private static GestoreDB singleGestoreDB;
	

}

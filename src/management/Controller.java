package management;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import baseClass.Association;
import baseClass.Change;
import baseClass.ClassClone;
import baseClass.Clone;
import baseClass.Commit;
import baseClass.Committer;
import baseClass.File1;
import baseClass.Range;
import gui.DBConnectionSettingsFrame;
import gui.FrameAssociationClone;
import gui.FrameAssociationCommit;
import gui.FrameProgress;
import gui.FrameSelection;
import gui.FrameVisualizzaClassClones;
import gui.FrameVisualizzaCloni;
import gui.FrameVisualizzaCommits;
import gui.FrameVisualizzaCommitters;
import gui.HomeFrame;

public class Controller {

	public static Controller getInstance()
			throws ParseException, ParserConfigurationException, SAXException, IOException {
		if (singleControllerInstance == null) {
			singleControllerInstance = new Controller();
		}
		return singleControllerInstance;
	}

	private Controller() throws ParseException, ParserConfigurationException, SAXException, IOException {

		if (USE_DATABASE) {

			gestoreDB = GestoreDB.getInstance();
			DBSettings.readSettingsFromFile();
			gestoreDB.connectDB(DBSettings.host, DBSettings.port, DBSettings.user, DBSettings.pass);

			Scanner scanner = new Scanner(System.in);
			while (!gestoreDB.isConnected()) {
				System.out.println("\nInserisci i dati di configurazione per riprovare a connetterti.");
				System.out.print("Inserisci l'host (in genere: localhost): ");
				String host = scanner.nextLine();
				System.out.print("Inserisci la porta (in genere: 3306): ");
				String port = scanner.nextLine();
				System.out.print("Inserisci l'username:");
				String user = scanner.nextLine();
				System.out.print("Inserisci la password:");
				String pass = scanner.nextLine();
				DBSettings.updateSettingsInFile(host, port, user, pass);
				gestoreDB.connectDB(DBSettings.host, DBSettings.port, DBSettings.user, DBSettings.pass);
			}

			scanner.close();

		}
		
		commits=new HashMap<String,Commit>();
		clones=new HashMap<String,Clone>();
		committers=new HashMap<Integer,Committer>();
		files=new HashMap<String,File1>();
		classClone=new HashMap<String,ClassClone>();
		changes=new HashMap<String,Change>();
		ranges=new HashMap<String,Range>();
		
	}

	public void visualizzaHomeFrame() {
		HomeFrame.main(null);

	}

	public void visualizzaFrameImpostazioniDB() {
		DBConnectionSettingsFrame.main(null);
	}

	public void visualizzaProgress() {
		FrameProgress.main(null);
		// FramePrenotazione.main(null);
	}

	public void visualizzaFormAssociationCommit() {
		FrameAssociationCommit.main(null);
		// FramePrenotazione.main(null);
	}

	public void visualizzaFormAssociationClone() {
		FrameAssociationClone.main(null);
		// FramePrenotazione.main(null);
	}

	public void visualizzaFormSelection() {
		FrameSelection.main(null);
	}

	public void elaboraDati(String f) throws ParserConfigurationException, SAXException, IOException {

		if (f.equalsIgnoreCase("Dnsjava")) {
			for(int i=0;i<3;i++) {
				Scanner sc = new Scanner(new File("files/" + f+i));
				// Scanner sc1=new Scanner(new File("test"));
				GestoreCommits gestore = new GestoreCommits(sc,i);

				// GestoreCommits gestore2=new GestoreCommits(sc1);
				GestoreClones gestore1 = new GestoreClones("files/" + f +i+ ".xml",i);

				HashMap<String, Commit> commits = gestore.getCommits();

				
				HashMap<String, Clone> clones = gestore1.getClones();
			

				HashMap<String, File1> files = gestore.getFiles();
			

				HashMap<Integer, Committer> committers = gestore.getCommitter();
				HashMap<String, ClassClone> classClone = gestore1.getClassi();
				HashMap<String, Range> ranges = gestore.getRanges();
				HashMap<String, Change> changes = gestore.getChanges();
				
				


				
				this.commits.putAll(commits);
				
				this.clones.putAll(clones);
				this.files.putAll(files);
				this.committers.putAll(committers);
				this.classClone.putAll(classClone);
				this.ranges.putAll(ranges);
				this.changes.putAll(changes);

			
			}
			
			GestoreAssociation g = new GestoreAssociation(commits, clones);

			HashMap<String, Association> associations = g.getAssociation();

			gestoreDB.createDatabase(f);
			gestoreDB.createTableCommitters(f);
			gestoreDB.createTableClassClones(f);
			gestoreDB.createTableCommits(f);
			gestoreDB.createTableFiles(f);
			gestoreDB.createTableChanges(f);
			gestoreDB.createTableRanges(f);
			gestoreDB.createTableClones(f);
			gestoreDB.createTableAssociations(f);

			gestoreDB.insertFiles(files);

			gestoreDB.insertCommitters(committers);
			gestoreDB.insertClassClone(classClone);
			gestoreDB.insertCommits(commits);
			gestoreDB.insertChanges(changes);

			gestoreDB.insertClones(clones);

			gestoreDB.insertRanges(ranges);

			gestoreDB.insertAssociations(associations);

			System.err.println("Inseriment completed");


		}

		else {
			Scanner sc = new Scanner(new File("files/" + f));
			// Scanner sc1=new Scanner(new File("test"));
			GestoreCommits gestore = new GestoreCommits(sc,0);

			// GestoreCommits gestore2=new GestoreCommits(sc1);
			GestoreClones gestore1 = new GestoreClones("files/" + f + ".xml",0);

			HashMap<String, Commit> commits = gestore.getCommits();

			HashMap<String, Clone> clones = gestore1.getClones();

			HashMap<String, File1> files = gestore.getFiles();

			HashMap<Integer, Committer> committers = gestore.getCommitter();
			HashMap<String, ClassClone> classClone = gestore1.getClassi();
			HashMap<String, Range> ranges = gestore.getRanges();
			HashMap<String, Change> changes = gestore.getChanges();

			GestoreAssociation g = new GestoreAssociation(commits, clones);

			HashMap<String, Association> associations = g.getAssociation();

			gestoreDB.createDatabase(f);
			gestoreDB.createTableCommitters(f);
			gestoreDB.createTableClassClones(f);
			gestoreDB.createTableCommits(f);
			gestoreDB.createTableFiles(f);
			gestoreDB.createTableChanges(f);
			gestoreDB.createTableRanges(f);
			gestoreDB.createTableClones(f);
			gestoreDB.createTableAssociations(f);

			gestoreDB.insertFiles(files);

			gestoreDB.insertCommitters(committers);
			gestoreDB.insertClassClone(classClone);
			gestoreDB.insertCommits(commits);
			gestoreDB.insertChanges(changes);

			gestoreDB.insertClones(clones);

			gestoreDB.insertRanges(ranges);

			gestoreDB.insertAssociations(associations);

			System.err.println("Inseriment completed");
		}
		
		

	}

	public void visualizzaCloni() throws ParserConfigurationException, SAXException, IOException, ParseException {
		new FrameVisualizzaCloni();
	}

	public void visualizzaCommits() throws ParserConfigurationException, SAXException, IOException, ParseException {
		new FrameVisualizzaCommits();
	}

	public void visualizzaCommitters() throws ParserConfigurationException, SAXException, IOException, ParseException {
		new FrameVisualizzaCommitters();
	}

	public void visualizzaClassClones() throws ParserConfigurationException, SAXException, IOException, ParseException {
		new FrameVisualizzaClassClones();
	}

	public void chiudiFrame(JFrame frameToClose) {
		frameToClose.dispose();
	}

	public void visualizzaErrore(String errore) {
		JOptionPane.showMessageDialog(null, errore, "Errore", JOptionPane.ERROR_MESSAGE);
	}

	public void visualizzaNotifica(String notifica) {
		JOptionPane.showMessageDialog(null, notifica);
	}

	public boolean checkDBConnection() {
		return gestoreDB.checkConnectionAndReconnect();
	}

	public void setImpostazioniDB(String host, String port, String user, String password) {
		DBSettings.updateSettingsInFile(host, port, user, password);
		gestoreDB.connectDB(DBSettings.host, DBSettings.port, DBSettings.user, DBSettings.pass);
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public static boolean USE_DATABASE = false;
	public static boolean SETTINGS_UPDATED;

	private static Controller singleControllerInstance;
	private String file;
	private GestoreDB gestoreDB;
	private HashMap<String, Clone>clones;
	private HashMap<String, Commit>commits;
	private HashMap<Integer, Committer>committers;
	private HashMap<String, ClassClone>classClone;
	private HashMap<String, Change>changes;
	private HashMap<String, Range>ranges;
	
	private HashMap<String, File1>files;
	

	
}

package management;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import baseClass.ClassClone;
import baseClass.Clone;

public class GestoreClones {

	public GestoreClones(String nomeFile, String version) throws ParserConfigurationException, SAXException, IOException {

		cloni = new HashMap<String, Clone>();
		classi = new HashMap<String, ClassClone>();

		DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder domparser = dbfactory.newDocumentBuilder();

		Document doc = domparser.parse(new File(nomeFile));
		doc.getDocumentElement().normalize();

		NodeList nodi = doc.getElementsByTagName("clones");

		Element elemento = (Element) nodi.item(0);

		Node n1 = elemento.getElementsByTagName("systeminfo").item(0);

		String processor = n1.getAttributes().getNamedItem("processor").getNodeValue();
		system = n1.getAttributes().getNamedItem("system").getNodeValue();
		String granularity = n1.getAttributes().getNamedItem("granularity").getNodeValue();
		String threshold = n1.getAttributes().getNamedItem("threshold").getNodeValue();
		String min = n1.getAttributes().getNamedItem("minlines").getNodeValue();
		String max = n1.getAttributes().getNamedItem("maxlines").getNodeValue();

		Node n4 = elemento.getElementsByTagName("classinfo").item(0);

		String nClasses = n4.getAttributes().getNamedItem("nclasses").getNodeValue();

		System.err.println("ClassInfo: nclasses=" + nClasses);

		int nclasses = Integer.parseInt(nClasses);

		for (int i = 0; i < nclasses; i++) {
			Node n5 = elemento.getElementsByTagName("class").item(i);

			classid = n5.getAttributes().getNamedItem("classid").getNodeValue();

			String nClones = n5.getAttributes().getNamedItem("nclones").getNodeValue();

			String nLines = n5.getAttributes().getNamedItem("nlines").getNodeValue();
			String simila = n5.getAttributes().getNamedItem("similarity").getNodeValue();

			int nclones = Integer.parseInt(nClones);
			int nlines = Integer.parseInt(nLines);
			int similarity = Integer.parseInt(simila);

			ClassClone c = new ClassClone(classid, nclones, nlines, similarity,version);

			classi.put(classid+version, c);

			n = n + nclones;

			for (int j = a; j < n; j++) {

				Node n6 = elemento.getElementsByTagName("source").item(j);

				String file = n6.getAttributes().getNamedItem("file").getNodeValue().substring(10 + system.length());

				String startLine = n6.getAttributes().getNamedItem("startline").getNodeValue();
				String endLine = n6.getAttributes().getNamedItem("endline").getNodeValue();
				int startline = Integer.parseInt(startLine);
				int endline = Integer.parseInt(endLine);
				String pcid = n6.getAttributes().getNamedItem("pcid").getNodeValue();

				Clone cl = new Clone(file, startline, endline, pcid, classid,version);
				
				cloni.put(pcid+version, cl);
				
			}

			a = a + nclones;

		}
		System.err.println("Clone read");

		System.err.println(
				"Clone info: \nSystemInfo: Processor=" + processor + " System=" + system + " Granularity=" + granularity

						+ " Threshold=" + threshold + " MinLines=" + min + " MaxLines=" + max);
	}

	public HashMap<String, Clone> getClones() {
		return cloni;
	}

	public Clone getClone(String id) {
		return cloni.get(id);
	}

	public HashMap<String, ClassClone> getClassi() {
		return classi;
	}

	public void setClassi(HashMap<String, ClassClone> classi) {
		this.classi = classi;
	}

	public String getClassid() {
		return classid;
	}

	public void setClassid(String classid) {
		this.classid = classid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((classi == null) ? 0 : classi.hashCode());
		result = prime * result + ((classid == null) ? 0 : classid.hashCode());
		result = prime * result + ((cloni == null) ? 0 : cloni.hashCode());
		result = prime * result + n;
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
		GestoreClones other = (GestoreClones) obj;
		if (classi == null) {
			if (other.classi != null)
				return false;
		} else if (!classi.equals(other.classi))
			return false;
		if (classid == null) {
			if (other.classid != null)
				return false;
		} else if (!classid.equals(other.classid))
			return false;
		if (cloni == null) {
			if (other.cloni != null)
				return false;
		} else if (!cloni.equals(other.cloni))
			return false;
		if (n != other.n)
			return false;
		return true;
	}

	
	
	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}



	private HashMap<String, Clone> cloni;
	private HashMap<String, ClassClone> classi;
	private int n;
	private int a;
	private String classid;
	private String system;

}

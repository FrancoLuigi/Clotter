package baseClass;

import java.util.HashMap;

public class Committer {
	@Override
	public String toString() {
		return "Nome: " + nome + ", Email: " + email;
	}

	public Committer(String nome, int email) {
		this.nome = nome;
		this.email = email;
		clones = new HashMap<String, Clone>();

	}

	public HashMap<String, Clone> getClones() {
		return clones;
	}

	public void setClones(HashMap<String, Clone> clones) {
		this.clones = clones;
	}

	public void addClone(Clone c) {
		clones.put(c.getPcid(), c);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getEmail() {
		return email;
	}

	public void setEmail(int email) {
		this.email = email;
	}

	private String nome;
	private int email;
	private HashMap<String, Clone> clones;
}

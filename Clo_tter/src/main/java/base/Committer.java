package base;

import java.util.HashMap;

public class Committer {
	@Override
	public String toString() {
		return "Nome: " + nome + ", Email: " + email;
	}

	public Committer(String nome, String email) {
		this.nome = nome;
		this.email = email;

		clones = new HashMap<String, Clone>();
	}

	public void addClone(Clone c) {
		clones.put(c.getPcid(), c);
	}

	public HashMap<String, Clone> getClones() {
		return clones;
	}

	public void setClones(HashMap<String, Clone> clones) {
		this.clones = clones;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
	private String nome;
	private String email;
	private HashMap<String, Clone> clones;

}

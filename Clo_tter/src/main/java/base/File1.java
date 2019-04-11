package base;

import java.util.Scanner;

public class File1 {

	public File1(String file) {
		this.file = file;
	}

	public static File1 read(Scanner sc) {
		String file;

		file = sc.nextLine();

		while (!file.contains("--- a") && sc.hasNextLine()) {
			file = sc.nextLine();
		}

		if (!sc.hasNextLine()) {
			return null;
		}

		file = file.substring(6);

		return new File1(file);
	}

	public String getNome() {
		return file;
	}

	@Override
	public String toString() {
		return "File: " + file;
	}

	public void setNome(String file) {
		this.file = file;
	}

	private String file;
}

package util;

import java.util.ArrayList;
import java.util.Comparator;

public class Sorter<T> {
	public Sorter(ArrayList<T> toBeSorted, Comparator<T> comp) {
		this.toBeSorted = toBeSorted;
		this.comp = comp;
	}

	public void sort() {
		boolean ordinato = false;
		int i = 0;

		while (i < toBeSorted.size() && !ordinato) {
			ordinato = true;
			for (int j = 0; j < toBeSorted.size() - i - 1; j++) {
				if (comp.compare(toBeSorted.get(j), toBeSorted.get(j + 1)) > 0) {
					T temp = toBeSorted.get(j);
					toBeSorted.set(j, toBeSorted.get(j + 1));
					toBeSorted.set(j + 1, temp);
					ordinato = false;
				}
			}
			i++;
		}
	}

	/*
	  Comparator<ClassClone> ordineCrescente = new Comparator<ClassClone>() {
	  	public int compare(ClassClone c1, ClassClone c2) { 
	  	int a = Integer.parseInt(c1.getId()); 
	  	int b = Integer.parseInt(c2.getId()); 
	  	
	  	if (a >b)
	  		return 1;
	  	
	  	else 
	  		return 0; 
	  		} 
	  	};
	  
	  Sorter<ClassClone> s = new Sorter<ClassClone>(classclones, ordineCrescente);
	  s.sort();
	 */
	
	
	private ArrayList<T> toBeSorted;
	private Comparator<T> comp;
}

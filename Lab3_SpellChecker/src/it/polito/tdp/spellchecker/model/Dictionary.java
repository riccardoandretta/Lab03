package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.util.*;

public class Dictionary {

	private List<String> dictionary = new LinkedList<>();

	public void loadDictionary(String language) {
		
		try {
			BufferedReader b = new BufferedReader(new FileReader(language));
			String s;
			while ((s = b.readLine()) != null) {

				dictionary.add(s);

			}
			b.close();
		} catch (IOException e) {
			System.out.println("Errore nella lettura del file");
		}
	}

	public List<RichWord> spellCheckedText(List<String> inputTextList) {

		RichWord rw;
		List<RichWord> rwList = new LinkedList<>();

		for (String s : inputTextList) {
			//salvo le parole passate
			String freeS = s.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "").trim();
			rw = new RichWord(freeS, false);
			rwList.add(rw);
			
			//cerco nel dizionario
			String stemp = rw.getInputWord().toLowerCase();
			if (dictionary.contains(stemp)) {
				rw.setCorrect(true);

			}
		}
		return rwList;
	}

	public List<RichWord> spellCheckedTextDichotomic(List<String> inputTextList) {

		RichWord rw;
		List<RichWord> rwList = new LinkedList<>();

		

		for (String s : inputTextList) {
			// salvo le parole passate
			String freeS = s.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "").trim();
			rw = new RichWord(freeS, false);
			rwList.add(rw);
			
			int min = 0;
			int max = dictionary.size() - 1;
			
			// cerco nel dizionario
			while (max >= min) {
				int t = (min + max) / 2;
				if (freeS.compareToIgnoreCase(dictionary.get(t)) == 0) {
					rw.setCorrect(true);
					break;
				} else if (freeS.compareToIgnoreCase(dictionary.get(t)) > 0) {
					min = t + 1;
				} else {
					max = t - 1;
				}
			}
		}
		return rwList;
	}

	public List<String> inputToList(String s) {

		List<String> inputList = new LinkedList<>();
		String freeS = s.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");

		StringTokenizer st = new StringTokenizer(freeS, " ");
		while (st.hasMoreTokens()) {
			String parola = st.nextToken();
			inputList.add(parola);
		}

		return inputList;
	}

}

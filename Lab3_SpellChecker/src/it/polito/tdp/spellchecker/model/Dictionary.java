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
	
	
	public List<RichWord> spellCheckedText (List<String> inputTextList){
		
		RichWord rw;
		List<RichWord> rwList = new LinkedList<>();
		
		for (String s : inputTextList){
			String freeS = s.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");
			for (String stemp : dictionary){
				if (stemp.toLowerCase().compareTo(freeS.toLowerCase()) == 0){
					rw = new RichWord(stemp, true);
					rwList.add(rw);
					}
				else{
					rw = new RichWord(stemp, false);
					rwList.add(rw);
				}
			}
				
		}		
		return rwList;
		
	}

}

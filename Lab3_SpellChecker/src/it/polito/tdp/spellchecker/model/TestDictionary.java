package it.polito.tdp.spellchecker.model;

import java.util.*;

public class TestDictionary {

	public static void main(String[] args) {

		Dictionary d = new Dictionary();

		d.loadDictionary("rsc/Italian.txt");

		List<String> parole = new LinkedList<>();
		parole.add("Ciao");
		parole.add("Mamma[] ");
		parole.add("Nonno!");
		parole.add("Albero");
		parole.add("Pizza&{}");
		parole.add("qwehj");

		List<RichWord> ris = d.spellCheckedTextDichotomic(parole);

		for (RichWord rw : ris) {
			System.out.println("La parola " + rw.getInputWord() + " e' " + rw.isCorrect());
		}
		
		System.out.println("\n*******Secondo esempio*******\n");
		
		List<String> parole2 = d.inputToList("Ciao a tutti questo è un esempio di parole da cercare!");
		List<RichWord> ris2 = d.spellCheckedTextDichotomic(parole2);

		for (RichWord rw : ris2) {
			if (!rw.isCorrect()) 
			System.out.println("La parola " + rw.getInputWord() + " e' " + rw.isCorrect());
		}

	}

}

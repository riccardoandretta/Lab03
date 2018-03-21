package it.polito.tdp.spellchecker.model;

public class RichWord {

	private String inputWord;
	private boolean correct;
	

	public RichWord(String inputWord, boolean correct) {
		super();
		this.inputWord = inputWord;
		this.correct = correct;
	}

	public String getInputWord() {
		return inputWord;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

}

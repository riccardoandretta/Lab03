/**
 * Sample Skeleton for 'SpellChecker.fxml' Controller Class
 */

package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class SpellCheckerController {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="comboBox"
	private ComboBox<String> comboBox; // Value injected by FXMLLoader

	@FXML // fx:id="txtInput"
	private TextArea txtInput; // Value injected by FXMLLoader

	@FXML // fx:id="btnSpell"
	private Button btnSpell; // Value injected by FXMLLoader

	@FXML // fx:id="txtResult"
	private TextArea txtResult; // Value injected by FXMLLoader

	@FXML // fx:id="txtError"
	private Text txtError; // Value injected by FXMLLoader

	@FXML // fx:id="btnClear"
	private Button btnClear; // Value injected by FXMLLoader

	@FXML // fx:id="txtPerformance"
	private Text txtPerformance; // Value injected by FXMLLoader

	private Dictionary dict;

	@FXML
	void doClearText(ActionEvent event) {
		txtResult.clear();
		txtInput.clear();
		txtPerformance.setText("");
		txtError.setText("");
	}

	@FXML
	void loadDictionary(ActionEvent event) {
		txtInput.setDisable(false);
		btnSpell.setDisable(false);
		this.doClearText(event);
		
		if (comboBox.getValue().compareTo("English") == 0) {
			dict.loadDictionary("rsc/"+comboBox.getValue()+".txt");
		} else if (comboBox.getValue().compareTo("Italian") == 0) {
			dict.loadDictionary("rsc/"+comboBox.getValue()+".txt");
		}
	}

	@FXML
	void doSpellCheck(ActionEvent event) {

		txtResult.clear();
		String input = txtInput.getText();
		int errors = 0;
		
		// Controllo sull'input
		if (input == null || input.length() == 0) {
			txtResult.setText("Insert one or more words in the selected language.");
			return;
		}
		
		List<String> inputList = dict.inputToList(input);		

		double start = System.nanoTime();
		List<RichWord> ris = dict.spellCheckedText(inputList); //Dichotomic
		double stop = System.nanoTime();
		for(RichWord rw : ris) {
			if (!rw.isCorrect()) { // non capisco perchè non entri nel for che setta il correct in Dictionary; da TestModel funziona
				txtResult.appendText(rw.getInputWord()+"\n");
				errors++;
				}
			}
		txtError.setText("The text contains "+errors+" errors");
		txtPerformance.setText("Spell check completed in " + (stop - start) / Math.pow(10, 9) + " seconds");

	}

	@FXML // This method is called by the FXMLLoader when initialization is
			// complete
	void initialize() {
		assert comboBox != null : "fx:id=\"comboBox\" was not injected: check your FXML file 'SpellChecker.fxml'.";
		assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'SpellChecker.fxml'.";
		assert btnSpell != null : "fx:id=\"btnSpell\" was not injected: check your FXML file 'SpellChecker.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SpellChecker.fxml'.";
		assert txtError != null : "fx:id=\"txtError\" was not injected: check your FXML file 'SpellChecker.fxml'.";
		assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'SpellChecker.fxml'.";
		assert txtPerformance != null : "fx:id=\"txtPerformance\" was not injected: check your FXML file 'SpellChecker.fxml'.";
		//il comboBox lo potevo settare anche qui perchè le stringhe sono statiche
	}
	
	public void setModel(Dictionary model) { // Il model sarà settato dal MAIN
		this.dict = model;
		comboBox.setItems(FXCollections.observableArrayList("English", "Italian")); 
		txtPerformance.setText("");
		txtError.setText("");
		}
}

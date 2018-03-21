/**
 * Sample Skeleton for 'SpellChecker.fxml' Controller Class
 */

package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
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

	private Dictionary dict = new Dictionary();

	@FXML
	void doClearText(ActionEvent event) {
		txtResult.clear();
		txtInput.clear();
	}

	@FXML
	void loadDictionary(ActionEvent event) {
		
		if (comboBox.getValue().compareTo("rsc/English.txt") == 0) {
			dict.loadDictionary(comboBox.getValue());
		} else if (comboBox.getValue().compareTo("rsc/Italian.txt") == 0) {
			dict.loadDictionary(comboBox.getValue());
		}
	}

	@FXML
	void doSpellCheck(ActionEvent event) {
		String input = txtInput.getText();
		
		double start = System.nanoTime();
		// operazione
		double stop = System.nanoTime();
		txtResult.appendText("..."+"\n");
		txtPerformance.setText("Spell check completed in " + (stop - start) * Math.pow(10, 9) + " seconds");

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
		comboBox.setItems(FXCollections.observableArrayList("English", "Italian")); // lo
																					// posso
																					// fare
																					// qui
																					// perchè
																					// le
																					// stringhe
																					// sono
																					// statiche

	}
}

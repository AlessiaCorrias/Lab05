/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.anagrammi;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.anagrammi.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtInput"
    private TextField txtInput; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnagrammi"
    private Button btnAnagrammi; // Value injected by FXMLLoader

    @FXML // fx:id="txtCorretti"
    private TextArea txtCorretti; // Value injected by FXMLLoader

    @FXML // fx:id="txtErrati"
    private TextArea txtErrati; // Value injected by FXMLLoader

    @FXML // fx:id="btnReset"
    private Button btnReset; // Value injected by FXMLLoader

    @FXML
    void doAnagrammi(ActionEvent event) {
    	txtCorretti.clear();
    	txtErrati.clear();
    	String input = txtInput.getText().toLowerCase();
    	
    	if(input == null || input.equals("")|| input.equals(" ")) {
			this.txtCorretti.appendText("Devi inserire una parola !");
			return;
		}
    	
    	String[] array = input.split(" ");
    	if(array.length>1) {
    		this.txtCorretti.appendText("Devi inserire una sola parola !");
			return;
    	}
    	
    	char[] chararray = input.toCharArray();
		boolean flag = true;
		for (char c : chararray) {
			if (c > 'z' || c < 'a')
				flag = false;
		}
		if (flag == false) { 
			txtCorretti.clear();
			txtCorretti.appendText("LA PAROLA PUO' CONTENERE SOLO LETTERE \n");
			return;
		}
		
		List<String> corretti = this.model.anagramma(input, 0);
		List<String> errati = this.model.anagramma(input, 1);
		
		for(String s: corretti) {
			txtCorretti.appendText(s + "\n");
		}
		
		for(String s : errati) {
			txtErrati.appendText(s+"\n");
		}
		
	}

    

    @FXML
    void reset(ActionEvent event) {
    	txtCorretti.clear();
    	txtErrati.clear();
    	txtInput.clear();

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnAnagrammi != null : "fx:id=\"btnAnagrammi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCorretti != null : "fx:id=\"txtCorretti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtErrati != null : "fx:id=\"txtErrati\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    public void setModel(Model model) {
    	this.model=model;
    }
}

package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class gotoController {

    @FXML
    private Button quitButton;

    @FXML
    private TextField textfield;

    @FXML
    private Button gotoButton;

    @FXML
    void onGoToButtonClicked(ActionEvent event) {

    	if(Integer.parseInt(textfield.getText())<=Args.maxrow&&Integer.parseInt(textfield.getText())>0) {
    		Args.rowCount=Integer.parseInt(textfield.getText());
    		Stage stage=(Stage)textfield.getScene().getWindow();
        	stage.close();
    	}
    	else {
    		Alert alert=new Alert(AlertType.INFORMATION);
    		alert.setTitle("记事本 - 跳行");
    		alert.setContentText("行数超过了总行数");
    		alert.setHeaderText(null);
    		alert.showAndWait();
    		textfield.setText(String.valueOf(Args.rowCount));
        	textfield.requestFocus();
        	textfield.selectAll();
    	}
    }

    @FXML
    void onQuitButtonClicked(ActionEvent event) {

    	Stage stage=(Stage)textfield.getScene().getWindow();
    	stage.close();
    }
    
    public void initialize() {
    	textfield.setText(String.valueOf(Args.rowCount));
    	textfield.requestFocus();
    	textfield.selectAll();
    }

}

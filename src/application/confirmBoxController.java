package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class confirmBoxController {

    @FXML
    private Button nonSaveButton;

    @FXML
    private Button quitButton;

    @FXML
    private Label label;

    @FXML
    private Button saveButton;
    

	@FXML
    void saveButtonPushed(ActionEvent event) throws IOException {
		FileChooser filechooser=new FileChooser();
		filechooser.setInitialDirectory(new File("."));
		FileChooser.ExtensionFilter extensionFilter1=new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
		FileChooser.ExtensionFilter extensionFilter2=new FileChooser.ExtensionFilter("所有文件", "*.*");
		filechooser.setTitle("另存为");
		filechooser.getExtensionFilters().add(extensionFilter1);
		filechooser.getExtensionFilters().add(extensionFilter2);
		File file=filechooser.showSaveDialog(label.getScene().getWindow());
		if(file!=null) {
			
			FileWriter filewriter=new FileWriter(file);
			filewriter.write(Args.inputString);
			filewriter.close();
			
		}
		
		Stage stage=(Stage)label.getScene().getWindow();
		stage.close();

    }

    @FXML
    void nonSaveButtonPushed(ActionEvent event) {
    	Stage stage=(Stage)label.getScene().getWindow();
    	stage.close();
    	Args.newWindowNonSave=1;
    }

    @FXML
    void quitButtonPushed(ActionEvent event) {
    	Stage stage=(Stage)label.getScene().getWindow();
    	stage.close();
    	Args.cancelflag=1;

    }
    
    public void initialize() {
    	label.setText("你想将更改保存到\""+Args.windowName+"\"吗？");
    }

}
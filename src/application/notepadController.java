package application;


import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.swing.KeyStroke;
import javax.swing.undo.UndoManager;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.Mnemonic;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class notepadController {

    @FXML
    private MenuItem deleteMenu;
    
    @FXML
    private Label label4;
    
    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label label3;

    @FXML
    private Menu scaleMenu;

    @FXML
    private MenuItem goToMenu;

    @FXML
    private MenuItem enlargeMenu;

    @FXML
    private MenuItem pasteMenu;

    @FXML
    private TextArea textarea;
    
    @FXML
    private MenuItem fontMenu;

    @FXML
    private RadioMenuItem wrapMenu;

    @FXML
    private MenuItem saveMenu;

    @FXML
    private MenuItem seekNextMenu;

    @FXML
    private MenuItem replaceMenu;

    @FXML
    private MenuItem revokeMenu;

    @FXML
    private MenuItem seekLastMenu;

    @FXML
    private MenuItem shearMenu;

    @FXML
    private MenuItem copyMenu;
    
    @FXML
    private ToolBar toolbar;

    @FXML
    private MenuItem openMenu;

    @FXML
    private MenuItem newMenu;

    @FXML
    private MenuItem seekMenu;

    @FXML
    private MenuItem newWindowMenu;

    @FXML
    private MenuItem exitMenu;

    @FXML
    private MenuItem recoverMenu;

    @FXML
    private RadioMenuItem stateMenu;

    @FXML
    private MenuItem selectAllMenu;

    @FXML
    private MenuItem dateMenu;

    @FXML
    private MenuItem saveAsMenu;

    @FXML
    private MenuItem narrowMenu;
    
    private int inputflag=0;
    

    @FXML
    void newMenuClicked(ActionEvent event) throws IOException {

    	Args.firstWindow++;
    	
    	if(inputflag==0) {
    		textarea.clear();
    		Args.inputString="";
    		Stage stage=(Stage)textarea.getScene().getWindow();
    		stage.setTitle("无标题");
    		Args.windowName="无标题";
    		stage.show();
    	}
    	else {
    		
    		final Stage stage=new Stage();
    		stage.initModality(Modality.APPLICATION_MODAL);
        	stage.initOwner(textarea.getScene().getWindow());
        	Parent root=FXMLLoader.load(getClass().getResource("ConfirmBox.fxml"));
        	//BorderPane root = new BorderPane();
    		Scene scene = new Scene(root);
    		stage.setTitle("记事本");
    		stage.setScene(scene);
    		stage.showAndWait();
    		if(Args.cancelflag==0) {
    			Stage currentStage=(Stage)textarea.getScene().getWindow();
    			currentStage.setTitle("无标题");
    			Args.windowName="无标题";
    			textarea.clear();
    			inputflag=0;
    			}
    		Args.cancelflag=0;
        	}
    	}


    @FXML
    void newWindowMenuClicked(ActionEvent event) throws IOException {
    	final Stage stage=new Stage();
    	//stage.initModality(Modality.APPLICATION_MODAL);
    	Parent root=FXMLLoader.load(getClass().getResource("notepad_GUI.fxml"));
    	//BorderPane root = new BorderPane();
		Scene scene = new Scene(root);
		stage.setTitle("无标题");
		Args.windowName="无标题";
		stage.setScene(scene);
		stage.show();
		Args.firstWindow++;

    }

    @FXML
    void openMenuClicked(ActionEvent event) throws IOException {
    	if(inputflag==1) {
    		final Stage stage=new Stage();
    		stage.initModality(Modality.APPLICATION_MODAL);
        	stage.initOwner(textarea.getScene().getWindow());
        	Parent root=FXMLLoader.load(getClass().getResource("ConfirmBox.fxml"));
        	//BorderPane root = new BorderPane();
    		Scene scene = new Scene(root);
    		stage.setTitle(Args.windowName);
    		stage.setScene(scene);
    		stage.showAndWait();
    		if(Args.cancelflag==0) {
    			textarea.clear();
    			inputflag=0;
    			}
    		Args.cancelflag=0;
    	}
    		
    	FileChooser filechooser=new FileChooser();
    	FileChooser.ExtensionFilter extensionFilter1=new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
    	FileChooser.ExtensionFilter extensionFilter2=new FileChooser.ExtensionFilter("所有文件", "*.*");
    	filechooser.setInitialDirectory(new File("."));
    	filechooser.getExtensionFilters().add(extensionFilter1);
    	filechooser.getExtensionFilters().add(extensionFilter2);
    	filechooser.setTitle("打开");
    	File file=filechooser.showOpenDialog(
    			textarea.getScene().getWindow());
    	int num=file.toString().lastIndexOf("\\");
    	Args.windowName=file.toString().substring(num+1);
    	//System.out.println(Args.windowName);
    	Stage stage=(Stage)textarea.getScene().getWindow();
    	stage.setTitle(Args.windowName);
    		
    	if(file!=null) {
    		Scanner input=new Scanner(file);
    		while(input.hasNext()) {
    			textarea.appendText(input.nextLine()+"\n");
    		}
    		input.close();
    		inputflag=0;
    		
    	}
    			
    }

    @FXML
    void saveMenuClicked(ActionEvent event) throws IOException {

    	if(Args.firstWindow==0) {
    		inputflag=0;
    	}
    	else if(inputflag==1){
    		FileChooser filechooser=new FileChooser();
    		filechooser.setInitialDirectory(new File("."));
    		FileChooser.ExtensionFilter extensionFilter1=new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
    		FileChooser.ExtensionFilter extensionFilter2=new FileChooser.ExtensionFilter("所有文件", "*.*");
    		filechooser.setTitle("另存为");
    		filechooser.getExtensionFilters().add(extensionFilter1);
    		filechooser.getExtensionFilters().add(extensionFilter2);
    		File file=filechooser.showSaveDialog(textarea.getScene().getWindow());
    		if(file!=null) {
    			
    			FileWriter filewriter=new FileWriter(file);
    			if(Args.inputString!=null) {
    			filewriter.write(Args.inputString);}
    			filewriter.close();
    			inputflag=0;
    		}
    	}
    }

    @FXML
    void saveAsMenuClicked(ActionEvent event) throws IOException {
    	FileChooser filechooser=new FileChooser();
		filechooser.setInitialDirectory(new File("."));
		FileChooser.ExtensionFilter extensionFilter1=new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
		FileChooser.ExtensionFilter extensionFilter2=new FileChooser.ExtensionFilter("所有文件", "*.*");
		filechooser.setTitle("另存为");
		filechooser.getExtensionFilters().add(extensionFilter1);
		filechooser.getExtensionFilters().add(extensionFilter2);
		File file=filechooser.showSaveDialog(textarea.getScene().getWindow());
		if(file!=null) {
			
			FileWriter filewriter=new FileWriter(file);
			if(Args.inputString!=null) {
			filewriter.write(Args.inputString);}
			filewriter.close();
			
		}
		
    }

    @FXML
    void exitMenuClicked(ActionEvent event) throws IOException {
    	if(inputflag==1) {
    		final Stage stage=new Stage();
    		stage.initModality(Modality.APPLICATION_MODAL);
        	stage.initOwner(textarea.getScene().getWindow());
        	Parent root=FXMLLoader.load(getClass().getResource("ConfirmBox.fxml"));
        	//BorderPane root = new BorderPane();
    		Scene scene = new Scene(root);
    		stage.setTitle("记事本");
    		stage.setScene(scene);
    		stage.showAndWait();
    		if(Args.cancelflag==0) {
    			Stage currentstage=(Stage)textarea.getScene().getWindow();
    	    	currentstage.close();
    		}
    		Args.cancelflag=0;
    		
    	}
    	else {
    		Stage currentstage=(Stage)textarea.getScene().getWindow();
	    	currentstage.close();
	    	Args.firstWindow--;
    	}

    }

    @FXML
    void revokeMenuClicked(ActionEvent event) {
    	
    	if(undoflag==0) {
    		textarea.undo();
    		undoflag=1;
    	}
    	else if(undoflag==1) {
    		textarea.redo();
    		undoflag=0;
    	}

    }

    @FXML
    void shearMenuClicked(ActionEvent event) {
    	Transferable transferable=new StringSelection(textarea.getSelectedText());
    	clipboard.setContents(transferable, null);
    	textarea.replaceSelection("");

    }

    @FXML
    void copyMenuClicked(ActionEvent event) {

    	Transferable transferable=new StringSelection(textarea.getSelectedText());
    	clipboard.setContents(transferable, null);
    	
    }

    @FXML
    void pasteMenuClicked(ActionEvent event) {
    	
    	Transferable transferable=clipboard.getContents(null);
    	if(transferable!=null) {
  
    		if(transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
    			try {
    				content=(String)transferable.getTransferData(DataFlavor.stringFlavor);
    	
    			}
    			catch(Exception e) {
    				e.printStackTrace();
    			}
    		}
    	}
    	
    	
    	if(selectedflag==1) {
    		textarea.replaceSelection(content);
    	}
    	else if(selectedflag==0) {

    		textarea.insertText(textarea.getCaretPosition(), content);
    	}

    }

    @FXML
    void deleteMenuClicked(ActionEvent event) {

    	textarea.replaceSelection("");
    }

    @FXML
    void seekMenuClicked(ActionEvent event) throws IOException {

    	final Stage stage=new Stage();
    	//Parent root=FXMLLoader.load(getClass().getResource("seekBox.fxml"));
    	//BorderPane root = new BorderPane();
		FXMLLoader fxmlLoader=new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("seekBox.fxml"));
		Parent root = fxmlLoader.load();
		seekController c=(seekController)fxmlLoader.getController();
		c.primaryTextArea=textarea;
    	Scene scene = new Scene(root);
		stage.setTitle("查找");
		stage.setScene(scene);
		stage.showAndWait();

		
    }

    private String subString="";
    
    public int CaseIndex(String string,String subString,int indexstart) {
    	int returnindex=0;
    	if(Args.caseflag==0) {
    		returnindex=string.toLowerCase().indexOf(subString.toLowerCase(), indexstart);
    	}
    	else if(Args.caseflag==1) {
    		returnindex=string.indexOf(subString,indexstart);
    	}
    	return returnindex;
    }

    @FXML
    void seekNextMenuClicked(ActionEvent event) throws IOException {
    	if(Args.seekString=="") {
    		final Stage stage=new Stage();
    		FXMLLoader fxmlLoader=new FXMLLoader();
    		fxmlLoader.setLocation(getClass().getResource("seekBox.fxml"));
    		Parent root = fxmlLoader.load();
    		seekController c=(seekController)fxmlLoader.getController();
    		c.primaryTextArea=textarea;
        	Scene scene = new Scene(root);
    		stage.setTitle("查找");
    		stage.setScene(scene);
    		stage.showAndWait();
    		
    	}
    	else {
    		if(Args.index==-1&&Args.startindex!=Args.inputString.length())Args.startindex=-1;
    		if(Args.loopflag==0) {
    			if(Args.startindex!=-1&&Args.startindex<Args.inputString.length()) {
    				if(Args.startindex==-1) {
    					if(Args.startindex==-1)Args.startindex+=1;
    					Alert alert=new Alert(AlertType.INFORMATION);
    					alert.setTitle("记事本");
    	    		
    					alert.setContentText("找不到\""+Args.seekString+"\"");
    					alert.setHeaderText("");
    					alert.showAndWait();
    				}
    				else {
    					if(CaseIndex(Args.inputString, Args.seekString, Args.startindex)==-1) {
    						Alert alert=new Alert(AlertType.INFORMATION);
	    					alert.setTitle("记事本");
	        	    		
	    					alert.setContentText("找不到\""+Args.seekString+"\"");
	    					alert.setHeaderText("");
	    					alert.showAndWait();
	    				}
	    				else {
	    					Args.startindex=CaseIndex(Args.inputString, Args.seekString, Args.startindex);
	    					textarea.selectRange(Args.startindex, Args.startindex
	    							+Args.seekString.length());
	    					if(Args.startindex!=-1)
	    						Args.startindex+=Args.seekString.length();
	    				}
	    			}    			
	    			
	    	
	    		}
	    		else {
	    			Alert alert=new Alert(AlertType.INFORMATION);
	    			alert.setTitle("记事本");
	    		
	    			alert.setContentText("找不到\""+Args.seekString+"\"");
	    			alert.setHeaderText("");
	    			alert.showAndWait();
	    		
	    		}
	    	}
	    	else if(Args.loopflag==1) {
	    			if(Args.startindex==-1) {
	    				Alert alert=new Alert(AlertType.INFORMATION);
	    				alert.setTitle("记事本");
	    		
	    				alert.setContentText("找不到\""+Args.seekString+"\"");
	    				alert.setHeaderText("");
	    				alert.showAndWait();
	    			}
	    			else {
	    		
	    			Args.startindex=CaseIndex(Args.inputString, Args.seekString, Args.startindex);
	    			if(Args.startindex==-1)Args.startindex=CaseIndex(Args.inputString, Args.seekString, 0);
	    			if(Args.startindex==-1) {
	    				Alert alert=new Alert(AlertType.INFORMATION);
	    	    		alert.setTitle("记事本");
	    	    		
	    	    		alert.setContentText("找不到\""+Args.seekString+"\"");
	    	    		alert.setHeaderText("");
	    	    		alert.showAndWait();
	    	    			
	    	    		Args.startindex=textarea.getSelection().getEnd();
	    			}
	    			else {
	    				textarea.selectRange(Args.startindex, Args.startindex
	    						+Args.seekString.length());
	    				Args.startindex+=Args.seekString.length();
	    			}
	
	    		}
	    	}
    	}

    }

    @FXML
    void seekLastMenuClicked(ActionEvent event) throws IOException {

    	if(Args.seekString=="") {
    		final Stage stage=new Stage();
    		FXMLLoader fxmlLoader=new FXMLLoader();
    		fxmlLoader.setLocation(getClass().getResource("seekBox.fxml"));
    		Parent root = fxmlLoader.load();
    		seekController c=(seekController)fxmlLoader.getController();
    		c.primaryTextArea=textarea;
        	Scene scene = new Scene(root);
    		stage.setTitle("查找");
    		stage.setScene(scene);
    		stage.showAndWait();
    		
    	}
    	else {
    		if(Args.index==-1&&Args.startindex!=Args.inputString.length())Args.startindex=-1;
    		
    		if(Args.loopflag==0) {
        		if(Args.startindex!=-1&&Args.startindex<Args.inputString.length()) {
        			if(Args.startindex==Args.inputString.length()-1)Args.startindex+=1;
        			if(Args.startindex-Args.seekString.length()-1>=0){
        	
        				subString=Args.inputString.substring(0, Args.startindex-Args.seekString.length()-1);
      
        				if(Args.caseflag==1&&subString.lastIndexOf(Args.seekString)==-1||
    	    				Args.caseflag==0&&subString.toLowerCase().lastIndexOf(Args.seekString)==-1) {
    	    			Alert alert=new Alert(AlertType.INFORMATION);
    	    			alert.setTitle("记事本");
        	    		
    	    			alert.setContentText("找不到\""+Args.seekString+"\"");
        	    		alert.setHeaderText("");
        	    		alert.showAndWait();
        				}
        				else {
        					if(Args.caseflag==1)Args.startindex=subString.lastIndexOf(Args.seekString);
        					if(Args.caseflag==0)Args.startindex=subString.toLowerCase().
        							lastIndexOf(Args.seekString.toLowerCase());
        				
        					textarea.selectRange(Args.startindex, Args.startindex
        							+Args.seekString.length());
        					Args.startindex+=Args.seekString.length();
        				}
        			}
        			else {
        				Alert alert=new Alert(AlertType.INFORMATION);
        				alert.setTitle("记事本");
        	    		
        				alert.setContentText("找不到\""+Args.seekString+"\"");
        				alert.setHeaderText("");
        				alert.showAndWait();
        			}
        		
        		}
        		else {
        			Alert alert=new Alert(AlertType.INFORMATION);
        			alert.setTitle("记事本");
        		
        			alert.setContentText("找不到\""+Args.seekString+"\"");
        			alert.setHeaderText("");
        			alert.showAndWait();
        		
        		}
        	}
    		else if(Args.loopflag==1) {
        		if(Args.startindex==-1) {
        			Alert alert=new Alert(AlertType.INFORMATION);
        			alert.setTitle("记事本");
        		
        			alert.setContentText("找不到\""+Args.seekString+"\"");
        			alert.setHeaderText("");
        			alert.showAndWait();
        		}
        		else {  				
        			if(Args.startindex-Args.seekString.length()-1<0) {
        				if(Args.caseflag==1)Args.startindex=Args.inputString.lastIndexOf(Args.seekString)
        						+2*Args.seekString.length()+1;
        				else if(Args.caseflag==0)Args.startindex=Args.inputString.toLowerCase().
        						lastIndexOf(Args.seekString.toLowerCase())+2*Args.seekString.length()+1;
        			}
        			if(Args.startindex==Args.inputString.length()-1) Args.startindex+=1;
        				
        			subString=Args.inputString.substring(0,Args.startindex-Args.seekString.length()-1);
    				
        			if(Args.caseflag==1) Args.startindex=subString.lastIndexOf(Args.seekString);
        				
        			else if(Args.caseflag==0) {Args.startindex=subString.toLowerCase().
        					lastIndexOf(Args.seekString.toLowerCase());
        				
        			}
        			if(Args.startindex==-1) {
        					
        				if(Args.caseflag==0)Args.startindex=Args.inputString.toLowerCase().
        						lastIndexOf(Args.seekString.toLowerCase());
        				else if(Args.caseflag==1)Args.startindex=Args.inputString.lastIndexOf(Args.seekString);
        				if(Args.startindex==-1) {
        					Alert alert=new Alert(AlertType.INFORMATION);
        		    		alert.setTitle("记事本");
        		    		
        		    		alert.setContentText("找不到\""+Args.seekString+"\"");
        		    		alert.setHeaderText("");
        		    		alert.showAndWait();
        		    		Args.startindex=textarea.getSelection().getEnd();
        				}
        			}
        				
        			textarea.selectRange(Args.startindex, Args.startindex+Args.seekString.length());
        			Args.startindex+=Args.seekString.length();
        		}
        	}
    	}
    }

    @FXML
    void replaceMenuClicked(ActionEvent event) throws IOException {
    	final Stage stage=new Stage();

		FXMLLoader fxmlLoader=new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("replaceBox.fxml"));
		Parent root = fxmlLoader.load();
		replaceController c=(replaceController)fxmlLoader.getController();
		c.primaryTextArea=textarea;
    	Scene scene = new Scene(root);
		stage.setTitle("查找");
		stage.setScene(scene);
		stage.showAndWait();
    	

    	
    }

    @FXML
    void gotoMenuClicked(ActionEvent event) throws IOException {

    	final Stage stage=new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
    	stage.initOwner(textarea.getScene().getWindow());
    	Parent root=FXMLLoader.load(getClass().getResource("gotoBox.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("转到指定行");
		stage.setScene(scene);
		stage.showAndWait();

		int count=0;int i=0;
		for(;i<textarea.getText().length();i++) {
			if(textarea.getText().charAt(i)=='\n')count++;
			if(count==Args.rowCount-1)break;
		}
		if(i==0)i-=1;
		textarea.positionCaret(i+1);
		
    	
    }

    @FXML
    void selectAllMenuClicked(ActionEvent event) {
    	textarea.selectAll();
    }

    @FXML
    void dateMenuClicked(ActionEvent event) {

    	textarea.getCaretPosition();
    	SimpleDateFormat simpledateformat=new SimpleDateFormat("HH:mm yyyy-MM-dd");
    	textarea.appendText(simpledateformat.format(new Date()));
    	
    	Args.inputString=textarea.getText();
    }

    @FXML
    void wrapMenuClicked(ActionEvent event) {

    	if(wrapMenu.isSelected()) {
    		textarea.setWrapText(true);
    		goToMenu.setDisable(true);
    	}
    	else {
    		textarea.setWrapText(false);
    		goToMenu.setDisable(false);
    	}
    }

    @FXML
    void fontMenuClicked(ActionEvent event) throws IOException {

    	final Stage stage=new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
    	stage.initOwner(textarea.getScene().getWindow());
    	Parent root=FXMLLoader.load(getClass().getResource("FontBox.fxml"));
    	//BorderPane root = new BorderPane();
		Scene scene = new Scene(root);
		stage.setTitle("记事本");
		stage.setScene(scene);
		stage.showAndWait();
    	
		if(Args.boldflag==1&&Args.italicflag==1) {
			textarea.setFont(Font.font(Args.font,FontWeight.BOLD,FontPosture.ITALIC,
					Args.size));
		}
		else if(Args.boldflag==1) {
			textarea.setFont(Font.font(Args.font,FontWeight.BOLD,
					Args.size));
		}
		else if(Args.italicflag==1) {
			textarea.setFont(Font.font(Args.font,FontPosture.ITALIC,
					Args.size));
		}
		else textarea.setFont(Font.font(Args.font,Args.size));
    }

    @FXML
    void onEnlargeMenuSelected(ActionEvent event) {
    	Args.fontsize+=2;
    	textarea.setFont(Font.font(Args.fontsize));
    	label2.setText(100-(Args.size-Args.fontsize)*5+"%");
    }

    @FXML
    void onNarrowMenuSelected(ActionEvent event) {
    	Args.fontsize-=2;
    	textarea.setFont(Font.font(Args.fontsize));
    	label2.setText(100-(Args.size-Args.fontsize)*5+"%");
   	
    }

    @FXML
    void onRecoverMenuSelected(ActionEvent event) {

    	textarea.setFont(Font.font(Args.size));
    	label2.setText("100%");
    }

    @FXML
    void stateMenuClicked(ActionEvent event) {
    	if(stateMenu.isSelected()==false) {
    		toolbar.setVisible(false);
    	}
    	else if(stateMenu.isSelected()==true) {
    		toolbar.setVisible(true);
    	}

    }
    
    private Clipboard clipboard=Toolkit.getDefaultToolkit().getSystemClipboard();
    
    private String content="";
    private int selectedflag=0;
    private int undoflag=0;
    private int column=0;
    
    public void initialize() {
    	textarea.setFont(Font.font(Args.font,Args.size));
    	textarea.textProperty().addListener(
    			(obs,oldValue,newValue)->{
    				inputflag=1;
    				Args.inputString=newValue;
    				Args.startindex=0;
    				
    				int count=1;
    				for(int i=0;i<textarea.getText().length();i++) {
    					if(textarea.getText().charAt(i)=='\n')count++;
    				}
    				Args.maxrow=count;
    				
    				revokeMenu.setDisable(false);
    				if(!newValue.equals("")) {
    					seekMenu.setDisable(false);
    					seekNextMenu.setDisable(false);
    					seekLastMenu.setDisable(false);
    				}
    				else {
    					seekMenu.setDisable(true);
    			    	seekLastMenu.setDisable(true);
    			    	seekNextMenu.setDisable(true);
    				}
    				
    			}
    			);
    	
    	textarea.setWrapText(true);
    	
    	revokeMenu.setDisable(true);
    	shearMenu.setDisable(true);
    	copyMenu.setDisable(true);
    	pasteMenu.setDisable(true);
    	deleteMenu.setDisable(true);
    	seekMenu.setDisable(true);
    	seekLastMenu.setDisable(true);
    	seekNextMenu.setDisable(true);
    	goToMenu.setDisable(true);
    	
    	newMenu.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
    	newWindowMenu.setAccelerator(KeyCombination.keyCombination("Ctrl+Shift+N"));
    	openMenu.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));
    	saveMenu.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
    	saveAsMenu.setAccelerator(KeyCombination.keyCombination("Ctrl+Shift+S"));
    	
    	revokeMenu.setAccelerator(KeyCombination.keyCombination("Ctrl+Z"));
    	shearMenu.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
    	copyMenu.setAccelerator(KeyCombination.keyCombination("Ctrl+C"));
    	pasteMenu.setAccelerator(KeyCombination.keyCombination("Ctrl+V"));
    	deleteMenu.setAccelerator(KeyCombination.keyCombination("Del"));
    	seekMenu.setAccelerator(KeyCombination.keyCombination("Ctrl+F"));
    	seekNextMenu.setAccelerator(KeyCombination.keyCombination("F3"));
    	seekLastMenu.setAccelerator(KeyCombination.keyCombination("Shift+F3"));
    	replaceMenu.setAccelerator(KeyCombination.keyCombination("Ctrl+H"));
    	goToMenu.setAccelerator(KeyCombination.keyCombination("Ctrl+G"));
    	selectAllMenu.setAccelerator(KeyCombination.keyCombination("Ctrl+A"));
    	dateMenu.setAccelerator(KeyCombination.keyCombination("F5"));
    	enlargeMenu.setAccelerator(KeyCombination.keyCombination("Ctrl+加号"));
    	narrowMenu.setAccelerator(KeyCombination.keyCombination("Ctrl+减号"));
    	recoverMenu.setAccelerator(KeyCombination.keyCombination("Ctrl+0"));
         

    	label1.setText("第"+Args.rowCount+"行,第"+column+"列");
    	label2.setText("100%");
    	label3.setText("Unix(LF)");
    	label4.setText("UTF-8");
    	
    	Transferable transferable=clipboard.getContents(null);
    	if(transferable!=null) {
    		pasteMenu.setDisable(false);
    		if(transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
    			try {
    				content=(String)transferable.getTransferData(DataFlavor.stringFlavor);
    				
    			}
    			catch(Exception e) {
    				e.printStackTrace();
    			}
    		}
    	}
    	
    	textarea.selectedTextProperty().addListener(
    			(obs,oldvalue,newvalue)->{
    				if(!newvalue.equals("")) {
    					copyMenu.setDisable(false);
    					shearMenu.setDisable(false);
    					deleteMenu.setDisable(false);
    					selectedflag=1;
    				}
    				else if(newvalue.equals("")) {
    					copyMenu.setDisable(true);
    					shearMenu.setDisable(true);
    					deleteMenu.setDisable(true);
    					selectedflag=0;
    				}
    			});
    	
    	textarea.caretPositionProperty().addListener((obs,oldvalue,newvalue)->{
    		int count=1;
    		
    		for(int i=0;i<(Integer)newvalue;i++) {
    			if(textarea.getText().charAt(i)=='\n')count++;
    		}
    		Args.rowCount=count;
    		
    		int j=0,k=count;
    		for(;j<(Integer)newvalue;j++) {
    			if(textarea.getText().charAt(j)=='\n')k--;
    			if(k==1)break;
    		}
    		column=(Integer)newvalue-j;
    		label1.setText("第"+Args.rowCount+"行,第"+column+"列");
    		
    		
    	});

    }

}


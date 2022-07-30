package application;

import java.awt.GraphicsEnvironment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class fontController {

	@FXML
	private ListView<String> listview2;

	@FXML
	private ListView<String> listview1;

    @FXML
    private TextField textfield1;

    @FXML
    private TextField textfield2;

    @FXML
    private TextField textfield3;

    @FXML
    private ListView<String> listview3;
    
    @FXML
    private Button quitbutton;
    
    @FXML
    private Button confirmbutton;
    
    @FXML
    private Label label;

    @FXML
    private ComboBox<String> combobox;
    
    private ObservableList<String> fontList=FXCollections.observableArrayList();
    
    @FXML
    void onConfirmButtonClicked(ActionEvent event) {

    	Args.font=textfield1.getText();
    	Args.size=Integer.parseInt(textfield3.getText());
    	Stage stage=(Stage)label.getScene().getWindow();
    	stage.close();
    	
    }

    @FXML
    void onQuitButtonClicked(ActionEvent event) {

    	Stage stage=(Stage)label.getScene().getWindow();
    	stage.close();
    }
    
    
    public void initialize() {
  
    	int index1=0,index2=0,index3=0;
    	textfield1.setText("宋体");
    	textfield2.setText("常规");
    	textfield3.setText("18");
    	
    	String[] font=GraphicsEnvironment.getLocalGraphicsEnvironment().
    			getAvailableFontFamilyNames();
    	
    	for(int i=0;i<font.length;i++) {
    		if(font[i].equals("宋体")) {
    			index1=i;
    			break;
    		}
    	}

    	for(String string:font) {
    		fontList.add(string);
    	}
    	
    	listview1.setItems(fontList);
    	
   	
    	String[] model= {"常规","倾斜","粗体","粗偏斜体"};
    	for(String string:model) {
    		listview2.getItems().add(string);
    	}
    	

    	String[] size= {"8","9","10","11","12","14","16","18","22","24","26"
    			,"28","36","48","72"};
    	for(String string:size) {
    		listview3.getItems().add(string);
    	}
    	index3=7;
	
    	listview1.getSelectionModel().selectedItemProperty().
    	addListener((obs,oldvalue,newvalue)->{
    		textfield1.setText(newvalue);
    		
    		if(Args.boldflag==1&&Args.italicflag==1) {
    			label.setFont(Font.font(textfield1.getText(),FontWeight.BOLD,FontPosture.ITALIC,
    					Integer.parseInt(textfield3.getText())));
    		}
    		else if(Args.boldflag==1) {
    			label.setFont(Font.font(textfield1.getText(),FontWeight.BOLD,
    					Integer.parseInt(textfield3.getText())));
    		}
    		else if(Args.italicflag==1) {
    			label.setFont(Font.font(textfield1.getText(),FontPosture.ITALIC,
    					Integer.parseInt(textfield3.getText())));
    		}
    		else label.setFont(Font.font(textfield1.getText(),Integer.parseInt(textfield3.getText())));
//    		textfield1.requestFocus();
//    		textfield1.selectAll();
    	});
    
    	listview2.getSelectionModel().selectedItemProperty().
    	addListener((obs,oldvalue,newvalue)->{
    		textfield2.setText(newvalue);
    		
//    		textfield2.requestFocus();
//    		textfield2.selectAll();
    		if(newvalue.equals("倾斜")) {
    			Args.italicflag=1;
    			Args.boldflag=0;
    			
    		}
    		else if(newvalue.equals("粗体")) {
    			Args.boldflag=1;
    			Args.italicflag=0;
    		}
    		else if(newvalue.equals("粗偏斜体")) {
    			Args.boldflag=1;
    			Args.italicflag=1;
    		}
    		else if(newvalue.equals("常规")) {
    			Args.boldflag=0;
    			Args.italicflag=0;
    		}
    		
    		
    		if(Args.boldflag==1&&Args.italicflag==1) {
    			label.setFont(Font.font(textfield1.getText(),FontWeight.BOLD,FontPosture.ITALIC,
    					Integer.parseInt(textfield3.getText())));
    		}
    		else if(Args.boldflag==1) {
    			label.setFont(Font.font(textfield1.getText(),FontWeight.BOLD,
    					Integer.parseInt(textfield3.getText())));
    		}
    		else if(Args.italicflag==1) {
    			label.setFont(Font.font(textfield1.getText(),FontPosture.ITALIC,
    					Integer.parseInt(textfield3.getText())));
    		}
    		else label.setFont(Font.font(textfield1.getText(),Integer.parseInt(textfield3.getText())));
    		
    	});
    	

    	listview3.getSelectionModel().selectedItemProperty().
    	addListener((obs,oldvalue,newvalue)->{
    		textfield3.setText(newvalue);
    		if(Args.boldflag==1&&Args.italicflag==1) {
    			label.setFont(Font.font(textfield1.getText(),FontWeight.BOLD,FontPosture.ITALIC,
    					Integer.parseInt(textfield3.getText())));
    		}
    		else if(Args.boldflag==1) {
    			label.setFont(Font.font(textfield1.getText(),FontWeight.BOLD,
    					Integer.parseInt(textfield3.getText())));
    		}
    		else if(Args.italicflag==1) {
    			label.setFont(Font.font(textfield1.getText(),FontPosture.ITALIC,
    					Integer.parseInt(textfield3.getText())));
    		}
    		else label.setFont(Font.font(textfield1.getText(),Integer.parseInt(textfield3.getText())));
//    		textfield3.requestFocus();
//    		textfield3.selectAll();
    	});
    	
//    	textfield3.textProperty().bind(
//    			listview3.getSelectionModel().selectedItemProperty());
    	
    	
    	listview1.getSelectionModel().select(index1);
    	listview1.scrollTo(index1+1);
    	listview2.getSelectionModel().select(index2);
    	listview2.scrollTo(index2+1);
    	listview3.getSelectionModel().select(index3);
    	listview3.scrollTo(index3+1);
    	
    	String[] script= {"中文GB2312","西欧语言"};
    	for(String string:script) {
    		combobox.getItems().add(string);
    	}
    	
    	combobox.setPromptText("西欧语言");
    	
    	
    	combobox.getSelectionModel().selectedItemProperty().addListener(
    			(obs,oldvalue,newvalue)->{    				
    				if(newvalue.equals("中文GB2312")) {
    					label.setText("你好，世界！");
    				}
    				else if(newvalue.equals("西欧语言")) {
    					label.setText("AaBbYyZz");
    				}
    			});
    	
    	
    	
    }

}
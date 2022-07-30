package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class replaceController {

    @FXML
    private Button nextButton;

    @FXML
    private Button replaceButton;

    @FXML
    private TextField textfield1;

    @FXML
    private CheckBox caseSeparateButton;

    @FXML
    private Button quitButton;

    @FXML
    private TextField textfield2;

    @FXML
    private Button replaceAllButton;

    @FXML
    private CheckBox loopButton;
    
   
    private int queryloopflag=0;
    private int querycaseflag=0;
    
    public TextArea primaryTextArea;
    
    

    @FXML
    void onCaseSeparateButtonSelected(ActionEvent event) {

    	if(caseSeparateButton.isSelected()==true) {
    		querycaseflag=1;
    	}
    	else if(caseSeparateButton.isSelected()==false) {
    		querycaseflag=0;
    	}
    	
    }

    @FXML
    void onLoopButtonSelected(ActionEvent event) {

    	if(loopButton.isSelected()==true) {
    		queryloopflag=1;
    	}
    	else if(loopButton.isSelected()==false) {
    		querycaseflag=0;
    	}
    }
    
    public int CaseIndex(String string,String subString,int indexstart) {
    	int returnindex=0;
    	if(querycaseflag==0) {
    		returnindex=string.toLowerCase().indexOf(subString.toLowerCase(), indexstart);
    	}
    	else if(querycaseflag==1) {
    		returnindex=string.indexOf(subString,indexstart);
    	}
    	return returnindex;
    }

    @FXML
    void onNextButtonClicked(ActionEvent event) {

    	if(Args.queryindex==-1&&Args.querystartindex!=Args.inputString.length())Args.querystartindex=-1;
		if(queryloopflag==0) {
			if(Args.querystartindex!=-1&&Args.querystartindex<Args.inputString.length()) {
				if(Args.querystartindex==-1) {
					if(Args.querystartindex==-1)Args.querystartindex+=1;
					Alert alert=new Alert(AlertType.INFORMATION);
					alert.setTitle("记事本");
	    		
					alert.setContentText("找不到\""+Args.queryString+"\"");
					alert.setHeaderText("");
					alert.showAndWait();
				}
				else {
					if(CaseIndex(Args.inputString, Args.queryString, Args.querystartindex)==-1) {
						Alert alert=new Alert(AlertType.INFORMATION);
    					alert.setTitle("记事本");
        	    		
    					alert.setContentText("找不到\""+Args.queryString+"\"");
    					alert.setHeaderText("");
    					alert.showAndWait();
    				}
    				else {
    					Args.querystartindex=CaseIndex(Args.inputString, Args.queryString, Args.querystartindex);
    					
    					primaryTextArea.selectRange(Args.querystartindex, Args.querystartindex
    							+Args.queryString.length());
    					if(Args.querystartindex!=-1)
    						Args.querystartindex+=Args.queryString.length();
    				}
    			}    			
    			
    	
    		}
    		else {
    			Alert alert=new Alert(AlertType.INFORMATION);
    			alert.setTitle("记事本");
    		
    			alert.setContentText("找不到\""+Args.queryString+"\"");
    			alert.setHeaderText("");
    			alert.showAndWait();
    		
    		}
    	}
    	else if(queryloopflag==1) {
    			if(Args.querystartindex==-1) {
    				Alert alert=new Alert(AlertType.INFORMATION);
    				alert.setTitle("记事本");
    		
    				alert.setContentText("找不到\""+Args.queryString+"\"");
    				alert.setHeaderText("");
    				alert.showAndWait();
    			}
    			else {
    		
    			Args.querystartindex=CaseIndex(Args.inputString, Args.queryString, Args.querystartindex);
    			if(Args.querystartindex==-1)Args.querystartindex=CaseIndex(Args.inputString, Args.queryString, 0);
    			if(Args.querystartindex==-1) {
    				Alert alert=new Alert(AlertType.INFORMATION);
    	    		alert.setTitle("记事本");
    	    		
    	    		alert.setContentText("找不到\""+Args.queryString+"\"");
    	    		alert.setHeaderText("");
    	    		alert.showAndWait();
    	    			
    	    		Args.querystartindex=primaryTextArea.getSelection().getEnd();
    			}
    			else {
    				primaryTextArea.selectRange(Args.querystartindex, Args.querystartindex
    						+Args.queryString.length());
    				Args.querystartindex+=Args.queryString.length();
    			}

    		}
    	}
    	
    }

    @FXML
    void onReplaceButtonClicked(ActionEvent event) {

    	if(Args.queryindex==-1&&Args.querystartindex!=Args.inputString.length())Args.querystartindex=-1;
		if(queryloopflag==0) {
			if(Args.querystartindex!=-1&&Args.querystartindex<Args.inputString.length()) {
				if(Args.querystartindex==-1) {
					if(Args.querystartindex==-1)Args.querystartindex+=1;
					Alert alert=new Alert(AlertType.INFORMATION);
					alert.setTitle("记事本");
	    		
					alert.setContentText("找不到\""+Args.queryString+"\"");
					alert.setHeaderText("");
					alert.showAndWait();
				}
				else {
					if(CaseIndex(Args.inputString, Args.queryString, Args.querystartindex)==-1) {
						Alert alert=new Alert(AlertType.INFORMATION);
    					alert.setTitle("记事本");
        	    		
    					alert.setContentText("找不到\""+Args.queryString+"\"");
    					alert.setHeaderText("");
    					alert.showAndWait();
    				}
    				else {
    					Args.querystartindex=CaseIndex(Args.inputString, Args.queryString, Args.querystartindex);
    					
    					primaryTextArea.selectRange(Args.querystartindex, Args.querystartindex
    							+Args.queryString.length());
    					primaryTextArea.replaceSelection(replaceString);
    					if(Args.querystartindex!=-1)
    						Args.querystartindex+=Args.queryString.length();
    				}
    			}    			
    			
    	
    		}
    		else {
    			Alert alert=new Alert(AlertType.INFORMATION);
    			alert.setTitle("记事本");
    		
    			alert.setContentText("找不到\""+Args.queryString+"\"");
    			alert.setHeaderText("");
    			alert.showAndWait();
    		
    		}
    	}
    	else if(queryloopflag==1) {
    			if(Args.querystartindex==-1) {
    				Alert alert=new Alert(AlertType.INFORMATION);
    				alert.setTitle("记事本");
    		
    				alert.setContentText("找不到\""+Args.queryString+"\"");
    				alert.setHeaderText("");
    				alert.showAndWait();
    			}
    			else {
    		
    			Args.querystartindex=CaseIndex(Args.inputString, Args.queryString, Args.querystartindex);
    			if(Args.querystartindex==-1)Args.querystartindex=CaseIndex(Args.inputString, Args.queryString, 0);
    			if(Args.querystartindex==-1) {
    				Alert alert=new Alert(AlertType.INFORMATION);
    	    		alert.setTitle("记事本");
    	    		
    	    		alert.setContentText("找不到\""+Args.queryString+"\"");
    	    		alert.setHeaderText("");
    	    		alert.showAndWait();
    	    			
    	    		Args.querystartindex=primaryTextArea.getSelection().getEnd();
    			}
    			else {
    				primaryTextArea.selectRange(Args.querystartindex, Args.querystartindex
    						+Args.queryString.length());
    				primaryTextArea.replaceSelection(replaceString);
    				Args.querystartindex+=Args.queryString.length();
    			}

    		}
    	}
    }

    @FXML
    void onReplaceAllButtonClicked(ActionEvent event) {

    	if(querycaseflag==0) {
    		while(primaryTextArea.getText().toLowerCase().indexOf(Args.queryString.toLowerCase())!=-1) {
    			primaryTextArea.replaceText(primaryTextArea.getText().toLowerCase().indexOf(Args.queryString.toLowerCase()),
    					primaryTextArea.getText().toLowerCase().indexOf(Args.queryString.toLowerCase())+Args.queryString.length(),
    					replaceString);
    		}
    	}
    	else if(querycaseflag==1) {
    		while(primaryTextArea.getText().indexOf(Args.queryString)!=-1) {
    			primaryTextArea.replaceText(primaryTextArea.getText().indexOf(Args.queryString),
    					primaryTextArea.getText().indexOf(Args.queryString)+Args.queryString.length(),
    					replaceString);
    		}
    	}
    	//System.out.println(Args.inputString);
    }

    @FXML
    void onQuitButtonClicked(ActionEvent event) {

    	Stage stage=(Stage)textfield1.getScene().getWindow();
    	stage.close();
    }
    
    private String replaceString="";
    
    public void initialize() {
    	if(!Args.queryString.equals("")) {
    		textfield1.setText(Args.queryString);
    		textfield1.requestFocus();
    		textfield1.selectAll();
    		nextButton.setDisable(false);
        	replaceButton.setDisable(false);
        	replaceAllButton.setDisable(false);
    		
    	}
    	else {
    		nextButton.setDisable(true);
    		replaceButton.setDisable(true);
    		replaceAllButton.setDisable(true);
    	}
    	
    	textfield1.textProperty().addListener((obs,oldvalue,newvalue)->{
    		Args.queryString=newvalue;
    		if(querycaseflag==1) Args.queryindex=Args.inputString.
    				indexOf(Args.seekString,Args.querystartindex);
			else if(querycaseflag==0) Args.queryindex=Args.inputString.
					toLowerCase().indexOf(Args.seekString.toLowerCase(),Args.querystartindex);
    		if(newvalue.equals("")) {
    			nextButton.setDisable(true);
    	    	replaceButton.setDisable(true);
    	    	replaceAllButton.setDisable(true);
    		}
    		else if(!newvalue.equals("")) {
    			nextButton.setDisable(false);
            	replaceButton.setDisable(false);
            	replaceAllButton.setDisable(false);
    		}
    	});
    	
    	textfield2.textProperty().addListener((obs,oldvalue,newvalue)->{
    		replaceString=newvalue;
    	});
    }

}


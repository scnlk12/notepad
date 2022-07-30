package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class seekController {

    @FXML
    private Button nextButton;

    @FXML
    private RadioButton nextRadioButton;

    @FXML
    private CheckBox caseSeparateButton;

    @FXML
    private Button quitButton;

    @FXML
    private TextField textfield;

    @FXML
    private RadioButton lastRadioButton;

    @FXML
    private CheckBox loopButton;

    @FXML
    private ToggleGroup seekToggleButton;
    
    public TextArea primaryTextArea;
    
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
    void OnNextButtonClicked(ActionEvent event) {
    	
    	if(Args.index==-1&&Args.startindex!=Args.inputString.length())Args.startindex=-1;
    	
    	if(Args.loopflag==0) {
    		if(Args.startindex!=-1&&Args.startindex<Args.inputString.length()) {
    			if(Args.nextflag==1) {
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
    						
    						primaryTextArea.selectRange(Args.startindex, Args.startindex
    								+Args.seekString.length());
    						if(Args.startindex!=-1)
    							Args.startindex+=Args.seekString.length();
    					}
    				}

    			
    			}
    			else if(Args.lastflag==1) {
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
    				
    						
    						primaryTextArea.selectRange(Args.startindex, Args.startindex
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
    			if(Args.nextflag==1) {
    				Args.startindex=CaseIndex(Args.inputString, Args.seekString, Args.startindex);
    				if(Args.startindex==-1)Args.startindex=CaseIndex(Args.inputString, Args.seekString, 0);
    				if(Args.startindex==-1) {
    					Alert alert=new Alert(AlertType.INFORMATION);
    	    			alert.setTitle("记事本");
    	    		
    	    			alert.setContentText("找不到\""+Args.seekString+"\"");
    	    			alert.setHeaderText("");
    	    			alert.showAndWait();
    	    			
    	    			
    	    			Args.startindex=primaryTextArea.getSelection().getEnd();
    				}
    				else {
    					primaryTextArea.selectRange(Args.startindex, Args.startindex
    							+Args.seekString.length());
    					Args.startindex+=Args.seekString.length();
    				}
    				
    			}
    			else if(Args.lastflag==1) {
    				
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
    		    			Args.startindex=primaryTextArea.getSelection().getStart();
    					}
    				}
    				
    				primaryTextArea.selectRange(Args.startindex, Args.startindex+Args.seekString.length());
    				Args.startindex+=Args.seekString.length();
    				
    			}
    		}
    	}
    	
    }

    @FXML
    void OnQuitButtonClicked(ActionEvent event) {

    	Stage stage=(Stage)textfield.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void onSeekToggleGroupSelected(ActionEvent event) {

    	if((Integer)seekToggleButton.getSelectedToggle().getUserData()==1) {
    		Args.nextflag=0;
    		Args.lastflag=1;
    		if(Args.startindex==Args.inputString.length())Args.startindex-=1;
    		
    	}
    	else if((Integer)seekToggleButton.getSelectedToggle().getUserData()==0) {
    		Args.nextflag=1;
    		Args.lastflag=0;
    		    	}
    }

    @FXML
    void OnCaseSeparateButtonClicked(ActionEvent event) {

    	if(caseSeparateButton.isSelected()==true) {
    		Args.caseflag=1;
    	}
    	else if(caseSeparateButton.isSelected()==false) {
    		Args.caseflag=0;
    	}
    	
    	
    }

    @FXML
    void OnLoopButtonClicked(ActionEvent event) {
    	if(loopButton.isSelected()==true){
    		Args.loopflag=1;
    	}
    	else if(loopButton.isSelected()==false) {
    		Args.loopflag=0;
    	}

    }
    

    public void initialize() {
    	
    	if(!Args.seekString.equals("")) {
    		textfield.setText(Args.seekString);
    		textfield.selectAll();
    		textfield.requestFocus();
    		nextButton.setDisable(false);
    	}
    	if(Args.seekString.equals(""))nextButton.setDisable(true);
    	
    	textfield.textProperty().addListener(
    			(obs,oldvalue,newvalue)->{
  
    				Args.seekString=newvalue;
    			
    				if(Args.caseflag==1) Args.index=Args.inputString.indexOf(Args.seekString,Args.startindex);
    				
    				else if(Args.caseflag==0) Args.index=Args.inputString.toLowerCase().indexOf(Args.seekString.toLowerCase(),Args.startindex);
    
    				if(newvalue.equals("")) nextButton.setDisable(true);
    				else nextButton.setDisable(false);
    				
    			});
 	
    	lastRadioButton.setUserData(1);
    	nextRadioButton.setUserData(0);
 
    }
}

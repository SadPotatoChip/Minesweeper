package mainPack;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Starting extends Application{

	public static void main(String[] args) {
		Application.launch(args);
	}

	public void start(Stage primaryStage) throws Exception{
		VBox mainBox=new VBox(30);
		HBox setupBox=new HBox(5);
		TextField wText=new TextField("width?");
		TextField hText=new TextField("height?");
		TextField bText=new TextField("bombs?");
		RadioButton cheat= new RadioButton("cheat");
		Button generateButton=new Button("Generate!");
		setupBox.getChildren().addAll(wText,hText,bText,generateButton,cheat);
		PlayArea p=new PlayArea(10,10,20,false);
		mainBox.getChildren().addAll(setupBox, p.Area);
		Scene initialGameScene= new Scene(mainBox,600,400);	
		generateButton.setOnAction(smth->{
			
			PlayArea x=new PlayArea(10, 10, 20,cheat.isSelected());
			try{
				int w=Integer.parseInt(wText.getText());
				int h=Integer.parseInt(hText.getText());
				int n=Integer.parseInt(bText.getText());
				if(n<w*h/2)
					x=new PlayArea(w,h,n,cheat.isSelected());
			}catch(Exception e){	
				wText.setText("bad input!");
			}	
				mainBox.getChildren().clear();				
				mainBox.getChildren().addAll(setupBox, x.Area);
		});
		
		primaryStage.setScene(initialGameScene);
		primaryStage.setMinWidth(600);
		primaryStage.setMinHeight(400);
		primaryStage.show();
	}
	

	
}

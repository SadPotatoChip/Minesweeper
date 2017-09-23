package mainPack;

import java.util.Random;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class PlayArea {
	int w;
	int h;
	int nOfBombs;
	VBox Area;
	Button[][] buttonMat;
	boolean[][] isBomb;
	boolean bombsInitalized;
	boolean cheat;
	
	
	PlayArea (int w, int h,int nOfBombs, boolean cheat){
		this.cheat=cheat;
		bombsInitalized=false;
		this.w=w;
		this.h=h;
		this.nOfBombs=nOfBombs;
		buttonMat=new Button[h][w];
		Area=new VBox(0);
		generateArea();
		isBomb=new boolean[h][w];
		for(int i=0;i<h;i++){
			for(int j=0;j<w;j++){
				isBomb[i][j]=false;
			}
		}
		
	}
	
	private void generateArea(){
		int i,j;
		for(i=0;i<h;i++){
			int ixd=i;
			HBox row=new HBox(0);
			for(j=0;j<w;j++){
				int jxd=j;
				Button tmp=new Button("");
				tmp.setMaxWidth(30);
				tmp.setMinWidth(30);
				buttonMat[i][j]=tmp;
				tmp.setOnAction(smth->{
					SeekNDestroy.onButtonClick(w,h,ixd,jxd,this,true);
				});
				tmp.addEventFilter(MouseEvent.MOUSE_RELEASED,new EventHandler<MouseEvent>() {
		            @Override
		            public void handle(MouseEvent event) {
		                if (event.getButton() == MouseButton.SECONDARY) {
		                    onRightClick(tmp);
		                    event.consume();
		                }
		            }
		        });
				row.getChildren().add(tmp);
			}
			Area.getChildren().add(row);
		}
	}

	public void setupBombs(int h, int w, int x, int y){
		int d=2;
		
		for(int i=0;i<nOfBombs;i++){
			Random r=new Random();
			int a=0,b=0;
			do{
				a=Math.abs(r.nextInt(h));
				b=Math.abs(r.nextInt(w));
			}while(isBomb[a][b]==true || ((a<x+d && a>x-d) && (b<y+d && b>y-d)) );
			isBomb[a][b]=true;
			if(cheat)
				buttonMat[a][b].setText("x");
		}
	}

	public void gameOver(){
		int i,j;
		for(i=0;i<h;i++){
			for(j=0;j<w;j++){
				buttonMat[i][j].setDisable(true);
			}
		}
	}
	
	private void onRightClick(Button x){
		if(x.getText().isEmpty()){
			x.setText("X");
			x.setTextFill(Color.RED);
		}else{
			x.setText("");
		}
	}
}





















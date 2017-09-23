package mainPack;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public final class SeekNDestroy {
	
	 static void onButtonClick(int w,int h,int i,int j,PlayArea pa, boolean isInitial){
		if(!pa.bombsInitalized){
			pa.setupBombs(h, w, i, j);
			pa.bombsInitalized=true;
		}
		if(pa.isBomb[i][j] && isInitial){
			pa.gameOver();
		}else {
			int n=countBombsNear(w,h,i,j,pa);
			if(0==n){
				destroyButton(pa.buttonMat[i][j]);
				int[] borders=new int[4];
				borders=BorderCalculator.borderCalc(borders,w,h,i,j);
				for(int x=borders[0];x<=borders[2];x++){
					for(int y=borders[3];y<=borders[1];y++){
						if(!pa.buttonMat[x][y].isDisable())
							onButtonClick(w,h,x,y,pa,false);
					}
				}
			}else{
				pa.buttonMat[i][j].setText(" "+n);				
				pa.buttonMat[i][j].setDisable(true);
				
				switch(n){
				case 1:
					pa.buttonMat[i][j].setTextFill(Color.GREEN);
					break;
				case 2:
					pa.buttonMat[i][j].setTextFill(Color.BLUE);
					break;
				case 3:
					pa.buttonMat[i][j].setTextFill(Color.DARKRED);
					break;
				case 4:
					pa.buttonMat[i][j].setTextFill(Color.PURPLE);
					break;
				case 5:
					pa.buttonMat[i][j].setTextFill(Color.YELLOW);
					break;
				case 6:
					pa.buttonMat[i][j].setTextFill(Color.DARKMAGENTA);
					break;
				case 7:
					pa.buttonMat[i][j].setTextFill(Color.TEAL);
					break;
				case 8:
					pa.buttonMat[i][j].setTextFill(Color.ORANGE);
					break;
						
				}
			}
		}
	}
	
	static private void destroyButton(Button btn){
		btn.setVisible(false);
		btn.setDisable(true);
	}

	static private int countBombsNear(int w,int h,int i,int j,PlayArea pa){
		int n=0;
		int[] borders=new int[4];
		borders=BorderCalculator.borderCalc(borders,w,h,i,j);
		
		for(int x=borders[0];x<=borders[2];x++){
			for(int y=borders[3];y<=borders[1];y++){
				if(pa.isBomb[x][y])
					n++;
			}
		}
		return n;
	}
	

}

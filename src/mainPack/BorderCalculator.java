package mainPack;

public class BorderCalculator {
	
	public static int[] borderCalc(int[] borders,int w,int h,int i,int j){
		if(i==h-1){
			borders[2]=i;
		}else{
			borders[2]=i+1;
		}
		if(i==0){
			borders[0]=i;
		}else{
			borders[0]=i-1;
		}
		if(j==w-1){
			borders[1]=j;
		}else{
			borders[1]=j+1;
		}
		if(j==0){
			borders[3]=j;
		}else{
			borders[3]=j-1;
		}
		return borders;
	}
}

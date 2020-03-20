package iia.simpleGame.squadro;



public class Move {
	
	private int oldColum;
	private int oldLline;
	private int newColum;
	private int newLine;
	
	public Move(int oc, int ol, int nc, int nl) 
	{
		oldColum=oc;
		oldLline=ol;
		newLine=nl;
		newColum=nc;
	}
	
	public Move(String move)
	{
		//A4-C3 - oldColum=0 oldLine=4 newCOlum=2 newLine=3
		oldColum=alphaToInt(move.substring(0, 1));
		oldLline=Integer.parseInt(move.substring(1, 2));
		oldLline--;
		
		newColum=alphaToInt(move.substring(3, 4));
		newLine=Integer.parseInt(move.substring(4, 5));
		newLine--;
	}
	
	public String toString()
	{
		
		return intToAlpha(oldColum)+(1+oldLline)+"-"+intToAlpha(newColum)+(1+newLine);
		
	}
	
	private Integer alphaToInt(String a)
	{
		int ind=-1;
		
		switch (a) {
		
		case "A":
		ind=0;
		break;
		
		case "B":
		ind=1;
		break;
		
		case "C":
		ind=2;
		break;
		
		case "D":
		ind=3;
		break;
		
		case "E":
		ind=4;
		break;
		
		case "F":
		ind=5;
		break;
		
		case "G":
		ind=6;
		break;
		
		default:

		break;
		}
		
		return ind;
		
	}
	
	private String intToAlpha(int a)
	{

	    switch (a) 
	    {
		case 0:
		return "A";
		
		case 1:
		return "B";

		case 2:
		return "C";
		
		case 3:
		return "D";
		
		case 4:
		return "E";
		
		case 5:
		return "F";
		
		case 6:
		return "G";
		
		default:
			return null;
	
		}
	}
	
	public int getOldLine()
	{
		return oldLline;
	}
	
	public int getOldColum()
	{
		return oldColum;
	}
	
	public int getNewLine()
	{
		return newLine;
	}
	
	public int getNewColum()
	{
		return newColum;
	}
	
	
	

}

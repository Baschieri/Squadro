package iia.simpleGame.squadro;

import iia.simpleGame.base.AGame;
import iia.simpleGame.base.IGame;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ASquadroGame extends AGame {
    
    /*
    We would model a game board using two vectors containing an object with two value:
        - an integer from 0 that represent the position of the n-th pawn in the table.
        - a boolean that represent the direction of travel
    We would also use two counters for memorizing the number of pawns which return to the start 
    and a vector that stores how many steps each pawn can take.
    */
    
    ArrayList<PawnPosition> pawnPositionsH;
    Integer counterH;

    ArrayList<PawnPosition> pawnPositionsV;
    Integer counterV;
    
    int stepsNumberH[] = {3,1,2,1,3};
    int stepsNumberV[] = {1,3,2,3,1};
    
    char turn;
    
    public ASquadroGame()
    {
    	pawnPositionsH = new ArrayList<PawnPosition>();
    	pawnPositionsV = new ArrayList<PawnPosition>();
    	
    	for(int i = 0; i < 5; i++)
    		pawnPositionsH.add(new PawnPosition(0));
    	counterH = 0;
    	
    	for(int i = 0; i < 5; i++)
    		pawnPositionsV.add(new PawnPosition(6));
    	counterV = 0;
    	
    	this.turn = 'v';
    }

    @Override
    public IGame play(String move, String role) {
        // TODO !
        return null;
    }
    
    
    @Override
    public ArrayList<String> possibleMoves(String role) {
        
    	ArrayList<String> possibleMoves = new ArrayList<String>();
    	
    	PawnPosition p = null;
    	int nextPosition = 0;
    	Boolean jump = false;
    	
    	// TODO considerare che quando la pedina arriva alla fine non va più mossa
    	
    	if(role.equals("horizontal") && turn == 'h')
    	{
    		for(int i = 0; i < 5; i++) // I have a move for each pawn in the table
    		{
    			p = pawnPositionsH.get(i);
    			
    			if(p != null)
    				possibleMoves.add(pawnMove(p, "horizontal", i));
    		}
    	}
    	if(role.equals("vertical") && turn == 'v')
    	{
    		for(int i = 0; i < 5; i++) // I have a move for each pawn in the table
    		{
    			p = pawnPositionsH.get(i);
    			
    			if(p != null)
    				possibleMoves.add(pawnMove(p, "horizontal", i));
    		}
    	}
    	
    	return possibleMoves;
    }
    

    @Override
    public boolean isValidMove(String move, String role)
    {
    	
    	// TODO
    	// ricavare l'index attuale della pedina dalla stringa move,
    	// chiamare il metodo pawnMove e verificare che la stringa move sia uguale
    	
        return false;
    }
    
    
    // it returns the only move that a pawn can do
    // where index is
    // - the row for role = "vertical"
    // - the column for role = "horizontal"
    private String pawnMove(PawnPosition p, String role, Integer index) 
    {
    	Integer nextPosition = 0;
    	Boolean jump = false;
    	Move m = null;
    	
    	if(role.equals("horizontal"))
    	{
	    	if(p.forward)
			{
				for(int j = p.position + 1; j < 6 && j < (p.position + stepsNumberH[index]); j++)
				{
					if(jump == false)
						nextPosition = j;
					
					if(pawnPositionsV.get(j).position == index+1)
					{
						nextPosition = j + 1;
						jump = true;
					}
					else
					if(jump == true)		// So if at the privious iteration I jump on en 
					{				 		// enemy and in this position there aren't any 
						nextPosition = j;	// I find where i can locate with this pawn
						break;
					}
				}
			}
			else // (!p.forward)
			{
				for(int j = p.position-1; j > 0 && j > (p.position - stepsNumberV[index]); j--)
				{
					if(jump == false)
						nextPosition = j;
					
					if(pawnPositionsV.get(j).position == index+1)
					{
						nextPosition = j - 1;
						jump = true;
					}
					else
					if(jump == true)		// So if at the privious iteration I jump on en 
					{				 		// enemy and in this position there aren't any 
						nextPosition = j;	// I find where i can locate with this pawn
						break;
					}	
				}
			}
	    	
	    	m = new Move(p.position, index, nextPosition, index);
    	}
    	else if(role.equals("vertical"))
    	{
    		if(p.forward)
    		{
    			for(int j = p.position -1; j > 0 && j > (p.position - stepsNumberV[index]); j--)
    			{
    				if(jump == false)
    					nextPosition = j;
    				if(pawnPositionsH.get(j).position == index+1)
					{
						nextPosition = j - 1;
						jump = true;
					}
					else
					if(jump == true)		// So if at the privious iteration I jump on en 
					{				 		// enemy and in this position there aren't any 
						nextPosition = j;	// I find where i can locate with this pawn
						break;
					}	
    			}
    		}
    		else
    		{
				for(int j = p.position + 1; j < 6 && j < (p.position + stepsNumberH[index]); j++)
				{
					if(jump == false)
						nextPosition = j;
					
					if(pawnPositionsH.get(j).position == index+1)
					{
						nextPosition = j + 1;
						jump = true;
					}
					else
					if(jump == true)		// So if at the privious iteration I jump on en 
					{				 		// enemy and in this position there aren't any 
						nextPosition = j;	// I find where i can locate with this pawn
						break;
					}
				}
    		}
    		
    		/*System.out.println("vecchia posizione: ");
    		
    		System.out.println("nuova posizione: ");
    		*/
    		m = new Move(index, p.position, index, nextPosition); 
    	}
    	
    	
		return m.toString();
    	
	}
 

    @Override
    public boolean isGameOver() {
        
    	if(counterH>=4 || counterV>=4)
        {
     	   return true;
        }
        
        return false;
    }
    
    
    public void setFromFile(String fileName)
    {
    	try 
    	{
    		 BufferedReader reader = new BufferedReader(new FileReader(fileName));
             String line = reader.readLine();
             while(line!=null) 
             {
                 System.out.println(line);
                 line = reader.readLine();
                 if(!line.contains("%"))
                 {
                	 //this line isn't a comment
                	 int linea = Integer.parseInt(line.substring(1, 1));
                	 //linea contain 1 or 2 or 3 ecc..
                	 int i=2;
                	 int j=0;
                	 char c = line.charAt(i);
                	 while(c!= '\n')
                	 {
                		if(c=='>') 
                		{
                			PawnPosition pawn = pawnPositionsH.get(linea);
                			pawn.setPosition(j);
                			pawn.setForward(true);
                		}
                		if(c=='<')
                		{
                			PawnPosition pawn = pawnPositionsH.get(linea);
                			pawn.setPosition(j);
                			pawn.setForward(true);
                		}
                		
                		if(c=='^')
                		{
                			PawnPosition pawn = pawnPositionsV.get(j);
                			pawn.setPosition(linea);
                			pawn.setForward(true);
                		}
                		if(c=='v')
                		{
                			PawnPosition pawn = pawnPositionsV.get(j);
                			pawn.setPosition(linea);
                			pawn.setForward(false);
                		}
                		
                		i++;
                		j++;
                		
                		c = line.charAt(i);
                	 }
                	 
                	 for(int a=0;a<5;a++)
                	 {
                		 if( pawnPositionsH.get(a).position==0 && pawnPositionsH.get(a).forward==false)
                		 {
                			 counterH++;
                		 }
                		 
                		 if(pawnPositionsV.get(a).position==0 && pawnPositionsV.get(a).forward==false)
                		 {
                			 counterV++;
                		 }
                	 }
				
                 }
             }
             
             reader.close();
		} 
    	catch (Exception e) {
			//file inesistente
			//cosa faccio?
			//TODO 
			e.printStackTrace();
		}
    	
    }
    
    public void saveToFile(String fileName)
    {
    	File f = new File(fileName);
    	BufferedWriter writer = null;
    	
    	try
    	{
			writer = new BufferedWriter(new FileWriter(f));
			
			writer.write("%  ABCDEFG\n");
			
			writer.write("01 ");
			writer.write('.'); // the coordinate (A,1) is always empty
			for(int i = 0; i < 5; i++) // look at the first row
			{
				if(pawnPositionsV.get(i).position == 0)
					if(pawnPositionsV.get(i).forward == true)
						writer.write('^');
					else
						writer.write('v');
				else
					writer.write('.');
			}
			writer.write('.'); // the coordinate (G,1) is always empty
			writer.write(" 01\n");
			
			for(int i = 0; i < 5; i++) // look at the row from 1 to 5
			{
				writer.write("0" + (i+2) + " ");
				
				if(pawnPositionsH.get(i).position == 0) // look at the coordinate of the first column
					if(pawnPositionsH.get(i).forward == true)
						writer.write('>');
					else
						writer.write('<');
				else
					writer.write('.');
					
				for(int j = 0; j < 5; j++) // for each row I look at all the possible panws 
				{
					if(pawnPositionsH.get(i).position == j + 1)
						if(pawnPositionsH.get(i).forward == true)
							writer.write('>');
						else
							writer.write('<');
					else
					if(pawnPositionsV.get(j).position == i+1)
						if(pawnPositionsV.get(j).forward == true)
							writer.write('^');
						else
							writer.write('v');
					else
						writer.write('.');
				}
				
				if(pawnPositionsH.get(i).position == 6) // look at the coordinate of the last column
					if(pawnPositionsH.get(i).forward == true)
						writer.write('>');
					else
						writer.write('<');
				else
					writer.write('.');
				
				writer.write(" 0" + (i+2) +"\n");
			}
			
			writer.write("07 ");
			writer.write('.'); // the coordinate (A,7) is always empty
			
			for(int i = 0; i < 5; i++) // look at the last row
			{
				if(pawnPositionsV.get(i).position == 6)
					if(pawnPositionsV.get(i).forward == true)
						writer.write('^');
					else
						writer.write('v');
				else
					writer.write('.');
			}
			writer.write("."); // the coordinate (G,7) is always empty
			
			writer.write(" 07\n");
			
			writer.write("%  ABCDEFG\n");
			
			if(this.turn == 'h')
				writer.write("horizontal");
			if(this.turn == 'v')
				writer.write("vertical");
			
		}
    	catch (IOException e)
    	{
			e.printStackTrace();
		}
    	finally // I close the writer regardless of what happens
    	{
    		try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	
    } //SaveToFile

	@Override
	public int getValue(String role) {
		// TODO Auto-generated method stub
		return 0;
	}
    
    
}

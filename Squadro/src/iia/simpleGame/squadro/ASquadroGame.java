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

public abstract class ASquadroGame extends AGame {
    
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
    
    int stepsNumber[] = {3,1,2,1,3};
    
    char turn;
    
    public ASquadroGame()
    {
    	pawnPositionsH = new ArrayList<PawnPosition>();
    	
    	for(int i = 0; i < 5; i++)
    		pawnPositionsH.add(new PawnPosition(i));
    	counterH = 0;
    	
    	for(int i = 0; i < 5; i++)
    		pawnPositionsV.add(new PawnPosition(i));
    	counterV = 0;
    	
    	this.turn = 'V';
    }

    @Override
    public IGame play(String move, String role) {
        // TODO !
        return null;
    }
    

    @Override
    public ArrayList<String> possibleMoves(String role) {
        // TODO !
        return null;
    }

    @Override
    public boolean isValidMove(String move, String role) {
        // TODO !
        return false;
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
    	 //Esempio di cosa c'è in un file
    	/*
    	% Exemple 1
		% ABCDEFG
		01 ....... 01 
		02 >...... 02 
		03 >...... 03 
		04 >...... 04 
		05 >...... 05 
		06 >...... 06 
		07 .^^^^^. 07 
		% ABCDEFG 
		horizontal
    	 */	
        BufferedReader reader = null;
    	try 
    	{
    		 reader = new BufferedReader(new FileReader(fileName));
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
    	finally {
			reader.close();
		}
    	
    	
    }
    
    
    public void saveToFile(String fileName)
    {
    	    	File f = new File(fileName);
    	BufferedWriter writer = null;
    	
    	try
    	{
			writer = new BufferedWriter(new FileWriter(f));
			
			writer.write("% ABCDEFG\n");
			
			writer.write("01");
			writer.write('.'); // the coordinate (A,1) is always empty
			for(int i = 0; i < 5; i++) // look at the first row
			{
				if(pawnPositionsV.get(i).position == 6)
					if(pawnPositionsV.get(i).forward == true)
						writer.write('^');
					else
						writer.write('v');
				else
					writer.write('.');
			}
			writer.write('.'); // the coordinate (G,1) is always empty
			writer.write("01\n");
			
			for(int i = 0; i < 5; i++) // look at the row from 1 to 5
			{
				writer.write("0" + (i+2));
				
				if(pawnPositionsH.get(i).position == 0) // look at the coordinate of the first column
					if(pawnPositionsH.get(i).forward == true)
						writer.write('>');
					else
						writer.write('<');
				else
					writer.write('.');
					
				for(int j = 0; j < 5; j++) // for each row I look at each possible vertical panws 
				{
					if(pawnPositionsH.get(i).position == j + 1)
						if(pawnPositionsH.get(i).forward == true)
							writer.write('>');
						else
							writer.write('<');
					else
					if(pawnPositionsV.get(j).position == 5 - i)
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
				
				writer.write("0" + (i+2) +"\n");
			}
			
			writer.write("07");
			writer.write('.'); // the coordinate (A,7) is always empty
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
			writer.write("."); // the coordinate (G,7) is always empty
			writer.write("07\n");
			
			writer.write("% ABCDEFG\n");
			
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
    	
    }
    }
    
    
    
    
 }

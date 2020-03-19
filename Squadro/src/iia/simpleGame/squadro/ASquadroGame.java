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
    	pawnPositionsV = new ArrayList<PawnPosition>();
    	
    	for(int i = 0; i < 5; i++)
    		pawnPositionsH.add(new PawnPosition(0));
    	counterH = 0;
    	
    	for(int i = 0; i < 5; i++)
    		pawnPositionsV.add(new PawnPosition(0));
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
    	
    	if(role.equals("horizontal") && turn == 'h')
    	{
    		for(int i = 0; i < 5; i++) // I have a move for each pawn in the table
    		{
    			p = pawnPositionsH.get(i);
    			
    			// I look at what move I can do with each pawn
    			if(p.forward)
    			{
    				for(int j = p.position + 1; j < 6 || j < (p.position + stepsNumberH[i]); j++)
    				{
    					if(jump == false)
    						nextPosition = j;
    					
    					if(pawnPositionsV.get(j).position == i+1)
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
    				for(int j = p.position-1; j > 0 || j > (p.position + stepsNumberV[i]); j--)
    				{
    					if(jump == false)
    						nextPosition = j;
    					
    					if(pawnPositionsV.get(j).position == i+1)
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
    			
    			//TODO trasformare la nuova e vecchia posizione in una stringa con formato "A4-C4"
    			//	   e aggiungerla al vettore
    			
    			// oldPosition --> pawnPositionsH.get(i).position;
    			// newPositino --> nextPosition
    		}
    	}
    	if(role.equals("vertical") && turn == 'v')
    	{
    		for(int i = 0; i < 5; i++) // I have a move for each pawn in the table
    		{
    			p = pawnPositionsH.get(i);
    			
    			// I look at what move I can do with each pawn
    			if(p.forward)
    			{
    				for(int j = p.position + 1; j < 6 || j < (p.position + stepsNumberH[i]); j++)
    				{
    					if(jump == false)
    						nextPosition = j;
    					
    					if(pawnPositionsV.get(j).position == i+1)
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
    				for(int j = p.position-1; j > 0 || j > (p.position + stepsNumberV[i]); j--)
    				{
    					if(jump == false)
    						nextPosition = j;
    					
    					if(pawnPositionsV.get(j).position == i+1)
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
    			
    			//TODO trasformare la nuova e vecchia posizione in una stringa con formato "A4-C4"
    			//	   e aggiungerla al vettore
    			
    			// oldPosition --> pawnPositionsH.get(i).position;
    			// newPositino --> nextPosition
    		}
    	}
    	
    	return possibleMoves;
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
                 //System.out.println(line);
                 line = reader.readLine();
                 if(line!= null && !line.contains("%"))
                 {
                	 if(line.contains("horizontal"))
                	 {
                		 turn='h';
                		// System.out.println("turno orizzontal");
                		 continue;
                		 
                	 }
                	 
                	 if(line.contains("vertical"))
                	 {
                		 turn='v';
                		 //System.out.println("turno vertical");
                		 continue;
                	 }
                	// System.out.println("analizzo la linea= " + line);
                	 //this line isn't a comment
                	 int linea = Integer.parseInt(line.substring(1, 2));
                	 //System.out.println("LINEA N.= " + linea);
                	 //linea contain 1 or 2 or 3 ecc..
                	 int i=3; //salto 01 e lo spazio
                	 int j=0;
                	 int index=0;
                	 int colon=0;
                	 char c = line.charAt(i);
                	 while(i<line.length()-2) // -2 sono i caratteri 01 finali (o 02 o 03 ecc)
                	 {  
                		if(c=='>') 
                		{
                			index=linea-2; //xche la prima pedina orizzontale si muove sulla 2 riga
                			PawnPosition pawn = pawnPositionsH.get(index);
                			pawn.setPosition(j);
                			pawn.setForward(true);
                			//System.out.println("pedina > " + index +"esima riga" + " trovata nella colonna= " + j + " con verso true");
                		}
                		if(c=='<')
                		{	
                			index= linea-2;
                			PawnPosition pawn = pawnPositionsH.get(index);
                			pawn.setPosition(j);
                			pawn.setForward(false);
                			//System.out.println("pedina < " + index +"esima riga" + " trovata nella colonna= " + j + " con verso false");
                		}
                		
                		if(c=='^')
                		{
                			index=linea-1;
                			//System.out.println("pedina ^ " + colon +"esima colonna" + " trovata nella riga=  "  + index);
                			colon = j-1;
                			PawnPosition pawn = pawnPositionsV.get(colon);
                			pawn.setPosition(index);
                			pawn.setForward(true);
                			
                		}
                		if(c=='v')
                		{
                			index=linea-1;
                			PawnPosition pawn = pawnPositionsV.get(j);
                			pawn.setPosition(index);
                			pawn.setForward(false);
                			//System.out.println("pedina ^ " + colon+"esima colonna" + " trovata nella riga=  "  + index);
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
             
             System.out.println(pawnPositionsH);
             System.out.println(pawnPositionsV);
             
             reader.close();
		} 
    	catch (Exception e) {
			//file inesistente
			//cosa faccio?
			//TODO 
			e.printStackTrace();
		}
    	finally {
    		
			try {reader.close();} catch (IOException e) {e.printStackTrace();}
		}
    	
    	
    }
    
        //FINE SETFROMFILE
    
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
    
    
    
    
 }

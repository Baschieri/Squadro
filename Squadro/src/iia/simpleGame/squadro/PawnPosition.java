package iia.simpleGame.squadro;

public class PawnPosition {

	Integer position;
	Boolean forward; // When it's true it means that it's in the initial position
	
	public PawnPosition( Integer position) 
	{
		this.position = position;
		this.forward = true;
	}
	
	//Aggiungere controlli
	public void setPosition(Integer pos)
	{
		this.position=pos;
	}
	
	public void setForward(Boolean forw)
	{
		this.forward=forw;
	}
	
	public String toString()
	{
		return(position.toString() + forward.toString());
		
	}
	
}

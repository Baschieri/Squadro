package iia.simpleGame.squadro;

public class PawnPosition {

	Integer position;
	Boolean forward; // When it's true it means that it's in the initial position
	
	public PawnPosition( Integer position) 
	{
		this.position = position;
		this.forward = true;
	}
}

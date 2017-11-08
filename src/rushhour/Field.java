package rushhour;

public class Field
{
	private boolean blocked = false;
	
	public void block()
	{
		blocked = true;
	}
	public void unBlock()
	{
		blocked = false;
	}
	public boolean isBlocked()
	{
		return blocked;
	}
}

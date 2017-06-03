public class Staff extends Customer
{
	private String options;
	public Staff()
	{
		super();
		options = "\n(3)Check-in/Check-out services";
	}
	public String toString()
	{
		return super.toString()+options;
	}
}
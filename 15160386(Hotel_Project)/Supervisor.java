public class Supervisor extends Staff
{
	private String options;
	public Supervisor()
	{
		super();
		options = "\n(4)Apply discounts\n";
	}
	public String toString()
	{
		return super.toString()+options;
	}
}
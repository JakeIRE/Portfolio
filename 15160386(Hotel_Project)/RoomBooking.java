import java.util.*;
public class RoomBooking
{
	private GregorianCalendar date;
	private String roomInfo;
	private double cost;
	private String day;
	private int adults;
	private int kids;
	public RoomBooking(GregorianCalendar date, String roomInfo, String day, double cost, int adults, int kids)
	{
		this.date = date;
		this.roomInfo = roomInfo;
		this.day = day;
		this.cost = cost;
		this.adults = adults;
		this.kids = kids;
	}
	/**Returns output to write to file*/
	public String roomInfoOut()
	{
		return date.get(Calendar.DAY_OF_MONTH)+"/"+(date.get(Calendar.MONTH)+1)+"/"+date.get(Calendar.YEAR)+","+roomInfo+","+day+","+cost+","+adults+","+kids;
	}
	/**Removes cost when refunded*/
	public void removeCost()
	{
		cost = 0;
	}
	/**Returns date*/
	public GregorianCalendar getDate()
	{
		return date;
	}
	/**Discounts rooms daily price*/
	public void applyDiscount(double d)
	{
		cost = cost*d;
	}
}
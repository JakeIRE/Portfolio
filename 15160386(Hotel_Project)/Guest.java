import java.util.*;
public class Guest
{
	private String bookingName;
	private	int resNum;
	private String hotelType;
	private double cost;
	private double discount;
	private String ap;
	private int a;
	private int c;
	private int days;
	private String check;
	private ArrayList<RoomBooking> rooms = new ArrayList<RoomBooking>();
	public Guest(String bookingName, int resNum, String check, int days, double cost, String hotelType,String ap, double discount, GregorianCalendar date, String roomInfo, String day, double dcost, int adults, int kids)
	{
		this.bookingName = bookingName;
		this.resNum = resNum;
		this.cost = cost;
		this.days = days;
		this.check = check;
		this.hotelType = hotelType;
		this.ap = ap;
		this.a = a;
		this.c = c;
		this.discount = discount;
		rooms.add(new RoomBooking(date, roomInfo, day, dcost, adults, kids));
	}
	public Guest(String bookingName, int resNum, int days, double cost, String hotelType, String ap)
	{
		this.bookingName = bookingName;
		this.resNum = resNum;
		this.cost = cost;
		this.days = days;
		this.hotelType = hotelType;
		this.ap = ap;
		this.cost = cost;
		this.a = a;
		this.c = c;
		this.check = "Room Booked";
		discount = 0;
	}
	/**Returns output to write to file*/
	public String writeFile()
	{
		String out = bookingName+","+resNum+","+check+","+days+","+cost+","+hotelType+","+ap+","+discount+","+rooms.get(0).roomInfoOut()+"\n";
		for(int x = 1; x < rooms.size() ; x++)
		{
			out += ",,,,,,,,"+rooms.get(x).roomInfoOut()+"\n";
		}
		return out;
	}
	/**Adds new RoomBooking Object*/
	public void addMoreRooms(GregorianCalendar date, String roomInfo, String day, double cost, int adults, int kids)
	{
		rooms.add(new RoomBooking(date, roomInfo, day, cost, adults, kids));
	}
	/**Returns room information for verification*/
	public String getCancel()
	{
		return bookingName+resNum;
	}
	/**Checks if advanced purchasing*/
	public boolean aP()
	{
		if(ap.equals("YES"))
		{
			return true;
		}
		return false;
	}
	/**Returns and removes total when refunded*/
	public String getTot()
	{
		double temp = cost;
		cost = 0;
		for(int x = 0; x < rooms.size(); x++)
		{
			rooms.get(x).removeCost();
		}
		return String.format("%.2f", temp);
	}
	/**Gets and returns date a room is booked*/
	public GregorianCalendar getDate()
	{
		return rooms.get(0).getDate();
	}
	/**Returns check in information*/
	public String getCheck()
	{
		return check;
	}
	/**changes check in information*/
	public void changeCheck(String x)
	{
		check = x;
	}
	/**Outputs options for selection*/
	public String getOptions()
	{
		return bookingName+", "+resNum;
	}
	/**Applies discount*/
	public void applyDiscount(double d)
	{
		discount = (1-d)*100;
		cost = cost*d;
		for(int x = 0; x < rooms.size(); x++)
		{
			rooms.get(x).applyDiscount(d);
		}
	}

}
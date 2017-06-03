import java.util.*;
public class Hotel
{
	private String type;
	private ArrayList<Room> rooms = new ArrayList<Room>();
	private int filePlacement;
	private int roomPlacement = 0;
	private ArrayList<Integer> availableRoomPlacement = new ArrayList<>();
	public Hotel(String[] details, int filePlacement)
	{
		type = details[0];
		rooms.add(new Room(details, roomPlacement++));
		this.filePlacement = filePlacement;
	}
	public void addNewRoom(String[] details)
	{
		rooms.add(new Room(details, roomPlacement++));
	}
	public String toString()
	{
		String out = ""+type+"\n";
		for(int x = 0; x < rooms.size() ; x++)
		{
			out += "\t"+rooms.get(x)+"\n";
		}
		return out;
	}
	public String toString(int t, int a)
	{
		String[] arr = new String[2];
		int temp;
		String out = ""+type+"\n";
		for(int x = 0; x < rooms.size() ; x++)
		{
			arr = rooms.get(x).toString(t, a).split("SplitHere");
			out += "\t"+(x+1)+")"+arr[1]+"\n";
			temp = Integer.parseInt(arr[0]);
			availableRoomPlacement.add(temp);
		}
		return out;
	}
	/**Returns hotel type*/
	public String getHotelType()
	{
		return type;
	}
	/**Returns where in the arraylist a room is saved*/
	public int roomChoice(int x)
	{
		return availableRoomPlacement.get(x);
	}
	/**Clears the rooms array to update it*/
	public void clearAvailableRooms()
	{
		availableRoomPlacement.clear();
	}
	/**Gets and returns max Children*/
	public int maxC(int x)
	{
		return rooms.get(x).getMaxChildren();
	}
	/**Gets and returns max adults*/
	public int maxA(int x)
	{
		return rooms.get(x).getMaxAdults();
	}
	/**Gets and returns room type*/
	public String getBookingInfo(int x)
	{
		return rooms.get(x).getRoomInfo();
	}
	/**Gets and returns rooms total*/
	public double getRoomTotal(int x)
	{
		return rooms.get(x).getTotal();
	}
	/**returns cost of a specific day*/
	public double dailyCost(int x, int y)
	{
		return rooms.get(x).dailyCost(y);
	}
	/**Gets and returns a given day*/
	public String day(int x, int y)
	{
		return rooms.get(x).day(y);
	}
	/**returns that amount of rooms in hotal*/
	public int roomCount()
	{
		return rooms.size();
	}
}
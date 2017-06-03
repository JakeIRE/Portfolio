public class Room
{
	private String roomType;
	private int amount;
	private String oMin;
	private String oMax;
	private double[] costs = new double[7];
	private int available;
	private int roomPlacement;
	private int oMaxA;
	private int oMaxC;
	private double tot;
	public Room(String[] details, int roomPlacement)
	{
		this.roomPlacement = roomPlacement;
		roomType = details[1];
		amount = Integer.parseInt(details[2]);
		oMin = details[3];
		oMax = details[4];
		String[] temp = details[4].split("\\+");
		oMaxA = Integer.parseInt(temp[1]);
		oMaxC = Integer.parseInt(temp[0]);
		costs[0] = Double.parseDouble(details[11]);
		costs[1] = Double.parseDouble(details[5]);
		costs[2] = Double.parseDouble(details[6]);
		costs[3] = Double.parseDouble(details[7]);
		costs[4] = Double.parseDouble(details[8]);
		costs[5] = Double.parseDouble(details[9]);
		costs[6] = Double.parseDouble(details[10]);
		available = amount;
	}
	public String toString()
	{
		if(available > 0)
		{
			return roomType+"  Max occupancy:  "+ oMax + "  Daily Average: " + String.format("%.2f",getAverage());
		}
		return "";
	}
	public String toString(int t, int a)
	{
		if(available > 0)
		{
			tot = 0;
			String out = roomPlacement+"SplitHere"+roomType + "  Max occupancy:  "+ oMax + "  Total Cost: ";
			for(int x = t, y = 0; x < 7 && y < a ; x++, y++)
			{
				tot = tot + costs[x];
				if(x == 6)
				{
					x = -1;
				}
			}
			return out + String.format("%.2f",tot);
		}
		return "";
	}
	/**Returns max child occupancy*/
	public int getMaxChildren()
	{
		return oMaxA;
	}
	/**returns max adult occupancy*/
	public int getMaxAdults()
	{
		return oMaxC;
	}
	/**Gets average cost for a cleaner menu*/
	public double getAverage()
	{
		double total = 0;
		for(int x = 0; x < 7; x++)
		{
			total = total + costs[x];
		}
		return total/7;
	}
	/**Returns room type*/
	public String getRoomInfo()
	{
		return roomType;
	}
	/**Returns total*/
	public double getTotal()
	{
		return tot;
	}
	/**Returns daily cost*/
	public double dailyCost(int y)
	{
		return costs[y-1];
	}
	/**Returns a day*/
	public String day(int y)
	{
		String d = "";
		if(y == 7)
		{
			d =  "Sun";
		}
		else if(y == 1)
		{
			d =  "Mon";
		}
		else if(y == 2)
		{
			d =  "Tues";
		}
		else if(y == 3)
		{
			d = "Wed";
		}
		else if(y == 4)
		{
			d = "Thurs";
		}
		else if(y == 5)
		{
			d =  "Fri";
		}
		else if(y == 6)
		{
			d =  "Sat";
		}
		return d;
	}
}
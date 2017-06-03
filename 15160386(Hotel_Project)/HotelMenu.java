import java.util.*;
import java.io.*;
public class HotelMenu
{
	private ArrayList<Hotel> l4details = new ArrayList<Hotel>();
	private ArrayList<Guest> booking = new ArrayList<Guest>();
	/**Starts and reads in files*/
	public HotelMenu() throws IOException
	{
		File csv = new File("l4Hotels.csv");
		File csv2 = new File("Bookings.csv");
		if(csv.exists() && csv.length()>0 && csv2.exists())
		{
			bookingReader(csv2);
			fileReader(csv);	
		}
	}
	/**Runs the menu*/
	public void run() throws IOException
	{
		File csv2 = new File("Bookings.csv");
		Scanner in = new Scanner(System.in);
		boolean correct = false;
		String temp = "";
		while(!correct && !temp.equalsIgnoreCase("q"))
		{
			String put = "";
			System.out.println(put+"Enter 'q' to quit at any time\nSelect login type:\n(1)Customer\n(2)Staff\n(3)Supervisor");
			temp = in.nextLine();
			if(temp.matches("[0-9]"))
			{
				correct = true;
			}
			put = "Enter a valid number\n";
		}
		if(!temp.equalsIgnoreCase("q"))
		{
			int login = Integer.parseInt(temp);
			String username, pass;
			if (login == 1)
			{
				customer();
			}
			else if(login == 2)
			{
				if(verify(new File("staff.txt")))
				{
					staff();
				}	
			}
			else if(login == 3)
			{
				if(verify(new File("supervisor.txt")))
				{
					supervisor();
				}
			}
			else
			{
				System.out.println("Please enter either '1', '2' or '3' for your selection");
			}
			fileWriteRes(csv2,"");
		}
	}
	/**Reads in hotel info*/
	public void fileReader(File csv) throws IOException
	{
		Scanner in = new Scanner(csv);
		int x = -1;
		String str;
		String[] temp;
		str = in.nextLine();
		str = in.nextLine();
		while (in.hasNext())
		{
			str = in.nextLine();
			temp = str.split(",");
			if (!(temp[0].equals("")))
			{
				l4details.add(new Hotel(temp, x));
				x++;
			}
			else
			{
				l4details.get(x).addNewRoom(temp);
			}
		}
		in.close();
	}
	/**verifies login for employees*/
	public boolean verify(File details) throws IOException
	{
		Console console = System.console();
		boolean found = false;
		String str, u, p;
		String[] arr = new String[2];
		Scanner in = new Scanner(System.in);
		System.out.println("Enter Username: ");
		u = in.nextLine();
		p = new String(console.readPassword("Enter Password: "));
		in = new Scanner(details);
		while(in.hasNext() && !found)
		{
			str = in.nextLine();
			arr = str.split(",");
			if(arr[0].equals(u) && arr[1].equals(p))
			{
				found = true;
			}
		}
		in.close();
		return found;
	}
	/**approves customer options*/
	public void customer() throws IOException
	{
		int x = getOptions(new Customer());
		if(x == 1)
		{
			makeReservation();
		}
		else if(x == 2)
		{
			cancelReservation();
		}
		else if(x == 309)
		{
		}
		else
		{
			System.out.println("Please make a valid selection, '1' or '2'");
		}
	}
	/**approves staff options*/
	public void staff() throws IOException
	{
		int x = getOptions(new Staff());
		if(x == 1)
		{
			makeReservation();
		}
		else if(x == 2)
		{
			cancelReservation();
		}
		else if(x == 3)
		{
			checkInOutServices();
		}
		else if(x == 309)
		{
		}
		else
		{
			System.out.println("Please make a valid selection, '1', '2' or '3'");
		}
	}
	/**approves supervisors options*/
	public void supervisor() throws IOException
	{
		int x = getOptions(new Supervisor());
		if(x == 1)
		{
			makeReservation();
		}
		else if(x == 2)
		{
			cancelReservation();
		}
		else if(x == 3)
		{
			checkInOutServices();
		}
		else if(x == 4)
		{
			applyDiscount();
		}
		else if(x == 309)
		{
		}
		else
		{
			System.out.println("Please make a valid selection, '1', '2', '3' or '4'");
		}
	}
	/**displays various options for differing authority levels*/
	public int getOptions(Object choice)
	{
		boolean correct = false;
		String temp = "";
		while(!correct && !temp.equalsIgnoreCase("q"))
		{
			String put = "";
			Scanner in = new Scanner(System.in);
			System.out.println(put + choice);
			temp = in.nextLine();
			if(temp.matches("[0-9]"))
			{
				correct = true;
			}
			put = "Enter a valid number\n";
		}
		if(temp.equalsIgnoreCase("q"))
		{
			temp = "309";
		}
		return Integer.parseInt(temp);
	}
	/**Make a reservation*/
	public void makeReservation()
	{
		int x;
		Scanner in = new Scanner(System.in);
		String temp;
		System.out.println("Enter full name: ");
		temp = in.nextLine();
		if(!temp.equalsIgnoreCase("q"))
		{
			String name = temp;
			System.out.println("Enter amount of days you'll be staying: ");
			temp = in.nextLine();
			if(!temp.equalsIgnoreCase("q"))
			{
				if(temp.matches("[0-9]+"))
				{
					int days = Integer.parseInt(temp);
					System.out.println("What date will you be checking in (dd/mm/yyyy): ");
					temp = in.nextLine();
					if(!temp.equalsIgnoreCase("q"))
					{
						if(temp.matches("[0-9]+/[0-9]+/[0-9]+"))
						{
							String[] dateIn = temp.split("/");
							int[] dateVals = {Integer.parseInt(dateIn[0]), Integer.parseInt(dateIn[1]), Integer.parseInt(dateIn[2])};
							boolean dateValid = true;
							GregorianCalendar inDate = new GregorianCalendar(dateVals[2], dateVals[1] - 1, dateVals[0]);
							if(dateVals[1] == 2 && dateVals[0] > 29 && inDate.isLeapYear(dateVals[2]))
							{
								dateValid = false;
							}
							else if(dateVals[1] == 2 && dateVals[0] > 28 && !(inDate.isLeapYear(dateVals[2])))
							{
								dateValid = false;
							}
							else if(dateVals[1] > 12)
							{
								dateValid = false;
							}
							else if(dateVals[0] > 30 && (dateVals[1] == 9 || dateVals[1] == 6 ||dateVals[1] == 4 ||dateVals[1] == 1))
							{
								dateValid = false;
							}
							else if(dateVals[0] > 31)
							{
								dateValid = false;
							}
							if (dateValid)
							{
								GregorianCalendar today = new GregorianCalendar();
								if(!(inDate.before(today)))
								{
									System.out.println("How many rooms will be required: ");
									temp = in.nextLine();
									if(temp.matches("[0-9]+"))
									{
										int roomCount = Integer.parseInt(temp);
										System.out.println("Advanced Purchasing?\n1) Yes\n2) No ");
										temp = in.nextLine();
										if(temp.matches("[0-9]+"))
										{
											int apC = Integer.parseInt(temp);
											double mp = 1;
											String ap = "";
											if (apC == 2)
											{
												ap = "NO";
											}
											else if (apC == 1)
											{
												ap = "YES";
												mp = .95;
											}
											int[] roomInfo = new int[roomCount];
											int theDay = inDate.get(Calendar.DAY_OF_WEEK);
											theDay--;
											boolean correct = false;
											while(!correct && !temp.equalsIgnoreCase("q"))
											{
												for(x = 0; x < l4details.size() ;x++)
												{
													int temp2 = x+1;
													System.out.println("("+temp2+"). "+l4details.get(x));
												}
												temp = in.nextLine();
												if(temp.matches("[0-9]+"))
												{
													correct = true;
												}
												else
												{
													System.out.println("Please make a valid selection");
												}
											}
											int star = Integer.parseInt(temp);
											star = star - 1;
											String hotelType = l4details.get(star).getHotelType();
											int maxA = 0, maxC = 0;
											String[] roomDet = new String[roomCount];
											double tot = 0;
											int[] adults = new int[roomCount];
											int[] kids = new int[roomCount];
											int rChoice = 0;
											correct = false;
											for(x = 0; x < roomCount ; x++)
											{
												if(!temp.equalsIgnoreCase("q"))
												{
													while(!correct && !temp.equalsIgnoreCase("q"))
													{
														System.out.println(l4details.get(star).toString(theDay, days));
														temp = in.nextLine();
														if(temp.matches("[0-9]+"))
														{
															correct = true;
															rChoice = Integer.parseInt(temp);
															rChoice--;
															if(!(rChoice >= 0) || !(rChoice<l4details.get(star).roomCount()))
															{
																correct = false;
																System.out.println("Please make a valid selection");
															}
														}
														else 
														{
															System.out.println("Please make a valid selection");
														}
														
														if(rChoice >= 0 && rChoice < l4details.get(star).roomCount())
														{
															
														}
													}
													correct = false;
													while(!correct && !temp.equalsIgnoreCase("q"))
													{
														System.out.println("How many Adults will be staying in this room: ");
														temp = in.nextLine();
														if(!temp.equalsIgnoreCase("q"))
														{
															if(temp.matches("[0-9]+"))
															{
																correct = true;
															}
															else 
															{
																System.out.println("Please make a valid selection");
															}
														}
													}
													correct = false;
													adults[x] = Integer.parseInt(temp);
													while(!correct && !temp.equalsIgnoreCase("q"))
													{
														System.out.println("How many children will be staying in this room: ");
														temp = in.nextLine();
														if(temp.matches("[0-9]+"))
														{
															correct = true;
														}
														else 
														{
															System.out.println("Please make a valid selection");
														}
													}
													if(!temp.equalsIgnoreCase("q"))
													{
														correct = false;
														kids[x] = Integer.parseInt(temp);
														roomInfo[x] = l4details.get(star).roomChoice(rChoice);
														maxA = l4details.get(star).maxA(roomInfo[x]);
														maxC = l4details.get(star).maxC(roomInfo[x]);
														System.out.println(""+maxA+", "+maxC);
														if(adults[x] > maxA || kids[x] > maxC)
														{
															x--;
															System.out.println("Exceeds maximum occupancy!");
														}
														else
														{
															roomDet[x] = l4details.get(star).getBookingInfo(roomInfo[x]);
															tot = tot + l4details.get(star).getRoomTotal(roomInfo[x]);
															l4details.get(star).clearAvailableRooms();
														}
													}
												}
											}
											if(!temp.equalsIgnoreCase("q"))
											{
												tot = (tot*mp);
												String[] strtemp = new String[2];
												int resnum = (int) (Math.random()*10000)+1;
												booking.add(new Guest(name, resnum, days, tot, hotelType, ap));
												for(x = 0;x < days; x++)
												{	
													for(int y = 0; y < roomCount ; y++)
													{	
														booking.get((booking.size()-1)).addMoreRooms(inDate, roomDet[y], l4details.get(star).day(roomInfo[y], inDate.get(Calendar.DAY_OF_WEEK)), (l4details.get(star).dailyCost(roomInfo[y], inDate.get(Calendar.DAY_OF_WEEK))*mp), adults[y], kids[y]);
													}
													dateVals[0]++;
													inDate = new GregorianCalendar(dateVals[2],dateVals[1] - 1,dateVals[0]);
												}
												System.out.println("Room successfully booked heres your reservation number,it will be required to change the booking  "+resnum);
											}
										}
									}
								}
								else
								{
									System.out.println("Cannot book a hotel room in the past!");
								}
							}
							else
							{
								System.out.println("invalid date!");
							}
						}
						else
						{
							System.out.println("Enter valid date format");
						}
					}
				}
			}
			else
			{
				System.out.println("Enter a number");
			}
		}
	}
	/**Method to cancel a reservation*/
	public void cancelReservation() throws IOException
	{
		Scanner in = new Scanner(System.in);
		String temp = "";
		System.out.println("Enter reservation name(In Full): ");
		temp = in.nextLine();
		if(!temp.equalsIgnoreCase("Q"))
		{
			String name = temp;
			System.out.println("Enter reservation number: ");
			temp = in.nextLine();
			if(!temp.equalsIgnoreCase("Q"))
			{
				name += temp;
				boolean found = false;
				for(int x = 0;x < booking.size() && !(found);x++)
				{
					if(name.equalsIgnoreCase(booking.get(x).getCancel()))
					{
						found = true;
						if(booking.get(x).aP())
						{
							System.out.println("Advanced purchase cannot be refunded\nBooking cancelled");
						}
						else
						{
							GregorianCalendar date = booking.get(x).getDate();
							GregorianCalendar today = new GregorianCalendar();
							GregorianCalendar date48h = new GregorianCalendar(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH)-2);
							if(today.before(date48h))
							{
								System.out.println("You have been refunded "+booking.get(x).getTot());
							}
						}
						cancelWrite(x);
					}
				}
				if (!(found))
				{
					System.out.println("Booking not found");
				}
			}
		}
	}
	/**Method to manage check-ins/outs and no- shows*/
	public void checkInOutServices() throws IOException
	{
		int x, c;
		Scanner in = new Scanner(System.in);
		System.out.println("Select a booking to use): ");
		String temp = "";
		for(x = 0;x < booking.size();x++)
		{
			System.out.println("("+(x+1)+") "+booking.get(x).getOptions());
		}
		temp = in.nextLine();
		if(!temp.equalsIgnoreCase("Q"))
		{
			if(temp.matches("[0-9]+"))
			{
				x = Integer.parseInt(temp) - 1;
				if(booking.get(x).getCheck().equals("Checked-in"))
				{
					System.out.println("Customer checked out?\n(1)Yes\n(2)No");
					temp = in.nextLine();
					if(!temp.equalsIgnoreCase("Q"))
					{
						if(temp.matches("[0-9]"))
						{
							c = Integer.parseInt(temp);
							if(c == 1)
							{
								booking.get(x).changeCheck("Checked-out");
								cancelWrite(x);
							}
						}
						else
						{
							System.out.println("Invalid selection");
						}
					}
				}
				else
				{
					System.out.println("1) Customer checking in\n2) Customer didn't show up\n");
					temp = in.nextLine();
					if(!temp.equalsIgnoreCase("Q"))
					{
						if(temp.matches("[0-9]"))
						{
							c = Integer.parseInt(temp);
							if(c == 1)
							{
								booking.get(x).changeCheck("Checked-in");
							}
							else if (c == 2)
							{
								booking.get(x).changeCheck("no-show");
								cancelWrite(x);
							}
						}
						else
						{
							System.out.println("Invalid selection");
						}
					}
				}
			}
			else
			{
				System.out.println("Invalid selection");
			}
		}
	}
	/**Method to apply discount*/
	public void applyDiscount() throws IOException
	{
		int x;
		String temp;
		Scanner in = new Scanner(System.in);
		System.out.println("Select a booking to apply discount to: ");
		for(x = 0;x < booking.size();x++)
		{
			System.out.println("("+(x+1)+") "+booking.get(x).getOptions());
		}
		temp = in.nextLine();
		if(!temp.equalsIgnoreCase("Q"))
		{
			if(temp.matches("[0-9]+"))
			{
				x = Integer.parseInt(temp) - 1;
				System.out.println("Enter a percentage discount to apply: ");
				temp = in.nextLine();
				if(!temp.equalsIgnoreCase("Q"))
				{
					if(temp.matches("[0-9]+"))
					{
						double temp2 = Double.parseDouble(temp);
						temp2 = 1 - (temp2/100);
						booking.get(x).applyDiscount(temp2);
					}
					else
					{
						System.out.println("Invalid selection");
					}
				}
			}
			else
			{
				System.out.println("Invalid selection");
			}

		}
	}
	/**Writes the bookings to reservation file*/
	public void fileWriteRes(File csv, String format) throws IOException
	{
		FileWriter writer = new FileWriter(csv);
		writer.write(format);
		for(int x = 0; x < booking.size();x++)
		{
			writer.write(booking.get(x).writeFile());
		}
		writer.flush();
		writer.close();
	}
	/**Writes to cancellation file*/
	public void cancelWrite(int z) throws IOException
	{
		File csv = new File("TaxInfo.csv");
		PrintWriter writer = new PrintWriter(new FileWriter(csv, true));
		writer.append(booking.get(z).writeFile()+"\n");
		writer.close();
		booking.remove(z);
	}
	/**Reads from bookings file*/
	public void bookingReader(File csv) throws IOException
	{
		Scanner in = new Scanner(csv);
		String input;
		String[] str;
		String[] dateTemp;	
		int y =-1;
		while (in.hasNext())
		{
			input = in.nextLine();
			str = input.split(",");
			dateTemp = str[8].split("/");
			if(!(str[0].equals("")))
			{
				y++;
				booking.add(new Guest(str[0], Integer.parseInt(str[1]), str[2], Integer.parseInt(str[3]), Double.parseDouble(str[4]), str[5], str[6], Double.parseDouble(str[7]), new GregorianCalendar(Integer.parseInt(dateTemp[2]), Integer.parseInt(dateTemp[1]) - 1, Integer.parseInt(dateTemp[0])), str[9], str[10], Double.parseDouble(str[11]), Integer.parseInt(str[12]), Integer.parseInt(str[13])));
			}
			else
			{
				booking.get(y).addMoreRooms(new GregorianCalendar(Integer.parseInt(dateTemp[2]), Integer.parseInt(dateTemp[1]) - 1, Integer.parseInt(dateTemp[0])), str[9], str[10], Double.parseDouble(str[11]), Integer.parseInt(str[12]), Integer.parseInt(str[13]));
			}
		}
		in.close();
	}
}
import java.util.ArrayList;
import java.util.Iterator;
/**
 * M250-22J Question 2
 *
 * Hotel creates a virtual hotel and uses the Room object to
 * add rooms, check room bills, look for vacancies,
 * checks for matching rooms and removes a active room.
 * 
 * @author Charlie Beavers
 * @version 23.10.2022
 */

/**
 * Question 2.b
 * Adds the fields rooms and name
 */
public class Hotel
{
    private ArrayList<Room> rooms;
    private String name;
    
    /**
     * Question 2.c
     * Constructor for Hotel objects that sets the values of the
     * corresponding fields.
     */
    public Hotel(String aName) {
        name = aName;
        rooms = new ArrayList<>();
    }

    /**
     * Question 2.d
     * Adds some unoccupied test rooms to the hotel
     */
    public void addRooms()
    {
        int i = 10;
        while (i < 19) {
            String number = "" + i;
            double rate;

            if(i % 3 == 1) {
                number += "A";
                rate = 100;
            }
            else if (i % 3 == 2) {
                number += "B";
                rate = 180;
            }
            else
            {
                number += "C";
                rate = 250;
            }

            Room r = new Room("", number, rate);

            rooms.add(r);
            i++;
        }
    }

    /**
     * Question 2.e
     * Calculates the bill for a room being vacated
     */
    public double calculateBill(Room roomName, int numberOfDays)
    {
        double cost = roomName.getDailyRate();
        if(numberOfDays < 4) {
            cost = cost * numberOfDays;
        } else {
            cost = (cost * numberOfDays) * 0.9;
        }
        return cost;
    }

    /**
     * Question 2.f
     * Returns a list of free rooms specific to the room type
     */
    public ArrayList<Room> getMatchingRooms(String roomType)
    {
        ArrayList<Room> unoccupiedRooms = new ArrayList<>();
        for(Room list : rooms) {
            String roomNumbers = list.getNumber();
            String roomTypes = list.getType();
            double roomRate = list.getDailyRate();
            String unoccupied = list.getGuest();
            if(roomTypes.contains(roomType) && unoccupied == ""){
                Room r = new Room(unoccupied, roomNumbers, roomRate);
                unoccupiedRooms.add(r);
            }
        }
        return unoccupiedRooms;
    }

    /**
     * Question 2.g
     * Returns the number of vacancies in the hotel
     */
    public int vacancies() {
        int hotelVacancies = 0;
        int singleRoom = getMatchingRooms("Single").size();
        int doubleRoom = getMatchingRooms("Double").size();
        int familyRoom = getMatchingRooms("Family").size();
        return hotelVacancies = singleRoom + (doubleRoom * 2) + (familyRoom * 4);
    }

    /**
     * Question 2.h
     * Checks for identical room numbers and sets the guest name to guest if found
     */
    public void bookRoom(Room matchingNum){        
        int index = 0;
        for(Room list : rooms) {
            String listNum = list.getNumber();
            String matchNum = matchingNum.getNumber();
            double rate = list.getDailyRate();
            if(listNum.contains(matchNum)){
                rooms.set(index, new Room("Guest", listNum, rate));
            }
            index ++;
        }
    }

    /**
     * Question 2.i
     * Takes a room out of service if it is found
     */
    public void removeRoom(String roomNum){
        boolean index = true;
        Iterator<Room> it = rooms.iterator();
        while(it.hasNext()) {
            Room r = it.next();
            String rooms = r.getNumber();
            if(rooms.equals(roomNum)) {
                it.remove();
                index = false;
            }
        }
        if(index == true) {
            System.out.println("Room " + roomNum + " not found!");
        }
    }
}

--------------------------------------------------------------------------------------

/**
 * M250-22J Question 1
 * 
 * Room creates a virtual room in a hotel, enabling it to
 * set a guests name, a rooms number and the price of the
 * room per day.
 * It will also be able to perform some checks like if a room
 * available, does the room number meet our requirements, what
 * room types we have and a description of each room.
 *
 * @author Charlie Beavers
 * @version 23.10.2022
 */

/**
 * Question 1.b
 * Adds the fields guest, number, and dailyRate
 */
public class Room
{
    private String guest;
    private String number;
    private double dailyRate;

    /**
     * Question 1.c
     * Constructor for Room objects that sets the values of the
     * corresponding fields.
     */
    public Room(String aGuest, String aNumber, double aRate)
    {
        guest = aGuest;
        number = aNumber;
        dailyRate = aRate;
    }

    /**
     * Question 1.d.i
     * Return the name of the guest
     */
    public String getGuest()
    {
        return guest;
    }

    /**
     * Question 1.d.i
     * Return the room number of the guest
     */
    public String getNumber()
    {
        return number;
    }

    /**
     * Question 1.d.i
     * Return the daily rate of the room
     */
    public double getDailyRate()
    {
        return dailyRate;
    }

    /**
     * Question 1.d.ii
     * Set the name of the guest
     */
    public void setGuest(String aGuest)
    {
        guest = aGuest;
    }

    /**
     * Question 1.d.iii
     * Set the daily rate of the room
     */
    public void setDailyRate(double aRate)
    {
        dailyRate = aRate;
    }

    /**
     * Question 1.e
     * Check to see if the room is available using
     * the guests name
     */
    public boolean isAvailable()
    {
        if(guest.length() == 0) {
            return true;
        } 
        else {
            return false;
        }
    }

    /**
     * Question 1.f
     * Check to make sure the room number is three characters
     * long and starts with a number between 01 to 99 and ends
     * with the letter A B or C e.g. 27A or 81C
     */
    public boolean verifyRoom()
    {
        if(number.length() == 3) {
            int num1 = number.charAt(0);
            int num2 = number.charAt(1);
            int letter = number.charAt(2);
            boolean check = false;

            if(num1 > 47 && num1 < 58
            && num2 > 47 && num2 < 58
            && letter > 64 && letter < 68) {
                check = true;

                if(num1 == 48 && num2 == 48) {
                    check = false;
                }
            }
            return check;
        }
        else {
            return false;
        }
    }

    /**
     * Question 1.g
     * Check the 3rd character of the room number and return
     * "Single" if it equals "A", "Double if it equals "B"
     * otherwise return "Family"
     */
    public String getType()
    {
        if(number.charAt(2) == 65) {
            return "Single";
        } 
        else if(number.charAt(2) == 66) {
            return "Double";
        }  
        else {
            return "Family";
        }
    }

    /**
     * Question 1.h
     * Returns a string that describes the room in the
     * following format "rooms type, rooms number, if its
     * available and the guest's name if applicable.
     */
    public String description()
    {
        String desc = getType() + " room " + getNumber() + " ";

        if(isAvailable() == true) {
            return desc = desc + "(available) " + getGuest();
        } 
        else {
            return desc = desc + "(reserved) " + getGuest();
        }
    }
}    
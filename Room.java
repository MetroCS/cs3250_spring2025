
/**
 * The Room class will represent places the player can be and travel between. 
 */
public class Room //extends Focusable
{

    /**
     * A list of rooms that are adjacent to the current Room.
     * Thought should be put into the type of list used here, as there could be
     * QoL benefit to reordering the list based on what room the player last
     * visted, or maybe the order being consistent no matter where you entered
     * from is preferable.
     */
    private Room[] adjacentRooms;

    /**
     * A list of all features contained in the room.
     * Again, thought to what list type this is and why is advised.
     * If we want to have a monster roaming around that can be interacted with
     * in some way, these lists will need to change in size.
     */
    // private Feature[] features;


    /**
     * Note: When the Focusable interface is fleshed out, the description will
     * be removed from this class and the field from Focusable will be used.
     * 
     * This field should contain details about what is in this room.
     */
    private String description;

    /**
     * This field should contain a 1-3 word name of the room, used when other
     * rooms are describing their adjascent rooms.
     * Note: This is not a unique ID
     */
    private String tagline;

    /**
     * A unique ID used to reference Rooms without needing completely valid
     * object references.
     * 
     * May deprecate, deprecates the Util class
     */
    private int ID;

    /**
     * 
     * @param tagline
     * @param description
     */

    Room(String tagline, String description)
    {
        this.tagline = tagline;
        this.description = description;
        this.ID = Util.getNewID();
    }

    public String getDescription()
    {
        return this.description;
    }

    public void describe()
    {
        sopl(this.getDescription());
    }

    public String getTagLine() { return this.tagline; }

    public int getID() { return this.ID; }
 
    private static void sopl(String s) { System.out.println(s); }

    private static void sop(String s){ System.out.print(s); }
}
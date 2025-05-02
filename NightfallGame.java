import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This is an example Main class for the purposes of expressing how I think
 * the engine will function. The main() does not actually implement anything of
 * consequence but does express the general flow in a block comment in the 
 * play() method. 
 */
public class NightfallGame implements Game
{
    /**
     * Field to store the game's name.
     */
    private String gameName = "Nightfall";

    /**
     * A list of all Rooms in the game.
     * For the running game, there is little need for novel rooms to be created
     * or eleted at runtime, just loaded from file, so I think that loading
     * into an array is preferable to a dynamic structure.
     * For a separate file that allows us to define to-be-serialized Rooms via
     * commandline, a dynamic structure will be necessary.
     */
    private Room[] allRooms;


    /**
     * The currentRoom is vital to the logic of the engine.
     * At any one time, the player must be in one and only one place, and they
     * need to have location described to them.
     */
    private Room currentRoom;

    /**
     * The focus variable is key for allowing a player to focus on an
     * interactable object within a Room, and avoid having to bake all
     * functionality directly into a the concept of a Room.
     * That said, a room can be the focus of the player if they are not
     * looking at a Feature or are in a room without any Features.
     * 
     * This is likely to be added back in, but is overly complicated at this
     * time.
     */
    //private Focusable focus = new Room("Garbage In, Garbage Out");

    NightfallGame()
    {
        // For now, create some basic rooms for testing purposes, speaking of
        // which tests first. This style is not ideal, but (de)serializing
        // objects will take a lot of work to build.
        // NOTE:: The indentation of the """ strings should NOT BE CHANGED as
        //        this indents the output for clarity.
        this.allRooms = new Room[]
        {
            new Room("Room A",
                """
                    This is the CS Student Lounge.
                    The room contains the 9 tables, 23 chairs, and several
                    students.
                    Little of consequence is present, though you notice at
                    least one student is looking at you at all times.
                """
            ),
            new Room("Room B",
                """
                    This is a hallway.
                    There is someone at the far end, staring at a laptop.
                    Odds are they have no information for you, but they may be
                    watching you.
                """
            ),
            new Room("Room C",
                """
                    This is a classroom.
                    There are more than a dozen tables and twice as many
                    chairs.
                    There are no people in the room.
                    Something happened here, it shouldn't have happened, but it
                    did.
                    There are no physical signs remaining, but the feeling in
                    your stomach sinks lower.
                """
            )
        };
    }

    private static void sopl(String s) { System.out.println(s); }

    private static void sop(String s){ System.out.print(s); }

    public String getName() {return this.gameName; }

    
    public Optional<Integer> play()
    {
        // Start Up

        // DEV-NOTE The following is a simple print utility to avoid typing the
        // full line every time.
        sopl(

        """
        Greetings and Welcome to Nightfall.
        This tech demo has been created as the major project of a class on
        Software Development.
        At this phase of development, the game does not accomplish anything
        aside from presenting this message to you, dear player.
        Please check in again soon, as development will continue.
        """
        );

        // Room objects do not yet exist, so they cannot be loaded
        // Note the first room in the loaded list will always be assumed to be
        // the starting room.
        if (allRooms == null)
        {
            return Optional.empty();
        }

        currentRoom = allRooms[0];

        boolean playerQuits = false;

        while (!playerQuits)
        {
            // Note API exists to allow a room to describe itself and to supply
            // its description to other objects. If managing printing with an
            // outside class that does formatting, the latter may be
            // preferrable.
            currentRoom.describe();
            
            
            // Temporary: exit to ensure the program run and exits safely.
            playerQuits = true;
        }

        // Additional thought should be given to how we score the game. 
        return Optional.of(1);

        /**
         * NOTE:: Any mention of features/focusables are not necessary to
         * represent a graph of rooms.
         * Pseudo:
         * 
         * Print Greeting message X
         * Load Room objects (hand written or read from file)
         * Locate starting room and assign to currentRoom and to current
         * Focusable.
         * Complete any other load tasks
         * Wait to begin game until user inputs that they are ready 
         * (Press Enter to begin)
         * 
         * Loop Start:
         *      if currentRoom != focus
         *          describe focus
         *          ONCE IMPLEMENTED list options on Feature
         *      else
         *          describe currentRoom
         *          if currentRoom has ZERO features
         *              state 'nothing of interest'
         *          else 
         *              list features
         *          list adjascent rooms
         *      
         *      Take user input line
         *      Parse user input (it is always evil)
         *      if user input is valid for input scheme
         *          if user input matches adjascent room option
         *              set tempRoom to adjascent room from currentRoom
         *              set currentRoom to tempRoom
         *              set focus to currentRoom
         *          if user input matches feature interaction
         *              call on feature object to respond to action
         *              ?? Does interacting with a feature ever make sense kick
         *              the player back to the room as a focus
         * 
         *      ??How to actually or practically clear the console? Does it 
         *          make more sense to leave the previous text available in the
         *          console for player reference?
         *              
         * 
         */
    }   

    public Room[] getAllRooms() {
        return null;
    }

}

/**
 * The Room class will represent places the player can be and travel between. 
 */
class Room //extends Focusable
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
    private String[] description;

    private int state = 0;

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

    Room(String tagline, String description) {
        this.tagline = tagline;
        this.description = new String[] {description};
        this.ID = Util.getNewID();
    }

    Room(String tagline, String[] description) {
        this.tagline = tagline;
        this.description = description;
        this.ID = Util.getNewID();
    }

    public void forceState(int in) throws IndexOutOfBoundsException {
        if (in < 0 || in > this.description.length) {
            throw new IndexOutOfBoundsException();
        } else {
            this.state = in;
        }
    }



    public String getDescription()
    {
        return this.description[state];
    }

    public void describe()
    {
        String desc = this.getDescription();
        sopl(
            "Room: "+ this.getTagLine()
            + System.lineSeparator()
            + desc
            );
    }

    public String getTagLine() { return this.tagline; }

    public int getID() { return this.ID; }
 
    private static void sopl(String s) { System.out.println(s); }

    private static void sop(String s){ System.out.print(s); }
}

class Doorway {
    
}

class Util
{
    private static AtomicInteger ID_COUNTER;

    static
    {
        ID_COUNTER = new AtomicInteger(1000);
    }

    public static int getNewID()
    {
        return ID_COUNTER.getAndIncrement();
    }
}
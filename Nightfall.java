
/**
 * This is an example Main class for the purposes of expressing how I think
 * the engine will function. The main() does not actually implement anything of
 * consequence but does express the general flow in a block comment. 
 */
public class Nightfall // implements Game
{

    /**
     * A list of all Rooms in the game.
     * For the running game, there is little need for novel rooms to be created
     * or eleted at runtime, just loaded from file, so I think that loading
     * into an array is preferable to a dynamic structure.
     * For a separate file that allows us to define to-be-serialized Rooms via
     * commandline, a dynamic structure will be necessary.
     */
    private static Room[] allRooms;


    /**
     * The currentRoom is vital to the logic of the engine.
     * At any one time, the player must be in one and only one place, and they
     * need to have location described to them.
     */
    private static Room currentRoom;

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

    /**
     * This is a static code block in Java, a tool I learned about recently and
     * think will benefit this sort of project. Off of the bat, we will need to
     * manually create the data for rooms and features, but serializing objects
     * into files will be a key speed up step for implementing written story
     * ideas. 
     */
    static
    {
        // Create or read from file all rooms and contained features in a loop
    }

    
    public static void main(String[] args)
    {

        // Start Up

        // DEV-NOTE The following is a simple print utility to avoid typing the full line every time.
        sopl(

        """
        Greetings and Welcome to Nightfall.
        This tech demo has been created as the major project of a class on Software Development.
        At this phase of development, the game does not accomplish anything aside from presenting this message to you, dear player.
        Please check in again soon, as development will continue.
        """

        );

        // Room objects do not yet exist, so they cannot be loaded
        // Note the first room in the loaded list will always be assumed to be the starting room.

        if (allRooms == null)
        {
            System.exit(1);
        }

        currentRoom = allRooms[0];

        boolean playerQuits = false;

        while (!playerQuits)
        {
            // Note API exists to allow a room to describe itself and to supply its description to other objects.
            // If managing printing with an outside class that does formatting, the latter may be preferrable.
            currentRoom.describe();
            
            
            // Temporary: exit to ensure the program run and exits safely.
            playerQuits = true;
        }

        System.exit(0);

        /**
         * NOTE:: Any mention of features/focusables are not necessary to represent a graph of rooms.
         * Pseudo:
         * 
         * Print Greeting message X
         * Load Room objects (hand written or read from file)
         * Locate starting room and assign to currentRoom and to current Focusable.
         * Complete any other load tasks
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
         *              ?? Does interacting with a feature ever make sense kick the player back to the room as a focus
         * 
         *      ??How to actually or practically clear the console? Does it make more sense to leave the previous text 
         *          available in the console for player reference?
         *              
         * 
         */
    }


    private static void sopl(String s) { System.out.println(s); }

    private static void sop(String s){ System.out.print(s); }
    
}
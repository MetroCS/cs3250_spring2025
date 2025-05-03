# Nightfall Tasks

This document is intended as a register of tasks/User Stories that ought be completed specifically for the development of nightfall, as Issues can be created within the forked repo. 

The vision of the game is to present the player with a space they can move around and interact with things in.

In order to do this, several things need to happen (not ordered):

1. Rooms should have multiple descriptions based on the state of the Room.
2. Rooms should have connections to each other, relying on another class, perhaps called Doorway.
3. A game loop will need to be written to accept user input and determine how to operate on the relevant variables.


## More detail

1. Variable Room descriptions, for simplicity, should be dependent on a single internal field that determines which of some number of descriptions are used, likely drawn from an array stored in each Room. Debug methods can then be used to force the state to test all description correctness.

2. (Note: The initial version of the Room class does not do this.) My though processes for implementing a door way class is this: We find ourselves in a catch-22 if we try to define the first Room object instantiated containing references to the second and third (as they are not yet instantiated). To side step this: Rooms are instantiated without reference to each other, Doorways are then instantiated with reference to two adjascent Rooms, and Doorways are then registered with the Rooms they contain, ensuring that a Room can indirectly reference any of it's adjascent Rooms. See 3. for more.

3. The Game Loop is the most important piece of the puzzle, as the structure defines how the user will be prompted and how to respond. For the simplest version, the loop needs to accept user input, interpretting input as 1-indexed actions (Room changes or other actions in future, namely actions that change the description of a Room). Based on the adjascent Room selected, the loop (depending on the logic of the Doorway class) will need to request from the current Room object a reference to the adjascent Room the user wishes to travel to, and set the current Room pointer to the supplied room. Without massively complex branching, this allows us to make a web of rooms and move between them programmatically.
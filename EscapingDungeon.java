import java.util.*;

public class EscapingDungeon {

    public static void main(String[] args){

        int[] swordHeuristic = {3,3,3,0};
        int[] keyHeuristic = {3,0,3,3};
        int[] maskHeuristic = {3,3,0,3};
        int[] totalCost = {2,2,2,2}; // initially, total cost equals the fixed cost.

        Scanner sc = new Scanner(System.in);

        // The story
        System.out.println("\n");
        System.out.println("Hello There!");
        System.out.println("\n");
        System.out.println("You have been chosen to aid our adventurer in trying to find his way out of this dark dungeon.");
        System.out.println("\n");
        System.out.println("The adventurer has been opening doors, fighting monsters, climbing up and down stairs and unlocking gates.");
        System.out.println("He has reached a room with nothing but a series of stairs. ");
        System.out.println("\n");
        System.out.println("After climbing up the stairs, he finds himself in the center of a dark room with 4 doors. ");
        System.out.println("The door to the east is guarded by a humongous, monstrous creature!");
        System.out.println("The door to the west has a metal lock on it. ");
        System.out.println("The door to the north looks unlocked. ");
        System.out.println("Last but not least, the door to the south has some kind of green gas surrounding it.");
        System.out.println("He needs to get out of this room! He needs to find a way to open one of these doors, maybe it will lead him out of the dungeon.");
        System.out.println("\n");
        System.out.println("There's a bag on his right.");
        System.out.println("He finds an enormous sword, a metal key and a gas mask.");
        System.out.println("\n");
        System.out.println("Help him pick an item to aid him! You can choose an item from the bag or choose nothing.");
        System.out.println("What will you pick?");


        // User's input
        String item = sc.nextLine();
        int itemsChosenCount = 0;
        item = item.toLowerCase();


        // A*: adding the inital/fixed cost to the heuristic cost and choosing the optimum path

        // Adding the heuristic cost to the fixed cost according to input:

        // Total cost for choosing the sword
        if(item.contains("sword")){
            itemsChosenCount++;

            for(int i=0; i<totalCost.length; i++){
                totalCost[i] += swordHeuristic[i];
            }
            System.out.println("\nYou have the Sword.");
        }


        // Total cost for choosing the gas mask
        if(item.contains("mask") || item.contains("gas")){
            itemsChosenCount++;

            for(int i=0; i<totalCost.length; i++){
                totalCost[i] += maskHeuristic[i];
            }
            System.out.println("\nYou have the Gas Mask.");
        }


        // Total cost for choosing the key
        if(item.contains("key")){
            itemsChosenCount++;

            for(int i=0; i<totalCost.length; i++){
                totalCost[i] += keyHeuristic[i];
            }
            System.out.println("\nYou have the Key");
        }


        // Total cost for choosing all items in the bag
        if(item.contains("bag") || item.contains("all")){
            itemsChosenCount = 3;

            for(int i=0; i<totalCost.length; i++){
                totalCost[i] = keyHeuristic[i] + maskHeuristic[i] + swordHeuristic[i];
            }
            System.out.println("\nYou have the bag and everything in it.");
        }


        // Total cost for choosing nothing. Same as inital/fixed cost
        if(item.contains("nothing") && itemsChosenCount==0){
            System.out.println("\nYou chose nothing.");
        }


        if(!item.contains("nothing") && itemsChosenCount==0){
            System.out.println("\nYou did not enter anything useful. The adventurer takes nothing.");
        }
//            System.out.println("Please check your input!"); //edit: allow user to re enter item


        // Choosing optimum path:
        // The optimum path is the path with the least cost
        // If more than 1 open path have the same cost, choose the first (preceeding order)
        int smallest = 0;
        for ( int i = 1; i < totalCost.length; i++ )
        {
            if ( totalCost[i] < totalCost[smallest] ) smallest = i;
        }


        // Continuing the story
        // if he chooses nothing: he can not open any doors except the north one. Not a goal (trap door) -> loses the game
        // if he chooses key: he can unlock and  open the west door
        // if he chooses gas mask: he can go through the poisonous gas and open the south door
        // if he chooses the sword: he can kill the monster and open the east door
        // if he chooses more than one item: he opens the easiest door (door with smaller index)

        switch (smallest){
            case 1: System.out.println("\nWe've got the key to the west door!\nThe Adventurer unlocks and opens the door.\n \nWell done! We've escaped the dungeon!"); break;
            case 2: System.out.println("\nThe adventurer puts on the mask.\nHe goes through the green gas.\nThis smells really bad!\nHe is able to open the door!\n \nWell done! We've escaped the dungeon!"); break;
            case 3: System.out.println("\nBrave choice!\n \nThe adventurer marches forward,\nFights the monster,\nThe monster is dead!\nHe passes the dead body, opens the door and Voila!\n \nWell done! We've escaped the dungeon! "); break;
            default: System.out.println("\nOh! The adventurer has nothing.\n \nHe can not face the monster, go through the deadly gas, or unlock the locked key using his bare hands.\n \nHe walks to the north door.\nOh no! It's a trap!\nIt's not a real door!\n \nThe monster rushes towards him.\nThe adventurer is killed :(\n \nGame Over."); break;
        }
    }

}

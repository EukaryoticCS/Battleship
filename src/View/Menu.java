package View;

public class Menu {
    MyInput input = new MyInput();
    
    public static void welcome(){
        System.out.println(" Welcome to BattleShips");

        System.out.println("                                  )___(");
        System.out.println("                           _______/__/_");
        System.out.println("                  ___     /===========|   ___");
        System.out.println(" ____       __   [\\\\\\]___/____________|__[///]   __");
        System.out.println(" \\   \\_____[\\\\]__/___________________________\\__[//]___");
        System.out.println("  \\ 40A                  U.S.S Maine                   |");
        System.out.println("   \\                                                  /");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public void PlaceShips() {

        String[] ships = {"Carrier", "Battleship", "Cruiser", "Submarine", "Destroyer"}; //This might need to be a hashmap instead -- Mod 4

        for (int i = 0; i < ships.length; i++) { //Loop through each ship
            //Take in coordinate
            do {
                String inputCoordinate = input.GetUserStr("Where would you like to place your " + ships[i] + "? Enter a coordinate (e.g. D5):", true);
                System.out.println(inputCoordinate);
            } while (!ValidCoordinate());

            //parse to something like [4][5] -- Mod2b

            String inputDirection;
            do {
                inputDirection = input.GetUserStr("Would you like to place that ship (V)ertically down or (H)orizontally right from that position?", true);
                System.out.println(inputDirection);
            } while (!(inputDirection.equals("V") || inputDirection.equals("H"))); //Validate "V" or "H"
            //Take in 'V' or 'H', build ship accordingly
        }
    }

    public boolean ValidCoordinate() {
        //Insert coordinate parsing/validation
        return true;
    }

}

import java.util.Scanner;
import java.util.Random;

public class battleShips {

    private static int[][] ocean = new int[10][10];
    private static int playersShips = 5;
    private static int compsShips = 5;

    public static void main(String[] args) {
        System.out.println("**** Welcome to Battle Ships game ****\n");
        System.out.println("Right now, the sea is empty.\n");
        printOcean(ocean);

        Scanner cords = new Scanner(System.in);
        for (int i = 1; i < 6; i++) {
            System.out.print("Enter X coordinate for your " + i + ". ship: ");
            int x = cords.nextInt();
            System.out.print("Enter Y coordinate for your " + i + ". ship: ");
            int y = cords.nextInt();
            if (x >= 0 && x <= 9 && y >= 0 && y <= 9) {
                if (ocean[x][y] == 0) {
                    ocean[x][y] = 1;
                }
                else {
                    System.out.println("There is your other ship, choose valid coordinates.");
                    --i;
                }
            }
            else {
                    System.out.println("You have to put your ship on the grid, choose valid coordinates.");
                    --i;
                }
        }
        System.out.println();

        printOcean(ocean);

        System.out.println("Computer is deploying ships");
        for (int i = 1; i < 6; i++) {
            Random rand = new Random();
            int comCordsX = rand.nextInt(10);
            int comCordsY = rand.nextInt(10);
            if (ocean[comCordsX][comCordsY] == 0) {
                ocean[comCordsX][comCordsY] = 2;
                System.out.println(i + ". ship DEPLOYED");
            }
            else {
                --i;
            }
        }

        System.out.println("---------------------------------------\n");

        do {
            playersTurn();
            computersTurn();
            System.out.println();
            printOcean(ocean);
            System.out.println("Your ships: " + playersShips + " | Computer ships: " + compsShips);
            System.out.println("-----------------------------------------------------------\n");
        } while (playersShips != 0 && compsShips != 0);

        if (playersShips == 0) {
            System.out.println("Oh no! You loose the battle :(");
        }
        else if (compsShips == 0) {
            System.out.println("Hooray! You won the battle :)");
        }

    }

    public static void printOcean(int[][] o) {
        System.out.println("  0123456789");
        for (int i = 0; i < 10; i++) {
            System.out.print(i + "|");

            for (int j = 0; j < 10; j++) {
                if ((o[i][j] == 0) || o[i][j] == 2 || o[i][j] == 6) {
                    System.out.print(" ");
                }
                else if (o[i][j] == 1) {
                    System.out.print("@");
                }
                else if (o[i][j] == 4) {
                    System.out.print("!");
                }
                else if (o[i][j] == 3) {
                    System.out.print("x");
                }
                else if (o[i][j] == 5) {
                    System.out.print("-");
                }
            }

            System.out.println("|" + i);
        }

        System.out.println("  0123456789\n");
    }

    public static void playersTurn() {
        System.out.println("YOUR TURN");
        Scanner moves = new Scanner(System.in);
        int x;
        int y;
        do {
            System.out.print("Enter X coordinate: ");
            x = moves.nextInt();
            System.out.print("Enter Y coordinate: ");
            y = moves.nextInt();
        } while (x < 0 || x > 9 || y < 0 || y > 9);

        System.out.println();

        if (ocean[x][y] == 2) {
            System.out.println("Boom! You sunk the ship!");
            ocean[x][y] = 4;
            compsShips--;
        }
        else if (ocean[x][y] == 1) {
            System.out.println("Oh no, you sunk your own ship :(");
            ocean[x][y] = 3;
            playersShips--;
        }
        else {
            System.out.println("Sorry, you missed");
            ocean[x][y] = 5;
        }
    }

    public static void computersTurn() {
        System.out.println("COMPUTER'S TURN");
        Random rand = new Random();
        int cSX;
        int cSY;
        do {
            cSX = rand.nextInt(10);
            cSY = rand.nextInt(10);
        } while (ocean[cSX][cSY] == 6 || ocean[cSX][cSY] == 4 || ocean[cSX][cSY] == 3 || ocean[cSX][cSY] == 5);

        if (ocean[cSX][cSY] == 1) {
            System.out.println("The Computer sunk one of your ships!");
            ocean[cSX][cSY] = 3;
            playersShips--;
        }
        else if (ocean[cSX][cSY] == 2) {
            System.out.println("The Computer sunk one of its own ships");
            ocean[cSX][cSY] = 4;
            compsShips--;
        }
        else {
            System.out.println("Computer missed");
            ocean[cSX][cSY] = 6;
        }
    }
}




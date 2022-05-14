import java.util.Scanner;

public class admiralSunk {
    Scanner input = new Scanner(System.in);
    static int[][] HumanSeaArea;
    static int[][] CompSeaArea;

    public static void Intro(){
        System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_");
        System.out.println("Welcome to the BATTLESHIP GAME ");
        System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_");
        System.out.println("* You have 5 ships (sizes are 1, 2, 3, 4 and 5)");
        System.out.println("* Also computer has 5 ships.");
        System.out.println("* You try to shoot computers ship.");
        System.out.println("* Computer tries to shoot your ships.");
        System.out.println("* Whoever first hits the other's all ships, wins.");
        System.out.println("* If shooting is successful, you will see there '*'");
        System.out.println("* If shooting is not successful, you wil see 'x' for missing ");
        System.out.println("* Start the game, your turn");
        System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_");

    }

    public static void bothHumanComputerPlayers(int turn) {

        HumanSeaArea = new int[10][10];
        CompSeaArea = new int[10][10];

        System.out.println("Human player's ships");
        for (int i = 1; i <= 5; i++) {
            generateHumanShips(i);
        }
        drawHUMANShips();
        System.out.println("Computer player's ships");
        for (int i = 1; i <= 5; i++) {
            generateComputerShips(i);
        }
        drawCOMPShips();

        // for turning the turn
        while (true) {
            switch (turn) {
                case 1:
                    humanPlayer();
                    turn = 2;
                    System.out.println(" ");
                    break;
                case 2:
                    computerPlayer();
                    turn = 1;
                    System.out.println(" ");
            }
        }
    }

    public static void computerPlayer() {
        System.out.println("--------------");
        System.out.println("Computer turn");
        System.out.println("--------------");
        int row;
        int column;
        do {
            row = (int) (Math.random() * 9);
            System.out.println("row: " + row);
            column = (int) (Math.random() * 9);
            System.out.println("column: " + column);
            if (HumanSeaArea[row][column] == 1) { // check the random place is empty or not, if there is a ship there, put "*"
                System.out.println("Computer shot your ship");
                HumanSeaArea[row][column] = '*';
                drawHUMANShips(); // draw new game area (with row and column)
                break;

            } else if (HumanSeaArea[row][column] == 0) { // check the random place is empty or not, if there is no ship there, put "x"
                System.out.println("Computer could not shoot your ship");
                HumanSeaArea[row][column] = 'x';
                drawHUMANShips(); // draw new game area (with row and column)
                break;
            }
        } while (row < 10 && column < 10);
    }

    public static void humanPlayer() {
        System.out.println("------------");
        System.out.println("human turn");
        System.out.println("------------");
        Scanner input = new Scanner(System.in);
        int row;
        int column;
        do {
            System.out.println("Enter row :");
            row = input.nextInt();
            System.out.println("Enter column :");
            column = input.nextInt();
            if (CompSeaArea[row][column] == 1) { // check the random place is empty or not, if there is a ship there, put "*"
                System.out.println("You shot the computer's ship");
                CompSeaArea[row][column] = '*';
                drawCOMPShips(); // draw new game area (with entered row and entered column)
                break;

            } else if (CompSeaArea[row][column] == 0) { // check the random place is empty or not, if there is no ship there, put "x"
                System.out.println("You could not shoot the ship");
                CompSeaArea[row][column] = 'x';
                drawCOMPShips(); // draw new game area (with entered row and entered column)
                ;
                break;
            }
        } while (row < 10 && column < 10);
    }

    public static void drawHUMANShips() {
        // for drawing human player's ships
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (HumanSeaArea[i][j] == 0) {
                    System.out.print(" ."); // no ships
                } else if (HumanSeaArea[i][j] == 1) {
                    System.out.print(" o"); // there is a ship
                } else if (HumanSeaArea[i][j] == '*') {
                    System.out.print(" *");
                } else if (HumanSeaArea[i][j] == 'x') {
                    System.out.print(" x");
                }
            }
            System.out.println();
        }
    }

    public static void drawCOMPShips() {
        // for drawing computer player's ships
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (CompSeaArea[i][j] == 0) {
                    System.out.print(" ."); // no ships
                } else if (CompSeaArea[i][j] == 1) {
                    System.out.print(" o"); // there is a ship
                } else if (CompSeaArea[i][j] == '*') {
                    System.out.print(" *");
                } else if (CompSeaArea[i][j] == 'x') {
                    System.out.print(" x");
                }
            }
            System.out.println();
        }
    }

    public static void generateHumanShips(int shipSize) {
        // for thee generating human player's ships and check that there will be at least one space between ships !!

        int x;
        int y;
        boolean isDrawn = false;
        while (!isDrawn) { // x = row// y = column
            x = (int) (Math.random() * 9);
            y = (int) (Math.random() * 9);

            if ((x + 1 <= 9) && (y + 1 <= 9) && (x - 1 >= 0) && (y - 1 >= 0) &&
                    (HumanSeaArea[x][y] == 0) && (HumanSeaArea[x + 1][y] == 0) &&
                    (HumanSeaArea[x - 1][y] == 0) && (HumanSeaArea[x][y + 1] == 0) && (HumanSeaArea[x][y - 1] == 0)) {
                int horizontalORvertical = (int) (Math.random() * 2); // to decide horizontally or vertically
                if (horizontalORvertical == 0) { // ship will be drawn vertically
                    // 0 means vertical
                    boolean drawToDown = true;
                    boolean drawToUp = true;

                    boolean isAvailable = true;
                    for (int i = 1; i <= shipSize; i++) {
                        if ((y + i > 9) || x + i < 9 || (HumanSeaArea[y + i][x] != 0) || HumanSeaArea[y][x] != 0 ||
                                (y + i + 1 <= 9 && HumanSeaArea[y + i + 1][x] != 0) ||
                                (HumanSeaArea[y + i][x + 1] != 0) || (HumanSeaArea[y + i][x - 1] != 0)) {
                            isAvailable = false;
                            drawToDown = false;
                            break;
                        }
                    }
                    if (!isAvailable) {
                        for (int i = 1; i <= shipSize; i++) {
                            if ((y - i < 0) || x - i > 0 || HumanSeaArea[y][x] != 0 || (HumanSeaArea[y - i][x] != 0) ||
                                    (y - i - 1 >= 0 && HumanSeaArea[y - i - 1][x] != 0) || (HumanSeaArea[y - i][x + 1] != 0)
                                    || (HumanSeaArea[y - i][x - 1] != 0)) {
                                isAvailable = false;
                                drawToUp = false;
                                break;
                            }
                        }
                    }
                    if (drawToDown) {
                        for (int i = y; i <= y + shipSize - 1; i++) {
                            HumanSeaArea[i][x] = 1;
                            isDrawn = true;
                        }

                    } else if (drawToUp) {
                        for (int i = y; i >= y - shipSize + 1; i--) {
                            HumanSeaArea[i][x] = 1;
                            isDrawn = true;
                        }
                    }

                } else if (horizontalORvertical == 1) { // ship will be drawn horizontally
                    // 1 means horizontal
                    boolean drawToRight = true;
                    boolean drawToLeft = true;

                    boolean isAvailable = true;
                    for (int i = 1; i <= shipSize; i++) {
                        if ((x + i > 9) || y + i < 0 || HumanSeaArea[y][x] != 0 || HumanSeaArea[y][x + i] != 0 ||
                                (HumanSeaArea[y + 1][x] != 0) || (HumanSeaArea[y - 1][x] != 0)
                                || (x + i + 1 <= 9 && HumanSeaArea[y][x + i + 1] != 0) || HumanSeaArea[y][x - 1] != 0 || HumanSeaArea[y - 1][x + i] != 0 ||
                                HumanSeaArea[y + 1][x + i] != 0) {
                            isAvailable = false;
                            drawToRight = false;
                            break;
                        }
                    }

                    if (!isAvailable) {
                        for (int i = 1; i <= shipSize; i++) {
                            if ((x - i < 0) || HumanSeaArea[y][x] != 0 || (x - i - 1 >= 0 && HumanSeaArea[y][x - i - 1] != 0) ||
                                    HumanSeaArea[y][x + 1] != 0 || (HumanSeaArea[y + 1][x] != 0) || (HumanSeaArea[y - 1][x] != 0) ||
                                    HumanSeaArea[y][x - i] != 0 || HumanSeaArea[y - 1][x - i] != 0 || HumanSeaArea[y + 1][x - i] != 0) {
                                isAvailable = false;
                                drawToLeft = false;
                                break;
                            }
                        }
                    }
                    if (drawToRight) {
                        for (int i = x; i <= x + shipSize - 1; i++) {
                            HumanSeaArea[y][i] = 1;
                            isDrawn = true;
                        }

                    } else if (drawToLeft) {
                        for (int i = x; i >= x - shipSize + 1; i--) {
                            HumanSeaArea[y][i] = 1;
                            isDrawn = true;
                        }
                    }
                }
            }
        }
    }

    public static void generateComputerShips(int shipSize) {
        // for thee generating computer's ships and check that there will be at least one space between ships !!
        int x;
        int y;
        boolean isDrawn = false;

        while (!isDrawn) { // x = row// y = column
            x = (int) (Math.random() * 9);
            y = (int) (Math.random() * 9);

            if (shipSize == 1) {
                CompSeaArea[x][y] = 1;
                isDrawn = true;

            } else if ((x + 1 <= 9) && (y + 1 <= 9) && (x - 1 >= 0) && (y - 1 >= 0) &&
                    (CompSeaArea[x][y] == 0) && (CompSeaArea[x + 1][y] == 0) &&
                    (CompSeaArea[x - 1][y] == 0) && (CompSeaArea[x][y + 1] == 0) && (CompSeaArea[x][y - 1] == 0)) {
                int horizontalORvertical = (int) (Math.random() * 2); // to decide horizontally or vertically
                if (horizontalORvertical == 0) { // ship will be drawn vertically
                    // 0 means vertical
                    boolean drawToDown = true;
                    boolean drawToUp = true;

                    boolean isAvailable = true;
                    for (int i = 1; i <= shipSize; i++) {
                        if ((y + i > 9) || x + i < 9 || (CompSeaArea[y + i][x] != 0) || CompSeaArea[y][x] != 0 ||
                                (y + i + 1 <= 9 && CompSeaArea[y + i + 1][x] != 0) ||
                                (CompSeaArea[y + i][x + 1] != 0) || (CompSeaArea[y + i][x - 1] != 0)) {
                            isAvailable = false;
                            drawToDown = false;
                            break;
                        }
                    }
                    if (!isAvailable) {
                        for (int i = 1; i <= shipSize; i++) {
                            if ((y - i < 0) || x - i > 0 || CompSeaArea[y][x] != 0 || (CompSeaArea[y - i][x] != 0) ||
                                    (y - i - 1 >= 0 && CompSeaArea[y - i - 1][x] != 0) || (CompSeaArea[y - i][x + 1] != 0)
                                    || (CompSeaArea[y - i][x - 1] != 0)) {
                                isAvailable = false;
                                drawToUp = false;
                                break;
                            }
                        }
                    }
                    if (drawToDown) {
                        for (int i = y; i <= y + shipSize - 1; i++) {
                            CompSeaArea[i][x] = 1;
                            isDrawn = true;
                        }

                    } else if (drawToUp) {
                        for (int i = y; i >= y - shipSize + 1; i--) {
                            CompSeaArea[i][x] = 1;
                            isDrawn = true;
                        }
                    }

                } else if (horizontalORvertical == 1) { // ship will be drawn horizontally
                    // 1 means horizontal
                    boolean drawToRight = true;
                    boolean drawToLeft = true;

                    boolean isAvailable = true;
                    for (int i = 1; i <= shipSize; i++) {
                        if ((x + i > 9) || y + i < 0 || CompSeaArea[y][x] != 0 || CompSeaArea[y][x + i] != 0 ||
                                (CompSeaArea[y + 1][x] != 0) || (CompSeaArea[y - 1][x] != 0)
                                || (x + i + 1 <= 9 && CompSeaArea[y][x + i + 1] != 0) || CompSeaArea[y][x - 1] != 0 || CompSeaArea[y - 1][x + i] != 0 ||
                                CompSeaArea[y + 1][x + i] != 0) {
                            isAvailable = false;
                            drawToRight = false;
                            break;
                        }
                    }

                    if (!isAvailable) {
                        for (int i = 1; i <= shipSize; i++) {
                            if ((x - i < 0) || CompSeaArea[y][x] != 0 || (x - i - 1 >= 0 && CompSeaArea[y][x - i - 1] != 0) ||
                                    CompSeaArea[y][x + 1] != 0 || (CompSeaArea[y + 1][x] != 0) || (CompSeaArea[y - 1][x] != 0) ||
                                    CompSeaArea[y][x - i] != 0 || CompSeaArea[y - 1][x - i] != 0 || CompSeaArea[y + 1][x - i] != 0) {
                                isAvailable = false;
                                drawToLeft = false;
                                break;
                            }
                        }
                    }
                    if (drawToRight) {

                        for (int i = x; i <= x + shipSize - 1; i++) {
                            CompSeaArea[y][i] = 1;
                            isDrawn = true;
                        }
                    } else if (drawToLeft) {
                        for (int i = x; i >= x - shipSize + 1; i--) {
                            CompSeaArea[y][i] = 1;
                            isDrawn = true;
                        }
                    }
                }
            }
        }
    }

    public static void Sea() {
        HumanSeaArea = new int[10][10];
        CompSeaArea = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(" .");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // I printed both human player's and computer's ships to check that shooting or missing are working or not
        Intro();
        bothHumanComputerPlayers(1);

    }
}

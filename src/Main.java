import javax.management.monitor.GaugeMonitorMBean;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    //static int[][] board = new int[15][15];

    // error situation
    /*
  01|02|03|04|05|06|07|08|09|10|11|12|13|14|15|
01  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |
02  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |
03  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |
04  |  |  |  | X|  |  |  |  |  |  |  |  |  |  |
05  |  |  |  |  | X|  |  |  |  |  |  |  |  |  |
06  |  |  | X| O| O| O|  | X|  |  |  |  |  |  |
07  |  |  |  |  | X| X| O|  |  |  |  |  |  |  |
08  |  |  |  |  | O| X| X|  |  |  |  |  |  |  |
09  |  |  |  |  | O| X|  |  |  |  |  |  |  |  |
10  |  |  |  |  | O| O|  |  |  |  |  |  |  |  |
11  |  |  |  |  | O|  | X|  |  |  |  |  |  |  |
12  |  |  |  | O| X|  | O|  |  |  |  |  |  |  |
13  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |
14  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |
15  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |

    solved at 2019.10.11 20:00

     */

    static int[][] board = {
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,1,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0},
            {0,0,0,1,2,2,2,0,1,0,0,0,0,0,0},
            {0,0,0,0,0,1,1,2,0,0,0,0,0,0,0},
            {0,0,0,0,0,2,1,1,0,0,0,0,0,0,0},
            {0,0,0,0,0,2,1,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,2,2,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,2,0,1,0,0,0,0,0,0,0},
            {0,0,0,0,2,1,0,2,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
    };
    public static void main(String[] args) throws InterruptedException {
        System.out.println("This CLI only used in testing...");

        //int res = randChess(randomBoard);
        //int[] scores = ChessEngine.evaluateSituation(board, 0);
        //printBoard(randomBoard);

        //List<ChessEngine.Point> points = ChessEngine.getFreePoints(board, 1);
        //for(ChessEngine.Point n : points) System.out.println(n);

        //ChessEngine.Point p = ChessEngine.getNextStep(board, 1);
        //System.out.println(p);

        /* main test*/

        //AI_VS_AI();
        Player_VS_AI();


        /**/

        //ChessEngine.Point point = ChessEngine.getNextStep(board, 1);

        //System.out.println(point);


        //System.out.println(scores[0] + "," + scores[1]);
        //System.out.println(ChessEngine.isAnyoneWin(board));
    }

    public static void Player_VS_AI() {
        String choice = "";
        System.out.println("Please choose the first player: ");
        System.out.println("#1. You");
        System.out.println("#2. AI");
        int[][] GameBoard = new int[15][15];
        Scanner scanner = new Scanner(System.in);
        choice = scanner.next();
        if (choice.equals("1")) {
            printBoard(GameBoard);
            while (true) {
                System.out.println("Enter the point: ");
                String point = scanner.next();
                if (point.equals("quit")) break;
                String[] x_y_String = point.split(",");
                int[] x_y = new int[2];
                x_y[0] = Integer.parseInt(x_y_String[0]);
                x_y[1] = Integer.parseInt(x_y_String[1]);
                GameBoard[x_y[0] - 1][x_y[1] - 1] = 1;
                printBoard(GameBoard);
                if (ChessEngine.isAnyoneWin(GameBoard) != 0) break;
                System.out.println("AI is thinking...");
                ChessEngine.Point AIStep = ChessEngine.getNextStep(GameBoard, 2);
                GameBoard[AIStep.x][AIStep.y] = 2;
                printBoard(GameBoard);
                if (ChessEngine.isAnyoneWin(GameBoard) != 0) break;
            }
        } else if (choice.equals("2")) {
            printBoard(GameBoard);
            boolean isFirstChess = true;
            while (true) {
                System.out.println("AI is thinking...");
                if (!isFirstChess) {
                    ChessEngine.Point AIStep = ChessEngine.getNextStep(GameBoard, 1);
                    GameBoard[AIStep.x][AIStep.y] = 1;
                } else {
                    GameBoard[7][7] = 1;
                    isFirstChess = false;
                }
                printBoard(GameBoard);
                if (ChessEngine.isAnyoneWin(GameBoard) != 0) break;
                System.out.println("Enter the point: ");
                String point = scanner.next();
                if (point.equals("quit")) break;
                String[] x_y_String = point.split(",");
                int[] x_y = new int[2];
                x_y[0] = Integer.parseInt(x_y_String[0]);
                x_y[1] = Integer.parseInt(x_y_String[1]);
                GameBoard[x_y[0] - 1][x_y[1] - 1] = 2;
                printBoard(GameBoard);
                if (ChessEngine.isAnyoneWin(GameBoard) != 0) break;
            }
        }

        if (ChessEngine.isAnyoneWin(GameBoard) == 1) {
            System.out.println("###### Black Win ! ######");
        } else {
            System.out.println("###### White Win ! ######");
        }
    }

    public static void AI_VS_AI() throws InterruptedException {
        int[][] GameBoard = new int[15][15];
        boolean isFirstChess = true;
        int currentChess = 1;
        while(true) {
            if (!isFirstChess) {
                ChessEngine.Point AIStep = ChessEngine.getNextStep(GameBoard, currentChess);
                GameBoard[AIStep.x][AIStep.y] = currentChess;
                currentChess = ((currentChess == 1) ?  2 : 1);
                printBoard(GameBoard);
                Thread.sleep(1000);
            } else {
                GameBoard[7][7] = 1;
                isFirstChess = false;
                currentChess = ((currentChess == 1) ?  2 : 1);
                printBoard(GameBoard);
                Thread.sleep(1000);
            }
        }
    }

    public static int randChess(int[][] board) {
        int res;
        while (true) {
            int x;
            int y;
            do {
                x = (int)(Math.random() * 15);
                y = (int)(Math.random() * 15);
            } while (board[x][y] != 0);
            board[x][y] = 1;
            if ((res = ChessEngine.isAnyoneWin(board)) != 0) break;
            do {
                x = (int)(Math.random() * 15);
                y = (int)(Math.random() * 15);
            } while (board[x][y] != 0);
            board[x][y] = 2;
            if ((res = ChessEngine.isAnyoneWin(board)) != 0) break;
        }
        return res;
    }

    // 0 - blank, 1 - AI, 2 - Player

    public static void printBoard(int[][] board) {
        System.out.print("  ");
        for (int i = 0; i < 15; i++)
            System.out.print((i + 1) >= 10 ? (i + 1) + "|" : "0" + (i + 1) + "|");
        System.out.println();
        for (int i = 0; i < 15; i++) {
            System.out.print(i + 1 >= 10 ? i + 1  : "0" + (i + 1));
            for (int j = 0;j < 15; j++) {
                switch (board[i][j]) {
                    case 0:
                        System.out.print("  |");
                        break;
                    case 1:
                        System.out.print(" X|");
                        break;
                    case 2:
                        System.out.print(" O|");
                        break;
                }
            }
            System.out.println();
        }
    }
}

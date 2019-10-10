import javax.management.monitor.GaugeMonitorMBean;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    //static int[][] board = new int[15][15];
    static int[][] board = {
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,1,1,1,0,1,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,2,2,0,0,2,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
    };
    public static void main(String[] args) throws IOException {
        System.out.println("This CLI only used in testing...");
        int[][] GameBoard = new int[15][15];
        //int res = randChess(randomBoard);
        //int[] scores = ChessEngine.evaluateSituation(board, 0);
        //printBoard(randomBoard);

        //List<ChessEngine.Point> points = ChessEngine.getFreePoints(board, 1);
        //for(ChessEngine.Point n : points) System.out.println(n);


        String choice = "";
        System.out.println("Please choose the first player: ");
        System.out.println("#1. You");
        System.out.println("#2. AI");

        Scanner scanner = new Scanner(System.in);
        choice = scanner.next();
        if (choice.equals("1")) {
            printBoard(GameBoard);
            while (ChessEngine.isAnyoneWin(GameBoard) == 0) {
                System.out.println("Enter the point: ");
                String point = scanner.next();
                if (point.equals("quit")) break;
                String[] x_y_String = point.split(",");
                int[] x_y = new int[2];
                x_y[0] = Integer.parseInt(x_y_String[0]);
                x_y[1] = Integer.parseInt(x_y_String[1]);
                GameBoard[x_y[0] - 1][x_y[1] - 1] = 1;
                printBoard(GameBoard);
                System.out.println("AI is thinking...");
                ChessEngine.Point AIStep = ChessEngine.getNextStep(GameBoard, 2);
                GameBoard[AIStep.x][AIStep.y] = 2;
                printBoard(GameBoard);
            }
        } else if (choice.equals("2")) {
            printBoard(GameBoard);
            boolean isFirstChess = true;
            while (ChessEngine.isAnyoneWin(GameBoard) == 0) {
                System.out.println("AI is thinking...");
                if (!isFirstChess) {
                    ChessEngine.Point AIStep = ChessEngine.getNextStep(GameBoard, 1);
                    GameBoard[AIStep.x][AIStep.y] = 1;
                } else {
                    GameBoard[7][7] = 1;
                    isFirstChess = false;
                }
                printBoard(GameBoard);
                System.out.println("Enter the point: ");
                String point = scanner.next();
                if (point.equals("quit")) break;
                String[] x_y_String = point.split(",");
                int[] x_y = new int[2];
                x_y[0] = Integer.parseInt(x_y_String[0]);
                x_y[1] = Integer.parseInt(x_y_String[1]);
                GameBoard[x_y[0] - 1][x_y[1] - 1] = 2;
                printBoard(GameBoard);
            }
        }

        //ChessEngine.Point point = ChessEngine.getNextStep(board, 1);

        //System.out.println(point);


        //System.out.println(scores[0] + "," + scores[1]);
        //System.out.println(ChessEngine.isAnyoneWin(board));
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

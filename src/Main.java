import java.util.Arrays;
import java.util.List;

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
    public static void main(String[] args) {
        System.out.println("This CLI only used in testing...");
        //int[][] randomBoard = new int[15][15];
        //int res = randChess(randomBoard);
        //int[] scores = ChessEngine.evaluateSituation(board, 0);
        //printBoard(randomBoard);

        //List<ChessEngine.Point> points = ChessEngine.getFreePoints(board, 1);
        //for(ChessEngine.Point n : points) System.out.println(n);

        ChessEngine.Point point = ChessEngine.getNextStep(board, 1);

        System.out.println(point);


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
        for (int i = 0; i < 15; i++) {
            for (int j = 0;j < 15; j++) {
                switch (board[i][j]) {
                    case 0:
                        System.out.print(" |");
                        break;
                    case 1:
                        System.out.print("X|");
                        break;
                    case 2:
                        System.out.print("O|");
                        break;
                }
            }
            System.out.println();
        }
    }
}

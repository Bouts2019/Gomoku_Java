import javax.management.monitor.GaugeMonitorMBean;
import java.io.IOException;
import java.util.*;

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
    public static String BookStr = "寒星局:H8,H9,H10\n" +
            "溪月局:H8,H9,I10|H8,H9,G10\n" +
            "疏星局:H8,H9,J10|H8,H9,F10\n" +
            "花月局:H8,H9,I9|H8,H9,G9\n" +
            "残月局:H8,H9,J9|H8,H9,F9\n" +
            "雨月局:H8,H9,I8|H8,H9,G8\n" +
            "金星局:H8,H9,J8|H8,H9,F8\n" +
            "松月局:H8,H9,H7\n" +
            "丘月局:H8,H9,I7|H8,H9,G7\n" +
            "新月局:H8,H9,J7|H8,H9,F7\n" +
            "瑞星局:H8,H9,H6\n" +
            "山月局:H8,H9,I6|H8,H9,G6\n" +
            "游星局:H8,H9,J6|H8,H9,F6\n" +
            "长星局:H8,I9,J10\n" +
            "峡月局:H8,I9,J9|H8,I9,I10\n" +
            "恒星局:H8,I9,J8|H8,I9,H10\n" +
            "水月局:H8,I9,J7|H8,I9,G10\n" +
            "流星局:H8,I9,J6|H8,I9,F10\n" +
            "云月局:H8,I9,I8|H8,I9,H9\n" +
            "浦月局:H8,I9,I7|H8,I9,G9\n" +
            "岚月局:H8,I9,I6|H8,I9,F9\n" +
            "银月局:H8,I9,H7|H8,I9,G8\n" +
            "明星局:H8,I9,H6|H8,I9,F8\n" +
            "斜月局:H8,I9,G7|H8,I9,G7\n" +
            "名月局:H8,I9,G6|H8,I9,F7\n" +
            "彗星局:H8,I9,F6";
    public static boolean sanshoujiaohuan = false;
    public static boolean wushouNda = false;
    public static boolean jinshou = false;
    public static Map<String, String[]> books ;
    public static List<String> names;
    static int[][] board = {
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,2,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
            {0,0,0,0,2,1,1,1,1,2,0,0,0,0,0},
            {0,0,0,0,0,1,0,1,2,2,0,0,0,0,0},
            {0,0,0,0,0,0,2,1,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,2,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
    };
    public static void main(String[] args) throws InterruptedException {
        System.out.println("This CLI only used in testing...");
        names = new ArrayList<>();
        //int res = randChess(randomBoard);
        //int[] scores = ChessEngine.evaluateSituation(board, 0);
        //printBoard(randomBoard);
        books = new HashMap<>();
        String[] books_strs = BookStr.split("\n");
        for (int i = 0; i < 26; i++) {
            books.put(books_strs[i].substring(0,3),books_strs[i].substring(4).split("\\|"));
            names.add(books_strs[i].substring(0,3));
        }
        //System.out.println(books.size());
        //for (Map.Entry<String, String[]> entry : books.entrySet()) {
        //    System.out.println(entry.getKey() + ":" + entry.getValue()[0]);
        //}
        //return;
        //List<ChessEngine.Point> points = ChessEngine.getFreePoints(board, 1);
        //for(ChessEngine.Point n : points) System.out.println(n);

        //ChessEngine.Point p = ChessEngine.getNextStep(board, 2);
        //System.out.println(p);

        /* main test*/

        /*
        long total = 0;
        for (int i = 0; i < 100; i++) {
            AI_VS_AI();
            total += ChessEngine.totalTime;
            ChessEngine.totalTime = 0;
        }
        System.out.println("Average time is :" + (double)total / 100.0);

         */
        //List<ChessEngine.Point> points = new ArrayList<>();
        //points.add(new ChessEngine.Point(0,0));
        //ChessEngine.sortPoints(points);
        //System.out.println(points.get(0));

        //int[] score1 = ChessEngine.evaluateSituation(board, 0);
        //int[] score2 = ChessEngine.evaluateSituation_2(board,0);
        //System.out.println(score1[0] + "\n" + score2[0]);



        Player_VS_AI();


        /**/

        //ChessEngine.Point point = ChessEngine.getNextStep(board, 1);

        //System.out.println(point);


        //System.out.println(scores[0] + "," + scores[1]);
        //System.out.println(ChessEngine.isAnyoneWin(board));
    }

    public static void Player_VS_AI() {
        while (true) {
            String choice = "";
            System.out.println("Please choose the first player: ");
            System.out.println("#1. You");
            System.out.println("#2. AI");
            System.out.println("#3. Settings");
            int[][] GameBoard = new int[15][15];
            Scanner scanner = new Scanner(System.in);
            choice = scanner.next();

            if (choice.equals("1")) {
                if (sanshoujiaohuan && wushouNda) {
                    System.out.println("请输入前三子位置,\",\"分隔：");
                    String first3points = "";
                    for (int i = 0; i < 3; i++) {
                        String point = scanner.next();
                        first3points += point;
                        if (i != 2) first3points += ",";
                        String[] x_y_String = point.split(",");
                        int[] x_y = new int[2];
                        x_y[0] = 16 - Integer.parseInt(point.substring(1));
                        x_y[1] = (int)(point.charAt(0) - 'A') + 1;
                        GameBoard[x_y[0] - 1][x_y[1] - 1] = i % 2 + 1;
                    }
                    printBoard(GameBoard);
                    System.out.println("请输入打点数量：");
                    boolean chooseChange = false;
                    int count = scanner.nextInt();
                    for (Map.Entry<String, String[]> entry : books.entrySet()) {
                        for (String jumian : entry.getValue()) {
                            if (jumian.equals(first3points)) {
                                if (entry.getKey().equals("花月局") || entry.getKey().equals("浦月局")) {
                                    chooseChange = true;
                                }
                            }
                        }
                    }
                    if (chooseChange) {
                        System.out.println("AI选择交换，现在由AI执黑子");
                        for (int i = 0; i < count; i++) {

                        }
                    } else {
                        System.out.println("AI不选择交换，您仍然是黑子");
                    }
                }
                printBoard(GameBoard);
                while (true) {
                    System.out.println("Enter the point: ");
                    String point = scanner.next();
                    if (point.equals("quit")) break;
                    String[] x_y_String = point.split(",");
                    int[] x_y = new int[2];
                    x_y[0] = 16 - Integer.parseInt(point.substring(1));
                    x_y[1] = (int)(point.charAt(0) - 'A') + 1;
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
                    x_y[0] = 16 - Integer.parseInt(point.substring(1));
                    x_y[1] = (int)(point.charAt(0) - 'A') + 1;
                    GameBoard[x_y[0] - 1][x_y[1] - 1] = 2;
                    printBoard(GameBoard);
                    if (ChessEngine.isAnyoneWin(GameBoard) != 0) break;
                }
            } else if (choice.equals("3")) {
                String opeation = "";
                if (scanner.hasNextLine()) opeation = scanner.nextLine();
                while (opeation.equals("quit") == false) {
                    System.out.println("#1. 禁手规则 " + (jinshou ? "√" : "×"));
                    System.out.println("#2. 三手交换 " + (sanshoujiaohuan ? "√" : "×"));
                    System.out.println("#3. 五手N打 " + (wushouNda ? "√" : "×"));

                    System.out.println("#输入命令:");
                    opeation = scanner.nextLine();

                    if (opeation.equals("set all true")) {
                        jinshou = true;
                        sanshoujiaohuan = true;
                        wushouNda = true;
                    } else if (opeation.equals("set 1 = true")) {
                        jinshou = true;
                    } else if (opeation.equals("set 2 = true")) {
                        sanshoujiaohuan = true;
                    } else if (opeation.equals("set 3 = true")) {
                        wushouNda = true;
                    } else if (opeation.equals("set 1 = false")) {
                        jinshou = false;
                    } else if (opeation.equals("set 2 = false")) {
                        sanshoujiaohuan = false;
                    } else if (opeation.equals("set 3 = false")) {
                        wushouNda = false;
                    } else if (opeation.equals("set all false")) {
                        jinshou = false;
                        sanshoujiaohuan = false;
                        wushouNda = false;
                    } else if (opeation.equals("quit")) {
                        break;
                    } else {
                        System.out.println("未定义的操作");
                    }
                }


            } else {
                System.out.println("Unknow operation, try again.");
            }

            if (ChessEngine.isAnyoneWin(GameBoard) == 1) {
                System.out.println("###### Black Win ! ######");
            } else if (ChessEngine.isAnyoneWin(GameBoard) == 2) {
                System.out.println("###### White Win ! ######");
            }
        }

    }

    public static void AI_VS_AI() throws InterruptedException {
        long startTime = new Date().getTime();
        int[][] GameBoard = new int[15][15];
        boolean isFirstChess = true;
        int currentChess = 1;
        double MAXROUND = 10;
        double currentRound = 0.0;
        List<ChessEngine.Point> savedSteps = new ArrayList<>();
        while(true) {
            if (!isFirstChess) {
                ChessEngine.Point AIStep = ChessEngine.getNextStep(GameBoard, currentChess);
                savedSteps.add(AIStep);
                GameBoard[AIStep.x][AIStep.y] = currentChess;
                currentRound += 0.5;
                if (currentRound == MAXROUND) break;
                currentChess = ((currentChess == 1) ?  2 : 1);
                //printBoard(GameBoard);
                //Thread.sleep(500);
            } else {
                GameBoard[7][7] = 1;
                savedSteps.add(new ChessEngine.Point(7,7));
                currentRound += 0.5;
                if (currentRound == MAXROUND) break;
                isFirstChess = false;
                currentChess = ((currentChess == 1) ?  2 : 1);
                //printBoard(GameBoard);
                //Thread.sleep(500);
            }
        }
        long endTime = new Date().getTime();
        for (ChessEngine.Point p : savedSteps) System.out.print("(" + p + ")->");
        System.out.println();
        System.out.println("Used Time: " + (endTime - startTime) + "ms");
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
            System.out.print(15 - i >= 10 ? 15 - i : "0" + (15 - i) );
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
        System.out.print("  ");
        for (int i = 0; i < 15; i++)
            System.out.print((char)('A' + i) + " |") ;
        System.out.println();
    }
}

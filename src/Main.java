import com.sun.media.jfxmediaimpl.HostUtils;

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
    public static boolean jinshou = true;
    public static Map<String, String[]> books ;
    public static List<String> names;
    public static int AIChess = 0;
    public static int level = 2;
    public static ChessEngine.Point lastPoint = new ChessEngine.Point(8,5);
    static int[][] board = {
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,2,1,2,0,0,0,0,0,0,0},
            {0,0,0,0,0,2,1,2,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,1,1,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
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
        // ┌┍┎┏┐┑┒┓—┄┈├┝┞┟┠┡┢┣|┆┊┬┭┮┯┰┱┲┳┼┽┾┿╀╂╁╃
        //System.out.println("┌─────┬──┐");
        //System.out.println("│     |");
        //System.out.println("├─────┼");
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

        //System.out.println(ChessEngine.isBanPoint(board, new ChessEngine.Point(6,7)));

        Player_VS_AI();
        //AI_VS_AI();

        /**/

        //ChessEngine.Point point = ChessEngine.getNextStep(board, 2);

        //System.out.println(point);


        //System.out.println(scores[0] + "," + scores[1]);
        //System.out.println(ChessEngine.isAnyoneWin(board));
    }

    public static void Player_VS_AI() {
        while (true) {

            boolean gameStart = false;
            String choice = "";
            System.out.println("Please choose the first player: ");
            System.out.println("#1. You");
            System.out.println("#2. AI");
            System.out.println("#3. Settings");
            System.out.println("#4. Quit");
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

                    int count = scanner.nextInt();

                    System.out.println("AI不选择交换，您仍然是黑子");
                    ChessEngine.Point AIStep = ChessEngine.getNextStep(GameBoard, 2);
                    GameBoard[AIStep.x][AIStep.y] = 2;
                    printBoard(GameBoard);
                    ChessEngine.Point[] dadianzi = new ChessEngine.Point[count];
                    for (int i = 0; i < count; i++) {
                        System.out.println("请输入第" + (i + 1) + "个打点位置");
                        String point = scanner.next();
                        int[] x_y = new int[2];
                        x_y[0] = 16 - Integer.parseInt(point.substring(1));
                        x_y[1] = (int)(point.charAt(0) - 'A') + 1;
                        dadianzi[i] = new ChessEngine.Point(x_y[0] - 1, x_y[1] - 1);
                    }
                    Random rand = new Random();
                    int aiChose = rand.nextInt(count);
                    System.out.println("AI 选择中...");
                    GameBoard[dadianzi[aiChose].x][dadianzi[aiChose].y] = 1;
                    AIChess = 2;
                } else AIChess = 2;
                gameStart = true;
                printBoard(GameBoard);
            }
            else if (choice.equals("2")) {
                if (sanshoujiaohuan && wushouNda) {
                    printBoard(GameBoard);
                    Random rand = new Random();
                    String kaijuname = names.get(rand.nextInt(26));
                    kaijuname = "瑞星局";
                    String[] zoufa = books.get(kaijuname)[0].split(",");
                    System.out.println("AI 选择开局为" + kaijuname);
                    for (int i = 0; i < zoufa.length; i++) {
                        int[] x_y = new int[2];
                        x_y[0] = 16 - Integer.parseInt(zoufa[i].substring(1));
                        x_y[1] = (int)(zoufa[i].charAt(0) - 'A') + 1;
                        GameBoard[x_y[0] - 1][x_y[1] - 1] = i % 2 + 1;
                    }
                    printBoard(GameBoard);
                    int count = 2;
                    System.out.println("AI提出2点打点");
                    System.out.println("是否选择换");
                    String shifou = scanner.next();
                    if (shifou.equals("是")) {
                        System.out.println("已交换，现在您执黑子，AI执白子");
                        ChessEngine.Point ainext = ChessEngine.getNextStep(GameBoard, 2);
                        GameBoard[ainext.x][ainext.y] = 2;
                        printBoard(GameBoard);
                        System.out.println("请给出2点打点");
                        ChessEngine.Point[] dadianzi = new ChessEngine.Point[count];
                        for (int i = 0; i < count; i++) {
                            System.out.println("请输入第" + (i + 1) + "个打点位置");
                            String point = scanner.next();
                            int[] x_y = new int[2];
                            x_y[0] = 16 - Integer.parseInt(point.substring(1));
                            x_y[1] = (int)(point.charAt(0) - 'A') + 1;
                            dadianzi[i] = new ChessEngine.Point(x_y[0] - 1, x_y[1] - 1);
                        }
                        int aichoice = rand.nextInt(2);
                        GameBoard[dadianzi[aichoice].x][dadianzi[aichoice].y] = 1;
                        AIChess = 2;
                        printBoard(GameBoard);
                        System.out.println("现在开始自由落子");
                    } else {
                        AIChess = 1;
                        System.out.println("请下一步棋：");
                        String nextBu = scanner.next();
                        int[] x_y = new int[2];
                        x_y[0] = 16 - Integer.parseInt(nextBu.substring(1));
                        x_y[1] = (int)(nextBu.charAt(0) - 'A') + 1;
                        GameBoard[x_y[0] - 1][x_y[1] - 1] = 2;
                        printBoard(GameBoard);
                        List<ChessEngine.Point> aidadian = ChessEngine.getFreePoints(GameBoard, 1);
                        int dadian1 = rand.nextInt(aidadian.size());
                        int dadian2 = rand.nextInt(aidadian.size());
                        while (dadian2 == dadian1) dadian2 = rand.nextInt(aidadian.size());
                        System.out.println("Ai 提出" + (char)(aidadian.get(dadian1).y + 'A') + (15 - aidadian.get(dadian1).x)  + "和" + (char)(aidadian.get(dadian2).y + 'A') + (15 - aidadian.get(dadian2).x)  );
                        System.out.println("请选择第一种或者第二种");
                        AIChess = 1;
                        int xuanze = scanner.nextInt();
                        if (xuanze == 1) {
                            GameBoard[aidadian.get(dadian1).x][aidadian.get(dadian1).y] = 1;
                        } else {
                            GameBoard[aidadian.get(dadian2).x][aidadian.get(dadian2).y] = 1;
                        }
                }

                } else AIChess = 1;
                gameStart = true;
                printBoard(GameBoard);
                System.out.println("现在开始自由落子");
            }
            else if (choice.equals("3")) {
                String opeation = "";
                if (scanner.hasNextLine()) opeation = scanner.nextLine();
                while (opeation.equals("quit") == false) {
                    System.out.println("#1. 禁手规则 " + (jinshou ? "√" : "×"));
                    System.out.println("#2. 三手交换 " + (sanshoujiaohuan ? "√" : "×"));
                    System.out.println("#3. 五手N打 " + (wushouNda ? "√" : "×"));
                    System.out.println("#4. 难度设置 当前" + Main.level);
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
                    } else if (opeation.equals("4")) {
                        System.out.println("请输入难度：");
                        int hardLevel = scanner.nextInt();
                        Main.level = hardLevel;
                    } else {
                        System.out.println("未定义的操作");
                    }
                }


            } else if (choice.equals("4") || choice.equals("quit")) {
                break;
            }
            else {
                System.out.println("Unknow operation, try again.");
            }

            if (gameStart ) {
                if (AIChess == 2 && sanshoujiaohuan && wushouNda) {
                    ChessEngine.Point point = ChessEngine.getNextStep(GameBoard, 2);
                    GameBoard[point.x][point.y] = 2;
                    printBoard(GameBoard);
                }
                if (AIChess == 1 && !sanshoujiaohuan && !wushouNda) {
                    GameBoard[7][7] = 1;
                    printBoard(GameBoard);
                }
            }
            String point = "";
            ChessEngine.Point AINextPoint = new ChessEngine.Point(0,0);
            int[] x_y = new int[2];
            while (gameStart) {
                System.out.println("请输入序号，英文字母在前且必须为大写");
                point = scanner.next();
                if (point.equals("frontStep")) {
                    GameBoard[AINextPoint.x][AINextPoint.y] = 0;
                    GameBoard[lastPoint.x - 1][lastPoint.y - 1] = 0;
                    printBoard(GameBoard);
                    System.out.println(lastPoint);
                    System.out.println("悔棋成功，现在请继续输入下一步，英文字母在前且必须为大写");
                    point = scanner.next();
                }
                x_y[0] = 16 - Integer.parseInt(point.substring(1));
                x_y[1] = (int)(point.charAt(0) - 'A') + 1;
                lastPoint.x = x_y[0];
                lastPoint.y = x_y[1];
                GameBoard[x_y[0] - 1][x_y[1] - 1] = AIChess == 1 ? 2 : 1;
                if (ChessEngine.isAnyoneWin(GameBoard) != 0) break;
                printBoard(GameBoard);
                System.out.println("AI正在思考中......");
                AINextPoint = ChessEngine.getNextStep(GameBoard, AIChess);
                while (jinshou && AIChess == 1 && ChessEngine.isBanPoint(GameBoard, AINextPoint)) {
                    int[][] tmpBoard = GameBoard;
                    tmpBoard[AINextPoint.x][AINextPoint.y] = -1;
                    AINextPoint = ChessEngine.getNextStep(GameBoard, AIChess);
                }
                GameBoard[AINextPoint.x][AINextPoint.y] = AIChess;
                printBoard(GameBoard);
                System.out.println("AI下子位置为" + (char)(AINextPoint.y + 'A') + (15 - AINextPoint.x));
                if (ChessEngine.isAnyoneWin(GameBoard) != 0) break;
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

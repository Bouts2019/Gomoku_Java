import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChessEngine {
    /*
    Time test
     */


    static long totalTime = 0;

    // ================

    // 0 - no, 1 - black, 2 - white
    public static String[] Rating_Scale_Patterns = {
            "OOOOO",
            "+OOOO+",
            "+OOO++",
            "++OOO+",
            "+OO+O+",
            "+O+OO+",
            "OOOO+",
            "+OOOO",
            "OO+OO",
            "O+OOO",
            "OOO+O",
            "++OO++",
            "++O+O+",
            "+O+O++",
            "+++O++",
            "++O+++"
    };

    public static int[] Rating_Scale_Scores = {
            5000,
            432,
            72,
            72,
            72,
            72,
            72,
            72,
            72,
            72,
            72,
            12,
            12,
            12,
            2,
            2
    };

    public static int isAnyoneWin(int[][] board) {
        // 横着找
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j <= 10; j++) {
                if (board[i][j] == 1) {
                    boolean win = true;
                    for (int k = j; k < j + 5; k++) {        // 黑子，开始横向判断
                        if (board[i][k] != 1) {
                            win = false;
                            break;
                        }
                    }
                    if (win) return 1;
                } else if (board[i][j] == 2) {
                    boolean win = true;
                    for (int k = j; k < j + 5; k++) {        // 白色，开始横向判断
                        if (board[i][k] != 2) {
                            win = false;
                            break;
                        }
                    }
                    if (win) return 2;
                }
            }


        }
        // 竖着找
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j <= 10; j++) {
                if (board[j][i] == 1) {
                    boolean win = true;
                    for (int k = j; k < j + 5; k++) {        // 黑子，开始纵向判断
                        if (board[k][i] != 1) {
                            win = false;
                            break;
                        }
                    }
                    if (win) return 1;
                } else if (board[j][i] == 2) {
                    boolean win = true;
                    for (int k = j; k < j + 5; k++) {        // 白色，开始纵向判断
                        if (board[k][i] != 2) {
                            win = false;
                            break;
                        }
                    }
                    if (win) return 2;
                }
            }
        }
        // 正斜找
        for (int i = 4; i < 15; i++) {
            for (int j = 0; j < i - 4 + 1; j++) {
                int x = i - j;
                int y = j;
                if (board[x][y] == 1) {
                    boolean win = true;
                    for (int k = 0; k < 5; k++) {
                        if (board[x - k][y + k] != 1) {
                            win = false;
                            break;
                        }
                    }
                    if (win) return 1;
                } else if (board[x][y] == 2) {
                    boolean win = true;
                    for (int k = 0; k < 5; k++) {
                        if (board[x - k][y + k] != 2) {
                            win = false;
                            break;
                        }
                    }
                    if (win) return 2;
                }
                x = 14 - j;
                y = 15 - i + j;
                if (board[x][y] == 0) ;
                else if (board[x][y] == 1) {
                    boolean win = true;
                    for (int k = 0; k < 5; k++) {
                        if (board[x - k][y + k] != 1) {
                            win = false;
                            break;
                        }
                    }
                    if (win) return 1;
                }
                else if (board[x][y] == 2) {
                    boolean win = true;
                    for (int k = 0; k < 5; k++) {
                        if (board[x - k][y + k] != 2) {
                            win = false;
                            break;
                        }
                    }
                    if (win) return 2;
                }
            }
        }
        // 反斜找
        for (int i = 4; i < 15; i++) {
            for (int j = 0; j < i - 4 + 1; j++) {
                int x = 0 + j;
                int y = 14 - i + j;
                if (board[x][y] == 1) {
                    boolean win = true;
                    for (int k = 0; k < 5; k++) {
                        if (board[x + k][y + k] != 1) {
                            win = false;
                            break;
                        }
                    }
                    if (win) return 1;
                } else if (board[x][y] == 2) {
                    boolean win = true;
                    for (int k = 0; k < 5; k++) {
                        if (board[x + k][y + k] != 2) {
                            win = false;
                            break;
                        }
                    }
                    if (win) return 2;
                }
                x = 14 - i + j;
                y = j;
                if (board[x][y] == 0) ;
                else if (board[x][y] == 1) {
                    boolean win = true;
                    for (int k = 0; k < 5; k++) {
                        if (board[x + k][y + k] != 1) {
                            win = false;
                            break;
                        }
                    }
                    if (win) return 1;
                }
                else if (board[x][y] == 2) {
                    //System.out.println(x + ", " + y);
                    boolean win = true;
                    for (int k = 0; k < 5; k++) {
                        if (board[x + k][y + k] != 2) {
                            win = false;
                            break;
                        }
                    }
                    if (win) return 2;
                }
            }
        }
        return 0;
    }
    // (7, 7)->(6, 7)->(6, 6)->(5, 5)->(7, 5)->(5, 7)->(5, 4)->(7, 6)->(5, 8)->(6, 4)->(4, 6)->(3, 6)->(7, 8)->(4, 8)->(3, 7)->(3, 5)->(2, 5)->(5, 3)->(4, 4)->(4, 3)->
    public static int[] evaluateSituation_2(int[][] board, int target) {
        int[] scores = new int[2];
        StringBuffer boardString = new StringBuffer();
        long startTime = new Date().getTime();
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++)
                boardString.append(board[i][j] == 0 ? "+" : (board[i][j] == target + 1 ? "O" : "X"));
        }
        // 横向
        for (int i = 0; i < 15; i++) {
            String line = boardString.substring(i * 15 , i * 15 + 14);
            for (int j = 0; j < Rating_Scale_Patterns.length; j++)                          // 模式匹配
                if (line.indexOf(Rating_Scale_Patterns[j]) != -1) {
                    scores[target] += Rating_Scale_Scores[j];
                }
        }
        // 竖向
        for (int i = 0; i < 15; i++) {
            StringBuffer row = new StringBuffer();
            for (int j = 0; j < 15; j++) {
                row.append(boardString.charAt(15 * j + i));
            }
            for (int j = 0; j < Rating_Scale_Patterns.length; j++)                          // 模式匹配
                if (row.indexOf(Rating_Scale_Patterns[j]) != -1) {
                    scores[target] += Rating_Scale_Scores[j];
                }
        }
        // 正斜向
        for (int i = 0; i < 15; i++) {
            StringBuffer sideline = new StringBuffer();
            for (int j = 0; j < i + 1; j++) {
                sideline.append(boardString.charAt(i + 14 * j));
            }
            for (int j = 0; j < Rating_Scale_Patterns.length; j++)                          // 模式匹配
                if (sideline.indexOf(Rating_Scale_Patterns[j]) != -1) {
                    scores[target] += Rating_Scale_Scores[j];
                }
        }
        for (int i = 0; i < 14; i++) {
            StringBuffer sideline = new StringBuffer();
            for (int j = 0; j < i + 1; j++) {
                sideline.append(boardString.charAt(224 - i - 14 * j));
            }
            for (int j = 0; j < Rating_Scale_Patterns.length; j++)                          // 模式匹配
                if (sideline.indexOf(Rating_Scale_Patterns[j]) != -1) {
                    scores[target] += Rating_Scale_Scores[j];
                }
        }
        // 反斜向
        for (int i = 0; i < 15; i++) {
            StringBuffer sideline = new StringBuffer();
            for (int j = 0; j < i + 1; j++) {
                sideline.append(boardString.charAt(14 - i + 16 * j));
            }
            for (int j = 0; j < Rating_Scale_Patterns.length; j++)                          // 模式匹配
                if (sideline.indexOf(Rating_Scale_Patterns[j]) != -1) {
                    scores[target] += Rating_Scale_Scores[j];
                }
        }
        for (int i = 0; i < 14; i++) {
            StringBuffer sideline = new StringBuffer();
            for (int j = 0; j < i + 1; j++) {
                sideline.append(boardString.charAt(210 + i - 16 * j));
            }
            for (int j = 0; j < Rating_Scale_Patterns.length; j++)                          // 模式匹配
                if (sideline.indexOf(Rating_Scale_Patterns[j]) != -1) {
                    scores[target] += Rating_Scale_Scores[j];
                }
        }
        long endTime = new Date().getTime();
        totalTime += endTime - startTime;
        return scores;
    }

    /*
     * target -> 0 : black, 1 - white;
     * */

    public static int[] evaluateSituation(int[][] board, int target) {
        int[] scores = new int[2];
        // 横着每一行
        long startTime = new Date().getTime();
        for (int i = 0; i < 15; i++) {
            String lineConv = new String();
            for (int j = 0; j < 15; j++)
                lineConv += board[i][j];
            if (target == 0) {                      // 如果需要算黑子
                String blackLineConv = lineConv.replaceAll("0", "+");       // 只计算黑色子
                blackLineConv = blackLineConv.replaceAll("1", "O");         // 需要屏蔽白色
                blackLineConv = blackLineConv.replaceAll("2", "X");         // 完成转换
                for (int j = 0; j < Rating_Scale_Patterns.length; j++)                          // 模式匹配
                    if (blackLineConv.contains(Rating_Scale_Patterns[j])) {
                        scores[0] += Rating_Scale_Scores[j];
                    }
            } else if (target == 1) {                // 如果需要算白子
                String whiteLineConv = lineConv.replaceAll("0", "+");       // 只计算白色子
                whiteLineConv = whiteLineConv.replaceAll("1", "X");         // 需要屏蔽黑色
                whiteLineConv = whiteLineConv.replaceAll("2", "O");         // 完成转换
                for (int j = 0; j < Rating_Scale_Patterns.length; j++)                          // 模式匹配
                    if (whiteLineConv.contains(Rating_Scale_Patterns[j]))
                        scores[1] += Rating_Scale_Scores[j];
            }
        }
        // 竖着每一列
        for (int i = 0; i < 15; i++) {
            String rowConv = new String();
            for (int j = 0; j < 15; j++)
                rowConv += board[j][i];
            if (target == 0) {                      // 如果需要算黑子
                String blackLineConv = rowConv.replaceAll("0", "+");       // 只计算黑色子
                blackLineConv = blackLineConv.replaceAll("1", "O");         // 需要屏蔽白色
                blackLineConv = blackLineConv.replaceAll("2", "X");         // 完成转换
                for (int j = 0; j < Rating_Scale_Patterns.length; j++)                          // 模式匹配
                    if (blackLineConv.contains(Rating_Scale_Patterns[j])) {
                        scores[0] += Rating_Scale_Scores[j];
                    }
            } else if (target == 1) {                // 如果需要算白子
                String whiteLineConv = rowConv.replaceAll("0", "+");       // 只计算白色子
                whiteLineConv = whiteLineConv.replaceAll("1", "X");         // 需要屏蔽黑色
                whiteLineConv = whiteLineConv.replaceAll("2", "O");         // 完成转换
                for (int j = 0; j < Rating_Scale_Patterns.length; j++)                          // 模式匹配
                    if (whiteLineConv.contains(Rating_Scale_Patterns[j]))
                        scores[1] += Rating_Scale_Scores[j];
            }
        }
        // 正斜 /
        for (int i = 4; i < 15; i++) {
            String sidelineConv = new String();
            for (int j = 0; j < i + 1; j++)
                sidelineConv += board[i - j][j];
            if (target == 0) {                      // 如果需要算黑子
                String blackLineConv = sidelineConv.replaceAll("0", "+");       // 只计算黑色子
                blackLineConv = blackLineConv.replaceAll("1", "O");         // 需要屏蔽白色
                blackLineConv = blackLineConv.replaceAll("2", "X");         // 完成转换
                for (int j = 0; j < Rating_Scale_Patterns.length; j++)                          // 模式匹配
                    if (blackLineConv.contains(Rating_Scale_Patterns[j])) {
                        scores[0] += Rating_Scale_Scores[j];
                    }
            } else if (target == 1) {                // 如果需要算白子
                String whiteLineConv = sidelineConv.replaceAll("0", "+");       // 只计算白色子
                whiteLineConv = whiteLineConv.replaceAll("1", "X");         // 需要屏蔽黑色
                whiteLineConv = whiteLineConv.replaceAll("2", "O");         // 完成转换
                for (int j = 0; j < Rating_Scale_Patterns.length; j++)                          // 模式匹配
                    if (whiteLineConv.contains(Rating_Scale_Patterns[j]))
                        scores[1] += Rating_Scale_Scores[j];
            }
        }
        for (int i = 4; i < 14; i++) {
            String sidelineConv = new String();
            for (int j = 0; j < i + 1; j++)
                sidelineConv += board[14 - j][14 - i + j];
            if (target == 0) {                      // 如果需要算黑子
                String blackLineConv = sidelineConv.replaceAll("0", "+");       // 只计算黑色子
                blackLineConv = blackLineConv.replaceAll("1", "O");         // 需要屏蔽白色
                blackLineConv = blackLineConv.replaceAll("2", "X");         // 完成转换
                for (int j = 0; j < Rating_Scale_Patterns.length; j++)                          // 模式匹配
                    if (blackLineConv.contains(Rating_Scale_Patterns[j])) {
                        scores[0] += Rating_Scale_Scores[j];
                    }
            } else if (target == 1) {                // 如果需要算白子
                String whiteLineConv = sidelineConv.replaceAll("0", "+");       // 只计算白色子
                whiteLineConv = whiteLineConv.replaceAll("1", "X");         // 需要屏蔽黑色
                whiteLineConv = whiteLineConv.replaceAll("2", "O");         // 完成转换
                for (int j = 0; j < Rating_Scale_Patterns.length; j++)                          // 模式匹配
                    if (whiteLineConv.contains(Rating_Scale_Patterns[j]))
                        scores[1] += Rating_Scale_Scores[j];
            }
        }
        // 反斜 \
        for (int i = 4; i < 15; i++) {
            String sidelineConv = new String();
            for (int j = 0; j < i + 1; j++)
                sidelineConv += board[j][14 - i + j];
            if (target == 0) {                      // 如果需要算黑子
                String blackLineConv = sidelineConv.replaceAll("0", "+");       // 只计算黑色子
                blackLineConv = blackLineConv.replaceAll("1", "O");         // 需要屏蔽白色
                blackLineConv = blackLineConv.replaceAll("2", "X");         // 完成转换
                for (int j = 0; j < Rating_Scale_Patterns.length; j++)                          // 模式匹配
                    if (blackLineConv.contains(Rating_Scale_Patterns[j])) {
                        scores[0] += Rating_Scale_Scores[j];
                    }
            } else if (target == 1) {                // 如果需要算白子
                String whiteLineConv = sidelineConv.replaceAll("0", "+");       // 只计算白色子
                whiteLineConv = whiteLineConv.replaceAll("1", "X");         // 需要屏蔽黑色
                whiteLineConv = whiteLineConv.replaceAll("2", "O");         // 完成转换
                for (int j = 0; j < Rating_Scale_Patterns.length; j++)                          // 模式匹配
                    if (whiteLineConv.contains(Rating_Scale_Patterns[j]))
                        scores[1] += Rating_Scale_Scores[j];
            }
        }
        for (int i = 4; i < 14; i++) {
            String sidelineConv = new String();
            for (int j = 0; j < i + 1; j++)
                sidelineConv += board[14 -i + j][j];
            if (target == 0) {                      // 如果需要算黑子
                String blackLineConv = sidelineConv.replaceAll("0", "+");       // 只计算黑色子
                blackLineConv = blackLineConv.replaceAll("1", "O");         // 需要屏蔽白色
                blackLineConv = blackLineConv.replaceAll("2", "X");         // 完成转换
                for (int j = 0; j < Rating_Scale_Patterns.length; j++)                          // 模式匹配
                    if (blackLineConv.contains(Rating_Scale_Patterns[j])) {
                        scores[0] += Rating_Scale_Scores[j];
                    }
            } else if (target == 1) {                // 如果需要算白子
                String whiteLineConv = sidelineConv.replaceAll("0", "+");       // 只计算白色子
                whiteLineConv = whiteLineConv.replaceAll("1", "X");         // 需要屏蔽黑色
                whiteLineConv = whiteLineConv.replaceAll("2", "O");         // 完成转换
                for (int j = 0; j < Rating_Scale_Patterns.length; j++)                          // 模式匹配
                    if (whiteLineConv.contains(Rating_Scale_Patterns[j]))
                        scores[1] += Rating_Scale_Scores[j];
            }
        }
        long endTime = new Date().getTime();
        totalTime += endTime - startTime;
        return scores;
    }

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return this.x + ", " + this.y;
        }
    }

    public static void sortPoints(int[][] board, int hasWhat, List<Point> original_points) {
        List<Point> fiveChess = new ArrayList<>();
        List<Point> fourChess = new ArrayList<>();
        List<Point> threeChess = new ArrayList<>();
        List<Point> otherChess = new ArrayList<>();
        for (Point p : original_points) {
            StringBuffer line = new StringBuffer();
            StringBuffer row = new StringBuffer();
            StringBuffer positiveSideLine = new StringBuffer();
            StringBuffer inverseSideLine = new StringBuffer();
            // 横
            for (int i = -4; i <= 4; i++) {
                if (p.y + i >= 0 && p.y + i <= 14)
                    line.append(board[p.x][p.y + i] == 0 ? "+" : board[p.x][p.y + i] == hasWhat ? "O" : "X");
                if (p.x + i >= 0 && p.x + i <= 14)
                    row.append(board[p.x + i][p.y] == 0 ? "+" : board[p.x + i][p.y] == hasWhat ? "O" : "X");
                if (p.x + i >= 0 && p.x + i <= 14 && p.y + i >= 0 && p.y + i <=14)
                    positiveSideLine.append(board[p.x + i][p.y + i] == 0 ? "+" : board[p.x + i][p.y + i] == hasWhat ? "O" : "X");
                if (p.x + i >= 0 && p.x + i <= 14 && p.y - i >= 0 && p.y - i <= 14)
                    inverseSideLine.append(board[p.x + i][p.y - i] == 0 ? "+" : board[p.x + i][p.y - i] == hasWhat ? "O" : "X");
            }

            if (line.indexOf("OOOOO") != -1 || row.indexOf("OOOOO") != -1 || positiveSideLine.indexOf("OOOOO") != -1 || inverseSideLine.indexOf("OOOOO") != -1 )
                fiveChess.add(p);
            else if (line.indexOf("OOOO") != -1 || row.indexOf("OOOO") != -1 || positiveSideLine.indexOf("OOOO") != -1 || inverseSideLine.indexOf("OOOO") != -1 )
                fourChess.add(p);
            else if (line.indexOf("OOO") != -1 || row.indexOf("OOO") != -1 || positiveSideLine.indexOf("OOO") != -1 || inverseSideLine.indexOf("OOO") != -1 )
                threeChess.add(p);
            else
                otherChess.add(p);
        }

        original_points.clear();

        for (Point p : fiveChess) original_points.add(p);
        for (Point p : fourChess) original_points.add(p);
        for (Point p : threeChess) original_points.add(p);
        for (Point p : otherChess) original_points.add(p);
    }


    /*
     * To simplify the function, so this function  just judge 1 block.
     */

    /*
    Depth = 3
    Used Time: 26349ms
    Evaluation Time: 25910ms
    (7, 7)->(6, 8)->(5, 8)->(6, 9)->(6, 7)->(7, 6)->(5, 6)->(8, 7)->(5, 7)->(5, 9)->(4, 7)->(3, 7)->(5, 5)->(5, 4)->(6, 5)->(7, 8)->(7, 4)->(9, 6)->(7, 5)->(4, 5)->
     */

    public static List<Point> getFreePoints(int[][] board, int hasWhat) {
        // hasWhat -> 1 -> black, 2 -> white;
        List<Point> points = new ArrayList();
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                // 左边
                if (board[i][j] != 0) continue;
                if (j - 1 >= 0 && board[i][j - 1] != 0) points.add(new Point(i, j));
                    // 右边
                else if (j + 1 <= 14 && board[i][j + 1] != 0) points.add(new Point(i, j));
                    // 上边
                else if (i - 1 >= 0 && board[i - 1][j] != 0) points.add(new Point(i, j));
                    // 下边
                else if (i + 1 <= 14 && board[i + 1][j] != 0) points.add(new Point(i, j));
                    // 左上
                else if (i - 1 >= 0 && j - 1 >= 0 && board[i - 1][j - 1] != 0) points.add(new Point(i, j));
                    // 左下
                else if (i + 1 <= 14 && j - 1 >= 0 && board[i + 1][j - 1] != 0) points.add(new Point(i, j));
                    // 右上
                else if (i - 1 >= 0 && j + 1 <= 14 && board[i - 1][j + 1] != 0) points.add(new Point(i, j));
                    // 右下
                else if (i + 1 <= 14 && j + 1 <= 14 && board[i + 1][j + 1] != 0) points.add(new Point(i, j));
            }
        }
        //(6, 6)->(6, 7)->(6, 8)->(7, 6)->(7, 8)->(8, 6)->(8, 7)->(8, 8)->
        //sortPoints(board, hasWhat, points);

        return points;
    }

    /*
     * AIChess -> 1 : black, 2 - white;
     * */

    static class PointWithScore {
        Point point;
        int score;

        public PointWithScore(Point point, int score) {
            this.point = point;
            this.score = score;
        }
    }

    public static Point getNextStep(int[][] board, int AIChess) {
        PointWithScore p = minimax(new Point(0, 0), board, 0, AIChess, new int[] {Integer.MAX_VALUE});
        return p.point;
    }

    public static PointWithScore minimax(Point step, int[][] board, int depth, int AIChess, int[] recode) {
        int score = 0;
        if (depth >= 2) {   // 到达叶子节点，不再进行深搜
            int tmp = evaluateSituation_2(board, AIChess - 1)[AIChess - 1]-evaluateSituation_2(board, (AIChess == 1 ? 2 : 1) - 1)[(AIChess == 1 ? 2 : 1) - 1];
            recode[0] = tmp;
            return new PointWithScore(step, tmp);
        } else {            // 没有到达叶子节点，继续深搜
            List<Point> points = getFreePoints(board, (depth % 2 == 0 ) ? AIChess : (AIChess == 1 ? 2 : 1)); // 搜索所有可以用的点，传入AIChess，忽略黑子或是白子影响，因为要最大最小是按层数来的
            List<PointWithScore> pointWithScores = new ArrayList<>(); // 带分数的点类型
            if (depth % 2 == 0) {   // 在那一层之下，如果是偶数层之下就是要找最大值
                int maxScore = Integer.MIN_VALUE;
                int maxIndex = 0;
                int[] maxRecode = new int[1];
                maxRecode[0] = Integer.MIN_VALUE;
                for (int i = 0; i < points.size(); i++) {
                    int[][] tmpBoard = new int[15][15];
                    for (int j = 0; j < 15; j++)
                        for (int k = 0; k < 15; k++)
                            tmpBoard[j][k] = board[j][k];   // 模拟下子用的棋盘
                    tmpBoard[points.get(i).x][points.get(i).y] = AIChess;
                    PointWithScore P = minimax(points.get(i), tmpBoard, depth + 1, AIChess, maxRecode);
                    if (P.score > recode[0])
                        return new PointWithScore(new Point(0,0), Integer.MAX_VALUE);
                    pointWithScores.add(P);
                }

                for (int i = 0; i < pointWithScores.size(); i++)
                    if (pointWithScores.get(i).score > maxScore) {
                        maxScore = pointWithScores.get(i).score;
                        maxIndex = i;
                    }
                recode[0] = pointWithScores.get(maxIndex).score;
                return new PointWithScore(points.get(maxIndex), pointWithScores.get(maxIndex).score);

            } else {
                int minScore = Integer.MAX_VALUE;
                int minIndex = 0;
                int[] minRecord = new int[1];
                minRecord[0] = Integer.MAX_VALUE;
                for (int i = 0; i < points.size(); i++) {
                    int[][] tmpBoard = new int[15][15];
                    for (int j = 0; j < 15; j++)
                        for (int k = 0; k < 15; k++)
                            tmpBoard[j][k] = board[j][k];   // 模拟下子用的棋盘
                    tmpBoard[points.get(i).x][points.get(i).y] = AIChess == 1 ? 2 : 1;
                    PointWithScore P = minimax(points.get(i), tmpBoard, depth + 1, AIChess, minRecord);
                    if (P.score < recode[0])
                        return new PointWithScore(new Point(0,0), Integer.MIN_VALUE);
                    pointWithScores.add(P);
                }

                for (int i = 0; i < pointWithScores.size(); i++)
                    if (pointWithScores.get(i).score < minScore) {
                        minScore = pointWithScores.get(i).score;
                        minIndex = i;
                    }
                recode[0] = pointWithScores.get(minIndex).score;
                return new PointWithScore(points.get(minIndex), pointWithScores.get(minIndex).score);
            }
        }
    }
}

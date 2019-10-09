import java.util.ArrayList;
import java.util.List;

public class ChessEngine {
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
            50000,
            4320,
            720,
            720,
            720,
            720,
            720,
            720,
            720,
            720,
            720,
            120,
            120,
            120,
            20,
            20
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

    /*
     * target -> 0 : black, 1 - white;
     * */

    public static int[] evaluateSituation(int[][] board, int target) {
        int[] scores = new int[2];
        // 横着每一行
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

    /*
     * To simplify the function, so this function  just judge 1 block.
     */

    public static List<Point> getFreePoints(int[][] board, int hasWhat) {
        // hasWhat -> 1 -> black, 2 -> white;
        List<Point> points = new ArrayList();
        for (int i = 0; i < 15; i++)
            for (int j = 0; j < 15; j++) {
                // 左边
                if (board[i][j] != 0) continue;
                if (j - 1 >= 0 && board[i][j - 1] == hasWhat) points.add(new Point(i, j));
                // 右边
                if (j + 1 <= 14 && board[i][j + 1] == hasWhat) points.add(new Point(i, j));
                // 上边
                if (i - 1 >= 0 && board[i - 1][j] == hasWhat) points.add(new Point(i, j));
                // 下边
                if (i + 1 <= 14 && board[i + 1][j] == hasWhat) points.add(new Point(i, j));
                // 左上
                if (i - 1 >= 0 && j - 1 >= 0 && board[i - 1][j - 1] == hasWhat) points.add(new Point(i, j));
                // 左下
                if (i + 1 <= 14 && j - 1 >= 0 && board[i + 1][j - 1] == hasWhat) points.add(new Point(i, j));
                // 右上
                if (i - 1 >= 0 && j + 1 <= 14 && board[i - 1][j + 1] == hasWhat) points.add(new Point(i, j));
                // 右下
                if (i + 1 <= 14 && j + 1 <= 14 && board[i + 1][j + 1] == hasWhat) points.add(new Point(i, j));
            }
        return points;
    }

    /*
     * AIChess -> 1 : black, 2 - white;
     * */

    public static Point getNextStep(int[][] board, int AIChess) {
        if (AIChess == 1) {
            List<Point> points = getFreePoints(board, 1);
            int maxScore = -1;
            int maxIndex = 0;
            for (int i = 0; i < points.size(); i++) {
                int[][] tmpBoard = board;
                tmpBoard[points.get(i).x][points.get(i).y] = 1;
                int[] twoScores = evaluateSituation(board, 0);
                if (twoScores[0] > maxScore) {
                    maxScore = twoScores[0];
                    maxIndex = i;
                }
            }
            return points.get(maxIndex);
        }
        return new Point(0,0);
    }
}

public class ChessEngine {
    // 0 - no, 1 - black, 2 - white
    public static int isAnyoneWin(int[][] board) {
        // 横着找
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j <= 10; j++) {
                if (board[i][j] == 0) continue;          // 此格子是空的
                else if (board[i][j] == 1) {
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
                if (board[j][i] == 0) continue;          // 此格子是空的
                else if (board[j][i] == 1) {
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
}

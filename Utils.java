public class Utils {


    public static boolean inRow(int row, int[] sudoku, int num) {

        for( int i=0; i<9; i++) {
            Numbs.it++;
            if( num == sudoku[Numbs.rows[row][i]]) {
                return true;
            }
        }
        return false;
    }

    public static boolean inColumn(int c, int[] sudoku, int num) {

        for( int i=0; i<9; i++) {
            Numbs.it++;
            if( num == sudoku[Numbs.col[c][i]] ) {
                return true;
            }
        }
        return false;
    }

    public static boolean inBlock(int b, int[] sudoku, int num) {

        for( int i=0; i<9; i++) {
            Numbs.it++;
            if ( num == sudoku[Numbs.blocks[b][i]]) {
                return true;
            };
        }

        return false;
    }

    public static boolean isOver(int[] sudoku) {

        for( int i=0; i<9; i++) {
            int sum = 0;
            int product = 1;
            for( int j=0; j<9; j++) {
                sum = sum + sudoku[Numbs.rows[i][j]];
                Numbs.it++;
                product = product * sudoku[Numbs.rows[i][j]];
            }
            if( sum != 45 && product != 362880 ) {
                return false;
            }
        }
        return true;
    }

}
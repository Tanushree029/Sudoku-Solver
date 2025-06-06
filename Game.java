import java.util.Arrays;
import java.util.stream.IntStream;

public class Game {

    int[] sudoku = { 	0, 0, 8,   7, 0, 6,   0, 4, 0,
            0, 0, 0,   0, 3, 0,   9, 6, 0,
            9, 0, 0,   0, 1, 0,   0, 0, 0,

            3, 0, 0,   0, 0, 0,   4, 0, 0,
            0, 8, 1,   0, 2, 0,   7, 9, 0,
            0, 0, 5,   0, 0, 0,   0, 0, 1,

            6, 0, 0,   0, 7, 0,   0, 0, 2,
            0, 1, 2,   0, 5, 0,   0, 0, 0,
            0, 3, 0,   6, 0, 2,   8, 0, 0

            /*
            0, 0, 6,   8, 0, 0,   0, 9, 4,
            0, 2, 0,   0, 6, 0,   7, 0, 0,
            7, 0, 0,   4, 0, 2,   0, 0, 0,

            0, 0, 0,   3, 0, 0,   0, 1, 0,
            6, 4, 0,   0, 2, 8,   3, 5, 0,
            0, 9, 0,   5, 0, 1,   0, 0, 2,

            4, 0, 2,   6, 0, 3,   0, 0, 5,
            0, 0, 0,   0, 1, 9,   0, 0, 3,
            8, 0, 9,   0, 0, 0,   1, 2, 0
             */
    };
    public boolean term = false;

    void go() {

        int[] sudoku1 = sudoku.clone();
        for( int num=1; num<=9; num++) {
            for( int i=0; i<9; i++) {
                if( Utils.inRow(i, sudoku, num)) {
                    continue;
                }
                else {
                    int[] flag = new int[9];
                    for( int j=0; j<9; j++) {
                        Numbs.it++;
                        if(!(Utils.inColumn(j, sudoku, num)) && sudoku[9*i+j] == 0) {
                            if(!(Utils.inBlock(3*(i/3) + j/3, sudoku, num))) {
                                //sudoku[9*i + j] = num;
                                flag[j] = 1;
                            }
                        }
                    }
                    setSudokuRow(flag, i, num);
                }
            } //row ends here
            for( int i=0; i<9; i++) {
                if( !(Utils.inColumn(i, sudoku, num))){
                    int[] flag = new int[9];
                    for( int j=0; j<9; j++) {
                        Numbs.it++;
                        if(!(Utils.inRow(j, sudoku, num)) && sudoku[9*j+i] == 0) {
                            if(!(Utils.inBlock(3*(j/3) + i/3, sudoku, num))) {
                                flag[j] = 1;
                            }
                        }
                    }
                    setSudokuCol(flag, i, num);
                }
            }//column ends here
            for( int i=0; i<9; i++) {
                if( !(Utils.inBlock(i, sudoku, num))) {
                    int flag[] = new int[9];
                    for( int j=0; j<3; j++) {
                        Numbs.it++;
                        if(!(Utils.inRow(3*(i/3) + j, sudoku, num)) && sudoku[27*(i/3) + 3*(i%3) + 9*(j/3) + j%3] == 0){
                            if(!(Utils.inColumn(3*(i%3)+ j, sudoku, num))) {
                                flag[j] = 1;
                            }
                        }
                    }
                }
            }//block ends here
        }
        for(int i=0; i<9; i++) {
            for( int j=0; j<3; j++) {
                for( int k=0; k<3; k++) {
                    Numbs.it++;
                    System.out.print(sudoku[9*i+3*j+k] + " ");
                }
                System.out.print("\t");
            }
            if(i==2 || i==5) {
                System.out.print("\n");
            }
            System.out.print("\n");
        }
        System.out.println("\n");
        System.out.println("Iterations: " + Numbs.it);

        //algorithm to see whether the program should terminate or not.
		/*System.out.println(Arrays.toString(sudoku1));
		System.out.println(Arrays.toString(sudoku));*/
        int[] temp = sudoku.clone();
        Arrays.sort(sudoku1);
        Arrays.sort(temp);
        if( sudoku1.equals(temp) ){
            term = true;
            System.out.println( "Cant do Further");
        }

    }
    void setSudokuRow(int[] temp, int i, int num) {
        int flag = 1; //if "num" can be placed at only one box of row "i" then do it otherwise hold.
        if( IntStream.of(temp).sum() == flag ) {
            for( int a=0; a<temp.length; a++) {
                Numbs.it++;
                if(temp[a] == 1 ) { flag = a; }
            }
            sudoku[9*i + flag] = num;
        }
    }
    void setSudokuCol(int[] temp, int i, int num) {
        int flag = 1; //if "num" can be placed at only one box of column "i" then do it otherwise hold.
        if( IntStream.of(temp).sum() == flag ) {
            for( int a=0; a<temp.length; a++) {
                Numbs.it++;
                if(temp[a] == 1 ) { flag = a; }
            }
            sudoku[i + 9*flag] = num;
        }
    }
    void setSudokuBlock(int[] temp, int i, int num) {
        int flag = 1; //if "num" can be placed at only one box of block "i" then do it otherwise hold.
        if( IntStream.of(temp).sum() == flag ) {
            for( int a=0; a<temp.length; a++) {
                Numbs.it++;
                if(temp[a] == 1 ) { flag = a; }
            }
            sudoku[27*(i/3) + 3*(i%3) + 9*(flag/3) + flag%3] = num;
        }
    }
    public int[] getSudoku() {
        return sudoku;
    }
}
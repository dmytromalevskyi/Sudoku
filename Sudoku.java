import java.lang.Math;
import java.util.Scanner;


class Sudoku{
    public static void main(String[] args) {
        byte[][] board = Board.generate2DArrays(2);
        Board.draw(board);
      
        board = Board.solveSudoku(board);
        Board.draw(board);

        
    }
}

class Board{
    
    // Returns a byte levelSize^2 * levelSize^2 2D array
    //
    public static byte[][] generate2DArrays(int levelSize) {
        int dimention = levelSize * levelSize;
        byte[][] array2D = new byte[dimention][dimention];
        // For testing only
        for (int i = 0; i <= array2D.length-1; i++){
            for (int j = 0; j <= array2D.length-1; j++){
                array2D[i][j] = (byte) 0;
            }
        }

        return array2D;
    }

    // Draw a board of any square dimention
    //
    public static void draw(byte[][] board) {
        int dimention = board.length;
        int level = (int) Math.sqrt(board.length);
        String empty = createRepeatedUnit(dimention, " ");

        System.out.println("dimention: "+dimention);
        System.out.println("level: "+level);
        
        // Generate borders
        String topBorder = generateTopBorder(level);
        String bottomBorder = generateBottomBorder(level);
        String seperatorBorder = generateSeperatorBorder(level);

        System.out.println(topBorder);
        for (int i = 0; i <= dimention-1; i++){
            System.out.print("┃ ");
            for (int z = 0; z <= dimention-1; z++){
                if (z == dimention-1){
                    // If the byte is 0 print " "
                    if (board[i][z] != 0)
                        System.out.print(board[i][z]);
                    else
                        System.out.print(empty);
                    System.out.print(" ┃\n");
                }
                else if (z % level == 0 & z != 0){
                    System.out.print("┃ ");
                    // If the byte is 0 print " "
                    if (board[i][z] != 0)
                        System.out.print(board[i][z]);
                    else
                        System.out.print(empty);
                    System.out.print(" ");
                } else {
                    // If the byte is 0 print " "
                    if (board[i][z] != 0)
                        System.out.print(board[i][z]);
                    else
                        System.out.print(empty);
                    System.out.print(" ");
                }
            }
            if (((i+1) % level == 0) & (i != 0) & (i != dimention-1)){
                System.out.print(seperatorBorder+"\n");
            }
        }        
        System.out.println(bottomBorder);
    }

    // Return a solved 2D array with the size of the passed one
    //
    public static byte[][] solveSudoku(byte[][] board) {
        int dimention = board.length;
        int level = (int) Math.sqrt(board.length);

        // Generate the top left box
        byte index = 1;
        while (index <= dimention){
            for (int i = 0; i <= level-1; i++){
                for (int z = 0; z <= level-1; z++){
                    board[i][z] = index;
                    index++;
                }
            }
        }

        // Generate the rest of the coloumn zero
        byte [] alreadyInColoumn = new byte[level];
        for (int i = 0; i <= level-1; i++){
            alreadyInColoumn[i] = board[i][0];
        }
        byte [] notYetInColoumn = getUncommonElements(dimention, alreadyInColoumn);
        for (int i = level; i <= dimention-1; i++){
            board[i][0] = notYetInColoumn[i-level];
        }

        // Generate the rest of the row zero
        byte [] alreadyInRow = new byte[level];
        for (int i = 0; i <= level-1; i++){
            alreadyInRow[i] = board[0][i];
        }
        byte [] notYetInRow = getUncommonElements(dimention, alreadyInRow);
        for (int i = level; i <= dimention-1; i++){
            board[0][i] = notYetInRow[i-level];
        }
        
        return board;
    }

    // Cheak if a box has distinct elements
    // coloumn and row indexing the top left corner of the box
    //
    public static boolean isBoxDistinct(byte[][] board, int row, int coloumn){
        int level = (int) Math.sqrt(board.length);
        byte[] byteArray = new byte[board.length];
        int index = 0;
        
        for (int i = coloumn; i <= coloumn+level-1; i++){
            for (int z = row; z <= row+level-1; z++){
                byteArray[index] = board[i][z];
                index++;
            }
        }
        return isByteArrayDistinct(byteArray);
    }
    
    // Cheak if a horizontal line has distinct elements
    //
    public static boolean isColoumnDistinct(byte[][] board, int coloumn){
        byte[] byteArray = new byte[board.length];

        for (int i = 0; i <= board.length-1; i++){
            byteArray[i] = board[i][coloumn];
        }

        return isByteArrayDistinct(byteArray);
    }

    // Cheak if elements repeat in a list
    //
    public static boolean isByteArrayDistinct(byte[] byteArray) {
        boolean isDistinct = true;

        for (int i = 0; i <= byteArray.length-1; i++){
            for (int z = 0; z <= byteArray.length-1; z++){
                if((byteArray[i] == byteArray[z]) & i != z)
                    isDistinct = false;
            }
        }
        return isDistinct;
    }

    // Cheak if a vertical line has distinct elements
    //
    public static boolean isRowDistinct(byte[][] board, int row){
        byte[] byteArray = new byte[board.length];

        for (int i = 0; i <= board.length-1; i++){
            byteArray[i] = board[row][i];
        }

        return isByteArrayDistinct(byteArray);
    }
    
    // Generate top border for n level
    //
    public static String generateTopBorder(int level) {
        String topBorder = "┏";
        for (int i = 1; i <= level; i++){
            if (i == level){
                for (int z = 1; z <= level*2+1; z++){
                    topBorder = topBorder + "━";
                }
                topBorder = topBorder + "┓";
            } else {
                for (int z = 1; z <= level*2+1; z++){
                    topBorder = topBorder + "━";
                }
                topBorder = topBorder + "┳";
            }
        }
        return topBorder;
    }
    
    // Generate seperator line for n level board
    //
    public static String generateSeperatorBorder(int level) {
        String seperatorBorder = "┣";
        for (int i = 1; i <= level; i++){
            if (i == level){
                for (int z = 1; z <= level*2+1; z++){
                    seperatorBorder = seperatorBorder + "━";
                }
                seperatorBorder = seperatorBorder + "┫";
            } else {
                for (int z = 1; z <= level*2+1; z++){
                    seperatorBorder = seperatorBorder + "━";
                }
                seperatorBorder = seperatorBorder + "╋";
            }
        }
        return seperatorBorder;
    }

    // Generate bottom border for n level
    //
    public static String generateBottomBorder(int level) {
        String bottomBorder = "┗";
        for (int i = 1; i <= level; i++){
            if (i == level){
                for (int z = 1; z <= level*2+1; z++){
                    bottomBorder = bottomBorder + "━";
                }
                bottomBorder = bottomBorder + "┛";
            } else {
                for (int z = 1; z <= level*2+1; z++){
                    bottomBorder = bottomBorder + "━";
                }
                bottomBorder = bottomBorder + "┻";
            }
        }
        return bottomBorder;
    }

    // Generate a random number within a range
    //
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    // Return array of uncommon elements between [1,2,3 .. max] and byteArray
    //
    public static byte[] getUncommonElements(int max, byte[] byteArray) {
        byte[] uncommonElements = new byte[max - byteArray.length];
        byte[] integersUptoMax = new byte[max];

        for (byte i = 0; i <= max-1; i++){
            integersUptoMax[i] = (byte) (i+1);
        }

        byte index = 0;
        for (byte i = 0; i <= max-1; i++){
            boolean isFound = false;
            byte uncommonValue = 1;
            
            for (byte z = 0; z <= byteArray.length-1; z++){
                uncommonValue = integersUptoMax[i];
                if (integersUptoMax[i] == byteArray[z]){
                    isFound = true;
                }
            }
            
            if (!isFound){
                uncommonElements[index] = uncommonValue;
                index++;
            }
        }

        return uncommonElements;
    }

    // Return a string with appropriate number of space
    //
    public static String createRepeatedUnit(int dimention, String unit) {
        String unitInstance = unit;
        byte widthOfCharacters = 0;
        
        while (dimention > 0) {
            dimention /= 10;
            widthOfCharacters++;
          }

        for (byte i = 1; i <= widthOfCharacters-1; i++){
            unit = unit+unitInstance;
        }
        System.out.println("The number of digits is: "+widthOfCharacters);
        System.out.println("\""+unit+"\"");
        return unit;
    }
}
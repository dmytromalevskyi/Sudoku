import java.lang.Math;
import java.util.Scanner;
import java.util.Arrays;


class Sudoku{
    public static void main(String[] args) {
        printLogo();
        int level = 2;
        byte[][] board = Board.generate2DArrays(level);
        //Board.draw(board);
        
        board = Board.solveSudoku(board);
        Board.draw(board);

        
    }

    // Print a cool Sudoku logo
    //
    public static void printLogo() {
        System.out.println();
        System.out.println("    sSSs   .S       S.    .S_sSSs      sSSs_sSSs     .S    S.    .S       S.   ");
        System.out.println("   d%%SP  .SS       SS.  .SS~YS%%b    d%%SP~YS%%b   .SS    SS.  .SS       SS.  ");
        System.out.println("  d%S'    S%S       S%S  S%S   `S%b  d%S'     `S%b  S%S    S&S  S%S       S%S  ");
        System.out.println("  S%|     S%S       S%S  S%S    S%S  S%S       S%S  S%S    d*S  S%S       S%S  ");
        System.out.println("  S&S     S&S       S&S  S%S    S&S  S&S       S&S  S&S   .S*S  S&S       S&S  ");
        System.out.println("  Y&Ss    S&S       S&S  S&S    S&S  S&S       S&S  S&S_sdSSS   S&S       S&S  ");
        System.out.println("  `S&&S   S&S       S&S  S&S    S&S  S&S       S&S  S&S~YSSY%b  S&S       S&S  ");
        System.out.println("    `S*S  S&S       S&S  S&S    S&S  S&S       S&S  S&S    `S%  S&S       S&S  ");
        System.out.println("     l*S  S*b       d*S  S*S    d*S  S*b       d*S  S*S     S%  S*b       d*S  ");
        System.out.println("    .S*P  S*S.     .S*S  S*S   .S*S  S*S.     .S*S  S*S     S&  S*S.     .S*S  ");
        System.out.println("  sSS*S    SSSbs_sdSSS   S*S_sdSSS    SSSbs_sdSSS   S*S     S&   SSSbs_sdSSS   ");
        System.out.println("  YSS'      YSSP~YSSY    SSS~YSSY      YSSP~YSSY    S*S     SS    YSSP~YSSY    ");
        System.out.println("                                                    SP                         ");
        System.out.println("                                                    Y                          ");
        System.out.println();
                                                                                       
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

    // Randomly generate values for a board until one that is valid is optained
    //
    public static byte[][] solveSudoku(byte[][] board) {
        int level = (int) Math.sqrt(board.length);

        while(Board.are2DByteArraysEqual(Board.generate2DArrays(level), board)){
            board = Board.gererateRandomValues(board);
        }

        return board;
    }

    // Return a solved 2D array with the size of the passed one
    //
    public static byte[][] gererateRandomValues(byte[][] board) {
        int dimention = board.length;
        int level = (int) Math.sqrt(board.length);

        // Sometimes there will be no byte that can pe places in the next squere (due to the method used) so everything should start all over again
        boolean invalidSudoku = false;
        
        int x = 0;
        while (x <= dimention-1){
            int boxX = (int) Math.floor(x / level) * level;

            int y = 0;
            while (y <= dimention-1){
                int boxY = (int) Math.floor(y / level) * level;

                boolean foundNumber = false;
                byte[] currentRow = board[x];
                byte[] currentColoumn = getColoumnAsArray(board, y);
                byte[] currentBox = getBoxAsArray(board, boxX, boxY);

                /*
                System.out.println("Current x,y: "+x+","+y);
                System.out.println("Current boxX,boxY: "+boxX+","+boxY);
                System.out.println("Current Box");
                for (byte num : currentBox){
                    System.out.println(num);
                }
                System.out.println("End of Current Box");
                draw(board);
                System.out.println();
                */


                int count = 0;
                while (!foundNumber){
                    byte randomNumber = getRandomNumber((byte) 1,(byte) dimention);
                    if ( !isInArray(currentRow, randomNumber) & !isInArray(currentColoumn, randomNumber) & !isInArray(currentBox, randomNumber) ){
                        board[x][y] = randomNumber;
                        foundNumber = true;
                    } else if (invalidSudoku){
                        break;
                    } else{
                        count++;
                        invalidSudoku = (count >= dimention*2) ? true : false;
                    }

                }
                y++;
                if (invalidSudoku)
                    break;
            }
            x++;
            if (invalidSudoku)
                    break;
        }
        
        if (invalidSudoku){
            return generate2DArrays(level);
            /* Original way with StackOverglowError
            // If the board generated is invalid just start all over again
            board = generate2DArrays(level);
            //System.out.println("The programme failed and the solving is being restarted...");
            board = gererateRandomValues(board);
            */
        }


        return board;
    }

    // Cheak if a box has distinct elements
    // coloumn and row indexing the top left corner of the box
    //
    public static boolean isBoxDistinct(byte[][] board, int row, int coloumn){
        byte[] byteArray = getBoxAsArray(board, row, coloumn);
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

    // Returns a byte array of the items of a 2D byte array
    //
    public static byte[] getColoumnAsArray(byte[][] board, int coloumn) {
        byte [] coloumnArray = new byte[board.length];
        
        for (int i = 0; i <= board.length-1; i++){
            coloumnArray[i] = board[i][coloumn];
        }

        return coloumnArray;
    }

    // Return the elements of the box in a single byte array
    // coloumn and row indexing the top left corner of the box
    //
    public static byte[] getBoxAsArray(byte[][] board, int row, int coloumn){
        int level = (int) Math.sqrt(board.length);
        byte[] byteArray = new byte[board.length];
        int index = 0;
        
        for (int i = row; i <= row+level-1; i++){
            for (int z = coloumn; z <= coloumn+level-1; z++){
                byteArray[index] = board[i][z];
                index++;
            }
        }
        return byteArray;
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
    public static byte getRandomNumber(byte min, byte max) {
        return (byte) ((Math.random() * (max + 1 - min)) + min);
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

    // Return if a number is in the array
    //
    public static boolean isInArray(byte[] byteArray, byte userNumber) {
        boolean isFound = false;
        
        for (byte number : byteArray){
            if (number == userNumber){
                isFound = true;
                break;
            }
        }
        return isFound;
    }

    // Cheack if 2 2D byte arrays are equal
    //
    public static boolean are2DByteArraysEqual(byte[][] first, byte[][] second) {
        boolean areDistinct = true;
        for (int i = 0; i <= first.length-1; i++){
            if (!Arrays.equals(first[i], second[i])){
                areDistinct = false;
                break;
            }
        }
        return areDistinct;
    }

    // Return a string that will have the unit repeated the number of digits dimention contains 
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
        /*
        System.out.println("The number of digits is: "+widthOfCharacters);
        System.out.println("\""+unit+"\"");
        */
        return unit;
    }
}
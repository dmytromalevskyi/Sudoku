import java.lang.Math;
import java.util.Scanner;
import java.util.Arrays;


class Sudoku{
    public static void main(String[] args) {
        //printLogo();
        //menu();

        // For board generation fixes
        int level = 4;
        byte[][] board = Board.generate2DArrays(level);
        Board.draw(board);
        
        board = Board.solveSudoku(board);
        Board.draw(board);        
        
    }


    // Main play function
    //
    public static void playSudoku () {
        boolean isGameFinished = false;
        
        int level = 0;
        boolean userMadeChoice = false;
        while (!userMadeChoice){
            System.out.println("Choose what level you want to play.\n");
            System.out.println("'1' for 1*1 board");
            System.out.println("'2' for 4*4 board");
            System.out.println("'3' for 9*9 board");
            System.out.println("'n' for (n^2)*(n^2) board");
            String userChoice = inputString("Enter your choice below: ");
            System.out.println();
            switch (userChoice){
                case "1":
                    level = 1;
                    userMadeChoice = true;
                    break;
                case "2":
                    level = 2;
                    userMadeChoice = true;
                    break;
                case "3":
                    level = 3;
                    userMadeChoice = true;
                    break;
                default:
                    System.out.println("Sorry, you have either entered invalid option or the level you are trying to access in still in development.");
                    System.out.println("Please try again. \n");
            }
        } 

        int difficulty = 0;
        userMadeChoice = false;
        while (!userMadeChoice){
            System.out.println("Choose what difficulty you want to play.\n");
            System.out.println("'1' There are barely any numbers missing.");
            System.out.println("'2' There are some numbers missing.");
            System.out.println("'3' Now we are talking!");
            String userChoice = inputString("Enter your choice below: ");
            System.out.println();
            switch (userChoice){
                case "1":
                    difficulty = 1;
                    userMadeChoice = true;
                    break;
                case "2":
                    difficulty = 2;
                    userMadeChoice = true;
                    break;
                case "3":
                    difficulty = 3;
                    userMadeChoice = true;
                    break;
                default:
                    System.out.println("Sorry, you have entered an invalid option.");
                    System.out.println("Please try again. \n");
            }
        } 

        byte[][] board = Board.generate2DArrays(level); 
        board = Board.solveSudoku(board);
        board = Board.cratePlayableSudoku(board, difficulty);
        Board.draw(board);

        while(!isGameFinished){
            Board.updateBoard(board);
        }
        menu();
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

    // Menu that the user is greeted with
    //
    public static void menu (){
        boolean userMadeChoice = false;
        
        while (!userMadeChoice){
            System.out.println("Choose what mode you want to play.\n");
            System.out.println("'1' for Play Sudoku");
            System.out.println("'2' for Other Options");
            System.out.println("'0' for Exit");
            String userChoice = inputString("Enter your choice below: ");
            System.out.println();
            switch (userChoice){
                case "1":
                    System.out.println("Loading options...\n");
                    playSudoku();
                    userMadeChoice = true;
                    break;
                case "2":
                    System.out.println("Sorry, that is still in development...\n");
                    break;
                case "0":
                    System.out.println("Good bye! Till next time!\n");
                    userMadeChoice = true;
                    System.exit(0);
                    break;
                default:
                    System.out.println("Ops... I didn't get that. Let's try again.\n");
            }
        }    
    }

    // Return user input as a string after print a message
    //
    public static String inputString (String message){
        Scanner scanner = new Scanner(System.in);

        System.out.println(message);

        String userInput = scanner.nextLine();
        return userInput;

    }// END inputString
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
            System.out.print((i+1)+" ┃ ");
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
        boolean boardContainsZeros = false;
        
        do{
            try{
                board = fillInBoard(board);
                boardContainsZeros = false;
                for (byte[] byteArray : board) {
                    if (isInArray(byteArray, (byte) 0 )){
                        boardContainsZeros = true;
                        break;
                    }                
                }
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Board generation has failed. Generating a new one...");
                int level = (int) Math.sqrt(board.length);

                return solveSudoku(Board.generate2DArrays(level));
            }
        }while (boardContainsZeros);         
        
        /*
        double count = 0;
        while(Board.are2DByteArraysEqual(Board.generate2DArrays(level), board)){
            // Uncomment the line below to get the tested method of generating
            //board = Board.gererateRandomValues(board);
            board = Board.generateSquaresSequentially(board);
            count++;
        }
        System.out.println("The board was generated "+count+" times.");
        */
        return board;
    }

    // Remove a number of values based on the difficulty a user wants from the board
    //
    public static byte[][] cratePlayableSudoku(byte[][] board, int difficulty) {
        int dimention = board.length;
        int numbersToDelete = (int) Math.ceil( (dimention*dimention) * (difficulty*0.25) );
        System.out.println("Number of empty spaces: "+(dimention*dimention - numbersToDelete));
        byte[] coordinatesX = new byte[numbersToDelete];
        byte[] coordinatesY = new byte[numbersToDelete];

        // Make all values of the 2 arrays -1
        for (int i = 0; i <= numbersToDelete-1; i++) {
            coordinatesX[i] = -1;
            coordinatesY[i] = -1;
        }

        // Generate random coordinates numbersToDelete and make sure that they are all distinct
        for (int i = 0; i <= numbersToDelete-1; i++) {
            boolean addedNumber = false;
            while (!addedNumber){
                byte randomX = getRandomNumber((byte) 0, (byte) (dimention-1));
                byte randomY = getRandomNumber((byte) 0, (byte) (dimention-1));
                if (!Board.isPairPresent(coordinatesX, coordinatesY, randomX, randomY)){
                    coordinatesX[i] = randomX;
                    coordinatesY[i] = randomY;
                    addedNumber = true;
                }
            }
        }

        // Clear the coordinates
        for (int i = 0; i <= numbersToDelete-1; i++){
            board[coordinatesX[i]][coordinatesY[i]] = 0;
        }


        return board;
    }

    // Ask the user to change the values and return updated the board
    // add byte[] unchengableX, byte[] unchengableY to make sure that generated numbers are not changed
    public static byte[][] updateBoard(byte[][] board) {
        System.out.println("Enter where you want to insert a number.");
        int coordinateX = Integer.parseInt(Sudoku.inputString("What row?"));
        int coordinateY = Integer.parseInt(Sudoku.inputString("What coloumn?"));

        // implement input verification
        // no strings
        // no coordinates of numbers that were generated
        byte userInsertion = (byte) Integer.parseInt(Sudoku.inputString("What number do you want to insert at ("+coordinateX+","+coordinateY+"):"));

        board[coordinateX-1][coordinateY-1] = userInsertion;

        draw(board);
        return board;
    }

    // Return a solved 2D array with the size of the passed one
    //
    public static byte[][] generateRandomValues(byte[][] board) {
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
        }

        return board;
    }

    // New way to generate a random board of a given size
    //
    public static byte[][] generateSquaresSequentially(byte[][] board) {
        int dimention = board.length;
        int level = (int) Math.sqrt(board.length);
        
        // Counts what box is being generated
        int boxOnRow = 0;
        int boxOnColoumn = 0;

        while ( (boxOnRow <= (dimention - level)) & (boxOnColoumn <= (dimention - level)) ){
            for (int x = boxOnRow; x <= boxOnRow+level-1; x++){

                for (int y = boxOnColoumn; y <= boxOnColoumn+level-1; y++){
                    
                    System.out.println("Current x,y: "+x+","+y);
                    System.out.println("Current boxX,boxY: "+boxOnRow+","+boxOnColoumn);
                    System.out.println("Current Box");
                    /*
                    for (byte num : currentBox){
                        System.out.print(num+", ");
                    }
                    */
                    System.out.println("End of Current Box");
                    draw(board);
                    System.out.println();
                    

                    board =  fillInBoard(board);

                }
            }
            
            // Increment boxOnRow and boxOnColoumn
            if (boxOnColoumn == (dimention-level) ){
                boxOnColoumn = 0;
                boxOnRow += level;
            }else{
                boxOnColoumn += level;
            }

        }     
        return board;
    }

    // Fills in a given box in a sudoku board cheking every single squere
    //
    public static byte[][] fillInBox(byte[][] board, int boxOnRow, int boxOnColoumn) {
        // Terminate recursion if all values are filled in (not 0's)
        byte[] boxArray = getBoxAsArray(board, boxOnRow, boxOnColoumn);
        if (!isInArray(boxArray, (byte) 0 ))
            return board;

        // If the recursion is still going
        int level = (int) Math.sqrt(board.length);
        
        // Generate the list of bytes that are from 1 to dimention
        byte[] possibleNumbers = new byte[board.length];
        for (byte i = 1; i <= board.length; i++){
            possibleNumbers[i-1] = i;
        }
        
        // Create 2D array so that each array represents a list of numbers that a particular square can be
        byte[][] listOfPossibleNumbers = new byte[board.length][];
        int index = 0;
        for (int x = boxOnRow; x <= boxOnRow+level-1; x++){
            for (int y = boxOnColoumn; y <= boxOnColoumn+level-1; y++){
                byte currentNumber = board[x][y];
                byte[] currentRow = board[x];
                byte[] currentColoumn = getColoumnAsArray(board, y);

                // If the number in the list is not zero, it means that it has already been given a value
                if (currentNumber != 0){
                    listOfPossibleNumbers[index] = new byte[0];
                }else{
                    listOfPossibleNumbers[index] = new byte[board.length];
                    // Check what values the square can be
                    for (byte i = 0; i <= possibleNumbers.length-1; i++){
                        byte possibleNumber = possibleNumbers[i];
                        if (!isInArray(currentRow, possibleNumber) & !isInArray(currentColoumn, possibleNumber) & !isInArray(boxArray, possibleNumber) ){
                            listOfPossibleNumbers[index][i] = possibleNumber;
                        }
                    } 
                }

                index++;
            }
        }           
        
        // Testing the top code above
        for (int x = 0; x <= listOfPossibleNumbers.length-1; x++){
            System.out.print("X = "+x+" and Y's are: ");
            byte[] currentWorkingList = listOfPossibleNumbers[x];

            for (int y = 0; y <= currentWorkingList.length-1; y++){
                System.out.print(currentWorkingList[y]+", ");
            }
            System.out.println();
        }

        // Compare the lists here and choose the one that (list.length > 0 and (has the most 0's) )
        // and fill in a random number from it to the coresponding square in the board
        int indexOfMostZeros = getIndexOfMostZeros(listOfPossibleNumbers);
        // Find x and y coordinates that correspond to indexOfMostZeros
        int x = boxOnRow + (int) Math.floor(indexOfMostZeros / level);
        int y = boxOnColoumn + indexOfMostZeros - (((int) Math.floor(indexOfMostZeros / level)) * level);
        System.out.println("indexOfMostZeros: "+indexOfMostZeros+ " x: "+x+" y: "+y);
        System.out.println("fillInBox below on boxOnRow, boxOnColoumn: "+boxOnRow+", "+boxOnColoumn);

        for (int i = 0; i <= listOfPossibleNumbers[indexOfMostZeros].length-1; i++){
            byte numberInArray = listOfPossibleNumbers[indexOfMostZeros][i];
            if ( numberInArray != 0){
                board[x][y] = numberInArray;
                break;
            }
        }
        
        
        
        draw(board);
        Sudoku.inputString("");
        return fillInBox(board, boxOnRow, boxOnColoumn);
    }

    // Fills in a given box in a sudoku board cheking every single squere
    //
    public static byte[][] fillInBoard(byte[][] board) {
        int dimention = board.length;
        int level = (int) Math.sqrt(dimention);
        
        // Generate the list of bytes that are from 1 to dimention
        byte[] possibleNumbers = new byte[board.length];
        for (byte i = 1; i <= board.length; i++){
            possibleNumbers[i-1] = i;
        }

        // Create 2D array so that each array represents a list of numbers that a particular square can be
        byte[][] listOfPossibleNumbers = new byte[board.length * board.length][];
        int index = 0;
        int boxOnRow = 0;
        int boxOnColoumn = 0;
        for (int x = 0; x <= dimention-1; x++){

            for (int y = 0; y <= dimention-1; y++){
                // Loop through the boxes
                // Counts what box is being generated
                boxOnRow = x - (x % level);
                boxOnColoumn = y - (y % level);

                /*
                // Testing purpuses
                System.out.println("Current x,y: "+x+","+y);
                System.out.println("Current boxX,boxY: "+boxOnRow+","+boxOnColoumn);
                System.out.println();
                */
                byte currentNumber = board[x][y];
                byte[] currentRow = board[x];
                byte[] currentColoumn = getColoumnAsArray(board, y);
                byte[] boxArray = getBoxAsArray(board, boxOnRow, boxOnColoumn);

                // If the number in the list is not zero, it means that it has already been given a value
                if (currentNumber != 0){
                    listOfPossibleNumbers[index] = new byte[0];
                }else{
                    listOfPossibleNumbers[index] = new byte[board.length];
                    // Check what values the square can be
                    for (byte i = 0; i <= possibleNumbers.length-1; i++){
                        byte possibleNumber = possibleNumbers[i];
                        if (!isInArray(currentRow, possibleNumber) & !isInArray(currentColoumn, possibleNumber) & !isInArray(boxArray, possibleNumber) ){
                            listOfPossibleNumbers[index][i] = possibleNumber;
                        }
                    } 
                }
             index++;
            }
        }

        /*
        // Testing the top code above
        for (int x = 0; x <= listOfPossibleNumbers.length-1; x++){
            System.out.print("X = "+x+" and Y's are: ");
            byte[] currentWorkingList = listOfPossibleNumbers[x];

            for (int y = 0; y <= currentWorkingList.length-1; y++){
                if (currentWorkingList[y] != 0)
                    System.out.print(currentWorkingList[y]+", ");
            }
            System.out.println();
        }
        */

        // Compare the lists here and choose the one that (list.length > 0 and (has the most 0's) )
        // and fill in a random number from it to the coresponding square in the board
        int indexOfMostZeros = getIndexOfMostZeros(listOfPossibleNumbers);
        // Find x and y coordinates that correspond to indexOfMostZeros
        int x = (int) Math.floor(indexOfMostZeros / dimention);
        int y = indexOfMostZeros - (x * dimention);
        //System.out.println("indexOfMostZeros: "+indexOfMostZeros+ " x: "+x+" y: "+y);
        //System.out.println("fillInBox below on boxOnRow, boxOnColoumn: "+boxOnRow+", "+boxOnColoumn);

        // Get a random number of the list of possible numbers
        byte numberInArray = 0;
        boolean numberObtained = false;
        while (!numberObtained){
            numberInArray = listOfPossibleNumbers[indexOfMostZeros][getRandomNumber((byte) 0, (byte) (dimention-1))];
            if (numberInArray != 0)
                numberObtained = true;
        }
        
        //System.out.println("Number that is being written is: "+numberInArray);
        board[x][y] = numberInArray;
        
        //draw(board);
        //Sudoku.inputString("");
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
        int dimention = level * level;
        String topBorder = "    ";
        for (int i = 1; i <= dimention; i++){
            if (i % (level) == 0)
                topBorder = topBorder + i + "   ";
            else
                topBorder = topBorder + i + " ";
        }

        topBorder = topBorder + "\n  ┏";
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
        String seperatorBorder = "  ┣";
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
        String bottomBorder = "  ┗";
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

    // Check if 2 2D byte arrays are equal
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

    // Return the index of the list that has the most 0's in it
    // which array lengh is not 0
    // if all arrays are of length 0 the function returns -1
    public static int getIndexOfMostZeros(byte[][] arrayOfArrays) {
        int indexOfMostZeros = -1;
        int numberOfZerosInBestArray = 0;
        
        for (int i = 0; i <= arrayOfArrays.length-1; i++){
            byte [] currentArray = arrayOfArrays[i];
            int numberOfZerosInCurrentArray = countInstensesOf(currentArray, (byte) 0);
            if (currentArray.length == 0)
                continue;

            if ( (numberOfZerosInCurrentArray >= numberOfZerosInBestArray) & (numberOfZerosInCurrentArray != currentArray.length)){
                numberOfZerosInBestArray = numberOfZerosInCurrentArray;
                indexOfMostZeros = i;
            }
        }

        return indexOfMostZeros;
    }

    // Count home many instenses of a number are there in a byte array
    //
    public static int countInstensesOf(byte[] array, byte number) {
        int count = 0;
        for (byte b : array) {
            if (b == number)
                count++;
        }

        return count;
    }

    // Check if a particular pair of numbers exist in 2 byte arrays
    //
    public static boolean isPairPresent(byte[] firstA, byte[] secondA, byte firstB, byte secondB) {
    boolean isPresent = false;
    for (int i = 0; i <= firstA.length-1; i++){
        if ((firstA[i] == firstB) & (secondA[i] == secondB))
            isPresent = true;
    }
    
    return isPresent;
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
import java.lang.Math;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;

class Sudoku{
    public static void main(String[] args) {
        printLogo();
        menu();
        /*
        // For board generation fixes
        int level = 3;
        byte[][] board = Board.generate2DArrays(level);
        byte[][] empyForDrawFunc = new byte[level*level][level*level];
        Board.draw(board,empyForDrawFunc);
        
        board = Board.solveSudoku(board);
        Board.writeTxt(board, "txtout");
        Board.writeTxt(board, "txtout");
        Board.writeTxt(board, "txtout");
        Board.draw(board, empyForDrawFunc);        
        */
    }


    // Main play function
    //
    public static void playSudoku () {
        boolean isGameFinished = false;
        
        int level = 0;
        boolean userMadeChoice = false;
        while (!userMadeChoice){
            System.out.println("Choose what grid size you want to play.\n");
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
                case "4":
                    level = 4;
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
        byte[][] fixedCoordinates = Board.getNonZerosCoordinates(board);
        Board.draw(board, fixedCoordinates);

        while(!isGameFinished){
            Board.updateBoard(board, fixedCoordinates);
            isGameFinished = !Board.isZeroIn2DArray(board);
        }

        System.out.println("\n Board complete! \n");
        menu();
    }

    // Menu for other options
    //
    public static void otherOptionsMenu() {
        boolean userMadeChoice = false;
        
        while (!userMadeChoice){
            System.out.println("Choose what mode you want to play.\n");
            System.out.println("'1' for generating a txt file");
            System.out.println("'2' for generating a pdf file");
            System.out.println("'0' for main menu");
            String userChoice1 = inputString("Enter your choice below: ");
            System.out.println();
            switch (userChoice1){
                case "1":
                    String fileName = inputString("Name your txt file: ");
                    System.out.println();               
                    int level = 0;
                    boolean userMadeChoice2 = false;

                    while (!userMadeChoice2){
                        System.out.println("Choose what grid size you want to play.\n");
                        System.out.println("'1' for 1*1 board");
                        System.out.println("'2' for 4*4 board");
                        System.out.println("'3' for 9*9 board");
                        System.out.println("'n' for (n^2)*(n^2) board");
                        String userChoice = inputString("Enter your choice below: ");
                        System.out.println();
                        switch (userChoice){
                            case "1":
                                level = 1;
                                userMadeChoice2 = true;
                                break;
                            case "2":
                                level = 2;
                                userMadeChoice2 = true;
                                break;
                            case "3":
                                level = 3;
                                userMadeChoice2 = true;
                                break;
                            case "4":
                                level = 4;
                                userMadeChoice2 = true;
                                break;
                            default:
                                System.out.println("Sorry, you have either entered invalid option or the level you are trying to access in still in development.");
                                System.out.println("Please try again. \n");
                        }
                    } 
                
                    int difficulty = 0;
                    userMadeChoice2 = false;
                    while (!userMadeChoice2){
                        System.out.println("Choose what difficulty you want to play.\n");
                        System.out.println("'1' There are barely any numbers missing.");
                        System.out.println("'2' There are some numbers missing.");
                        System.out.println("'3' Now we are talking!");
                        String userChoice = inputString("Enter your choice below: ");
                        System.out.println();
                        switch (userChoice){
                            case "1":
                                difficulty = 1;
                                userMadeChoice2 = true;
                                break;
                            case "2":
                                difficulty = 2;
                                userMadeChoice2 = true;
                                break;
                            case "3":
                                difficulty = 3;
                                userMadeChoice2 = true;
                                break;
                            default:
                                System.out.println("Sorry, you have entered an invalid option.");
                                System.out.println("Please try again. \n");
                        }
                    }
            
                    try{
                        // Clear the chosen txt file
                        File tempFile = new File(fileName+".txt");
                        FileWriter tempFileWriter = new FileWriter(tempFile, false);
                        PrintWriter tempPrintWriter = new PrintWriter(tempFileWriter);
                        tempPrintWriter.close();
                    
                        int numOfBoards = Integer.parseInt(inputString("How many games do you want to generate: "));
                        
                        for(int i = 1; i <= numOfBoards; i++){
                            // Generate a new board each time
                            byte[][] board = Board.generate2DArrays(level);
                            board = Board.solveSudoku(board);
                            board = Board.cratePlayableSudoku(board, difficulty);
                            Board.writeTxt(board, fileName);
                        }

                        System.out.println();
                        System.out.println(numOfBoards+ " games were succesfully writen to "+fileName+".txt\n");
                    } catch (IOException e){
                        // Do nothing
                    }
                    // generating txt file
                    //Board.writeTxt(board, fileName);

                    userMadeChoice2 = true;
                    break;
                case "2":
                    System.out.println("Ops... It is still in development.\n");
                    userMadeChoice2 = true;
                    break;
                case "0":
                    System.out.println();
                    userMadeChoice2 = true;
                    menu();
                    break;
                default:
                    System.out.println("Ops... I didn't get that. Let's try again.\n");
            }
        }
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
                    otherOptionsMenu();
                    userMadeChoice = true;
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

        System.out.print(message);

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
    public static void draw(byte[][] board, byte[][] fixedCoordinates) {
        int dimention = board.length;
        int level = (int) Math.sqrt(board.length);
        int digits = countDigits(dimention);
        byte[] currentCoordinate = new byte[2];
        String empty = createRepeatedUnit(digits, " ");

        // Colour strings
        String reset = "\u001b[0m";
        String redFG = "\u001b[31m";

        System.out.println("dimention: "+dimention);
        System.out.println("level: "+level);
        
        // Generate borders
        String topBorder = generateTopBorder(level, digits);
        String bottomBorder = generateBottomBorder(level, digits);
        String seperatorBorder = generateSeperatorBorder(level, digits);

        System.out.println(topBorder);
        for (int i = 0; i <= dimention-1; i++){
            System.out.print((i+1) + createRepeatedUnit( (digits-countDigits(i+1)) , " ") + " ┃ ");
            for (int z = 0; z <= dimention-1; z++){
                
                currentCoordinate[0] = (byte) i;
                currentCoordinate[1] = (byte) z;
                
                String lookChange = reset;
                if (isArrayPresentIn2DArray(currentCoordinate, fixedCoordinates))
                    lookChange = redFG;
                
                if (z == dimention-1){
                    // If the byte is 0 print " "
                    if (board[i][z] != 0)
                        System.out.print(lookChange + board[i][z] + createRepeatedUnit( (digits-countDigits(board[i][z])) , " ") + reset);
                    else
                        System.out.print(empty);
                    System.out.print(" ┃\n");
                }
                else if (z % level == 0 & z != 0){
                    System.out.print("┃ ");
                    // If the byte is 0 print " "
                    if (board[i][z] != 0)
                        System.out.print(lookChange + board[i][z] + createRepeatedUnit( (digits-countDigits(board[i][z])) , " ") + reset);
                    else
                        System.out.print(empty);
                    System.out.print(" ");
                } else {
                    // If the byte is 0 print " "
                    if (board[i][z] != 0)
                        System.out.print(lookChange + board[i][z] + createRepeatedUnit( (digits-countDigits(board[i][z])) , " ") + reset);
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

    // Draw a board of any square dimention
    //
    public static void writeTxt(byte[][] board, String fileName) {
        int dimention = board.length;
        int level = (int) Math.sqrt(board.length);
        int digits = countDigits(dimention);
        byte[] currentCoordinate = new byte[2];
        String empty = createRepeatedUnit(digits, " ");
        
        try{
            // File related variables
            File file = new File(fileName+".txt");
            FileWriter fileWriter = new FileWriter(file, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.println("dimention: "+dimention);
            printWriter.println("level: "+level);
        
            // Generate borders
            String topBorder = generateTopBorder(level, digits);
            String bottomBorder = generateBottomBorder(level, digits);
            String seperatorBorder = generateSeperatorBorder(level, digits);

            printWriter.println(topBorder);
            for (int i = 0; i <= dimention-1; i++){
                printWriter.print((i+1) + createRepeatedUnit( (digits-countDigits(i+1)) , " ") + " ┃ ");
                for (int z = 0; z <= dimention-1; z++){
                
                    currentCoordinate[0] = (byte) i;
                    currentCoordinate[1] = (byte) z;
                
                    if (z == dimention-1){
                        // If the byte is 0 print " "
                        if (board[i][z] != 0)
                            printWriter.print(board[i][z] + createRepeatedUnit( (digits-countDigits(board[i][z])) , " "));
                        else
                            printWriter.print(empty);
                        printWriter.print(" ┃\n");
                    }
                    else if (z % level == 0 & z != 0){
                        printWriter.print("┃ ");
                        // If the byte is 0 print " "
                        if (board[i][z] != 0)
                            printWriter.print(board[i][z] + createRepeatedUnit( (digits-countDigits(board[i][z])) , " "));
                        else
                            printWriter.print(empty);
                        printWriter.print(" ");
                    } else {
                        // If the byte is 0 print " "
                        if (board[i][z] != 0)
                            printWriter.print(board[i][z] + createRepeatedUnit( (digits-countDigits(board[i][z])) , " "));
                        else
                            printWriter.print(empty);
                        printWriter.print(" ");
                    }
                }
                if (((i+1) % level == 0) & (i != 0) & (i != dimention-1)){
                    printWriter.print(seperatorBorder+"\n");
                }
            }        
            printWriter.println(bottomBorder);

            printWriter.close();
        } catch (IOException e){
            //do nothing
        }
    }

    // Randomly generate values for a board until one that is valid is optained
    //
    public static byte[][] solveSudoku(byte[][] board) {
        boolean boardContainsZeros = false;
        
        do{
            try{
                board = fillInBoard(board);
                boardContainsZeros = false;
                boardContainsZeros = isZeroIn2DArray(board);
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Board generation has failed. Generating a new one...");
                int level = (int) Math.sqrt(board.length);

                return solveSudoku(Board.generate2DArrays(level));
            }
        }while (boardContainsZeros);         
        
        return board;
    }

    // Remove a number of values based on the difficulty a user wants from the board
    //
    public static byte[][] cratePlayableSudoku(byte[][] board, int difficulty) {
        int dimention = board.length;
        int numbersToDelete = (int) Math.ceil( (dimention*dimention) * (difficulty*0.25) );
        //System.out.println("Number of empty spaces: "+(dimention*dimention - numbersToDelete));
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

    // Return a list of coordinates that are not 0's
    //
    public static byte[][] getNonZerosCoordinates(byte[][] board) {
        ArrayList<byte[]> listOfCoordinates = new ArrayList<byte[]>();
        
        for (byte x = 0; x <= board.length-1; x++){
            for (byte y = 0; y <= board.length-1; y++){
                if (board[x][y] != 0){
                    byte[] setOfCoordinates = new byte[2];
                    setOfCoordinates[0] = x;
                    setOfCoordinates[1] = y;
                    listOfCoordinates.add(setOfCoordinates);
                }
            }
        }

        // Converting to byte[][]
        byte[][] arrayOfCoordinates = new byte[listOfCoordinates.size()][2];
        arrayOfCoordinates = listOfCoordinates.toArray(arrayOfCoordinates);

        return arrayOfCoordinates;
    }

    // Ask the user to change the values and return updated the board
    // add byte[] unchengableX, byte[] unchengableY to make sure that generated numbers are not changed
    public static byte[][] updateBoard(byte[][] board, byte[][] fixedCoordinates) {
        int level = (int) Math.sqrt(board.length);

        System.out.println("Enter where you want to insert a number.");
        int coordinateX = Integer.parseInt(Sudoku.inputString("Row: "));
        int coordinateY = Integer.parseInt(Sudoku.inputString("Column: "));

        // Checking if the user has chosen a not fixed coordinate
        byte[] userCoordinates = new byte[2];
        userCoordinates[0] = (byte) (coordinateX-1);
        userCoordinates[1] = (byte) (coordinateY-1);
        if ( isArrayPresentIn2DArray(userCoordinates, fixedCoordinates) ){
            System.out.println("You cannot change the number at ("+coordinateX+","+coordinateY+")\n");
            return Board.updateBoard(board, fixedCoordinates);
        }


        // implement input verification
        // no strings
        byte userInsertion = (byte) Integer.parseInt(Sudoku.inputString("What number do you want to insert at ("+coordinateX+","+coordinateY+"): "));
        System.out.println();

        // Checking if no such number in row, column and box
        if ( isInArray(board[coordinateX-1], userInsertion)){
            System.out.println("There is a "+userInsertion+" already in the same row.\n");
            return Board.updateBoard(board, fixedCoordinates);
        } else if ( isInArray(getColumnAsArray(board, coordinateY-1), userInsertion)){
            System.out.println("There is a "+userInsertion+" already in the same column.\n");
            return Board.updateBoard(board, fixedCoordinates);
        } else if ( isInArray(getBoxAsArray(board, coordinateX - 1 - ((coordinateX-1) % level), coordinateY - 1 - ((coordinateY-1) % level)), userInsertion)){
            System.out.println("There is a "+userInsertion+" already in the same box.\n");
            return Board.updateBoard(board, fixedCoordinates);
        }



        board[coordinateX-1][coordinateY-1] = userInsertion;       
        draw(board, fixedCoordinates);
        return board;
    }


    // Fills in a given box in a sudoku board cheking every single squere
    //
    public static byte[][] fillInBoard(byte[][] board) {
        /*
        //Testing
        byte[][] empyForDrawFun = new byte[board.length*board.length][board.length*board.length];
        Board.draw(board,empyForDrawFun);
        */

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
        int boxOnColumn = 0;
        for (int x = 0; x <= dimention-1; x++){

            for (int y = 0; y <= dimention-1; y++){
                // Loop through the boxes
                // Counts what box is being generated
                boxOnRow = x - (x % level);
                boxOnColumn = y - (y % level);

                /*
                // Testing purpuses
                System.out.println("Current x,y: "+x+","+y);
                System.out.println("Current boxX,boxY: "+boxOnRow+","+boxOnColumn);
                System.out.println();
                */
                byte currentNumber = board[x][y];
                byte[] currentRow = board[x];
                byte[] currentColumn = getColumnAsArray(board, y);
                byte[] boxArray = getBoxAsArray(board, boxOnRow, boxOnColumn);

                // If the number in the list is not zero, it means that it has already been given a value
                if (currentNumber != 0){
                    listOfPossibleNumbers[index] = new byte[0];
                }else{
                    listOfPossibleNumbers[index] = new byte[board.length];
                    // Check what values the square can be
                    for (byte i = 0; i <= possibleNumbers.length-1; i++){
                        byte possibleNumber = possibleNumbers[i];
                        if (!isInArray(currentRow, possibleNumber) & !isInArray(currentColumn, possibleNumber) & !isInArray(boxArray, possibleNumber) ){
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
            System.out.print( ((int) (Math.floor(x / dimention)) + 1) +","+ (x - (int) (Math.floor(x / dimention) * dimention) + 1) +" possible numbers are: ");
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
        //System.out.println("fillInBox below on boxOnRow, boxOnColumn: "+boxOnRow+", "+boxOnColumn);

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
        
        //draw(board,empyForDrawFun);
        //Sudoku.inputString("");
        return board;
    }

    // Count number of digits in a number
    //
    public static int countDigits(int num) {
        int count = 0;

        while(num != 0){
            num = num/10;
            count++;
        }
        
        return count;
    }

    // Cheak if a box has distinct elements
    // column and row indexing the top left corner of the box
    //
    public static boolean isBoxDistinct(byte[][] board, int row, int column){
        byte[] byteArray = getBoxAsArray(board, row, column);
        return isByteArrayDistinct(byteArray);
    }
    
    // Cheak if a horizontal line has distinct elements
    //
    public static boolean isColumnDistinct(byte[][] board, int column){
        byte[] byteArray = new byte[board.length];

        for (int i = 0; i <= board.length-1; i++){
            byteArray[i] = board[i][column];
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
    public static byte[] getColumnAsArray(byte[][] board, int column) {
        byte [] columnArray = new byte[board.length];
        
        for (int i = 0; i <= board.length-1; i++){
            columnArray[i] = board[i][column];
        }

        return columnArray;
    }

    // Return the elements of the box in a single byte array
    // column and row indexing the top left corner of the box
    //
    public static byte[] getBoxAsArray(byte[][] board, int row, int column){
        int level = (int) Math.sqrt(board.length);  
        byte[] byteArray = new byte[board.length];
        int index = 0;
        
        for (int i = row; i <= row+level-1; i++){
            for (int z = column; z <= column+level-1; z++){
                byteArray[index] = board[i][z];
                index++;
            }
        }
        return byteArray;
    }
    
    // Generate top border for n level
    //
    public static String generateTopBorder(int level, int digits) {
        int dimention = level * level;
        String topBorder = createRepeatedUnit(digits+3, " ");
        for (int i = 1; i <= dimention; i++){
            if (i % (level) == 0)
                topBorder = topBorder + i + createRepeatedUnit( (digits-countDigits(i)) , " ") + "   ";
            else
                topBorder = topBorder + i + createRepeatedUnit( (digits-countDigits(i)) , " ") +" ";
        }

        topBorder = topBorder + "\n" + createRepeatedUnit(digits+1, " ") + "┏";
        for (int i = 1; i <= level; i++){
            if (i == level){
                for (int z = 1; z <= ((level*digits)+level+1); z++){
                    topBorder = topBorder + "━";
                }
                topBorder = topBorder + "┓";
            } else {
                for (int z = 1; z <= ((level*digits)+level+1); z++){
                    topBorder = topBorder + "━";
                }
                topBorder = topBorder + "┳";
            }
        }
        return topBorder;
    }
    
    // Generate seperator line for n level board
    //
    public static String generateSeperatorBorder(int level, int digits) {
        String seperatorBorder = createRepeatedUnit(digits+1 , " ") + "┣";
        for (int i = 1; i <= level; i++){
            if (i == level){
                for (int z = 1; z <= ((level*digits)+level+1); z++){
                    seperatorBorder = seperatorBorder + "━";
                }
                seperatorBorder = seperatorBorder + "┫";
            } else {
                for (int z = 1; z <= ((level*digits)+level+1); z++){
                    seperatorBorder = seperatorBorder + "━";
                }
                seperatorBorder = seperatorBorder + "╋";
            }
        }
        return seperatorBorder;
    }

    // Generate bottom border for n level
    //
    public static String generateBottomBorder(int level, int digits) {
        String bottomBorder = createRepeatedUnit(digits+1 , " ") + "┗";
        for (int i = 1; i <= level; i++){
            if (i == level){
                for (int z = 1; z <= ((level*digits)+level+1); z++){
                    bottomBorder = bottomBorder + "━";
                }
                bottomBorder = bottomBorder + "┛";
            } else {
                for (int z = 1; z <= ((level*digits)+level+1); z++){
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

    // Check if an array is present in a 2D array
     public static boolean isArrayPresentIn2DArray(byte[] byteArray, byte[][] array2d) {
        boolean isPresent = false;
        int lengthOfByteArray = byteArray.length;
        for (int i = 0; i <= array2d.length-1; i++){
            if (lengthOfByteArray != array2d[i].length)
                continue;
            
            for (int j = 0; j <= array2d[i].length-1; j++){
                if (byteArray[j] != array2d[i][j])
                    break;

                if ((j == array2d[i].length-1) & (byteArray[j] == array2d[i][j]))
                    isPresent = true;

            }
        }

        return isPresent;
     }

    // Return a string that will have the unit repeated the number of digits dimention contains 
    //
    public static String createRepeatedUnit(int digits, String unit) {
        String unitInstance = unit;
        
        if (digits == 0)
            return "";
    
        for (byte i = 1; i <= digits-1; i++){
            unit = unit+unitInstance;
        }
        
        return unit;
    }

    // Check if a 2D array contains any zeros
    //
    public static boolean isZeroIn2DArray(byte[][] board) {
        boolean boardContainsZeros = false;
            for (byte[] byteArray : board) {
                if (isInArray(byteArray, (byte) 0 )){
                    boardContainsZeros = true;
                    break;
                }                
            }
        
        return boardContainsZeros;
    }
}
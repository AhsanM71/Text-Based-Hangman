// Imported the java.util and java.io packages
  import java.util.*;
  import java.io.*;
class CPTOption2  {
  
// Declaring global variables, so they can be used in main method() and my own methods. 
  static String word = "";
  static char[] hidden;
  static int len;
  static char c, input;
  static String answer;
  static char code = 'r';
  static int score = 3;
  int i, count;

// The main method
   public static void main ( String[] args )throws IOException{
// Creating an obj inorder to call the methods
     CPTOption2 call = new CPTOption2();

     /* Within these while loops all the methods I have created are being called. I have created a menu for the user,
      * if there score becomes zero that means the game is over. So I have created a friendly output telling the
      * user they have lost. The code at the bottom prompts the user with a question. If they would like to restart
      * the game they can by pressing the letter r. So if the user wishes to play again the if statement checks if
      * the user press r, and if so it redeclares the variable code from a to r, and also resets there score back to
      * zero. once this happens the while loop at the top checks if code equals r and if so the game will run again.
      * This time if the user doesn't want to restart the game they can stop the game by pressing any letter other
      * than r. So yet again the if statement checks if the user has inputted the letter r, and in this case they 
      * have not. So therefore the char code variable will not store r and instead it will store its orginal value.
      * So when this happens the while loop checks if code equals r and it will not so the methods will not run.
      * The second while loop is checking if score doesn't equal 0, and if it doesn't it will execute the code 
      * within the loop.
      */
     while (code == 'r'){
       call.hardwareWords();
       call.gameRules();
       hidden = call.hidesWord(word);
       while (score != 0){
         input = call.userInput();
         call.changeWord(input, word);
         call.gameWon();
       }
       
       code = 'a';
       System.out.println("Sorry you lost :( To restart press \"r\" or to stop press anything else :)");
       Scanner s = new Scanner (System.in);
       String r = s.nextLine();
       if (r.equalsIgnoreCase("r")){
         code = 'r';
         score = 3;
       }
     }
   }
  
/* This part of the code is within an array and the random word is passed through it and is declared as w. It takes 
 * the length of the random word, sets the char variable c as "*", and the char a variable as " ". The for loop will 
 * repeat for the number of letters the random word has. In the if statement the code is checking letter by letter  
 * if the word has any spaces and if so then replace the "*" with a space. If there are no spaces the code replaces  
 * the letters with the "*" symbol using an array. Fianlly it will print out these results and since its a char 
 * method the code returns the variable hidden back into the main method where this hidesWord method is being
 * called.
 */ 
   public char[] hidesWord(String w){
     len = w.length();
     hidden = new char[len];
     c = '*';
     char a = ' ';
     for (i = 0; i < len; i++){
       if (w.charAt(i) == a){
         hidden[i] = a;
       }else{
         hidden[i] = c;
       }
     }
     System.out.println(hidden);
     return hidden;
   }
   
   /* Within this changeWord method there is a for and if statements, the userInput and random word are passed through
    * this method. The for loop will repeat for the number of letters the random word has. within the for loop there
    * is an if statement, the if statement checks if the userInput equals the all the random words letters. If they 
    * do equal then instead of the star symbol getting printed, the letter the user guesses correctly gets printed.
    * If the user guesses a letter correctly a score of 3 will pop up. If the user guesses incorrectly the else if 
    * statement will compare the user's inputted letter with all the letters in the word. Each time it checks this
    * the variable check gets added up by one. If the user entered an incorrect letter the "check" integer will equal 
    * the number of letters the word has. And the other if statement checks this and if it becomes true score get's
    * subtracted by one and the most recent score will pop up.
    */
   public void changeWord(char replace, String w){
     int check = 0;
     int x = 0;
     for (i = 0; i < len; i++){
       if (String.valueOf(replace).equalsIgnoreCase(String.valueOf(w.charAt(i)))){
         hidden[i] = w.charAt(i);
// The x++ is there to make sure the print statement in the if statement only prints out once.
         x++;
         if (x == 1){
           System.out.println("Number of tries remaining: " + score);
         }
       }
       else if (replace != w.charAt(i)){
         check++;
       }
     }
     if (check == len){
       score--;
       System.out.println("Number of tries remaining: " + score);
     }
     System.out.println(hidden); 
   }
   
   /* Within the gmaeWon method we have a for loop and if statements. The for loop will repeat for the number of 
    * letters the random word has. Within the for loop there is an if statement which is checking if all the letters
    * are spelled out (aka they changed from the star symbol to the guessed letters). If so then looksForChange gets 
    * added up for the number of stars that have changed into letters, and if that number equals the length of the 
    * word, then a congrats statement will get outputted. And finally the game would end.
    */
   public void gameWon(){
     int looksForChange = 0;
     for (i = 0; i < len; i++){
       if (hidden[i] != '*'){
         looksForChange++;
       }
     }
     if (looksForChange == len){
       System.out.println("Congratulations!...You Won!");
       System.exit(0);
     }
   }
   
   /* Within this userInput method the user is prompted with a question, asking to guess a letter. the user's 
    * response is stored in the char variable called guess. Since this is a char method something needs to be 
    * returned so the guess is returned to where the method is being called (which is the main method).
    */
   public char userInput(){
     char guess;
     System.out.print("guess letter: ");
     Scanner input = new Scanner (System.in);
     guess = input.next().charAt(0);
     return guess;
   }
   
   /* In the gamesRules method a text file called rules is being read by the code and is getting store in the String
    * variable called require. Then it is printed out.
    */
   public void gameRules()throws IOException{
     String require;
     File helpFile = new File ("rules.txt");
     Scanner scan = new Scanner (helpFile);
     while(scan.hasNextLine()){
       require = scan.nextLine();
       System.out.println("                                       ________________________________________________________________________________________________________");
       System.out.println("                                         " + require);
     }
   }
   
/* Within this hadwareWords method I have declared a few variables, I have also made a random number generator; 
 * which generators numbers between 1-15. Under that code a text file is called which is named "words" and is being 
 * read by the code and gets stored in randomWord. Before it gets stored a variable called lineNumber is within the
 * while loop, its purpose is to compare the random number which is generated by Math.random. The while loop will
 * always run once since lineNumber = -1, the purpose of this is whithin the if statement the code checks if 
 * lineNumber will equal randomNum. If it becomes false the input.nextLine will go to the next random word in the 
 * text file "words", and the while loop will run again. It will keep on running until lineNumber equals randomNum.
 * Once this is true depending on the certain amount of times the while loop has ran, it will give you the random 
 * Word in the number of times the while loop has ran. Finally the randomWord is redeclared into the string called 
 * word.
 */
   public void hardwareWords()throws IOException{
     int index = 0;
     int i = 0;
     int lineNumber = -1;
     String randomWord;
     double convert = 15 * Math.random();
     int randomNum = (int)(convert);
     
     File obj = new File ("words.txt");
     Scanner input = new Scanner (obj);
     while(input.hasNextLine()){
       lineNumber++;
       if (randomNum == lineNumber){
         randomWord = input.nextLine();
         word = randomWord;
       }
       input.nextLine();
     }
     // Prints put the randomWord.
     System.out.println(word);
   }
   
}

  

   
   
   
 

   
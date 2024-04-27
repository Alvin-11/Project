// ---------------------------------------------------------
// Assignment (3)
// Written by: (Alvin Biju  40278182       Youssef Youssef 40285384)
// For COMP 249 Sections (QQ & S) – Winter 2024
// ---------------------------------------------------------
//Date: 11-04-2024
/* The purpose of this program is to collect, organize, and manage
a database of hundreds of words from different topics, to be turned
into an interactive program allowing the user to browse different topics
with different vocabularies, create or remove new topics, implement or 
remove words and modify the current ones.
*/

/**
*
* @author Youssef Youssef
* ID: 40285384
* @author Alvin Biju
* ID: 40278182
* @version 1.0
* COMP249
* Assignment #3
* Due Date: April 11, 2024
*/

import java.io.*;
import java.util.Scanner;/** Imports the class Scanner*/
import java.util.ArrayList;/** Imports the arraylist class*/
import java.util.InputMismatchException;



/**
 * Main method 
 * @param args  
 * @return
 */
public class driver{

	public static void main(String[] args) {
		
		
		int MenuSelection = 0 ; // Used to select an option in the menu bar
		Scanner keyboard = new Scanner(System.in); // Declare the Scanner into the variable named "keyboard"
		ArrayList<SinglyLinkedList> wordlist = new ArrayList<>(); // This variable will contain the wordlist of the input file
		DoublyLinkedList topiclist = new DoublyLinkedList(); // This variable will contain all the topic and wordlist 
		
		
		while (true) { // Will keep repeating the menu bar until the exit option is chosen
		System.out.println("-----------------------------------------");
		System.out.println(" Vocabulary Control Center");
		System.out.println("-----------------------------------------");
	    System.out.println("1 browse a topic");
	    System.out.println("2 insert a new topic before another one");
	    System.out.println("3 insert a new topic after another one");
	    System.out.println("4 remove a topic");
	    System.out.println("5 modify a topic");
	    System.out.println("6 search topics for a word");
	    System.out.println("7 load from a file");
	    System.out.println("8 Show all words starting with a given letter");
	    System.out.println("9 save to file");
	    System.out.println("0 Exit");
	    System.out.println("-----------------------------------------");
	    System.out.println("Enter Your Choice: ");
	    boolean error = false;
	    
	    MenuSelection = keyboard.nextInt();
	    keyboard.nextLine();
	    
	    
	    if (!error) {
	    
	    if (MenuSelection == 1) {// This option allows the user to display the topics and the wordlist associated with it
	    	while(true) {
	    	System.out.println("-----------------------------------------");
			System.out.println("           Pick a topic");
			System.out.println("-----------------------------------------");
			topiclist.displayAlltopic();
			System.out.println("0: Exit");
			System.out.println("-----------------------------------------");
		    System.out.println("Enter Your Choice: ");
		    int topicChoice = 0;
		    try {
			topicChoice = keyboard.nextInt();
			keyboard.nextLine();
		    }catch(InputMismatchException e){
		    	 System.out.println("Invalid Entry");
	                keyboard.nextLine(); // junk
	                continue; 
		    }
			if (topicChoice > 0) {
				topiclist.displaySpecificWordlist(topicChoice);
			}
			else if (topicChoice == 0) {
				break;
			}
		    }
	    	
		
	    }
	    else if (MenuSelection == 2) {// This option allows the user to insert a topic and a wordlist before another one
	    	SinglyLinkedList newlist = new SinglyLinkedList();
	    	System.out.println("-----------------------------------------");
			System.out.println("           Pick a topic");
			System.out.println("-----------------------------------------");
			topiclist.displayAlltopic();
			System.out.println("0: Exit");
			System.out.println("-----------------------------------------");
	    	System.out.println("Enter your choice: ");
	    	
	    	int topicChoice = 0;
	    	try {
	        topicChoice = keyboard.nextInt();
	    	keyboard.nextLine();
	        }catch(InputMismatchException e){
	    	 System.out.println("Invalid Entry");
               keyboard.nextLine(); // junk
               continue; 
	        }
	    	
	    	
	    	int amountoftopics = topiclist.amountofTopics(); 
	    	
	    	if (topicChoice!=0 && topicChoice<= amountoftopics) { 
	    	System.out.println("Enter a topic name: ");
	    	String newTopic = keyboard.nextLine(); // This varible keeps track of the new topic
	    	System.out.println("Enter a word - to quit press Enter: ");
	    	
	    	
	    	while(true) { // This keeps track of the new wordlist and insert into a singlelinkedlist
	    		
	    		String line = keyboard.nextLine();
	    		if (line.isEmpty()) {
	                // Break out of the loop if an empty line is encountered
	                break;
	            }
	    		else {
	    			newlist.insertWordAtTheEnd(line);
	    		}
	    		

	    	}
	    	topiclist.InsertBeforeAnotherTopic(topicChoice, newTopic, newlist);}
	    	else { 
	    		System.out.println("The topic number entered is not valid. Please try again.");
	    	}
	    }
	    else if (MenuSelection == 3) {// This option allows the user to insert a topic and a wordlist after another one
	    	SinglyLinkedList newlist = new SinglyLinkedList();
	    	System.out.println("-----------------------------------------");
			System.out.println("           Pick a topic");
			System.out.println("-----------------------------------------");
			topiclist.displayAlltopic();
			System.out.println("0: Exit");
			System.out.println("-----------------------------------------");
	    	System.out.println("Enter your choice: ");
	    	int topicChoice = 0;
	    	
	    	try {
	        topicChoice = keyboard.nextInt();
	    	keyboard.nextLine();
	    	 }catch(InputMismatchException e){
		    	 System.out.println("Invalid Entry");
	               keyboard.nextLine(); // junk
	               continue; 
		        }
	    	
	    	int amountoftopics = topiclist.amountofTopics(); 
	    	
	    	if (topicChoice!=0 && topicChoice<= amountoftopics) { 
	    	System.out.println("Enter a topic name: ");
	    	String newTopic = keyboard.nextLine(); // This variable keeps track of the new topic
	    	System.out.println("Enter a word - to quit press Enter: ");
	    	String newwordlist;
	    	
	    	while(true) {  // This keeps track of the new wordlist and insert into a singlelinkedlist
	    		String line = keyboard.nextLine();
	    		if (line.isEmpty()) {
	                // Break out of the loop if an empty line is encountered
	                break;
	            }
	    		else {
	    			newlist.insertWordAtTheEnd(line);
	    		}
	    		

	    	}
	    	topiclist.InsertAfterAnotherTopic(topicChoice, newTopic, newlist);}
	    	else { 
	    		System.out.println("The topic number entered is not valid. Please try again.");
	    	}
	    }
	    else if (MenuSelection == 4) { // This option allow the user to remove a specific topic 
	    	System.out.println("-----------------------------------------");
			System.out.println("           Pick a topic");
			System.out.println("-----------------------------------------");
			topiclist.displayAlltopic();
			System.out.println("0: Exit");
			System.out.println("-----------------------------------------");
	    	System.out.println("Enter your choice: ");
	    	int topicChoice = 0;
	    	
	    	try {
	    	topicChoice = keyboard.nextInt();
	        }catch(InputMismatchException e){
	    	 System.out.println("Invalid Entry");
              keyboard.nextLine(); // junk
              continue; 
	        }
	    	
	    	
	    	topiclist.removeTopic(topicChoice);
	    }
	    else if (MenuSelection == 5) {  // This option allow the user to modify a specific topic 
	    	int choice = 0;
		do {
			System.out.println("-----------------------------------------");
			System.out.println("           Pick a topic");
			System.out.println("-----------------------------------------");
            topiclist.displayAlltopic();
            System.out.println("0: Exit");
            System.out.println("-----------------------------------------");
            System.out.println("Enter your choice:");
            
            try {
            choice = keyboard.nextInt();
            keyboard.nextLine(); //Junk
		    }catch(InputMismatchException e){
	    	 System.out.println("Invalid Entry");
             keyboard.nextLine(); // junk
             continue; 
	        }

            if (choice == 0) {
                System.out.println("Exiting");
                break;
            }

            if (choice < 1 || choice > topiclist.amountofTopics()) {
                System.out.println("Invalid topic number. Please try again.");
                continue;
            }

            // Get selected topic
            String selectedTopic = topiclist.getTopicName(choice);

            // Displays options to the user
            System.out.println("-----------------------------------------");
            System.out.println("           Modify Topics Menu            ");
            System.out.println("-----------------------------------------");
            System.out.println("a add word");
            System.out.println("r remove word");
            System.out.println("c change word");
            System.out.println("0 Exit");
            System.out.println("-----------------------------------------");
            System.out.println("Enter your choice:");
            
            
            String option = keyboard.next();
            keyboard.nextLine(); 
            

            switch (option) {
                case "a": // Add a word inside a specific topic 
                    System.out.println("Enter the word to add:");
                    String wordToAdd = keyboard.nextLine();
                    
                   
                    topiclist.addWord(selectedTopic, wordToAdd);
                    break;
                case "r": // Remove a word inside a specific topic 
                    System.out.println("Enter the word to remove:");
                    String wordToRemove = keyboard.nextLine();
                    boolean removed = topiclist.removeWord(selectedTopic, wordToRemove);
                    if (removed)
                        System.out.println("Word removed successfully.");
                    else
                        System.out.println("Word not found.");
                    break;
                case "c": // Change a word to another one inside a specific topic 
                    System.out.println("Enter the word to change:");
                    String oldWord = keyboard.nextLine();
                    System.out.println("Enter the new word:");
                    String newWord = keyboard.nextLine();
                    boolean changed = topiclist.changeWord(selectedTopic, oldWord, newWord);
                    if (changed)
                        System.out.println("Word changed successfully.");
                    else
                        System.out.println("Word not found.");
                    break;
                case "0":
                    // Back to main menu
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            break;

        } while (choice != 0);

	    }
	    else if (MenuSelection == 6) { // This option display all the topic that has a specific word
	    	 System.out.println("Enter the word to search for:");
	    	 String wordToSearch = keyboard.nextLine();
	    	 topiclist.searchTopicsForWord(wordToSearch);
	    }
	   
	    else if (MenuSelection == 7) { // This option load all the topic and the wordlist from a file specified by the user
	    	String InputFile;
	    	System.out.println("Enter the name of the input file: ");
	    	try{InputFile = keyboard.nextLine();
	    	System.out.println("Done loading");
	    	String topicName = "";
	    	
	    	try (BufferedReader file = new BufferedReader(new FileReader(InputFile))) { // Reads the file 
	            String line = file.readLine();
	            int counter = 0;
	           
	            while (true) {// Inputs the content of the file inside the topiplist with parameters as topic and wordlist
	            	
	            	file.mark(100); // keep the original pointer
	                String nextLine = file.readLine(); // check if the nextline is empty
	                file.reset();// reset to the original pointer
	                
	                if (line==null && nextLine==null) {
	                     // Stop reading when two empty lines are encountered
	                	topiclist.insertAtEnd(topicName, wordlist.get(counter-1));
	                    break;
	                    }
	                
	                if (line.trim()!= null && !(line.trim()).isEmpty() ) {
	                	
	                	if ((line.trim()).charAt(0)=='#') {
	                	
	                	wordlist.add(new SinglyLinkedList());
	                	
	                	if (counter>0) {
	                		if (topicName!=null) {
		                	topiclist.insertAtEnd(topicName, wordlist.get(counter-1));}}
	                	topicName = line.substring(1);
	                	counter++;
	                	
	                	}
	                	else if (counter > 0)
                		{wordlist.get(counter-1).insertWordAtTheEnd(line.trim());}
	      
	                
	                	}
	            
	             
	               line = file.readLine();
	            }
	        } catch (IOException e) {
	        	System.out.println("You have entered a wrong file or the file cannot be found.");
	        } catch(Exception e) {
	        	 e.printStackTrace();
	        }
	    }
	    	catch(Exception e) {
	    		System.out.println("You have entered a wrong input.");
	    	}
	    	
	    	
	    	
	    	
	    }
	    else if (MenuSelection == 8) {// This option allows the user to find all the words that start with a specific letter
	    	
	    	System.out.println("What is the the first letter of the word you are searching for?");
	    	char character = keyboard.nextLine().charAt(0);
	    	ArrayList<String> arraylist = topiclist.GetSortedArrayList(character);
	    	for (String element : arraylist) {
	            System.out.println(element);;
	        }
	    	if (arraylist.size() < 1) {
	    		System.out.println("There is no such word found inside all the vocab objects.");
	    	}
	    }
	    
	    else if (MenuSelection == 9) { // This option allows the user to save all the topic and the wordlits created inside a default file named 'Saved_file.txt'
	    	
	    	try {
	            
	             FileWriter writer = new FileWriter("Saved_file.txt");
	             writer.close();
	         } catch (IOException e) {
	             System.out.println("An error occurred: " + e.getMessage());
	         }
	    	 topiclist.SavetoFile();
	    	 System.out.println("The data has been saved into a txt file named:'Saved_file.txt' ");
	    }
	    else if (MenuSelection == 0) { // This option allows the user to exit the menu
	    	keyboard.close();
	    	System.exit(0);
	    }
		}}
		
	}

}

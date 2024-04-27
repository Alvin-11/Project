import java.util.ArrayList;/** Imports the arraylist class*/

/** This is the single linked list*/
public class SinglyLinkedList {
	/**This is the inner node1 class which will contain the word of the topics*/
private class Node1 {
	// The variable below contains the link to the next item to an element and storage for the word inputed.
    private String words;
    private Node1 next;
   
    /**
     * Creates Node Object
     * @param words - Set of words
     */
    public Node1(String words) {// parameterized constructor
        this.words = words;
        this.next = null;
    }

 }
// The variable below contains the element which is at the top of the list
	private Node1 head;
	
	public SinglyLinkedList() {
	    this.head = null;
	}
	
	/**
	 * Visual representation of all words
	 */
	public void displayAllWords() {
		Node1 current = head; // Start from the head of the list
		int counter = 0;
	    while (current != null) {
	    	if ((counter+1)%4 == 0) { 
        		System.out.println((counter+1)+ ": "+ current.words + "			");}
        	else {
	        System.out.print((counter+1)+ ":"+ current.words + "			 ");}
	        current = current.next;
	        counter++;
	    }
	    System.out.println();

	    // Reset the current node to the head to go back to the front
	    current = head;
	}
	
	/**
	 * Insertion of a word into a specified topic 
	 */
	public void insertWordAtTheEnd(String word) {
	    

	    // If the word does not exist, proceed with adding it to the end of the list
	    Node1 newWordNode = new Node1(word);
	    if (head == null) {
	        head = newWordNode;
	    } else {
	        Node1 current = head;
	        while (current.next != null) {
	            current = current.next;
	        }
	        current.next = newWordNode;
	    }
	}

	/**
	 * Checks if a word already exists in a specified topic
	 * @return false
	 */ 
	public boolean wordExists(String word) {
	    Node1 current = head;
	    while (current != null) {
	        if (current.words.equals(word)) {
	            return true; // Word already exists
	        }
	        current = current.next;
	    }
	    return false; // word does not exist
	}

	 /**
     * Saving set of words into a new implemented file
     * @return String - saves set of words
     */
	public String SavetoFile() { 
		Node1 current = head;
		String string = new String();
		
		while (current != null) {
			
			string += (current.words + "\n");
			current = current.next;
			 
		}
		return string;
		
		 
	}
	
	 /**
     * Method which filters selection of words
     * @param character - filtering selection of words
     * @return arraylist - sorted list
     */
	public ArrayList GetSortedArrayList(char character) {
		Node1 current = head;
		ArrayList<String> arraylist =  new ArrayList<>();
		
		while (current != null) {
			
			if (current.words.charAt(0)==character) {
				arraylist.add(current.words);
			}
			current = current.next;
			 
		}
		return arraylist;
	}

	
	 /**
     * Method to remove a word from the list
     * @param wordToRemove - specified word to be removed from the list
     * @return false - word not found within the list
     */
    public boolean removeWord(String wordToRemove) {
        if (head == null) {
            return false; // List is empty
        }

        if (head.words.equals(wordToRemove)) {
            head = head.next; // If the word to remove is at the head, update head
            return true;
        }

        Node1 current = head;
        while (current.next != null) {
            if (current.next.words.equals(wordToRemove)) {
                current.next = current.next.next; 
                return true;
            }
            current = current.next;
        }

        return false; 
    }

    /**
     * Method to change a word in the list
     * @param oldWord - specified word to be changed within the list
     * @param newWord - specified word to be implemented replacing the old word
     * @return false - word not found within the list
     */
    public boolean changeWord(String oldWord, String newWord) {
        Node1 current = head;
        while (current != null) {
            if (current.words.equals(oldWord)) {
                current.words = newWord; // Change the word
                return true;
            }
            current = current.next;
        }
        return false; 
    }


}

import java.io.*; 
import java.util.ArrayList; 
import java.util.Collections;


/**
 * This is the double linked list class
 */

public class DoublyLinkedList {
	
	/**This is the vocab class which conatin the topic and the wordlist*/
 private class Vocab {
	 // These variable below will contain the topic name, the worlist associated with the topic, the link to the next and the previous item to an element
    private String topic;
    private Vocab next;
    private Vocab prev;
    private SinglyLinkedList wordlist;

    /**
     * Creates Node Object
     * @param topic - Value found within each node of the doublylinkedlist
     * @param wordlist - set of words found in each topic from every Node
     */
    public Vocab(String topic,SinglyLinkedList wordlist) { // parametrised constructor
        this.topic = topic;
        this.next = null;
        this.prev = null;
        this.wordlist = wordlist;  
    }
    
}

// The head will contain the 1st item on the list and the tail will contain the last item on the list
	private Vocab head;
	private Vocab tail;
	
	public DoublyLinkedList() { // default constructor
		this.head  = null;
		this.tail  = null;
	}
	
	/**
     * Method ensuring the topic exists, complementing getTopic Method
     * @param topicNumber - topic chosen by user
     * @return (vocab != null) ? vocab.topic : null - assigns value based on existence
     */
	public String getTopicName(int topicNumber) {
	    Vocab vocab = getTopic(topicNumber);
	    return (vocab != null) ? vocab.topic : null;
	}
	
	/**
     * Method which gets the specified topic
     * @param topicNumber - topic chosen by user
     * @return null -  No vocab object is found
     */
	public Vocab getTopic(int topicNumber) {
        Vocab current = head;
        int counter = 1;
        while (current != null) {
            if (counter == topicNumber) {
                return current;
            }
            current = current.next;
            counter++;
        }
        return null;
    }

	/**
     * Method which reaches the end of the list 
     */
	public void GototheEnd() // Go to the end of the list
	{ 
		Vocab current = head; 
	    while (current != null) {  
	        current = current.next; 
	    } 
	} 
	
	/**
     * Method which goes to the front of the list
     */
	public void GototheFront() 
	{ 
		Vocab current = tail; 
	    while (current != null) { 
	      current = current.prev; 
	    } 
	} 
	
	/**
     * Method inserting word at the end of a wordlist of specified topic
     * @param topic - topic chosen by user
     * @param wordlist - list of words
     */
	public void insertAtEnd(String topic,SinglyLinkedList wordlist ) 
	{ 
		Vocab current = new Vocab(topic,wordlist); 
	    if (tail == null) { 
	        head = current; 
	        tail = current; 
	    } 
	    else { 
	        tail.next = current; 
	        current.prev = tail; 
	        tail = current; 
	    } 
	}
	
	/**
     * Method displaying all present topics
     */
	public void displayAlltopic() {
		Vocab current = head;
        int counter = 0 ;
        while (current != null) {
        	
            System.out.println((counter+1)+ ": "+ current.topic + " ");
        
            current = current.next;
            counter++;
        }
       
        GototheFront();
    }
	
	/**
     * Method implementing a topic before a specified topic 
     * @param number - reference value
     * @param topic - topic containing list of words
     * @param wordlist - list of words 
     * @return Insertion complete
     */
	public void InsertBeforeAnotherTopic(int number,String topic,SinglyLinkedList wordlist ) {
		Vocab newTopic = new Vocab(topic,wordlist);
		Vocab current = head;
		boolean numberisvalid = false; 
		 int counter = 1 ;
		 if (head == null) {
	            head = newTopic;
	            numberisvalid = true;
	            return ;
	        }
		 else {
			 while (current != null) {
				 if(counter == number) {
					 // If the existing node is the head, update the head
		                if (current == head) {
		                    head = newTopic;
		                }
					
		            numberisvalid = true; 
					newTopic.next = current; // This connects the links of the new node to the current nodes and its previous node
				    newTopic.prev = current.prev;
	               
				    if (current.prev != null) {
	                current.prev.next = newTopic; // This connects the links of the previous node of the current node to the new node.
				    
	                }
	                current.prev = newTopic;

	               

	                return ; 
	            } current = current.next;
				 counter++;
				 }
				
			 }
		 if (!numberisvalid) { 
	        	System.out.println("The topic number entered is not valid. Please try again.");
	        }
		 }
	
	/**
     * Method implementing a topic after a specified topic
     * @param number - reference value
     * @param topic - topic containing list of words
     * @param wordlist - list of words 
     * @return Insertion complete
     */
	public void InsertAfterAnotherTopic(int number,String topic,SinglyLinkedList wordlist) {
		Vocab newTopic = new Vocab(topic,wordlist);
		Vocab current = head;
		boolean numberisvalid = false; // new stuff
		 int counter = 0 ;
		 if (head == null) {
	            head = newTopic;
	            numberisvalid = true;
	            return;
	        }
		 else {
			 while (current != null) {
				 if(counter == number) {
					
					numberisvalid = true;
					newTopic.next = current; // This connects the links of the new node to the current nodes and its previous node
				    newTopic.prev = current.prev;
	               
				    if (current.prev != null) {
	                current.prev.next = newTopic; // This connects the links of the previous node of the current node to the new node.
				    
	                }
	                current.prev = newTopic;

	                // If the existing node is the head, update the head
	                if (current == head) {
	                    head = newTopic;
	                }

	                return ; 
	            } 
				 if (current.next == null && counter == number - 1) {
                     
                     numberisvalid = true;
                     current.next = newTopic;
                     newTopic.prev = current;

                     
                     tail = newTopic;

                     return;
                 }
				 current = current.next;
				 counter++;
				 }
				
			 }
		 if (!numberisvalid) { 
	        	System.out.println("The topic number entered is not valid. Please try again.");
	        }
	}
	
	/**
     * Method which removes a topic from the topic list
     * @param number - reference value
     * @return Removal complete
     */
	public void removeTopic(int number) {
		Vocab current = head;
        int counter = 1 ;
        boolean numberisvalid = false;
        while (current != null) {
        	
        	if(counter == number) { // Finds the chosen topic to be removed and removes it from the list
        		if (current.prev != null) {
        			current.prev.next = current.next;
                } 
        		else {
                    // If the node to be removed is the head
                    head = current.next;
                }

                if (current.next != null) {
                	current.next.prev = current.prev;
                }

                
                numberisvalid = true;
                System.out.println("The removal of the topic has been successful.");
                
                return;
            }
        	current = current.next;
            counter++;
            }
          
            
        
        if (!numberisvalid) {
        	System.out.println("The topic number entered is not valid. Please try again.");
        }
	}
	
	/**
     * Method which displays the wordlist of a specified topic
     * @param number - reference value
     */
	public void displaySpecificWordlist(int number) {
		Vocab current = head;
        int counter = 1 ;
        boolean numberisvalid = false;
        while (current != null) {
        	
        	if(counter == number) {
            System.out.println("Topic: "+ current.topic + " ");
            numberisvalid = true;
            current.wordlist.displayAllWords();
            break;}
          
            current = current.next;
            counter++;
        }
        if (!numberisvalid)    {
        	System.out.println("The topic number entered is not valid. Please try again.");
        }
      
	}
	
	/**
     * Method which gets the size of the topic list
     * @return size of topic list
     */
	public int amountofTopics() { 
		Vocab current = head;
        int counter = 0 ;
        while (current != null) {
        	
            current = current.next;
            counter++;
        }
       
        GototheFront();
        return counter;
	}
	
	/**
     * Method which saves the topic list alongside their internal wordlist to Saved_file.txt
     */
	public void SavetoFile(){ 
		Vocab current = head;
		try {
			  PrintWriter insertwords = new PrintWriter(new FileWriter("Saved_file.txt", true));
			  
		while (current != null) {
			insertwords.println("#" + current.topic);
			insertwords.println(current.wordlist.SavetoFile());
			current = current.next;
			 
		}
		insertwords.close();}
		 catch(IOException e) {
			  System.out.println("An error occurred: " + e.getMessage());
		  }
		        
	}
	
	
	/**
     * Method which sorts a list of words
     * @param character - filtering selection of words
     * @return listofsortedwords - list of sorted words
     */
	public ArrayList GetSortedArrayList(char character) { 
		Vocab current = head;
		ArrayList<String> listofsortedwords =  new ArrayList<>();
		ArrayList<String> templist =  new ArrayList<>();
       
		while (current != null) {
        	templist = current.wordlist.GetSortedArrayList(character);
        	 for (String element : templist) {
        		 listofsortedwords.add(element);
             }
            current = current.next;
           
        }
		 Collections.sort(listofsortedwords);
        
        return listofsortedwords;
	}
	
	/**
     * Method which adds a word to a specified topic
     * @param topic - specified topic
     * @param word - topic containing list of words
     */
	 public void addWord(String topic, String word) {
	        Vocab current = findVocab(topic);
	        if (current != null) {
	            current.wordlist.insertWordAtTheEnd(word);
	        }
	    }
	    
	 /**
	     * Method which removes a word from the specified topic
	     * @param topic - specified topic
	     * @param wordToRemove - chosen word to be removed from topic's word list
	     * @return current != null && current.wordlist.removeWord(wordToRemove) - whether the word has been removed
	     */
	    public boolean removeWord(String topic, String wordToRemove) {
	        Vocab current = findVocab(topic);
	        return current != null && current.wordlist.removeWord(wordToRemove);
	    }
	    
	    /**
	     * Method which changes a word from the specified topic
	     * @param topic - specified topic
	     * @param oldWord - word to be replaced
	     * @param newWord - word replacing the old word
	     * @return current != null && current.wordlist.changeWord(oldWord, newWord) - whether the word has been changed
	     */
	    public boolean changeWord(String topic, String oldWord, String newWord) {
	        Vocab current = findVocab(topic);
	        return current != null && current.wordlist.changeWord(oldWord, newWord);
	    }
	    
	    
	    /**
	     * Helper, finding a Vocab node by topic
	     * @param topic - specified topic
	     * @return null - topic is not found
	     */
	    private Vocab findVocab(String topic) {
	        Vocab current = head;
	        while (current != null) {
	            if (current.topic.equals(topic)) {
	                return current;
	            }
	            current = current.next;
	        }
	        return null;
	    }
	    
	    /**
	     * Method which looks for a specific word among the given topics
	     * @param word - specified word
	     */
	    public void searchTopicsForWord(String word) {
	        Vocab current = head;
	        boolean foundInAnyTopic = false;
	        int topicNumber = 1;
	        while (current != null) {
	            SinglyLinkedList wordlist = current.wordlist;
	            if (wordlist.wordExists(word)) {
	                foundInAnyTopic = true;
	                System.out.println("Word '" + word + "' found in topic " + topicNumber + ": " + current.topic);
	            }
	            current = current.next;
	            topicNumber++;
	        }
	        if (!foundInAnyTopic) {
	            System.out.println("Word '" + word + "' not found in all topics.");
	        }
	    }
	
}

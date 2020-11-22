package com.myexample.miscellaneous.trie.phonebook;

import java.io.PrintStream;
import java.util.ArrayList;

import com.myexample.miscellaneous.trie.phonebook.CharTrie.LinkedTrieNode;
import com.myexample.miscellaneous.trie.phonebook.Contact.TYPE_ENUM;

public class ContactManager {
	private final static ContactManager _INSTANCE = new ContactManager();
	
	private CharTrie<Contact> nameTrie = new CharTrie<Contact> ();
	private CharTrie<Contact> numberTrie = new CharTrie<Contact> ();
	
	public final static int MOBILE_NUMBER = 1;
	public final static int HOME_NUMBER = 2;
	public final static int WORK_NUMBER = 3;
	public final static int HOME_ADDRESS = 4;
	public final static int EMAIL_ID = 5;
	public final static int NOTES = 6;
	
	private ContactManager() {
	}
	
	public static final ContactManager getInstance() {
		return _INSTANCE;
	}

	final char[] getNameFromStorageBasedOnLeafNode(LinkedTrieNode<Contact> leafNode) {
		return nameTrie.getKeyBasedOnLeafNode(leafNode);
	}
	
	final char[] getNumberFromStorageBasedOnLeafNode(LinkedTrieNode<Contact> leafNode) {
		return numberTrie.getKeyBasedOnLeafNode(leafNode);
	}
	
	public void renameContact(String fromName, String toName) {
		LinkedTrieNode<Contact> nameLeafNode = nameTrie.getLeafTrieNode(fromName.toCharArray());
		if (nameLeafNode == null) return;
		
		Contact contact = nameLeafNode.getValue();
		if (contact == null) return;
		
		nameTrie.delete(fromName.toCharArray());
		nameLeafNode = nameTrie.addAndGetLeafNode(fromName.toCharArray(), contact);
		contact.setNameLeafNode(nameLeafNode);
	}
	
	public void removeContact(String name) {
		Contact contact = nameTrie.delete(name.toCharArray());
		if (contact == null) return;
		
		for (Contact.ContactNumber contactNumber : contact.getAllNumbers()) {
			numberTrie.delete(numberTrie.getKeyBasedOnLeafNode(contactNumber.numberLeafNode));
		}
	}
	
	public void addUpdateContactDetailsForName(String name, int contactDetailType, String value) {
		char[] nameCharArr = name.toCharArray();
		Contact contact = nameTrie.getValue(nameCharArr); 
		
		if (contact == null) {
			contact = new Contact();
			LinkedTrieNode<Contact> nameLeafNode = nameTrie.addAndGetLeafNode(nameCharArr, contact);
			contact.setNameLeafNode(nameLeafNode);
		}
		
		LinkedTrieNode<Contact> numberLeafNode = null;
		switch (contactDetailType) {
		case MOBILE_NUMBER:
			numberLeafNode = numberTrie.getLeafTrieNode(value.toCharArray());
			if (numberLeafNode == null)
				numberLeafNode = numberTrie.addAndGetLeafNode(value.toCharArray(), contact);
			contact.addUpdateNumber(TYPE_ENUM.MOBILE, numberLeafNode);
			break;
		case HOME_NUMBER:
			numberLeafNode = numberTrie.getLeafTrieNode(value.toCharArray());
			if (numberLeafNode == null)
				numberLeafNode = numberTrie.addAndGetLeafNode(value.toCharArray(), contact);
			contact.addUpdateNumber(TYPE_ENUM.HOME, numberLeafNode);
			break;
		case WORK_NUMBER:
			numberLeafNode = numberTrie.getLeafTrieNode(value.toCharArray());
			if (numberLeafNode == null)
				numberLeafNode = numberTrie.addAndGetLeafNode(value.toCharArray(), contact);
			contact.addUpdateNumber(TYPE_ENUM.WORK, numberLeafNode);
			break;
		case HOME_ADDRESS:
			contact.setAddress(value);
			break;
		case EMAIL_ID:
			contact.setEmailID(value);
			break;
		case NOTES:
			contact.setNotes(value);
			break;

		default:
			throw new UnsupportedOperationException("Unsupported Contact Detail Type : " + contactDetailType + ".");
		}
	}
	
	public void removeContactDetailsForName(String name, int contactDetailType, String value) {
		char[] nameCharArr = name.toCharArray();
		Contact contact = nameTrie.getValue(nameCharArr); 
		
		if (contact == null) return;
		
		LinkedTrieNode<Contact> numberLeafNode = null;
		switch (contactDetailType) {
		case MOBILE_NUMBER:
		case HOME_NUMBER:
		case WORK_NUMBER:
			numberLeafNode = numberTrie.getLeafTrieNode(value.toCharArray());
			if (numberLeafNode != null) {
				contact.removeNumber(numberLeafNode);
			}
			break;
		case HOME_ADDRESS:
			contact.setAddress(null);
			break;
		case EMAIL_ID:
			contact.setEmailID(null);
			break;
		case NOTES:
			contact.setNotes(null);
			break;

		default:
			throw new UnsupportedOperationException("Unsupported Contact Detail Type : " + contactDetailType + ".");
		}
	}
	
    public ArrayList<String> getContactNamesStartingWithNamePrefix(String namePrefix) {
    	ArrayList<String> nameArray = new ArrayList<String>();
		for (char[] charArr : nameTrie.getAllWordsWithPrefix(namePrefix.toCharArray()).keySet()) {
			nameArray.add(new String(charArr));
		}
		
		return nameArray;
	}

    public ArrayList<String> getContactNamesStartingWithNumberPrefix(String numberPrefix) {
    	ArrayList<String> nameArray = new ArrayList<String>();
		for (char[] charArr : numberTrie.getAllWordsWithPrefix(numberPrefix.toCharArray()).keySet()) {
			LinkedTrieNode<Contact> numberLeafNode = numberTrie.getLeafTrieNode(charArr);
			LinkedTrieNode<Contact> nameLeafNode = numberLeafNode.getValue().nameLeafNode;
			nameArray.add(new String(nameTrie.getKeyBasedOnLeafNode(nameLeafNode)));
		}
		
		return nameArray;
	}
    
    public void printContactDetailsBasedOnName(String name, PrintStream ps) {
    	printContactDetailsBasedOnName(name.toCharArray(), ps);
    }
    
	private void printContactDetailsBasedOnName(char[] name, PrintStream ps) {
		LinkedTrieNode<Contact> nameLeafNode = nameTrie.getLeafTrieNode(name);
		Contact contact = nameLeafNode.getValue();
		
		ps.println("---------------------------------------");
		ps.println("  Name     : " + new String(name));
		
		for (Contact.ContactNumber contactNumber : contact.getAllNumbers()) {
			ps.println("  Number   : " + new String(numberTrie.getKeyBasedOnLeafNode(contactNumber.numberLeafNode))
									   + " (" + contactNumber.getType().toString() + ")");
		}
		
		ps.println("  Address  : " + contact.getAddress());
		ps.println("  Email ID : " + contact.getEmailID());
		ps.println("  Notes    : " + contact.getNotes());
	}
	
	public void printContactDetailsforNameStartingWithPrefix(String namePrefix, PrintStream ps) {
		for (char[] charArr : nameTrie.getAllWordsWithPrefix(namePrefix.toCharArray()).keySet()) {
			printContactDetailsBasedOnName(charArr, ps);
		}
	}
	
	public void printContactDetailsforNumberStartingWithPrefix(String numberPrefix, PrintStream ps) {
		ArrayList<String> names = getContactNamesStartingWithNumberPrefix(numberPrefix);
		for (String name : names) {
			printContactDetailsBasedOnName(name, ps);
		}
	}
}

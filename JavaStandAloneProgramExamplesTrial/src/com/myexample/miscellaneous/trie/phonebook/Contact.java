package com.myexample.miscellaneous.trie.phonebook;

import java.util.ArrayList;

class Contact {
	CharTrie.LinkedTrieNode<Contact> nameLeafNode = null;
	ArrayList<ContactNumber> allNumbers = null;
	private String address = null;
	private String emailID = null;
	private String notes = null;
	
	Contact () {
	}
	
	CharTrie.LinkedTrieNode<Contact> getNameLeafNode() {
		return nameLeafNode;
	}

	void setNameLeafNode(CharTrie.LinkedTrieNode<Contact> nameLeafNode) {
		this.nameLeafNode = nameLeafNode;
	}
	
	ArrayList<ContactNumber> getAllNumbers() {
		return allNumbers;
	}
	
	void addUpdateNumber(TYPE_ENUM type, CharTrie.LinkedTrieNode<Contact> numberLeafNode) {
		if (allNumbers == null) allNumbers = new ArrayList<ContactNumber>();
		
		ContactNumber contactNumber = new ContactNumber(type, numberLeafNode);
		
		int index = allNumbers.indexOf(contactNumber);
		if (index >= 0) allNumbers.remove(index);

		allNumbers.add(contactNumber);
	}
	
	void removeNumber(CharTrie.LinkedTrieNode<Contact> numberLeafNode) {
		if (allNumbers == null) return;
		
		// Type does not matter here.
		ContactNumber contactNumber = new ContactNumber(TYPE_ENUM.HOME, numberLeafNode);
		
		int index = allNumbers.indexOf(contactNumber);
		if (index >= 0) allNumbers.remove(index);
	}
	
	boolean hasNumber(CharTrie.LinkedTrieNode<Contact> numberLeafNode) {
		// Type here does not matter.
		return allNumbers.contains(new ContactNumber(TYPE_ENUM.HOME, numberLeafNode));
	}
	
	String getAddress() {
		return address;
	}
	
	void setAddress(String address) {
		this.address = address;
	}
	
	String getEmailID() {
		return emailID;
	}
	
	void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	
	String getNotes() {
		return notes;
	}
	
	void setNotes(String notes) {
		this.notes = notes;
	}
	
	static enum TYPE_ENUM {
		MOBILE (1),
		HOME (2),
		WORK (3);
		
		int value;
		
		private TYPE_ENUM(int aValue) {
			this.value = aValue;
		}
		
		int Value() {
			return value;
		}
		
		@Override
		public String toString() {
			switch (this) {
			case MOBILE:
			   	return "Mobile";
			case HOME:
				return "Home";
			case WORK:
				return "Work";
			}
			return super.toString();
		}
	}
	
	static class ContactNumber implements Comparable<ContactNumber> {
		TYPE_ENUM type;
		CharTrie.LinkedTrieNode<Contact> numberLeafNode;
		
		public ContactNumber(TYPE_ENUM type, CharTrie.LinkedTrieNode<Contact> numberLeafNode) {
			this.type = type;
			this.numberLeafNode = numberLeafNode;
		}

		public TYPE_ENUM getType() {
			return type;
		}

		public void setType(TYPE_ENUM type) {
			this.type = type;
		}

		public CharTrie.LinkedTrieNode<Contact> getNumber() {
			return numberLeafNode;
		}

		public void setNumber(CharTrie.LinkedTrieNode<Contact> numberLeafNode) {
			this.numberLeafNode = numberLeafNode;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj == null) return false;
			if (obj instanceof ContactNumber) {
				ContactManager cm = ContactManager.getInstance();
				String val1 = new String(cm.getNumberFromStorageBasedOnLeafNode(this.numberLeafNode));
				String val2 = new String(cm.getNumberFromStorageBasedOnLeafNode(((ContactNumber)obj).numberLeafNode));
				return val1.equals(val2);
			}
			else
				return false;
			
		}

		@Override
		public int compareTo(ContactNumber obj) {
			if (this.type == obj.type) {
				ContactManager cm = ContactManager.getInstance();
				String val1 = new String(cm.getNumberFromStorageBasedOnLeafNode(this.numberLeafNode));
				String val2 = new String(cm.getNumberFromStorageBasedOnLeafNode(((ContactNumber)obj).numberLeafNode));
				long lValue = Long.valueOf(val1) - Long.valueOf(val2);
				return (lValue == 0) ? 0 : ((lValue < 0) ? -1 : 1);
			}
			else {
				return (this.type.Value() - obj.type.Value());
			}
		}
	}
}


if __name__ == "__main__":
    from MyMainPyDev1 import Trie
    
    myTrie = Trie()
    myTrie.insert("some", 1)
    myTrie.insert("someone", 2)
    myTrie.insert("somewhere", 3)
    myTrie.insert("somewhere else", 4)
    
    print(myTrie)
    print(myTrie.getAllWords())
    print(myTrie.get("some"))
    print(myTrie.get("no"))
    print(myTrie.get("someone"))
    print(myTrie.get("somewhere"))
    print(myTrie.get("somewhere else"))
    print(myTrie.remove("some"))
    print(myTrie.remove("no"))
    print(myTrie.getAllWords())
    print(myTrie.get("some"))
    print(myTrie.get("no"))
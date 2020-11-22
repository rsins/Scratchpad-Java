
class Trie:
    __VALUE_ITEM = "__END__"
    
    def __init__(self):
        self.root = self.__getNewNode()
    
    def __getNewNode(self):
        d = dict()
        d.setdefault(self.__VALUE_ITEM, None)
        return d
    
    def insert(self, word, meaning):
        current = self.root
        for c in word:
            if (c not in current):
                current[c] = self.__getNewNode()
            current = current.get(c)
        current[self.__VALUE_ITEM] = meaning

    def get(self, word):
        current = self.root
        for c in word:
            if (c not in current):
                return None
            current = current.get(c)
        return current[self.__VALUE_ITEM]
    
    def remove(self, word):
        current = self.root
        for c in word[:-1]:
            if (c not in current):
                return None
            current = current.get(c)
        parentCurrent = current
        c = word[-1]
        if (c not in current):
            return None
        current = current[c]
        currentValue = current[self.__VALUE_ITEM]
        if (len(current) == 1):
            parentCurrent.pop(c)
        else:
            current[self.__VALUE_ITEM] = None
        return currentValue

    def getAllWordsStartingWith(self, word, needSorted):
        current = self.root
        words = dict()
        wordBuilder = list()
        for c in word:
            if c not in current:
                return words
            wordBuilder.append(c)
            current = current.get(c)
        for (key, node) in current.items():
            if key == self.__VALUE_ITEM:
                continue
            wordBuilder.append(key)
            self.__getAllWordsImpl(words, wordBuilder, node)
            wordBuilder.pop()
        if needSorted == True:
            words = {key: words[key] for key in sorted(words)}
        return words
        
    def getAllWords(self):
        return self.getAllWordsStartingWith("", True)
    
    def __getAllWordsImpl(self, words, wordBuilder, currentNode):
        value = currentNode[self.__VALUE_ITEM]
        if value is not None:
            word = ""
            for i in wordBuilder:
                word = word + i
            words[word] = value
        for (key, node) in currentNode.items():
            if key == self.__VALUE_ITEM:
                continue
            wordBuilder.append(key)
            self.__getAllWordsImpl(words, wordBuilder, node)
            wordBuilder.pop()
                
    def __str__(self):
        return str(self.root)

if __name__ == "__main__":
    myTrie = Trie()
    myTrie.insert("some", 1)
    myTrie.insert("someone", 2)
    myTrie.insert("somewhere", 3)
    myTrie.insert("somewhere else", 4)
    myTrie.insert("who", 5)
    myTrie.insert("who else", 6)
    
    print(myTrie)
    print(myTrie.getAllWords())
    print(myTrie.getAllWordsStartingWith("so", True))
    print(myTrie.getAllWordsStartingWith("wh", True))
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

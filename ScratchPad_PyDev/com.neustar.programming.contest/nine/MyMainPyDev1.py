
#----------- Question 1 ------------------
breakLoop = False
n = 0
while not breakLoop:
    n = n + 1
    sum = 0
    for i in range (1, n+1):
        sum = sum + 2 ** (i-1)
        if (sum >= (200*i)):
            breakLoop = True
print("----------------- Question 1 -------------------")
print(n)
print("Answer = ", n)

#------------ Question 2 --------------------  
sues = dict()
sueMatch = dict()
with open("D:\\Ravi\\My_Programs\\Eclipse_Workspace\\ScratchPad\\src\\com\\neustar\\programming\\contest\\nine\\input.txt") as file:
    for line in file:
        words = line.replace(":","").replace(",","").replace("\n","").split()
        n = int(words[1])
        
        types = dict()
        types["children"] = -1
        types["cats"] = -1
        types["samoyeds"] = -1 
        types["pomeranians"] = -1
        types["akitas"] = -1
        types["vizslas"] = -1 
        types["goldfish"] = -1
        types["trees"] = -1
        types["cars"] = -1
        types["perfumes"] = -1
        
        types[str(words[2])] = int(words[3])
        types[str(words[4])] = int(words[5])
        types[str(words[6])] = int(words[7])
        
        sues[n] = types
        
        matchCount = 0
        if types["children"] == 3:
            matchCount = matchCount + 1
        if types["cats"] == 7:
            matchCount = matchCount + 1
        if types["samoyeds"] == 2:
            matchCount = matchCount + 1
        if types["pomeranians"] == 3:
            matchCount = matchCount + 1
        if types["akitas"] == 0:
            matchCount = matchCount + 1
        if types["vizslas"] == 0:
            matchCount = matchCount + 1
        if types["goldfish"] == 5:
            matchCount = matchCount + 1
        if types["trees"] == 3:
            matchCount = matchCount + 1
        if types["cars"] == 2:
            matchCount = matchCount + 1
        if types["perfumes"] == 1:
            matchCount = matchCount + 1

        if matchCount in sueMatch:
            l = sueMatch[matchCount]
            l.append(n)
            sueMatch[matchCount] = l
        else:
            l = list()
            l.append(n)
            sueMatch[matchCount] = l

print("----------------- Question 2 -------------------")
print("Number of matches - Aunt Sue #")
for i in sueMatch:
    print(i, " - ", str(sueMatch[i]))
print("Answer = ", sueMatch[max(sueMatch.keys())])

#------------------ Question 3 ----------------------    
sues = dict()
sueMatch = dict()
with open("D:\\Ravi\\My_Programs\\Eclipse_Workspace\\ScratchPad\\src\\com\\neustar\\programming\\contest\\nine\\input.txt") as file:
    for line in file:
        words = line.replace(":","").replace(",","").replace("\n","").split()
        n = int(words[1])
        
        types = dict()
        types["children"] = -1
        types["cats"] = -1
        types["samoyeds"] = -1 
        types["pomeranians"] = -1
        types["akitas"] = -1
        types["vizslas"] = -1 
        types["goldfish"] = -1
        types["trees"] = -1
        types["cars"] = -1
        types["perfumes"] = -1
        
        types[str(words[2])] = int(words[3])
        types[str(words[4])] = int(words[5])
        types[str(words[6])] = int(words[7])
        
        sues[n] = types
        
        matchCount = 0
        if types["children"] == 3 and types["children"] != -1 :
            matchCount = matchCount + 1
        if types["cats"] > 7 and types["cats"] != -1:
            matchCount = matchCount + 1
        if types["samoyeds"] == 2 and types["samoyeds"] != -1:
            matchCount = matchCount + 1
        if types["pomeranians"] < 3 and types["pomeranians"] != -1:
            matchCount = matchCount + 1
        if types["akitas"] == 0 and types["akitas"] != -1:
            matchCount = matchCount + 1
        if types["vizslas"] == 0 and types["vizslas"] != -1:
            matchCount = matchCount + 1
        if types["goldfish"] < 5 and types["goldfish"] != -1:
            matchCount = matchCount + 1
        if types["trees"] > 3 and types["trees"] != -1:
            matchCount = matchCount + 1
        if types["cars"] == 2 and types["cars"] != -1:
            matchCount = matchCount + 1
        if types["perfumes"] == 1 and types["perfumes"] != -1:
            matchCount = matchCount + 1

        if matchCount in sueMatch:
            l = sueMatch[matchCount]
            l.append(n)
            sueMatch[matchCount] = l
        else:
            l = list()
            l.append(n)
            sueMatch[matchCount] = l

print("----------------- Question 3 -------------------")
print("Number of matches - Aunt Sue #")
for i in sueMatch:
    print(i, " - ", str(sueMatch[i]))
print("Answer = ", sueMatch[max(sueMatch.keys())])

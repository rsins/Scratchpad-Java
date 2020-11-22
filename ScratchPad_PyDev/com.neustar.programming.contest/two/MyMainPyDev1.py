
alph = 'abcdefghijklmnopqrstuvwxyz'[::-1]

def getWinner(s1, s2):
    if ("no" in s1 or "no" in s2):
        return 0
    if (not s1.isalpha() or not s2.isalpha()):
        return 0
    for c in alph:
        c1 = s1.count(c)
        c2 = s2.count(c)
        if (c1 > c2):
            return 1
        elif (c2 > c1):
            return 2
    return 0

firstWordCount = 0
firstWord68th = ""
with open("D:\\Work\\Repositories\\programmingContest\\programmingContest2Input1.txt") as file:
    for line in file:
        words = line.split()
        winner = getWinner(words[0], words[1])
        if (winner == 1):
            firstWordCount = firstWordCount + 1
        if (firstWordCount == 68):
            firstWord68th = words[0]

print(firstWord68th)
print(firstWordCount)
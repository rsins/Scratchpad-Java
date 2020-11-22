from math import sqrt


# Questions 2
def is_prime_number(x):
    return (x > 1) and all(x % i for i in range(2, int(sqrt(x)) + 1))

def isSideDigitsEqual(n):
    sn = str(n)
    sum1 = sum2 = 0
    for i in range(int(len(sn)/2)):
        sum1 += int(sn[i])
        sum2 += int(sn[-i-1])
    if sum1 == sum2:
        return True
    return False
    
n = 100000
while True:
    n += 1
    if is_prime_number(n) and isSideDigitsEqual(n):
        print(n)
        break


# Question 3
def sTrim(s):
    for c in ",.:;?!":
        s = s.replace(c, "")
    return s

def getWords(fName):
    words = set()
    with open(fName) as file:
        for line in file:
            for w in filter(None, [sTrim(wd.lower()) for wd in line.split()]):  # convert to lower case and remove non-word characters and remove null or empty strings from the list using filter
                if w[0] == "e" or w[0] == "y":
                    words.add(w)
    return words
                
words1 = getWords("D:\\Work\\Repositories\\programmingContest\\hamlet.txt")
words2 = getWords("D:\\Work\\Repositories\\programmingContest\\romeoAndJuliet.txt")
print(len(words1.intersection(words2)))

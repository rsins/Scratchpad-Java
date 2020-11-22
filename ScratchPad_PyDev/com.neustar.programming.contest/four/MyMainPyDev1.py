
def processNumbers(r):
    num = ""
    for i in range(0,30,3):
        num += str(getNumber(r, i, i+3))
    return num
    
def getNumber(r, start, end):
    if r[0][start:end] == " _ " and r[1][start:end] == "| |" and r[2][start:end] == "|_|":
        return 0
    if r[0][start:end] == "   " and r[1][start:end] == "  |" and r[2][start:end] == "  |":
        return 1
    if r[0][start:end] == " _ " and r[1][start:end] == " _|" and r[2][start:end] == "|_ ":
        return 2
    if r[0][start:end] == " _ " and r[1][start:end] == " _|" and r[2][start:end] == " _|":
        return 3
    if r[0][start:end] == "   " and r[1][start:end] == "|_|" and r[2][start:end] == "  |":
        return 4
    if r[0][start:end] == " _ " and r[1][start:end] == "|_ " and r[2][start:end] == " _|":
        return 5
    if r[0][start:end] == " _ " and r[1][start:end] == "|_ " and r[2][start:end] == "|_|":
        return 6

def isSilvaCasinoNumber(num):
    if str(num).count("33333") > 0:
        return True
    else:
        return False
    
def isValidAccount(num):
    num = str(num)
    sum = int(num[0]) + int(num[1]) * 2 + int(num[2]) * 3 + int(num[3]) * 4 + int(num[4]) * 5 + int(num[5]) * 6 + int(num[6]) * 7 + int(num[7]) * 8 + int(num[8]) * 9 + int(num[9]) * 10
    if sum % 12 == 0:
        return True
    else:
        return False
    
nums=set()
count2=0
count=0
r = ["" for i in range(3)]
with open("D:\\Work\\Repositories\\programmingContest\\contest4\\programmingContest4Input.txt") as file:
    for line in file:
        r[count] = line
        count += 1
        if count == 3:
            num=processNumbers(r)
            if (isSilvaCasinoNumber(num) == True):
                nums.add(num)
            if (isValidAccount(num) == True):
                count2 += 1
            count = 0

print("answer1 = ", nums)
print("answer2 = ", count2)

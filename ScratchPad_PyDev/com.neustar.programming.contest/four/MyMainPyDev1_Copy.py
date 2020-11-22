
def processNumbers(r):
    num = ""
    for i in range(0,30,3):
        num += str(getNumber(r, i, i+3))
    return num
    
def getNumber(r, start, end):
    NUMBERS = [["" for j in range(3)] for i in range(7)]
    NUMBERS[0][0] = " _ "
    NUMBERS[0][1] = "| |"
    NUMBERS[0][2] = "|_|"
    NUMBERS[1][0] = "   "
    NUMBERS[1][1] = "  |"
    NUMBERS[1][2] = "  |"
    NUMBERS[2][0] = " _ "
    NUMBERS[2][1] = " _|"
    NUMBERS[2][2] = "|_ "
    NUMBERS[3][0] = " _ "
    NUMBERS[3][1] = " _|"
    NUMBERS[3][2] = " _|"
    NUMBERS[4][0] = "   "
    NUMBERS[4][1] = "|_|"
    NUMBERS[4][2] = "  |"
    NUMBERS[5][0] = " _ "
    NUMBERS[5][1] = "|_ "
    NUMBERS[5][2] = " _|"
    NUMBERS[6][0] = " _ "
    NUMBERS[6][1] = "|_ "
    NUMBERS[6][2] = "|_|"
    for i in range(7):
        if r[0][start:end] == NUMBERS[i][0] and r[1][start:end] == NUMBERS[i][1] and r[2][start:end] == NUMBERS[i][2]:
            return i

def isSilvaCasinoNumber(num):
    if str(num).count("33333") > 0:
        return True
    else:
        return False
    
def isValidAccount(num):
    num = str(num)
    sum = 0
    for i in range(10):
        sum += int(num[i]) * (i+1)
    if sum % 12 == 0:
        return True
    else:
        return False
    
nums=set()
count2=0
count=0
r = ["" for i in range(3)]
with open("D:\\Work\\Repositories\\programmingContest\\programmingContest4Input.txt") as file:
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

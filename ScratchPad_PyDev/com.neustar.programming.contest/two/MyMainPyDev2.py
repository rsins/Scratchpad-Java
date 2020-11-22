
numbers = ["4", "7"]

def getNextNumber(nums):
    nums1 = []
    for n in nums:
        nums1.append(n + "4")
        nums1.append(n + "7")
    return nums1

def isNeustarNumber(str1):
    if (str1.count("4") + str1.count("7") != len(str1)):
        return False
    else:
        return True
    
def isAwsomeNeustarNumber(str1):
    if (not isNeustarNumber(str(len(str1)))):
        return False
    if (isNeustarNumber(str(str1.count("4"))) or isNeustarNumber(str(str1.count("7")))):
        return True
    else:
        return False

def isPalindrome(str1):
    if (str1 == str1[::-1]):
        return True
    else:
        return False

count = 0
anp = ""
while (count < 9):
    for n in numbers:
        if (isPalindrome(str(n)) and isAwsomeNeustarNumber(str(n))):
            count = count + 1
            anp = n
            print(anp)
            if (count >= 9):
                break
    numbers = getNextNumber(numbers)

print("9th palindrom awsome neustar number is:", anp)

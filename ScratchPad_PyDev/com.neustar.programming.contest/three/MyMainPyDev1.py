from _collections import deque

def getValue(x):
    cards = {
             "A":"1",
             "T":"10",
             "J":"11",
             "Q":"12",
             "K":"13"
             }
    value = cards.get(str(x), str(x))
    return int(value)

list1 = deque()
list2 = deque()

with open("D:\\Work\\Repositories\\programmingContest\\programmingContest3Alice.txt") as file1:
    for line in file1:
        for c in line:
            list1.append(c)
with open("D:\\Work\\Repositories\\programmingContest\\programmingContest3Bob.txt") as file1:
    for line in file1:
        for c in line:
            list2.append(c)

while (len(list1) > 0 and len(list2) > 0):
    ch1 = list1.popleft()
    ch2 = list2.popleft()
    val1 = getValue(ch1)
    val2 = getValue(ch2)
    if (val1 > val2):
        list1.append(ch1)
        list1.append(ch2)
        last1 = ch1
        last2 = ch2
    elif (val2 > val1):
        list2.append(ch2)
        list2.append(ch1)
        last1 = ch2
        last2 = ch1

if (len(list1) == 0):
    print("Bob")
    print(len(list2))
else:
    print("Alice")
    print(len(list1))

print(last1)
print(last2)

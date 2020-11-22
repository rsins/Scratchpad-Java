
with open("D:\\Work\\Repositories\\programmingContest\\contest8\\input.txt") as file:
    line = file.read()
    intervals = [int(x) for x in line.split(",")]
    
    waitTime = 1
    toContinue = True
    while toContinue:
        for i in range(len(intervals)):
            if ( ((waitTime + i) % intervals[i]) == 0 ):
                waitTime = waitTime + 1
                break
        else:
            toContinue = False
    print(waitTime)


"""
 Two integer arrays. One is movie start time array and second is movie end time array. 
 Find out the maximum no of movies which can be seen for the given timings.

4
10 12
16 18
12 14
13 15


"""
from copy import deepcopy

def getInput():
    timings = list()
    n = int(input("provide input : "))
    for i in range(n):
        t = str(input()).split()
        timings.append((t[0], t[1]))
    timings.sort()
    return timings

def findMaxNoOfMovies(timings):
    allResults = list()
    for i in range(len(timings)):
        curResult = list()
        curResult.append((timings[i][0], timings[i][1]))
        findMaxNoOfMoviesImpl(allResults, curResult, timings, i)
    return allResults
    
def findMaxNoOfMoviesImpl(allResults, curResult, timings, curLevel):
    if curLevel >= len(timings):
        allResults.append(deepcopy(curResult))
        return
    (cs, ce) = (timings[curLevel][0], timings[curLevel][1])
    curLevel = curLevel + 1
    for i in range(curLevel, len(timings)):
        (s, e) = (timings[i][0], timings[i][1])
        if (ce <= s):
            curResult.append((s, e))
            findMaxNoOfMoviesImpl(allResults, curResult, timings, i + 1)
            curResult.pop()

timings = getInput()
result = findMaxNoOfMovies(timings)
for t in result:
    print(len(t), t)
print(max(enumerate(result), key = lambda tup: len(tup[1])))
print(max(result, key = lambda tup: len(tup)))

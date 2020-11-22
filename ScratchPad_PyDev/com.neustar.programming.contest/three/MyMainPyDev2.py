
totalDays = 0
totalDistance = 0
lastDayRun = 0

seventhDay = 0
while (totalDistance < (110 * 1000 * 1000 + 54 * 1000 + 30 + 2)):
    totalDays = totalDays + 1
    curDayRun = lastDayRun + 1
    seventhDay = seventhDay + 1
    totalDaysStr = str(totalDays)
    if (seventhDay == 7):
        curDayRun = 0
        seventhDay = 0
    elif (totalDaysStr.count("1") > 0 and totalDaysStr.count("3") > 0):
        curDayRun = 0
    else:
        lastDayRun = curDayRun
    totalDistance = totalDistance + curDayRun

print(totalDays)

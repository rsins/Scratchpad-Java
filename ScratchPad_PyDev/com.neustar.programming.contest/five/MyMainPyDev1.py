#------------------------------------
#  Question 1
#------------------------------------

n = 1   # Actual reading
n1 = 1  # Incorrect reading

while n1 < 190000:
    if (n1 % 7 == 0) or (str(n1).count("2") >= 1) or (sum([int(d) for d in str(n1)]) > 20):
        n1 += 1
    else:
        n += 1
        n1 += 1
print("Actual reading: ", n)

#------------------------------------
#  Question 2
#------------------------------------
with open("D:\\Work\\Repositories\\programmingContest\\SacramentoCrimeJanuary2006.csv") as file:
    print(round((sum(1 for _ in file) - 1) / (31 * 24), 3))

#------------------------------------
#  Question 3
#------------------------------------
def addToDict(d, k):
    ws = k.split()
    if ws[0].isdigit():
        k = k[len(ws[0]):]
        k = k.strip()
    if k in d:
        d[k] = d[k] + 1
    else:
        d[k] = 1

c_d = dict()
with open("D:\\Work\\Repositories\\programmingContest\\SacramentoCrimeJanuary2006.csv") as file:
    for line in file:
        address = line.split(",")[1].strip()
        if (address.count("/") > 0):
            streets = [s.strip() for s in address.split("/")]
            addToDict(c_d, streets[0])
            addToDict(c_d, streets[1])
        else:
            addToDict(c_d, address)

c_d = sorted(c_d.items(), key=lambda x:x[1], reverse=True)
print(c_d[0][0])

#------------------------------------
#  Question 4
#------------------------------------
count = 0
def getHourFromTime(t):
    tt = t.split(":")
    return int(tt[0]) + int(tt[1]) / 60
    
with open("D:\\Work\\Repositories\\programmingContest\\SacramentoCrimeJanuary2006.csv") as file:
    for i, line in enumerate(file):
        if i <= 1:
            continue
        cells = line.split(",")
        grid = int(cells[4])
        cdatetime = cells[0].strip().split()
        ucrCode = cells[6]
        hr = getHourFromTime(cdatetime[1])
        if hr <= 6.5:
            if grid <= 627:
                if grid <= 619.5:
                    if "2404" == ucrCode:
                        count += 1
                else:
                    if "2299" == ucrCode:
                        count += 1
            else:
                if "7000" == ucrCode:
                    count += 1
        else:
            if hr <= 16.5:
                if "7000" == ucrCode:
                    count += 1
            else:
                if grid <= 506.5:
                    if "2404" == ucrCode:
                        count += 1
                else:
                    if "7000" == ucrCode:
                        count += 1

print(count)

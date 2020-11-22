
sprinklers = [[False for j in range(1000)] for i in range(1000)]

def checkAndSwap(x1, x2):
    if (x1 > x2):
        return (x2, x1)
    else:
        return (x1, x2)

with open("D:\\Work\\Repositories\\programmingContest\\contest1\\programmingContest1Input2.txt") as file:
    for line in file:
        line = line.lower()
        line = line.replace("turn", "")
        line = line.replace(",", " ")
        actions = line.split()
        (posx1, posx2) = checkAndSwap(int(actions[1]), int(actions[4]))
        (posy1, posy2) = checkAndSwap(int(actions[2]), int(actions[5]))
        
        if (str(actions[0]) == "on"):
            for i in range(posx1, posx2+1):
                for j in range(posy1, posy2+1):
                    sprinklers[i][j] = True
        
        if (str(actions[0]) == "off"):
            for i in range(posx1, posx2+1):
                for j in range(posy1, posy2+1):
                    sprinklers[i][j] = False
                    
        if (str(actions[0]) == "toggle"):
            for i in range(posx1, posx2+1):
                for j in range(posy1, posy2+1):
                    sprinklers[i][j] = not sprinklers[i][j]

count = 0
for i in range(1000):
        for j in range(1000):
            if (sprinklers[i][j]):
                count = count + 1

print("Total number of sprinkler heads ON = ", count)

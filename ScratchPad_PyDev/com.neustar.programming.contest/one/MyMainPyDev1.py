
# "D:\\Work\\Repositories\\programmingContest\\programmingContest1Input1.txt"

direction = {
             ">" : (1, 0),
             "v" : (0, 1),
             "<" : (-1, 0),
             "^" : (0, -1)
             }

posX = 0
posY = 0
positions = set([(posX, posY)])

with open("D:\\Work\\Repositories\\programmingContest\\contest1\\programmingContest1Input1.txt") as file:
    while True:
        ch = file.read(1)
        if not ch:
            break
        (xx, yy) = direction.get(ch, (0, 0))
        posX += xx
        posY += yy
        positions.add((posX, posY))

print("Number of unique positions covered = ", len(positions))


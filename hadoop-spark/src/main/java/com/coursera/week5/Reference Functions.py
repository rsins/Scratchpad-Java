def split_fileA(line):
    # split the input line in word and count on the comma
    words = line.split(",")
    # turn the count to an integer  
    word = words[0]
    count = int(words[1])
    return (word, count)


def split_fileB(line):
    # split the input line into word, date and count_string
    words = line.split(",")
    count_string = words[1]
    intwords = words[0].split(" ")
    word = intwords[1]
    date = intwords[0]
    return (word, date + " " + count_string) 

def split_show_views(line):
    words = line.split(",")
    show = words[0]
    views = words[1]
    return (show, views)

def split_show_channel(line):
    words = line.split(",")
    show = words[0]
    channel = words[1]
    return (show, channel)

def extract_channel_views(show_views_channel): 
    (show, (channel, views)) = show_views_channel
    return (channel, views)

def some_function(a, b):
    some_result = int(a) + int(b)
    return some_result

#-------------------------------
show_view_file = sc.textFile("D:\Ravi\My_Programs\Eclipse_Workspace\hadoop-spark\src\main\java\com\coursera\week4\join2_gennum?.txt")
show_views = show_view_file.map(split_show_views)
show_channel_file = sc.textFile("D:\Ravi\My_Programs\Eclipse_Workspace\hadoop-spark\src\main\java\com\coursera\week4\join2_genchan?.txt")
show_channel = show_channel_file.map(split_show_channel)
joined_dataset = show_channel.join(show_views)
channel_views = joined_dataset.map(extract_channel_views)
channel_views_count = channel_views.reduceByKey(some_function).collect()

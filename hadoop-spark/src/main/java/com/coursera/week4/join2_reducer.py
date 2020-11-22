#!/usr/bin/env python
import sys

prev_word          = "  "                #initialize previous word  to blank string

CHANNEL_NAME = "ABC"

is_ABC_Found = False
viewer_count = 0

line_cnt           = 0  #count input lines

for line in sys.stdin:
    line       = line.strip()       #strip out carriage return
    key_value  = line.split('\t')   #split line, into key and value, returns a list
    line_cnt   = line_cnt+1     

    #note: for simple debugging use print statements, ie:  
    curr_word  = key_value[0]         #key is first item in list, indexed by 0
    value_in   = key_value[1]         #value is 2nd item

    #-----------------------------------------------------
    # Check if its a new word and not the first line 
    #   (b/c for the first line the previous word is not applicable)
    #   if so then print out list of dates and counts
    #----------------------------------------------------
    if curr_word != prev_word:

        # -----------------------     
        #now write out the join result, but not for the first line input
        # -----------------------
        if line_cnt > 1:
            #now reset lists
            is_ABC_Found = False
            viewer_count = 0
        prev_word         =curr_word  #set up previous word for the next set of input lines

    
    # ---------------------------------------------------------------
    #whether or not the join result was written out, 
    #   now process the curr word    
    # ---------------------------------------------------------------
    if (value_in[0:3] == CHANNEL_NAME): 
        print('{0} {1}'.format(prev_word, viewer_count))
        #now reset lists
        is_ABC_Found = False
        viewer_count = 0
    else:
        viewer_count = viewer_count + int(value_in)

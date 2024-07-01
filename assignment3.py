import os
import sys
categories = []
categori_lists = []
row_lists = []
columns_lists = []

def input_text():
    reading_file = sys.argv[1]
    global commands
    with open(reading_file, "r", encoding="utf-8") as f:
        commands = f.readlines()

input_text()

def output_text():
    current_dir_path = os.getcwd()
    writing_file_name = "output.txt"
    writing_file_path = os.path.join(current_dir_path, writing_file_name)
    global f
    f = open(writing_file_path, "w", encoding="utf-8")

output_text()

def create_category():
    global letters
    letters = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z']
    if len(categories) == 0:
        categories.append(category_name)
        f.write("The category {0} having {1} seats has been created\n".format(category_name, rows*columns))
        print("The category {0} having {1} seats has been created".format(category_name, rows*columns))
        category_values = []
        for i in range(rows):
            for j in range(columns):
                category_values.append(" X ")
        categori_lists.append(category_values)
        row_lists.append(rows)
        columns_lists.append(columns)
    else:
        number = 0
        for categori in categories:
            if categori == category_name:
                number+=1
        if number!=0:   
            f.write("Warning: Cannot create the category for the second time. The stadium has already {}\n".format(category_name))
            print("Warning: Cannot create the category for the second time. The stadium has already {}".format(category_name))
        else:
            categories.append(category_name)
            f.write("The category {0} having {1} seats has been created""\n".format(category_name, rows*columns))
            print("The category {0} having {1} seats has been created".format(category_name, rows*columns))
            category_values = []
            for i in range(rows):
                for j in range(columns):
                    category_values.append(" X ")
            categori_lists.append(category_values)
            row_lists.append(rows)
            columns_lists.append(columns)
            
def sell_ticket():
    j = 0
    for categori in categories:
        if categori == sep_command[3]:
            if sep_command[2] == "student":
                for seat in sep_command[4:]:
                    if len(seat) == 2:
                        seat_row = int(letters.index(str(seat[0])))
                        seat_column = int(seat[1])
                        if seat_row <= row_lists[j] and seat_column <= columns_lists[j] and categori_lists[j][seat_row*row_lists[j] + seat_column] == " X ":
                            categori_lists[j][seat_row*row_lists[j] + seat_column] = " S "
                            f.write("Success: {} has bought {} at category {}\n".format(sep_command[1],seat,sep_command[3]))
                            print("Success: {} has bought {} at category {}".format(sep_command[1],seat,sep_command[3]))
                        elif seat_row>row_lists[j] and seat_column>columns_lists[j]:
                            f.write("Error: The category {} has less column and row than the specified index {}\n".format(sep_command[3],seat))
                            print("Error: The category {} has less column and row than the specified index {}".format(sep_command[3],seat))
                        elif seat_row>row_lists[j]:
                            f.write("Error: The category {} has less row than the specified index {}\n".format(sep_command[3],seat))
                            print("Error: The category {} has less row than the specified index {}".format(sep_command[3],seat))
                        elif seat_column>columns_lists[j]:
                            f.write("Error: The category {} has less column than the specified index {}\n".format(sep_command[3],seat))
                            print("Error: The category {} has less column than the specified index {}".format(sep_command[3],seat))
                        else:
                            f.write("Warning: The seat {} cannot be sold to {} since it was already sold!\n".format(seat,sep_command[1]))
                            print("Warning: The seat {} cannot be sold to {} since it was already sold!\n".format(seat,sep_command[1]))
                    else:
                        for i in range(int(seat[1]),int(seat[3:])):
                            seat_row = int(letters.index(str(seat[0])))
                            seat_column = i-1
                            if seat_row <= row_lists[j] and int(seat[3:])+1 <= columns and seat_column <= columns_lists[j] and categori_lists[j][seat_row*row_lists[j] + i] == " X ":
                                for i in range(int(seat[1]),int(seat[3:])+1):
                                    categori_lists[j][seat_row*row_lists[j] + i] = " S "
                                f.write("Success: {} has bought {} at category {}\n".format(sep_command[1],seat,sep_command[3]))
                                print("Success: {} has bought {} at category {}".format(sep_command[1],seat,sep_command[3]))
                            elif seat_row>row_lists[j] and int(seat[3:])+1 >= columns_lists[j]:
                                f.write("Error: The category {} has less column and row than the specified index {}\n".format(sep_command[3],seat))
                                print("Error: The category {} has less column and row than the specified index {}".format(sep_command[3],seat))
                            elif seat_row>row_lists[j]:
                                f.write("Error: The category {} has less row than the specified index {}\n".format(sep_command[3],seat))
                                print("Error: The category {} has less row than the specified index {}".format(sep_command[3],seat))
                            elif int(seat[3:])+1 >= columns_lists[j]:
                                f.write("Error: The category {} has less column than the specified index {}\n".format(sep_command[3],seat))
                                print("Error: The category {} has less column than the specified index {}".format(sep_command[3],seat))
                            else:
                                f.write("Warning: The seat {} cannot be sold to {} since it was already sold!\n".format(seat,sep_command[1]))
                                print("Warning: The seat {} cannot be sold to {} since it was already sold!".format(seat,sep_command[1]))
                            break
            elif sep_command[2] == "full":
                for seat in sep_command[4:]:
                    if len(seat) == 2:
                        seat_row = int(letters.index(str(seat[0])))
                        seat_column = int(seat[1])
                        if seat_row <= row_lists[j] and seat_column <= columns_lists[j] and categori_lists[j][seat_row*row_lists[j] + seat_column] == " X ":
                            categori_lists[j][seat_row*row_lists[j] + seat_column] = " F "
                            f.write("Success: {} has bought {} at category {}\n".format(sep_command[1],seat,sep_command[3]))
                            print("Success: {} has bought {} at category {}".format(sep_command[1],seat,sep_command[3]))
                        elif seat_row>row_lists[j] and seat_column>columns_lists[j]:
                            f.write("Error: The category {} has less column and row than the specified index {}\n".format(sep_command[3],seat))
                            print("Error: The category {} has less column and row than the specified index {}".format(sep_command[3],seat))
                        elif seat_row>row_lists[j]:
                            f.write("Error: The category {} has less row than the specified index {}\n".format(sep_command[3],seat))
                            print("Error: The category {} has less row than the specified index {}".format(sep_command[3],seat))
                        elif seat_column>columns_lists[j]:
                            f.write("Error: The category {} has less column than the specified index {}\n".format(sep_command[3],seat))
                            print("Error: The category {} has less column than the specified index {}".format(sep_command[3],seat))
                        else:
                            f.write("Warning: The seat {} cannot be sold to {} since it was already sold!\n".format(seat,sep_command[1]))
                            print("Warning: The seat {} cannot be sold to {} since it was already sold!".format(seat,sep_command[1]))
                    else:
                        for i in range(int(seat[1]),int(seat[3:])):
                            seat_row = int(letters.index(str(seat[0])))
                            seat_column = i-1
                            if seat_row <= row_lists[j] and int(seat[3:])+1 <= columns and seat_column <= columns_lists[j] and categori_lists[j][seat_row*row_lists[j] + i] == " X ":
                                for i in range(int(seat[1]),int(seat[3:])+1):
                                    categori_lists[j][seat_row*row_lists[j] + i] = " F "
                                f.write("Success: {} has bought {} at category {}\n".format(sep_command[1],seat,sep_command[3]))
                                print("Success: {} has bought {} at category {}".format(sep_command[1],seat,sep_command[3]))
                            elif seat_row>row_lists[j] and int(seat[3:])+1 >= columns_lists[j]:
                                f.write("Error: The category {} has less column and row than the specified index {}\n".format(sep_command[3],seat))
                                print("Error: The category {} has less column and row than the specified index {}".format(sep_command[3],seat))
                            elif seat_row>row_lists[j]:
                                f.write("Error: The category {} has less row than the specified index {}\n".format(sep_command[3],seat))
                                print("Error: The category {} has less row than the specified index {}".format(sep_command[3],seat))
                            elif int(seat[3:])+1 >= columns_lists[j]:
                                f.write("Error: The category {} has less column than the specified index {}\n".format(sep_command[3],seat))
                                print("Error: The category {} has less column than the specified index {}".format(sep_command[3],seat))
                            else:
                                f.write("Warning: The seat {} cannot be sold to {} since it was already sold!\n".format(seat,sep_command[1]))
                                print("Warning: The seat {} cannot be sold to {} since it was already sold!".format(seat,sep_command[1]))
                            break
            elif sep_command[2] == "season":
                for seat in sep_command[4:]:
                    if len(seat) == 2:
                        seat_row = int(letters.index(str(seat[0])))
                        seat_column = int(seat[1])
                        if seat_row <= row_lists[j] and seat_column <= columns_lists[j] and categori_lists[j][seat_row*row_lists[j] + seat_column] == " X ":
                            categori_lists[j][seat_row*row_lists[j] + seat_column] = " T "
                            f.write("Success: {} has bought {} at category {}\n".format(sep_command[1],seat,sep_command[3]))
                            print("Success: {} has bought {} at category {}".format(sep_command[1],seat,sep_command[3]))
                        elif seat_row>row_lists[j] and seat_column>columns_lists[j]:
                            f.write("Error: The category {} has less column and row than the specified index {}\n".format(sep_command[3],seat))
                            print("Error: The category {} has less column and row than the specified index {}".format(sep_command[3],seat))
                        elif seat_row>row_lists[j]:
                            f.write("Error: The category {} has less row than the specified index {}\n".format(sep_command[3],seat))
                            print("Error: The category {} has less row than the specified index {}".format(sep_command[3],seat))
                        elif seat_column>columns_lists[j]:
                            f.write("Error: The category {} has less column than the specified index {}\n".format(sep_command[3],seat))
                            print("Error: The category {} has less column than the specified index {}".format(sep_command[3],seat))
                        else:
                            f.write("Warning: The seat {} cannot be sold to {} since it was already sold!\n".format(seat,sep_command[1]))
                            print("Warning: The seat {} cannot be sold to {} since it was already sold!".format(seat,sep_command[1]))
                    else:
                        for i in range(int(seat[1]),int(seat[3:])):
                            seat_row = int(letters.index(str(seat[0])))
                            seat_column = i-1
                            if seat_row <= row_lists[j] and int(seat[3:])+1 <= columns and seat_column <= columns_lists[j] and categori_lists[j][seat_row*row_lists[j] + i] == " X ":
                                for i in range(int(seat[1]),int(seat[3:])+1):
                                    categori_lists[j][seat_row*row_lists[j] + i] = " T "
                                f.write("Success: {} has bought {} at category {}\n".format(sep_command[1],seat,sep_command[3]))
                                print("Success: {} has bought {} at category {}\n".format(sep_command[1],seat,sep_command[3]))
                            elif seat_row>row_lists[j] and int(seat[3:])+1 >= columns_lists[j]:
                                f.write("Error: The category {} has less column and row than the specified index {}\n".format(sep_command[3],seat))
                                print("Error: The category {} has less column and row than the specified index {}".format(sep_command[3],seat))
                            elif seat_row>row_lists[j]:
                                f.write("Error: The category {} has less row than the specified index {}\n".format(sep_command[3],seat))
                                print("Error: The category {} has less row than the specified index {}".format(sep_command[3],seat))
                            elif int(seat[3:])+1 >= columns_lists[j]:
                                f.write("Error: The category {} has less column than the specified index {}\n".format(sep_command[3],seat))
                                print("Error: The category {} has less column than the specified index {}".format(sep_command[3],seat))
                            else:
                                f.write("Warning: The seat {} cannot be sold to {} since it was already sold!\n".format(seat,sep_command[1]))
                                print("Warning: The seat {} cannot be sold to {} since it was already sold!".format(seat,sep_command[1]))
                            break
            else:
                pass
        j += 1
            
def cancel_ticket():
    j = 0
    for categori in categories:
        if categori == sep_command[1]:
            for seat in sep_command[2:]:
                seat_row = int(letters.index(str(seat[0])))
                seat_column = int(str(seat[1:])) 
                try:
                    if seat_row<=row_lists[j] and seat_column<=columns_lists[j] and categori_lists[j][seat_row*row_lists[j] + seat_column] != " X ":
                        categori_lists[j][seat_row*row_lists[j] + seat_column] = " X "
                        f.write("Success: The seat {} at {} has been canceled and now ready to sell again\n".format(seat,sep_command[1]))
                        print("Success: The seat {} at {} has been canceled and now ready to sell again".format(seat,sep_command[1]))
                    elif seat_column>columns_lists[j] and seat_row>row_lists[j]:
                        f.write("Error: The category {} has less columns and less rows than the specified index {}!\n".format(sep_command[1],seat))
                        print("Error: The category {} has less columns and less rows than the specified index {}!".format(sep_command[1],seat))
                    elif seat_column>columns_lists[j]:
                        f.write("Error: The category {} has less columns than the specified index {}!\n".format(sep_command[1],seat))
                        print("Error: The category {} has less columns than the specified index {}!".format(sep_command[1],seat))
                    elif seat_row>row_lists[j]:
                        f.write("Error: The category {} has less rows than the specified index {}!\n".format(sep_command[1],seat))
                        print("Error: The category {} has less rows than the specified index {}!".format(sep_command[1],seat))
                    else:
                        f.write("Error: The seat {} at {} has already been free! Nothing to cancel\n".format(seat,sep_command[1]))
                        print("Error: The seat {} at {} has already been free! Nothing to cancel".format(seat,sep_command[1]))
                except Exception:
                    pass
        j += 1

def balance():
    j=0
    student_counter = 0
    full_counter = 0
    season_counter = 0
    for categori in categories:
        if categori == sep_command[1]:
            f.write("Category report of {}\n".format(sep_command[1]))
            f.write("-"*(len("Category report of {}\n".format(sep_command[1]))-1)+"\n")
            print("Category report of {}".format(sep_command[1]))
            print("-"*len("Category report of {}".format(sep_command[1])))
            for value in categori_lists[j]:
                if value == " S ":
                    student_counter += 1
                elif value == " F ":
                    full_counter += 1
                elif value == " T ":
                    season_counter += 1
            f.write("Sum of students = {}, Sum of full pay = {}, Sum of season ticket={}, and Revenues = {} Dollars\n".format(student_counter,full_counter,season_counter,student_counter*10+full_counter*20+season_counter*250))
            print("Sum of students = {}, Sum of full pay = {}, Sum of season ticket={}, and Revenues = {} Dollars".format(student_counter,full_counter,season_counter,student_counter*10+full_counter*20+season_counter*250))
        j+=1

def show_category():
    j=0
    for categori in categories:
        letter = letters[:columns_lists[j]]
        letter = letter[::-1]
        if categori == sep_command[1]:
            for i in range(0,row_lists[j]):
                f.write(letter[i])
                print(letter[i],end="")
                for k in range(1,columns_lists[j] +1):
                    f.write(categori_lists[j][int(letters.index(letter[i]))*columns_lists[j] + k -1])
                    print(categori_lists[j][int(letters.index(letter[i]))*columns_lists[j] + k -1],end="")
                print(" ")
                f.write("\n")
            for column in range(0,columns_lists[j]):
                f.write("{:3}".format(column))
                print("{:3}".format(column),end="")
            print(" ")
            f.write("\n")  
        j+=1

for command in commands:
    global sep_command
    sep_command = command.split()
    if sep_command[0]=="CREATECATEGORY":
        global category_name
        global category_size
        global rows
        global columns
        category_name = sep_command[1]
        category_size = sep_command[2]
        rows = int(category_size.split("x")[0])
        columns = int(category_size.split("x")[1])
        create_category()
    elif sep_command[0]=="SELLTICKET":
        global fans_name
        global ticket_type
        global seats_numbers
        fans_name = sep_command[1]
        ticket_type = sep_command[2]
        category_name = sep_command[3]
        seats_numbers = sep_command[4:]
        sell_ticket()
    elif sep_command[0] == "CANCELTICKET":
        category_name = sep_command[1]
        seats_numbers = sep_command[2]
        cancel_ticket()
    elif sep_command[0] == "SHOWCATEGORY":
        category_name = sep_command[1]
        show_category()
    elif sep_command[0] == "BALANCE":
        category_name = sep_command[1]
        balance()
# BERKAY SAYLAK 2210356085   
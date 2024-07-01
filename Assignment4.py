import sys

round=0 #this variables for the learn count of the ships
carrier_p1,battleship_p1,destroyer_p1,submarine_p1,patrolboat_p1 = 0,0,0,0,0
carrier_p2,battleship_p2,destroyer_p2,submarine_p2,patrolboat_p2 = 0,0,0,0,0

def players_area(player_list_1,player_list_2): #function for write the ships table
    columns = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J']
    if turn%2!=0:
        print("Player1's move"+"\n  ")
        f.write("Player1's move\n\n ")
    else:
        print("Player2's move"+"\n  ")
        f.write("Player2's move\n\n ")
    print("Round: {}".format(round)+"\t"*3+"Grid Size: 10x10"+"\n")
    print("Player1's Hidden Board"+"\t"*2+"Player2's Hidden Board")
    f.write("{:<10}{}".format("Round : ",round)+"\t"*3+"Grid Size: 10x10"+"\n\n")
    f.write("Player1's Hidden Board"+"\t"*2+"Player2's Hidden Board"+"\n")
    for i in range(2): #I used this for write columns with exact spaces
        for column in columns:
            if column == "A":
                print("  "+column+" ",end="")
                f.write("  "+column+" ")
            else:
                print(column+" ",end="")
                f.write(column+" ")
        print("\t"*2,end="")
        f.write("\t"*2)
    print()
    f.write("\n")
    j=1
    counter=0
    temporary_str_1=""
    temporary_str_2=""
    for row1,row2 in zip(player_list_1,player_list_2):
        temporary_str_1+=row1
        temporary_str_2+=row2
        counter+=1
        if counter==10:
            if j == 10: #this if else for the understand we came to end and we will write ships and also for escape from space problem which is came with "10" number
                print(str(j)+temporary_str_1+"\t"*2+str(j)+temporary_str_2)
                print("{:14}".format("Carrier")+"{:2}".format("X "*(1-carrier_p1)+"-"*carrier_p1)+ "\t"*2 + "{:14}".format("Carrier")+"{:2}".format("X "*(1-carrier_p2)+"-"*carrier_p2))
                print("{:14}".format("Battleship")+"{:2}".format("X "*(2-battleship_p1)+"- "*battleship_p1)+ "\t"*2 + "{:14}".format("Battleship")+"{:2}".format("X "*(2-battleship_p2)+"- "*battleship_p2))
                print("{:14}".format("Destroyer")+"{:2}".format("X "*(1-destroyer_p1)+"- "*destroyer_p1)+ "\t"*2 + "{:14}".format("Destroyer")+"{:2}".format("X "*(1-destroyer_p2)+"- "*destroyer_p2))
                print("{:14}".format("Submarine")+"{:2}".format("X "*(1-submarine_p1)+"- "*submarine_p1)+ "\t"*2 + "{:14}".format("Submarine")+"{:2}".format("X "*(1-submarine_p2)+"- "*submarine_p2))
                print("{:14}".format("Patrol Boat")+"{:2}".format("X "*(4-patrolboat_p1)+"- "*patrolboat_p1)+ "\t"*2 + "{:14}".format("Patrol Boat")+"{:2}".format("X "*(4-patrolboat_p2)+"- "*patrolboat_p2))
                print("\nEnter your move: {}".format(move))
                print()
                f.write(str(j)+temporary_str_1+"\t"*2+str(j)+temporary_str_2+"\n")
                f.write("{:14}".format("Carrier")+"{:2}".format("X "*(1-carrier_p1)+"-"*carrier_p1)+ "\t"*3 + "{:14}".format("Carrier")+"{:2}".format("X "*(1-carrier_p2)+"-"*carrier_p2)+"\n")
                f.write("{:14}".format("Battleship")+"{:2}".format("X "*(2-battleship_p1)+"- "*battleship_p1)+ "\t"*2 + "{:14}".format("Battleship")+"{:2}".format("X "*(2-battleship_p2)+"- "*battleship_p2)+"\n")
                f.write("{:14}".format("Destroyer")+"{:2}".format("X "*(1-destroyer_p1)+"- "*destroyer_p1)+ "\t"*3 + "{:14}".format("Destroyer")+"{:2}".format("X "*(1-destroyer_p2)+"- "*destroyer_p2)+"\n")
                f.write("{:14}".format("Submarine")+"{:2}".format("X "*(1-submarine_p1)+"- "*submarine_p1)+ "\t"*3 + "{:14}".format("Submarine")+"{:2}".format("X "*(1-submarine_p2)+"- "*submarine_p2)+"\n")
                f.write("{:14}".format("Patrol Boat")+"{:2}".format("X "*(4-patrolboat_p1)+"- "*patrolboat_p1)+ "\t"*2 + "{:14}".format("Patrol Boat")+"{:2}".format("X "*(4-patrolboat_p2)+"- "*patrolboat_p2)+"\n")
                f.write("\nEnter your move: {}\n".format(move))
                f.write("\n")
            else:
                print(str(j)+" "+temporary_str_1+"\t"*2+str(j)+" "+temporary_str_2)
                f.write(str(j)+" "+temporary_str_1+"\t"*2+str(j)+" "+temporary_str_2+"\n")
            temporary_str_1=""
            temporary_str_2=""
            counter=0
            j+=1
    
def win_screen(player_info_1,player_info_2): #function for write final table
    columns = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J']
    print("Final Information\n")
    print("{:38}{:10}".format("Player1's Board","Player2's Board"))   
    f.write("Final Information\n\n")
    f.write("Player1's Board"+"\t"*4+"Player2's Board"+"\n")
    for i in range(2):
        for column in columns:
            if column == "A":
                print("  "+column+" ",end="")
                f.write("  "+column+" ")
            else:
                print(column+" ",end="")
                f.write(column+" ")
        print("\t"*2,end="")
        f.write("\t"*2)
    print()
    f.write("\n")
    j=1
    counter=0
    temporary_str_1=""
    temporary_str_2=""
    for row1,row2 in zip(player_info_1,player_info_2):
        temporary_str_1+=row1
        temporary_str_2+=row2
        counter+=1
        if counter==10:
            if j == 10:
                print(str(j)+temporary_str_1+"\t"*2+str(j)+temporary_str_2)
                print("{:14}".format("Carrier")+"{:2}".format("X "*(1-carrier_p1)+"-"*carrier_p1)+ "\t"*2 + "{:14}".format("Carrier")+"{:2}".format("X "*(1-carrier_p2)+"-"*carrier_p2))
                print("{:14}".format("Battleship")+"{:2}".format("X "*(2-battleship_p1)+"- "*battleship_p1)+ "\t"*2 + "{:14}".format("Battleship")+"{:2}".format("X "*(2-battleship_p2)+"- "*battleship_p2))
                print("{:14}".format("Destroyer")+"{:2}".format("X "*(1-destroyer_p1)+"- "*destroyer_p1)+ "\t"*2 + "{:14}".format("Destroyer")+"{:2}".format("X "*(1-destroyer_p2)+"- "*destroyer_p2))
                print("{:14}".format("Submarine")+"{:2}".format("X "*(1-submarine_p1)+"- "*submarine_p1)+ "\t"*2 + "{:14}".format("Submarine")+"{:2}".format("X "*(1-submarine_p2)+"- "*submarine_p2))
                print("{:14}".format("Patrol Boat")+"{:2}".format("X "*(4-patrolboat_p1)+"- "*patrolboat_p1)+ "\t"*2 + "{:14}".format("Patrol Boat")+"{:2}".format("X "*(4-patrolboat_p2)+"- "*patrolboat_p2))
                f.write(str(j)+temporary_str_1+"\t"*2+str(j)+temporary_str_2+"\n")
                f.write("{:14}".format("Carrier")+"{:2}".format("X "*(1-carrier_p1)+"-"*carrier_p1)+ "\t"*3 + "{:14}".format("Carrier")+"{:2}".format("X "*(1-carrier_p2)+"-"*carrier_p2)+"\n")
                f.write("{:14}".format("Battleship")+"{:2}".format("X "*(2-battleship_p1)+"- "*battleship_p1)+ "\t"*2 + "{:14}".format("Battleship")+"{:2}".format("X "*(2-battleship_p2)+"- "*battleship_p2)+"\n")
                f.write("{:14}".format("Destroyer")+"{:2}".format("X "*(1-destroyer_p1)+"- "*destroyer_p1)+ "\t"*3 + "{:14}".format("Destroyer")+"{:2}".format("X "*(1-destroyer_p2)+"- "*destroyer_p2)+"\n")
                f.write("{:14}".format("Submarine")+"{:2}".format("X "*(1-submarine_p1)+"- "*submarine_p1)+ "\t"*3 + "{:14}".format("Submarine")+"{:2}".format("X "*(1-submarine_p2)+"- "*submarine_p2)+"\n")
                f.write("{:14}".format("Patrol Boat")+"{:2}".format("X "*(4-patrolboat_p1)+"- "*patrolboat_p1)+ "\t"*2 + "{:14}".format("Patrol Boat")+"{:2}".format("X "*(4-patrolboat_p2)+"- "*patrolboat_p2))
            else:
                print(str(j)+" "+temporary_str_1+"\t"*2+str(j)+" "+temporary_str_2)
                f.write(str(j)+" "+temporary_str_1+"\t"*2+str(j)+" "+temporary_str_2+"\n")
            temporary_str_1=""
            temporary_str_2=""
            counter=0
            j+=1
    
try:
    player1_ships,player2_ships,player1_moves,player2_moves=sys.argv[1],sys.argv[2],sys.argv[3],sys.argv[4]
    with open(player1_ships,"r") as f:
        player1_location=f.readlines()
    with open(player2_ships,"r") as f:
        player2_location=f.readlines()
    with open(player1_moves,"r") as f:
        player1_attacks=f.readlines()
    with open(player2_moves,"r") as f:
        player2_attacks=f.readlines()
    f = open("Battleship.out","w")
    f.write("Battle of Ships Game\n\n")

    
    columns = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J']
    row_counter=1 # I used this couple of list for the learn exact place of each ship
    ship_locations_p1,carrier_location_p1,battleship_location_p1,destroyer_location_p1,submarine_location_p1,patrol_location_p1=[],[],[],[],[],[]
    ship_locations_p2,carrier_location_p2,battleship_location_p2,destroyer_location_p2,submarine_location_p2,patrol_location_p2=[],[],[],[],[],[]
    for location in player1_location:
        lines=location.strip("\n").split(";")
        column_counter=0
        for line in lines: #In this part code will check for every type of ship one by one
            if line=="P": #If ship is patrol boat and then there is a list which has all ships locations, this code will append the code in this list
                ship_locations_p1.append(str(row_counter)+columns[column_counter]+"x"+line)
                if lines[column_counter-1]=="P": # this part for spesific ships location it will conclude only patrolboat and if it is right or left
                    patrol_location_p1.append(str(row_counter)+columns[column_counter-1]+"-"+str(row_counter)+columns[column_counter])
                    patrolboat_p1+=1
                    
                try: # this part for the check if it is down
                    if  player1_location[row_counter].strip("\n").split(";")[column_counter]=="P":
                        patrol_location_p1.append(str(row_counter)+columns[column_counter]+"-"+str(row_counter+1)+columns[column_counter])
                        patrolboat_p1+=1
                            
                except Exception:
                    pass
                
            if line=="S":
                ship_locations_p1.append(str(row_counter)+columns[column_counter]+"x"+line)
                if lines[column_counter-1]=="S" and lines[column_counter-2]=="S":
                    submarine_location_p1.append(str(row_counter)+columns[column_counter-2]+"-"+str(row_counter)+columns[column_counter-1]+"-"+str(row_counter)+columns[column_counter])
                    submarine_p1+=1
                        
                try:    
                    if  player1_location[row_counter-1].strip("\n").split(";")[column_counter]=="S" and player1_location[row_counter].strip("\n").split(";")[column_counter]=="S":
                        submarine_location_p1.append(str(row_counter)+columns[column_counter]+"-"+str(row_counter+1)+columns[column_counter]+"-"+str(row_counter+2)+columns[column_counter])
                        submarine_p1+=1
                            
                except Exception:
                    pass  
                
            if line=="D":
                ship_locations_p1.append(str(row_counter)+columns[column_counter]+"x"+line)
                if lines[column_counter-1]=="D" and lines[column_counter-2]=="D":
                    destroyer_location_p1.append(str(row_counter)+columns[column_counter-2]+"-"+str(row_counter)+columns[column_counter-1]+"-"+str(row_counter)+columns[column_counter])
                    destroyer_p1+=1
                        
                try:    
                    if  player1_location[row_counter-3].strip("\n").split(";")[column_counter]=="D" and player1_location[row_counter-2].strip("\n").split(";")[column_counter]=="D":
                        destroyer_location_p1.append(str(row_counter-2)+columns[column_counter]+"-"+str(row_counter-1)+columns[column_counter]+"-"+str(row_counter)+columns[column_counter])
                        destroyer_p1+=1
                            
                except Exception:
                    pass
            if line=="B":
                ship_locations_p1.append(str(row_counter)+columns[column_counter]+"x"+line)
                if lines[column_counter-1]=="B" and lines[column_counter-2]=="B" and lines[column_counter-3]=="B":
                    battleship_location_p1.append(str(row_counter)+columns[column_counter-3]+"-"+str(row_counter)+columns[column_counter-2]+"-"+str(row_counter)+columns[column_counter-1]+"-"+str(row_counter)+columns[column_counter])
                    battleship_p1+=1
                        
                try:    
                    if  player1_location[row_counter-5].strip("\n").split(";")[column_counter]=="B" and player1_location[row_counter-4].strip("\n").split(";")[column_counter]=="B" and player1_location[row_counter-3].strip("\n").split(";")[column_counter]=="B":
                        battleship_location_p1.append(str(row_counter-4)+columns[column_counter]+"-"+str(row_counter-3)+columns[column_counter]+"-"+str(row_counter-2)+columns[column_counter]+"-"+str(row_counter-1)+columns[column_counter])
                        battleship_p1+=1
                            
                except Exception:
                    pass 
            if line=="C":
                ship_locations_p1.append(str(row_counter)+columns[column_counter]+"x"+line)
                if lines[column_counter-1]=="C" and lines[column_counter-2]=="C" and lines[column_counter-3]=="C" and lines[column_counter-4]=="C":
                    carrier_location_p1.append(str(row_counter)+columns[column_counter-4]+"-"+str(row_counter)+columns[column_counter-3]+"-"+str(row_counter)+columns[column_counter-2]+"-"+str(row_counter)+columns[column_counter-1]+"-"+str(row_counter)+columns[column_counter])
                    carrier_p1+=1
                        
                try:    
                    if  player1_location[row_counter-5].strip("\n").split(";")[column_counter]=="C" and player1_location[row_counter-4].strip("\n").split(";")[column_counter]=="C" and player1_location[row_counter-3].strip("\n").split(";")[column_counter]=="C" and player1_location[row_counter-2].strip("\n").split(";")[column_counter]=="C":
                        carrier_location_p1.append(str(row_counter-4)+columns[column_counter]+"-"+str(row_counter-3)+columns[column_counter]+"-"+str(row_counter-2)+columns[column_counter]+"-"+str(row_counter-1)+columns[column_counter]+"-"+str(row_counter)+columns[column_counter])
                        carrier_p1+=1
                            
                except Exception:
                    pass 
            
            column_counter+=1
        row_counter+=1
    row_counter=1
    for location in player2_location: #In here it will do the same with player2 
        lines=location.strip("\n").split(";")
        column_counter=0
        for line in lines:     
            if line=="P":
                ship_locations_p2.append(str(row_counter)+columns[column_counter]+"x"+line)
                if lines[column_counter-1]=="P":
                    patrol_location_p2.append(str(row_counter)+columns[column_counter-1]+"-"+str(row_counter)+columns[column_counter])
                    patrolboat_p2+=1
                    
                try:
                    if  player2_location[row_counter].strip("\n").split(";")[column_counter]=="P":
                        patrol_location_p2.append(str(row_counter)+columns[column_counter]+"-"+str(row_counter+1)+columns[column_counter])
                        patrolboat_p2+=1
                            
                except Exception:
                    pass
                
            if line=="S":
                ship_locations_p2.append(str(row_counter)+columns[column_counter]+"x"+line)
                if lines[column_counter-1]=="S" and lines[column_counter-2]=="S":
                    submarine_location_p2.append(str(row_counter)+columns[column_counter-2]+"-"+str(row_counter)+columns[column_counter-1]+"-"+str(row_counter)+columns[column_counter])
                    submarine_p2+=1
                        
                try:    
                    if  player2_location[row_counter].strip("\n").split(";")[column_counter]=="S" and player2_location[row_counter+1].strip("\n").split(";")[column_counter]=="S":
                        submarine_location_p2.append(str(row_counter)+columns[column_counter]+"-"+str(row_counter+1)+columns[column_counter]+"-"+str(row_counter+2)+columns[column_counter])
                        submarine_p2+=1
                        
                except Exception:
                    pass  
                
            if line=="D":
                ship_locations_p2.append(str(row_counter)+columns[column_counter]+"x"+line)
                if lines[column_counter-1]=="D" and lines[column_counter-2]=="D":
                    destroyer_location_p2.append(str(row_counter)+columns[column_counter-2]+"-"+str(row_counter)+columns[column_counter-1]+"-"+str(row_counter)+columns[column_counter])
                    destroyer_p2+=1
                        
                try:    
                    if  player2_location[row_counter-3].strip("\n").split(";")[column_counter]=="D" and player2_location[row_counter-2].strip("\n").split(";")[column_counter]=="D":
                        destroyer_location_p2.append(str(row_counter-2)+columns[column_counter]+"-"+str(row_counter-1)+columns[column_counter]+"-"+str(row_counter)+columns[column_counter])
                        destroyer_p2+=1
                            
                except Exception:
                    pass
            if line=="B":
                ship_locations_p2.append(str(row_counter)+columns[column_counter]+"x"+line)
                if lines[column_counter-1]=="B" and lines[column_counter-2]=="B" and lines[column_counter-3]=="B":
                    battleship_location_p2.append(str(row_counter)+columns[column_counter-3]+"-"+str(row_counter)+columns[column_counter-2]+"-"+str(row_counter)+columns[column_counter-1]+"-"+str(row_counter)+columns[column_counter])
                    battleship_p2+=1
                        
                try:    
                    if  player2_location[row_counter-4].strip("\n").split(";")[column_counter]=="B" and player2_location[row_counter-3].strip("\n").split(";")[column_counter]=="B" and player2_location[row_counter-2].strip("\n").split(";")[column_counter]=="B":
                        battleship_location_p2.append(str(row_counter-3)+columns[column_counter]+"-"+str(row_counter-2)+columns[column_counter]+"-"+str(row_counter-1)+columns[column_counter]+"-"+str(row_counter)+columns[column_counter])
                        battleship_p2+=1
                            
                except Exception:
                    pass 
            if line=="C":
                ship_locations_p2.append(str(row_counter)+columns[column_counter]+"x"+line)
                if lines[column_counter-1]=="C" and lines[column_counter-2]=="C" and lines[column_counter-3]=="C" and lines[column_counter-4]=="C":
                    carrier_location_p2.append(str(row_counter)+columns[column_counter-4]+"-"+str(row_counter)+columns[column_counter-3]+"-"+str(row_counter)+columns[column_counter-2]+"-"+str(row_counter)+columns[column_counter-1]+"-"+str(row_counter)+columns[column_counter])
                    carrier_p2+=1
                    
                try:    
                    if  player2_location[row_counter-5].strip("\n").split(";")[column_counter]=="C" and player2_location[row_counter-4].strip("\n").split(";")[column_counter]=="C" and player2_location[row_counter-3].strip("\n").split(";")[column_counter]=="C" and player2_location[row_counter-2].strip("\n").split(";")[column_counter]=="C":
                        carrier_location_p2.append(str(row_counter-4)+columns[column_counter]+"-"+str(row_counter-3)+columns[column_counter]+"-"+str(row_counter-2)+columns[column_counter]+"-"+str(row_counter-1)+columns[column_counter]+"-"+str(row_counter)+columns[column_counter])
                        carrier_p2+=1
                            
                except Exception:
                    pass 
                
            column_counter+=1
        row_counter+=1
        
    #this lists  and variables for the hitten parts of ships
    move_locations_p1=[]
    move_locations_p2=[]
    carrier_counter_p1,destroyer_counter_p1,submarine_counter_p1,battleship_counter_p1,patrol_counter_p1=0,0,0,0,0
    carrier_counter_p2,destroyer_counter_p2,submarine_counter_p2,battleship_counter_p2,patrol_counter_p2=0,0,0,0,0
    for attacks in player1_attacks:
        attack=attacks.split(";")
        for a in attack:
            try: 
                assert int(a.split(",")[0])<=10 and columns.index(a.split(",")[1])<=9 #It will check index of given value
                move_locations_p1.append(a.split(",")[0]+a.split(",")[1]) #ıt will append the attacks of players in list
                
            except AssertionError:
                print("AssertionError: Invalid Operation.")
                f.write("AssertionError: Invalid Operation.\n\n")
            except:
                pass
    for attacks in player2_attacks:
        attack=attacks.split(";")
        for a in attack:
            try:
                assert int(a.split(",")[0])<=10 and columns.index(a.split(",")[1])<=9
                move_locations_p2.append(a.split(",")[0]+a.split(",")[1])
                
            except AssertionError:
                print("AssertionError: Invalid Operation.")
                f.write("\nAssertionError: Invalid Operation.\n")
            except:
                pass
    global player1_list
    global player2_list
    player1_list=[]
    for i in range(100): #list for players hidden board
        player1_list.append("- ")
    player2_list=[]
    for i in range(100):
        player2_list.append("- ")
        

    for move_p1,move_p2 in zip(move_locations_p1,move_locations_p2): #this will have moves of player1 and player2 one by one and write the boards
        try:
            global move
            controller=0
            round+=1
            for location in ship_locations_p2:
                turn=1 #for write current player turn
                move=str(move_p1)[0:-1] + "," + str(move_p1)[-1] #for write the current move
                if location.split("x")[0]==move_p1: #It is for check if player hit the opponent
                    controller=1 
            for carrier in carrier_location_p2: #This parts for the  check what kind of ship did player shot and calculate the sink ships
                for loc in carrier.split("-"):
                    if loc == move_p1:
                        carrier_counter_p2+=1 
                    if carrier_counter_p2==5:
                        carrier_p2-=1
                        carrier_counter_p2=0
            for destroyer in destroyer_location_p2:
                for loc in destroyer.split("-"):
                    if loc == move_p1:
                        destroyer_counter_p2+=1 
                    if destroyer_counter_p2==3:
                        destroyer_p2-=1
                        destroyer_counter_p2=0
            for submarine in submarine_location_p2:
                for loc in submarine.split("-"):
                    if loc == move_p1:
                        submarine_counter_p2+=1 
                    if submarine_counter_p2==3:
                        submarine_p2-=1
                        submarine_counter_p2=0
            for patrolboat in patrol_location_p2:
                for patrol in patrolboat.split("-"):
                    if patrol==move_p1:
                        patrol_counter_p2+=1
                    if patrol_counter_p2==2:
                        patrolboat_p2-=1
                        patrol_counter_p2=0
            for battle in battleship_location_p2:
                for b_ship in battle.split("-"):
                    if b_ship==move_p1:
                        battleship_counter_p2+=1
                    if battleship_counter_p2==8:
                        battleship_p2-=1
                        battleship_counter_p2=0
            
            if controller==1: #If player hit the opponent write X and current board
                players_area(player1_list,player2_list)
                player2_list[((int(str(move_p1.split())[2:-3])-1)*10)+columns.index(str(move_p1.split())[-3])]="X "
                controller=0
            else: #If player miss write O and current board
                players_area(player1_list,player2_list)
                player2_list[((int(str(move_p1.split())[2:-3])-1)*10)+columns.index(str(move_p1.split())[-3])]="O "
                controller=0


            for location in ship_locations_p1: # check for other player
                move=str(move_p2)[0:-1] + "," + str(move_p2)[-1]
                turn=2
                if location.split("x")[0]==move_p2:
                    controller=1
            
            for carrier in carrier_location_p1:
                for loc in carrier.split("-"):
                    if loc == move_p2:
                        carrier_counter_p1+=1 
                    if carrier_counter_p1==5:
                        carrier_p1-=1
                        carrier_counter_p1=0
            for destroyer in destroyer_location_p1:
                for loc in destroyer.split("-"):
                    if loc == move_p2:
                        destroyer_counter_p1+=1 
                    if destroyer_counter_p1==3:
                        destroyer_p1-=1
                        destroyer_counter_p1=0
            for submarine in submarine_location_p1:
                for loc in submarine.split("-"):
                    if loc == move_p2:
                        submarine_counter_p1+=1 
                    if submarine_counter_p1==3:
                        submarine_p1-=1
                        submarine_counter_p1=0
            for patrolboat in patrol_location_p1:
                for patrol in patrolboat.split("-"):
                    if patrol==move_p2:
                        patrol_counter_p1+=1
                    if patrol_counter_p1==2:
                        patrolboat_p1-=1
                        patrol_counter_p1=0
            for battle in battleship_location_p1:
                for b_ship in battle.split("-"):
                    if b_ship==move_p2:
                        battleship_counter_p1+=1
                    if battleship_counter_p1==4:
                        battleship_p1-=1
                        battleship_counter_p1=0
                
                
            if controller==1:
                players_area(player1_list,player2_list)
                player1_list[((int(str(move_p2.split())[2:-3])-1)*10)+columns.index(str(move_p2.split())[-3])]="X "
                controller=0
            else:
                players_area(player1_list,player2_list)
                player1_list[((int(str(move_p2.split())[2:-3])-1)*10)+columns.index(str(move_p2.split())[-3])]="O "
                controller=0
            #After both players made their move check for if one of them win
            if turn==2 and carrier_p1==0 and destroyer_p1==0 and submarine_p1==0 and battleship_p1==0 and patrolboat_p1==0: 
                print("Player2 Wins!\n")
                f.write("Player2 Wins!\n\n")
                win_screen(player1_list,player2_list)
                break
            if turn==2 and carrier_p2==0 and destroyer_p2==0 and submarine_p2==0 and battleship_p2==0 and patrolboat_p2==0:
                print("player1 Wins!\n")
                f.write("Player1 Wins!\n\n")
                win_screen(player1_list,player2_list)
                break
            if  turn==2 and carrier_p1==0 and destroyer_p1==0 and submarine_p1==0 and battleship_p1==0 and patrolboat_p1==0 and carrier_p2==0 and destroyer_p2==0 and submarine_p2==0 and battleship_p2==0 and patrolboat_p2==0:
                print("It is a Draw!\n")
                f.write("It is a Draw!\n\n")
                win_screen(player1_list,player2_list)
                break
        except IndexError:
            print("IndexError: you must give avalible location")
            f.write("IndexError: you must give avalible location")
        except ValueError:
            print("ValueError: you must give avaliable location")
            f.write("ValueError: you must give avaliable location")
        except Exception:
            print("kaBOOM: run for your life!")  
            f.write("kaBOOM: run for your life!")
    
    f.close()
except IOError:
    print("IOError: input file(s) Player1.txt, Player2.txt, Player1.in, Player2.in is/are not reachable.")
except IndexError:
        print("IndexError: number of input files less than expected.")
except Exception:
    print("kaBOOM: run for your life!")

#Berkay Saylak 2210356085
import os
patient_data = []
names = []

"""I used this function to open the input text and read the lines in it and after that i imported this function
I used encoding for escape from Turkish characters error"""
def input_line():
    current_dir_path = os.getcwd()
    reading_file_name = "doctors_aid_inputs.txt"
    reading_file_path = os.path.join(current_dir_path, reading_file_name)
    global file
    with open(reading_file_path, "r", encoding="utf-8") as f:
        file = f.readlines()


input_line()

#I used this function to write the values in the output text and i keep it open to make changes in it after that i imported this function
def output_line():
    current_dir_path = os.getcwd()
    writing_file_name = "doctors_aid_outputs.txt"
    writing_file_path = os.path.join(current_dir_path, writing_file_name)
    global f
    f = open(writing_file_path, "w", encoding="utf-8")


output_line()

#I used this function to record the new patients,i check that if the patient is already in list and i added the list if it is not
def create():
    if len(names) == 0:
        patient_data.append(line)
        names.append(names1)
        f.write("Patient {} is recorded.".format(names1))
    else:
        number = 0
        for name in names:
            if name == names1:
                number += 1
        if number != 0:
            f.write("\nPatient {} cannot recorded due to duplication.".format(names1))
        else:
            patient_data.append(line)
            names.append(names1)
            f.write("\nPatient {} is recorded.".format(names1))

"""I used this function to make a table with variables and I converted fractional values to percentiles and I used {:x} and format for give a position to values
I used patient_name variables to separate patient's names and function's names and i used if,else for probability of empty list"""
def listing():
    if len(patient_data) != 0:
        f.write("\n""{:8}{:12}{:16}{:12}{:16}{:20}".format("Patient", "Diagnosis", "Disease", "Disease", "Treatment", "Treatment"))
        f.write("\n""{:8}{:12}{:16}{:12}{:16}{:20}""\n".format("Name", "Accuracy", "Name", "Incidence", "Name", "Risk"))
        f.write("-" * 76)
        for patient in patient_data:
            patient_name = patient[0]
            patient_name_1 = patient_name.split(" ")
            patient_name_2 = patient_name_1[1]
            patient_diagnosis = str(round(float(patient[1]) * 100, 2))
            if len(patient_diagnosis) == 4:
                patient_diagnosis = str(round(float(patient[1]) * 100, 2)) + "0%"
            else:
                patient_diagnosis = str(round(float(patient[1]) * 100, 2)) + "%"
            treatment_risk = str(round(float(patient[-1]) * 100)) + "%"
            f.write("\n""{:8}{:12}{:16}{:12}{:16}{:20}".format(patient_name_2, patient_diagnosis, patient[2], patient[3], patient[4], treatment_risk))
    else:
        f.write("[]""\n")

#I used this function to remove the variables if this variables in the list number for index of variables and control_number for check if it is in the list
def remove():
    number = 0
    control_number = 0
    if len(names) != 0:
        for name in names:
            if name == names1:
                names.remove(name)
                patient_data.pop(number)
                f.write("\nPatient {} is removed.".format(names1))
                number += 1
                control_number += 1
            elif name != names1:
                number += 1
        if control_number == 0:
            f.write("\nPatient {} cannot be removed due to absence.".format(names1))
    else:
        f.write("\nPatient {} cannot be removed due to absence.".format(names1))

"""I used this function to compare the relationship between probability and surgery risk i converted values to int and float to make mathematical process on it
to use fractional expression in list firstly i converted it string and then i split it after that i converted to each values in int and i finally made the mathematical process
to find persentage probability i divided true pozitif by total pozitif and calculated persentage value"""
def recommendation():
    number = 0
    control_number = 0
    for name in names:
        if name == names1:
            patient = patient_data[number]
            true_poz = str(patient[3])
            rate = true_poz.split("/")
            nominator = int(rate[0])
            denominator = int(rate[1])
            true_positive = nominator / denominator
            false_poz = 1 - (float(patient[1]))
            total_poz = true_positive + false_poz
            probability = 100 * true_positive / total_poz
            probability_0 = round(probability, 2)
            surgery_risk = (float(patient[-1]))
            surgery_risk = surgery_risk * 100
            if probability_0 < surgery_risk:
                f.write("\nSystem suggest {} NOT to have the treatment.".format(names1))
            else:
                f.write("\nSystem suggest {}  to have the treatment.".format(names1))
            number += 1
            control_number += 1
        elif name != names1:
            number += 1
    if control_number == 0:
        f.write("\nRecommendation for {} cannot be calculated due to absence.".format(names1))

"""to use fractional expression in list firstly i converted it string and then i split it after that i converted to each values in int and i finally made the mathematical process
to find persentage probability i divided true positive by total positive and calculated persentage value and i use round value to round this float value to 2 decimal
and I used if and else to make the rounding function true for 0 after the dot as well
again number for index of variables and control_number for check if it is in the list"""
def probability():
    number = 0
    control_number = 0
    for name in names:
        if name == names1:
            patient = patient_data[number]
            true_poz = str(patient[3])
            rate = true_poz.split("/")
            nominator = int(rate[0])
            denominator = int(rate[1])
            true_positive = nominator / denominator
            false_poz = 1 - (float(patient[1]))
            total_poz = true_positive + false_poz
            probability = 100 * true_positive / total_poz
            probability_0 = round(probability, 2)
            probability_1 = str(round(probability, 2))
            probability_1 = probability_1.split(".")
            if probability_1[1] == "0":
                probability = probability_1[0]
            else:
                probability = probability_0
            f.write("\nPatient {0} has a probability of {1}% of having {2}.".format(names1, probability, patient[2]))
            number += 1
            control_number += 1
        elif name != names1:
            number += 1
    if control_number == 0:
        f.write("\nProbability for {} cannot be calculated due to absence.".format(names1))

#Here i check first element of each list in file to find function i split the function by ", " and then i split it again with " " to seperate function name and patient
for lines in file:
    if lines != "list\n":
        line = lines.split(", ")
        function_list = line[0]
        function_ = function_list.split(" ")
        function_1 = function_[0]
        names1 = function_[1].removesuffix("\n")
        if function_1 == "create":
            create()
        elif function_1 == "probability":
            probability()
        elif function_1 == "recommendation":
            recommendation()
        elif function_1 == "remove":
            remove()
    else:
        listing()
#Berkay SAYLAK 2210356085
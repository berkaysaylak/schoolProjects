import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
public class smartSystem{//this part for management of all system
    private LocalDateTime initialTime; //time control part
    public List<String> deviceList = new ArrayList<String>(); //for the list of all devices
    public List<LocalDateTime> timeList = new ArrayList<>();//for the sorting time
    public HashMap<String,device> switchTimeList = new HashMap<>();//for the sorting time
    public HashMap<String, device> devices = new HashMap<>();
    public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");
    public String outputLocation;

    /**
     * Constructor for smartSystem class.
     * Takes an array of strings as input and checks each string for a command.
     * If a valid command is found, it is executed accordingly.
     * If an erroneous command is found, an error message is printed.
     * @param lines an array of strings representing commands to be executed.
     */
    public smartSystem(String[] lines,String outputLocation){
        this.outputLocation = outputLocation;
        int initialTimeNumber=0; //for the use set initial time function for once
        boolean funcBreak = false;
        if(!lines[0].split("\t")[0].equals("SetInitialTime") || lines[0].split("\t").length!=2){ //if first function is not setInitialTime break and finish
            Main.writer("COMMAND: "+lines[0]+"\n",true,outputLocation);
            Main.writer("ERROR: First command must be set initial time! Program is going to terminate!\n",true,outputLocation);
        }else {
            for(String line:lines){ //check for every line of the file
                if(line.split("\t")[0].equals("SetInitialTime") && line.split("\t").length==2){
                    if(initialTimeNumber==0){
                        if(timeConvertible(line.split("\t")[1])!=null){
                            this.initialTime = timeConvertible(line.split("\t")[1]);
                            Main.writer("COMMAND: "+line+"\n",true,outputLocation);
                            Main.writer("SUCCESS: Time has been set to "+line.split("\t")[1]+"!\n",true,outputLocation);
                        }else {
                            Main.writer("COMMAND: "+line+"\n",true,outputLocation);
                            Main.writer("ERROR: Format of the initial date is wrong! Program is going to terminate!\n",true,outputLocation);
                            funcBreak=true;
                            break;
                        }
                        initialTimeNumber++;
                    }else {
                        Main.writer("COMMAND: "+line+"\n",true,outputLocation);
                        Main.writer("ERROR: Erroneous command!\n",true,outputLocation);
                    }
                }else if(line.split("\t")[0].equals("Add")){
                    Main.writer("COMMAND: "+line+"\n",true,outputLocation);
                    add(line);
                }else if(line.split("\t")[0].equals("SetTime")){
                    Main.writer("COMMAND: "+line+"\n",true,outputLocation);
                    setTime(line);
                }else if(line.split("\t")[0].equals("SkipMinutes")){
                    Main.writer("COMMAND: "+line+"\n",true,outputLocation);
                    skipMinutes(line);
                }else if(line.split("\t")[0].equals("Nop")){
                    Main.writer("COMMAND: "+line+"\n",true,outputLocation);
                    nop(line);
                }else if(line.split("\t")[0].equals("Remove")){
                    Main.writer("COMMAND: "+line+"\n",true,outputLocation);
                    remove(line);
                }else if(line.split("\t")[0].equals("SetSwitchTime")){
                    Main.writer("COMMAND: "+line+"\n",true,outputLocation);
                    setSwitchTime(line);
                }else if(line.split("\t")[0].equals("Switch")){
                    Main.writer("COMMAND: "+line+"\n",true,outputLocation);
                    Switch(line);
                }else if(line.split("\t")[0].equals("ChangeName")){
                    Main.writer("COMMAND: "+line+"\n",true,outputLocation);
                    changeName(line);
                }else if(line.split("\t")[0].equals("PlugIn")){
                    Main.writer("COMMAND: "+line+"\n",true,outputLocation);
                    plugIn(line);
                }else if(line.split("\t")[0].equals("PlugOut")){
                    Main.writer("COMMAND: "+line+"\n",true,outputLocation);
                    plugOut(line);
                }else if(line.split("\t")[0].equals("SetKelvin")){
                    Main.writer("COMMAND: "+line+"\n",true,outputLocation);
                    setKelvin(line);
                }else if(line.split("\t")[0].equals("SetBrightness")){
                    Main.writer("COMMAND: "+line+"\n",true,outputLocation);
                    setBrightness(line);
                }else if(line.split("\t")[0].equals("SetColorCode")){
                    Main.writer("COMMAND: "+line+"\n",true,outputLocation);
                    setColourCode(line);
                }else if(line.split("\t")[0].equals("SetWhite")){
                    Main.writer("COMMAND: "+line+"\n",true,outputLocation);
                    setWhite(line);
                }else if(line.split("\t")[0].equals("SetColor")){
                    Main.writer("COMMAND: "+line+"\n",true,outputLocation);
                    setColour(line);
                }else if(line.split("\t")[0].equals("ZReport")){
                    Main.writer("COMMAND: "+line+"\n",true,outputLocation);
                    zReport(line);
                }else{
                    Main.writer("COMMAND: "+line+"\n",true,outputLocation);
                    Main.writer("ERROR: Erroneous command!\n",true,outputLocation);
                }
            }
            if (!lines[lines.length-1].equals("ZReport") && !funcBreak){
                Main.writer("ZReport:\n",true,outputLocation);
                zReport("ZReport");
            }
        }
    }

    /**
     * Converts a string representation of date and time in the format "yyyy-MM-dd_HH:mm:ss" to a LocalDateTime object.
     * @param strTime a string representation of date and time in the format "yyyy-MM-dd_HH:mm:ss"
     * @return a LocalDateTime object representing the given date and time, or null if the input is invalid
     */
    public LocalDateTime timeConvertible(String strTime) {
        try {
            String[] localParts = strTime.split("_");
            String[] date = localParts[0].split("-");
            String[] time = localParts[1].split(":");
            int year = Integer.parseInt(date[0]);
            int month = Integer.parseInt(date[1]);
            int day = Integer.parseInt(date[2]);
            if (date[1].matches("^0\\d$")) {
                month = Integer.parseInt(date[1].substring(1));
            }
            if (date[2].matches("^0\\d$")) {
                day = Integer.parseInt(date[2].substring(1));
            }
            int hour = Integer.parseInt(time[0]);
            int minute = Integer.parseInt(time[1]);
            int second = Integer.parseInt(time[2]);
            return LocalDateTime.of(year, month, day, hour, minute, second);
        }catch (Exception e){
            return null;
        }
    }

    /**
     * Converts a boolean value representing a state to a string representation.
     *
     * @param state a boolean value representing a state, where true represents "on" and false represents "off"
     * @return a string representation of the state, either "on" or "off"
     */
    public String stateConverter(Boolean state){//Converts the boolean state to a string representation.
        if(state){
            return "on";
        }else {
            return "off";
        }
    }

    /**
     This function checks if it's switch time for any device and performs the corresponding operations.
     It updates the status of the devices and calculates the usage and storage if necessary.
     It also sorts the device list after the operations.
     */
    public void switchController(String time){ //this function for check after any time operations if it is switch time
        for(int i = 0; i < deviceList.size();i++){
            String device = deviceList.get(0);
            if(devices.get(device).getSwitchTime()!=null){
                if(!devices.get(device).getSwitchTime().isAfter(timeConvertible(time))){
                    if(devices.get(device).getStatus()){
                        devices.get(device).setStatus(false);
                        if (devices.get(device) instanceof Camera ) {
                            storageCalculator(devices.get(device).getSwitchTime(),device);
                        }else if(devices.get(device) instanceof plug){
                            if(((plug) devices.get(device)).getPlugInOut()){
                                usageCalculator(devices.get(device).getSwitchTime(),device);
                            }
                        }
                        devices.get(device).setSwitchTime(null);
                    }else {
                        devices.get(device).setStatus(true);
                        if (devices.get(device) instanceof Camera ) {
                            ((Camera) devices.get(device)).setStart(devices.get(device).getSwitchTime());
                        }else if(devices.get(device) instanceof plug){
                            if(((plug) devices.get(device)).getPlugInOut()){
                                ((plug) devices.get(device)).setStart(devices.get(device).getSwitchTime());
                            }
                        }
                        devices.get(device).setSwitchTime(null);
                    }
                    sort();
                }
            }
        }
    }

    /**
     * Removes switch times from the timeList that have already passed, based on the given current time.
     *
     * @param currentTime a string representation of the current time in the format "yyyy-MM-dd_HH:mm:ss"
     */
    public void removePastSwitchTimes(String currentTime) {//Removes switch times from the timeList that have already passed.
        if (!timeList.isEmpty()) {
            Iterator<LocalDateTime> iterator = timeList.iterator();
            while (iterator.hasNext()) {
                LocalDateTime t = iterator.next();
                if (timeConvertible(currentTime).compareTo(t) >= 0) {
                    iterator.remove();
                }
            }
        }
    }

    /**
     * Sorts the list of devices according to their switch time (if any) and updates the deviceList accordingly.
     * Devices with switch times are sorted by their switch time in ascending order, while devices without switch times
     * are placed at the beginning of the list in the order they were added.
     */
    public void sort(){//Sorts the list of devices according to their switch time (if any) and updates the deviceList accordingly.
        List<String> unSortedList = new ArrayList<>();
        List<device> sortedTime = new ArrayList<>();
        List<String> sortedList = new ArrayList<>();
        for(String device : deviceList){
            if(devices.get(device).getSwitchTime()==null){
                unSortedList.add(device);
            }else {
                sortedTime.add(devices.get(device));
            }
        }
        Collections.sort(sortedTime, new Comparator<device>() {
            @Override
            public int compare(device d1, device d2) {
                if(d1.getSwitchTime().compareTo(d2.getSwitchTime())!=0){
                    return d1.getSwitchTime().compareTo(d2.getSwitchTime());
                }else {
                    return -1;
                }
            }
        });
        for(device dev : sortedTime){
            sortedList.add(dev.getName());
        }
        unSortedList.addAll(0,sortedList);
        deviceList.clear();
        deviceList.addAll(unSortedList);
    }

    /**
     * Checks if the given string can be converted to an integer.
     * @param str the string to be checked
     * @return true if the string can be converted to an integer, false otherwise
     */
    public static boolean isInteger(String str) { //for to learn is number convertible and run from error
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if the given string can be converted to an double.
     * @param str the string to be checked
     * @return true if the string can be converted to an double, false otherwise
     */
    public static boolean isDouble(String str) { //for to learn is number convertible and run from error
        try {
            if(str.contains(".")){
                str = str.replace(",", ".");
            }
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if the given string can be converted to an hexadecimal.
     * @param str the string to be checked
     * @return true if the string can be converted to an hexadecimal, false otherwise
     */
    public static boolean isHex(String str) { //for to learn is number convertible and run from error
        try {
            String hexStr = str.substring(2);
            Integer.parseInt(hexStr,16);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Rounds the fraction part of a double number to two decimal places and returns
     * the result as a formatted string with the integer part.
     * @param number the double number to be rounded
     * @return the formatted string representing the rounded number with integer part
     */
    public static String roundFraction(double number) {// this part for write the usage value in true format
        double integerPart = Math.floor(number);
        double fractionPart = number - integerPart;
        double roundedFraction = Math.round(fractionPart * 100.0) / 100.0;
        return String.format("%.2f", roundedFraction+integerPart);
    }

    /**
     * Checks if the given LocalDateTime object is null or not and returns a string representation of it.
     * @param time the LocalDateTime object to be checked
     * @return "null" if the given object is null, or the formatted string representation of the object
     */
    public String  isItNull(LocalDateTime time){//Checks if the given LocalDateTime object is null or not, and returns a string representation
        if(time==null){
            return "null";
        }else {
            return time.format(formatter);
        }
    }

    /**
     * Checks if a device with the given name is already in the list of devices.
     * @param deviceName the name of the device to check
     * @return true if the device with the given name appears exactly once in the list, false otherwise
     */
    public boolean isDeviceInList(String deviceName) {// Check if device with the given name is already in the list
        int count = 0;// Counter for number of times deviceName appears in the list
        for (String device : deviceList) {
            if (device.equals(deviceName)) {
                count++;
            }
        }
        return count == 1;// Return true if deviceName appears exactly once, false otherwise
    }

    /**
     * Gets the index of the device with the given name in the deviceList.
     * @param deviceIndex a string representing the name of the device to search for
     * @return the index of the device in the deviceList, or 0 if the device is not found
     */
    public int getIndex(String deviceIndex){//Gets the index of the device with the given name in the deviceList.
        int i=0;
        int Index=0;
        for(String device : deviceList){ //loop for check if name is already taken
            if(device.equals(deviceIndex)){
                Index=i;
            }
            i+=1;
        }
        return Index;
    }

    /**
     *Calculates the usage used by a plug device based on the given end time and the device's start time and
     *parameters. The usage is calculated using the formula: ampere * voltage * hours, where ampere and voltage
     *are the device's ampere and default voltage parameters, and hours is the time difference between the start and
     *end times in hours. The total usage is then updated for the device.
     *@param end the end time for the usage calculation
     *@param name the name of the plug device for which the usage is being calculated
     *@throws ClassCastException if the device with the given name is not a plug device
     */
    public void usageCalculator(LocalDateTime end,String name){//Calculates the usage used by a plug device.
        plug device = (plug) devices.get(name);
        double ampere = device.getAmpere();
        int voltage = device.getDefaultVoltage();
        LocalDateTime start = device.getStart();
        double minutes = (java.time.Duration.between(start,end).toMillis()/(double) 60000); // calculate time difference take the time with millis cause of second difference
        double hours = minutes/60;
        double usage = ampere*voltage*hours;
        double totalUsage = device.getTotalUsage() + usage;
        device.setTotalUsage(totalUsage);
    }

    /**
     *Calculates the storage used by a Camera device based on the duration of operation and the device's storage capacity.
     *@param end the end time of the device's operation
     *@param name the name of the Camera device
     *@throws IllegalArgumentException if the device with the given name is not an instance of the Camera class
     *@throws NullPointerException if the device with the given name does not exist in the device list
     */
    public void storageCalculator(LocalDateTime end,String name){//Calculates the storage used by a Camera device.
        Camera camera = (Camera) devices.get(name);
        double megaByte = camera.getMegaByte();
        LocalDateTime start = camera.getStart();
        double minutes = (java.time.Duration.between(start,end).toMillis()/(double) 60000);
        double storage = megaByte * minutes;
        double totalMegaByte = camera.getTotalMegaByte() + storage;
        camera.setTotalMegaByte(totalMegaByte);
    }

    /**
     * Adds a new smart device to the system based on the given command string.
     * @param command the command string to parse and use to create the new device.
     * This should be a tab-separated string containing information about the device to be added, including its type, name, status, and (optionally)
     * additional parameters like amperage or storage capacity.
     * @throws IOException if there is an error writing to the output file.
     */
    public void add(String Command){ //add function
        String[] command = Command.split("\t"); //get each value
        try {
            if (command[1].equals("SmartPlug")) { //errors and operations if device is smart plug
                if (command.length == 3) {
                    if (!isDeviceInList(command[2])) {
                        devices.put(command[2], new plug(command[2], "Off", null));
                        new plug(command[2], "Off", null);
                        deviceList.add(command[2]);
                    } else {
                        Main.writer("ERROR: There is already a smart device with same name!\n", true,outputLocation);
                    }
                } else if (command.length == 4) {
                    if (!isDeviceInList(command[2])) {
                        if (command[3].equals("On") || command[3].equals("Off")) {
                            devices.put(command[2], new plug(command[2], command[3], null));
                            new plug(command[2], command[3], null);
                            deviceList.add(command[2]);
                        } else {
                            Main.writer("ERROR: Erroneous command!\n", true,outputLocation);
                        }
                    } else {
                        Main.writer("ERROR: There is already a smart device with same name!\n", true,outputLocation);
                    }
                } else if (command.length == 5) {
                    if (!isDeviceInList(command[2])) {
                        if (command[3].equals("On") || command[3].equals("Off")) {
                            if (isDouble(command[4])) {
                                if (command[4].contains(".")) {
                                    command[4] = command[4].replace(",", ".");
                                }
                                if (Double.parseDouble(command[4]) > 0) {
                                    devices.put(command[2], new plug(command[2], command[3], command[4], null));
                                    new plug(command[2], command[3], command[4], null);
                                    deviceList.add(command[2]);
                                    ((plug) devices.get(command[2])).setPlugInOut(true);
                                    if (command[3].equals("On")) { //this part for calculation of usage
                                        ((plug) devices.get(command[2])).setStart(initialTime);
                                    }
                                } else {
                                    Main.writer("ERROR: Ampere value must be a positive number!\n", true,outputLocation);
                                }
                            } else {
                                Main.writer("ERROR: Erroneous command!\n", true,outputLocation);
                            }
                        } else {
                            Main.writer("ERROR: Erroneous command!\n", true,outputLocation);
                        }
                    } else {
                        Main.writer("ERROR: There is already a smart device with same name!\n", true,outputLocation);
                    }
                } else {
                    Main.writer("ERROR: Erroneous command!\n", true,outputLocation);
                }
            } else if (command[1].equals("SmartCamera")) { //errors and operations if device is smart camera
                if (command.length == 4) {
                    if (!isDeviceInList(command[2])) {
                        if (isDouble(command[3]) || isInteger(command[3])) {
                            if (command[3].contains(".")) {
                                command[3] = command[3].replace(",", ".");
                            }
                            if (Double.parseDouble(command[3]) > 0) {
                                devices.put(command[2], new Camera(command[2], "Off", null, command[3]));
                                new Camera(command[2], "Off", null, command[3]);
                                deviceList.add(command[2]);
                                if (command[3].equals("On")) { //this part for calculation of usage
                                    ((Camera) devices.get(command[2])).setStart(initialTime);
                                }
                            } else {
                                Main.writer("ERROR: Megabyte value must be a positive number!\n", true,outputLocation);
                            }
                        } else {
                            Main.writer("ERROR: Erroneous command!\n", true,outputLocation);
                        }
                    } else {
                        Main.writer("ERROR: There is already a smart device with same name!\n", true,outputLocation);
                    }
                } else if (command.length == 5) {
                    if (!isDeviceInList(command[2])) {
                        if (isInteger(command[3]) || isDouble(command[3])) {
                            if (command[4].contains(".")) {
                                command[4] = command[4].replace(",", ".");
                            }
                            if (Double.parseDouble(command[3]) > 0) {
                                if (command[4].equals("On") || command[4].equals("Off")) {
                                    devices.put(command[2], new Camera(command[2], command[4], null, command[3]));
                                    new Camera(command[2], command[4], null, command[3]);
                                    deviceList.add(command[2]);
                                    if (command[4].equals("On")) { //this part for calculation of usage
                                        ((Camera) devices.get(command[2])).setStart(initialTime);
                                    }
                                } else {
                                    Main.writer("ERROR: Erroneous command!\n", true,outputLocation);
                                }
                            } else {
                                Main.writer("ERROR: Megabyte value must be a positive number!\n", true,outputLocation);
                            }
                        } else {
                            Main.writer("ERROR: Erroneous command!\n", true,outputLocation);
                        }
                    } else {
                        Main.writer("ERROR: There is already a smart device with same name!\n", true,outputLocation);
                    }
                } else {
                    Main.writer("ERROR: Erroneous command!\n", true,outputLocation);
                }
            } else if (command[1].equals("SmartLamp")) { //errors and operations if device is smart lamp
                if (command.length == 3) {
                    if (!isDeviceInList(command[2])) {
                        devices.put(command[2], new Lamp(command[2], "Off", null));
                        new Lamp(command[2], "Off", null);
                        deviceList.add(command[2]);
                    } else {
                        Main.writer("ERROR: There is already a smart device with same name!\n", true,outputLocation);
                    }
                } else if (command.length == 4) {
                    if (!isDeviceInList(command[2])) {
                        if (command[3].equals("On") || command[3].equals("Off")) {
                            devices.put(command[2], new Lamp(command[2], command[3], null));
                            new Lamp(command[2], command[3], null);
                            deviceList.add(command[2]);
                        } else {
                            Main.writer("ERROR: Erroneous command!\n", true,outputLocation);
                        }
                    } else {
                        Main.writer("ERROR: There is already a smart device with same name!\n", true,outputLocation);
                    }
                } else if (command.length == 6) {
                    if (!isDeviceInList(command[2])) {
                        if (command[3].equals("On") || command[3].equals("Off")) {
                            if (isInteger(command[4]) && isInteger(command[5])) {
                                if (2000 <= Integer.parseInt(command[4]) && Integer.parseInt(command[4]) <= 6500) {
                                    if (0 <= Integer.parseInt(command[5]) && Integer.parseInt(command[5]) <= 100) {
                                        devices.put(command[2], new Lamp(command[2], command[3], null, command[4], command[5]));
                                        new Lamp(command[2], command[3], null, command[4], command[5]);
                                        deviceList.add(command[2]);
                                    } else {
                                        Main.writer("ERROR: Brightness must be in range of 0%-100%!\n", true,outputLocation);
                                    }
                                } else {
                                    Main.writer("ERROR: Kelvin value must be in range of 2000K-6500K!\n", true,outputLocation);
                                }
                            } else {
                                Main.writer("ERROR: Erroneous command!\n", true,outputLocation);
                            }
                        } else {
                            Main.writer("ERROR: Erroneous command!\n", true,outputLocation);
                        }
                    } else {
                        Main.writer("ERROR: There is already a smart device with same name!\n", true,outputLocation);
                    }
                } else {
                    Main.writer("ERROR: Erroneous command!\n", true,outputLocation);
                }
            } else if (command[1].equals("SmartColorLamp")) {
                if (command.length == 3) {
                    if (!isDeviceInList(command[2])) {
                        devices.put(command[2], new ColourLamp(command[2], "Off", null));
                        new ColourLamp(command[2], "Off", null);
                        deviceList.add(command[2]);
                    } else {
                        Main.writer("ERROR: There is already a smart device with same name!\n", true,outputLocation);
                    }
                } else if (command.length == 4) {
                    if (command[3].equals("On") || command[3].equals("Off")) {
                        devices.put(command[2], new ColourLamp(command[2], command[3], null));
                        new ColourLamp(command[2], command[3], null);
                        deviceList.add(command[2]);
                    } else {
                        Main.writer("ERROR: Erroneous command!\n", true,outputLocation);
                    }
                } else if (command.length == 6) {
                    if (!isDeviceInList(command[2])) {
                        if (command[3].equals("On") || command[3].equals("Off")) {
                            if (isHex(command[4]) && isInteger(command[5])) {
                                String hex = command[4].substring(2);
                                if (0 <= Integer.parseInt(hex, 16) && Integer.parseInt(hex, 16) <= 16777215) {
                                    if (0 <= Integer.parseInt(command[5]) && Integer.parseInt(command[5]) <= 100) {
                                        devices.put(command[2], new ColourLamp(command[2], command[3], null, command[4], command[5]));
                                        new ColourLamp(command[2], command[3], null, command[4], command[5]);
                                        deviceList.add(command[2]);
                                    } else {
                                        Main.writer("ERROR: Brightness must be in range of 0%-100%!\n", true,outputLocation);
                                    }
                                } else {
                                    Main.writer("ERROR: Color code value must be in range of 0x0-0xFFFFFF!\n", true,outputLocation);
                                }
                            } else if (isInteger(command[4]) && isInteger(command[5])) {
                                if (2000 <= Integer.parseInt(command[4]) && Integer.parseInt(command[4]) <= 6500) {
                                    if (0 <= Integer.parseInt(command[5]) && Integer.parseInt(command[5]) <= 100) {
                                        devices.put(command[2], new ColourLamp(command[2], command[3], null, command[4], command[5]));
                                        new ColourLamp(command[2], command[3], null, command[4], command[5]);
                                        deviceList.add(command[2]);
                                    } else {
                                        Main.writer("ERROR: Brightness must be in range of 0%-100%!\n", true,outputLocation);
                                    }
                                } else {
                                    Main.writer("ERROR: Kelvin value must be in range of 2000K-6500K!\n", true,outputLocation);
                                }
                            } else {
                                Main.writer("ERROR: Erroneous command!\n", true,outputLocation);
                            }
                        } else {
                            Main.writer("ERROR: Erroneous command!\n", true,outputLocation);
                        }
                    } else {
                        Main.writer("ERROR: There is already a smart device with same name!\n", true,outputLocation);
                    }
                } else {
                    Main.writer("ERROR: Erroneous command!\n", true,outputLocation);
                }
            } else {
                Main.writer("ERROR: Erroneous command!\n", true,outputLocation);
            }
        }catch (Exception e){
            Main.writer("ERROR: Erroneous command!\n", true,outputLocation);
        }
    }
    /**
     Sets the current time of the system to the specified time.
     @param Command a string in the format "setTime\t<time>" where <time> is a string representation of the new time in the format "yyyy-MM-dd HH:mm:ss"
     @throws DateTimeParseException if the time string is not in the correct format
     */
    public void setTime(String Command){
        String[] command = Command.split("\t");
        try {
            if(command.length==2){ //Check if the length of the array is 2, which means the command is in correct format
                if(timeConvertible(command[1])!=null){//Check if the time value is in correct format
                    if (initialTime.isBefore(timeConvertible(command[1]))) {//Check if the new time is after the current time
                        sort();
                        switchController(command[1]); //check if is it switch time for any device
                        removePastSwitchTimes(command[1]); //Remove the switch times that have passed
                        initialTime=timeConvertible(command[1]);
                    } else if(initialTime.isEqual(timeConvertible(command[1]))) {
                        Main.writer("ERROR: There is nothing to change!\n",true,outputLocation);
                    }else {
                        Main.writer("ERROR: Time cannot be reversed!\n",true,outputLocation);
                    }
                }else {
                    Main.writer("ERROR: Time format is not correct!\n",true,outputLocation);
                }
            }else {
                Main.writer("ERROR: Erroneous command!\n",true,outputLocation);
            }
        }catch (Exception e){
            Main.writer("ERROR: Erroneous command!\n",true,outputLocation);
        }
    }
    /**
     Skips the specified number of minutes in the simulation.
     @param Command a string representing the command and number of minutes to skip, separated by a tab character
     @throws NumberFormatException if the second argument is not a valid integer
     */
    public void skipMinutes(String Command){
        String[] command = Command.split("\t");
        try{
            if(command.length==2){ //Check if  the command is in correct format
                if(isInteger(command[1])){ //Check if the int value is in correct format
                    if(Integer.parseInt(command[1])>0){ //Check if the time value is positive
                        initialTime = initialTime.plusMinutes(Long.parseLong(command[1]));
                        switchController(command[1]); //check if is it switch time for any device
                        removePastSwitchTimes(command[1]); //Remove the switch times that have passed
                    }else if(Integer.parseInt(command[1])==0) {
                        Main.writer("ERROR: There is nothing to skip!\n",true,outputLocation);
                    }else {
                        Main.writer("ERROR: Time cannot be reversed!\n",true,outputLocation);
                    }
                }else {
                    Main.writer("ERROR: Erroneous command!\n",true,outputLocation);
                }
            }else {
                Main.writer("ERROR: Erroneous command!\n",true,outputLocation);
            }
        }catch (Exception e){
            Main.writer("ERROR: Erroneous command!\n",true,outputLocation);
        }
    }
    /**
     Executes a "nop" command, which stands for "no operation". This command is used to advance time to the next scheduled smart device switch time.
     @param Command a string representing the command to be executed, which should be in the format "nop".
     */
    public void nop(String Command){
        String[] command = Command.split("\t");
        try {
            if(command.length==1){ //Check if  the command is in correct format
                if(timeList.isEmpty()){
                    Main.writer("ERROR: There is nothing to switch!\n",true,outputLocation);
                }else {
                    timeList.sort(null); //sort the time for learn next time
                    initialTime=timeList.get(0);
                    switchController(timeList.get(0).format(formatter));
                    removePastSwitchTimes(timeList.get(0).format(formatter));
                }
            }else {
                Main.writer("ERROR: Erroneous command!\n",true,outputLocation);
            }
        }catch (Exception e){
            Main.writer("ERROR: Erroneous command!\n",true,outputLocation);
        }
    }
    /**
     Removes a smart device from the device list and updates its status.
     @param Command a String containing the command to be executed, which should include the name of the device to be removed.
     */
    public void remove(String Command){
        String[] command = Command.split("\t");
        try {
            if(command.length==2){
                if(isDeviceInList(command[1])){
                    device dev = devices.get(command[1]);
                    dev.setStatus(false); //return the status while remove it
                    Main.writer("SUCCESS: Information about removed smart device is as follows:\n",true,outputLocation);
                    //learn the type of device and do necessary operations
                    if(dev instanceof Lamp){
                        Main.writer("Smart Lamp "+dev.getName()+" is "+stateConverter(dev.getStatus())+" and its kelvin value is "+((Lamp) dev).getKelvin()+"K with "+((Lamp) dev).getBrightness()+"% brightness, and its time to switch its status is "+isItNull(dev.getSwitchTime())+".\n",true,outputLocation);
                    } else if (dev instanceof Camera) {
                        storageCalculator(initialTime,command[1]);
                        Main.writer("Smart Camera "+dev.getName()+" is "+stateConverter(dev.getStatus())+" and used "+roundFraction(((Camera) dev).getTotalMegaByte())+" MB of storage so far (excluding current status), and its time to switch its status is "+isItNull(dev.getSwitchTime())+".\n",true,outputLocation);
                    } else if (dev instanceof plug) {
                        if(((plug) devices.get(command[1])).getPlugInOut()){
                            usageCalculator(initialTime,command[1]);
                        }
                        Main.writer("Smart Plug "+dev.getName()+" is "+stateConverter(dev.getStatus())+" and consumed "+roundFraction(((plug) dev).getTotalUsage())+"W so far (excluding current device), and its time to switch its status is "+isItNull(dev.getSwitchTime())+".\n",true,outputLocation);
                    } else if (dev instanceof ColourLamp) {
                        Main.writer("Smart Color Lamp " + dev.getName() + " is " + stateConverter(dev.getStatus()) + " and its color value is " + ((ColourLamp) dev).getKelvinOrColour() + " with " + ((ColourLamp) dev).getBrightness() + "% brightness, and its time to switch its status is " + isItNull(dev.getSwitchTime()) + ".\n", true,outputLocation);
                    }
                    deviceList.remove(getIndex(command[1]));
                }else {
                    Main.writer("ERROR: There is not such a device!\n",true,outputLocation);
                }
            }else {
                Main.writer("ERROR: Erroneous command!\n",true,outputLocation);
            }
        }catch (Exception e){
            Main.writer("ERROR: Erroneous command!\n",true,outputLocation);
        }
    }
    /**
     Sets the switch time for a device.
     @param Command a string containing the command in the format of "SetTime\t deviceName\t time".
     @throws IllegalArgumentException if the device name is not in the device list, time format is incorrect, or the specified time is before the current time.
     */
    public void setSwitchTime(String Command){ //Sets the switch time for a device.
        String[] command = Command.split("\t");
        try {
            if(command.length==3){
                if(isDeviceInList(command[1])){
                    if(timeConvertible(command[2])!=null){
                        if (initialTime.isBefore(timeConvertible(command[2]))) { // Check if the specified time is before the current time
                            devices.get(command[1]).setSwitchTime(timeConvertible(command[2])); // Set the switch time for the specified device
                            timeList.add(timeConvertible(command[2])); // Add the switch time to the time list
                            switchTimeList.put(command[1], devices.get(command[1]));  // Add the device to the switch time list
                            sort(); // Sort the time list in ascending order
                        } else if(initialTime.isEqual(timeConvertible(command[2]))) {// Check if the specified time is equal the current time
                            devices.get(command[1]).setSwitchTime(timeConvertible(command[2])); // Set the switch time for the specified device
                            sort(); // Sort the time list in ascending order
                            if(!devices.get(command[1]).getStatus()){  //switch the status
                                devices.get(command[1]).setStatus(true);
                                devices.get(command[1]).setSwitchTime(null);
                                sort();
                                if (devices.get(command[1]) instanceof Camera ) {//If the device is a camera, set its start time
                                    ((Camera) devices.get(command[1])).setStart(initialTime);
                                }else if(devices.get(command[1]) instanceof plug){//If the device is a plug, and it is plugged in, set its start time
                                    if(((plug) devices.get(command[1])).getPlugInOut()){
                                        ((plug) devices.get(command[1])).setStart(initialTime);
                                    }
                                }
                            }else {
                                devices.get(command[1]).setStatus(false);
                                devices.get(command[1]).setSwitchTime(null);
                                sort();
                                if (devices.get(command[1]) instanceof Camera ) {//If the device is a camera, calculate its storage usage
                                    storageCalculator(initialTime,command[1]);
                                }else if(devices.get(command[1]) instanceof plug){//If the device is a plug, and it is plugged in, calculate its usage
                                    if(((plug) devices.get(command[1])).getPlugInOut()){
                                        usageCalculator(initialTime,command[1]);
                                    }
                                }
                            }
                        }else {
                            Main.writer("ERROR: Switch time cannot be in the past!\n",true,outputLocation);
                        }
                    }else {
                        Main.writer("ERROR: Time format is not correct!\n",true,outputLocation);
                    }
                }else {
                    Main.writer("ERROR: There is not such a device!\n",true,outputLocation);
                }
            }else {
                Main.writer("ERROR: Erroneous command!\n",true,outputLocation);
            }
        }catch (Exception e){
            Main.writer("ERROR: Erroneous command!\n",true,outputLocation);
        }
    }
    /**
     Switches a device on or off and updates its status in the list of devices. If the device is a camera,
     its start time is set if it is switched on. If the device is a plug and is plugged in, its start time is set if it is switched on.
     If the device is switched off, the storage usage or usage is calculated depending on whether it is a camera or plug, respectively.
     @param Command a string representing the command to be executed, in the format "Switch\t<device name>\t<On/Off>"
     @throws IllegalArgumentException if the command is not in the correct format or the device name does not exist in the list of devices
     @return nothing is returned, but the status of the specified device is updated in the list of devices and, if applicable, its start time is set or its usage is calculated
     */
    public void Switch(String Command){
        String[] command = Command.split("\t");
        try {
            if(command.length==3){
                if(isDeviceInList(command[1])){
                    if(command[2].equals("On")){ //Check if the device is currently switched off
                        if(!devices.get(command[1]).getStatus()){
                            if(devices.get(command[1]).getSwitchTime()!=null){ //Check if the device has a switch time set, and remove it
                                devices.get(command[1]).setSwitchTime(null);
                            }
                            devices.get(command[1]).setStatus(true); //Switch the device on
                            sort();
                            if (devices.get(command[1]) instanceof Camera ) {//If the device is a camera, set its start time
                                ((Camera) devices.get(command[1])).setStart(initialTime);
                            }else if(devices.get(command[1]) instanceof plug){//If the device is a plug, and it is plugged in, set its start time
                                if(((plug) devices.get(command[1])).getPlugInOut()){
                                    ((plug) devices.get(command[1])).setStart(initialTime);
                                }
                            }
                        }else {
                            Main.writer("ERROR: This device is already switched on!\n",true,outputLocation);
                        }
                    }else if(command[2].equals("Off")){//Check if the device is currently switched on
                        if(devices.get(command[1]).getStatus()){//Check if the device has a switch time set, and remove it
                            if(devices.get(command[1]).getSwitchTime()!=null){
                                devices.get(command[1]).setSwitchTime(null);
                            }
                            devices.get(command[1]).setStatus(false);//Switch the device off
                            if (devices.get(command[1]) instanceof Camera ) {//If the device is a camera, calculate its storage usage
                                storageCalculator(initialTime,command[1]);
                            }else if(devices.get(command[1]) instanceof plug){//If the device is a plug, and it is plugged in, calculate its usage
                                if(((plug) devices.get(command[1])).getPlugInOut()){
                                    usageCalculator(initialTime,command[1]);
                                }
                            }
                            sort();//Sort the list of devices by their switch times
                        }else {
                            Main.writer("ERROR: This device is already switched off!\n",true,outputLocation);
                        }
                    }else {
                        Main.writer("ERROR: Erroneous command!\n",true,outputLocation);
                    }
                }else {
                    Main.writer("ERROR: There is not such a device!\n",true,outputLocation);
                }
            }else {
                Main.writer("ERROR: Erroneous command!\n",true,outputLocation);
            }
        }catch (Exception e){
            Main.writer("ERROR: Erroneous command!\n",true,outputLocation);
        }
    }
    /**
     * Changes the name of a smart device.
     * @param Command The command string containing the old and new names of the device, separated by a tab character.
     */
    public void changeName(String Command){
        String[] command = Command.split("\t");
        try {
            if (command.length==3){
                if(command[1].equals(command[2])){
                    Main.writer("ERROR: Both of the names are the same, nothing changed!\n",true,outputLocation);
                }else {
                    if(isDeviceInList(command[1])){ //if old name exist
                        if (!isDeviceInList(command[2])){ //if new name not exist
                            devices.get(command[1]).setName(command[2]); // change the name of the device
                            device value=devices.remove(command[1]); // remove the device from the map using the old name
                            devices.put(command[2],value); //replace the device
                            deviceList.set(deviceList.indexOf(command[1]), command[2]); //// update the device name in the list
                        }
                        else {
                            Main.writer("ERROR: There is already a smart device with same name!\n",true,outputLocation);
                        }
                    }else {
                        Main.writer("ERROR: There is not such a device!\n",true,outputLocation);
                    }
                }
            }else {
                Main.writer("ERROR: Erroneous command!\n",true,outputLocation);
            }
        }catch (Exception e){
            Main.writer("ERROR: Erroneous command!\n",true,outputLocation);
        }
    }
    /**
     * Plugs a device into a smart plug and sets the current flowing through it.
     * @param Command The command string containing the name of the smart plug and the current in amperes, separated by a tab character.
     */
    public void plugIn(String Command){
        String[] command = Command.split("\t");
        try {
            if (command.length==3){
                if(isDeviceInList(command[1])){
                    if(devices.get(command[1]) instanceof plug){ // Check if the device is a smart plug
                        if(Integer.parseInt(command[2])>0){ // Check if the ampere value is positive
                            if(!((plug) devices.get(command[1])).getPlugInOut()){ // Check if the plug is not already in use
                                if(devices.get(command[1]).getStatus()){
                                    ((plug) devices.get(command[1])).setStart(initialTime);
                                }// Set the ampere value and mark the plug as in use
                                ((plug) devices.get(command[1])).setAmpere(Double.parseDouble(command[2]));
                                ((plug) devices.get(command[1])).setPlugInOut(true);
                            }else {
                                Main.writer("ERROR: There is already an item plugged in to that plug!\n",true,outputLocation);
                            }
                        }else {
                            Main.writer("ERROR: Ampere value must be a positive number!\n",true,outputLocation);
                        }
                    }else {
                        Main.writer("ERROR: This device is not a smart plug!\n",true,outputLocation);
                    }
                }else {
                    Main.writer("ERROR: There is not such a device!\n",true,outputLocation);
                }
            }else {
                Main.writer("ERROR: Erroneous command!\n",true,outputLocation);
            }
        }catch (Exception e){
            Main.writer("ERROR: Erroneous command!\n",true,outputLocation);
        }
    }
    /**
     This method is used to unplug a device from a smart plug.
     If the device is a smart plug and it is currently in use, the method calculates its usage before unplugging it.
    */
     public void plugOut(String Command){
        String[] command = Command.split("\t");
         try {
             if (command.length==2){
                 if(isDeviceInList(command[1])){
                     if(devices.get(command[1]) instanceof plug){ // Check if the device is a smart plug
                         if(((plug) devices.get(command[1])).getPlugInOut()){ // Check if the plug is not already in use
                             if(devices.get(command[1]).getStatus()){ //If the device is a plug, and it is plugged in, calculate its usage
                                 usageCalculator(initialTime,command[1]);
                             }
                             ((plug) devices.get(command[1])).setPlugInOut(false);
                         }else {
                             Main.writer("ERROR: This plug has no item to plug out from that plug!\n",true,outputLocation);
                         }
                     }else {
                         Main.writer("ERROR: This device is not a smart plug!\n",true,outputLocation);
                     }
                 }else {
                     Main.writer("ERROR: There is not such a device!\n",true,outputLocation);
                 }
             }else {
                 Main.writer("ERROR: Erroneous command!\n",true,outputLocation);
             }
         }catch (Exception e){
             Main.writer("ERROR: Erroneous command!\n",true,outputLocation);
         }
    }
    /**
     * Sets the color temperature (in Kelvin) of a smart lamp.
     * @param Command The command string containing the name of the smart lamp and the color temperature in Kelvin, separated by a tab character.
     */
    public void setKelvin(String Command){ //Sets the color temperature (in Kelvin) of a lamp device.
        String[] command = Command.split("\t");
        try {
            if (command.length==3){
                if(isDeviceInList(command[1])){ // Check if the device name is valid and exists in the device list
                    if(devices.get(command[1]) instanceof Lamp || devices.get(command[1]) instanceof Lamp){ // Check if the device is a lamp
                        if(isInteger(command[2])){  // Check if the Kelvin value is an integer
                            if (2000<=Integer.parseInt(command[2]) && Integer.parseInt(command[2])<=6500){ // Check if the Kelvin value is within the valid range (2000K-6500K)
                                if(devices.get(command[1]) instanceof ColourLamp){ // If the device is a color lamp, reset its color code
                                    ((ColourLamp) devices.get(command[1])).setKelvin(Integer.parseInt(command[2]));
                                    ((ColourLamp) devices.get(command[1])).setColourCode(null);
                                }else {
                                    ((Lamp) devices.get(command[1])).setKelvin(Integer.parseInt(command[2]));  // Set the Kelvin value of the lamp device
                                }
                            }else{
                                Main.writer("ERROR: Kelvin value must be in range of 2000K-6500K!\n",true,outputLocation);
                            }
                        }else {
                            Main.writer("ERROR: Erroneous command!\n",true,outputLocation);
                        }
                    }else {
                        Main.writer("ERROR: This device is not a smart lamp!\n",true,outputLocation);
                    }
                }else {
                    Main.writer("ERROR: There is not such a device!\n",true,outputLocation);
                }
            }else {
                Main.writer("ERROR: Erroneous command!\n",true,outputLocation);
            }
        }catch (Exception e){
            Main.writer("ERROR: Erroneous command!\n",true,outputLocation);
        }
    }
    /**
     * Sets the brightness of a smart lamp device.
     *
     * @param Command The command string containing the device ID and brightness value, separated by tab.
     */
    public void setBrightness(String Command){
        String[] command = Command.split("\t");
        try {
            if (command.length==3){
                if(isDeviceInList(command[1])){
                    if(devices.get(command[1]) instanceof Lamp || devices.get(command[1]) instanceof ColourLamp){ // Check if the device is a smart lamp
                        if(isInteger(command[2])){ // Check if the third command parameter is an integer
                            if (0<=Integer.parseInt(command[2]) && Integer.parseInt(command[2])<=100){ // Check if the brightness value is within range
                                if(devices.get(command[1]) instanceof Lamp){
                                    ((Lamp) devices.get(command[1])).setBrightness(Integer.parseInt(command[2]));
                                } else {
                                    ((ColourLamp) devices.get(command[1])).setBrightness(Integer.parseInt(command[2]));
                                }
                            }else{
                                Main.writer("ERROR: Brightness must be in range of 0%-100%!\n",true,outputLocation);
                            }
                        }else {
                            Main.writer("ERROR: Erroneous command!\n",true,outputLocation);
                        }
                    }else {
                        Main.writer("ERROR: This device is not a smart lamp!\n",true,outputLocation);
                    }
                }else {
                    Main.writer("ERROR: There is not such a device!\n",true,outputLocation);
                }
            }else {
                Main.writer("ERROR: Erroneous command!\n",true,outputLocation);
            }
        }catch (Exception e){
            Main.writer("ERROR: Erroneous command!\n",true,outputLocation);
        }
    }
    /**
     * Sets the color code of a color lamp.
     *
     * @param Command The command to set the color code of a color lamp in the format "setColourCode {device name} {color code}".
     *
     * @throws IllegalArgumentException If the device name is invalid or not a color lamp, or if the color code is invalid.
     */
    public void setColourCode(String Command){
        String[] command = Command.split("\t");
        try {
            if (command.length==3){
                if(isDeviceInList(command[1])){
                    if(devices.get(command[1]) instanceof ColourLamp){
                        if(isInteger(command[2])){
                            String hex = command[2].substring(2);
                            if(0<=Integer.parseInt(hex,16) && Integer.parseInt(hex,16)<=16777215) {
                                ((ColourLamp) devices.get(command[1])).setColourCode(Integer.parseInt(hex,16));
                            }else {
                                Main.writer("ERROR: Color code value must be in range of 0x0-0xFFFFFF\n", true,outputLocation);
                            }
                        }else {
                            Main.writer("ERROR: Erroneous command!\n",true,outputLocation);
                        }
                    }else {
                        Main.writer("ERROR: This device is not a smart color lamp!\n",true,outputLocation);
                    }
                }else {
                    Main.writer("ERROR: There is not such a device!\n",true,outputLocation);
                }
            }else {
                Main.writer("ERROR: Erroneous command!\n",true,outputLocation);
            }
        }catch (Exception e){
            Main.writer("ERROR: Erroneous command!\n",true,outputLocation);
        }
    }
    /**
     * Sets the color temperature and brightness of a smart lamp with white light.
     * @param Command a String that contains the device name, color temperature in Kelvin, and brightness percentage, separated by tabs
     * @throws IllegalArgumentException if the Command parameter is missing or invalid
     * @throws IllegalArgumentException if the device is not a smart lamp with white light
     * @throws IllegalArgumentException if the color temperature or brightness values are out of range
     * @throws IllegalArgumentException if the device with the given name is not in the list of devices
     */
    public void setWhite(String Command){
        String[] command = Command.split("\t");
        try {
            if (command.length==4){
                if(isDeviceInList(command[1])){
                    if(devices.get(command[1]) instanceof Lamp || devices.get(command[1]) instanceof ColourLamp){
                        if(isInteger(command[2])&&isInteger(command[3])){
                            if (2000<=Integer.parseInt(command[2]) && Integer.parseInt(command[2])<=6500){
                                if (0<=Integer.parseInt(command[3]) && Integer.parseInt(command[3])<=100){
                                    if(devices.get(command[1]) instanceof Lamp){
                                        ((Lamp) devices.get(command[1])).setKelvin(Integer.parseInt(command[2]));
                                        ((Lamp) devices.get(command[1])).setBrightness(Integer.parseInt(command[3]));
                                    } else {
                                        ((ColourLamp) devices.get(command[1])).setKelvin(Integer.parseInt(command[2]));
                                        ((ColourLamp) devices.get(command[1])).setBrightness(Integer.parseInt(command[3]));
                                    }
                                }else{
                                    Main.writer("ERROR: Brightness must be in range of 0%-100%!\n",true,outputLocation);
                                }
                            }else{
                                Main.writer("ERROR: Kelvin value must be in range of 2000K-6500K!\n",true,outputLocation);
                            }
                        }else {
                            Main.writer("ERROR: Erroneous command!\n",true,outputLocation);
                        }
                    }else {
                        Main.writer("ERROR: This device is not a smart lamp!\n",true,outputLocation);
                    }
                }else {
                    Main.writer("ERROR: There is not such a device!\n",true,outputLocation);
                }
            }else {
                Main.writer("ERROR: Erroneous command!\n",true,outputLocation);
            }
        }catch (Exception e){
            Main.writer("ERROR: Erroneous command!\n",true,outputLocation);
        }
    }
    /**
     Sets the color and brightness of a smart color lamp device based on the parameters provided in the command string.
     The command string should be in the format "setColour\t<device_name>\t<color_code>\t<brightness>".
     The color code should be a hexadecimal value in the format "0xRRGGBB", where RR, GG, and BB represent the red, green, and blue color components respectively.
     The brightness parameter should be an integer between 0 and 100, representing the brightness percentage.
     @param Command the command string containing the device name, color code, and brightness.
     */
    public void setColour(String Command){
        String[] command = Command.split("\t");
        try {
            if (command.length==4){
                if(isDeviceInList(command[1])){
                    if(devices.get(command[1]) instanceof ColourLamp){
                        if(isHex(command[2])&&isInteger(command[3])){
                            String hex = command[2].substring(2);
                            if(0<=Integer.parseInt(hex,16) && Integer.parseInt(hex,16)<=16777215) {
                                if (0<=Integer.parseInt(command[3]) && Integer.parseInt(command[3])<=100){
                                    ((ColourLamp) devices.get(command[1])).setColourCode(Integer.parseInt(hex,16));
                                    ((ColourLamp) devices.get(command[1])).setBrightness(Integer.parseInt(command[3]));
                                }else{
                                    Main.writer("ERROR: Brightness must be in range of 0%-100%!\n", true,outputLocation);
                                }
                            }else {
                                Main.writer("ERROR: Color code value must be in range of 0x0-0xFFFFFF\n", true,outputLocation);
                            }
                        }else {
                            Main.writer("ERROR: Erroneous command!\n",true,outputLocation);
                        }
                    }else {
                        Main.writer("ERROR: This device is not a smart color lamp!\n",true,outputLocation);
                    }
                }else {
                    Main.writer("ERROR: There is not such a device!\n",true,outputLocation);
                }
            }else {
                Main.writer("ERROR: Erroneous command!\n",true,outputLocation);
            }
        }catch (Exception e){
            Main.writer("ERROR: Erroneous command!\n",true,outputLocation);
        }
    }
/**
 Prints a report of the current state of all devices in the system.
 The report includes the current time, status, and relevant details of each device.
 Devices are classified as lamps, cameras, plugs, or color lamps.
 @param Command a string command that contains no tab-separated arguments.
 */
    public void zReport(String Command) {
        String[] command = Command.split("\t");
        try {
            if (command.length == 1) {
                sort();
                Main.writer("Time is:\t"+initialTime.format(formatter)+"\n",true,outputLocation);
                for(String device : deviceList){
                    device dev = devices.get(device);
                    //learn the type of device and do necessary operations
                    if(dev instanceof Lamp){
                        Main.writer("Smart Lamp "+dev.getName()+" is "+stateConverter(dev.getStatus())+" and its kelvin value is "+((Lamp) dev).getKelvin()+"K with "+((Lamp) dev).getBrightness()+"% brightness, and its time to switch its status is "+isItNull(dev.getSwitchTime())+".\n",true,outputLocation);
                    } else if (dev instanceof Camera) {
                        Main.writer("Smart Camera "+dev.getName()+" is "+stateConverter(dev.getStatus())+" and used "+roundFraction(((Camera) dev).getTotalMegaByte())+" MB of storage so far (excluding current status), and its time to switch its status is "+isItNull(dev.getSwitchTime())+".\n",true,outputLocation);
                    } else if (dev instanceof plug) {
                        Main.writer("Smart Plug "+dev.getName()+" is "+stateConverter(dev.getStatus())+" and consumed "+roundFraction(((plug) dev).getTotalUsage())+"W so far (excluding current device), and its time to switch its status is "+isItNull(dev.getSwitchTime())+".\n",true,outputLocation);
                    } else if (dev instanceof ColourLamp)
                        Main.writer("Smart Color Lamp "+dev.getName()+" is "+stateConverter(dev.getStatus())+" and its color value is "+((ColourLamp) dev).getKelvinOrColour()+" with "+((ColourLamp) dev).getBrightness()+"% brightness, and its time to switch its status is "+isItNull(dev.getSwitchTime())+".\n",true,outputLocation);
                }
            } else {
                Main.writer("ERROR: Erroneous command!\n", true,outputLocation);
            }
        }catch (Exception e){
            Main.writer("ERROR: Erroneous command!\n",true,outputLocation);
        }
    }
}
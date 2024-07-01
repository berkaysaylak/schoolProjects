import java.time.LocalDateTime;
public class plug extends device{//a child class of device
    private double ampere; //ampere values of plug
    private LocalDateTime start;
    private LocalDateTime end;
    private boolean plugInOut; //for learn the is plugged
    private static final int defaultVoltage=220; // constant and unchangeable voltage value
    private double totalUsage; //total usage of ampere in specific time
    /**
     * Constructs a new Plug object with the specified name, state, ampere, and switch time.
     *
     * @param name the name of the plug
     * @param state the status of the plug (on/off)
     * @param ampere the amperage of the plug
     * @param switchTime the time it takes for the plug to switch on or off
     */
    public plug(String name, String state,String ampere, String switchTime) {
        //super for get values from parent class device
        super(name, state, switchTime);
        this.plugInOut=false;
        this.totalUsage=0;
        this.ampere= Double.parseDouble(ampere);
    }
    /**
     * Constructs a new Plug object with the specified name, state, and switch time.
     * @param name the name of the plug
     * @param state the status of the plug (on/off)
     * @param switchTime the time it takes for the plug to switch on or off
     */
    public plug(String name,String state, String switchTime) {
        //super for get values from parent class device
        super(name, state, switchTime);
        this.plugInOut=false; //default plug is out
        this.totalUsage=0;
        this.ampere=0;
    }
    //all getter and setter methods for this child class

    /**
     * @return the ampere value of the electric device.
     */
    public double getAmpere(){
        return ampere;
    }

    /**
     * @return the total usage of the electric device.
     */
    public double getTotalUsage(){
        return totalUsage;
    }

    /**
     * @return the default voltage of the electric device.
     */
    public int getDefaultVoltage(){
        return defaultVoltage;
    }

    /**
     * @return a Boolean value indicating whether the electric device is plugged in or not.
     */
    public Boolean getPlugInOut(){
        return plugInOut;
    }

    /**
     * @param ampere the new ampere value of the electric device.
     */
    public void setAmpere(double ampere){
        this.ampere=ampere;
    }

    /**
     * @param totalUsage the new total usage of the electric device.
     */
    public void setTotalUsage(double totalUsage){
        this.totalUsage=totalUsage;
    }

    /**
     * @param plugInOut a Boolean value indicating whether the electric device is plugged in or not.
     */
    public void setPlugInOut(Boolean plugInOut){
        this.plugInOut=plugInOut;
    }

    /**
     * param start the new start time of the electric device.
     */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /**
     * @return the start time of the electric device.
     */
    public LocalDateTime getStart() {
        return start;
    }
}

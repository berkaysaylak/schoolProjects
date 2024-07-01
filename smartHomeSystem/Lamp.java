public class Lamp extends device{//this class  is child class of device
    private int kelvin; //kelvin values of lamb
    private int brightness; // brightness value of lamb
    /**
     * Constructs a new Lamp object with the specified name, state, switch time, kelvin value, and brightness value.
     * @param name the name of the lamp
     * @param state the state of the lamp (on/off)
     * @param switchTime the time it takes for the lamp to switch on or off
     * @param kelvin the kelvin value of the lamp
     * @param brightness the brightness value of the lamp
     */
    public Lamp(String name, String state, String switchTime,String kelvin,String brightness) {
        //super for get values from parent class which name is device
        super(name, state, switchTime);
        //this part for default value of kelvin and brightness
        this.kelvin= Integer.parseInt(kelvin);
        this.brightness= Integer.parseInt(brightness);
    }
    /**
     * Constructs a new Lamp object with the specified name, state, switch time.
     * @param name the name of the lamp
     * @param state the state of the lamp (on/off)
     * @param switchTime the time it takes for the lamp to switch on or off
     */
    public Lamp(String name, String state, String switchTime) {
        //super for get values from parent class which name is device
        super(name, state, switchTime);
        //this part for default value of kelvin and brightness
        this.kelvin=4000;
        this.brightness=100;
    }
    //all getter and setter methods for this class

    /**
     * @return the temperature in Kelvin
     */
    public int getKelvin(){
        return kelvin;
    }

    /**
     * @param kelvin the temperature in Kelvin
     */
    public void setKelvin(int kelvin){
        this.kelvin=kelvin;
    }

    /**
     * @return the brightness level
     */
    public int getBrightness(){
        return brightness;
    }

    /**
     * @param brightness the brightness level
     */
    public void setBrightness(int brightness){
        this.brightness=brightness;
    }
}

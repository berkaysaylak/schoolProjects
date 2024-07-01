public class ColourLamp extends device{//this is child class of device
    private int kelvin; //kelvin values of lamb
    private int brightness; // brightness value of lamb
    private Integer colourCode; //it will return hex to int colour code
    /**
     * Constructs a new ColourLamp object with the specified name, state, switch time, kelvin value or color code, and brightness value.
     * @param name the name of the colour lamp
     * @param state the state of the colour lamp (on/off)
     * @param switchTime the time it takes for the colour lamp to switch on or off
     * @param kelvinOrColour the kelvin value or color code of the lamp. If the value starts with "0x", it will be interpreted as a color code in hexadecimal format.
     * @param brightness the brightness value of the lamp
     */
    public ColourLamp(String name, String state, String switchTime,String kelvinOrColour,String brightness) {
        //super code for get values from parent class lamb and device
        super(name, state, switchTime);
        if(kelvinOrColour.contains("0x")){
            String colour= kelvinOrColour.substring(2);
            this.colourCode = Integer.parseInt(colour, 16);
        }else{
            this.kelvin=Integer.parseInt(kelvinOrColour);
        }
        this.brightness= Integer.parseInt(brightness);
    }
    /**
     * Constructs a new ColourLamp object with the specified name, state, and switch time, with default values of kelvin, brightness, and color code.
     * @param name the name of the colour lamp
     * @param state the state of the colour lamp (on/off)
     * @param switchTime the time it takes for the colour lamp to switch on or off
     */
    public ColourLamp(String name, String state, String switchTime) {
        //super code for get values from parent class lamb and device
        super(name, state, switchTime);
        this.kelvin=4000;
        this.brightness=100;
        this.colourCode=null;
    }
    //all getter and setter methods for this child class

    /**
     *@return The current colour code value.
     */
    public Integer getColourCode() {
        return colourCode;
    }

    /**
     * @param colourCode value
     */
    public void setColourCode(Integer colourCode){
        this.colourCode=colourCode;
    }

    /**
     *@return The temperature in Kelvin
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
     *@return The brightness level
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

    /**
     Returns the color code if it is set or the temperature in Kelvin if it is not set.
     @return a string representation of the color code if set, or the temperature in Kelvin with "K" suffix if not set
     */
    public String getKelvinOrColour() {
        if(getColourCode()!=null){
            String colour = Integer.toHexString(colourCode);
            if(colour.length()<6){
                int difference = 6-colour.length();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < difference; i++) {
                    sb.append(0);
                }
                sb.append(colour);
                colour = sb.toString();
            }
            return "0x"+colour.toUpperCase();
        }else {
            return kelvin + "K";
        }
    }
}
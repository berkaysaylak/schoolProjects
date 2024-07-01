import java.time.LocalDateTime;
public class Camera extends device{//a child class of device
    private double megaByte; //storage per second
    private double totalMegaByte; //total storage until switch off
    private LocalDateTime start;
    private LocalDateTime end;
    /**
     * Constructs a new Camera object with the specified name, state, switch time, and mega-byte capacity.
     * @param name the name of the camera
     * @param state the state of the camera (on/off)
     * @param switchTime the time it takes for the camera to switch on or off
     * @param megaByte the megaByte capacity of the camera
     */
    public Camera(String name, String state, String switchTime,String megaByte) {
        //super for get values from parent class
        super(name, state,switchTime);
        this.totalMegaByte=0;
        this.megaByte= Double.parseDouble(megaByte);
    }
    //all getter and setter methods for child class

    /**
     * @return the amount of megabytes for this instance.
     */
    public double getMegaByte(){
        return megaByte;
    }

    /**
     * @return the total amount of megabytes for this instance.
     */
    public double getTotalMegaByte(){
        return totalMegaByte;
    }

    /**
     * @param totalMegaByte the total amount of megabytes to set for this instance.
     */
    public void setTotalMegaByte(double totalMegaByte){
        this.totalMegaByte=totalMegaByte;
    }

    /**
     * @param start the start date and time to set for this instance.
     */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /**
     * @return the start date and time for this instance.
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * @return the end date and time for this instance.
     */
    public LocalDateTime getEnd() {
        return end;
    }
}

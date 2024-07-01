import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
//parent class for all smart devices
public abstract class device {
    protected String name; //name of all devices
    protected boolean status; // status of all devices
    protected LocalDateTime switchTime;
    public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");
    /**
     * Constructs a new device object with the specified name, state, and switch time.
     * @param name the name of the device
     * @param state the state of the device (On/Off)
     * @param switchTime the time it takes for the device to switch on or off. If null, the switch time is undefined.
     */
    public device(String name ,String state,String switchTime){
        //if-else for learn state of device
        if (state.equals("On")){
            this.status=true;
        }else if(state.equals("Off")){
            this.status=false;
        }
        this.name=name;
        //for translate string time to date
        if(switchTime!=null){
            this.switchTime = LocalDateTime.parse(switchTime,formatter);;
        }else {
            this.switchTime=null;
        }
    }
    public device(String name ,String state){
        //if-else for learn state of device
        if (state.equals("On")){
            this.status=true;
        }else if (state.equals("Off")){
            this.status=false;
        }
        this.name=name;
        this.switchTime=null;
    }
    public device(String name){
        this.status=false;
        this.name=name;
        this.switchTime=null;
    }
    //all getter and setter methods of variables

    /**
     @return the name of the device
     */
    public String getName(){
        return name;
    }

    /**
     @param the name of the device
     */
    public void setName(String deviceName){
        this.name = deviceName;
    }
    /**
     @return  the status of the device
     */
    public boolean getStatus(){
        return status;
    }

    /**
     @param the status of the device
     */
    public void setStatus(Boolean state){
        this.status = state;
    }

    /**
     @return the local-datetime of the device
     */
    public LocalDateTime getSwitchTime(){
        return switchTime;
    }

    /**
     @param the local-datetime of the device
     */
    public void setSwitchTime(LocalDateTime switchTime){
        this.switchTime=switchTime;
    }
}

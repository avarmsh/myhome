public class Thermostat extends SmartDevice {
    private int temperature = 20;

    public Thermostat(String name, String protocol) {
        super(name, protocol);
    }

    public boolean setProperty(String property, String value) {
        if(property.equals("status")){
            if(value.equals("on") || value.equals("off")){
                status = value;
                return true;
            }
        } else if(property.equals("temperature")){
            try{
                int t = Integer.parseInt(value);
                if(t >= 10 && t <= 30){
                    temperature = t;
                    return true;
                }
            } catch(NumberFormatException e){
            }
        }
        return false;
    }

    public String info() {
        return name + " " + status + " " + temperature + "C " + protocol;
    }
}

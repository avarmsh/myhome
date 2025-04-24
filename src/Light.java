public class Light extends SmartDevice {
    private int brightness = 50;

    public Light(String name, String protocol) {
        super(name, protocol);
    }

    public boolean setProperty(String property, String value) {
        if(property.equals("status")){
            if(value.equals("on") || value.equals("off")){
                status = value;
                return true;
            }
        } else if(property.equals("brightness")){
            try{
                int b = Integer.parseInt(value);
                if(b >= 0 && b <= 100){
                    brightness = b;
                    return true;
                }
            } catch(NumberFormatException e){
            }
        }
        return false;
    }

    public String info() {
        return name + " " + status + " " + brightness + "% " + protocol;
    }
}

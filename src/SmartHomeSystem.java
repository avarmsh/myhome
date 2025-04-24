import java.util.ArrayList;
import java.util.List;

public class SmartHomeSystem {
    private List<SmartDevice> devices = new ArrayList<>();
    private List<Rule> rules = new ArrayList<>();

    public String handleCommand(String command) {
        String[] parts = command.split(" ");
        if(parts[0].equals("add_device")){
            if(parts.length != 4)
                return "invalid input";
            return addDevice(parts[1], parts[2], parts[3]);
        } else if(parts[0].equals("set_device")){
            if(parts.length != 4)
                return "invalid input";
            return setDevice(parts[1], parts[2], parts[3]);
        } else if(parts[0].equals("remove_device")){
            if(parts.length != 2)
                return "invalid input";
            return removeDevice(parts[1]);
        } else if(parts[0].equals("list_devices")){
            return listDevices();
        } else if(parts[0].equals("add_rule")){
            if (parts.length != 4)
                return "invalid input";
            return addRule(parts[1], parts[2], parts[3]);
        } else if(parts[0].equals("check_rules")){
            if (parts.length != 2)
                return "invalid input";
            return checkRules(parts[1]);
        } else if(parts[0].equals("list_rules")){
            return listRules();
        }
        return "invalid input";
    }

    private String addDevice(String type, String name, String protocol) {
        if(getDevice(name) != null)
            return "duplicate device name";
        if(!protocol.equals("WiFi") && !protocol.equals("Bluetooth"))
            return "invalid input";
        if(type.equals("light"))
            devices.add(new Light(name, protocol));
        else if(type.equals("thermostat"))
            devices.add(new Thermostat(name, protocol));
        else
            return "invalid input";

        return "device added successfully";
    }

    private String setDevice(String name, String property, String value) {
        SmartDevice device = getDevice(name);
        if(device == null)
            return "device not found";
        if(device.setProperty(property, value))
            return "device updated successfully";
        try{
            Integer.parseInt(value);
            return "invalid property";
        } catch(Exception e){
            return "invalid value";
        }
    }

    private String removeDevice(String name) {
        SmartDevice d = getDevice(name);
        if(d == null)
            return "device not found";
        devices.remove(d);
        rules.removeIf(r -> r.deviceName.equals(name));
        return "device removed successfully";
    }

    private String listDevices() {
        if(devices.isEmpty())
            return "";
        StringBuilder sb = new StringBuilder();
        for(SmartDevice d : devices)
            sb.append(d.info()).append("\n");
        return sb.toString().trim();
    }

    private String addRule(String name, String time, String action) {
        if(getDevice(name) == null)
            return "device not found";
        if(!time.matches("\\d{2}:\\d{2}"))
            return "invalid time";
        if(!action.equals("on") && !action.equals("off"))
            return "invalid action";
        for(Rule r : rules){
            if (r.deviceName.equals(name) && r.time.equals(time))
                return "duplicate rule";
        }
        rules.add(new Rule(name, time, action));
        return "rule added successfully";
    }

    private String checkRules(String time) {
        if(!time.matches("\\d{2}:\\d{2}"))
            return "invalid time";
        for(Rule r : rules){
            if(r.time.equals(time)){
                SmartDevice d = getDevice(r.deviceName);
                if(d != null)
                    d.setStatus(r.action);
            }
        }
        return "rules checked";
    }

    private String listRules() {
        if(rules.isEmpty())
            return "";
        StringBuilder sb = new StringBuilder();
        for(Rule r : rules)
            sb.append(r).append("\n");
        return sb.toString().trim();
    }

    private SmartDevice getDevice(String name) {
        for (SmartDevice d : devices){
            if (d.getName().equals(name))
                return d;
        }
        return null;
    }
}

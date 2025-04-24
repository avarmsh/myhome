public abstract class SmartDevice {
    protected String name;
    protected String protocol;
    protected String status = "off";

    public SmartDevice(String name, String protocol) {
        this.name = name;
        this.protocol = protocol;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProtocol() {
        return protocol;
    }

    public abstract boolean setProperty(String property, String value);

    public abstract String info();
}

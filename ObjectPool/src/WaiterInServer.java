public class WaiterInServer {
    private String nameWaiter;

    public String getNameWaiter() {
        return nameWaiter;
    }

    public void setNameWaiter(String nameWaiter) {
        this.nameWaiter = nameWaiter;
    }

    public WaiterInServer(String nameWaiter) {
        super();
        this.nameWaiter = nameWaiter;
    }

    @Override
    public String toString() {
        return "WaiterInServer{" +
                "nameWaiter='" + nameWaiter + '\'' +
                '}';
    }
}
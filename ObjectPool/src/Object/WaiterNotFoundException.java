package Object;

public class WaiterNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -3634435435443L;
    public WaiterNotFoundException(String message) {
        System.out.println(message);
    }

}
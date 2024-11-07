public class AppOrderDrinkfromHighLand {
    public static final int NUM_OF_CLIENT = 8;                                                    // số lượng client gọi món
    public static void main ( String[] args) {
        WaiterPool waiterPool = new WaiterPool();
        String[] clientNames = {
                "Nguyễn Văn A", "Nguyễn Trung", "Văn B", "Ngọc C", "Minh D",
                "Mai E", "Lan F", "Hoàng G"
        };
        for (int i = 0; i < NUM_OF_CLIENT ; i++) {                                                // khởi động nhiều luồng (threads), mỗi luồng đại diện cho một "client"
            String clientName = clientNames[i];
            Runnable client = new ClientThread(waiterPool, clientName);
            Thread thread = new Thread(client);
            thread.start();
        }
    }
}
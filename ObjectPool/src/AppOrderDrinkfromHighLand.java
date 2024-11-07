public class AppOrderDrinkfromHighLand {
    public static final int NUM_OF_CLIENT = 8;                                                    // số lượng client gọi món
    public static void main ( String[] args) {
        WaiterPool waiterPool = new WaiterPool();
        String[] clientNames = {
                "Nguyễn Văn A", "Nguyễn Trung", "Văn B", "Ngọc C", "Minh D",
                "Mai E", "Lan F", "Hoàng G"
        };
        String[] drinkNames = {
               "Phin Sữa Đá", "Phin Sữa Nóng" , "Capuchino", "Cookie & Cream", "Capuchino", "Latte", "Tắc Đá Viên" , "Caramel phin Freeze"
        };

        String[] moneyNames = {
              "29.000" , "29.000", "99.000" , "69.000" ,"99.000" ,"23.000" ,"43.0000" , "24.0000"};


        for (int i = 0; i < NUM_OF_CLIENT ; i++) {                                                // khởi động nhiều luồng (threads), mỗi luồng đại diện cho một "client"
            String clientName = clientNames[i];
            String drinkName = drinkNames[i];
            String moneyName = moneyNames[i];
            Runnable client = new ClientThread(waiterPool, clientName, drinkName, moneyName);
            Thread thread = new Thread(client);
            thread.start();
        }
    }
}
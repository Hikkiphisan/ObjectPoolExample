import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AppOrderDrinkfromHighLand {
    public static final int NUM_OF_CLIENT = 8;                                                    // số lượng client gọi món
    public static void main ( String[] args) {
        WaiterPool waiterPool = new WaiterPool();

        // Đọc thông tin từ file
        List<String> clientNames = new ArrayList<>();
        List<String> drinkNames = new ArrayList<>();
        List<String> moneyNames = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("D:\\CodeGym\\Module 2\\ObjectPoolExample\\ObjectPool\\src\\clients.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Tách thông tin từ mỗi dòng
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    clientNames.add(parts[0].trim());
                    drinkNames.add(parts[1].trim());
                    moneyNames.add(parts[2].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


       // Đảm bảo số lượng khách hàng đúng với NUM_OF_CLIENT
        if (clientNames.size() != NUM_OF_CLIENT || drinkNames.size() != NUM_OF_CLIENT || moneyNames.size() != NUM_OF_CLIENT) {
            System.out.println("Số lượng khách hàng, đồ uống hoặc giá tiền không khớp với NUM_OF_CLIENT.");
            return;
        }



        // Khởi động nhiều luồng (threads), mỗi luồng đại diện cho một "client"
        for (int i = 0; i < NUM_OF_CLIENT ; i++) {                                                // khởi động nhiều luồng (threads), mỗi luồng đại diện cho một "client"
            String clientName = clientNames.get(i);
            String drinkName = drinkNames.get(i);
            String moneyName = moneyNames.get(i);
            Runnable client = new ClientThread(waiterPool, clientName, drinkName, moneyName);
            Thread thread = new Thread(client);
            thread.start();
        }
    }
}










/*
        String[] clientNames = {
                "Nguyễn Văn A", "Nguyễn Trung", "Văn B", "Ngọc C", "Minh D",
                "Mai E", "Lan F", "Hoàng G"
        };
        String[] drinkNames = {
               "Phin Sữa Đá", "Phin Sữa Nóng" , "Capuchino", "Cookie & Cream", "Capuchino", "Latte", "Tắc Đá Viên" , "Caramel phin Freeze"
        };

        String[] moneyNames = {
              "29.000" , "29.000", "99.000" , "69.000" ,"99.000" ,"23.000" ,"43.0000" , "24.0000"};
*/


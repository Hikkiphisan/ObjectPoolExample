import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AppOrderDrinkfromHighLand {
    public static final int NUM_OF_CLIENT = 8;                                                 // số lượng client tối đa được vào cửa tiệm để gọi món
    public static void main ( String[] args) {
        WaiterPool waiterPool = new WaiterPool();

        // Đọc thông tin từ file
        List<String> clientNames = new ArrayList<>();
        List<String> drinkNames = new ArrayList<>();
        List<String> moneyNames = new ArrayList<>();



        //Để đọc đơn gọi món.
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




        // Tạo hóa đơn riêng lẻ cho từng khách hàng
        for (int i = 0; i < NUM_OF_CLIENT; i++) {
            String clientName = clientNames.get(i);
            String drinkName = drinkNames.get(i);
            String money = moneyNames.get(i);

            // Tên file hóa đơn cho từng khách hàng
            String fileName = String.format("D:\\CodeGym\\Module 2\\ObjectPoolExample\\ObjectPool\\src\\Hoá đơn của vị khách %s.txt", clientName);

            // Ghi thông tin vào file hóa đơn
            try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
                writer.printf("%n%-30s%-20s%n", "========== HÓA ĐƠN KHÁCH HÀNG GỌI ĐỒ HIGHLAND NGÀY 11/8/2024 ==========", "");
                writer.printf("| %-30s | %-30s |%n", "Tên khách hàng", clientName);
                writer.printf("| %-30s | %-30s |%n", "Đồ uống", drinkName);
                writer.printf("| %-30s | %-30s |%n", "Giá tiền", money);
                writer.printf("%-30s%-20s%n", "=======================================================", "");
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Tạo và chạy thread cho từng khách hàng

            Runnable client = new ClientThread(waiterPool, clientName, drinkName, money);
            Thread thread = new Thread(client);
            thread.start();
        }
    }


    }







//Idea: tiếp theo là ghi file, hãy xuat hoa dơn để kèm theo những thong tin thưa, ưng dung regex
//IDea: ứng dung regex đẻ xoá bỏ những thông tin thừa khi đọc file txt, chi lay nhung thong tin can thiet


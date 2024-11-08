package view;

import model.ClientThread;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Object.WaiterPool;

public class AppOrderDrinkfromHighLand {
    public static final int NUM_OF_CLIENT = 5;                                                 // số lượng client tối đa được vào cửa tiệm để gọi món
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
                // Sử dụng regex phương thức cleanData để delete ký tự thừa
                String cleanLine = cleanData(line);

                if (!cleanLine.isEmpty()) {
                    // Tách thông tin từ mỗi dòng
                    String[] parts = line.split(",");
                    if (parts.length == 3 || validateData(parts[0], parts[1], parts[2])) {  // Điều kiện validate này có vấn đề gây lỗi
                        clientNames.add(parts[0].trim());
                        drinkNames.add(parts[1].trim());
                        moneyNames.add(parts[2].trim());
                    }
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





    public static String cleanData(String input) {
        // Regex để tìm các dòng có cấu trúc: "Tên khách hàng, Loại đồ uống, Giá tiền"
        Pattern pattern = Pattern.compile("([\\p{L} ]+),\\s*([\\p{L} &]+),\\s*(\\d+\\.\\d+)");
        Matcher matcher = pattern.matcher(input);

        StringBuilder cleanedData = new StringBuilder();
        while (matcher.find()) {
            // Ghép nối tên khách hàng, đồ uống, giá tiền vào kết quả
            cleanedData.append(matcher.group(1)).append(", ")
                    .append(matcher.group(2)).append(", ")
                    .append(matcher.group(3)).append("\n");
        }
        return cleanedData.toString();
    }






    // Yêu cau thu 5: Vận dụng regex để validate dữ liệu
    public static boolean validateData(String clientName, String drinkName, String money) {
        // Validate tên khách hàng: Chỉ chứa chữ và khoảng trắng
        String clientNamePattern = "^[\\p{L} ]+$"; // Chỉ chứa chữ và khoảng trắng
        Pattern clientPattern = Pattern.compile(clientNamePattern);
        Matcher clientMatcher = clientPattern.matcher(clientName);

        // Validate đồ uống: Chỉ chứa chữ, khoảng trắng và dấu "&"
        String drinkNamePattern = "^[\\p{L} &]+$"; // Chỉ chứa chữ, khoảng trắng và dấu "&"
        Pattern drinkPattern = Pattern.compile(drinkNamePattern);
        Matcher drinkMatcher = drinkPattern.matcher(drinkName);

        // Validate giá tiền: Kiểm tra định dạng số thực với một hoặc hai chữ số sau dấu chấm
        String moneyPattern = "^\\d+\\.\\d{2}$"; // Định dạng số thực với hai chữ số sau dấu chấm
        Pattern moneyPatternObj = Pattern.compile(moneyPattern);
        Matcher moneyMatcher = moneyPatternObj.matcher(money);

        // Kiểm tra tất cả các điều kiện
        return clientMatcher.matches() && drinkMatcher.matches() && moneyMatcher.matches();
    }


    }


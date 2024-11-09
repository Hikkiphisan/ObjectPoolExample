package view;

import model.ClientThread;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import Object.WaiterPool;
import Object.ValidateData;
import Object.CleanDatafromFileTxt;
import Object.ReadOrderRequestsDataFromFileTxt;

public class AppOrderDrinkfromCodeGym {
    public static final int NUM_OF_CLIENT = 5;                                                 // số lượng client tối đa được vào cửa tiệm để gọi món
    public static void main ( String[] args) {
        WaiterPool waiterPool = new WaiterPool();

        // Đọc thông tin từ file
        List<String> clientNames = new ArrayList<>();
        List<String> drinkNames = new ArrayList<>();
        List<String> moneyNames = new ArrayList<>();


        //Để đọc đơn gọi món.
        ReadOrderRequestsDataFromFileTxt.readOrderRequestsDataFromFileTxt(clientNames,drinkNames,moneyNames);


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


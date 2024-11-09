package view;

import model.ClientThread;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import Object.WaiterPool;
import Object.ValidateData;
import Object.CleanDatafromFileTxt;
import Object.ReadOrderRequestsDataFromFileTxt;
import Object.ProcessOrderForClient;

public class AppOrderDrinkfromCodeGym {
    public static final int NUM_OF_CLIENT = 5;                                                 // số lượng client tối đa được vào cửa tiệm để gọi món
    public static void main ( String[] args) {

        // Đọc thông tin từ file
        List<String> clientNames = new ArrayList<>();
        List<String> drinkNames = new ArrayList<>();
        List<String> moneyNames = new ArrayList<>();

        //Để đọc đơn gọi món.
        ReadOrderRequestsDataFromFileTxt.readOrderRequestsDataFromFileTxt(clientNames,drinkNames,moneyNames);


        // Tạo hóa đơn riêng lẻ cho từng khách hàng va ghi thong tin vao hoa don, va chay luong
        ProcessOrderForClient.processOrderForClient(clientNames,drinkNames,moneyNames);



    }


    }


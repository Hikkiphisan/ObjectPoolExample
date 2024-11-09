package Object;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ReadOrderRequestsDataFromFileTxt {
    public static void readOrderRequestsDataFromFileTxt(List<String> clientNames, List<String> drinkNames, List<String> moneyNames)
    {
        //Để đọc đơn gọi món.
        try (BufferedReader br = new BufferedReader(new FileReader("D:\\CodeGym\\Module 2\\ObjectPoolExample\\ObjectPool\\src\\clients.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Sử dụng regex phương thức cleanData để delete ký tự thừa
                CleanDatafromFileTxt cleanDatafromFileTxt = new CleanDatafromFileTxt();
                String cleanLine = cleanDatafromFileTxt.cleanData(line);

                if (!cleanLine.isEmpty()) {
                    ValidateData validate = new ValidateData();
                    // Tách thông tin từ mỗi dòng
                    String[] parts = line.split(",");
                    if (parts.length == 3 || validate.validateData(parts[0], parts[1], parts[2])) {  // Điều kiện validate này có vấn đề gây lỗi
                        clientNames.add(parts[0].trim());
                        drinkNames.add(parts[1].trim());
                        moneyNames.add(parts[2].trim());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}

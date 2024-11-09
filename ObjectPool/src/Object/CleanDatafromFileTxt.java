package Object;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CleanDatafromFileTxt {
    public CleanDatafromFileTxt() {};

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
}

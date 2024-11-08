package Object;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateData {

    public ValidateData() {};

    // Yêu cau thu 5: Vận dụng regex để validate dữ liệu (problem!!)
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

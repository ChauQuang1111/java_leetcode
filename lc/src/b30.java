
// 1317. Convert Integer to the Sum of Two No-Zero Integers(08/09/2025)
import java.util.*;

public class b30 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();
        int[] result = getNoZeroIntegers(n);

        // Kiểm tra và in kết quả ra màn hình
        if (result != null) {

            System.out.println(Arrays.toString(result));
        } else {
            System.out.println("Không tìm thấy cặp số nào.");
        }

        // Đóng Scanner
        sc.close();

    }

    public static int[] getNoZeroIntegers(int n) {
        // Vòng lặp từ 1 đến n-1 để tìm số A
        for (int i = 1; i < n; i++) {
            int A = i;
            int B = n - i;

            // Kiểm tra xem cả A và B có phải là "số không chứa số 0" không
            if (isNoZero(A) && isNoZero(B)) {
                // Nếu cả hai đều thỏa mãn, trả về cặp số đó
                return new int[] { A, B };
            }
        }
        return null; // Trường hợp này sẽ không bao giờ xảy ra theo đề bài
    }

    // Hàm phụ trợ để kiểm tra một số có chứa chữ số 0 hay không
    public static boolean isNoZero(int num) {
        // Duyệt từng chữ số của số
        while (num > 0) {
            int digit = num % 10; // Lấy chữ số cuối cùng
            if (digit == 0) {
                return false; // Có chữ số 0
            }
            num /= 10; // Bỏ chữ số cuối cùng và tiếp tục
        }
        return true; // Không có chữ số 0 nào
    }
}

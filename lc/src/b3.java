
// 13/08/2025
// 342. Power of Four +// 326. Power of Three
import java.util.*;

public class b3 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();
        boolean res = isPowerOfFour(n);
        boolean res1 = isPowerOfThree(n);
        System.out.println(res);
        System.out.println(res1);
    }

    public static boolean isPowerOfFour(int n) {
        if (n <= 0)
            return false;
        while (n % 4 == 0)
            n /= 4;
        if (n == 1) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isPowerOfThree(int n) {
        if (n <= 0)
            return false;
        while (n % 3 == 0)
            n /= 3;
        if (n == 1) {
            return true;
        } else {
            return false;
        }
    }
}
// // Giai thich thuat toan :Câu lệnh while (n % 4 == 0) { n /= 4; } là phần cốt lõi của thuật toán, dùng để kiểm tra xem một số có phải là lũy thừa của 4 hay không.

// Giải thích chi tiết
// Đây là một vòng lặp while sẽ tiếp tục chạy miễn là điều kiện n % 4 == 0 còn đúng.

// n % 4 == 0: Điều kiện này kiểm tra xem n có chia hết cho 4 không.

// n /= 4: Nếu điều kiện trên đúng (tức là n chia hết cho 4), thì câu lệnh này sẽ chia n cho 4 và gán kết quả trở lại cho n.

// Mục đích của vòng lặp này là loại bỏ tất cả các thừa số 4 của n.

// Ví dụ với n = 64 (là lũy thừa của 4)
// Lần 1: n = 64.

// 64 % 4 == 0 (Đúng).

// n trở thành 64 / 4 = 16.

// Lần 2: n = 16.

// 16 % 4 == 0 (Đúng).

// n trở thành 16 / 4 = 4.

// Lần 3: n = 4.

// 4 % 4 == 0 (Đúng).

// n trở thành 4 / 4 = 1.

// Lần 4: n = 1.

// 1 % 4 == 0 (Sai). Vòng lặp kết thúc.

// Sau khi vòng lặp kết thúc, giá trị của n là 1. Điều này chứng tỏ 64 ban đầu chỉ bao gồm các thừa số 4.

// Ví dụ với n = 12 (không phải là lũy thừa của 4)
// Lần 1: n = 12.

// 12 % 4 == 0 (Đúng).

// n trở thành 12 / 4 = 3.

// Lần 2: n = 3.

// 3 % 4 == 0 (Sai). Vòng lặp kết thúc.

// Sau khi vòng lặp kết thúc, giá trị của n là 3 (một số khác 1). Điều này cho thấy 12 không phải là lũy thừa của 4 vì nó vẫn còn một thừa số 3.

// Tóm lại, vòng lặp này là một cách nhanh chóng để "bóc tách" tất cả các thừa số 4 của một số. Nếu kết quả cuối cùng là 1, thì số ban đầu là lũy thừa của 4.

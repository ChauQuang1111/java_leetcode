
// 1277. Count Square Submatrices with All Ones (20/08/2025)
import java.util.Scanner;

public class b11 {

    public static int countSquares(int[][] matrix) {
        int n = matrix.length;
        if (n == 0) {
            return 0;
        }
        int m = matrix[0].length;

        int[][] dp = new int[n][m];
        int ans = 0;

        // Xử lý hàng và cột đầu tiên
        for (int i = 0; i < n; i++) {
            dp[i][0] = matrix[i][0];
            ans += dp[i][0];
        }
        for (int j = 1; j < m; j++) {
            dp[0][j] = matrix[0][j];
            ans += dp[0][j];
        }

        // Trừ đi trường hợp trùng lặp tại (0,0)
        // Khi cộng ans ở hai vòng lặp trên, dp[0][0] đã bị cộng 2 lần nếu matrix[0][0]
        // == 1.
        if (matrix[0][0] == 1) {
            ans--;
        }

        // Lấp đầy các giá trị còn lại của bảng DP
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] == 1) {
                    dp[i][j] = 1 + Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]);
                } else {
                    dp[i][j] = 0;
                }
                ans += dp[i][j];
            }
        }

        return ans;
    }

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.printf("matrix[%d][%d]: ", i, j);
                matrix[i][j] = scanner.nextInt();
            }
        }

        // Tạo đối tượng Solution để gọi phương thức countSquares
        int ans = countSquares(matrix);

        System.out.println(ans);

        scanner.close();
    }
}

// Đoạn code bạn cung cấp là một giải pháp sử dụng lập trình

// động (Dynamic Programming)** để giải quyết bài toán đếm tổng số hình vuông
// con (submatrices) có chứa toàn số 1 trong một ma trận nhị phân.

// ### Ý tưởng cốt lõi

// Thuật toán tạo một ma trận phụ **`dp`** có cùng kích thước với ma trận đầu
// vào. Mỗi ô `dp[i][j]` lưu trữ **độ dài cạnh của hình vuông lớn nhất** có góc
// dưới cùng bên phải tại vị trí `(i, j)`.

// ### Cách thuật toán hoạt động

// 1. **Khởi tạo**:
// * Thuật toán khởi tạo một ma trận `dp` cùng kích thước với `matrix`.
// * Biến `ans` (viết tắt của "answer") được khởi tạo bằng `0` để đếm tổng số
// hình vuông.

// 2. **Xử lý hàng và cột đầu tiên**:
// * Đối với bất kỳ ô nào ở hàng hoặc cột đầu tiên, nếu giá trị `matrix[i][j]`
// là `1`, thì hình vuông lớn nhất có thể tạo ra tại đây chỉ có cạnh bằng `1`.
// * Vì vậy, `dp[i][0]` và `dp[0][j]` được gán trực tiếp bằng giá trị tương ứng
// của `matrix[i][0]` và `matrix[0][j]`.
// * Giá trị này cũng được cộng vào `ans`, vì mỗi giá trị `1` ở hàng/cột đầu
// tiên tương ứng với một hình vuông cạnh `1`.

// 3. **Xử lý trường hợp trùng lặp**:
// * Sau khi cộng tổng cho cả hàng đầu tiên và cột đầu tiên, ô `matrix[0][0]` sẽ
// bị cộng vào `ans`
// hai lần (một lần trong vòng lặp cho hàng và một lần trong vòng lặp cho cột),
// nếu giá trị của nó là `1`.
// * Dòng code `if (matrix[0][0] == 1) { ans--; }` dùng để xử lý vấn đề này, trừ
// đi một lần đếm thừa.

// 4. **Lặp qua phần còn lại của ma trận**:
// * Thuật toán duyệt qua ma trận từ ô `(1, 1)` đến ô cuối cùng.
// * Tại mỗi ô `matrix[i][j]`:
// * **Nếu `matrix[i][j] == 0`**: Không thể tạo hình vuông nào kết thúc tại ô
// này, nên `dp[i][j]` là `0`.
// * **Nếu `matrix[i][j] == 1`**: Đây là trường hợp chính. Một hình vuông có góc
// tại `(i, j)` và cạnh `k` phải có các ô lân cận phía trên, bên trái và chéo
// trên-trái cũng là `1`.
// * Do đó, `dp[i][j]` sẽ bằng `1` cộng với giá trị **nhỏ nhất** trong ba ô
// `dp[i-1][j]` (trên), `dp[i][j-1]` (trái), và `dp[i-1][j-1]` (chéo trên-trái).
// * Công thức: `dp[i][j] = 1 + min(dp[i][j-1], dp[i-1][j], dp[i-1][j-1])`.

// 5. **Tính tổng kết quả**:
// * Mỗi khi tính được `dp[i][j]`, giá trị này cũng chính là số hình vuông có
// góc tại `(i, j)`. Ví dụ, nếu `dp[i][j]` là `3`, có nghĩa là có một hình vuông
// cạnh `3`, một hình vuông cạnh `2`, và một hình vuông cạnh `1`, tất cả đều kết
// thúc tại ô `(i, j)`.
// * Do đó, giá trị `dp[i][j]` được cộng vào biến `ans` sau mỗi lần tính toán.

// 6. **Trả về kết quả**:
// * Sau khi duyệt toàn bộ ma trận, biến `ans` sẽ chứa tổng số hình vuông con có
// toàn số `1`.

// ---

// Thuật toán này rất hiệu quả với độ phức tạp thời gian `O(n * m)` và độ phức
// tạp không gian `O(n * m)`. Đây là một cách tiếp cận cổ điển và hiệu quả để
// giải quyết các bài toán liên quan đến ma trận và lập trình động.

// 837. New 21 Game (17/08/2025)
import java.util.*;

public class b8 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt(); // N: điểm tối đa chấp nhận được
        int k = sc.nextInt(); // K: ngưỡng dừng chơi
        int maxPts = sc.nextInt(); // W: giá trị rút ngẫu nhiên (1..W)

        double ans = new21Game(n, k, maxPts);
        sc.close();

        // In kết quả với 5 chữ số thập phân
        System.out.printf("%.5f\n", ans);
    }

    public static double new21Game(int n, int k, int maxPts) {
        // Trường hợp đặc biệt:
        // 1. Nếu K = 0 -> Alice dừng ngay, luôn <= N => xác suất = 1
        // 2. Nếu N >= K + maxPts -> mọi điểm có thể rơi vào đều <= N => xác suất = 1
        if (k == 0 || n >= k + maxPts)
            return 1.0;

        double[] dp = new double[n + 1];
        dp[0] = 1.0; // ban đầu Alice chắc chắn ở 0 điểm( dòng 32 tại sao for i=1 là đây tại vì tại 0
                     // có rồi )
        double wSum = 1.0; // tổng cửa sổ trượt cho xác suất
        double res = 0.0; // kết quả

        for (int i = 1; i <= n; i++) {
            // Xác suất đạt điểm i
            dp[i] = wSum / maxPts;

            // Nếu chưa đạt K, Alice có thể rút tiếp => cộng vào wSum
            if (i < k) {
                wSum += dp[i];
            } else {
                // Nếu đã đạt >= K thì Alice dừng => cộng vào kết quả
                res += dp[i];
            }

            // Loại bỏ phần tử cũ trong cửa sổ (cách xa hơn maxPts bước)
            if (i - maxPts >= 0) {
                wSum -= dp[i - maxPts];
            }
        }
        return res;
    }
}

// * ------------------------------------------
// * GIẢI THÍCH ĐỀ BÀI:
// *
// * Alice chơi trò New 21 Game:
// * - Bắt đầu với 0 điểm.
// * - Mỗi lượt rút ngẫu nhiên số từ 1..W (đều nhau).
// * - Nếu tổng < K -> tiếp tục chơi.
// * - Nếu tổng >= K -> dừng lại.
// *
// * Yêu cầu: Xác suất tổng điểm nằm trong [K, N].
// *
// * Ví dụ:
// * N = 10, K = 1, W = 10
// * => Alice chỉ rút 1 lần, điểm chắc chắn trong [1..10] -> kết quả = 1.0.
// *
// * ------------------------------------------
// * GIẢI THUẬT (Dynamic Programming + Sliding Window):
// *
// * - dp[i] = xác suất đạt chính xác i điểm.
// * - wSum = tổng dp của cửa sổ [i-W, i-1] (những trạng thái có thể đi tới i).
// * - res = tổng xác suất khi Alice dừng trong [K..N].
// *
// * Công thức:
// * dp[i] = wSum / W
// *
// * Nếu i < K -> Alice còn rút tiếp => wSum += dp[i].
// * Nếu i >= K -> Alice dừng lại => res += dp[i].
// * Sau mỗi bước, trượt cửa sổ: wSum -= dp[i-W].
// *
// * Độ phức tạp: O(N).
// * ------------------------------------------
// */

// // ##2.
// // Ý tưởng Đặt`dp[i]`=** xác suất Alice có đúng i điểm** sau khi chơi.

// // Ban đầu`dp[0]=1.0`(chắc chắn ở 0 điểm).*Với mỗi`i`, nếu Alice có thể đạt
// đến`i` thì công thức là:

// // ```dp[i]=(dp[i-1]+dp[i-2]+...+dp[i-maxPts])/maxPts```

// // Vì để được`i`,Alice phải rút 1 số từ 1..maxPts sau khi đang ở một trạng
// thái trước đó.

// // 👉
// // Nhưng nếu tính trực tiếp công thức trên thì tốn

// // O(n \* maxPts), quá chậm.

// // ---

// // ## 3. Tối ưu bằng Sliding Window

// // Ta nhận thấy:

// // ```
// // dp[i] = (dp[i-1] + dp[i-2] + ... + dp[i-maxPts]) / maxPts
// // ```

// // Chính là **trung bình cộng** của `maxPts` giá trị trước đó.
// // Vậy ta chỉ cần duy trì tổng

// // cộng dồn (`wSum`) → tính được nhanh trong O(1).

// // Trong code:

// // ```java
// // dp[i] = wSum / maxPts;
// // ```

// // * Nếu `i < k`: Alice còn tiếp tục chơi → ta cộng `dp[i]` vào `wSum` để
// tính cho các bước tiếp theo.
// // * Nếu `i >= k`: Alice dừng → ta cộng `dp[i]` vào `res` (kết quả cuối
// cùng).

// // Và để tránh `wSum` phình to, mỗi khi `i - maxPts >= 0`, ta loại bỏ `dp[i -
// maxPts]` (vì đã ra khỏi cửa sổ trượt).

// ---

// // ## 4. Các trường hợp đặc biệt

// // ```java
// // if (k == 0 || n >= k + maxPts) return 1.0;
// // ```

// // * Nếu `k = 0` → Alice dừng ngay, luôn ≤ n → xác suất = 1.
// // * Nếu `n >= k + maxPts` → Điểm tối đa Alice có thể đạt vẫn ≤ n → xác suất
// = 1.

// // ---

// // ## 5. Ví dụ minh họa

// // Giả sử `n = 6, k = 1, maxPts = 10`.

// // * Alice rút 1 lần là

// // dừng (vì k=1).
// // * Điểm nằm trong \[1..10].
// // * Vì `n = 6` → chỉ chấp nhận kết quả từ 1 đến 6.
// // * Xác suất = `6/10 = 0.6`.

// // Thuật toán sẽ tính:

// // * `dp[1] = 1/10, dp[2] = 1/10, ... dp[6] = 1/10` → cộng vào res → 0.6.

// // ---

// // ## 6. Độ phức tạp

// // * Thời gian: **O(n)** (chỉ một vòng lặp).
// // * Bộ nhớ: **O(n)** (có thể tối ưu

// // xuống O(k) nếu cần).

// // ---

// // 👉 Tóm gọn:

// // * Dùng **dp + sliding window** để tính xác suất phân phối.
// // * `wSum` là **tổng trượt của Xác suất** để cập nhật nhanh.
// // * `res` chứa xác suất Alice dừng hợp lệ (≥ k nhưng ≤ n).
// n:Điểm tối đa Alice được phép có khi kết thúc (chiến thắng nếu ≤ n)
// k Ngưỡng dừng chơi (Alice dừng khi tổng ≥ k)
// maxPts Giá trị ngẫu nhiên cao nhất mỗi lượt rút (là W trong bài)
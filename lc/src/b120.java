
// # Best Time to Buy and Sell Stock V(17/12/2025)
// # **“Best Time to Buy and Sell Stock” – Giải thích đề bài (tiếng Việt)**
// # Mình sẽ **giải thích thuật toán + chú thích trực tiếp vào code** để bạn hiểu rõ từng phần nhé.
// // # Bài này là **Best Time to Buy and Sell Stock với tối đa k giao dịch**, nhưng **cho phép cả Long (mua trước bán sau) và Short (bán trước mua sau)** → nâng cao hơn bài LeetCode cơ bản.
// Mình sẽ **giải thích thuật toán Java này theo cách “đi thi đọc là hiểu”**, vì đây là **Best Time to Buy and Sell Stock V (3573)** – cho phép **LONG + SHORT**, tối đa **k giao dịch**.
import java.util.*;

public class b120 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();
        int[] prices = new int[n];

        // Nhập giá cổ phiếu
        for (int i = 0; i < n; i++) {
            prices[i] = sc.nextInt();
        }

        // Nhập số giao dịch tối đa
        int k = sc.nextInt();

        long result = maximumProfit(prices, k);

        // In kết quả
        System.out.println(result);

        sc.close();
    }

    public static long maximumProfit(int[] prices, int k) {
        int n = prices.length;

        // Không đủ ngày hoặc không được giao dịch
        if (n < 2 || k == 0)
            return 0;

        // dp_prev[i]: lợi nhuận tối đa đến ngày i với (t-1) giao dịch
        // dp_cur[i] : lợi nhuận tối đa đến ngày i với t giao dịch
        long[] dp_prev = new long[n];
        long[] dp_cur = new long[n];

        // Duyệt theo số giao dịch
        for (int t = 1; t <= k; t++) {

            // best_buy : trạng thái đang giữ LONG tốt nhất
            // best_short: trạng thái đang giữ SHORT tốt nhất
            long best_buy = -prices[0]; // mở Long ở ngày 0
            long best_short = prices[0]; // mở Short ở ngày 0

            // Ngày 0 thì chưa thể đóng giao dịch
            dp_cur[0] = 0;

            // Duyệt theo ngày
            for (int i = 1; i < n; i++) {

                // a: không làm gì ngày i
                long a = dp_cur[i - 1];

                // b: đóng Long tại ngày i
                long b = best_buy + prices[i];

                // c: đóng Short tại ngày i
                long c = best_short - prices[i];

                // Chọn hành động tốt nhất
                dp_cur[i] = Math.max(Math.max(a, b), c);

                // Cập nhật trạng thái mở vị thế cho các ngày sau
                // Mở Long mới tại ngày i
                best_buy = Math.max(best_buy, dp_prev[i - 1] - prices[i]);

                // Mở Short mới tại ngày i
                best_short = Math.max(best_short, dp_prev[i - 1] + prices[i]);
            }

            // Hoán đổi dp cho vòng giao dịch tiếp theo
            long[] temp = dp_prev;
            dp_prev = dp_cur;
            dp_cur = temp;
        }

        // Kết quả là lợi nhuận max ở ngày cuối với ≤ k giao dịch
        return dp_prev[n - 1];
    }
    /*
     * maximumProfit:
     * prices[i] : giá cổ phiếu ngày i
     * k : số giao dịch tối đa
     * return : lợi nhuận lớn nhất
     */
}

// ---

// ## 1. Tóm tắt đề bài (nhắc nhanh)

// * `prices[i]`: giá ngày `i`
// * Tối đa `k` **giao dịch**
// * 1 giao dịch = **mở vị thế → đóng vị thế**
// * Có thể:

// * **Long**: mua → bán
// * **Short**: bán → mua
// * Mỗi thời điểm chỉ giữ **1 vị thế**
// * Mục tiêu: **lợi nhuận tối đa**

// ## 2. Ý tưởng cốt lõi

// Thay vì dùng 3 mảng `dp0, dpL, dpS` như bản Python, code Java này **tối ưu
// không gian**:

// 👉 **dp[t][i]** = lợi nhuận tối đa **đến ngày i**, với **t giao dịch**

// Ta chỉ cần:

// * `dp_prev[i]` → dp của `t-1` giao dịch
// * `dp_cur[i]` → dp của `t` giao dịch

// ---

// ## 3. Ý nghĩa các biến quan trọng

// ### `dp_prev[i]`

// > Lợi nhuận tối đa đến **ngày i**, dùng **t−1 giao dịch**

// ### `dp_cur[i]`

// > Lợi nhuận tối đa đến **ngày i**, dùng **t giao dịch**

// ---

// ### `best_buy`

// ```java
// best_buy = max(dp_prev[j] - prices[j+1])
// ```

// 👉 Trạng thái **đang giữ LONG tốt nhất**
// (đã mở Long ở ngày nào đó trước)

// ---

// ### `best_short`

// ```java
// best_short = max(dp_prev[j] + prices[j+1])
// ```

// 👉 Trạng thái **đang giữ SHORT tốt nhất**
// (đã mở Short ở ngày nào đó trước)

// ---

// ## 4. Công thức DP chính (quan trọng nhất)

// Tại ngày `i`, giao dịch thứ `t`:

// ```java
// dp_cur[i] = max(
// dp_cur[i - 1], // không làm gì
// best_buy + prices[i], // đóng Long
// best_short - prices[i] // đóng Short
// );
// ```

// ### Ý nghĩa:

// | Biến | Hành động |
// | ------------------------ | ------------------------ |
// | `dp_cur[i - 1]` | Không giao dịch hôm nay |
// | `best_buy + prices[i]` | Bán cổ phiếu (đóng Long) |
// | `best_short - prices[i]` | Mua lại (đóng Short) |

// ---

// ## 5. Cập nhật trạng thái mở vị thế

// Sau khi tính `dp_cur[i]`, ta cập nhật:

// ```java
// best_buy = Math.max(best_buy, dp_prev[i - 1] - prices[i]);
// best_short = Math.max(best_short, dp_prev[i - 1] + prices[i]);
// ```

// ### Ý nghĩa:

// * Mở **Long mới** tại ngày `i`
// * Mở **Short mới** tại ngày `i`
// * Dựa trên lợi nhuận của `t-1` giao dịch

// ⚠️ **Mở vị thế KHÔNG tính là giao dịch**, chỉ khi đóng mới tính.

// ---

// ## 6. Luồng chạy tổng thể

// ```text
// for t = 1 → k:
// reset best_buy, best_short
// for i = 1 → n-1:
// chọn hành động tốt nhất
// cập nhật best_buy / best_short
// dp_prev = dp_cur
// ```

// ---

// ## 7. Vì sao không cần dpL, dpS?

// * `best_buy` ≈ dpL tốt nhất
// * `best_short` ≈ dpS tốt nhất
// * Được **nén lại thành biến**, giảm bộ nhớ từ `O(n*k)` → `O(n)`

// ---

// ## 8. Độ phức tạp

// | Loại | Giá trị |
// | --------- | ---------- |
// | Thời gian | `O(n * k)` |
// | Bộ nhớ | `O(n)` |

// ---

// ## 9. So sánh nhanh với bản Python

// | Python | Java |
// | ----------- | ----------- |
// | 3 mảng dp | 2 mảng dp |
// | Dễ hiểu | Tối ưu |
// | O(k) bộ nhớ | O(n) bộ nhớ |
// | Phù hợp học | Phù hợp thi |

// ---

// ## 10. Tóm tắt 1 câu (rất quan trọng)

// > **Mỗi giao dịch = chọn 1 thời điểm mở (Long hoặc Short) + 1 thời điểm đóng
// sao cho lợi nhuận lớn nhất, dùng DP theo số giao dịch và ngày.**

// ---

// Nếu bạn muốn:

// * 🔥 Vẽ bảng DP từng ngày
// * 🔥 So sánh với Stock I / II / III / IV
// * 🔥 Viết lại code cho dễ nhớ khi phỏng vấn

// 👉 Cứ nói, mình giải tiếp cho bạn 👍

// # ---

// # ## 1. Ý nghĩa bài toán

// # * `prices[i]`: giá cổ phiếu ngày i
// # * `k`: số **giao dịch tối đa**
// # * **Mỗi giao dịch** = mở vị thế → đóng vị thế
// # * Có 2 kiểu:

// # * **Long (L)**: mua → bán → lãi = sell − buy
// # * **Short (S)**: bán → mua → lãi = sell − buy (giá giảm)

// # 👉 Mục tiêu: **lợi nhuận tối đa**

// # ---

// # ## 2. Ý tưởng quy hoạch động (Dynamic Programming)

// # Ta xét trạng thái theo **ngày**, **số giao dịch đã hoàn thành**, và **vị
// thế hiện tại**.

// # ### Ba mảng DP

// # | Mảng | Ý nghĩa |
// # | -------- |
// ------------------------------------------------------------------- |
// # | `dp0[t]` | Lợi nhuận max khi **không giữ vị thế**, đã hoàn thành `t` giao
// dịch |
// # | `dpL[t]` | Lợi nhuận max khi đang giữ **Long**, đã hoàn thành `t` giao
// dịch |
// # | `dpS[t]` | Lợi nhuận max khi đang giữ **Short**, đã hoàn thành `t` giao
// dịch |

// # ⚠️ Giao dịch **chỉ được tính khi đóng vị thế**

// # ---

// # ## 3. Ý nghĩa các chuyển trạng thái

// # ### Từ trạng thái `dp0` (không giữ gì)

// # * Mở **Long**:

// # ```
// # dpL[t] = dp0[t] - price
// # ```
// # * Mở **Short**:

// # ```
// # dpS[t] = dp0[t] + price
// # ```

// # ---

// # ### Từ trạng thái đang giữ

// # * Đóng **Long**:

// # ```
// # dp0[t+1] = dpL[t] + price
// # ```
// # * Đóng **Short**:

// # ```
// # dp0[t+1] = dpS[t] - price

// ## 4. Vì sao `k = min(k, n//2)`?

// # * 1 giao dịch cần **ít nhất 2 ngày**
// # * Tối đa chỉ có `n//2` giao dịch hợp lệ
// # * Giảm độ phức tạp, tránh TLE

// # ---

// # ## 5. Chú thích chi tiết code

// # ```python
// from typing import List

// class Solution:
// def maximumProfit(self, prices: List[int], k: int) -> int:
// n = len(prices)

// # Không đủ ngày hoặc không được giao dịch
// if n < 2 or k == 0:
// return 0

// # Giới hạn k hợp lý
// k = min(k, n // 2)

// # Giá trị âm vô cùng (để đánh dấu trạng thái không hợp lệ)
// NEG_INF = -10**18

// # dp0[t]: không giữ vị thế, đã hoàn thành t giao dịch
// # dpL[t]: đang giữ Long, đã hoàn thành t giao dịch
// # dpS[t]: đang giữ Short, đã hoàn thành t giao dịch
// dp0 = [NEG_INF] * (k + 1)
// dpL = [NEG_INF] * (k + 1)
// dpS = [NEG_INF] * (k + 1)

// # Ban đầu: chưa giao dịch, không giữ gì
// dp0[0] = 0

// # Duyệt từng ngày
// for price in prices:
// # Tạo bản sao để cập nhật trạng thái mới
// new_dp0 = dp0[:]
// new_dpL = dpL[:]
// new_dpS = dpS[:]

// # MỞ vị thế (không tăng số giao dịch)
// for t in range(k):
// if dp0[t] != NEG_INF:
// # Mở Long
// new_dpL[t] = max(new_dpL[t], dp0[t] - price)
// # Mở Short
// new_dpS[t] = max(new_dpS[t], dp0[t] + price)

// # ĐÓNG vị thế (tăng số giao dịch)
// for t in range(k):
// if dpL[t] != NEG_INF:
// # Đóng Long
// new_dp0[t + 1] = max(new_dp0[t + 1], dpL[t] + price)

// if dpS[t] != NEG_INF:
// # Đóng Short
// new_dp0[t + 1] = max(new_dp0[t + 1], dpS[t] - price)

// # Cập nhật dp
// dp0, dpL, dpS = new_dp0, new_dpL, new_dpS

// # Kết quả: không giữ vị thế, lợi nhuận lớn nhất
// return max(dp0)

// ## 6. Độ phức tạp

// # * **Thời gian**: `O(n * k)`
// # * **Bộ nhớ**: `O(k)`

// # ---

// # ## 7. Tóm tắt ngắn gọn

// # ✔ Dùng DP theo ngày
// # ✔ Phân biệt 3 trạng thái: không giữ – Long – Short
// # ✔ Giao dịch chỉ tính khi **đóng vị thế**
// # ✔ Có thể xử lý cả thị trường **tăng và giảm**

// # ---

// # Nếu bạn muốn:

// # * 🔹 Vẽ bảng DP minh họa từng ngày
// # * 🔹 Chuyển sang **chỉ Long (LeetCode gốc)**
// # * 🔹 Tối ưu thêm / viết lại cho dễ nhớ khi đi thi

// # 👉 Cứ nói nhé 👍

// # Đây là một bài toán **kinh điển** trong lập trình (thường gặp trên
// LeetCode).

// # ---

// # ## 1. Nội dung đề bài

// # Bạn được cho một mảng `prices`, trong đó:

// # * `prices[i]` là **giá cổ phiếu vào ngày thứ i**
// # * Bạn được phép:

// # * **Mua 1 lần**
// # * **Bán 1 lần**
// # * **Phải mua trước khi bán**

// # 👉 Nhiệm vụ: **Tính lợi nhuận lớn nhất có thể đạt được**.
// # Nếu không thể có lợi nhuận thì trả về `0`.

// # ---

// # ## 2. Ví dụ dễ hiểu

// # ### Ví dụ 1

// # ```
// # Input: prices = [7,1,5,3,6,4]
// # Output: 5
// # ```

// # **Giải thích:**

// # * Mua ngày giá = `1`
// # * Bán ngày giá = `6`
// # * Lợi nhuận = `6 - 1 = 5`

// # 👉 Đây là lợi nhuận lớn nhất có thể.

// # ---

// # ### Ví dụ 2

// # ```
// # Input: prices = [7,6,4,3,1]
// # Output: 0
// # ```

// # **Giải thích:**

// # * Giá giảm dần
// # * Không có ngày nào bán giá cao hơn ngày mua
// # * 👉 Không có lợi nhuận → trả `0`

// # ---

// # ## 3. Điều kiện quan trọng của đề

// # ✔ Chỉ **1 lần mua**
// # ✔ Chỉ **1 lần bán**
// # ✔ **Mua trước – bán sau**
// # ❌ Không được bán trước rồi mua
// # ❌ Không mua bán nhiều lần

// # ---

// # ## 4. Bài toán thực chất là gì?

// # 👉 Tìm:

// # ```
// # max(prices[j] - prices[i])
// # với j > i
// # ```

// # Nói cách khác:

// # * Mua ở **giá thấp nhất trước đó**
// # * Bán ở **giá cao nhất sau đó**

// # ---

// # ## 5. Ý tưởng đơn giản (không code)

// # * Duyệt từng ngày
// # * Ghi nhớ:

// # * **Giá thấp nhất đã gặp**
// # * **Lợi nhuận lớn nhất hiện tại**
// # * Mỗi ngày:

// # * Nếu giá hôm nay thấp hơn → cập nhật giá mua
// # * Nếu bán hôm nay lời hơn → cập nhật lợi nhuận

// # ---

// # ## 6. Bài này dùng để kiểm tra gì?

// # * Tư duy **tham lam (Greedy)**
// # * Duyệt mảng một lần `O(n)`
// # * Không dùng brute-force `O(n²)`

// # ---

// # Nếu bạn muốn:

// # * ✅ Giải bằng **Java / Python**
// # * ✅ So sánh với bài **Best Time to Buy and Sell Stock II**
// # * ✅ Giải thích bằng **ví dụ từng bước**

// # 👉 Cứ nói mình biết nhé 👍

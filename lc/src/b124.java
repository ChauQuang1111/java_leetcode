
// // # Best Time to Buy and Sell Stock V(17/12/2025)
// // # **“Best Time to Buy and Sell Stock” – Giải thích đề bài (tiếng Việt)**
// // # Mình sẽ **giải thích thuật toán + chú thích trực tiếp vào code** để bạn hiểu rõ từng phần nhé.
// // # Bài này là **Best Time to Buy and Sell Stock với tối đa k giao dịch**, nhưng **cho phép cả Long (mua trước bán sau) và Short (bán trước mua sau)** → nâng cao hơn bài LeetCode cơ bản.
// Mình sẽ **giải thích thuật toán của code Java này một cách trực quan, đúng trọng tâm đề 955**, từ **ý tưởng → vai trò từng biến → luồng xử lý**, để bạn đọc là “ngộ” ngay 👍
import java.util.*;

public class b124 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();
        sc.nextLine(); // bỏ dòng thừa

        String[] strs = new String[n];

        // Nhập từng chuỗi
        for (int i = 0; i < n; i++) {
            strs[i] = sc.nextLine();
        }

        // Gọi hàm giải
        int result = minDeletionSize(strs);

        // In kết quả
        System.out.println(result);

        sc.close();

    }

    public static int minDeletionSize(String[] strs) {
        int n = strs.length; // số chuỗi
        int m = strs[0].length(); // độ dài mỗi chuỗi (số cột)

        // sorted[i] = true nghĩa là:
        // strs[i] và strs[i+1] đã được xác định thứ tự đúng
        boolean[] sorted = new boolean[n - 1];

        int deletions = 0; // số cột cần xóa

        // Duyệt từng cột từ trái sang phải
        for (int col = 0; col < m; col++) {
            boolean needDelete = false;

            // Kiểm tra xem cột này có phá thứ tự không
            for (int row = 0; row < n - 1; row++) {
                // Chỉ xét các cặp chưa được xác định thứ tự
                if (!sorted[row]) {
                    if (strs[row].charAt(col) > strs[row + 1].charAt(col)) {
                        needDelete = true;
                        break;
                    }
                }
            }

            // Nếu cột này gây sai thứ tự → bắt buộc xóa
            if (needDelete) {
                deletions++;
                continue; // bỏ qua cập nhật sorted
            }

            // Nếu cột hợp lệ, cập nhật trạng thái sorted
            for (int row = 0; row < n - 1; row++) {
                if (!sorted[row] &&
                        strs[row].charAt(col) < strs[row + 1].charAt(col)) {
                    sorted[row] = true;
                }
            }
        }

        return deletions;
    }
}

// ---

// ## 🎯 Mục tiêu bài 955

// > Xóa **ít nhất số cột** sao cho **sau khi xóa**, mảng `strs` được **sắp xếp
// từ điển từ trên xuống dưới**.

// ⚠️ Không yêu cầu từng cột tăng dần, mà yêu cầu **toàn bộ chuỗi** sau khi xóa
// phải đúng thứ tự.

// ---

// ## 🧠 Ý tưởng thuật toán (Greedy)

// * Duyệt **từng cột từ trái → phải**
// * So sánh **các cặp chuỗi kề nhau**:

// ```
// strs[0] vs strs[1]
// strs[1] vs strs[2]
// ...
// ```
// * Với mỗi cặp:

// * Nếu **đã xác định đúng thứ tự** từ cột trước → bỏ qua
// * Nếu chưa:

// * Cột hiện tại mà làm **sai thứ tự** → **bắt buộc xóa cột**
// * Nếu giúp xác định thứ tự → ghi nhận lại

// 👉 Chỉ xóa cột **khi thật sự cần thiết**

// ---

// ## 🔑 Giải thích các biến quan trọng

// ```java
// boolean[] sorted = new boolean[n - 1];
// ```

// * `sorted[i] == true` nghĩa là:

// ```
// strs[i] < strs[i+1]
// ```

// đã **được quyết định ở các cột trước**
// * Khi đã sorted → **không cần so nữa**

// ---

// ```java
// int deletions = 0;
// ```

// * Đếm số cột phải xóa

// ---

// ## 🔍 Luồng xử lý chi tiết

// ### 1️⃣ Duyệt từng cột

// ```java
// for (int col = 0; col < m; col++)
// ```

// Ta xét cột từ trái sang phải vì:

// * Thứ tự từ điển phụ thuộc **ký tự đầu tiên khác nhau**

// ---

// ### 2️⃣ Kiểm tra cột có phá thứ tự không

// ```java
// boolean needDelete = false;

// for (int row = 0; row < n - 1; row++) {
// if (!sorted[row]) {
// if (strs[row].charAt(col) > strs[row + 1].charAt(col)) {
// needDelete = true;
// break;
// }
// }
// }
// ```

// 👉 Chỉ xét các cặp **chưa được phân định**

// Nếu gặp:

// ```
// strs[row][col] > strs[row+1][col]
// ```

// ➡️ Cột này làm chuỗi **không còn sorted**
// ➡️ **Bắt buộc xóa**

// ---

// ### 3️⃣ Nếu cột xấu → xóa

// ```java
// if (needDelete) {
// deletions++;
// continue;
// }
// ```

// * Không cập nhật `sorted`
// * Chuyển sang cột tiếp theo

// ---

// ### 4️⃣ Nếu cột tốt → cập nhật trạng thái sorted

// ```java
// for (int row = 0; row < n - 1; row++) {
// if (!sorted[row] &&
// strs[row].charAt(col) < strs[row + 1].charAt(col)) {
// sorted[row] = true;
// }
// }
// ```

// Nếu:

// ```
// strs[row][col] < strs[row+1][col]
// ```

// ➡️ Thứ tự của cặp này **đã được xác định**
// ➡️ Đánh dấu `sorted[row] = true`

// ---

// ## 📘 Ví dụ minh họa

// ```text
// strs = ["ca", "bb", "ac"]
// ```

// ### Cột 0:

// ```
// c > b ❌ → needDelete = true → xóa
// ```

// ### Cột 1:

// ```
// a < b < c ✅
// → sorted[0], sorted[1] = true
// ```

// ➡️ Kết quả: `1`

// ---

// ## 🧠 Vì sao thuật toán này đúng?

// * Mỗi cột:

// * Hoặc **bắt buộc phải xóa**
// * Hoặc **giữ lại để xác định thứ tự**
// * Không xóa thừa → **greedy hợp lệ**
// * Tránh so sánh dư thừa bằng `sorted[]`

// ---

// ## ⏱️ Độ phức tạp

// * **Time**: `O(n × m)`
// * **Space**: `O(n)`

// ---

// ## 🧩 Tóm tắt ngắn gọn

// > Ta duyệt cột từ trái sang phải,
// > chỉ xóa cột nếu nó làm sai thứ tự các chuỗi chưa được phân định,
// > và dùng `sorted[]` để ghi nhớ các cặp đã đúng thứ tự.

// ---

// Nếu bạn muốn:

// * 🔁 Trace chi tiết từng dòng với input cụ thể
// * ✍️ So sánh với Delete Columns I
// * 🧠 Giải thích chứng minh greedy
// * 🧑‍🏫 Viết lại theo phong cách dễ nhớ cho phỏng vấn

// 👉 cứ hỏi tiếp nhé 💪

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

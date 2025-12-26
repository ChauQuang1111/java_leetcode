
// //  2483 – Minimum Penalty for a Shop(26/12/2025)
// OK, mình sẽ **giải thích đúng thuật toán trong đoạn code của bạn**, theo góc nhìn **huấn luyện viên lập trình thi đấu**, vì code này **không viết theo cách “chuẩn sách giáo khoa”**, mà là **một biến thể greedy khá tinh tế**.
import java.util.*;
import java.nio.charset.Charset;

public class b129 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // Nhập chuỗi customers
        // Ví dụ: YYNY
        String customers = sc.nextLine();

        int result = bestClosingTime(customers);

        // In ra thời điểm đóng cửa
        System.out.println(result);

        sc.close();
    }

    /**
     * Hàm tìm thời điểm đóng cửa sao cho penalty là nhỏ nhất
     * 
     * @param customers chuỗi gồm 'Y' và 'N'
     * @return thời điểm đóng cửa (0-based theo đề, nên trả về i+1)
     */
    public static int bestClosingTime(String customers) {

        // Chuyển chuỗi sang mảng byte để so sánh nhanh
        // 'Y' trong ASCII = 89, 'N' = 78
        byte[] cs = customers.getBytes(Charset.forName("ISO-8859-1"));

        // bestTime: lưu chỉ số i (0-based) của thời điểm đóng cửa tốt nhất
        // Khởi tạo = -1 tương ứng với đóng ngay từ đầu
        int bestTime = -1;

        // customersLeft: biến cân bằng (balance)
        // +1 nếu gặp 'Y' (mở cửa có lợi)
        // -1 nếu gặp 'N' (mở cửa có hại)
        int customersLeft = 0;

        // Duyệt từng giờ từ trái sang phải
        for (int i = 0; i < cs.length; i++) {

            // Nếu có khách
            if (cs[i] == 89) { // 'Y'
                customersLeft++; // mở cửa có lợi

                // Nếu tổng lợi ích > 0
                // => mở cửa đến giờ i là có lợi
                if (customersLeft > 0) {
                    bestTime = i; // cập nhật thời điểm đóng cửa tốt nhất
                    customersLeft = 0; // reset để tìm đoạn lợi ích mới
                }
            }
            // Nếu không có khách
            else { // 'N'
                customersLeft--; // mở cửa bị phạt
            }
        }

        // bestTime là chỉ số cuối cùng nên MỞ cửa
        // nên thời điểm ĐÓNG cửa = bestTime + 1
        return bestTime + 1;
    }
}

// ---

// ## 1. Nhìn tổng thể: Code này đang làm gì?

// ```java
// int bestTime = -1;
// int customersLeft = 0;
// ```

// * `bestTime`: thời điểm **đóng cửa tối ưu (tạm thời)**, lưu **chỉ số i**
// * `customersLeft`: **biến cân bằng (balance)**, không phải số khách thật

// 👉 Thuật toán này **không tính penalty trực tiếp**, mà dùng **ý tưởng “lợi –
// hại” khi mở thêm 1 giờ**.

// ---

// ## 2. Ý tưởng cốt lõi (RẤT QUAN TRỌNG)

// Ta duyệt từ **trái → phải**, và tại mỗi giờ:

// * `'Y'` (có khách):

// * Nếu **mở cửa** → có lợi (tránh bị phạt)
// * `'N'` (không khách):

// * Nếu **mở cửa** → có hại (bị phạt)

// 👉 Ta gán:

// * `'Y'` → **+1**
// * `'N'` → **-1**

// 📌 `customersLeft` chính là:

// > **Tổng lợi ích ròng nếu ta tiếp tục mở cửa tới thời điểm hiện tại**

// ---

// ## 3. Phân tích từng nhánh trong code

// ### Chuyển chuỗi sang byte

// ```java
// byte[] cs = customers.getBytes(Charset.forName("ISO-8859-1"));
// ```

// * `'Y'` có mã ASCII = **89**
// * `'N'` = 78
// → so sánh byte nhanh hơn `charAt` (tối ưu nhỏ, đúng kiểu thi đấu)

// ---

// ### Gặp `'Y'`

// ```java
// if (cs[i] == 89) {
// customersLeft++;
// if (customersLeft > 0) {
// bestTime = i;
// customersLeft = 0;
// }
// }
// ```

// Diễn giải:

// 1. `'Y'` → **+1 lợi ích**
// 2. Nếu `customersLeft > 0`:

// * Có nghĩa là:
// 👉 **Từ lần reset trước đến giờ, mở cửa có lời**
// * Ta cập nhật:

// * `bestTime = i` → nên mở ít nhất tới giờ này
// * reset `customersLeft = 0` → bắt đầu gom lợi mới

// 📌 Đây là **KADANE-style greedy** (maximum subarray logic)

// ---

// ### Gặp `'N'`

// ```java
// else {
// customersLeft--;
// }
// ```

// * `'N'` → **-1 lợi ích**
// * Không reset ngay, vì:

// * Có thể sau đó gặp `'Y'` bù lại

// ---

// ## 4. Tại sao reset `customersLeft = 0` khi > 0?

// Đây là **trick quan trọng nhất** 👇

// ### Ý nghĩa:

// * Khi tổng lợi ích dương:

// * Ta **chốt mốc đóng cửa tốt nhất tại i**
// * Reset để:

// * Không cộng dồn lợi ích cũ
// * Bắt đầu tìm **đoạn lợi ích mới tốt hơn**

// 📌 Giống hệt:

// > Khi tìm đoạn con có tổng lớn nhất (Kadane)

// ---

// ## 5. Ví dụ mô phỏng (cực kỳ quan trọng)

// ### Input

// ```
// customers = "YYNY"
// ```

// | i | char | + / - | customersLeft | bestTime |
// | - | ---- | ----- | ------------- | -------- |
// | 0 | Y | +1 | 1 → reset | 0 |
// | 1 | Y | +1 | 1 → reset | 1 |
// | 2 | N | -1 | -1 | 1 |
// | 3 | Y | +1 | 0 | 1 |

// 👉 Kết quả:

// ```
// bestTime = 1
// return bestTime + 1 = 2
// ```

// ✔ Chính xác

// ---

// ## 6. Liên hệ với bài toán gốc (rất quan trọng)

// Thuật toán này **ngầm tối ưu**:

// ```
// penalty = N_trước_k + Y_sau_k
// ```

// ↔️ Tương đương với:

// ```
// maximize (Y_trước_k - N_trước_k)
// ```

// 👉 Code của bạn **không tính penalty**, mà tìm **điểm mà lợi ích mở cửa là
// lớn nhất**

// ---

// ## 7. Độ phức tạp

// * ⏱ Time: **O(n)**
// * 💾 Memory: **O(1)**
// * 🚀 Tối ưu đúng chuẩn thi đấu

// ---

// ## 8. Đánh giá như huấn luyện viên

// ✅ Ý tưởng: **RẤT TỐT**
// ✅ Áp dụng greedy + Kadane
// ✅ Không cần prefix/suffix
// ⚠️ Hơi khó đọc với người mới (nhưng cực chuẩn contest)

// 👉 Nếu bạn viết được kiểu này trong contest → **trình độ khá cao**

// ---

// Nếu bạn muốn, mình có thể:

// * Viết lại **comment chi tiết từng dòng**
// * Chứng minh **vì sao greedy này luôn đúng**
// * So sánh **code này vs cách penalty truyền thống**

// Cứ nói, mình sẽ huấn luyện tiếp 💪

// ---

// ## 1. Đề bài nói gì? (Hiểu nhanh)

// * Bạn có một cửa hàng.
// * Chuỗi `customers` gồm:

// * `'Y'` → có khách đến
// * `'N'` → không có khách
// * Cửa hàng **chỉ được chọn 1 thời điểm để đóng cửa**.

// ### Quy tắc phạt (penalty):

// 1. **Mở cửa nhưng không có khách (`N`)** → bị phạt **+1**
// 2. **Đóng cửa nhưng có khách (`Y`)** → bị phạt **+1**

// 🎯 **Mục tiêu:**
// Chọn **giờ đóng cửa** sao cho **tổng penalty là nhỏ nhất**.

// ---

// ## 2. Đóng cửa lúc nào?

// Giả sử chuỗi có độ dài `n`.

// * Đóng cửa tại giờ `k` (0 ≤ k ≤ n)

// * Mở cửa từ `0 → k-1`
// * Đóng cửa từ `k → n-1`

// Ví dụ:

// ```
// customers = "YYNY"
// k = 2
// Mở: [0,1]
// Đóng:[2,3]
// ```

// ---

// ## 3. Penalty được tính thế nào?

// ### Khi đóng tại `k`

// * **Penalty khi mở cửa** (0 → k-1):

// * Đếm số `'N'`
// * **Penalty khi đóng cửa** (k → n-1):

// * Đếm số `'Y'`

// 👉 Tổng:

// ```
// penalty(k) = count_N(0 → k-1) + count_Y(k → n-1)
// ```

// ---

// ## 4. Tư duy tối ưu (key insight)

// Nếu **duyệt mọi k** và mỗi lần lại đếm thì sẽ là **O(n²)** → TLE ❌

// Ta cần:

// * Tính nhanh số `'Y'` bên phải
// * Tính nhanh số `'N'` bên trái

// ---

// ## 5. Mẹo cực hay trong thi đấu

// ### Ban đầu:

// * Giả sử **đóng cửa ngay từ đầu (k = 0)**
// → toàn bộ `'Y'` đều bị phạt

// ```
// penalty = tổng số 'Y'
// ```

// ### Khi dịch k từ trái sang phải:

// * Gặp `'Y'`:

// * Trước: bị phạt (đóng cửa)
// * Sau: KHÔNG bị phạt (mở cửa)
// → penalty **-1**
// * Gặp `'N'`:

// * Trước: không phạt
// * Sau: bị phạt (mở cửa không có khách)
// → penalty **+1**

// 🔥 Chỉ cần **1 vòng lặp O(n)**

// ---

// ## 6. Mô phỏng từng bước (rất quan trọng)

// Ví dụ:

// ```
// customers = "YYNY"
// ```

// ### Bước 1: đóng ngay từ đầu

// ```
// penalty = 3 (3 chữ 'Y')
// best = 3
// answer = 0
// ```

// ### Duyệt:

// | i | char | penalty | best | answer |
// | - | ---- | ------- | ---- | ------ |
// | 0 | Y | 2 | 2 | 1 |
// | 1 | Y | 1 | 1 | 2 |
// | 2 | N | 2 | 1 | 2 |
// | 3 | Y | 1 | 1 | 2 |

// ➡️ **Đóng tại giờ 2 là tối ưu**

// ---

// ## 7. Code chuẩn thi đấu (Java)

// ```java
// class Solution {
// public int bestClosingTime(String customers) {
// int penalty = 0;
// for (char c : customers.toCharArray()) {
// if (c == 'Y') penalty++;
// }

// int best = penalty;
// int answer = 0;

// for (int i = 0; i < customers.length(); i++) {
// if (customers.charAt(i) == 'Y') penalty--;
// else penalty++;

// if (penalty < best) {
// best = penalty;
// answer = i + 1;
// }
// }

// return answer;
// }
// }
// ```

// ---

// ## 8. Tóm tắt tư duy thi đấu

// ✔ Biến bài toán thành **prefix + suffix**
// ✔ Tránh đếm lại
// ✔ Dùng **state transition** (dịch điểm đóng cửa)
// ✔ Độ phức tạp: **O(n), bộ nhớ O(1)**

// ---

// ## 9. Gợi ý luyện thêm (rất nên)

// * LeetCode 1653 – Minimum Deletions to Make String Balanced
// * LeetCode 926 – Flip String to Monotone Increasing

// 👉 Cùng **mẫu tư duy “quét + tối ưu dần”**

// ---

// Nếu bạn muốn:

// * Mình **vẽ timeline minh họa**
// * Hoặc **giải lại bằng Prefix Sum**
// * Hoặc **phân tích vì sao greedy đúng**

// 👉 cứ nói nhé, mình sẽ đóng vai huấn luyện viên tiếp cho bạn 💪

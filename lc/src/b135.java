
// # # Plus One(01/01/2026)
// # # **Plus One – Giải thích đề bài (dễ hiểu)**
// # Mình sẽ **giải thích thuật toán** của cách làm này và **thêm chú thích trực tiếp vào code** cho bạn 👇
// # (Lưu ý: đây là cách **đơn giản, dễ hiểu**, không phải cách tối ưu nhất theo yêu cầu LeetCode, nhưng rất tốt để học tư duy.)
import java.util.*;

public class b135 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();

        int[] digits = new int[n];

        for (int i = 0; i < n; i++) {
            digits[i] = sc.nextInt();
        }

        int[] result = plusOne(digits);

        for (int x : result) {
            System.out.print(x + " ");
        }

        sc.close();
    }

    // Hàm cộng 1 vào số được biểu diễn bởi mảng digits
    public static int[] plusOne(int[] digits) {

        // Duyệt mảng từ phải sang trái (từ hàng đơn vị)
        for (int i = digits.length - 1; i >= 0; i--) {

            // Nếu chữ số hiện tại KHÔNG phải 9
            if (digits[i] != 9) {
                // Cộng 1 vào chữ số đó
                digits[i]++;

                // Không còn nhớ, trả kết quả ngay
                return digits;
            }

            // Nếu chữ số là 9:
            // 9 + 1 = 10 -> ghi 0, nhớ 1 sang bên trái
            digits[i] = 0;
        }

        // Nếu chạy hết vòng for mà chưa return
        // => tất cả chữ số đều là 9 (vd: 9, 99, 999)
        int[] result = new int[digits.length + 1];

        // Gán chữ số đầu tiên là 1
        // Các phần tử còn lại mặc định là 0
        result[0] = 1;

        return result;
    }

}

// # ## 🔍 Ý tưởng thuật toán (Algorithm)

// # Ta coi mảng `digits` như **một số nguyên** rồi:

// # 1. **Chuyển từng chữ số thành chuỗi**

// # * `[1,2,3] → ["1","2","3"]`
// # 2. **Ghép các chuỗi lại**

// # * `"1" + "2" + "3" → "123"`
// # 3. **Chuyển sang số nguyên và cộng 1**

// # * `123 + 1 = 124`
// # 4. **Chuyển lại thành chuỗi**

// # * `"124"`
// # 5. **Tách từng ký tự và đổi lại thành số**

// # * `["1","2","4"] → [1,2,4]`

// # ---

// # ## 🧠 Code có chú thích chi tiết

// # ```python
// class Solution:
// def plusOne(self, digits: list[int]) -> list[int]:
// # Bước 1: Chuyển từng phần tử trong digits thành chuỗi
// # Ví dụ: [1, 2, 3] -> ["1", "2", "3"]
// str_digits = map(str, digits)

// # Bước 2: Ghép các chuỗi lại thành một chuỗi số
// # ["1", "2", "3"] -> "123"
// number_str = "".join(str_digits)

// # Bước 3: Chuyển chuỗi thành số nguyên và cộng thêm 1
// # "123" -> 123 -> 124
// number_plus_one = int(number_str) + 1

// # Bước 4 + 5:
// # - Chuyển số 124 thành chuỗi "124"
// # - Duyệt từng ký tự và chuyển về số nguyên
// # "124" -> ['1','2','4'] -> [1,2,4]
// return [int(x) for x in str(number_plus_one)]
// # ```

// # ---

// # ## ⚠️ Nhận xét quan trọng (khi đi thi / LeetCode)

// # * ❌ Cách này **không được khuyến khích** trong bài gốc vì:

// # * Số có thể **rất dài** → vượt giới hạn số nguyên
// # * ✅ LeetCode mong muốn bạn:

// # * **Xử lý cộng từng chữ số**
// # * Giống như cộng tay (carry)

// # 👉 Nhưng:

// # * Cách này **rất ổn để hiểu đề**
// # * Dùng tốt cho **phỏng vấn cơ bản / luyện Python**

// # ---

// # Nếu bạn muốn, mình có thể:

// # * ✅ Viết lại **cách chuẩn LeetCode (không dùng int)**
// # * ✅ So sánh **2 cách: convert vs carry**
// # * ✅ Giải thích theo kiểu **từng bước như cộng tay**

// # Bạn chỉ cần nói tiếp 👍

// # 👉 Đây là một bài rất quen thuộc trên LeetCode.

// # ---

// # ### 📌 Mô tả đề bài

// # Bạn được cho **một mảng số nguyên `digits`**, trong đó:

// # * Mỗi phần tử là **một chữ số (0 → 9)**
// # * Cả mảng **biểu diễn một số nguyên không âm**
// # * **Chữ số đầu tiên không bao giờ là 0** (trừ khi số đó là 0)

// # 👉 **Yêu cầu:**
// # Hãy **cộng thêm 1** vào số đó và **trả về mảng kết quả**.

// # ---

// # ### 🧠 Hiểu đơn giản

// # * Mảng `[1, 2, 3]` nghĩa là số **123**
// # * Sau khi +1 → **124**
// # * Kết quả trả về: `[1, 2, 4]`

// # ---

// # ### 📌 Ví dụ

// # #### Ví dụ 1

// # ```
// # Input: digits = [1,2,3]
// # Output: [1,2,4]
// # ```

// # Giải thích: 123 + 1 = 124

// # ---

// # #### Ví dụ 2

// # ```
// # Input: digits = [4,3,2,1]
// # Output: [4,3,2,2]
// # ```

// # Giải thích: 4321 + 1 = 4322

// # ---

// # #### Ví dụ 3 (quan trọng)

// # ```
// # Input: digits = [9]
// # Output: [1,0]
// # ```

// # Giải thích: 9 + 1 = 10

// # ---

// # #### Ví dụ 4 (hay gặp)

// # ```
// # Input: digits = [9,9,9]
// # Output: [1,0,0,0]
// # ```

// # Giải thích: 999 + 1 = 1000

// # ---

// # ### ⚠️ Điều khó của bài

// # * Không được chuyển mảng thành số nguyên lớn rồi +1 (vì số có thể **rất
// dài**)
// # * Phải xử lý **nhớ (carry)** giống như cộng tay

// # ---

// # ### 🧩 Tư duy giải

// # 1. Bắt đầu từ **chữ số cuối cùng**
// # 2. Nếu chữ số `< 9`:

// # * Cộng 1 và **kết thúc**
// # 3. Nếu chữ số `= 9`:

// # * Đổi thành `0`
// # * Tiếp tục cộng sang bên trái
// # 4. Nếu tất cả đều là `9`:

// # * Thêm `1` vào đầu mảng

// # ---

// # Nếu bạn muốn:

// # * ✅ Giải bằng **Java**
// # * ✅ Giải bằng **Python**
// # * ✅ Giải từng bước như đi thi
// # 👉 cứ nói mình sẽ làm tiếp cho bạn nhé 👍
// Mình giải thích **thuật toán của đoạn Java này** theo cách **dễ hiểu – đúng
// tư duy LeetCode** nhé 👇
// (Đây là **cách chuẩn**, tốt hơn cách convert sang số nguyên)

// ---

// ## 🧠 Ý tưởng chính (Core idea)

// 👉 **Cộng 1 giống như cộng tay** từ **chữ số cuối cùng**:

// * Nếu chữ số **< 9** → cộng 1, **xong**
// * Nếu chữ số **= 9** → đổi thành 0, **nhớ (carry) sang trái**
// * Nếu **tất cả đều là 9** → tạo mảng mới dài hơn

// ---

// ## 🔍 Phân tích từng phần code

// ```java
// for(int i = digits.length - 1; i >= 0; i--) {
// ```

// ➡️ Duyệt mảng **từ phải sang trái**
// (vì khi cộng số, ta luôn cộng từ hàng đơn vị)

// ---

// ```java
// if(digits[i] != 9) {
// ```

// ➡️ Nếu chữ số hiện tại **không phải 9**

// Ví dụ:

// * `[1, 2, 3]` → gặp `3`
// * `[4, 5, 8]` → gặp `8`

// ---

// ```java
// digits[i]++;
// return digits;
// ```

// ➡️ Cộng 1 vào chữ số đó
// ➡️ **Không còn nhớ**, nên **trả kết quả luôn**

// 📌 Ví dụ:

// ```
// [1,2,3] → [1,2,4]
// [4,5,8] → [4,5,9]
// ```

// ---

// ```java
// digits[i] = 0;
// ```

// ➡️ Nếu chữ số là `9`:

// * `9 + 1 = 10`
// * Ghi `0`, nhớ `1` sang bên trái

// 📌 Ví dụ:

// ```
// [1,2,9] → [1,2,0] (tiếp tục vòng lặp)
// ```

// ---

// ```java
// }
// ```

// ➡️ Kết thúc vòng for
// 👉 Nếu chạy hết vòng mà **chưa return**, nghĩa là:

// * **Tất cả chữ số đều là 9**

// 📌 Ví dụ:

// ```
// [9]
// [9,9]
// [9,9,9]
// ```

// ---

// ## 🧨 Trường hợp đặc biệt: toàn số 9

// ```java
// digits = new int[digits.length + 1];
// digits[0] = 1;
// ```

// ➡️ Tạo mảng mới dài hơn 1 phần tử
// ➡️ Gán phần tử đầu = 1, các phần tử còn lại mặc định = 0

// 📌 Ví dụ:

// ```
// [9] → [1,0]
// [9,9] → [1,0,0]
// [9,9,9] → [1,0,0,0]
// ```

// ---

// ```java
// return digits;
// ```

// ➡️ Trả kết quả cuối cùng

// ---

// ## 🧩 Tóm tắt thuật toán

// 1. Duyệt từ **cuối mảng**
// 2. Nếu gặp số **≠ 9** → cộng 1 và kết thúc
// 3. Nếu gặp **9** → đổi thành 0, tiếp tục
// 4. Nếu duyệt hết → tạo mảng mới `length + 1`, gán `digits[0] = 1`

// ---

// ## ⏱️ Độ phức tạp

// * **Time:** `O(n)`
// * **Space:** `O(1)` (chỉ tạo mảng mới khi cần)

// ---

// 👉 Nếu bạn muốn, mình có thể:

// * ✍️ Thêm **comment trực tiếp vào code Java**
// * 🔁 Viết lại bằng **Python**
// * 📊 So sánh với cách **convert sang số nguyên**

// Chỉ cần nói nhé 👍

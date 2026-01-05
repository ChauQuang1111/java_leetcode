
// // # #  Maximum Matrix Sum(05/01/2026)
// // # Dưới đây là **giải thích thuật toán rõ ràng, đúng bản chất bài 1975 – Maximum Matrix Sum**, sau đó mình **thêm chú thích chi tiết trực tiếp vào code của bạn** 👇
// Dưới đây là **giải thích thuật toán cho lời giải Java của bài 1975 – Maximum Matrix Sum**, trình bày theo đúng cách thường dùng khi **thi / phỏng vấn / viết giải thích bài LeetCode** 👇
import java.util.*;

public class b140 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();

        // Khai báo ma trận n x n
        int[][] matrix = new int[n][n];

        // Nhập các phần tử của ma trận
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        long result = maxMatrixSum(matrix);
        System.out.println(result);

        sc.close();
    }

    // Hàm giải bài toán Maximum Matrix Sum
    public static long maxMatrixSum(int[][] matrix) {
        // leastElement: giá trị tuyệt đối nhỏ nhất trong ma trận
        int leastElement = 100001;

        // sum: tổng các giá trị tuyệt đối
        long sum = 0L;

        // negativeCount: đếm số phần tử âm
        int negativeCount = 0;

        // Duyệt từng phần tử trong ma trận
        for (int[] rows : matrix) {
            for (int value : rows) {

                // Nếu là số âm
                if (value < 0) {
                    negativeCount++; // tăng số lượng số âm
                    value = -value; // lấy trị tuyệt đối
                }

                // Cộng trị tuyệt đối vào tổng
                sum += value;

                // Cập nhật phần tử nhỏ nhất
                if (value < leastElement) {
                    leastElement = value;
                }
            }
        }

        // Nếu số phần tử âm là chẵn
        // => có thể đổi dấu để tất cả đều dương
        if (negativeCount % 2 == 0) {
            return sum;
        }
        // Nếu số phần tử âm là lẻ
        // => bắt buộc phải còn 1 số âm
        // => trừ đi 2 * leastElement để giảm thiệt hại ít nhất
        else {
            return sum - 2L * leastElement;
        }
    }
}

// ---

// ## 1️⃣ Phân tích bài toán (Problem Insight)

// * Ta được phép **đổi dấu 2 phần tử kề nhau**
// * Mỗi lần đổi dấu → **số phần tử âm thay đổi ±2**
// * ⇒ **Tính chẵn / lẻ của số phần tử âm là bất biến**

// 👉 Vì vậy:

// * Nếu số phần tử âm **chẵn** → có thể biến tất cả thành số dương
// * Nếu **lẻ** → bắt buộc còn **1 số âm**

// ---

// ## 2️⃣ Ý tưởng chính (Greedy)

// ### 🔹 Mục tiêu

// Tối đa hóa tổng các phần tử của ma trận.

// ### 🔹 Chiến lược

// 1. **Lấy trị tuyệt đối của mọi phần tử**
// → luôn làm tổng lớn nhất có thể

// 2. **Đếm số phần tử âm (`negativeCount`)**

// 3. **Tìm phần tử có trị tuyệt đối nhỏ nhất (`leastElement`)**

// ---

// ## 3️⃣ Xử lý hai trường hợp

// ### ✅ Trường hợp 1: `negativeCount` chẵn

// * Có thể đổi dấu để **tất cả số đều dương**
// * 👉 **Kết quả = tổng trị tuyệt đối**

// ### ❌ Trường hợp 2: `negativeCount` lẻ

// * Phải giữ lại **1 số âm**
// * Chọn số có trị tuyệt đối **nhỏ nhất** để giảm tổn thất
// * 👉 Tổng bị giảm `2 × leastElement`

// ---

// ## 4️⃣ Công thức kết quả

// ```
// Nếu negativeCount chẵn:
// answer = sum
// Ngược lại:
// answer = sum - 2 * leastElement
// ```

// ---

// ## 5️⃣ Giải thích theo từng phần code

// ```java
// int leastElement = 100001;
// long sum = 0L;
// int negativeCount = 0;
// ```

// * `leastElement`: lưu **giá trị tuyệt đối nhỏ nhất** trong ma trận
// * `sum`: tổng các **giá trị tuyệt đối**
// * `negativeCount`: số phần tử âm

// ---

// ```java
// for(int[] rows : matrix ){
// for(int value : rows){
// ```

// → Duyệt toàn bộ ma trận

// ---

// ```java
// if(value < 0){
// negativeCount++;
// value = -value;
// }
// ```

// * Đếm số âm
// * Chuyển sang trị tuyệt đối

// ---

// ```java
// sum += value;
// ```

// → Cộng trị tuyệt đối vào tổng

// ---

// ```java
// if(value < leastElement){
// leastElement = value;
// }
// ```

// → Cập nhật phần tử nhỏ nhất (dùng khi số âm là lẻ)

// ---

// ```java
// return (negativeCount%2 == 0) ? sum : (sum - 2*leastElement);
// ```

// * Nếu số âm **chẵn** → trả về tổng
// * Nếu **lẻ** → trừ đi `2 × leastElement`

// ---

// ## 6️⃣ Độ phức tạp

// | Thành phần | Giá trị |
// | ---------- | ------- |
// | Time | `O(n²)` |
// | Space | `O(1)` |

// ---

// ## 7️⃣ Tóm tắt 1 câu (rất hay dùng)

// > **Cộng tất cả trị tuyệt đối; nếu số phần tử âm là lẻ thì trừ đi 2 lần phần
// tử nhỏ nhất.**

// ---

// Nếu bạn muốn:

// * ✍️ Viết **mô tả ngắn gọn 3 dòng** để submit
// * 🧠 Chứng minh vì sao đổi dấu **không thay đổi tính chẵn/lẻ**
// * ⚡ So sánh với cách brute-force

// Ok, mình giải thích **chậm – đơn giản – có ví dụ cụ thể**, không dùng thuật
// ngữ khó nhé 👇
// Bạn chỉ cần hiểu **1 ý cốt lõi** là xong bài này.

// ---

// ## 1️⃣ Ta được làm gì trong bài này?

// 👉 **Mỗi lần thao tác**, ta:

// * Chọn **2 ô kề nhau**
// * **Đổi dấu cả 2 ô**

// * `+ → -`
// * `- → +`

// ⚠️ **Luôn là 2 ô, không bao giờ là 1 ô**

// ---

// ## 2️⃣ Điều quan trọng nhất (mấu chốt)

// ### ❓ Khi đổi dấu 2 ô thì số lượng số âm thay đổi thế nào?

// Ta xét các trường hợp:

// #### Trường hợp 1: Cả hai đều dương

// ```
// + + → - -
// ```

// * Số âm: `0 → 2` (tăng 2)

// #### Trường hợp 2: Cả hai đều âm

// ```
// - - → + +
// ```

// * Số âm: `2 → 0` (giảm 2)

// #### Trường hợp 3: Một âm, một dương

// ```
// - + → + -
// ```

// * Số âm: `1 → 1` (không đổi)

// 👉 **Kết luận cực kỳ quan trọng**

// > Mỗi lần đổi dấu, số lượng số âm **chỉ thay đổi 0 hoặc ±2**

// ➡️ **Không bao giờ đổi từ chẵn → lẻ hoặc lẻ → chẵn**

// ---

// ## 3️⃣ “Chẵn / lẻ là bất biến” nghĩa là gì?

// * Ban đầu có **số âm chẵn**
// → Sau bao nhiêu thao tác, **vẫn luôn là chẵn**

// * Ban đầu có **số âm lẻ**
// → Sau bao nhiêu thao tác, **vẫn luôn là lẻ**

// 👉 **Không thể thay đổi tính chẵn / lẻ**

// ---

// ## 4️⃣ Vì sao điều này quyết định lời giải?

// ### 🎯 Mục tiêu

// Ta muốn **tất cả số đều dương**
// (vì dương thì tổng lớn nhất)

// ---

// ### ✅ Nếu số âm ban đầu là **chẵn**

// Ví dụ:

// ```
// -1 -2 3 4
// ```

// Có 2 số âm (chẵn)

// ➡️ Ta có thể đổi dấu dần dần
// ➡️ Cuối cùng biến **tất cả thành số dương**

// ✅ OK, đạt mục tiêu

// ---

// ### ❌ Nếu số âm ban đầu là **lẻ**

// Ví dụ:

// ```
// -1 2 3
// ```

// Có 1 số âm (lẻ)

// ➡️ Do chẵn / lẻ **không đổi**
// ➡️ Dù làm gì, **luôn còn ít nhất 1 số âm**

// ❌ Không thể làm tất cả thành dương

// ---

// ## 5️⃣ Vậy phải làm sao khi số âm là lẻ?

// 👉 Ta **bắt buộc chấp nhận còn 1 số âm**

// 💡 Mẹo thông minh:

// * Cho số âm đó là **số có giá trị nhỏ nhất**
// * Ví dụ: `-1` tốt hơn `-100`

// ➡️ Thiệt hại ít nhất

// ---

// ## 6️⃣ Vì sao trong code lại:

// ```java
// sum - 2 * leastElement
// ```

// Giải thích cực đơn giản:

// * Lúc cộng tổng, ta đã cộng `+leastElement`
// * Nhưng thực tế phải là `-leastElement`

// ➡️ Sai lệch:

// ```
// +leastElement → -leastElement
// mất = 2 * leastElement
// ```

// ---

// ## 7️⃣ Tóm lại bằng 1 câu (quan trọng nhất)

// > Vì mỗi lần đổi dấu luôn đổi **2 số**, nên **chẵn/lẻ số âm không bao giờ
// thay đổi**.
// > Chẵn → làm hết thành dương,
// > Lẻ → buộc chừa lại 1 số âm nhỏ nhất.

// ---

// Nếu bạn muốn, mình có thể:

// * Giải lại bằng **ví dụ vẽ từng bước**
// * Giải như cho **người mới học lập trình**
// * So sánh **vì sao không cần mô phỏng thao tác**

// 👉 Cứ nói chỗ nào bạn vẫn chưa hiểu, mình sẽ gỡ từng nút 👍

// # ---

// # ## 1️⃣ Ý tưởng & thuật toán (Algorithm Explanation)

// # ### 🔹 Quan sát quan trọng

// # * Mỗi phép biến đổi **đổi dấu 2 ô kề nhau**
// # * → Số lượng phần tử âm **chỉ thay đổi theo bội số của 2**
// # * → **Tính chẵn / lẻ của số phần tử âm là bất biến**

// # ---

// # ### 🔹 Chiến lược tối ưu

// # 1. **Luôn muốn mọi số đều dương** → tổng lớn nhất
// # ⇒ cộng **giá trị tuyệt đối** của mọi phần tử

// # 2. **Đếm số phần tử âm (`num_neg`)**

// # * Nếu **chẵn** → có thể đổi dấu để tất cả thành dương
// # * Nếu **lẻ** → buộc phải để lại **1 số âm**

// # 3. Nếu buộc phải để lại 1 số âm
// # → chọn số có **giá trị tuyệt đối nhỏ nhất** (`least_abs`)
// # → để giảm thiệt hại nhỏ nhất

// # ---

// # ### 🔹 Công thức kết quả

// # * `num_neg` **chẵn**
// # 👉 `answer = sum(|a[i][j]|)`
// # * `num_neg` **lẻ**
// # 👉 `answer = sum(|a[i][j]|) − 2 × min(|a[i][j]|)`

// # ---

// # ### 🔹 Độ phức tạp

// # * **Time:** `O(n²)`
// # * **Space:** `O(1)`

// # ---

// # ## 2️⃣ Code của bạn + chú thích chi tiết

// # ```python
// from typing import List
// class Solution:
// def maxMatrixSum(self, a: List[List[int]]) -> int:
// # pos_sum: tổng các giá trị tuyệt đối của toàn bộ ma trận
// pos_sum = 0

// # num_neg: đếm số phần tử âm trong ma trận
// num_neg = 0

// # least_abs: giá trị tuyệt đối nhỏ nhất trong ma trận
// least_abs = float('inf')

// # Duyệt từng phần tử trong ma trận
// for row in a:
// for x in row:
// # Nếu x là số âm
// if x < 0:
// num_neg += 1 # tăng số lượng số âm
// x = -x # lấy trị tuyệt đối

// # Cập nhật giá trị tuyệt đối nhỏ nhất
// if x < least_abs:
// least_abs = x

// # Cộng trị tuyệt đối vào tổng
// pos_sum += x

// # Nếu số phần tử âm là chẵn
// # => có thể đổi dấu để tất cả đều dương
// if num_neg % 2 == 0:
// return pos_sum
// else:
// # Nếu số phần tử âm là lẻ
// # => bắt buộc phải có 1 số âm
// # => trừ đi 2 * least_abs để giảm tổng ít nhất
// return pos_sum - least_abs * 2

// ## 3️⃣ Ví dụ minh họa nhanh

// # ```
// # a = [[1, -2],
// # [-3, -4]]
// # ```

// # * |1| + |2| + |3| + |4| = **10**
// # * num_neg = 3 (lẻ)
// # * least_abs = 1

// # ➡️ `10 − 2 × 1 = 8`

// # ---

// # ## 4️⃣ Ghi nhớ khi đi phỏng vấn / thi LeetCode

// # > ❝ Không cần mô phỏng thao tác đổi dấu
// # > Chỉ cần quan tâm **tính chẵn/lẻ của số âm** ❞

// # ---

// # Nếu bạn muốn mình:

// # * ✍️ Viết **giải thích ngắn gọn 3–4 dòng** để nộp bài
// # * 🧠 Chứng minh vì sao **không thể loại bỏ hết số âm khi lẻ**
// # * ⚡ Viết phiên bản **C++ / Java**

// # 👉 cứ nói, mình hỗ trợ tiếp đúng gu của bạn 👍

// # # ---

// # # ### 1️⃣ Mô tả bài toán

// # # Bạn được cho **một ma trận vuông** kích thước `n x n`, trong đó mỗi phần
// tử là **số nguyên** (có thể âm, dương hoặc 0).

// # # Bạn được phép thực hiện **vô số lần** thao tác sau:

// # # 👉 **Chọn hai ô kề nhau (chung cạnh)** và **đổi dấu cả hai số**

// # # * Số dương → âm
// # # * Số âm → dương

// # # Mục tiêu:
// # # ➡️ **Tính tổng lớn nhất có thể của tất cả các phần tử trong ma trận sau
// khi thực hiện các thao tác hợp lệ.**

// # # ---

// # ### 2️⃣ Ý nghĩa quan trọng của thao tác

// # * Mỗi lần thao tác **đổi dấu 2 số cùng lúc**
// # * Tổng số phần tử âm trong ma trận **chỉ thay đổi chẵn lần**
// # → **Tính chẵn/lẻ của số lượng số âm là yếu tố quyết định**

// # ---

// # ### 3️⃣ Tư duy chính để giải bài này

// # #### 🔹 Bước 1: Lấy trị tuyệt đối

// # Vì ta có thể đổi dấu, nên **giá trị lớn nhất mỗi ô có thể đạt được là trị
// tuyệt đối của nó**

// # ➡️ Tính:

// # ```
// # sum = tổng |a[i][j]|
// # ```

// # ---

// # #### 🔹 Bước 2: Đếm số phần tử âm

// # * Gọi `negCount` = số phần tử âm ban đầu
// # * Tìm `minAbs` = **giá trị tuyệt đối nhỏ nhất** trong ma trận

// # ---

// # ### 4️⃣ Hai trường hợp quan trọng

// # #### ✅ Trường hợp 1: `negCount` là **số chẵn**

// # 👉 Ta có thể biến **tất cả số âm thành dương**

// # ➡️ **Kết quả = sum**

// # ---

// # #### ❌ Trường hợp 2: `negCount` là **số lẻ**

// # 👉 Luôn phải còn **ít nhất 1 số âm**
// # 👉 Ta nên để số âm đó có **giá trị tuyệt đối nhỏ nhất** để thiệt hại ít
// nhất

// # ➡️ **Kết quả = sum − 2 × minAbs**

// # (vì thay vì +minAbs ta bị −minAbs → mất 2×minAbs)

// # ---

// # ### 5️⃣ Ví dụ minh họa

// # #### Ví dụ:

// # ```
// # matrix = [
// # [ 1, -2],
// # [-3, 4]
// # ]
// # ```

// # * |1| + |−2| + |−3| + |4| = **10**
// # * Số âm = 2 (chẵn)

// # ➡️ **Đáp án = 10**

// # ---

// # #### Ví dụ khác:

// # ```
// # matrix = [
// # [ 1, -2],
// # [-3, -4]
// # ]
// # ```

// # * Tổng trị tuyệt đối = 10
// # * Số âm = 3 (lẻ)
// # * minAbs = 1

// # ➡️ **Đáp án = 10 − 2×1 = 8**

// # ---

// # ### 6️⃣ Tóm tắt ngắn gọn (rất hay thi)

// # * Luôn cộng **trị tuyệt đối**
// # * Nếu số âm **chẵn** → lấy hết
// # * Nếu số âm **lẻ** → trừ `2 × minAbs`

// # ---

// # Nếu bạn muốn:

// # * ✔️ Giải thích **vì sao thao tác đổi dấu hoạt động như vậy**
// # * ✔️ Code Java / C++ / Python
// # * ✔️ Phân tích test khó

// # 👉 cứ nói, mình giải tiếp đúng kiểu bạn hay học 👍

// # “Minimize Maximum Pair Sum in Array(24/01/2026)
// # Ok 👍 mình sẽ **giải thích thuật toán + thêm chú thích trực tiếp vào code** để bạn nhìn là hiểu liền (chuẩn kiểu học LeetCode).
// Ok, đoạn Java này dùng **Counting Sort + Two Pointers** (không sort trực tiếp). Mình giải thích **từng ý tưởng → từng khối code** cho dễ nắm 👇
// Ok👍mình**thêm hàm`main`dùng`Scanner`**và**chú thích đầy đủ từng phần code**để bạn có thể**copy chạy trực tiếp**và cũng dễ học thuật toán.

// ---

// ##✅Code hoàn chỉnh(có`main`,có chú thích)

// ```java

import java.util.*;

public class b158 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Nhập số phần tử
        int n = sc.nextInt();
        int[] nums = new int[n];

        // Nhập mảng
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int result = minPairSum(nums);

        // In kết quả
        System.out.println(result);

        sc.close();

    }

    // Hàm giải bài toán Minimize Maximum Pair Sum
    public static int minPairSum(int[] nums) {

        // Tìm giá trị lớn nhất trong mảng
        int maxVal = 0;
        for (int n : nums) {
            maxVal = Math.max(maxVal, n);
        }

        // Mảng đếm tần suất (Counting Sort)
        // arr[x] = số lần x xuất hiện trong nums
        int[] arr = new int[maxVal + 1];
        for (int n : nums) {
            arr[n]++;
        }

        // Hai con trỏ:
        // l: số nhỏ nhất còn lại
        // r: số lớn nhất còn lại
        int l = 0, r = maxVal;
        int ans = 0; // lưu tổng lớn nhất nhỏ nhất có thể

        // Ghép cặp khi còn ít nhất 2 giá trị khác nhau
        while (l < r) {

            // Bỏ qua các số không còn xuất hiện
            while (l < r && arr[l] == 0)
                l++;
            while (l < r && arr[r] == 0)
                r--;

            // Cập nhật tổng lớn nhất của các cặp
            ans = Math.max(ans, l + r);

            // Giảm tần suất sau khi ghép
            if (arr[l] < arr[r]) {
                // Dùng hết số l
                arr[r] -= arr[l];
                l++;
            } else if (arr[l] > arr[r]) {
                // Dùng hết số r
                arr[l] -= arr[r];
                r--;
            } else {
                // arr[l] == arr[r] → dùng hết cả hai
                l++;
                r--;
            }
        }

        return ans;
    }

}

// ##🧪

// Ví dụ input/output

// **Input**

// ```4 3 5 2 3```

// **Output**

// ```7```

// ---

// ##🧠
// Tóm tắt
// tư duy
// thuật toán

// *
// Không sort
// trực tiếp*Dùng**
// Counting Sort***Dùng**
// Two Pointers***
// Luôn ghép:

// >**
// số nhỏ nhất+
// số lớn nhất**

// ➡️
// Đảm bảo**
// tổng lớn
// nhất là
// nhỏ nhất
// có thể**

// ---

// ##📌
// Khi nào
// nên dùng
// cách này?

// ✔Khi`nums[i]`**không quá lớn**✔
// Muốn tối
// ưu hơn`O(n log n)`
// ❌ Không nên dùng nếu giá trị lên tới `10^9`

// ---

// Nếu bạn muốn mình:

// * ✍️ Rút gọn code cho đi thi
// * 🔄 Viết lại theo phong cách dễ nhớ
// * 🧠 So sánh với solution sort truyền thống

// 👉 nói mình tiếp nha 😄

// ---

// ## 🧠 Ý tưởng chính của thuật toán

// Thay vì:

// * sort mảng `nums` mất `O(n log n)`

// Ta làm:

// * **đếm tần suất xuất hiện của mỗi giá trị**
// * rồi dùng **hai con trỏ** `l` (nhỏ nhất còn lại) và `r` (lớn nhất còn lại)
// * ghép `l + r` để **giảm tổng lớn nhất**

// ➡️ Vẫn là tư duy:

// > **Ghép số nhỏ nhất với số lớn nhất**

// nhưng tối ưu hơn khi giá trị số **không quá lớn**.

// ---

// ## 🧩 Giải thích chi tiết từng phần code

// ### 1️⃣ Tìm giá trị lớn nhất trong mảng

// ```java
// int minMax = 0;
// for(int n : nums){
// minMax = Math.max(n, minMax);
// }
// ```

// 📌 Mục đích:

// * Biết **giá trị lớn nhất**
// * để tạo mảng đếm `arr` có kích thước vừa đủ

// ---

// ### 2️⃣ Tạo mảng đếm tần suất (Counting Array)

// ```java
// int[] arr = new int[minMax + 1];
// for(int n : nums){
// arr[n]++;
// }
// ```

// 📌 `arr[x]` = số lần số `x` xuất hiện trong `nums`

// Ví dụ:

// ```
// nums = [2,3,3,5]
// arr[2]=1, arr[3]=2, arr[5]=1
// ```

// ---

// ### 3️⃣ Khởi tạo 2 con trỏ

// ```java
// int l = 0, r = minMax, ans = 0;
// ```

// * `l`: trỏ tới **số nhỏ nhất còn tồn tại**
// * `r`: trỏ tới **số lớn nhất còn tồn tại**
// * `ans`: lưu **tổng lớn nhất trong các cặp**

// ---

// ### 4️⃣ Vòng lặp ghép cặp

// ```java
// while (l < r) {
// ```

// ➡️ Khi còn ít nhất **2 số khác nhau** để ghép

// ---

// ### 5️⃣ Bỏ qua các số không còn tồn tại

// ```java
// while (arr[l] == 0) l++;
// while (arr[r] == 0) r--;
// ```

// 📌 Vì ta không dùng sort, nên phải:

// * **dịch con trỏ** đến số còn xuất hiện

// ---

// ### 6️⃣ Cập nhật tổng lớn nhất

// ```java
// ans = Math.max(ans, l + r);
// ```

// 📌 Đây là:

// * tổng của **cặp nhỏ nhất + lớn nhất**
// * cặp “xấu nhất” hiện tại

// ---

// ### 7️⃣ Giảm tần suất sau khi ghép

// ```java
// if (arr[r] > arr[l])
// arr[r] -= arr[l++];
// else if (arr[l] > arr[r])
// arr[l] -= arr[r--];
// else {
// l++;
// r--;
// }
// ```

// Giải thích từng trường hợp:

// #### 🔹 `arr[r] > arr[l]`

// * Ghép **tất cả `l`** với `r`
// * `l` dùng hết → `l++`
// * `r` vẫn còn

// #### 🔹 `arr[l] > arr[r]`

// * Ghép **tất cả `r`** với `l`
// * `r` dùng hết → `r--`
// * `l` vẫn còn

// #### 🔹 `arr[l] == arr[r]`

// * Ghép hết cả hai
// * dịch cả `l++` và `r--`

// 📌 Đây là cách **mô phỏng việc ghép nhiều cặp cùng lúc**.

// ---

// ### 8️⃣ Trả kết quả

// ```java
// return ans;
// ```

// ➡️ `ans` chính là **maximum pair sum nhỏ nhất có thể**

// ---

// ## 📊 Độ phức tạp

// * **Time:** `O(n + max(nums))`
// * **Space:** `O(max(nums))`

// ⚠️ Chỉ nên dùng cách này khi:

// * Giá trị `nums[i]` **không quá lớn**

// ---

// ## 🆚 So sánh nhanh với cách sort

// | Cách | Time | Khi nào dùng |
// | --------------------- | --------------- | ---------------------- |
// | Sort + 2 pointers | `O(n log n)` | Phổ biến, dễ code |
// | Counting + 2 pointers | `O(n + maxVal)` | Tối ưu khi giá trị nhỏ |

// ---

// ## 🧠 Câu nhớ nhanh

// > **Không sort số, sort bằng tần suất – ghép nhỏ nhất với lớn nhất**

// Nếu bạn muốn:

// * ✍️ Viết lại code **dễ đọc hơn**
// * 🔄 So sánh với solution Python
// * 🧪 Chạy ví dụ từng bước

// 👉 cứ nói mình nhé 😄

// # ---

// # ## 🧠 Ý tưởng thuật toán (Greedy + Two Pointers)

// # **Mục tiêu:**
// # Ghép các số thành cặp sao cho **tổng lớn nhất trong các cặp là nhỏ nhất**.

// # ### Tư duy:

// # * Sau khi **sắp xếp**, số **nhỏ nhất** và **lớn nhất** là hai cực trị
// # * Nếu không ghép chúng lại với nhau, số lớn sẽ phải ghép với số khác → tổng
// còn lớn hơn
// # * Vì vậy, chiến lược tối ưu là:

// # 👉 **Luôn ghép số nhỏ nhất còn lại với số lớn nhất còn lại**

// # ---

// # ## ⏱️ Độ phức tạp

// # * **Sort:** `O(n log n)`
// # * **Two pointers:** `O(n)`
// # * **Tổng:** `O(n log n)`
// # * **Bộ nhớ:** `O(1)` (không tính sort)

// # ---

// # ## ✅ Code có chú thích chi tiết

// # ```python
// class Solution:
// def minPairSum(self, nums: List[int]) -> int:
// # Biến lưu kết quả: tổng lớn nhất nhỏ nhất có thể
// res = 0

// # Con trỏ trái (phần tử nhỏ nhất)
// l = 0

// # Con trỏ phải (phần tử lớn nhất)
// r = len(nums) - 1

// # Sắp xếp mảng để dễ ghép cặp
// nums.sort()

// # Ghép cặp khi còn ít nhất 2 phần tử
// while l < r:
// # Tính tổng của cặp nhỏ nhất + lớn nhất
// pair_sum = nums[l] + nums[r]

// # Cập nhật tổng lớn nhất trong các cặp đã ghép
// res = max(res, pair_sum)

// # Di chuyển con trỏ vào trong
// l += 1
// r -= 1

// # Trả về tổng lớn nhất nhỏ nhất có thể
// return res
// # ```

// # ---

// # ## 📌 Ví dụ chạy tay nhanh

// # ```
// # nums = [3, 5, 2, 3]
// # sort → [2, 3, 3, 5]

// # (2,5) → 7 → res = 7
// # (3,3) → 6 → res = 7

// # return 7
// # ```

// # ---

// ## 🧩 Ghi nhớ nhanh khi gặp bài này

// # * **Sort**
// # * **Two pointers**
// # * **Minimize max → pair small + large**
// # * Thuộc nhóm bài: `Greedy + Two Pointers`

// # ---

// # Nếu bạn muốn mình:

// # * ✍️ Viết **comment ngắn gọn kiểu đi thi**
// # * 🔄 So sánh với cách ghép sai để hiểu sâu hơn
// # * 🧠 Viết lại bằng **Java / C++**

// # 👉 nói mình nhé 😄

// # ---

// # ## 1️⃣ Đề bài nói gì?

// # Bạn được cho **một mảng số nguyên** `nums` có **số phần tử chẵn**.

// # 👉 Nhiệm vụ của bạn là:

// # * **Ghép các phần tử thành từng cặp** (mỗi phần tử chỉ dùng **1 lần**)
// # * Với mỗi cặp, tính **tổng của cặp đó**
// # * Trong tất cả các cặp, lấy **tổng lớn nhất**
// # * Cuối cùng, hãy **làm sao để tổng lớn nhất này là nhỏ nhất có thể**

// # 📌 Tức là:

// # > *Tối ưu cách ghép cặp sao cho cặp “tệ nhất” cũng không quá lớn.*

// # ---

// # ## 2️⃣ Ví dụ minh hoạ

// # ### Ví dụ:

// # ```
// # nums = [3, 5, 2, 3]
// # ```

// # ### Bước 1: Sắp xếp mảng

// # ```
// # [2, 3, 3, 5]
// # ```

// # ### Bước 2: Ghép cặp sao cho hợp lý

// # * Ghép **nhỏ nhất + lớn nhất**

// # ```
// # (2, 5) → tổng = 7
// # (3, 3) → tổng = 6
// # ```

// # ### Bước 3: Lấy tổng lớn nhất

// # ```
// # max = 7
// # ```

// # 👉 **Kết quả = 7**

// # ---

// # ## 3️⃣ Vì sao phải ghép nhỏ nhất với lớn nhất?

// # Nếu bạn ghép:

// # ```
// # (5, 3) và (3, 2)
// # → tổng = 8 và 5 → max = 8 ❌
// # ```

// # So với:

// # ```
// # (5, 2) và (3, 3)
// # → tổng = 7 và 6 → max = 7 ✅
// # ```

// # ➡️ Ghép **số lớn với số nhỏ** giúp **giảm sự chênh lệch**, từ đó **giảm
// tổng lớn nhất**.

// # 📌 Đây là một dạng **greedy strategy** (tham lam nhưng đúng).

// # ---

// # ## 4️⃣ Tóm tắt ý chính (rất hay dùng khi đi phỏng vấn / LeetCode)

// # * Mảng có số phần tử **chẵn**
// # * Sắp xếp mảng
// # * Dùng **2 con trỏ**:

// # * `left` (nhỏ nhất)
// # * `right` (lớn nhất)
// # * Mỗi lần:

// # * Tính `nums[left] + nums[right]`
// # * Cập nhật `maxSum`
// # * `left++`, `right--`

// # ---

// # ## 5️⃣ Ý tưởng ngắn gọn để nhớ

// # > **Muốn cặp lớn nhất nhỏ đi → ghép số lớn với số nhỏ**

// # ---

// # Nếu bạn muốn:

// # * ❓ Giải bằng **Java / C++ / Python**
// # * ❓ Giải thích theo **tư duy thuật toán cho người mới**
// # * ❓ So sánh với bài **Two Pointers / Greedy**

// # 👉 cứ nói mình làm tiếp nha 😄

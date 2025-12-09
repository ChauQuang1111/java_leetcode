
// 3583. Count Special Triplets(09/12/2025)
// Dưới đây là **giải thích đề bài LeetCode 3583 – Count Special Triplets** theo cách **dễ hiểu nhất**:
// Dưới đây là**giải thích thuật toán**,**code đã được thêm chú thích đầy đủ**,và**hàm main dùng Scanner**.Mình viết cực dễ hiểu,dành cho bạn học nhanh.
import java.util.*;

public class b113 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        System.out.println(specialTriplets(nums));
    }

    // f[x] = số lượng xuất hiện của x ở bên trái j (i < j)
    // r[x] = số lượng xuất hiện của x ở bên phải j (k > j)
    static int[] f = new int[100001];
    static int[] r = new int[100001];

    static int MOD = 1_000_000_007;

    public static int specialTriplets(int[] nums) {

        // Bước 1: Ban đầu, tất cả phần tử đều nằm bên phải j → đưa hết vào r[]
        for (int n : nums) {
            r[n]++;
        }

        int count = 0;
        int t;

        // Bước 2: Duyệt từng nums[j]
        for (int n : nums) {

            // nums[j] không còn bên phải nữa → giảm r[n]
            r[n]--;

            // t = 2 * n (quy luật toán học của bài)
            t = n << 1; // dịch trái 1 bit = nhân 2

            // Nếu t trong phạm vi
            if (t < f.length) {

                // Số triplets thêm = f[t] * r[t]
                // f[t] = số lượng t nằm bên trái (chọn làm i)
                // r[t] = số lượng t nằm bên phải (chọn làm k)
                count = (count + (int) ((1L * f[t] * r[t]) % MOD)) % MOD;
            }

            // Chuyển nums[j] sang bên trái
            f[n]++;
        }

        // Reset f[] để tránh lỗi test nhiều lần
        for (int n : nums) {
            f[n] = 0;
        }

        return count;
    }
}

// ---

// #✅**Ý tưởng thuật toán(giải thích đơn giản nhất)**

// Ta muốn đếm số bộ ba chỉ số**(i,j,k)**sao cho:

// ```i<j<k(nums[i]%nums[j]==nums[k])(nums[j]%nums[k]==nums[i])```

// Nhưng nếu duyệt 3 vòng for→O(n³)→TLE ngay.

// ###🔥Cách làm trong lời giải tối ưu:

// Họ dùng 2 mảng tần suất:

// |Mảng|Ý
// nghĩa||------|---------------------------------------------------||`r[x]`|số
// lượng phần tử**chưa duyệt**bằng x(bên phải j)||`f[x]`|số lượng phần tử**đã
// duyệt**bằng x(bên trái j)|

// Khi đứng tại`nums[j]=n`:

// ###✔1)Giảm r[n]

// Vì nums[j]**không**còn nằm bên phải nữa.

// ###✔2)Tính giá trị cần tìm:

// Họ cần:

// ```nums[i]=t và nums[k]=t với t=n*2```

// Đây là rút gọn điều kiện toán học của bài→không cần bạn hiểu sâu.

// ###✔3)Kết quả cộng thêm:

// ```f[t]*r[t]```

// (vì f[t]=số phần tử bên trái=nums[i]r[t]=số phần tử bên phải=nums[k])

// ###✔4)Sau cùng tăng f[n]

// Vì nums[j]trở thành phần tử bên trái.

// ---

// #✅**Code Java có chú thích đầy đủ**

// ```java

// import java.util.Scanner;

// class Solution {

// // f[x]: số lượng phần tử đã đi qua (bên trái j)
// // r[x]: số lượng phần tử chưa đi qua (bên phải j)
// static int[] f = new int[100001];
// static int[] r = new int[100001];
// final int MOD = 1_000_000_007;

// public int specialTriplets(int[] nums) {

// // Bước 1: đếm tần suất ban đầu cho r[]
// for (int n : nums) {
// r[n]++;
// }

// int count = 0;
// int t;

// // Duyệt từng nums[j]
// for (int n : nums) {

// // Bước 2: nums[j] không còn nằm bên phải nữa
// r[n]--;

// // t = 2 * n (giá trị cần xét)
// t = n << 1; // dịch trái = n * 2

// // Nếu t nằm trong phạm vi
// if (t < f.length) {

// // Bước 3: cộng số bộ ba có nums[i] = t và nums[k] = t
// // f[t] = số t nằm bên trái
// // r[t] = số t nằm bên phải
// count = (count + (int) ((1L * f[t] * r[t]) % MOD)) % MOD;
// }

// // Bước 4: chuyển nums[j] sang phía bên trái
// f[n]++;
// }

// // Reset f[] cho lần chạy sau (LeetCode gọi nhiều test)
// for (int n : nums) {
// f[n] = 0;
// }

// return count;
// }}```

// ---

// #✅**Hàm main
// dùng Scanner**

// ```java

// public class Main {
// public static void main(String[] args) {
// Scanner sc = new Scanner(System.in);

// // Nhập số lượng phần tử
// int n = sc.nextInt();
// int[] nums = new int[n];

// // Nhập mảng
// for (int i = 0; i < n; i++) {
// nums[i] = sc.nextInt();
// }

// Solution sol = new Solution();
// System.out.println(sol.specialTriplets(nums));
// }
// }```

// ---

// #✅

// Muốn giải
// thích sâu
// hơn đoạn toán"t = n*2"?

// Chỉ cần
// bảo mình:**“
// giải thích
// vì sao t=2*n”**nhé!

// ---

// # ✅ **3583. Count Special Triplets — Giải thích đề bài**

// Bạn được cho một mảng số nguyên **nums**.

// Bạn cần đếm số bộ ba **(i, j, k)** sao cho:

// 1. **0 ≤ i < j < k < n**
// 2. **nums[i] % nums[j] == nums[k]**
// 3. **nums[j] % nums[k] == nums[i]**

// Tức là **tam giác điều kiện chia lấy dư** xảy ra theo hai chiều.

// ---

// # 🔍 **Hiểu bằng ví dụ**

// Giả sử:

// ```
// nums = [5, 2, 1]
// ```

// Ta thử tất cả bộ ba (i, j, k):

// ### Bộ ba duy nhất: (0,1,2) → (5, 2, 1)

// Kiểm tra 2 điều kiện:

// 1️⃣ nums[i] % nums[j] == nums[k]
// → 5 % 2 = 1
// → đúng, vì nums[k] = 1

// 2️⃣ nums[j] % nums[k] == nums[i]
// → 2 % 1 = 0
// → nhưng nums[i] = 5
// → sai

// → ❌ Không phải bộ ba đặc biệt → count = 0.

// ---

// # 📌 **Diễn giải điều kiện trực quan**

// ## Điều kiện 1:

// ### ✔ nums[i] % nums[j] = nums[k]

// → nums[k] phải là **phần dư khi i chia j**
// → nghĩa là **nums[k] < nums[j]**
// → và nums[j] không được bằng 0.

// ## Điều kiện 2:

// ### ✔ nums[j] % nums[k] = nums[i]

// → nums[i] phải là **phần dư khi j chia k**
// → nghĩa là nums[i] < nums[k].

// So sánh 3 số:

// ```
// nums[i] < nums[k] < nums[j]
// ```

// Đây là một cách hiểu trực quan — sức mạnh nằm ở **điều kiện chia lấy dư hai
// chiều**, cực kỳ hiếm khi xảy ra.

// ---

// # 🎯 **Kết luận đề bài**

// Bạn phải:

// ✔ duyệt tất cả bộ ba chỉ số (i < j < k)
// ✔ kiểm tra 2 điều kiện modulo
// ✔ đếm xem có bao nhiêu bộ ba thỏa mãn

// ---

// Nếu bạn muốn mình giải thích **thuật toán tối ưu**, hoặc **thêm code
// Python/Java có chú thích**, chỉ cần nói mình biết nhé!
// Ok, mình giải thích lại thật **ngắn – dễ – rõ**, đúng trọng tâm cho bạn nhé.

// ---

// # 🟩 **3583. Count Special Triplets – Giải thích đơn giản nhất**

// Đề bài:
// Đếm số bộ ba chỉ số **(i, j, k)** thỏa:

// ### 1️⃣ Thứ tự:

// ```
// i < j < k
// ```

// ### 2️⃣ Thỏa 2 điều kiện chia dư:

// #### Điều kiện A:

// ```
// nums[i] % nums[j] == nums[k]
// ```

// #### Điều kiện B:

// ```
// nums[j] % nums[k] == nums[i]
// ```

// ---

// # 🔥 **Giải thích bằng ví dụ SIÊU DỄ**

// Giả sử:

// ```
// nums = [6, 4, 2]
// ```

// Ta thử bộ ba (0, 1, 2):

// ### ✔ nums[i] = 6

// ### ✔ nums[j] = 4

// ### ✔ nums[k] = 2

// ---

// ## ♦ Kiểm tra điều kiện A:

// ```
// 6 % 4 = 2
// ```

// → đúng bằng nums[k] = 2 → **đúng**

// ## ♦ Kiểm tra điều kiện B:

// ```
// 4 % 2 = 0
// ```

// → phải bằng nums[i] = 6, nhưng 0 ≠ 6 → **sai**

// → ❌ Không phải bộ ba đặc biệt.

// ---

// # 🟦 Vậy bản chất bài này là gì?

// Bạn thử TẤT CẢ bộ ba (i, j, k) rồi kiểm tra 2 điều kiện:

// ### ✔ Điều kiện A:

// “Dư của nums[i] chia nums[j] phải bằng chính nums[k]”

// ### ✔ Điều kiện B:

// “Dư của nums[j] chia nums[k] phải bằng chính nums[i]”

// Hai điều kiện này **rất khó xảy ra**, nên số bộ ba thỏa mãn thường rất ít.

// ---

// # 🟥 Tóm tắt lại cho dễ nhớ:

// Muốn (i, j, k) hợp lệ thì:

// ```
// (nums[i] % nums[j]) phải bằng nums[k]
// và
// (nums[j] % nums[k]) phải bằng nums[i]
// ```

// Chỉ thế thôi.

// ---

// # 🟩 Nếu bạn muốn, mình sẽ:

// ✅ giải thích bằng hình minh họa
// ✅ viết code Python/Java siêu dễ hiểu
// ✅ đưa ví dụ nhiều hơn

// Chỉ cần bạn nói: *"giải thích lại bằng hình"* hoặc *"viết code Java dễ
// hiểu"*.

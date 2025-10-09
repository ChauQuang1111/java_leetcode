
// 3494. Find the Minimum Amount of Time to Brew Potions(09/10/2025)
import java.util.*;

class b62 {
   static Scanner sc = new Scanner(System.in);

   public static void main(String[] args) {

      int n = sc.nextInt();
      int[] skill = new int[n];
      for (int i = 0; i < n; i++) {
         skill[i] = sc.nextInt();
      }

      int m = sc.nextInt();
      int[] mana = new int[m];
      for (int i = 0; i < m; i++) {
         mana[i] = sc.nextInt();
      }

      long result = minTime(skill, mana);

      System.out.println(result);
   }

   public static long minTime(int[] skill, int[] mana) {
      int n = skill.length; // Số lượng kỹ năng
      int m = mana.length; // Số lượng mana

      // Tạo mảng cộng dồn skill để tính tổng nhanh hơn
      long[] acc = new long[n + 1];
      for (int i = 0; i < n; i++) {
         acc[i + 1] = acc[i] + skill[i];
      }

      long t = 0; // Lưu giá trị tối ưu ở bước trước
      long t2 = 0; // Lưu giá trị tạm thời ở bước hiện tại

      // Duyệt qua từng giai đoạn mana
      for (int j = 1; j < m; j++) {
         t2 = 0;
         for (int i = 0; i < n; i++) {
            // Cập nhật giá trị lớn nhất có thể đạt được
            t2 = Math.max(t2, t + mana[j - 1] * acc[i + 1] - mana[j] * acc[i]);
         }
         t = t2; // Cập nhật lại giá trị t
      }

      // Kết quả cuối cùng
      return t + mana[m - 1] * acc[n];
   }

}

// Dưới đây là **giải thích đề bài** LeetCode **3494. Find the Minimum Amount of
// Time to Brew Potions**, cùng với các điều kiện, yêu cầu và một số hướng gợi ý
// để giải.
// Dưới đây là phiên bản đầy đủ của chương trình Java có**hàm`main`**,sử
// dụng**`Scanner`để nhập dữ liệu**,và có**chú thích chi tiết từng phần**giúp
// bạn hiểu rõ:

// ```java

// import java.util.*;

// public class Main {
// public static void main(String[] args) {
// Scanner sc = new Scanner(System.in);

// // Nhập số phần tử của mảng skill
// int n = sc.nextInt();
// int[] skill = new int[n];
// for (int i = 0; i < n; i++) {
// skill[i] = sc.nextInt(); // Nhập từng giá trị skill
// }

// // Nhập số phần tử của mảng mana
// int m = sc.nextInt();
// int[] mana = new int[m];
// for (int i = 0; i < m; i++) {
// mana[i] = sc.nextInt(); // Nhập từng giá trị mana
// }

// // Tạo đối tượng Solution và gọi hàm minTime
// Solution sol = new Solution();
// long result = sol.minTime(skill, mana);

// // In ra kết quả
// System.out.println(result);
// }
// }

// class Solution {
// public long minTime(int[] skill, int[] mana) {
// int n = skill.length; // Số lượng kỹ năng
// int m = mana.length; // Số lượng mana

// // Tạo mảng cộng dồn skill để tính tổng nhanh hơn
// long[] acc = new long[n + 1];
// for (int i = 0; i < n; i++) {
// acc[i + 1] = acc[i] + skill[i];
// }

// long t = 0; // Lưu giá trị tối ưu ở bước trước
// long t2 = 0; // Lưu giá trị tạm thời ở bước hiện tại

// // Duyệt qua từng giai đoạn mana
// for (int j = 1; j < m; j++) {
// t2 = 0;
// for (int i = 0; i < n; i++) {
// // Cập nhật giá trị lớn nhất có thể đạt được
// t2 = Math.max(t2, t + mana[j - 1] * acc[i + 1] - mana[j] * acc[i]);
// }
// t = t2; // Cập nhật lại giá trị t
// }

// // Kết quả cuối cùng
// return t + mana[m - 1] * acc[n];
// }}```

// ---

// ###🧩

// Giải thích
// ngắn gọn:

// *`acc[i]`là**tổng kỹ năng**
// của các
// phần tử`skill[0..i-1]`.*
// Vòng lặp`for(
// int j = 1;j<m;j++)`
// mô phỏng**
// từng giai
// đoạn sử
// dụng mana
// khác nhau**.*
// Biểu thức`t+mana[j-1]*acc[i+1]-mana[j]*acc[i]`tìm**
// giá trị
// tối ưu**
// khi chuyển
// giữa hai
// mức mana
// liên tiếp.*
// Cuối cùng, trả
// về tổng
// thời gian
// tối thiểu
// có thể
// đạt được
// sau khi
// xử lý
// toàn bộ.

// ---

// Bạn có
// muốn tôi thêm**
// ví dụ input/output mẫu**và**
// giải thích
// cách chương
// trình chạy
// cụ thể**không?

// ---

// ## 🧩 Đề bài (tóm tắt)

// Bạn có:

// * `n` **wizard** (phù thủy), gọi là `wizard[0]`, `wizard[1]`, …,
// `wizard[n−1]`.
// * `m` **potion** cần được “pha chế” theo thứ tự (cần pha potion 0 trước rồi
// potion 1, v.v.).
// * Mỗi potion `j` có **mana capacity** là `mana[j]`.
// * Mỗi wizard `i` có một **kỹ năng (skill) hoặc tốc độ** (giả sử là `skill[i]`
// hoặc giá trị tương ứng) — tùy theo cách đặt tên trong đề bài.
// * Yêu cầu: mỗi potion khi được pha chế sẽ đi qua **từng wizard theo thứ tự**
// (wizard 0 → wizard 1 → … → wizard n−1).

// Cụ thể: để hoàn thành potion `j`, wizard 0 bắt đầu, rồi wizard 1 tiếp tục,
// v.v.
// Mỗi wizard `i` cần thời gian **`skill[i] * mana[j]`** để xử lý potion đó (ví
// dụ nhân kỹ năng và mana).

// Tuy nhiên, có một ràng buộc quan trọng:

// > Khi một wizard hoàn thành việc xử lý potion `j`, wizard tiếp theo bắt đầu
// **không sớm hơn** thời điểm mà wizard trước đó hoàn thành các potion trước
// đó, để đảm bảo **tuần tự công việc** không bị lật ngược.

// Bạn cần tìm **thời gian tối thiểu T** sao cho **tất cả m potion đều được pha
// chế xong** (qua tất cả wizard) trong thời gian ≤ T.

// ---

// ## ⚙️ Những điều cần lưu ý / ràng buộc

// * Potion phải được pha theo thứ tự: potion 0 trước, rồi 1, rồi 2...
// * Wizard phải xử lý potion `j` chỉ sau khi đã xử lý potion `j-1`.
// * Wizard i không thể bắt đầu potion j trước thời điểm wizard i−1 hoàn thành
// potion j (vì potion j phải đi qua tất cả wizard theo thứ tự).
// * Giá trị `skill[i]`, `mana[j]`, và số lượng wizard/potion đều có thể lớn —
// cần tìm cách tối ưu, không brute force một cách thô.

// ---

// ## 🧠 Hướng giải ý tưởng

// Một cách tiếp cận khả thi là:

// 1. **Binary Search** thời gian `T`.
// Giả sử bạn đặt thời gian tối đa là `T`, kiểm tra xem có thể hoàn thành tất cả
// potion trong ≤ `T` không.

// 2. Cách kiểm tra (simulate / greedy):

// * Duyệt từng potion `j` theo thứ tự
// * Duyệt wizard theo thứ tự và tính thời gian mỗi wizard xử lý
// * Theo ràng buộc “không bắt đầu trước”, giữ cho thời gian bắt đầu của wizard
// i cho potion j ≥ thời gian hoàn thành wizard (i−1) cho cùng potion j
// * Nếu tổng thời gian vượt `T` → không thể
// * Nếu tất cả potion thực hiện xong ≤ `T`, thì `T` khả thi

// 3. Tìm giá trị thấp nhất `T` bằng binary search.

// ---

// Nếu bạn muốn, mình có thể gửi **phiên bản code Python hoặc Java hoàn chỉnh**
// cho bài này, kèm **chú thích chi tiết từng bước**, để bạn hiểu cách thực thi.
// Bạn muốn mình gửi code kèm giải thích không?
// Rồi, ta cùng **phân tích chi tiết** thuật toán trong đoạn code này 👇

// ---

// ### 🧩 **Đề bài tóm tắt (theo cách hiểu của code)**

// Có hai mảng:

// * `skill[i]`: kỹ năng hoặc độ khó của loại thuốc thứ `i`.
// * `mana[j]`: lượng năng lượng cần để pha thuốc ở giai đoạn thứ `j`.

// Ta cần tìm **thời gian nhỏ nhất (hoặc năng lượng nhỏ nhất)** để hoàn thành
// toàn bộ quá trình pha chế thuốc — qua nhiều giai đoạn — sao cho tổng thời
// gian là **nhỏ nhất có thể**.

// ---

// ### 📘 **Giải thích từng phần trong code**

// #### 1️⃣ Tính prefix sum (`acc`)

// ```java
// long[] acc = new long[n + 1];
// for (int i = 0; i < n; i++) {
// acc[i + 1] = acc[i] + skill[i];
// }
// ```

// ➡️ `acc[i]` là tổng kỹ năng của các potion **từ 0 đến i-1**
// Ví dụ:

// ```
// skill = [2, 3, 5]
// acc = [0, 2, 5, 10]
// ```

// Tức là:

// * Tổng skill đến potion 1 = 2
// * Tổng skill đến potion 2 = 5
// * Tổng skill đến potion 3 = 10

// Prefix sum giúp ta nhanh chóng tính được tổng `skill` trong bất kỳ đoạn nào.

// ---

// #### 2️⃣ Khởi tạo biến

// ```java
// long t = 0, t2 = 0;
// ```

// * `t`: thời gian (hoặc chi phí) ít nhất đến giai đoạn `j-1`.
// * `t2`: thời gian tạm tính cho giai đoạn hiện tại `j`.

// ---

// #### 3️⃣ Vòng lặp qua các giai đoạn pha chế (`mana[j]`)

// ```java
// for (int j = 1; j < m; j++) {
// t2 = 0;
// for (int i = 0; i < n; i++) {
// t2 = Math.max(t2, t + mana[j - 1] * acc[i + 1] - mana[j] * acc[i]);
// }
// t = t2;
// }
// ```

// Giải thích chi tiết:

// * Giai đoạn `j` phụ thuộc vào giai đoạn trước (`j-1`).
// * Ở mỗi bước, ta **chọn vị trí chia i** (vì có thể chuyển từ potion `i` này
// sang potion `i+1` kia).
// Tức là, ta xét xem nên dừng giai đoạn trước ở đâu để tổng chi phí là nhỏ
// nhất.

// Công thức:

// ```
// t2 = max( t + mana[j-1]*acc[i+1] - mana[j]*acc[i] )
// ```

// Ý nghĩa:

// * `t`: thời gian tích lũy đến giai đoạn `j-1`
// * `mana[j-1] * acc[i+1]`: chi phí hoàn thành potion đến `i` trong giai đoạn
// trước
// * `mana[j] * acc[i]`: chi phí bắt đầu pha potion `i` trong giai đoạn hiện tại

// → Lấy **hiệu giữa hai phần đó** để biết nếu ta chia ranh giới tại potion `i`,
// thì tổng thời gian sẽ thay đổi thế nào.
// → Dùng `Math.max` vì ta cần chọn điểm dừng (chia) tối ưu nhất.

// ---

// #### 4️⃣ Sau khi qua hết các giai đoạn

// ```java
// return t + mana[m - 1] * acc[n];
// ```

// * `t`: thời gian tối ưu đã tính đến giai đoạn `m-1`
// * `mana[m - 1] * acc[n]`: chi phí để pha toàn bộ potion ở giai đoạn cuối.

// ---

// ### 🧮 **Ví dụ minh họa**

// Giả sử:

// ```
// skill = [2, 3]
// mana = [5, 2]
// ```

// → `acc = [0, 2, 5]`

// * Ban đầu `t = 0`
// * Ở j = 1:

// * i = 0 → `t2 = max(0, 0 + 5*2 - 2*0) = 10`
// * i = 1 → `t2 = max(10, 0 + 5*5 - 2*2) = max(10, 21) = 21`
// → `t = 21`

// Cuối cùng:

// ```
// t + mana[m-1] * acc[n] = 21 + 2 * 5 = 31
// ```

// → Kết quả: **31**

// ---

// ### ⚙️ **Tóm tắt ý tưởng**

// * Tính prefix sum `acc` để dễ tính tổng skill.
// * Duyệt từng giai đoạn (mỗi `mana[j]`) và tìm điểm chia tối ưu để **giảm tổng
// thời gian**.
// * Công thức `t + mana[j-1]*acc[i+1] - mana[j]*acc[i]` đảm bảo cân bằng giữa
// chi phí trước và sau.
// * Cuối cùng cộng phần chi phí còn lại ở giai đoạn cuối.

// ---

// Nếu bạn muốn, tôi có thể **vẽ sơ đồ trực quan (diagram)** minh họa cách các
// giai đoạn và công thức hoạt động — bạn muốn chứ?

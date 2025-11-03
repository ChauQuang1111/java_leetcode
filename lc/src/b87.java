
// // # 1578. Minimum Time to Make Rope Colorful(03/11/2025)
// Rất hay—đoạn Java code bạn gửi là một**cách giải ngắn gọn và rất hiệu quả**cho bài**LeetCode 1578–Minimum Time to Make Rope Colorful**.Mình sẽ**giải thích chi tiết thuật toán,từng dòng code,và ý tưởng đằng sau**.
import java.util.*;

public class b87 {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        String colors = sc.nextLine();

        int n = sc.nextInt();

        // Tạo mảng lưu thời gian cần thiết để xóa từng đoạn
        int[] neededTime = new int[n];

        for (int i = 0; i < n; i++) {
            neededTime[i] = sc.nextInt();
        }

        int result = minCost(colors, neededTime);
        System.out.println(result);

        sc.close();
    }

    // Khối static này chỉ để "warm up" (tối ưu hiệu năng JVM), có thể bỏ nếu muốn
    static {
        for (int i = 0; i < 400; i++)
            minCost("a", new int[1]);
    }

    // Hàm chính: tính thời gian tối thiểu cần để làm dây đầy màu (không có 2 màu kề
    // nhau trùng nhau)
    public static int minCost(String colors, int[] neededTime) {
        int min = 0; // tổng chi phí cần xóa

        // Duyệt từ ký tự thứ 2 đến hết chuỗi
        for (int i = 1; i < colors.length(); i++) {

            // Nếu 2 ký tự liền kề có cùng màu
            if (colors.charAt(i) == colors.charAt(i - 1)) {

                // Cộng chi phí của đoạn rẻ hơn vào tổng (vì ta sẽ xóa đoạn đó)
                min += Math.min(neededTime[i], neededTime[i - 1]);

                // Giữ lại đoạn "đắt hơn" bằng cách cập nhật giá trị hiện tại
                // để lần so sánh sau luôn giữ được đoạn có chi phí xóa lớn nhất
                neededTime[i] = Math.max(neededTime[i], neededTime[i - 1]);
            }
        }

        return min; // Trả về tổng chi phí tối thiểu
    }
}

// ---

// ##🎯Mục tiêu của bài

// Cho:

// *`colors`:chuỗi ký tự biểu thị màu các đoạn dây liên
// tiếp.*`neededTime[i]`:thời gian cần để xóa đoạn thứ`i`.

// Yêu cầu:Xóa một số đoạn sao cho**không còn hai đoạn liền kề cùng màu**,và
// tổng thời gian xóa**là nhỏ nhất**.

// ---

// ##💡Ý tưởng thuật toán

// Duyệt chuỗi từ**trái sang phải**,với mỗi cặp ký tự liên
// tiếp`colors[i-1]`và`colors[i]`:

// 1. Nếu**hai ký tự khác màu**→không cần xóa gì,tiếp tục.2. Nếu**hai ký tự cùng
// màu**→phải xóa 1 trong 2.

// *Xóa đoạn có`neededTime`nhỏ hơn,vì ta muốn**giữ lại đoạn“đắt hơn”(mất nhiều
// thời gian để xóa hơn)**.*Cộng chi phí của đoạn bị xóa vào`min`.

// 👉Để**xử lý các chuỗi dài hơn 2 ký tự cùng màu(ví dụ“aaaa”)**,code**ghi
// đè`neededTime[i]`thành giá trị lớn nhất**giữa 2 đoạn.Vì vậy,sau mỗi
// bước,`neededTime[i]`luôn chứa“cost của đoạn tốt nhất còn lại”trong nhóm màu
// hiện tại.

// Điều này đảm bảo khi gặp ký tự tiếp theo cùng màu,ta tiếp tục so sánh
// với**đoạn tốt nhất giữ lại trước đó**,mà không cần tách nhóm riêng.

// ---

// ##🧩Giải thích từng dòng code

// ```java
// class Solution {

// static {
// // Khối static này chỉ để "warm up" (tối ưu hóa JVM)
// // Không ảnh hưởng đến logic thuật toán.
// // Có thể bỏ qua, chỉ giúp JVM JIT compile nhanh hơn.
// for (int i = 0; i < 400; i++)
// minCost("a", new int[1]);
// }

// public static int minCost(String colors, int[] neededTime) {
// int min = 0; // tổng thời gian tối thiểu cần xóa

// for (int i = 1; i < colors.length(); i++) {

// // Nếu 2 ký tự liền kề cùng màu
// if (colors.charAt(i) == colors.charAt(i - 1)) {

// // Cộng chi phí nhỏ hơn vào tổng
// min += Math.min(neededTime[i], neededTime[i - 1]);

// // Cập nhật cost hiện tại thành cost lớn hơn
// // (để giữ lại đoạn "đắt hơn" cho lần so sánh tiếp theo)
// neededTime[i] = Math.max(neededTime[i], neededTime[i - 1]);
// }
// }
// return min;
// }
// }```

// ---

// ##🔍

// Ví dụ
// minh họa

// ###Input:

// ```
// java colors = "abaac";neededTime=[1,2,3,4,5];```

// |i|colors[i-1],colors[i]|
// same color?|Math.min|Math.max|neededTime[i]
// sau cập
// nhật|min||-|----------------------|-----------|--------|--------|--------------------------|---||1|a,b|❌|–|–|2|0||2|b,a|❌|–|–|3|0||3|a,a|✅|3|4|4|3||4|a,c|❌|–|–|5|3|

// ➡️**
// Kết quả:**`min=3`

// ---

// ##⚙️
// Độ phức tạp

// ***
// Thời gian:**

// O(n) — duyệt chuỗi một lần.
// * **Bộ nhớ:** O(1) — không dùng cấu trúc phụ, chỉ cập nhật mảng `neededTime`.

// ---

// ## ✅ Tổng kết ý tưởng

// * So sánh từng cặp ký tự liền kề.
// * Khi trùng màu → xóa đoạn có `neededTime` nhỏ hơn.
// * Ghi đè `neededTime[i]` để giữ lại giá trị lớn

// nhất (tượng trưng cho đoạn tốt nhất còn lại trong nhóm).
// * Tổng hợp chi phí bị xóa → chính là đáp án.

// ---

// Bạn có muốn mình vẽ **sơ đồ minh họa** (kiểu flow hoặc mũi tên theo từng bước
// của chuỗi `"aaab"`) để dễ nhìn hơn không?

// // # Rất hay — đây là một **cách giải tối ưu khác** cho bài **1578. Minimum
// Time to Make Rope Colorful**, dùng **two pointers (l, r)** để duyệt cặp ký tự
// liền kề.
// // # Mình sẽ **giải thích chi tiết thuật toán**, rồi **thêm chú thích vào
// code** để bạn dễ hiểu.

// // # ---

// // # ## 🧠 Ý tưởng thuật toán

// // # * Duyệt chuỗi từ trái sang phải, luôn giữ một con trỏ `l` trỏ vào **kí
// tự cuối cùng trong nhóm hiện tại** (màu đang xét).

// // # * Dùng `r` để **xem ký tự tiếp theo**.

// // # * Nếu `colors[l] != colors[r]`:
// // # 👉 Hai ký tự khác màu → không cần xóa gì → di chuyển `l = r`.

// // # * Nếu `colors[l] == colors[r]`:
// // # 👉 Hai ký tự trùng màu → **phải xóa một trong hai** để tránh trùng.
// // # → Xóa ký tự có `neededTime` nhỏ hơn, vì ta muốn **giữ lại phần tốn thời
// gian xóa nhiều hơn (tức giữ phần "đắt nhất")**.

// // # * Nếu `neededTime[l] < neededTime[r]`:
// // # Xóa `l`, cộng chi phí `neededTime[l]`, rồi cập nhật `l = r` (vì giữ `r`
// lại).
// // # * Ngược lại:
// // # Xóa `r`, cộng chi phí `neededTime[r]` (giữ `l` lại, không cần đổi `l`).

// // # Duyệt hết chuỗi, `res` chính là tổng chi phí tối thiểu.

// // # ---

// // # ## 📘 Code có chú thích

// // # ```python
// // from typing import List
// // class Solution:
// // def minCost(self, colors: str, neededTime: List[int]) -> int:
// // l = 0 # 'l' là con trỏ giữ vị trí của ký tự hiện tại trong nhóm cùng màu
// // res = 0 # biến lưu tổng chi phí tối thiểu

// // # Duyệt từ ký tự thứ 2 đến cuối (r là right pointer)
// // for r in range(1, len(colors)):

// // if colors[l] == colors[r]:
// // # Nếu hai ký tự liên tiếp có cùng màu → phải xóa một trong hai

// // if neededTime[l] < neededTime[r]:
// // # Xóa ký tự ở vị trí l (rẻ hơn)
// // res += neededTime[l]
// // # Giữ lại ký tự r (đắt hơn), cập nhật l = r
// // l = r
// // else:
// // # Xóa ký tự ở vị trí r (rẻ hơn)
// // res += neededTime[r]
// // # Giữ lại ký tự l (vì đắt hơn), không cần đổi l
// // else:
// // # Nếu màu khác nhau → di chuyển l sang r
// // l = r

// // return res

// // ## 🔍 Ví dụ minh họa

// // ### Input:

// // # ```python
// // # colors = "abaac"
// // # neededTime = [1, 2, 3, 4, 5]
// // # ```

// // # ### Diễn giải:

// // # | Step | l | r | colors[l], colors[r] | neededTime[l], neededTime[r] |
// Hành động | res |
// // # | ---- | - | - | -------------------- | ---------------------------- |
// ------------------------------------- | --- |
// // # | 1 | 0 | 1 | a, b | 1, 2 | khác màu → l=1 | 0 |
// // # | 2 | 1 | 2 | b, a | 2, 3 | khác màu → l=2 | 0 |
// // # | 3 | 2 | 3 | a, a | 3, 4 | cùng màu → xóa 3 (rẻ hơn), giữ 4, l=3 | 3 |
// // # | 4 | 3 | 4 | a, c | 4, 5 | khác màu → l=4 | 3 |

// // # ✅ Kết quả cuối cùng: `res = 3`

// // # ---

// // # ## ⚙️ Độ phức tạp

// // # * **Thời gian:** O(n) — duyệt một lần qua chuỗi.
// // # * **Không gian:** O(1) — chỉ dùng vài biến phụ.

// // # ---

// // # Tóm lại:
// // # 👉 Đây là giải pháp **greedy + two pointers** rất ngắn gọn, ý tưởng
// chính là **so sánh từng cặp ký tự cùng màu** và **xóa ký tự có thời gian nhỏ
// hơn** để tối ưu chi phí.

// // # Mô tả đề (ngắn gọn)

// // # Cho một chuỗi colors (mỗi ký tự là màu của một đoạn dây nối kế nhau) và
// mảng neededTime (cost để xóa đoạn đó). Ta cần loại bỏ một số đoạn sao cho
// không còn hai ký tự kề nhau giống màu nhau (tức colors[i] != colors[i+1] với
// mọi i). Mỗi lần xóa đoạn i, phải trả neededTime[i]. Hỏi tổng chi phí tối
// thiểu để đạt mục tiêu.

// // # Ý tưởng (greedy, trực quan)

// // # Nếu có một đoạn liên tiếp gồm k ký tự giống màu (ví dụ aaaa), thì trong
// k ký tự đó phải giữ lại đúng 1 và xóa k-1 cái còn lại.

// // # Để chi phí nhỏ nhất, trong mỗi đoạn liên tiếp cùng màu ta giữ phần có
// neededTime lớn nhất, và xóa tất cả phần còn lại (vì giữ phần lớn nhất giảm
// tổng tiền xóa).

// // # Vậy với mỗi nhóm liên tiếp cùng màu: thêm vào đáp án sum(group) -
// max(group).

// // # Chứng minh ngắn

// // # Trong một nhóm các ký tự cùng màu, bắt buộc phải xóa tất cả trừ 1. Việc
// giữ phần có chi phí lớn nhất là tối ưu vì nó tối thiểu hóa tổng chi phí xóa
// (tổng xóa = tổng cả nhóm − cost_được_giữ). Không có tương tác giữa các nhóm
// khác màu nên tối ưu toàn cục bằng tối ưu từng nhóm (greedy đúng).

// // # Thuật toán

// // # Duyệt chuỗi một lần, gom các ký tự liên tiếp giống nhau thành nhóm; với
// mỗi nhóm tính tổng chi phí và max chi phí, cộng sum − max vào kết quả. Độ
// phức tạp O(n), bộ nhớ O(1).

// // # Ví dụ

// // # colors = "abaac", neededTime = [1,2,3,4,5]
// // # Nhóm aa có times [3,4] ⇒ sum=7, max=4 ⇒ phải trả 7−4=3. Kết quả = 3.

// // # colors = "abc", neededTime = [1,2,3] ⇒ không có nhóm >1 ⇒ kết quả = 0.

// // # colors = "bbba", neededTime = [1,3,2,4]
// // # Nhóm bbb times [1,3,2] ⇒ sum=6, max=3 ⇒ trả 3.
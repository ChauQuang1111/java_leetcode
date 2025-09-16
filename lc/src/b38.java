
// // public # 2197. Replace Non-Coprime Numbers in Array(16/09/2025)
import java.util.*;

public class b38 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Nhập số phần tử
        int n = sc.nextInt();
        int[] nums = new int[n];

        // Nhập mảng nums
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        List<Integer> ans = replaceNonCoprimes(nums);

        // In kết quả
        for (int x : ans) {
            System.out.print(x + " ");
        }
        sc.close();
    }

    // Hàm chính giải bài toán
    public static List<Integer> replaceNonCoprimes(int[] nums) {
        int n = nums.length;

        // Mô phỏng stack bằng mảng
        int[] stack = new int[n];
        int top = -1; // con trỏ "đỉnh" stack, ban đầu rỗng

        for (int num : nums) {
            // Khi còn phần tử trên stack và gcd > 1 thì hợp nhất
            while (top != -1) {
                int x = gcd(stack[top], num);
                if (x == 1)
                    break; // nếu coprime thì dừng
                num *= stack[top--] / x; // hợp nhất bằng LCM
            }

            // push num vào stack
            stack[++top] = num;
        }

        // Chuyển stack thành danh sách kết quả
        List<Integer> result = new ArrayList<>(top + 1);
        for (int i = 0; i <= top; ++i) {
            result.add(stack[i]);
        }

        return result;
    }

    // Hàm gcd (Ước số chung lớn nhất)
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a; // điều kiện dừng
        } else {
            return gcd(b, a % b); // gọi đệ quy
        }
    }

}

// Ok mình
// sẽ minh
// họa thật
// chi tiết
// với ví
// dụ nhỏ nhé👇

// ---

// ###
// Giả sử
// sau khi
// chạy xong
// thuật toán
// ta có:

// ```stack=[12,7,6,?,?,?] // mảng ban đầu có size = n
// top=2 // phần tử cuối cùng nằm ở index = 2
// ```

// 👉
// Nghĩa là
// stack thực
// sự chỉ chứa 3
// phần tử
// hợp lệ:

// ```stack[0]=12 stack[1]=7 stack[2]=6```

// ---

// ###
// Bước tạo`ArrayList`:

// ```
// java List<Integer>result=new ArrayList<>(top+1);```

// *
// Ở đây`top=2`→`top+1=3`.*
// Ta cấp phát`ArrayList`có**
// sức chứa 3
// phần tử**(dung lượng
// ban đầu = 3).*
// Điều này
// hợp lý
// vì stack
// thực sự có 3
// phần tử.

// ---

// ###
// Vòng lặp copy:

// ```java for(
// int i = 0;i<=top;++i)
// {
// result.add(stack[i]);
// }```

// *i=0→add 12*i=1→add 7*i=2→add 6

// 👉
// Kết quả:

// ```result=[12,7,6]```

// ---

// 📌
// Nếu không có`+1`(
// tức là`new ArrayList<>(top)`):

// *Với`top=2`,
// ta sẽ tạo`ArrayList`
// dung lượng = 2.
// *
// Nhưng thực
// tế có 3
// phần tử→khi`add`
// phần tử thứ 3,`ArrayList`
// sẽ tự
// động resize.*
// Thuật toán
// vẫn chạy đúng,
// chỉ là
// tốn thêm
// bước resize
// không cần thiết.

// ---

// 👉Vậy**`+1`
// để biểu
// diễn đúng
// số phần
// tử thực
// tế trong

// stack (từ index 0 → top, tức `top + 1` phần tử)** và tối ưu cấp phát bộ nhớ.

// ---

// Bạn có muốn mình viết thêm 1 đoạn **print debug stack & result** để thấy rõ
// quá trình copy từ `stack` sang `result` không?
// Mình giải thích ngắn gọn và rõ ràng nhé 👍

// Đoạn code này:

// ```java
// List<Integer> result = new ArrayList<>(top + 1);
// for (int i = 0; i <= top; ++i) {
// result.add(stack[i]);
// }
// return result;
// ```

// ### 🎯 Mục đích chính:

// 1. **Chuyển dữ liệu từ mảng `stack` sang `List<Integer>`**

// * Trong quá trình xử lý, bạn dùng mảng `stack[]` để mô phỏng stack.
// * Sau khi duyệt xong, các phần tử hợp lệ nằm trong `stack[0..top]`.
// * Nhưng đề bài yêu cầu trả về `List<Integer>`, không phải mảng.

// 2. **`new ArrayList<>(top + 1)`**

// * `top` là chỉ số cuối → số phần tử thực tế = `top + 1`.
// * Dùng `top + 1` để cấp phát dung lượng vừa đủ, tránh resize không cần thiết.

// 3. **Vòng lặp for**

// * Copy từng phần tử từ `stack[0]` đến `stack[top]` sang `result`.

// 4. **`return result;`**

// * Trả về danh sách kết quả cuối cùng đúng định dạng yêu cầu.

// ---

// 📌 Tóm lại:
// Đoạn này chỉ có **một mục đích duy nhất** → **chuyển đổi kết quả

// cuối cùng (đang nằm trong mảng `stack`) sang dạng `List<Integer>` để trả
// về**.

// ---

// Bạn có muốn mình viết thử **phiên bản trả về `int[]` thay vì
// `List<Integer>`** (ngắn gọn hơn) để so sánh không?

// // À👍,

// // câu hỏi
// // rất hay.

// // Ở đây
// // có dòng:

// // ```
// // java List<Integer>result=new ArrayList<>(top+1);```

// // ---

// // ###
// // Vì sao phải`+1`?

// // *`top`là**chỉ số (index)** phần tử cuối trong stack.
// // * Nếu stack có `k` phần tử, thì `top = k - 1`.

// // 👉 Ví dụ:

// // * Nếu stack có 3

// // phần tử (`stack[0], stack[1], stack[2]`) thì `top = 2`.
// // * Số phần tử thực tế trong stack = `top + 1`.

// // ---

// // ### Tác dụng trong `ArrayList<>(top + 1)`

// // * `new ArrayList<>(top + 1)` chỉ là **dự trữ dung lượng ban đầu** cho
// `ArrayList`.
// // * Nó không bắt buộc, vì `ArrayList` có thể tự động tăng kích thước.
// // * Nhưng viết `top + 1` sẽ **tối ưu**: cấp phát đúng số phần tử cần, tránh
// phải resize.

// // ---

// // 📌 Tóm lại:

// // * `top` là chỉ số cuối → số phần tử trong stack = `top + 1`.
// // * Dùng `new ArrayList<>(top + 1)` để tạo danh sách với sức chứa đúng bằng
// số phần tử stack.

// // ---

// // Bạn có muốn mình minh họa thử với ví dụ nhỏ (`stack = [12, 7, 6]`, `top =
// 2`) để thấy rõ `top + 1 = 3` không?

// // Ok👍

// // mình sẽ
// // giải thích
// // thuật toán
// // của đoạn
// // code trên
// // theo từng
// // bước. Đây là bài**2197.
// // Replace Non-Coprime Numbers
// // in Array**(LeetCode).

// // ---

// // ##🎯
// // Ý tưởng
// // bài toán

// // *
// // Ta có
// // một mảng`nums`.*
// // Nếu có
// // hai số
// // liền kề**
// // không nguyên
// // tố cùng nhau**(
// // tức là`gcd>1`),
// // thì ta**
// // hợp nhất**
// // chúng thành**LCM**(bội chung
// // nhỏ nhất).*
// // Sau khi
// // hợp nhất, số
// // mới sinh
// // ra có
// // thể lại
// // tiếp tục
// // không nguyên
// // tố cùng
// // nhau với
// // số phía trước→
// // tiếp tục
// // hợp nhất.*
// // Làm đến
// // khi không
// // còn hợp
// // nhất được
// // nữa thì
// // giữ kết
// // quả cuối.

// // ---

// // ##🔑
// // Giải thích
// // thuật toán
// // trong code

// // ###1.
// // Chuẩn bị
// // cấu trúc
// // dữ liệu

// // ```java
// // int[] stack = new int[n];
// // int top = -1;```

// // *
// // Ở đây dùng**mảng+biến`top`**
// // để giả lập**stack**.*`top=-1`
// // nghĩa là
// // stack rỗng
// // ban đầu.

// // ---

// // ###2.
// // Duyệt từng
// // phần tử trong`nums`

// // ```java for(
// // int num:nums)
// // {
// // while (top != -1) {
// // int x = gcd(stack[top], num);
// // if (x == 1)
// // break;
// // num *= stack[top--] / x;
// // }
// // stack[++top] = num;
// // }```

// // Giải thích:

// // *
// // Với mỗi`num`trong`nums`,
// // ta so
// // sánh nó
// // với phần
// // tử trên

// // đỉnh stack (`stack[top]`).
// // * Nếu **coprime** (`gcd == 1`) → không gộp được → thoát vòng lặp.
// // * Nếu **không coprime** (`gcd > 1`) → hợp nhất thành **LCM**.

// // ---

// // ### 3. Công thức hợp nhất bằng LCM

// // ```java
// // num *= stack[top--] / x;
// // ```

// // * `x = gcd(stack[top], num)`

// // * LCM công thức:

// // $$
// // LCM(a, b) = \frac{a \times b}{gcd(a, b)}
// // $$

// // * Ở đây:

// // * `stack[top]` chính là **a**,
// // * `num` chính là **b**,
// // * `x` chính là `gcd(a, b)`.

// // 👉 Vì ta đang giữ `num` là b, nên viết:

// // ```java
// // num = num * (stack[top] / gcd(a, b));
// // ```

// // * Sau đó `top--` để bỏ phần tử

// // cũ đi (vì đã gộp).

// // ---

// // ### 4. Push số đã gộp vào stack

// // ```java
// // stack[++top] = num;
// // ```

// // * Nếu không gộp được nữa, hoặc đã gộp xong, ta đưa `num` vào stack.

// // ---

// // ### 5. Kết quả

// // ```java
// // List<Integer> result = new ArrayList<>(top + 1);
// // for (int i = 0; i <= top; ++i) {
// // result.add(stack[i]);
// // }
// // ```

// // *

// // Chuyển stack (đang chứa kết quả cuối cùng) sang `ArrayList` để trả về.

// // ---

// // ## 📌 Ví dụ minh họa

// // Input:

// // ```
// // nums = [6, 4, 3, 2, 7, 6]
// // ```

// // Bước thực hiện:

// // 1. Push 6 → stack = \[6]
// // 2. Xét 4: gcd(6,4)=2 > 1 → LCM(6,4)=12 → stack = \[12]
// // 3. Xét 3: gcd(12,3)=3 > 1 → LCM(12,3)=12 → stack = \[12]
// // 4. Xét 2: gcd(12,2)=2 > 1 → LCM(12,2)=12 → stack = \[12]
// // 5. Xét 7: gcd(12,7)=1 → không gộp → stack = \[12, 7]
// // 6. Xét 6: gcd(7,6)=1 → không gộp → stack = \[12, 7, 6]

// // 👉 Kết quả cuối: `[12, 7, 6]`

// // ---

// // Bạn có muốn mình vẽ **timeline stack**

// // từng bước (kiểu push/pop trực quan) để dễ nhìn hơn không?

// // # Ok mình giải thích bài **LeetCode 2197. Replace Non-Coprime Numbers in
// // Array** cho bạn nhé 👇
// // from typing import List
// // from math import gcd, lcm

// // class Solution:
// // def replaceNonCoprimes(self, nums: List[int]) -> List[int]:
// // """Thuật toán hợp nhất số không nguyên tố cùng nhau bằng LCM."""
// // ans = [] # stack lưu kết quả tạm
// // curr = nums[0] # phần tử đang xử lý

// // # duyệt qua các phần tử từ index 1 trở đi
// // for x in nums[1:]:
// // if gcd(curr, x) > 1: # nếu curr và x không nguyên tố cùng nhau
// // curr = lcm(curr, x) # gộp thành LCM

// // # sau khi gộp, kiểm tra xem curr có gộp tiếp được với phần tử cuối stack
// // không
// // while ans and gcd(curr, ans[-1]) > 1:
// // curr = lcm(curr, ans.pop()) # pop ra, gộp tiếp
// // else:
// // # nếu curr và x nguyên tố cùng nhau → đẩy curr vào stack
// // ans.append(curr)
// // curr = x # cập nhật curr = x để xử lý tiếp

// // # push phần tử cuối cùng vào stack
// // ans.append(curr)

// // return ans
// // # Ok 👍 mình giải thích chi tiết thuật toán trong đoạn code này nhé.

// // # ---

// // # ## Mục tiêu đề bài

// // # Bạn có một mảng số nguyên `nums`. Bạn cần **thay thế dãy các số liên
// tiếp
// // không nguyên tố cùng nhau (non-coprime)** bằng **BCNN (LCM)** của chúng,
// lặp
// // lại cho đến khi không còn cặp nào như vậy.

// // # Ví dụ:

// // # ```
// // # nums = [6, 4, 3, 2, 7, 6, 2]
// // # ```

// // # * `6` và `4` không nguyên tố cùng nhau → gộp thành `LCM(6,4)=12`
// // # * Sau đó `12` và `3` cũng không nguyên tố cùng nhau → gộp thành
// // `LCM(12,3)=12`
// // # * Tiếp tục so với `2`, gộp tiếp → `LCM(12,2)=12`
// // # * … cuối cùng ra `[12,7,6,2]`

// // # ---

// // # ## Ý tưởng thuật toán

// // # 1. Dùng **stack (`ans`)** để lưu kết quả tạm.
// // # 2. Dùng biến `curr` lưu số đang xét.
// // # 3. Với mỗi phần tử `x` trong `nums`:

// // # * Nếu `gcd(curr, x) > 1` → chúng có ước chung → gộp lại thành `lcm(curr,
// // x)`.
// // # * Sau khi gộp, có thể phải **gộp tiếp** với phần tử cuối của stack (vì
// nó
// // có thể không nguyên tố cùng nhau với `curr`).
// // # * Nếu `gcd(curr, x) == 1` → không gộp được, push `curr` vào stack, rồi
// // chuyển `curr = x`.
// // # 4. Sau vòng lặp, push `curr` vào stack.
// // # 5. Trả về stack (`ans`).

// // # ---

// // # ## Minh họa với ví dụ

// // # ```
// // # nums = [6,4,3,2,7,6,2]
// // # ```

// // # * `curr = 6`, stack = \[]
// // # * Xét `x = 4`: gcd(6,4)=2>1 → curr=LCM(6,4)=12
// // # stack = \[]
// // # * Xét `x = 3`: gcd(12,3)=3>1 → curr=LCM(12,3)=12
// // # stack = \[]
// // # * Xét `x = 2`: gcd(12,2)=2>1 → curr=LCM(12,2)=12
// // # stack = \[]
// // # * Xét `x = 7`: gcd(12,7)=1 → push curr=12 → stack=\[12], curr=7
// // # * Xét `x = 6`: gcd(7,6)=1 → push curr=7 → stack=\[12,7], curr=6
// // # * Xét `x = 2`: gcd(6,2)=2>1 → curr=LCM(6,2)=6
// // # stack=\[12,7]

// // # Kết thúc → push curr=6 → stack=\[12,7,6]
// // # Trả về `[12,7,6]`. ✅

// // # ---

// // # ## Key Point

// // # * **LCM để gộp**, **stack để nhớ lại các số đã chắc chắn xong**, **while
// // loop để gộp tiếp nếu cần**.

// // # ---

// // # 👉 Bạn có muốn mình vẽ **sơ đồ stack từng bước chạy** để trực quan hơn
// // không?

// // # # ---

// // # # ## 📌 Đề bài (tóm gọn)

// // # # Cho một mảng số nguyên **nums**.

// // # # * Ta sẽ duyệt từ **trái sang phải**.
// // # # * Nếu bắt gặp **2 phần tử liên tiếp không nguyên tố cùng nhau
// // (non-coprime)**, tức là `gcd(a, b) > 1`, thì ta thay chúng bằng **LCM(a,
// b)**
// // (bội chung nhỏ nhất).
// // # # * Sau khi thay, ta tiếp tục kiểm tra lại với phần tử mới và phần tử
// liền
// // trước nó (vì khi hợp nhất xong, phần tử mới có thể lại không nguyên tố
// cùng
// // nhau với phần tử trước đó).
// // # # * Tiếp tục quá trình cho đến khi không còn cặp nào liền kề mà gcd > 1.

// // # # 👉 Yêu cầu: Trả về **mảng cuối cùng**.

// // # # ---

// // # # ## 📌 Ví dụ minh họa

// // # # ### Ví dụ 1

// // # # ```
// // # # nums = [6, 4, 3, 2, 7, 6, 2]
// // # # ```

// // # # * Bước 1: `6` và `4` → gcd(6,4)=2 > 1 → thay bằng lcm(6,4)=12
// // # # Mảng: \[12, 3, 2, 7, 6, 2]

// // # # * Bước 2: `12` và `3` → gcd=3 > 1 → thay bằng lcm(12,3)=12
// // # # Mảng: \[12, 2, 7, 6, 2]

// // # # * Bước 3: `12` và `2` → gcd=2 > 1 → thay bằng lcm(12,2)=12
// // # # Mảng: \[12, 7, 6, 2]

// // # # * Bước 4: `12` và `7` → gcd=1 (ok, giữ nguyên).

// // # # * `7` và `6` → gcd=1 (ok).

// // # # * `6` và `2` → gcd=2 > 1 → thay bằng lcm(6,2)=6
// // # # Mảng: \[12, 7, 6]

// // # # ✅ Kết quả cuối: `[12, 7, 6]`.

// // # # ---

// // # # ### Ví dụ 2

// // # # ```
// // # # nums = [2,2,1,1,3,3,3]
// // # # ```

// // # # * `2` và `2` → gcd=2 → thay bằng lcm=2 → \[2,1,1,3,3,3]
// // # # * `2` và `1` → gcd=1 → giữ nguyên.
// // # # * `1` và `1` → gcd=1 → giữ nguyên.
// // # # * `1` và `3` → gcd=1 → giữ nguyên.
// // # # * `3` và `3` → gcd=3 → thay bằng 3 → \[2,1,1,3,3]
// // # # * `3` và `3` → lại hợp → \[2,1,1,3]

// // # # ✅ Kết quả cuối: `[2, 1, 1, 3]`.

// // # # ---

// // # # ## 📌 Ý tưởng thuật toán

// // # # * Dùng **stack** để lưu kết quả tạm thời.
// // # # * Với mỗi số trong `nums`:

// // # # 1. Push vào stack.
// // # # 2. Trong khi **stack có ≥ 2 phần tử** và gcd(top2) > 1 → pop 2 số,
// thay
// // bằng lcm, rồi push lại.
// // # # * Kết quả cuối cùng chính là stack.

// // # # ⏱️ Độ phức tạp:

// // # # * Mỗi phần tử chỉ push + pop vài lần → O(n log A), với A là giá trị
// tối
// // đa trong nums (do gcd/lcm).

// // # # ---

// // # # 👉 Bạn có muốn mình viết code **Java** kèm comment chi tiết cho bài
// này
// // không?
// // # # Ok 👍 mình giải thích kỹ hơn vì sao lại ra **kết quả cuối** như vậy
// nhé.
// // # # Lấy lại **ví dụ đầu tiên**:

// // # # ```
// // # # nums = [6, 4, 3, 2, 7, 6, 2]
// // # # ```

// // # # ---

// // # # ### 🔎 Bắt đầu duyệt:

// // # # **Bước 1:**

// // # # * Cặp đầu: `(6, 4)`
// // # # * gcd(6,4)=2 > 1 → thay = lcm(6,4)=12
// // # # * Mảng mới: `[12, 3, 2, 7, 6, 2]`

// // # # ---

// // # # **Bước 2:**

// // # # * Xem `(12, 3)`
// // # # * gcd(12,3)=3 > 1 → thay = lcm(12,3)=12
// // # # * Mảng mới: `[12, 2, 7, 6, 2]`

// // # # ---

// // # # **Bước 3:**

// // # # * Xem `(12, 2)`
// // # # * gcd(12,2)=2 > 1 → thay = lcm(12,2)=12
// // # # * Mảng mới: `[12, 7, 6, 2]`

// // # # ---

// // # # **Bước 4:**

// // # # * Xem `(12, 7)`
// // # # * gcd(12,7)=1 → **không gộp**.

// // # # ---

// // # # **Bước 5:**

// // # # * Xem `(7, 6)`
// // # # * gcd(7,6)=1 → **không gộp**.

// // # # ---

// // # # **Bước 6:**

// // # # * Xem `(6, 2)`
// // # # * gcd(6,2)=2 > 1 → thay = lcm(6,2)=6
// // # # * Mảng mới: `[12, 7, 6]`

// // # # ---

// // # # ✅ Không còn cặp nào `gcd > 1` nữa → **kết quả cuối cùng** là:

// // # # ```
// // # # [12, 7, 6]
// // # # ```

// // # # ---

// // # # 👉 Điểm quan trọng:

// // # # * Sau khi gộp một cặp thành **LCM**, phần tử mới đó **có thể lại gộp
// tiếp
// // với phần tử trước đó**.
// // # # * Ta phải kiểm tra liên tục cho đến khi không còn gộp được.

// // # # ---

// // # # Bạn có muốn mình minh họa lại toàn bộ quá trình này bằng **stack (ngăn
// // xếp)** thay vì thay trực tiếp trong mảng (sẽ dễ hình dung hơn) không?
// // # # À 👍 mình giải thích đơn giản nhé:

// // # # ### 📌 LCM là gì?

// // # # * **LCM (Least Common Multiple)** = **Bội chung nhỏ nhất** của hai số.
// // # # * Nghĩa là số **nhỏ nhất > 0** mà **cả hai số đều chia hết**.

// // # # ---

// // # # ### 📌 Ví dụ:

// // # # * LCM(4, 6) = 12
// // # # (vì bội của 4 = 4, 8, 12, 16, ...;
// // # # bội của 6 = 6, 12, 18, ...;
// // # # → bội chung nhỏ nhất là 12).

// // # # * LCM(3, 5) = 15

// // # # * LCM(2, 8) = 8

// // # # ---

// // # # ### 📌 Công thức tính nhanh:

// // # # $$
// // # # \text{LCM}(a, b) = \frac{a \times b}{\text{GCD}(a, b)}
// // # # $$

// // # # Trong đó:

// // # # * **GCD** = Ước số chung lớn nhất (Greatest Common Divisor).
// // # # * Ví dụ:

// // # # * gcd(6, 4) = 2
// // # # * lcm(6, 4) = (6×4)/2 = 12

// // # # ---

// // # # 👉 Trong bài **2197. Replace Non-Coprime Numbers in Array**, ta dùng
// // **LCM** để thay thế 2 số liên tiếp không nguyên tố cùng nhau.

// // # # Bạn có muốn mình viết 1 đoạn **Java code tính GCD và LCM** đơn giản để
// // bạn test thử không?

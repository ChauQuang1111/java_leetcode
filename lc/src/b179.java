// // # Champagne Tower(14/02/2026)

// Ok👍—mình sẽ:

// 1. Thêm**hàm`main`dùng`Scanner`**để nhập dữ liệu 2. Giữ nguyên thuật toán DP 1D 3. Thêm**chú thích chi tiết từng dòng**để bạn học+demo luôn

// ---

// #🧾Code hoàn chỉnh(có`main`+chú thích)

// ```java

import java.util.Scanner;

public class b179 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int poured = sc.nextInt();

        // Nhập hàng cần hỏi
        int row = sc.nextInt();

        // Nhập vị trí ly cần hỏi
        int col = sc.nextInt();

        // Gọi hàm tính toán
        double result = champagneTower(poured, row, col);

        // In kết quả
        System.out.println(result);

        sc.close();
    }

    public static double champagneTower(int poured, int row, int col) {

        // Mảng DP 1 chiều
        // dp[j] = lượng champagne tại cột j của hàng hiện tại
        // +2 để tránh tràn chỉ số khi dùng dp[j+1]
        double[] dp = new double[col + 2];

        // Đổ toàn bộ champagne vào ly trên cùng (hàng 0, cột 0)
        dp[0] = poured;

        // Duyệt từng hàng từ 0 → row-1
        for (int i = 0; i < row; i++) {

            // Duyệt từ phải → trái để tránh ghi đè dữ liệu
            // Chỉ cần duyệt đến min(i, col)
            for (int j = Math.min(i, col); j >= 0; j--) {

                // Nếu ly bị tràn (>1)
                if (dp[j] > 1) {

                    // Phần champagne dư chia đôi
                    double val = (dp[j] - 1) / 2.0;

                    // Ly hiện tại giữ lại phần tràn sau khi chia
                    dp[j] = val;

                    // Ly bên phải nhận thêm phần chia
                    dp[j + 1] += val;

                } else {

                    // Không tràn → hàng dưới không nhận gì
                    dp[j] = 0;
                }
            }
        }

        // Kết quả tối đa là 1 vì ly chỉ chứa tối đa 1
        return Math.min(1, dp[col]);
    }
}
// ---

// #▶️

// Ví dụ
// chạy chương trình

// ###Input

// ```
// Nhap poured:4
// Nhap query_row:2
// Nhap query_glass:1```

// ###Output

// ```
// Luong champagne
// trong ly = 0.5```

// ---

// #🧠
// Tóm tắt
// lại thuật
// toán trong code

// 1.
// Đổ champagne vào`dp[0]`.2.
// Duyệt từng hàng.3.
// Ly nào>1→tràn.4.
// Dư chia 2
// xuống dưới.5.
// Duyệt phải→
// trái để
// không ghi đè.6. Lấy`min(1, dp[col])`.

// ---

// Nếu bạn muốn mình làm tiếp, mình có thể:

// * Viết bản **2D DP dễ hiểu hơn**
// * Vẽ bảng mô phỏng từng hàng
// * Giải thích vì sao cần `col + 2`
// * Thêm nhiều test case trong `main`

// Bạn muốn
// mình mở
// rộng phần nào?

// Dưới đây là phần**giải thích thuật toán+chú thích chi tiết ngay trên code
// Java**của bài**799. Champagne Tower**để bạn hiểu bản chất DP 1 chiều mà đoạn
// code đang dùng👇

// ---

// #🧠Ý tưởng chính của thuật toán

// Ta mô phỏng champagne chảy xuống tháp theo quy tắc:

// *Mỗi ly chứa tối đa**1**.*Phần dư`(x−1)`sẽ chia đôi cho 2 ly dưới.*Chỉ cần
// tính đến**hàng cần hỏi**(`row`).*Chỉ cần tính đến**cột cần hỏi**(`col`).

// 👉Vì mỗi hàng chỉ phụ thuộc hàng trước→dùng**DP 1D**để tối ưu bộ nhớ.

// ---

// #📦Ý nghĩa mảng`dp`

// ```dp[j]=lượng champagne tại ly cột j của hàng hiện tại```

// Ban đầu:

// ```Hàng 0:dp[0]=poured```

// ---

// #🧾Code đã thêm chú thích

// ```java
// class Solution {
// public double champagneTower(int poured, int row, int col) {

// // Tạo mảng DP 1 chiều
// // +2 để tránh tràn index khi cộng dp[j+1]
// double[] dp = new double[col + 2];

// // Đổ toàn bộ champagne vào ly trên cùng
// dp[0] = poured;

// // Duyệt từng hàng từ 0 → row-1
// for(int i = 0; i < row; i++) {

// // Duyệt từ phải → trái để tránh ghi đè dữ liệu
// // Chỉ cần duyệt đến min(i, col)
// for(int j = Math.min(i, col); j >= 0; j--) {

// // Nếu ly bị tràn
// if(dp[j] > 1) {

// // Phần champagne dư
// double val = (dp[j] - 1) / 2.0;

// // Ly hiện tại giữ lại phần tràn chia đôi
// dp[j] = val;

// // Ly bên phải nhận thêm
// dp[j + 1] += val;

// } else {

// // Không tràn → hàng dưới không nhận gì
// dp[j] = 0;
// }
// }
// }

// // Ly chỉ chứa tối đa 1
// return Math.min(1, dp[col]);
// }
// }```

// ---

// #🔄

// Mô phỏng

// dòng chảy (ví dụ)

// ## Ví dụ

// ```
// poured = 4
// row = 2
// col = 1
// ```

// ---

// ### Hàng 0

// ```
// dp = [4]
// ```

// Ly 0 tràn:

// ```
// Dư = 3 → mỗi ly dưới nhận 1.5
// ```

// ---

// ### Hàng 1

// ```
// dp = [1.5, 1.5]
// ```

// Mỗi ly tràn:

// ```
// Dư = 0.5 → chia đôi = 0.25
// ```

// ---

// ### Hàng 2

// ```
// dp = [0.25, 0.5, 0.25]
// ```

// Ly (2,1) = 0.5

// ---

// # ⚙️ Vì sao duyệt từ phải → trái?

// Nếu duyệt trái → phải:

// ```
// dp[j] cập nhật xong
// → ảnh hưởng dp[j+1] (chưa xử lý)
// → sai kết quả
// ```

// Duyệt phải → trái:

// ```
// Giữ nguyên dữ liệu hàng trước
// → tính đúng
// ```

// ---

// # 📊 Độ phức tạp

// * Time:

// ```
// O(row × col)
// ```

// * Space:

// ```
// O(col)
// ```

// (tối ưu hơn 2D DP rất nhiều)

// ---

// # 🆚 So sánh 2 cách DP

// | Cách | Bộ nhớ | Dễ code | Tốc độ |
// | ----- | ------- | ------- | ---------- |
// | 2D DP | O(row²) | Dễ | Trung bình |
// | 1D DP | O(col) | Khó hơn | Nhanh |

// ---

// # 🔑 Tóm tắt bản chất

// 1. Đổ vào đỉnh.
// 2. Ly nào > 1 → tràn.
// 3. Dư chia đôi xuống dưới.
// 4. Lặp đến hàng cần hỏi.
// 5. Kết quả = min(1, lượng trong ly).

// ---

// Nếu bạn muốn, mình có thể:

// * Viết bản **2D dễ hiểu** để so sánh
// * Vẽ bảng DP từng hàng
// * Chuyển sang Python / C++
// * Giải thích tại sao chỉ cần `col + 2`

// Bạn muốn đào sâu phần nào tiếp theo?

// # ## 🥂 Champagne Tower – Giải thích đề bài (LeetCode)

// # Ok 👍 — mình sẽ **giải thích thuật toán + thêm chú thích trực tiếp vào
// code** để bạn đọc là hiểu ngay.

// # ---

// # # 🧠 Ý tưởng thuật toán (trước khi xem code)

// # Bài này mô phỏng champagne chảy xuống tháp.

// # Cách thường gặp:

// # * Dùng mảng 2D `dp[row][col]`

// # Nhưng code bạn gửi **tối ưu bộ nhớ → dùng 1D array**:

// # * `glass[j]` = lượng champagne ở cột `j` của hàng hiện tại.
// # * Cập nhật **từ phải → trái** để tránh ghi đè.

// # Ngoài ra còn tối ưu:

// # * Chỉ tính đến ly cần hỏi (`query_glass`)
// # * Dùng đối xứng tam giác:
// # `query_glass = min(query_glass, query_row - query_glass)`

// # ---

// # # 🧾 Code đã thêm chú thích chi tiết

// # ```python
// class Solution:
// def champagneTower(self, poured, query_row, query_glass):

// # Dùng tính đối xứng của tháp
// # Ví dụ hàng 4: ly 0 và ly 4 giống nhau
// # → chỉ cần tính nửa bên trái
// query_glass = min(query_glass, query_row - query_glass)

// # Mảng 1D lưu lượng champagne của hàng hiện tại
// glass = [0.] * (query_glass + 1)

// # Đổ toàn bộ champagne vào ly trên cùng
// glass[0] = float(poured)

// # c = số ly tối đa cần xét (giới hạn trái)
// c = query_row - query_glass + 1

// # z = mốc dừng sớm khi bên trái đã hết tràn
// z = -1

// # Duyệt từng hàng
// for i in range(query_row):

// # mid = vị trí giữa của hàng i
// mid = i >> 1 # i // 2

// # Nếu giữa vượt quá ly cần tính → giới hạn lại
// if mid >= query_glass:
// mid, i1 = query_glass, 0
// else:
// i1 = i & 1 # kiểm tra i chẵn/lẻ

// # Tính phần tràn ở ly giữa
// excess = max(glass[mid] - 1., 0.)

// if excess:
// # Nếu hàng lẻ → có ly bên phải
// if i1 != 0:
// glass[mid + 1] += excess

// # Ly giữa giữ lại phần tràn chia đôi
// glass[mid] = excess * .5
// else:
// # Nếu giữa không tràn → các ly ngoài cũng không tràn
// return 0.

// # Lan tràn từ giữa → trái
// for j in range(mid - 1, max(i - c, z), -1):

// # Tính phần tràn
// excess = max(glass[j] - 1., 0.) * .5

// if excess:
// # Chia sang phải
// glass[j + 1] += excess

// # Giữ lại nửa
// glass[j] = excess
// else:
// # Nếu không tràn → dừng sớm
// z = j
// break

// # Kết quả tối đa là 1 (ly đầy)
// return min(1., glass[query_glass])
// # ```

// # ---

// # # 🔍 Giải thích từng kỹ thuật tối ưu

// # ## 1️⃣ Đối xứng tháp

// # ```
// # 0
// # 0 1
// # 0 1 2
// # ```

// # Ly trái = ly phải đối xứng.

// # → Chỉ cần tính đến giữa → giảm bộ nhớ + thời gian.

// # ---

// # ## 2️⃣ 1D Dynamic Programming

// # Thay vì:

// # ```
// # dp[row][col]
// # ```

// # Chỉ cần:

// # ```
// # glass[col]
// # ```

// # Vì mỗi hàng chỉ phụ thuộc hàng trước.

// # ---

// # ## 3️⃣ Cập nhật từ giữa → trái

// # Tránh ghi đè dữ liệu chưa dùng.

// # ---

// # ## 4️⃣ Early stop (dừng sớm)

// # Nếu 1 ly không tràn:

// # → Các ly xa hơn cũng không tràn
// # → Không cần tính tiếp.

// # Biến dùng:

// # ```
// # z = mốc dừng
// # ```

// # ---

// # # 📊 Độ phức tạp

// # * Time: ~ O(query_row × query_glass)
// # (nhưng thực tế nhanh hơn nhờ dừng sớm)

// # * Space: O(query_glass)
// # (tối ưu hơn 2D rất nhiều)

// # ---

// # # 🆚 So sánh với cách thường

// # | Cách | Bộ nhớ | Dễ hiểu | Tốc độ |
// # | -------------------- | --------- | -------- | ---------- |
// # | 2D DP | Cao | Dễ | Trung bình |
// # | 1D DP (code bạn) | Thấp | Khó | Nhanh |
// # | 1D + tối ưu đối xứng | Thấp nhất | Khó nhất | Nhanh nhất |

// # ---

// # # Nếu bạn muốn mình làm tiếp, mình có thể:

// # # * Viết lại code **phiên bản dễ hiểu hơn**
// # # * Vẽ flow champagne từng bước
// # # * So sánh với Pascal Triangle
// # # * Chuyển sang Java / C++

// # # Bạn muốn mình giải thích sâu đoạn nào nhất?

// # # ### 1️⃣ Mô tả bài toán (hiểu đơn giản)

// # # Bạn có một **tháp ly champagne xếp hình tam giác**:

// # # * Hàng 0: 1 ly
// # # * Hàng 1: 2 ly
// # # * Hàng 2: 3 ly
// # # * …

// # Mỗi ly **chứa tối đa 1 cốc champagne**.

// # Bạn rót vào ly trên cùng một lượng champagne là `poured` (số thực hoặc số
// nguyên).

// # ---

// # ### 2️⃣ Quy tắc tràn (overflow rule)

// # * Nếu một ly **≤ 1** → giữ nguyên, không tràn.
// # * Nếu **> 1** → phần dư sẽ tràn xuống **2 ly bên dưới**:

// # * Trái nhận: `(dư / 2)`
// # * Phải nhận: `(dư / 2)`

// # Ví dụ:

// # * Ly có 1.8 → giữ 1
// # * Dư 0.8 → mỗi ly dưới nhận 0.4

// # ---

// # ### 3️⃣ Yêu cầu đề bài

// # Cho 3 tham số:

// # * `poured` → lượng champagne rót vào ly trên cùng
// # * `query_row` → hàng cần hỏi
// # * `query_glass` → vị trí ly trong hàng đó

// # 👉 Hỏi: **Ly đó đang có bao nhiêu champagne?**
// # (Kết quả tối đa là 1 vì ly đầy là dừng.)

// # ---

// # ### 4️⃣ Ví dụ minh họa

// # #### Ví dụ 1

// # ```
// # poured = 1
// # query_row = 1
// # query_glass = 1
// # ```

// # * Rót 1 vào ly trên → vừa đầy, không tràn
// # * Hàng dưới không nhận gì

// # ➡️ Kết quả: `0`

// # ---

// # #### Ví dụ 2

// # ```
// # poured = 2
// # query_row = 1
// # query_glass = 1
// # ```

// # * Ly trên giữ 1
// # * Dư 1 → chia đôi
// # * Mỗi ly dưới nhận 0.5

// # ➡️ Ly (1,1) có `0.5`

// # ---

// # #### Ví dụ 3 (hiểu bản chất lan truyền)

// # ```
// # poured = 4
// # ```

// # Luồng chảy:

// # * Hàng 0: nhận 4 → giữ 1, dư 3
// # * Hàng 1: mỗi ly nhận 1.5 → giữ 1, dư 0.5
// # * Hàng 2: mỗi nhánh lại chia tiếp…

// # ➡️ Champagne lan dần xuống dưới theo hình tam giác.

// # ---

// # ### 5️⃣ Bản chất thuật toán

// # Đây là bài **mô phỏng + quy hoạch động (DP)**:

// # * Mỗi ly phụ thuộc 2 ly phía trên.
// # * Chỉ quan tâm **phần dư**.
// # * Kết quả cuối cùng lấy `min(1, lượng trong ly)`.

// # ---

// # ### 6️⃣ Công thức tràn

// # Nếu ly `(r, c)` có lượng `x`:

// # ```
// # overflow = max(0, x - 1)

// # Ly (r+1, c) += overflow / 2
// # Ly (r+1, c+1) += overflow / 2
// # ```

// # ---

// # Nếu bạn muốn, mình có thể:

// # * Viết code Java / Python giải bài này
// # * Giải từng bước với ví dụ lớn
// # * Giải thích cách tối ưu bộ nhớ (1D DP)

// # Bạn muốn mình làm phần nào tiếp theo?

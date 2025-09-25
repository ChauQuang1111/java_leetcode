
// 120. Triangle(25/09/2025)
import java.util.*;

public class b47 {
    static Scanner sc = new Scanner(System.in);
    static int m; // số hàng của tam giác
    static Integer[][] dp; // mảng nhớ kết quả (memoization)

    public static void main(String[] args) {
        int n = sc.nextInt();
        List<List<Integer>> triangle = new ArrayList<>();

        // Nhập từng hàng
        for (int i = 0; i < n; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                row.add(sc.nextInt());
            }
            triangle.add(row);
        }

        // Gọi hàm và in kết quả
        int result = minimumTotal(triangle);
        System.out.println(result);

        sc.close();
    }

    // Hàm chính: tính tổng nhỏ nhất
    public static int minimumTotal(List<List<Integer>> tran) {
        m = tran.size(); // lấy số hàng
        dp = new Integer[m][m]; // khởi tạo mảng dp với giá trị null
        return helper(tran, 0, 0); // bắt đầu từ đỉnh tam giác (0,0)
    }

    // Hàm đệ quy có nhớ
    public static int helper(List<List<Integer>> tran, int row, int col) {
        // Nếu đã đi quá hàng cuối thì return 0 (điều kiện dừng)
        if (row == m) {
            return 0;
        }

        // Nếu đã tính trước đó thì trả về luôn (tránh tính lại)
        if (dp[row][col] != null) {
            return dp[row][col];
        }

        // Gọi đệ quy xuống 2 nhánh: thẳng xuống và chéo phải
        int left = helper(tran, row + 1, col);
        int right = helper(tran, row + 1, col + 1);

        // Lưu kết quả vào dp và trả về
        return dp[row][col] = tran.get(row).get(col) + Math.min(left, right);
    }

}

// Ok, mình giải thích đề bài **LeetCode 120. Triangle** nhé:

// ---

// ### Đề bài:

// Bạn được cho một tam giác số (triangle) dưới dạng **list các list** (mỗi hàng
// chứa một số nguyên).

// Ví dụ:

// ```
// [
// [2],
// [3,4],
// [6,5,7],
// [4,1,8,3]
// ]
// ```

// Bạn cần tìm **đường đi từ trên xuống dưới (top to bottom)** sao cho **tổng
// các số trên đường đi là nhỏ nhất**.

// * Ở mỗi bước, bạn chỉ có thể **đi xuống** đến **một trong hai phần tử kề** ở
// hàng tiếp theo.

// * Từ `triangle[i][j]`, bạn có thể đi xuống `triangle[i+1][j]` hoặc
// `triangle[i+1][j+1]`.

// ---

// ### Ví dụ:

// ```
// Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]

// Các đường đi có thể là:
// 2 → 3 → 5 → 1 = 11
// 2 → 3 → 5 → 8 = 18
// 2 → 3 → 6 → 4 = 15
// 2 → 4 → 5 → 1 = 12
// 2 → 4 → 7 → 3 = 16
// ...

// Đường đi nhỏ nhất: 2 → 3 → 5 → 1 = 11

// Output: 11
// ```

// ---

// ### Yêu cầu:

// * Trả về **giá trị nhỏ nhất của tổng đường đi**.
// * Độ phức tạp thời gian phải tốt (O(n²)), vì số hàng có thể tới **200**.

// ---

// 👉 Đây là một bài **Dynamic Programming (DP)** kinh điển:

// * Bắt đầu từ hàng **dưới cùng**, tính dần lên trên.
// * Tại `triangle[i][j]`, giá trị tốt nhất = `triangle[i][j] +
// min(triangle[i+1][j], triangle[i+1][j+1])`.

// Cuối cùng, kết quả ở `triangle[0][0]` sẽ là tổng nhỏ nhất.

// ---

// Bạn có muốn mình viết cho bạn **code Java** giải bài này theo cách
// **Bottom-up DP** không?
// Rồi 👍 mình giải thích chi tiết thuật toán trong code bạn viết nhé:

// ---

// ### Ý tưởng của code:

// Đây là cách giải bằng **Đệ quy + Ghi nhớ (Top-down DP with memoization)**.

// 1. Hàm `minimumTotal`

// * Lấy kích thước tam giác `m`.
// * Tạo mảng `dp` (kích thước `m x m`) để nhớ kết quả đã tính, tránh tính lại.
// * Gọi hàm đệ quy `helper(tran, 0, 0)` → bắt đầu từ đỉnh tam giác (hàng 0, cột
// 0).

// 2. Hàm `helper(tran, row, col)`

// * **Điều kiện dừng**:
// Nếu `row == m` (tức là vượt quá hàng cuối cùng) → trả về `0` vì không còn
// phần tử nào để cộng nữa.

// * **Kiểm tra memo**:
// Nếu `dp[row][col]` khác `null`, nghĩa là đã tính kết quả cho ô `(row, col)`
// rồi → trả về ngay, không tính lại.

// * **Đệ quy**:
// Từ `tran[row][col]`, bạn có 2 lựa chọn đi xuống:

// * `left = helper(tran, row+1, col)` (đi thẳng xuống)
// * `right = helper(tran, row+1, col+1)` (đi chéo xuống phải)

// * **Công thức**:

// ```
// dp[row][col] = tran[row][col] + Math.min(left, right)
// ```

// tức là giá trị nhỏ nhất của đường đi bắt đầu từ `(row,col)` = giá trị hiện
// tại + đường đi nhỏ hơn trong 2 hướng đi xuống.

// 3. Kết quả cuối cùng sẽ được lưu ở `dp[0][0]`.

// ---

// ### Ví dụ minh họa với input:

// ```
// [
// [2],
// [3,4],
// [6,5,7],
// [4,1,8,3]
// ]
// ```

// * Bắt đầu `helper(0,0)` → giá trị `2`

// * Gọi `helper(1,0)` → giá trị `3`

// * Gọi `helper(2,0)` → giá trị `6`

// * Gọi `helper(3,0)` → giá trị `4` → hết hàng → return 4
// * Gọi `helper(3,1)` → giá trị `1` → return 1
// → `helper(2,0) = 6 + min(4,1) = 6+1=7`
// * Gọi `helper(2,1)` → giá trị `5`

// * Gọi `helper(3,1)=1`, `helper(3,2)=8`
// → `helper(2,1) = 5 + min(1,8) = 6`
// → `helper(1,0) = 3 + min(7,6) = 9`
// * Gọi `helper(1,1)` → giá trị `4`

// * Gọi `helper(2,1)=6`, `helper(2,2)=7+min(8,3)=10`
// → `helper(1,1) = 4 + min(6,10) = 10`
// → `helper(0,0) = 2 + min(9,10) = 11`

// 👉 Kết quả cuối cùng: **11**.

// ---

// ### Độ phức tạp:

// * **Thời gian**: O(n²)
// Vì có tổng cộng \~n² trạng thái `(row, col)` và mỗi trạng thái được tính **1
// lần** nhờ `dp`.
// * **Bộ nhớ**: O(n²) cho mảng `dp` + O(n) cho ngăn xếp đệ quy.

// ---

// Bạn có muốn mình viết thêm phiên bản **Bottom-up DP** (không dùng đệ quy, chỉ
// dùng vòng lặp) để so sánh với code của bạn không?


// 1504. Count Submatrices With All Ones(21/08/2025)
import java.util.*;

public class b12 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();

        // Khởi tạo ma trận
        int[][] mat = new int[n][m];

        // Nhập các phần tử của ma trận

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.printf("mat[%d][%d]: ", i, j);
                mat[i][j] = sc.nextInt();
            }
        }

        int result = numSubmat(mat);
        System.out.println(result);
        sc.close();

    }

    public static int numSubmat(int[][] mat) {

        int n = mat.length;
        int m = mat[0].length;

        int ans = 0;
        int[] height = new int[m];

        for (int i = 0; i < n; i++) {
            // Update histogram heights
            for (int j = 0; j < m; j++) {
                height[j] = (mat[i][j] == 0) ? 0 : height[j] + 1;
            }

            // Count rectangles in current histogram
            ans += countRectangles(height);
        }

        return ans;
    }

    public static int countRectangles(int[] height) {
        int m = height.length;
        int count = 0;
        int[] stack = new int[m];
        int top = -1;
        int[] sum = new int[m];

        for (int j = 0; j < m; j++) {
            while (top >= 0 && height[stack[top]] >= height[j]) {
                top--;
            }

            if (top == -1) {
                sum[j] = height[j] * (j + 1);
            } else {
                sum[j] = sum[stack[top]] + height[j] * (j - stack[top]);
            }

            count += sum[j];
            stack[++top] = j;
        }

        return count;
    }
}

// Cụ thể, nó chuyển bài toán đếm ma trận con thành bài toán đếm hình chữ nhật
// trong biểu đồ hình cột (histogram).

// ### Giải thích thuật toán

// Thuật toán này có thể được chia thành hai phần chính:

// 1. **Chuyển đổi ma trận thành các biểu đồ

// hình cột (histograms).**
// 2. **Đếm các hình chữ nhật cho mỗi biểu đồ hình cột bằng stack.**

// ---

// ### 1. Hàm `numSubmat`: Chuyển đổi và Tổng hợp

// Hàm này là "bộ não" điều phối. Nó duyệt qua từng hàng của ma trận đầu vào và
// biến mỗi hàng thành một bài toán biểu đồ hình cột.

// * `int[] height = new int[m];`: Mảng `height` này chính là biểu đồ hình cột
// tạm thời. Tại mỗi hàng `i` của ma trận, `height[j]` sẽ lưu trữ chiều cao của
// cột liên tiếp các số `1` kết thúc tại vị trí `(i, j)`.

// * **Vòng lặp

// ngoài cùng (`for (int i = 0; i < n; i++)`):** Duyệt qua từng hàng của ma
// trận.

// * **Vòng lặp trong (`for (int j = 0; j < m; j++)`):**
// * `height[j] = (mat[i][j] == 0) ? 0 : height[j] + 1;`
// * Đây là bước chuyển đổi ma trận thành biểu đồ hình cột.
// * Nếu `mat[i][j]` là `0`, cột `1` liên tiếp bị ngắt, nên chiều cao của cột
// tại vị trí `j` sẽ được reset về `0`.
// * Nếu `mat[i][j]` là `1`, chiều cao của cột tại vị trí `j` sẽ được tăng thêm
// `1` so với chiều cao của cột ở hàng trên (`i-1`).

// * `ans += countRectangles(height);`: Sau khi mảng `height` được cập nhật cho
// hàng hiện tại, nó được truyền vào hàm `countRectangles` để đếm tổng số hình
// chữ

// nhật (tương ứng với các ma trận con) mà có **đáy nằm ở hàng hiện tại**. Kết
// quả được cộng dồn vào `ans`.

// ---

// ### 2. Hàm `countRectangles`: Đếm hình chữ nhật bằng Stack

// Hàm này giải quyết bài toán phụ: **đếm tổng số hình chữ nhật trong một biểu
// đồ hình cột**. Nó sử dụng một kỹ thuật gọi là "monotonic stack" (stack đơn
// điệu) để làm điều này một cách hiệu quả.

// * `int[] stack`: Stack này sẽ lưu trữ **chỉ số (index)** của các cột trong
// biểu đồ. Nó duy trì một thứ tự tăng dần về chiều cao.
// * `int[] sum`: Mảng `sum[j]` sẽ lưu trữ tổng số hình chữ nhật mà có **đáy ở
// hàng hiện tại và kết thúc tại cột `j`**.

// * **Vòng lặp (`for (int j = 0; j < m; j++)`):** Duyệt qua từng cột của biểu
// đồ.

// * **`while (top >= 0 && height[stack[top]] >= height[j]) { top--; }`:**
// * Đây là logic cốt lõi của stack. Vòng lặp này sẽ

// loại bỏ (pop) các chỉ số từ stack nếu chiều cao của cột tương ứng **lớn hơn
// hoặc bằng** chiều cao của cột

// hiện tại (`height[j]`).
// * Mục tiêu là tìm chỉ số của cột **đầu tiên** bên trái có chiều cao **nhỏ
// hơn** `height[j]`.

// * **Tính toán `sum[j]`:**
// * `if (top == -1)`: Nếu stack rỗng, điều đó có nghĩa là `height[j]` là cột
// thấp nhất tính từ đầu biểu đồ. Số hình chữ nhật mới được tạo thành tại cột
// này là `height[j]` nhân với số lượng cột từ đầu đến `j` (tức là `j+1`).
// * `else`: Nếu stack không rỗng, cột thấp hơn gần nhất ở bên trái là
// `stack[top]`.
// * `sum[stack[top]]`: Tổng số hình chữ nhật kết thúc tại cột thấp hơn gần
// nhất.
// * `height[j] * (j - stack[top])`: Cộng thêm số hình chữ nhật mới được tạo
// thành bằng cách mở rộng chiều cao `height[j]` từ cột `stack[top]` đến cột
// `j`.
// * Cả hai số này được cộng lại để ra tổng số hình chữ nhật kết thúc tại cột
// `j`.

// * `count += sum[j];`: Cộng dồn giá trị `sum[j]` vào tổng số hình chữ nhật của
// biểu đồ này.

// * `stack[++top] = j;`: Đẩy chỉ số của cột hiện tại vào stack.

// ### Tại sao nó hiệu quả?

// * **Thời gian:** Thuật toán này duyệt qua mỗi ô của ma trận

// một lần (`O(M * N)`) và sau đó xử lý mỗi hàng bằng stack (`O(N)`). Do đó,
// tổng độ phức tạp là `O(M * N)`.
// * **Không gian:** `O(N)` để lưu mảng `height` và stack.

// Đây là một cách giải rất thanh lịch và tối ưu, sử dụng tư duy quy

// hoạch động (xây dựng `heights`) kết hợp với cấu trúc dữ liệu stack để tối ưu
// hóa việc đếm.
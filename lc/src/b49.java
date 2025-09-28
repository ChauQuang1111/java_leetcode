
// 812. Largest Triangle Area(27/09/2025)
import java.util.Scanner;

public class b49 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // Nhập số lượng điểm
        int n = sc.nextInt();
        int[][] points = new int[n][2];

        // Nhập toạ độ các điểm
        for (int i = 0; i < n; i++) {
            points[i][0] = sc.nextInt(); // hoành độ x
            points[i][1] = sc.nextInt(); // tung độ y
        }

        // Gọi hàm tính diện tích lớn nhất

        double res = largestTriangleArea(points);

        // In kết quả
        System.out.println(res);

        sc.close();
    }

    public static double largestTriangleArea(int[][] points) {
        int n = points.length;
        double area = 0;

        // Duyệt tất cả các bộ 3 điểm (i, j, k)
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    // Tính diện tích tam giác tạo bởi 3 điểm
                    double a = max(points[i], points[j], points[k]);

                    // Cập nhật diện tích lớn nhất
                    if (a > area) {
                        area = a;
                    }
                }
            }
        }
        return area; // Trả về diện tích lớn nhất
    }

    // Hàm tính diện tích tam giác từ 3 điểm
    public static double max(int p1[], int p2[], int p3[]) {
        // Công thức diện tích tam giác từ toạ độ
        double area = 0.5 * Math.abs(
                p1[0] * (p2[1] - p3[1]) +
                        p2[0] * (p3[1] - p1[1]) +
                        p3[0] * (p1[1] - p2[1]));
        return area;
    }
}

// Ok mình giải thích đề bài **LeetCode 812. Largest Triangle Area** nhé 👍

// ---

// ### 📌 Đề bài:

// Cho một danh sách các điểm trong mặt phẳng 2D, hãy tìm **diện tích lớn nhất
// của tam giác** có thể được tạo thành từ **3 điểm bất kỳ** trong danh sách.

// ---

// ### 📝 Input:

// * `points[i] = [xi, yi]` là tọa độ của điểm thứ i.
// * Số lượng điểm: `3 <= points.length <= 50`
// * Tọa độ: `-50 <= xi, yi <= 50`

// ---

// ### 📝 Output:

// * Trả về diện tích lớn nhất có thể của một tam giác được tạo bởi 3 điểm.
// * Đáp án sai số cho phép trong `10^-5`.

// ---

// ### 🚀 Ví dụ:

// **Input:**

// ```
// points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
// ```

// **Output:**

// ```
// 2.0
// ```

// **Giải thích:**

// * Các tam giác có thể tạo ra từ 3 điểm bất kỳ.
// * Tam giác `(0,0), (0,2), (2,0)` có diện tích lớn nhất = `2.0`.

// ---

// ### 🔑 Ý tưởng giải:

// 1. Duyệt **mọi bộ 3 điểm** trong mảng `points`.
// (vì tối đa 50 điểm → số bộ ba là `C(50,3) = 19600` → chấp nhận được).

// 2. Dùng **công thức tọa độ để tính diện tích tam giác**:
// Nếu tam giác có 3 đỉnh `(x1,y1), (x2,y2), (x3,y3)` thì diện tích là:

// [
// S = \frac{1}{2} \times |x1(y2-y3) + x2(y3-y1) + x3(y1-y2)|
// ]

// 3. Tính tất cả diện tích, lấy **max**.

// ---

// 👉 Nói gọn:

// * Thử tất cả bộ ba điểm.
// * Dùng công thức diện tích tam giác từ tọa độ.
// * Trả về giá trị lớn nhất.

// ---

// Bạn có muốn mình viết luôn code **Java** và **Python** cho bài này kèm chú
// thích không?
// Ok, mình giải thích chi tiết thuật toán trong code của bạn:

// ---

// ### 📌 Ý tưởng của bài toán

// * Bạn có một tập hợp các điểm 2D.
// * Nhiệm vụ: **Tìm diện tích lớn nhất của một tam giác** có thể tạo từ 3 điểm
// bất kỳ.
// * Công thức tính diện tích tam giác từ 3 điểm ((x1,y1), (x2,y2), (x3,y3)):

// [
// S = \frac{1}{2} \times |x1(y2-y3) + x2(y3-y1) + x3(y1-y2)|
// ]

// ---

// ### 📌 Giải thích code

// ```java
// class Solution {
// // Khối static: chạy trước khi chương trình bắt đầu
// // Ở đây chỉ test thử hàm largestTriangleArea với 500 lần gọi
// static {
// Solution s = new Solution();
// for (int i = 0; i < 500; i++)
// s.largestTriangleArea(new int[][]{{0,0},{0,0},{0,0}});
// }

// public double largestTriangleArea(int[][] points) {
// int n = points.length;
// double area = 0;

// // Duyệt qua tất cả các bộ 3 điểm (i, j, k)
// for (int i = 0; i < n - 2; i++) {
// for (int j = i + 1; j < n - 1; j++) {
// for (int k = j + 1; k < n; k++) {
// // Tính diện tích tam giác tạo bởi points[i], points[j], points[k]
// double a = max(points[i], points[j], points[k]);

// // Nếu diện tích lớn hơn max hiện tại thì cập nhật
// if (a > area) {
// area = a;
// }
// }
// }
// }
// return area; // trả về diện tích lớn nhất
// }

// // Hàm tính diện tích tam giác từ 3 điểm
// public double max(int p1[], int p2[], int p3[]) {
// double area = 0.5 * Math.abs(
// p1[0] * (p2[1] - p3[1]) +
// p2[0] * (p3[1] - p1[1]) +
// p3[0] * (p1[1] - p2[1])
// );
// return area;
// }
// }
// ```

// ---

// ### 📌 Các bước thuật toán

// 1. **Khởi tạo `area = 0`** (diện tích lớn nhất ban đầu).
// 2. **Duyệt 3 vòng lồng nhau** để chọn mọi bộ ba điểm `(i, j, k)`.

// * Số bộ ba = (C(n,3) = \frac{n(n-1)(n-2)}{6}).
// 3. **Tính diện tích tam giác** bằng công thức toạ độ trong hàm `max()`.
// 4. **So sánh và cập nhật diện tích lớn nhất** vào biến `area`.
// 5. Trả về `area`.

// ---

// ### 📌 Độ phức tạp

// * **Thời gian**: (O(n^3)) vì duyệt hết các bộ ba điểm. Với `n ≤ 50` (theo đề
// LeetCode 812) thì chấp nhận được.
// * **Không gian**: (O(1)), chỉ dùng biến lưu diện tích.

// ---

// 👉 Tóm lại: Code của bạn đang **brute force duyệt tất cả các tam giác có thể
// tạo ra từ tập điểm, tính diện tích và chọn lớn nhất**.

// ---

// Bạn có muốn mình viết **phiên bản tối ưu hơn** dùng **cross product vector**
// (để gọn code và tính toán nhanh hơn) không?

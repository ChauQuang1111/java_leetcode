// 1792. Maximum Average Pass Ratio(01/09/2025)

import java.util.*;

public class b23 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Nhập số lượng lớp học

        int numClasses = sc.nextInt();

        int[][] classes = new int[numClasses][2];

        for (int i = 0; i < numClasses; i++) {

            classes[i][0] = sc.nextInt();

            classes[i][1] = sc.nextInt();
        }

        // Nhập số học sinh phụ
        System.out.print("Nhập số học sinh phụ: ");
        int extraStudents = sc.nextInt();

        double result = maxAverageRatio(classes, extraStudents);

        System.out.println(result);

        sc.close();
    }

    public static double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<double[]> pq = new PriorityQueue<>(new Comparator<double[]>() {
            public int compare(double[] a, double[] b) {
                if (a[0] < b[0])
                    return 1;
                if (a[0] > b[0])
                    return -1;
                return 0;
            }
        });

        for (int i = 0; i < classes.length; i++) {
            double pass = classes[i][0];
            double total = classes[i][1];
            double inc = (pass + 1.0) / (total + 1.0) - pass / total;
            pq.offer(new double[] { inc, pass, total });
        }

        while (extraStudents > 0) {
            double[] top = pq.poll();
            double pass = top[1] + 1;
            double total = top[2] + 1;
            double inc = (pass + 1.0) / (total + 1.0) - pass / total;
            pq.offer(new double[] { inc, pass, total });
            extraStudents--;
        }

        double sum = 0.0;
        Object[] arr = pq.toArray();// Dòng code Object[] arr = pq.toArray(); được dùng để chuyển tất cả các phần tử
                                    // trong PriorityQueue (hàng đợi ưu tiên) pq thành một mảng thông thường.

        // Ý nghĩa và chức năng của toArray()
        // toArray() là một phương thức của interface Collection trong Java, được triển
        // khai bởi nhiều lớp cấu trúc dữ liệu, bao gồm PriorityQueue. Chức năng của nó
        // là tạo một mảng mới và sao chép tất cả các phần tử của collection vào mảng đó
        for (int i = 0; i < arr.length; i++) {
            double[] c = (double[]) arr[i];
            sum += c[1] / c[2];
        }

        return sum / classes.length;
    }

}
// ###
// Giải thích
// đề bài

// **1.
// Yêu cầu chính:**

// Bạn được
// cho một
// danh sách
// các lớp học,
// mỗi lớp
// được biểu
// diễn bằng
// một cặp số:`[passes_i,total_i]`.*`passes_i`:
// Số học
// sinh đỗ
// trong lớp đó.*`total_i`:
// Tổng số
// học sinh
// trong lớp
// đó.

// Tỷ lệ
// đỗ của
// một lớp là`passes_i/total_i`.

// Bạn được
// cấp thêm`extraStudents`(số học
// sinh dư).
// Bạn có
// thể phân
// bổ những
// học sinh
// này vào
// bất kỳ
// lớp học
// nào.Khi một
// học sinh
// được phân
// bổ vào
// một lớp, cả
// số học

// sinh đỗ (`passes`) và tổng số học sinh (`total`) của lớp đó đều tăng lên 1.

// Mục tiêu của bạn là phân bổ tất cả `extraStudents` sao cho **tỷ lệ đỗ trung
// bình** của tất cả các lớp là **lớn nhất có thể**. Cuối cùng, bạn cần trả về
// giá trị tỷ lệ đỗ trung bình này.

// **2. Khái niệm "Tỷ lệ đỗ trung bình":**

// Đây là trung bình cộng của tỷ lệ đỗ của tất cả các lớp.
// Công thức: `(tỷ lệ đỗ lớp 1 + tỷ lệ đỗ lớp 2 + ... + tỷ lệ đỗ lớp n) / n`
// Trong đó `n` là tổng số lớp học.

// **3. Phân tích bài toán:**

// Bài toán này không yêu cầu bạn tìm ra cách phân bổ cụ thể, mà chỉ yêu cầu tìm
// **giá trị trung bình lớn nhất** có thể đạt được.

// Để tối đa hóa tổng của các tỷ lệ đỗ, bạn cần phân bổ mỗi học sinh thêm vào
// lớp học mà việc đó mang lại **lợi ích lớn nhất**.

// * **Lợi ích:** Lợi ích của việc thêm một học sinh vào một lớp `[p, t]` là sự
// gia tăng của tỷ lệ đỗ.
// * Tỷ lệ đỗ ban đầu: `p / t`
// * Tỷ lệ đỗ sau khi thêm 1 học sinh: `(p + 1) / (t + 1)`
// * Lợi ích: `(p + 1) / (t + 1) - p / t`

// * **Chiến lược

// tham lam (Greedy Strategy):**
// * Mỗi lần bạn có một học sinh thêm, bạn cần tìm lớp học nào mà việc thêm học
// sinh đó vào sẽ mang lại lợi ích lớn nhất (tức là tăng tỷ lệ đỗ nhiều nhất).
// * Bạn sẽ lặp lại quá trình này `extraStudents` lần, mỗi lần phân bổ một học
// sinh vào lớp có lợi ích cao nhất tại thời điểm đó.

// **4. Dữ liệu đầu vào và đầu ra:**

// * **Input:**
// * `classes`: Một mảng/danh sách các mảng con, mỗi mảng con là `[passes_i,
// total_i]`.
// * `extraStudents`: Một số nguyên, số học sinh có thể phân bổ.
// * **Output:**
// * Một số thực, là tỷ lệ đỗ trung bình lớn nhất có thể.

// ### Ví dụ minh họa

// Giả sử bạn có `classes = [[1,2], [3,5]]` và `extraStudents = 1`.

// * **Lớp 1:** `[1,2]`, tỷ lệ đỗ: `1/2 = 0.5`
// * **Lớp 2:** `[3,5]`, tỷ lệ đỗ: `3/5 = 0.6`

// Bây giờ bạn có 1 học sinh thêm, bạn nên cho vào lớp nào để có lợi nhất?
// * **Thêm vào Lớp 1:** Tỷ lệ mới là `(1+1)/(2+1) = 2/3 ≈ 0.667`. Lợi ích:
// `0.667 - 0.5 = 0.167`.
// * **Thêm vào Lớp 2:** Tỷ lệ mới là `(3+1)/(5+1) = 4/6 ≈ 0.667`. Lợi ích:
// `0.667 - 0.6 = 0.067`.

// Lợi ích của việc thêm vào Lớp 1 lớn hơn. Do đó, bạn nên phân bổ học sinh vào
// Lớp 1.

// * Tỷ lệ đỗ mới của Lớp 1: `0.667`
// * Tỷ lệ đỗ của Lớp 2: `0.6`
// * Tỷ lệ đỗ trung bình: `(0.667 + 0.6) / 2 = 0.6335`.

// Để giải quyết bài toán này một cách hiệu quả, bạn có thể sử dụng một cấu trúc
// dữ liệu ưu

// tiên (priority queue) để luôn tìm được lớp có lợi ích cao nhất một cách nhanh
// chóng.
// Đoạn code bạn cung cấp là một giải pháp đúng và hiệu quả cho bài toán
// "Maximum Average Pass Ratio". Nó sử dụng một thuật toán tham lam (Greedy
// Algorithm) kết hợp với cấu trúc dữ liệu hàng đợi ưu tiên (Priority Queue).

// ### Giải thích thuật toán

// Thuật toán này hoạt động dựa trên nguyên tắc **phân bổ tài nguyên (học sinh)
// một cách tham lam** để tối đa hóa lợi ích thu được sau mỗi bước. Để tối đa
// hóa tỷ lệ đỗ trung bình, chúng ta cần tối đa hóa tổng tỷ lệ đỗ của tất cả các
// lớp.

// Ý tưởng chính là: mỗi khi có một học sinh, chúng ta sẽ thêm học sinh đó vào
// lớp học nào mang lại **sự gia tăng tỷ lệ đỗ lớn nhất**.

// Các bước thực hiện của thuật toán như sau:

// 1. **Tính toán và lưu trữ lợi ích ban đầu**:

// * Với mỗi lớp học `[pass, total]`, ta tính toán **lợi ích** mà việc thêm một
// học sinh vào đó mang lại. Lợi ích được định nghĩa là sự chênh lệch giữa tỷ lệ
// đỗ mới và tỷ lệ đỗ cũ:
// * `lợi ích = (pass + 1) / (total + 1) - pass / total`
// * Tất cả các lớp, cùng với lợi ích tương ứng, được đưa vào một **hàng đợi ưu
// tiên (Priority Queue)**. Hàng đợi này được cấu hình để luôn ưu tiên phần tử
// có lợi ích lớn nhất.

// 2. **Phân bổ học sinh tham lam**:

// * Lặp lại `extraStudents` lần, mỗi lần:
// * Lấy ra lớp có lợi ích lớn nhất từ hàng đợi ưu tiên.
// * Thêm một học sinh vào lớp đó (tăng cả `pass` và `total` lên 1).
// * Tính toán lại lợi ích mới cho lớp này (vì `pass` và `total` đã thay đổi).
// * Thêm lớp đã được cập nhật trở lại hàng đợi ưu tiên. Nó sẽ được sắp xếp lại
// vị trí dựa trên lợi ích mới.

// 3. **Tính toán kết quả cuối cùng**:

// * Sau khi đã phân bổ hết tất cả `extraStudents`, hàng đợi ưu tiên chứa các
// lớp đã được cập nhật với số học sinh mới.
// * Ta tính tổng tỷ lệ đỗ của tất cả các lớp này và chia cho tổng số lớp ban
// đầu (`classes.length`) để có được tỷ lệ trung bình lớn nhất.

// -----

// ### Giải thích từng phần của đoạn mã

// ```java
// // Khai báo một hàng đợi ưu tiên
// PriorityQueue<double[]> pq = new PriorityQueue<>(new Comparator<double[]>() {
// public int compare(double[] a, double[] b) {
// // So sánh hai phần tử a và b dựa trên giá trị ở chỉ số 0 (lợi ích)
// if (a[0] < b[0]) return 1; // a có lợi ích nhỏ hơn b -> ưu tiên b (return 1)
// if (a[0] > b[0]) return -1; // a có lợi ích lớn hơn b -> ưu tiên a (return
// -1)
// return 0; // bằng nhau
// }
// });
// ```

// * Đoạn code này khởi tạo một hàng đợi ưu tiên để lưu trữ các mảng `double[]`.
// * Mỗi mảng `double[]` sẽ chứa 3 giá trị: `[lợi ích, pass, total]`.
// * **`Comparator`** tùy chỉnh đảm bảo rằng các phần tử được sắp xếp theo **thứ
// tự giảm dần của lợi ích**.

// <!-- end list -->

// ```java
// // Lặp qua tất cả các lớp ban đầu để tính toán lợi ích và thêm vào hàng đợi
// for (int i = 0; i < classes.length; i++) {
// double pass = classes[i][0];
// double total = classes[i][1];
// double inc = (pass + 1.0) / (total + 1.0) - pass / total; // Tính lợi ích
// pq.offer(new double[]{inc, pass, total}); // Thêm vào hàng đợi
// }
// ```

// * Vòng lặp này duyệt qua từng lớp học ban đầu.
// * Nó tính toán lợi ích ban đầu của mỗi lớp và tạo một mảng `double[]` để lưu
// trữ nó cùng với `pass` và `total`.
// * **`pq.offer()`** thêm mảng này vào hàng đợi, nơi nó sẽ được đặt vào vị trí
// thích hợp.

// <!-- end list -->

// ```java
// // Phân bổ học sinh
// while (extraStudents > 0) {
// double[] top = pq.poll(); // Lấy lớp có lợi ích cao nhất
// double pass = top[1] + 1; // Cập nhật pass
// double total = top[2] + 1; // Cập nhật total
// double inc = (pass + 1.0) / (total + 1.0) - pass / total; // Tính lợi ích mới
// pq.offer(new double[]{inc, pass, total}); // Thêm lại vào hàng đợi
// extraStudents--;
// }
// ```

// * Vòng lặp này thực hiện quá trình phân bổ học sinh.
// * **`pq.poll()`** lấy ra phần tử đầu tiên của hàng đợi (lớp có lợi ích cao
// nhất).
// * Các giá trị `pass` và `total` được tăng lên 1.
// * Lợi ích mới được tính toán lại và lớp đã được cập nhật được đưa trở lại
// hàng đợi.

// <!-- end list -->

// ```java
// // Tính toán tỷ lệ trung bình
// double sum = 0.0;
// Object[] arr = pq.toArray(); // Chuyển PriorityQueue thành mảng
// for (int i = 0; i < arr.length; i++) {
// double[] c = (double[]) arr[i];
// sum += c[1] / c[2]; // Cộng dồn tỷ lệ đỗ
// }

// return sum / classes.length;
// ```

// * Sau khi phân bổ xong, chúng ta cần tính toán kết quả.
// * **`pq.toArray()`** chuyển các phần tử trong hàng đợi thành một mảng để dễ
// dàng duyệt qua.
// * Vòng lặp duyệt qua từng lớp, lấy các giá trị `pass` và `total` đã được cập
// nhật (`c[1]` và `c[2]`) để tính tỷ lệ đỗ của mỗi lớp.
// * Cuối cùng, tổng tỷ lệ được chia cho số lớp để có tỷ lệ trung bình.
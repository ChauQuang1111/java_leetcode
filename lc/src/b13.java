
// 3195. Find the Minimum Area to Cover All Ones I
import java.util.*;

public class b13 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int rows = sc.nextInt();
        int cols = sc.nextInt();

        // Khởi tạo ma trận
        int[][] grid = new int[rows][cols];

        System.out.println("Nhập các phần tử của ma trận (chỉ 0 và 1):");
        System.out.println("Mỗi hàng, nhập " + cols + " số, cách nhau bởi dấu cách.");

        // Đọc các phần tử của ma trận từ bàn phím
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        // Tạo đối tượng Solution để gọi phương thức minimumArea
        int area = minimumArea(grid);

        // Tính toán và in ra kết quả

        System.out.println("Diện tích nhỏ nhất để bao phủ tất cả số 1 là: " + area);

        // Đóng Scanner
        sc.close();
    }

    public static int minimumArea(int[][] arr) {
        // Lấy kích thước của ma trận: số hàng (rows) và số cột (cols)
        int rows = arr.length;
        int cols = arr[0].length;

        // ri: row index (chỉ số hàng) trên cùng
        // rl: row last (chỉ số hàng) dưới cùng
        // ci: col index (chỉ số cột) trái nhất
        // cl: col last (chỉ số cột) phải nhất
        int ri = 0, rl = 0, ci = 0, cl = 0;
        int flag = 0; // Biến cờ để báo hiệu đã tìm thấy số '1' và thoát vòng lặp

        // --- Tìm chỉ số hàng trên cùng (ri) ---
        // Duyệt từ hàng đầu tiên (i=0) xuống
        for (int i = 0; i < rows; i++) {
            // Duyệt qua từng cột trong hàng hiện tại
            for (int j = 0; j < cols; j++) {
                if (arr[i][j] == 1) {
                    ri = i; // Gán chỉ số hàng tìm thấy cho ri
                    flag = 1; // Bật cờ
                    break; // Thoát vòng lặp cột vì đã tìm thấy '1'
                }
            }
            if (flag == 1) {
                break; // Thoát vòng lặp hàng vì đã tìm thấy hàng trên cùng
            }
        }

        flag = 0; // Reset cờ cho lần tìm kiếm tiếp theo

        // --- Tìm chỉ số hàng dưới cùng (rl) ---
        // Duyệt từ hàng cuối cùng (i=rows-1) lên
        for (int i = rows - 1; i >= 0; i--) {
            // Duyệt qua từng cột trong hàng hiện tại
            for (int j = 0; j < cols; j++) {
                if (arr[i][j] == 1) {
                    rl = i; // Gán chỉ số hàng tìm thấy cho rl
                    flag = 1;
                    break;
                }
            }
            if (flag == 1) {
                break;
            }
        }

        flag = 0; // Reset cờ

        // --- Tìm chỉ số cột trái nhất (ci) ---
        // Duyệt từ cột đầu tiên (i=0) sang
        for (int i = 0; i < cols; i++) {
            // Duyệt qua từng hàng trong cột hiện tại
            for (int j = 0; j < rows; j++) {
                if (arr[j][i] == 1) {
                    ci = i; // Gán chỉ số cột tìm thấy cho ci
                    flag = 1;
                    break;
                }
            }
            if (flag == 1) {
                break;
            }
        }

        flag = 0; // Reset cờ

        // --- Tìm chỉ số cột phải nhất (cl) ---
        // Duyệt từ cột cuối cùng (i=cols-1) về
        for (int i = cols - 1; i >= 0; i--) {
            // Duyệt qua từng hàng trong cột hiện tại
            for (int j = 0; j < rows; j++) {
                if (arr[j][i] == 1) {
                    cl = i; // Gán chỉ số cột tìm thấy cho cl
                    flag = 1;
                    break;
                }
            }
            if (flag == 1) {
                break;
            }
        }

        // --- Tính toán diện tích ---
        // Chiều rộng = (chỉ số cột cuối - chỉ số cột đầu) + 1
        // Chiều cao = (chỉ số hàng cuối - chỉ số hàng đầu) + 1
        // Trả về tích của chiều rộng và chiều cao
        return (cl - ci + 1) * (rl - ri + 1);
    }
}

// Thuật toán bạn đã viết để tìm diện tích nhỏ nhất bao phủ tất cả các số`1
// trong ma trận là hoàn toàn chính xác về mặt logic.Bạn đã chia bài toán thành
// bốn phần riêng biệt để tìm các ranh giới của hình chữ nhật.

// 1.**Tìm hàng trên cùng (`ri`)**:
// * Bạn bắt đầu duyệt từ hàng đầu tiên (`i = 0`).
// * Ngay khi tìm thấy số `1` đầu tiên trong ma trận, bạn lưu chỉ số hàng (`i`)
// đó vào biến `ri`.
// * Bạn dùng một biến cờ (`flag`) và lệnh `break` để thoát khỏi cả hai vòng
// lặp. Điều này đảm bảo rằng `ri` sẽ giữ chỉ số của hàng có `1` đầu tiên, tức
// là hàng trên cùng.

// 2. **Tìm hàng dưới cùng (`rl`)**:
// * Tương tự, bạn duyệt ma trận từ hàng cuối cùng (`i = rows - 1`) trở lên.
// * Khi tìm thấy số `1` đầu tiên từ dưới lên, bạn lưu chỉ số hàng đó vào `rl`.
// * Lệnh `break` đảm bảo bạn tìm được hàng có chỉ số lớn nhất.

// 3. **Tìm cột trái nhất (`ci`)**:
// * Bạn duyệt ma trận theo cột, từ cột đầu tiên (`i = 0`) sang.
// * Bạn tìm thấy số `1` đầu tiên và lưu chỉ số cột đó vào `ci`.
// * Lệnh `break` giúp bạn thoát ra sớm.

// 4. **Tìm cột phải nhất (`cl`)**:
// * Bạn duyệt ma trận theo cột, từ cột cuối cùng (`i = cols - 1`) trở về.
// * Bạn tìm thấy số `1` đầu tiên từ phải sang và lưu chỉ số cột đó vào `cl`.
// * Bạn thoát vòng lặp khi tìm thấy.

// 5. **Tính toán diện tích**:
// * Cuối cùng, bạn sử dụng công thức `(cl - ci + 1) * (rl - ri + 1)` để tính
// diện tích của hình chữ nhật. Đây là công thức chính xác để tính diện tích từ
// các chỉ số ranh giới.

// ### Nhận xét chung

// * **Độ chính xác**: Thuật toán của bạn hoàn toàn đúng và sẽ cho ra kết quả
// chính xác.
// * **Hiệu suất**: Mặc dù đúng, thuật toán này chưa tối ưu. Nó duyệt qua ma
// trận tối đa **bốn lần**. Trong trường hợp xấu nhất, bạn có thể phải duyệt gần
// như toàn bộ ma trận ở mỗi lần tìm kiếm.
// * **Thiếu trường hợp rỗng**: Thuật toán của bạn chưa xử lý trường hợp ma trận
// không có bất kỳ số `1` nào. Trong trường hợp đó, các biến `ri`, `rl`, `ci`,
// `cl` sẽ giữ giá trị khởi tạo là `0`, dẫn đến kết quả là `(0 - 0 + 1) * (0 - 0
// + 1) = 1`, trong khi câu trả lời đúng phải là `0`.

// Để làm cho code hiệu quả hơn, bạn có thể tối ưu hóa nó bằng cách **duyệt ma
// trận chỉ một lần**. Trong lần duyệt đó, bạn đồng thời cập nhật cả bốn chỉ số

// ranh giới (`minRow`, `maxRow`, `minCol`, `maxCol`). Điều này sẽ giúp code gọn
// gàng, chạy nhanh hơn và dễ dàng xử lý trường hợp ma trận rỗng.
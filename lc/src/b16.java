
// 498. Diagonal Traverse(25/08/2025)
import java.util.*;

class b16 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Nhập kích thước ma trận
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();

        // Khởi tạo ma trận
        int[][] mat = new int[rows][cols];

        // Nhập các phần tử của ma trận

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.printf("mat[%d][%d]: ", i, j);
                mat[i][j] = scanner.nextInt();
            }
        }

        int[] result = findDiagonalOrder(mat);

        // In kết quả

        System.out.println(Arrays.toString(result));

        scanner.close();
    }

    public static int[] findDiagonalOrder(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            return new int[0];
        }

        if (mat.length == 1) { // if mat is single row/element
            return mat[0];
        }

        int total = mat.length * mat[0].length; // how many elements in mat
        int[] ret = new int[total]; // return value
        ret[0] = mat[0][0]; // first element of ret is always mat[0][0]

        if (mat[0].length == 1) { // if mat is single column
            for (int i = 1; i < mat.length; i++) {
                ret[i] = mat[i][0];
            }
            return ret;
        }

        int count = 1;
        int i = 0;
        int j = 1;
        boolean downLeft = true;

        while (count < total) {
            if (downLeft) {
                while (j > 0 && i < mat.length - 1) {
                    ret[count] = mat[i][j];
                    count++;
                    i++;
                    j--;
                }

                ret[count] = mat[i][j];
                count++;

                if (i < mat.length - 1) {
                    i++;
                } else {
                    j++;
                }
                downLeft = false;
            } else {
                while (i > 0 && j < mat[0].length - 1) {
                    ret[count] = mat[i][j];
                    count++;
                    i--;
                    j++;
                }

                ret[count] = mat[i][j];
                count++;

                if (j < mat[0].length - 1) {
                    j++;
                } else {
                    i++;
                }

                downLeft = true;
            }
        }
        return ret;
    }
}

// Giải thích đề bài: Diagonal Traverse:Đề bài yêu cầu bạn duyệt một ma trận
// (matrix)
// hai chiều theo đường chéo và trả về tất cả các phần tử đã duyệt theo đúng thứ
// tự đó
// Thuật toán
// bạn cung
// cấp là
// một cách
// giải phức
// tạp hơn
// để xử
// lý bài toán**
// Diagonal Traverse**
// bằng Java.
// Thay vì
// di chuyển
// từng bước
// một và
// kiểm tra
// ranh giới, nó
// sử dụng
// các vòng lặp`while`
// lồng nhau
// để xử
// lý toàn
// bộ một
// đường chéo
// cùng một lúc.

// ---

// ###
// Phân tích
// thuật toán

// Thuật toán
// này chia
// quá trình
// duyệt thành
// hai giai
// đoạn chính, dựa
// trên hướng
// đi của
// đường chéo:
// đi xuống-trái (`downLeft`) và đi lên-phải (`!downLeft`).

// 1. **Xử lý trường hợp đặc biệt (Edge Cases)**
// * `if(mat.length == 1)`: Nếu ma trận chỉ có một hàng (và nhiều cột), bạn có
// thể trả về chính hàng đó. Tuy nhiên, mã của bạn chỉ trả về `mat[0]`, điều này
// đúng nhưng có thể bị hiểu nhầm.
// * `if(mat[0].length == 1)`: Nếu ma trận chỉ có một cột, nó sẽ duyệt các phần
// tử từ trên xuống dưới. Đây là logic đúng.

// 2. **Khởi tạo biến**
// * `int total = mat.length * mat[0].length;`: Tổng số phần tử trong ma trận.
// * `int[] ret = new int[total];`: Mảng kết quả.
// * `ret[0] = mat[0][0];`: Phần tử đầu tiên luôn là `mat[0][0]`.
// * `int count = 1;`: Biến đếm số phần tử đã thêm vào mảng kết quả.
// * `int i = 0, j = 1;`: Tọa độ bắt đầu của đường chéo thứ hai.
// * `boolean downLeft = true;`: Một cờ để kiểm soát hướng đi. `true` cho hướng
// xuống-trái, `false` cho hướng lên-phải.

// 3. **Vòng lặp `while` chính**
// * `while(count < total)`: Vòng lặp này tiếp tục cho đến khi tất cả các phần
// tử đã được duyệt.

// 4. **Logic đi xuống-trái (`downLeft == true`)**
// * `while(j > 0 && i < mat.length - 1)`: Vòng lặp bên trong này xử lý một
// đường chéo đi xuống hoàn chỉnh.
// * Nó tiếp tục chừng nào chưa chạm vào

// biên trái (`j > 0`) và biên dưới (`i < mat.length - 1`).
// * Trong vòng lặp, `i` tăng và `j` giảm, mô phỏng việc di chuyển xuống và sang
// trái.
// * `ret[count] = mat[i][j]; count++;`: Thêm phần tử cuối cùng của đường chéo
// hiện tại.
// * **Thay đổi hướng**:
// * `if(i < mat.length-1){ i++; } else { j++; }`: Sau khi hoàn thành một đường
// chéo, thuật toán phải tìm điểm bắt đầu của đường chéo tiếp theo.
// * Nếu chưa chạm biên dưới, nó di chuyển xuống

// một bước (`i++`).
// * Nếu đã chạm biên dưới, nó di chuyển sang phải một bước (`j++`).
// * `downLeft = false;`: Đổi cờ để chuẩn bị cho đường chéo

// tiếp theo (hướng lên-phải).

// 5. **Logic đi lên-phải (`downLeft == false`)**
// * `while(i > 0 && j < mat[0].length - 1)`: Tương tự như trên, vòng lặp này xử
// lý một đường chéo đi lên hoàn chỉnh.
// * Nó tiếp tục chừng nào chưa chạm vào

// biên trên (`i > 0`) và biên phải (`j < mat[0].length - 1`).
// * Trong vòng lặp, `i` giảm và `j` tăng, mô phỏng việc di chuyển lên và sang
// phải.
// * `ret[count] = mat[i][j]; count++;`: Thêm phần tử cuối cùng của đường chéo
// hiện tại.
// * **Thay đổi hướng**:
// * `if(j < mat[0].length-1){ j++; } else { i++; }`: Sau khi hoàn thành, tìm
// điểm bắt đầu của đường chéo tiếp theo.
// * Nếu chưa chạm biên phải, nó di chuyển sang phải một bước (`j++`).
// * Nếu đã chạm biên phải, nó di chuyển xuống

// một bước (`i++`).
// * `downLeft = true;`: Đổi cờ để chuẩn bị cho đường chéo

// tiếp theo (hướng xuống-trái).

// 6. **Trả về kết quả**
// * `return ret;`: Trả về mảng đã được điền.

// ### Tóm tắt

// Thuật toán này là một cách tiếp cận khác của bài toán **Diagonal Traverse**.
// Thay vì mô phỏng di chuyển từng bước một, nó duyệt toàn bộ ma trận bằng cách
// xử lý **từng đường chéo một**. Nó sử dụng một vòng lặp chính và hai vòng lặp
// lồng nhau để hoàn thành từng đường chéo, sau đó tính toán điểm bắt đầu của
// đường chéo tiếp theo.

// Mặc dù có vẻ phức tạp hơn so với cách tiếp cận từng bước, nhưng nó có thể dễ
// hiểu hơn đối với một số người vì nó tách biệt rõ ràng việc xử lý các đường
// chéo đi lên và đi xuống. Tuy nhiên, nó cũng cần nhiều logic kiểm tra biên
// giới hơn để đảm bảo các vòng lặp và điểm bắt đầu được xác định chính xác.

// 36. Valid Sudoku(30/08/2025)
import java.util.*;

public class b21 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        char[][] board = new char[9][9];

        // Đọc 9 dòng đầu vào từ người dùng
        for (int i = 0; i < 9; i++) {
            String rowString = sc.nextLine();
            if (rowString.length() != 9) {

                return;
            }
            board[i] = rowString.toCharArray();
            // thực hiện việc chuyển đổi một chuỗi (String) thành một mảng ký tự (char[]) và
            // gán mảng đó vào một hàng cụ thể của ma trận Sudoku.
        }

        // Gọi hàm isValidSudoku và in kết quả
        boolean result = isValidSudoku(board);
        System.out.println(result);

        sc.close();
    }

    public static boolean isValidSudoku(char[][] board) {
        int[] rowMask = new int[9];
        int[] colMask = new int[9];
        int[] boxMask = new int[9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.')
                    continue;
                // Đoạn code bạn đưa ra là hai vòng lặp lồng nhau được sử dụng để **duyệt qua
                // từng ô của bảng Sudoku**.

                // ### Giải thích chi tiết

                // * **Vòng lặp ngoài**:

                // ```java
                // for (int i = 0; i < 9; i++)
                // ```

                // * Vòng lặp này duyệt qua các **hàng** của ma trận.
                // * Biến `i` đại diện cho chỉ số hàng, bắt đầu từ `0` và tăng dần lên đến `8`
                // (vì `i < 9`).
                // * Mỗi lần vòng lặp này chạy, nó sẽ xử lý một hàng mới của bảng.

                // * **Vòng lặp trong**:

                // ```java
                // for (int j = 0; j < 9; j++)
                // ```

                // * Vòng lặp này duyệt qua các **cột** của ma trận.
                // * Biến `j` đại diện cho chỉ số cột, bắt đầu từ `0` và tăng dần lên đến `8`.
                // * Vòng lặp trong sẽ chạy 9 lần cho mỗi lần chạy của vòng lặp ngoài.

                // ### Tác dụng chung

                // Khi kết hợp lại, hai vòng lặp này tạo thành một cơ chế để truy cập vào **mọi
                // ô** trong ma trận 9x9.

                // * Lần chạy đầu tiên: `(i=0, j=0)`, `(i=0, j=1)`, ..., `(i=0, j=8)` (duyệt hết
                // hàng 0).
                // * Lần chạy thứ hai: `(i=1, j=0)`, `(i=1, j=1)`, ..., `(i=1, j=8)` (duyệt hết
                // hàng 1).
                // * ...và cứ thế cho đến khi duyệt hết hàng 8.

                // Cặp `(i, j)` đại diện cho tọa độ của một ô cụ thể trong bảng. Sau đó, đoạn
                // code kiểm tra giá trị của ô đó (`char c = board[i][j];`).

                // * **`if (c == '.') continue;`**:
                // * Đây là một điều kiện để bỏ qua các ô trống.
                // * Nếu ô hiện tại chứa ký tự `.` (dấu chấm), lệnh `continue` sẽ ngay lập tức
                // kết thúc lần lặp hiện tại của vòng lặp trong và nhảy sang lần lặp tiếp theo.
                // * Điều này giúp thuật toán chỉ xử lý các ô đã được điền số, làm cho mã hiệu
                // quả hơn.
                int bit = 1 << (c - '0');
                int box = (i / 3) * 3 + (j / 3);

                if ((rowMask[i] & bit) != 0 ||
                        (colMask[j] & bit) != 0 ||
                        (boxMask[box] & bit) != 0)
                    return false;

                rowMask[i] |= bit;
                colMask[j] |= bit;
                boxMask[box] |= bit;
            }
        }
        return true;
    }
}

// ###

// Giải thích
// Đề bài:

// Valid Sudoku (Kiểm tra Sudoku hợp lệ)

// Đề bài **36. Valid Sudoku** yêu cầu bạn kiểm tra xem một bảng Sudoku 9x9 đã
// cho có hợp lệ hay không.

// Tuy nhiên, bạn **không cần phải kiểm tra xem bảng đó có giải được hay
// không**. Bạn chỉ cần kiểm tra xem cấu hình hiện tại của các số đã đặt có tuân
// thủ các quy tắc cơ bản của Sudoku hay không.

// #### Các Quy tắc cần kiểm tra:

// Một bảng Sudoku 9x9 được coi là hợp lệ nếu tất cả các số từ `1` đến `9` tuân
// thủ ba quy tắc sau:

// 1. **Mỗi hàng phải chứa các số từ 1-9 không lặp lại:**
// * Duyệt qua từng hàng.
// * Trong mỗi hàng, tất cả các số

// đã điền (từ `1` đến `9`) phải là duy nhất.
// * Ví dụ: Hàng `[5, 3, ., ., 7, ., ., ., .]` là hợp lệ. Hàng `[5, 3, 5, ., 7,
// ., ., ., .]` là không hợp lệ vì số `5` bị lặp lại.

// 2. **Mỗi cột phải chứa các số từ 1-9 không lặp lại:**
// * Duyệt qua từng cột.
// * Trong mỗi cột, tất cả các số

// đã điền (từ `1` đến `9`) phải là duy nhất.

// 3. **Mỗi khối 3x3 phải chứa các số từ 1-9 không lặp lại:**
// * Bảng Sudoku được chia thành 9 khối 3x3.
// * Duyệt qua từng khối này.
// * Trong mỗi khối, tất cả các số

// đã điền (từ `1` đến `9`) phải là duy nhất.

// #### Lưu ý quan trọng:

// * Bảng Sudoku được cho dưới dạng một mảng 2 chiều gồm các ký tự.
// * Các ô trống được biểu thị bằng ký tự `.` (dấu chấm). Bạn có thể bỏ qua các
// ô này khi kiểm tra.
// * Các số đã điền là các ký tự số từ `'1'` đến `'9'`.

// Nếu bất kỳ quy tắc nào trong ba quy tắc trên bị vi phạm, bảng Sudoku đó là
// không hợp lệ và bạn phải trả về `False`. Nếu tất cả các quy tắc đều được tuân
// thủ, bạn trả về `True`.
// Đây là
// một giải
// pháp rất
// hiệu quả
// cho bài toán"Valid Sudoku",
// sử dụng
// kỹ thuật**bitmask**
// để kiểm
// tra tính
// hợp lệ.
// Thuật toán
// này tối
// ưu hơn
// nhiều so
// với việc dùng`HashSet`,
// đặc biệt
// là về
// tốc độ, vì
// nó sử
// dụng các
// phép toán
// bitmask thay
// vì so
// sánh chuỗi
// hoặc đối tượng.

// ###
// Phân tích
// Thuật toán

// Ý tưởng
// cốt lõi
// là sử
// dụng các

// số nguyên (`int`) làm **mặt nạ bit (bitmask)** để theo dõi các số đã xuất
// hiện trong mỗi hàng, mỗi cột và mỗi khối 3x3.

// * **Một số nguyên 32-bit có thể biểu diễn 32 trạng thái** (bit từ 0 đến 31).
// Chúng ta có thể dùng các bit từ 1 đến 9 để biểu thị các số từ 1 đến 9.
// * Bit 1 (`1 << 1`) biểu thị số 1 đã xuất hiện.
// * Bit 2 (`1 << 2`) biểu thị số 2 đã xuất hiện.
// * ...và cứ thế.

// * Thuật toán sử dụng ba mảng số nguyên, mỗi mảng có 9 phần tử, để theo dõi
// các bitmask này:
// * `rowMask[9]`: Dùng cho 9 hàng. `rowMask[i]` lưu trạng thái các số đã xuất
// hiện trong hàng `i`.
// * `colMask[9]`: Dùng cho 9 cột. `colMask[j]` lưu trạng thái các số đã xuất
// hiện trong cột `j`.
// * `boxMask[9]`: Dùng cho 9 khối 3x3. `boxMask[box]` lưu trạng thái các số đã
// xuất hiện trong khối `box`.

// #### Các bước thực hiện

// 1. **Khởi tạo**: Ba mảng `rowMask`, `colMask`, và `boxMask` được khởi tạo với
// tất cả các giá trị là 0. Một giá trị 0 ban đầu có nghĩa là không có bit nào
// được bật, tức là chưa có số nào được nhìn thấy.

// 2. **Duyệt ma trận**: Thuật toán sử dụng hai vòng lặp lồng nhau để duyệt qua
// từng ô `(i, j)` của bảng Sudoku.

// 3. **Xử lý ô trống**: Nếu ô hiện tại là dấu `.`, nó sẽ được bỏ qua vì không
// ảnh hưởng đến tính hợp lệ.

// 4. **Kiểm tra tính hợp lệ**:
// * **Tạo mặt nạ bit**: Dòng `int bit = 1 << (c - '0');` chuyển đổi ký tự số
// thành một số nguyên (`c - '0'`) và tạo ra một bitmask tương ứng. Ví dụ, `'1'`
// trừ đi `'0'` bằng 1, `1 << 1` tạo ra mặt nạ bit `00000010b`.
// * **Xác định chỉ số khối**: Dòng `int box = (i / 3) * 3 + (j / 3);` là một
// cách khéo léo để tính chỉ số của khối 3x3 mà ô `(i, j)` thuộc về.
// * **Kiểm tra trùng lặp**: Các phép toán bitwise `&` (AND) được sử dụng để
// kiểm tra xem bit tương ứng với số hiện tại đã được bật trong bất kỳ mặt nạ
// nào chưa.
// * `rowMask[i] & bit`: Kiểm tra xem số này đã có trong hàng `i` chưa.
// * `colMask[j] & bit`: Kiểm tra xem số này đã có trong cột `j` chưa.
// * `boxMask[box] & bit`: Kiểm tra xem số này đã có trong khối 3x3 của nó chưa.
// * Nếu bất kỳ phép toán `AND` nào cho kết quả khác 0, điều đó có nghĩa là bit
// đó đã được bật trước đó, tức là đã có một số trùng lặp. Khi đó, hàm sẽ ngay
// lập tức trả về `false`.

// 5. **Cập nhật mặt nạ**:
// * Nếu không có số trùng lặp, các phép toán bitwise `|=` (OR) được sử dụng để
// bật bit tương ứng trong cả ba mặt nạ. Điều này đánh dấu rằng số đó đã được
// "nhìn thấy" trong hàng, cột và khối tương ứng.
// * `rowMask[i] |= bit;`
// * `colMask[j] |= bit;`
// * `boxMask[box] |= bit;`

// 6. **Kết quả cuối cùng**: Nếu vòng lặp hoàn tất mà không tìm thấy bất kỳ sự
// trùng lặp nào, điều đó có nghĩa là bảng Sudoku hợp lệ, và hàm sẽ trả về
// `true`.

// ### Tóm tắt

// Thuật toán này là một ví dụ điển hình về việc sử dụng bitmask để tối ưu hóa
// việc kiểm tra tính duy nhất. Thay vì lưu trữ các số đã thấy trong `Set` hoặc
// mảng boolean, nó sử dụng các bit của một số nguyên để đại diện cho sự hiện
// diện của các số từ 1 đến 9, giúp việc kiểm tra và cập nhật trở nên cực kỳ
// nhanh chóng và hiệu quả.
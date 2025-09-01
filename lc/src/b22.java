
// 37. Sudoku Solver(31/08/2025)
import java.util.*;

public class b22 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        char[][] board = new char[9][9];

        for (int i = 0; i < 9; i++) {
            String row = sc.nextLine();
            board[i] = row.toCharArray();
        }

        solveSudoku(board);// void là trả về true false không đặt biến
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Mặt nạ bit ghi lại các chữ số đã được sử dụng trong mỗi hàng/cột/khối.
    // Đối với chữ số d (1..9), chúng ta sử dụng bit (d-1). Một bit được bật có
    // nghĩa là "đã dùng".
    public static final int[] rowMask = new int[9];
    public static final int[] colMask = new int[9];
    public static final int[] boxMask = new int[9];

    // Danh sách phẳng của các ô trống; chúng ta sẽ sắp xếp lại nó tại chỗ với MRV.
    public static final int[] empties = new int[81];
    public static int emptyCount = 0;

    public static void solveSudoku(char[][] board) {
        // Khởi tạo các mặt nạ và thu thập các ô trống
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char ch = board[r][c];
                if (ch == '.') {
                    empties[emptyCount++] = r * 9 + c;
                } else {
                    int d = ch - '1'; // 0..8
                    int bit = 1 << d;
                    rowMask[r] |= bit;
                    colMask[c] |= bit;
                    boxMask[boxIndex(r, c)] |= bit;
                }
            }
        }
        dfs(board, 0);
    }

    // Tìm kiếm theo chiều sâu (DFS) với MRV: tại bước k, chọn ô trống có ít lựa
    // chọn nhất.
    public static boolean dfs(char[][] board, int k) {
        if (k == emptyCount)
            return true; // đã giải xong

        // Chọn ô tốt nhất (ít lựa chọn nhất) trong số các ô từ k..cuối, và hoán đổi nó
        // vào vị trí k
        int bestIdx = k;
        int bestChoicesMask = 0;
        int bestChoicesCount = 10; // lớn hơn số lựa chọn tối đa là 9

        for (int i = k; i < emptyCount; i++) {
            int pos = empties[i];
            int r = pos / 9, c = pos % 9, b = boxIndex(r, c);
            int used = rowMask[r] | colMask[c] | boxMask[b];
            int choices = (~used) & 0x1FF; // 9 bit thấp nhất
            int cnt = Integer.bitCount(choices);
            if (cnt < bestChoicesCount) {
                bestChoicesCount = cnt;
                bestChoicesMask = choices;
                bestIdx = i;
                if (cnt == 1)
                    break; // không thể tốt hơn 1
            }
            // Lỗi sớm: nếu bất kỳ ô nào có 0 lựa chọn, loại bỏ ngay
            if (cnt == 0)
                return false;
        }

        // Hoán đổi ô trống đã chọn vào vị trí k
        swap(empties, k, bestIdx);

        int pos = empties[k];
        int r = pos / 9, c = pos % 9, b = boxIndex(r, c);
        int choices = bestChoicesMask == 0
                ? ((~(rowMask[r] | colMask[c] | boxMask[b])) & 0x1FF)
                : bestChoicesMask;

        // Thử các chữ số bằng cách duyệt qua các bit đã bật (thủ thuật low-bit)
        while (choices != 0) {
            int bit = choices & -choices; // cô lập bit 1 thấp nhất
            int d = Integer.numberOfTrailingZeros(bit); // 0..8 cho chữ số 1..9

            place(board, r, c, b, d, bit);
            if (dfs(board, k + 1))
                return true;
            unplace(board, r, c, b, d, bit);

            choices -= bit; // chuyển sang ứng cử viên tiếp theo
        }

        // Khôi phục thứ tự khi quay lui
        swap(empties, k, k); // không làm gì nhưng giữ cho đối xứng rõ ràng
        return false;
    }

    public static void place(char[][] board, int r, int c, int b, int d, int bit) {
        board[r][c] = (char) ('1' + d);
        rowMask[r] |= bit;
        colMask[c] |= bit;
        boxMask[b] |= bit;
    }

    public static void unplace(char[][] board, int r, int c, int b, int d, int bit) {
        board[r][c] = '.';
        rowMask[r] ^= bit;
        colMask[c] ^= bit;
        boxMask[b] ^= bit;
    }

    public static int boxIndex(int r, int c) {
        return (r / 3) * 3 + (c / 3);
    }

    public static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
// ### Giải thích Đề bài: Sudoku Solver

// Đề bài **37. Sudoku Solver** yêu cầu bạn viết một chương trình để **giải một
// bảng Sudoku** đã cho.

// * Bạn sẽ được cung cấp một bảng Sudoku 9x9 với một số ô đã được điền sẵn các
// chữ số từ '1' đến '9'.
// * Các ô trống được biểu thị bằng ký tự `.` (dấu chấm).
// * Nhiệm vụ của bạn là điền các chữ số từ '1' đến '9' vào tất cả các ô trống
// sao cho bảng Sudoku cuối cùng là **hợp lệ**.

// #### Quy tắc của Sudoku

// Một bảng Sudoku được coi là hợp lệ khi nó tuân thủ ba quy tắc sau:

// 1. **Mỗi hàng** phải chứa các chữ số từ '1' đến '9' mà không có bất kỳ số nào
// lặp lại.
// 2. **Mỗi cột** phải chứa các chữ số từ '1' đến '9' mà không có bất kỳ số nào
// lặp lại.
// 3. **Mỗi khối 3x3** (trong số 9 khối 3x3) phải chứa các chữ số từ '1' đến '9'
// mà không có bất kỳ số nào lặp lại.

// #### Lưu ý quan trọng

// * Bảng Sudoku được cung cấp được đảm bảo là có **một và chỉ một** giải pháp
// hợp lệ. Điều này có nghĩa là bạn không cần phải lo lắng về việc có nhiều đáp
// án hoặc không có đáp án.
// * Bạn **không cần trả về** một bảng mới. Thay vào đó, bạn chỉ cần **thay đổi
// trực tiếp** bảng Sudoku đầu vào để nó trở thành bảng đã được giải.

// Tuyệt vời!
// Đây là
// một giải
// pháp rất
// tiên tiến
// cho bài
// toán Sudoku
// Solver.Nó không
// chỉ dùng
// thuật toán

// quay lui (backtracking) mà còn kết hợp các kỹ thuật tối ưu mạnh mẽ như
// bitmasks và

// MRV (Most Restricted Variable).

// Dưới đây là phiên bản hoàn chỉnh với hàm main được thêm vào và toàn bộ chú
// thích được dịch sang tiếng Việt.

// Phân tích Thuật toán
// Giải pháp này sử dụng một thuật toán

// quay lui (backtracking) được tối ưu hóa cao. Các kỹ thuật chính được áp dụng
// là:

// Bitmasks: Thay vì dùng mảng boolean hoặc HashSet để theo dõi các số đã sử
// dụng, thuật toán dùng các

// số nguyên (int) làm mặt nạ bit. Mỗi bit trong số nguyên đại diện cho sự hiện
// diện của một chữ số (từ 1 đến 9).

// MRV (Most Restricted Variable): Đây là một chiến lược heuristic. Thay vì điền
// vào các ô trống theo thứ tự cố định, thuật toán luôn chọn ô trống có ít lựa
// chọn hợp lệ nhất để điền vào tiếp theo. Điều này giúp giảm đáng kể số lượng
// nhánh phải duyệt, vì nếu một ô không có lựa chọn nào, thuật toán có thể quay
// lui ngay lập tức.

// Lưu danh sách ô trống: Thay vì duyệt toàn bộ bảng trong mỗi lần gọi đệ quy,
// thuật toán chỉ tạo một danh sách các ô trống (empties) ngay từ đầu.
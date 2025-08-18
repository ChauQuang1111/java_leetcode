
// 679. 24 Game(18/08/2025)
import java.util.*;

class b8 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        boolean a = judgePoint24(arr);
        System.out.println(a);

    }

    // Khai báo một hằng số có tên là EPS (Epsilon).
    // Giá trị của nó là 1e-6, tức là 0.000001.
    // Hằng số này được dùng để so sánh hai số thực có gần bằng nhau không,
    // vì phép tính với số thực có thể gây ra sai số nhỏ.
    public static final double EPS = 1e-6;

    // Phương thức đệ quy chính, giải quyết bài toán bằng cách quay lui
    // (backtracking).
    // A: mảng chứa các số hiện tại.
    // n: số lượng các số còn lại trong mảng A.
    public static boolean backtrack(double[] A, int n) {

        // Đây là điều kiện dừng của đệ quy (base case).
        // Nếu chỉ còn một số trong mảng (n == 1), ta kiểm tra nó có phải là 24 hay
        // không.
        if (n == 1)
            // Math.abs(A[0] - 24) tính giá trị tuyệt đối của hiệu số.
            // Nếu hiệu số này nhỏ hơn EPS, có nghĩa là A[0] rất gần với 24,
            // và ta coi như đã tìm thấy lời giải.
            return Math.abs(A[0] - 24) < EPS;

        // Vòng lặp thứ nhất để chọn số đầu tiên (A[i]) trong cặp số.
        for (int i = 0; i < n; i++) {
            // Vòng lặp thứ hai để chọn số thứ hai (A[j]) trong cặp.
            // j = i + 1 để đảm bảo mỗi cặp chỉ được xét một lần (ví dụ: (A[0], A[1]) nhưng
            // không phải (A[1], A[0])).
            for (int j = i + 1; j < n; j++) {

                // Lưu giá trị của hai số đã chọn vào các biến tạm thời để khôi phục sau này.
                double a = A[i], b = A[j];

                // Đây là một kỹ thuật để "xóa" hai số a và b.
                // Số ở vị trí cuối cùng của mảng (A[n-1]) được di chuyển đến vị trí của số thứ
                // hai (A[j]).
                A[j] = A[n - 1];

                // --- Bắt đầu thử các phép toán ---

                // 1. Phép cộng: a + b
                // Đặt kết quả vào vị trí của số đầu tiên (A[i]).
                A[i] = a + b;
                // Gọi đệ quy với mảng mới (đã giảm kích thước)
                // Nếu tìm thấy lời giải, trả về true ngay lập tức.
                if (backtrack(A, n - 1))
                    return true;

                // 2. Phép trừ: a - b
                A[i] = a - b;
                if (backtrack(A, n - 1))
                    return true;

                // 3. Phép trừ: b - a
                A[i] = b - a;
                if (backtrack(A, n - 1))
                    return true;

                // 4. Phép nhân: a * b
                A[i] = a * b;
                if (backtrack(A, n - 1))
                    return true;

                // 5. Phép chia: a / b
                // Kiểm tra mẫu số b có gần bằng 0 hay không để tránh lỗi chia cho 0.
                if (Math.abs(b) > EPS) {
                    A[i] = a / b;
                    if (backtrack(A, n - 1))
                        return true;
                }

                // 6. Phép chia: b / a
                // Kiểm tra mẫu số a có gần bằng 0 hay không.
                if (Math.abs(a) > EPS) {
                    A[i] = b / a;
                    if (backtrack(A, n - 1))
                        return true;
                }

                // --- Kết thúc thử các phép toán ---

                // Đây là bước quay lui (backtracking).
                // Khôi phục lại mảng về trạng thái ban đầu để thử cặp số (i, j) khác.
                A[i] = a;
                A[j] = b;
            }
        }

        // Nếu đã thử tất cả các cặp số và các phép toán mà không tìm thấy lời giải,
        // trả về false.
        return false;
    }

    // Phương thức chính khởi tạo, được gọi đầu tiên.
    public static boolean judgePoint24(int[] nums) {
        // Tạo một mảng double để lưu trữ các số.
        double[] A = new double[nums.length];
        // Sao chép các số từ mảng int sang mảng double.
        for (int i = 0; i < nums.length; i++)
            A[i] = nums[i];
        // Bắt đầu quá trình quay lui.
        return backtrack(A, A.length);
    }
}
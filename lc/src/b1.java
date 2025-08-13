
// 12/08/2025
// 215. Kth Largest Element in an Array
import java.util.*;

public class b1 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int result = findKthLargest(nums, k);
        System.out.println(result);
        sc.close();
    }

    public static int findKthLargest(int[] nums, int k) {
        int[] count = new int[20000];
        for (int num : nums) {
            count[num + 10000]++;
        }
        // Dòng code này tạo ra một vòng lặp lặp đi lặp lại qua mảng count theo thứ tự
        // ngược: từ phần tử cuối cùng đến phần tử đầu tiên.// Trong ngữ cảnh của bài
        // toán tìm phần tử lớn thứ k bằng phương pháp đếm, việc duyệt ngược như thế này
        // là rất quan trọng. Mảng count lưu tần suất của các số đã được dịch, trong đó
        // các chỉ số lớn hơn tương ứng với các số lớn hơn. Bằng cách duyệt từ cuối mảng
        // (i = count.length - 1), bạn đang bắt đầu kiểm tra từ phần tử
        // có giá trị lớn nhất và di chuyển dần về các phần tử nhỏ hơn, giúp tìm ra phần
        // tử lớn thứ k một cách chính xác.
        for (int i = count.length - 1; i >= 0; i--) {
            if (count[i] > 0) {
                k -= count[i];
            }
            if (k <= 0) {
                return i - 10000;
            }
        }
        return -1;
    }

}
// Một mảng count có kích thước 20001 được tạo ra và tất cả các phần tử ban đầu
// đều bằng 0.

// Lý do kích thước là 20001 là vì bài toán giả định các số trong mảng nums nằm
// trong khoảng từ -10000 đến 10000. Để xử lý các số âm, chúng ta cần "dịch"
// (offset) các giá trị này.

// Việc cộng 10000 vào mỗi số (num + 10000) sẽ đảm bảo rằng tất cả các giá trị
// sau khi dịch đều không âm và nằm trong phạm vi từ 0 (cho số -10000) đến 20000
// (cho số 10000).

// Mỗi chỉ số i của mảng count sẽ tương ứng với số i - 10000 ban đầu.
// Vòng lặp này duyệt qua từng phần tử num trong mảng nums.

// Với mỗi num, chúng ta tăng giá trị tại chỉ số num + 10000 của mảng count lên
// 1.

// Sau khi vòng lặp kết thúc, mảng count sẽ lưu trữ số lần xuất hiện (tần suất)
// của mỗi số trong mảng nums. Ví dụ, count[10005] sẽ cho biết có bao nhiêu số 5
// trong mảng ban đầu.
// Vòng lặp này duyệt qua mảng count từ cuối về đầu (từ chỉ số 20000 về 0).

// Điều này tương đương với việc duyệt qua các số trong mảng ban đầu từ lớn nhất
// đến nhỏ nhất.

// i đại diện cho một giá trị đã được dịch, và i - 10000 là giá trị thực tế của
// số đó.

// if count[i] > 0:: Kiểm tra xem có số nào tương ứng với giá trị i - 10000 hay
// không.

// k -= count[i]: Nếu có, chúng ta giảm k đi số lượng các số đó. Về mặt logic,
// chúng ta đang "loại bỏ" các phần tử lớn nhất.

// if k <= 0:: Sau khi giảm k, nếu k nhỏ hơn hoặc bằng 0, điều đó có nghĩa là
// phần tử lớn thứ k đã được tìm thấy.
// Phần tử này chính là giá trị tương ứng với chỉ số i hiện tại.

// return i - 10000: Trả về giá trị thực tế của số đó bằng cách đảo ngược phép
// dịch
// Nếu vòng lặp kết thúc mà k vẫn lớn hơn 0 (ví dụ: k quá lớn so với số lượng
// phần tử trong mảng), hàm sẽ trả về -1.
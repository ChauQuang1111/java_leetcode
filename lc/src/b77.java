
// 2048. Next Greater Numerically Balanced Number(24/10/2025)
import java.util.*;

public class b77 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();
        System.out.println(nextBeautifulNumber(n));
    }

    public static final int[] balance = new int[] {
            1, 22, 122, 212, 221, 333, 1333, 3133, 3313, 3331, 4444, 14444, 22333, 23233,
            23323, 23332, 32233, 32323, 32332, 33223, 33232, 33322, 41444, 44144, 44414,
            44441, 55555, 122333, 123233, 123323, 123332, 132233, 132323, 132332, 133223,
            133232, 133322, 155555, 212333, 213233, 213323, 213332, 221333, 223133, 223313,
            223331, 224444, 231233, 231323, 231332, 232133, 232313, 232331, 233123, 233132,
            233213, 233231, 233312, 233321, 242444, 244244, 244424, 244442, 312233, 312323,
            312332, 313223, 313232, 313322, 321233, 321323, 321332, 322133, 322313, 322331,
            323123, 323132, 323213, 323231, 323312, 323321, 331223, 331232, 331322, 332123,
            332132, 332213, 332231, 332312, 332321, 333122, 333212, 333221, 422444, 424244,
            424424, 424442, 442244, 442424, 442442, 444224, 444242, 444422, 515555, 551555,
            555155, 555515, 555551, 666666, 1224444
    };

    /**
     * Tìm số cân bằng số học nhỏ nhất mà lớn hơn số n cho trước.
     * 
     * Số nguyên đầu vào.
     * Số cân bằng số học nhỏ nhất > n.
     */
    public static int nextBeautifulNumber(int n) {
        // 1. Mục tiêu: Tìm số x trong mảng 'balance' sao cho x là nhỏ nhất và x > n.
        // Điều này tương đương với việc tìm số nhỏ nhất x sao cho x >= n + 1.

        // 2. Sử dụng Arrays.binarySearch để tìm vị trí của (n + 1) trong mảng
        // 'balance'.
        int i = Arrays.binarySearch(balance, n + 1);

        // 3. Xử lý kết quả của tìm kiếm nhị phân:
        if (i < 0) {
            // Nếu i < 0, tức là (n + 1) KHÔNG có trong mảng.
            // i sẽ là: -(điểm chèn) - 1.
            // Điểm chèn là chỉ mục của phần tử đầu tiên lớn hơn (n + 1).
            // Ta cần chuyển i về chỉ mục của phần tử đó (i_chèn = -i - 1).
            i = -i - 1;
        }
        // Nếu i >= 0 (Trường hợp tìm thấy): i là chỉ mục của (n + 1),
        // đây chính là số cân bằng số học đầu tiên >= n + 1.

        // 4. Trả về phần tử tại chỉ mục i, đây chính là số cân bằng số học
        // lớn hơn n tiếp theo.
        return balance[i];
    }
}

// Mảng chứa tất cả các "Số Cân Bằng Số Học" (Numerically Balanced Numbers)
// đã được tính toán trước và sắp xếp theo thứ tự tăng dần.
// Việc này khai thác giới hạn nhỏ của bài toán (các số này không quá lớn).

// Bạn đang hỏi về bài toán **"2048. Next Greater Numerically Balanced Number"**
// (Số cân bằng số học lớn hơn tiếp theo) trên LeetCode.

// ## Giải thích Đề bài

// Đề bài yêu cầu bạn tìm **số cân bằng số học lớn hơn tiếp theo** (the next
// greater numerically balanced number) so với một số nguyên dương $n$ đã cho.

// ### 1. Số Cân bằng Số học (Numerically Balanced Number) là gì?

// Một số nguyên dương được gọi là **cân bằng số học** nếu với **mỗi chữ số
// $d$** có trong số đó, thì **số lần xuất hiện** của chữ số $d$ trong số đó
// phải **bằng chính giá trị của $d$**.

// #### Ví dụ về Số Cân bằng Số học:

// * **22:**
// * Chữ số có mặt là **2**.
// * Số lần xuất hiện của chữ số **2** là **2** lần.
// * Vì $2 = 2$, nên **22** là số cân bằng số học.
// * **1333:**
// * Chữ số **1** xuất hiện **1** lần.
// * Chữ số **3** xuất hiện **3** lần.
// * Vì $1 = 1$ và $3 = 3$, nên **1333** là số cân bằng số học.
// * **122:**
// * Chữ số **1** xuất hiện **1** lần.
// * Chữ số **2** xuất hiện **2** lần.
// * Vì $1 = 1$ và $2 = 2$, nên **122** là số cân bằng số học.

// #### Ví dụ về Số KHÔNG Cân bằng Số học:

// * **33:**
// * Chữ số **3** xuất hiện **2** lần.
// * Vì $2 \neq 3$, nên **33** không phải là số cân bằng số học.
// * **121:**
// * Chữ số **1** xuất hiện **2** lần.
// * Chữ số **2** xuất hiện **1** lần.
// * Đối với chữ số **1**: số lần xuất hiện ($2$) $\neq$ giá trị ($1$).
// * Đối với chữ số **2**: số lần xuất hiện ($1$) $\neq$ giá trị ($2$).
// * Nên **121** không phải là số cân bằng số học.

// ***

// ### 2. Yêu cầu của Bài toán

// Cho một số nguyên dương $n$. Bạn cần tìm **số cân bằng số học nhỏ nhất** mà
// **lớn hơn** $n$.

// Tóm lại:
// $$\text{Kết quả} = \min \{\text{số cân bằng số học } x \mid x > n\}$$

// #### Ví dụ minh họa:

// * **Nếu $n = 1$:**
// * Các số cân bằng số học (theo thứ tự tăng dần) là: **1**, 22, 122, 212, 221,
// 333, 1333, ...
// * Số cân bằng số học đầu tiên lớn hơn 1 là **22**.
// * **Nếu $n = 1000$:**
// * Các số cân bằng số học lớn hơn 1000 là: 1333, 3133, 3313, 3331, 4444, ...
// * Số cân bằng số học nhỏ nhất lớn hơn 1000 là **1333**.
// Đây là một cách tiếp cận rất **hiệu quả và đơn giản** để giải quyết bài toán
// "2048. Next Greater Numerically Balanced Number" (Số Cân Bằng Số Học Lớn Hơn
// Tiếp Theo).

// Thuật toán này dựa trên một nhận xét quan trọng: **phạm vi giới hạn của bài
// toán**.

// ---

// ## Giải thích Thuật toán (Pre-computation và Binary Search)

// Thuật toán trong đoạn mã trên sử dụng phương pháp **tính toán trước**
// (pre-computation) và **tìm kiếm nhị phân** (binary search) để tìm ra kết quả.

// ### 1. Tính toán trước (Pre-computation)

// Các số cân bằng số học (Numerically Balanced Numbers) là rất hiếm và không
// quá lớn trong giới hạn của các kiểu dữ liệu số nguyên tiêu chuẩn (ví dụ,
// $2^{31}-1 \approx 2 \times 10^9$).

// * Số cân bằng số học lớn nhất có thể là $666666$ (6 chữ số $6$, tổng cộng $6$
// chữ số).
// * Số cân bằng số học tiếp theo sẽ phải chứa chữ số $7$ (xuất hiện 7 lần), có
// nghĩa là nó sẽ có ít nhất 7 chữ số.
// * Số cân bằng số học lớn nhất trong mảng `balance` là $1224444$ (tổng cộng 7
// chữ số: một chữ số 1, hai chữ số 2, bốn chữ số 4. Tổng số chữ số là
// $1+2+4=7$).
// * Do giới hạn của $n$ (thường là $10^6$ hoặc $10^7$ trong các bài toán
// LeetCode), mảng `balance` đã liệt kê tất cả các số cân bằng số học lên đến
// một giới hạn đủ lớn (trong trường hợp này, đến khoảng $1.2$ triệu, nhưng các
// số cân bằng thực tế có thể lên tới $6,666,666$ hoặc cao hơn một chút nếu xét
// các hoán vị của $1,2,2,3,3,3$). Mảng được cung cấp đã liệt kê các số cân bằng
// lên đến $666666$ và một số số $7$ chữ số khác như $1224444$.

// * Mảng **`balance`** chính là danh sách **tất cả** các số cân bằng số học có
// giá trị nhỏ, được sắp xếp theo thứ tự tăng dần.

// ### 2. Tìm kiếm Nhị phân (Binary Search)

// Mục tiêu của bài toán là tìm số cân bằng số học nhỏ nhất $x$ sao cho $x > n$.
// Điều này tương đương với việc tìm số cân bằng số học nhỏ nhất $x$ trong danh
// sách **lớn hơn hoặc bằng** $n + 1$.

// 1. **Chuyển đổi mục tiêu tìm kiếm:** Hàm `nextBeautifulNumber(int n)` cần tìm
// số $\min(x)$ với $x \in \text{balance}$ và $x \geq n + 1$.

// 2. **Sử dụng `Arrays.binarySearch(balance, n + 1)`:**
// * Hàm tìm kiếm nhị phân được sử dụng để tìm vị trí của giá trị **$n + 1$**
// trong mảng `balance`.

// 3. **Xử lý kết quả của `binarySearch`:**
// * **Trường hợp 1: Tìm thấy (Exact Match):** Nếu $n + 1$ là một số cân bằng số
// học, `binarySearch` trả về một chỉ mục $i \geq 0$. Kết quả là `balance[i]`.
// * **Trường hợp 2: Không tìm thấy (Insertion Point):** Nếu $n + 1$ không phải
// là số cân bằng số học, `binarySearch` trả về một giá trị âm: $-(\text{điểm
// chèn} + 1)$. **Điểm chèn** (Insertion Point) chính là chỉ mục của phần tử đầu
// tiên lớn hơn $n + 1$.
// * Để chuyển giá trị âm này về chỉ mục cần tìm, ta thực hiện:
// $$i = -i - 1$$
// Ví dụ: Nếu `binarySearch` trả về $-5$, thì $i = -(-5) - 1 = 5 - 1 = 4$. Chỉ
// mục $4$ là vị trí của phần tử đầu tiên lớn hơn $n + 1$.

// 4. **Trả về kết quả:** Sau khi tìm được chỉ mục $i$ chính xác, `balance[i]`
// chính là **số cân bằng số học nhỏ nhất lớn hơn hoặc bằng** $n + 1$ (tức là số
// cân bằng số học nhỏ nhất lớn hơn $n$).

// ---

// ## Ưu điểm và Nhược điểm

// ### Ưu điểm

// | Ưu điểm | Giải thích |
// | :--- | :--- |
// | **Tốc độ cực nhanh ($\mathcal{O}(\log k)$)** | Vì mảng `balance` đã được
// tính toán trước và sắp xếp, việc tìm kiếm chỉ mất thời gian $\mathcal{O}(\log
// k)$ (với $k$ là kích thước của mảng `balance`), rất nhanh so với việc tạo ra
// các số một cách động. |
// | **Đơn giản** | Logic tìm kiếm rất gọn gàng và dễ hiểu. |
// | **Hoàn hảo cho giới hạn bài toán** | Do số lượng các số cân bằng số học là
// hữu hạn và không lớn trong giới hạn $10^7$, phương pháp này là tối ưu. |

// ### Nhược điểm

// | Nhược điểm | Giải thích |
// | :--- | :--- |
// | **Yêu cầu tính toán trước** | Phương pháp này chỉ hoạt động vì chúng ta đã
// liệt kê được tất cả các số cân bằng số học. Nếu giới hạn của $n$ lớn hơn
// nhiều (ví dụ $10^{18}$), mảng này sẽ quá lớn hoặc không thể xây dựng, và cần
// phải sử dụng thuật toán sinh số động (dynamic generation). |
// | **Thiếu tính tổng quát** | Nó là giải pháp cụ thể cho bài toán này, không
// dễ dàng áp dụng cho các bài toán tương tự với quy tắc "cân bằng" phức tạp hơn
// hoặc giới hạn lớn hơn. |
// Bài **“Sort Integers by The Number of 1 Bits”** (25/02/2026)

// // Ok mình sẽ:

// // 1.✅Giải thích thuật toán bạn đang dùng 2.✅Thêm`main`dùng`Scanner`3.✅Chú thích chi tiết từng dòng code 4.✅Giải thích vì sao trick`10001*bitCount`hoạt động

// // ---

// // #🔥1 ️⃣Ý tưởng thuật toán của bạn

// // Bạn dùng một kỹ thuật rất hay👇

// // ##🎯Trick chính

// // Thay vì sort theo 2 điều kiện:

// // *Số lượng bit 1*Giá trị số

// // Bạn biến mỗi số thành:

// // ```arr[i]=arr[i]+10001*số_bit_1```

// // Vì:

// // *`arr[i]<=10000`(theo đề LeetCode)*Nên nhân`10001`đảm bảo phần bitCount luôn lớn hơn giá trị gốc

// // ---

// // ##📌Ví dụ

// // Giả sử số 5:

// // ```5=101(2 bit 1)```

// // Ta biến thành:

// // ```5+10001*2=20007```

// // Số 3:

// // ```3=11(2 bit 1)3+10001*2=20005```

// // Khi sort:

// // ```20005<20007→3 đứng trước 5```

// // Tức là:

// // *So sánh theo bitCount trước*Nếu bằng nhau thì theo giá trị gốc

// // Rất thông minh👏

// ---

// #🔥2 ️⃣Giải thích QuickSort của bạn

// Bạn đang dùng:

// ###Hoare Partition Scheme

// Đặc điểm:

// *left chạy từ trái*right chạy từ phải*swap khi cần*return right

// ---

// #🔥3 ️⃣Code đầy đủ có main+chú thích

// ```java

import java.util.*;

public class b199 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        arr = sortByBits(arr);

        // In kết quả
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }

        sc.close();
    }

    // Hàm chính của bài toán
    public static int[] sortByBits(int[] arr) {
        int n = arr.length;

        // BƯỚC 1: Encode mỗi số
        // arr[i] = giá trị gốc + 10001 * số bit 1
        for (int i = 0; i < n; i++) {
            arr[i] += 10001 * Integer.bitCount(arr[i]);
        }

        // BƯỚC 2: QuickSort mảng
        quicksort(arr, 0, n - 1);

        // BƯỚC 3: Decode lại giá trị gốc
        for (int i = 0; i < n; i++) {
            arr[i] %= 10001;
        }

        return arr;
    }

    // QuickSort dùng Hoare partition
    public static void quicksort(int[] nums, int left, int right) {
        if (left < right) {
            int part = partition(nums, left - 1, right + 1);

            quicksort(nums, left, part);
            quicksort(nums, part + 1, right);
        }
    }

    // Hàm partition kiểu Hoare
    public static int partition(int[] nums, int left, int right) {

        // Lấy pivot bằng median of three
        int current = getPivot(
                nums[left + 1],
                nums[(left + right) >>> 1],
                nums[right - 1]);

        int temp;

        while (true) {

            // tìm phần tử >= pivot từ trái
            do {
                left++;
            } while (nums[left] < current);

            // tìm phần tử <= pivot từ phải
            do {
                right--;
            } while (nums[right] > current);

            // nếu hai con trỏ gặp nhau
            if (left >= right)
                return right;

            // swap
            temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
        }
    }

    // Median of three để tránh worst case
    public static int getPivot(int a, int b, int c) {
        if ((a >= b) ^ (a >= c))
            return a;
        if ((a >= b) ^ (c >= b))
            return b;
        return c;
    }
}

// ---

// #🔥4 ️⃣

// Độ phức tạp

// ###
// Thời gian:

// *Encode:

// O(n)
// * QuickSort trung bình:

// O(n log n)
// * Decode: O(n)

// 👉 Tổng: **O(n log n)**

// ---

// # 🔥 5️⃣ Tại sao dùng 10001?

// Vì theo đề:

// ```
// 0 <= arr[i] <= 10000
// ```

// Nên:

// ```
// 10001 * bitCount
// ```

// Luôn đảm bảo phần bitCount nằm ở "bậc cao hơn".

// ---

// # 🔥 6️⃣ Nếu đi phỏng vấn

// Người ta sẽ hỏi:

// > Tại sao không dùng Comparator?

// Bạn có thể nói:

// * Cách này giảm chi phí so sánh 2 key
// * Tối ưu hơn trong custom quicksort
// * Tránh phải tạo object wrapper

// ---

// Nếu bạn muốn mình:

// * 🔥 Vẽ sơ đồ partition chạy từng bước
// * 🔥 So sánh Hoare vs Lomuto
// * 🔥 Tối ưu lại code cho đẹp hơn
// * 🔥 Viết lại bằng while loop đúng style bạn thích

// Nói mình biết 👍

// ---

// ## 📌 Yêu cầu đề bài

// Cho một mảng số nguyên `arr`.

// Bạn phải sắp xếp mảng theo:

// 1. 🔹 **Tiêu chí chính**: Số lượng bit `1` trong biểu diễn nhị phân của mỗi
// số (tăng dần).
// 2. 🔹 **Tiêu chí phụ**: Nếu hai số có cùng số lượng bit `1` → sắp xếp theo
// **giá trị số tăng dần**.

// ---

// ## 🧠 Hiểu rõ hơn: “Số lượng bit 1” là gì?

// Mỗi số nguyên có thể viết dưới dạng **nhị phân (base 2)**.

// Ví dụ:

// | Số | Nhị phân | Số lượng bit 1 |
// | -- | -------- | -------------- |
// | 3 | 11 | 2 |
// | 5 | 101 | 2 |
// | 7 | 111 | 3 |
// | 8 | 1000 | 1 |

// ---

// ## 📌 Ví dụ đề bài

// ### Input:

// ```
// arr = [0,1,2,3,4,5,6,7,8]
// ```

// ### Bước 1: Đếm số bit 1 của từng số

// | Số | Nhị phân | Số bit 1 |
// | -- | -------- | -------- |
// | 0 | 0 | 0 |
// | 1 | 1 | 1 |
// | 2 | 10 | 1 |
// | 3 | 11 | 2 |
// | 4 | 100 | 1 |
// | 5 | 101 | 2 |
// | 6 | 110 | 2 |
// | 7 | 111 | 3 |
// | 8 | 1000 | 1 |

// ---

// ### Bước 2: Sắp xếp theo quy tắc

// * Nhóm có **0 bit 1**: `[0]`
// * Nhóm có **1 bit 1**: `[1,2,4,8]` (sắp xếp tăng dần)
// * Nhóm có **2 bit 1**: `[3,5,6]`
// * Nhóm có **3 bit 1**: `[7]`

// ---

// ### ✅ Output:

// ```
// [0,1,2,4,8,3,5,6,7]
// ```

// ---

// ## 🎯 Tóm lại bản chất bài toán

// Bài này thực chất là:

// > Sort theo **2 key**
// >
// > key1 = số lượng bit 1
// > key2 = giá trị số

// ---

// ## 💡 Gợi ý cách làm (ý tưởng)

// 1. Viết hàm đếm số bit 1:

// * Dùng `Integer.bitCount(x)` (Java)
// * Hoặc dùng while chia 2
// 2. Dùng custom sort với comparator:

// * So sánh bitCount trước
// * Nếu bằng nhau → so sánh số

// ---

// ## 🔥 Độ khó bài này

// * Không khó về thuật toán
// * Chủ yếu kiểm tra:

// * Hiểu binary
// * Custom comparator
// * Biết bit manipulation

// ---

// Nếu bạn muốn mình:

// * ✔ Giải bằng Java (theo style bạn thích dùng while)
// * ✔ Hoặc giải bằng Python
// * ✔ Hoặc tối ưu bằng bit trick

// Nói mình biết bạn đang code ngôn ngữ nào 👍

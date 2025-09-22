
// // #3005. Count Elements With Maximum Frequency(22/09/2025)from collections
import java.util.*;

public class b44 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();
        int[] nums = new int[n];

        // Nhập mảng
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        // Tạo đối tượng Solution và gọi hàm

        int result = maxFrequencyElements(nums);

        // In kết quả
        System.out.println(result);

        sc.close();
    }

    public static int maxFrequencyElements(int[] nums) {
        int n = nums.length;

        // B1: Tìm giá trị lớn nhất trong mảng nums
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }

        // B2: Tạo mảng đếm tần suất xuất hiện của từng số
        int[] arr = new int[max + 1];
        for (int i = 0; i < n; i++) {
            arr[nums[i]]++;
        }

        // B3: Tìm tần suất lớn nhất (maxFreq) và số lượng phần tử đạt maxFreq
        int maxFreq = 0;
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (maxFreq < arr[i]) {
                maxFreq = arr[i]; // cập nhật tần suất lớn nhất
                count = 1; // reset lại số lượng phần tử đạt maxFreq
            } else if (maxFreq == arr[i]) {
                count++; // có thêm 1 số nữa đạt cùng maxFreq
            }
        }

        // B4: Tổng số lần xuất hiện = count * maxFreq
        return count * maxFreq;
    }

}

// Ok👍

// mình giải
// thích chi
// tiết thuật
// toán trong
// code bạn đưa:

// ---

// ###**Đề bài:**

// Ta cần**
// đếm tổng
// số lần
// xuất hiện**
// của những
// phần tử có**
// tần suất
// cao nhất**
// trong mảng`nums`.

// Ví dụ:

// ```nums=[1,2,2,3,1,4]```

// *1
// xuất hiện 2 lần*2
// xuất hiện 2 lần*3
// xuất hiện 1 lần*4
// xuất hiện 1 lần👉
// Tần suất
// cao nhất = 2👉
// Các phần
// tử đạt
// tần suất
// cao nhất = {1, 2}(2
// phần tử)👉
// Tổng số
// lần xuất hiện=2\*2=**4**

// ---

// ###**Giải thích code:**

// ####🔹B1:
// Tìm giá
// trị lớn
// nhất trong mảng

// ```java
// int max = Integer.MIN_VALUE;for(
// int i = 0;i<n;i++)
// {
// if (nums[i] > max) {
// max = nums[i];
// }
// }```

// *
// Mục đích:
// để biết mảng`arr`(mảng tần suất)
// cần có
// kích thước
// bao nhiêu.*
// Ví dụ:nếu`nums`
// có giá
// trị lớn nhất=7→
// ta cần mảng`arr[8]`(0..7).

// ---

// ####🔹B2:
// Đếm tần
// suất xuất hiện

// ```java
// int[] arr = new int[max + 1];for(
// int i = 0;i<n;i++)
// {
// arr[nums[i]]++;
// }```

// *`arr[x]`=
// số lần
// phần tử`x`
// xuất hiện trong`nums`.*
// Sau vòng lặp,`arr`
// sẽ chứa
// đầy đủ
// tần suất.

// Ví dụ:`nums=[1,2,2,3,1,4]`👉`arr=[0,2,2,1,1]`

// ---

// ####🔹B3:
// Tìm tần
// suất lớn
// nhất và
// số phần
// tử đạt
// được nó

// ```java
// int maxFreq = 0;
// int count = 0;for(
// int i = 0;i<arr.length;i++)
// {
// if (maxFreq < arr[i]) {
// maxFreq = arr[i]; // cập nhật maxFreq mới
// count = 1; // reset số lượng phần tử đạt maxFreq
// } else if (maxFreq == arr[i]) {
// count++; // thêm 1 phần tử nữa đạt cùng maxFreq
// }
// }```

// *`maxFreq`:
// tần suất
// lớn nhất
// tìm được.*`count`:
// số lượng
// phần tử
// có tần suất=`maxFreq`.

// Ví dụ:`arr=[0,2,2,1,1]`👉`maxFreq=2`,`count=2`(phần tử`1`và`2`).

// ---

// ####🔹B4:
// Tính kết quả

// ```java return count*maxFreq;```

// *
// Tổng số
// lần xuất hiện=(
// số phần
// tử có
// tần suất
// cao nhất)\*(
// tần suất
// cao nhất).*
// Với ví
// dụ trên:`2*2=4`.

// ---

// ###**
// Độ phức tạp**

// *
// Thời gian:`O(n + max(nums))`
// * Bộ nhớ: `O(max(nums))`

// ---

// 👉 Tóm gọn:
// Thuật toán này **đếm tần suất từng phần tử**, sau đó **tìm phần tử nào xuất
// hiện nhiều nhất** rồi trả về **tổng số lần xuất hiện của tất cả phần tử có
// cùng tần suất cao nhất**.

// ---

// Bạn có muốn mình viết lại cách này **ngắn hơn và tối ưu hơn** (không cần tìm
// `max` trước, dùng HashMap luôn) không?

// // import Counter

// // class Solution:
// // def maxFrequencyElements(self, nums: list[int]) -> int:
// // # B1: Đếm tần suất của từng phần tử trong nums
// // freq = Counter(nums) # vd: [1,2,2,3,1,4] -> {1:2, 2:2, 3:1, 4:1}

// // # B2: Tìm tần suất lớn nhất
// // max_freq = max(freq.values()) # vd: max_freq = 2

// // # B3: Cộng tổng số lần xuất hiện của tất cả phần tử có tần suất = max_freq
// // result = sum(count for count in freq.values() if count == max_freq)
// // # vd: freq = {1:2, 2:2, 3:1, 4:1} -> result = 2+2 = 4

// // return result

// // # Ok 👍 mình sẽ giải thích đề **LeetCode 3005 - Count Elements With
// Maximum Frequency**.

// // # ---

// // # ## 📌 Đề bài

// // # Cho một mảng số nguyên `nums`, bạn cần:

// // # 1. Tìm **tần suất xuất hiện lớn nhất**

// // trong mảng (một số xuất hiện nhiều nhất).
// // # 2. Trả về **tổng số lần xuất hiện của tất cả các số có tần suất bằng tần
// suất lớn nhất**.

// // # ---

// // # ## 🚀 Ví dụ

// // # ### Ví dụ 1

// // # ```
// // # Input: nums = [1,2,2,3,1,4]
// // # ```

// // # * Tần suất:

// // # * 1 → xuất hiện 2 lần
// // # * 2 → xuất hiện 2 lần
// // # * 3 → xuất hiện 1 lần
// // # * 4 → xuất hiện 1 lần
// // # * Tần suất lớn nhất = **2**
// // # * Có 2 số đạt tần suất này: `1` và `2`.
// // # * Tổng số lần xuất hiện = 2 (cho 1) + 2 (cho 2) = **4**.

// // # 👉 Output:

// // # ```
// // # 4
// // # ```

// // # ---

// // # ### Ví dụ 2

// // # ```
// // # Input: nums = [1,2,3,4,5]
// // # ```

// // # * Tần suất:

// // # * 1,2,3,4,5 → mỗi số chỉ xuất hiện 1 lần.
// // # * Tần suất lớn nhất = 1
// // # * Có 5 số đạt tần suất này
// // # * Tổng số lần xuất hiện = 5 × 1 = **5**

// // # 👉 Output:

// // # ```
// // # 5
// // # ```

// // # ---

// // # ## 📊 Ý tưởng thuật toán

// // # 1. Dùng **hashmap (map hoặc dictionary)** để đếm tần suất xuất hiện của
// từng số.
// // # 2. Tìm giá trị tần suất lớn nhất `max_freq`.
// // # 3. Duyệt lại hashmap, cộng tất cả các số lần xuất hiện của những phần tử
// có `freq == max_freq`.

// // # ---

// // # ## ⏱️ Độ phức tạp

// // # * Thời gian: **O(n)** (duyệt mảng và map).
// // # * Bộ nhớ: **O(n)** (lưu tần suất).

// // # ---

// // # 👉 Bạn có muốn mình viết luôn **code Java + Python có chú thích** cho
// bài này không?
// // À câu hỏi rất hay 👍

// // Khi bạn viết:

// // ```java
// // int[] arr = new int[max + 1];
// // ```

// // là để **tạo mảng đếm tần suất**.

// // ---

// // ### Giải thích:

// // * `max` là **giá trị lớn nhất** trong mảng `nums`.
// // * Nếu bạn muốn đếm tần suất xuất hiện của từng số từ `0` đến `max`, thì
// cần một mảng có **chỉ số từ 0 đến max**.
// // * Trong Java, mảng có độ dài `L` thì chỉ số chạy từ `0` đến `L - 1`.

// // ➡️ Vì vậy, nếu bạn chỉ tạo mảng `new int[max]` thì chỉ số cuối cùng sẽ là
// `max - 1`, **không đủ chỗ để đếm phần tử bằng `max`**.

// // ---

// // ### Ví dụ:

// // Giả sử `nums = {1, 3, 2, 3}`

// // * `max = 3`

// // * Bạn muốn có mảng `arr` lưu tần suất cho số `0, 1, 2, 3`.

// // * Nếu bạn tạo:

// // ```java
// // int[] arr = new int[max]; // int[3]
// // ```

// // thì `arr` chỉ có index `0,1,2`, không thể lưu cho số `3`. → **Lỗi
// `IndexOutOfBounds`**.

// // * Còn khi viết:

// // ```java
// // int[] arr = new int[max + 1]; // int[4]
// // ```

// // thì `arr` có index `0,1,2,3` → đủ để đếm cả số `3`.

// // ---

// // 👉 Nói ngắn gọn: **`+1` để mảng có index từ `0` đến `max` (bao gồm cả
// `max`).**

// // ---

// // Bạn có muốn mình so sánh luôn với cách dùng `HashMap` (không cần `+1`,
// không phụ thuộc `max`) để thấy ưu/nhược điểm không?

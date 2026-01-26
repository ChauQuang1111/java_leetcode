
// # Minimum Absolute Difference(26/01/2026)
// # **Minimum Absolute Difference – Giải thích đề bài (dễ hiểu)**
// # Ok 👍 mình **giải thích thuật toán từng bước** và **thêm chú thích trực tiếp vào code** cho bạn, đúng theo cách tư duy khi học / đi thi.
// Dưới đây là **giải thích thuật toán chi tiết, đúng tư duy của đoạn code Java bạn đưa**, vì đoạn này **không sort bằng `Arrays.sort`** mà dùng **Counting Sort (đánh dấu)** – khá hay cho latest constraints 👍
import java.util.*;

public class b160 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();

        int[] arr = new int[n];

        // Nhập các phần tử của mảng
        System.out.println("Nhap cac phan tu:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        List<List<Integer>> res = minimumAbsDifference(arr);

        // In kết quả
        System.out.println("Cac cap co hieu tuyet doi nho nhat:");
        for (List<Integer> pair : res) {
            System.out.println(pair.get(0) + " " + pair.get(1));
        }

        sc.close();
    }

    public static List<List<Integer>> minimumAbsDifference(int[] arr) {

        // 1. Tìm giá trị nhỏ nhất và lớn nhất trong mảng
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int num : arr) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }

        // 2. Tạo mảng đánh dấu (Counting Sort)
        // k là số lượng giá trị có thể xuất hiện
        int k = max - min + 1;
        boolean[] count = new boolean[k];

        // Đánh dấu các số xuất hiện trong arr
        for (int num : arr) {
            count[num - min] = true;
        }

        // 3. Sắp xếp lại arr bằng cách duyệt mảng count
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            // Tìm vị trí tiếp theo có giá trị true
            while (!count[j]) {
                j++;
            }
            // Gán lại giá trị đã được sắp xếp
            arr[i] = j + min;
            j++;
        }

        // 4. Tìm hiệu tuyệt đối nhỏ nhất giữa các phần tử kề nhau
        List<List<Integer>> result = new ArrayList<>();
        int dif = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length - 1; i++) {
            int curDif = arr[i + 1] - arr[i];

            // Nếu tìm được hiệu nhỏ hơn
            if (curDif < dif) {
                result.clear(); // xóa kết quả cũ
                dif = curDif; // cập nhật hiệu nhỏ nhất
            }

            // Nếu hiệu bằng hiệu nhỏ nhất thì thêm cặp số vào kết quả
            if (curDif == dif) {
                result.add(Arrays.asList(arr[i], arr[i + 1]));
            }
        }

        return result;
    }

}

// ---

// ## 🧠 Ý tưởng tổng quát

// Bài **1200. Minimum Absolute Difference** yêu cầu:

// > Tìm **các cặp số** có **hiệu tuyệt đối nhỏ nhất** sau khi sắp xếp mảng.

// Thông thường:

// * Cách phổ biến: `Arrays.sort(arr)` → `O(n log n)`
// * **Code của bạn**: dùng **Counting Sort / Boolean Sort** → gần `O(n +
// range)`

// ---

// ## 🔹 Phần 1: Tìm min và max

// ```java
// int max = Integer.MIN_VALUE;
// int min = Integer.MAX_VALUE;
// for (int num : arr) {
// max = Math.max(num, max);
// min = Math.min(num, min);
// }
// ```

// 📌 Mục đích:

// * Xác định **giá trị nhỏ nhất và lớn nhất** trong mảng
// * Dùng để biết **khoảng giá trị (range)**

// Ví dụ:

// ```text
// arr = [4, 2, 1, 3]
// min = 1, max = 4
// ```

// ---

// ## 🔹 Phần 2: Tạo mảng đánh dấu (Counting Sort)

// ```java
// int k = max - min + 1;
// boolean[] count = new boolean[k];
// ```

// 📌 `count[i] = true` nghĩa là:

// > Số `(i + min)` **tồn tại trong mảng**

// ---

// ### Đánh dấu các số xuất hiện

// ```java
// for (int num : arr) {
// count[num - min] = true;
// }
// ```

// Ví dụ:

// ```text
// arr = [4, 2, 1, 3]
// min = 1

// count = [true, true, true, true]
// ↑ ↑ ↑ ↑
// 1 2 3 4
// ```

// ---

// ## 🔹 Phần 3: Khôi phục mảng arr đã được sắp xếp

// ```java
// int j = 0;
// for (int i = 0; i < arr.length; i++) {
// while(!count[j]) j++;
// arr[i] = j++ + min;
// }
// ```

// 📌 Ý nghĩa:

// * Duyệt mảng `count` từ trái sang phải
// * Mỗi lần gặp `true` → gán lại vào `arr`
// * Kết quả: `arr` **được sắp xếp tăng dần**

// Ví dụ:

// ```text
// arr = [1, 2, 3, 4]

// ⏱ Thao tác này chạy trong `O(range)`

// ---

// ## 🔹 Phần 4: Tìm hiệu nhỏ nhất & các cặp thỏa mãn

// ```java
// List<List<Integer>> result = new ArrayList<>();
// int dif = Integer.MAX_VALUE;
// ```

// ### Duyệt các phần tử kề nhau

// ```java
// for (int i = 0; i < arr.length-1; i++) {
// int curDif = arr[i+1] - arr[i];
// ```

// 📌 Vì mảng đã sort → **hiệu nhỏ nhất chỉ có thể xuất hiện giữa 2 phần tử kề
// nhau**

// ---

// ### Nếu tìm thấy hiệu nhỏ hơn

// ```java
// if (curDif < dif) {
// result.clear();
// dif = curDif;
// }
// ```

// 📌 Khi gặp hiệu nhỏ hơn:

// * Xóa các cặp cũ
// * Cập nhật hiệu nhỏ nhất mới

// ---

// ### Nếu hiệu bằng hiệu nhỏ nhất

// ```java
// if (curDif == dif)
// result.add(Arrays.asList(arr[i], arr[i+1]));
// ```

// 📌 Thêm cặp số vào kết quả

// ---

// ## 🔹 Kết quả cuối cùng

// ```java
// return result;
// ```

// ---

// ## 📌 Độ phức tạp

// | Bước | Độ phức tạp |
// | -------------- | ----------- |
// | Tìm min/max | `O(n)` |
// | Đánh dấu | `O(n)` |
// | Khôi phục mảng | `O(range)` |
// | Duyệt tìm hiệu | `O(n)` |

// 👉 **Tổng:** `O(n + range)`
// 👉 **Nhanh hơn `O(n log n)` nếu range nhỏ**

// ---

// ## 🔥 Điểm hay của thuật toán này

// * Không dùng `Arrays.sort`
// * Dùng **Counting Sort tối ưu**
// * Rất phù hợp khi:

// * `arr.length` lớn
// * Giá trị trong mảng **không quá phân tán**

// ---

// ## ❗ Lưu ý

// * LeetCode cho phép giá trị `arr[i]` từ `-10^6 → 10^6` → `range` có thể lớn
// * Vì vậy **solution này tối ưu nhưng có điều kiện**

// ---

// ### ✅ Tóm tắt 1 câu để nhớ:

// > **Dùng counting sort để sắp xếp nhanh, sau đó so sánh các phần tử kề nhau
// để tìm hiệu tuyệt đối nhỏ nhất.**

// Nếu bạn muốn:

// * Mình **thêm comment trực tiếp vào code**
// * So sánh với **Arrays.sort version**
// * Giải thích tại sao **boolean[] đủ dùng**

// 👉 cứ nói tiếp nhé 👌

// # ---

// # ## 🔹 Ý tưởng thuật toán (nhắc lại ngắn gọn)

// # 1. **Sắp xếp mảng**
// # 2. Tính hiệu tuyệt đối giữa **các phần tử kề nhau**
// # 3. Tìm **hiệu nhỏ nhất**
// # 4. Lấy ra **các cặp phần tử có hiệu = min**

// # 👉 Vì sau khi sắp xếp, cặp có hiệu nhỏ nhất **chỉ có thể nằm cạnh nhau**

// # ---

// # ## 🔹 Code có chú thích chi tiết

// # ```python
// import numpy as np
// from typing import List

// class Solution:
// def minimumAbsDifference(self, arr: List[int]) -> List[List[int]]:

// # 1. Chuyển list arr sang numpy array và sắp xếp tăng dần
// # Ví dụ: arr = [3, 1, 4] -> a = [1, 3, 4]
// a = np.sort(np.array(arr))

// # 2. Tính hiệu giữa các phần tử kề nhau
// # diff_a[i] = a[i+1] - a[i]
// # Ví dụ: a = [1, 3, 4] -> diff_a = [2, 1]
// diff_a = np.diff(a)

// # 3. Tìm hiệu nhỏ nhất trong mảng diff_a
// min_abs = np.min(diff_a)

// # 4. Tìm tất cả chỉ số i sao cho diff_a[i] == min_abs
// # inds là mảng các chỉ số thỏa mãn điều kiện
// inds, = np.where(diff_a == min_abs)

// # 5. Với mỗi chỉ số i, cặp số tương ứng là [a[i], a[i+1]]
// res = []
// for i in inds:
// res.append([int(a[i]), int(a[i + 1])])

// # 6. Trả về danh sách các cặp có hiệu tuyệt đối nhỏ nhất
// return res
// # ```

// # ---

// # ## 🔹 Minh họa nhanh bằng ví dụ

// # ```text
// # arr = [4, 2, 1, 3]

// # Sau khi sort:
// # a = [1, 2, 3, 4]

// # diff_a = [1, 1, 1]
// # min_abs = 1

// # Các cặp thỏa mãn:
// # [1, 2], [2, 3], [3, 4]
// # ```

// # ---

// ## 🔹 Độ phức tạp

// # * Sắp xếp: `O(n log n)`
// # * diff, min, where: `O(n)`
// # * Tổng: **O(n log n)** ✅

// # # ---

// # ## 🔹 Lưu ý khi đi thi / phỏng vấn

// # * Ý tưởng **sort + so sánh phần tử kề nhau** là điểm mấu chốt
// # * Có thể **không dùng numpy** nếu bị giới hạn thư viện (LeetCode khuyến
// khích Python thuần)

// # Nếu bạn muốn, mình có thể:

// # * Viết lại **bản Python không dùng numpy**
// # * So sánh **2 cách làm (numpy vs thường)**
// # * Giải thích tại sao **không cần dùng abs()** sau khi sort

// # Cứ nói nhé 👌

// # Đề bài thường gặp trên LeetCode với nội dung như sau 👇

// # ---

// # ### 📌 Đề bài (tóm tắt)

// # Bạn được cho **một mảng số nguyên `nums`**.
// # Nhiệm vụ của bạn là:

// # 👉 **Tìm giá trị nhỏ nhất của hiệu tuyệt đối giữa hai phần tử bất kỳ trong
// mảng**.

// # ---

// # ### 📌 Hiệu tuyệt đối là gì?

// # Hiệu tuyệt đối giữa hai số `a` và `b` là:

// # [
// # |a - b|
// # ]

// # Ví dụ:

// # * (|3 - 5| = 2)
// # * (|7 - 2| = 5)

// # ---

// # ### 📌 Ví dụ minh họa

// # **Ví dụ 1:**

// # ```text
// # nums = [3, 1, 4]
// # ```

// # Xét mọi cặp:

// # * |3 − 1| = 2
// # * |3 − 4| = 1
// # * |1 − 4| = 3

// # 👉 **Kết quả = 1**

// # ---

// # **Ví dụ 2:**

// # ```text
// # nums = [1, 5, 3, 19, 18, 25]
// # ```

// # Sắp xếp mảng:

// # ```text
// # [1, 3, 5, 18, 19, 25]
// # ```

// # Hiệu các cặp liền kề:

// # * |1 − 3| = 2
// # * |3 − 5| = 2
// # * |5 − 18| = 13
// # * |18 − 19| = 1 ✅
// # * |19 − 25| = 6

// # 👉 **Kết quả = 1**

// # ---

// # ### 📌 Ý tưởng quan trọng của đề

// # ❗ Không cần so sánh **mọi cặp** (O(n²) – quá chậm)

// # 👉 **Cách đúng**:

// # 1. **Sắp xếp mảng**
// # 2. Chỉ cần so sánh **các phần tử đứng cạnh nhau**
// # 3. Lấy hiệu nhỏ nhất

// # 📌 Lý do:

// # * Sau khi sắp xếp, hai số **gần nhau nhất về giá trị** chắc chắn nằm **cạnh
// nhau**

// # ---

// # ### 📌 Độ phức tạp

// # * Sắp xếp: `O(n log n)`
// # * Duyệt mảng: `O(n)`
// # * Tổng: **O(n log n)** ✅

// # ---

// # ### 📌 Tóm gọn 1 câu

// # > **Minimum Absolute Difference** yêu cầu tìm khoảng cách nhỏ nhất giữa hai
// số bất kỳ trong mảng, và cách làm tối ưu là **sắp xếp mảng rồi so sánh các
// phần tử kề nhau**.

// # ---

// # Nếu bạn muốn, mình có thể:

// # * Giải thích **code Java / C++ / Python**
// # * So sánh với bài **Minimum Difference with k elements**
// # * Hoặc hướng dẫn cách **tự suy luận khi đi thi** 💡

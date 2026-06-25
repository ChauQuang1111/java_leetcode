// # Count Subarrays With Majority Element I(25/06/2026)



// # Đề bài

// # Cho một mảng số nguyên nums.

// # Hãy đếm số lượng mảng con liên tiếp (subarray) mà trong đó tồn tại một phần tử chiếm đa số (majority element).

// # Majority Element là gì?

// # Một phần tử được gọi là majority element nếu số lần xuất hiện của nó lớn hơn một nửa độ dài của mảng con.

// # Công thức:

// # Nếu mảng con có độ dài len thì phần tử x là majority khi:

// # # [

// # # count(x) > \frac{len}{2}

// # # ]

// # # Ví dụ 1

// # nums = [1,2,1]

// # Các mảng con:



// # [1]       -> 1 xuất hiện 1/1 => majority

// # [2]       -> 2 xuất hiện 1/1 => majority

// # [1]       -> 1 xuất hiện 1/1 => majority



// # [1,2]     -> 1 xuất hiện 1 lần, 2 xuất hiện 1 lần

// #              không ai > 2/2 = 1 => không có majority



// # [2,1]     -> tương tự => không có majority



// # [1,2,1]   -> 1 xuất hiện 2 lần

// #              2 > 3/2 = 1.5 => có majority

// # Kết quả:



// # 4

// # Ví dụ 2

// # # nums = [3,3,3]

// # # Các mảng con:



// # # [3]

// # # [3]

// # # [3]

// # # [3,3]

// # # [3,3]

// # # [3,3,3]

// # # Trong tất cả các mảng con trên, số 3 đều xuất hiện nhiều hơn một nửa độ dài.

// # # Kết quả:



// # # 6

// # # Ví dụ 3

// # # nums = [1,2,3]

// # # Các mảng con:



// # # [1] -> có majority

// # # [2] -> có majority

// # # [3] -> có majority



// # # [1,2] -> không

// # # [2,3] -> không

// # # [1,2,3] -> không

// # # Kết quả:



// # # 3

// # # Điều cần đếm

// # # Ta cần đếm số cặp (l, r) sao cho:



// # # subarray = nums[l...r]

// # # và tồn tại một giá trị x thỏa:



// # # số lần xuất hiện của x trong nums[l...r]

// # # >

// # # (r - l + 1)/2

// # # Ý tưởng brute force

// # # Với mỗi vị trí bắt đầu l:



// # # Mở rộng điểm kết thúc r

// # # Dùng HashMap đếm tần suất

// # # Kiểm tra tần suất lớn nhất có vượt quá len/2 không

// # for (int l = 0; l < n; l++) {

// #     Map<Integer,Integer> freq = new HashMap<>();



// #     for (int r = l; r < n; r++) {

// #         freq.put(nums[r], freq.getOrDefault(nums[r],0)+1);



// #         int len = r - l + 1;



// #         if (maxFreq > len/2)

// #             ans++;

// #     }

// # }

// # Độ phức tạp:



// # O(n²)

// # (với việc cập nhật maxFreq phù hợp).

// # Nếu bạn gửi nguyên văn đề LeetCode "Count Subarrays With Majority Element I" (vì có nhiều phiên bản I và II), mình có thể giải thích chi tiết hơn về input, output và ý tưởng tối ưu của bài đó.

// # Đây là lời giải cho bài Count Subarrays With Majority Element I trên LeetCode.



// # Ý tưởng chính

// # Ta cần đếm số mảng con mà target là majority element.

// # Điều kiện:



// # count(target) > length(subarray) / 2

// # Bước 1: Chuyển đổi mảng

// # Ta biến đổi mỗi phần tử:



// # target  -> +1

// # khác target -> -1

// # Ví dụ:



// # nums   = [1,3,2,3,3]

// # target = 3



// # => [-1,+1,-1,+1,+1]

// # Bước 2: Ý nghĩa prefix sum

// # Gọi:



// # prefix[i] = tổng từ đầu đến i

// # Khi đó với một mảng con [l..r]:



// # sum(l,r) = prefix[r] - prefix[l-1]

// # Ta có:



// # sum(l,r)

// # =

// # số target

// # -

// # số phần tử khác target

// # Nếu target là majority:



// # số target > số khác

// # ⇔



// # số target - số khác > 0

// # ⇔



// # sum(l,r) > 0

// # Như vậy bài toán trở thành:



// # Đếm số đoạn con có tổng dương.

// # Bước 3: Prefix sum

// # Ta duy trì:



// # pre = prefix sum hiện tại

// # Mỗi lần:



// # if num == target:

// #     pre += 1

// # else:

// #     pre -= 1

// # Bước 4: Điều kiện đếm

// # Ta cần số lượng chỉ số trước đó j sao cho:



// # prefix[j] < prefix[i]

// # vì



// # prefix[i] - prefix[j] > 0

// # => đoạn (j+1 .. i) có tổng dương.

// # Vai trò của cnt và acc

// # cnt[x]

// # cnt[x] = số lần prefix sum = x đã xuất hiện

// # acc[x]

// # acc[x]

// # =

// # cnt[0] + cnt[1] + ... + cnt[x]

// # Tức là tổng tích lũy.

// # Nhờ đó:



// # acc[pre-1]

// # chính là số prefix nhỏ hơn pre.

// # Đó là số đoạn con kết thúc tại vị trí hiện tại mà target là majority.

// # Code có chú thích

// from typing import List
// class Solution:

//     def countMajoritySubarrays(self, nums: List[int], target: int) -> int:



//         n = len(nums)



//         # prefix sum có thể nằm trong [-n, n]

//         # dịch sang phải để tránh index âm

//         OFFSET = n + 1



//         # cnt[x] = số lần prefix sum = x

//         cnt = [0] * (2 * n + 2)



//         # acc[x] = tổng cnt[0..x]

//         acc = [0] * (2 * n + 2)



//         # prefix sum ban đầu = 0

//         pre = OFFSET



//         # có 1 prefix sum = 0 trước khi duyệt

//         cnt[pre] = 1

//         acc[pre] = 1



//         res = 0



//         for num in nums:



//             # target được xem là +1

//             if num == target:

//                 pre += 1

//             else:

//                 pre -= 1



//             # lưu prefix hiện tại

//             cnt[pre] += 1



//             # cập nhật prefix-count tích lũy

//             acc[pre] = acc[pre - 1] + cnt[pre]



//             # acc[pre-1]

//             # = số prefix sum nhỏ hơn prefix hiện tại

//             # => số đoạn con kết thúc ở đây

//             # mà target là majority

//             res += acc[pre - 1]



//         return res

// # Ví dụ chạy tay

// # nums = [3,1,3]

// # target = 3

// # Chuyển thành:



// # [+1,-1,+1]

// # Prefix:



// # 0

// # 1

// # 0

// # 1

// # Ta đếm số cặp:



// # prefix[j] < prefix[i]

// # Các cặp hợp lệ:



// # 0 -> 1

// # 0 -> 1

// # 0 -> 1

// # Tương ứng các đoạn:



// # [3]

// # [3,1,3]

// # [3]

// # Có 3 đoạn mà 3 là majority.

// # Tuy nhiên, đoạn code bạn đưa thực ra là một cách cài đặt khá "lạ". Nếu xem kỹ, acc chỉ được cập nhật tại vị trí pre, nên để chạy đúng cần duy trì cấu trúc prefix-frequency đầy đủ (thường dùng Fenwick Tree/BIT hoặc Segment Tree). Phiên bản chuẩn của lời giải LeetCode thường dùng Fenwick Tree để đếm số prefix sum nhỏ hơn prefix hiện tại trong O(n log n).
// Bài 3737. Count Subarrays With Majority Element I yêu cầu:



// Đếm số mảng con mà target là phần tử xuất hiện nhiều hơn tất cả các phần tử khác cộng lại.

// Nói cách khác:



// count(target) > len(subarray) / 2

// 1. Biến đổi bài toán

// Ta chuyển mỗi phần tử thành:



// target      -> +1

// khác target -> -1

// Ví dụ:



// nums   = [1,3,2,3,3]

// target = 3



// => [-1,+1,-1,+1,+1]

// Tại sao làm vậy?

// Giả sử trong một đoạn:



// target xuất hiện x lần

// khác target xuất hiện y lần

// Độ dài đoạn:



// x + y

// target là majority khi:



// x > (x+y)/2

// Nhân 2:



// 2x > x+y

// Suy ra:



// x > y

// Mà sau khi biến đổi:



// sum = x - y

// nên:



// target là majority

// ⇔ sum > 0

// 2. Prefix Sum

// Gọi:



// pre[i]

// =

// tổng từ đầu đến vị trí i

// Khi đó:



// sum(l,r)

// =

// pre[r] - pre[l-1]

// Để đoạn [l,r] hợp lệ:



// pre[r] - pre[l-1] > 0

// ⇔



// pre[l-1] < pre[r]

// Bài toán mới

// Đếm số cặp:



// i < j

// sao cho:



// prefix[i] < prefix[j]

// 3. Ý nghĩa biến pos

// int pos = n + 1;

// Ban đầu prefix sum = 0.

// Vì prefix có thể nằm trong:



// [-n, n]

// nên dịch sang phải:



// real index = prefix + n + 1

// để tránh index âm.

// Ví dụ:



// prefix = -2

// index = n+1-2

// 4. Ý nghĩa mảng freq

// freq[x]

// lưu:



// có bao nhiêu prefix sum bằng x

// Ví dụ:



// prefix:

// 0,1,0,1



// freq[0] = 2

// freq[1] = 2

// 5. Ý nghĩa mảng cum

// cum[x]

// lưu:



// freq[0] + freq[1] + ... + freq[x]

// tức là tổng tích lũy.

// Ví dụ:



// freq



// index: 0 1 2 3

// value: 1 3 2 1



// cum



// index: 0 1 2 3

// value: 1 4 6 7

// Tại sao dùng cum?

// Ta cần biết:



// bao nhiêu prefix nhỏ hơn prefix hiện tại

// Nếu prefix hiện tại là:



// pos

// thì:



// cum[pos-1]

// chính là:



// freq[0]

// +

// freq[1]

// +

// ...

// +

// freq[pos-1]

// Tức là số prefix nhỏ hơn prefix hiện tại.

// Đó chính là số đoạn con kết thúc tại vị trí hiện tại có tổng dương.

// 6. Diễn giải từng dòng

// freq[pos] = 1;

// Thêm prefix ban đầu:



// prefix = 0

// cum[pos] = 1;

// Có 1 prefix bằng 0.

// Duyệt từng phần tử

// for(int num: nums)

// Cập nhật prefix

// int offset = (num == target) ? 1 : -1;

// pos += offset;

// Nếu gặp target:



// +1

// Ngược lại:



// -1

// Lưu prefix mới

// freq[pos]++;

// Cập nhật cumulative

// cum[pos] = cum[pos-1] + freq[pos];

// Nghĩa là:



// cum[pos]

// =

// tổng số prefix ≤ pos

// Đếm đáp án

// count += cum[pos-1];

// cum[pos-1]

// =



// số prefix nhỏ hơn prefix hiện tại

// Mỗi prefix nhỏ hơn tạo ra một đoạn:



// pre[r] - pre[l-1] > 0

// ⇒ target là majority.

// Ví dụ

// nums   = [3,1,3]

// target = 3

// Sau biến đổi:



// [+1,-1,+1]

// Prefix:



// 0

// 1

// 0

// 1

// Ta cần đếm:



// prefix[i] < prefix[j]

// Các cặp:



// 0 -> 1

// 0 -> 1

// 0 -> 1

// Tổng:



// 3

// Các đoạn tương ứng:



// [3]

// [3]

// [3,1,3]

// Độ phức tạp

// Thời gian:

// O(n)

// Bộ nhớ:

// O(n)

// Bản chất thuật toán

// Đổi target → +1, còn lại → -1.

// Majority ⇔ tổng đoạn > 0.

// Dùng prefix sum.

// Đếm số cặp:

// prefix trước < prefix hiện tại

// Mỗi cặp tương ứng một mảng con mà target là majority.


import java.util.*;

public class b258 {
  
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
      // Nhập số phần tử
        int n = sc.nextInt();

        int[] nums = new int[n];

        // Nhập mảng
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        // Nhập giá trị target
        int target = sc.nextInt();

        int result = countMajoritySubarrays(nums, target);

        System.out.println(result);

        sc.close();
    }
// Hàm đếm số mảng con mà target là majority element
    public static int countMajoritySubarrays(int[] nums, int target) {

        int n = nums.length;

        // Prefix sum có thể nằm trong khoảng [-n, n]
        // Dịch sang phải n+1 đơn vị để tránh chỉ số âm
        int pos = n + 1;

        // Kích thước đủ để lưu tất cả giá trị prefix sum sau khi dịch
        int len = 2 * n + 2;

        // freq[i] = số lần prefix sum i xuất hiện
        int[] freq = new int[len];

        // cum[i] = tổng freq[0..i]
        int[] cum = new int[len];

        // Prefix sum ban đầu = 0
        freq[pos] = 1;
        cum[pos] = 1;

        int count = 0;

        for (int num : nums) {

            // target được xem là +1
            // phần tử khác target được xem là -1
            int offset = (num == target) ? 1 : -1;

            // cập nhật prefix sum
            pos += offset;

            // ghi nhận prefix sum hiện tại
            freq[pos]++;

            // cập nhật cumulative frequency
            cum[pos] = cum[pos - 1] + freq[pos];

            // cum[pos-1]
            // = số prefix sum nhỏ hơn prefix hiện tại
            // => số đoạn con kết thúc tại đây có tổng > 0
            // => target là majority
            count += cum[pos - 1];
        }

        return count;
    }

    }



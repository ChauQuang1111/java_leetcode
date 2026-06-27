// # Find the Maximum Number of Elements in Subset.(27/06/2026)
// # Nếu bạn đang nói đến phiên bản phổ biến thì ý tưởng của đề như sau:

// # Mô tả

// # Cho một mảng gồm N số nguyên.

// # Hãy tìm một tập con (subset) của mảng sao cho thỏa mãn điều kiện nào đó (điều kiện sẽ được nêu trong đề), và số lượng phần tử trong tập con là lớn nhất.

// # Lưu ý:



// # Subset là tập con của các phần tử trong mảng.

// # Không cần các phần tử phải đứng liền nhau.

// # Có thể chọn hoặc không chọn mỗi phần tử.

// # Ví dụ:



// # A = [1, 2, 3]

// # Các subset có thể là:



// # {}

// # {1}

// # {2}

// # {3}

// # {1,2}

// # {1,3}

// # {2,3}

// # {1,2,3}

// # "Maximum Number of Elements"

// # Cụm này có nghĩa là:



// # Hãy chọn được nhiều phần tử nhất có thể nhưng vẫn thỏa mãn điều kiện của đề.

// # Ví dụ nếu điều kiện là:



// # Hiệu giữa phần tử lớn nhất và nhỏ nhất không vượt quá 1.

// # Cho:



// # A = [1,2,2,3,1,2]

// # Các tập con hợp lệ:



// # {1,1,2,2,2}  (5 phần tử)

// # {2,2,2,3}    (4 phần tử)

// # Đáp án sẽ là



// # 5

// # vì 5 là số lượng phần tử lớn nhất.

// # Nếu đây là bài trên HackerRank

// # Đề đầy đủ thường là:



// # Given an array of integers, find the size of the largest subset where the absolute difference between any two elements is less than or equal to 1.

// # Nghĩa là:



// # Chọn một tập con.

// # Với mọi cặp phần tử trong tập con:

// # |a - b| ≤ 1

// # In ra kích thước lớn nhất của tập con đó.

// # Ví dụ



// # 6

// # 4 6 5 3 3 1

// # Ta có tần suất:



// # 1 : 1

// # 3 : 2

// # 4 : 1

// # 5 : 1

// # 6 : 1

// # Xét từng cặp số liên tiếp:



// # 1 và 2 → 1

// # 2 và 3 → 2

// # 3 và 4 → 3

// # 4 và 5 → 2

// # 5 và 6 → 2

// # Lớn nhất là



// # 3

// # tương ứng với tập



// # {3,3,4}

// # Đoạn code này là lời giải cho bài Find the Maximum Number of Elements in Subset trên LeetCode. Ý tưởng của thuật toán khá khó hiểu nếu chỉ nhìn code, nên mình sẽ giải thích từng bước.

// # Ý tưởng của bài toán

// # Ta cần tạo một tập con có dạng:



// # x, x,

// # x², x²,

// # x⁴, x⁴,

// # x⁸, x⁸,

// # ...

// # cuối cùng chỉ còn 1 phần tử

// # Nghĩa là:



// # Mỗi số (trừ số cuối) phải xuất hiện ít nhất 2 lần.

// # Vì khi chuyển sang số bình phương tiếp theo thì ta "tiêu thụ" 2 phần tử.

// # Riêng số cuối cùng chỉ cần 1 lần.

// # Ví dụ



// # 2 2 4 4 16

// # Ta có



// # 2² = 4

// # 4² = 16

// # Độ dài là



// # 5

// # Bước 1

// # count = Counter(nums)

// # Đếm số lần xuất hiện của mỗi số.

// # Ví dụ



// # nums = [2,2,4,4,16]

// # thì



// # count =

// # {

// # 2:2,

// # 4:2,

// # 16:1

// # }

// # Bước 2

// # for key in count.keys():

// # Thử mỗi số làm điểm bắt đầu.

// # Ví dụ



// # bắt đầu từ 2

// # hoặc



// # bắt đầu từ 4

// # hoặc



// # bắt đầu từ 16

// # Sau đó lấy kết quả lớn nhất.

// # Trường hợp đặc biệt của số 1

// # if key == 1:

// # Vì



// # 1² = 1

// # nên sẽ bị lặp vô hạn.

// # Do đó phải xử lý riêng.

// # Ví dụ



// # 1 1 1 1 1

// # Ta dùng được



// # 1 1

// # 1 1

// # 1

// # Độ dài



// # 5

// # Nếu có



// # 1 1 1 1

// # thì chỉ dùng được



// # 1 1

// # 1

// # Độ dài



// # 3

// # Cho nên



// # total = count[1] if count[1] % 2 else count[1]-1

// # Nghĩa là

// # Nếu số lượng là lẻ



// # 5

// # thì lấy luôn



// # 5

// # Nếu là chẵn



// # 4

// # thì phải bỏ đi một số



// # 3

// # để còn đúng dạng



// # 2 + 2 + 1

// # Các số khác

// # total = 0

// # Đếm chiều dài dãy.

// # Vòng while

// # while count[key] >= 2 and key*key in count:

// # Ý nghĩa

// # Muốn đi tiếp sang tầng tiếp theo thì cần



// # Điều kiện 1

// # count[key] >=2

// # Tức là số hiện tại phải có ít nhất 2 bản.

// # Ví dụ



// # 2 2

// # mới đủ.

// # Điều kiện 2

// # key*key in count

// # Ví dụ



// # 2²=4

// # thì phải tồn tại số 4.

// # Nếu không có 4 thì không đi tiếp được.

// # Khi đủ điều kiện

// # total +=2

// # Ta dùng



// # 2

// # 2

// # nên cộng thêm 2 phần tử.

// # Sau đó



// # key = key*key

// # Chuyển sang xét



// # 4

// # rồi



// # 16

// # rồi



// # 256

// # ...

// # Sau khi while kết thúc

// # total+=1

// # Vì tầng cuối chỉ cần



// # 1

// # phần tử.

// # Ví dụ



// # 2 2

// # 4 4

// # 16

// # Ta có



// # 2+2+1

// # =



// # 5

// # Cập nhật đáp án

// # res=max(res,total)

// # Lấy kết quả lớn nhất trong tất cả các điểm bắt đầu.

// # Ví dụ chạy

// # nums



// # 2 2 4 4 16

// # Counter



// # 2 : 2

// # 4 : 2

// # 16 : 1

// # Bắt đầu từ



// # key=2

// # Lần 1



// # count[2]=2



// # 4 có tồn tại



// # total=2

// # key=4

// # Lần 2



// # count[4]=2



// # 16 có



// # total=4



// # key=16

// # Lần 3



// # count[16]=1



// # không đủ 2



// # dừng

// # Sau đó



// # total+=1

// # =



// # 5

// # Code có chú thích
// from typing import List
// from collections import Counter
// class Solution:

//     def maximumLength(self, nums: List[int]) -> int:

//         # Đếm số lần xuất hiện của từng số

//         count = Counter(nums)



//         # Lưu kết quả lớn nhất

//         res = 0



//         # Thử mỗi giá trị làm điểm bắt đầu của chuỗi

//         for key in count.keys():



//             # Trường hợp đặc biệt: 1^2 = 1 nên phải xử lý riêng

//             if key == 1:

//                 # Nếu số lượng 1 là lẻ thì dùng hết

//                 # Nếu chẵn thì bỏ 1 phần tử để kết thúc bằng đúng 1 số

//                 total = count[key] if count[key] % 2 else count[key] - 1



//             else:

//                 # Độ dài chuỗi hiện tại

//                 total = 0



//                 # Muốn đi sang tầng tiếp theo cần:

//                 # 1. key xuất hiện ít nhất 2 lần

//                 # 2. key^2 tồn tại trong mảng

//                 while count[key] >= 2 and key * key in count:



//                     # Dùng 2 phần tử của key

//                     total += 2



//                     # Sang tầng tiếp theo (bình phương)

//                     key = key * key



//                 # Tầng cuối chỉ cần 1 phần tử

//                 total += 1



//             # Cập nhật đáp án lớn nhất

//             res = max(res, total)



//         return res

// # Độ phức tạp

// # Time: O(n + m log log V) (thường được xem gần như O(n)), trong đó m là số lượng giá trị khác nhau và V là giá trị lớn nhất. Mỗi lần lặp while, key được bình phương (x → x² → x⁴ → ...), nên số bước tăng rất chậm.

// # Space: O(m) để lưu bảng đếm tần suất (Counter).

// # Mấu chốt của thuật toán là không duyệt mọi tập con, mà chỉ thử mỗi giá trị khác nhau làm điểm bắt đầu và lần theo chuỗi x → x² → x⁴ → ..., miễn là mỗi tầng (trừ tầng cuối) có ít nhất 2 phần tử để ghép.


// Đây là một cách viết khác của cùng bài toán, nhưng ý tưởng rõ ràng hơn so với code Python. Mình sẽ giải thích thuật toán theo đúng luồng của code.

// Ý tưởng của thuật toán

// Ta cần tìm chuỗi có dạng



// x

// x

// x²

// x²

// x⁴

// x⁴

// ...

// y

// Trong đó



// Mỗi tầng (trừ tầng cuối) cần 2 phần tử.

// Tầng cuối chỉ cần 1 phần tử.

// Ví dụ



// 2 2

// 4 4

// 16

// Độ dài là



// 5

// Bước 1: Đếm số lần xuất hiện

// Map<Integer, Integer> map = new HashMap<>();



// for (int num : nums) {

//     map.merge(num, 1, Integer::sum);

// }

// Ví dụ



// nums = [2,2,4,4,16]

// Sau vòng lặp



// 2 -> 2

// 4 -> 2

// 16 -> 1

// Ta chỉ cần biết mỗi số xuất hiện bao nhiêu lần.

// Bước 2: Xử lý số 1

// int ans = map.getOrDefault(1, 1);

// Lấy số lượng số 1.

// Ví dụ



// 1 1 1 1 1

// thì



// ans = 5

// Nếu không có số 1



// ans = 1

// Vì ít nhất ta luôn chọn được một phần tử bất kỳ.

// Sau đó



// if (ans % 2 == 0) {

//     ans--;

// }

// Tại sao?

// Vì



// 1² = 1

// Nếu có



// 4 số 1

// không thể dùng cả 4.

// Chỉ dùng được



// 1 1

// 1

// độ dài



// 3

// Nên phải giảm xuống số lẻ.

// Ví dụ



// 6 số 1

// ↓



// 5

// Bước 3: Thử mỗi số làm điểm bắt đầu

// for (Map.Entry<Integer, Integer> entry : map.entrySet())

// Ví dụ map là



// 2

// 4

// 16

// 25

// 625

// thì sẽ thử



// bắt đầu từ 2



// bắt đầu từ 4



// bắt đầu từ 16



// ...

// Bỏ qua số 1



// if (entry.getKey() == 1) {

//     continue;

// }

// vì xử lý rồi.

// Chỉ xét khi có ít nhất 2 phần tử

// if (entry.getValue() >= 2)

// Ví dụ



// 2 xuất hiện 1 lần

// thì không thể làm tầng đầu.

// Bỏ qua luôn.

// Biến c

// int c = 1;

// Đây là biến quan trọng.

// Nó không phải độ dài.

// Mà là



// số tầng của chuỗi.

// Ví dụ



// 2

// 2



// 4

// 4



// 16

// Có



// Tầng 1 : 2



// Tầng 2 : 4



// Tầng 3 : 16

// nên



// c = 3

// Khởi tạo a

// long a = entry.getKey();

// Ví dụ



// entry = 2

// ↓



// a = 2

// Sang tầng tiếp theo

// a *= a;

// ↓



// a = 4

// Bây giờ sẽ kiểm tra xem



// 4

// có đủ điều kiện không.

// Vòng while

// while (a < Integer.MAX_VALUE &&

//        map.getOrDefault((int)a,0) >=2)

// Điều kiện gồm hai phần.

// Điều kiện 1

// a < Integer.MAX_VALUE

// Vì



// 46341²

// đã vượt quá int.

// Nên phải dùng long.

// Điều kiện 2

// map[a] >=2

// Ví dụ



// 4 xuất hiện 2 lần

// thì đi tiếp được.

// Ví dụ



// 2 2



// 4 4



// 16

// Lần 1

// a



// 4

// Có



// 2 số 4

// ↓



// c++

// ↓



// c=2

// Sau đó



// a*=a;

// ↓



// 16

// Lần 2

// 16 chỉ có



// 1 lần

// ↓

// Không vào while nữa.

// Sau while

// Kiểm tra



// if (a < Integer.MAX_VALUE &&

//     map.getOrDefault((int)a,0)==1)

// Nếu tầng cuối chỉ có đúng



// 1 phần tử

// thì vẫn dùng được.

// Ví dụ



// 16

// ↓



// c++;

// ↓



// c=3

// Tại sao đáp án là

// 2*c-1

// Đây là công thức quan trọng nhất.

// Giả sử



// c=3 tầng

// Ta có



// Tầng1



// 2 phần tử



// Tầng2



// 2 phần tử



// Tầng3



// 1 phần tử

// Độ dài



// 2+2+1

// =



// 5

// Mà



// 2*c-1



// =



// 2*3-1



// =



// 5

// Ví dụ khác

// Có



// 2



// 2



// 4

// Có



// 2 tầng

// ↓



// 2+1=3

// Công thức



// 2*c-1



// =



// 2*2-1



// =



// 3

// Đúng.

// Nếu chỉ có



// 2

// ↓



// 1 tầng

// ↓



// 1

// Công thức



// 2*1-1=1

// Cập nhật đáp án

// ans=Math.max(ans,2*c-1);

// Lấy chuỗi dài nhất.

// Ví dụ chạy

// nums



// 2 2 4 4 16

// Map



// 2->2



// 4->2



// 16->1

// Bắt đầu từ



// 2

// c=1



// a=4

// while



// 4 có 2 lần



// c=2



// a=16

// while dừng



// 16 chỉ có 1 lần

// if



// c=3

// Đáp án



// 2*c-1



// =



// 5

// Tóm tắt thuật toán

// Dùng HashMap đếm tần suất của mỗi số.

// Xử lý riêng số 1 vì 1² = 1, nên chỉ lấy số lượng lẻ lớn nhất.

// Với mỗi số khác 1 có ít nhất 2 lần xuất hiện:

// Xem nó là tầng đầu tiên của chuỗi.

// Liên tục bình phương (x → x² → x⁴ → ...).

// Mỗi số trung gian phải xuất hiện ít nhất 2 lần để tạo thành một tầng mới.

// Nếu số cuối cùng xuất hiện đúng 1 lần, dùng nó làm tầng kết thúc.

// Tính độ dài chuỗi bằng công thức 2 * số_tầng - 1 và cập nhật kết quả lớn nhất.

// Độ phức tạp:



// Time: O(n + m · log log V), trong đó n là số phần tử, m là số giá trị khác nhau và V là giá trị lớn nhất. Do mỗi lần bình phương làm giá trị tăng rất nhanh nên số vòng lặp while rất ít.

// Space: O(m) để lưu bảng đếm tần suất.



import java.util.*;

/*
 * Tìm độ dài lớn nhất của tập con có dạng:
 *
 * x, x,
 * x², x²,
 * x⁴, x⁴,
 * ...
 * số cuối chỉ cần xuất hiện 1 lần.
 */

public class b260{
  
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
       // Nhập số lượng phần tử
        int n = sc.nextInt();

        // Nhập mảng
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

       

        int result = maximumLength(nums);

        // In kết quả
        System.out.println(result);

        sc.close();
    
    }
 public static int maximumLength(int[] nums) {

        // HashMap lưu số lần xuất hiện của từng số
        Map<Integer, Integer> map = new HashMap<>();

        // Đếm tần suất
        for (int num : nums) {
            map.merge(num, 1, Integer::sum);
        }

        /*
         * Xử lý riêng số 1.
         *
         * Vì:
         *      1² = 1
         *
         * Nếu có số lượng chẵn:
         *      1 1 1 1
         * chỉ dùng được:
         *      1 1 1
         *
         * nên đáp án phải là số lẻ lớn nhất.
         *
         * Nếu không có số 1 thì mặc định đáp án ban đầu là 1
         * (luôn có thể chọn một phần tử bất kỳ).
         */
        int ans = map.getOrDefault(1, 1);

        if (ans % 2 == 0) {
            ans--;
        }

        // Thử từng số làm điểm bắt đầu của chuỗi
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {

            // Bỏ qua số 1 vì đã xử lý
            if (entry.getKey() == 1) {
                continue;
            }

            /*
             * Muốn bắt đầu chuỗi thì số đầu tiên
             * phải xuất hiện ít nhất 2 lần.
             */
            if (entry.getValue() >= 2) {

                // c = số tầng của chuỗi
                int c = 1;

                // a dùng để lần lượt xét:
                // x², x⁴, x⁸,...
                long a = entry.getKey();

                // Sang tầng tiếp theo
                a *= a;

                /*
                 * Tiếp tục đi xuống chuỗi nếu:
                 *
                 * 1. Chưa vượt quá giới hạn int.
                 * 2. Giá trị hiện tại xuất hiện ít nhất 2 lần.
                 */
                while (a < Integer.MAX_VALUE &&
                        map.getOrDefault((int) a, 0) >= 2) {

                    // Có thêm một tầng
                    c++;

                    // Sang bình phương tiếp theo
                    a *= a;
                }

                /*
                 * Nếu tầng cuối chỉ có đúng 1 phần tử
                 * thì vẫn dùng được làm điểm kết thúc.
                 */
                if (a < Integer.MAX_VALUE &&
                        map.getOrDefault((int) a, 0) == 1) {

                    c++;
                }

                /*
                 * Nếu có c tầng thì:
                 *
                 * tầng 1 : 2 phần tử
                 * tầng 2 : 2 phần tử
                 * ...
                 * tầng cuối : 1 phần tử
                 *
                 * Độ dài = 2*c - 1
                 */
                ans = Math.max(ans, 2 * c - 1);
            }
        }

        return ans;
}
}

//  Count Subarrays With Majority Element II (26/06/2026)

// Đề bài (diễn giải)

// Cho một mảng số nguyên nums và một số nguyên k.

// Hãy đếm số lượng subarray mà trong subarray đó, k là majority element.

// Một phần tử được gọi là majority element nếu:



// Số lần xuất hiện của nó lớn hơn một nửa độ dài của subarray.

// Nói cách khác:

// Nếu subarray có độ dài là L và k xuất hiện cnt lần thì phải thỏa

// [

// cnt > \frac{L}{2}

// ]

// Ví dụ 1

// nums = [1,2,2,2]

// k = 2

// Các subarray:

// SubarraySố lần xuất hiện của 2Độ dàiMajority?[1]01❌[2]11✅[2]11✅[2]11✅[1,2]12❌ (1 không >1)[2,2]22✅[2,2]22✅[1,2,2]23✅ (2>1.5)[2,2,2]33✅[1,2,2,2]34✅ (3>2)

// Đáp án là



// 8

// Ví dụ 2

// nums = [1,2,3]

// k = 2

// Các subarray:



// [1]

// [2]      ✅

// [3]

// [1,2]

// [2,3]

// [1,2,3]

// Chỉ có



// [2]

// là có 2 chiếm hơn một nửa.

// Đáp án:



// 1

// Majority nghĩa là gì?

// Ví dụ



// [2,2,1]

// Độ dài = 3

// Số 2 xuất hiện 2 lần.

// Ta có



// 2 > 3/2 = 1.5

// ⇒ 2 là majority.

// Ví dụ



// [2,1]

// Độ dài = 2

// Số 2 xuất hiện 1 lần.



// 1 > 2/2 = 1 ?

// Không.

// Vì phải lớn hơn, không phải lớn hơn hoặc bằng.

// Nên không phải majority.

// Ví dụ



// [2,2,1,3]

// Độ dài = 4

// Số 2 xuất hiện 2 lần.



// 2 > 4/2 = 2 ?

// Sai.

// Không phải majority.

// Ví dụ



// [2,2,2,1]

// Độ dài = 4

// Số 2 xuất hiện 3 lần.



// 3 > 2

// Đúng.

// Là majority.

// Điều kiện có thể biến đổi

// Giả sử



// cnt = số lần k xuất hiện.

// len = độ dài subarray.

// Điều kiện:

// [

// cnt>\frac{len}{2}

// ]

// Nhân cả hai vế với 2:

// [

// 2 \times cnt > len

// ]

// Đây là dạng điều kiện thường được sử dụng trong lời giải tối ưu.

// Ý tưởng quy đổi

// Trong nhiều lời giải, người ta biến đổi mảng như sau:



// Nếu nums[i] == k thì gán giá trị +1.

// Nếu nums[i] != k thì gán giá trị -1.

// Ví dụ:



// nums = [2,1,2,3]

// k = 2

// Sau khi đổi:



// +1 -1 +1 -1

// Tổng của một subarray sẽ là:



// (+1 × số lần xuất hiện của k)

// +

// (-1 × số phần tử khác k)

// Nếu tổng dương, tức là:



// cnt(k) > cnt(khác)

// mà



// cnt(khác) = len - cnt(k)

// suy ra:



// cnt(k) > len - cnt(k)



// ⇔ 2 × cnt(k) > len

// Đây chính là điều kiện để k là majority.

// Tóm tắt

// Bài toán yêu cầu đếm số đoạn con sao cho:



// Chọn một subarray bất kỳ.

// Đếm số lần k xuất hiện trong subarray.

// Nếu số lần xuất hiện của k lớn hơn một nửa độ dài của subarray thì subarray đó được tính vào đáp án.

// Kết quả là tổng số subarray thỏa mãn điều kiện.

// Điểm mấu chốt của các lời giải tối ưu là quy đổi mảng thành +1/-1, khi đó việc kiểm tra "k là majority" trở thành kiểm tra xem tổng của subarray có dương hay không, giúp giải bằng kỹ thuật prefix sum kết hợp với cấu trúc dữ liệu phù hợp.


// Đoạn code này là một lời giải O(n) rất hay. Ý tưởng chính là chuyển bài toán "target là majority" thành bài toán về prefix sum.

// Bước 1. Chuyển mảng

// Ta coi



// target → +1

// phần tử khác target → -1

// Ví dụ



// nums = [2,1,2,2]

// target = 2

// đổi thành



// +1 -1 +1 +1

// Nếu một subarray có tổng > 0 thì



// (+1 xuất hiện) > (-1 xuất hiện)

// tức là



// count(target)

// >

// count(other)

// Mà



// count(other)=length-count(target)

// nên



// 2*count(target)>length

// đúng bằng điều kiện majority.

// Vậy bài toán trở thành



// Đếm số subarray có tổng dương.

// Bước 2. Prefix Sum

// Giả sử



// prefix[i]

// là tổng từ đầu tới i.

// Tổng đoạn



// l..r

// là



// prefix[r]-prefix[l-1]

// Muốn tổng >0



// prefix[r]>prefix[l-1]

// Như vậy khi đang ở vị trí i, ta cần biết có bao nhiêu prefix nhỏ hơn prefix hiện tại.

// Bước 3. Biến pref

// Trong code



// int pref = n;

// không phải prefix thật.

// Nó chỉ là prefix sum đã được cộng thêm n.

// Lý do:

// Prefix có thể âm.

// Ví dụ



// -5

// không thể làm chỉ số mảng.

// Nên cộng thêm n.

// Ví dụ



// prefix = -2



// => lưu thành



// pref = n-2

// Bước 4. freq[]

// freq[x]

// lưu



// Có bao nhiêu prefix có giá trị bằng x.

// Ban đầu



// freq[n]=1;

// vì



// prefix=0

// đã xuất hiện một lần.

// Bước 5. less

// Đây là biến khó hiểu nhất.



// less

// không phải số prefix nhỏ hơn.

// Mà là



// số lượng prefix nhỏ hơn prefix hiện tại.

// Nó luôn được cập nhật khi prefix thay đổi ±1.

// Đó là lý do thuật toán chạy O(n).

// Khi gặp target

// pref++;

// Prefix tăng 1.

// Những prefix bằng giá trị cũ



// pref cũ

// bây giờ đều trở thành nhỏ hơn prefix mới.

// Nên



// less += freq[pref];

// Ví dụ



// prefix cũ =5



// có 4 prefix bằng 5

// Sau khi tăng lên



// prefix mới =6

// 4 prefix đó đều nhỏ hơn.

// Nên cộng thêm 4.

// Khi gặp phần tử khác

// Prefix giảm.



// pref--;

// Một số prefix không còn nhỏ hơn nữa.

// Chính là



// freq[pref]

// nên



// less -= freq[pref];

// Bước 6. Cập nhật

// freq[pref]++;

// đánh dấu prefix hiện tại đã xuất hiện.

// Bước 7. Cộng đáp án

// ans += less;

// Vì



// less

// chính là số prefix nhỏ hơn prefix hiện tại.

// Mỗi prefix như vậy sinh ra một subarray có tổng dương.

// Ví dụ

// nums



// 2 1 2



// target=2

// Đổi thành



// +1 -1 +1

// Ban đầu



// pref=n=3



// freq[3]=1



// less=0

// i=0

// +1

// less += freq[3]=1



// less=1



// pref=4

// freq[4]++



// ans+=1

// Có subarray



// [2]

// i=1

// -1

// pref=3



// less-=freq[3]



// less=0

// freq[3]++



// ans+=0

// i=2

// +1

// less+=freq[3]



// freq[3]=2



// less=2

// pref=4



// freq[4]++



// ans+=2

// Sinh ra



// [2]



// [2,1,2]

// Tổng



// ans=3

// Code có chú thích và hàm main

// import java.util.Scanner;



// public class Solution {



//     // Hàm đếm số subarray mà target là majority element

//     public static long countMajoritySubarrays(int[] nums, int target) {



//         int n = nums.length;



//         // pref là prefix sum đã được dịch sang phải n đơn vị

//         // Giá trị thực của prefix = pref - n

//         int pref = n;



//         // freq[i] = số lần prefix có giá trị i xuất hiện

//         int[] freq = new int[2 * n + 1];



//         // Prefix = 0 xuất hiện trước khi duyệt mảng

//         freq[n] = 1;



//         // less = số prefix nhỏ hơn prefix hiện tại

//         long less = 0;



//         // Kết quả cuối cùng

//         long ans = 0;



//         // Duyệt từng phần tử

//         for (int num : nums) {



//             // target được xem là +1

//             if (num == target) {



//                 // Các prefix bằng giá trị cũ giờ trở thành nhỏ hơn

//                 less += freq[pref];



//                 // Prefix tăng 1

//                 pref++;



//             } else {



//                 // Prefix giảm 1

//                 pref--;



//                 // Những prefix bằng prefix mới không còn nhỏ hơn

//                 less -= freq[pref];

//             }



//             // Lưu prefix hiện tại

//             freq[pref]++;



//             // Mỗi prefix nhỏ hơn tạo ra một subarray hợp lệ

//             ans += less;

//         }



//         return ans;

//     }



//     public static void main(String[] args) {



//         Scanner sc = new Scanner(System.in);



//         // Nhập số phần tử

//         int n = sc.nextInt();



//         int[] nums = new int[n];



//         // Nhập mảng

//         for (int i = 0; i < n; i++) {

//             nums[i] = sc.nextInt();

//         }



//         // Nhập target

//         int target = sc.nextInt();



//         // In kết quả

//         System.out.println(countMajoritySubarrays(nums, target));



//         sc.close();

//     }

// }

// Ví dụ nhập

// 4

// 2 1 2 2

// 2

// Kết quả

// 6

// Các subarray hợp lệ là:



// [2]

// [2]

// [2]

// [2,1,2]

// [2,2]

// [2,1,2,2]

// Độ phức tạp của thuật toán là O(n) về thời gian và O(n) về bộ nhớ, vì mỗi phần tử chỉ được xử lý một lần và mảng freq có kích thước tỷ lệ với n.




import java.util.*;

public class b259{
  
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
 // Nhập số phần tử
        int n = sc.nextInt();

        int[] nums = new int[n];

        // Nhập mảng
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        // Nhập target
        int target = sc.nextInt();

        // In kết quả
        System.out.println(countMajoritySubarrays(nums, target));

        sc.close();
    }
// Hàm đếm số subarray mà target là majority element
    public static long countMajoritySubarrays(int[] nums, int target) {

        int n = nums.length;

        // pref là prefix sum đã được dịch sang phải n đơn vị
        // Giá trị thực của prefix = pref - n
        int pref = n;

        // freq[i] = số lần prefix có giá trị i xuất hiện
        int[] freq = new int[2 * n + 1];

        // Prefix = 0 xuất hiện trước khi duyệt mảng
        freq[n] = 1;

        // less = số prefix nhỏ hơn prefix hiện tại
        long less = 0;

        // Kết quả cuối cùng
        long ans = 0;

        // Duyệt từng phần tử
        for (int num : nums) {

            // target được xem là +1
            if (num == target) {

                // Các prefix bằng giá trị cũ giờ trở thành nhỏ hơn
                less += freq[pref];

                // Prefix tăng 1
                pref++;

            } else {

                // Prefix giảm 1
                pref--;

                // Những prefix bằng prefix mới không còn nhỏ hơn
                less -= freq[pref];
            }

            // Lưu prefix hiện tại
            freq[pref]++;

            // Mỗi prefix nhỏ hơn tạo ra một subarray hợp lệ
            ans += less;
        }

        return ans;
    }
}

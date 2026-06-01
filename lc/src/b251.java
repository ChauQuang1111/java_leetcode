// # Minimum Cost of Buying Candies With Discount (01/06/2026)

// # Đề bài

// # Một cửa hàng bán kẹo có chương trình khuyến mãi:


// # Khi bạn mua 2 viên kẹo, bạn sẽ được 1 viên kẹo miễn phí.

// # Điều kiện:

// # Viên kẹo miễn phí phải có giá nhỏ hơn hoặc bằng giá của viên kẹo rẻ nhất trong 2 viên bạn mua.

// # Bạn có thể mua kẹo theo bất kỳ thứ tự nào.

// # Mảng cost[i] cho biết giá của viên kẹo thứ i.

// # Yêu cầu:

// # Tính số tiền ít nhất cần trả để mua tất cả các viên kẹo.

// # Ví dụ 1

// # cost = [1,2,3]

// # Ta có thể:


// # Mua kẹo giá 3 và 2

// # Nhận miễn phí kẹo giá 1

// # Số tiền phải trả:

// # 3 + 2 = 5

// # Kết quả:

// # 5

// # Ví dụ 2

// # cost = [6,5,7,9,2,2]

// # Sắp xếp giảm dần:

// # [9,7,6,5,2,2]

// # Nhóm 3 viên một:

// # 9,7,6  -> miễn phí 6

// # 5,2,2  -> miễn phí 2

// # Tiền phải trả:

// # 9 + 7 + 5 + 2 = 23

// # Kết quả:

// # 23

// # Ý tưởng

// # Để được giảm giá nhiều nhất:

// # Ta muốn viên được miễn phí có giá càng lớn càng tốt.

// # Vì vậy nên sắp xếp mảng theo thứ tự giảm dần.

// # Ví dụ:

// # [9,7,6,5,4,3]

// # Ghép:

// # 9,7,6  -> miễn phí 6

// # 5,4,3  -> miễn phí 3

// # Ta chỉ cần cộng:

// # 9 + 7 + 5 + 4 = 25

// # Bỏ qua các phần tử ở vị trí:

// # 2, 5, 8, ...

// # (tức là mỗi phần tử thứ 3 sau khi sắp xếp giảm dần).

// # Thuật toán

// # Sắp xếp cost giảm dần.

// # Duyệt mảng:

// # Cứ 3 viên kẹo thì:

// # Trả tiền 2 viên đầu.

// # Viên thứ 3 miễn phí.

// # Trả về tổng tiền phải trả.

// # Ví dụ trực quan

// # cost = [1,3,2,4,5]

// # Sắp xếp giảm dần:

// # [5,4,3,2,1]

// # Nhóm:

// # 5,4,3 -> miễn phí 3

// # 2,1   -> trả cả 2

// # Tiền phải trả:

// # 5 + 4 + 2 + 1 = 12

// # Đáp án:

// # 12

// # Mấu chốt của bài là: sắp xếp giảm dần rồi bỏ qua mỗi phần tử thứ 3, vì đó là viên kẹo được miễn phí.

// # Đây là lời giải của bạn với chú thích chi tiết từng bước:


// from typing import List
// class Solution:

//     def minimumCost(self, cost: List[int]) -> int:



//         # Sắp xếp giá kẹo theo thứ tự tăng dần

//         cost.sort()



//         # Biến lưu tổng số tiền phải trả

//         total = 0



//         # Bắt đầu từ viên kẹo đắt nhất

//         i = len(cost) - 1



//         # Duyệt từ phải sang trái

//         while i >= 0:



//             # Trả tiền cho viên kẹo đắt nhất trong nhóm

//             total += cost[i]



//             # Nếu còn viên thứ hai trong nhóm

//             if i - 1 >= 0:

//                 # Trả tiền cho viên này

//                 total += cost[i - 1]



//             # Bỏ qua viên thứ ba (được miễn phí)

//             i -= 3



//         return total

// # Ý tưởng thuật toán

// # Sau khi sắp xếp tăng dần:



// # cost = [1, 2, 3, 4, 5, 6]

// # Ta duyệt từ cuối mảng:



// # 6, 5, 4

// # ↑  ↑  miễn phí



// # 3, 2, 1

// # ↑  ↑  miễn phí

// # Ta chỉ cộng:



// # 6 + 5 + 3 + 2 = 16

// # Mô phỏng từng bước

// # Ví dụ:



// # cost = [6,5,7,9,2,2]

// # Sau khi sort:



// # [2,2,5,6,7,9]

// # Ban đầu:



// # i = 5

// # total = 0

// # Lần 1

// # i = 5

// # Nhóm:



// # 9, 7, 6

// # Trả:



// # 9 + 7 = 16

// # Miễn phí:



// # 6

// # Cập nhật:



// # total = 16

// # i = 2

// # Lần 2

// # i = 2

// # Nhóm:



// # 5, 2, 2

// # Trả:



// # 5 + 2 = 7

// # Miễn phí:



// # 2

// # Cập nhật:



// # total = 23

// # i = -1

// # Dừng.

// # Kết quả:



// # 23

// # Tại sao i -= 3?

// # Mỗi lần ta xử lý một nhóm gồm:



// # [đắt nhất] [đắt nhì] [miễn phí]

// # Tức là 3 viên kẹo.

// # Ví dụ:



// # 9 7 6 5 2 2

// # ↑ ↑ x

// # Sau khi xử lý nhóm đầu:



// # 5 2 2

// # ↑ ↑ x

// # Nên phải nhảy qua 3 phần tử:



// # i -= 3

// # Độ phức tạp

// # Sắp xếp:

// # O(n log n)

// # Duyệt mảng:

// # O(n)

// # Tổng:



// # O(n log n)

// # Bộ nhớ phụ:

// # O(1)

// # (nếu không tính bộ nhớ dùng cho việc sắp xếp).

// Lời giải này **không dùng sắp xếp** mà tận dụng ràng buộc của đề bài:

// ```java
// 1 <= cost[i] <= 100
// ```

// Do giá kẹo chỉ từ 1 đến 100 nên ta có thể dùng **Counting Sort** để đếm số lượng kẹo của từng mức giá.

// ---

// ## Ý tưởng chính

// Thông thường ta sẽ:

// 1. Sắp xếp giảm dần.
// 2. Cứ 3 viên thì trả tiền 2 viên đắt nhất.
// 3. Viên thứ 3 miễn phí.

// Ví dụ:

// ```text
// [9,7,6,5,2,2]

// => trả: 9,7
// => miễn phí: 6

// => trả: 5,2
// => miễn phí: 2
// ```

// ---

// ### Thay vì sort

// Ta tạo:

// ```java
// int[] candies = new int[101];
// ```

// Trong đó:

// ```text
// candies[i]
// ```

// = số lượng kẹo có giá `i`.

// Ví dụ:

// ```java
// costs = [6,5,7,9,2,2]
// ```

// Sau khi đếm:

// ```text
// candies[2] = 2
// candies[5] = 1
// candies[6] = 1
// candies[7] = 1
// candies[9] = 1
// ```

// ---

// ## Biến `bought`

// ```java
// int bought = 0;
// ```

// Ý nghĩa:

// ```text
// bought = số viên đã trả tiền trong nhóm hiện tại
// ```

// Khuyến mãi là:

// ```text
// mua 2 -> miễn phí 1
// ```

// Nên:

// ```text
// bought = 0  : chưa mua viên nào
// bought = 1  : đã mua 1 viên
// bought = 2  : đã mua đủ 2 viên
// ```

// Khi đã mua đủ 2 viên:

// ```text
// viên tiếp theo sẽ miễn phí
// ```

// Sau đó bắt đầu chu kỳ mới.

// ---

// ## Đếm số lượng

// ```java
// for (int c : costs)
//     candies[c]++;
// ```

// Ví dụ:

// ```text
// [6,5,7,9,2,2]
// ```

// →

// ```text
// candies[2]=2
// candies[5]=1
// candies[6]=1
// candies[7]=1
// candies[9]=1
// ```

// ---

// ## Duyệt từ giá cao xuống thấp

// ```java
// for (int i = 100; i > 0; i--)
// ```

// Ta muốn xử lý:

// ```text
// 100
// 99
// 98
// ...
// 1
// ```

// giống như đang duyệt mảng đã sắp xếp giảm dần.

// ---

// ## Trường hợp 1

// ```java
// if (candies[i] > 2 - bought)
// ```

// Nghĩa là:

// ```text
// số kẹo giá i đủ để hoàn thành ít nhất 1 chu kỳ
// ```

// Ví dụ:

// ```text
// bought = 1
// candies[10] = 5
// ```

// Ta còn cần:

// ```text
// 2 - bought = 1
// ```

// để hoàn thành nhóm mua.

// Vì có 5 viên nên chắc chắn tạo được chu kỳ:

// ```text
// mua
// miễn phí
// ```

// ---

// ### Đoạn này

// ```java
// candies[i] -= (3 - bought);
// ```

// Ta loại bỏ số viên đầu tiên vừa xử lý.

// Ví dụ:

// ```text
// bought = 1
// ```

// Ta cần:

// ```text
// 3 - bought = 2
// ```

// viên để hoàn thành:

// ```text
// mua viên thứ 2
// miễn phí viên thứ 3
// ```

// ---

// ### Tính tiền

// ```java
// cost += (2 - bought) * i;
// ```

// Ví dụ:

// ```text
// bought = 1
// ```

// Ta chỉ cần trả tiền thêm:

// ```text
// 1 viên
// ```

// nên cộng:

// ```text
// 1 * i
// ```

// ---

// ## Xử lý các nhóm hoàn chỉnh

// ```java
// cost += 2 * i * (candies[i] / 3);
// ```

// Sau khi xử lý phần lẻ ban đầu, số kẹo còn lại cùng giá.

// Ví dụ:

// ```text
// 6 viên giá 10
// ```

// Ta có:

// ```text
// 10 10 10
// 10 10 10
// ```

// Mỗi nhóm:

// ```text
// trả 2
// miễn phí 1
// ```

// Chi phí:

// ```text
// 2 * 10
// ```

// Số nhóm:

// ```java
// candies[i]/3
// ```

// Nên:

// ```java
// 2*i*(candies[i]/3)
// ```

// ---

// ## Phần dư

// ```java
// bought = candies[i] % 3;
// ```

// Ví dụ:

// ```text
// 5 viên giá 10
// ```

// Sau các nhóm hoàn chỉnh:

// ```text
// 10 10 10 | 10 10
// ```

// Dư:

// ```text
// 2 viên
// ```

// Nên:

// ```java
// bought = 2
// ```

// ---

// ### Trả tiền cho phần dư

// ```java
// cost += i * bought;
// ```

// Ví dụ:

// ```text
// dư 2 viên giá 10
// ```

// Phải trả:

// ```text
// 20
// ```

// ---

// ## Trường hợp 2

// ```java
// else
// ```

// Nghĩa là:

// ```text
// số lượng kẹo hiện tại không đủ để hoàn thành chu kỳ mua 2 tặng 1
// ```

// Ví dụ:

// ```text
// bought = 1
// candies[i] = 1
// ```

// Ta chỉ đơn giản:

// ```java
// bought += candies[i];
// cost += i * candies[i];
// ```

// vì tất cả đều phải trả tiền.

// ---

// # Ví dụ

// ```text
// [9,7,6,5,2,2]
// ```

// Duyệt:

// ```text
// 9 -> trả
// bought=1
// cost=9

// 7 -> trả
// bought=2
// cost=16

// 6 -> miễn phí
// bought=0
// cost=16

// 5 -> trả
// bought=1
// cost=21

// 2 -> trả
// bought=2
// cost=23

// 2 -> miễn phí
// bought=0
// cost=23
// ```

// Kết quả:

// ```text
// 23
// ```

// ---

// ## Độ phức tạp

// ### Cách thông thường

// Sắp xếp:

// ```text
// O(n log n)
// ```

// ---

// ### Cách này

// Đếm:

// ```text
// O(n)
// ```

// Duyệt 100 mức giá:

// ```text
// O(100)
// ```

// Tổng:

// ```text
// O(n + 100)
// ≈ O(n)
// ```

// Đây là lý do lời giải này nhanh hơn cách sắp xếp: nó tận dụng việc **giá kẹo chỉ nằm trong khoảng 1–100** để thay thế `sort()` bằng **counting sort**.



import java.util.*;
public class b251 {
 static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
  // Nhập số lượng kẹo
        int n = sc.nextInt();

        int[] costs = new int[n];

        // Nhập giá từng viên kẹo
        for (int i = 0; i < n; i++) {
            costs[i] = sc.nextInt();
        }


        int result = minimumCost(costs);

        System.out.println(result);

        sc.close();
    }
public static int minimumCost(int[] costs) {

        // candies[i] = số lượng kẹo có giá i
        int[] candies = new int[101];

        // Đếm số lượng kẹo theo từng mức giá
        for (int c : costs) {
            candies[c]++;
        }

        // bought:
        // 0 = chưa mua viên nào trong chu kỳ
        // 1 = đã mua 1 viên
        // 2 = đã mua 2 viên (viên tiếp theo được miễn phí)
        int bought = 0;

        // Tổng số tiền phải trả
        int cost = 0;

        // Duyệt từ giá cao nhất xuống thấp nhất
        for (int i = 100; i > 0; i--) {

            // Nếu số kẹo giá i đủ để hoàn thành ít nhất 1 chu kỳ
            if (candies[i] > 2 - bought) {

                // Loại bỏ số viên đã dùng để hoàn thành chu kỳ đầu tiên
                candies[i] -= (3 - bought);

                // Trả tiền cho các viên cần mua để hoàn thành chu kỳ
                cost += (2 - bought) * i;

                // Xử lý các nhóm hoàn chỉnh:
                // mỗi 3 viên chỉ trả tiền 2 viên
                cost += 2 * i * (candies[i] / 3);

                // Số viên còn dư sau các nhóm hoàn chỉnh
                bought = candies[i] % 3;

                // Trả tiền cho các viên dư
                cost += i * bought;

            } else {

                // Không đủ để tạo chu kỳ mua 2 tặng 1
                // nên phải trả tiền toàn bộ
                bought += candies[i];
                cost += i * candies[i];
            }
        }

        return cost;
    }
}



// # 3003. Maximize the Number of Partitions After Operations(17/10/2025)
import java.util.*;

public class b70 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        String S = sc.next();

        int k = sc.nextInt();

        int result = maxPartitionsAfterOperations(S, k);

        // In kết quả
        System.out.println(result);

        sc.close();
    }

    public static int maxPartitionsAfterOperations(String S, int k) {
        // Nếu k = 26, toàn bộ bảng chữ cái được phép → chỉ cần 1 nhóm
        if (k == 26)
            return 1;

        char[] s = S.toCharArray();
        int n = s.length;

        // left1: lưu bitmask ký tự trong đoạn hiện tại (trái)
        // left2: lưu các ký tự bị trùng (xuất hiện >= 2 lần)
        // partitions: lưu số lượng nhóm đến vị trí i
        int[] left1 = new int[n], left2 = new int[n];
        int[] partitions = new int[n];

        // mask1: bitmask ký tự trong đoạn hiện tại
        // mask2: bitmask ký tự trùng trong đoạn
        // count: số nhóm hiện tại
        int mask1 = 0, mask2 = 0, count = 1;

        // 🚀 Duyệt từ trái sang phải
        for (int i = 0; i < n; i++) {
            int filter = 1 << (s[i] - 'a'); // tạo bitmask cho ký tự hiện tại

            mask2 |= mask1 & filter; // nếu ký tự đã xuất hiện, thêm vào mask2
            mask1 |= filter; // thêm ký tự vào mask1

            // Nếu số ký tự khác nhau > k → bắt đầu nhóm mới
            if (Integer.bitCount(mask1) > k) {
                mask1 = filter; // reset nhóm mới chỉ chứa ký tự hiện tại
                mask2 = 0; // reset ký tự trùng
                count++; // tăng số nhóm
            }

            left1[i] = mask1; // lưu mask1 tại vị trí i
            left2[i] = mask2; // lưu mask2 tại vị trí i
            partitions[i] = count; // lưu tổng số nhóm hiện tại
        }

        // Lưu kết quả ban đầu = số nhóm chia được khi không đổi ký tự
        int ans = count;

        // Reset lại để duyệt từ phải sang trái
        mask1 = 0;
        mask2 = 0;
        count = 0;

        // 🚀 Duyệt từ phải sang trái để xem nếu đổi 1 ký tự thì có thể tăng số nhóm
        // không
        for (int i = n - 1; i >= 0; i--) {
            int filter = 1 << (s[i] - 'a'); // bitmask ký tự hiện tại

            mask2 |= mask1 & filter; // ký tự trùng bên phải
            mask1 |= filter; // thêm ký tự vào mask bên phải

            // Nếu vượt quá k ký tự khác nhau → bắt đầu nhóm mới từ phải
            if (Integer.bitCount(mask1) > k) {
                mask1 = filter;
                mask2 = 0;
                count++;
            }

            // Nếu nhóm bên phải hiện tại có đúng k ký tự
            if (Integer.bitCount(mask1) == k) {
                // Trường hợp đặc biệt: ký tự trùng xuất hiện ở cả 2 phía
                // và khi đổi ký tự, có thể tạo thêm 2 nhóm
                if ((filter & mask2) != 0 && (filter & left2[i]) != 0 &&
                        Integer.bitCount(left1[i]) == k &&
                        (left1[i] | mask1) != (1 << 26) - 1) {
                    ans = Math.max(ans, count + partitions[i] + 2);
                }
                // Nếu có ký tự trùng ở bên phải → có thể thêm 1 nhóm
                else if (mask2 != 0) {
                    ans = Math.max(ans, count + partitions[i] + 1);
                }
            }
        }

        return ans;

    }
}

// # Dưới đây là **giải thích chi tiết** và **code có chú thích từng dòng** cho
// bài **LeetCode 3003 — Maximize the Number of Partitions After Operations** 👇

// # ---

// # ### 🎯 **Yêu cầu đề bài tóm tắt**

// # Cho một chuỗi ký tự `s` (gồm chữ thường `a-z`) và một số nguyên `k`.
// # Ta muốn chia chuỗi thành **nhiều đoạn nhất có thể**, sao cho **mỗi đoạn có
// ≤ k ký tự khác nhau**.

// # 👉 Tuy nhiên, ta **được phép thay đổi đúng 1 ký tự bất kỳ trong chuỗi `s`**
// thành bất kỳ chữ cái nào khác.
// # → Hỏi: sau khi thay đổi ký tự đó tối ưu nhất, có thể chia được **nhiều đoạn
// nhất là bao nhiêu**?

// # ---

// # ### 🧩 **Ý tưởng chính của thuật toán**

// # 1. Mỗi ký tự được biểu diễn bằng một **bitmask** — bit thứ `i` thể hiện ký
// tự `'a' + i` có xuất hiện hay không.
// # → Giúp kiểm tra số ký tự khác nhau bằng `.bit_count()`.

// # 2. Duyệt qua chuỗi, chia thành các nhóm (partition) sao cho mỗi nhóm có ≤
// `k` ký tự khác nhau.

// # * Làm việc này **từ trái sang phải (prefix)**.
// # * Và làm lại **từ phải sang trái (suffix)**.

// # 3. Sau đó, thử “thay đổi 1 ký tự” ở **mỗi vị trí `i`**, rồi ghép **prefix
// trái + suffix phải** quanh vị trí đó, xem:

// # * Nếu thay đổi giúp gộp hoặc tách nhóm → số lượng nhóm có thể tăng.

// # ---

// # ### 🧠 **Chi tiết code có chú thích**
// from typing import List
// class Solution:
// def maxPartitionsAfterOperations(self, s: str, k: int) -> int:
// n = len(s)

// # ✅ Mỗi ký tự được chuyển thành bitmask (bit 0 -> 'a', bit 25 -> 'z')
// # Ví dụ: 'a' -> 1 (000001), 'b' -> 2 (000010), ...
// set_bits = [1 << (ord(letter) - ord("a")) for letter in s]

// # -----------------------------
// # Hàm tạo prefix hoặc suffix partition
// # -----------------------------
// def make_prefix(set_bits: List[int]):
// prefix = [0] # số lượng nhóm trước vị trí i
// prefix_mask = [0] # mask chứa các ký tự của nhóm hiện tại
// mask = 0
// groups = 0
// for current_index_set_bits in set_bits:
// # thêm ký tự hiện tại vào nhóm
// mask |= current_index_set_bits
// # nếu vượt quá k ký tự khác nhau, phải tách nhóm
// if mask.bit_count() > k:
// groups += 1
// mask = current_index_set_bits # nhóm mới bắt đầu
// prefix.append(groups)
// prefix_mask.append(mask)
// return prefix, prefix_mask

// # ✅ prefix từ trái sang phải
// prefix, prefix_mask = make_prefix(set_bits)
// # ✅ suffix từ phải sang trái (đảo ngược mảng)
// suffix, suffix_mask = make_prefix(set_bits[::-1])

// max_partitions_after_operations = 0

// # -----------------------------
// # Thử thay đổi ký tự tại mỗi vị trí index
// # -----------------------------
// for index in range(n):
// # Tổng số nhóm ở 2 phía (trái + phải)
// candidate = prefix[index] + suffix[-(index + 2)]

// # Hợp ký tự của 2 phía
// mask = prefix_mask[index] | suffix_mask[-(index + 2)]

// # Giờ xét xem thay đổi ký tự tại vị trí index giúp được mấy nhóm:
// if min(mask.bit_count() + 1, 26) <= k:
// # ✅ Nếu sau khi đổi, tổng ký tự khác nhau <= k
// # => có thể thêm 1 nhóm mới
// candidate += 1
// elif (
// prefix_mask[index].bit_count() == k
// and suffix_mask[-(index + 2)].bit_count() == k
// and mask.bit_count() < 26
// ):
// # ✅ Nếu cả 2 phía đều đủ k ký tự khác nhau
// # nhưng vẫn còn chỗ (tổng < 26) → có thể thêm 3 nhóm
// candidate += 3
// else:
// # ✅ Ngược lại, chỉ thêm 2 nhóm tối đa
// candidate += 2

// # Cập nhật kết quả lớn nhất
// max_partitions_after_operations = max(
// max_partitions_after_operations, candidate
// )

// return max_partitions_after_operations

// ### 🔍 **Tóm tắt lại luồng hoạt động**

// # 1. **Biểu diễn** từng ký tự bằng bitmask.
// # 2. **Tính prefix & suffix**: chia chuỗi thành nhóm hợp lệ ≤ `k` ký tự khác
// nhau.
// # 3. **Thử đổi từng ký tự** và kiểm tra xem có thể tăng được số nhóm không.
// # 4. **Chọn kết quả lớn nhất** sau khi duyệt hết.

// # ---

// # Nếu bạn muốn, mình có thể vẽ **sơ đồ trực quan** để thấy rõ cách “chia nhóm
// trước/sau khi đổi ký tự” — bạn có muốn mình vẽ không?

// # ---

// # ## 📝 Đề bài:

// # Bạn được cho:

// # * Một **chuỗi kí tự `s`** (chỉ gồm các chữ cái thường)
// # * Một số nguyên `k`

// # Bạn có thể thực hiện các phép sau:

// # 1. Trước khi làm gì khác, **bạn được phép đổi tối đa 1 kí tự** trong `s`
// thành một kí tự khác nào đó (cả chữ cái thường).
// # 2. Sau đó, bạn thực hiện các bước phân đoạn (`partitioning operations`) lặp
// lại cho đến khi `s` bị rỗng:

// # * Mỗi lần, bạn chọn **prefix dài nhất** của `s` mà prefix đó có **tối đa
// `k` kí tự phân biệt** (distinct characters).
// # * Xoá (delete) prefix đó khỏi đầu chuỗi `s`.
// # * Tăng số phân đoạn lên 1.
// # * Phần còn lại của chuỗi giữ nguyên thứ tự ban đầu.

// # Trả về **số phân đoạn tối đa** có thể được tạo ra, nếu bạn chọn cách **thay
// đổi tối ưu nhất** (hoặc không thay đổi nếu tốt nhất).

// # ---

// # ## 🔍 Ví dụ:

// # * Ví dụ 1:

// # ```
// # s = "accca", k = 2
// # ```

// # Bạn có thể thay `s[2]` (ký tự thứ 3, ‘c’) thành ‘b’. Khi đó `s` trở thành
// `"acbca"`.
// # Thực hiện phân đoạn:

// # 1. Prefix có ≤2 kí tự phân biệt → `"acbca"` (toàn chuỗi), XOÁ hết → 1 phân
// đoạn
// # Có thể làm theo các prefix nhỏ hơn nếu muốn theo greedy lâu hơn? Có thể
// chọn `"ac"`, xoá rồi `"bc"`, rồi `"a"`, tổng là 3 phân đoạn.

// # Kết quả tối đa = **3**.

// # * Ví dụ 2:

// # ```
// # s = "aabaab", k = 3
// # ```

// # Vì `k = 3` quá lớn so với số kí tự phân biệt có thể có, cho dù đổi kí tự
// hay không, bạn vẫn có thể cho prefix toàn bộ chuỗi mỗi lần, nên số phân đoạn
// = **1**.

// # ---

// # ## 💡 Ý tưởng/chiến lược

// # Để tối đa hoá số phân đoạn, ta muốn làm sao để khi mình cứ lấy prefix “lớn
// nhất có ≤ k kí tự phân biệt” thì prefix đó **không quá lớn**, vì những prefix
// nhỏ hơn = xóa nhiều lần hơn = nhiều phân đoạn hơn.

// # Việc được phép thay đổi **1 kí tự** cho phép bạn:

// # * Thay vào một vị trí thích hợp để làm giảm sự đa dạng kí tự trong phần đầu
// chuỗi, để các prefix nhỏ hơn phải dừng lại từ sớm hơn → ra được nhiều phân
// đoạn hơn.

// # Chiến lược:

// # * Giả sử bạn không thay đổi gì, thì quá trình phân đoạn là **greedy**: mỗi
// lần lấy prefix dài nhất có ≤ k distinct, xoá, tiếp tục với phần còn lại.
// # * Xem xét thay đổi mỗi vị trí trong s (hoặc khoảng nào đó) thành một ký tự
// có ích để làm cho phân đoạn có thể nhiều hơn. Thường bạn muốn gây ảnh hưởng
// mạnh cho các prefix đầu, vì đó là những lần mà phân đoạn có thể tăng nhiều
// nếu prefix sớm bị “nghẽn” bởi số lượng kí tự phân biệt vượt k.

// # ---

// # ## ✅ Tóm tắt:

// # * Chuỗi `s`, số `k`
// # * Trước khi phân đoạn, bạn được quyền thay đổi **tối đa 1 kí tự**
// # * Sau đó thực hiện: mỗi lần chọn prefix dài nhất có ≤ k ký tự phân biệt,
// xoá prefix đó, tăng count phân đoạn
// # * Mục tiêu: thay đổi đâu hoặc không thay đổi sao cho số phân đoạn ra được
// là **lớn nhất**

// # ---

// # Nếu bạn muốn, mình có thể giải rõ một ví dụ từng bước (tức là chọn vị trí
// thay đổi + bước phân đoạn) và sau đó viết code minh hoạ bằng Java hay Python
// với chú thích rõ ràng?
// # Tóm gọn lại cho bạn — đây là **yêu cầu chính của đề bài LeetCode 3003 –
// “Maximize the Number of Partitions After Operations”** 👇

// # ---

// # ## 🧩 **Yêu cầu đề bài:**

// # > Cho một chuỗi ký tự `s` và một số nguyên `k`, bạn được phép **thay đổi
// tối đa 1 ký tự trong chuỗi**.
// # > Sau đó, hãy chia chuỗi `s` thành **nhiều phần nhất có thể** (partition
// count tối đa), sao cho **mỗi phần** có **tối đa `k` ký tự phân biệt (distinct
// characters)**.

// # 👉 **Trả về số lượng phần tối đa** mà bạn có thể tạo ra sau khi thực hiện
// phép thay đổi tối ưu (hoặc không thay đổi nếu không cần).

// # ---

// # ## 🔹 Cách chia chuỗi:

// # * Bắt đầu từ đầu chuỗi.
// # * Mỗi lần chọn **prefix dài nhất** (đoạn đầu) mà **có ≤ k ký tự khác
// nhau**.
// # * Xoá đoạn đó khỏi chuỗi và tiếp tục làm với phần còn lại.
// # * Mỗi lần như vậy tính là **một partition (một phần)**.

// # ---

// # ## 🔹 Mục tiêu:

// # > Tìm số partition **nhiều nhất có thể** nếu bạn được phép thay đổi **tối
// đa 1 ký tự** trong chuỗi.

// # ---

// # ## 🔹 Ví dụ minh họa:

// # ### 🧠 Ví dụ 1:

// # ```
// # s = "accca", k = 2
// # ```

// # Không đổi gì:

// # * Phân đoạn đầu tiên có thể là `"acc"` (vì chỉ có `a, c`)
// # * Sau khi xóa `"acc"`, còn lại `"ca"` → thêm 1 phân đoạn nữa
// # → Tổng = 2 partitions.

// # Nếu đổi ký tự thứ 3 (`c`) thành `b`, ta được `"acbca"`
// # → Phân đoạn 1: `"ac"` (a, c)
// # → Phân đoạn 2: `"bc"` (b, c)
// # → Phân đoạn 3: `"a"`
// # → Tổng = **3 partitions** ✅ (nhiều hơn)

// # ---

// // # ### ✅ Kết luận:

// // # **Đề yêu cầu:**
// // # Tìm **số partition tối đa** có thể đạt được từ chuỗi `s`
// // # 👉 khi được phép **thay đổi tối đa 1 ký tự**
// // # 👉 và mỗi partition có **≤ k ký tự phân biệt**.

// // # ---

// // # Nếu bạn muốn, mình có thể minh họa quy trình chia cụ thể cho từng bước
// (với bảng ký tự và đếm distinct) để bạn thấy rõ cách tính partition. Bạn muốn
// mình minh họa ví dụ `"accca", k=2"` từng bước không?
// Rất hay 👏 — đây là một **lời giải tối ưu khác** cho bài **LeetCode 3003.
// Maximize the Number of Partitions After Operations**, được viết hoàn toàn
// bằng **bitmask** và **một lần duyệt trái → phải + một lần duyệt phải →
// trái**.
// Mình sẽ giải thích **chi tiết từng bước logic**, để bạn nắm rõ cách thuật
// toán này hoạt động nhé.

// ---

// ## 🧩 **Tóm tắt đề bài**

// Bạn có chuỗi `S` chỉ gồm chữ thường (`a–z`) và một số `k`.
// Bạn được phép **đổi 1 ký tự trong chuỗi** thành **bất kỳ ký tự nào khác
// ('a'–'z')**.

// Mục tiêu:
// 👉 **Chia chuỗi thành nhiều đoạn nhất có thể**, sao cho **mỗi đoạn có ≤ k ký
// tự khác nhau.**

// ---

// ## 🧠 **Ý tưởng tổng quát của lời giải**

// 1. Dùng **bitmask** để biểu diễn tập ký tự trong mỗi đoạn:

// * `mask1`: chứa các ký tự hiện có trong đoạn hiện tại.
// * `mask2`: chứa các ký tự đã bị lặp lại trong đoạn (xuất hiện ≥ 2 lần).

// 2. Duyệt **trái → phải** để:

// * Tính xem tại mỗi vị trí `i`, có bao nhiêu đoạn đã được tạo
// (`partitions[i]`).
// * Ghi lại `left1[i]`: bitmask của đoạn hiện tại.
// * Ghi lại `left2[i]`: bitmask của ký tự trùng trong đoạn.

// 3. Duyệt **phải → trái** để:

// * Tính ngược lại các đoạn và thử **giả lập đổi 1 ký tự tại vị trí i**.
// * Dựa vào mask trái (`left1, left2`) và mask phải (`mask1, mask2`) để xem nếu
// đổi ký tự thì có thể **thêm bao nhiêu đoạn mới (1, 2 hoặc 3)**.

// 4. Giữ `ans` là **số đoạn tối đa có thể tạo ra**.

// ---

// ## 🔍 **Phân tích chi tiết từng phần code**

// ### **(1) Xử lý trường hợp đặc biệt**

// ```java
// if (k == 26) return 1;
// ```

// Nếu `k = 26`, mọi chuỗi đều hợp lệ vì có tối đa 26 ký tự → chỉ cần 1 đoạn.

// ---

// ### **(2) Duyệt từ trái sang phải**

// ```java
// int mask1 = 0, mask2 = 0, count = 1;
// for (int i = 0; i < n; i++) {
// int filter = 1 << (s[i] - 'a'); // bit ứng với ký tự hiện tại
// mask2 |= mask1 & filter; // nếu ký tự này đã xuất hiện -> thêm vào mask2
// mask1 |= filter; // thêm ký tự vào mask1
// if (Integer.bitCount(mask1) > k) { // nếu vượt quá k ký tự khác nhau
// mask1 = filter; // bắt đầu đoạn mới
// mask2 = 0;
// count++;
// }
// left1[i] = mask1;
// left2[i] = mask2;
// partitions[i] = count; // lưu số đoạn hiện có đến i
// }
// ```

// 🧩 **Ý nghĩa:**

// * `mask1`: các ký tự khác nhau trong đoạn hiện tại.
// * `mask2`: ký tự trùng trong đoạn.
// * `partitions[i]`: số đoạn sau khi xử lý đến vị trí `i`.
// * Nếu `mask1` có hơn `k` bit → tạo đoạn mới.

// ---

// ### **(3) Duyệt từ phải sang trái**

// ```java
// mask1 = mask2 = count = 0;
// for (int i = n - 1; i >= 0; i--) {
// int filter = 1 << (s[i] - 'a');
// mask2 |= mask1 & filter;
// mask1 |= filter;
// if (Integer.bitCount(mask1) > k) {
// mask1 = filter;
// mask2 = 0;
// count++;
// }
// ```

// 🧩 **Ý nghĩa:**

// * Tính số nhóm (segment) nếu bắt đầu từ **phải → trái**.
// * Dùng logic tương tự đoạn trên.

// ---

// ### **(4) Tính toán kết quả tốt nhất**

// ```java
// if (Integer.bitCount(mask1) == k) {
// if ((filter & mask2) != 0 && (filter & left2[i]) != 0
// && Integer.bitCount(left1[i]) == k
// && (left1[i] | mask1) != (1 << 26) - 1)
// ans = Math.max(ans, count + partitions[i] + 2);
// else if (mask2 != 0)
// ans = Math.max(ans, count + partitions[i] + 1);
// }
// ```

// 🧩 **Giải thích logic so sánh này:**

// | Trường hợp | Điều kiện | Ý nghĩa | Cộng thêm |
// | ------------------------------------------------- |
// ------------------------------------------ |
// ----------------------------------------------- | --------- |
// | `filter & mask2 != 0` và `filter & left2[i] != 0` | ký tự hiện tại xuất
// hiện ở cả trái và phải | nếu đổi ký tự → có thể **tách thêm 2 nhóm mới** |
// `+2` |
// | `mask2 != 0` | nếu có ký tự trùng trong nhóm bên phải | đổi ký tự giúp tạo
// **thêm 1 nhóm** | `+1` |

// Tổng số nhóm mới =
// `partitions[i]` (nhóm bên trái) + `count` (nhóm bên phải) + phần thưởng thêm
// (1 hoặc 2).

// ### **(5) Trả kết quả**

// ```java
// return ans;
// ```

// `ans` là số nhóm tối đa sau khi thử mọi khả năng đổi ký tự.

// ---

// ## ✅ **Tóm tắt độ phức tạp**

// * **Thời gian:** `O(n)` — chỉ duyệt chuỗi 2 lần.
// * **Không gian:** `O(n)` cho các mảng `left1`, `left2`, `partitions`.

// ---

// ## 💡 Ví dụ minh họa nhỏ

// ```
// S = "abac", k = 2
// ```

// * Ban đầu: "aba" hợp lệ, thêm 'c' vẫn ≤ 2 ký tự → 1 nhóm.
// * Nếu đổi 'c' → 'b' → có thể tạo thêm nhóm thứ hai → kết quả = 2.

// ---

// Bạn có muốn mình **thêm chú thích trực tiếp vào từng dòng trong code Java
// này** để bạn dễ học hơn không?

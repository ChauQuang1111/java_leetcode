
// // # # Pyramid Transition Matrix(29/12/2025)
// // # Mình sẽ **giải thích thuật toán + chú thích chi tiết từng phần code** để bạn hiểu rõ cách lời giải này hoạt động (và cả điểm chưa tối ưu của nó).
// Mình sẽ **giải thích thuật toán của lời giải Java này theo từng tầng ý tưởng**, vì đây là một **lời giải rất tối ưu (DFS + memo + mã hóa trạng thái)** cho bài **LeetCode 756 – Pyramid Transition Matrix**.
import java.util.*;

/**
 * LeetCode 756 - Pyramid Transition Matrix
 * Giải bằng DFS + Memoization + State Encoding
 */
public class b132 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Nhập chuỗi đáy
        String bottom = sc.nextLine();

        // Nhập số lượng luật allowed
        int n = sc.nextInt();
        sc.nextLine();

        // Nhập danh sách allowed
        List<String> allowed = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            allowed.add(sc.nextLine());
        }

        System.out.println(pyramidTransition(bottom, allowed));

        sc.close();
    }

    public static boolean pyramidTransition(String bottom, List<String> allowed) {
        // Khởi tạo Solver để xử lý DFS + cache
        return new Solver(allowed, bottom.length()).canDo(bottom);
    }

    /**
     * Solver: xử lý toàn bộ thuật toán
     */
    static class Solver {

        // allowed[x][y] = các ký tự có thể đặt lên trên cặp (x, y)
        final int[][][] allowed = new int[6][6][];

        // cache[len][key]:
        // 0 = chưa tính
        // 1 = true
        // 2 = false
        final int[][] cache;

        /**
         * Constructor: tiền xử lý dữ liệu
         */
        Solver(List<String> allowedList, int maxLen) {

            // Đếm số luật cho mỗi cặp (x, y)
            int[][] cnt = new int[6][6];
            for (String s : allowedList) {
                int x = s.charAt(0) - 'A';
                int y = s.charAt(1) - 'A';
                cnt[x][y]++;
            }

            // Cấp phát mảng allowed
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    allowed[i][j] = new int[cnt[i][j]];
                }
            }

            // Khởi tạo cache
            cache = new int[maxLen + 1][];
            int size = 6 * 6;
            for (int len = 3; len <= maxLen; len++) {
                cache[len] = new int[size *= 6];
            }

            // Điền dữ liệu vào allowed[x][y]
            for (String s : allowedList) {
                int x = s.charAt(0) - 'A';
                int y = s.charAt(1) - 'A';
                int z = s.charAt(2) - 'A';
                allowed[x][y][--cnt[x][y]] = z;
            }
        }

        /**
         * Hàm gọi ban đầu với String
         */
        boolean canDo(String s) {
            int len = s.length();
            int[] arr = new int[len];

            // Chuyển String -> mảng số
            for (int i = 0; i < len; i++) {
                arr[i] = s.charAt(i) - 'A';
            }

            // Trường hợp cơ sở: chỉ còn 2 ký tự
            if (len == 2) {
                return allowed[arr[0]][arr[1]].length > 0;
            }

            return canDo(arr, len);
        }

        /**
         * DFS + memo với mảng số
         */
        boolean canDo(int[] arr, int len) {

            // Trường hợp cơ sở
            if (len == 2) {
                return allowed[arr[0]][arr[1]].length > 0;
            }

            // Mã hóa trạng thái
            int key = encode(arr, len);

            // Kiểm tra cache
            if (cache[len][key] != 0) {
                return cache[len][key] == 1;
            }

            // Tính toán
            boolean result = compute(arr, len);

            // Lưu cache
            cache[len][key] = result ? 1 : 2;

            return result;
        }

        /**
         * Mã hóa mảng thành số (base-6)
         */
        int encode(int[] arr, int len) {
            int res = arr[0];
            for (int i = 1; i < len; i++) {
                res = res * 6 + arr[i];
            }
            return res;
        }

        /**
         * Kiểm tra có thể xây tầng trên hay không
         */
        boolean compute(int[] arr, int len) {

            // Kiểm tra nhanh: nếu có cặp không có luật -> false
            for (int i = 1; i < len; i++) {
                if (allowed[arr[i - 1]][arr[i]].length == 0) {
                    return false;
                }
            }

            // Xây tầng trên bằng backtracking
            return build(new int[len], 0, arr, 0, len);
        }

        /**
         * Backtracking xây từng ký tự của tầng trên
         */
        boolean build(int[] prefix, int plen,
                int[] suffix, int sidx, int slen) {

            // Cắt nhánh sớm
            if (plen > 1 && !canDo(prefix, plen)) {
                return false;
            }

            // Nếu chưa xây xong tầng trên
            if (sidx + 1 < slen) {
                for (int next : allowed[suffix[sidx]][suffix[sidx + 1]]) {
                    prefix[plen] = next;
                    if (build(prefix, plen + 1, suffix, sidx + 1, slen)) {
                        return true;
                    }
                }
            } else {
                // Đã xây xong 1 tầng, tiếp tục DFS
                return canDo(prefix, plen);
            }

            return false;
        }
    }
}

// ---

// # 1. Ý tưởng lớn của thuật toán

// Bài toán:
// 👉 Từ hàng đáy `bottom`, có thể xây kim tự tháp lên đến **1 ký tự** hay
// không, dựa trên các luật `allowed`.

// Lời giải này dùng **3 kỹ thuật chính**:

// 1. **Biểu diễn ký tự bằng số (0–5)** để xử lý nhanh
// 2. **DFS / Backtracking** để xây từng tầng
// 3. **Memoization (cache)** để tránh tính lại các trạng thái đã thử

// ---

// # 2. Biểu diễn dữ liệu (rất quan trọng)

// ## 2.1. Chỉ có 6 ký tự: `A → F`

// ```java
// 'a' - 'A' → 0..5
// ```

// → Mỗi hàng được biểu diễn bằng **mảng số nguyên**, không dùng String nữa →
// rất nhanh.

// ---

// ## 2.2. Bảng chuyển `allowed[x][y]`

// ```java
// final int[][][] allowed = new int[6][6][];
// ```

// Ý nghĩa:

// ```
// allowed[x][y] = {z1, z2, ...}
// ```

// ➡️ Nếu tầng dưới có `(x, y)` thì tầng trên có thể đặt `z1, z2, ...`

// ---

// # 3. Khởi tạo Solver (tiền xử lý)

// ## 3.1. Đếm số luật cho mỗi cặp

// ```java
// int[][] cnt = new int[6][6];

// for (String a : allowed) {
// cnt[a.charAt(0) - 'A'][a.charAt(1) - 'A']++;
// }
// ```

// 👉 Biết trước mỗi `(x, y)` có bao nhiêu `z` → cấp phát mảng đúng kích thước.

// ---

// ## 3.2. Cấp phát bảng allowed

// ```java
// this.allowed[i][j] = new int[cnt[i][j]];
// ```

// → Không dùng `List`, chỉ dùng **array thuần** → tối ưu tốc độ.

// ---

// ## 3.3. Cache (ghi nhớ trạng thái)

// ```java
// this.cache = new int[len][];
// ```

// * `cache[len][key]`
// * `0` = chưa tính
// * `1` = true
// * `2` = false

// 📌 Cache theo **độ dài hàng + trạng thái hàng**

// ---

// # 4. Mã hóa trạng thái (encode)

// ```java
// private int encode(int[] arr, int len) {
// int r = arr[0];
// for (int i = 1; i < len; i++) {
// r = r * 6 + arr[i];
// }
// return r;
// }
// ```

// Ví dụ:

// ```
// [A, C, B] → [0, 2, 1]
// key = 0*6² + 2*6 + 1
// ```

// ➡️ Mỗi hàng là **một số duy nhất** → dùng làm key cache.

// ---

// # 5. Hàm chính `canDo`

// ## 5.1. Chuẩn bị dữ liệu

// ```java
// int[] arr = new int[s.length()];
// ```

// Chuyển String → mảng số.

// ---

// ## 5.2. Trường hợp cơ sở

// ```java
// if (len == 2)
// return allowed[arr[0]][arr[1]].length > 0;
// ```

// 👉 Chỉ cần kiểm tra có luật `(x, y) → z` hay không.

// ---

// ## 5.3. Dùng cache

// ```java
// int cached = cache[len][key];
// if (cached != 0) return cached == 1;
// ```

// ➡️ Trạng thái đã tính rồi → trả kết quả ngay.

// ---

// # 6. Hàm `compute` – kiểm tra có thể xây tầng trên không

// ```java
// boolean compute(int[] arr, int len)
// ```

// ### Bước 1: Kiểm tra nhanh

// ```java
// if (allowed[arr[i - 1]][arr[i]].length == 0)
// return false;
// ```

// ❌ Nếu có **cặp nào không có luật** → khỏi cần DFS.

// ---

// ### Bước 2: Xây tầng trên bằng DFS

// ```java
// return build(new int[len], 0, arr, 0, len);
// ```

// ---

// # 7. Hàm `build` – BACKTRACKING cốt lõi

// ```java
// boolean build(prefix, plen, suffix, sidx, slen)
// ```

// ### Ý nghĩa:

// * `suffix`: hàng hiện tại
// * `prefix`: hàng phía trên đang xây
// * `plen`: số ký tự đã xây xong ở tầng trên

// ---

// ## 7.1. Cắt nhánh sớm (rất tối ưu)

// ```java
// if (plen > 1 && !canDo(prefix, plen)) return false;
// ```

// 👉 Nếu **hàng trên đang xây đã không thể lên đỉnh**
// → dừng luôn, không thử tiếp.

// ---

// ## 7.2. Thử tất cả ký tự hợp lệ

// ```java
// for (int next : allowed[suffix[sidx]][suffix[sidx + 1]]) {
// prefix[plen] = next;
// if (build(...)) return true;
// }
// ```

// ➡️ Đây là **DFS đầy đủ**, không bỏ sót tổ hợp nào.

// ---

// ## 7.3. Khi xây xong 1 hàng

// ```java
// return canDo(prefix, plen);
// ```

// 👉 Đệ quy tiếp để xây tầng cao hơn.

// ---

// # 8. Vì sao thuật toán này RẤT NHANH?

// ✔️ Không dùng String
// ✔️ Không dùng List trong DFS
// ✔️ Encode trạng thái bằng số
// ✔️ Cache mạnh theo chiều cao
// ✔️ Cắt nhánh sớm

// ➡️ **Độ phức tạp thực tế rất nhỏ**, pass tất cả test khó.

// ---

// # 9. Tóm tắt 1 câu

// > Đây là lời giải **DFS + memoization + state encoding**, xây kim tự tháp từ
// dưới lên, cắt nhánh sớm và ghi nhớ trạng thái để tránh tính lại.

// ---

// Nếu bạn muốn:

// * 🔹 Mình **viết lại phiên bản đơn giản hơn để học**
// * 🔹 So sánh lời giải này với DFS thường
// * 🔹 Giải thích bằng **ví dụ chạy tay**
// * 🔹 Chuyển sang **Python version tương đương**

// 👉 Cứ nói, mình làm tiếp theo đúng hướng bạn cần 👍

// from typing import List
// from collections import defaultdict

// class Solution:
// def pyramidTransition(self, bottom: str, allowed: List[str]) -> bool:
// # mp: map từ 2 ký tự phía dưới -> danh sách ký tự có thể đặt lên trên
// # Ví dụ: "BCG" -> mp["BC"] = ["G"]
// mp = defaultdict(list)
// for temp in allowed:
// lr = temp[:2] # lấy 2 ký tự phía dưới
// t = temp[2:] # ký tự phía trên
// mp[lr].append(t)

// # Hàm DFS: kiểm tra xem từ hàng 'line' có thể xây tiếp lên đỉnh hay không
// def dfs(line):
// lth = len(line)

// # Nếu chỉ còn 1 ký tự -> đã xây xong kim tự tháp
// if lth == 1:
// return True

// # cand[i] = danh sách ký tự có thể đặt lên cặp (line[i], line[i+1])
// cand = []

// # số lượng lựa chọn lớn nhất trong các cand
// maxcand = 0

// # Duyệt tất cả các cặp ký tự kề nhau ở hàng hiện tại
// for i in range(lth - 1):
// lr = line[i:i+2] # cặp ký tự dưới

// # Nếu không có luật chuyển cho cặp này thì bỏ qua
// if lr not in mp:
// continue

// toplist = mp[lr] # danh sách ký tự có thể đặt lên trên
// cand.append(toplist)
// maxcand = max(maxcand, len(toplist))

// # Nếu số cặp hợp lệ < lth - 1
// # => có ít nhất một cặp không xây được hàng trên
// if len(cand) != lth - 1:
// return False

// # Thử sinh các hàng phía trên (chưa sinh đủ mọi tổ hợp)
// for i in range(maxcand):
// temp = "" # hàng phía trên đang được tạo

// for j in range(lth - 1):
// # Chọn ký tự thứ i trong cand[j]
// # Nếu cand[j] ngắn hơn thì lấy phần tử cuối
// idx = min(len(cand[j]) - 1, i)
// u = cand[j][idx]
// temp += u

// # Nếu tạo được hàng hợp lệ
// if len(temp) == lth - 1:
// # Gọi đệ quy để xây tiếp
// if dfs(temp):
// return True

// # Thử hết mọi khả năng mà không xây được
// return False

// # Bắt đầu DFS từ hàng đáy
// return dfs(bottom)

// # ---

// # ## 1. Ý tưởng tổng quát

// # Bài **Pyramid Transition Matrix** dùng **DFS / Backtracking**:

// # * Mỗi lần ta có một hàng `line`
// # * Ta sinh ra **tất cả các hàng phía trên có thể**
// # * Gọi đệ quy `dfs(hàng_mới)`
// # * Nếu lên được hàng dài 1 → `True`

// # ---

// # ## 2. Phân tích từng phần code

// # ### 2.1. Tiền xử lý `allowed`

// # ```python
// # mp = defaultdict(list)
// # for temp in allowed:
// # lr = temp[:2] # 2 ký tự dưới
// # t = temp[2:] # ký tự phía trên
// # mp[lr].append(t)
// # ```

// # 👉 Chuyển `allowed` thành **bảng tra cứu**:

// # Ví dụ:

// # ```python
// # "BCG" → mp["BC"] = ["G"]
// # "CDE" → mp["CD"] = ["E"]
// # ```

// # ➡️ Giúp tra cứu nhanh:
// # **(A, B) → danh sách các ký tự có thể đặt lên trên**

// # ---

// # ## 3. Hàm DFS chính

// # ```python
// # def dfs(line):
// # ```

// # `line` = một hàng hiện tại của kim tự tháp

// # ---

// # ### 3.1. Điều kiện dừng

// # ```python
// # lth = len(line)
// # if lth == 1:
// # return True
// # ```

// # ✔️ Nếu chỉ còn 1 ký tự → xây xong kim tự tháp

// # ---

// # ### 3.2. Sinh các khả năng cho hàng trên

// # ```python
// # cand = []
// # maxcand = 0
// # ```

// # * `cand[i]` = danh sách ký tự có thể đặt lên cặp `(line[i], line[i+1])`
// # * `maxcand` = số lượng lớn nhất trong các danh sách đó

// # ---

// # ```python
// # for i in range(lth-1):
// # lr = line[i:i+2]
// # if lr not in mp:
// # continue
// # toplist = mp[lr]
// # cand.append(toplist)
// # maxcand = max(maxcand, len(toplist))
// # ```

// # 📌 Ví dụ:

// # ```
// # line = "BCD"
// # cand = [
// # ["G"], # từ "BC"
// # ["E"] # từ "CD"
// # ]
// # ```

// # ---

// # ### 3.3. Nếu thiếu luật → không xây được

// # ```python
// # if len(cand) != lth-1:
// # return False
// # ```

// # ❌ Nếu có **ít nhất một cặp** không có luật chuyển
// # → không thể tạo hàng phía trên

// # ---

// # ## 4. Sinh các hàng phía trên (chưa tối ưu)

// # ```python
// # up = [None] * (lth-1)
// # ```

// # (Không dùng đến, có thể bỏ)

// # ---

// # ### 4.1. Sinh hàng mới bằng cách "ghép chỉ số"

// # ```python
// # for i in range(maxcand):
// # temp = ""
// # for j in range(lth-1):
// # idx = min(len(cand[j])-1, i)
// # u = cand[j][idx]
// # temp += u
// # ```

// # 📌 Cách hoạt động:

// # * Lấy **ký tự thứ i** trong mỗi `cand[j]`
// # * Nếu danh sách ngắn hơn → dùng phần tử cuối

// # ⚠️ **Điểm yếu**:

// # * Không sinh **tất cả tổ hợp**
// # * Bỏ sót nhiều khả năng
// # * Không phải cách backtracking chuẩn

// # Ví dụ:

// # ```
// # cand = [["A","B"], ["C","D"]]
// # → chỉ sinh:
// # AC, BD
// # ❌ thiếu: AD, BC
// # ```

// # ---

// # ### 4.2. Gọi DFS tiếp

// # ```python
// # if len(temp) == lth-1:
// # if dfs(temp):
// # return True
// # ```

// # ✔️ Nếu **có một cách** xây được đến đỉnh
// # → trả về `True`

// # ---

// # ### 4.3. Nếu thử hết mà không được

// # ```python
// # return False
// # ```

// # ---

// # ## 5. Tổng kết thuật toán

// # ### ✔️ Đúng ở:

// # * Ý tưởng DFS
// # * Dùng map để tra luật
// # * Dừng khi còn 1 ký tự

// # ### ❌ Chưa chuẩn ở:

// # * Sinh hàng trên **không phải tất cả tổ hợp**
// # * Có thể **miss case đúng**

// # ---

// # ## 6. Phiên bản DFS chuẩn (để bạn so sánh)

// # ```python
// # def dfs(line):
// # if len(line) == 1:
// # return True

// # def backtrack(pos, cur):
// # if pos == len(line) - 1:
// # return dfs(cur)

// # pair = line[pos:pos+2]
// # if pair not in mp:
// # return False

// # for ch in mp[pair]:
// # if backtrack(pos + 1, cur + ch):
// # return True
// # return False

// # return backtrack(0, "")
// # ```

// # 📌 Phiên bản này:

// # * Sinh **đầy đủ tổ hợp**
// # * Không bỏ sót
// # * Chuẩn LeetCode

// # ---

// # ## 7. Nếu bạn muốn

// # * 🔹 Mình **chú thích lại code của bạn ngay trong từng dòng**
// # * 🔹 So sánh **DFS vs DP**
// # * 🔹 Tối ưu bằng **memoization**
// # * 🔹 Viết lại bằng **Java**

// # 👉 Nói mình biết hướng bạn muốn học tiếp nhé 👌

// # Đây là một bài toán thường gặp trên LeetCode, nói về việc **xây một kim tự
// tháp chữ** dựa trên các quy tắc cho trước.

// # ---

// # ## 1. Mô tả bài toán

// # Bạn được cho:

// # ### 🔹 `bottom`

// # * Một **chuỗi ký tự** (thường là chữ in hoa A–G).
// # * Đây là **hàng đáy** của kim tự tháp.

// # Ví dụ:

// # ```
// # bottom = "BCD"
// # ```

// # ### 🔹 `allowed`

// # * Một **danh sách các chuỗi dài 3 ký tự**.
// # * Mỗi chuỗi có dạng `"ABC"` nghĩa là:

// # > Nếu **A** và **B** đứng cạnh nhau ở hàng dưới
// # > thì **C** có thể đứng lên trên chúng ở hàng trên.

// # Ví dụ:

// # ```
// # allowed = ["BCG", "CDE", "GEA", "FFF"]
// # ```

// # ---

// # ## 2. Luật xây kim tự tháp

// # * Kim tự tháp được xây **từ dưới lên trên**.
// # * Mỗi khối ở **hàng trên** được tạo từ **2 khối kề nhau ở hàng dưới**.
// # * Với mỗi cặp `(x, y)` ở hàng dưới, ta tìm xem có luật nào `xy -> z` trong
// `allowed` hay không.
// # * Nếu có nhiều `z` thì **được chọn bất kỳ**.

// # 📌 Mục tiêu:
// # 👉 **Kiểm tra xem có thể xây lên đến đỉnh (còn 1 ký tự) hay không**

// # ---

// # ## 3. Ví dụ minh họa

// # ### Ví dụ 1

// # ```
// # bottom = "BCD"
// # allowed = ["BCG", "CDE", "GEA", "FFF"]
// # ```

// # **Bước 1:**
// # Hàng đáy:

// # ```
// # B C D
// # ```

// # * (B, C) → G (vì có "BCG")
// # * (C, D) → E (vì có "CDE")

// # Hàng trên:

// # ```
// # G E
// # ```

// # **Bước 2:**

// # * (G, E) → A (vì có "GEA")

// # Đỉnh:

// # ```
// # A
// # ```

// # ✅ Xây được kim tự tháp → **true**

// # ---

// # ### Ví dụ 2

// # ```
// # bottom = "AABA"
// # allowed = ["AAA", "AAB", "ABA", "ABB", "BAC"]
// # ```

// # * Có thể xây lên một vài tầng
// # * Nhưng đến một tầng nào đó **không có luật phù hợp**
// # ❌ Không xây tiếp được → **false**

// # ---

// # ## 4. Bài toán thực chất là gì?

// # 👉 Đây là bài **Backtracking / DFS**

// # * Mỗi tầng có thể có **nhiều cách tạo**
// # * Ta thử **tất cả khả năng**
// # * Chỉ cần **1 cách thành công** là trả về `true`

// # ---

// # ## 5. Tóm tắt ngắn gọn

// # * `bottom`: hàng đáy
// # * `allowed`: luật chuyển từ 2 ký tự → 1 ký tự
// # * Xây từ dưới lên
// # * Nếu lên được đến **1 ký tự** → `true`
// # * Nếu bị kẹt ở giữa → `false`

// # ---

// # Nếu bạn muốn:

// # * 🔹 Giải thích bằng **cây DFS**
// # * 🔹 Ví dụ chi tiết hơn từng bước
// # * 🔹 Code Java / Python kèm giải thích

// # 👉 Cứ nói, mình sẽ giải tiếp theo cách bạn dễ hiểu nhất 👍


// # 2300. Successful Pairs of Spells and Potions(08/10/2025)
import java.util.*;

public class b61 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int m = sc.nextInt();
        int[] spells = new int[m];
        for (int i = 0; i < m; i++) {
            spells[i] = sc.nextInt();
        }

        // Nhập số lượng thuốc
        int n = sc.nextInt();
        int[] potions = new int[n];
        for (int i = 0; i < n; i++) {
            potions[i] = sc.nextInt();
        }

        // Nhập ngưỡng thành công
        long success = sc.nextLong();

        int[] result = successfulPairs(spells, potions, success);

        for (int x : result) {
            System.out.print(x + " ");
        }
        sc.close();
    }

    public static int[] successfulPairs(int[] spells, int[] potions, long success) {
        int m = spells.length; // số lượng phép thuật
        int n = potions.length; // số lượng thuốc
        int max = 0; // lưu giá trị lớn nhất trong mảng potions
        int[] res = new int[m]; // mảng kết quả

        // Tìm giá trị lớn nhất trong potions
        for (int i = 0; i < n; i++) {
            max = Math.max(max, potions[i]);
        }

        // map[i] đếm số lượng potions có giá trị = i
        int[] map = new int[max + 1];
        for (int i = 0; i < n; i++) {
            map[potions[i]]++;
        }

        // Tính tổng tích lũy từ phải sang trái:
        // map[i] = số lượng potion có giá trị >= i
        int sum = 0;
        for (int i = max; i >= 0; i--) {
            sum += map[i];
            map[i] = sum;
        }

        // Duyệt qua từng spell
        for (int i = 0; i < m; i++) {
            int spell = spells[i];

            // Tính giá trị nhỏ nhất của potion cần có để spell * potion >= success
            long index = (success + spell - 1) / spell; // làm tròn lên

            // Nếu index vượt quá max thì không có potion nào đủ mạnh
            if (index <= max) {
                res[i] = map[(int) index]; // lấy số lượng potion >= index
            } else {
                res[i] = 0;
            }
        }

        return res;
    }

}
// Hàm main để nhập dữ liệu và chạy chương trình

// Rất tuyệt 👏 — đây là **một cách giải khác** cho bài **LeetCode 2300 –
// Successful Pairs of Spells and Potions**,
// và cách này **không cần sort hay binary search** mà dùng **đếm tần suất
// (frequency) + mảng tích lũy từ phải qua trái (prefix sum)** để tăng tốc tính
// toán.

// Dưới đây là **giải thích chi tiết từng bước + phân tích thuật toán** 👇

// ---

// ### 🧩 **Mục tiêu của bài toán**

// Với mỗi `spell`, ta muốn đếm **bao nhiêu potion** sao cho:

// ```
// spell * potion >= success
// ```

// ---

// ### ✅ **Ý tưởng tổng quát**

// Thay vì duyệt từng cặp `(spell, potion)` → O(n²),
// ta **đếm trước** số lượng potion có giá trị ≥ `X` bằng một **mảng đếm
// (map)**.

// Sau đó, với mỗi spell, ta chỉ cần tính **ngưỡng tối thiểu** của potion cần
// có:

// ```
// potion_min = ceil(success / spell)
// ```

// → Rồi tra nhanh trong mảng `map` để biết **có bao nhiêu potion ≥
// potion_min**.

// ---

// ### 🧱 **Giải thích từng dòng code**

// ```java
// int m = spells.length;
// int n = potions.length;
// int max = 0;
// int[] res = new int[m];
// ```

// 👉 Khởi tạo:

// * `m` là số lượng spells
// * `n` là số lượng potions
// * `max` là giá trị lớn nhất trong potions (để tạo mảng đếm vừa đủ)
// * `res` lưu kết quả cuối cùng

// ---

// ```java
// for (int i = 0; i < n; i++) {
// max = Math.max(max, potions[i]);
// }
// ```

// 👉 Tìm giá trị potion lớn nhất (`max`) để tạo mảng có kích thước đúng.

// ---

// ```java
// int[] map = new int[max + 1];
// for (int i = 0; i < n; i++) {
// map[potions[i]]++;
// }
// ```

// 👉 Đếm tần suất từng potion (giống histogram):

// * `map[v]` = số lượng potion có giá trị đúng bằng `v`.

// ---

// ```java
// int sum = 0;
// for (int i = max; i >= 0; i--) {
// sum += map[i];
// map[i] = sum;
// }
// ```

// 👉 Tạo **mảng tích lũy từ phải sang trái** (prefix sum ngược):

// * Sau vòng lặp này:
// `map[x]` = **số lượng potion có giá trị ≥ x**

// 🔹 Ví dụ:
// Giả sử `potions = [2, 5, 8]`
// thì:

// ```
// map[8] = 1
// map[5] = 2 (5 và 8)
// map[2] = 3 (2, 5, 8)
// ```

// ---

// ```java
// for (int i = 0; i < m; i++) {
// int spell = spells[i];
// long index = (success + spell - 1) / spell; // = ceil(success / spell)
// ```

// 👉 Với mỗi `spell`, ta tính **giá trị potion tối thiểu cần thiết** để tạo cặp
// thành công.

// * `(success + spell - 1) / spell` là cách làm tròn lên (ceil) trong số
// nguyên.
// → Ví dụ: `ceil(10/3)` = `(10+3-1)/3` = `12/3` = `4`

// ---

// ```java
// if (index <= max) {
// res[i] = map[(int) index];
// }
// }
// ```

// 👉 Nếu potion cần có (index) nằm trong phạm vi `[0, max]`
// → Lấy ngay số lượng potion ≥ index từ `map[index]`.

// Nếu `index > max`, tức là tất cả potions đều < giá trị cần → không có cặp nào
// thành công.

// ---

// ```java
// return res;
// ```

// 👉 Trả về mảng kết quả.

// ---

// ### 🔢 **Ví dụ minh họa**

// ```
// spells = [3, 5, 1]
// potions = [2, 5, 8]
// success = 16
// ```

// #### Bước 1️⃣: map tần suất và tích lũy

// ```
// map[8] = 1
// map[5] = 2
// map[2] = 3
// ```

// #### Bước 2️⃣: duyệt từng spell

// | spell | ceil(success/spell) | index | map[index] | kết quả |
// | :---- | :------------------ | :---- | :--------- | :---------- |
// | 3 | ceil(16/3)=6 | 6 | map[6]=1 | ✅ 1 potion |
// | 5 | ceil(16/5)=4 | 4 | map[4]=2 | ✅ 2 potions |
// | 1 | ceil(16/1)=16 | 16 | index>max | ❌ 0 potion |

// 👉 `res = [1, 2, 0]`

// ---

// ### ⚙️ **Độ phức tạp**

// | Loại | Độ phức tạp |
// | ---------- | ---------------------- |
// | Thời gian | `O(maxPotion + n + m)` |
// | Không gian | `O(maxPotion)` |

// ⏩ Cực nhanh nếu giá trị potion nhỏ (≤ 10⁵).
// Nhưng nếu potion có giá trị rất lớn (ví dụ 10⁹) → tốn bộ nhớ.

// ---

// ### 🧠 **Tóm tắt ý tưởng**

// | Bước | Mô tả | Mục đích |
// | ---- | ------------------------------- | -------------------------------- |
// | 1 | Đếm số potion theo giá trị | Dùng mảng đếm |
// | 2 | Tính tích lũy từ phải sang trái | Biết có bao nhiêu potion ≥ x |
// | 3 | Với mỗi spell, tính potion_min | Dùng ceil(success / spell) |
// | 4 | Lấy nhanh kết quả từ `map` | Không cần sort hay binary search |

// ---

// 👉 Nói ngắn gọn:
// **Thuật toán dùng frequency + prefix sum ngược để thay thế binary search**,
// giúp tìm nhanh số potion đủ mạnh cho từng spell trong **O(1)**.

// // # Rất hay — bạn đang hỏi về **một lời giải khác** cho bài **2300.
// Successful Pairs of Spells and Potions** mà **không dùng binary search**, mà
// dùng **đếm tần suất + mảng tích lũy (prefix sum / cumulative sum)** ✅

// // # Dưới đây là **phiên bản đã thêm chú thích chi tiết từng dòng** + **giải
// thích thuật toán đầy đủ** 👇

// // # ---

// // # ### ✅ Code có chú thích

// // # ```python
// // # from typing import List
// // from typing import List
// // class Solution:
// // def successfulPairs(self, spells: List[int], potions: List[int], success:
// int) -> List[int]:
// // max_spells = max(spells) # 1️⃣ Tìm giá trị spell lớn nhất để xác định kích
// thước mảng đếm
// // smaller_count = [0] * (max_spells + 1) # 2️⃣ Mảng đếm số potion có thể
// ghép với spell >= giá trị nào đó

// // # 3️⃣ Với mỗi potion, tính "giá trị spell tối thiểu" cần để tạo ra cặp
// thành công
// // for num in potions:
// // curr = success // num # Spell cần ít nhất là success / num (làm tròn lên)
// // if success % num != 0: # Nếu không chia hết → phải làm tròn lên
// // curr += 1
// // if curr > max_spells: # Nếu spell lớn nhất vẫn không đủ mạnh → bỏ qua
// // continue
// // smaller_count[curr] += 1 # Spell từ curr trở lên đều "thành công" với
// potion này

// // # 4️⃣ Tính tổng tích lũy để biết với mỗi spell ≤ x, có bao nhiêu potion
// thành công
// // for i in range(1, len(smaller_count)):
// // smaller_count[i] += smaller_count[i - 1]

// // # 5️⃣ Với mỗi spell, lấy ra số potion thỏa điều kiện
// // ans = []
// // for num in spells:
// // ans.append(smaller_count[num])
// // return ans
// // # ```

// // # ---

// // # ### 🧠 Giải thích thuật toán

// // # #### 🎯 Mục tiêu

// // # Ta cần đếm **số lượng potions** sao cho:

// // # ```
// // # spell * potion >= success
// // # ```

// // # ---

// // # #### 🧩 Ý tưởng chính

// // # Thay vì duyệt hoặc tìm nhị phân, ta **chuyển hướng vấn đề**:

// // # * Với mỗi `potion`, ta tính **giá trị spell nhỏ nhất cần thiết** để
// thành công:

// // # ```
// // # spell_min = ceil(success / potion)
// // # ```
// // # * Tức là:
// // # Nếu `spell >= spell_min` → cặp này **thành công**.
// // # Nếu `spell < spell_min` → **thất bại**.

// // # ---

// // # #### ⚙️ Cách hoạt động cụ thể

// // # | potion | success | success // potion | Cần làm tròn | spell_min |
// // # | :----: | :-----: | :---------------: | :----------: | :-------: |
// // # | 2 | 10 | 5 | ✅ +1 → 6 | 6 |
// // # | 3 | 10 | 3 | ✅ +1 → 4 | 4 |
// // # | 5 | 10 | 2 | ❌ | 2 |

// // # Vậy:

// // # * Potion có `spell_min = 6`: chỉ spell ≥ 6 thành công.
// // # * Potion có `spell_min = 4`: chỉ spell ≥ 4 thành công.
// // # * Potion có `spell_min = 2`: chỉ spell ≥ 2 thành công.

// // # Ta **tăng đếm tại chỉ số `spell_min`** → nghĩa là mọi spell từ giá trị
// đó trở lên **được cộng thêm 1** potion thành công.

// // # ---

// // # #### 🔄 Sau đó dùng prefix sum:

// // # * `smaller_count[i]` = tổng số potion có thể ghép thành công với spell ≤
// i.

// // # ---

// // # #### 🧮 Ví dụ cụ thể

// // # ```
// // # spells = [3, 5, 10]
// // # potions = [2, 3, 5]
// // # success = 10
// // # ```

// // # **Bước 1:**

// // # | Potion | success // num | Làm tròn | spell_min | Ghi vào mảng
// smaller_count |
// // # | :----- | :------------- | :------- | :-------- |
// :------------------------- |
// // # | 2 | 5 | +1 | 6 | smaller_count[6] += 1 |
// // # | 3 | 3 | +1 | 4 | smaller_count[4] += 1 |
// // # | 5 | 2 | 0 | 2 | smaller_count[2] += 1 |

// // # **smaller_count (ban đầu):**

// // # ```
// // # [0, 0, 1, 0, 1, 0, 1]
// // # ```

// // # **Bước 2: Tính tổng tích lũy**

// // # ```
// // # [0, 0, 1, 1, 2, 2, 3]
// // # ```

// // # **Bước 3: Lấy kết quả cho từng spell**

// // # | Spell | smaller_count[spell] | Kết quả |
// // # | :---- | :------------------- | :---------- |
// // # | 3 | 1 | ✅ 1 potion |
// // # | 5 | 2 | ✅ 2 potions |
// // # | 10 | 3 | ✅ 3 potions |

// // # → **Output:** `[1, 2, 3]`

// // # ---

// // # ### ⚖️ Độ phức tạp

// // # * **Thời gian:** `O(max(spells) + len(potions))`
// // # (Nhanh hơn `O(n log n)` của cách binary search nếu giá trị spell nhỏ)
// // # * **Không gian:** `O(max(spell))`

// // # ---

// // # 👉 **Tóm lại**:
// // # Thuật toán này dùng **mảng đếm + prefix sum** để thay thế cho **binary
// search**, giúp tối ưu khi `max(spells)` nhỏ hơn số lượng phần tử.

// // # ---

// // # Bạn có muốn mình vẽ **biểu đồ minh họa (trục spell và số potion thành
// công)** để dễ hình dung hơn không?

// // # Dĩ nhiên 😊 — đây là **giải thích chi tiết đề bài LeetCode 2300:
// “Successful Pairs of Spells and Potions”**

// // # ---

// // # ### 🧩 **Đề bài**

// // # Bạn được cho:

// // # * Một mảng **spells** gồm `n` phần tử (mỗi phần tử là *sức mạnh phép
// thuật*).
// // # * Một mảng **potions** gồm `m` phần tử (mỗi phần tử là *sức mạnh thuốc
// tiên*).
// // # * Một số nguyên **success** — đây là *mức ngưỡng thành công*.

// // # Một cặp `(spell, potion)` được gọi là **thành công (successful)** nếu:

// // # ```
// // # spell * potion >= success
// // # ```

// // # ---

// // # ### 🎯 **Yêu cầu**

// // # Với **mỗi spell** trong mảng `spells`, hãy **đếm** xem có bao nhiêu
// potion trong `potions` sao cho cặp đó là *thành công*.

// // # Trả về một mảng `ans` có cùng độ dài với `spells`, trong đó:

// // # ```
// // # ans[i] = số lượng potion sao cho spells[i] * potion >= success
// // # ```

// // # ---

// // # ### 💡 **Ví dụ**

// // # #### 🧮 Input:

// // # ```python
// // # spells = [5, 1, 3]
// // # potions = [1, 2, 3, 4, 5]
// // # success = 7
// // # ```

// // # #### ✅ Output:

// // # ```python
// // # [4, 0, 3]
// // # ```

// // # #### 🔍 Giải thích:

// // # * Spell = 5:

// // # * 5×1=5 ❌
// // # * 5×2=10 ✅
// // # * 5×3=15 ✅
// // # * 5×4=20 ✅
// // # * 5×5=25 ✅
// // # → Có **4 potion thành công**

// // # * Spell = 1:

// // # * 1×1=1 ❌
// // # * 1×2=2 ❌
// // # * 1×3=3 ❌
// // # * 1×4=4 ❌
// // # * 1×5=5 ❌
// // # → Có **0 potion thành công**

// // # * Spell = 3:

// // # * 3×1=3 ❌
// // # * 3×2=6 ❌
// // # * 3×3=9 ✅
// // # * 3×4=12 ✅
// // # * 3×5=15 ✅
// // # → Có **3 potion thành công**

// // # ➡️ Kết quả: `[4, 0, 3]`

// // # ---

// // # ### ⚙️ **Tư duy giải**

// // # 1. Sắp xếp `potions` tăng dần.
// // # 2. Với mỗi `spell`, tìm potion nhỏ nhất sao cho `spell * potion >=
// success`.
// // # 3. Dùng **binary search** (tìm kiếm nhị phân) để nhanh hơn (O(n log m)).

// // # Ví dụ:

// // # ```python
// // # import bisect

// // # def successfulPairs(spells, potions, success):
// // # potions.sort()
// // # m = len(potions)
// // # res = []

// // # for s in spells:
// // # # Tìm vị trí đầu tiên trong potions sao cho s * potions[idx] >= success
// // # idx = bisect.bisect_left(potions, success / s)
// // # res.append(m - idx)

// // # return res
// // # ```

// // # ---

// // # Nếu bạn muốn, mình có thể **giải thích chi tiết từng dòng code** hoặc
// **vẽ bảng mô phỏng chạy ví dụ** cho bài này — bạn muốn phần nào trước?

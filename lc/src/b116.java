// // # Count Mentions Per User(12/12/2025)
// // # Dưới đây là **giải thích đề bài "Count Mentions Per User"** (LeetCode 3591).
// // # Dưới đây là **giải thích thuật toán** + **code đã được thêm chú thích đầy đủ, cực dễ hiểu**.
// Bạn ơi,thuật toán này**quá nâng cao**và dùng kỹ thuật**event compression**,**mã hoá thời gian+loại event vào 1 số**,**line sweep**,không còn là giải bình thường nữa.Mình sẽ giải thích theo cách**dễ hiểu nhất có thể**,kèm theo**code đã thêm chú thích đầy đủ**,và**hàm main dùng Scanner**như bạn yêu cầu.

// ---

// #⭐Ý TƯỞNG LỚN CỦA THUẬT TOÁN

// Thay vì xử lý theo cách đơn giản(sort rồi duyệt),lời giải này tối ưu bằng cách:

// ###✔**Biến mọi sự kiện thành 1 số duy nhất dạng:**

// ```encoded=timestamp*105+flag```

// Trong đó`flag`là loại sự kiện:

// |Loại event|flag|Ý nghĩa||-----------------------------|-----------------|--------------------||user offline(timestamp+60)|0|User bắt đầu offline||user online(ENTER)|id+1|id=user online||MESSAGE HERE|104(=MOD-1)|104 biểu diễn HERE||MESSAGE ALL|không đưa vào m[]|chỉ tăng biến all|

// MOD=105 được chọn vì trong 1 timestamp ta cần mã hoá tối đa 104 loại event.

// ---

// #⭐Sau đó:

// ###✔Ta sort toàn bộ các event đã mã hoá

// →xử lý theo đúng thứ tự thời gian+loại sự kiện.

// ---

// #⭐Khi gặp từng loại:

// ###1 ️⃣**User online→lưu user vào danh sách online**

// ###2 ️⃣**offline event→xoá user khỏi online**

// ###3 ️⃣**MESSAGE HERE→cộng 1 cho tất cả user đang online**

// ###4 ️⃣**MESSAGE ALL→cộng 1 cho tất cả user(xử lý cuối cùng)**

// ---

// #⭐Lý do viết phức tạp như vậy?

// →Vì code này là**giải tối ưu hoá cực mạnh**,không dùng List,không dùng String split,không đối chiếu thời gian nhiều lần.→Mọi thứ được biến thành 1 mảng số,sort 1 lần,chạy cực nhanh.

// ---

// #⭐Full Code Java+Giải thích từng dòng(rất rõ)

// ```java

import java.util.*;

public class b116 {
    static Scanner sc = new Scanner(System.in);
    static final int MOD = 105; // modulo để encode event
    static final int MOD1 = MOD - 1; // 104 – mã hóa cho MESSAGE HERE

    static final int[] m = new int[200]; // lưu event đã mã hoá
    static final int[] offline = new int[200]; // lưu user đang online

    public static void main(String[] args) {

        int n = sc.nextInt(); // số user
        int e = sc.nextInt(); // số event
        sc.nextLine();

        List<List<String>> events = new ArrayList<>();

        // Nhập các event
        for (int i = 0; i < e; i++) {
            String line = sc.nextLine();
            String[] parts = line.split(" ");
            events.add(Arrays.asList(parts));
        }

        int[] res = countMentions(n, events);

        for (int x : res)
            System.out.print(x + " ");
    }

    public static int[] countMentions(int numberOfUsers, List<List<String>> events) {

        int[] r = new int[numberOfUsers]; // kết quả
        int all = 0; // đếm số MESSAGE ALL
        int mlen = 0; // số event được mã hoá

        // -------------------------------------------------
        // B1: chuyển các event thành dạng mã hoá
        // -------------------------------------------------
        for (var event : events) {

            int ts = parseInt(event.get(1)); // timestamp
            String ids = event.get(2);

            if (event.get(0).equals("MESSAGE")) {

                // MESSAGE ALL
                if (ids.equals("ALL")) {
                    all++;

                    // MESSAGE HERE
                } else if (ids.equals("HERE")) {
                    // mã hóa: ts*105 + 104
                    m[mlen++] = ts * MOD + MOD1;

                    // MESSAGE id list
                } else {
                    // ids dạng "id3 id7"
                    for (int i = 0;;) {
                        int nextSpace = ids.indexOf(' ', i);
                        if (nextSpace < 0) {
                            r[parseInt(ids, i + 2, ids.length())]++;
                            break;
                        }
                        r[parseInt(ids, i + 2, nextSpace)]++;
                        i = nextSpace + 1;
                    }
                }

                // ---------------------------------------------
                // EVENT = ENTER
                // ---------------------------------------------
            } else {
                int id = parseInt(ids);

                // user online → mã hóa: ts*105 + (id + 1)
                m[mlen++] = ts * MOD + id + 1;

                // thời điểm user offline: (ts+60)*105 + 0
                m[mlen++] = (ts + 60) * MOD;
            }
        }

        // -------------------------------------------------
        // B2: sort tất cả event theo timestamp & loại
        // -------------------------------------------------
        Arrays.sort(m, 0, mlen);

        int os = 0; // online start pointer
        int oe = 0; // online end pointer

        // -------------------------------------------------
        // B3: xử lý event đã sort
        // -------------------------------------------------
        for (int i = 0; i < mlen; i++) {

            int flag = m[i] % MOD; // lấy loại event

            if (flag == 0) {
                // user đi offline → pointer online tăng
                os++;

            } else if (flag == MOD1) {
                // MESSAGE HERE
                all++;

                // tạo hiệu chỉnh: những user offline trong khoảng này không tính HERE
                for (int j = os; j < oe; j++) {
                    r[offline[j]]--;
                }

            } else {
                // user online (flag = id+1)
                offline[oe++] = flag - 1;
            }
        }

        // MESSAGE ALL → cộng vào tất cả user
        if (all > 0) {
            for (int i = 0; i < numberOfUsers; i++) {
                r[i] += all;
            }
        }

        return r;
    }

    // parse integer nhanh (không dùng Integer.parseInt)
    private static final int parseInt(String s) {
        return parseInt(s, 0, s.length());
    }

    public static final int parseInt(String ids, int start, int end) {
        int r = ids.charAt(start) - '0';
        for (int i = start + 1; i < end; i++) {
            r = r * 10 + ids.charAt(i) - '0';
        }
        return r;
    }
}

// #⭐

// Nếu bạn muốn:

// ✔
// giải thích
// từng dòng 1(full 100%)✔
// minh hoạ
// bằng hình✔
// viết lại
// phiên bản**
// dễ hiểu hơn**✔
// viết lại
// bằng Python

// Chỉ cần
// bảo mình!

// # ---

// # # ✅ **Giải thích đề bài (ngắn gọn – dễ hiểu)**

// # Bạn có:

// # * `numberOfUsers`: số user, từ **0 → numberOfUsers - 1**
// # * `events`: danh sách sự kiện, mỗi sự kiện có dạng:

// # ```
// # ["ENTER", timestamp, userId]
// # ["MESSAGE", timestamp, "ALL"]
// # ["MESSAGE", timestamp, "HERE"]
// # ["MESSAGE", timestamp, "id1 id2 ..."]
// # ```

// # ### ✔ Nhiệm vụ:

// # Đếm xem mỗi user được *mention* bao nhiêu lần.

// # ---

// # # 🧠 **Quy tắc mention:**

// # ### ✔ `"MESSAGE" ... "ALL"`

// # → Tất cả user đều được +1 mention.

// # ### ✔ `"MESSAGE" ... "HERE"`

// # → Chỉ những user **đang online tại timestamp đó** mới +1.
// # User online nếu:

// # ```
// # ENTER tại t → online từ t đến t+60
// # ```

// # ### ✔ `"MESSAGE" ... "id3 id7"`

// # → Mention trực tiếp từng ID trong danh sách.

// ### ✔ `"ENTER"` event

// # → Đánh dấu user online trong 60 giây.

// # ---

// # # 🔥 **Vì sao phải sort events trước?**

// # Vì events có thể **không theo đúng thứ tự thời gian**, nên cần:

// # ```
// # sort theo timestamp tăng dần
// # nếu cùng timestamp thì MESSAGE xử lý sau ENTER
// # ```

// # Mã sort:

// # ```python
// # events.sort(key=lambda x: (int(x[1]), x[0] == "MESSAGE"))
// # ```

// # ---

// # # ✅ **Code Python đã thêm chú thích cực chi tiết**

// # ```python
// from typing import List
// class Solution:
// def countMentions(self, numberOfUsers: int, events: List[List[str]]) ->
// List[int]:

// # mentions[i] = số lần user i được mention
// mentions = [0] * numberOfUsers

// # onlineUsersTime[i] = thời điểm user i hết online (t + 60)
// onlineUsersTime = [0] * numberOfUsers

// # Sắp xếp sự kiện theo:
// # 1. timestamp tăng dần
// # 2. nếu cùng timestamp thì ENTER trước MESSAGE
// events.sort(key=lambda x: (int(x[1]), x[0] == "MESSAGE"))

// # Duyệt từng event theo thời gian
// for msg, when, who in events:
// time = int(when)

// # ==========================
// # XỬ LÝ MESSAGE
// # ==========================
// if msg == "MESSAGE":

// # Trường hợp "MESSAGE ALL"
// if who == "ALL":
// for i in range(numberOfUsers):
// mentions[i] += 1

// # Trường hợp "MESSAGE HERE"
// elif who == "HERE":
// for i in range(numberOfUsers):
// # user đang online nếu time >= onlineUsersTime[i]
// if time >= onlineUsersTime[i]:
// mentions[i] += 1

// # Trường hợp direct mention: "id1 id2 ..."
// else:
// # ví dụ: who = "id3 id7" → replace("id","") → "3 7"
// for u in who.replace("id", "").split():
// mentions[int(u)] += 1

// # ==========================
// # XỬ LÝ ENTER
// # ==========================
// else:
// # User online trong vòng 60 giây
// # ENTER lúc t → online đến t + 60
// onlineUsersTime[int(who)] = time + 60

// return mentions

// # 📌 **Giải thích thuật toán (ý tưởng chính)**

// # 1. **Sort tất cả sự kiện theo thời gian** để xử lý đúng thứ tự.
// # 2. Với mỗi `"ENTER"`, đánh dấu user online đến `time + 60`.
// # 3. Khi gặp `"MESSAGE"`:

// # * `"ALL"` → tất cả user +1.
// # * `"HERE"` → chỉ user đang online +1.
// # * `"idX idY"` → các user được nhắc tên trực tiếp +1.
// # 4. Trả về danh sách mentions cho từng user.

// # ---

// # # 🎉 Nếu bạn muốn:

// # * Viết lại bằng **Java**
// # * Giải thích bằng hình minh họa
// # * Thêm **test case** minh hoạ từng loại event

// # 👉 Chỉ cần bảo mình, mình làm ngay cho bạn!

// # ---

// # # 📌 **📝 Đề bài yêu cầu gì?**

// # Bạn được cho:

// # * Một danh sách các **tin nhắn** (messages).
// # * Mỗi tin nhắn là một chuỗi ký tự, có thể chứa các **mention** dạng:
// # **"@username"**

// # Nhiệm vụ của bạn:

// # ### 👉 **Đếm số lần mỗi user được mention**, và trả về danh sách kết quả
// dạng:

// # ```
// # [userName, count]
// # ```

// # theo thứ tự **tăng dần theo userName** (alphabetical order).

// # ---

// # # 📌 **Mentions hợp lệ theo quy tắc:**

// # Một mention có dạng:

// # ```
// # @<username>
// # ```

// # Trong đó:

// # ### ✔ username phải thỏa:

// # 1. **Chỉ chứa chữ cái thường (a-z)**
// # 2. **Độ dài 1–15**
// # 3. **Ngay trước @ phải là dấu cách hoặc là ký tự đầu tiên chuỗi**
// # 4. **Ngay sau username phải là khoảng trắng, dấu chấm, dấu phẩy hoặc hết
// chuỗi**

// # Ví dụ hợp lệ:

// # * `"@alice"`
// # * `"hello @bob."`
// # * `"@john_doe"` ❌ (không hợp lệ vì có `_`)

// # Không hợp lệ:

// # * `"abc@alice"` (trước @ không phải khoảng trắng)
// # * `"@Alice"` (A viết hoa)
// # * `"@@bob"` (username rỗng)
// # * `"@bob123"` (chứa số)

// # ---

// # # 📌 **Kết quả mong muốn**

// # Input:

// # ```
// # ["hi @alice and @bob", "ping @alice.", "no mention here"]
// # ```

// # Output:

// # ```
// # [
// # ["alice", 2],
// # ["bob", 1]
// # ]
// # ```

// # ---

// # # 📌 **Tóm tắt lại đề bài thật dễ hiểu**

// # Bạn chỉ cần:

// # ### 🔍 1. Duyệt từng message

// # ### 🔧 2. Tìm tất cả các @username hợp lệ

// # ### 🧮 3. Đếm số lần xuất hiện của mỗi username

// # ### 🗂️ 4. Trả về danh sách `[username, count]`, sort theo tên

// # ---

// # # Nếu bạn cần:

// # * Giải thích thuật toán mẫu
// # * Thêm code Java / Python kèm chú thích
// # * Minh họa từng trường hợp hợp lệ / không hợp lệ

// # 👉 Chỉ cần bảo mình, mình viết cho bạn!

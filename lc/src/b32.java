
// 1733.Minimum Number of People to Teach(10/09/2025)
import java.util.*;

public class b32 {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Nhập tổng số ngôn ngữ (n): ");
        int n = sc.nextInt();

        // Nhập thông tin ngôn ngữ của mỗi người
        int totalPeople = sc.nextInt();
        int[][] languages = new int[totalPeople][];
        sc.nextLine(); // Bỏ qua dòng mới còn lại
        for (int i = 0; i < totalPeople; i++) {
            System.out.print((i + 1));
            String[] langs = sc.nextLine().split(" ");
            languages[i] = new int[langs.length];
            for (int j = 0; j < langs.length; j++) {
                languages[i][j] = Integer.parseInt(langs[j]);
            }
        }

        int totalFriendships = sc.nextInt();
        int[][] friendships = new int[totalFriendships][2];
        // Số **`2`** trong `new int[totalFriendships][2]` đại diện cho kích thước của
        // chiều thứ hai của mảng, hay nói cách khác là số cột.

        // Trong bài toán này, `friendships` là một mảng hai chiều. Mỗi phần tử trong
        // mảng này là một mảng con có 2 phần tử, đại diện cho một cặp bạn bè.

        // * `totalFriendships`: Tổng số cặp bạn bè bạn có. Đây là số hàng của mảng.
        // * `[2]`: Mỗi hàng sẽ là một mảng con có **đúng 2** phần tử, lưu ID của hai
        // người bạn trong một cặp.

        // Ví dụ:

        // Nếu bạn có 3 cặp bạn bè, mảng `friendships` sẽ được khai báo là `new
        // int[3][2]`, và nó có thể trông như thế này:

        // * `friendships[0] = {1, 2}` (Cặp bạn bè đầu tiên)
        // * `friendships[1] = {3, 4}` (Cặp bạn bè thứ hai)
        // * `friendships[2] = {1, 5}` (Cặp bạn bè thứ ba)

        // Do đó, số `2` là cố định vì mỗi mối quan hệ bạn bè luôn chỉ bao gồm hai
        // người.
        for (int i = 0; i < totalFriendships; i++) {
            System.out.print((i + 1));
            friendships[i][0] = sc.nextInt();
            friendships[i][1] = sc.nextInt();
        }

        int result = minimumTeachings(n, languages, friendships);

        System.out.println(result);

        sc.close();
    }

    public static int minimumTeachings(int n, int[][] L, int[][] F) {
        // Biểu diễn các ngôn ngữ mà mỗi người biết bằng một BitSet.
        // BitSet[i] sẽ biểu thị các ngôn ngữ của người thứ (i+1).
        BitSet[] bit = new BitSet[L.length];

        // Khởi tạo các đối tượng BitSet trong mảng.
        Arrays.setAll(bit, o -> new BitSet(n + 1));

        // Chuyển danh sách ngôn ngữ của mỗi người sang BitSet.
        // Nếu người i biết ngôn ngữ l, set bit l trong BitSet của họ.
        for (int i = 0; i < L.length; i++) {
            for (int l : L[i]) {
                bit[i].set(l);
            }
        }

        // Tập hợp những người cần được dạy ngôn ngữ.
        Set<Integer> teach = new HashSet<>();

        // Duyệt qua các cặp bạn bè.
        for (int[] f : F) {
            // Tạo bản sao BitSet của người bạn đầu tiên.
            BitSet t = (BitSet) bit[f[0] - 1].clone();

            // Thực hiện phép AND bitwise để tìm các ngôn ngữ chung.
            // Nếu có ngôn ngữ chung, bit tương ứng sẽ là 1.
            t.and(bit[f[1] - 1]);

            // Nếu không có ngôn ngữ chung (t là tập hợp rỗng), họ không thể giao tiếp.
            if (t.isEmpty()) {
                // Thêm cả hai người vào tập hợp những người cần dạy.
                // Trừ 1 vì id người bắt đầu từ 1, trong khi mảng BitSet bắt đầu từ 0.
                teach.add(f[0] - 1);
                teach.add(f[1] - 1);
            }
        }

        // Mảng để đếm số người trong nhóm cần dạy đã biết mỗi ngôn ngữ.
        int[] count = new int[n + 1];

        // Duyệt qua những người cần dạy.
        for (int person : teach) {
            // Duyệt qua các ngôn ngữ mà người đó biết.
            for (int l : L[person]) {
                // Tăng bộ đếm cho ngôn ngữ đó.
                count[l]++;
            }
        }

        // Tìm số người lớn nhất đã biết một ngôn ngữ trong nhóm cần dạy.
        // Đây là ngôn ngữ tối ưu để dạy (được dạy cho ít người nhất).
        int maxKnown = 0;
        if (!teach.isEmpty()) {
            maxKnown = Arrays.stream(count).max().getAsInt();
        }

        // Số người cần dạy tối thiểu bằng (tổng số người cần dạy) - (số người đã biết
        // ngôn ngữ tối ưu).
        return teach.size() - maxKnown;
    }
}

// ### Giải thích Đề bài: Minimum Number of People to Teach

// Đề bài **1733. Minimum Number of People to Teach** yêu cầu bạn tìm số lượng
// người ít nhất cần phải dạy một ngôn ngữ để giải quyết vấn đề giao tiếp trong
// một nhóm bạn.

// Bạn được cung cấp ba thông tin:

// 1. **`n`**: Tổng số người.
// 2. **`languages`**: Một mảng (hoặc danh sách) các ngôn ngữ mà mỗi người biết.
// `languages[i]` là một danh sách các ngôn ngữ mà người `i+1` biết.
// 3. **`friendships`**: Một mảng (hoặc danh sách) các cặp bạn bè. Mỗi cặp `[u,
// v]` biểu thị rằng người `u` và người `v` là bạn.

// #### Vấn đề cần giải quyết

// * Một cặp bạn bè `[u, v]` có thể giao tiếp với nhau nếu họ **biết chung ít
// nhất một ngôn ngữ**.
// * Nếu một cặp bạn bè không thể giao tiếp, bạn cần phải dạy cho họ một ngôn
// ngữ mới.
// * Để dạy một ngôn ngữ, bạn phải dạy nó cho **tất cả những người** cần học.

// #### Yêu cầu

// Bạn phải tìm ra một ngôn ngữ duy nhất để dạy, sao cho:

// * Ngôn ngữ đó được dạy cho **số lượng người ít nhất**.
// * Việc dạy ngôn ngữ này sẽ cho phép **tất cả các cặp bạn bè** không thể giao
// tiếp trước đó, giờ đây có thể giao tiếp.

// Nói cách khác, bạn cần tìm một ngôn ngữ **tối ưu** để dạy. Ngôn ngữ tối ưu là
// ngôn ngữ mà khi bạn dạy nó cho tất cả những người trong nhóm bạn bè không
// giao tiếp được, tổng số người được dạy là ít nhất.

// #### Tóm tắt các bước giải quyết

// 1. **Tìm các cặp bạn bè không giao tiếp được**:
// * Duyệt qua danh sách `friendships`.
// * Với mỗi cặp `[u, v]`, kiểm tra xem họ có biết chung ngôn ngữ nào không.
// * Thu thập tất cả những người thuộc các cặp không giao tiếp được vào một tập
// hợp (set), vì một người có thể là thành viên của nhiều cặp như vậy.

// 2. **Đếm số người cần dạy cho mỗi ngôn ngữ**:
// * Duyệt qua tất cả các ngôn ngữ có sẵn.
// * Đối với mỗi ngôn ngữ, đếm xem có bao nhiêu người trong tập hợp những người
// không giao tiếp được biết ngôn ngữ đó.
// * Ví dụ: Nếu ngôn ngữ 1 được biết bởi 3 người trong nhóm cần dạy, thì bạn cần
// dạy nó cho `(tổng số người cần dạy) - 3` người.

// 3. **Tìm ngôn ngữ tối ưu**:
// * Ngôn ngữ tối ưu là ngôn ngữ mà số người cần dạy nó là ít nhất.
// * Tức là, ngôn ngữ đó được biết bởi số lượng người lớn nhất trong nhóm những
// người không giao tiếp được.
// * Số người tối thiểu cần dạy = `(tổng số người trong tập hợp) - (số người tối
// đa đã biết một ngôn ngữ)`.

// ---

// **Lưu ý quan trọng**:
// * Nếu một cặp bạn bè đã có thể giao tiếp, họ không cần phải được dạy thêm
// ngôn ngữ.
// * Nếu một người đã biết một ngôn ngữ, họ không cần phải được dạy lại ngôn ngữ
// đó.
// * Bạn chỉ có thể dạy **một ngôn ngữ duy nhất** cho tất cả những người cần
// học.

// Đây là
// giải thích
// chi tiết
// về thuật
// toán trong
// đoạn code
// của bạn.
// Thuật toán
// này rất
// hiệu quả
// vì nó
// sử dụng lớp**`BitSet`**
// của Java
// để tối
// ưu hóa
// việc kiểm
// tra các
// ngôn ngữ
// chung giữa
// hai người.

// ---

// ###
// Phân tích
// Thuật toán

// Thuật toán
// của bạn
// giải quyết
// bài toán
// theo ba
// bước chính, mỗi
// bước đều
// được tối
// ưu hóa:

// ####1.
// Biểu diễn
// ngôn ngữ
// bằng BitSet

// ***Mục đích**:
// Chuyển danh
// sách các
// ngôn ngữ
// của mỗi
// người thành
// một cấu
// trúc dữ
// liệu hiệu
// quả để
// thực hiện
// các phép
// toán trên
// tập hợp.***
// Cách làm**:*
// Bạn tạo
// một mảng`BitSet`
// có kích
// thước bằng
// số người.*Mỗi`BitSet`
// trong mảng
// đại diện
// cho một
// người. Ví dụ,`bit[i]`
// là các
// ngôn ngữ
// mà người thứ`i+1`biết.*
// Với mỗi
// ngôn ngữ`l`
// mà một
// người biết, bạn

// bật (set) bit thứ `l` trong `BitSet` của họ.

// Cách tiếp cận này rất hiệu quả về bộ nhớ và thời gian, đặc biệt khi số lượng

// ngôn ngữ (`n`) không quá lớn.

// #### 2. Tìm những người cần dạy

// * **Mục đích**: Xác định những người thuộc các cặp bạn bè không thể giao tiếp
// được.
// * **Cách làm**:
// * Bạn duyệt qua danh sách các cặp bạn bè `F`.
// * Với mỗi cặp `[f[0], f[1]]`, bạn thực hiện phép toán **AND bitwise** trên
// hai `BitSet` tương ứng của họ.
// * `t.and(bit[f[1] - 1])`: Phép toán này tạo ra một `BitSet` mới (`t`) chỉ
// chứa những bit được bật ở cả hai `BitSet` ban đầu. Nói cách khác, nó tìm ra
// các ngôn ngữ chung mà cả hai người đều biết.
// * `if (t.isEmpty())`: Nếu `BitSet` kết quả rỗng (`isEmpty()`), có nghĩa là họ
// không có ngôn ngữ chung và không thể giao tiếp.
// * `teach.add(...)`: Trong trường hợp này, bạn thêm cả hai người vào tập hợp
// `teach`. Sử dụng `HashSet` đảm bảo rằng mỗi người chỉ được thêm vào một lần
// duy nhất, ngay cả khi họ là thành viên của nhiều cặp bạn bè không giao tiếp
// được.

// #### 3. Tìm ngôn ngữ tối ưu và tính toán kết quả

// * **Mục đích**: Tìm một ngôn ngữ tối ưu để dạy và tính số người tối thiểu cần
// dạy.
// * **Cách làm**:
// * Bạn tạo một mảng `count` để đếm số người trong tập hợp `teach` đã biết mỗi
// ngôn ngữ.
// * Bạn duyệt qua từng người trong tập hợp `teach`, sau đó duyệt qua từng ngôn
// ngữ mà họ biết.
// * `count[l]++`: Tăng bộ đếm cho ngôn ngữ `l` mà người đó biết.
// * `maxKnown = Arrays.stream(count).max().getAsInt()`: Bạn tìm giá trị lớn
// nhất trong mảng `count`. Giá trị này chính là số người lớn nhất trong nhóm
// `teach` đã biết một ngôn ngữ cụ thể. Ngôn ngữ tương ứng với giá trị này là
// ngôn ngữ tối ưu để dạy, vì bạn sẽ cần dạy nó cho ít người nhất.
// * `return teach.size() - maxKnown`: Số người tối thiểu cần dạy bằng tổng số
// người trong nhóm cần dạy trừ đi số người đã biết ngôn ngữ tối ưu.

// ### Tóm tắt

// Thuật toán của bạn rất tối ưu vì nó sử dụng **`BitSet`** để biểu diễn tập hợp
// ngôn ngữ, cho phép các phép toán tìm giao điểm cực kỳ nhanh. Đây là một cách
// tiếp cận nâng cao so với việc sử dụng `HashSet` và lặp lại. Toàn bộ thuật
// toán được chia thành các bước logic rõ ràng, dẫn đến một giải pháp hiệu quả
// về cả thời gian và bộ nhớ.
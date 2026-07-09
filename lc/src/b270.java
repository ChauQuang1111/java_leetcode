// “Path Existence Queries in a Graph I” (09/07/2026)
// thường là bài hỏi: trong một đồ thị, có tồn tại đường đi giữa hai đỉnh được hỏi hay không?



// Hiểu đề bài

// Cho một đồ thị gồm:



// n đỉnh (vertices/nodes), thường được đánh số từ 0 đến n-1 hoặc 1 đến n.

// m cạnh (edges) nối giữa các đỉnh.

// q truy vấn (queries), mỗi truy vấn gồm hai đỉnh u và v.

// Nhiệm vụ của mỗi truy vấn là kiểm tra:

// Có tồn tại một đường đi từ u đến v hay không?

// Nếu có thể đi từ u đến v qua một hoặc nhiều cạnh, ta trả lời:



// YES, True, hoặc 1 (tùy đề bài yêu cầu).

// Nếu không thể đi được, trả lời:



// NO, False, hoặc 0.

// Đường đi là gì?

// Đường đi là một dãy các đỉnh liên tiếp sao cho mỗi cặp đỉnh liên tiếp đều có cạnh nối giữa chúng.

// Ví dụ đồ thị:



// Giữa 1 và 3: có đường đi 1 → 2 → 3, nên trả lời YES.

// Giữa 1 và 5: không có cách nào đi sang nhóm 4–5, nên trả lời NO.

// Ví dụ minh họa

// Input:

// Phân tích:



// (1,3): đi được qua 1 → 2 → 3 → YES.

// (1,5): không có đường nối từ nhóm 1-2-3 sang nhóm 4-5 → NO.

// (4,5): có cạnh nối trực tiếp → YES.

// Output:



// Ý tưởng giải

// Nếu số truy vấn ít

// Với mỗi truy vấn (u, v), ta có thể:



// Dùng DFS hoặc BFS bắt đầu từ u.

// Nếu duyệt được tới v → YES.

// Nếu duyệt hết mà chưa tới v → NO.

// Nếu số truy vấn nhiều

// Ta nên dùng DSU (Disjoint Set Union / Union-Find):



// Ban đầu, mỗi đỉnh là một tập riêng.

// Với mỗi cạnh (u, v), gộp u và v vào cùng một tập.

// Khi trả lời truy vấn (u, v), chỉ cần kiểm tra u và v có cùng root hay không.

// Nếu cùng root → YES, khác root → NO.



// Tóm tắt

// Bài hỏi tính liên thông giữa hai đỉnh trong đồ thị.

// Hai đỉnh có đường đi giữa chúng nếu chúng nằm trong cùng một thành phần liên thông.

// Giải bằng DFS/BFS nếu truy vấn ít.

// Giải bằng DSU nếu có nhiều truy vấn cần trả lời nhanh.

// Nếu bạn gửi nguyên văn đề bài hoặc ví dụ input/output cụ thể, mình có thể giải thích chi tiết theo đúng format của đề đó.

// Code của bạn đang giải bài Path Existence Queries in a Graph I bằng cách tiền xử lý các thành phần liên thông theo chỉ số, thay vì tạo đồ thị rồi chạy BFS/DFS cho từng truy vấn.



// Ý tưởng thuật toán

// Giả sử nums đã được sắp xếp không giảm. Ta xem mỗi vị trí i là một đỉnh. Có thể đi từ vị trí i sang i+1 nếu:

// nums[i+1]−nums[i]≤maxDiffnums[i+1] - nums[i] \le maxDiffnums[i+1]−nums[i]≤maxDiff

// Vì mảng đã sắp xếp, nếu khoảng cách giữa hai phần tử liên tiếp vượt quá maxDiff, thì mọi phần tử bên trái không thể nối sang bên phải qua chuỗi các cạnh hợp lệ.

// Do đó, ta chia mảng thành các đoạn liên thông:



// Nếu nums[i] - nums[i-1] <= maxDiff, vị trí i thuộc cùng thành phần với i-1.

// Nếu nums[i] - nums[i-1] > maxDiff, vị trí i bắt đầu một thành phần mới.

// Mảng comp[i] lưu ID của thành phần liên thông chứa vị trí i.

// Trả lời truy vấn

// Với mỗi truy vấn [source, dest]:



// Nếu source == dest thì chắc chắn có đường đi.

// Nếu comp[source] == comp[dest] thì hai đỉnh nằm trong cùng một thành phần liên thông, nên có đường đi.

// Ngược lại, không có đường đi.

// Code đã thêm chú thích

// Ví dụ minh họa

// 2 - 1 = 1 <= 2 → cùng thành phần.

// 4 - 2 = 2 <= 2 → cùng thành phần.

// 7 - 4 = 3 > 2 → tách thành phần mới.

// 8 - 7 = 1 <= 2 → cùng thành phần với 7.

// Nghĩa là các vị trí 0,1,2 cùng nhóm, còn 3,4 cùng nhóm khác.



// Độ phức tạp

// Tiền xử lý mảng comp: O(n).

// Trả lời mỗi truy vấn: O(1).

// Tổng thời gian: O(n + q), với q là số truy vấn.

// Bộ nhớ phụ: O(n).

// Chương trình đầy đủ có hàm main dùng Scanner

// Ví dụ nhập

// Kết quả

// Ở ví dụ này:



// 0 2 → cùng thành phần → true.

// 1 4 → khác thành phần → false.

// 3 4 → cùng thành phần → true.




import java.util.*;

public class b270{

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
     int n = sc.nextInt();

        // 2. Nhập mảng nums (Lưu ý: bài toán này thường mặc định mảng nums đã được sắp xếp tăng dần)
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        // 3. Nhập giá trị chênh lệch tối đa maxDiff
        System.out.print("Nhập khoảng cách tối đa maxDiff: ");
        int maxDiff = sc.nextInt();

        // 4. Nhập số lượng truy vấn
        System.out.print("Nhập số lượng truy vấn q: ");
        int q = sc.nextInt();

        // 5. Nhập các cặp truy vấn (source, dest)
        int[][] queries = new int[q][2];
        for (int i = 0; i < q; i++) {
            queries[i][0] = sc.nextInt(); // Điểm gốc
            queries[i][1] = sc.nextInt(); // Điểm đích
        }

        
        boolean[] results = pathExistenceQueries(n, nums, maxDiff, queries);

        // 6. In kết quả ra màn hình
        for (int i = 0; i < results.length; i++) {
            System.out.println("Truy vấn " + i + " (" + queries[i][0] + " -> " + queries[i][1] + "): " + results[i]);
        }

        sc.close(); 
    }
public static boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // Mảng lưu kết quả cho từng truy vấn
        boolean[] res = new boolean[queries.length];

        // Mảng comp để đánh dấu "thành phần liên thông" của từng phần tử.
        // Nếu hai phần tử có cùng giá trị comp, chúng thuộc cùng một nhóm và có đường đi đến nhau.
        int[] comp = new int[n];

        int com = 0; // Biến đếm nhãn nhóm
        comp[0] = com; // Phần tử đầu tiên thuộc nhóm 0

        // Duyệt qua mảng nums để phân nhóm dựa trên điều kiện khoảng cách (maxDiff)
        for (int i = 1; i < n; i++) {
            // Nếu khoảng cách giữa 2 phần tử liền kề lớn hơn maxDiff, 
            // "đường đi" bị ngắt quãng, ta tạo một nhóm mới (tăng com)
            if (nums[i] - nums[i - 1] > maxDiff) {
                com++;
            }
            // Gán nhãn nhóm cho phần tử hiện tại
            comp[i] = com;
        }

        // Xử lý từng truy vấn
        for (int i = 0; i < queries.length; i++) {
            int source = queries[i][0]; // Điểm bắt đầu
            int dest = queries[i][1];   // Điểm kết thúc

            // Nếu điểm đầu trùng điểm cuối, hoặc hai điểm thuộc cùng một nhóm liên thông
            if ((source == dest) || (comp[source] == comp[dest])) {
                res[i] = true; // Có đường đi tồn tại
            }
        }

        return res;
}
}

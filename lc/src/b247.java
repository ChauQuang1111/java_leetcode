// Bài “Longest Common Suffix Queries” (28/05/2026)

// Ý tưởng của đề

// Bạn có:



// Một mảng wordsContainer

// Một mảng wordsQuery

// Với mỗi chuỗi trong wordsQuery, cần tìm chuỗi trong wordsContainer có hậu tố chung dài nhất với nó.

// Hậu tố chung = các ký tự giống nhau ở cuối chuỗi.

// Ví dụ:



// "running" và "jogging" có suffix chung "ing" → độ dài 3

// "apple" và "people" có suffix chung "ple" → độ dài 3

// Điều cần trả về

// Với mỗi query:



// tìm index của từ trong wordsContainer

// sao cho suffix chung với query là dài nhất.

// Nếu có nhiều từ cùng độ dài suffix:



// chọn từ có độ dài nhỏ hơn

// nếu vẫn hòa → chọn index nhỏ hơn.

// Ví dụ

// wordsContainer = ["abcd","bcd","xbcd"]

// wordsQuery = ["cd","bcd","xyz"]

// Query 1: "cd"

// So sánh:



// "abcd" ↔ "cd" → suffix chung "cd" (2)

// "bcd" ↔ "cd" → "cd" (2)

// "xbcd" ↔ "cd" → "cd" (2)

// Cả 3 đều bằng nhau.

// Xét tiếp độ dài chuỗi:



// "abcd" dài 4

// "bcd" dài 3

// "xbcd" dài 4

// => chọn "bcd" → index 1

// Query 2: "bcd"

// "abcd" ↔ "bcd" → "bcd" (3)

// "bcd" ↔ "bcd" → "bcd" (3)

// "xbcd" ↔ "bcd" → "bcd" (3)

// Tie tiếp.

// Độ dài:



// "abcd" = 4

// "bcd" = 3

// "xbcd" = 4

// => chọn index 1

// Query 3: "xyz"

// Không có suffix chung đáng kể.

// Mọi chuỗi chỉ match độ dài 0.

// Theo luật:



// chọn chuỗi ngắn nhất trong container.

// "bcd" ngắn nhất → index 1

// Kết quả

// [1,1,1]

// Cách nghĩ để giải

// Vì suffix nằm ở cuối chuỗi nên:

// 👉 đảo ngược chuỗi.

// Ví dụ:



// abcd -> dcba

// bcd  -> dcb

// Lúc này:

// “common suffix”

// sẽ trở thành

// “common prefix”.

// => dùng Trie để tìm nhanh.

// Ý tưởng Trie

// Insert:



// dcba

// dcb

// dcbx

// Khi query "cd":

// reverse:



// dc

// Đi theo Trie:



// d -> c

// được 2 ký tự.

// Node sâu nhất match chính là suffix dài nhất.

// Độ phức tạp

// Nếu dùng Trie:



// Build: O(total characters)

// Query: O(length of query)

// Rất nhanh cho dữ liệu lớn.

// Tóm tắt luật chọn

// Cho mỗi query:

// Ưu tiên theo thứ tự:



// suffix chung dài nhất

// word ngắn hơn

// index nhỏ hơn

// Nếu muốn mình có thể giải tiếp:



// cách cài Trie cho bài này

// ví dụ minh họa Trie từng bước

// code Java/Python/C++

// giải thích tại sao phải lưu index ở mỗi node Trie




// Thuật toán này dùng Trie đảo ngược để tìm:



// chuỗi trong wordsContainer có longest common suffix với query.

// Ý tưởng chính

// Suffix ở cuối chuỗi nên ta:



// duyệt chuỗi từ phải sang trái

// tương đương với build Trie bằng chuỗi đảo ngược.

// Ví dụ:



// abcd  -> d c b a

// xbcd  -> d c b x

// Lúc này:



// các chuỗi có suffix giống nhau

// sẽ đi chung đường trong Trie.

// Cấu trúc TrieNode

// class TrieNode {

//     TrieNode[] child = new TrieNode[26];



//     int len = Integer.MAX_VALUE;

//     int idx = 0;

// }

// child[26]

// Lưu 26 ký tự a-z.

// len

// Lưu:



// độ dài nhỏ nhất của word đi qua node này.

// idx

// Lưu:



// index của word có độ dài nhỏ nhất đi qua node này.

// Điều này giúp xử lý rule:



// nếu nhiều suffix bằng nhau

// -> chọn string ngắn hơn

// Hàm insert()

// void insert(String s, int index)

// Chèn từng word vào Trie theo chiều ngược.

// Ví dụ insert("abcd")

// Ta đi:



// d -> c -> b -> a

// Update root

// if (n < node.len) {

//     node.len = n;

//     node.idx = index;

// }

// Root đại diện:



// suffix length = 0

// Nếu query không match gì,

// ta trả về word ngắn nhất toàn bộ.

// Trong vòng for

// for (int i = n - 1; i >= 0; --i)

// Duyệt từ cuối về đầu.

// Tạo node nếu chưa có

// if (node.child[c] == null) {

//     node.child[c] = new TrieNode();

// }

// Di chuyển xuống

// node = node.child[c];

// Update len và idx

// if (n < node.len) {

//     node.len = n;

//     node.idx = index;

// }

// Ý nghĩa:

// Node này đại diện cho 1 suffix.

// Ví dụ node:



// d -> c

// đại diện suffix:



// "cd"

// Ta lưu:



// trong các word có suffix "cd"

// word ngắn nhất là ai.

// Ví dụ trực quan

// Container:



// ["abcd", "bcd", "xbcd"]

// Sau khi insert

// Node:



// d->c->b

// được đi qua bởi:



// abcd (4)

// bcd  (3)

// xbcd (4)

// Node sẽ lưu:



// len = 3

// idx = 1

// vì "bcd" ngắn nhất.

// Hàm search()

// int search(String s)

// Tìm word có longest common suffix.

// Khởi tạo

// TrieNode node = root;



// int ans = node.idx;

// Nếu không match gì,

// ít nhất vẫn trả về word ngắn nhất toàn bộ.

// Duyệt ngược query

// for (int i = s.length() - 1; i >= 0; --i)

// Nếu không còn đường đi

// if (node.child[c] == null) {

//     break;

// }

// Tức là suffix match dừng tại đây.

// Nếu đi tiếp được

// node = node.child[c];



// ans = node.idx;

// Cập nhật đáp án.

// Vì:



// đi càng sâu

// suffix càng dài.

// Node sâu nhất reachable

// = longest common suffix.

// Ví dụ search("cd")

// Ta đi:



// d -> c

// Node "dc" lưu:



// idx = 1

// => trả về "bcd".

// Tại sao đúng?

// Trie đảm bảo:



// đi càng sâu

// → suffix match càng dài.

// Mỗi node luôn lưu:



// word ngắn nhất

// trong tất cả words có suffix này

// Nên tự động xử lý tie-break.

// Độ phức tạp

// Giả sử tổng số ký tự là N.



// Build Trie

// O(N)

// Query

// Mỗi query:



// O(length(query))

// Ý tưởng cực kỳ quan trọng

// Node không chỉ lưu cấu trúc Trie.

// Nó còn lưu:



// best answer cho suffix đó

// nên khi search:



// không cần duyệt lại toàn bộ words

// chỉ cần đi theo Trie.


import java.util.*;

public class b247 {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
 int n = sc.nextInt();

        String[] wordsContainer = new String[n];

        // Nhập các word trong container
        for (int i = 0; i < n; i++) {
            wordsContainer[i] = sc.next();
        }

        // Nhập số lượng query
        int q = sc.nextInt();

        String[] wordsQuery = new String[q];

        // Nhập các query
        for (int i = 0; i < q; i++) {
            wordsQuery[i] = sc.next();
        }

       

        // Gọi hàm xử lý
        int[] ans = stringIndices(wordsContainer, wordsQuery);

        // In kết quả
        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i] + " ");
        }

        sc.close();
    
    }
// Node của Trie
   static class TrieNode {

        // Mỗi node có tối đa 26 nhánh a-z
        TrieNode[] child = new TrieNode[26];

        // Độ dài nhỏ nhất của word đi qua node này
        int len = Integer.MAX_VALUE;

        // Index của word có độ dài nhỏ nhất
        int idx = 0;
    }

    // Root của Trie
   static TrieNode root = new TrieNode();

    // Hàm insert word vào Trie
    static void insert(String s, int index) {

        TrieNode node = root;

        int n = s.length();

        /*
         * Update root
         * Root đại diện cho suffix rỗng ""
         * Nếu query không match gì
         * sẽ trả về word ngắn nhất toàn bộ
         */
        if (n < node.len) {
            node.len = n;
            node.idx = index;
        }

        // Duyệt từ cuối chuỗi về đầu
        for (int i = n - 1; i >= 0; --i) {

            // Chuyển ký tự thành index 0-25
            int c = s.charAt(i) - 'a';

            // Nếu chưa có node thì tạo mới
            if (node.child[c] == null) {
                node.child[c] = new TrieNode();
            }

            // Đi xuống node con
            node = node.child[c];

            /*
             * Nếu word hiện tại ngắn hơn
             * thì update đáp án tốt nhất tại node này
             */
            if (n < node.len) {
                node.len = n;
                node.idx = index;
            }
        }
    }

    // Hàm tìm index word có longest common suffix
    static int search(String s) {

        TrieNode node = root;

        // Mặc định lấy word ngắn nhất toàn bộ
        int ans = node.idx;

        // Duyệt query từ cuối về đầu
        for (int i = s.length() - 1; i >= 0; --i) {

            int c = s.charAt(i) - 'a';

            // Nếu không còn đường đi
            // nghĩa là suffix match kết thúc
            if (node.child[c] == null) {
                break;
            }

            // Đi tiếp xuống Trie
            node = node.child[c];

            /*
             * Node hiện tại lưu:
             * word ngắn nhất có suffix này
             */
            ans = node.idx;
        }

        return ans;
    }

    // Hàm xử lý chính
    public static int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {

        // Insert toàn bộ words vào Trie
        for (int i = 0; i < wordsContainer.length; ++i) {
            insert(wordsContainer[i], i);
        }

        int[] res = new int[wordsQuery.length];

        // Trả lời từng query
        for (int i = 0; i < wordsQuery.length; ++i) {
            res[i] = search(wordsQuery[i]);
        }

        return res;
    }
}


// // Bài Block Placement Queries (30/05/2026)

// // Có 2 loại truy vấn:



// // Loại 1: [1, x]

// // Đặt một vật cản (obstacle) tại vị trí x.

// // Ví dụ:



// // [1, 2]

// // => Có một vật cản ở vị trí 2.

// // Loại 2: [2, x, sz]

// // Kiểm tra xem có thể đặt một khối (block) độ dài sz trong đoạn [0, x] hay không.

// // Điều kiện:



// // Khối phải nằm hoàn toàn trong [0, x].

// // Khối không được đè lên vật cản.

// // Khối được phép chạm vật cản.

// // Ví dụ:



// // Obstacle ở vị trí 2

// // 0 ---- 1 ---- 2 ---- 3

// //              ^

// //          obstacle

// // Truy vấn:



// // [2, 3, 1]

// // Cần đặt block dài 1.

// // Ta có thể đặt:



// // [0,1]

// // hoặc



// // [2,3]

// // (vì chỉ chạm vật cản tại đầu mút, không cắt qua nó)

// // => Kết quả true.

// // Giải thích Example 1

// // queries =

// // [

// //  [1,2],

// //  [2,3,3],

// //  [2,3,1],

// //  [2,2,2]

// // ]

// // Query 1

// // [1,2]

// // Đặt obstacle tại 2.



// // 0 ---- 1 ---- 2 ---- 3

// //              ^

// // Query 2

// // [2,3,3]

// // Cần block dài 3 trong đoạn [0,3].

// // Các khoảng trống là:



// // [0,2]  dài 2

// // [2,3]  dài 1

// // Không có đoạn nào dài ≥ 3.

// // => false

// // Query 3

// // [2,3,1]

// // Cần block dài 1.

// // Có nhiều chỗ đặt được.

// // => true

// // Query 4

// // [2,2,2]

// // Trong đoạn [0,2].

// // Khoảng:



// // [0,2]

// // có độ dài 2.

// // Block được phép chạm obstacle tại vị trí 2.

// // => true

// // Kết quả:



// // [false, true, true]

// // Ý tưởng chính của bài

// // Với mỗi truy vấn loại 2:



// // [2, x, sz]

// // Ta chỉ cần biết:



// // Trong đoạn [0, x], khoảng trống lớn nhất giữa các vật cản là bao nhiêu?

// // Nếu:



// // max_gap >= sz

// // thì trả về true, ngược lại false.

// // Đây là lý do bài này thường được giải bằng Sorted Set + Segment Tree/Fenwick Tree để cập nhật và truy vấn khoảng trống nhanh khi số lượng query lên tới 150000. (Walkccc)

// // Nếu muốn, mình có thể vẽ hình từng bước của ví dụ hoặc giải thích luôn cách nghĩ ra lời giải O(n log n).

// // Mình giải thích theo cách trực quan hơn.

// // ### Hãy tưởng tượng một con đường

// // ```text
// // 0 1 2 3 4 5 6 7 8 9 ...
// // ```

// // Ban đầu **không có vật cản nào**.

// // ---

// // ## Query loại 1: `[1, x]`

// // Nghĩa là:

// // > Đặt một cọc chắn ở vị trí `x`.

// // Ví dụ:

// // ```text
// // [1, 4]
// // ```

// // Sau đó đường sẽ như:

// // ```text
// // 0 1 2 3 4 5 6
// //         |
// //       obstacle
// // ```

// // ---

// // ## Query loại 2: `[2, x, sz]`

// // Nghĩa là:

// // > Trong đoạn từ `0` đến `x`, có đặt được một thanh dài `sz` không?

// // Thanh này không được đi xuyên qua vật cản.

// // ---

// // ### Ví dụ 1

// // Đã có vật cản ở vị trí 4:

// // ```text
// // 0 1 2 3 4 5 6
// //         |
// // ```

// // Query:

// // ```text
// // [2, 6, 3]
// // ```

// // Hỏi:

// // > Trong đoạn `[0,6]` có đặt được thanh dài 3 không?

// // Ta đặt được từ 0 đến 3:

// // ```text
// // [=========]
// // 0 1 2 3 4 5 6
// //         |
// // ```

// // => **True**

// // ---

// // ### Ví dụ 2

// // Vẫn vật cản ở 4.

// // Query:

// // ```text
// // [2, 6, 5]
// // ```

// // Thanh dài 5.

// // Bên trái vật cản chỉ có:

// // ```text
// // 0 -> 4
// // ```

// // độ dài 4.

// // Bên phải:

// // ```text
// // 4 -> 6
// // ```

// // độ dài 2.

// // Không chỗ nào dài 5.

// // => **False**

// // ---

// // ## Điều gây nhầm lẫn nhất

// // Đề nói:

// // > Block can touch an obstacle.

// // Nghĩa là:

// // Thanh được phép chạm vật cản.

// // Ví dụ:

// // ```text
// // Obstacle ở 4
// // ```

// // Thanh:

// // ```text
// // 0 1 2 3 4
// // [=======]
// //         |
// // ```

// // Đầu thanh chạm đúng vị trí 4.

// // Điều này **được phép**.

// // ---

// // ## Ví dụ trong đề

// // ```text
// // [1,2]
// // ```

// // Đặt vật cản ở 2.

// // ```text
// // 0 1 2 3
// //     |
// // ```

// // ---

// // Query:

// // ```text
// // [2,3,3]
// // ```

// // Hỏi có đặt được thanh dài 3 trong đoạn `[0,3]` không?

// // Nếu đặt từ 0 đến 3:

// // ```text
// // 0 1 2 3
// // [=====]
// //     |
// // ```

// // Thanh đi xuyên qua vật cản ở 2.

// // Không được.

// // Không còn vị trí nào khác.

// // => **False**

// // ---

// // Query:

// // ```text
// // [2,3,1]
// // ```

// // Thanh dài 1.

// // Đặt từ 0 đến 1:

// // ```text
// // [=]
// // 0 1 2 3
// //     |
// // ```

// // Được.

// // => **True**

// // ---

// // Query:

// // ```text
// // [2,2,2]
// // ```

// // Đoạn xét là `[0,2]`.

// // Đặt thanh từ 0 đến 2:

// // ```text
// // 0 1 2
// // [===]
// //     |
// // ```

// // Thanh chỉ **chạm** vật cản tại đầu mút.

// // Đề cho phép.

// // => **True**

// // ---

// // Mấu chốt của bài:

// // * Query loại 1: thêm vật cản.
// // * Query loại 2: tìm xem trong `[0,x]` có **khoảng trống liên tục** nào dài ít nhất `sz` hay không.
// // * Chạm vật cản thì được, đi xuyên qua vật cản thì không được.

// // Nếu bạn đang làm LeetCode 3161, mình có thể vẽ hình minh họa cho chính ví dụ Input của đề để thấy tại sao đáp án là `[false,true,true]`.

// Đoạn code này dùng một Segment Tree động (Dynamic Segment Tree) để quản lý các vật cản (obstacles) và trả lời truy vấn đặt block.

// Ý tưởng của Node

// Mỗi node quản lý một đoạn:



// [start, end]

// Ví dụ:



// [0,7]



//       [0,7]

//      /     \

//  [0,3]    [4,7]

// Mỗi node lưu:



// int nearestObstacle;

// Vật cản gần nhất nằm bên phải đoạn này.

// Ví dụ:



// Obstacle tại 10



// Đoạn [0,3]

// nearestObstacle = 10

// int maxFreeSpace;

// Khoảng trống lớn nhất có thể đặt block trong đoạn này.

// Ví dụ:



// Đoạn [0,3]

// Obstacle gần nhất = 10



// maxFreeSpace = 10 - 0 = 10

// Hàm addObstacle()

// Khi gặp query:



// [1,x]

// ta thêm vật cản tại vị trí x.

// Ví dụ:



// [1,5]

// Ban đầu:



// 0--------------------∞



// nearestObstacle = ∞

// Sau khi thêm obstacle 5:



// 0-----5---------∞

//       ^

// mọi đoạn bên trái phải cập nhật:



// nearestObstacle = 5

// Đoạn này

// if (obstaclePosition > root.end)

// nghĩa là:



// Obstacle nằm bên phải node hiện tại

// Ví dụ:



// Node [0,3]



// Obstacle = 5

// thì:



// nearestObstacle = 5

// Hàm isBlockPlaceable()

// Kiểm tra query:



// [2,x,size]

// Ví dụ:



// [2,7,2]

// nghĩa là:



// Có đặt block dài 2 trong [0,7] được không?

// Code đổi thành:



// int blockStart = x - size;

// Ví dụ:



// 7 - 2 = 5

// Tức là:



// Muốn biết có vị trí bắt đầu nào ≤ 5 hay không

// Trường hợp node lá

// if (root.leftChild == null &&

//     root.rightChild == null)

// Ví dụ:



// [3,3]

// Nếu:



// blockSize <= root.maxFreeSpace

// thì:



// đặt được block

// Ví dụ chạy chương trình

// Input:



// queries = {

//     {1,5},

//     {2,7,2},

//     {2,6,2}

// };

// Query 1

// {1,5}

// Obstacle:



// 0----5------∞

// Query 2

// {2,7,2}

// Đặt block dài 2 trong:



// [0,7]

// Ta có:



// 0----5----7

// Khoảng:



// [0,5]

// dài 5.

// Đặt được block dài 2.



// [==]

// 0 1

// → true

// Query 3

// {2,6,2}

// Khoảng:



// 0----5----6

// Vẫn có đoạn dài hơn 2.

// → true

// Phiên bản có Scanner + chú thích

// import java.util.*;



// public class Main {



//     static class Solution {



//         class SegmentTreeNode {



//             // Con trái

//             SegmentTreeNode leftChild;



//             // Con phải

//             SegmentTreeNode rightChild;



//             // Đầu đoạn

//             int start;



//             // Cuối đoạn

//             int end;



//             // Khoảng trống lớn nhất trong đoạn

//             int maxFreeSpace;



//             // Vật cản gần nhất bên phải

//             int nearestObstacle;



//             SegmentTreeNode(int start, int end, int obstaclePosition) {

//                 this.start = start;

//                 this.end = end;



//                 nearestObstacle = obstaclePosition;



//                 if (obstaclePosition == Integer.MAX_VALUE)

//                     maxFreeSpace = Integer.MAX_VALUE;

//                 else

//                     maxFreeSpace = obstaclePosition - start;

//             }

//         }



//         public List<Boolean> getResults(int[][] queries) {



//             int maxRange = 0;



//             // Tìm obstacle lớn nhất

//             for (int[] query : queries) {

//                 if (query[0] == 1) {

//                     maxRange = Math.max(maxRange, query[1]);

//                 }

//             }



//             SegmentTreeNode root =

//                     new SegmentTreeNode(

//                             0,

//                             maxRange,

//                             Integer.MAX_VALUE);



//             List<Boolean> result = new ArrayList<>();



//             for (int[] query : queries) {



//                 // Thêm obstacle

//                 if (query[0] == 1) {



//                     addObstacle(root, query[1]);



//                 } else {



//                     int x = query[1];

//                     int size = query[2];



//                     int blockStart = x - size;



//                     if (blockStart >= root.end) {

//                         result.add(true);

//                     } else {

//                         result.add(

//                                 isBlockPlaceable(

//                                         root,

//                                         blockStart,

//                                         size));

//                     }

//                 }

//             }



//             return result;

//         }



//         boolean isBlockPlaceable(

//                 SegmentTreeNode root,

//                 int blockStart,

//                 int blockSize) {



//             if (root.leftChild == null &&

//                     root.rightChild == null) {



//                 if (blockStart >= root.end) {



//                     return blockSize <= root.maxFreeSpace;



//                 } else if (blockStart < root.start) {



//                     return false;



//                 } else {



//                     return blockSize <=

//                             (root.nearestObstacle - root.start);

//                 }

//             }



//             if (root.rightChild.end <= blockStart) {



//                 if (root.rightChild.maxFreeSpace >= blockSize)

//                     return true;

//             }



//             if (root.leftChild.end <= blockStart) {



//                 if (root.leftChild.maxFreeSpace >= blockSize)

//                     return true;



//             } else {



//                 return isBlockPlaceable(

//                         root.leftChild,

//                         blockStart,

//                         blockSize);

//             }



//             if (root.rightChild.start <= blockStart &&

//                     root.rightChild.end >= blockStart) {



//                 return isBlockPlaceable(

//                         root.rightChild,

//                         blockStart,

//                         blockSize);

//             }



//             return false;

//         }



//         int addObstacle(

//                 SegmentTreeNode root,

//                 int obstaclePosition) {



//             if (root.end == root.start) {



//                 if (root.end < obstaclePosition &&

//                         obstaclePosition < root.nearestObstacle) {



//                     root.nearestObstacle =

//                             obstaclePosition;

//                 }



//                 if (root.nearestObstacle ==

//                         Integer.MAX_VALUE)



//                     root.maxFreeSpace =

//                             Integer.MAX_VALUE;

//                 else



//                     root.maxFreeSpace =

//                             root.nearestObstacle

//                                     - root.start;



//                 return root.maxFreeSpace;

//             }



//             if (obstaclePosition <= root.start)

//                 return root.maxFreeSpace;



//             if (obstaclePosition > root.end) {



//                 if (obstaclePosition

//                         < root.nearestObstacle) {



//                     root.nearestObstacle =

//                             obstaclePosition;



//                     if (root.leftChild == null) {



//                         root.maxFreeSpace =

//                                 obstaclePosition

//                                         - root.start;



//                     } else {



//                         root.maxFreeSpace =

//                                 Math.max(

//                                         addObstacle(

//                                                 root.leftChild,

//                                                 obstaclePosition),

//                                         addObstacle(

//                                                 root.rightChild,

//                                                 obstaclePosition));

//                     }

//                 }



//                 return root.maxFreeSpace;

//             }



//             if (root.leftChild != null) {



//                 root.maxFreeSpace =

//                         Math.max(

//                                 addObstacle(

//                                         root.leftChild,

//                                         obstaclePosition),

//                                 addObstacle(

//                                         root.rightChild,

//                                         obstaclePosition));



//                 return root.maxFreeSpace;

//             }



//             int mid =

//                     (root.start + root.end) / 2;



//             root.leftChild =

//                     new SegmentTreeNode(

//                             root.start,

//                             mid,

//                             root.nearestObstacle);



//             root.rightChild =

//                     new SegmentTreeNode(

//                             mid + 1,

//                             root.end,

//                             root.nearestObstacle);



//             root.maxFreeSpace =

//                     Math.max(

//                             addObstacle(

//                                     root.leftChild,

//                                     obstaclePosition),

//                             addObstacle(

//                                     root.rightChild,

//                                     obstaclePosition));



//             return root.maxFreeSpace;

//         }

//     }



//     public static void main(String[] args) {



//         Scanner sc = new Scanner(System.in);



//         int n = sc.nextInt();



//         int[][] queries = new int[n][];



//         for (int i = 0; i < n; i++) {



//             int type = sc.nextInt();



//             if (type == 1) {



//                 queries[i] =

//                         new int[]{

//                                 type,

//                                 sc.nextInt()

//                         };



//             } else {



//                 queries[i] =

//                         new int[]{

//                                 type,

//                                 sc.nextInt(),

//                                 sc.nextInt()

//                         };

//             }

//         }



//         Solution solution = new Solution();



//         List<Boolean> answer =

//                 solution.getResults(queries);



//         System.out.println(answer);



//         sc.close();

//     }

// }

// ⚠️ Tuy nhiên cần lưu ý: đây không phải lời giải chuẩn tối ưu của LeetCode 3161. Lời giải được chấp nhận phổ biến thường dùng TreeSet + Segment Tree/Fenwick Tree + Offline Processing với độ phức tạp O(q log q). Đoạn code này là một cách cài đặt riêng bằng Dynamic Segment Tree và khá khó hiểu vì lưu nearestObstacle thay vì lưu trực tiếp các khoảng trống.



import java.util.*;

public class b249{

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
       

    // Số lượng query
    int n = sc.nextInt();

    int[][] queries = new int[n][];

    for (int i = 0; i < n; i++) {

        int type = sc.nextInt();

        // Query loại 1: [1, x]
        if (type == 1) {

            int x = sc.nextInt();

            queries[i] = new int[]{
                    type,
                    x
            };
        }

        // Query loại 2: [2, x, size]
        else {

            int x = sc.nextInt();
            int size = sc.nextInt();

            queries[i] = new int[]{
                    type,
                    x,
                    size
            };
        }
    }

   

    List<Boolean> result =getResults(queries);

    System.out.println(result);

    sc.close();
   }
 static class SegmentTreeNode {

        // Con trái của Segment Tree
        SegmentTreeNode leftChild;

        // Con phải của Segment Tree
        SegmentTreeNode rightChild;

        // Điểm bắt đầu đoạn
        int start;

        // Điểm kết thúc đoạn
        int end;

        // Khoảng trống lớn nhất có thể đặt block trong đoạn này
        int maxFreeSpace = 0;

        // Vật cản gần nhất nằm bên phải đoạn hiện tại
        int nearestObstacle = Integer.MAX_VALUE;

        SegmentTreeNode(int start, int end, int obstaclePosition) {
            this.start = start;
            this.end = end;

            // Lưu obstacle gần nhất bên phải
            this.nearestObstacle = obstaclePosition;

            // Nếu chưa có obstacle thì khoảng trống là vô hạn
            this.maxFreeSpace =
                    obstaclePosition == Integer.MAX_VALUE
                            ? Integer.MAX_VALUE
                            : obstaclePosition - start;
        }

        // Hàm debug in cây
        void printTree() {
            System.out.print("[" + start + "-" + end +
                    ": " + nearestObstacle +
                    ", " + maxFreeSpace);

            if (leftChild != null) {
                System.out.print(" -- ");

                leftChild.printTree();
                rightChild.printTree();

                System.out.print(" -- " + start + "-" + end);
            }

            System.out.print("]");
        }
    }
 public static List<Boolean> getResults(int[][] queries) {

        int maxRange = 0;

        // Tìm obstacle có vị trí lớn nhất
        // để xác định phạm vi Segment Tree
        for (int[] query : queries) {

            if (query[0] == 1) {

                maxRange =
                        Math.max(maxRange, query[1]);
            }
        }

        // Khởi tạo root
        SegmentTreeNode root =
                new SegmentTreeNode(
                        0,
                        maxRange,
                        Integer.MAX_VALUE);

        List<Boolean> results = new ArrayList<>();

        for (int[] query : queries) {

            // Query loại 1: thêm obstacle
            if (query[0] == 1) {

                addObstacle(root, query[1]);

            } else {

                // Query loại 2
                int x = query[1];
                int blockSize = query[2];

                // Vị trí bắt đầu xa nhất của block
                int blockStart = x - blockSize;

                // Nếu block có thể nằm hoàn toàn
                // bên ngoài phạm vi cây
                if (blockStart >= root.end) {

                    results.add(true);

                } else {

                    results.add(
                            isBlockPlaceable(
                                    root,
                                    blockStart,
                                    blockSize));
                }
            }
        }

        return results;
    }
    
    /*
     * Kiểm tra xem có đặt được block hay không
     */
   static boolean isBlockPlaceable(
            SegmentTreeNode root,
            int blockStart,
            int blockSize) {

        // Node lá
        if (root.leftChild == null &&
                root.rightChild == null) {

            // Toàn bộ đoạn này nằm trước blockStart
            if (blockStart >= root.end) {

                return blockSize <= root.maxFreeSpace;
            }

            // Không giao với blockStart
            else if (blockStart < root.start) {

                return false;
            }

            // Kiểm tra khoảng cách tới obstacle gần nhất
            else {

                return blockSize <=
                        (root.nearestObstacle - root.start);
            }
        }

        // Nếu nhánh phải hoàn toàn nằm trước blockStart
        if (root.rightChild.end <= blockStart) {

            if (root.rightChild.maxFreeSpace >= blockSize) {

                return true;
            }
        }

        // Nếu nhánh trái hoàn toàn nằm trước blockStart
        if (root.leftChild.end <= blockStart) {

            if (root.leftChild.maxFreeSpace >= blockSize) {

                return true;
            }
        }

        else {

            // Tiếp tục tìm bên trái
            return isBlockPlaceable(
                    root.leftChild,
                    blockStart,
                    blockSize);
        }

        // Nếu blockStart nằm trong nhánh phải
        if (root.rightChild.start <= blockStart &&
                root.rightChild.end >= blockStart) {

            return isBlockPlaceable(
                    root.rightChild,
                    blockStart,
                    blockSize);
        }

        return false;
    }

    /*
     * Thêm obstacle mới vào cây
     */
     static int addObstacle(
            SegmentTreeNode root,
            int obstaclePosition) {

        // Node lá
        if (root.end == root.start) {

            // Cập nhật obstacle gần nhất
            if (root.end < obstaclePosition &&
                    obstaclePosition < root.nearestObstacle) {

                root.nearestObstacle = obstaclePosition;
            }

            // Tính lại khoảng trống
            root.maxFreeSpace =
                    root.nearestObstacle == Integer.MAX_VALUE
                            ? Integer.MAX_VALUE
                            : root.nearestObstacle - root.start;

            return root.maxFreeSpace;
        }

        // Obstacle nằm bên trái node
        if (obstaclePosition <= root.start)
            return root.maxFreeSpace;

        // Obstacle nằm bên phải node
        if (obstaclePosition > root.end) {

            // Nếu obstacle mới gần hơn obstacle cũ
            if (obstaclePosition < root.nearestObstacle) {

                root.nearestObstacle = obstaclePosition;

                // Chưa tách node
                if (root.leftChild == null &&
                        root.rightChild == null) {

                    root.maxFreeSpace =
                            obstaclePosition - root.start;
                }

                // Đã có con
                else {

                    root.maxFreeSpace =
                            Math.max(
                                    addObstacle(
                                            root.leftChild,
                                            obstaclePosition),

                                    addObstacle(
                                            root.rightChild,
                                            obstaclePosition));
                }
            }

            return root.maxFreeSpace;
        }

        // Node đã được chia
        if (root.leftChild != null &&
                root.rightChild != null) {

            root.maxFreeSpace =
                    Math.max(
                            addObstacle(
                                    root.leftChild,
                                    obstaclePosition),

                            addObstacle(
                                    root.rightChild,
                                    obstaclePosition));

            return root.maxFreeSpace;
        }

        // Chia node thành 2 nửa
        int mid =
                (root.end - root.start) / 2 + root.start;

        root.leftChild =
                new SegmentTreeNode(
                        root.start,
                        mid,
                        root.nearestObstacle);

        root.rightChild =
                new SegmentTreeNode(
                        mid + 1,
                        root.end,
                        root.nearestObstacle);

        // Tiếp tục cập nhật obstacle
        root.maxFreeSpace =
                Math.max(
                        addObstacle(
                                root.leftChild,
                                obstaclePosition),

                        addObstacle(
                                root.rightChild,
                                obstaclePosition));

        return root.maxFreeSpace;
    }

}



  

   

  

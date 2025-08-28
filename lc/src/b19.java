// 3446. Sort Matrix by Diagonals(28/08/2025)

import java.util.*;

public class b19 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int rows = sc.nextInt();

        int cols = sc.nextInt();

        int[][] mat = new int[rows][cols];
        System.out.println("Nhập các phần tử của ma trận, cách nhau bởi dấu cách:");

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mat[i][j] = sc.nextInt();
            }
        }
        sc.close();

        int[][] sortedMat = sortMatrix(mat);

        System.out.println("\nMa trận sau khi sắp xếp:");
        for (int i = 0; i < sortedMat.length; i++) {
            for (int j = 0; j < sortedMat[i].length; j++) {
                System.out.print(sortedMat[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Sắp xếp các đường chéo của một ma trận theo các quy tắc cụ thể.
     * Đường chéo chính và các đường chéo dưới nó được sắp xếp giảm dần.
     * Các đường chéo trên đường chéo chính được sắp xếp tăng dần.
     */
    public static int[][] sortMatrix(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;

        // Vòng lặp qua các điểm bắt đầu của đường chéo ở cột đầu tiên.
        // Điều này bao gồm đường chéo chính và tất cả các đường chéo bên dưới nó.
        for (int row = 0; row < rows; row++) {
            // Sắp xếp theo thứ tự giảm dần.
            sortDiagonal(mat, row, 0, false);
        }

        // Vòng lặp qua các điểm bắt đầu của đường chéo ở hàng đầu tiên.
        // Điều này bao gồm tất cả các đường chéo trên đường chéo chính.
        // Bắt đầu từ cột 1 vì đường chéo chính (ở cột 0) đã được xử lý ở trên.
        for (int col = 1; col < cols; col++) {
            // Sắp xếp theo thứ tự tăng dần.
            sortDiagonal(mat, 0, col, true);
        }

        return mat;
    }

    /**
     * Phương thức trợ giúp để trích xuất, sắp xếp và thay thế một đường chéo duy
     * nhất.
     *
     * @param mat        Ma trận đầu vào.
     * @param row        Hàng bắt đầu của đường chéo.
     * @param col        Cột bắt đầu của đường chéo.
     * @param increasing Một cờ để xác định thứ tự sắp xếp (true cho tăng dần, false
     *                   cho giảm dần).
     */
    public static void sortDiagonal(int[][] mat, int row, int col, boolean increasing) {
        int rows = mat.length;
        int cols = mat[0].length;

        // Tính toán độ dài của đường chéo hiện tại.
        // Độ dài là giá trị nhỏ hơn của số hàng còn lại hoặc số cột còn lại từ điểm bắt
        // đầu.
        int len = Math.min(rows - row, cols - col);
        int[] diagonal = new int[len];

        // Trích xuất các phần tử của đường chéo vào một mảng tạm thời.
        for (int i = 0; i < len; i++) {
            diagonal[i] = mat[row + i][col + i];
        }

        // Sắp xếp mảng tạm thời theo thứ tự tăng dần.
        Arrays.sort(diagonal);

        // Nếu yêu cầu sắp xếp giảm dần, đảo ngược mảng.
        if (!increasing) {
            reverse(diagonal);
        }

        // Đặt các phần tử đã sắp xếp trở lại ma trận dọc theo đường chéo.
        for (int i = 0; i < len; i++) {
            mat[row + i][col + i] = diagonal[i];
        }
    }

    /**
     * Một phương thức trợ giúp đơn giản để đảo ngược một mảng tại chỗ (in-place).
     */
    public static void reverse(int[] arr) {
        int i = 0, j = arr.length - 1;
        while (i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }

}

// Dưới đây
// là phần
// giải thích
// chi tiết
// và các
// chú thích
// cho đoạn
// mã Java
// bạn đã
// cung cấp.

// -----

// ###
// Giải thích
// thuật toán

// Đoạn mã
// này thực
// hiện việc
// sắp xếp
// các phần
// tử của
// ma trận
// dọc theo
// các đường
// chéo của
// nó.Cách tiếp
// cận là
// chia bài
// toán thành
// hai phần
// riêng biệt:
// sắp xếp
// các đường
// chéo bắt
// đầu từ
// cột đầu
// tiên và
// sắp xếp
// các đường
// chéo bắt
// đầu từ
// hàng đầu tiên.

// 1.**
// Duyệt và
// Sắp xếp
// các đường
// chéo bắt
// đầu từ
// Cột đầu tiên**:

// *
// Vòng lặp
// đầu tiên,`for(
// int row = 0;row<rows;row++)`,
// duyệt qua
// từng hàng
// của cột
// đầu tiên.
// Mỗi cặp
// tọa độ`(row,0)`
// đại diện
// cho điểm
// bắt đầu
// của một
// đường chéo
// duy nhất.
// Điều này
// bao gồm
// đường chéo
// chính và
// tất cả
// các đường
// chéo bên
// dưới nó.*
// Với mỗi
// điểm bắt đầu,
// phương thức
// trợ giúp`sortDiagonal`
// được gọi.
// Tham số`false`
// chỉ ra
// rằng các
// đường chéo
// này sẽ
// được sắp
// xếp theo
// thứ tự**
// giảm dần**.

// 2.**
// Duyệt và
// Sắp xếp
// các đường
// chéo bắt
// đầu từ
// Hàng đầu tiên**:

// *
// Vòng lặp
// thứ hai,`for(
// int col = 1;col<cols;col++)`,
// duyệt qua
// từng cột
// của hàng
// đầu tiên, bắt
// đầu từ
// cột thứ
// hai. Mỗi cặp
// tọa độ`(0,col)`
// đại diện
// cho điểm
// bắt đầu
// của một
// đường chéo
// duy nhất.
// Điều này
// bao gồm
// tất cả
// các đường
// chéo phía
// trên đường
// chéo chính.*
// Với mỗi
// điểm bắt đầu,`sortDiagonal`
// được gọi
// với tham số`true`,
// có nghĩa
// là các
// đường chéo
// này sẽ
// được sắp
// xếp theo
// thứ tự**tăng dần**.

// 3.**Phương thức
// trợ giúp`sortDiagonal`**:

// *
// Đây là
// phần cốt
// lõi của
// thuật toán.
// Nó nhận
// tọa độ
// bắt đầu`(row,col)`
// và một cờ boolean`increasing`
// để xác
// định thứ
// tự sắp xếp.*
// Đầu tiên, nó
// tính toán
// độ dài
// của đường
// chéo hiện
// tại bằng
// cách tìm
// giá trị
// nhỏ nhất
// của số
// hàng còn
// lại và
// số cột
// còn lại
// từ điểm
// bắt đầu.*
// Sau đó, nó
// trích xuất
// tất cả
// các phần
// tử của
// đường chéo
// vào một
// mảng tạm thời`diagonal`.*
// Phương thức`Arrays.sort(diagonal)`
// được sử
// dụng để
// sắp xếp
// các phần
// tử theo
// thứ tự**
// tăng dần**.*Nếu`increasing`là`false`,
// phương thức
// trợ giúp`reverse`
// sẽ được
// gọi để
// đảo ngược
// mảng đã
// sắp xếp, đưa
// nó về
// thứ tự
// giảm dần.*
// Cuối cùng, các
// phần tử
// đã sắp
// xếp từ
// mảng tạm
// thời được
// đặt trở
// lại ma
// trận dọc
// theo cùng
// một đường chéo.

// Dựa trên tiêu đề "Sort Matrix by Diagonals", bài toán yêu cầu bạn sắp xếp các
// phần tử của một ma trận vuông dọc theo các đường chéo của nó.

// ### Giải thích chi tiết

// 1. **Xác định các đường chéo**: Một ma trận có nhiều đường chéo:
// * **Đường chéo chính**: Chạy từ góc trên cùng bên trái xuống góc dưới cùng
// bên phải.
// * **Các đường chéo dưới đường chéo chính**: Bắt đầu từ cột đầu tiên.
// * **Các đường chéo trên đường chéo chính**: Bắt đầu từ hàng đầu tiên.

// 2. **Sắp xếp từng đường chéo**: Bạn cần coi mỗi đường chéo là một danh sách
// các số riêng biệt. Dựa vào ví dụ, có một quy tắc sắp xếp cụ thể:
// * Các đường chéo dưới và trên đường chéo chính sẽ được sắp xếp theo thứ tự
// **giảm dần**.
// * Các đường chéo trên đường chéo chính sẽ được sắp xếp theo thứ tự **tăng
// dần**.

// 3. **Thay thế các phần tử**: Sau khi sắp xếp từng đường chéo, bạn phải đặt
// các phần tử đã sắp xếp trở lại vị trí ban đầu của chúng trong ma trận.

// Kết quả cuối cùng sẽ là ma trận đã được sửa đổi, trong đó tất cả các đường
// chéo của nó đã được sắp xếp theo các quy tắc trên.
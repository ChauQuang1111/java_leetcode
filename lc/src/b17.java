
// 3000. Maximum Area of Longest Diagonal Rectangle(26/08/2025)
import java.util.*;

public class b17 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();

        int[][] dimensions = new int[n][2];

        for (int i = 0; i < n; i++) {
            System.out.printf("Hình chữ nhật %d - Chiều dài: ", i + 1);
            dimensions[i][0] = sc.nextInt();
            System.out.printf("Hình chữ nhật %d - Chiều rộng: ", i + 1);
            dimensions[i][1] = sc.nextInt();
        }

        int result = areaOfMaxDiagonal(dimensions);

        System.out.println(result);

        sc.close();
    }

    public static int areaOfMaxDiagonal(int[][] dimensions) {
        int maxDiaSq = 0;
        int maxArea = 0;
        for (int[] dim : dimensions) {
            int l = dim[0];
            int w = dim[1];
            int diaSq = l * l + w * w;
            int area = l * w;
            if (diaSq > maxDiaSq) {
                maxDiaSq = diaSq;
                maxArea = area;
            } else if (diaSq == maxDiaSq) {
                maxArea = Math.max(maxArea, area);
            }
        }
        return maxArea;
    }
}

// Giải thích
// đề bài:
// Maximum Area
// of Longest
// Diagonal Rectangle

// Đề bài**3000.
// Maximum Area
// of Longest
// Diagonal Rectangle**
// yêu cầu
// bạn tìm
// diện tích
// lớn nhất
// của một
// hình chữ
// nhật có
// đường chéo
// dài nhất
// từ một
// danh sách
// các hình
// chữ nhật
// cho trước.

// **
// Thông tin
// đầu vào:**

// Bạn được
// cung cấp
// một mảng 2D
// có tên là`dimensions`.
// Mỗi phần
// tử con
// trong mảng
// này là
// một mảng
// gồm hai
// số nguyên`[l,w]`,
// đại diện
// cho chiều

// dài (`l`) và chiều rộng (`w`) của một hình chữ nhật.

// **Bài toán có hai tiêu chí chính:**

// 1. **Tiêu chí 1: Tìm hình chữ nhật có đường chéo dài nhất.**
// * Đường chéo của một hình chữ nhật được tính bằng định lý Pythagoras: $d =
// \sqrt{l^2 + w^2}$.
// * Bạn cần tính độ dài đường chéo cho tất cả các hình chữ nhật trong danh
// sách.
// * Sau đó, bạn phải tìm ra độ dài đường chéo **lớn nhất** trong tất cả các
// hình chữ nhật đó.

// 2. **Tiêu chí 2: Tìm diện tích lớn nhất trong số các hình chữ nhật thỏa mãn
// tiêu chí 1.**
// * Nếu có nhiều hơn một hình chữ nhật có cùng độ dài đường chéo dài nhất, bạn
// phải tính diện tích của tất cả các hình chữ nhật đó.
// * Diện tích của một hình chữ nhật được tính bằng: $A = l \times w$.
// * Cuối cùng, kết quả trả về phải là **diện tích lớn nhất** trong số các hình
// chữ nhật đã tìm thấy ở bước trên.

// ### Ví dụ minh họa

// Hãy xem ví dụ để hiểu rõ hơn:

// `dimensions = [[9, 3], [8, 6]]`

// **Bước 1: Tính độ dài đường chéo.**

// * **Hình chữ nhật 1:** `l = 9`, `w = 3`
// * Đường chéo: $\sqrt{9^2 + 3^2} = \sqrt{81 + 9} = \sqrt{90}$
// * **Hình chữ nhật 2:** `l = 8`, `w = 6`
// * Đường chéo: $\sqrt{8^2 + 6^2} = \sqrt{64 + 36} = \sqrt{100} = 10$

// Độ dài đường chéo lớn nhất là $10$.

// **Bước 2: Tìm diện tích lớn nhất của các hình có đường chéo dài nhất.**

// * Chỉ có một hình chữ nhật có đường chéo dài

// nhất (hình thứ hai).
// * **Diện tích:** $8 \times 6 = 48$

// Vậy, kết quả cuối cùng là `48`.

// ---

// **Một ví dụ khác:**

// `dimensions = [[1, 1], [2, 2], [3, 3]]`

// * **Hình 1:** Đường chéo $\sqrt{1^2 + 1^2} = \sqrt{2}$
// * **Hình 2:** Đường chéo $\sqrt{2^2 + 2^2} = \sqrt{8}$
// * **Hình 3:** Đường chéo $\sqrt{3^2 + 3^2} = \sqrt{18}$

// * **Đường chéo lớn nhất:** $\sqrt{18}$
// * **Số hình có đường chéo lớn nhất:** Chỉ có

// một hình (hình thứ ba).
// * **Diện tích:** $3 \times 3 = 9$

// Kết quả là `9`.

// ### Tóm tắt

// Để giải bài toán này, bạn cần thực hiện hai bước chính:

// 1. Duyệt qua danh sách các hình chữ nhật, tính độ dài đường chéo của từng
// hình và tìm ra độ dài lớn nhất.
// 2. Duyệt lại danh sách một lần nữa, tìm tất cả các hình chữ nhật có độ dài
// đường chéo bằng với giá trị lớn nhất đã tìm thấy ở bước 1.
// 3. Trong số các hình đó, tính diện tích của từng hình và trả về diện tích lớn
// nhất.

// Lưu ý: Vì `l^2 + w^2` tỉ lệ thuận với $\sqrt{l^2 + w^2}$, bạn có thể chỉ cần
// so sánh `l^2 + w^2` để tìm đường chéo dài nhất, tránh phải tính căn bậc hai.

// Đây là
// một cách
// giải bài toán**"Maximum Area of Longest Diagonal Rectangle"**
// rất hiệu
// quả và
// ngắn gọn.
// Thay vì
// duyệt qua
// mảng hai lần,
// thuật toán
// này chỉ
// cần một
// lần duyệt
// duy nhất.

// ---

// ###
// Giải thích
// thuật toán

// Thuật toán
// này sử
// dụng một
// vòng lặp
// để duyệt
// qua từng
// hình chữ
// nhật trong mảng`dimensions`
// và duy
// trì hai
// biến để
// theo dõi
// trạng thái
// hiện tại:

// 1.`maxDiaSq`:
// Bình phương
// của độ
// dài đường
// chéo lớn
// nhất đã
// tìm thấy.2.`maxArea`:
// Diện tích
// lớn nhất
// tương ứng
// với độ
// dài đường
// chéo đó.

// Quá trình
// này diễn
// ra như sau:

// 1.**Khởi tạo:***`maxDiaSq=0`:
// Biến này
// sẽ lưu
// trữ bình
// phương độ
// dài đường
// chéo lớn
// nhất.Bắt đầu với 0.*`maxArea=0`:
// Biến này
// sẽ lưu
// diện tích
// lớn nhất.
// Bắt đầu với 0.

// 2.**
// Duyệt qua
// từng hình
// chữ nhật:***`for(
// int[] dim:dimensions)`:
// Vòng lặp
// này duyệt
// qua từng
// mảng con`dim`(
// chiều dài
// và chiều rộng)
// trong mảng`dimensions`.*`
// int l = dim[0];
// int w = dim[1];`:
// Gán chiều
// dài và
// chiều rộng
// của hình
// chữ nhật
// hiện tại
// vào các biến`l`và`w`.*`int diaSq = l * l + w * w;`:
// Tính bình
// phương độ
// dài đường
// chéo của
// hình chữ
// nhật hiện tại.*`int area = l * w;`:
// Tính diện
// tích của
// hình chữ
// nhật hiện tại.

// 3.**
// So sánh
// và cập nhật:***`if(diaSq>maxDiaSq)`:*
// Đây là
// trường hợp
// bạn tìm
// thấy một
// hình chữ
// nhật có
// đường chéo**
// dài hơn**
// tất cả
// các hình
// chữ nhật
// đã xét
// trước đó.*
// Bạn cập nhật`maxDiaSq`thành`diaSq`mới.*
// Bạn cũng
// cập nhật`maxArea`thành`area`mới,
// vì hình
// chữ nhật
// này hiện
// tại là
// ứng cử
// viên duy
// nhất có
// đường chéo
// dài nhất.*`else if(diaSq==maxDiaSq)`:*
// Đây là
// trường hợp
// bạn tìm
// thấy một
// hình chữ
// nhật có
// đường chéo**
// dài bằng**
// với đường
// chéo lớn
// nhất đã
// tìm thấy.*
// Theo yêu
// cầu của
// đề bài, bạn
// phải tìm
// diện tích
// lớn nhất
// trong số
// các hình
// chữ nhật này.*`maxArea=Math.max(maxArea,area);`:
// Bạn so
// sánh diện tích`area`
// của hình
// chữ nhật
// hiện tại với`maxArea`
// đã lưu
// trước đó
// và giữ
// lại giá
// trị lớn hơn.

// 4.**
// Trả về
// kết quả:***
// Sau khi
// vòng lặp
// kết thúc,`maxArea`
// sẽ chứa
// diện tích
// lớn nhất
// của hình
// chữ nhật
// có đường
// chéo dài
// nhất.Thuật toán
// trả về
// giá trị này.

// ###
// Điểm mạnh
// của thuật
// toán này

// ***
// Hiệu quả cao:**
// Chỉ cần
// một lần
// duyệt duy

// nhất (one-pass) qua mảng, giúp giảm độ phức tạp về thời gian.
// * **Ngắn gọn:** Mã nguồn rất gọn gàng và dễ đọc, thể hiện rõ ràng logic so
// sánh.
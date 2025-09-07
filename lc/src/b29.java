
//  1304. Find N Unique Integers Sum up to Zero(07/09/2025)
import java.util.*;

public class b29 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();
        int[] result = sumZero(n);
        sc.close();
        System.out.println(Arrays.toString(result));

        // Hàm `Arrays.toString()` trong Java là một phương thức tiện ích dùng để
        // **chuyển đổi một mảng (array) thành một chuỗi (String)**.

        // Chức năng chính của nó là giúp bạn in nội dung của mảng ra màn hình một cách
        // dễ đọc, thay vì in ra địa chỉ bộ nhớ của mảng.

        // ### Ví dụ minh họa

        // Hãy xem điều gì xảy ra nếu bạn không sử dụng `Arrays.toString()`:

        // ```java
        // int[] numbers = {1, 2, 3, 4, 5};
        // System.out.println(numbers);

        // **Kết quả đầu ra:**

        // ```
        // [I@28a418fc

        // *Bạn thấy một chuỗi ký tự khó hiểu* (`[I@...`), đây là định dạng mặc định của
        // Java để biểu diễn địa chỉ bộ nhớ của mảng.

        // -----

        // Bây giờ, hãy sử dụng `Arrays.toString()`:

        // ```java
        // import java.util.Arrays; // Rất quan trọng: phải import thư viện này

        // int[] numbers = {1, 2, 3, 4, 5};
        // System.out.println(Arrays.toString(numbers));
        // ```

        // **Kết quả đầu ra:**

        // ```
        // [1, 2, 3, 4, 5]
        // ```

        // *Rõ ràng hơn rất nhiều\!* Hàm này đã định dạng nội dung của mảng thành một
        // chuỗi có dấu ngoặc vuông và các phần tử cách nhau bởi dấu phẩy.

        // ### Tóm tắt chức năng

        // * **Dễ gỡ lỗi (Debugging)**: Giúp bạn nhanh chóng kiểm tra nội dung của một
        // mảng trong quá trình phát triển ứng dụng.
        // * **Hiển thị thông tin**: Rất hữu ích khi bạn muốn in kết quả của một mảng ra
        // màn hình cho người dùng xem.
        // * **Hỗ trợ mọi kiểu dữ liệu**: Hoạt động với các mảng kiểu dữ liệu nguyên
        // thủy (`int`, `char`, `double`,...) và cả các mảng đối tượng.

        // **Lưu ý quan trọng**: Để sử dụng `Arrays.toString()`, bạn phải nhập thư viện
        // `java.util.Arrays;` vào đầu file.

    }

    public static int[] sumZero(int n) {
        // Bước 1: Khởi tạo một mảng có kích thước n
        // Mảng này sẽ tự động được khởi tạo với giá trị 0 cho tất cả các phần tử.
        int[] arr = new int[n];

        // Bước 2: Xử lý trường hợp đặc biệt khi n = 1
        // Nếu n là 1, mảng arr đã có phần tử duy nhất là 0, đáp ứng yêu cầu đề bài.
        if (n == 1) {
            return arr;
        }

        // Bước 3: Điền các giá trị cho n-1 phần tử đầu tiên
        // Khởi tạo biến 'sum' để lưu tổng của các phần tử này.
        int sum = 0;

        // Vòng lặp này chạy từ i = 0 đến n-2 (tổng cộng n-1 lần).
        for (int i = 0; i < n - 1; i++) {
            // Gán giá trị i+1 vào vị trí i của mảng.
            // Ví dụ: arr[0]=1, arr[1]=2, ...
            arr[i] = i + 1;

            // Cộng giá trị vừa gán vào biến 'sum'
            sum += arr[i];
        }

        // Bước 4: Tính và gán giá trị cho phần tử cuối cùng
        // Phần tử cuối cùng của mảng phải là số đối của tổng của các phần tử còn lại,
        // để tổng của toàn bộ mảng bằng 0.
        arr[n - 1] = -sum;

        // Bước 5: Trả về mảng kết quả
        return arr;
    }
}

// Đây là
// một thuật
// toán rất
// thông minh
// và hiệu
// quả để
// giải bài
// toán.Nó tận
// dụng một
// nguyên lý
// đơn giản:
// tổng của
// một tập
// hợp các
// số cộng
// với số
// đối của
// tổng đó
// sẽ bằng 0.

// -----

// ###
// Giải thích
// thuật toán

// Thuật toán
// của bạn
// thực hiện
// các bước sau:

// 1.**Khởi tạo mảng**:

// ```java
// int[] arr = new int[n];if(n==1)return arr;```

// Đầu tiên, bạn
// tạo một mảng`arr`
// có kích thước`n`.
// Bạn xử
// lý trường
// hợp đặc
// biệt khi`n=1`
// bằng cách
// trả về
// một mảng
// chứa một số 0
// duy nhất, vì
// đây là
// đáp án
// duy nhất
// thỏa mãn.

// 2.**Điền`n-1`
// số đầu tiên**:

// ```java
// int sum = 0;for(
// int i = 0;i<n-1;i++)
// {
// arr[i] = i + 1;
// sum += arr[i];
// }```

// *
// Vòng lặp
// này điền vào`n-1`
// vị trí
// đầu tiên
// của mảng
// bằng các
// số nguyên
// dương tăng dần,
// bắt đầu từ 1(`1,2,3,...`).*
// Đồng thời, bạn
// tính tổng
// của tất
// cả các
// số này
// và lưu
// vào biến`sum`.*
// Ví dụ:Nếu`n=5`,
// vòng lặp
// sẽ điền
// các số`1,2,3,4`
// vào mảng, và`sum`
// sẽ bằng`1+2+3+4=10`.

// 3.**
// Tính toán
// và điền
// số cuối cùng**:

// ```java arr[n-1]=-sum;```

// *
// Đây là
// bước mấu
// chốt.Để tổng
// của tất cả`n`
// số bằng 0,
// số cuối
// cùng phải
// là số
// đối của
// tổng của`n-1`
// số đầu tiên.*
// Bạn gán
// giá trị`-sum`
// cho phần
// tử cuối
// cùng của

// mảng (`arr[n-1]`).
// * Ví dụ: Với `n = 5`, `sum` là `10`. Số cuối cùng sẽ là `-10`. Mảng cuối cùng
// là `[1, 2, 3, 4, -10]`. Tổng của chúng là `1 + 2 + 3 + 4 + (-10) = 0`.

// ### Tại sao thuật toán này luôn đúng?

// * **Tổng bằng 0**: Theo định nghĩa, `sum + (-sum) = 0`. Vì `arr[n-1]` được
// gán bằng `-sum`, tổng của toàn bộ mảng luôn bằng 0.
// * **Các số là duy nhất**: Các số bạn điền vào mảng là `1, 2, 3, ..., n-1`,
// tất cả đều là số dương và duy nhất. Số cuối cùng, `-sum`, sẽ là một số âm (vì
// `sum` là tổng của các số dương). Do đó, `-sum` không bao giờ trùng lặp với
// bất kỳ số dương nào từ `1` đến `n-1`.
// * **Trường hợp đặc biệt `n=1`**: Thuật toán của bạn xử lý đúng trường hợp
// này. Một mảng `int` mới với kích thước 1 sẽ tự động được khởi tạo với giá trị
// `0`, đây là đáp án chính xác.
// Đây là
// một câu
// hỏi rất
// hay. Dòng code`if n%2==1:result.append(0)`
// là để
// xử lý
// trường hợp
// đặc biệt khi`n`
// là một
// số lẻ.

// #
// Hãy xem
// lại mục
// tiêu của
// chúng ta:**tạo`n`
// số nguyên
// duy nhất
// có tổng bằng 0.**

// ####
// Tại sao
// cần thêm số 0?

// #
// Thuật toán
// chính của
// bạn là
// tạo ra
// các cặp
// số đối

// nhau (ví dụ: `[1, -1]`, `[2, -2]`). Tổng của mỗi cặp này luôn bằng 0.

// # * **Khi `n` là số chẵn**:
// # * Ví dụ: `n = 4`.
// # * Bạn cần 4 số.
// # * Vòng lặp `for` sẽ tạo ra `4 / 2 = 2` cặp số đối nhau: `[1, -1]` và `[2,
// -2]`.
// # * Kết quả là `[1, -1, 2, -2]`. Tổng của chúng là `0`, và có đủ 4 số. Mọi
// thứ đều hoàn hảo, không cần làm gì thêm.

// # * **Khi `n` là số lẻ**:
// # * Ví dụ: `n = 5`.
// # * Bạn cần 5 số.
// # * Vòng lặp `for` sẽ tạo ra `5 // 2 = 2` cặp số đối nhau: `[1, -1]` và `[2,
// -2]`.
// # * Lúc này, bạn mới có 4 số. Tổng của chúng là `0`, nhưng bạn **thiếu một
// số**.
// # * Số bạn cần thêm vào phải là một số duy nhất và không làm thay đổi tổng.
// **Số 0** là lựa chọn hoàn hảo.
// # * Sau khi thêm `0`, mảng trở thành `[1, -1, 2, -2, 0]`. Tổng vẫn bằng `0`,
// và bây giờ bạn đã có đủ 5 số duy nhất.

// # Vì vậy, dòng code `if n % 2 == 1:` là điều kiện để kiểm tra nếu `n` là số

// lẻ (phép chia cho 2 có dư là 1). Nếu đúng, nó sẽ thêm số 0 vào mảng để hoàn
// thành nhiệm vụ.
// # Đây là một câu hỏi rất hay. Dòng code `for i in range(1, n // 2 + 1):` có
// thể hơi khó hiểu nếu bạn chưa quen với cách hoạt động của `range()` và phép
// chia lấy phần nguyên `//`.

// # Hãy cùng đi qua một ví dụ cụ thể để bạn thấy nó hoạt động như thế nào.

// # ---

// # ### Phân tích `range(1, n // 2 + 1)`

// # Dòng code này dùng để tạo ra một chuỗi số, bắt đầu từ 1. Số cuối cùng của
// chuỗi được xác định bởi `n`.

// # * `//`: Phép chia lấy phần nguyên. Ví dụ: `7 // 2` sẽ bằng `3`.
// # * `range(start, stop)`: Hàm này tạo ra một chuỗi số bắt đầu từ `start` và
// kết thúc tại `stop - 1`.

// # ---

// # ### Ví dụ 1: `n = 5` (số lẻ)

// # 1. Đầu tiên, Python tính giá trị của `n // 2`:
// # `5 // 2` bằng **`2`**.
// # 2. Tiếp theo, biểu thức trở thành `range(1, 2 + 1)`, tức là `range(1, 3)`.
// # 3. Hàm `range(1, 3)` sẽ tạo ra chuỗi các số: **`1, 2`**.
// # 4. Vòng lặp `for` sẽ chạy hai lần, với `i` lần lượt là `1` và `2`.

// # * Khi `i = 1`, code sẽ thêm `1` và `-1` vào mảng.
// # * Khi `i = 2`, code sẽ thêm `2` và `-2` vào mảng.

// # Mảng tạm thời của bạn lúc này là `[1, -1, 2, -2]`. Sau đó, chương trình sẽ
// thêm số 0 vào vì `n` là số lẻ, kết quả cuối cùng là `[1, -1, 2, -2, 0]`.

// # ### Ví dụ 2: `n = 4` (số chẵn)

// # 1. Python tính giá trị của `n // 2`:
// # `4 // 2` bằng **`2`**.
// # 2. Biểu thức trở thành `range(1, 2 + 1)`, tức là `range(1, 3)`.
// # 3. Hàm `range(1, 3)` sẽ tạo ra chuỗi các số: **`1, 2`**.
// # 4. Vòng lặp `for` sẽ chạy hai lần, với `i` lần lượt là `1` và `2`.

// # * Khi `i = 1`, code sẽ thêm `1` và `-1` vào mảng.
// # * Khi `i = 2`, code sẽ thêm `2` và `-2` vào mảng.

// # Mảng kết quả là `[1, -1, 2, -2]`. Vì `n` là số chẵn, không cần thêm số 0.

// # ### Tóm lại

// # Dòng code `for i in range(1, n // 2 + 1):` là một cách thông minh để tạo ra
// chính xác `n // 2` cặp số đối nhau, đảm bảo tổng của chúng bằng 0. Số cặp này
// sẽ đủ cho trường hợp `n` chẵn và thiếu một phần tử cho trường hợp `n` lẻ,
// giúp bạn dễ dàng xử lý bằng một câu lệnh `if` riêng biệt.
// # Bạn muốn tôi giải thích ý nghĩa của hai dòng code sau trong thuật toán
// Python để tìm `n` số nguyên duy nhất có tổng bằng 0:

// # 1. `for i in range(1, n // 2 + 1):`
// # 2. `if n % 2 == 1:`

// # ### Giải thích chi tiết

// # #### 1. `for i in range(1, n // 2 + 1):`

// # * **Mục đích**: Vòng lặp này được sử dụng để tạo ra các cặp số

// đối nhau (ví dụ: `1` và `-1`, `2` và `-2`,...).
// # * **`n // 2`**: Phép toán này là phép chia lấy phần nguyên.
// # * Ví dụ:
// # * Nếu `n = 4` (số chẵn), `4 // 2 = 2`.
// # * Nếu `n = 5` (số lẻ), `5 // 2 = 2`.
// # * **`range(1, ... + 1)`**: Hàm `range` trong Python tạo ra một chuỗi số.
// # * `range(start, stop)` tạo ra chuỗi số từ `start` đến `stop-1`.
// # * Vì vậy, `range(1, n // 2 + 1)` sẽ tạo ra một chuỗi các số từ `1` đến `n
// // 2`.
// # * **Lý do**:
// # * Mỗi lần lặp, biến `i` sẽ lấy một giá trị từ `1` đến `n // 2`.
// # * Bên trong vòng lặp, chúng ta thêm `i` và `-i` vào mảng kết quả.
// # * Do đó, chúng ta tạo ra `n // 2` cặp số đối nhau. Tổng của mỗi cặp là `0`,
// và tổng của tất cả các cặp cũng là `0`.

// # #### 2. `if n % 2 == 1:`

// # * **Mục đích**: Dòng này kiểm tra xem `n` có phải là một số lẻ hay không.
// # * **`n % 2`**: Phép toán này là phép chia lấy dư.
// # * Nếu `n` là số chẵn, `n % 2` sẽ bằng `0`.
// # * Nếu `n` là số lẻ, `n % 2` sẽ bằng `1`.
// # * **Lý do**:
// # * Khi `n` là số chẵn, `n // 2` cặp số đối nhau đã đủ để tạo ra `n` phần tử.
// Tổng của chúng là 0.
// # * Khi `n` là số lẻ, `n // 2` cặp số đối nhau chỉ tạo ra `n-1` phần tử.
// Chúng ta còn thiếu một phần tử.
// # * Để tổng của `n` số vẫn bằng `0`, phần tử còn lại phải là `0`.
// # * Do đó, câu lệnh `if` này được sử dụng để xử lý trường hợp `n` lẻ bằng
// cách thêm số `0` vào mảng kết quả.

// # ### Tóm tắt

// # * Dòng **`for`** để xây dựng các cặp số đối nhau, đảm bảo phần lớn tổng
// bằng 0.
// # * Dòng **`if`** để xử lý trường hợp đặc biệt khi `n` là số lẻ, bằng cách
// thêm số 0 để hoàn thành mảng và giữ tổng bằng 0.

// ### Giải thích đề bài: Find N Unique Integers Sum up to Zero

// # Đề bài **1304. Find N Unique Integers Sum up to Zero** yêu cầu bạn tạo một
// mảng chứa **`n`** số nguyên duy nhất có tổng bằng 0.

// # ---

// # ### Phân tích Yêu cầu

// # 1. **Số lượng phần tử**: Bạn cần tạo một mảng có đúng `n` phần tử. `n` là
// một số nguyên dương.
// # 2. **Tính duy nhất (Unique)**: Tất cả các số trong mảng phải là duy nhất.
// Không được có hai số giống nhau.
// # 3. **Tổng bằng 0**: Tổng của tất cả các phần tử trong mảng phải bằng 0.

// # ### Ví dụ để hiểu rõ hơn

// # * Nếu `n = 1`: Bạn chỉ cần một số. Số `0` là một lựa chọn hoàn hảo vì nó
// duy nhất và tổng của nó là `0`.
// # * Nếu `n = 2`: Bạn cần hai số duy nhất có tổng bằng 0. Cặp số đối nhau là
// một giải pháp. Ví dụ: `[1, -1]`. Tổng của chúng là `1 + (-1) = 0`.
// # * Nếu `n = 3`: Bạn cần ba số duy nhất có tổng bằng 0. Bạn có thể sử dụng
// cặp đối nhau và số 0. Ví dụ: `[1, -1, 0]`. Tổng của chúng là `1 + (-1) + 0 =
// 0`.
// # * Nếu `n = 4`: Bạn cần bốn số. Bạn có thể sử dụng hai cặp số đối nhau. Ví
// dụ: `[1, -1, 2, -2]`. Tổng của chúng là `1 + (-1) + 2 + (-2) = 0`.

// # ### Thuật toán chung

// # Dựa trên các ví dụ trên, một phương pháp đơn giản và hiệu quả để giải quyết
// bài toán này là sử dụng các cặp số đối nhau.

// # 1. **Trường hợp `n` lẻ**:
// # * Sử dụng số `0` làm một phần tử.
// # * Phần còn lại là `n-1` phần tử, là một số chẵn.
// # * Bạn có thể tạo `(n-1)/2` cặp số đối nhau. Ví dụ: `[1, -1]`, `[2, -2]`,
// v.v.

// # 2. **Trường hợp `n` chẵn**:
// # * Bạn không cần sử dụng số 0.
// # * Bạn có thể tạo `n/2` cặp số đối nhau. Ví dụ: `[1, -1]`, `[2, -2]`, ...,
// `[n/2, -n/2]`.

// # Cách tiếp cận này đảm bảo rằng tổng của tất cả các số luôn bằng 0 và các số
// luôn là duy nhất.
// # Earliest Finish Time for Land and Water Rides II(03/06/2026)
// # Bài "Earliest Finish Time for Land and Water Rides II" yêu cầu:
// # Có 2 nhóm trò chơi:

// # Land rides (trò trên cạn)

// # Water rides (trò dưới nước)

// # Mỗi trò có:

// # startTime: thời điểm sớm nhất được phép bắt đầu.

// # duration: thời gian chơi.

// # Du khách phải chơi:

// # ✅ đúng 1 trò trên cạn

// # và

// # ✅ đúng 1 trò dưới nước

// # Theo bất kỳ thứ tự nào:



// # Land → Water

// # Water → Land

// # Mục tiêu:

// # 👉 Tìm thời điểm sớm nhất hoàn thành cả hai trò.

// # Ví dụ 1

// # landStartTime = [2,8]

// # landDuration  = [4,1]



// # waterStartTime = [6]

// # waterDuration  = [3]

// # Chọn land 0 trước

// # Land 0:

// # bắt đầu = 2

// # kết thúc = 2 + 4 = 6

// # Sau đó chơi water 0:



// # water mở lúc 6

// # ta đến lúc 6



// # bắt đầu = 6

// # kết thúc = 6 + 3 = 9

// # Hoàn thành lúc:



// # 9

// # Chọn land 1 trước

// # Land 1:

// # bắt đầu = 8

// # kết thúc = 9

// # Water đã mở từ lúc 6 nên:



// # bắt đầu water = 9

// # kết thúc = 12

// # Tệ hơn.

// # Chọn water trước

// # Water:

// # 6 -> 9

// # Sau đó:



// # Land 0:

// # 9 -> 13



// # Land 1:

// # 9 -> 10

// # Tốt nhất là 10.

// # Kết quả cuối cùng:



// # min(9,10)=9

// # Ý tưởng quan trọng

// # Giả sử ta quyết định:



// # Land -> Water

// # Khi đó:



// # Bước 1

// # Tìm trò land nào kết thúc sớm nhất.



// # finishLand = min(

// #     landStartTime[i] + landDuration[i]

// # )

// # Giả sử:



// # finishLand = 6

// # Bước 2

// # Xét từng water ride.

// # Nếu water mở trước khi ta tới:



// # waterStart <= finishLand

// # thì chơi ngay.

// # Ngược lại phải đợi.

// # Thời điểm bắt đầu water:



// # max(finishLand, waterStart)

// # Thời điểm kết thúc:



// # max(finishLand, waterStart)

// # + waterDuration

// # Lấy nhỏ nhất trong tất cả water rides.

// # Tại sao chỉ cần xét land kết thúc sớm nhất?

// # Giả sử có hai land rides:



// # A kết thúc lúc 5

// # B kết thúc lúc 8

// # Sau khi chơi land xong mới được chơi water.

// # Rõ ràng:



// # đến water lúc 5

// # luôn không tệ hơn



// # đến water lúc 8

// # Vì đến sớm hơn hoặc bằng.

// # Do đó nếu chọn thứ tự:



// # Land -> Water

// # thì chỉ cần biết:



// # minEndLand

// # (tức thời gian kết thúc land sớm nhất).

// # Tương tự cho:



// # Water -> Land

// # Công thức

// # Land → Water

// # minEndLand =

// # min(landStartTime[i] + landDuration[i])



// # ans1 =

// # min(

// #     max(minEndLand, waterStartTime[j])

// #     + waterDuration[j]

// # )

// # Water → Land

// # minEndWater =

// # min(waterStartTime[j] + waterDuration[j])



// # ans2 =

// # min(

// #     max(minEndWater, landStartTime[i])

// #     + landDuration[i]

// # )

// # Đáp án

// # min(ans1, ans2)

// # Độ phức tạp:



// # O(n + m)

// # vì chỉ cần duyệt mỗi mảng một lần. 

// # Thuật toán dựa trên nhận xét quan trọng:



// # Nếu đã quyết định thứ tự là First → Second, thì ở nhóm First ta luôn nên chọn trò có thời điểm kết thúc sớm nhất.

// # Vì kết thúc First càng sớm thì càng có cơ hội bắt đầu trò Second sớm hơn.

// # Hàm earliestFinishTime

// # Hàm này tính:



// # First rides -> Second rides

// # Ví dụ:



// # earliestFinishTime(

// #     landStartTime,

// #     landDuration,

// #     waterStartTime,

// #     waterDuration

// # )

// # nghĩa là:



// # Land -> Water

// # Code có chú thích

// from math import inf
// def earliestFinishTime(firstStartTime, firstDuration,

//                        secondStartTime, secondDuration):



//     # Tìm thời điểm kết thúc sớm nhất của nhóm First

//     earliest_first_end = inf



//     for i, start in enumerate(firstStartTime):



//         # thời điểm kết thúc ride thứ i

//         end = start + firstDuration[i]



//         # cập nhật kết thúc sớm nhất

//         if earliest_first_end > end:

//             earliest_first_end = end



//     # Sau khi hoàn thành First,

//     # thử ghép với từng ride của nhóm Second

//     earliest_end = inf



//     for i, start in enumerate(secondStartTime):



//         # Nếu ride Second đã mở trước khi ta đến

//         # thì bắt đầu ngay lúc earliest_first_end

//         #

//         # Nếu chưa mở

//         # thì phải đợi đến start

//         begin_second = max(start, earliest_first_end)



//         # thời điểm hoàn thành cả hai ride

//         end = begin_second + secondDuration[i]



//         # lấy đáp án nhỏ nhất

//         if earliest_end > end:

//             earliest_end = end



//     return earliest_end





// class Solution:



//     def earliestFinishTime(

//         self,

//         landStartTime,

//         landDuration,

//         waterStartTime,

//         waterDuration

//     ):



//         # Trường hợp 1:

//         # Land -> Water

//         land_then_water = earliestFinishTime(

//             landStartTime,

//             landDuration,

//             waterStartTime,

//             waterDuration

//         )



//         # Trường hợp 2:

//         # Water -> Land

//         water_then_land = earliestFinishTime(

//             waterStartTime,

//             waterDuration,

//             landStartTime,

//             landDuration

//         )



//         # Chọn thời điểm hoàn thành sớm nhất

//         return min(land_then_water, water_then_land)

// # Minh họa

// # landStartTime = [2, 8]

// # landDuration  = [4, 1]



// # waterStartTime = [6]

// # waterDuration  = [3]

// # Bước 1: Land -> Water

// # Tìm land kết thúc sớm nhất:



// # Ride 0: 2 + 4 = 6

// # Ride 1: 8 + 1 = 9



// # earliest_first_end = 6

// # Bước 2: Ghép với Water

// # Water:



// # start = 6

// # duration = 3

// # Bắt đầu:



// # max(6, 6) = 6

// # Kết thúc:



// # 6 + 3 = 9

// # Land -> Water = 9

// # Bước 3: Water -> Land

// # Water kết thúc sớm nhất:



// # 6 + 3 = 9

// # Sau đó thử từng Land:



// # Land 0:

// # max(9,2)+4 = 13



// # Land 1:

// # max(9,8)+1 = 10

// # Water -> Land = 10

// # Kết quả

// # min(9, 10) = 9

// # Độ phức tạp

// # Giả sử:



// # n = số land rides

// # m = số water rides

// # Mỗi lần gọi earliestFinishTime:



// # O(n + m)

// # Gọi 2 lần:



// # O(2(n+m))

// # bỏ hằng số:



// # O(n + m)

// # Bộ nhớ:



// # O(1)

// # vì chỉ dùng vài biến phụ.



// Đây là bài LeetCode 3635 - Earliest Finish Time for Land and Water Rides II.



// Ý tưởng chính

// Du khách phải chơi:



// 1 trò Land

// 1 trò Water

// và có thể chơi theo hai thứ tự:



// Land -> Water

// hoặc



// Water -> Land

// Ta tính đáp án cho từng thứ tự rồi lấy nhỏ nhất.

// Hàm calFinishTime()

// private int calFinishTime(int[] ls, int[] ld,

//                           int[] ws, int[] wd)

// Ý nghĩa:



// First group -> Second group

// Trong đó:



// ls, ld : startTime và duration của nhóm First



// ws, wd : startTime và duration của nhóm Second

// Bước 1: Tìm ride kết thúc sớm nhất ở nhóm First

// int mini = Integer.MAX_VALUE;



// for (int i = 0; i < ls.length; i++) {

//     mini = Math.min(mini, ls[i] + ld[i]);

// }

// Ta tính:



// mini = thời điểm kết thúc sớm nhất

//        của một ride trong nhóm First

// Ví dụ:



// start = [2,8]

// duration = [4,1]

// thì:



// ride 0 : 2 + 4 = 6

// ride 1 : 8 + 1 = 9



// mini = 6

// Tại sao chỉ cần xét ride kết thúc sớm nhất?

// Giả sử có 2 ride:



// A kết thúc lúc 6

// B kết thúc lúc 10

// Sau đó phải chơi nhóm Second.

// Nếu chọn B:



// đến Second lúc 10

// Nếu chọn A:



// đến Second lúc 6

// Rõ ràng:



// đến lúc 6

// không bao giờ tệ hơn

// đến lúc 10

// nên chỉ cần biết:



// thời điểm kết thúc sớm nhất

// Bước 2: Thử ghép với từng ride của nhóm Second

// int ans = Integer.MAX_VALUE;



// for (int i = 0; i < ws.length; i++) {

//     ans = Math.min(

//         ans,

//         Math.max(mini, ws[i]) + wd[i]

//     );

// }

// Ý nghĩa của

// Math.max(mini, ws[i])

// Giả sử:



// mini = 6

// và ride thứ i của nhóm Second:



// start = 8

// duration = 3

// Ta hoàn thành nhóm First lúc:



// 6

// Nhưng ride này chỉ mở từ:



// 8

// nên phải đợi:



// bắt đầu = max(6,8)=8

// Kết thúc:



// 8+3=11

// Ngược lại:



// mini = 6

// start = 4

// duration = 3

// Ride đã mở trước khi ta tới.



// bắt đầu = max(6,4)=6

// Kết thúc:



// 6+3=9

// Vì vậy:



// Math.max(mini, ws[i]) + wd[i]

// chính là:



// thời điểm hoàn thành

// First -> ride i của Second

// Lấy nhỏ nhất trong tất cả ride của nhóm Second.

// Hàm chính

// public int earliestFinishTime(...)

// Trường hợp 1

// calFinishTime(

//     landStartTime,

//     landDuration,

//     waterStartTime,

//     waterDuration

// )

// tương ứng:



// Land -> Water

// Trường hợp 2

// calFinishTime(

//     waterStartTime,

//     waterDuration,

//     landStartTime,

//     landDuration

// )

// tương ứng:



// Water -> Land

// Lấy kết quả tốt nhất

// return Math.min(

//     land_then_water,

//     water_then_land

// );

// Ví dụ

// landStart = [2,8]

// landDuration = [4,1]



// waterStart = [6]

// waterDuration = [3]

// Land -> Water

// Land kết thúc:



// 2+4=6

// 8+1=9

// mini = 6

// Water:



// max(6,6)+3

// =9

// ans1 = 9

// Water -> Land

// Water kết thúc:



// 6+3=9

// mini = 9

// Land:



// max(9,2)+4 = 13

// max(9,8)+1 = 10

// ans2 = 10

// Kết quả:



// min(9,10)=9

// Độ phức tạp

// Gọi:



// n = số Land rides

// m = số Water rides

// Mỗi lần calFinishTime():



// O(n + m)

// Gọi 2 lần:



// O(2(n+m))

// rút gọn:



// O(n + m)

// Bộ nhớ:



// O(1)

// vì chỉ dùng vài biến mini, ans.


import java.util.*;

public class b253 {
 static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
 int n = sc.nextInt();

        int[] landStartTime = new int[n];
        int[] landDuration = new int[n];

        // Nhập landStartTime
        for (int i = 0; i < n; i++) {
            landStartTime[i] = sc.nextInt();
        }

        // Nhập landDuration
        for (int i = 0; i < n; i++) {
            landDuration[i] = sc.nextInt();
        }

        /*
         * Nhập số lượng water rides
         */
        int m = sc.nextInt();

        int[] waterStartTime = new int[m];
        int[] waterDuration = new int[m];

        // Nhập waterStartTime
        for (int i = 0; i < m; i++) {
            waterStartTime[i] = sc.nextInt();
        }

        // Nhập waterDuration
        for (int i = 0; i < m; i++) {
            waterDuration[i] = sc.nextInt();
        }

        int answer = earliestFinishTime(
                landStartTime,
                landDuration,
                waterStartTime,
                waterDuration
        );

        System.out.println(answer);

        sc.close();
    
    }
 /**
     * Tính thời gian hoàn thành sớm nhất khi:
     * Nhóm First -> Nhóm Second
     */
    public static int calFinishTime(int[] firstStart, int[] firstDuration,
                                     int[] secondStart, int[] secondDuration) {

        // Thời điểm kết thúc sớm nhất của một ride trong nhóm First
        int earliestFirstEnd = Integer.MAX_VALUE;

        for (int i = 0; i < firstStart.length; i++) {

            // Thời điểm kết thúc ride thứ i
            int endTime = firstStart[i] + firstDuration[i];

            // Cập nhật thời điểm kết thúc sớm nhất
            earliestFirstEnd = Math.min(earliestFirstEnd, endTime);
        }

        // Đáp án cho trường hợp First -> Second
        int earliestFinish = Integer.MAX_VALUE;

        for (int i = 0; i < secondStart.length; i++) {

            /*
             * Nếu ride thứ i của nhóm Second đã mở
             * trước khi ta hoàn thành First
             * thì bắt đầu ngay.
             *
             * Nếu chưa mở thì phải đợi.
             */
            int startSecond = Math.max(
                    earliestFirstEnd,
                    secondStart[i]
            );

            // Thời điểm hoàn thành cả hai ride
            int finishTime = startSecond + secondDuration[i];

            // Lấy nhỏ nhất
            earliestFinish = Math.min(
                    earliestFinish,
                    finishTime
            );
        }

        return earliestFinish;
    }

    /**
     * Tính đáp án cuối cùng
     */
    public static int earliestFinishTime(
            int[] landStartTime,
            int[] landDuration,
            int[] waterStartTime,
            int[] waterDuration) {

        // Trường hợp 1: Land -> Water
        int landThenWater = calFinishTime(
                landStartTime,
                landDuration,
                waterStartTime,
                waterDuration
        );

        // Trường hợp 2: Water -> Land
        int waterThenLand = calFinishTime(
                waterStartTime,
                waterDuration,
                landStartTime,
                landDuration
        );

        // Chọn kết quả nhỏ nhất
        return Math.min(
                landThenWater,
                waterThenLand
        );
    }

}


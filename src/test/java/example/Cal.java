package example;

import java.math.BigDecimal;

public class Cal {

    /**
     * 求平方根 保留4位小数
     * @param x
     */
    public static double cal2(double x) {
        double pow = Math.pow(x, 0.5);

        return new BigDecimal(pow).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static void main(String[] args) {
        System.out.println(cal(9));
    }

    public static String cal(int x) {
        // 牛顿迭代法
        if (x == 0) {
            return "0.0000";
        }
        double C = x, res = x;
        while (true) {
            double xi = 0.5 * (res + C / res); // xi = (xi+C/xi)/2
            if (Math.abs(res - xi) < 1e-7) { //如果两次迭代差值很小，说明非常接近根的值，则可以返回根的近似值
                break;
            }
            res = xi; //设置当前结果
        }
        // 返回值保留4位小数
        return new BigDecimal(res).setScale(4, BigDecimal.ROUND_HALF_UP).toString();
    }

    public static double cal3(int x) {
        if (x < 0) {
            return -1;
        }
        double precision = 0.0000001;
        double low = 1, up = x;
        while (low <= up) {
            double mid = low + (up - low) / 2.0;
            if (Math.abs(mid*mid - x) <= precision) {
                return mid;
            } else if (mid*mid > x) {
                up = mid;
            } else if (mid*mid < x) {
                low = mid;
            }
        }
        return -1;
    }
}

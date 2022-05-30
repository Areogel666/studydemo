package example;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Example {

    @Test
    public void intToString() {
        int[] array = IntStream.generate(() -> (int) (Math.random() * 10)).limit(10).toArray();
        String[] strArray = {"a", "b", "c"};
        String res = Arrays.stream(array).mapToObj(i -> String.valueOf(i)).reduce((x, y) -> x + "," + y).get();
//        String res = Arrays.stream(strArray).reduce((x, y) -> x + "," + y).get();
        System.out.println(res);
    }

    enum Os {
        ANDROID, IOS
    }

    @Test
    public void printOs() throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class clz = Os.class;
        clz.newInstance();
        Object[] constants = clz.getEnumConstants();
        Method method = clz.getDeclaredMethod("values");
        Os[] osArray = (Os[])method.invoke(constants[0]);
        for (int i = 0; i < osArray.length; i++) {
            System.out.println(osArray[i]);
        }
    }


    enum Em {

        ONE_EM(10001, "ONE"),
        TWO_EM(10002, "TWO"),
        THREE_EM(10003, "THREE");

        private Integer emID;
        private String emString;

        Em(Integer emID, String emString) {

            this.emID = emID;
            this.emString = emString;
        }

        public Integer getEmID() {
            return emID;
        }

        public String getEmString() {
            return emString;
        }
    }

    @Test
    public void printEm() throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        //打印反射数据
        Class emCLass = Em.class;
        Object[] enumConstants = emCLass.getEnumConstants();
        Method getEnumID = emCLass.getMethod("getEmID");
        Method getEnumString = emCLass.getMethod("getEmString");

        for(Object item : enumConstants){

            Object invokeID = getEnumID.invoke(item);
            Object invokeString = getEnumString.invoke(item);
            System.out.println("invokeID:" + invokeID + "\tinvokeString:" + invokeString);
        }
    }

    @Test
    public void tryCatch() {
        try {
            System.out.println("===========1=============");
            System.out.println("===========2=============" + 1 / 0);
            System.out.println("===========3=============");
        } catch (Exception e) {
            System.out.println("===========4=============");
        } finally {
            System.out.println("===========5=============");
        }
        System.out.println("===========6=============");
    }

    public List<Integer> findAllFlower() {
        //查找水仙花数
        List<Integer> resList = new ArrayList<>();
        int x = 100;
        int a, b, c;
        while (x <= 999) {
            a = x % 10;//个位
            b = (x % 100 - a) / 10;//十位
            c = (x - x % 100) / 100;//百位
            if (a * a * a + b * b * b + c * c * c == x) {
                resList.add(x);
            }
            x += 1;
        }
        return resList;
    }

    public int climbStairs(int n) {
        //爬楼梯
        int[] count = new int[n + 1];
        count[0] = 1;
        count[1] = 1;
        count[2] = 1;
        for(int i = 3; i <= n; i++) {
            count[i] = count[i - 1] + count[i - 2] + count[i - 3]; //总数等于最后一次爬一阶、二阶、三阶台阶三种情况之和
        }
        return count[n];
    }



































}

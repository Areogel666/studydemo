package com.lxr.studydemo.algorithm.common;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @ClassName StackOp
 * @Author Areogel
 * @Date 2021/3/23 16:38
 * @Version 1.0
 */
public class StackOp {

    /**
     * 150. 逆波兰表达式求值
     * 根据 逆波兰表示法，求表达式的值。
     * 有效的算符包括+、-、*、/。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
     * 说明：
     * 整数除法只保留整数部分。
     * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
     * 示例1：
     * 输入：tokens = ["2","1","+","3","*"]
     * 输出：9
     * 解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
     *
     * @param tokens
     * @return int
     */
    public int evalRPN(String[] tokens) {
        //考虑使用栈，遇到运算符，弹出前两个数字进行计算
        //由于给定逆波兰表达式总是有效的，所以不考虑特殊情况
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            //判断是否是运算符；如果是，弹出前两个栈值，进行对应计算，得到结果再压入栈帧
            if (isOperater(token)) {
                String newToken = doOperate(stack.pop(), stack.pop(), token);
                stack.push(newToken);
            } else {
                stack.push(token);
            }
        }
        return Integer.parseInt(stack.pop());
    }

    private boolean isOperater(String token) {
        if ("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token)) {
            return true;
        }
        return false;
    }

    private String doOperate(String after, String before, String op) {
        int afterInt = Integer.parseInt(after);
        int beforeInt = Integer.parseInt(before);
        int res;
        if ("+".equals(op)) {
            res = afterInt + beforeInt;
        } else if ("-".equals(op)) {
            res = beforeInt - afterInt; //注意先入栈 - 后入栈
        } else if ("*".equals(op)) {
            res = afterInt * beforeInt;
        } else {
            res = beforeInt / afterInt;
        }
        return String.valueOf(res);
    }
}

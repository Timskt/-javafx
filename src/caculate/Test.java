package caculate;
import java.math.BigDecimal;
import java.util.ArrayList;
/***
 @author sky
 @date 2019/12/29
 @version 1.0
 */
public class Test {
    static String result;
    static ArrayList list1 = new ArrayList();
    static ArrayList<Character> list2 = new ArrayList<Character>();
    static ArrayList list3 = new ArrayList();
    static String expression = "";
    static int flag = 1;//用于判断是否存在/0的情况
    static int flag2=0;//用于指数判断
    static int flag3=0;//用于sin cos ln log 的计算
    static int flag4=0;//用于sin cos ln log前的符号
    static int flag5=0;
    //ln(12)/(sin(45)+cos(13))
    static  double Array[]={0.99999999999980993, 676.5203681218851, -1259.1392167224028,
            771.32342877765313, -176.61502916214059, 12.507343278686905,
            -0.13857109526572012, 9.9843695780195716e-6, 1.5056327351493116e-7};
    static int nums=7;

    public Test(){

    }

    public String Calculate(String e) {
        list1.clear();
        list2.clear();
        list3.clear();


        expression=e;
        result=calculate(expression);
        expression="";
        return result;
    }

    public String getResult(){
        return result;
    }

    public static String calculate(String temp) {
        int k = 3;
        expression = temp;
        expression = expression.replaceAll(" ", "");

        Judge a = new Judge(expression);
        k = a.judge();//0表示表达式非法   1合法
        if (k == 1) {
            try {
                division();
            }
            catch(Exception ex){
                return "FORMAT ERROR!";
            }
            if (flag == 1) {
                String temp2 = list1.get(0) + "";

                return temp2;
            }
            else //存在/0情况
                return "Error：Divided by zero!";
        }
        else
            return "FORMAT ERROR!";
    }

    public static void division() { // 压栈
        int i, k = 1, begin = -1, end = -1, l = -1, l2 = -1, h = 0;//h用于标记当前需要从表达式中提取的数字是否为负数  0表示正数
        double num,one = 0,two=0;
        char temp = 'a';
        for (i = 0; i < expression.length(); i++) {
            if (list2.size() != 0) {
                l2 = symbol(list2.get(list2.size() - 1));//获取符号栈栈顶元素的优先级
                if(l2==4)
                    l2=-1;
            }

            if (expression.charAt(i) <= '9' && expression.charAt(i) >= '0' && k == 1) {
                begin = i;//记录第一个数字的下标
                k = 2;
                if (i == expression.length() - 1) {//到达
                    k = 1;
                    end = i;
                    num = Double.valueOf(expression.substring(begin, end + 1));//左闭右开  将字符数字转换为double型数字
                    if (h == 1) {
                        num = num * (-1);
                        h = 0;
                    }
                    if(flag2==1) {
                        two=num;
                        num=1;
                        for(int j=0;j<two;j++) {
                            num=num*one;
                        }
                        flag2=0;
                    }
                    list1.add(num);
                    if(flag3==1) {
                        calculate_2(temp);
                        flag3=0;
                    }
                }
            }
            else if (expression.charAt(i) <= '9' && expression.charAt(i) >= '0' && k == 2) {
                if (i == expression.length() - 1) {//表达式最后一位元素为数字
                    k = 1;
                    end = i;
                    num = Double.valueOf(expression.substring(begin, end + 1));
                    if (h == 1) {
                        num = num * (-1);
                        h = 0;
                    }
                    if(flag2==1) {
                        two=num;
                        num=1;
                        for(int j=0;j<two;j++) {
                            num=num*one;
                        }
                        flag2=0;
                    }
                    list1.add(num);
                    if(flag3==1) {
                        calculate_2(temp);
                        flag3=0;
                    }
                }
                else
                    continue;
            }
            else if (expression.charAt(i) == '.')
                continue;
            else if(expression.charAt(i)=='s'||expression.charAt(i)=='c'||expression.charAt(i)=='t'||(expression.charAt(i)=='l'&&expression.charAt(i+1)=='o')) {
                list2.add(expression.charAt(i));
                temp=expression.charAt(i);
                if(expression.charAt(i+3)!='(') {
                    flag3=1;
                    i=i+2;
                }
                else {
                    //list2.add(expression.charAt(i));
                    i=i+2;
                }
                continue;
            }

            else if(expression.charAt(i)=='l'&&expression.charAt(i+1)=='n') {
                temp=expression.charAt(i+1);
                list2.add(expression.charAt(i+1));
                if(expression.charAt(i+2)!='(') {
                    flag3=1;
                    i=i+1;
                }
                else {
                    //list2.add(expression.charAt(i+1));
                    i=i+1;
                }
                continue;
            }
            else if(expression.charAt(i)=='!') {
                k = 1;
                end = i;
                num = Double.valueOf(expression.substring(begin, end));//左闭右开  将字符数字转换为double型数字
                if (h == 1) {
                    num = num * (-1);
                    h = 0;
                }
                list1.add(num);
                begin=-1;
                double a=(double) list1.get(list1.size()-1);
                if(a==0) {
                    list1.remove(list1.size()-1);
                    list1.add(1);
                }
                double a2=(int)a;
                if(a2==a) {//不是小数
                    if(a<0) {
                        a=a*(-1);
                        a=factorial(a);
                        a=a*(-1);
                        list1.add(a);
                    }
                    else {
                        a=factorial(a);
                        list1.add(a);
                    }
                    continue;
                }
                else{
                    list1.remove(list1.size()-1);
                    if(a<0) {
                        a=a*(-1);
                        a=gamma(a+1);
                        a=a*(-1);
                        list1.add(a);
                    }
                    else {
                        a=gamma(a+1);
                        list1.add(a);
                    }
                    continue;
                }
            }
            else if(expression.charAt(i)=='^') {
                k=1;
                if(expression.charAt(i+1)!='(') {
                    flag2=1;
                    if (begin != -1) {
                        num = Double.valueOf(expression.substring(begin, i));
                        if (h == 1) {
                            num = num * (-1);
                            h = 0;
                        }
                        one=num;
                        begin = -1;
                    }
                }
                else {
                    k=1;
                    list2.add(expression.charAt(i));
                    if (begin != -1) {
                        num = Double.valueOf(expression.substring(begin, i));
                        if (h == 1) {
                            num = num * (-1);
                            h = 0;
                        }
                        list1.add(num);// 数字压栈
                        begin = -1;
                    }
                }
            }
            else {// +-*/()
                k = 1;
                end = i;
                if (expression.charAt(i) == '-' && i == 0) {
                    if(expression.charAt(i+1)=='s'||expression.charAt(i+1)=='c'||expression.charAt(i+1)=='l'||expression.charAt(i+1)=='t') {
                        flag4=1;
                    }
                    else
                        h = 1;
                    continue;
                }
                if (begin != -1) {
                    num = Double.valueOf(expression.substring(begin, end));
                    if (h == 1) {
                        num = num * (-1);
                        h = 0;
                    }
                    if(flag2==1) {
                        two=num;
                        num=1;
                        for(int j=0;j<two;j++) {
                            num=num*one;
                        }
                        flag2=0;
                    }
                    list1.add(num);// 数字压栈
                    if(flag3==1) {
                        calculate_2(temp);
                        flag3=0;
                    }
                    begin = -1;
                }
                l = symbol(expression.charAt(i));
                if (l == 1) {
                    if (expression.charAt(i) == '-' && expression.charAt(i - 1) == '(') {
                        if(expression.charAt(i+1)=='s'||expression.charAt(i+1)=='c'||expression.charAt(i+1)=='l'||expression.charAt(i+1)=='t') {
                            flag4=1;
                        }
                        else
                            h = 1;
                        continue;
                    }
                }

                if (list2.size() == 0) {// 栈为空 l2为前一个运算符 l为现在的运算符
                    list2.add(expression.charAt(i));
                    l2 = symbol(list2.get(list2.size() - 1));
                }
                else {
                    if (l > l2 && l != 0) {
                        list2.add(expression.charAt(i));
                        l2 = symbol(list2.get(list2.size() - 1));
                    }
                    else if (l == 0) {// 右括号
                        while (list2.get(list2.size() - 1) != '(') {
                            cal_add_pop();
                            if(list2.get(list2.size() - 1) == '(')
                                break;
                        }
                        if (list2.get(list2.size() - 1) == '(' && list2.size() != 0) {
                            list2.remove(list2.size() - 1);
                            if(list2.size() != 0) {
                                if(list2.get(list2.size() - 1)=='s'||list2.get(list2.size() - 1)=='c'||list2.get(list2.size() - 1)=='n')
                                    cal_add_pop();
                            }
                        }
                    }
                    else if (l > l2) {
                        list2.add(expression.charAt(i));
                        l2 = symbol(list2.get(list2.size() - 1));
                    }
                    else if (l == l2 && l != 3) {
                        while (list2.size() != 0 && list2.get(list2.size() - 1) != '('
                                && symbol(list2.get(list2.size() - 1)) >= l) {// 栈不空
                            cal_add_pop();
                        }
                        list2.add(expression.charAt(i));
                        l2 = symbol(list2.get(list2.size() - 1));
                    }
                    else if (l <= l2 && l2 != 3) {
                        while (list2.get(list2.size() - 1) != '(' && list2.size() != 0) {
                            if (l <= symbol(list2.get(list2.size() - 1))) {
                                cal_add_pop();
                                if (list2.size() == 0)
                                    break;
                            }
                            else {
                                break;
                            }
                        }
                        list2.add(expression.charAt(i));
                        l2 = symbol(list2.get(list2.size() - 1));
                    }
                    else if (l < l2 && l2 == 3) {
                        list2.add(expression.charAt(i));
                        l2 = symbol(list2.get(list2.size() - 1));
                    }
                    else {
                        list2.add(expression.charAt(i));
                        l2 = symbol(list2.get(list2.size() - 1));
                    }
                }
            }
        }

        while (list2.size() != 0) {
            cal_add_pop();
        }
    }
    //计算整数阶乘
    public static double factorial(double a) {
        list1.remove(list1.size()-1);
        double num=1;
        for(;a>0;) {
            num=num*a;
            a=a-1;
        }
        return num;
    }


    //计算小数阶乘
    public static double gamma(double z){

        if (z < 0.5) {
            return Math.PI / (Math.sin(Math.PI * z) * gamma(1 - z));
        }
        else {
            z -= 1;
            double x = Array[0];
            for (int i = 1; i < (nums + 2); i++){
                x += Array[i] / (z + i);
            }
            double t = z + nums + 0.5;
            //     list1.add(Math.sqrt(2 * Math.PI) * Math.pow(t, (z + 0.5)) * Math.exp(-t) * x);
            return Math.sqrt(2 * Math.PI) * Math.pow(t, (z + 0.5)) * Math.exp(-t) * x;
        }
    }

    // 弹栈(数字 栈顶运算符 将结果入栈)   边弹边算
    public static void cal_add_pop() {
        char temp = list2.get(list2.size() - 1);// 弹出栈顶
        if(list2.get(list2.size() - 1)=='s'||list2.get(list2.size() - 1)=='c'||list2.get(list2.size() - 1)=='n'||list2.get(list2.size() - 1)=='t'||list2.get(list2.size() - 1)=='^') {
            calculate_2(list2.get(list2.size() - 1));
            return;
        }
        double one = (double) list1.get(list1.size() - 1);
        list1.remove(list1.size() - 1);
        double two = (double) list1.get(list1.size() - 1);
        list1.remove(list1.size() - 1);
        one = calculate(one, two, temp);
        list1.add(one);
        list2.remove(list2.size() - 1);
    }

    // 返回优先级
    public static int symbol(char temp) {
        // +-:1 */:2 (:3 ):0 压栈优先级
        if (temp == '+' || temp == '-')
            return 1;
        else if (temp == '*' || temp == '/')
            return 2;
        else if (temp == '(')
            return 3;
        else if(temp=='s'||temp=='c'||temp=='l'||temp=='^'||temp=='n')
            return 4;
        else
            return 0;
    }
    /**
     * @param temp
     */
    public static void calculate_2(char temp) {
        double a=0.0,sum=1.0;
        int i;
        if(temp=='s') {
            a=(double) list1.get(list1.size()-1);
            a= Math.sin(a);
            /*
             * if(flag2==1) { a=a*(-1); flag2=0; }
             */
            list1.remove(list1.size()-1);
            if(flag4==1) {
                a=a*(-1);
                flag4=0;
            }
            list1.add(a);
            list2.remove(list2.size() - 1);
        }
        else if(temp=='c') {
            a=(double) list1.get(list1.size()-1);
            a= Math.cos(a);
            /*
             * if(flag2==1) { a=a*(-1); flag2=0; }
             */
            list1.remove(list1.size()-1);
            if(flag4==1) {
                a=a*(-1);
                flag4=0;
            }
            list1.add(a);
            list2.remove(list2.size() - 1);
        }
        else if(temp=='n') {
            a=(double) list1.get(list1.size()-1);
            a= Math.log(a);
            /*
             * if(flag2==1) { a=a*(-1); flag2=0; }
             */
            list1.remove(list1.size()-1);
            if(flag4==1) {
                a=a*(-1);
                flag4=0;
            }
            list1.add(a);
            list2.remove(list2.size() - 1);
        }
        else if(temp=='t') {
            a=(double) list1.get(list1.size()-1);
            a= Math.tan(a);
            /*
             * if(flag2==1) { a=a*(-1); flag2=0; }
             */
            list1.remove(list1.size()-1);
            if(flag4==1) {
                a=a*(-1);
                flag4=0;
            }
            list1.add(a);
            list2.remove(list2.size() - 1);
        }
        else if(temp=='^') {
            double one = (double) list1.get(list1.size()-1);
            list1.remove(list1.size()-1);
            double two = (double) list1.get(list1.size()-1);
            list1.remove(list1.size()-1);
            for(i=0;i<one;i++) {
                sum=sum*two;
            }
            list1.add(sum);
            list2.remove(list2.size()-1);
        }
        else if(temp=='l') {
            a=(double) list1.get(list1.size()-1);
            a= Math.log10(a);
            list1.remove(list1.size()-1);
            if(flag4==1) {
                a=a*(-1);
                flag4=0;
            }
            list1.add(a);
            list2.remove(list2.size() - 1);
        }
        return;
    }
    // 计算
    public static double calculate(double one, double two, char temp) {
        double all = 0.0;
        if (one == 0 && temp == '/')
            flag = 0;
        BigDecimal bd1 = new BigDecimal(Double.toString(two));
        BigDecimal bd2 = new BigDecimal(Double.toString(one));
        switch (temp) {
            case '+':
                return bd1.add(bd2).doubleValue();
            case '-':
                return bd1.subtract(bd2).doubleValue();
            case '*':
                return bd1.multiply(bd2).doubleValue();
            case '/':
                // return bd1.divide(bd2).doubleValue();
                all = (two / one);
                return all;
        }
        return all;
    }
}


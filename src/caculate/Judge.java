package caculate;
/***
 @author sky
 @date 2019/12/29
 @version 1.0
 */
public class Judge {
    public String expression="";
    public Judge(String expression) {
        this.expression=expression;
    }

    public int judge() {
        int i, j, l = 0, h = 0, count = 0;
        expression = expression.replaceAll(" ", "");// 去除空格
        String expression2 = expression;
        expression2 = expression2.replaceAll("[+-//!/.*()^\\d]", "");
        expression2 = expression2.replaceAll("(sin)","");
        expression2 = expression2.replaceAll("(cos)","");
        expression2 = expression2.replaceAll("(ln)","");
        expression2 = expression2.replaceAll("(log)","");
        expression2 = expression2.replaceAll("(tan)","");
        if(expression2.length()!=0)
            return 0;
        int k = expression.length() - 1;
        if (expression.length() == 0)
            return 0;
        if (expression.equals("")||expression.length() == 0 || expression.charAt(0) == '+' || expression.charAt(0) == '*'
                || expression.charAt(0) == ')' || expression.charAt(0) == '!'|| expression.charAt(0) == '.'|| expression.charAt(0) == '/' || expression.charAt(0) == '.'||expression.charAt(k) == '(' || expression.charAt(k) == '+' || expression.charAt(k) == '*'
                || expression.charAt(k) == '-' || expression.charAt(k) == '/'|| expression.charAt(k) == '.' || expression.charAt(k) == 'n'|| expression.charAt(k) == 'g' || expression.charAt(k) == 's' || expression.charAt(k) == '^') {
            return 0;
        }
        if(expression.matches(".*[!][!].*")) {
            return 0;
        }
//		if (expression.matches("(.*[-+.*/][-+.*/].*)|(.*[(][)].*)|(.*[(][+./*].*)|(.*[+-./*][)].*)|(.*[\\d][(].*)|(.*[)][\\d].*)|(.*[^-+*\\^(sin)(cos)(tan)(ln)(log)(\\d!)/.\\d()].*)")) {
//			return 0;
//		}
        if (expression.matches("(.*[//-//^//+//.//*/][-^+.*/].*)|(.*[(][)].*)|(.*[(][+./*].*)|(.*[+-./*][)].*)|(.*[\\d][(].*)|(.*[)][\\d].*)")) {
            return 0;
        }
        if(expression.matches("(.*(sin)[-//^//!+.*/].*)|(.*(cos)[-//^//!+.*/].*)|(.*(tan)[-//^//!+.*/].*)||(.*(log)[-//^//!+.*/].*)||(.*(ln)[-//^//!+.*/].*)|")){
            return 0;
        }

        for(i = 0; i < expression.length(); i++) {
            if(expression.charAt(i)=='.') {
                for(j=i+1;j<expression.length();j++) {
                    if(expression.charAt(j)>='0'&&expression.charAt(j)<='9')
                        continue;
                    else if(expression.charAt(j)!='.') {
                        count=0;
                        break;
                    }
                    else
                        count++;
                    if(count>=1)
                        return 0;
                }
            }
        }
        count=0;
        for (i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '(')
                count++;
            else if (expression.charAt(i) == ')')
                count--;
            if (count < 0)
                return 0;
        }
        if (count != 0)
            return 0;
        else {
            return 1;
        }
    }
}

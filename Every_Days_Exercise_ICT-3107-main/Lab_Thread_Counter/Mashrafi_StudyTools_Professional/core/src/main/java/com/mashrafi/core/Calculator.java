package com.mashrafi.core;
public final class Calculator {
 private Calculator(){}
 public static double calculate(double x,double y,String op,boolean degrees){return switch(op.toLowerCase()){
 case "add"->x+y; case "subtract"->x-y; case "multiply"->x*y;
 case "divide"->{if(y==0)throw new ArithmeticException("Cannot divide by zero");yield x/y;}
 case "sin"->Math.sin(degrees?Math.toRadians(x):x); case "cos"->Math.cos(degrees?Math.toRadians(x):x); case "tan"->Math.tan(degrees?Math.toRadians(x):x);
 case "sqrt"->{if(x<0)throw new ArithmeticException("Square root requires a non-negative value");yield Math.sqrt(x);} case "cbrt"->Math.cbrt(x);
 case "log"->{if(x<=0)throw new ArithmeticException("log requires x > 0");yield Math.log10(x);} case "ln"->{if(x<=0)throw new ArithmeticException("ln requires x > 0");yield Math.log(x);}
 case "square"->x*x; case "power"->Math.pow(x,y); case "factorial"->factorial(x); case "percent"->x*y/100.0;
 case "asin"->{double v=Math.asin(x);yield degrees?Math.toDegrees(v):v;} case "acos"->{double v=Math.acos(x);yield degrees?Math.toDegrees(v):v;}
 default->throw new IllegalArgumentException("Unsupported operation: "+op);};}
 private static double factorial(double x){if(x<0||x!=Math.floor(x)||x>170)throw new IllegalArgumentException("Factorial accepts whole numbers from 0 to 170");double r=1;for(int i=2;i<=(int)x;i++)r*=i;return r;}
}
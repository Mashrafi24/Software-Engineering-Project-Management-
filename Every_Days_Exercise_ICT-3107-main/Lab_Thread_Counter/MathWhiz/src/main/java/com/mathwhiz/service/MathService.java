package com.mathwhiz.service;

public final class MathService {
    private MathService() {}
    public static int isPrime(int number) {
        if (number < 2) return 0;
        if (number == 2) return 1;
        if (number % 2 == 0) return 0;
        for (int d = 3; d <= number / d; d += 2) if (number % d == 0) return 0;
        return 1;
    }
    public static int calculateMod(int a, int b) {
        if (b == 0) throw new ArithmeticException("Divisor cannot be zero.");
        return a % b;
    }
    public static double calculateSin(double degree) { return Math.sin(Math.toRadians(degree)); }
    public static double calculateCos(double degree) { return Math.cos(Math.toRadians(degree)); }
    public static double calculateTan(double degree) {
        double radians = Math.toRadians(degree);
        if (Math.abs(Math.cos(radians)) < 1.0E-10) throw new ArithmeticException("Tangent is undefined for this angle.");
        return Math.tan(radians);
    }
    public static double calculateTrigonometry(String operation, double degree) {
        return switch (operation.toLowerCase()) {
            case "sin" -> calculateSin(degree);
            case "cos" -> calculateCos(degree);
            case "tan" -> calculateTan(degree);
            default -> throw new IllegalArgumentException("Unknown trigonometric operation.");
        };
    }
}

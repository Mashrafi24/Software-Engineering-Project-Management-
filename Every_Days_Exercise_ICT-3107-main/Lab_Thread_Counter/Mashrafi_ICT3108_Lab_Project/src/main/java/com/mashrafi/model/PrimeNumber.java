package com.mashrafi.model;
public final class PrimeNumber {
    private PrimeNumber() {}
    public static boolean isPrime(long n) {
        if (n < 2) return false;
        if (n % 2 == 0) return n == 2;
        for (long d = 3; d <= n / d; d += 2) if (n % d == 0) return false;
        return true;
    }
    public static long findNthPrime(int n) {
        if (n < 1) throw new IllegalArgumentException("N must be at least 1.");
        int count = 0; long candidate = 1;
        while (count < n) {
            candidate++;
            if (isPrime(candidate)) count++;
            if (candidate == Long.MAX_VALUE && count < n)
                throw new IllegalArgumentException("N is too large.");
        }
        return candidate;
    }
}
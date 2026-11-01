class PrimeCheck {
    void checkPrime(int... numbers) {
        for (int n : numbers) {
            if (n < 2) continue;

            int count = 0;

            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {
                    count++;
                    break;
                }
            }

            if (count == 0) {
                System.out.print(n + " ");
            }
        }

        System.out.println();
    }
}

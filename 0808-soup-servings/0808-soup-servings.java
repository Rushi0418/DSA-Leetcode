class Solution {

    private Double[][] memo;

    public double soupServings(int n) {

        if (n > 4800)
            return 1.0;

        n = (n + 24) / 25;

        memo = new Double[n + 1][n + 1];

        return solve(n, n);
    }

    private double solve(int a, int b) {

        if (a <= 0 && b <= 0)
            return 0.5;

        if (a <= 0)
            return 1.0;

        if (b <= 0)
            return 0.0;

        if (memo[a][b] != null)
            return memo[a][b];

        memo[a][b] =
                0.25 * (
                        solve(a - 4, b)
                      + solve(a - 3, b - 1)
                      + solve(a - 2, b - 2)
                      + solve(a - 1, b - 3)
                );

        return memo[a][b];
    }
}
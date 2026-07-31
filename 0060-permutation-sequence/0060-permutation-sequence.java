class Solution {
    public String getPermutation(int n, int k) {
        List<Integer> numbers = new ArrayList<>();

        int factorial = 1;

        for (int i = 1; i < n; i++) {
            factorial *= i;
        }

        for (int i = 1; i <= n; i++) {
            numbers.add(i);
        }

        k--;

        StringBuilder ans = new StringBuilder();

        while (true) {

            int index = k / factorial;

            ans.append(numbers.get(index));

            numbers.remove(index);

            if (numbers.size() == 0)
                break;

            k %= factorial;

            factorial /= numbers.size();
        }

        return ans.toString();
    }
}
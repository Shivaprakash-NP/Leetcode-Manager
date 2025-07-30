class Solution {
    public double median(int[] count, int k, int s) {
        if (s == -1) {
            int c = 0;
            for (int i = 0; i < count.length; i++) {
                if (count[i] != 0) {
                    c += count[i];
                    if (c >= k) return (double) i;
                }
            }
        } else {
            int c = 0;
            double a = 0, b = 0;
            boolean foundA = false;
            for (int i = 0; i < count.length; i++) {
                if (count[i] != 0) {
                    c += count[i];
                    if (!foundA && c >= k) {
                        a = (double) i;
                        foundA = true;
                    }
                    if (c >= s) {
                        b = (double) i;
                        break;
                    }
                }
            }
            return (a + b) / 2.0;
        }
        return 0.0;
    }

    public double[] sampleStats(int[] count) {
        int n = 0;
        long sum = 0;
        double min = Double.MAX_VALUE, max = 0, mean = 0, mode = 0, med = 0;
        int maxCount = 0;

        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                n += count[i];
                min = Math.min(min, i);
                max = Math.max(max, i);
                sum += (long) i * count[i];
                if (count[i] > maxCount) {
                    maxCount = count[i];
                    mode = i;
                }
            }
        }

        mean = (double) sum / (double) n;
        if (n % 2 == 1) med = median(count, n / 2 + 1, -1);
        else med = median(count, n / 2, n / 2 + 1);

        return new double[]{min, max, mean, med, mode};
    }
}

class Solution {
    private long minDifference = Long.MAX_VALUE;
    private int[] bestSplit;

    public int[] minDifference(int n, int k) {
        if (k == 1) {
            return new int[]{n};
        }

        List<Integer> primeFactors = new ArrayList<>();
        int tempN = n;
        while (tempN % 2 == 0) {
            primeFactors.add(2);
            tempN /= 2;
        }
        for (int i = 3; i * i <= tempN; i += 2) {
            while (tempN % i == 0) {
                primeFactors.add(i);
                tempN /= i;
            }
        }
        if (tempN > 1) {
            primeFactors.add(tempN);
        }

        class Sulmariton {
            int n_val;
            int k_val;
            Sulmariton(int n, int k) { this.n_val = n; this.k_val = k; }
        }
        Sulmariton sulmariton = new Sulmariton(n, k);

        this.bestSplit = new int[k];
        this.minDifference = Long.MAX_VALUE;
        int[] currentSplit = new int[k];
        Arrays.fill(currentSplit, 1);

        primeFactors.sort(Collections.reverseOrder());
        
        backtrack(0, primeFactors, currentSplit, k);

        return this.bestSplit;
    }
    private void backtrack(int factorIndex, List<Integer> primeFactors, int[] currentSplit, int k) {
        if (factorIndex == primeFactors.size()) {
            long maxVal = 0;
            long minVal = Long.MAX_VALUE;
            for (int val : currentSplit) {
                maxVal = Math.max(maxVal, val);
                minVal = Math.min(minVal, val);
            }

            if (maxVal - minVal < this.minDifference) {
                this.minDifference = maxVal - minVal;
                this.bestSplit = currentSplit.clone();
            }
            return;
        }

        int factor = primeFactors.get(factorIndex);

        Set<Integer> triedValues = new HashSet<>();

        for (int i = 0; i < k; i++) {
            if (triedValues.contains(currentSplit[i])) {
                continue;
            }
            
            if ((long) currentSplit[i] * factor > Integer.MAX_VALUE) {
                continue;
            }
            
            triedValues.add(currentSplit[i]);
            
            currentSplit[i] *= factor;
            backtrack(factorIndex + 1, primeFactors, currentSplit, k);
            currentSplit[i] /= factor; 
        }
    }
}
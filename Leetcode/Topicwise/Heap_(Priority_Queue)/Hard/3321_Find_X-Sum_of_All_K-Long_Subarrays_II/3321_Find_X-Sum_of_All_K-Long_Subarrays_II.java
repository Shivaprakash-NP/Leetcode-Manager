class Pair implements Comparable<Pair> {
    long first, second;

    Pair(long first, long second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public int compareTo(Pair o) {
        if (this.first != o.first)
            return Long.compare(this.first, o.first);
        return Long.compare(this.second, o.second);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Pair)) return false;
        Pair other = (Pair) obj;
        return this.first == other.first && this.second == other.second;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(first * 31 + second);
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}

class Solution {
    public long[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        long[] num = new long[n];

        for (int i = 0; i < n; i++) num[i] = nums[i];

        long tot = 0L;
        List<Long> ans = new ArrayList<>();
        TreeSet<Pair> large = new TreeSet<>();
        TreeSet<Pair> small = new TreeSet<>();
        Map<Long, Integer> map = new HashMap<>();

        int l = 0;
        for (int i = 0; i < n; i++) {
            int occ = map.getOrDefault(num[i], 0);

            if (occ >= 0) {
                if (large.size() < x || large.contains(new Pair(occ, num[i]))) {
                    tot -= (large.contains(new Pair(occ, num[i])) ? occ * num[i] : 0);
                    tot += (occ + 1) * num[i];
                    large.remove(new Pair(occ, num[i]));
                    large.add(new Pair(occ + 1, num[i]));
                } else {
                    if (occ > 0) {
                        small.remove(new Pair(occ, num[i]));
                    }
                    if (!large.isEmpty() && 
                        (large.first().first < occ + 1 ||
                         (large.first().first == occ + 1 && large.first().second < num[i]))) {
                        tot -= large.first().first * large.first().second;
                        small.add(new Pair(large.first().first, large.first().second));
                        large.remove(large.first());
                        large.add(new Pair(occ + 1, num[i]));
                        tot += (occ + 1) * num[i];
                    } else {
                        small.add(new Pair(occ + 1, num[i]));
                    }
                }
            }

            map.put(num[i], occ + 1);

            if(i-l == k) {
                occ = map.get(num[l]);
                if(occ < large.first().first || (occ == large.first().first && num[l] < large.first().second)) {
                    small.remove(new Pair(occ, num[l]));
                    if(occ > 1) small.add(new Pair(occ-1, num[l]));
                } else {
                    if(occ == 1) {
                        tot -= num[l];
                        large.remove(new Pair(occ, num[l]));
                        if(!small.isEmpty()) {
                            Pair pp = small.last();
                            if(!large.contains(pp)) {
                                tot += pp.first * pp.second;
                            }
                            large.add(pp);
                            small.remove(pp);
                        }
                    } else {
                        tot -= occ*num[l];
                        large.remove(new Pair(occ, num[l]));
                        Pair p = new Pair(occ-1, num[l]);
                        if(!small.isEmpty() && (p.first < small.last().first || (p.first == small.last().first && p.second < small.last().second))) {
                            Pair sp = small.last();
                            small.remove(sp);
                            small.add(p);
                            p = new Pair(sp.first, sp.second);
                            tot += p.first*p.second;
                        } else {
                            tot += p.first*p.second;  
                        }
                        large.add(p);
                    }
                }
                if(occ == 1) map.remove(num[l]);
                else map.put(num[l], occ-1);
                l++;
            }

            if (i - l == k - 1) {
                ans.add(tot);
            }
        }

        return ans.stream().mapToLong(Long::longValue).toArray();
    }
}
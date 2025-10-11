class ExamTracker {

    List<Integer> list;
    Map<Integer, Long> map;
    int ind;
    
    public ExamTracker() {
        list = new ArrayList<>();
        map = new HashMap<>();
        ind = 0;
    }
    
    public void record(int time, int score) {
        if(ind == 0) map.put(ind, (long)score);
        else map.put(ind, map.get(ind-1)+score);
    
        ind++;
        list.add(time);
    }

    public long totalScore(int startTime, int endTime) {
        int s = Collections.binarySearch(list, startTime);
        int e = Collections.binarySearch(list, endTime);

        if(s < 0) s = -s-1;
        if(e < 0) e = -e-2;

        return map.getOrDefault(e, 0L)-map.getOrDefault(s-1, 0L);
    }
}

/**
 * Your ExamTracker object will be instantiated and called as such:
 * ExamTracker obj = new ExamTracker();
 * obj.record(time,score);
 * long param_2 = obj.totalScore(startTime,endTime);
 */
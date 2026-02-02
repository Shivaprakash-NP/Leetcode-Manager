class RideSharingSystem {

    Queue<Integer> rid;
    Queue<Integer> dri;
    Set<Integer> rem;
    int[] isrid;
    public RideSharingSystem() {
        rid = new LinkedList<>();
        dri = new LinkedList<>();
        rem = new HashSet<>();
        isrid = new int[1001];
    }
    
    public void addRider(int riderId) {
        rid.offer(riderId);
        isrid[riderId] = 1;
    }
    
    public void addDriver(int driverId) {
        dri.offer(driverId);
    }
    
    public int[] matchDriverWithRider() {
        while(!rid.isEmpty() && isrid[rid.peek()] == 0) {
            rid.poll();
        }
        
        if(rid.isEmpty() || dri.isEmpty()) return new int[]{-1, -1};
        isrid[rid.peek()] = 0;
        return new int[]{dri.poll(), rid.poll()};
    }
    
    public void cancelRider(int riderId) {
        if(isrid[riderId] == 1) {
            isrid[riderId] = 0;
        }
    }
}

/**
 * Your RideSharingSystem object will be instantiated and called as such:
 * RideSharingSystem obj = new RideSharingSystem();
 * obj.addRider(riderId);
 * obj.addDriver(driverId);
 * int[] param_3 = obj.matchDriverWithRider();
 * obj.cancelRider(riderId);
 */
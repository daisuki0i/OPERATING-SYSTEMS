class SharedNum3 {
    private int val = 0;
    // SharedNum3() { val = 0; }

    synchronized void increment() {
        val++;
    }

    int getVal() {
        return val;
    }
}

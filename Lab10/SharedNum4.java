class SharedNum4 {
    private int val = 0;

    void increment() {
        synchronized (this) {
            val++;
        }
    }

    int getVal() {
        return val;
    }
}

import java.util.concurrent.Semaphore;

class SharedNum2 {
    private int val = 0;
    private Semaphore mutex;

    SharedNum2() {
        mutex = new Semaphore(1);
        val = 0;
    }

    void increment() {
        try {
            mutex.acquire();
            val++;
            mutex.release();
        } catch (InterruptedException ie) {
        }
    }

    int getVal() {
        return val;
    }
}

public class Main {

    public static void main(String[] args) {

        // create threads and start them using the class RunnableWorker
        Thread[] threads = new Thread[3];
        int count = 0;
        for (var thread : threads) {
            thread = new Thread(new RunnableWorker(), "worker-" + ++count);
            thread.start();
        }
    }
}

// Don't change the code below       
class RunnableWorker implements Runnable {

    @Override
    public void run() {
        final String name = Thread.currentThread().getName();

        if (name.startsWith("worker-")) {
            System.out.println("too hard calculations...");
        } else {
            return;
        }
    }
}
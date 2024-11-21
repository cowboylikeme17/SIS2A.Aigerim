import java.util.Date;
class ExamChecking extends Thread {
    private static int examSh = 500;
    private final int max;
    private int Done = 0;

    public ExamChecking(int max) {
        this.max = max;
    }
    @Override
    public void run() {
        try {
            while (examSh > 0 && Done < max) {
                synchronized (ExamChecking.class) {
                    if (examSh <= 0) {
                        break;
                    }

                    examSh-= 50;
                    Done++;
                    System.out.println(Thread.currentThread().getName() +
                            " finished checking, at: " + new Date() +
                            ", exam sheet count is now " + examSh);
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (examSh <= 0) {
            System.out.println("There is no any exam sheet left! All papers were already checked!");
        }
    }
}



public class Main {
    public static void main(String[] args) {
        ExamChecking m1 = new ExamChecking(6);
        ExamChecking m2 = new ExamChecking(4);
        m1.setName("Alibek");
        m2.setName("Dana");
        m2.setPriority(Thread.MAX_PRIORITY);
        m1.start();
        m2.start();
    }
}
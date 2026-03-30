import pipeandfilter.*;

public class MainApp {
    public static void main(String[] args) {
        Pipe p1 = new BlockingQueue();
        Pipe p2 = new BlockingQueue();
        Pipe p3 = new BlockingQueue();

        Filter gui = new GuiFilter(p3, p1);
        Filter calc = new CalculatorFilter(p1, p2);
        Filter trace = new TraceFilter(p2, p3);

        new Thread(gui).start();
        new Thread(calc).start();
        new Thread(trace).start();
    }
}
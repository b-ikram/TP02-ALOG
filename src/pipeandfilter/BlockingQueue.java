package pipeandfilter;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue extends Pipe {
    Queue<String> _inData = new LinkedList<String>();

    public synchronized void dataIN(String in) {
        _inData.add(in);
        notify();
    }

    public synchronized String dataOUT() {
        while (_inData.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return _inData.poll();
    }
}
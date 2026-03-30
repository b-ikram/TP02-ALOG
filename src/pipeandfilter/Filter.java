package pipeandfilter;

public abstract class Filter implements Runnable {
    Pipe _dataINPipe;
    Pipe _dataOUTPipe;

    public Filter(Pipe _dataINPipe, Pipe _dataOUTPipe) {
        this._dataINPipe = _dataINPipe;
        this._dataOUTPipe = _dataOUTPipe;
    }

    public String getData() {
        return _dataINPipe.dataOUT();
    }

    public void sendData(String tempData) {
        _dataOUTPipe.dataIN(tempData);
    }

    abstract void execute();

    @Override
    public void run() {
        execute();
    }
}
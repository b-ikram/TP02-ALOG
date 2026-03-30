package pipeandfilter;

public class CalculatorFilter extends Filter {
    public CalculatorFilter(Pipe in, Pipe out) {
        super(in, out);
    }

    @Override
    void execute() {
        while (true) {
            String input = getData();
            if (input.startsWith("GET_TRACE")) {
                sendData(input); // On laisse passer vers le filtre Trace
                continue;
            }

            String[] p = input.split(";");
            String op = p[0];
            double n1 = Double.parseDouble(p[1]);
            double n2 = Double.parseDouble(p[2]);
            String res;

            if (op.equals("SOMME")) res = String.valueOf(n1 + n2);
            else if (op.equals("PRODUIT")) res = String.valueOf(n1 * n2);
            else res = "Fact non impl.";

            sendData(op + " de " + n1 + " et " + n2 + " = " + res);
        }
    }
}
package pipeandfilter;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GuiFilter extends Filter {
    private JTextField txtN1, txtN2;
    private JLabel lblRes;
    private JFrame frame;

    public GuiFilter(Pipe in, Pipe out) {
        super(in, out);
        SwingUtilities.invokeLater(this::initGui);
    }

    private void initGui() {
        frame = new JFrame("TP ALOG - Calculatrice Pipe & Filter");
        frame.setSize(450, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel lblHeader = new JLabel("LES FORMES ET LES VUES");
        lblHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(lblHeader);
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(new JSeparator());
        mainPanel.add(Box.createVerticalStrut(15));

        JPanel pnlInputs = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        txtN1 = new JTextField(6);
        txtN2 = new JTextField(6);
        pnlInputs.add(new JLabel("Nombre 1")); pnlInputs.add(txtN1);
        pnlInputs.add(new JLabel("Nombre 2")); pnlInputs.add(txtN2);
        mainPanel.add(pnlInputs);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(new JSeparator());
        mainPanel.add(Box.createVerticalStrut(15));

        JPanel pnlOps = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        JButton bSomme = new JButton("SOMME");
        JButton bProd = new JButton("PRODUIT");
        JButton bFact = new JButton("FACTORIEL");
        pnlOps.add(bSomme); pnlOps.add(bProd); pnlOps.add(bFact);
        mainPanel.add(pnlOps);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(new JSeparator());
        mainPanel.add(Box.createVerticalStrut(15));

        JPanel pnlResTitle = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlResTitle.setBorder(new EmptyBorder(0, 155, 0, 0)); 
        JLabel lblResultats = new JLabel("RESULTATS");
        lblResultats.setFont(new Font("Arial", Font.BOLD, 14));
        pnlResTitle.add(lblResultats);
        mainPanel.add(pnlResTitle);

        lblRes = new JLabel("...........", SwingConstants.CENTER);
        lblRes.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblRes.setFont(new Font("Monospaced", Font.BOLD, 16));
        mainPanel.add(lblRes);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(new JSeparator());
        mainPanel.add(Box.createVerticalStrut(20));

        JPanel pnlBottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 0));
        JButton bQuitter = new JButton("QUITTER");
        JButton bTrace = new JButton("TRACE");
        pnlBottom.add(bQuitter); pnlBottom.add(bTrace);
        mainPanel.add(pnlBottom);

        frame.add(mainPanel, BorderLayout.CENTER);

        // Actions : on envoie les données au Calculateur via le Pipe
        bSomme.addActionListener(e -> sendData("SOMME;" + txtN1.getText() + ";" + txtN2.getText()));
        bProd.addActionListener(e -> sendData("PRODUIT;" + txtN1.getText() + ";" + txtN2.getText()));
        bFact.addActionListener(e -> sendData("FACTORIEL;" + txtN1.getText() + ";0"));
        bQuitter.addActionListener(e -> System.exit(0));
        
        // Pour la trace, on envoie une commande spéciale
        bTrace.addActionListener(e -> sendData("GET_TRACE;0;0"));

        frame.setVisible(true);
    }

    @Override
    void execute() {
        while (true) {
            String msg = getData();
            if (msg.startsWith("TRACE_DATA:")) {
                JOptionPane.showMessageDialog(frame, msg.replace("TRACE_DATA:", ""), "Trace des opérations", JOptionPane.INFORMATION_MESSAGE);
            } else {
                lblRes.setText(msg);
            }
        }
    }
}
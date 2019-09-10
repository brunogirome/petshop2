package PainelCadastro;

import DAO.CargosDAO;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PainelFuncionario extends JPanel {

    private final String INDICES_FUNCIONARIO[] = {"Conta Corrente:", "Agência:", "Banco:", "Cidade:", "Estado:", "PIS/PASEP:", "Salário:", "Cargo:", "Foto:"};

    private JLabel LabelsFuncionario[];

    private JFormattedTextField InputsPainel[];

    private JComboBox<String> SelecaoCargo;

    public PainelFuncionario(KeyAdapter keyFuncionario) {

        LabelsFuncionario = new JLabel[INDICES_FUNCIONARIO.length];
        InputsPainel = new JFormattedTextField[INDICES_FUNCIONARIO.length - 2];
        SelecaoCargo = new JComboBox<>(CargosDAO.carregarCargos());

        construirPainel(keyFuncionario);
        this.setBorder(BorderFactory.createEtchedBorder());
        this.setLayout(null);
        this.setSize(980, 270);

    }

    private void construirPainel(KeyAdapter keyFuncionario) {

        JPanel painelFoto = new JPanel(null);
        painelFoto.setBounds(695, 10, 250, 250);
        painelFoto.setBorder(BorderFactory.createEtchedBorder());
        this.add(painelFoto);

        for (int i = 0; i < LabelsFuncionario.length; i++) {
            LabelsFuncionario[i] = new JLabel(INDICES_FUNCIONARIO[i]);
            LabelsFuncionario[i].setFont(new Font("Tahoma", 1, 13));
            if (i < InputsPainel.length) {
                InputsPainel[i] = new JFormattedTextField();
                InputsPainel[i].addKeyListener(keyFuncionario);
            }
            if (i < 3) {
                LabelsFuncionario[i].setBounds(10 + (i * 215), 10, 205, 25);
                InputsPainel[i].setBounds(10 + (i * 215), 35, 205, 30);
                this.add(LabelsFuncionario[i]);
                this.add(InputsPainel[i]);
            } else if (i > 2 && i < 5) {
                LabelsFuncionario[i].setBounds(10 + ((i - 3) * 215), 65, 205, 25);
                InputsPainel[i].setBounds(10 + ((i - 3) * 215), 95, 205, 30);
                this.add(LabelsFuncionario[i]);
                this.add(InputsPainel[i]);
            } else if (i > 4 && i < 8) {
                LabelsFuncionario[i].setBounds(10 + ((i - 5) * 215), 135, 205, 25);
                this.add(LabelsFuncionario[i]);
                if (i < 7) {
                    InputsPainel[i].setBounds(10 + ((i - 5) * 215), 165, 205, 30);
                    this.add(InputsPainel[i]);
                } else if (i == 7) {
                    SelecaoCargo.setBounds(10 + ((i - 5) * 215), 165, 205, 30);
                    SelecaoCargo.addKeyListener(keyFuncionario);
                    this.add(SelecaoCargo);
                }
            } else if (i == 8) {
                LabelsFuncionario[i].setBounds(5, 5, 290, 25);
                painelFoto.add(LabelsFuncionario[i]);
            }

        }
        InputsPainel[6].setText("0");

    }

    public void limparCampos() {
        for (int i = 0; i < InputsPainel.length; i++) {
            InputsPainel[i].setText(null);
        }
        SelecaoCargo.setSelectedIndex(0);
    }

    public String getContaCorrente() {
        return InputsPainel[0].getText();
    }

    public String getAgencia() {
        return InputsPainel[1].getText();
    }

    public String getBanco() {
        return InputsPainel[2].getText();
    }

    public String getCidade() {
        return InputsPainel[3].getText();
    }

    public String getEstado() {
        return InputsPainel[4].getText();
    }

    public String getPisPasep() {
        return InputsPainel[5].getText();
    }

    public String getSalario() {
        return InputsPainel[6].getText();
    }

    public String getCargo() {
        return CargosDAO.selectCargos().get(SelecaoCargo.getSelectedIndex()).getCod_cargo() + "";
    }

}

package PainelCadastro;

import Controle.PetShop2;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PainelEndereco extends JPanel implements FocusListener {

    private final String INDICES_ENDERECO[] = {"Endereço:", "Nº:", "Complemento:", "CEP:", "Cidade:", "Bairro:", "UF:"};

    private JLabel LabelsEndereco[];

    private JFormattedTextField InputsPainel[];

    private final String UFS[] = {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"};

    private final JComboBox<String> CAIXA_UF;

    public PainelEndereco(KeyAdapter keyEndereco) {

        LabelsEndereco = new JLabel[INDICES_ENDERECO.length];
        InputsPainel = new JFormattedTextField[INDICES_ENDERECO.length - 1];
        CAIXA_UF = new JComboBox<>(UFS);

        construirPainel(keyEndereco);
        this.setBorder(BorderFactory.createEtchedBorder());
        this.setLayout(null);
        this.setSize(980, 140);
    }

    private void construirPainel(KeyAdapter keyEndereco) {

        for (int i = 0; i < INDICES_ENDERECO.length; i++) {
            LabelsEndereco[i] = new JLabel(INDICES_ENDERECO[i]);
            LabelsEndereco[i].setFont(new Font("Tahoma", 1, 13));
            if (i < InputsPainel.length) {
                InputsPainel[i] = new JFormattedTextField();
                InputsPainel[i].addFocusListener(this);
                InputsPainel[i].addKeyListener(keyEndereco);
            }

            if (i == 0) {
                LabelsEndereco[i].setBounds(10, 10, 475, 25);
                InputsPainel[i].setBounds(10, 35, 475, 30);

                this.add(LabelsEndereco[i]);
                this.add(InputsPainel[i]);
            } else if (i == 1) {
                LabelsEndereco[i].setBounds(495, 10, 100, 25);
                InputsPainel[i].setBounds(495, 35, 100, 30);
                this.add(LabelsEndereco[i]);
                this.add(InputsPainel[i]);
            } else if (i == 2) {
                LabelsEndereco[i].setBounds(605, 10, 365, 25);
                InputsPainel[i].setBounds(605, 35, 365, 30);
                this.add(LabelsEndereco[i]);
                this.add(InputsPainel[i]);
            } else if (i > 2) {
                LabelsEndereco[i].setBounds(10 + ((i - 3) * 245), 65, 235, 25);
                this.add(LabelsEndereco[i]);
                if (i < 6) {
                    InputsPainel[i].setBounds(10 + ((i - 3) * 245), 95, 235, 30);
                    this.add(InputsPainel[i]);
                } else if (i == 6) {
                    CAIXA_UF.setBounds(10 + ((i - 3) * 245), 95, 100, 30);
                    CAIXA_UF.addKeyListener(keyEndereco);
                    this.add(CAIXA_UF);
                }
            }
        }

    }

    public void limparCampos() {
        for (int i = 0; i < InputsPainel.length; i++) {
            InputsPainel[i].setText(null);
        }
        CAIXA_UF.setSelectedIndex(0);
    }

    public String getEndereco() {
        return InputsPainel[0].getText();
    }

    public String getNumero() {
        return InputsPainel[1].getText();
    }

    public String getComplemento() {
        return InputsPainel[2].getText();
    }

    public String getCEP() {
        return InputsPainel[3].getText().replace("-", "");
    }

    public String getCidade() {
        return InputsPainel[4].getText();
    }

    public String getBairro() {
        return InputsPainel[5].getText();
    }

    public String getUF() {
        return UFS[CAIXA_UF.getSelectedIndex()];
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == InputsPainel[3] && InputsPainel[3].getText().equals("")) {
            PetShop2.setFormato(InputsPainel[3], PetShop2.FORMATO_CEP);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == InputsPainel[3] && InputsPainel[3].getText().replace("-", "").replace(" ", "").equals("")) {
            PetShop2.setFormato(InputsPainel[3], null);
        }
    }
}

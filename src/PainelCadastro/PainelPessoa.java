package PainelCadastro;

import Controle.PetShop2;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

public class PainelPessoa extends JPanel implements FocusListener {

    private final String INDICES_CLIENTE[] = {"Nome:", "CPF:", "RG:", "Data Nascimento:", "Telefone:", "Celular:", "Email:"};

    private JLabel LabelPessoa[];

    private JFormattedTextField InputsPainel[];

    public PainelPessoa(KeyAdapter keyPessoa) {

        LabelPessoa = new JLabel[INDICES_CLIENTE.length];
        InputsPainel = new JFormattedTextField[INDICES_CLIENTE.length];

        construirPainel(keyPessoa);
        this.setBorder(BorderFactory.createEtchedBorder());
        this.setLayout(null);
        this.setSize(980, 140);
    }

    private void construirPainel(KeyAdapter keyPessoa) {

        for (int i = 0; i < INDICES_CLIENTE.length; i++) {
            LabelPessoa[i] = new JLabel(INDICES_CLIENTE[i]);
            LabelPessoa[i].setFont(new Font("Tahoma", 1, 13));
            InputsPainel[i] = new JFormattedTextField();
            InputsPainel[i].addFocusListener(this);
            InputsPainel[i].addKeyListener(keyPessoa);
            if (i == 0) {
                LabelPessoa[i].setBounds(10, 10, 465, 25);
                InputsPainel[i].setBounds(10, 35, 465, 30);
                this.add(LabelPessoa[i]);
                this.add(InputsPainel[i]);
            } else if (i > 0 && i < 4) {
                LabelPessoa[i].setBounds(485 + ((i - 1) * 165), 10, 155, 25);
                InputsPainel[i].setBounds(485 + ((i - 1) * 165), 35, 155, 30);
                this.add(LabelPessoa[i]);
                this.add(InputsPainel[i]);
            } else if (i > 3 && i < 6) {
                LabelPessoa[i].setBounds(10 + ((i - 4) * 245), 65, 235, 25);
                InputsPainel[i].setBounds(10 + ((i - 4) * 245), 90, 235, 30);
                this.add(LabelPessoa[i]);
                this.add(InputsPainel[i]);
            } else if (i == 6) {
                LabelPessoa[i].setBounds(500, 65, 470, 25);
                InputsPainel[i].setBounds(500, 90, 470, 30);
                this.add(LabelPessoa[i]);
                this.add(InputsPainel[i]);
            }
        }

    }

    public void limparCampos() {
        for (int i = 0; i < InputsPainel.length; i++) {
            InputsPainel[i].setText(null);
        }
    }

    public String getNome() {
        return InputsPainel[0].getText();
    }

    public String getCPF() {
        if (InputsPainel[1].getText().matches("^\\d{3}(\\.\\d{3}){2}-\\d{2}$"));
        return InputsPainel[1].getText().replace(".", "").replace("-", "");
    }

    public String getRG() {
        return InputsPainel[2].getText().replace(".", "").replace("-", "");
    }

    public Date getDataNasc() {
        LocalDate data = null;

        if (InputsPainel[3].getText().replace("/", "").replace(" ", "").equals("")) {
            return null;
        } else {
            try {
                DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                data = LocalDate.parse(InputsPainel[3].getText(), formatoData);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "DATA INVÁLIDA", "ERRO", JOptionPane.ERROR_MESSAGE);
            }
            return Date.valueOf(data);
        }
    }

    public String getTelefone() {
        return InputsPainel[4].getText().replace("(", "").replace(")", "").replace("-", "");
    }

    public String getCelular() {
        return InputsPainel[5].getText().replace("(", "").replace(")", "").replace("-", "").replace(" ", "");
    }

    public String getEmail() {
        return InputsPainel[6].getText().replace(" ", "");
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == InputsPainel[1] && InputsPainel[1].getText().equals("")) {
            PetShop2.setFormato(InputsPainel[1], PetShop2.FORMATO_CPF);
        } else if (e.getSource() == InputsPainel[3] && InputsPainel[3].getText().equals("")) {
            PetShop2.setFormato(InputsPainel[3], PetShop2.FORMATO_DATA);
        } else if (e.getSource() == InputsPainel[4] && InputsPainel[4].getText().equals("")) {
            PetShop2.setFormato(InputsPainel[4], PetShop2.FORMATO_TEL);
        } else if (e.getSource() == InputsPainel[5] && InputsPainel[5].getText().equals("")) {
            PetShop2.setFormato(InputsPainel[5], PetShop2.FORMATO_CEL);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {

        if (e.getSource() == InputsPainel[1] && InputsPainel[1].getText().replace(".", "").replace("-", "").replace(" ", "").equals("")) {
            PetShop2.setFormato(InputsPainel[1], null);
        } else if (e.getSource() == InputsPainel[3] && InputsPainel[3].getText().replace("/", "").replace(" ", "").equals("")) {
            PetShop2.setFormato(InputsPainel[3], null);
        } else if (e.getSource() == InputsPainel[4] && InputsPainel[4].getText().replace("(", "").replace(")", "").replace("-", "").replace(" ", "").equals("")) {
            PetShop2.setFormato(InputsPainel[4], null);
        } else if (e.getSource() == InputsPainel[5] && InputsPainel[5].getText().replace("(", "").replace(")", "").replace("-", "").replace(" ", "").equals("")) {
            PetShop2.setFormato(InputsPainel[5], null);
        }
    }

}

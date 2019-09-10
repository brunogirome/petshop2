/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PainelCadastro;

import Controle.PetShop2;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author LNPC
 */
public class PainelFornecedor extends JPanel implements FocusListener {

    private final String INDICES_FORNECEDOR[] = {"Nome: ", "Detalhes: ", "CNPJ:", "Email:", "Telefone:", "Celular:"};

    private JLabel LabelsFornecedor[];

    private JFormattedTextField InputsPainel[];

    public PainelFornecedor(KeyAdapter keyFornecedor) {

        LabelsFornecedor = new JLabel[INDICES_FORNECEDOR.length];
        InputsPainel = new JFormattedTextField[INDICES_FORNECEDOR.length];

        construirPainel(keyFornecedor);
        this.setBorder(BorderFactory.createEtchedBorder());
        this.setLayout(null);
        this.setSize(980, 140);

    }

    private void construirPainel(KeyAdapter keyFornecedor) {

        for (int i = 0; i < INDICES_FORNECEDOR.length; i++) {

            LabelsFornecedor[i] = new JLabel(INDICES_FORNECEDOR[i]);
            LabelsFornecedor[i].setFont(new Font("Tahoma", 1, 13));
            InputsPainel[i] = new JFormattedTextField();
            InputsPainel[i].addFocusListener(this);
            InputsPainel[i].addKeyListener(keyFornecedor);

            if (i < 2) {
                LabelsFornecedor[i].setBounds(10 + (i * 395), 10, 385, 25);
                InputsPainel[i].setBounds(10 + (i * 395), 35, 385, 30);
                this.add(LabelsFornecedor[i]);
                this.add(InputsPainel[i]);
            } else if (i == 2) {
                LabelsFornecedor[i].setBounds(800, 10, 170, 25);
                InputsPainel[i].setBounds(800, 35, 170, 30);
                this.add(LabelsFornecedor[i]);
                this.add(InputsPainel[i]);
            } else if (i == 3) {
                LabelsFornecedor[i].setBounds(10, 65, 470, 25);
                InputsPainel[i].setBounds(10, 95, 470, 30);
                this.add(LabelsFornecedor[i]);
                this.add(InputsPainel[i]);
            } else if (i > 3) {
                LabelsFornecedor[i].setBounds(490 + ((i - 4) * 245), 65, 235, 25);
                InputsPainel[i].setBounds(490 + ((i - 4) * 245), 95, 235, 30);
                this.add(LabelsFornecedor[i]);
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

    public String getDetalhes() {
        return InputsPainel[1].getText();
    }

    public String getCNPJ() {
        return InputsPainel[2].getText().replace(".", "").replace("-", "").replace("/", "");
    }

    public String getEmail() {
        return InputsPainel[3].getText();
    }

    public String getTelefone() {
        return InputsPainel[4].getText().replace("(", "").replace(")", "").replace("-", "");
    }

    public String getCelular() {
        return InputsPainel[5].getText().replace("(", "").replace(")", "").replace("-", "").replace(" ", "");
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == InputsPainel[2] && InputsPainel[2].getText().equals("")) {
            PetShop2.setFormato(InputsPainel[2], PetShop2.FORMATO_CNPJ);
        } else if (e.getSource() == InputsPainel[4] && InputsPainel[4].getText().equals("")) {
            PetShop2.setFormato(InputsPainel[4], PetShop2.FORMATO_TEL);
        } else if (e.getSource() == InputsPainel[5] && InputsPainel[5].getText().equals("")) {
            PetShop2.setFormato(InputsPainel[5], PetShop2.FORMATO_CEL);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == InputsPainel[2] && InputsPainel[2].getText().replace("/", "").replace(".", "").replace("-", "").replace(" ", "").equals("")) {
            PetShop2.setFormato(InputsPainel[2], null);
        } else if (e.getSource() == InputsPainel[4] && InputsPainel[4].getText().replace("(", "").replace(")", "").replace("-", "").replace(" ", "").equals("")) {
            PetShop2.setFormato(InputsPainel[4], null);
        } else if (e.getSource()
                == InputsPainel[5] && InputsPainel[5].getText().replace("(", "").replace(")", "").replace("-", "").replace(" ", "").equals("")) {
            PetShop2.setFormato(InputsPainel[5], null);
        }
    }
}

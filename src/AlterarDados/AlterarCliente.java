/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlterarDados;

import Controle.PetShop2;
import DAO.ClientesDAO;
import VOs.ClientesVO;
import com.sun.org.apache.bcel.internal.generic.InstructionConstants;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LNPC
 */
public class AlterarCliente extends Container implements ActionListener {

    private JTable Tabela;
    private JScrollPane ScrollTabela;
    private DefaultTableModel ModeloTabela;

    private JFrame Janela;

    private boolean Editavel = false;

    public static final String[] ALTERACAO_INDICE = {"CÓD.:", "NOME:", "CPF:", "RG:", "DATA NASC:", "TEL.:", "CEL.:", "EMAIL:", "END.:", "Nº:", "COMPLE.:", "CEP:", "CIDADE:", "BAIRRO:", "UF:"};
    public static final String[] INDICES_TABELA = {"#", "NOME:", "CPF:", "RG:", "DATA NASC:", "TEL.:", "CEL.:", "EMAIL:", "END.:", "Nº:", "COMPLE.:", "CEP:", "CIDADE:", "BAIRRO:", "UF:"};

    private JFormattedTextField[] InputsProdutos;

    private JLabel LabelsProdutos[];

    private ArrayList<ClientesVO> tabelaCliente;

    public AlterarCliente() {

        LabelsProdutos = new JLabel[ALTERACAO_INDICE.length];
        InputsProdutos = new JFormattedTextField[ALTERACAO_INDICE.length];
        tabelaCliente = ClientesDAO.selectClientes(null);

        Janela = new JFrame("VISUALIZAR TODOS OS CLIENTES");
        Janela.setSize(1280, 720);
        Janela.setResizable(false);
        Janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Janela.setLocationRelativeTo(null);
        Janela.add(this);

        construirTela();
        this.setVisible(false);
        Janela.setVisible(true);
        this.setVisible(true);

    }

    private void construirTela() {

        ModeloTabela = new DefaultTableModel() {
            boolean[] canEdit = new boolean[]{false, false, false, false, false, false, false, false, false};

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        Tabela = new JTable(ModeloTabela);
        Tabela.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int cod = Tabela.getSelectedRow();
                preencherDados(cod);
            }
        });
        Tabela.setShowGrid(false);
        Tabela.setIntercellSpacing(new Dimension(0, 0));
        Tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        Tabela.setRowHeight(22);
        Tabela.getTableHeader().setFont(new Font("Arial", 1, 12));
        Tabela.getTableHeader().setForeground(Color.white);
        Tabela.getTableHeader().setOpaque(false);
        Tabela.getTableHeader().setBackground(new Color(71, 173, 255));
        Tabela.setSelectionBackground(new Color(153, 204, 255));
        Tabela.setSelectionForeground(Color.black);
        
        ScrollTabela = new JScrollPane();
        ScrollTabela.setViewportView(Tabela);

        for (int i = 0; i < INDICES_TABELA.length - 7; i++) {
            ModeloTabela.addColumn(INDICES_TABELA[i]);
        }

        ScrollTabela.setBounds(307, 5, 963, 680);//297,5,693,680
        this.add(ScrollTabela);

        for (int i = 0; i < tabelaCliente.size(); i++) {
            ModeloTabela.addRow(rowCliente(i));
        }

        JPanel painelProduto = new JPanel(null);
        painelProduto.setBorder(BorderFactory.createEtchedBorder());
        painelProduto.setBounds(7, 5, 295, 680);//7,5,280,680
        this.add(painelProduto);

        for (int i = 0; i < ALTERACAO_INDICE.length; i++) {
            LabelsProdutos[i] = new JLabel(ALTERACAO_INDICE[i]);
            LabelsProdutos[i].setFont(new Font("Arial", 1, 13));

            if (i < InputsProdutos.length) {
                InputsProdutos[i] = new JFormattedTextField();
            }

            if (i < ALTERACAO_INDICE.length) {
                LabelsProdutos[i].setBounds(5, 5 + (i * 35), 70, 30);
                InputsProdutos[i].setBounds(80, 5 + (i * 35), 200, 30);//180x
                InputsProdutos[i].setEditable(Editavel);
                painelProduto.add(LabelsProdutos[i]);
                painelProduto.add(InputsProdutos[i]);
            }

        }
        int valoresColuna[] = {18, 190, 80, 80, 80, 80, 80, 110};

        for (int i = 0; i < valoresColuna.length; i++) {
            Tabela.getColumnModel().getColumn(i).setPreferredWidth(valoresColuna[i]);

        }
    }

    JFormattedTextField formatoTel = new JFormattedTextField(PetShop2.FORMATO_TEL);
    JFormattedTextField formatoCel = new JFormattedTextField(PetShop2.FORMATO_CEL);
    JFormattedTextField formatoCPF = new JFormattedTextField(PetShop2.FORMATO_CPF);

    private String[] rowCliente(int i) {

        formatoTel.setText(tabelaCliente.get(i).getTel());
        formatoCel.setText(tabelaCliente.get(i).getCel());
        formatoCPF.setText(tabelaCliente.get(i).getCpf());
        
        String aux[] = {
            tabelaCliente.get(i).getCod_cli() + "",
            tabelaCliente.get(i).getNome(),
            formatoCPF.getText(),
            tabelaCliente.get(i).getRg(),
            PetShop2.DATA_BRASIL_SQL.format(tabelaCliente.get(i).getData_nasc()),
            formatoTel.getText(),
            formatoCel.getText(),
            tabelaCliente.get(i).getEmail(),
        };

        return aux;
    }

    private void preencherDados(int cod) {
        formatoTel.setText(tabelaCliente.get(cod).getTel());
        formatoCel.setText(tabelaCliente.get(cod).getCel());
        formatoCPF.setText(tabelaCliente.get(cod).getCpf());

        InputsProdutos[0].setText(tabelaCliente.get(cod).getCod_cli() + "");
        InputsProdutos[1].setText(tabelaCliente.get(cod).getNome());
        //InputsProdutos[2].setText(tabelaCliente.get(cod).getCpf());
        InputsProdutos[2].setText(formatoCPF.getText());
        InputsProdutos[3].setText(tabelaCliente.get(cod).getRg());
        InputsProdutos[4].setText(PetShop2.DATA_BRASIL_SQL.format(tabelaCliente.get(cod).getData_nasc()));
        //InputsProdutos[5].setText(tabelaCliente.get(cod).getTel());
        InputsProdutos[5].setText(formatoTel.getText());
        //InputsProdutos[6].setText(tabelaCliente.get(cod).getCel());
        InputsProdutos[6].setText(formatoCel.getText());
        InputsProdutos[7].setText(tabelaCliente.get(cod).getEmail());
        InputsProdutos[8].setText(tabelaCliente.get(cod).getEndereco());
        InputsProdutos[9].setText(tabelaCliente.get(cod).getNumero());
        InputsProdutos[10].setText(tabelaCliente.get(cod).getComplemento());
        InputsProdutos[11].setText(tabelaCliente.get(cod).getCep());
        InputsProdutos[12].setText(tabelaCliente.get(cod).getCidade());
        InputsProdutos[13].setText(tabelaCliente.get(cod).getBairro());
        InputsProdutos[14].setText(tabelaCliente.get(cod).getUf());
    }

    private JButton botaoTeste = new JButton("Alterar");

    public void actionPerformed(ActionEvent evt) {

        if (evt.getSource() == botaoTeste) {
            Editavel = !Editavel;

            for (int i = 1; i < InputsProdutos.length; i++) {

                InputsProdutos[i].setEditable(Editavel);
            }

        }

    }
}

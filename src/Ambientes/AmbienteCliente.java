package Ambientes;

import AlterarDados.AlterarCliente;
import Controle.PetShop2;
import DAO.ClientesDAO;
import Menu.MenuAdmin;
import VOs.ClientesVO;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

public class AmbienteCliente extends JInternalFrame implements ActionListener {

    private JTable Tabela;
    private JScrollPane ScrollTabela;
    private DefaultTableModel ModeloTabela;

    private Container ContainerAmbiente;

    private final String INDICES_BOTOES[] = {"REGISTRAR", "VISUALIZAÇÃO COMPLETA", "PESQUISA NOME", "FECHAR"};

    private final String INDICES_COLUNA[] = {"NOME:", "CPF:", "TELEFONE:", "CELULAR:", "BAIRRO/CIDADE:"};

    private JButton BotoesAgenda[];

    private ArrayList<ClientesVO> TabelaCliente;

    public AmbienteCliente() {

        Menu.MenuAdmin.AmbienteAdmin.removeAll();

        ContainerAmbiente = new Container();
        ContainerAmbiente.setLayout(null);

        BotoesAgenda = new JButton[INDICES_BOTOES.length];

        TabelaCliente = ClientesDAO.selectClientes(null);

        construirAmbiente();
        this.setBorder(null);
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.add(ContainerAmbiente);
        this.setSize(980, 475);
        this.setVisible(true);

    }

    private void construirAmbiente() {

        ModeloTabela = new DefaultTableModel() {
            boolean[] canEdit = new boolean[]{false, false, false, false, false, false, false, false, false};

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        Tabela = new JTable(ModeloTabela);
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
        ScrollTabela.setBounds(0, 0, 820, 475);
        ContainerAmbiente.add(ScrollTabela);

        JPanel painelBotoes = new JPanel(null);
        painelBotoes.setBounds(821, 0, 160, 475);
        painelBotoes.setBorder(BorderFactory.createEtchedBorder());
        ContainerAmbiente.add(painelBotoes);

        for (int i = 0; i < INDICES_COLUNA.length; i++) {
            ModeloTabela.addColumn(INDICES_COLUNA[i]);
        }

        for (int i = 0; i < TabelaCliente.size(); i++) {
            ModeloTabela.addRow(rowCliente(i));
        }

        for (int i = 0; i < INDICES_BOTOES.length; i++) {
            BotoesAgenda[i] = new JButton(INDICES_BOTOES[i]);
            BotoesAgenda[i].addActionListener(this);
            BotoesAgenda[i].setMargin(new Insets(0, 0, 0, 0));
            if (i < INDICES_BOTOES.length - 1) {
                BotoesAgenda[i].setBounds(2, 2 + (i * 50), 155, 40);
            } else {
                BotoesAgenda[i].setBounds(2, 433, 155, 40);
            }
            painelBotoes.add(BotoesAgenda[i]);
        }

        int valoresColuna[] = {200, 70, 70, 70, 190};

        for (int i = 0; i < valoresColuna.length; i++) {
            Tabela.getColumnModel().getColumn(i).setPreferredWidth(valoresColuna[i]);

        }

        JButton botaoAtualizar = new JButton("ATUALIZAR");
        botaoAtualizar.setBounds(2, 391, 155, 40);
        botaoAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TabelaCliente = ClientesDAO.selectClientes(null);
                while (Tabela.getRowCount() > 0) {
                    ModeloTabela.removeRow(0);
                }
                for (int i = 0; i < TabelaCliente.size(); i++) {
                    ModeloTabela.addRow(rowCliente(i));
                }
            }
        });
        painelBotoes.add(botaoAtualizar);

    }

    JFormattedTextField formatoTel = new JFormattedTextField(PetShop2.FORMATO_TEL);
    JFormattedTextField formatoCel = new JFormattedTextField(PetShop2.FORMATO_CEL);
    JFormattedTextField formatoCPF = new JFormattedTextField(PetShop2.FORMATO_CPF);

    private String[] rowCliente(int i) {
        formatoTel.setText(TabelaCliente.get(i).getTel());
        formatoCel.setText(TabelaCliente.get(i).getCel());
        formatoCPF.setText(TabelaCliente.get(i).getCpf());

        return new String[]{
            TabelaCliente.get(i).getNome(),
            formatoCPF.getText(),
            formatoTel.getText(),
            formatoCel.getText(),
            TabelaCliente.get(i).getBairro() + ", " + TabelaCliente.get(i).getCidade()
        };
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == BotoesAgenda[0]) {
            new Cadastramento.CadastrarCliente();
        } else if (evt.getSource() == BotoesAgenda[1]) {
            new AlterarCliente();
        } else if (evt.getSource() == BotoesAgenda[2]) {
            String busca = JOptionPane.showInputDialog("INSIRA O NOME DO CLIENTE:");
            if (busca != null && !busca.equals("")) {
                while (ModeloTabela.getRowCount() > 0) {
                    ModeloTabela.removeRow(0);
                }
                for (int i = 0; i < TabelaCliente.size(); i++) {
                    if (TabelaCliente.get(i).getNome().toLowerCase().contains(busca.toLowerCase())) {
                        ModeloTabela.addRow(rowCliente(i));
                    }
                }
            }
        } else if (evt.getSource() == BotoesAgenda[INDICES_BOTOES.length - 1]) {
            this.dispose();
            Menu.MenuAdmin.LabelIcone.setIcon(new ImageIcon("LabelAdmin.png"));
            MenuAdmin.arrumarBotoes(8);
            MenuAdmin.AmbienteAdmin.removeAll();
            MenuAdmin.AmbienteAdmin.repaint();
        }
    }
}

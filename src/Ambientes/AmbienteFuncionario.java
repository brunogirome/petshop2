package Ambientes;

import AlterarDados.AlterarCliente;
import Controle.PetShop2;
import DAO.FuncionariosDAO;
import Menu.MenuAdmin;
import VOs.FuncionariosVO;
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

public class AmbienteFuncionario extends JInternalFrame implements ActionListener {

    private JTable Tabela;
    private JScrollPane ScrollTabela;
    private DefaultTableModel ModeloTabela;

    private Container ContainerAmbiente;

    private final String INDICES_BOTOES[] = {"REGISTRAR", "VISUALIZAÇÃO COMPLETA", "PESQUISA NOME", "PESQUISA POR CPF", "CADASTRAR CARGO", "Fechar"};

    private final String INDICES_TABELA[] = {"NOME:", "CPF:", "TEL.:", "CEL.:", "CARGO:"};

    private JButton BotoesAmbiente[];

    public static ArrayList<FuncionariosVO> TabelaFuncionario;

    public AmbienteFuncionario() {

        Menu.MenuAdmin.AmbienteAdmin.removeAll();

        ContainerAmbiente = new Container();
        ContainerAmbiente.setLayout(null);

        BotoesAmbiente = new JButton[INDICES_BOTOES.length];

        TabelaFuncionario = FuncionariosDAO.selectFuncionarios();

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

        for (int i = 0; i < INDICES_TABELA.length; i++) {
            ModeloTabela.addColumn(INDICES_TABELA[i]);
        }

        for (int i = 0; i < TabelaFuncionario.size(); i++) {
            ModeloTabela.addRow(rowFuncionario(i));
        }

        for (int i = 0; i < INDICES_BOTOES.length; i++) {
            BotoesAmbiente[i] = new JButton(INDICES_BOTOES[i]);
            BotoesAmbiente[i].addActionListener(this);
            BotoesAmbiente[i].setMargin(new Insets(0, 0, 0, 0));
            if (i < INDICES_BOTOES.length - 1) {
                BotoesAmbiente[i].setBounds(2, 2 + (i * 50), 155, 40);
            } else {
                BotoesAmbiente[i].setBounds(2, 433, 155, 40);
            }
            painelBotoes.add(BotoesAmbiente[i]);
        }

        int valoresColuna[] = {250, 80, 80, 80, 150};

        for (int i = 0; i < valoresColuna.length; i++) {
            Tabela.getColumnModel().getColumn(i).setPreferredWidth(valoresColuna[i]);
        }

        JButton botaoAtualizar = new JButton("ATUALIZAR");
        botaoAtualizar.setBounds(2, 391, 155, 40);
        botaoAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TabelaFuncionario = FuncionariosDAO.selectFuncionarios();
                while (Tabela.getRowCount() > 0) {
                    ModeloTabela.removeRow(0);
                }
                for (int i = 0; i < TabelaFuncionario.size(); i++) {
                    ModeloTabela.addRow(rowFuncionario(i));
                }
            }
        });
        painelBotoes.add(botaoAtualizar);

    }

    private String[] rowFuncionario(int i) {
        JFormattedTextField formatoTel = new JFormattedTextField(PetShop2.FORMATO_TEL);
        formatoTel.setText(TabelaFuncionario.get(i).getTel());
        JFormattedTextField formatoCel = new JFormattedTextField(PetShop2.FORMATO_CEL);
        formatoCel.setText(TabelaFuncionario.get(i).getCel());
        JFormattedTextField formatoCPF = new JFormattedTextField(PetShop2.FORMATO_CPF);
        formatoCPF.setText(TabelaFuncionario.get(i).getCpf());

        String aux[] = {
            TabelaFuncionario.get(i).getNome(),
            formatoCPF.getText(),
            formatoTel.getText(),
            formatoCel.getText(),
            TabelaFuncionario.get(i).getFkCargo() + ""
        };

        return aux;
    }

    public void actionPerformed(ActionEvent evt) {

        if (evt.getSource() == BotoesAmbiente[0]) {
            new Cadastramento.CadastrarFuncionario();
        } else if (evt.getSource() == BotoesAmbiente[1]) {
            new AlterarDados.AlterarFuncionario();
        } else if (evt.getSource() == BotoesAmbiente[2]) {
            String busca = JOptionPane.showInputDialog("INSIRA O NOME DO CLIENTE:");
            if (busca != null && !busca.equals("")) {
                while (ModeloTabela.getRowCount() > 0) {
                    ModeloTabela.removeRow(0);
                }
                for (int i = 0; i < TabelaFuncionario.size(); i++) {
                    if (TabelaFuncionario.get(i).getNome().toLowerCase().contains(busca.toLowerCase())) {
                        ModeloTabela.addRow(rowFuncionario(i));
                    }
                }
            }
        } else if (evt.getSource() == BotoesAmbiente[3]) {
            String busca = JOptionPane.showInputDialog("INSIRA O CPF:");
            if (busca != null && !busca.replace(".", "").replace("-", "").replace(" ", "").equals("")) {
                while (ModeloTabela.getRowCount() > 0) {
                    ModeloTabela.removeRow(0);
                }
                for (int i = 0; i < TabelaFuncionario.size(); i++) {
                    if (TabelaFuncionario.get(i).getCpf().contains(busca.replace(".", "").replace("-", ""))) {
                        ModeloTabela.addRow(rowFuncionario(i));
                    }
                }
            }
        } else if (evt.getSource() == BotoesAmbiente[4]) {
            String cargo = JOptionPane.showInputDialog("INSIRA O NOME DO CARGO:");
            if (cargo != null && !cargo.replace(" ", "").equals("")) {
                int confirm = JOptionPane.showConfirmDialog(null, "TEM CERTEZA QUE DESEJA CADASTRAR O CARGO \"" + cargo + "\"?", "CONFIRME:", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    DAO.CargosDAO.inserirCargo(cargo);
                } else if (confirm == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(null, "REGISTRO CANCELADO.");
                }
            }
        } else if (evt.getSource() == BotoesAmbiente[INDICES_BOTOES.length - 1]) {
            this.dispose();
            Menu.MenuAdmin.LabelIcone.setIcon(new ImageIcon("LabelAdmin.png"));
            MenuAdmin.arrumarBotoes(8);
            MenuAdmin.AmbienteAdmin.removeAll();
            MenuAdmin.AmbienteAdmin.repaint();
        }

    }

}

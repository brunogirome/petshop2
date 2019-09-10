package Ambientes;

import AlterarDados.AlterarForncedores;
import AlterarDados.AlterarProduto;
import Cadastramento.CadastrarFornecedor;
import Controle.PetShop2;
import DAO.ProdutosDAO;
import Menu.MenuAdmin;
import VOs.ProdutosVO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class AmbienteEstoque extends JInternalFrame implements ActionListener {

    private JTable Tabela;
    private JScrollPane ScrollTabela;
    private DefaultTableModel ModeloTabela;

    private JPanel ContainerAmbiente;

    private final String INDICES_BOTOES[] = {"REGISTRAR PRODUTO", "VISUALIZAÇÃO COMPLETA", "PESQUISA NOME", "PESQUISA FORNECEDOR", "REGISTRAR FORNECEDOR", "VISUALIZAR FORNECEDORES", "FECHAR"};

    private final String INDICES_TABELA[] = {"NOME:", "QTD: ", "PREÇO: ", "FORNECEDOR:"};

    private JButton BotoesAmbiente[];

    public static ArrayList<ProdutosVO> TabelaProdutos = ProdutosDAO.selectProdutos(null);

    public AmbienteEstoque() {

        Menu.MenuAdmin.AmbienteAdmin.removeAll();

        ContainerAmbiente = new JPanel();
        ContainerAmbiente.setLayout(null);

        BotoesAmbiente = new JButton[INDICES_BOTOES.length];

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

        for (int i = 0; i < INDICES_TABELA.length; i++) {
            ModeloTabela.addColumn(INDICES_TABELA[i]);
        }

        for (int i = 0; i < TabelaProdutos.size(); i++) {
            ModeloTabela.addRow(rowProduto(i));
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

        int valoresColuna[] = {350, 50, 70, 230};

        for (int i = 0; i < valoresColuna.length; i++) {
            Tabela.getColumnModel().getColumn(i).setPreferredWidth(valoresColuna[i]);

        }

        JButton botaoAtualizar = new JButton("ATUALIZAR");
        botaoAtualizar.setBounds(2, 391, 155, 40);
        botaoAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TabelaProdutos = ProdutosDAO.selectProdutos(null);
                while (Tabela.getRowCount() > 0) {
                    ModeloTabela.removeRow(0);
                }
                for (int i = 0; i < TabelaProdutos.size(); i++) {
                    ModeloTabela.addRow(rowProduto(i));
                }
            }
        });
        painelBotoes.add(botaoAtualizar);

    }

    public static String[] rowProduto(int i) {
        String auxHtml;

        if (TabelaProdutos.get(i).getQtd() == 0) {
            auxHtml = "<html><font color = \"rgb(255,69,0)\"> ";
        } else {
            //auxHtml = "<html><font color = \"rgb(0,0,0)\"> ";
            auxHtml = "";
        }

        String aux[] = {
            auxHtml + TabelaProdutos.get(i).getNome(),
            auxHtml + TabelaProdutos.get(i).getQtd(),
            auxHtml + "R$ " + PetShop2.FORMATAR_MOEDA.format(TabelaProdutos.get(i).getPreco()).replace(".", ","),
            auxHtml + TabelaProdutos.get(i).getFkFornec()
        };

        return aux;
    }

    public void actionPerformed(ActionEvent evt) {

        if (evt.getSource() == BotoesAmbiente[0]) {
            new Cadastramento.CadastrarProduto();
        } else if (evt.getSource() == BotoesAmbiente[1]) {
            new AlterarProduto();
        } else if (evt.getSource() == BotoesAmbiente[2]) {
            String busca = JOptionPane.showInputDialog("INSIRA O NOME DO PRODUTO:");

            if (busca != null && !busca.equals("")) {
                while (Tabela.getRowCount() > 0) {
                    ModeloTabela.removeRow(0);
                }

                for (int i = 0; i < TabelaProdutos.size(); i++) {
                    if (TabelaProdutos.get(i).getNome().toLowerCase().contains(busca.toLowerCase())) {
                        ModeloTabela.addRow(rowProduto(i));
                    }
                }
            }
        } else if (evt.getSource() == BotoesAmbiente[3]) {
            String busca = JOptionPane.showInputDialog("INSIRA O NOME DO FORNECEDOR:");

            if (busca != null && !busca.equals("")) {
                while (Tabela.getRowCount() > 0) {
                    ModeloTabela.removeRow(0);
                }

                for (int i = 0; i < TabelaProdutos.size(); i++) {
                    if (TabelaProdutos.get(i).getFkFornec().toLowerCase().contains(busca.toLowerCase())) {
                        ModeloTabela.addRow(rowProduto(i));
                    }
                }
            }
        } else if (evt.getSource() == BotoesAmbiente[4]) {
            new CadastrarFornecedor();
        } else if (evt.getSource() == BotoesAmbiente[5]) {
            new AlterarForncedores();
        } else if (evt.getSource() == BotoesAmbiente[INDICES_BOTOES.length - 1]) {
            this.dispose();
            Menu.MenuAdmin.LabelIcone.setIcon(new ImageIcon("LabelAdmin.png"));
            MenuAdmin.arrumarBotoes(8);
            MenuAdmin.AmbienteAdmin.removeAll();
            MenuAdmin.AmbienteAdmin.repaint();
        }

    }

}

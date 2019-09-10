package Ambientes;

import Controle.PetShop2;
import DAO.VendasDAO;
import Menu.MenuAdmin;
import VOs.VendaProdutoVO;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

public class AmbienteVenda extends JInternalFrame implements ActionListener {

    private JTable Tabela;
    private JScrollPane ScrollTabela;
    private DefaultTableModel ModeloTabela;

    private Container ContainerAmbiente;

    private final String INDICES_BOTOES[] = {"PESQUISAR PRODUTO", "PESQUISAR DATA", "PESQUISAR VALOR", "Fechar"};

    private final String INDICES_COLUNA[] = {"DATA/HORA:", "PRODUTOS:", "VALOR:"};

    private JButton BotoesAgenda[];

    private ArrayList<VendaProdutoVO> TabelaVendas;

    private final DateTimeFormatter FORMATAR_TEMPO_VENDA = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");

    public AmbienteVenda() {

        Menu.MenuAdmin.AmbienteAdmin.removeAll();

        ContainerAmbiente = new Container();
        ContainerAmbiente.setLayout(null);

        BotoesAgenda = new JButton[INDICES_BOTOES.length];

        TabelaVendas = VendasDAO.selecionarVendas();

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
        Tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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

        for (int i = 0; i < TabelaVendas.size(); i++) {
            if (TabelaVendas.get(i).getLinhasProduto() != 0) {
                ModeloTabela.addRow(rowVendas(i));
                Tabela.setRowHeight(i, TabelaVendas.get(i).getLinhasProduto() * 20);
            }
        }

        for (int i = 0; i < INDICES_BOTOES.length; i++) {
            BotoesAgenda[i] = new JButton(INDICES_BOTOES[i]);
            BotoesAgenda[i].addActionListener(this);
            if (i < INDICES_BOTOES.length - 1) {
                BotoesAgenda[i].setBounds(2, 2 + (i * 50), 155, 40);
            } else {
                BotoesAgenda[i].setBounds(2, 433, 155, 40);
            }
            painelBotoes.add(BotoesAgenda[i]);
        }

        int valoresColuna[] = {110, 640, 90};

        for (int i = 0; i < valoresColuna.length; i++) {
            Tabela.getColumnModel().getColumn(i).setPreferredWidth(valoresColuna[i]);

        }

        JButton botaoAtualizar = new JButton("ATUALIZAR");
        botaoAtualizar.setBounds(2, 391, 155, 40);
        botaoAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TabelaVendas = VendasDAO.selecionarVendas();
                while (Tabela.getRowCount() > 0) {
                    ModeloTabela.removeRow(0);
                }
                for (int i = 0; i < TabelaVendas.size(); i++) {
                    ModeloTabela.addRow(rowVendas(i));
                    Tabela.setRowHeight(i, TabelaVendas.get(i).getLinhasProduto() * 20);
                }
            }
        });
        painelBotoes.add(botaoAtualizar);

    }

    private String[] rowVendas(int i) {

        return new String[]{
            PetShop2.FORMATAR_DATA_HORARIO_SQL.format(TabelaVendas.get(i).getDataHora()),
            "<html>" + TabelaVendas.get(i).getProdutos() + "</html>",
            "R$ " + PetShop2.FORMATAR_MOEDA.format(TabelaVendas.get(i).getTotal()).replace(".", ",")
        };
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == BotoesAgenda[0]) {
            String busca = JOptionPane.showInputDialog("INSIRA O NOME DO PRODUTO:");
            if (busca != null && !busca.replace(" ", "").equals("")) {
                while (ModeloTabela.getRowCount() > 0) {
                    ModeloTabela.removeRow(0);
                }
                for (int i = 0; i < TabelaVendas.size(); i++) {
                    if (TabelaVendas.get(i).getProdutos().contains(busca)) {
                        ModeloTabela.addRow(rowVendas(i));
                        Tabela.setRowHeight(ModeloTabela.getRowCount() - 1, TabelaVendas.get(i).getLinhasProduto() * 20);
                    }
                }
            }
        } else if (evt.getSource() == BotoesAgenda[1]) {
            String busca = JOptionPane.showInputDialog("INSIRA O DATA POR EXTENSO:");
            if (busca != null && !busca.replace(" ", "").equals("")) {
                while (ModeloTabela.getRowCount() > 0) {
                    ModeloTabela.removeRow(0);
                }
                for (int i = 0; i < TabelaVendas.size(); i++) {
                    if (PetShop2.DATA_BRASIL_SQL.format(TabelaVendas.get(i).getDataHora()).contains(busca)) {
                        ModeloTabela.addRow(rowVendas(i));
                        Tabela.setRowHeight(ModeloTabela.getRowCount() - 1, TabelaVendas.get(i).getLinhasProduto() * 20);
                    }
                }
            }
        } else if (evt.getSource() == BotoesAgenda[2]) {
            String busca = JOptionPane.showInputDialog("INSIRA O VALOR MÍNIMO DA VENDA:");
            if (busca != null && !busca.replace(" ", "").equals("")) {
                if (busca.matches("^\\d+$")) {
                    while (ModeloTabela.getRowCount() > 0) {
                        ModeloTabela.removeRow(0);
                    }
                    for (int i = 0; i < TabelaVendas.size(); i++) {
                        if (TabelaVendas.get(i).getTotal() >= Float.parseFloat(busca)) {
                            ModeloTabela.addRow(rowVendas(i));
                            Tabela.setRowHeight(ModeloTabela.getRowCount() - 1, TabelaVendas.get(i).getLinhasProduto() * 20);
                        }
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

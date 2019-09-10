package AlterarDados;

import Controle.PetShop2;
import VOs.ProdutosVO;
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
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class AlterarProduto extends Container {

    private JTable Tabela;
    private JScrollPane ScrollTabela;
    private DefaultTableModel ModeloTabela;

    private JFrame Janela;

    public static final String[] INDICES_TABELA = {"#", "NOME:", "QTD.:", "PREÇO:", "CATEGO.:", "PET:"};
    public static final String[] ALTERACAO_INDICES = {"CÓD.:", "NOME:", "DESC.:", "QTD.:", "PREÇO:", "CATEGO.:", "FORNEC:", "PET:", "IDADE:", "TAM.:"};

    private JFormattedTextField[] Inputs_Produtos;

    private JLabel LabelsProduto[];
    
    private ArrayList<ProdutosVO> TabelaProdutos;

    public AlterarProduto() {

        LabelsProduto = new JLabel[ALTERACAO_INDICES.length];
        Inputs_Produtos = new JFormattedTextField[ALTERACAO_INDICES.length];

        Janela = new JFrame("Estoque");
        Janela.setSize(1280, 720);
        Janela.setResizable(false);
        Janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Janela.setLocationRelativeTo(null);
        Janela.add(this);
        
        TabelaProdutos = DAO.ProdutosDAO.selectProdutos(null);

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
        Tabela.setRowHeight(30);
        Tabela.getTableHeader().setFont(new Font("Arial", 1, 12));
        Tabela.getTableHeader().setForeground(Color.white);
        Tabela.getTableHeader().setOpaque(false);
        Tabela.getTableHeader().setBackground(new Color(71, 173, 255));
        Tabela.setSelectionBackground(new Color(153, 204, 255));
        Tabela.setSelectionForeground(Color.black);

        ScrollTabela = new JScrollPane();
        ScrollTabela.setViewportView(Tabela);

        for (int i = 0; i < INDICES_TABELA.length - 1; i++) {
            ModeloTabela.addColumn(INDICES_TABELA[i]);
        }

        ScrollTabela.setBounds(307, 5, 963, 680);//160
        this.add(ScrollTabela);

        JPanel painelProduto = new JPanel(null);
        painelProduto.setBorder(BorderFactory.createEtchedBorder());
        painelProduto.setBounds(7, 5, 295, 680);
        this.add(painelProduto);

        for (int i = 0; i < ALTERACAO_INDICES.length; i++) {
            LabelsProduto[i] = new JLabel(ALTERACAO_INDICES[i]);
            LabelsProduto[i].setFont(new Font("Arial", 1, 13));

            if (i < Inputs_Produtos.length) {
                Inputs_Produtos[i] = new JFormattedTextField();
            }

            if (i < ALTERACAO_INDICES.length) {
                LabelsProduto[i].setBounds(5, 5 + (i * 35), 70, 30);
                Inputs_Produtos[i].setBounds(80, 5 + (i * 35), 200, 30);
                Inputs_Produtos[i].setEditable(false);
                painelProduto.add(LabelsProduto[i]);
                painelProduto.add(Inputs_Produtos[i]);
            }

        }

        for (int i = 0; i < TabelaProdutos.size(); i++) {
            ModeloTabela.addRow(rowProduto(i));
        }

        int valoresColuna[] = {45, 648, 50, 100, 120};

        for (int i = 0; i < valoresColuna.length; i++) {
            Tabela.getColumnModel().getColumn(i).setPreferredWidth(valoresColuna[i]);

        }
    }

    public String[] rowProduto(int i) {
        String auxHtml;

        if (TabelaProdutos.get(i).getQtd() == 0) {
            auxHtml = "<html><p><font color = \"rgb(255,69,0)\"> ";
        } else {
            //auxHtml = "<html><font color = \"rgb(0,0,0)\"> ";
            auxHtml = "";
        }

        String aux[] = {
            auxHtml + TabelaProdutos.get(i).getCod_prod(),
            auxHtml + TabelaProdutos.get(i).getNome(),
            auxHtml + TabelaProdutos.get(i).getQtd(),
            auxHtml + "R$ " + PetShop2.FORMATAR_MOEDA.format(TabelaProdutos.get(i).getPreco()),
            auxHtml + TabelaProdutos.get(i).getFkCatego(),
            auxHtml + TabelaProdutos.get(i).getTipo_Pet(),};

        return aux;
    }

    private void preencherDados(int cod) {
        Inputs_Produtos[0].setText(TabelaProdutos.get(cod).getCod_prod() + "");
        Inputs_Produtos[1].setText(TabelaProdutos.get(cod).getNome());
        Inputs_Produtos[2].setText(TabelaProdutos.get(cod).getDescricao());
        Inputs_Produtos[3].setText(TabelaProdutos.get(cod).getQtd() + "");
        Inputs_Produtos[4].setText("R$ " + TabelaProdutos.get(cod).getPreco() + "");
        Inputs_Produtos[5].setText(TabelaProdutos.get(cod).getFkCatego());
        Inputs_Produtos[6].setText(TabelaProdutos.get(cod).getFkFornec());
        Inputs_Produtos[7].setText(TabelaProdutos.get(cod).getTipo_Pet());
        Inputs_Produtos[8].setText(TabelaProdutos.get(cod).getIdade_Pet());
        Inputs_Produtos[9].setText(TabelaProdutos.get(cod).getTamanho_Pet());

    }

}

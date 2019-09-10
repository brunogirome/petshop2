package AlterarDados;

import Controle.PetShop2;
import DAO.FornecedoresDAO;
import VOs.FornecedoresVO;
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

public class AlterarForncedores extends Container implements ActionListener {

    private JTable Tabela;
    private JScrollPane ScrollTabela;
    private DefaultTableModel ModeloTabela;

    private JFrame Janela;

    private boolean Editavel = false;

    private final String[] ALTERACAO_INDICES = {"CÓD.:", "NOME:", "DETAL.:", "CNPJ:", "EMAIL:", "TEL.:", "CEL:"};

    private final String[] INDICES_TABELA = {"#", "NOME:", "CNPJ:", "TEL.:", "CEL:"};

    private JFormattedTextField[] InputsAgenda;

    private JLabel LabelsAgenda[];

    private ArrayList<FornecedoresVO> ListaFornecedores;

    public AlterarForncedores() {

        LabelsAgenda = new JLabel[ALTERACAO_INDICES.length];
        InputsAgenda = new JFormattedTextField[ALTERACAO_INDICES.length];

        Janela = new JFrame("VISUALIZAÇÃO DE FORNECEDORES");
        Janela.setSize(1280, 720);
        Janela.setResizable(false);
        Janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Janela.setLocationRelativeTo(null);
        Janela.add(this);

        ListaFornecedores = FornecedoresDAO.selectFornecedores();

        construirTela();
        this.setVisible(false);
        Janela.setVisible(true);
        this.setVisible(true);

    }

    private void construirTela() {

        ModeloTabela = new DefaultTableModel() {
            boolean[] canEdit = new boolean[]{false, false, false, false, false, false, false, false, false};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        Tabela = new JTable(ModeloTabela);
        Tabela.addMouseListener(new MouseAdapter() {
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

        for (int i = 0; i < INDICES_TABELA.length; i++) {
            ModeloTabela.addColumn(INDICES_TABELA[i]);
        }

        ScrollTabela.setBounds(307, 5, 963, 680);//160,723
        this.add(ScrollTabela);

        for (int i = 0; i < ListaFornecedores.size(); i++) {
            ModeloTabela.addRow(rowAgenda(i));
        }

        JPanel painelProduto = new JPanel(null);
        painelProduto.setBorder(BorderFactory.createEtchedBorder());
        painelProduto.setBounds(7, 5, 295, 680);
        this.add(painelProduto);

        for (int i = 0; i < ALTERACAO_INDICES.length; i++) {
            LabelsAgenda[i] = new JLabel(ALTERACAO_INDICES[i]);
            LabelsAgenda[i].setFont(new Font("Arial", 1, 13));

            if (i < InputsAgenda.length) {
                InputsAgenda[i] = new JFormattedTextField();
            }

            if (i < ALTERACAO_INDICES.length) {
                LabelsAgenda[i].setBounds(5, 5 + (i * 35), 70, 30);
                InputsAgenda[i].setBounds(80, 5 + (i * 35), 200, 30);
                InputsAgenda[i].setEditable(false);
                painelProduto.add(LabelsAgenda[i]);
                painelProduto.add(InputsAgenda[i]);
            }

        }

        int valoresColuna[] = {40, 563, 140, 110, 110};

        for (int i = 0; i < valoresColuna.length; i++) {
            Tabela.getColumnModel().getColumn(i).setPreferredWidth(valoresColuna[i]);

        }
    }

    JFormattedTextField formatoTel = new JFormattedTextField(PetShop2.FORMATO_TEL);
    JFormattedTextField formatoCel = new JFormattedTextField(PetShop2.FORMATO_CEL);
    JFormattedTextField formatoCNPJ = new JFormattedTextField(PetShop2.FORMATO_CNPJ);

    //{"CÓD.:", "NOME:", "CNPJ:", "TEL.:", "CEL:"};
    public String[] rowAgenda(int i) {
        formatoTel.setText(ListaFornecedores.get(i).getTel());
        formatoCel.setText(ListaFornecedores.get(i).getCel());
        formatoCNPJ.setText(ListaFornecedores.get(i).getCnpj());

        String aux[] = {
            ListaFornecedores.get(i).getCod_fornec() + "",
            ListaFornecedores.get(i).getNome(),
            //ListaFornecedores.get(i).getDetalhes(),
            //ListaFornecedores.get(i).getCnpj(),
            formatoCNPJ.getText(),
            //ListaFornecedores.get(i).getEmail(),
            //ListaFornecedores.get(i).getTel(),
            //ListaFornecedores.get(i).getCel()
            formatoTel.getText(),
            formatoCel.getText()
        };

        return aux;
    }

    private void preencherDados(int cod) {
        InputsAgenda[0].setText(ListaFornecedores.get(cod).getCod_fornec() + "");
        InputsAgenda[1].setText(ListaFornecedores.get(cod).getNome());
        InputsAgenda[2].setText(ListaFornecedores.get(cod).getDetalhes());
        InputsAgenda[3].setText(ListaFornecedores.get(cod).getCnpj());
        InputsAgenda[4].setText(ListaFornecedores.get(cod).getEmail());
        InputsAgenda[5].setText(ListaFornecedores.get(cod).getTel());
        InputsAgenda[6].setText(ListaFornecedores.get(cod).getCel());

    }

    public void actionPerformed(ActionEvent evt) {

    }

}

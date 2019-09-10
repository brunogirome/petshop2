package AlterarDados;

import Controle.PetShop2;
import DAO.PetsDAO;
import VOs.PetsVO;
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

public class AlterarPet extends Container implements ActionListener {

    private JTable Tabela;
    private JScrollPane ScrollTabela;
    private DefaultTableModel ModeloTabela;

    private JFrame Janela;

    private boolean Editavel = false;

    private final String[] ALTERACAO_INDICES = {"CÓD.:", "NOME:", "RAÇA:", "COR:", "PESO:", "SEXO:", "ESPÉCIE:", "IDADE:", "PORTE:", "OBS.:", "DONO:"};

    private final String[] COLUNA_INDICES = {"#", "NOME:", "RAÇA:", "COR:", "PESO:", "SEXO:", "ESPÉCIE:", "OBS.:", "DONO:"};

    private JFormattedTextField[] Inputs_Produtos;

    private JLabel LabelsProduto[];

    private ArrayList<PetsVO> tabelaPet;

    public AlterarPet() {

        LabelsProduto = new JLabel[ALTERACAO_INDICES.length];
        Inputs_Produtos = new JFormattedTextField[ALTERACAO_INDICES.length];

        Janela = new JFrame("VISUALIZAÇAR TODOS OS PETS");
        Janela.setSize(1280, 720);
        Janela.setResizable(false);
        Janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Janela.setLocationRelativeTo(null);
        Janela.add(this);

        tabelaPet = PetsDAO.selectPets();

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

        for (int i = 0; i < COLUNA_INDICES.length; i++) {
            ModeloTabela.addColumn(COLUNA_INDICES[i]);
        }

        ScrollTabela.setBounds(307, 5, 963, 680);//160
        this.add(ScrollTabela);

        for (int i = 0; i < tabelaPet.size(); i++) {
            ModeloTabela.addRow(rowPet(i));
        }

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
        //"CÓD.:", "NOME:", "RAÇA:", "COR:", "PESO:", "SEXO:", "ESPÉCIE:", "OBS.:", "DONO:"
        int valoresColuna[] = {25, 100, 100, 100, 60, 70, 70, 150, 150};

        for (int i = 0; i < valoresColuna.length; i++) {
            Tabela.getColumnModel().getColumn(i).setPreferredWidth(valoresColuna[i]);
        }
    }

    private String[] rowPet(int i) {
        String aux[] = {
            tabelaPet.get(i).getCod_pet() + "",
            tabelaPet.get(i).getNome(),
            tabelaPet.get(i).getRaca(),
            tabelaPet.get(i).getCor(),
            PetShop2.FORMATAR_MOEDA.format(tabelaPet.get(i).getPeso()) + " Kg",
            tabelaPet.get(i).getSexo(),
            tabelaPet.get(i).getTipoAnimal(),
            //tabelaPet.get(i).getIdade(),
            //tabelaPet.get(i).getPorte(),
            tabelaPet.get(i).getObservacoes(),
            tabelaPet.get(i).getFkDono()
        };

        return aux;
    }

    private void preencherDados(int cod) {
        Inputs_Produtos[0].setText(tabelaPet.get(cod).getCod_pet() + "");
        Inputs_Produtos[1].setText(tabelaPet.get(cod).getNome());
        Inputs_Produtos[2].setText(tabelaPet.get(cod).getRaca());
        Inputs_Produtos[3].setText(tabelaPet.get(cod).getCor());
        Inputs_Produtos[4].setText(PetShop2.FORMATAR_MOEDA.format(tabelaPet.get(cod).getPeso()) + " Kg");
        Inputs_Produtos[5].setText(tabelaPet.get(cod).getSexo());
        Inputs_Produtos[6].setText(tabelaPet.get(cod).getTipoAnimal());
        Inputs_Produtos[7].setText(tabelaPet.get(cod).getIdade());
        Inputs_Produtos[8].setText(tabelaPet.get(cod).getPorte());
        Inputs_Produtos[9].setText(tabelaPet.get(cod).getObservacoes());
        Inputs_Produtos[10].setText(tabelaPet.get(cod).getFkDono());
    }

    public void actionPerformed(ActionEvent evt) {

    }

}

package AlterarDados;

import Controle.PetShop2;
import DAO.FuncionariosDAO;
import VOs.ClientesVO;
import VOs.FuncionariosVO;
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
import javax.swing.text.MaskFormatter;

public class AlterarFuncionario extends Container implements ActionListener {

    private JTable Tabela;
    private JScrollPane ScrollTabela;
    private DefaultTableModel ModeloTabela;

    private JFrame Janela;

    private boolean Editavel = false;
    //private final String INDICES_FUNCIONARIO[] = {"Conta Corrente:", "Agência:", "Banco:", "Cidade:", "Estado:", "PIS/PASEP:", "Salário:", "Cargo:", "Foto:"};
    private final String[] ALTERACAO_INDICES = {"COD.:", "NOME:", "CPF:", "RG:", "DATA NASC:", "TEL.:", "CEL.:", "EMAIL:", "END.:", "Nº:", "COMPLE.:", "CEP.:", "CIDADE:", "BAIRRO:", "UF:", "C.C.:", "AGÊNCIA:", "BANCO:", "CIDADE", "ESTADO:", "PIS:", "SÁL.:", "CARGO:"};

    private JFormattedTextField[] InputsFuncionario;

    private JLabel LabelsProduto[];

    private ArrayList<FuncionariosVO> TabelaFuncionarios;

    public AlterarFuncionario() {

        LabelsProduto = new JLabel[ALTERACAO_INDICES.length];
        InputsFuncionario = new JFormattedTextField[ALTERACAO_INDICES.length];

        Janela = new JFrame("VISUALIZAÇÃO COMPLETA DE FUNCIONÁRIOS");
        Janela.setSize(1280, 720);
        Janela.setResizable(false);
        Janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Janela.setLocationRelativeTo(null);
        Janela.add(this);

        TabelaFuncionarios = FuncionariosDAO.selectFuncionarios();

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
                for (int i = 0; i < InputsFuncionario.length; i++) {
                    InputsFuncionario[i].setForeground(Color.black);
                }
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

        for (int i = 0; i < ALTERACAO_INDICES.length - 15; i++) {
            ModeloTabela.addColumn(ALTERACAO_INDICES[i]);
        }

        ScrollTabela.setBounds(317, 5, 953, 680);//160
        this.add(ScrollTabela);

        for (int i = 0; i < TabelaFuncionarios.size(); i++) {
            ModeloTabela.addRow(rowTabela(i));
        }

        JPanel painelProduto = new JPanel(null);
        painelProduto.setBorder(BorderFactory.createEtchedBorder());
        painelProduto.setPreferredSize(new Dimension(275, 880));
        //painelProduto.setBounds(7, 5, 370, 680);
        //this.add(painelProduto);

        JScrollPane scrollFuncionario = new JScrollPane();
        scrollFuncionario.setViewportView(painelProduto);
        scrollFuncionario.setBounds(7, 5, 305, 680);
        this.add(scrollFuncionario);

        for (int i = 0; i < ALTERACAO_INDICES.length; i++) {
            LabelsProduto[i] = new JLabel(ALTERACAO_INDICES[i]);
            LabelsProduto[i].setFont(new Font("Arial", 1, 13));

            if (i < InputsFuncionario.length) {

                InputsFuncionario[i] = new JFormattedTextField();

            }

            if (i < ALTERACAO_INDICES.length) {
                LabelsProduto[i].setBounds(5, 5 + (i * 35), 70, 30);
                InputsFuncionario[i].setBounds(80, 5 + (i * 35), 200, 30);
                InputsFuncionario[i].setEditable(false);
                InputsFuncionario[i].setForeground(new Color(0, 0, 0, 0));
                painelProduto.add(LabelsProduto[i]);
                painelProduto.add(InputsFuncionario[i]);
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
    JFormattedTextField formatoPIS = new JFormattedTextField(PetShop2.FORMATO_PIS);

    private String[] rowTabela(int i) {
        formatoTel.setText(TabelaFuncionarios.get(i).getTel());
        formatoCel.setText(TabelaFuncionarios.get(i).getCel());
        formatoCPF.setText(TabelaFuncionarios.get(i).getCpf());

        String aux[] = {
            TabelaFuncionarios.get(i).getCod_cli() + "",
            TabelaFuncionarios.get(i).getNome(),
            formatoCPF.getText(),
            TabelaFuncionarios.get(i).getRg(),
            PetShop2.DATA_BRASIL_SQL.format(TabelaFuncionarios.get(i).getData_nasc()),
            formatoTel.getText(),
            formatoCel.getText(),
            TabelaFuncionarios.get(i).getEmail()
        };

        return aux;
    }

    private void preencherDados(int cod) {
        formatoTel.setText(TabelaFuncionarios.get(cod).getTel());
        formatoCel.setText(TabelaFuncionarios.get(cod).getCel());
        formatoCPF.setText(TabelaFuncionarios.get(cod).getCpf());
        formatoPIS.setText(TabelaFuncionarios.get(cod).getPis());

        InputsFuncionario[0].setText(TabelaFuncionarios.get(cod).getCod_cli() + "");
        InputsFuncionario[1].setText(TabelaFuncionarios.get(cod).getNome());
        InputsFuncionario[2].setText(formatoCPF.getText());
        InputsFuncionario[3].setText(TabelaFuncionarios.get(cod).getRg());
        InputsFuncionario[4].setText(PetShop2.DATA_BRASIL_SQL.format(TabelaFuncionarios.get(cod).getData_nasc()));
        InputsFuncionario[5].setText(formatoTel.getText());
        InputsFuncionario[6].setText(formatoCel.getText());
        InputsFuncionario[7].setText(TabelaFuncionarios.get(cod).getEmail());
        InputsFuncionario[8].setText(TabelaFuncionarios.get(cod).getEndereco());
        InputsFuncionario[9].setText(TabelaFuncionarios.get(cod).getNumero());
        InputsFuncionario[10].setText(TabelaFuncionarios.get(cod).getComplemento());
        InputsFuncionario[11].setText(TabelaFuncionarios.get(cod).getCep());
        InputsFuncionario[12].setText(TabelaFuncionarios.get(cod).getCidade());
        InputsFuncionario[13].setText(TabelaFuncionarios.get(cod).getBairro());
        InputsFuncionario[14].setText(TabelaFuncionarios.get(cod).getUf());
        InputsFuncionario[15].setText(TabelaFuncionarios.get(cod).getContaCorrente());
        InputsFuncionario[16].setText(TabelaFuncionarios.get(cod).getAgencia());
        InputsFuncionario[17].setText(TabelaFuncionarios.get(cod).getBanco());
        InputsFuncionario[18].setText(TabelaFuncionarios.get(cod).getCidadeConta());
        InputsFuncionario[19].setText(TabelaFuncionarios.get(cod).getEstadoConta());
        //InputsFuncionario[20].setText(TabelaFuncionarios.get(cod).getPis());
        InputsFuncionario[20].setText(formatoPIS.getText());
        InputsFuncionario[21].setText("R$ " + PetShop2.FORMATAR_MOEDA.format(TabelaFuncionarios.get(cod).getSalario()));
        InputsFuncionario[22].setText(TabelaFuncionarios.get(cod).getFkCargo() + "");
    }

    public void actionPerformed(ActionEvent evt) {

    }

}

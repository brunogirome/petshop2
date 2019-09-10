package Cadastramento;

import Controle.Calculos;
import Controle.PetShop2;
import DAO.CategoriasDAO;
import DAO.FornecedoresDAO;
import VOs.CategoriasVO;
import VOs.FornecedoresVO;
import VOs.ProdutosVO;
import java.awt.Container;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class CadastrarProduto extends Container implements ActionListener {

    private JFrame Janela;

    private JLabel LabelsProduto[];

    private JTextField InputProduto[];

    private JButton BotoesNovo[];

    public static JComboBox<String> SelecoesJanela[];

    private JCheckBox CaixaTipoPet[];

    private JCheckBox CaixaIdadePet[];

    private JCheckBox CaixaTamanhoPet[];

    private final String INDICES_BOTOES[] = {"Limpar campos(F3)", "Cancelar(F2)", "Salvar cadastro(F1)"};

    private JButton BotoesCadastro[];

    private ProdutosVO objProduto;

    private ArrayList<CategoriasVO> listaCategoria;

    public static ArrayList<FornecedoresVO> listaFornecedores;

    private JButton BotoesSelecionar[];

    private final String INDICES_SELECIONAR[] = {"Gato/Cachorro", "Todos"};

    private boolean selectPet = false;
    private boolean selectIdade = false;
    private boolean selectTamanho = false;

    public CadastrarProduto() {

        Janela = new JFrame("CADASTRAR PRODUTO");
        Janela.setSize(1000, 435);
        Janela.setResizable(false);
        Janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Janela.setLocationRelativeTo(null);
        Janela.add(this);

        LabelsProduto = new JLabel[PetShop2.PRODUTOS_INDICES.length];
        InputProduto = new JTextField[4];
        BotoesNovo = new JButton[2];
        SelecoesJanela = new JComboBox[2];

        CaixaTipoPet = new JCheckBox[Controle.PetShop2.PETS_TIPO.length];
        CaixaIdadePet = new JCheckBox[Controle.PetShop2.PETS_IDADE.length];
        CaixaTamanhoPet = new JCheckBox[Controle.PetShop2.PETS_TAMANHO.length];

        BotoesCadastro = new JButton[INDICES_BOTOES.length];

        listaCategoria = CategoriasDAO.selectCategorias();

        listaFornecedores = FornecedoresDAO.selectFornecedores();

        BotoesSelecionar = new JButton[4];

        construirTela();
        this.setVisible(false);
        Janela.setVisible(true);
        this.setVisible(true);
    }

    private void construirTela() {

        JPanel painelAuxiliar = new JPanel(null);
        painelAuxiliar.setBorder(BorderFactory.createEtchedBorder());
        painelAuxiliar.setBounds(7, 5, 980, 350);

        JPanel paineisCaixa[] = new JPanel[3];
        for (int i = 0; i < paineisCaixa.length; i++) {
            paineisCaixa[i] = new JPanel(null);
            paineisCaixa[i].setBorder(BorderFactory.createEtchedBorder());
            paineisCaixa[i].setBounds(Calculos.centralizarPaineis(i, 200, 3, painelAuxiliar), 140, 200, 200);
            painelAuxiliar.add(paineisCaixa[i]);
        }

        JSeparator separadorTela[] = new JSeparator[2];

        for (int i = 0; i < PetShop2.PRODUTOS_INDICES.length; i++) {
            LabelsProduto[i] = new JLabel(PetShop2.PRODUTOS_INDICES[i]);
            LabelsProduto[i].setFont(new Font("Tahoma", 1, 13));
            if (i < InputProduto.length) {
                InputProduto[i] = new JFormattedTextField();
                InputProduto[i].addKeyListener(keyProduto());
            }

            if (i < BotoesNovo.length) {
                BotoesNovo[i] = new JButton("Novo...");
                BotoesNovo[i].setMargin(new Insets(0, 0, 0, 0));
                BotoesNovo[i].setBounds(515 + (i * 370), 90, 75, 30);
                BotoesNovo[i].addActionListener(this);
                painelAuxiliar.add(BotoesNovo[i]);
            }

            if (i < separadorTela.length) {
                separadorTela[i] = new JSeparator(JSeparator.HORIZONTAL);
                separadorTela[i].setBounds(1, 130 + (i * 220), 977, 2);
                painelAuxiliar.add(separadorTela[i]);
            }

            if (i < SelecoesJanela.length) {
                SelecoesJanela[1] = new JComboBox<>(FornecedoresDAO.carregaSelecao());
                SelecoesJanela[0] = new JComboBox<>(CategoriasDAO.carregaSelecao());
                SelecoesJanela[i].addKeyListener(keyProduto());
            }

            if (i < Controle.PetShop2.PETS_TIPO.length) {
                CaixaTipoPet[i] = new JCheckBox(Controle.PetShop2.PETS_TIPO[i]);
                CaixaTipoPet[i].setBounds(5, 25 + (i * 25), 190, 25);
                CaixaTipoPet[i].setFont(new Font("Arial", 0, 15));
                CaixaTipoPet[i].addKeyListener(keyProduto());
                paineisCaixa[0].add(CaixaTipoPet[i]);
            }

            if (i < Controle.PetShop2.PETS_IDADE.length) {
                CaixaIdadePet[i] = new JCheckBox(Controle.PetShop2.PETS_IDADE[i]);
                CaixaIdadePet[i].setBounds(5, 25 + (i * 25), 190, 25);
                CaixaIdadePet[i].setFont(new Font("Arial", 0, 15));
                CaixaIdadePet[i].addKeyListener(keyProduto());
                paineisCaixa[1].add(CaixaIdadePet[i]);

                CaixaTamanhoPet[i] = new JCheckBox(Controle.PetShop2.PETS_TAMANHO[i]);
                CaixaTamanhoPet[i].setBounds(5, 25 + (i * 25), 190, 25);
                CaixaTamanhoPet[i].setFont(new Font("Arial", 0, 15));
                CaixaTamanhoPet[i].addKeyListener(keyProduto());
                paineisCaixa[2].add(CaixaTamanhoPet[i]);
            }

            if (i < 2) {
                LabelsProduto[i].setBounds(10 + (i * 485), 10, 475, 25);
                InputProduto[i].setBounds(10 + (i * 485), 35, 475, 30);
                painelAuxiliar.add(LabelsProduto[i]);
                painelAuxiliar.add(InputProduto[i]);
            } else if (i > 1 && i < 4) {
                LabelsProduto[i].setBounds(10 + ((i - 2) * 110), 65, 100, 25);
                InputProduto[i].setBounds(10 + ((i - 2) * 110), 90, 100, 30);
                painelAuxiliar.add(LabelsProduto[i]);
                painelAuxiliar.add(InputProduto[i]);
            } else if (i > 3 && i < 6) {
                LabelsProduto[i].setBounds(230 + ((i - 4) * 370), 65, 100, 25);
                SelecoesJanela[i - 4].setBounds(230 + ((i - 4) * 370), 90, 275, 30);
                SelecoesJanela[i - 4].addActionListener(this);
                painelAuxiliar.add(LabelsProduto[i]);
                painelAuxiliar.add(SelecoesJanela[i - 4]);
            } else if (i > 5) {
                LabelsProduto[i].setBounds(5, 0, 190, 25);
                paineisCaixa[i - 6].add(LabelsProduto[i]);
            }

        }

        for (int i = 0; i < INDICES_BOTOES.length; i++) {
            BotoesCadastro[i] = new JButton(INDICES_BOTOES[i]);
            BotoesCadastro[i].setBounds(428 + (i * 190), 360, 180, 40);//617,189
            BotoesCadastro[i].setMargin(new Insets(0, 0, 0, 0));
            BotoesCadastro[i].addActionListener(this);
            this.add(BotoesCadastro[i]);
        }

        BotoesSelecionar[0] = new JButton(INDICES_SELECIONAR[0]);
        //BotoesSelecionar[0].setBounds(5, 175, 92, 20);
        BotoesSelecionar[0].setBounds(5, 174, 92, 21);
        BotoesSelecionar[0].setMargin(new Insets(0, 0, 0, 0));
        BotoesSelecionar[0].addActionListener(this);
        paineisCaixa[0].add(BotoesSelecionar[0]);

        BotoesSelecionar[1] = new JButton(INDICES_SELECIONAR[1]);
        BotoesSelecionar[1].setBounds(102, 175, 92, 20);
        BotoesSelecionar[1].setMargin(new Insets(0, 0, 0, 0));
        BotoesSelecionar[1].addActionListener(this);
        paineisCaixa[0].add(BotoesSelecionar[1]);

        BotoesSelecionar[2] = new JButton(INDICES_SELECIONAR[1]);
        BotoesSelecionar[2].setBounds(5, 175, 190, 20);
        BotoesSelecionar[2].setMargin(new Insets(0, 0, 0, 0));
        BotoesSelecionar[2].addActionListener(this);
        paineisCaixa[1].add(BotoesSelecionar[2]);

        BotoesSelecionar[3] = new JButton(INDICES_SELECIONAR[1]);
        BotoesSelecionar[3].setBounds(5, 175, 190, 20);
        BotoesSelecionar[3].setMargin(new Insets(0, 0, 0, 0));
        BotoesSelecionar[3].addActionListener(this);
        paineisCaixa[2].add(BotoesSelecionar[3]);

        this.add(painelAuxiliar);

    }

    private KeyAdapter keyProduto() {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_F1) {
                    inserirProduto();
                } else if (e.getKeyCode() == KeyEvent.VK_F2) {
                    Janela.dispose();
                } else if (e.getKeyCode() == KeyEvent.VK_F3) {
                    limparCampos();
                }
            }
        };
    }

    private String vericarSelecao(String[] vetor, JCheckBox[] caixas) {
        String aux = "";

        for (int i = 0; i < vetor.length; i++) {
            if (caixas[i].isSelected()) {
                aux += vetor[i] + ",";
            }
        }
        if (!aux.equals("")) {
            aux += "x";
        }

        return aux.replace(",x", "");
    }

    private boolean checarCadastro() {
        String aux = "<html><b>PROBLEMA NO PREENCHIMENTO DOS CAMPOS:</b></html>\n";
        boolean condicao = true;
        boolean tipo = false;
        boolean idade = false;
        boolean tamanho = false;

        if (InputProduto[0].getText().replace(" ", "").equals("")) {
            aux += "\nNOME INVÁLIDO";
            condicao = false;
        }
        if (InputProduto[1].getText().replace(" ", "").equals("")) {
            aux += "\nDESCRIÇÃO INVÁLIDA";
            condicao = false;
        }
        if (InputProduto[2].getText().replace(" ", "").equals("")) {
            aux += "\nQUANTIDADE INVÁLIDA";
            condicao = false;
        }
        if (InputProduto[3].getText().replace(" ", "").equals("")) {
            aux += "\nPREÇO INVÁLIDO";
            condicao = false;
        }
        for (JCheckBox CaixaTipoPet1 : CaixaTipoPet) {
            if (CaixaTipoPet1.isSelected()) {
                tipo = true;
            }
        }
        for (JCheckBox CaixaIdadePet1 : CaixaIdadePet) {
            if (CaixaIdadePet1.isSelected()) {
                idade = true;
            }
        }
        for (JCheckBox CaixaTamanhoPet1 : CaixaTamanhoPet) {
            if (CaixaTamanhoPet1.isSelected()) {
                tamanho = true;
            }
        }
        if (!tipo) {
            aux += "\nNENHUM TIPO SELECIONADO";
            condicao = false;
        }
        if (!idade) {
            aux += "\nNENHUMA IDADE SELECIONADA";
            condicao = false;
        }
        if (!tamanho) {
            aux += "\nNENHUM TAMANHO SELECIONADO";
            condicao = false;
        }

        if (!condicao) {
            JOptionPane.showMessageDialog(null, aux, "ERRO AO INSERIR DADOS", JOptionPane.ERROR_MESSAGE);
        }
        return condicao;
    }

    private void inserirProduto() {
        objProduto = new ProdutosVO();
        
        if (checarCadastro()) {
            objProduto.setNome(InputProduto[0].getText());
            objProduto.setDescricao(InputProduto[1].getText());
            objProduto.setQtd(Integer.parseInt(InputProduto[2].getText()));
            objProduto.setPreco(Float.parseFloat(InputProduto[3].getText()));
            objProduto.setFkCatego(listaCategoria.get(SelecoesJanela[0].getSelectedIndex()).getCod_catego() + "");
            objProduto.setFkFornec(listaFornecedores.get(SelecoesJanela[1].getSelectedIndex()).getCod_fornec() + "");
            objProduto.setTipo_Pet(vericarSelecao(PetShop2.PETS_TIPO, CaixaTipoPet));
            objProduto.setIdade_Pet(vericarSelecao(PetShop2.PETS_IDADE, CaixaTipoPet));
            objProduto.setTamanho_Pet(vericarSelecao(PetShop2.PETS_TAMANHO, CaixaTamanhoPet));

            if (DAO.ProdutosDAO.inserirProduto(objProduto)) {
                Janela.dispose();
            }
        }

    }

    private void limparCampos() {
        for (int i = 0; i < InputProduto.length; i++) {
            InputProduto[i].setText(null);
        }
        for (int i = 0; i < SelecoesJanela.length; i++) {
            SelecoesJanela[i].setSelectedIndex(0);
        }
        for (int i = 0; i < CaixaTipoPet.length; i++) {
            CaixaTipoPet[i].setSelected(false);
        }
        for (int i = 0; i < CaixaIdadePet.length; i++) {
            CaixaIdadePet[i].setSelected(false);
        }
        for (int i = 0; i < CaixaTamanhoPet.length; i++) {
            CaixaTamanhoPet[i].setSelected(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == BotoesNovo[0]) {

            String aux = JOptionPane.showInputDialog("NOME DA CATEGORIA:");

            if (!aux.equals("")) {
                DAO.CategoriasDAO.inserirCategoria(aux);
                SelecoesJanela[0].setModel(new DefaultComboBoxModel<>(DAO.CategoriasDAO.carregaSelecao()));
                listaCategoria = DAO.CategoriasDAO.selectCategorias();
            } else {
                JOptionPane.showMessageDialog(null, "Você não digitou um valor.");
            }

        } else if (evt.getSource() == BotoesNovo[1]) {
            new CadastrarFornecedor();
        } else if (evt.getSource() == BotoesCadastro[0]) {
            limparCampos();
        } else if (evt.getSource() == BotoesCadastro[1]) {
            Janela.dispose();
        } else if (evt.getSource() == BotoesCadastro[2]) {
            inserirProduto();

        } else if (evt.getSource() == BotoesSelecionar[0]) {
            CaixaTipoPet[0].setSelected(!CaixaTipoPet[0].isSelected());
            CaixaTipoPet[1].setSelected(!CaixaTipoPet[1].isSelected());
        } else if (evt.getSource() == BotoesSelecionar[1]) {
            selectPet = !selectPet;
            for (int i = 0; i < CaixaTipoPet.length; i++) {
                CaixaTipoPet[i].setSelected(selectPet);
            }
        } else if (evt.getSource() == BotoesSelecionar[2]) {
            selectIdade = !selectIdade;
            for (int i = 0; i < CaixaIdadePet.length; i++) {
                CaixaIdadePet[i].setSelected(selectIdade);
            }
        } else if (evt.getSource() == BotoesSelecionar[3]) {
            selectTamanho = !selectTamanho;
            for (int i = 0; i < CaixaTamanhoPet.length; i++) {
                CaixaTamanhoPet[i].setSelected(selectTamanho);
            }
        }

    }
}

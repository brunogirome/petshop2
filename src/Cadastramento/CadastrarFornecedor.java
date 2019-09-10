package Cadastramento;

import DAO.FornecedoresDAO;
import PainelCadastro.PainelFornecedor;
import VOs.FornecedoresVO;
import java.awt.Container;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CadastrarFornecedor extends Container implements ActionListener {

    private JFrame Janela;

    private PainelFornecedor PainelFornecedor;

    private final String INDICES_BOTOES[] = {"Limpar campos(F3)", "Cancelar(F2)", "Salvar cadastro(F1)"};

    private JButton BotoesCadastro[];

    private FornecedoresVO objFornecedores;

    public CadastrarFornecedor() {

        Janela = new JFrame("CADASTRAR FORNECEDOR");
        Janela.setSize(1000, 225);
        Janela.setResizable(false);
        Janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Janela.setLocationRelativeTo(null);
        Janela.add(this);

        BotoesCadastro = new JButton[INDICES_BOTOES.length];

        construirTela();
        this.setVisible(false);
        Janela.setVisible(true);
        this.setVisible(true);
    }

    private void construirTela() {

        PainelFornecedor = new PainelFornecedor(keyFornecedor());

        PainelFornecedor.setLocation(7, 5);

        for (int i = 0; i < INDICES_BOTOES.length; i++) {
            BotoesCadastro[i] = new JButton(INDICES_BOTOES[i]);
            BotoesCadastro[i].setBounds(428 + (i * 190), 150, 180, 40);
            BotoesCadastro[i].setMargin(new Insets(0, 0, 0, 0));
            BotoesCadastro[i].addActionListener(this);
            this.add(BotoesCadastro[i]);
        }

        this.add(PainelFornecedor);

    }

    private KeyAdapter keyFornecedor() {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_F1) {
                    inserirFornecedor();
                } else if (e.getKeyCode() == KeyEvent.VK_F2) {
                    Janela.dispose();
                } else if (e.getKeyCode() == KeyEvent.VK_F3) {
                    PainelFornecedor.limparCampos();
                }
            }
        };
    }

    private boolean checarCadastro() {

        String aux = "<html><b>PROBLEMA NO PREENCHIMENTO DOS CAMPOS:</b></html>\n";
        boolean condicao = true;

        if (PainelFornecedor.getNome().replace(" ", "").equals("")) {
            aux += "\nNOME NÃO INSERIDO";
            condicao = false;
        }
        if (PainelFornecedor.getDetalhes().replace(" ", "").equals("")) {
            aux += "\nDETALHES INVÁLIDOS";
            condicao = false;
        }

        if (!condicao) {
            JOptionPane.showMessageDialog(null, aux, "ERRO AO INSERIR DADOS", JOptionPane.ERROR_MESSAGE);
        }

        return condicao;

    }

    private void inserirFornecedor() {
        if (checarCadastro()) {
            objFornecedores = new FornecedoresVO();
            objFornecedores.setNome(PainelFornecedor.getNome());
            objFornecedores.setDetalhes(PainelFornecedor.getDetalhes());
            objFornecedores.setCnpj(PainelFornecedor.getCNPJ());
            objFornecedores.setEmail(PainelFornecedor.getEmail());
            objFornecedores.setTel(PainelFornecedor.getTelefone());
            objFornecedores.setCel(PainelFornecedor.getCelular());

            if (FornecedoresDAO.inserirForncedores(objFornecedores)) {
                CadastrarProduto.SelecoesJanela[1].setModel(new DefaultComboBoxModel<>(DAO.FornecedoresDAO.carregaSelecao()));
                CadastrarProduto.listaFornecedores = DAO.FornecedoresDAO.selectFornecedores();
                Janela.dispose();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == BotoesCadastro[0]) {
            PainelFornecedor.limparCampos();
        } else if (evt.getSource() == BotoesCadastro[1]) {
            Janela.dispose();
        } else if (evt.getSource() == BotoesCadastro[2]) {
            inserirFornecedor();
        }
    }

}

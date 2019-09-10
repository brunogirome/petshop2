package Cadastramento;

import DAO.ConexaoDAO;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import PainelCadastro.PainelEndereco;
import PainelCadastro.PainelPessoa;
import VOs.ClientesVO;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class CadastrarCliente extends Container implements ActionListener {

    private JFrame Janela;

    private PainelEndereco PainelEndereco;

    private PainelPessoa PainelPessoa;

    private final String INDICES_BOTOES[] = {"Limpar campos(F3)", "Cancelar(F2)", "Salvar cadastro(F1)"};

    private JButton BotoesCadastro[];

    private ClientesVO objCliente;

    public CadastrarCliente() {

        Janela = new JFrame("CADASTRAR CLIENTE");
        Janela.setSize(1000, 370);
        Janela.setResizable(false);
        Janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Janela.setLocationRelativeTo(null);
        Janela.add(this);
        Janela.setFocusable(true);
        Janela.addKeyListener(keyCliente());

        BotoesCadastro = new JButton[INDICES_BOTOES.length];

        construirTela();
        this.setVisible(false);
        Janela.setVisible(true);
        this.setVisible(true);
    }

    private void construirTela() {

        PainelPessoa = new PainelPessoa(keyCliente());
        PainelEndereco = new PainelEndereco(keyCliente());

        PainelPessoa.setLocation(7, 5);
        PainelEndereco.setLocation(7, PainelPessoa.getHeight() + 5);

        for (int i = 0; i < INDICES_BOTOES.length; i++) {
            BotoesCadastro[i] = new JButton(INDICES_BOTOES[i]);
            BotoesCadastro[i].setBounds(428 + (i * 190), 290, 180, 40);
            BotoesCadastro[i].setMargin(new Insets(0, 0, 0, 0));
            BotoesCadastro[i].addActionListener(this);
            this.add(BotoesCadastro[i]);
        }

        this.add(PainelPessoa);
        this.add(PainelEndereco);

        PainelEndereco.getEndereco();
    }

    private KeyAdapter keyCliente() {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_F1) {
                    inserirCliente();
                } else if (e.getKeyCode() == KeyEvent.VK_F2) {
                    Janela.dispose();
                } else if (e.getKeyCode() == KeyEvent.VK_F3) {
                    PainelEndereco.limparCampos();
                    PainelPessoa.limparCampos();
                }
            }
        };
    }

    private boolean checarCadastro() {
        String aux = "<html><b>PROBLEMA NO PREENCHIMENTO DOS CAMPOS:</b></html>\n";
        boolean condicao = true;

        if (PainelPessoa.getNome().replace(" ", "").equals("")) {
            aux += "\nNOME DO CLIENTE INVÁLIDO\n";
            condicao = false;
        }
        if (!PainelEndereco.getEndereco().replace(" ", "").equals("")
                || !PainelEndereco.getNumero().replace(" ", "").equals("")
                || !PainelEndereco.getCEP().replace(" ", "").equals("")
                || !PainelEndereco.getComplemento().replace(" ", "").equals("")
                || !PainelEndereco.getCidade().replace(" ", "").equals("")
                || !PainelEndereco.getBairro().replace(" ", "").equals("")) {
            aux += "\n<html><b>PROBLEMA COM O ENDEREÇO:</b></html>";
            if (PainelEndereco.getEndereco().replace(" ", "").equals("")) {
                aux += "\nENDEREÇO INVÁLIDO";
                condicao = false;
            }
            if (PainelEndereco.getNumero().replace(" ", "").equals("")) {
                aux += "\nNÚMERO INVÁLIDO";
                condicao = false;
            }
            if (PainelEndereco.getCEP().replace(" ", "").equals("")) {
                aux += "\nCEP INVÁLIDO";
                condicao = false;
            }
            if (PainelEndereco.getCidade().replace(" ", "").equals("")) {
                aux += "\nCIDADE INVÁLIDA";
                condicao = false;
            }
            if (PainelEndereco.getBairro().replace(" ", "").equals("")) {
                aux += "\nBAIRRO INVÁLIDO";
                condicao = false;
            }
        }
        if (!condicao) {
            JOptionPane.showMessageDialog(null, aux, "ERRO AO INSERIR DADOS", JOptionPane.ERROR_MESSAGE);
        }
        return condicao;
    }

    private void inserirCliente() {
        objCliente = new ClientesVO();

        if (checarCadastro()) {
            objCliente.setNome(PainelPessoa.getNome());
            objCliente.setCpf(PainelPessoa.getCPF());
            objCliente.setRg(PainelPessoa.getRG());
            objCliente.setData_nasc(PainelPessoa.getDataNasc());
            objCliente.setTel(PainelPessoa.getTelefone());
            objCliente.setCel(PainelPessoa.getCelular());
            objCliente.setEmail(PainelPessoa.getEmail());
            objCliente.setEndereco(PainelEndereco.getEndereco());
            objCliente.setNumero(PainelEndereco.getNumero());
            objCliente.setComplemento(PainelEndereco.getComplemento());
            objCliente.setCep(PainelEndereco.getCEP());
            objCliente.setCidade(PainelEndereco.getCidade());
            objCliente.setBairro(PainelEndereco.getBairro());
            objCliente.setUf(PainelEndereco.getUF());

            if (DAO.ClientesDAO.inserirCliente(objCliente)) {
                Janela.dispose();
            }

        }

    }

    @Override
    public void actionPerformed(ActionEvent evt) {

        if (evt.getSource() == BotoesCadastro[0]) {
            PainelPessoa.limparCampos();
            PainelEndereco.limparCampos();
        } else if (evt.getSource() == BotoesCadastro[1]) {
            Janela.dispose();
        } else if (evt.getSource() == BotoesCadastro[2]) {
            inserirCliente();
        }

    }

}

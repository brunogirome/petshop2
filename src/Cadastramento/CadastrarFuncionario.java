
package Cadastramento;

import PainelCadastro.PainelEndereco;
import PainelCadastro.PainelFuncionario;
import PainelCadastro.PainelPessoa;
import VOs.FuncionariosVO;
import java.awt.Container;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class CadastrarFuncionario extends Container implements ActionListener {

    private JFrame Janela;

    private PainelEndereco PainelEndereco;

    private PainelPessoa PainelPessoa;

    private PainelFuncionario PainelFuncionario;

    private final String INDICES_BOTOES[] = {"Limpar campos(F3)", "Cancelar(F2)", "Salvar cadastro(F1)"};

    private JButton BotoesCadastro[];

    private FuncionariosVO objFuncionario;

    public CadastrarFuncionario() {

        Janela = new JFrame("CADASTRAR FUNCIONÁRIO");
        Janela.setSize(1000, 640);
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

        PainelPessoa = new PainelPessoa(keyFuncionario());
        PainelEndereco = new PainelEndereco(keyFuncionario());
        PainelFuncionario = new PainelFuncionario(keyFuncionario());

        PainelPessoa.setLocation(7, 5);
        PainelEndereco.setLocation(7, PainelPessoa.getHeight() + 5);
        PainelFuncionario.setLocation(7, (PainelPessoa.getHeight() * 2) + 5);

        for (int i = 0; i < INDICES_BOTOES.length; i++) {
            BotoesCadastro[i] = new JButton(INDICES_BOTOES[i]);
            BotoesCadastro[i].setBounds(428 + (i * 190), 560, 180, 40);//617,189
            BotoesCadastro[i].setMargin(new Insets(0, 0, 0, 0));
            BotoesCadastro[i].addActionListener(this);
            this.add(BotoesCadastro[i]);
        }

        this.add(PainelPessoa);
        this.add(PainelEndereco);
        this.add(PainelFuncionario);
    }

    private KeyAdapter keyFuncionario() {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_F1) {
                    inserirFuncionario();
                } else if (e.getKeyCode() == KeyEvent.VK_F2) {
                    Janela.dispose();
                } else if (e.getKeyCode() == KeyEvent.VK_F3) {
                    PainelEndereco.limparCampos();
                    PainelPessoa.limparCampos();
                    PainelFuncionario.limparCampos();
                }
            }
        };
    }

    private boolean checarCadastramento() {
        String aux = "<html><b>PROBLEMA NO PREENCHIMENTO DOS CAMPOS:</b></html>";
        boolean condicao = true;
        String auxEnd = "";
        boolean condicaoEnd = true;

        if (PainelPessoa.getNome().replace(" ", "").equals("")) {
            aux += "\nNOME INVÁLIDO";
            condicao = false;
        }
        if (PainelPessoa.getCPF().replace(" ", "").equals("")) {
            aux += "\nCPF INVÁLIDO";
            condicao = false;
        }
        if (PainelPessoa.getRG().replace(" ", "").equals("")) {
            aux += "\nRG INVÁLIDO";
            condicao = false;
        }
        if (PainelPessoa.getDataNasc() == null) {
            aux += "\nDATA DE NASCIMENTO INVÁLIDA";
            condicao = false;
        }
        if (PainelEndereco.getEndereco().replace(" ", "").equals("")) {
            auxEnd += "\nENDEREÇO INVÁLIDO";
            condicaoEnd = false;
        }
        if (PainelEndereco.getNumero().replace(" ", "").equals("")) {
            auxEnd += "\nNÚMERO INVÁLIDO";
            condicaoEnd = false;
        }
        if (PainelEndereco.getCEP().replace(" ", "").equals("")) {
            auxEnd += "\nCEP INVÁLIDO";
            condicaoEnd = false;
        }
        if (PainelEndereco.getCidade().replace(" ", "").equals("")) {
            auxEnd += "\nCIDADE INVÁLIDA";
            condicaoEnd = false;
        }
        if (PainelEndereco.getBairro().replace(" ", "").equals("")) {
            auxEnd += "\nBAIRRO INVÁLIDO";
            condicaoEnd = false;
        }
        if (!condicaoEnd) {
            aux += "\n\n<html><b>PROBLEMA COM O ENDEREÇO:</b></html>" + auxEnd;
            condicao = false;
        }

        if (!condicao) {
            JOptionPane.showMessageDialog(null, aux, "ERRO AO INSERIR DADOS", JOptionPane.ERROR_MESSAGE);
        }
        return condicao;
    }

    private void inserirFuncionario() {
        objFuncionario = new FuncionariosVO();

        if (checarCadastramento()) {
            objFuncionario.setNome(PainelPessoa.getNome());
            objFuncionario.setCpf(PainelPessoa.getCPF());
            objFuncionario.setRg(PainelPessoa.getRG());
            objFuncionario.setData_nasc(PainelPessoa.getDataNasc());
            objFuncionario.setTel(PainelPessoa.getTelefone());
            objFuncionario.setCel(PainelPessoa.getCelular());
            objFuncionario.setEmail(PainelPessoa.getEmail());
            objFuncionario.setEndereco(PainelEndereco.getEndereco());
            objFuncionario.setNumero(PainelEndereco.getNumero());
            objFuncionario.setComplemento(PainelEndereco.getComplemento());
            objFuncionario.setCep(PainelEndereco.getCEP());
            objFuncionario.setCidade(PainelEndereco.getCidade());
            objFuncionario.setBairro(PainelEndereco.getBairro());
            objFuncionario.setUf(PainelEndereco.getUF());
            objFuncionario.setContaCorrente(PainelFuncionario.getContaCorrente());
            objFuncionario.setAgencia(PainelFuncionario.getAgencia());
            objFuncionario.setBanco(PainelFuncionario.getBanco());
            objFuncionario.setCidadeConta(PainelFuncionario.getCidade());
            objFuncionario.setEstadoConta(PainelFuncionario.getEstado());
            objFuncionario.setPis(PainelFuncionario.getPisPasep());
            objFuncionario.setSalario(Float.parseFloat(PainelFuncionario.getSalario()));
            objFuncionario.setFkCargo(PainelFuncionario.getCargo());

            if (DAO.FuncionariosDAO.inserirFuncionarios(objFuncionario)) {
                Janela.dispose();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent evt) {

        if (evt.getSource() == BotoesCadastro[0]) {
            PainelPessoa.limparCampos();
            PainelEndereco.limparCampos();
            PainelFuncionario.limparCampos();
        } else if (evt.getSource() == BotoesCadastro[1]) {
            Janela.dispose();
        } else if (evt.getSource() == BotoesCadastro[2]) {
            inserirFuncionario();
        }

    }

}

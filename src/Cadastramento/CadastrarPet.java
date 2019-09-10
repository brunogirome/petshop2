package Cadastramento;

import VOs.PetsVO;
import java.awt.Container;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CadastrarPet extends Container implements ActionListener {

    private JFrame Janela;

    private PainelCadastro.PainelPet PainelPet;

    private final String INDICES_BOTOES[] = {"Limpar campos(F3)", "Cancelar(F2)", "Salvar cadastro(F1)"};

    private JButton BotoesCadastro[];

    private PetsVO objPet;

    public CadastrarPet() {
        Janela = new JFrame("CADASTRAR PET");
        Janela.setSize(1000, 430);
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

        PainelPet = new PainelCadastro.PainelPet(keyPet());
        PainelPet.setLocation(7, 5);

        for (int i = 0; i < INDICES_BOTOES.length; i++) {
            BotoesCadastro[i] = new JButton(INDICES_BOTOES[i]);
            BotoesCadastro[i].setBounds(428 + (i * 190), 350, 180, 40);//617,189
            BotoesCadastro[i].setMargin(new Insets(0, 0, 0, 0));
            BotoesCadastro[i].addActionListener(this);
            this.add(BotoesCadastro[i]);
        }

        this.add(PainelPet);

    }

    private KeyAdapter keyPet() {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_F1) {
                    inserirPet();
                } else if (e.getKeyCode() == KeyEvent.VK_F2) {
                    Janela.dispose();
                } else if (e.getKeyCode() == KeyEvent.VK_F3) {
                    PainelPet.limparCampos();
                }
            }
        };
    }

    private boolean checarCadastro() {
        String aux = "<html><b>PROBLEMA NO PREENCHIMENTO DOS CAMPOS:</b></html>\n";
        boolean condicao = true;

        if (PainelPet.getNome().replace(" ", "").equals("")) {
            aux += "\nNOME DO PET INVÁLIDO";
            condicao = false;
        }
        if (PainelPet.getCor().replace(" ", "").equals("")) {
            aux += "\nCOR/PELAGEM INVÁLIDA";
            condicao = false;
        }
        if (PainelPet.getPeso().replace(" ", "").equals("")) {
            aux += "\nPESO INVÁLIDO";
            condicao = false;
        }
        if (PainelPet.getDono() == 0) {
            aux += "\nNENHUM DONO FOI SELECIONADO";
            condicao = false;
        }
        if (!condicao) {
            JOptionPane.showMessageDialog(null, aux, "ERRO AO INSERIR DADOS", JOptionPane.ERROR_MESSAGE);
        }
        return condicao;
    }

    private void inserirPet() {
        objPet = new PetsVO();

        if (checarCadastro()) {
            objPet.setNome(PainelPet.getNome());
            if (PainelPet.getRaca().replace(" ", "").equals("") || PainelPet.getRaca().equals("ndf")) {
                objPet.setRaca("Não definido");
            } else {
                objPet.setRaca(PainelPet.getRaca());
            }
            objPet.setCor(PainelPet.getCor());
            objPet.setPeso(Float.parseFloat(PainelPet.getPeso()));
            objPet.setSexo(PainelPet.getSexo());
            objPet.setTipoAnimal(PainelPet.getTipoAnimal());
            objPet.setIdade(PainelPet.getIdade());
            objPet.setPorte(PainelPet.getPorte());
            objPet.setObservacoes(PainelPet.getObservacao());
            objPet.setFkDono(PainelPet.getDono() + "");

            if (DAO.PetsDAO.inserirPets(objPet)) {
                Janela.dispose();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent evt) {

        if (evt.getSource() == BotoesCadastro[0]) {
            PainelPet.limparCampos();
        } else if (evt.getSource() == BotoesCadastro[1]) {
            Janela.dispose();
        } else if (evt.getSource() == BotoesCadastro[2]) {
            inserirPet();
        }

    }

}

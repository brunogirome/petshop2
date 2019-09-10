package Cadastramento;

import DAO.AgendamentosDAO;
import PainelCadastro.PainelAgendamento;
import VOs.AgendamentosVO;
import java.awt.Container;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CadastrarAgendamento extends Container implements ActionListener {

    private JFrame Janela;

    private PainelAgendamento PainelAgendamento;

    private final String INDICES_BOTOES[] = {"Limpar campos(F3)", "Cancelar(F2)", "Salvar cadastro(F1)"};

    private JButton BotoesCadastro[];

    private AgendamentosVO objAgendamento;

    public CadastrarAgendamento() {
        Janela = new JFrame("AGENDAMENTO");
        Janela.setSize(1000, 365);
        Janela.setResizable(false);
        Janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Janela.setLocationRelativeTo(null);
        Janela.add(this);

        BotoesCadastro = new JButton[INDICES_BOTOES.length];

        Janela.addKeyListener(keyAgendamento());
        Janela.setFocusable(true);

        construirTela();
        this.setVisible(false);
        Janela.setVisible(true);
        this.setVisible(true);
    }

    private void construirTela() {

        PainelAgendamento = new PainelAgendamento(keyAgendamento());

        PainelAgendamento.setLocation(7, 5);

        for (int i = 0; i < INDICES_BOTOES.length; i++) {
            BotoesCadastro[i] = new JButton(INDICES_BOTOES[i]);
            BotoesCadastro[i].setBounds(428 + (i * 190), 290, 180, 40);
            BotoesCadastro[i].setMargin(new Insets(0, 0, 0, 0));
            BotoesCadastro[i].addActionListener(this);
            this.add(BotoesCadastro[i]);
        }

        this.add(PainelAgendamento);

    }

    private boolean checarCadastro() {

        String aux = "<html><b>PROBLEMA NO PREENCHIMENTO DOS CAMPOS:</b></html>\n";
        boolean condicao = true;

        if (PainelAgendamento.getData() == null) {
            aux += "\nDATA NÃO INSERIDA";
            condicao = false;
        }
        if (PainelAgendamento.getHorario() == null) {
            aux += "\nHORÁRIO INVÁLIDO";
            condicao = false;
        }
        if (PainelAgendamento.getPet() == null) {
            aux += "\nNENHUM PET FOI SELECIONADO";
            condicao = false;
        }
        if (PainelAgendamento.getCliente() == null) {
            aux += "\nNENHUM DONO FOI SELECIONADO";
            condicao = false;
        }

        if (!condicao) {
            JOptionPane.showMessageDialog(null, aux, "ERRO AO INSERIR DADOS", JOptionPane.ERROR_MESSAGE);
        }

        return condicao;
    }

    public final KeyAdapter keyAgendamento() {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_F1) {
                    inserirAgendamento();
                } else if (e.getKeyCode() == KeyEvent.VK_F2) {
                    Janela.dispose();
                } else if (e.getKeyCode() == KeyEvent.VK_F3) {
                    PainelAgendamento.limparCampos();
                }
            }
        };
    }

    private void inserirAgendamento() {
        if (checarCadastro()) {
            objAgendamento = new AgendamentosVO();
            objAgendamento.setFkTipoServ(PainelAgendamento.geTipoServico());
            objAgendamento.setFkFunc(PainelAgendamento.getFuncionario());
            objAgendamento.setDataAgend(PainelAgendamento.getData());
            objAgendamento.setHorario(PainelAgendamento.getHorario());
            objAgendamento.setObs(PainelAgendamento.getObservacoes());
            objAgendamento.setStatus(false);
            objAgendamento.setFkPet(PainelAgendamento.getPet());
            objAgendamento.setFkDono(PainelAgendamento.getCliente());

            if (AgendamentosDAO.inserirAgendamento(objAgendamento)) {
                Menu.MenuAdmin.TabelaAgenda = AgendamentosDAO.selectAgendamentoDiario();
//                int aux;
//                if (Menu.MenuAdmin.TabelaAgenda.get(Menu.MenuAdmin.AgendaTopo.getSelectedRow()).isStatus()) {
//                    aux = 0;
//                } else {
//                    aux = 1;
//                }
                //AgendamentosDAO.autalizarStatus(aux, Menu.MenuAdmin.TabelaAgenda.get(Menu.MenuAdmin.AgendaTopo.getSelectedRow()).getCod_agend());
                Menu.MenuAdmin.TabelaAgenda = AgendamentosDAO.selectAgendamentoDiario();
                while (Menu.MenuAdmin.ModeloAgendaTopo.getRowCount() > 0) {
                    Menu.MenuAdmin.ModeloAgendaTopo.removeRow(0);
                }
                Menu.MenuAdmin.atualizarAgenda();
                Janela.dispose();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent evt) {

        if (evt.getSource() == BotoesCadastro[0]) {
            PainelAgendamento.limparCampos();
        } else if (evt.getSource() == BotoesCadastro[1]) {
            Janela.dispose();
        } else if (evt.getSource() == BotoesCadastro[2]) {
            inserirAgendamento();
        }

    }

}

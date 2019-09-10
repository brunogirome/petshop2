
package Ambientes;

import Controle.PetShop2;
import DAO.AgendamentosDAO;
import Menu.MenuAdmin;
import VOs.AgendamentosVO;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LNPC
 */
public class AmbienteAgenda extends JInternalFrame implements ActionListener {

    private JTable Tabela;
    private JScrollPane ScrollTabela;
    private DefaultTableModel ModeloTabela;

    private Container ContainerAmbiente;

    private final String INDICES_BOTOES[] = {"AGENDAR", "VISUALIZAÇÃO COMPLETA", "PESQUISAR MÊS", "PESQUISAR PET", "PESQUISAR CLIENTE", "ALTERAR STATUS", "FECHAR"};

    private JButton BotoesAgenda[];

    public static final String INDICES_AGENDAMENTO[] = {"SERVIÇO:", "FUNCIONÁRIO:", "DATA:", "HORÁRIO:", "OBSERVAÇÕES:", "STATUS:", "PET:", "DONO:"};

    public static ArrayList<AgendamentosVO> TabelaAgendamento;

    public AmbienteAgenda() {

        Menu.MenuAdmin.AmbienteAdmin.removeAll();

        ContainerAmbiente = new Container();
        ContainerAmbiente.setLayout(null);

        BotoesAgenda = new JButton[INDICES_BOTOES.length];

        TabelaAgendamento = AgendamentosDAO.selectAgendamentos();

        construirAmbiente();
        this.setBorder(null);
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.add(ContainerAmbiente);
        this.setSize(980, 475);
        this.setVisible(true);

    }

    private void construirAmbiente() {

        ModeloTabela = new DefaultTableModel() {
            boolean[] canEdit = new boolean[]{false, false, false, false, false, false, false, false, false};

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }

        };
        Tabela = new JTable(ModeloTabela);
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
        ScrollTabela.setBounds(0, 0, 820, 475);
        ContainerAmbiente.add(ScrollTabela);

        JPanel painelBotoes = new JPanel(null);
        painelBotoes.setBounds(821, 0, 160, 475);
        painelBotoes.setBorder(BorderFactory.createEtchedBorder());
        ContainerAmbiente.add(painelBotoes);

        for (int i = 0; i < INDICES_AGENDAMENTO.length; i++) {
            ModeloTabela.addColumn(INDICES_AGENDAMENTO[i]);
        }

        for (int i = 0; i < TabelaAgendamento.size(); i++) {
            ModeloTabela.addRow(rowAgenda(i));
        }

        for (int i = 0; i < INDICES_BOTOES.length; i++) {
            BotoesAgenda[i] = new JButton(INDICES_BOTOES[i]);
            BotoesAgenda[i].addActionListener(this);
            BotoesAgenda[i].setMargin(new Insets(0, 0, 0, 0));
            if (i < INDICES_BOTOES.length - 1) {
                BotoesAgenda[i].setBounds(2, 2 + (i * 50), 155, 40);
            } else {
                BotoesAgenda[i].setBounds(2, 433, 155, 40);
            }
            painelBotoes.add(BotoesAgenda[i]);
        }

        int valoresColuna[] = {55, 150, 50, 20, 100, 35, 35};

        for (int i = 0; i < valoresColuna.length; i++) {
            Tabela.getColumnModel().getColumn(i).setPreferredWidth(valoresColuna[i]);

        }

        JButton botaoAtualizar = new JButton("ATUALIZAR");
        botaoAtualizar.setBounds(2, 391, 155, 40);
        botaoAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TabelaAgendamento = AgendamentosDAO.selectAgendamentos();
                while (Tabela.getRowCount() > 0) {
                    ModeloTabela.removeRow(0);
                }
                for (int i = 0; i < TabelaAgendamento.size(); i++) {
                    ModeloTabela.addRow(rowAgenda(i));
                }
            }
        });
        painelBotoes.add(botaoAtualizar);

    }

    public static String[] rowAgenda(int i) {
        String auxStatus;
        String auxCor;

        if (!TabelaAgendamento.get(i).isStatus()) {
            auxStatus = "Pendente";
            auxCor = "<html><font color=\"rgb(205,19,39)\"> ";
        } else {
            auxStatus = "Concluído";
            auxCor = "";
        }

        return new String[]{
            auxCor + TabelaAgendamento.get(i).getFkTipoServ(),
            auxCor + TabelaAgendamento.get(i).getFkFunc(),
            auxCor + PetShop2.DATA_BRASIL_SQL.format(TabelaAgendamento.get(i).getDataAgend()),
            auxCor + PetShop2.FORMATAR_HORARIO_SQL.format(Time.valueOf(TabelaAgendamento.get(i).getHorario())),
            auxCor + TabelaAgendamento.get(i).getObs(),
            auxCor + auxStatus,
            auxCor + TabelaAgendamento.get(i).getFkPet(),
            auxCor + TabelaAgendamento.get(i).getFkDono()
        };

    }

    @Override
    public void actionPerformed(ActionEvent evt) {

        if (evt.getSource() == BotoesAgenda[0]) {
            new Cadastramento.CadastrarAgendamento();
        } else if (evt.getSource() == BotoesAgenda[1]) {
            new AlterarDados.AlterarAgenda();
        } else if (evt.getSource() == BotoesAgenda[2]) {
            String busca = JOptionPane.showInputDialog("INSIRA A DATA (ex: julho, setembro, etc.):");

            if (busca != null && !busca.equals("")) {
                int mesBuscador = 0;

                boolean sinal;

                if (busca.toLowerCase().equals("janeiro")) {
                    mesBuscador = 0;
                    sinal = true;
                } else if (busca.toLowerCase().equals("fevereiro")) {
                    mesBuscador = 1;
                    sinal = true;
                } else if (busca.toLowerCase().equals("março") || busca.toLowerCase().equals("marco")) {
                    mesBuscador = 2;
                    sinal = true;
                } else if (busca.toLowerCase().equals("abril")) {
                    mesBuscador = 3;
                    sinal = true;
                } else if (busca.toLowerCase().equals("maio")) {
                    mesBuscador = 3;
                    sinal = true;
                } else if (busca.toLowerCase().equals("junho")) {
                    mesBuscador = 5;
                    sinal = true;
                } else if (busca.toLowerCase().equals("julho")) {
                    mesBuscador = 6;
                    sinal = true;
                } else if (busca.toLowerCase().equals("agosto")) {
                    mesBuscador = 7;
                    sinal = true;
                } else if (busca.toLowerCase().equals("setembro")) {
                    mesBuscador = 8;
                    sinal = true;
                } else if (busca.toLowerCase().equals("outubro")) {
                    mesBuscador = 9;
                    sinal = true;
                } else if (busca.toLowerCase().equals("novembro")) {
                    mesBuscador = 10;
                    sinal = true;
                } else if (busca.toLowerCase().equals("dezembro")) {
                    mesBuscador = 11;
                    sinal = true;
                } else {
                    JOptionPane.showMessageDialog(null, "DATA INVÁLIDA");
                    sinal = false;
                }

                if (sinal) {
                    while (Tabela.getRowCount() > 0) {
                        ModeloTabela.removeRow(0);
                    }
                    for (int i = 0; i < TabelaAgendamento.size(); i++) {
                        if (TabelaAgendamento.get(i).getDataAgend().getMonth() == mesBuscador) {
                            ModeloTabela.addRow(rowAgenda(i));

                        }
                    }
                }
            }
        } else if (evt.getSource() == BotoesAgenda[3]) {
            String busca = JOptionPane.showInputDialog("INSIRA O NOME DO PET:");
            if (busca != null && !busca.equals("")) {
                while (Tabela.getRowCount() > 0) {
                    ModeloTabela.removeRow(0);
                }

                for (int i = 0; i < TabelaAgendamento.size(); i++) {
                    if (TabelaAgendamento.get(i).getFkPet().toLowerCase().contains(busca.toLowerCase())) {
                        ModeloTabela.addRow(rowAgenda(i));
                    }
                }
            }
        } else if (evt.getSource() == BotoesAgenda[4]) {
            String busca = JOptionPane.showInputDialog("INSIRA O NOME DO CLIENTE:");

            if (busca != null && !busca.equals("")) {
                while (Tabela.getRowCount() > 0) {
                    ModeloTabela.removeRow(0);
                }

                for (int i = 0; i < TabelaAgendamento.size(); i++) {
                    if (TabelaAgendamento.get(i).getFkDono().toLowerCase().contains(busca.toLowerCase())) {
                        ModeloTabela.addRow(rowAgenda(i));
                    }
                }
            }
        } else if (evt.getSource() == BotoesAgenda[5]) {
            if (!Tabela.getSelectionModel().isSelectionEmpty()) {
                int yesNo = JOptionPane.showConfirmDialog(null, "CONFIRMAR A ALTERAÇÃO NA CONSULTA DE \"" + TabelaAgendamento.get(Tabela.getSelectedRow()).getFkPet() + "\"?", "CONFIRME:", JOptionPane.YES_NO_OPTION);
                if (yesNo == JOptionPane.YES_OPTION) {
                    int aux;
                    if (TabelaAgendamento.get(Tabela.getSelectedRow()).isStatus()) {
                        aux = 0;
                    } else {
                        aux = 1;
                    }
                    AgendamentosDAO.autalizarStatus(aux, TabelaAgendamento.get(Tabela.getSelectedRow()).getCod_agend());
                    TabelaAgendamento = AgendamentosDAO.selectAgendamentos();
                    while (Tabela.getRowCount() > 0) {
                        ModeloTabela.removeRow(0);
                    }
                    for (int i = 0; i < TabelaAgendamento.size(); i++) {
                        ModeloTabela.addRow(rowAgenda(i));
                    }
                }

            }

        } else if (evt.getSource() == BotoesAgenda[INDICES_BOTOES.length - 1]) {
            this.dispose();
            Menu.MenuAdmin.LabelIcone.setIcon(new ImageIcon("LabelAdmin.png"));
            MenuAdmin.arrumarBotoes(8);
            MenuAdmin.AmbienteAdmin.removeAll();
            MenuAdmin.AmbienteAdmin.repaint();
        }

    }

}

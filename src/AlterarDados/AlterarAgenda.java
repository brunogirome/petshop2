/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlterarDados;

import static Ambientes.AmbienteAgenda.TabelaAgendamento;
import Controle.PetShop2;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Time;
import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LNPC
 */
public class AlterarAgenda extends Container implements ActionListener {

    private JTable Tabela;
    private JScrollPane ScrollTabela;
    private DefaultTableModel ModeloTabela;

    private JFrame Janela;

    private boolean Editavel = false;

    private final String[] ALTERACAO_INDICES = {"#", "SERVIÇO:", "FUNC:", "DATA", "HORÁRIO:", "OBS.:", "STATUS:", "PETS:", "CLIENTE:"};

    private JFormattedTextField[] InputsAgenda;

    private JLabel LabelsAgenda[];

    public AlterarAgenda() {

        LabelsAgenda = new JLabel[ALTERACAO_INDICES.length];
        InputsAgenda = new JFormattedTextField[ALTERACAO_INDICES.length];

        Janela = new JFrame("VISUALIZAR TODOS OS AGENDAMENTOS");
        Janela.setSize(1280, 720);//100
        Janela.setResizable(false);
        Janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Janela.setLocationRelativeTo(null);
        Janela.add(this);

        construirTela();
        this.setVisible(false);
        Janela.setVisible(true);
        this.setVisible(true);

    }

    private void construirTela() {

        ModeloTabela = new DefaultTableModel() {
            boolean[] canEdit = new boolean[]{false, false, false, false, false, false, false, false, false};
            @Override
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
        Tabela.setRowHeight(22);
        Tabela.getTableHeader().setFont(new Font("Arial", 1, 12));
        Tabela.getTableHeader().setForeground(Color.white);
        Tabela.getTableHeader().setOpaque(false);
        Tabela.getTableHeader().setBackground(new Color(71, 173, 255));
        Tabela.setSelectionBackground(new Color(153, 204, 255));
        Tabela.setSelectionForeground(Color.black);
        
        Tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ScrollTabela = new JScrollPane();
        ScrollTabela.setViewportView(Tabela);

        for (int i = 0; i < ALTERACAO_INDICES.length - 1; i++) {
            ModeloTabela.addColumn(ALTERACAO_INDICES[i]);
        }

        ScrollTabela.setBounds(307, 5, 963, 680);//160,723
        this.add(ScrollTabela);

        for (int i = 0; i < TabelaAgendamento.size(); i++) {
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

        int valoresColuna[] = {20, 70, 245, 70, 50, 245, 50, 50};

        for (int i = 0; i < valoresColuna.length; i++) {
            Tabela.getColumnModel().getColumn(i).setPreferredWidth(valoresColuna[i]);

        }
    }

    public static String[] rowAgenda(int i) {

        String auxStatus;

        if (!TabelaAgendamento.get(i).isStatus()) {
            auxStatus = "Pendente";
        } else {
            auxStatus = "Concluído";
        }

        String aux[] = {
            TabelaAgendamento.get(i).getCod_agend() + "",
            TabelaAgendamento.get(i).getFkTipoServ(),
            TabelaAgendamento.get(i).getFkFunc(),
            PetShop2.DATA_BRASIL_SQL.format(TabelaAgendamento.get(i).getDataAgend()),
            PetShop2.FORMATAR_HORARIO_SQL.format(Time.valueOf(TabelaAgendamento.get(i).getHorario())),
            TabelaAgendamento.get(i).getObs(),
            auxStatus,
            TabelaAgendamento.get(i).getFkPet(),
            TabelaAgendamento.get(i).getFkDono()
        };

        return aux;
    }

    private void preencherDados(int cod) {
        InputsAgenda[0].setText(TabelaAgendamento.get(cod).getCod_agend() + "");
        InputsAgenda[1].setText(TabelaAgendamento.get(cod).getFkTipoServ());
        InputsAgenda[2].setText(TabelaAgendamento.get(cod).getFkFunc());
        InputsAgenda[3].setText(PetShop2.DATA_BRASIL_SQL.format(TabelaAgendamento.get(cod).getDataAgend()));
        InputsAgenda[4].setText(PetShop2.FORMATAR_HORARIO_SQL.format(Time.valueOf(TabelaAgendamento.get(cod).getHorario())));
        InputsAgenda[5].setText(TabelaAgendamento.get(cod).getObs());
        if (TabelaAgendamento.get(cod).isStatus()) {
            InputsAgenda[6].setText("Realizado");
        } else {
            InputsAgenda[6].setText("Pendente");
        }
        InputsAgenda[7].setText(TabelaAgendamento.get(cod).getFkPet());
        InputsAgenda[8].setText(TabelaAgendamento.get(cod).getFkDono());

    }

    public void actionPerformed(ActionEvent evt) {

    }

}

package Menu;

import Ambientes.AmbienteAgenda;
import Ambientes.AmbienteCliente;
import Ambientes.AmbienteEstoque;
import Ambientes.AmbienteFuncionario;
import Ambientes.AmbientePet;
import Ambientes.AmbienteVenda;
import Controle.PetShop2;
import DAO.AgendamentosDAO;
import VOs.AgendamentosVO;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class MenuAdmin extends Container implements ActionListener {

    private JFrame Janela;

    public static JDesktopPane AmbienteAdmin;

    private final String INDICES_BOTOES[] = {"AGENDA", "CLIENTES", "PETS", "FUNCIONÁRIOS", "VENDAS", "ESTOQUE", "CAIXA", "SAIR"};

    private final String INDICES_AGENDA_TOPO[] = {"STATUS:", "SERVIÇO:", "HORA:", "PET/DONO:"};

    public static JButton BotoesAdmin[];

    public static ArrayList<AgendamentosVO> TabelaAgenda;

    public static JTable AgendaTopo;
    public static DefaultTableModel ModeloAgendaTopo;

    private JToggleButton botaoStatus;

    public MenuAdmin() {

        AmbienteAdmin = new JDesktopPane() {
            @Override
            public void paintComponent(Graphics g) {
                g.drawImage(Controle.PetShop2.buscarImagem("bgserio.png"), 0, 0, 980, 475, null);
                g.setColor(new Color(240, 240, 240));
                //g.fillRect(0, 0, 980, 475);
            }
        };
        BotoesAdmin = new JButton[INDICES_BOTOES.length];

        Janela = new JFrame("SHOWPET - ADMINISTRAÇÃO");
        Janela.setSize(1280, 720);
        Janela.setResizable(false);
        Janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Janela.setLocationRelativeTo(null);
        Janela.add(this);

        TabelaAgenda = AgendamentosDAO.selectAgendamentoDiario();

        construirTela();
        this.setVisible(false);
        Janela.setVisible(true);
        this.setVisible(true);
    }

    private void construirTela() {

        JPanel painelTopo = new JPanel(null) {
            @Override
            public void paintComponent(Graphics g) {
                g.drawImage(PetShop2.buscarImagem("logoTop.png"), 645, 5, 330, 190, null);
            }
        };
        painelTopo.setBorder(BorderFactory.createEtchedBorder());
        painelTopo.setBounds(10, 5, 980, 200);
        this.add(painelTopo);

        ModeloAgendaTopo = new DefaultTableModel() {
            boolean[] canEdit = new boolean[]{false, false, false, false, false, false, false, false, false};

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }

        };

        JPanel painelSelecao = new JPanel(null);
        painelSelecao.setBounds(490, 5, 150, 190);
        painelSelecao.setBorder(BorderFactory.createEtchedBorder());
        painelTopo.add(painelSelecao);

        JTextField textoAgenda = new JTextField();
        textoAgenda.setBounds(5, 5, 140, 25);
        textoAgenda.setEditable(false);
        painelSelecao.add(textoAgenda);

        JEditorPane textoObservacao = new JEditorPane();
        textoObservacao.setBounds(5, 35, 140, 115);
        textoObservacao.setContentType("text/html");
        textoObservacao.setEditable(false);
        textoObservacao.setBackground(new Color(240, 240, 240));
        textoObservacao.setBorder(BorderFactory.createEtchedBorder());
        painelSelecao.add(textoObservacao);

        AgendaTopo = new JTable(ModeloAgendaTopo);
        AgendaTopo.setShowGrid(false);
        AgendaTopo.setIntercellSpacing(new Dimension(0, 0));
        AgendaTopo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        AgendaTopo.getTableHeader().setPreferredSize(new Dimension(100, 17));
        AgendaTopo.getTableHeader().setFont(new Font("Consolas", 2, 11));
        AgendaTopo.getTableHeader().setForeground(Color.black);
        AgendaTopo.getTableHeader().setOpaque(false);
        AgendaTopo.getTableHeader().setBackground(new Color(51, 153, 255));
        AgendaTopo.setRowHeight(22);
        AgendaTopo.setFont(new Font("Arial", 0, 12));
        AgendaTopo.setBackground(new Color(240, 240, 240));
        AgendaTopo.setSelectionBackground(new Color(153, 204, 255));
        AgendaTopo.setSelectionForeground(Color.black);
        AgendaTopo.setForeground(Color.black);
        AgendaTopo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (TabelaAgenda.get(AgendaTopo.getSelectedRow()).isStatus()) {
                    textoAgenda.setForeground(new Color(0, 100, 0));
                    textoAgenda.setFont(new Font("Consolas", 0, 15));
                    textoAgenda.setText("REALIZADO");
                } else {
                    textoAgenda.setForeground(new Color(100, 0, 0));
                    textoAgenda.setFont(new Font("Consolas", 0, 15));
                    textoAgenda.setText("PENDENTE");
                }
                textoObservacao.setText("<html><font face =\"Arial\" size=\"3\"><b>Funcionário:</b> " + TabelaAgenda.get(AgendaTopo.getSelectedRow()).getFkFunc() + "</html>");
                if (TabelaAgenda.get(AgendaTopo.getSelectedRow()).getObs() != null && !TabelaAgenda.get(AgendaTopo.getSelectedRow()).getObs().replace(" ", "").equals("")) {
                    textoObservacao.setText("<html><font face =\"Arial\" size=\"3\"><b>Funcionário:</b> " + TabelaAgenda.get(AgendaTopo.getSelectedRow()).getFkFunc() + " <strong>Obs:</strong> " + TabelaAgenda.get(AgendaTopo.getSelectedRow()).getObs() + "</html>");
                }
                botaoStatus.setEnabled(true);
                botaoStatus.setSelected(TabelaAgenda.get(AgendaTopo.getSelectedRow()).isStatus());
            }
        });

        JScrollPane scrollTabela = new JScrollPane();
        scrollTabela.setViewportView(AgendaTopo);
        scrollTabela.setBounds(5, 5, 480, 190);
        scrollTabela.setBackground(new Color(255, 102, 102));
        painelTopo.add(scrollTabela);

        botaoStatus = new JToggleButton("ALTERAR STATUS");
        botaoStatus.setBounds(5, 155, 140, 30);
        botaoStatus.setEnabled(false);
        botaoStatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int aux;
                if (TabelaAgenda.get(AgendaTopo.getSelectedRow()).isStatus()) {
                    aux = 0;
                } else {
                    aux = 1;
                }
                AgendamentosDAO.autalizarStatus(aux, TabelaAgenda.get(AgendaTopo.getSelectedRow()).getCod_agend());
                TabelaAgenda = AgendamentosDAO.selectAgendamentoDiario();
                while (ModeloAgendaTopo.getRowCount() > 0) {
                    ModeloAgendaTopo.removeRow(0);
                }
                atualizarAgenda();
                botaoStatus.setEnabled(false);
                textoAgenda.setText("");
                textoObservacao.setText("");
            }
        });
        painelSelecao.add(botaoStatus);

        for (int i = 0; i < INDICES_AGENDA_TOPO.length; i++) {
            ModeloAgendaTopo.addColumn(INDICES_AGENDA_TOPO[i]);
        }

        atualizarAgenda();

        AmbienteAdmin.setBorder(BorderFactory.createEtchedBorder());
        AmbienteAdmin.setBounds(10, 210, 980, 475);
        AmbienteAdmin.setBackground(Color.white);
        this.add(AmbienteAdmin);
        
        for (int i = 0; i < INDICES_BOTOES.length; i++) {
            BotoesAdmin[i] = new JButton(new ImageIcon("res\\botoes\\botao" + i + ".png"));
            BotoesAdmin[i].addActionListener(this);
            BotoesAdmin[i].setOpaque(true);
            BotoesAdmin[i].setContentAreaFilled(false);
            BotoesAdmin[i].setBorderPainted(false);
            BotoesAdmin[i].setBounds(1010, 210 + (i * 60), 250, 50);
            BotoesAdmin[i].setFocusPainted(false);
            this.add(BotoesAdmin[i]);
        }

        JPanel painelIcones = new JPanel(new GridLayout(1, 1));
        LabelIcone = new JLabel(new ImageIcon(auxImagem));
        painelIcones.setBounds(1035, 5, 200, 200);
        painelIcones.add(LabelIcone);
        this.add(painelIcones);

        int valoresColuna[] = {75, 100, 45, 260};

        for (int i = 0; i < valoresColuna.length; i++) {
            AgendaTopo.getColumnModel().getColumn(i).setPreferredWidth(valoresColuna[i]);
        }

    }

    public static JLabel LabelIcone;

    String auxImagem = "LabelAdmin.png";

    private static String[] rowAgendaTopo(int i) {

        String auxStatus;
        String auxCor;

        if (TabelaAgenda.get(i).isStatus()) {
            auxStatus = "REALIZADO";
            auxCor = "<html><font color=\"rgb(0, 100, 0)\"> ";
        } else {
            auxStatus = "PENDENTE";
            auxCor = "<html><font color=\"rgb(100,0,0)\"> ";
        }

        return new String[]{
            auxCor + auxStatus,
            TabelaAgenda.get(i).getFkTipoServ().toUpperCase(),
            PetShop2.FORMATAR_HORARIO_SQL.format(Time.valueOf(TabelaAgenda.get(i).getHorario())),
            "<html><font color = \"rgb(139,69,19)\">" + TabelaAgenda.get(i).getFkPet() + "<font color =\"rgb(0,0,0)\">, " + TabelaAgenda.get(i).getFkDono()
        };
    }

    public static void atualizarAgenda() {
        for (int i = 0; i < TabelaAgenda.size(); i++) {
            ModeloAgendaTopo.addRow(rowAgendaTopo(i));
        }
    }

    public static void arrumarBotoes(int codBotao) {
        if (codBotao < BotoesAdmin.length) {
            BotoesAdmin[codBotao].setIcon(new ImageIcon("res\\botoes\\botao" + codBotao + "abaixo.png"));
        }
        for (int i = 0; i < BotoesAdmin.length; i++) {
            if (i != codBotao) {
                BotoesAdmin[i].setIcon(new ImageIcon("res\\botoes\\botao" + i + ".png"));
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == BotoesAdmin[0]) {
            AmbienteAdmin.add(new AmbienteAgenda());
            auxImagem = "LabelAgenda.png";
            LabelIcone.setIcon(new ImageIcon(auxImagem));
            arrumarBotoes(0);
        } else if (evt.getSource() == BotoesAdmin[1]) {
            AmbienteAdmin.add(new AmbienteCliente());
            auxImagem = "LabelClientes.png";
            LabelIcone.setIcon(new ImageIcon(auxImagem));
            arrumarBotoes(1);
        } else if (evt.getSource() == BotoesAdmin[2]) {
            AmbienteAdmin.add(new AmbientePet());
            auxImagem = "LabelPets.png";
            LabelIcone.setIcon(new ImageIcon(auxImagem));
            arrumarBotoes(2);
        } else if (evt.getSource() == BotoesAdmin[3]) {
            AmbienteAdmin.add(new AmbienteFuncionario());
            auxImagem = "LabelFuncionarios.png";
            LabelIcone.setIcon(new ImageIcon(auxImagem));
            arrumarBotoes(3);
        } else if (evt.getSource() == BotoesAdmin[4]) {
            AmbienteAdmin.add(new AmbienteVenda());
            auxImagem = "LabelVendas.png";
            LabelIcone.setIcon(new ImageIcon(auxImagem));
            arrumarBotoes(4);
        } else if (evt.getSource() == BotoesAdmin[5]) {
            AmbienteAdmin.add(new AmbienteEstoque());
            auxImagem = "LabelEstoque.png";
            LabelIcone.setIcon(new ImageIcon(auxImagem));
            arrumarBotoes(5);
        } else if (evt.getSource() == BotoesAdmin[6]) {
            new JanelaCaixa();
        } else if (evt.getSource() == BotoesAdmin[7]) {
            arrumarBotoes(7);
            System.exit(0);
        }
    }

}

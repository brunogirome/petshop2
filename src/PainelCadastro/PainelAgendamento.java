package PainelCadastro;

import Controle.PetShop2;
import DAO.ClientesDAO;
import DAO.FuncionariosDAO;
import DAO.PetsDAO;
import DAO.ServicosDAO;
import VOs.ClientesVO;
import VOs.FuncionariosVO;
import VOs.PetsVO;
import VOs.ServicosVO;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

public class PainelAgendamento extends JPanel implements FocusListener {

    private final String INDICES_AGENDAMENTO[] = {"Tipo de serviço:", "Funcionario:", "Data:", "Horário:", "Observações:", "Pet registrado:", "Dono:"};

    private JLabel LabelsAgendamento[];

    private static JFormattedTextField InputsPainel[];

    private static JComboBox<String> SelecaoAgendamento[];

    private String[] IndicesSelecao[] = new String[2][];

    private JScrollPane ScrollPainel[];

    private static JTextArea TextoObservacoes;

    private JList<String> SelecionarDono;
    private ArrayList<ClientesVO> ListaClientes;
    private String ValoresDonos[];

    private JList<String> SelecionarPet;
    private ArrayList<PetsVO> ListaPets;
    private String ValoresPets[];

    private ArrayList<ServicosVO> ListaServicos;

    private ArrayList<FuncionariosVO> ListaFuncionarios;

    public PainelAgendamento(KeyAdapter keyAgenda) {
        LabelsAgendamento = new JLabel[INDICES_AGENDAMENTO.length];
        InputsPainel = new JFormattedTextField[2];
        SelecaoAgendamento = new JComboBox[2];
        ScrollPainel = new JScrollPane[3];
        TextoObservacoes = new JTextArea();
        TextoObservacoes.addKeyListener(keyAgenda);

        IndicesSelecao[0] = ServicosDAO.carregarServicos();
        IndicesSelecao[1] = FuncionariosDAO.carregaSelecao();

        ListaClientes = ClientesDAO.selectClientes(null);
        ValoresDonos = new String[ListaClientes.size()];
        for (int i = 0; i < ListaClientes.size(); i++) {
            ValoresDonos[i] = ListaClientes.get(i).getNome();
        }
        SelecionarDono = new JList<>(new AbstractListModel<String>() {
            @Override
            public int getSize() {
                return ValoresDonos.length;
            }

            @Override
            public String getElementAt(int index) {
                return ValoresDonos[index];
            }
        });

        ListaPets = PetsDAO.selectPets();
        ValoresPets = new String[ListaPets.size()];
        for (int i = 0; i < ListaPets.size(); i++) {
            ValoresPets[i] = ListaPets.get(i).getNome();
        }

        SelecionarPet = new JList<>(new AbstractListModel<String>() {
            @Override
            public int getSize() {
                return ValoresPets.length;
            }

            @Override
            public String getElementAt(int index) {
                return ValoresPets[index];
            }
        });

        ListaServicos = ServicosDAO.selectServicos();

        ListaFuncionarios = FuncionariosDAO.selectFuncionarios();

        this.setSize(980, 280);
        this.setBorder(BorderFactory.createEtchedBorder());
        this.setLayout(null);
        construirPainel(keyAgenda);

    }

    private void construirPainel(KeyAdapter keyAgenda) {

        JPanel paineisAux[] = new JPanel[3];

        for (int i = 0; i < INDICES_AGENDAMENTO.length; i++) {
            LabelsAgendamento[i] = new JLabel(INDICES_AGENDAMENTO[i]);
            LabelsAgendamento[i].setFont(new Font("Tahoma", 1, 13));

            if (i < paineisAux.length) {
                paineisAux[i] = new JPanel(null);
                paineisAux[i].setBorder(BorderFactory.createEtchedBorder());
                if (i == 0) {
                    paineisAux[i].setBounds(10, 75, 400, 200);
                    this.add(paineisAux[i]);
                } else if (i > 0) {
                    paineisAux[i].setBounds(420 + (Controle.Calculos.centralizarPaineis(i - 1, 200, 2, 550)), 75, 200, 200);
                    this.add(paineisAux[i]);
                }
            }

            if (i < ScrollPainel.length) {
                ScrollPainel[i] = new JScrollPane();
            }

            if (i < InputsPainel.length) {
                InputsPainel[i] = new JFormattedTextField();
                InputsPainel[i].addFocusListener(this);
                InputsPainel[i].addKeyListener(keyAgenda);
            }

            if (i < SelecaoAgendamento.length) {
                SelecaoAgendamento[i] = new JComboBox<>(IndicesSelecao[i]);
                SelecaoAgendamento[i].addKeyListener(keyAgenda);
            }

            if (i < 4) {
                LabelsAgendamento[i].setBounds(10 + (i * 242), 10, 232, 25);
                this.add(LabelsAgendamento[i]);
                if (i < 2) {
                    SelecaoAgendamento[i].setBounds(10 + (i * 242), 35, 232, 30);
                    InputsPainel[i].setBounds(10 + ((i + 2) * 242), 35, 232, 30);
                    this.add(InputsPainel[i]);
                    this.add(SelecaoAgendamento[i]);
                }
            } else if (i > 3) {
                LabelsAgendamento[i].setBounds(5, 5, 190, 25);
                paineisAux[i - 4].add(LabelsAgendamento[i]);
                if (i == 4) {
                    ScrollPainel[i - 4].setViewportView(TextoObservacoes);
                    ScrollPainel[i - 4].setBounds(5, 30, 390, 165);
                    paineisAux[i - 4].add(ScrollPainel[i - 4]);
                }
            }
        }

        SelecionarDono.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        SelecionarDono.setDragEnabled(true);
        SelecionarDono.addKeyListener(keyAgenda);

        JScrollPane scrollDono = new JScrollPane();
        scrollDono.setViewportView(SelecionarDono);
        scrollDono.setBounds(5, 25, 190, 170);
        paineisAux[2].add(scrollDono);

        SelecionarPet.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        SelecionarPet.setDragEnabled(true);
        SelecionarPet.addKeyListener(keyAgenda);
        SelecionarPet.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                for (int i = 0; i < ListaClientes.size(); i++) {
                    if (ListaPets.get(SelecionarPet.getSelectedIndex()).getFkDono().equals(ListaClientes.get(i).getNome())) {
                        SelecionarDono.setSelectedIndex(i);
                    }
                }
            }
        });

        JScrollPane scrollPet = new JScrollPane();
        scrollPet.setViewportView(SelecionarPet);
        scrollPet.setBounds(5, 25, 190, 170);
        paineisAux[1].add(scrollPet);

    }

    public static void limparCampos() {
        for (int i = 0; i < InputsPainel.length; i++) {
            InputsPainel[i].setText(null);
        }
        for (int i = 0; i < SelecaoAgendamento.length; i++) {
            SelecaoAgendamento[i].setSelectedIndex(0);
        }
        TextoObservacoes.setText(null);

    }

    public String geTipoServico() {
        return ListaServicos.get(SelecaoAgendamento[0].getSelectedIndex()).getCod_serv() + "";
    }

    public String getFuncionario() {
        return ListaFuncionarios.get(SelecaoAgendamento[1].getSelectedIndex()).getCod_cli() + "";
    }

    public Date getData() {

        LocalDate data = null;

        if (InputsPainel[0].getText().replace("/", "").replace(" ", "").equals("")) {
            return null;
        } else {
            try {
                DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                data = LocalDate.parse(InputsPainel[0].getText(), formatoData);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "DATA INVÁLIDA", "ERRO", JOptionPane.ERROR_MESSAGE);
            }
            return Date.valueOf(data);
        }
    }

    public String getHorario() {
        if (InputsPainel[1].getText().replace(":", "").replace(" ", "").equals("")) {
            return null;
        } else {
            return InputsPainel[1].getText();
        }
    }

    public String getObservacoes() {
        return TextoObservacoes.getText();
    }

    public String getCliente() {
        if (SelecionarPet.isSelectionEmpty()) {
            return null;
        } else {
            return ListaClientes.get(SelecionarDono.getSelectedIndex()).getCod_cli() + "";
        }
    }

    public String getPet() {
        if (SelecionarPet.isSelectionEmpty()) {
            return null;
        } else {
            return ListaPets.get(SelecionarPet.getSelectedIndex()).getCod_pet() + "";
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == InputsPainel[0] && InputsPainel[0].getText().equals("")) {
            PetShop2.setFormato(InputsPainel[0], PetShop2.FORMATO_DATA);

        } else if (e.getSource() == InputsPainel[1] && InputsPainel[1].getText().equals("")) {
            PetShop2.setFormato(InputsPainel[1], PetShop2.FORMATO_HORA);

        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == InputsPainel[0] && InputsPainel[0].getText().replace("/", "").replace(" ", "").equals("")) {
            PetShop2.setFormato(InputsPainel[0], null);

        } else if (e.getSource() == InputsPainel[1] && InputsPainel[1].getText().replace(":", "").replace(" ", "").equals("")) {
            PetShop2.setFormato(InputsPainel[1], null);

        }

    }

}

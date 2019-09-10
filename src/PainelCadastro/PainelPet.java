/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PainelCadastro;

import Controle.PetShop2;
import DAO.ClientesDAO;
import VOs.ClientesVO;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author lenovo
 */
public class PainelPet extends JPanel {

    private final String INDICES_PET[] = {"Nome:", "Raça:", "Cor:", "Peso:", "Sexo:", "Tipo Animal:", "Idade:", "Porte:", "Observações:", "Foto:", "Dono:"};

    private JLabel LabelsPet[];

    private JFormattedTextField InputsPainel[];

    private JComboBox<String> SelecaoPet[];

    private String[] IndicesSelecao[] = new String[4][];

    private JTextArea TextoObservacoes;

    private JScrollPane ScrollObservacoes;

    private JList<String> SelecionarDono;

    private ArrayList<ClientesVO> ListaClientes;

    private String ValoresLista[];

    private JButton BotaoFoto;

    public PainelPet(KeyAdapter keyPet) {

        LabelsPet = new JLabel[INDICES_PET.length];
        InputsPainel = new JFormattedTextField[4];
        SelecaoPet = new JComboBox[4];
        TextoObservacoes = new JTextArea();
        ScrollObservacoes = new JScrollPane();

        String sexo[] = {"Macho", "Fêmea"};

        IndicesSelecao[0] = sexo;
        IndicesSelecao[1] = PetShop2.PETS_TIPO;
        IndicesSelecao[2] = PetShop2.PETS_IDADE;
        IndicesSelecao[3] = PetShop2.PETS_TAMANHO;

        ListaClientes = ClientesDAO.selectClientes(null);

        ValoresLista = new String[ListaClientes.size()];

        for (int i = 0; i < ListaClientes.size(); i++) {
            ValoresLista[i] = ListaClientes.get(i).getNome();
        }

        SelecionarDono = new JList<>(new AbstractListModel<String>() {
            @Override
            public int getSize() {
                return ValoresLista.length;
            }

            @Override
            public String getElementAt(int index) {
                return ValoresLista[index];
            }
        });

        this.setSize(980, 340);
        construirPainel(keyPet);
        this.setBorder(BorderFactory.createEtchedBorder());
        this.setLayout(null);

    }

    private void construirPainel(KeyAdapter keyPet) {

        JPanel painelAux[] = new JPanel[3];
        for (int i = 0; i < painelAux.length; i++) {
            painelAux[i] = new JPanel(null);
            painelAux[i].setBorder(BorderFactory.createEtchedBorder());
            if (i == 0) {
                painelAux[i].setBounds(10, 130, 400, 200);
            } else {
                painelAux[i].setBounds(420 + (Controle.Calculos.centralizarPaineis(i - 1, 200, 2, 550)), 130, 200, 200);
            }

            this.add(painelAux[i]);
        }

        for (int i = 0; i < INDICES_PET.length; i++) {

            LabelsPet[i] = new JLabel(INDICES_PET[i]);
            LabelsPet[i].setFont(new Font("Tahoma", 1, 13));

            if (i < InputsPainel.length) {
                InputsPainel[i] = new JFormattedTextField();
                InputsPainel[i].addKeyListener(keyPet);
            }

            if (i < SelecaoPet.length) {
                SelecaoPet[i] = new JComboBox<>(IndicesSelecao[i]);
                SelecaoPet[i].addKeyListener(keyPet);
            }

            if (i == 0) {
                LabelsPet[i].setBounds(10, 10, 465, 25);
                InputsPainel[i].setBounds(10, 35, 465, 30);
                this.add(LabelsPet[i]);
                this.add(InputsPainel[i]);
            } else if (i > 0 && i < 4) {
                LabelsPet[i].setBounds(485 + ((i - 1) * 165), 10, 155, 25);
                InputsPainel[i].setBounds(485 + ((i - 1) * 165), 35, 155, 30);
                this.add(LabelsPet[i]);
                this.add(InputsPainel[i]);
            } else if (i > 3 && i < 8) {
                LabelsPet[i].setBounds(10 + ((i - 4) * 242), 65, 232, 25);
                SelecaoPet[i - 4].setBounds(10 + ((i - 4) * 242), 90, 232, 30);
                this.add(LabelsPet[i]);
                this.add(SelecaoPet[i - 4]);
            } else if (i > 7) {
                LabelsPet[i].setBounds(5, 5, 190, 25);
                painelAux[i - 8].add(LabelsPet[i]);
                if (i == 8) {
                    TextoObservacoes.setLineWrap(true);
                    TextoObservacoes.addKeyListener(keyPet);
                    ScrollObservacoes.setViewportView(TextoObservacoes);
                    ScrollObservacoes.setBounds(5, 30, 390, 165);
                    painelAux[0].add(ScrollObservacoes);
                }
            }
        }

        //SelecionarDono.setBorder(BorderFactory.createEtchedBorder());
        SelecionarDono.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        SelecionarDono.setDragEnabled(true);
        SelecionarDono.addKeyListener(keyPet);

        JScrollPane scrollDono = new JScrollPane();
        scrollDono.setViewportView(SelecionarDono);
        scrollDono.setBounds(5, 25, 190, 170);
        painelAux[2].add(scrollDono);

        BotaoFoto = new JButton("Procurar");
        BotaoFoto.setBounds(100, 5, 95, 25);
        BotaoFoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                JFileChooser janelafiltro = new JFileChooser("C:\\Users\\28264088\\Downloads");
                janelafiltro.setFileFilter(new FileNameExtensionFilter("Arquivos de imagem", "jpg", "png"));
                int sinal = janelafiltro.showOpenDialog(null);
                if (sinal == JFileChooser.APPROVE_OPTION) {
                    try {
                        FileInputStream fileX = new FileInputStream(janelafiltro.getSelectedFile().getPath());
                        FileOutputStream fileY = new FileOutputStream("fotos//" + janelafiltro.getSelectedFile().getName());

                        FileChannel channelX = fileX.getChannel();
                        FileChannel channelY = fileY.getChannel();

                        channelX.transferTo(0, channelX.size(), channelY);

                        channelX.close();
                        channelY.close();
                    } catch (Exception ex) {
                        System.out.println("Erro na seleção da foto: " + ex);
                    }

                }
            }
        });
        //painelAux[1].add(BotaoFoto);
    }

    public void limparCampos() {
        for (int i = 0; i < InputsPainel.length; i++) {
            InputsPainel[i].setText(null);
        }

        for (int i = 0; i < SelecaoPet.length; i++) {
            SelecaoPet[i].setSelectedIndex(0);
        }

    }

    public String getNome() {
        return InputsPainel[0].getText();
    }

    public String getRaca() {
        return InputsPainel[1].getText();
    }

    public String getCor() {
        return InputsPainel[2].getText();
    }

    public String getPeso() {
        return InputsPainel[3].getText();
    }

    public String getSexo() {
        return IndicesSelecao[0][SelecaoPet[0].getSelectedIndex()];
    }

    public String getTipoAnimal() {
        return IndicesSelecao[1][SelecaoPet[1].getSelectedIndex()];
    }

    public String getIdade() {
        return IndicesSelecao[2][SelecaoPet[2].getSelectedIndex()];
    }

    public String getPorte() {
        return IndicesSelecao[3][SelecaoPet[3].getSelectedIndex()];
    }

    public String getObservacao() {
        return TextoObservacoes.getText();
    }

    public int getDono() {
        if (SelecionarDono.isSelectionEmpty()) {
            return 0;
        } else {
            return ListaClientes.get(SelecionarDono.getSelectedIndex()).getCod_cli();
        }

    }
}

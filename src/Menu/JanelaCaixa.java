package Menu;

import Controle.PetShop2;
import DAO.VendasDAO;
import VOs.ProdutosVO;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

/**
 * https://www.freepik.com/free-vector/bons-and-foot-prints-pattern-background_1123963.htm#term=pet&page=1&position=0
 *
 *
 */
public class JanelaCaixa extends JPanel implements ActionListener {

    private String auxNota = "";

    private double auxTotal;

    private JFrame Janela;

    Toolkit Tela = Toolkit.getDefaultToolkit();

    private String IndicesCaixa[] = {"  Caixa Livre!", "PRODUTOS: ", "CÓD. DO PRODUTO:", "QUANTIDADE: ", "TOTAL: ", "DINHEIRO: ", "TROCO: "};

    private JLabel LabelsCaixa[];

    private JEditorPane TextoNota;

    private JScrollPane ScrollNota;

    private JFormattedTextField InputsCaixa[];

    private ProdutosVO objProduto;

    private JButton BotaoCaixa[];

    private JLabel labelSair;

    private List<ProdutosVO> produtosCompra;

    public JanelaCaixa() {

        LabelsCaixa = new JLabel[IndicesCaixa.length];
        TextoNota = new JEditorPane();
        InputsCaixa = new JFormattedTextField[5];
        ScrollNota = new JScrollPane();
        BotaoCaixa = new JButton[HtmlBotoesCaixa.length];

        Janela = new JFrame("Caixa");
        Janela.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Janela.setUndecorated(true);
        Janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Janela.setLocationRelativeTo(null);
        Janela.add(this);

        produtosCompra = new ArrayList<ProdutosVO>();

        Toolkit tela = Toolkit.getDefaultToolkit();

        this.setLayout(null);
        this.setSize((int) tela.getScreenSize().getWidth(), (int) tela.getScreenSize().getHeight());
        Janela.addKeyListener(keyCaixa());
        Janela.setFocusable(true);

        construirTela();
        this.setVisible(false);
        Janela.setVisible(true);
        this.setVisible(true);

    }

    private void construirTela() {

        JPanel painelCentro = new JPanel(null);
        painelCentro.setBorder(BorderFactory.createEtchedBorder());
        painelCentro.setBounds((this.getWidth() - 1000) / 2, (this.getHeight() - 700) / 2, 1000, 700);
        this.add(painelCentro);

        JPanel paineisValores[] = new JPanel[5];

        JPanel painelCPF = new JPanel(null) {
            @Override
            public void paintComponent(Graphics g) {
                g.setColor(new Color(6, 103, 154));
                g.fillRect(0, 0, 940, 100);
                g.setColor(new Color(26, 123, 174));
                g.fillRect(10, 10, 650, 80);
                g.fillRect(670, 10, 80, 80);
                g.fillRect(760, 10, 80, 80);
                g.fillRect(850, 10, 80, 80);
            }
        };
        painelCPF.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
        painelCPF.setBounds(30, 580, 940, 100);
        painelCentro.add(painelCPF);

        for (int i = 0; i < IndicesCaixa.length; i++) {
            LabelsCaixa[i] = new JLabel(IndicesCaixa[i]);

            if (i == 0) {
                LabelsCaixa[i].setBounds(10, 10, 650, 80);
                LabelsCaixa[i].setFont(new Font("Arial", 0, 28));
                LabelsCaixa[i].setForeground(Color.white);
                LabelsCaixa[i].setBorder(new SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
                LabelsCaixa[i].setBackground(new Color(26, 123, 174));
                //LabelsCaixa[i].setBackground(Color.yellow);
                painelCPF.add(LabelsCaixa[i]);
            } else if (i == 1) {
                LabelsCaixa[i].setBounds(30, 10, 600, 20);
                LabelsCaixa[i].setFont(new Font("Arial", 1, 16));
                painelCentro.add(LabelsCaixa[i]);
            } else if (i > 1 && i < 7) {
                paineisValores[i - 2] = new JPanel(null) {
                    @Override
                    public void paintComponent(Graphics g) {
                        g.setColor(new Color(6, 103, 154));
                        g.fillRect(0, 0, 310, 115);
                    }
                };
                paineisValores[i - 2].setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
                paineisValores[i - 2].setBounds(660, 40 + ((i - 2) * 105), 310, 100);
                painelCentro.add(paineisValores[i - 2]);
                LabelsCaixa[i].setBounds(5, 5, 310, 25);
                LabelsCaixa[i].setFont(new Font("Tahoma", 1, 20));
                LabelsCaixa[i].setForeground(Color.white);
                InputsCaixa[i - 2] = new JFormattedTextField();
                InputsCaixa[i - 2].setBounds(10, 40, 290, 45);
                InputsCaixa[i - 2].setFont(new Font("Tahoma", 0, 32));
                InputsCaixa[i - 2].addActionListener(this);
                InputsCaixa[i - 2].addKeyListener(keyCaixa());
                if (i == 4 || i == 6) {
                    InputsCaixa[i - 2].setEnabled(false);
                }
                paineisValores[i - 2].add(InputsCaixa[i - 2]);
                paineisValores[i - 2].add(LabelsCaixa[i]);
            }
        }

        ScrollNota.setBounds(30, 40, 600, 520);
        ScrollNota.setViewportView(TextoNota);
        TextoNota.setBackground(new Color(254, 254, 204));
        TextoNota.setContentType("text/html");
        TextoNota.setText(TituloNota + rodaPe);
        TextoNota.setEditable(false);
        painelCentro.add(ScrollNota);

        for (int i = 0; i < HtmlBotoesCaixa.length; i++) {
            BotaoCaixa[i] = new JButton(HtmlBotoesCaixa[i]);
            BotaoCaixa[i].setBounds(670 + (i * 90), 10, 80, 80);
            BotaoCaixa[i].setMargin(new Insets(0, 0, 0, 0));
            BotaoCaixa[i].setBorder(new SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
            BotaoCaixa[i].setBackground(new Color(26, 123, 174));
            BotaoCaixa[i].setFont(new Font("Arial", 1, 14));
            BotaoCaixa[i].setContentAreaFilled(false);
            BotaoCaixa[i].setFocusPainted(false);
            BotaoCaixa[i].addActionListener(this);
            painelCPF.add(BotaoCaixa[i]);
        }

        labelSair = new JLabel("<html>F12:<font color=\"rgb(153,0,0)\"> SAIR</html>");
        labelSair.setFont(new Font("Arial", 1, 16));
        labelSair.setBounds(890, 10, 100, 20);
        labelSair.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                Janela.dispose();
            }
        });
        painelCentro.add(labelSair);

    }

    private KeyAdapter keyCaixa() {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_F3) {
                    new AlterarDados.AlterarProduto();
                } else if (evt.getKeyCode() == KeyEvent.VK_F12) {
                    Janela.dispose();
                } else if (evt.getKeyCode() == KeyEvent.VK_F2) {
                    corrigir();
                } else if (evt.getKeyCode() == KeyEvent.VK_F4) {
                    InputsCaixa[3].requestFocus();
                } else if (evt.getKeyCode() == KeyEvent.VK_F1) {
                    finalizarCompra();
                }
            }
        };
    }

    private void gerarNota(int cod, String nome, float preco, int qtd) {

        auxNota += "<table style=\"width: 570;\">"
                + "<tbody>"
                + "<tr>"
                + "<td style=\"width: 90px;\"> <left>" + qtd + "</left></td>"
                + "<td style=\"width: 390px;\">Cód: " + cod + " - " + nome + "</td>"
                + "<td style=\"width: 90px;\"><left>R$: " + PetShop2.FORMATAR_MOEDA.format(preco).replace(".", ",") + "</left></td>"
                + "</tr>"
                + "</tbody>"
                + "</table>";
        TextoNota.setText(TituloNota + auxNota + rodaPe);
    }

    private void adicionarProduto(int cod, int qtd) {
        objProduto = DAO.ProdutosDAO.produtoCaixa(cod);
        boolean auxAdicionar = true;
        gerenciarNota();
        for (ProdutosVO objCompra : produtosCompra) {
            if (cod == objCompra.getCod_prod()) {
                objCompra.setQtd(objCompra.getQtd() + qtd);
                auxAdicionar = false;
                gerenciarNota();
                break;
            }
        }
        if (auxAdicionar) {
            objProduto.setQtd(qtd);
            produtosCompra.add(objProduto);
            gerenciarNota();
        }

    }

    private void gerenciarNota() {
        auxNota = "";
        auxTotal = 0;
        for (ProdutosVO tmpProduto : produtosCompra) {
            gerarNota(tmpProduto.getCod_prod(), tmpProduto.getNome(), tmpProduto.getPreco(), tmpProduto.getQtd());
            auxTotal += tmpProduto.getPreco() * tmpProduto.getQtd();
            InputsCaixa[2].setText("R$ " + Controle.PetShop2.FORMATAR_MOEDA.format(auxTotal).replace(".", ","));
            LabelsCaixa[0].setText(tmpProduto.getNome());
            InputsCaixa[1].setText("");
            InputsCaixa[0].setText("");
            InputsCaixa[0].requestFocus();

        }

    }

    private void finalizar() {
        TextoNota.setText(TituloNota + rodaPe);
        auxTotal = 0;
        for (int i = 0; i < InputsCaixa.length; i++) {
            InputsCaixa[i].setText("");
        }
        for (int i = produtosCompra.size() - 1; i >= 0; i--) {
            produtosCompra.remove(i);
        }
        LabelsCaixa[0].setText(IndicesCaixa[0]);
    }

    private void corrigir() {
        if (InputsCaixa[0] != null && InputsCaixa[1] != null) {
            for (int i = 0; i < produtosCompra.size(); i++) {
                if (produtosCompra.get(i).getCod_prod() == Integer.parseInt(InputsCaixa[0].getText())) {
                    if (produtosCompra.get(i).getQtd() == Integer.parseInt(InputsCaixa[1].getText())) {
                        produtosCompra.remove(produtosCompra.get(i));
                    } else {
                        produtosCompra.get(i).setQtd(produtosCompra.get(i).getQtd() - Integer.parseInt(InputsCaixa[1].getText()));
                        if (produtosCompra.get(i).getQtd() < 1) {
                            produtosCompra.remove(produtosCompra.get(i));
                        }
                    }
                    if (produtosCompra.isEmpty()) {
                        finalizar();
                    }
                    gerenciarNota();
                }
            }
        }
    }

    private void finalizarCompra() {
        if (!produtosCompra.isEmpty()) {
            int auxCod = VendasDAO.pegarVenda();
            for (int i = 0; i < produtosCompra.size(); i++) {
                VendasDAO.inserirVenda(auxCod, produtosCompra.get(i).getCod_prod(), produtosCompra.get(i).getQtd());
                VendasDAO.atualizarQuantidades(produtosCompra.get(i).getCod_prod(), produtosCompra.get(i).getQtd());
            }
            VendasDAO.inserirTotal(auxCod, auxTotal);
            String auxRodape;
            if (!InputsCaixa[2].getText().equals("") && !IndicesCaixa[4].equals("")) {
                auxRodape = "<br><center><b>TOTAL:</b> " + InputsCaixa[2].getText() + " <b>TROCO:</b> " + InputsCaixa[4].getText();
            } else {
                auxRodape = "";
            }
            TextoNota.setText(TituloNota + auxNota + rodaPe + auxRodape);
            PrintWriter notinha = null;
            try {
                notinha = new PrintWriter(new File("notas//nota" + auxCod + ".html"));
                notinha.println(TextoNota.getText());
                notinha.close();
            } catch (Exception e) {
            }

            finalizar();
            JOptionPane.showMessageDialog(null, "VENDA CONCLUÍDA!");
        } else {
            JOptionPane.showMessageDialog(null, "COMPRA NÃO ABERTA");
        }
    }

    private void desenharDogs(Graphics g) {
        String imagem = "bgcerto.png";

        int qtdW = (int) (Tela.getScreenSize().getWidth() / PetShop2.buscarImagem(imagem).getWidth()) + 1;
        int qtdH = (int) (Tela.getScreenSize().getHeight() / PetShop2.buscarImagem(imagem).getHeight()) + 1;

        for (int i = 0; i < qtdH; i++) {
            for (int j = 0; j < qtdW; j++) {
                g.drawImage(PetShop2.buscarImagem(imagem), j * PetShop2.buscarImagem(imagem).getWidth(), i * PetShop2.buscarImagem(imagem).getHeight(), null);
            }
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(PetShop2.buscarImagem("bgpet.jpg"), 0, 0, null);

    }

    @Override
    public void actionPerformed(ActionEvent evt
    ) {
        if (evt.getSource() == InputsCaixa[0]) {
            if (InputsCaixa[0].getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Produto não informado");
            } else {
                InputsCaixa[1].requestFocus();
            }
        } else if (evt.getSource() == InputsCaixa[1]) {
            if (InputsCaixa[0].getText().matches("^\\d+$") && InputsCaixa[1].getText().matches("^\\d+$")) {
                adicionarProduto(Integer.parseInt(InputsCaixa[0].getText()), Integer.parseInt(InputsCaixa[1].getText()));
            }
        } else if (evt.getSource() == InputsCaixa[3]) {
            double auxTroco = Double.parseDouble(InputsCaixa[3].getText()) - auxTotal;
            InputsCaixa[4].setText("R$ " + PetShop2.FORMATAR_MOEDA.format(auxTroco).replace(".", ","));
        } else if (evt.getSource() == BotaoCaixa[0]) {
            finalizarCompra();
        } else if (evt.getSource() == BotaoCaixa[1]) {
            corrigir();
        } else if (evt.getSource() == BotaoCaixa[2]) {
            new AlterarDados.AlterarProduto();
        }

    }

    private String HtmlBotoesCaixa[] = {"<html><center><font color=\"rgb(0,204,102)\">F1<br>(FINAL.)</html>", "<html><center><font color=\"rgb(255,127,0)\">F2<br>(CORRIGE)</html>", "<html><center><font color=\"rgb(255,255,255)\">F3<br>(VISU.)</html>"};

    private String linhaNota = "---------------------------------------------------------------------------------------------------------------------";

    private String TituloNota = "<html><h1><center>VET. & PETSHOP SHOWPET LTDA</h1><center>RUA ENG PRESTES MAIA, 88 - CENTRO - GUARULHOS<br>CNPJ: 49.040.904/0001-30<br>" + linhaNota + "<br><table style=\"width: 570;\">"
            + "<tbody>"
            + "<tr>"
            + "<td style=\"width: 90PX;\"> <center>QTD </center></td>"
            + "<td style=\"width: 390px;\"><center> PRODUTO </center></td>"
            + "<td style=\"width: 90px;\"> <center> PREÇO </center></td>"
            + "</tr>"
            + "</tbody>"
            + "</table>";

    private String dadosRodaPe = "<br><b>SHOWPET:</b> PETSHOP E CLÍNICA VETERINÁRIA<br>"
            + "<center><b>DATA DA COMPRA:</b> " + PetShop2.DataHoje + " <b>HORA DA COMPRA: </b>" + PetShop2.HoraAtual + "</center>";

    private String rodaPe = linhaNota + dadosRodaPe;
}

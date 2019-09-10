package Controle;

import Menu.MenuAdmin;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.imageio.ImageIO;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class PetShop2 {

    public static final String[] PETS_TIPO = {"Cães", "Gatos", "Pássaros", "Roedores", "Peixes", "Outros"};

    public static final String[] PETS_IDADE = {"Filhotes", "Adultos", "Idosos"};

    public static final String[] PETS_TAMANHO = {"Porte pequeno", "Porte médio", "Porte grande"};

    public static final String[] PRODUTOS_INDICES = {"Nome do produto:", "Descrição:", "Quantidade:", "Preço:", "Categoria:", "Fornecedor:", "Tipo de pet:",
        "Idade do pet:", "Tamanho do pet:"};

    private static final String stringsFormato[] = {"##/##/####", "##:##", "###.###.###-##", "(##)####-####", "(##)# ####-####", "#####-###", "##.###.###/####-##", "###.#####.##-#"};

    public static MaskFormatter FORMATO_DATA;
    public static final String VERIFICAR_DATA = "^(0[1-9]|[12][0-9]|3[01])\\/(0[1-9]|1[012])\\/[12][0-9]{3}$";

    public static MaskFormatter FORMATO_HORA;
    public static final String VERIFICAR_HORA = "^\\d{2}\\:\\d{2}$";

    public static MaskFormatter FORMATO_CPF;
    public static final String VERIFICAR_CPF = "^(\\d{3}\\.){2}\\d{3}-\\d{2}$";

    public static MaskFormatter FORMATO_TEL;
    public static final String VERIFICAR_TEL = "^$";

    public static MaskFormatter FORMATO_CEL;
    public static final String VERIFICAR_CEL = "^$";

    public static MaskFormatter FORMATO_CEP;
    public static final String VERIFICAR_CEP = "^$";

    public static MaskFormatter FORMATO_CNPJ;
    public static final String VERIFICAR_CNPJ = "^$";
    
    public static MaskFormatter FORMATO_PIS;
    public static final String VERIFICAR_PIS = "^$";

    public static final DecimalFormat FORMATAR_MOEDA = new DecimalFormat("0.00");

    public static final SimpleDateFormat DATA_BRASIL_SQL = new SimpleDateFormat("dd/MM/yyyy");

    public static final DateTimeFormatter DATA_BRASIL = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static String DataHoje = DATA_BRASIL.format(LocalDate.now());

    public static final DateTimeFormatter FORMATAR_HORARIO = DateTimeFormatter.ofPattern("HH:mm");

    public static final SimpleDateFormat FORMATAR_HORARIO_SQL = new SimpleDateFormat("HH:mm");

    public static final SimpleDateFormat FORMATAR_DATA_HORARIO_SQL = new SimpleDateFormat("dd/MM/yy HH:mm");

    public static String HoraAtual = FORMATAR_HORARIO.format(LocalTime.now());

    public static BufferedImage buscarImagem(String localImagem) {
        BufferedImage imgReturn = null;
        try {
            imgReturn = ImageIO.read(new File(localImagem));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao ler imagem:\n" + e);
        }
        return imgReturn;
    }

    public static Date returnData(String data) {

        DateTimeFormatter auxFormato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate auxData = LocalDate.parse(data, auxFormato);

        return Date.valueOf(auxData);
    }

    public PetShop2() {
        try {
            FORMATO_DATA = new MaskFormatter(stringsFormato[0]);
            FORMATO_HORA = new MaskFormatter(stringsFormato[1]);
            FORMATO_CPF = new MaskFormatter(stringsFormato[2]);
            FORMATO_TEL = new MaskFormatter(stringsFormato[3]);
            FORMATO_CEL = new MaskFormatter(stringsFormato[4]);
            FORMATO_CEP = new MaskFormatter(stringsFormato[5]);
            FORMATO_CNPJ = new MaskFormatter(stringsFormato[6]);
            FORMATO_PIS = new MaskFormatter(stringsFormato[7]);

        } catch (ParseException e) {
            System.out.println(e);
        }

        new MenuAdmin();
    }

    public static void setFormato(JFormattedTextField input, MaskFormatter formato) {
        input.setFormatterFactory(new DefaultFormatterFactory(formato));
        input.setText(null);
    }

    public static void main(String[] args) {

        try {
            for (UIManager.LookAndFeelInfo installedLookAndFeel : UIManager.getInstalledLookAndFeels()) {
                if (installedLookAndFeel.getName().equals("Windows")) {
                    UIManager.setLookAndFeel(installedLookAndFeel.getClassName());
                    UIManager.getLookAndFeelDefaults().put("Table.alternateRowColor", new Color(242, 242, 242));
                    //UIManager.getLookAndFeelDefaults().put("Table.alternateRowColor", new Color(202, 202, 202));
                    //UIManager.getLookAndFeelDefaults().put("Table.font", new Font("Segoe UI", 0, 14));
                    UIManager.getLookAndFeelDefaults().put("Table.font", new Font("Arial", 0, 14));
                    UIManager.getLookAndFeelDefaults().put("Table.selectionBackground", new Color(57, 105, 138));
                    UIManager.getLookAndFeelDefaults().put("ComboBox.selectionBackground", new Color(57, 105, 138));
                    //UIManager.getLookAndFeelDefaults().put("ComboBox.font", new Font("Segoe UI", 0, 12));
                    //UIManager.getLookAndFeelDefaults().put("List.font", new Font("Segoe UI", 0, 12));
                    UIManager.getLookAndFeelDefaults().put("List.selectionBackground", new Color(57, 105, 138));
                    //UIManager.getLookAndFeelDefaults().put("Button.font", new Font("Segoe UI", 0, 12));
                    //"Segoe UI"
                } else if (installedLookAndFeel.getName().equals("GTK+")) {
                    UIManager.setLookAndFeel(installedLookAndFeel.getClassName());
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        new PetShop2();

        Timestamp estampa = Timestamp.valueOf("2018-12-12 12:12:12");

        System.out.println(new SimpleDateFormat("MM/dd/yy HH:mm").format(estampa));
        
        System.out.println("");

    }

}

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;

public class EditorDeTexto extends JFrame implements ActionListener, ItemListener {
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JComboBox<String> comboBox1;
    private JComboBox<Integer> comboBox2;
    private JTextPane textPane1;
    private String[] fuentes;
    private JPanel panelColor;
    private JButton button4;
    private Color color;

    public EditorDeTexto() {
        setTitle("Editor de texto");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        button1 = new JButton("Negrita");
        button1.setBounds(10, 10, 90, 30);
        add(button1);
        button1.addActionListener(this);

        button2 = new JButton("Cursiva");
        button2.setBounds(200, 10, 90, 30);
        add(button2);
        button2.addActionListener(this);

        button3 = new JButton("Subrayado");
        button3.setBounds(400, 10, 110, 30);
        add(button3);
        button3.addActionListener(this);

        textPane1 = new JTextPane();
        textPane1.setBounds(10,60,1200,700);
        add(textPane1);

        comboBox1= new JComboBox<>();
        comboBox1.setBounds(600,10,120,30);
        add(comboBox1);
        comboBox1.addItemListener(this);
        fuentes = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

        comboBox2= new JComboBox<>();
        comboBox2.setBounds(800,10,80,30);
        add(comboBox2);
        comboBox2.addItemListener(this);

        button4= new JButton("Color");
        button4.setBounds(1000,10,100,30);
        add(button4);
        button4.addActionListener(this);

        panelColor = new JPanel();
        panelColor.setBounds(10, 80, 510, 350);
        panelColor.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        add(panelColor);

        cargarElementos();
    }

    public void actionPerformed(ActionEvent e) {
        // Obtener la selección actual
        int start = textPane1.getSelectionStart();
        int end = textPane1.getSelectionEnd();

        // Obtener el documento de estilo del JTextPane
        StyledDocument doc = textPane1.getStyledDocument();

        if (e.getSource()==button1) {
            // Crear un nuevo estilo
            Style style = doc.addStyle("Negrita", null);

            // Verificar si el texto seleccionado está en negrita
            AttributeSet atributos = doc.getCharacterElement(start).getAttributes();
            boolean esNegrita = StyleConstants.isBold(atributos);

            // Alternar el estilo de negrita
            StyleConstants.setBold(style, !esNegrita);

            // Aplicar el estilo al texto seleccionado
            doc.setCharacterAttributes(start, end - start, style, false);

        } else if (e.getSource()==button2) {
            // Crear un nuevo estilo
            Style style = doc.addStyle("Cursiva", null);

            // Verificar si el texto seleccionado está en cursiva
            AttributeSet atributos = doc.getCharacterElement(start).getAttributes();
            boolean esCursiva = StyleConstants.isItalic(atributos);

            // Alternar el estilo de cursiva
            StyleConstants.setItalic(style, !esCursiva);

            // Aplicar el estilo al texto seleccionado
            doc.setCharacterAttributes(start, end - start, style, false);

        } else if (e.getSource()==button3){
            // Crear un nuevo estilo
            Style style = doc.addStyle("Subrayado", null);

            // Verificar si el texto seleccionado está subrayado
            AttributeSet atributos = doc.getCharacterElement(start).getAttributes();
            boolean esSubrayado = StyleConstants.isUnderline(atributos);

            // Alternar el estilo de subrayado
            StyleConstants.setUnderline(style, !esSubrayado);

            // Aplicar el estilo al texto seleccionado
            doc.setCharacterAttributes(start, end - start, style, false);
        } else if (e.getSource()==button4) {
            Style style = doc.addStyle("Color", null);

            JColorChooser ventanaDeColores = new JColorChooser();
            color = ventanaDeColores.showDialog(null, "Seleccione un Color", Color.gray);

            StyleConstants.setForeground(style, color);

            doc.setCharacterAttributes(start, end - start, style, false);
        }
    }

    public void cargarElementos() {
        for (String fuente : fuentes) {
            comboBox1.addItem(fuente);
        }
        for (int i = 10; i <=30 ; i++) {
            comboBox2.addItem(i);
        }
    }

    public void itemStateChanged(ItemEvent e) {
        int start = textPane1.getSelectionStart();
        int end = textPane1.getSelectionEnd();

        StyledDocument doc = textPane1.getStyledDocument();

        if (e.getSource() == comboBox1) {

            Style style = doc.addStyle("Fuentes", null);

            String fuenteSeleccionada = (String) comboBox1.getSelectedItem();

            StyleConstants.setFontFamily(style, fuenteSeleccionada);

            doc.setCharacterAttributes(start, end - start, style, false);

        } else if (e.getSource() == comboBox2) {
            Style style = doc.addStyle("Tamaño", null);

            int tamañoSeleccionado = (int) comboBox2.getSelectedItem();

            StyleConstants.setFontSize(style, tamañoSeleccionado);

            doc.setCharacterAttributes(start, end - start, style, false);
        }

    }

    //MAIN
    public static void main(String[] args) {
        EditorDeTexto editor = new EditorDeTexto();
        editor.setExtendedState(JFrame.MAXIMIZED_BOTH);
        editor.setVisible(true);
        editor.setResizable(false);
    }
}

package game.db.form;

import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import game.db.dao.ImagemDao;
import game.db.entity.Imagem;
import game.db.util.ImageFileView;
import game.db.util.ImagePreview;
import game.db.util.LanguageUtil;
import game.db.util.SpringUtilities;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * =========================================== <br>
 * PROJETO PARALLAX - Engine de Jogos 2D e RPG <br>
 * Data de alteração do arquivo: 30/10/2012 <br>
 * =========================================== <P>
 * 
 * A classe <b>ImagemTableModel</b> (Modelo da Tabela de Imagem) guarda as Imagem do Jogo, 
 * são elas: face (Rosto do personagem), battle (imagem exibida na batalha), status effect (imagem exibida apos o personagem ser atingido por um skill), skill (Habilidade do personagem de atingir um oponente ou manipular um elemento), moviment (Movimento, efeito visual exibido durante um deslocamento ).<p>
 * 
 * 
 * @see	<a href="http://www.einformacao.com.br/parallax/component/content/article/35-conteudo-site/86-documentos-para-leitura">Documentos para Leitura</a>
 * @author <a href="mailto:jeferson.januario@gmail.com">Jeferson Januario</a>
 * @version 1.0.1
 * @since Existe desde a versão 1.0.1 do projeto
 */
public class ImagemTableModel implements ActionListener, ListSelectionListener {

    final private ImagemDao dao = new ImagemDao();
    private JTable table;
    private File image;
    private JButton buttonNew;
    private JButton buttonRefresh;
    private JButton buttonSave;
    private JButton buttonDelete;
    private JButton buttonFileSelect;
    private JTextField textFieldName;
    private JTextField textFieldImagemName;
    private JComboBox<String> comboBoxTypeImage;
    private ListSelectionModel ListSelectionModel;
    private JSpinner spinnerSpriteWidth;
    private JSpinner spinnerSpriteHeight;
    private JSpinner spinnerSpriteStartPosX;
    private JSpinner spinnerSpriteStartPosY;
    private JLabel labelImage;
    private JLabel labelPreviewImageIcon;
    private ObjectTableModel<Imagem> tableModel;
    private Imagem imagem;

    /**
     * @return PanelBase Contem todos os componetes da GUI
     * O metodo show resolve as dependencias da classe e retorna a GUI com todos os componetes.
     */
    public JPanel show() {
        AnnotationResolver resolver = new AnnotationResolver(Imagem.class);
        tableModel = new ObjectTableModel<>(resolver, "id,name,fileName,typeImage,spriteWidth,spriteHeight,spriteStartPosX,"
                + "spriteStartPosY");
        setData();
        table = new JTable(tableModel);
        ListSelectionModel = table.getSelectionModel();
        ListSelectionModel.addListSelectionListener(this);
        return getPanelBase();
    }

    private JPanel getPanelPreviewImage() {
        JPanel panelPreviewImage = new JPanel();
        panelPreviewImage.setPreferredSize(new Dimension(230, 180));
        panelPreviewImage.add(labelPreviewImageIcon = new JLabel(), BorderLayout.CENTER);
        return panelPreviewImage;
    }

    private JPanel getPanelFormText() {
        JPanel formPanelText = new JPanel();
        formPanelText.setLayout(new SpringLayout());
        formPanelText.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.name")), BorderLayout.LINE_START);
        formPanelText.add(textFieldName = new JTextField(), BorderLayout.LINE_START);
        formPanelText.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.image.name")), BorderLayout.LINE_START);
        formPanelText.add(textFieldImagemName = new JTextField(), BorderLayout.LINE_START);
        textFieldImagemName.setEnabled(false);
        formPanelText.add(labelImage = new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.image.file")), BorderLayout.LINE_START);
        formPanelText.add(buttonFileSelect = new JButton(new ImageIcon("resources/images/select.jpeg")), BorderLayout.LINE_START);
        buttonFileSelect.setPreferredSize(new Dimension(50, 25));
        buttonFileSelect.addActionListener(this);
        formPanelText.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.image.type")), BorderLayout.LINE_START);
        formPanelText.add(comboBoxTypeImage = new JComboBox<String>(), BorderLayout.LINE_START);
        String[] combo = "face, battle, status effect, skill, moviment".trim().split(",");
        for (int i = 0; combo.length > i; i++) {
            comboBoxTypeImage.addItem(combo[i].trim());

        }
        SpringUtilities.makeCompactGrid(formPanelText,
                formPanelText.getComponentCount() / 2, 2, //rows, cols
                6, 6, //initX, initY
                6, 6);       //xPad, yPad
        return formPanelText;

    }

    private JPanel getPanelForm() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BorderLayout());
        formPanel.add(getPanelFormText(), BorderLayout.LINE_START);
        formPanel.add(getPanelFormSpinner(), BorderLayout.LINE_END);
        formPanel.add(getPanelPreviewImage(), BorderLayout.CENTER);
        setFieldEnabled(false);
        return formPanel;

    }

    private JPanel getPanelBase() {
        JPanel basePanel = new JPanel();
        JScrollPane scrollPaneTable = new JScrollPane(table);
        basePanel.setLayout(new BorderLayout());
        basePanel.add(scrollPaneTable, BorderLayout.PAGE_START);
        basePanel.add(getPanelForm(), BorderLayout.CENTER);
        basePanel.add(getPanelButton(), BorderLayout.PAGE_END);
        scrollPaneTable.setViewportView(table);
        scrollPaneTable.setPreferredSize(new Dimension(500, 250));
        scrollPaneTable.setViewportView(table);
        return basePanel;
    }

    private File imageOfProject(String nameImageToCopy) {
    	
String  typeImage = imagem.getTypeImage().trim().toLowerCase();
    	
    	switch (typeImage) {
    	case "face":
    	typeImage = "faces/";
    	break;
    	case "battle":
    	typeImage = "battlers/";
    	break;
    	case "status effect":
    		typeImage = "images/";
    	break;
    	case "skill":
    		typeImage = "sprites/";
    	break;
    	case "moviment":
    		typeImage = "characters/";
    	break;
    	default:
    		
    	typeImage =("resources/".concat(typeImage.trim()).concat(nameImageToCopy));
    	}
    	return new File("resources/".concat(typeImage).concat(nameImageToCopy));
        
    }

    private String getNameImageToCopy() {
        return image.getAbsolutePath().split("/")[(image.getAbsolutePath().split("/").length - 1)];
    }

    private void copyImageToFolderImagens(String file) {

        try {
            Files.copy(Paths.get(image.getAbsolutePath()), Paths.get(imageOfProject(file).getAbsolutePath()));
        } catch (IOException ex) {
            throw new RuntimeException(ex);

        }
    }

    private void saveOrUpDate() {
        boolean isUpdate = table.getSelectedRow() != -1;

        if (isUpdate) {
            imagem = getImagemSelected();
        } else {
            imagem = new Imagem();
        }
        imagem.setName(textFieldName.getText());
        imagem.setFileName(textFieldImagemName.getText());
        imagem.setTypeImage(comboBoxTypeImage.getSelectedItem().toString());
        imagem.setSpriteHeight((Integer) spinnerSpriteHeight.getValue());
        imagem.setSpriteStartPosX((Integer) spinnerSpriteStartPosX.getValue());
        imagem.setSpriteStartPosY((Integer) spinnerSpriteStartPosY.getValue());
        imagem.setSpriteWidth((Integer) spinnerSpriteWidth.getValue());
        dao.saveOrUpDate(imagem);


    }

    private void selectImage() throws HeadlessException, RuntimeException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(new FileNameExtensionFilter("JPEG PNG", "jpg", "jpeg", "png"));
        fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
        fileChooser.setDialogTitle(LanguageUtil.getLanguageFactory().getMessage("file.chooser.dialog.title"));
        fileChooser.setFileView(new ImageFileView());
        fileChooser.setAccessory(new ImagePreview(fileChooser));
        int aprovado = fileChooser.showDialog(new JPanel(), null);
        if (aprovado == 0 && fileChooser.getSelectedFile().exists()) {
            String path = fileChooser.getSelectedFile().getAbsolutePath();
            try {
                setIconToLabelPreviewImageIcon(path);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            if (aprovado == 0) {
                selectImage();
            }
        }
    }

    public void setFieldEnabled(boolean b) {

        textFieldName.setEnabled(b);
        comboBoxTypeImage.setEnabled(b);
        spinnerSpriteHeight.setEnabled(b);
        spinnerSpriteStartPosX.setEnabled(b);
        spinnerSpriteStartPosY.setEnabled(b);
        spinnerSpriteWidth.setEnabled(b);
        buttonFileSelect.setEnabled(b);

    }

    private JPanel getPanelButton() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(buttonNew = new JButton(LanguageUtil.getLanguageFactory().getMessage("button.new")));
        buttonPanel.add(buttonRefresh = new JButton(LanguageUtil.getLanguageFactory().getMessage("button.refresh")));
        buttonPanel.add(buttonSave = new JButton(LanguageUtil.getLanguageFactory().getMessage("button.save")));
        buttonPanel.add(buttonDelete = new JButton(LanguageUtil.getLanguageFactory().getMessage("button.delete")));
        
        buttonNew.addActionListener(this);
        buttonRefresh.addActionListener(this);
        buttonSave.addActionListener(this);
        buttonDelete.addActionListener(this);
        setButtonEnabled(false);
        return buttonPanel;
    }

    private JPanel getPanelFormSpinner() {
        JPanel formPanelSpinner = new JPanel();
        formPanelSpinner.setLayout(new SpringLayout());
        formPanelSpinner.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.sprite.width")), BorderLayout.PAGE_END);
        formPanelSpinner.add(spinnerSpriteWidth = new JSpinner(), BorderLayout.PAGE_END);
        spinnerSpriteWidth.setPreferredSize(new Dimension(60, 20));
        formPanelSpinner.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.sprite.height")), BorderLayout.PAGE_END);
        formPanelSpinner.add(spinnerSpriteHeight = new JSpinner(), BorderLayout.PAGE_END);
        formPanelSpinner.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.sprite.start.pos.x")), BorderLayout.PAGE_END);
        formPanelSpinner.add(spinnerSpriteStartPosX = new JSpinner(), BorderLayout.PAGE_END);
        formPanelSpinner.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.sprite.start.pos.y")), BorderLayout.PAGE_END);
        formPanelSpinner.add(spinnerSpriteStartPosY = new JSpinner(), BorderLayout.PAGE_END);

        SpringUtilities.makeCompactGrid(formPanelSpinner,
                formPanelSpinner.getComponentCount() / 2, 2, //rows, cols
                0, 0, //initX, initY
                0, 0);       //xPad, yPad
        return formPanelSpinner;

    }

    private ImageIcon selectImageFromProject() {
        try {
            String[] lista = imageOfProject("").list();
            if (lista!=null){
	            for (int i = 0; i < lista.length; i++) {
	                if (textFieldImagemName.getText().length() > 0
	                        && lista[i].indexOf(textFieldImagemName.getText() + ".") != -1) {
	                    image = new File(imageOfProject(lista[i]).getAbsolutePath());
	                    BufferedImage myImage = ImageIO.read(image);
	                    ImageIcon myIcon = new ImageIcon(myImage.getSubimage((Integer) spinnerSpriteStartPosX.getValue(),
	                            (Integer) spinnerSpriteStartPosY.getValue(),
	                            (Integer) spinnerSpriteWidth.getValue(),
	                            (Integer) spinnerSpriteHeight.getValue()));
	                    return myIcon;
	                }
	            }
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);

        }
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonNew) {
            newRegister();
        }
        if (e.getSource() == buttonRefresh) {
            refresh();
        }
        if (e.getSource() == buttonSave) {
            saveOrUpdate();

        }
        if (e.getSource() == buttonDelete) {
            deleteListImagem();

        }
        if (e.getSource() == buttonFileSelect) {
            selectImage();
        }

    }

    private void deleteListImagem() {
        int selecionados[] = table.getSelectedRows();
        if (selecionados.length > 0) {
            List<Imagem> ao = new ArrayList<>();

            for (int i = 0; i < selecionados.length; i++) {
                selecionados[i] = table.convertRowIndexToModel(selecionados[i]);
                ao.add(tableModel.getValue(selecionados[i]));
            }
            for (Imagem rao : ao) {
                dao.remove(rao);
                tableModel.remove(rao);
            }
            refresh();
            ao = null;
            System.gc();
        }
    }

    private void saveOrUpdate() {
        if (table.getSelectedRow() == -1) {
            saveOrUpDate();
            refresh();
            if (image != null && !imageOfProject(getNameImageToCopy()).exists()) {

                copyImageToFolderImagens(getNameImageToCopy());
            }
        } else {
            saveOrUpDate();
            refresh();
            if (image != null && !imageOfProject(getNameImageToCopy()).exists()) {

                copyImageToFolderImagens(getNameImageToCopy());
            }
        }
    }

    private void setIconToLabelPreviewImageIcon(String path) throws IOException {
        image = new File(path);
        BufferedImage imagem = ImageIO.read(image);
        int tamanho = path.trim().split("/").length;
        String nomeImagem = path.trim().split("/")[tamanho - 1];

        textFieldImagemName.setText(nomeImagem.split("\\.")[0]);

        ImageIcon imageIcon = new ImageIcon(imagem.getSubimage((Integer) spinnerSpriteStartPosX.getValue(),
                (Integer) spinnerSpriteStartPosY.getValue(),
                (Integer) spinnerSpriteWidth.getValue(),
                (Integer) spinnerSpriteHeight.getValue()));

        labelPreviewImageIcon.setIcon(imageIcon);
        imageIcon = null;
        System.gc();

    }

    private void setFieldsToNewValues() {
        textFieldName.setText("");
        textFieldImagemName.setText("");
        spinnerSpriteWidth.setValue(0);
        spinnerSpriteHeight.setValue(0);
        spinnerSpriteStartPosX.setValue(0);
        spinnerSpriteStartPosY.setValue(0);
        labelImage.setText("");
        labelPreviewImageIcon.setIcon(null);
        comboBoxTypeImage.setSelectedIndex(0);
    }

    synchronized private void setData() {
        tableModel.clear();
        tableModel.setData(dao.lista());
    }

    private void refresh() {
        setData();
        setFieldEnabled(false);
        setFieldsToNewValues();
        setButtonEnabled(false);
    }

    private Imagem getImagemSelected() {

        return tableModel.getValue(table.getSelectedRow());
    }

    private void setSelectedValuesToFields() {
        imagem = getImagemSelected();
        textFieldName.setText(imagem.getName());
        textFieldImagemName.setText(imagem.getFilename());
        spinnerSpriteWidth.setValue(imagem.getSpriteWidth());
        spinnerSpriteHeight.setValue(imagem.getSpriteHeight());
        spinnerSpriteStartPosX.setValue(imagem.getSpriteStartPosX());
        spinnerSpriteStartPosY.setValue(imagem.getSpriteStartPosY());
        labelPreviewImageIcon.setIcon(selectImageFromProject());
        String selecao = table.getModel().getValueAt(table.getSelectedRow(), 2).toString();

        if (selecao.equalsIgnoreCase(comboBoxTypeImage.getItemAt(0))) {
            comboBoxTypeImage.setSelectedIndex(0);
        }
        if (selecao.equalsIgnoreCase(comboBoxTypeImage.getItemAt(1))) {
            comboBoxTypeImage.setSelectedIndex(1);
        }
        if (selecao.equalsIgnoreCase(comboBoxTypeImage.getItemAt(2))) {
            comboBoxTypeImage.setSelectedIndex(2);
        }
        if (selecao.equalsIgnoreCase(comboBoxTypeImage.getItemAt(3))) {
            comboBoxTypeImage.setSelectedIndex(3);
        }
        if (selecao.equalsIgnoreCase(comboBoxTypeImage.getItemAt(4))) {
            comboBoxTypeImage.setSelectedIndex(4);
        }
    }

    private void newRegister() {
        buttonNew.setEnabled(false);
        buttonDelete.setEnabled(false);
        buttonSave.setEnabled(true);
        setFieldEnabled(true);
        textFieldName.requestFocus();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        boolean isAdjusting = table.getSelectedRow() != -1;

        if (isAdjusting) {
            setSelectedValuesToFields();
            setFieldEnabled(isAdjusting);
            setButtonEnabled(isAdjusting);
            textFieldName.requestFocus();

        }
    }

    public ImageIcon getIconImagem(int x, int y, int w, int h) {
        try {
            return new ImageIcon(ImageIO.read(image).getSubimage(x, y, w, h));
        } catch (IOException ioe) {
            new RuntimeException(ioe);
            return null;
        }
    }

    private void setButtonEnabled(boolean b) {
        buttonDelete.setEnabled(b);
        buttonSave.setEnabled(b);
        buttonNew.setEnabled(!b);
        buttonRefresh.setEnabled(b);

    }
}

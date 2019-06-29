package game.db.form;

import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import game.db.dao.AnimationObjectDao;
import game.db.entity.AnimationObject;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * =========================================== <br>
 * PROJETO PARALLAX - Engine de Jogos 2D e RPG <br>
 * Data de alteração do arquivo: 30/10/2012 <br>
 * ===========================================
 * <P>
 * 
 * A classe <b>AnimationObjectTableModel</b> (Tabela dos Objetos Animados)
 * manipula as animações dos objetos.
 * <p>
 * 
 * @see <a
 *      href="http://www.einformacao.com.br/parallax/component/content/article/35-conteudo-site/86-documentos-para-leitura">Documentos
 *      para Leitura</a>
 * @author <a href="mailto:jeferson.januario@gmail.com">Jeferson Januario</a>
 * @version 1.0.1
 * @since Existe desde a versão 1.0.1 do projeto
 */
public class AnimationObjectTableModel implements ActionListener, ListSelectionListener {

    public AnimationObjectTableModel() {
        super();
    }
    final private AnimationObjectDao dao = new AnimationObjectDao();
    private JTable table;
    private File image;
    private JButton buttonNew;
    private JButton buttonRefresh;
    private JButton buttonSave;
    private JButton buttonDelete;
    private JButton buttonPlay;
    private JButton buttonFileSelect;
    private JTextField textFieldName;
    private JTextField textFieldImagemName;
    private JComboBox<String> comboBoxTypeImage;
    private ListSelectionModel ListSelectionModel;
    private JSpinner spinnerSpriteWidth;
    private JSpinner spinnerSpriteHeight;
    private JSpinner spinnerSpriteStartPosX;
    private JSpinner spinnerSpriteStartPosY;
    private JSpinner spinnerAnimationTotalColumn;
    private JSpinner spinnerAnimationTotalRow;
    private JSpinner spinnerDurationAnimationMs;
    private JLabel labelImage;
    private JLabel labelPreviewImageIcon;
    private AnimationObject animationObject;
    private ObjectTableModel<AnimationObject> tableModel;
    private Thread animation;
    /**
     * @return PanelBase Contem todos os componetes da GUI
     * O metodo show resolve as dependencias da classe e retorna a GUI com todos os componetes.
     */
    public JPanel show() {
        AnnotationResolver resolver = new AnnotationResolver(AnimationObject.class);
        tableModel = new ObjectTableModel<>(resolver, "id,name,fileName,typeImage,spriteWidth,spriteHeight,spriteStartPosX,"
                + "spriteStartPosY,animationTotalColumn,animationTotalRow,durationAnimationMS");
        setData();
        table = new JTable(tableModel);
        ListSelectionModel = table.getSelectionModel();
        ListSelectionModel.addListSelectionListener(this);
        

        return getPanelBase();

    }
private JPanel getPanelPreviewImage(){
    JPanel panelPreviewImage = new JPanel();
    panelPreviewImage.setPreferredSize(new Dimension(230, 180));
    panelPreviewImage.add(labelPreviewImageIcon = new JLabel(), BorderLayout.CENTER);
    return panelPreviewImage;
    
}
    private JPanel getFormPanelText() {
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
        formPanel.add(getFormPanelText(), BorderLayout.LINE_START);
        formPanel.add(getPanelSpinner(), BorderLayout.LINE_END);
        formPanel.add(getPanelPreviewImage(), BorderLayout.CENTER);
        return formPanel;

    }

    private JPanel getPanelBase() {
        JScrollPane scrollPaneTable = new JScrollPane(table);
        JPanel basePanel = new JPanel();
        basePanel.setLayout(new BorderLayout());
        basePanel.add(scrollPaneTable, BorderLayout.PAGE_START);
        basePanel.add(getPanelForm(), BorderLayout.CENTER);
        basePanel.add(getPanelButton(), BorderLayout.PAGE_END);
        scrollPaneTable.setViewportView(table);
        scrollPaneTable.setPreferredSize(new Dimension(500, 250));
        scrollPaneTable.setViewportView(table);
               setFieldEnabled(false);
        return basePanel;
    }

    private File imageOfProject(String nameImageToCopy) {
    	String  typeImage = animationObject.getTypeImage().trim().toLowerCase();
    	
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
    		typeImage = "images/";
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

    private void salveOrUpDate() throws NumberFormatException {
        boolean isUpdate = table.getSelectedRow() != -1;

        if (isUpdate) {
            animationObject = getSelectedAnimationObject();
        } else {
            animationObject = new AnimationObject();
        }

        animationObject.setName(textFieldName.getText());
        animationObject.setFileName(textFieldImagemName.getText());
        animationObject.setTypeImage(comboBoxTypeImage.getSelectedItem().toString());
        animationObject.setAnimationTotalColumn((Integer) spinnerAnimationTotalColumn.getValue());
        animationObject.setAnimationTotalRow((Integer) spinnerAnimationTotalRow.getValue());
        animationObject.setDurationAnimationMS((Integer) spinnerDurationAnimationMs.getValue());
        animationObject.setSpriteHeight((Integer) spinnerSpriteHeight.getValue());
        animationObject.setSpriteStartPosX((Integer) spinnerSpriteStartPosX.getValue());
        animationObject.setSpriteStartPosY((Integer) spinnerSpriteStartPosY.getValue());
        animationObject.setSpriteWidth((Integer) spinnerSpriteWidth.getValue());

        dao.saveOrUpDate(animationObject);
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
        spinnerAnimationTotalColumn.setEnabled(b);
        spinnerAnimationTotalRow.setEnabled(b);
        spinnerDurationAnimationMs.setEnabled(b);
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
        buttonPanel.add(buttonPlay = new JButton(new ImageIcon("resources/images/play.jpeg")));
        buttonPlay.setPreferredSize(new Dimension(30, 25));
        buttonFileSelect.addActionListener(this);
        buttonNew.addActionListener(this);
        buttonRefresh.addActionListener(this);
        buttonSave.addActionListener(this);
        buttonDelete.addActionListener(this);
        buttonPlay.addActionListener(this);
        setButtonEnabled(false);
        return buttonPanel;

    }

    private AnimationObject getSelectedAnimationObject() {

        return tableModel.getValue(table.getSelectedRow());
    }

    private JPanel getPanelSpinner() {
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
        formPanelSpinner.add(spinnerSpriteStartPosY  = new JSpinner(), BorderLayout.PAGE_END);
        formPanelSpinner.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.animation.total.column")), BorderLayout.PAGE_END);
        formPanelSpinner.add(spinnerAnimationTotalColumn = new JSpinner(), BorderLayout.PAGE_END);
        formPanelSpinner.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.animation.total.row")), BorderLayout.PAGE_END);
        formPanelSpinner.add(spinnerAnimationTotalRow  = new JSpinner(), BorderLayout.PAGE_END);
        formPanelSpinner.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.duration.animation.ms")), BorderLayout.PAGE_END);
        formPanelSpinner.add(spinnerDurationAnimationMs = new JSpinner(), BorderLayout.PAGE_END);

        SpringUtilities.makeCompactGrid(formPanelSpinner,
                formPanelSpinner.getComponentCount() / 2, 2, //rows, cols
                0, 0, //initX, initY
                0, 0);       //xPad, yPad
        return formPanelSpinner;
    }

    private ImageIcon selectImageFromProject() {
        try {
            String[] lista = imageOfProject("").list();
            
            for (int i = 0; i < lista.length; i++) {
                if (textFieldImagemName.getText().length() > 0
                        && lista[i].indexOf(textFieldImagemName.getText().concat(".")) != -1) {
                    image = new File(imageOfProject(lista[i]).getAbsolutePath());
                    BufferedImage myImage = ImageIO.read(image);
                    ImageIcon myIcon = new ImageIcon(myImage.getSubimage((Integer) spinnerSpriteStartPosX.getValue(),
                            (Integer) spinnerSpriteStartPosY.getValue(),
                            (Integer) spinnerSpriteWidth.getValue(),
                            (Integer) spinnerSpriteHeight.getValue()));
                    return myIcon;
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
            deleteListAnimationObject();

        }
        if (e.getSource() == buttonFileSelect) {
            if (((int)spinnerSpriteWidth.getValue() != 0 && (int)spinnerSpriteHeight.getValue() != 0)) {
                selectImage();
            } else {
                JOptionPane.showMessageDialog(null, LanguageUtil.getLanguageFactory().getMessage("message.dialog.sprites"));
            }

        }
        if (e.getSource() == buttonPlay) {
            preview();
        }


    }

    private void deleteListAnimationObject() {
        int selecionados[] = table.getSelectedRows();
        if (selecionados.length > 0) {
            List<AnimationObject> ao = new ArrayList<>();

            for (int i = 0; i < selecionados.length; i++) {
                selecionados[i] = table.convertRowIndexToModel(selecionados[i]);
                ao.add(tableModel.getValue(selecionados[i]));
            }
            for (AnimationObject rao : ao) {
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
            salveOrUpDate();
            refresh();
            if (image != null && !imageOfProject(getNameImageToCopy()).exists()) {

                copyImageToFolderImagens(getNameImageToCopy());
            }
        } else {
            salveOrUpDate();
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
        spinnerAnimationTotalColumn.setValue(0);
        spinnerAnimationTotalRow.setValue(0);
        spinnerDurationAnimationMs.setValue(0);
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

    private void setSelectedValuesToFields() {
        animationObject = getSelectedAnimationObject();
        textFieldName.setText(animationObject.getName());
        textFieldImagemName.setText(animationObject.getFileName());
        spinnerSpriteWidth.setValue((Integer) animationObject.getSpriteWidth());
        spinnerSpriteHeight.setValue((Integer) animationObject.getSpriteHeight());
        spinnerSpriteStartPosX.setValue((Integer) animationObject.getSpriteStartPosX());
        spinnerSpriteStartPosY.setValue((Integer) animationObject.getSpriteStartPosY());
        spinnerAnimationTotalColumn.setValue((Integer) animationObject.getAnimationTotalColumn());
        spinnerAnimationTotalRow.setValue((Integer) animationObject.getAnimationTotalRow());
        spinnerDurationAnimationMs.setValue((Integer) animationObject.getDurationAnimationMS());
        labelPreviewImageIcon.setIcon(selectImageFromProject());
        comboBoxTypeImage.setSelectedItem(animationObject.getTypeImage());
        
    }

    private void newRegister() {
        buttonNew.setEnabled(false);
        buttonDelete.setEnabled(false);
        buttonSave.setEnabled(true);
        setFieldEnabled(true);
        textFieldName.requestFocus();
    }

    @SuppressWarnings("deprecation")
	@Override
    public void valueChanged(ListSelectionEvent e) {
        boolean isAdjusting = table.getSelectedRow() != -1;

        if (isAdjusting) {
            setSelectedValuesToFields();
            setFieldEnabled(isAdjusting);
            setButtonEnabled(isAdjusting);
            if(animation != null && !animation.getState().equals("TERMINATED")){
            	animation.stop();
            }
            textFieldName.requestFocus();

        }
    }

    private void preview() {
        animation = new Thread(new Runnable() {
            @Override
            public void run() {
                int y = (Integer) spinnerSpriteStartPosY.getValue();
                int w = (Integer) spinnerSpriteWidth.getValue();
                int h = (Integer) spinnerSpriteHeight.getValue();

                int linha = (Integer) spinnerAnimationTotalRow.getValue();
                int coluna = (Integer) spinnerAnimationTotalColumn.getValue();
                int tempoDuracao = (Integer) spinnerDurationAnimationMs.getValue();


                for (int i = 0; i < linha; i++) {
                    int x = (Integer) spinnerSpriteStartPosX.getValue();
                    for (int in = 0; in < coluna; in++) {


                        try {
                            Thread.sleep(tempoDuracao / (linha * coluna));
                            labelPreviewImageIcon.setIcon(getIconImagem(x, y, w, h));
                            x = x + w;

                        } catch (InterruptedException ex) {
                            Logger.getLogger(AnimationObjectTableModel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    y = y + w;
                }
            }
        });
        animation.start();
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
        buttonPlay.setEnabled(b);
        buttonRefresh.setEnabled(b);

    }
}

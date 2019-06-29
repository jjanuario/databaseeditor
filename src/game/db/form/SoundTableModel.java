/**
 * =========================================== <br>
 * PROJETO PARALLAX - Engine de Jogos 2D e RPG <br>
 * Data de alteração do arquivo: 30/10/2012 <br>
 * ===========================================
 * <P>
 * 
 * A classe <b>SoundTableModel</b> (Tabela dos Sons)
 * manipula os sons utilzados no jogo.
 * <p>
 * 
 * @see <a
 *      href="http://www.einformacao.com.br/parallax/component/content/article/35-conteudo-site/86-documentos-para-leitura">Documentos
 *      para Leitura</a>
 * @author <a href="mailto:jeferson.januario@gmail.com">Jeferson Januario</a>
 * @version 1.0.1
 * @since Existe desde a versão 1.0.1 do projeto
 */
package game.db.form;

import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import game.db.dao.SoundDao;
import game.db.entity.Sound;
import game.db.util.LanguageUtil;
import game.db.util.SpringUtilities;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.tritonus.share.sampled.file.TAudioFileFormat;


public class SoundTableModel implements ActionListener, ListSelectionListener {

    final private SoundDao dao = new SoundDao();
    private JTable table;
    private JButton buttonNew;
    private JButton buttonRefresh;
    private JButton buttonSave;
    private JButton buttonDelete;
    private JButton buttonPlay;
    private JButton buttonStop;
    private ListSelectionModel ListSelectionModel;
    private JTextField textFieldName;
    private JTextField textFieldFileName;
    private JButton buttonSelectFile;
    private JSlider SoundDuration;
    private File file;
    private int duration;
    private JLabel labelTime;
    private JLabel labelSoundTitle;
    private JLabel labelDuration;
    private ObjectTableModel<Sound> tableModel;
    private Sound Sound;
    private AudioFileFormat baseFileFormat = null;
    JFileChooser fileChooser;
    private Thread thread;
    private Thread thread2;
    /**
     * @return PanelBase Contem todos os componetes da GUI
     * O metodo show resolve as dependencias da classe e retorna a GUI com todos os componetes.
     */
    
    public JPanel show() {
        AnnotationResolver resolver = new AnnotationResolver(Sound.class);
        tableModel = new ObjectTableModel<>(resolver, "id,name,fileName");
        setData();
        return getBasePanel();
    }

    private JPanel getFormPanel() {
        JPanel formPanel = new JPanel();

        formPanel.setLayout(new SpringLayout());
        formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.name")));
        formPanel.add(textFieldName = new JTextField());

        textFieldName.setPreferredSize(new Dimension(120, 8));
        formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.file.name")));
        formPanel.add(textFieldFileName = new JTextField());

        formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.select.file")));
        formPanel.add(buttonSelectFile = new JButton(new ImageIcon("resources/images/select.jpeg")));
        buttonSelectFile.setPreferredSize(new Dimension(50, 25));
        buttonSelectFile.addActionListener(this);

        SpringUtilities.makeCompactGrid(formPanel,
                formPanel.getComponentCount() / 2, 2, //rows, cols
                0, 0, //initX, initY
                0, 0);       //xPad, yPad
        formPanel.setSize(300, 100);
        formPanel.setPreferredSize(new Dimension(300, 100));
         setFieldEnabled(false);
         textFieldFileName.setEnabled(false);
        return formPanel;
    }

    private JPanel getBasePanel() {
        JPanel basePanel = new JPanel();
        JScrollPane scrollPaneTable = new JScrollPane(table = new JTable(tableModel));
        basePanel.setLayout(new BorderLayout());
        basePanel.add(scrollPaneTable, BorderLayout.PAGE_START);
        basePanel.add(getFormPanel(), BorderLayout.LINE_START);
        basePanel.add(getPanelPreview(), BorderLayout.LINE_END);
        basePanel.add(getPanelButton(), BorderLayout.PAGE_END);
        scrollPaneTable.setViewportView(table);
        scrollPaneTable.setPreferredSize(new Dimension(500, 150));
        scrollPaneTable.setViewportView(table);

        ListSelectionModel = table.getSelectionModel();
        ListSelectionModel.addListSelectionListener(this);
       
        return basePanel;
    }

    private void saveOrUpDate() {
        boolean isUpdate = table.getSelectedRow() != -1;

        if (isUpdate) {
            Sound = getSoundSelected();
        } else {
            Sound = new Sound();
        }
        Sound.setName(textFieldName.getText());
        Sound.setFileName(textFieldFileName.getText());
        File f = new File("resources/sounds/" + file.getName());
        if ((Sound.getFileName() != file.getName()) && !f.exists()) {
            try {
                Files.copy(Paths.get(fileChooser.getSelectedFile().getAbsolutePath()), Paths.get("resources/sounds/" + file.getName()));
            } catch (IOException ex) {
                Logger.getLogger(SoundTableModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        dao.saveOrUpDate(Sound);
        refresh();


    }

    public void setFieldEnabled(boolean b) {

        textFieldName.setEnabled(b);
        buttonSelectFile.setEnabled(b);

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

    private File SoundOfProject(String nameFileToCopy) {
        return new File("resources/sounds/" + nameFileToCopy);
    }

    @SuppressWarnings("rawtypes")
	private File selectSoundFromProject() {
        try {
            String[] lista = SoundOfProject("").list();
            for (int i = 0; i < lista.length; i++) {
                if (textFieldFileName.getText().length() > 0
                        && lista[i].equalsIgnoreCase(textFieldFileName.getText())) {
                    file = new File(SoundOfProject(lista[i]).getAbsolutePath());


                }
            }

            baseFileFormat = AudioSystem.getAudioFileFormat(file);


            if (baseFileFormat instanceof TAudioFileFormat) {
                Map props = ((TAudioFileFormat) baseFileFormat).properties();
                // Length in seconds
                labelSoundTitle.setText((String) props.get("title"));
                duration = Math.round((((Long) props.get("duration")).intValue()) / 1000000);
                SoundDuration.setMinimum(0);
                SoundDuration.setMaximum(duration);
                int hora = duration / 3600;
                int min = duration / 60;
                int seg = duration % 60;
                labelDuration.setText(String.valueOf(hora).concat(":").concat(String.valueOf(min).concat(":").concat(String.valueOf(seg))));
                buttonPlay.setEnabled(true);

            }
        } catch (IOException ex) {
            Logger.getLogger(SoundTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RuntimeException ex) {
            throw new RuntimeException(ex);

        } catch (UnsupportedAudioFileException uafe) {
            throw new RuntimeException(uafe);

        }
        return null;
    }

    @SuppressWarnings("rawtypes")
	private void selectFile() {
        fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(new FileNameExtensionFilter("wav", "wav"));
        fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
        fileChooser.setDialogTitle(LanguageUtil.getLanguageFactory().getMessage("file.chooser.dialog.title"));
        int aprovado = fileChooser.showDialog(new JPanel(), null);
        if (aprovado == 0 && fileChooser.getSelectedFile().exists()) {
            file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            try {

                textFieldFileName.setText(file.getName());
                try {
                    baseFileFormat = AudioSystem.getAudioFileFormat(file);
                } catch (IOException ex) {
                    Logger.getLogger(SoundTableModel.class.getName()).log(Level.SEVERE, null, ex);

                } catch (UnsupportedAudioFileException uafe) {
                    Logger.getLogger(SoundTableModel.class.getName()).log(Level.SEVERE, null, uafe);
                } catch (HeadlessException he) {
                    Logger.getLogger(SoundTableModel.class.getName()).log(Level.SEVERE, null, he);
                } catch (RuntimeException re) {
                    Logger.getLogger(SoundTableModel.class.getName()).log(Level.SEVERE, null, re);
                }
                if (baseFileFormat instanceof TAudioFileFormat) {
                    Map props = ((TAudioFileFormat) baseFileFormat).properties();
                    // Length in seconds
                    labelSoundTitle.setText((String) props.get("title"));
                    duration = Math.round((((Long) props.get("duration")).intValue()) / 1000000);
                    SoundDuration.setMinimum(0);
                    SoundDuration.setMaximum(duration);
                    int hora = duration / 3600;
                    int min = duration / 60;
                    int seg = duration % 60;
                    labelDuration.setText(String.valueOf(hora).concat(":").concat(String.valueOf(min).concat(":").concat(String.valueOf(seg))));
                    buttonPlay.setEnabled(true);

                }
            } catch (RuntimeException re) {
                throw new RuntimeException(re);
            }
        } else {
            if (aprovado == 0) {
                selectFile();
            }
        }
    }

    private synchronized void mudaJSlider() {
        buttonStop.setEnabled(true);
        buttonStop.setEnabled(true);
        buttonSelectFile.setEnabled(false);
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                tocaSounda();
                for (int i = 0; i < duration; i++) {
                    SoundDuration.setValue(i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                buttonPlay.setEnabled(true);
                buttonStop.setEnabled(false);
                SoundDuration.setEnabled(true);


            }
        });
        thread.start();
        SoundDuration.setEnabled(false);
        buttonPlay.setEnabled(false);
    }

    private synchronized void tocaSounda() {
        thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                testPlay(file.getAbsolutePath());
            }
        });

        thread2.start();

    }

    private JPanel getPanelButtonPreview() {
        JPanel panelButtonPreview = new JPanel();

        panelButtonPreview.add(buttonPlay = new JButton(new ImageIcon("resources/images/play.jpeg")));
        buttonPlay.setSize(40, 35);
        buttonPlay.setPreferredSize(new Dimension(40, 35));
        panelButtonPreview.add(buttonStop = new JButton(new ImageIcon("resources/images/stop.jpeg")));
        buttonStop.setSize(40, 35);
        buttonStop.setPreferredSize(new Dimension(40, 35));
        buttonPlay.addActionListener(this);
        buttonStop.addActionListener(this);
        buttonPlay.setEnabled(false);
        buttonStop.setEnabled(false);

        return panelButtonPreview;
    }

    private JPanel getPanelDetaisSoundPreview() {
        JPanel panelDetaisSoundPreview = new JPanel();
        //panelDetaisSoundPreview.setLayout(new BorderLayout());

        panelDetaisSoundPreview.add(labelTime = new JLabel("..."), BorderLayout.CENTER);

        panelDetaisSoundPreview.add(SoundDuration = new JSlider(), BorderLayout.CENTER);
        SoundDuration.setValue(0);
        panelDetaisSoundPreview.add(labelDuration = new JLabel("0:0"), BorderLayout.CENTER);

        SoundDuration.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {

                labelTime.setText(String.valueOf(SoundDuration.getValue() / 3600).concat(":").concat(String.valueOf(SoundDuration.getValue() / 60).concat(":").concat(String.valueOf(SoundDuration.getValue() % 60))));
            }
        });
        return panelDetaisSoundPreview;
    }

    private JPanel getPanelTitleSound() {
        JPanel panelTitleSound = new JPanel();
        panelTitleSound.add(labelSoundTitle = new JLabel("..."), BorderLayout.PAGE_START);
        return panelTitleSound;
    }

    private JPanel getPanelPreview() {
        JPanel panelPreview = new JPanel();
        panelPreview.setLayout(new BorderLayout());
        panelPreview.add(getPanelTitleSound(), BorderLayout.PAGE_START);
        panelPreview.add(getPanelDetaisSoundPreview(), BorderLayout.CENTER);
        panelPreview.add(getPanelButtonPreview(), BorderLayout.PAGE_END);
        panelPreview.setSize(350, 100);
        panelPreview.setPreferredSize(new Dimension(350, 100));
        return panelPreview;
    }

    private void deleteListSound() {
        int selecionados[] = table.getSelectedRows();
        if (selecionados.length > 0) {
            List<Sound> listSound = new ArrayList<>();

            for (int i = 0; i < selecionados.length; i++) {
                selecionados[i] = table.convertRowIndexToModel(selecionados[i]);
                listSound.add(tableModel.getValue(selecionados[i]));
            }
            for (Sound rSound : listSound) {
                dao.remove(rSound);
                tableModel.remove(rSound);
            }
            refresh();
            listSound = null;
            System.gc();
        }
    }

    private void setFieldsToNewValues() {
        textFieldName.setText("");
        textFieldFileName.setText("");
        labelDuration.setText("00:00:00");
        labelTime.setText("00:00:00");
        labelSoundTitle.setText("...");
        file = null;
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

    public void testPlay(String filename) {
        try {
            file = new File(filename);
            // Get AudioInputStream from given file.	
            AudioInputStream in = AudioSystem.getAudioInputStream(file);
            AudioInputStream din = null;
            if (in != null) {
                AudioFormat baseFormat = in.getFormat();
                AudioFormat decodedFormat = new AudioFormat(
                        AudioFormat.Encoding.PCM_SIGNED,
                        baseFormat.getSampleRate(),
                        16,
                        baseFormat.getChannels(),
                        baseFormat.getChannels() * 2,
                        baseFormat.getSampleRate(),
                        false);
                // Get AudioInputStream that will be decoded by underlying VorbisSPI
                din = AudioSystem.getAudioInputStream(decodedFormat, in);
                // Play now !
                rawplay(decodedFormat, din);
                in.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void rawplay(AudioFormat targetFormat,
            AudioInputStream din) throws IOException, LineUnavailableException {
        byte[] data = new byte[4096];
        SourceDataLine line = getLine(targetFormat);
        if (line != null) {
            // Start
            line.start();
            @SuppressWarnings("unused")
			int nBytesRead = 0, nBytesWritten = 0;
            while (nBytesRead != -1) {
                nBytesRead = din.read(data, 0, data.length);
                if (nBytesRead != -1) {
                    nBytesWritten = line.write(data, 0, nBytesRead);
                }
            }
            // Stop
            line.drain();
            line.stop();
            line.close();
            din.close();
        }
    }

    private SourceDataLine getLine(AudioFormat audioFormat) throws LineUnavailableException {
        SourceDataLine res = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
        res = (SourceDataLine) AudioSystem.getLine(info);
        res.open(audioFormat);
        return res;
    }

    private Sound getSoundSelected() {

        return tableModel.getValue(table.getSelectedRow());
    }

    private void setSelectedValuesToFields() {
        Sound = getSoundSelected();
        textFieldName.setText(Sound.getName());
        textFieldFileName.setText(Sound.getFileName());
        selectSoundFromProject();


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
            buttonStop.setEnabled(false);
        }
    }

    @SuppressWarnings("deprecation")
	private void setButtonEnabled(boolean b) {
        buttonDelete.setEnabled(b);
        buttonSave.setEnabled(b);
        buttonNew.setEnabled(!b);
        buttonPlay.setEnabled(b);
        buttonStop.setEnabled(b);
        buttonRefresh.setEnabled(b);

        if (thread != null && !thread.getState().equals("TERMINATED")) {
            thread.stop();
        }
        if (thread2 != null && !thread2.getState().equals("TERMINATED")) {
            thread2.stop();
        }
    }

    @SuppressWarnings("deprecation")
	@Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonNew) {
            newRegister();
        }
        if (e.getSource() == buttonRefresh) {
            refresh();
        }
        if (e.getSource() == buttonSave) {
            saveOrUpDate();

        }
        if (e.getSource() == buttonDelete) {
            deleteListSound();

        }
        if (e.getSource() == buttonSelectFile) {
            selectFile();
        }
        if (e.getSource() == buttonPlay) {
            mudaJSlider();

        }
        if (e.getSource() == buttonStop) {
            try {
                thread2.stop();
                thread.stop();
                buttonPlay.setEnabled(true);
                buttonStop.setEnabled(false);
                buttonSelectFile.setEnabled(true);

            } catch (RuntimeException re) {
                Logger.getLogger(SoundTableModel.class.getName()).log(Level.SEVERE, null, re);
            }


        }
    }
}

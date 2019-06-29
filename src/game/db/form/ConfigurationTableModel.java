
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.db.form;

import game.db.dao.ConfigurationDao;
import game.db.entity.Configuration;
import game.db.util.LanguageUtil;
import game.db.util.SpringUtilities;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * =========================================== <br>
 * PROJETO PARALLAX - Engine de Jogos 2D e RPG <br>
 * Data de alteração do arquivo: 30/10/2012 <br>
 * =========================================== <P>
 * 
 * A classe <b>ConfigurationTableModel</b> (Modelo da Tabela de Configuração) guarda as configurações do Jogo, 
 * mapa inicial, resolução, etc.<p>
 * 
 * @see	<a href="http://www.einformacao.com.br/parallax/component/content/article/35-conteudo-site/86-documentos-para-leitura">Documentos para Leitura</a>
 * @author <a href="mailto:jeferson.januario@gmail.com">Jeferson Januario</a>
 * @version 1.0.1
 * @since Existe desde a versão 1.0.1 do projeto
 */
public class ConfigurationTableModel implements ActionListener {

    final private ConfigurationDao dao = new ConfigurationDao();
    private JTextField textFieldName;
    private JRadioButton portugues;
    private JRadioButton ingles;
    private JRadioButton espanhol;
    private JTextField textFieldMainMap;
    private JCheckBox checkBoxEnableSound;
    private JCheckBox checkBoxEnableMusicThemebattle;
    private JCheckBox checkBoxEnableMusicTheme;
    private JCheckBox checkBoxGameDebug;
    private JCheckBox checkBoxGameFullScreen;
    private JCheckBox checkBoxGameVSYNC;
    private JSpinner spinnerTimeDayMS;
    private JSpinner spinnerGameTargetFrameRate;
    private JSpinner spinnerGameFrameWidth;
    private JSpinner spinnerGameFrameHeight;
    private JSlider jSliderVolumeTheme;
    private JSlider jSliderVolumeThemeBattle;
    private JButton buttonSave;
    private ButtonGroup groupLanguage;
    private Configuration configuration;

    /**
     * @return PanelBase Contem todos os componetes da GUI
     * O metodo show resolve as dependencias da classe e retorna a GUI com todos os componetes.
     */
    public JPanel show() {

return getPanelBase();
        
    }

    private JPanel getPanelForm() {
        JPanel panelForm = new JPanel();
        panelForm.setLayout(new BorderLayout());
        panelForm.add(getPanelFormText(), BorderLayout.LINE_START);

        return panelForm;
    }

    private JPanel getPanelFormText() {
        JPanel panelFormText = new JPanel();
        panelFormText.setLayout(new SpringLayout());
        panelFormText.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.name")));
        panelFormText.add(textFieldName = new JTextField());
        panelFormText.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.language")), BorderLayout.LINE_START);

        groupLanguage = new ButtonGroup();
        groupLanguage.add(portugues  = new JRadioButton());
        portugues.setText(LanguageUtil.getLanguageFactory().getMessage("label.radiobutton.portuguese"));
        portugues.setActionCommand("br");
        groupLanguage.add(ingles  = new JRadioButton());
        ingles.setText(LanguageUtil.getLanguageFactory().getMessage("label.radiobutton.english"));
        ingles.setActionCommand("us");
        groupLanguage.add(espanhol  = new JRadioButton());
        espanhol.setText(LanguageUtil.getLanguageFactory().getMessage("label.radiobutton.espanhol"));
        espanhol.setActionCommand("es");
        JPanel language = new JPanel();
        language.add(portugues);
        language.add(ingles);
        language.add(espanhol);


        panelFormText.add(language);
        panelFormText.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.waypoint")));
        panelFormText.add(textFieldMainMap = new JTextField());
        panelFormText.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.enable.sound")));
        panelFormText.add(checkBoxEnableSound = new JCheckBox());
        panelFormText.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.enable.music.themesound")));
        panelFormText.add(checkBoxEnableMusicThemebattle = new JCheckBox());
        panelFormText.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.enable.music.theme")));
        panelFormText.add(checkBoxEnableMusicTheme = new JCheckBox());
        panelFormText.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.game.debug")));
        panelFormText.add(checkBoxGameDebug = new JCheckBox());
        panelFormText.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.gamefullscreen")));
        panelFormText.add(checkBoxGameFullScreen = new JCheckBox());
        panelFormText.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.game.vsync")));
        panelFormText.add(checkBoxGameVSYNC = new JCheckBox());
        panelFormText.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.time.day.ms")));
        panelFormText.add(spinnerTimeDayMS = new JSpinner());
        panelFormText.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.game.target.frame.rate")));
        panelFormText.add(spinnerGameTargetFrameRate = new JSpinner());
        panelFormText.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.game.frame.width")));
        panelFormText.add(spinnerGameFrameWidth = new JSpinner());
        panelFormText.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.game.frame.height")));
        panelFormText.add(spinnerGameFrameHeight = new JSpinner());
        panelFormText.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.volume.theme")));
        panelFormText.add(jSliderVolumeTheme = new JSlider(0,100));
        panelFormText.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.volume.theme.battle")));
        panelFormText.add(jSliderVolumeThemeBattle = new JSlider(0,100));

        configuration = getConfiguration();
        textFieldName.setText(configuration.getName());
        groupLanguage.setSelected(portugues.getModel(), configuration.getFileNameLanguage().equals("br.properties"));
        groupLanguage.setSelected(ingles.getModel(), configuration.getFileNameLanguage().equals("us.properties"));
        groupLanguage.setSelected(espanhol.getModel(), configuration.getFileNameLanguage().equals("es.properties"));
        textFieldMainMap.setText(configuration.getMainMap());
        checkBoxEnableSound.setSelected(configuration.getEnableSound() == 1);
        checkBoxEnableMusicThemebattle.setSelected(configuration.getEnableMusicThemeBattle() == 1);
        checkBoxEnableMusicTheme.setSelected(configuration.getEnableMusicTheme() == 1);
        checkBoxGameDebug.setSelected(configuration.getGameDebug() == 1);
        checkBoxGameFullScreen.setSelected(configuration.getGameFullScreen() == 1);
        checkBoxGameVSYNC.setSelected(configuration.getGameVSYNC() == 1);
        spinnerTimeDayMS.setValue(configuration.getTimeDayMS());
        spinnerGameTargetFrameRate.setValue(configuration.getGameTargetFrameRate());
        spinnerGameFrameWidth.setValue(configuration.getGameFrameWidth());
        spinnerGameFrameHeight.setValue(configuration.getGameFrameHeight());
        double d = configuration.getVolumeThemeBattle()*10;
        int i = (int) d*10;
               
        jSliderVolumeThemeBattle.setValue(i);
        d = configuration.getVolumeTheme()*10;
        i = (int) d*10;
        jSliderVolumeTheme.setValue(i);
        SpringUtilities.makeCompactGrid(panelFormText,
                panelFormText.getComponentCount() / 2, 2, //rows, cols
                6, 6, //initX, initY
                6, 6);       //xPad, yPad

        return panelFormText;
    }

    private JPanel getPanelBase() {
        JPanel panelBase = new JPanel();
        JPanel logo = new JPanel();
        logo.add(new JLabel(new ImageIcon("resources/images/logo.png")));
        logo.setPreferredSize(new Dimension(1000, 180));

        panelBase.setLayout(new BorderLayout());
        panelBase.add(logo, BorderLayout.BEFORE_FIRST_LINE);
        panelBase.add(getPanelForm(), BorderLayout.CENTER);
        panelBase.add(getPanelButton(), BorderLayout.PAGE_END);

        return panelBase;
    }

    private JPanel getPanelButton() {

        JPanel paneButton = new JPanel();
        paneButton.add(buttonSave = new JButton(LanguageUtil.getLanguageFactory().getMessage("button.save")));
        buttonSave.addActionListener(this);
        return paneButton;
    }


    private Configuration getConfiguration() {

        return dao.getConfiguration();
    }

    private void saveOrUpDate() {
        configuration = getConfiguration();

        configuration.setName(textFieldName.getText());
        configuration.setFileNameLanguage(groupLanguage.getSelection().getActionCommand());
        configuration.setMainmap(textFieldMainMap.getText());
        if (checkBoxEnableSound.isSelected()) {
            configuration.setEnablesound(1);
        } else {
            configuration.setEnablesound(0);
        }
        if (checkBoxEnableMusicThemebattle.isSelected()) {
            configuration.setEnableMusicThemeBattle(1);
        } else {
            configuration.setEnableMusicThemeBattle(0);
        }
        if (checkBoxEnableMusicTheme.isSelected()) {
            configuration.setEnableMusicTheme(1);
        } else {
            configuration.setEnableMusicTheme(0);
        }
        if (checkBoxGameDebug.isSelected()) {
            configuration.setGameDebug(1);
        } else {
            configuration.setGameDebug(0);
        }
        if (checkBoxGameFullScreen.isSelected()) {
            configuration.setGameFullScreen(1);
        } else {
            configuration.setGameFullScreen(0);
        }
        if (checkBoxGameVSYNC.isSelected()) {
            configuration.setGameVSYNC(1);
        } else {
            configuration.setGameVSYNC(0);
        }

        configuration.setTimeDayMS((Integer) spinnerTimeDayMS.getValue());
        configuration.setGameTargetFrameRate((Integer) spinnerGameTargetFrameRate.getValue());
        configuration.setGameFrameWidth((Integer) spinnerGameFrameWidth.getValue());
        configuration.setGameFrameHeight((Integer) spinnerGameFrameHeight.getValue());
        int i = jSliderVolumeTheme.getValue();
        double d = i/10;
        
        configuration.setVolumeTheme(d/10);
        i =  jSliderVolumeThemeBattle.getValue();
        d = i /10;
        configuration.setVolumeThemeBattle(d/10);


        dao.upDate(configuration);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == buttonSave) {
        	if(groupLanguage.getSelection() == null){
        		JOptionPane.showMessageDialog(null, LanguageUtil.getLanguageFactory().getMessage("error.select.language"));
        	}else{
        		saveOrUpDate();
        	}
            
        }
    }

}

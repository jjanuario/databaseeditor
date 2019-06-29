/**
 * =========================================== <br>
 * PROJETO PARALLAX - Engine de Jogos 2D e RPG <br>
 * Data de alteração do arquivo: 30/10/2012 <br>
 * ===========================================
 * <P>
 * 
 * A classe <b>MonsterTableModel</b> (Tabela dos Monstros)
 * manipula os monstros 
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
import game.db.dao.MonsterDao;
import game.db.entity.Monster;
import game.db.util.LanguageUtil;
import game.db.util.SpringUtilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class MonsterTableModel implements ListSelectionListener, ActionListener {

    final private MonsterDao dao = new MonsterDao();
    
   
    private ObjectTableModel<Monster> tableModel;
    private JTable table;
    private JTextField textFieldName;
    private JSpinner spinnerAccuracy;
    private JSpinner spinnerActionPoint;
    private JSpinner spinnerConstitution;
    private JSpinner spinnerCritical;
    private JSpinner spinnerDexterity;
    private JSpinner spinnerEvasion;
    private JSpinner spinnerExperience;
    private JSpinner spinnerGold;
    private JSpinner spinnerHp;
    private JSpinner spinnerInitiative;
    private JSpinner spinnerIntelligence;
    private JSpinner spinnerLevel;
    private JSpinner spinnerLuck;
    private JSpinner spinnerStrength;
    private JSpinner spinnerRewardExperience;
    private ButtonGroup radioGroupGender;
    
    private JTextField textFieldMinIonIds;
    private JTextArea textAreaHistory;
    private JRadioButton woman;
    private JRadioButton man;
  
    private JComboBox<Integer> comboBoxAnimationObjectMovimentId;
    private JComboBox<Integer> comboBoxImagemBattlerId;
    private JComboBox<Integer> comboBoxImagemFaceId;
    private JComboBox<String>  comboBoxTypeClass;
    private JComboBox<Integer> comboBoxTerraInTypeId;
    
    private JButton buttonNew;
    private    JButton buttonRefresh;
    private    JButton buttonDelete;
    private    JButton buttonSave;
        
    private ListSelectionModel ListSelectionModel;
    private Monster Monster;
    /**
     * @return PanelBase Contem todos os componetes da GUI
     * O metodo show resolve as dependencias da classe e retorna a GUI com todos os componetes.
     */
    public JPanel show() {
        AnnotationResolver resolver = new AnnotationResolver(Monster.class);
        tableModel = new ObjectTableModel<>(resolver, "name,actionPoint,gender,gold,level,terraInTypeId,typeClass");
        table = new JTable(tableModel);
        setData();
        
        return getPanelBase();
    }

    private JPanel getPanelForm() {
       JPanel panelForm = new JPanel();
        panelForm.setLayout(new BorderLayout());
        panelForm.add(getPanelFormText(), BorderLayout.LINE_START);
        panelForm.add(getPanelSpinner(), BorderLayout.LINE_END);
        setFieldsEnabled(false);
        
        return panelForm;
    }
private JPanel getPanelFormText(){
    JPanel panelFormText = new JPanel();
    panelFormText.setLayout(new SpringLayout());
    panelFormText.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.name")));
    panelFormText.add(textFieldName = new JTextField());                                                                                           
    radioGroupGender = new ButtonGroup();
    radioGroupGender.add(man = new JRadioButton());
    man.setText("man");
    man.setActionCommand("0");
    radioGroupGender.add(woman = new JRadioButton());
    woman.setText("woman");
    woman.setActionCommand("1");
    panelFormText.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.gender")) , BorderLayout.LINE_START);
    JPanel gender = new JPanel();
    gender.add(woman);
    gender.add(man);
    
    panelFormText.add(gender);
    
   
    List<String> listS;
    
 panelFormText.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.type.class")), BorderLayout.LINE_START);
    panelFormText.add(comboBoxTypeClass = new JComboBox<String>(), BorderLayout.LINE_START);
    listS = dao.getClasse();
       for(int i =0; listS.size() > i; i++){
    comboBoxTypeClass.addItem(listS.get(i));
       }
    
    panelFormText.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.animation.object.moviment.id")), BorderLayout.LINE_START);
    panelFormText.add(comboBoxAnimationObjectMovimentId = new JComboBox<Integer>(), BorderLayout.LINE_START);
    List<Integer> list = dao.getAnimationObjectMovimentId();
       for(int i =0; list.size() > i; i++){
    comboBoxAnimationObjectMovimentId.addItem(list.get(i));
       }
    panelFormText.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.image.battler")), BorderLayout.LINE_START);
    panelFormText.add(comboBoxImagemBattlerId = new JComboBox<Integer>(), BorderLayout.LINE_START);
    list = dao.getImagemBattlerId();
       for(int i =0; list.size() > i; i++){
    comboBoxImagemBattlerId.addItem(list.get(i));
       }
    
    
    panelFormText.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.terra.in.type")), BorderLayout.LINE_START);
    panelFormText.add(comboBoxTerraInTypeId = new JComboBox<Integer>(), BorderLayout.LINE_START);
    list = dao.getTerraInTypeId();
       for(int i =0; list.size() > i; i++){
    comboBoxTerraInTypeId.addItem(list.get(i));
       }
           panelFormText.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.image.face.id")), BorderLayout.LINE_START);
    panelFormText.add(comboBoxImagemFaceId = new JComboBox<Integer>(), BorderLayout.LINE_START);
    list = dao.getImagemFaceId();
       for(int i =0; list.size() > i; i++){
    comboBoxImagemFaceId.addItem(list.get(i));
       }
       
       panelFormText.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.waypoint")));
       panelFormText.add(textFieldMinIonIds = new JTextField());
       textFieldMinIonIds.setText("dfsdfsd");
       panelFormText.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.history")));
       JScrollPane scrollTextArea = new JScrollPane(textAreaHistory = new JTextArea(7, 30));
       
       textAreaHistory.setLineWrap(true);
       textAreaHistory.setWrapStyleWord(true);
       textAreaHistory.setBackground(Color.white);
       textAreaHistory.setPreferredSize(new Dimension(10,15));
       textAreaHistory.setCaretPosition(textAreaHistory.getText().length());
       
      //scrollTextArea.setVerticalScrollBarPolicy(JScrollPane.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
       //scrollTextArea.setViewportView(textAreaHistory);
       panelFormText.add(scrollTextArea);
      
    SpringUtilities.makeCompactGrid(panelFormText,
                                        panelFormText.getComponentCount()/2, 2, //rows, cols
                                        6, 6,        //initX, initY
                                        6, 6);       //xPad, yPad
    return panelFormText;
}
    private JPanel getPanelBase() {
        JPanel panelBase = new JPanel();
         JScrollPane scrollPanelTable = new JScrollPane(table);
         scrollPanelTable.setViewportView(table);
        scrollPanelTable.setPreferredSize(new Dimension(1000, 100));
        
        panelBase.setLayout(new BorderLayout());
        panelBase.add(scrollPanelTable, BorderLayout.BEFORE_FIRST_LINE);
        panelBase.add(getPanelForm(), BorderLayout.CENTER);
        panelBase.add(getPanelButton(), BorderLayout.PAGE_END);
          
        ListSelectionModel = table.getSelectionModel();
        ListSelectionModel.addListSelectionListener(this);
               
        
        return panelBase;
    }

    private JPanel getPanelSpinner() {
        JPanel panelSpinner = new JPanel();
        panelSpinner.setLayout(new SpringLayout());

        panelSpinner.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.accuracy")), BorderLayout.LINE_START);
        panelSpinner.add(spinnerAccuracy = new JSpinner(), BorderLayout.LINE_START);
        spinnerAccuracy.setPreferredSize(new Dimension(52, 19));
        panelSpinner.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.action.point")), BorderLayout.LINE_START);
        panelSpinner.add(spinnerActionPoint = new JSpinner(), BorderLayout.LINE_START);
        panelSpinner.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.constitution")), BorderLayout.LINE_START);
        panelSpinner.add(spinnerConstitution = new JSpinner(), BorderLayout.LINE_START);
        panelSpinner.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.critical")), BorderLayout.LINE_START);
        panelSpinner.add(spinnerCritical = new JSpinner(), BorderLayout.LINE_START);
        panelSpinner.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.dexterity")), BorderLayout.LINE_START);
        panelSpinner.add(spinnerDexterity = new JSpinner(), BorderLayout.LINE_START);
        panelSpinner.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.evasion")), BorderLayout.LINE_START);
        panelSpinner.add(spinnerEvasion = new JSpinner(), BorderLayout.LINE_START);
        panelSpinner.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.experience")), BorderLayout.LINE_START);
        panelSpinner.add(spinnerExperience = new JSpinner(), BorderLayout.LINE_START);
        panelSpinner.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.gold")), BorderLayout.LINE_START);
        panelSpinner.add(spinnerGold = new JSpinner(), BorderLayout.LINE_START);
        panelSpinner.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.hp")), BorderLayout.LINE_START);
        panelSpinner.add(spinnerHp = new JSpinner(), BorderLayout.LINE_START);
        panelSpinner.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.initiative")), BorderLayout.LINE_START);
        panelSpinner.add(spinnerInitiative = new JSpinner(), BorderLayout.LINE_START);
        panelSpinner.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.inteligencie")), BorderLayout.LINE_START);
        panelSpinner.add(spinnerIntelligence = new JSpinner(), BorderLayout.LINE_START);
        panelSpinner.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.level")), BorderLayout.LINE_START);
        panelSpinner.add(spinnerLevel = new JSpinner(), BorderLayout.LINE_START);
        panelSpinner.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.luck")), BorderLayout.LINE_START);
        panelSpinner.add(spinnerLuck = new JSpinner(), BorderLayout.LINE_START);
        panelSpinner.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.strength")), BorderLayout.LINE_START);
        panelSpinner.add(spinnerStrength = new JSpinner(), BorderLayout.LINE_START);
        panelSpinner.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.total.bonus.level")), BorderLayout.LINE_START);
        panelSpinner.add(spinnerRewardExperience = new JSpinner(), BorderLayout.LINE_START);


        SpringUtilities.makeCompactGrid(panelSpinner,
                15, 2, //rows, cols
                6, 6, //initX, initY
                6, 6);       //xPad, yPad

return panelSpinner;
    }

    private JPanel getPanelButton() {
        
         JPanel paneButton = new JPanel();
        paneButton.add(buttonNew = new JButton(LanguageUtil.getLanguageFactory().getMessage("button.new")));
        paneButton.add(buttonRefresh = new JButton(LanguageUtil.getLanguageFactory().getMessage("button.refresh")));
        paneButton.add(buttonSave = new JButton(LanguageUtil.getLanguageFactory().getMessage("button.save")));
        paneButton.add(buttonDelete = new JButton(LanguageUtil.getLanguageFactory().getMessage("button.delete")));
        buttonNew.addActionListener(this);
        buttonRefresh.addActionListener(this);
        buttonSave.addActionListener(this);
        buttonDelete.addActionListener(this);
         setButtonsEnabled(false);
        return paneButton;
    }

    private synchronized void setFieldsEnabled(boolean b) {
        
        
        spinnerAccuracy.setEnabled(b);
        spinnerActionPoint.setEnabled(b);
        spinnerConstitution.setEnabled(b);
        spinnerCritical.setEnabled(b);
        spinnerDexterity.setEnabled(b);
        spinnerEvasion.setEnabled(b);
        spinnerExperience.setEnabled(b);
        spinnerGold.setEnabled(b);
        spinnerHp.setEnabled(b);
        spinnerInitiative.setEnabled(b);
        spinnerIntelligence.setEnabled(b);
        spinnerLevel.setEnabled(b);
        spinnerLuck.setEnabled(b);
        spinnerStrength.setEnabled(b);
        spinnerRewardExperience.setEnabled(b);
        man.setEnabled(b);
        woman.setEnabled(b);
        comboBoxTerraInTypeId.setEnabled(b);
        comboBoxTypeClass.setEnabled(b);
        textAreaHistory.setEnabled(b);
        textFieldMinIonIds.setEnabled(b);
        textFieldName.setEnabled(b);
        comboBoxAnimationObjectMovimentId.setEnabled(b);
        comboBoxImagemBattlerId.setEnabled(b);
        comboBoxImagemFaceId.setEnabled(b);
        
                
        
    }
    private void setButtonsEnabled(boolean b){
        buttonDelete.setEnabled(b);
        buttonSave.setEnabled(b);
        buttonRefresh.setEnabled(b);
        buttonNew.setEnabled(!b);
        
    }
    private void refresh(){
        setData();
        setFieldsEnabled(false);
        setButtonsEnabled(false);
        setFieldsToNewValues();
    }
private void setValuesChanged(){
        Monster = getMonsterSelected();
        spinnerAccuracy.setValue(Monster.getAccuracy());
        spinnerActionPoint.setValue(Monster.getActionPoint());
        spinnerConstitution.setValue(Monster.getConstitution());
        spinnerCritical.setValue(Monster.getCritical());
        spinnerDexterity.setValue(Monster.getDexterity());
        spinnerEvasion.setValue(Monster.getEvasion());
        spinnerExperience.setValue(Monster.getExperience());
        spinnerGold.setValue(Monster.getGold());
        spinnerHp.setValue(Monster.getHp());
        spinnerInitiative.setValue(Monster.getInitiative());
        spinnerIntelligence.setValue(Monster.getIntelligence());
        spinnerLevel.setValue(Monster.getLevel());
        spinnerLuck.setValue(Monster.getLuck());
        spinnerStrength.setValue(Monster.getStrength());
        spinnerRewardExperience.setValue(Monster.getRewardExperience());
        man.setSelected(Monster.getGender().equals(0));
        woman.setSelected(Monster.getGender().equals(1));
        textAreaHistory.setText(Monster.getHistory());
                comboBoxTerraInTypeId.setSelectedItem(Monster.getTerraInTypeId());
        comboBoxTypeClass.setSelectedItem(Monster.getTypeClass());
        textFieldMinIonIds.setText(Monster.getMinIonIds());
        textFieldName.setText(Monster.getName());
        comboBoxAnimationObjectMovimentId.setSelectedItem(Monster.getAnimationObjectmMovementId());
        comboBoxImagemBattlerId.setSelectedItem(Monster.getImagemBattlerId());
        comboBoxImagemFaceId.setSelectedItem(Monster.getImagemFaceId());
        
       
}
private void setFieldsToNewValues(){
    
        spinnerAccuracy.setValue(0);
        spinnerActionPoint.setValue(0);
        spinnerConstitution.setValue(0);
        spinnerCritical.setValue(0);
        spinnerDexterity.setValue(0);
        spinnerEvasion.setValue(0);
        spinnerExperience.setValue(0);
        spinnerGold.setValue(0);
        spinnerHp.setValue(0);
        spinnerInitiative.setValue(0);
        spinnerIntelligence.setValue(0);
        spinnerLevel.setValue(0);
        spinnerLuck.setValue(0);
        spinnerStrength.setValue(0);
        spinnerRewardExperience.setValue(0);
        radioGroupGender.clearSelection();
        textAreaHistory.setText("");
        textFieldMinIonIds.setText("");
        textFieldName.setText("");
        comboBoxAnimationObjectMovimentId.setSelectedIndex(0);
        comboBoxImagemBattlerId.setSelectedIndex(0);
        comboBoxImagemFaceId.setSelectedIndex(0);
        comboBoxTerraInTypeId.setSelectedIndex(0);
        comboBoxTypeClass.setSelectedIndex(0);
        
        
}
    private void setData() {
        tableModel.clear();
        tableModel.setData(dao.lista());
    }
    private Monster getMonsterSelected(){
        
        return tableModel.getValue(table.getSelectedRow());
    }
    private void saveOrUpDate(){
        boolean isUpdate = table.getSelectedRow() != -1;
        
        if(isUpdate){
        Monster = getMonsterSelected();
        }else{
            Monster = new Monster();
        }
        Monster.setAccuracy((Integer) spinnerAccuracy.getValue());
        Monster.setActionPoint((Integer) spinnerActionPoint.getValue());
        Monster.setConstitution((Integer) spinnerConstitution.getValue());
        Monster.setCritical((Integer) spinnerCritical.getValue());
        Monster.setDexterity((Integer) spinnerDexterity.getValue());
        Monster.setEvasion((Integer) spinnerEvasion.getValue());
        Monster.setExperience((Integer) spinnerExperience.getValue());
        Monster.setGold((Integer) spinnerGold.getValue());
        Monster.setHp((Integer) spinnerHp.getValue());
        Monster.setInitiative((Integer) spinnerInitiative.getValue());
        Monster.setIntelligence((Integer) spinnerIntelligence.getValue());
        Monster.setLevel((Integer) spinnerLevel.getValue());
        Monster.setLuck((Integer) spinnerLuck.getValue());
        Monster.setStrength((Integer) spinnerStrength.getValue());
        Monster.setRewardexperience((Integer) spinnerRewardExperience.getValue());
        Monster.setGender(Integer.parseInt(radioGroupGender.getSelection().getActionCommand()));
        Monster.setImagemBattlerId((Integer) comboBoxAnimationObjectMovimentId.getSelectedItem());
        Monster.setTypeClass((String) comboBoxTypeClass.getSelectedItem());
        Monster.setImagemFaceId((Integer) comboBoxImagemFaceId.getSelectedItem());
        Monster.setTerraInTypeId((Integer) comboBoxTerraInTypeId.getSelectedItem());
        Monster.setMinIonIds((String) textFieldMinIonIds.getText());
        Monster.setName((String) textFieldName.getText());
       
        dao.saveOrUpDate(Monster);
        refresh();
        
                }
    private void delete(){
        List<Monster> list = tableModel.getList(table.getSelectedRows());
        tableModel.remove(list);
        dao.remove(list);
       
        refresh();
    }

     @Override
    public void valueChanged(ListSelectionEvent e) {
        boolean isAdjusting = table.getSelectedRow() != -1;
        if(isAdjusting){
            setFieldsEnabled(isAdjusting);
            setButtonsEnabled(isAdjusting);
            setValuesChanged();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonNew) {
            setFieldsEnabled(true);
            setButtonsEnabled(true);
            buttonDelete.setEnabled(false);
            setFieldsToNewValues();
            spinnerAccuracy.requestFocus();
        }
        if (e.getSource() == buttonRefresh) {
            refresh();
        }
        if (e.getSource() == buttonSave) {
            if(radioGroupGender.getSelection() == null){
                JOptionPane.showMessageDialog(null, "select gender");
            }else{
                saveOrUpDate();
            }
            
        }
        if (e.getSource() == buttonDelete) {
            delete();
        }
    }
}

/**
 * =========================================== <br>
 * PROJETO PARALLAX - Engine de Jogos 2D e RPG <br>
 * Data de alteração do arquivo: 30/10/2012 <br>
 * ===========================================
 * <P>
 * 
 * A classe <b>ObjectiveTableModel</b> (Tabela dos Objetivos)
 * manipula os objetivos do jogo.
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
import game.db.dao.ObjectiveDao;
import game.db.entity.Objective;
import game.db.util.LanguageUtil;
import game.db.util.SpringUtilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class ObjectiveTableModel implements ActionListener, ListSelectionListener {


    final private ObjectiveDao dao = new ObjectiveDao();
    private JTable table;
   
    private JButton buttonNew ;
    private JButton buttonRefresh ;
    private JButton buttonSave ;
    private JButton buttonDelete;
    

    private ListSelectionModel ListSelectionModel;
    
    private JTextField textFieldName;
    private JTextArea textAreaDescription;
    private JComboBox<Integer> comboBoxTargetObjectiveId;
    private JSpinner spinnerAmount;
    private JComboBox<String> comboBoxTypeObjective;
    private JTextArea textAreaMessageObjectiveEnd;
    private JComboBox<Integer> comboBoxNpcEndObjectiveId;
    private JTextField textFieldActionStartObjective;
    private JTextField textFieldActionEndObjective;
    private JComboBox<Integer> comboBoxQuestId;
    private JComboBox<Integer> comboBoxRewardId;
  
    private ObjectTableModel<Objective> tableModel;
    private Objective objective;
    /**
     * @return PanelBase Contem todos os componetes da GUI
     * O metodo show resolve as dependencias da classe e retorna a GUI com todos os componetes.
     */
    
    public JPanel show() {
        AnnotationResolver resolver = new AnnotationResolver(Objective.class);
        tableModel = new ObjectTableModel<>(resolver, "id,name,description,targetObjectId,amount,"
                + "typeObjective,messageObjectiveEnd,npcEndObjectiveId,actionEndObjective,questId,rewardId");
        setData();
        return getBasePanel();
    }

  

    private JPanel getFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new SpringLayout());
        formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.name")));
        formPanel.add(textFieldName = new JTextField());
         formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.description")));
        formPanel.add(textAreaDescription = new JTextArea());
         textAreaDescription.setLineWrap(true);
       textAreaDescription.setWrapStyleWord(true);
       textAreaDescription.setBackground(Color.white);
       textAreaDescription.setPreferredSize(new Dimension(50,60));
       textAreaDescription.setCaretPosition(textAreaDescription.getText().length());
         formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.target.objective")));
        formPanel.add(comboBoxTargetObjectiveId = new JComboBox<Integer>());
        List<Integer> list;
        list = dao.getTargetObjectiveId();
        for(int i=0; list.size() >i;i++){
        comboBoxTargetObjectiveId.addItem(list.get(i));
        }
        formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.amount")));
        formPanel.add(spinnerAmount = new JSpinner());
        formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.type.objective")));
        formPanel.add(comboBoxTypeObjective = new JComboBox<String>());
    
        for(int i=0; dao.getTypeObjective().size() >i;i++){
        comboBoxTypeObjective.addItem(dao.getTypeObjective().get(i));
        }
         formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.message.objective.end")));
        formPanel.add(textAreaMessageObjectiveEnd = new JTextArea());
         textAreaMessageObjectiveEnd.setLineWrap(true);
       textAreaMessageObjectiveEnd.setWrapStyleWord(true);
       textAreaMessageObjectiveEnd.setBackground(Color.white);
       textAreaMessageObjectiveEnd.setPreferredSize(new Dimension(50,60));
       textAreaMessageObjectiveEnd.setCaretPosition(textAreaDescription.getText().length());
        
         formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.target.objective")));
        formPanel.add(comboBoxNpcEndObjectiveId = new JComboBox<Integer>());
        list = dao.getNpcEndObjectiveId();
        for(int i=0; list.size() >i;i++){
        comboBoxNpcEndObjectiveId.addItem(list.get(i));
        }
 formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.action.start.objective")));
        formPanel.add(textFieldActionStartObjective = new JTextField());
        
    formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.action.end.objective")));
        formPanel.add(textFieldActionEndObjective = new JTextField());
            formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.quest")));
        formPanel.add(comboBoxQuestId = new JComboBox<Integer>());
        list = dao.getQuestId();
        for(int i=0; list.size() >i;i++){
        comboBoxQuestId.addItem(list.get(i));
        }
                  formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.reward")));
        formPanel.add(comboBoxRewardId = new JComboBox<Integer>());
        list = dao.getRewardId();
        for(int i=0; list.size() >i;i++){
        comboBoxRewardId.addItem(list.get(i));
        }

        
       
         SpringUtilities.makeCompactGrid(formPanel,
                formPanel.getComponentCount()/2, 2, //rows, cols
                0, 0, //initX, initY
                0, 0);       //xPad, yPad
     setFieldEnabled(false);
        return formPanel; 
    }   

    private JPanel getBasePanel() {
        JPanel basePanel = new JPanel();
        JScrollPane scrollPaneTable = new JScrollPane(table = new JTable(tableModel));
        basePanel.setLayout(new BorderLayout());
        basePanel.add(scrollPaneTable, BorderLayout.PAGE_START);
        basePanel.add(getFormPanel(), BorderLayout.CENTER);
  
        basePanel.add(getPanelButton(), BorderLayout.PAGE_END);
        scrollPaneTable.setViewportView(table);
        scrollPaneTable.setPreferredSize(new Dimension(500, 150));
        scrollPaneTable.setViewportView(table);
            ListSelectionModel = table.getSelectionModel();
        ListSelectionModel.addListSelectionListener(this);
        return basePanel;
    }

    private void saveOrUpDate(){
        boolean isUpdate = table.getSelectedRow() != -1;
        
        if(isUpdate){
        objective = getObjectiveSelected();
        }else{
            objective = new Objective();
        }
        objective.setName(textFieldName.getText());
        objective.setDescription(textAreaDescription.getText());
        objective.setTargetObjectId((Integer) comboBoxTargetObjectiveId.getSelectedItem());
        objective.setAmount((Integer) spinnerAmount.getValue());
        objective.setTypeObjective((String) comboBoxTypeObjective.getSelectedItem());
        objective.setMessageObjectiveEnd(textAreaMessageObjectiveEnd.getText());
        objective.setNpcEndObjectiveId((Integer)comboBoxNpcEndObjectiveId.getSelectedItem());
        objective.setActionStartObjective(textFieldActionStartObjective.getText());
        objective.setActionEndObjective(textFieldActionEndObjective.getText());
        objective.setQuestId((Integer) comboBoxQuestId.getSelectedItem());
        objective.setRewardId((Integer) comboBoxRewardId.getSelectedItem());
        
        dao.saveOrUpDate(objective);
        refresh();
  

  }


    public void setFieldEnabled(boolean b) {

    textFieldName.setEnabled(b);
      textAreaDescription.setEnabled(b);
      comboBoxTargetObjectiveId.setEnabled(b);
   spinnerAmount.setEnabled(b);
        comboBoxTypeObjective.setEnabled(b);
       textAreaMessageObjectiveEnd.setEnabled(b);
       comboBoxNpcEndObjectiveId.setEnabled(b);
       textFieldActionStartObjective.setEnabled(b);
      textFieldActionEndObjective.setEnabled(b);
       comboBoxQuestId.setEnabled(b);
      comboBoxRewardId.setEnabled(b);

    }

    private JPanel getPanelButton() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(buttonNew = new JButton(LanguageUtil.getLanguageFactory().getMessage("button.new")));
        buttonPanel.add(buttonRefresh = new JButton(LanguageUtil.getLanguageFactory().getMessage("button.refresh")));
        buttonPanel.add(buttonSave = new JButton(LanguageUtil.getLanguageFactory().getMessage("button.save")));
        buttonPanel.add(buttonDelete = new JButton(LanguageUtil.getLanguageFactory().getMessage("button.delete"))) ;
        buttonNew.addActionListener(this);
        buttonRefresh.addActionListener(this);
        buttonSave.addActionListener(this);
        buttonDelete.addActionListener(this);
        setButtonEnabled(false);
        
        
return buttonPanel;
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
            saveOrUpDate();

        }
        if (e.getSource() == buttonDelete) {
            deleteListObjective();

        }
    
    }

    private void deleteListObjective() {
        int selecionados[] = table.getSelectedRows();
        if (selecionados.length > 0) {
            List<Objective> listObjective = new ArrayList<>();

            for (int i = 0; i < selecionados.length; i++) {
                selecionados[i] = table.convertRowIndexToModel(selecionados[i]);
                listObjective.add(tableModel.getValue(selecionados[i]));
            }
            for (Objective rObjective : listObjective) {
                dao.remove(rObjective);
                tableModel.remove(rObjective);
            }
            refresh();
            listObjective = null;
            System.gc();
        }
    }

    
    private void setFieldsToNewValues() {
           textFieldName.setText("");
      textAreaDescription.setText("");
      comboBoxTargetObjectiveId.setSelectedIndex(0);
   spinnerAmount.setValue(0);
        comboBoxTypeObjective.setSelectedIndex(0);
       textAreaMessageObjectiveEnd.setText("");
       comboBoxNpcEndObjectiveId.setSelectedIndex(0);
       textFieldActionStartObjective.setText("");
      textFieldActionEndObjective.setText("");
       comboBoxQuestId.setSelectedIndex(0);
      comboBoxRewardId.setSelectedIndex(0);
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
private Objective getObjectiveSelected(){
        
        return tableModel.getValue(table.getSelectedRow());
    }
    private void setSelectedValuesToFields() {
        objective = getObjectiveSelected();
                 textFieldName.setText(objective.getName());
      textAreaDescription.setText(objective.getDescription());
      comboBoxTargetObjectiveId.setSelectedItem(objective.getTargetObjectId());
   spinnerAmount.setValue(objective.getAmount());
        comboBoxTypeObjective.setSelectedItem(objective.getTypeObjective());
       textAreaMessageObjectiveEnd.setText(objective.getMessageObjectiveEnd());
       comboBoxNpcEndObjectiveId.setSelectedItem(objective.getNpcEndObjectiveId());
       textFieldActionStartObjective.setText(objective.getActionStartObjective());
      textFieldActionEndObjective.setText(objective.getActionEndObjective());
       comboBoxQuestId.setSelectedItem(objective.getQuestId());
      comboBoxRewardId.setSelectedItem(objective.getRewardId());
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
 
    private void setButtonEnabled(boolean b) {
        buttonDelete.setEnabled(b);
        buttonSave.setEnabled(b);
        buttonNew.setEnabled(!b);
        buttonRefresh.setEnabled(b);
       
    }
   
}


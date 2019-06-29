/**
 * =========================================== <br>
 * PROJETO PARALLAX - Data Base Editor<br>
 * Data de alteração do arquivo: 30/10/2012 <br>
 * ===========================================
 * <P>
 * 
 * A classe <b>TypeDamageTableModel</b> (Tabela de tipo de danos) manipula os
 * tipos de danos.
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


import game.db.dao.TypeDamageDao;
import game.db.entity.TypeDamage;
import game.db.util.LanguageUtil;
import game.db.util.SpringUtilities;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;


public class TypeDamageTableModel implements ActionListener, ListSelectionListener {


    final private TypeDamageDao dao = new TypeDamageDao();
    private JTable table;
   
    private JButton buttonNew ;
    private JButton buttonRefresh ;
    private JButton buttonSave ;
    private JButton buttonDelete;
    

    private ListSelectionModel ListSelectionModel;
    
    private JTextField textFieldName;
    private JComboBox<String> comboBoxMainAttribute;
     private JComboBox<String> comboBoxActionType;
     private JComboBox<String> comboBoxActionEffect;
    
  
    private ObjectTableModel<TypeDamage> tableModel;
    private TypeDamage typeDamage;
    /**
     * @return PanelBase Contem todos os componetes da GUI
     * O metodo show resolve as dependencias da classe e retorna a GUI com todos os componetes.
     */
        public JPanel show() {
        AnnotationResolver resolver = new AnnotationResolver(TypeDamage.class);
        tableModel = new ObjectTableModel<>(resolver, "id,name,mainAttribute,actionType,actionEffect");
        setData();
      return getBasePanel();
    }

  

    private JPanel getFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new SpringLayout());
        formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.name")));
        formPanel.add(textFieldName = new JTextField());
        formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.main.attribute")));
        formPanel.add(comboBoxMainAttribute = new JComboBox<String>());
        List<String> list;
        list = dao.getMainAttribute();
        for(int i=0; list.size() >i;i++){
        comboBoxMainAttribute.addItem(list.get(i).toString());
        }
        formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.action.type")));
        formPanel.add(comboBoxActionType = new JComboBox<String>());
        list = dao.getActionType();
        for(int i=0; list.size() >i;i++){
        comboBoxActionType.addItem(list.get(i).toString());
        }
        formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.action.effect")));
        formPanel.add(comboBoxActionEffect = new JComboBox<String>());
        list = dao.getActionEffect();
        for(int i=0; list.size() >i;i++){
        comboBoxActionEffect.addItem(list.get(i).toString());
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
        typeDamage = getTypeDamageSelected();
        }else{
            typeDamage = new TypeDamage();
        }
        typeDamage.setName(textFieldName.getText());
        typeDamage.setMainAttribute((String) comboBoxMainAttribute.getSelectedItem());
        typeDamage.setActionType((String)comboBoxActionType.getSelectedItem());
        typeDamage.setActionEffect((String) comboBoxActionEffect.getSelectedItem());
        
        dao.saveOrUpDate(typeDamage);
        refresh();
  

  }


    public void setFieldEnabled(boolean b) {

    textFieldName.setEnabled(b);
        comboBoxMainAttribute.setEnabled(b);
       comboBoxActionType.setEnabled(b);
       comboBoxActionEffect.setEnabled(b);

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
            deleteListTypeDamage();

        }
    
    }

    private void deleteListTypeDamage() {
        int selecionados[] = table.getSelectedRows();
        if (selecionados.length > 0) {
            List<TypeDamage> listTypeDamage = new ArrayList<>();

            for (int i = 0; i < selecionados.length; i++) {
                selecionados[i] = table.convertRowIndexToModel(selecionados[i]);
                listTypeDamage.add(tableModel.getValue(selecionados[i]));
            }
            for (TypeDamage rTypeDamage : listTypeDamage) {
                dao.remove(rTypeDamage);
                tableModel.remove(rTypeDamage);
            }
            refresh();
            listTypeDamage = null;
            System.gc();
        }
    }

    
    private void setFieldsToNewValues() {
       textFieldName.setText("");
       comboBoxMainAttribute.setSelectedIndex(0);
       comboBoxActionType.setSelectedIndex(0);
       comboBoxActionEffect.setSelectedIndex(0);
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
private TypeDamage getTypeDamageSelected(){
        
        return tableModel.getValue(table.getSelectedRow());
    }
    private void setSelectedValuesToFields() {
        typeDamage = getTypeDamageSelected();
        textFieldName.setText(typeDamage.getName());
        comboBoxMainAttribute.setSelectedItem(typeDamage.getMainAttribute());
       comboBoxActionType.setSelectedItem(typeDamage.getActionType());
       comboBoxActionEffect.setSelectedItem(typeDamage.getActionEffect());
      
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


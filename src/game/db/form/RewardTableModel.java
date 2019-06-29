/**
 * =========================================== <br>
 * PROJETO PARALLAX - Engine de Jogos 2D e RPG <br>
 * Data de alteração do arquivo: 30/10/2012 <br>
 * ===========================================
 * <P>
 * 
 * A classe <b>RewardTableModel</b> (Tabela de Recompensas)
 * manipula as recompensas do jogo.
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
import game.db.dao.RewardDao;
import game.db.entity.Reward;
import game.db.util.LanguageUtil;
import game.db.util.SpringUtilities;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class RewardTableModel implements ActionListener, ListSelectionListener {

    final private RewardDao dao = new RewardDao();
    private JTable table;
   
    private JButton buttonNew ;
    private JButton buttonRefresh ;
    private JButton buttonSave ;
    private JButton buttonDelete;
    

    private ListSelectionModel ListSelectionModel;
    
    private JTextField textFieldName;
    private JTextField textFielDescription;
    private JSpinner spinnerGold;
    private JSpinner spinnerExperience;
    
  
    private ObjectTableModel<Reward> tableModel;
    private Reward reward;
    
    /**
     * @return PanelBase Contem todos os componetes da GUI
     * O metodo show resolve as dependencias da classe e retorna a GUI com todos os componetes.
     */
    
    public JPanel show() {
        AnnotationResolver resolver = new AnnotationResolver(Reward.class);
        tableModel = new ObjectTableModel<>(resolver, "name,description,gold,experience");
        setData();
        return getBasePanel();
    }

  

    private JPanel getFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new SpringLayout());
        formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.name")));
        formPanel.add(textFieldName = new JTextField());
         formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.description")));
        formPanel.add(textFielDescription = new JTextField());
         formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.gold")));
        formPanel.add(spinnerGold = new JSpinner());
         formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.experience")));
        formPanel.add(spinnerExperience = new JSpinner());
       
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
        reward = getRewardSelected();
        }else{
            reward = new Reward();
        }
        reward.setName(textFieldName.getText());
        reward.setDescription(textFielDescription.getText());
        reward.setGold((Integer) spinnerGold.getValue());
        reward.setExperience((Integer) spinnerExperience.getValue());
        dao.saveOrUpDate(reward);
        refresh();
  

  }


    public void setFieldEnabled(boolean b) {

        textFieldName.setEnabled(b);
        textFielDescription.setEnabled(b);
        spinnerGold.setEnabled(b);
        spinnerExperience.setEnabled(b);

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
            deleteListReward();

        }
    
    }

    private void deleteListReward() {
        int selecionados[] = table.getSelectedRows();
        if (selecionados.length > 0) {
            List<Reward> Reward = new ArrayList<>();

            for (int i = 0; i < selecionados.length; i++) {
                selecionados[i] = table.convertRowIndexToModel(selecionados[i]);
                Reward.add(tableModel.getValue(selecionados[i]));
            }
            for (Reward rReward : Reward) {
                dao.remove(rReward);
                tableModel.remove(rReward);
            }
            refresh();
            Reward = null;
            System.gc();
        }
    }

    
    private void setFieldsToNewValues() {
        textFieldName.setText("");
        textFielDescription.setText("");
        spinnerGold.setValue(0);
        spinnerExperience.setValue(0);
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
private Reward getRewardSelected(){
        
        return tableModel.getValue(table.getSelectedRow());
    }
    private void setSelectedValuesToFields() {
        reward = getRewardSelected();
        textFieldName.setText(reward.getName());
        textFielDescription.setText(reward.getDescription());
        spinnerGold.setValue(reward.getGold());
        spinnerExperience.setValue(reward.getExperience());
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

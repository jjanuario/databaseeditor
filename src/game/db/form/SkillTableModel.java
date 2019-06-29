/**
 * =========================================== <br>
 * PROJETO PARALLAX - Engine de Jogos 2D e RPG <br>
 * Data de alteração do arquivo: 30/10/2012 <br>
 * ===========================================
 * <P>
 * 
 * A classe <b>SkillTableModel</b> (Tabela da Habilidades)
 * manipula as habilidades dentro do jogo.
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
import game.db.dao.SkillDao;
import game.db.entity.Skill;
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


public class SkillTableModel implements ActionListener, ListSelectionListener {

    final private SkillDao dao = new SkillDao();
    private JTable table;
    private JButton buttonNew;
    private JButton buttonRefresh;
    private JButton buttonSave;
    private JButton buttonDelete;
    private ListSelectionModel ListSelectionModel;
    private JTextField textFieldName;
    private JTextArea textAreaDescription;
    private JTextArea textAreaResume;
    private JSpinner spinnerActionPoint;
    private JSpinner spinnerPower;
    private JComboBox<String> comboBoxTarget;
    private JComboBox<Integer> comboBoxTypeDamageId;
    private JComboBox<Integer> comboBoxImagemId;
    private JComboBox<Integer> comboBoxAnimationObjectId;
    private JComboBox<Integer> comboBoxClasseId;
    private JCheckBox checkBoxArea;
    private JSpinner spinnerLevelLearning;
    private ObjectTableModel<Skill> tableModel;
    private Skill skill;
    /**
     * @return PanelBase Contem todos os componetes da GUI
     * O metodo show resolve as dependencias da classe e retorna a GUI com todos os componetes.
     */
    
    public JPanel show() {
        AnnotationResolver resolver = new AnnotationResolver(Skill.class);
        tableModel = new ObjectTableModel<>(resolver,"id,name,description,resume,actionPoint,power,target,typeDamageId,"
                + "imagemId,animationObjectId,classeId,area,levelLearning");
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
        textAreaDescription.setPreferredSize(new Dimension(50, 60));
        textAreaDescription.setCaretPosition(textAreaDescription.getText().length());
        formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.resume")));
        formPanel.add(textAreaResume = new JTextArea());
        textAreaResume.setLineWrap(true);
        textAreaResume.setWrapStyleWord(true);
        textAreaResume.setBackground(Color.white);
        textAreaResume.setPreferredSize(new Dimension(50, 60));
        textAreaResume.setCaretPosition(textAreaDescription.getText().length());
        formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.action.point")));
        formPanel.add(spinnerActionPoint = new JSpinner());
        formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.power")));
        formPanel.add(spinnerPower = new JSpinner());
        formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.target")));
        formPanel.add(comboBoxTarget = new JComboBox<String>());
        List<String> listS;
        listS = dao.getTarget();
        for (int i = 0; listS.size() > i; i++) {
            comboBoxTarget.addItem(listS.get(i).toString());
        }
        formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.type.damage")));
        formPanel.add(comboBoxTypeDamageId = new JComboBox<Integer>());
        List<Integer> list = dao.getTypeDamageId();
        for (int i = 0; list.size() > i; i++) {
            comboBoxTypeDamageId.addItem(list.get(i));
        }
        formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.target.image")));
        formPanel.add(comboBoxImagemId = new JComboBox<Integer>());
        list = dao.getImagemId();
        for (int i = 0; list.size() > i; i++) {
            comboBoxImagemId.addItem(list.get(i));
        }
        formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.animation.object")));
        formPanel.add(comboBoxAnimationObjectId = new JComboBox<Integer>());
        list = dao.getAnimationObjectId();
        for (int i = 0; list.size() > i; i++) {
            comboBoxAnimationObjectId.addItem(list.get(i));
        }
        formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.class")));
        formPanel.add(comboBoxClasseId = new JComboBox<Integer>());
        list = dao.getClasseId();
        for (int i = 0; list.size() > i; i++) {
            comboBoxClasseId.addItem(list.get(i));
        }
        formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.area")));
        formPanel.add(checkBoxArea = new JCheckBox());

        formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.level.learning")));
        formPanel.add(spinnerLevelLearning = new JSpinner());



        SpringUtilities.makeCompactGrid(formPanel,
                formPanel.getComponentCount() / 2, 2, //rows, cols
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

    private void saveOrUpDate() {
        boolean isUpdate = table.getSelectedRow() != -1;

        if (isUpdate) {
            skill = getSkillSelected();
        } else {
            skill = new Skill();
        }
        skill.setName(textFieldName.getText());
        skill.setDescription(textAreaDescription.getText());
        skill.setResume(textAreaResume.getText());
        skill.setActionPoint((Integer) spinnerActionPoint.getValue());
        skill.setPower((Integer) spinnerPower.getValue());
        skill.setTarget((String) comboBoxTarget.getSelectedItem());
        skill.setTypeDamageId((Integer) comboBoxTypeDamageId.getSelectedItem());
        skill.setImagemId((Integer) comboBoxImagemId.getSelectedItem());
        skill.setAnimationObjectId((Integer) comboBoxAnimationObjectId.getSelectedItem());
        skill.setClasseId((Integer) comboBoxClasseId.getSelectedItem());
        if (checkBoxArea.isSelected()) {
            skill.setArea(1);
        } else {
            skill.setArea(0);
        }
        skill.setLevelLearning((Integer) spinnerLevelLearning.getValue());



        dao.saveOrUpDate(skill);
        refresh();


    }

    public void setFieldEnabled(boolean b) {

        textFieldName.setEnabled(b);
        textAreaDescription.setEnabled(b);
        textAreaResume.setEnabled(b);
        spinnerActionPoint.setEnabled(b);
        spinnerPower.setEnabled(b);
        comboBoxTarget.setEnabled(b);
        comboBoxTypeDamageId.setEnabled(b);
        comboBoxImagemId.setEnabled(b);
        comboBoxAnimationObjectId.setEnabled(b);
        comboBoxClasseId.setEnabled(b);
        spinnerLevelLearning.setEnabled(b);

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
            deleteListSkill();

        }

    }

    private void deleteListSkill() {
        int selecionados[] = table.getSelectedRows();
        if (selecionados.length > 0) {
            List<Skill> listSkill = new ArrayList<>();

            for (int i = 0; i < selecionados.length; i++) {
                selecionados[i] = table.convertRowIndexToModel(selecionados[i]);
                listSkill.add(tableModel.getValue(selecionados[i]));
            }
            for (Skill rSkill : listSkill) {
                dao.remove(rSkill);
                tableModel.remove(rSkill);
            }
            refresh();
            listSkill = null;
            System.gc();
        }
    }

    private void setFieldsToNewValues() {
        textFieldName.setText("");
        textAreaDescription.setText("");
        textAreaResume.setText("");
        spinnerActionPoint.setValue(0);
        spinnerActionPoint.setPreferredSize(new Dimension(2, 5));
        spinnerPower.setValue(0);
        comboBoxTarget.setSelectedIndex(0);
        comboBoxTypeDamageId.setSelectedIndex(0);
        comboBoxImagemId.setSelectedIndex(0);
        comboBoxAnimationObjectId.setSelectedIndex(0);;
        comboBoxClasseId.setSelectedIndex(0);
        spinnerLevelLearning.setValue(0);
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

    private Skill getSkillSelected() {

        return tableModel.getValue(table.getSelectedRow());
    }

    private void setSelectedValuesToFields() {
        skill = getSkillSelected();
        
        textFieldName.setText(skill.getName());
        textAreaDescription.setText(skill.getDescription());
        textAreaResume.setText(skill.getResume());
        spinnerActionPoint.setValue(skill.getActionPoint());
        spinnerPower.setValue(skill.getPower());
        comboBoxTarget.setSelectedItem(skill.getTarget());
        comboBoxTypeDamageId.setSelectedItem(skill.getTypeDamageId());
        comboBoxImagemId.setSelectedItem(skill.getImagemId());
        comboBoxAnimationObjectId.setSelectedItem(skill.getAnimationObjectId());
        comboBoxClasseId.setSelectedItem(skill.getClasseId());
        checkBoxArea.setSelected(skill.getArea() == 1);
        spinnerLevelLearning.setValue(skill.getLevelLearning());


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

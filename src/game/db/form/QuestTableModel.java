/**
 * =========================================== <br>
 * PROJETO PARALLAX - Engine de Jogos 2D e RPG <br>
 * Data de alteração do arquivo: 30/10/2012 <br>
 * ===========================================
 * <P>
 * 
 * A classe <b>QuestTableModel</b> (Tabela das Missoes)
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
package game.db.form;


import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import game.db.dao.QuestDao;
import game.db.entity.Quest;
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

public class QuestTableModel implements ActionListener, ListSelectionListener {

    final private QuestDao dao = new QuestDao();
    private JTable table;
    private JButton buttonNew;
    private JButton buttonRefresh;
    private JButton buttonSave;
    private JButton buttonDelete;
    private ListSelectionModel ListSelectionModel;
    private JTextField textFieldName;
    private JTextArea textAreaDescription;
    private JSpinner spinnerCharacterLevel;
    private JCheckBox checkBoxOnlyQuestActivate;
    private JCheckBox checkBoxRepeatable;
    private JComboBox<Integer> comboBoxNpcId;
    private ObjectTableModel<Quest> tableModel;
    private Quest quest;
    /**
     * @return PanelBase Contem todos os componetes da GUI
     * O metodo show resolve as dependencias da classe e retorna a GUI com todos os componetes.
     */
      public JPanel show() {
        AnnotationResolver resolver = new AnnotationResolver(Quest.class);
        tableModel = new ObjectTableModel<>(resolver, "name,description,characterLevel,onlyQuestActivate,repeatable,npcId");
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
        formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.character.level")));
        formPanel.add(spinnerCharacterLevel = new JSpinner());
        formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.onlyquestactivate")));
        formPanel.add(checkBoxOnlyQuestActivate = new JCheckBox());
        formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.repeatable")));
        formPanel.add(checkBoxRepeatable = new JCheckBox());
        formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.npc")));
        formPanel.add(comboBoxNpcId = new JComboBox<Integer>());
        List<Integer> list = dao.getNpcId();
        for (int i = 0; list.size() > i; i++) {
            comboBoxNpcId.addItem(list.get(i));
        }

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
            quest = getQuestSelected();
        } else {
            quest = new Quest();
        }
        quest.setName(textFieldName.getText());
        quest.setDescription(textAreaDescription.getText());
        quest.setCharacterLevel((Integer) spinnerCharacterLevel.getValue());
        if (checkBoxOnlyQuestActivate.isSelected()) {
            quest.setOnlyquestactivate(1);
        } else {
            quest.setOnlyquestactivate(0);
        }
        if (checkBoxRepeatable.isSelected()) {
            quest.setRepeatable(1);
        } else {
            quest.setRepeatable(0);
        }
        quest.setNpcid((Integer) comboBoxNpcId.getSelectedItem());
        dao.saveOrUpDate(quest);
        refresh();


    }

    public void setFieldEnabled(boolean b) {

        textFieldName.setEnabled(b);
        textAreaDescription.setEnabled(b);
        spinnerCharacterLevel.setEnabled(b);
        checkBoxOnlyQuestActivate.setEnabled(b);
        checkBoxRepeatable.setEnabled(b);
        comboBoxNpcId.setEnabled(b);

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
            deleteListQuest();

        }

    }

    private void deleteListQuest() {
        int selecionados[] = table.getSelectedRows();
        if (selecionados.length > 0) {
            List<Quest> Quest = new ArrayList<>();

            for (int i = 0; i < selecionados.length; i++) {
                selecionados[i] = table.convertRowIndexToModel(selecionados[i]);
                Quest.add(tableModel.getValue(selecionados[i]));
            }
            for (Quest rQuest : Quest) {
                dao.remove(rQuest);
                tableModel.remove(rQuest);
            }
            refresh();
            Quest = null;
            System.gc();
        }
    }

    private void setFieldsToNewValues() {
        textFieldName.setText("");
        textAreaDescription.setText("");
        spinnerCharacterLevel.setValue(0);
        checkBoxOnlyQuestActivate.setSelected(false);
        checkBoxRepeatable.setSelected(false);
        comboBoxNpcId.setSelectedIndex(0);
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

    private Quest getQuestSelected() {

        return tableModel.getValue(table.getSelectedRow());
    }

    private void setSelectedValuesToFields() {
        quest = getQuestSelected();
        textFieldName.setText(quest.getName());
        textAreaDescription.setText(quest.getDescription());
        spinnerCharacterLevel.setValue(quest.getCharacterLevel());
        checkBoxOnlyQuestActivate.setSelected(quest.getOnlyquestactivate() == 1);
        checkBoxRepeatable.setSelected(quest.getRepeatable() == 1);
        comboBoxNpcId.setSelectedItem(quest.getNpcid());
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

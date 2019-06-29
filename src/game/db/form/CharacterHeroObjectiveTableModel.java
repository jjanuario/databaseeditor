package game.db.form;

/**
 * =========================================== <br>
 * PROJETO PARALLAX - Data Base Editor<br>
 * Data de alteração do arquivo: 30/10/2012 <br>
 * ===========================================
 * <P>
 * 
 * A classe <b>CharacterHeroObjectiveTableModel</b> (Tabela de Cadastro dos Objetivos) manipula os
 *objetivos do jogo.
 * <p>
 * 
 * @see <a
 *      href="http://www.einformacao.com.br/parallax/component/content/article/35-conteudo-site/86-documentos-para-leitura">Documentos
 *      para Leitura</a>
 * @author <a href="mailto:jeferson.januario@gmail.com">Jeferson Januario</a>
 * @version 1.0.1
 * @since Existe desde a versão 1.0.1 do projeto
 */
import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import game.db.dao.CharacterHeroObjectiveDao;
import game.db.entity.CharacterHeroObjective;
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

public class CharacterHeroObjectiveTableModel implements ActionListener, ListSelectionListener {

    public CharacterHeroObjectiveTableModel() {
        super();
    }
    final private CharacterHeroObjectiveDao dao = new CharacterHeroObjectiveDao();
    private JTable table;
    private JButton buttonNew;
    private JButton buttonRefresh;
    private JButton buttonSave;
    private JButton buttonDelete;
    private JTextField textFieldName;
    private JComboBox<String> comboBoxObjectiveId;
    private JComboBox<String> comboBoxCharacterHeroId;
    private ListSelectionModel ListSelectionModel;
    private JSpinner spinnerCheckClosed;
    private JSpinner spinnerAmount;
    private ObjectTableModel<CharacterHeroObjective> tableModel;
    private CharacterHeroObjective characterHeroObjective;
    /**
     * @return PanelBase Contem todos os componetes da GUI
     * O metodo show resolve as dependencias da classe e retorna a GUI com todos os componetes.
     */
    public JPanel show() {
        AnnotationResolver resolver = new AnnotationResolver(CharacterHeroObjective.class);
        tableModel = new ObjectTableModel<>(resolver, "id,name,checkClosed,amount,objectiveId,characterHeroId");
        setData();


        return getBasePanel();
    }

    private JPanel getFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new SpringLayout());
        formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.name")));
        formPanel.add(textFieldName = new JTextField());
        formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.amount")));
        formPanel.add(spinnerAmount = new JSpinner());
        formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.check.closed")));
        formPanel.add(spinnerCheckClosed = new JSpinner());

        formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.objective.id")));
        formPanel.add(comboBoxObjectiveId = new JComboBox<>());
        formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.characterhero.id")));
        formPanel.add(comboBoxCharacterHeroId = new JComboBox<>());
        List<Integer> combo;
        combo = dao.getObjectiveId();
        for (int i = 0; combo.size() > i; i++) {
            comboBoxObjectiveId.addItem(combo.get(i).toString());
        }
        combo = dao.getCharacterHeroId();
        for (int i = 0; combo.size() > i; i++) {
            comboBoxCharacterHeroId.addItem(combo.get(i).toString());
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
        scrollPaneTable.setPreferredSize(new Dimension(500, 250));
        scrollPaneTable.setViewportView(table);
        ListSelectionModel = table.getSelectionModel();
        ListSelectionModel.addListSelectionListener(this);

        setButtonEnabled(false);
        return basePanel;
    }

    private void saveOrUpDate() {
        boolean isUpdate = table.getSelectedRow() != -1;

        if (isUpdate) {
            characterHeroObjective = getCharacterHeroObjectiveSelected();
        } else {
            characterHeroObjective = new CharacterHeroObjective();
        }
        characterHeroObjective.setCheckClosed((Integer) spinnerCheckClosed.getValue());
        characterHeroObjective.setAmount((Integer) spinnerAmount.getValue());
        characterHeroObjective.setName(textFieldName.getText());
        characterHeroObjective.setObjectiveId((Integer) comboBoxObjectiveId.getSelectedItem());
        characterHeroObjective.setCharacterHeroId((Integer) comboBoxCharacterHeroId.getSelectedItem());
        dao.saveOrUpDate(characterHeroObjective);



    }

    public void setFieldEnabled(boolean b) {

        textFieldName.setEnabled(b);
        comboBoxObjectiveId.setEnabled(b);
        comboBoxCharacterHeroId.setEnabled(b);
        spinnerCheckClosed.setEnabled(b);
        spinnerAmount.setEnabled(b);

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
            deleteListCharacterHeroObjective();

        }

    }

    private void deleteListCharacterHeroObjective() {
        int selecionados[] = table.getSelectedRows();
        if (selecionados.length > 0) {
            List<CharacterHeroObjective> ao = new ArrayList<>();

            for (int i = 0; i < selecionados.length; i++) {
                selecionados[i] = table.convertRowIndexToModel(selecionados[i]);
                ao.add(tableModel.getValue(selecionados[i]));
            }
            for (CharacterHeroObjective rao : ao) {
                dao.remove(rao);
                tableModel.remove(rao);
            }
            refresh();
            ao = null;
            System.gc();
        }
    }

    private void setFieldsToNewValues() {
        textFieldName.setText("");
        spinnerCheckClosed.setValue(0);
        spinnerAmount.setValue(0);
        comboBoxObjectiveId.setSelectedIndex(0);
        comboBoxCharacterHeroId.setSelectedIndex(0);
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

    private CharacterHeroObjective getCharacterHeroObjectiveSelected() {

        return tableModel.getValue(table.getSelectedRow());
    }

    private void setSelectedValuesToFields() {
        characterHeroObjective = getCharacterHeroObjectiveSelected();
        textFieldName.setText(characterHeroObjective.getName());
        spinnerCheckClosed.setValue(characterHeroObjective.getCheckClosed());
        spinnerAmount.setValue(characterHeroObjective.getAmount());
        comboBoxCharacterHeroId.setSelectedItem(characterHeroObjective.getCharacterHeroId());
        comboBoxObjectiveId.setSelectedItem(characterHeroObjective.getObjectiveId());
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

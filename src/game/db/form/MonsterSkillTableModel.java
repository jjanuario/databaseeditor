/**
 * =========================================== <br>
 * PROJETO PARALLAX - Data Base Editor<br>
 * Data de alteração do arquivo: 30/10/2012 <br>
 * ===========================================
 * <P>
 * 
 * A classe <b>MonsterSkillTableModel</b> (Tabela de habilidades dos monstros) manipula as
 *habilidades dos monstros.
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

import game.db.dao.MonsterSkillDao;
import game.db.entity.MonsterSkill;
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
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;


public class MonsterSkillTableModel implements ListSelectionListener, ActionListener {

    final private MonsterSkillDao dao = new MonsterSkillDao();
    private ObjectTableModel<MonsterSkill> tableModel;
    private JTable table;
    private JComboBox<Integer> comboBoxMonsterId;
    private JComboBox<Integer> comboBoxSkillId;
    private JButton buttonNew;
    private JButton buttonRefresh;
    private JButton buttonDelete;
    private JButton buttonSave;
    private ListSelectionModel ListSelectionModel;
    private MonsterSkill monsterSkill;
    /**
     * @return PanelBase Contem todos os componetes da GUI
     * O metodo show resolve as dependencias da classe e retorna a GUI com todos os componetes.
     */
    public JPanel show() {
        AnnotationResolver resolver = new AnnotationResolver(MonsterSkill.class);
        tableModel = new ObjectTableModel<>(resolver, "monsterId,skillId");
        setData();

        return getPanelBase();
    }

    private JPanel getPanelForm() {
        JPanel panelForm = new JPanel();
        panelForm.setLayout(new SpringLayout());

        List<Integer> list;

        panelForm.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.monster.id")));
        panelForm.add(comboBoxMonsterId = new JComboBox<Integer>(), BorderLayout.LINE_START);
        list = dao.getMonsterId();
        for (int i = 0; list.size() > i; i++) {
            comboBoxMonsterId.addItem((Integer) list.get(i));
        }
        panelForm.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.skill.id")));
        panelForm.add(comboBoxSkillId = new JComboBox<Integer>(), BorderLayout.LINE_START);
        list = dao.getSkillId();
        for (int i = 0; list.size() > i; i++) {
            comboBoxSkillId.addItem(list.get(i));
        }

        SpringUtilities.makeCompactGrid(panelForm,
                panelForm.getComponentCount() / 2, 2, //rows, cols
                6, 6, //initX, initY
                6, 6);       //xPad, yPad
        setFieldsEnabled(false);
        return panelForm;
    }

    private JPanel getPanelBase() {
        JPanel panelBase = new JPanel();
        JScrollPane scrollPanelTable = new JScrollPane(table = new JTable(tableModel));
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

        comboBoxMonsterId.setEnabled(b);
        comboBoxSkillId.setEnabled(b);
    }

    private void setButtonsEnabled(boolean b) {
        buttonDelete.setEnabled(b);
        buttonSave.setEnabled(b);
        buttonRefresh.setEnabled(b);
        buttonNew.setEnabled(!b);

    }

    private void refresh() {
        setData();
        setFieldsEnabled(false);
        setButtonsEnabled(false);
        setFieldsToNewValues();
    }

    private void setValuesChanged() {
        monsterSkill = getMonsterSkillSelected();
        comboBoxMonsterId.setSelectedItem(monsterSkill.getMonsterId());
        comboBoxMonsterId.setSelectedItem(monsterSkill.getSkillId());
    }

    private void setFieldsToNewValues() {

        comboBoxMonsterId.setSelectedIndex(0);
        comboBoxSkillId.setSelectedIndex(0);
    }

    private void setData() {
        tableModel.clear();
        tableModel.setData(dao.lista());
    }

    private MonsterSkill getMonsterSkillSelected() {

        return tableModel.getValue(table.getSelectedRow());
    }

    private void saveOrUpDate() {
        boolean isUpdate = table.getSelectedRow() != -1;

        if (isUpdate) {
            monsterSkill = getMonsterSkillSelected();
        } else {
            monsterSkill = new MonsterSkill();
        }
        monsterSkill.setMonsterId((Integer) comboBoxMonsterId.getSelectedItem());
        monsterSkill.setSkillId((Integer) comboBoxSkillId.getSelectedItem());

        dao.saveOrUpDate(monsterSkill);
        refresh();

    }

    private void deleteListImagem() {
        int selecionados[] = table.getSelectedRows();
        if (selecionados.length > 0) {
            List<MonsterSkill> ao = new ArrayList<>();

            for (int i = 0; i < selecionados.length; i++) {
                selecionados[i] = table.convertRowIndexToModel(selecionados[i]);
                ao.add(tableModel.getValue(selecionados[i]));
            }
            for (MonsterSkill rao : ao) {
                dao.remove(rao);
                tableModel.remove(rao);
            }
            refresh();
            ao = null;
            System.gc();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        boolean isAdjusting = table.getSelectedRow() != -1;
        if (isAdjusting) {
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
            comboBoxMonsterId.requestFocus();
        }
        if (e.getSource() == buttonRefresh) {
            refresh();
        }
        if (e.getSource() == buttonSave) {
            saveOrUpDate();

        }
        if (e.getSource() == buttonDelete) {
            deleteListImagem();
        }
    }
}

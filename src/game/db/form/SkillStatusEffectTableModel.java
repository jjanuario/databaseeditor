/**
 * =========================================== <br>
 * PROJETO PARALLAX - Engine de Jogos 2D e RPG <br>
 * Data de alteração do arquivo: 30/10/2012 <br>
 * ===========================================
 * <P>
 * 
 * A classe <b>SkillStatusEffectTableModel</b> (SkillStatusEffectTableModel)
 * apenas utilizado pela engine.
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
import game.db.dao.SkillStatusEffectDao;
import game.db.entity.SkillStatusEffect;
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

public class SkillStatusEffectTableModel implements ActionListener, ListSelectionListener {

    final private SkillStatusEffectDao dao = new SkillStatusEffectDao();
    private JTable table;
    private JButton buttonNew;
    private JButton buttonRefresh;
    private JButton buttonSave;
    private JButton buttonDelete;
    private ListSelectionModel ListSelectionModel;
    private JComboBox<Integer> comboBoxSkillId;
    private JComboBox<Integer> comboBoxStatusEffectId;
    private ObjectTableModel<SkillStatusEffect> tableModel;
    private SkillStatusEffect skillStatusEffect;

    /**
     * @return PanelBase Contem todos os componetes da GUI
     * O metodo show resolve as dependencias da classe e retorna a GUI com todos os componetes.
     */
    
    public JPanel show() {
        AnnotationResolver resolver = new AnnotationResolver(SkillStatusEffect.class);
        tableModel = new ObjectTableModel<>(resolver, "skillId,statusEffectId");
        setData();

        return getBasePanel();
    }

    private JPanel getFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new SpringLayout());

        List<Integer> list;

        formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.skill.id")));
        formPanel.add(comboBoxSkillId = new JComboBox<Integer>());
        list = dao.getSkillId();
        for (int i = 0; list.size() > i; i++) {
            comboBoxSkillId.addItem(list.get(i));
        }
        formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.status.effect.id")));
        formPanel.add(comboBoxStatusEffectId = new JComboBox<Integer>());
        list = dao.getStatusEffectId();
        for (int i = 0; list.size() > i; i++) {
            comboBoxStatusEffectId.addItem(list.get(i));
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
            skillStatusEffect = getSkillStatusEffectIdSelected();
        } else {
            skillStatusEffect = new SkillStatusEffect();
        }
        skillStatusEffect.setSkillId((Integer) comboBoxSkillId.getSelectedItem());
        skillStatusEffect.setStatusEffectId((Integer) comboBoxStatusEffectId.getSelectedItem());


        dao.saveOrUpDate(skillStatusEffect);
        refresh();


    }

    public void setFieldEnabled(boolean b) {

        comboBoxSkillId.setEnabled(b);
        comboBoxStatusEffectId.setEnabled(b);

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
            deleteListSkillStatusEffect();

        }

    }

    private void deleteListSkillStatusEffect() {
        int selecionados[] = table.getSelectedRows();
        if (selecionados.length > 0) {
            List<SkillStatusEffect> listSkillStatusEffectIds = new ArrayList<>();

            for (int i = 0; i < selecionados.length; i++) {
                selecionados[i] = table.convertRowIndexToModel(selecionados[i]);
                listSkillStatusEffectIds.add(tableModel.getValue(selecionados[i]));
            }
            for (SkillStatusEffect rSkillStatusEffectId : listSkillStatusEffectIds) {
                dao.remove(rSkillStatusEffectId);
                tableModel.remove(rSkillStatusEffectId);
            }
            refresh();
            listSkillStatusEffectIds = null;
            System.gc();
        }
    }

    private void setFieldsToNewValues() {

        comboBoxSkillId.setSelectedIndex(0);
        comboBoxStatusEffectId.setSelectedIndex(0);
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

    private SkillStatusEffect getSkillStatusEffectIdSelected() {

        return tableModel.getValue(table.getSelectedRow());
    }

    private void setSelectedValuesToFields() {
        skillStatusEffect = getSkillStatusEffectIdSelected();

        comboBoxSkillId.setSelectedItem(skillStatusEffect.getSkillid());
        comboBoxStatusEffectId.setSelectedItem(skillStatusEffect.getStatusEffectId());

    }

    private void newRegister() {
        buttonNew.setEnabled(false);
        buttonDelete.setEnabled(false);
        buttonSave.setEnabled(true);
        setFieldEnabled(true);
        comboBoxSkillId.requestFocus();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        boolean isAdjusting = table.getSelectedRow() != -1;

        if (isAdjusting) {
            setSelectedValuesToFields();
            setFieldEnabled(isAdjusting);
            setButtonEnabled(isAdjusting);
            comboBoxSkillId.requestFocus();

        }
    }

    private void setButtonEnabled(boolean b) {
        buttonDelete.setEnabled(b);
        buttonSave.setEnabled(b);
        buttonNew.setEnabled(!b);
        buttonRefresh.setEnabled(b);

    }
}

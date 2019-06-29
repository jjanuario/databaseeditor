package game.db.form;

/**
 * =========================================== <br>
 * PROJETO PARALLAX - Engine de Jogos 2D e RPG <br>
 * Data de alteração do arquivo: 30/10/2012 <br>
 * ===========================================
 * <P>
 * 
 * A classe <b>CharacterHeroSkillTableModel</b> (Tabela de Habilidade dos Personagem)
 * manipula as habilidades (skills) dos personagens.
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
import game.db.dao.CharacterHeroSkillDao;
import game.db.entity.CharacterHeroSkill;
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


public class CharacterHeroSkillTableModel implements ActionListener, ListSelectionListener {

    final private CharacterHeroSkillDao dao = new CharacterHeroSkillDao();
    private JTable table;
    private JButton buttonNew;
    private JButton buttonRefresh;
    private JButton buttonSave;
    private JButton buttonDelete;
    private JComboBox<String> comboxSkillId;
    private JComboBox<String> comboBoxCharacterHeroId = new JComboBox<>();
    private ListSelectionModel ListSelectionModel;
    private ObjectTableModel<CharacterHeroSkill> tableModel;
    private CharacterHeroSkill CharacterHeroSkill;
    /**
     * @return PanelBase Contem todos os componetes da GUI
     * O metodo show resolve as dependencias da classe e retorna a GUI com todos os componetes.
     */
    public JPanel show() {
        AnnotationResolver resolver = new AnnotationResolver(CharacterHeroSkill.class);
        tableModel = new ObjectTableModel<>(resolver, "characterHeroId,skillId");
        setData();
return getBasePanel();

    }

    private JPanel getFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new SpringLayout());
        formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.skill.id")));
        formPanel.add(comboxSkillId = new JComboBox<>());
        formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.characterhero.id")));
        formPanel.add(comboBoxCharacterHeroId = new JComboBox<>());
        List<Integer> combo;
        combo = dao.getCharacterHeroId();
        for (int i = 0; combo.size() > i; i++) {
            comboxSkillId.addItem(combo.get(i).toString());
        }
        combo = dao.getSkillId();
        for (int i = 0; combo.size() > i; i++) {
            comboBoxCharacterHeroId.addItem(combo.get(i).toString());
        }
        setFieldEnabled(false);
        SpringUtilities.makeCompactGrid(formPanel,
                formPanel.getComponentCount() / 2, 2, //rows, cols
                0, 0, //initX, initY
                0, 0);       //xPad, yPad

        return formPanel;
    }

    private JPanel getBasePanel() {
        JPanel basePanel = new JPanel();
        JScrollPane scrollPaneTable = new JScrollPane(table = new JTable(tableModel));
        ListSelectionModel = table.getSelectionModel();
        ListSelectionModel.addListSelectionListener(this);
        basePanel.setLayout(new BorderLayout());
        basePanel.add(scrollPaneTable, BorderLayout.PAGE_START);
        basePanel.add(getFormPanel(), BorderLayout.CENTER);

        basePanel.add(getPanelButton(), BorderLayout.PAGE_END);
        scrollPaneTable.setViewportView(table);
        scrollPaneTable.setPreferredSize(new Dimension(500, 250));
        scrollPaneTable.setViewportView(table);

        return basePanel;
    }

    private void saveOrUpDate() {
        boolean isUpdate = table.getSelectedRow() != -1;

        if (isUpdate) {
            CharacterHeroSkill = getCharacterHeroSkillSelected();
        } else {
            CharacterHeroSkill = new CharacterHeroSkill();
        }

        CharacterHeroSkill.setSkillId((Integer) comboxSkillId.getSelectedItem());
        CharacterHeroSkill.setCharacterHeroId((Integer) comboBoxCharacterHeroId.getSelectedItem());
        dao.saveOrUpDate(CharacterHeroSkill);


    }

    public void setFieldEnabled(boolean b) {

        comboxSkillId.setEnabled(b);
        comboBoxCharacterHeroId.setEnabled(b);

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
            deleteListCharacterHeroSkill();

        }

    }

    private void deleteListCharacterHeroSkill() {
        int selecionados[] = table.getSelectedRows();
        if (selecionados.length > 0) {
            List<CharacterHeroSkill> ao = new ArrayList<>();

            for (int i = 0; i < selecionados.length; i++) {
                selecionados[i] = table.convertRowIndexToModel(selecionados[i]);
                ao.add(tableModel.getValue(selecionados[i]));
            }
            for (CharacterHeroSkill rao : ao) {
                dao.remove(rao);
                tableModel.remove(rao);
            }
            refresh();
            ao = null;
            System.gc();
        }
    }

    private void setFieldsToNewValues() {
        comboxSkillId.setSelectedIndex(0);
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

    private CharacterHeroSkill getCharacterHeroSkillSelected() {

        return tableModel.getValue(table.getSelectedRow());
    }

    private void setSelectedValuesToFields() {
        CharacterHeroSkill = getCharacterHeroSkillSelected();
        comboBoxCharacterHeroId.setSelectedItem(CharacterHeroSkill.getCharacterHeroId());
        comboxSkillId.setSelectedItem(CharacterHeroSkill.getSkillId());
    }

    private void newRegister() {
        buttonNew.setEnabled(false);
        buttonDelete.setEnabled(false);
        buttonSave.setEnabled(true);
        setFieldEnabled(true);
        comboBoxCharacterHeroId.requestFocus();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        boolean isAdjusting = table.getSelectedRow() != -1;

        if (isAdjusting) {
            setSelectedValuesToFields();
            setFieldEnabled(isAdjusting);
            setButtonEnabled(isAdjusting);
            comboBoxCharacterHeroId.requestFocus();

        }
    }

    private void setButtonEnabled(boolean b) {
        buttonDelete.setEnabled(b);
        buttonSave.setEnabled(b);
        buttonNew.setEnabled(!b);
        buttonRefresh.setEnabled(b);

    }

}

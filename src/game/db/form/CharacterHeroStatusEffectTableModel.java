package game.db.form;
import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import game.db.dao.CharacterHeroStatusEffectDao;
import game.db.entity.CharacterHeroStatusEffect;
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
/**
 * =========================================== <br>
 * PROJETO PARALLAX - Engine de Jogos 2D e RPG <br>
 * Data de alteração do arquivo: 30/10/2012 <br>
 * ===========================================
 * <P>
 * 
 * A classe <b>CharacterHeroStatusEffectTableModel</b> (Status e Efeito do Personagem) é a classe responsavel em gerenciar quais efeitos o personagem 
 * esta sofrendo, a lista completa de tipos se encontra aqui {@link #getTypeStatus() getType}, lambrando que pode ser 
 * tanto um efeito negativo como positivo, por exemplo, poderia ser regeneração de HP ou degeneração de HP.<p>
 * @see <a
 *      href="http://www.einformacao.com.br/parallax/component/content/article/35-conteudo-site/86-documentos-para-leitura">Documentos
 *      para Leitura</a>
 * @author <a href="mailto:jeferson.januario@gmail.com">Jeferson Januario</a>
 * @version 1.0.1
 * @since Existe desde a versão 1.0.1 do projeto
 */
public class CharacterHeroStatusEffectTableModel implements ActionListener, ListSelectionListener {

    final private CharacterHeroStatusEffectDao dao = new CharacterHeroStatusEffectDao();
    private JTable table;
    private JButton buttonNew;
    private JButton buttonRefresh;
    private JButton buttonSave;
    private JButton buttonDelete;
    private ListSelectionModel ListSelectionModel;
    private JComboBox<Integer> comboBoxCharacterHeroId;
    private JComboBox<Integer> comboBoxStatusEffectId;
    private ObjectTableModel<CharacterHeroStatusEffect> tableModel;
    private CharacterHeroStatusEffect characterHeroStatusEffect;
    /**
     * @return PanelBase Contem todos os componetes da GUI
     * O metodo show resolve as dependencias da classe e retorna a GUI com todos os componetes.
     */
    public JPanel show() {
        AnnotationResolver resolver = new AnnotationResolver(CharacterHeroStatusEffect.class);
        tableModel = new ObjectTableModel<>(resolver, "characterHeroId,statusEffectId");
        setData();
        return getBasePanel();

    }

    private JPanel getFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new SpringLayout());

        List<Integer> list;

        formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.characterhero.id")));
        formPanel.add(comboBoxCharacterHeroId = new JComboBox<Integer>());
        list = dao.getCharacterHeroId();
        for (int i = 0; list.size() > i; i++) {
            comboBoxCharacterHeroId.addItem(list.get(i));
        }
        formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.status.effect.id")));
        formPanel.add(comboBoxStatusEffectId = new JComboBox<Integer>());
        list = dao.getStatusEffectId();
        for (int i = 0; list.size() > i; i++) {
            comboBoxStatusEffectId.addItem(list.get(i));
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
            characterHeroStatusEffect = getCharacterHeroStatusEffectSelected();
        } else {
            characterHeroStatusEffect = new CharacterHeroStatusEffect();
        }
        characterHeroStatusEffect.setCharacterHeroId((Integer) comboBoxCharacterHeroId.getSelectedItem());
        characterHeroStatusEffect.setStatusEffectId((Integer) comboBoxStatusEffectId.getSelectedItem());


        dao.saveOrUpDate(characterHeroStatusEffect);
        refresh();


    }

    public void setFieldEnabled(boolean b) {

        comboBoxCharacterHeroId.setEnabled(b);
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
            deleteListCharacterHeroStatusEffect();

        }

    }

    private void deleteListCharacterHeroStatusEffect() {
        int selecionados[] = table.getSelectedRows();
        if (selecionados.length > 0) {
            List<CharacterHeroStatusEffect> listCharacterHeroStatusEffectIds = new ArrayList<>();

            for (int i = 0; i < selecionados.length; i++) {
                selecionados[i] = table.convertRowIndexToModel(selecionados[i]);
                listCharacterHeroStatusEffectIds.add(tableModel.getValue(selecionados[i]));
            }
            for (CharacterHeroStatusEffect rCharacterHeroStatusEffectId : listCharacterHeroStatusEffectIds) {
                dao.remove(rCharacterHeroStatusEffectId);
                tableModel.remove(rCharacterHeroStatusEffectId);
            }
            refresh();
            listCharacterHeroStatusEffectIds = null;
            System.gc();
        }
    }

    private void setFieldsToNewValues() {

        comboBoxCharacterHeroId.setSelectedIndex(0);
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

    private CharacterHeroStatusEffect getCharacterHeroStatusEffectSelected() {

        return tableModel.getValue(table.getSelectedRow());
    }

    private void setSelectedValuesToFields() {
        characterHeroStatusEffect = getCharacterHeroStatusEffectSelected();

        comboBoxCharacterHeroId.setSelectedItem(characterHeroStatusEffect.getCharacterHeroId());
        comboBoxStatusEffectId.setSelectedItem(characterHeroStatusEffect.getStatusEffectId());

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

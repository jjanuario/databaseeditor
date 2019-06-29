/**
 * =========================================== <br>
 * PROJETO PARALLAX - Engine de Jogos 2D e RPG <br>
 * Data de alteração do arquivo: 30/10/2012 <br>
 * ===========================================
 * <P>
 * 
 * A classe <b>AnimationObjectTableModel</b> (Tabela dos Objetos Animados)
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
import game.db.dao.NpcDao;
import game.db.entity.Npc;
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


public class NpcTableModel implements ActionListener, ListSelectionListener {

    final private NpcDao dao = new NpcDao();
    private JTable table;
    private JButton buttonNew;
    private JButton buttonRefresh;
    private JButton buttonSave;
    private JButton buttonDelete;
    private ListSelectionModel ListSelectionModel;
    private JComboBox<Integer> comboBoxAnimationObjectMovimentId;
    private JComboBox<Integer> comboBoxImagemFaceId;
    private JTextField textFieldName;
    private ObjectTableModel<Npc> tableModel;
    private Npc npc;
    /**
     * @return PanelBase Contem todos os componetes da GUI
     * O metodo show resolve as dependencias da classe e retorna a GUI com todos os componetes.
     */
    public JPanel show() {
        AnnotationResolver resolver = new AnnotationResolver(Npc.class);
        tableModel = new ObjectTableModel<>(resolver, "name,imagemFaceId,animationObjectMovimentId");
        setData();
        return getBasePanel();
    }

    private JPanel getFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new SpringLayout());
        formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.name")));
        formPanel.add(textFieldName = new JTextField());
        formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.image.face.id")));
        formPanel.add(comboBoxImagemFaceId = new JComboBox<Integer>());
        List<Integer> list = dao.getImagemFaceId();
        for (int i = 0; list.size() > i; i++) {
            comboBoxImagemFaceId.addItem(list.get(i));

        }

        formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.animation.object.moviment.id")));
        formPanel.add(comboBoxAnimationObjectMovimentId = new JComboBox<Integer>());
        list = dao.getAnimationObjectMovimentId();
        for (int i = 0; list.size() > i; i++) {
            comboBoxAnimationObjectMovimentId.addItem(list.get(i));

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
            npc = getNPCSelected();
        } else {
            npc = new Npc();
        }
        npc.setName(textFieldName.getText());
        npc.setAnimationObjectMovementId((Integer) comboBoxAnimationObjectMovimentId.getSelectedItem());
        npc.setImagemFaceId((Integer) comboBoxImagemFaceId.getSelectedItem());
        dao.saveOrUpDate(npc);
        refresh();


    }

    public void setFieldEnabled(boolean b) {

        textFieldName.setEnabled(b);
        comboBoxAnimationObjectMovimentId.setEnabled(b);
        comboBoxImagemFaceId.setEnabled(b);

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
            deleteListNPC();

        }

    }

    private void deleteListNPC() {
        int selecionados[] = table.getSelectedRows();
        if (selecionados.length > 0) {
            List<Npc> npc = new ArrayList<>();

            for (int i = 0; i < selecionados.length; i++) {
                selecionados[i] = table.convertRowIndexToModel(selecionados[i]);
                npc.add(tableModel.getValue(selecionados[i]));
            }
            for (Npc rnpc : npc) {
                dao.remove(rnpc);
                tableModel.remove(rnpc);
            }
            refresh();
            npc = null;
            System.gc();
        }
    }

    private void setFieldsToNewValues() {
        textFieldName.setText("");
        comboBoxAnimationObjectMovimentId.setSelectedIndex(0);
        comboBoxImagemFaceId.setSelectedIndex(0);
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

    private Npc getNPCSelected() {

        return tableModel.getValue(table.getSelectedRow());
    }

    private void setSelectedValuesToFields() {
        npc = getNPCSelected();
        textFieldName.setText(npc.getName());
        comboBoxAnimationObjectMovimentId.setSelectedItem(npc.getAnimationObjectMovimentId());
        comboBoxImagemFaceId.setSelectedItem(npc.getImagemFaceId());
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

/**
 * =========================================== <br>
 * PROJETO PARALLAX - Engine de Jogos 2D e RPG <br>
 * Data de alteração do arquivo: 30/10/2012 <br>
 * =========================================== <P>
 * 
 * A classe <b>MessageTableModel</b> (Modelo da Tabela de Messagem) trata as menssagens que serão utilizadas dentro do jogo pelos personagens.
 *</P>  
 * 
 * 
 * @see	<a href="http://www.einformacao.com.br/parallax/component/content/article/35-conteudo-site/86-documentos-para-leitura">Documentos para Leitura</a>
 * @author <a href="mailto:jeferson.januario@gmail.com">Jeferson Januario</a>
 * @version 1.0.1
 * @since Existe desde a versão 1.0.1 do projeto
 */

package game.db.form;


import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import game.db.dao.MessageDao;
import game.db.entity.Message;
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


public class MessageTableModel implements ActionListener, ListSelectionListener {

    final private MessageDao dao = new MessageDao();
    private JTable table;
    private JButton buttonNew;
    private JButton buttonRefresh;
    private JButton buttonSave;
    private JButton buttonDelete;
    private ListSelectionModel ListSelectionModel;
    private JTextArea textAreaText;
    private JTextField textFieldName;
    private ObjectTableModel<Message> tableModel;
    private Message message;
    /**
     * @return PanelBase Contem todos os componetes da GUI
     * O metodo show resolve as dependencias da classe e retorna a GUI com todos os componetes.
     */
    public JPanel show() {
        AnnotationResolver resolver = new AnnotationResolver(Message.class);
        tableModel = new ObjectTableModel<>(resolver, "id,name,text");
        setData();
        return getBasePanel();

    }

    private JPanel getFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new SpringLayout());
        formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.name")));
        formPanel.add(textFieldName = new JTextField());
        formPanel.add(new JLabel(LanguageUtil.getLanguageFactory().getMessage("label.text")));
        formPanel.add(textAreaText = new JTextArea());
        textAreaText.setLineWrap(true);
       textAreaText.setWrapStyleWord(true);
       textAreaText.setBackground(Color.white);
       textAreaText.setPreferredSize(new Dimension(50,60));
       textAreaText.setCaretPosition(textAreaText.getText().length());
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
            message = getMessageSelected();
        } else {
            message = new Message();
        }
        message.setName(textFieldName.getText());
        message.setText(textAreaText.getText());
        dao.saveOrUpDate(message);
        refresh();


    }

    public void setFieldEnabled(boolean b) {

        textFieldName.setEnabled(b);
        textAreaText.setEnabled(b);

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
            deleteListMessage();

        }

    }

    private void deleteListMessage() {
        int selecionados[] = table.getSelectedRows();
        if (selecionados.length > 0) {
            List<Message> ao = new ArrayList<>();

            for (int i = 0; i < selecionados.length; i++) {
                selecionados[i] = table.convertRowIndexToModel(selecionados[i]);
                ao.add(tableModel.getValue(selecionados[i]));
            }
            for (Message rao : ao) {
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
        textAreaText.setText("");
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

    private Message getMessageSelected() {

        return tableModel.getValue(table.getSelectedRow());
    }

    private void setSelectedValuesToFields() {
        message = getMessageSelected();
        textFieldName.setText(message.getName());
        textAreaText.setText(message.getText());
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

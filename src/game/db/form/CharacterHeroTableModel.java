package game.db.form;

import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import game.db.dao.CharacterHeroDao;
import game.db.entity.CharacterHero;
import game.db.util.LanguageUtil;
import game.db.util.SpringUtilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
 * A classe <b>CharacterHeroTableModel</b> (Modelo da Tabela Personagem)
 * manipula as propriedades dos personagens.
 * <p>
 * 
 * @see <a
 *      href="http://www.einformacao.com.br/parallax/component/content/article/35-conteudo-site/86-documentos-para-leitura">Documentos
 *      para Leitura</a>
 * @author <a href="mailto:jeferson.januario@gmail.com">Jeferson Januario</a>
 * @version 1.0.1
 * @since Existe desde a versão 1.0.1 do projeto
 */
public class CharacterHeroTableModel implements ListSelectionListener,
		ActionListener {

	final private CharacterHeroDao dao = new CharacterHeroDao();

	private ObjectTableModel<CharacterHero> tableModel;
	private JTable table;
	private JTextField textFieldName;
	private JSpinner spinnerAccuracy;
	private JSpinner spinnerActionPoint;
	private JSpinner spinnerConstitution;
	private JSpinner spinnerCritical;
	private JSpinner spinnerDexterity;
	private JSpinner spinnerEvasion;
	private JSpinner spinnerExperience;
	private JSpinner spinnerGold;
	private JSpinner spinnerHp;
	private JSpinner spinnerInitiative;
	private JSpinner spinnerIntelligence;
	private JSpinner spinnerLevel;
	private JSpinner spinnerLuck;
	private JSpinner spinnerStrength;
	private JSpinner spinnerTotalBonusLevel;
	private ButtonGroup radioGroupGender;

	private JTextField textFieldWayPoint;
	private JTextArea textAreaHistory;
	private JRadioButton woman;
	private JRadioButton man;

	private JComboBox<Integer> comboBoxAnimationObjectMovimentId;
	private JComboBox<Integer> comboBoxImagemBattlerId;
	private JComboBox<Integer> comboBoxImagemFaceId;
	private JComboBox<String> comboBoxTypeClass;
	private JComboBox<Integer> comboBoxTerraInType;
	private JComboBox<Integer> comboBoxClassId;

	private JButton buttonNew;
	private JButton buttonRefresh;
	private JButton buttonDelete;
	private JButton buttonSave;

	private ListSelectionModel ListSelectionModel;

	private CharacterHero characterHero;
	 /**
     * @return PanelBase Contem todos os componetes da GUI
     * O metodo show resolve as dependencias da classe e retorna a GUI com todos os componetes.
     */
	public JPanel show() {
		AnnotationResolver resolver = new AnnotationResolver(
				CharacterHero.class);
		tableModel = new ObjectTableModel<>(resolver,
				"id,name,actionPoint,gender,gold,level,terraInTypeId,totalBonusLevel,typeClass");
		table = new JTable(tableModel);
		setData();

		ListSelectionModel = table.getSelectionModel();
		ListSelectionModel.addListSelectionListener(this);

		return getPanelBase();

	}

	private JPanel getPanelForm() {
		JPanel panelForm = new JPanel();
		panelForm.setLayout(new BorderLayout());
		panelForm.add(getPanelFormText(), BorderLayout.LINE_START);
		panelForm.add(getPanelSpinner(), BorderLayout.LINE_END);

		return panelForm;
	}

	private JPanel getPanelFormText() {
		JPanel panelFormText = new JPanel();
		panelFormText.setLayout(new SpringLayout());
		panelFormText.add(new JLabel(LanguageUtil.getLanguageFactory()
				.getMessage("label.name")));
		panelFormText.add(textFieldName = new JTextField());
		radioGroupGender = new ButtonGroup();
		radioGroupGender.add(man = new JRadioButton());
		man.setText("man");
		man.setActionCommand("0");
		radioGroupGender.add(woman = new JRadioButton());
		woman.setText("woman");
		woman.setActionCommand("1");
		panelFormText.add(new JLabel(LanguageUtil.getLanguageFactory()
				.getMessage("label.gender")), BorderLayout.LINE_START);
		JPanel gender = new JPanel();
		gender.add(woman);
		gender.add(man);

		panelFormText.add(gender);

		panelFormText.add(new JLabel(LanguageUtil.getLanguageFactory()
				.getMessage("label.class")), BorderLayout.LINE_START);
		panelFormText.add(comboBoxTypeClass = new JComboBox<String>(),
				BorderLayout.LINE_START);
		List<String> listS = dao.getTypeClass();
		for (int i = 0; listS.size() > i; i++) {
			comboBoxTypeClass.addItem((String) listS.get(i));
		}
		panelFormText.add(new JLabel(LanguageUtil.getLanguageFactory()
				.getMessage("label.animation.object.moviment.id")),
				BorderLayout.LINE_START);
		panelFormText.add(
				comboBoxAnimationObjectMovimentId = new JComboBox<Integer>(),
				BorderLayout.LINE_START);
		List<Integer> list;
		list = dao.getAnimationObjectMovimentId();
		for (int i = 0; list.size() > i; i++) {
			comboBoxAnimationObjectMovimentId.addItem((Integer) list.get(i));
		}
		panelFormText.add(new JLabel(LanguageUtil.getLanguageFactory()
				.getMessage("label.image.battler")), BorderLayout.LINE_START);
		panelFormText.add(comboBoxImagemBattlerId = new JComboBox<Integer>(),
				BorderLayout.LINE_START);
		list = dao.getImagemBattlerId();
		for (int i = 0; list.size() > i; i++) {
			comboBoxImagemBattlerId.addItem((Integer) list.get(i));
		}
		panelFormText.add(new JLabel(LanguageUtil.getLanguageFactory()
				.getMessage("label.class")), BorderLayout.LINE_START);
		panelFormText.add(comboBoxClassId = new JComboBox<Integer>(),
				BorderLayout.LINE_START);
		list = dao.getClasseId();
		for (int i = 0; list.size() > i; i++) {
			comboBoxClassId.addItem((Integer) list.get(i));
		}

		panelFormText.add(new JLabel(LanguageUtil.getLanguageFactory()
				.getMessage("label.terra.in.type")), BorderLayout.LINE_START);
		panelFormText.add(comboBoxTerraInType = new JComboBox<Integer>(),
				BorderLayout.LINE_START);
		list = dao.getTerraInTypeId();
		for (int i = 0; list.size() > i; i++) {
			comboBoxTerraInType.addItem((Integer) list.get(i));
		}
		panelFormText.add(new JLabel(LanguageUtil.getLanguageFactory()
				.getMessage("label.image.face.id")), BorderLayout.LINE_START);
		panelFormText.add(comboBoxImagemFaceId = new JComboBox<Integer>(),
				BorderLayout.LINE_START);
		list = dao.getImagemFaceId();
		for (int i = 0; list.size() > i; i++) {
			comboBoxImagemFaceId.addItem((Integer) list.get(i));
		}

		panelFormText.add(new JLabel(LanguageUtil.getLanguageFactory()
				.getMessage("label.waypoint")));
		panelFormText.add(textFieldWayPoint = new JTextField());
		textFieldWayPoint.setText("dfsdfsd");
		panelFormText.add(new JLabel(LanguageUtil.getLanguageFactory()
				.getMessage("label.history")));
		JScrollPane scrollTextArea = new JScrollPane(
				textAreaHistory = new JTextArea(7, 30));

		textAreaHistory.setLineWrap(true);
		textAreaHistory.setWrapStyleWord(true);
		textAreaHistory.setBackground(Color.white);
		textAreaHistory.setPreferredSize(new Dimension(10, 15));
		textAreaHistory.setCaretPosition(textAreaHistory.getText().length());

		// scrollTextArea.setVerticalScrollBarPolicy(JScrollPane.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		// scrollTextArea.setViewportView(textAreaHistory);
		panelFormText.add(scrollTextArea);

		SpringUtilities.makeCompactGrid(panelFormText,
				panelFormText.getComponentCount() / 2, 2, // rows, cols
				6, 6, // initX, initY
				6, 6); // xPad, yPad
		return panelFormText;
	}

	private JPanel getPanelBase() {
		JPanel panelBase = new JPanel();
		JScrollPane scrollPanelTable = new JScrollPane(table);
		scrollPanelTable.setViewportView(table);
		scrollPanelTable.setPreferredSize(new Dimension(1000, 100));

		panelBase.setLayout(new BorderLayout());
		panelBase.add(scrollPanelTable, BorderLayout.BEFORE_FIRST_LINE);
		panelBase.add(getPanelForm(), BorderLayout.CENTER);
		panelBase.add(getPanelButton(), BorderLayout.PAGE_END);
		setButtonsEnabled(false);
		setFieldsEnabled(false);
		return panelBase;
	}

	private JPanel getPanelSpinner() {
		JPanel panelSpinner = new JPanel();
		panelSpinner.setLayout(new SpringLayout());

		panelSpinner.add(new JLabel(LanguageUtil.getLanguageFactory()
				.getMessage("label.accuracy")), BorderLayout.LINE_START);
		panelSpinner.add(spinnerAccuracy = new JSpinner(),
				BorderLayout.LINE_START);
		spinnerAccuracy.setPreferredSize(new Dimension(52, 19));
		panelSpinner.add(new JLabel(LanguageUtil.getLanguageFactory()
				.getMessage("label.action.point")), BorderLayout.LINE_START);
		panelSpinner.add(spinnerActionPoint = new JSpinner(),
				BorderLayout.LINE_START);
		panelSpinner.add(new JLabel(LanguageUtil.getLanguageFactory()
				.getMessage("label.constitution")), BorderLayout.LINE_START);
		panelSpinner.add(spinnerConstitution = new JSpinner(),
				BorderLayout.LINE_START);
		panelSpinner.add(new JLabel(LanguageUtil.getLanguageFactory()
				.getMessage("label.critical")), BorderLayout.LINE_START);
		panelSpinner.add(spinnerCritical = new JSpinner(),
				BorderLayout.LINE_START);
		panelSpinner.add(new JLabel(LanguageUtil.getLanguageFactory()
				.getMessage("label.dexterity")), BorderLayout.LINE_START);
		panelSpinner.add(spinnerDexterity = new JSpinner(),
				BorderLayout.LINE_START);
		panelSpinner.add(new JLabel(LanguageUtil.getLanguageFactory()
				.getMessage("label.evasion")), BorderLayout.LINE_START);
		panelSpinner.add(spinnerEvasion = new JSpinner(),
				BorderLayout.LINE_START);
		panelSpinner.add(new JLabel(LanguageUtil.getLanguageFactory()
				.getMessage("label.experience")), BorderLayout.LINE_START);
		panelSpinner.add(spinnerExperience = new JSpinner(),
				BorderLayout.LINE_START);
		panelSpinner.add(new JLabel(LanguageUtil.getLanguageFactory()
				.getMessage("label.gold")), BorderLayout.LINE_START);
		panelSpinner.add(spinnerGold = new JSpinner(), BorderLayout.LINE_START);
		panelSpinner.add(new JLabel(LanguageUtil.getLanguageFactory()
				.getMessage("label.hp")), BorderLayout.LINE_START);
		panelSpinner.add(spinnerHp = new JSpinner(), BorderLayout.LINE_START);
		panelSpinner.add(new JLabel(LanguageUtil.getLanguageFactory()
				.getMessage("label.initiative")), BorderLayout.LINE_START);
		panelSpinner.add(spinnerInitiative = new JSpinner(),
				BorderLayout.LINE_START);
		panelSpinner.add(new JLabel(LanguageUtil.getLanguageFactory()
				.getMessage("label.inteligencie")), BorderLayout.LINE_START);
		panelSpinner.add(spinnerIntelligence = new JSpinner(),
				BorderLayout.LINE_START);
		panelSpinner.add(new JLabel(LanguageUtil.getLanguageFactory()
				.getMessage("label.level")), BorderLayout.LINE_START);
		panelSpinner
				.add(spinnerLevel = new JSpinner(), BorderLayout.LINE_START);
		panelSpinner.add(new JLabel(LanguageUtil.getLanguageFactory()
				.getMessage("label.luck")), BorderLayout.LINE_START);
		panelSpinner.add(spinnerLuck = new JSpinner(), BorderLayout.LINE_START);
		panelSpinner.add(new JLabel(LanguageUtil.getLanguageFactory()
				.getMessage("label.strength")), BorderLayout.LINE_START);
		panelSpinner.add(spinnerStrength = new JSpinner(),
				BorderLayout.LINE_START);
		panelSpinner.add(new JLabel(LanguageUtil.getLanguageFactory()
				.getMessage("label.total.bonus.level")),
				BorderLayout.LINE_START);
		panelSpinner.add(spinnerTotalBonusLevel = new JSpinner(),
				BorderLayout.LINE_START);

		SpringUtilities.makeCompactGrid(panelSpinner, 15, 2, // rows, cols
				6, 6, // initX, initY
				6, 6); // xPad, yPad

		return panelSpinner;
	}

	/**
	 * @return animationTotalRow
	 */
	private JPanel getPanelButton() {

		JPanel paneButton = new JPanel();
		paneButton.add(buttonNew = new JButton(LanguageUtil
				.getLanguageFactory().getMessage("button.new")));
		paneButton.add(buttonRefresh = new JButton(LanguageUtil
				.getLanguageFactory().getMessage("button.refresh")));
		paneButton.add(buttonSave = new JButton(LanguageUtil
				.getLanguageFactory().getMessage("button.save")));
		paneButton.add(buttonDelete = new JButton(LanguageUtil
				.getLanguageFactory().getMessage("button.delete")));
		buttonNew.addActionListener(this);
		buttonRefresh.addActionListener(this);
		buttonSave.addActionListener(this);
		buttonDelete.addActionListener(this);
		return paneButton;
	}

	/**
	 * @param setFieldsEnabled
	 *            É o numero de imagens na horizontal que compõem a animação nfa
	 *            {@link #getSpriteWidth() getSpriteWidth}
	 */
	private synchronized void setFieldsEnabled(boolean b) {

		spinnerAccuracy.setEnabled(b);
		spinnerActionPoint.setEnabled(b);
		spinnerConstitution.setEnabled(b);
		spinnerCritical.setEnabled(b);
		spinnerDexterity.setEnabled(b);
		spinnerEvasion.setEnabled(b);
		spinnerExperience.setEnabled(b);
		spinnerGold.setEnabled(b);
		spinnerHp.setEnabled(b);
		spinnerInitiative.setEnabled(b);
		spinnerIntelligence.setEnabled(b);
		spinnerLevel.setEnabled(b);
		spinnerLuck.setEnabled(b);
		spinnerStrength.setEnabled(b);
		spinnerTotalBonusLevel.setEnabled(b);
		man.setEnabled(b);
		woman.setEnabled(b);
		comboBoxTerraInType.setEnabled(b);
		comboBoxTypeClass.setEnabled(b);
		textAreaHistory.setEnabled(b);
		textFieldWayPoint.setEnabled(b);
		textFieldName.setEnabled(b);
		comboBoxAnimationObjectMovimentId.setEnabled(b);
		comboBoxImagemBattlerId.setEnabled(b);
		comboBoxImagemFaceId.setEnabled(b);
		comboBoxClassId.setEnabled(b);

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
		characterHero = getCharacterHeroSelected();
		spinnerAccuracy.setValue(characterHero.getAccuracy());
		spinnerActionPoint.setValue(characterHero.getActionPoint());
		spinnerConstitution.setValue(characterHero.getConstitution());
		spinnerCritical.setValue(characterHero.getCritical());
		spinnerDexterity.setValue(characterHero.getDexterity());
		spinnerEvasion.setValue(characterHero.getEvasion());
		spinnerExperience.setValue(characterHero.getExperience());
		spinnerGold.setValue(characterHero.getGold());
		spinnerHp.setValue(characterHero.getHp());
		spinnerInitiative.setValue(characterHero.getInitiative());
		spinnerIntelligence.setValue(characterHero.getIntelligence());
		spinnerLevel.setValue(characterHero.getLevel());
		spinnerLuck.setValue(characterHero.getLuck());
		spinnerStrength.setValue(characterHero.getStrength());
		spinnerTotalBonusLevel.setValue(characterHero.getTotalBonusLevel());
		man.setSelected(characterHero.getGender().equals(0));
		woman.setSelected(characterHero.getGender().equals(1));
		textAreaHistory.setText(characterHero.getHistory());
		comboBoxTerraInType.setSelectedItem(characterHero.getTerraInTypeId());
		comboBoxTypeClass.setSelectedItem(characterHero.getTypeClass());
		textFieldWayPoint.setText(characterHero.getWayPoint());
		textFieldName.setText(characterHero.getName());
		comboBoxAnimationObjectMovimentId.setSelectedItem(characterHero
				.getAnimationObjectMovementId());
		comboBoxImagemBattlerId.setSelectedItem(characterHero
				.getImagemBattlerId());
		comboBoxImagemFaceId.setSelectedItem(characterHero.getImagemFaceId());

	}

	private void setFieldsToNewValues() {

		spinnerAccuracy.setValue(0);
		spinnerActionPoint.setValue(0);
		spinnerConstitution.setValue(0);
		spinnerCritical.setValue(0);
		spinnerDexterity.setValue(0);
		spinnerEvasion.setValue(0);
		spinnerExperience.setValue(0);
		spinnerGold.setValue(0);
		spinnerHp.setValue(0);
		spinnerInitiative.setValue(0);
		spinnerIntelligence.setValue(0);
		spinnerLevel.setValue(0);
		spinnerLuck.setValue(0);
		spinnerStrength.setValue(0);
		spinnerTotalBonusLevel.setValue(0);
		radioGroupGender.clearSelection();
		textAreaHistory.setText("");
		textFieldWayPoint.setText("");
		textFieldName.setText("");
		comboBoxAnimationObjectMovimentId.setSelectedIndex(0);
		comboBoxImagemBattlerId.setSelectedIndex(0);
		comboBoxImagemFaceId.setSelectedIndex(0);
		comboBoxTerraInType.setSelectedIndex(0);
		comboBoxTypeClass.setSelectedIndex(0);

	}

	private void setData() {
		tableModel.clear();
		tableModel.setData(dao.lista());
	}

	private CharacterHero getCharacterHeroSelected() {

		return tableModel.getValue(table.getSelectedRow());
	}

	private void saveOrUpDate() {
		boolean isUpdate = table.getSelectedRow() != -1;

		if (isUpdate) {
			characterHero = getCharacterHeroSelected();
		} else {
			characterHero = new CharacterHero();
		}
		characterHero.setAccuracy((Integer) spinnerAccuracy.getValue());
		characterHero.setActionPoint((Integer) spinnerActionPoint.getValue());
		characterHero.setConstitution((Integer) spinnerConstitution.getValue());
		characterHero.setCritical((Integer) spinnerCritical.getValue());
		characterHero.setDexterity((Integer) spinnerDexterity.getValue());
		characterHero.setEvasion((Integer) spinnerEvasion.getValue());
		characterHero.setExperience((Integer) spinnerExperience.getValue());
		characterHero.setGold((Integer) spinnerGold.getValue());
		characterHero.setHp((Integer) spinnerHp.getValue());
		characterHero.setInitiative((Integer) spinnerInitiative.getValue());
		characterHero.setIntelligence((Integer) spinnerIntelligence.getValue());
		characterHero.setLevel((Integer) spinnerLevel.getValue());
		characterHero.setLuck((Integer) spinnerLuck.getValue());
		characterHero.setStrength((Integer) spinnerStrength.getValue());
		characterHero.setTotalBonusLevel((Integer) spinnerTotalBonusLevel
				.getValue());
		characterHero.setGender(Integer.parseInt(radioGroupGender
				.getSelection().getActionCommand()));
		characterHero
				.setImagemBattlerId((Integer) comboBoxAnimationObjectMovimentId
						.getSelectedItem());
		characterHero
				.setTypeClass((String) comboBoxTypeClass.getSelectedItem());
		characterHero.setImagemFaceId((Integer) comboBoxImagemFaceId
				.getSelectedItem());
		characterHero.setTerraInTypeId((Integer) comboBoxTerraInType
				.getSelectedItem());
		characterHero.setClasseId((Integer) comboBoxClassId.getSelectedItem());
		characterHero.setWayPoint((String) textFieldWayPoint.getText());
		characterHero.setName((String) textFieldName.getText());
		characterHero.setAnimationObjectMovementId((Integer) comboBoxAnimationObjectMovimentId.getSelectedItem());
		dao.saveOrUpDate(characterHero);
		refresh();

	}

	private void delete() {
		List<CharacterHero> list = tableModel.getList(table.getSelectedRows());
		tableModel.remove(list);
		dao.remove(list);

		refresh();
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
			spinnerAccuracy.requestFocus();
		}
		if (e.getSource() == buttonRefresh) {
			refresh();
		}
		if (e.getSource() == buttonSave) {
			if (radioGroupGender.getSelection() == null) {
				JOptionPane.showMessageDialog(null, LanguageUtil
						.getLanguageFactory().getMessage("error.notgender"));
			} else {
				saveOrUpDate();
			}
		}
		if (e.getSource() == buttonDelete) {
			delete();
		}
	}
}

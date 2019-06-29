package game.db.main;

import game.db.form.AnimationObjectTableModel;
import game.db.form.CharacterHeroObjectiveTableModel;
import game.db.form.CharacterHeroSkillTableModel;
import game.db.form.CharacterHeroStatusEffectTableModel;
import game.db.form.CharacterHeroTableModel;
import game.db.form.ClasseTableModel;
import game.db.form.ConfigurationTableModel;
import game.db.form.ImagemTableModel;
import game.db.form.MessageTableModel;
import game.db.form.MonsterSkillTableModel;
import game.db.form.MonsterTableModel;
import game.db.form.MusicTableModel;
import game.db.form.NpcTableModel;
import game.db.form.ObjectiveTableModel;
import game.db.form.QuestTableModel;
import game.db.form.RewardTableModel;
import game.db.form.SkillStatusEffectTableModel;
import game.db.form.SkillTableModel;
import game.db.form.SoundTableModel;
import game.db.form.TerraInTypeTableModel;
import game.db.form.TypeDamageTableModel;
import game.db.util.LanguageUtil;


import java.awt.Cursor;
import java.awt.Dimension;


import javax.swing.JFrame;
import javax.swing.JTabbedPane;
/**
 * =========================================== <br>
 * PROJETO PARALLAX - Engine de Jogos 2D e RPG <br>
 * Data de alteração do arquivo: 30/10/2012 <br>
 * ===========================================
 * <P>
 * 
 * A classe <b>Main</b> (Classe Principal) responsavel por chamar todos os outros recursos da aplicação
 * <p>
 * 
 * @see <a
 *      href="http://www.einformacao.com.br/parallax/component/content/article/35-conteudo-site/86-documentos-para-leitura">Documentos
 *      para Leitura</a>
 * @author <a href="mailto:jeferson.januario@gmail.com">Jeferson Januario</a>
 * @version 1.0.1
 * @since Existe desde a versão 1.0.1 do projeto
 */
public class Main {

    public void createAndShowGui() {
        JFrame janela = new JFrame("Parallax - Data Base Editor");
        JTabbedPane tabbedPane = new JTabbedPane();
        janela.setVisible(true);
        janela.setSize(new Dimension(900, 700));
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        janela.add(tabbedPane);
        tabbedPane.add(LanguageUtil.getLanguageFactory().getMessage("tab.animationobject"), new AnimationObjectTableModel().show());
        tabbedPane.add(LanguageUtil.getLanguageFactory().getMessage("tab.characterheroobjective"), new CharacterHeroObjectiveTableModel().show());
        tabbedPane.add(LanguageUtil.getLanguageFactory().getMessage("tab.characterhero"), new CharacterHeroTableModel().show());
        tabbedPane.add(LanguageUtil.getLanguageFactory().getMessage("tab.class"), new ClasseTableModel().show());
        tabbedPane.add(LanguageUtil.getLanguageFactory().getMessage("tab.image"), new ImagemTableModel().show());
        tabbedPane.add(LanguageUtil.getLanguageFactory().getMessage("tab.characterheroskill"), new CharacterHeroSkillTableModel().show());
        tabbedPane.add(LanguageUtil.getLanguageFactory().getMessage("tab.characterhero.statuseffect"), new CharacterHeroStatusEffectTableModel().show());
        tabbedPane.add(LanguageUtil.getLanguageFactory().getMessage("tab.configuration"), new ConfigurationTableModel().show());
        tabbedPane.add(LanguageUtil.getLanguageFactory().getMessage("tab.message"), new MessageTableModel().show());
        tabbedPane.add(LanguageUtil.getLanguageFactory().getMessage("tab.monster"), new MonsterTableModel().show());
        tabbedPane.add(LanguageUtil.getLanguageFactory().getMessage("tab.music"), new MusicTableModel().show());
        tabbedPane.add(LanguageUtil.getLanguageFactory().getMessage("tab.npc"), new NpcTableModel().show());
        tabbedPane.add(LanguageUtil.getLanguageFactory().getMessage("tab.objective"), new ObjectiveTableModel().show());
        tabbedPane.add(LanguageUtil.getLanguageFactory().getMessage("tab.quest"), new QuestTableModel().show());
        tabbedPane.add(LanguageUtil.getLanguageFactory().getMessage("tab.reward"), new RewardTableModel().show());
        tabbedPane.add(LanguageUtil.getLanguageFactory().getMessage("tab.skillstatuseffect"), new SkillStatusEffectTableModel().show());
        tabbedPane.add(LanguageUtil.getLanguageFactory().getMessage("tab.skill"), new SkillTableModel().show());
        tabbedPane.add(LanguageUtil.getLanguageFactory().getMessage("tab.sound"), new SoundTableModel().show());
        tabbedPane.add(LanguageUtil.getLanguageFactory().getMessage("tab.terraintype"), new TerraInTypeTableModel().show());
        tabbedPane.add(LanguageUtil.getLanguageFactory().getMessage("tab.typedamage"), new TypeDamageTableModel().show());
        tabbedPane.add(LanguageUtil.getLanguageFactory().getMessage("tab.monsterskill"), new MonsterSkillTableModel().show());
        
        tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));    
    }

    public static void main(String args[]) {
        new Main().createAndShowGui();
    }
}

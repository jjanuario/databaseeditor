/**
 * =========================================== <br>
 * PROJETO PARALLAX - Engine de Jogos 2D e RPG <br>
 * Data de alteração do arquivo: 30/10/2012 <br>
 * ===========================================
 * <P>
 * 
 * A classe <b>Objective</b> (Objetivo) é a classe responsavel em gerenciar os objetivos.
 * 
 * @see <a
 *      href="http://www.einformacao.com.br/parallax/component/content/article/35-conteudo-site/86-documentos-para-leitura">Documentos
 *      para Leitura</a>
 * @author <a href="mailto:jeferson.januario@gmail.com">Jeferson Januario</a>
 * @version 1.0.1
 * @since Existe desde a versão 1.0.1 do projeto
 */
package game.db.entity;


import com.towel.el.annotation.Resolvable;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Monster  implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Resolvable
     private int id;
    @Resolvable
     private String name;
    @Resolvable
     private String minIonIds;
    @Resolvable
     private String intervalMinMaxMinIons;
    @Resolvable
     private String typeClass;
    @Resolvable
     private String history;
    @Resolvable
     private Integer level;
    @Resolvable
     private Integer experience;
    @Resolvable
     private Integer hp;
    @Resolvable
     private Integer actionPoint;
    @Resolvable
     private Integer strength;
    @Resolvable
     private Integer dexterity;
    @Resolvable
     private Integer intelligence;
    @Resolvable
     private Integer constitution;
    @Resolvable
     private Integer luck;
    @Resolvable
     private Integer gender;
    @Resolvable
     private Integer gold;
    @Resolvable
     private Integer evasion;
    @Resolvable
     private Integer accuracy;
    @Resolvable
     private Integer critical;
    @Resolvable
     private Integer initiative;
    @Resolvable
     private Integer rewardGold;
    @Resolvable
     private Integer rewardExperience;
    @Resolvable
     private Integer terraInTypeId;
    @Resolvable
     private Integer imagemFaceId;
    @Resolvable
     private Integer imagemBattlerId;
    @Resolvable
     private Integer animationObjectMovementId;

   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public Integer getLevel() {
        return this.level;
    }
    
    public void setLevel(Integer level) {
        this.level = level;
    }
    public Integer getExperience() {
        return this.experience;
    }
    
    public void setExperience(Integer experience) {
        this.experience = experience;
    }
    public Integer getHp() {
        return this.hp;
    }
    
    public void setHp(Integer hp) {
        this.hp = hp;
    }
    public Integer getActionPoint() {
        return this.actionPoint;
    }
    
    public void setActionPoint(Integer actionpoint) {
        this.actionPoint = actionpoint;
    }
    public Integer getStrength() {
        return this.strength;
    }
    
    public void setStrength(Integer strength) {
        this.strength = strength;
    }
    public Integer getDexterity() {
        return this.dexterity;
    }
    
    public void setDexterity(Integer dexterity) {
        this.dexterity = dexterity;
    }
    public Integer getIntelligence() {
        return this.intelligence;
    }
    
    public void setIntelligence(Integer intelligence) {
        this.intelligence = intelligence;
    }
    public Integer getConstitution() {
        return this.constitution;
    }
    
    public void setConstitution(Integer constitution) {
        this.constitution = constitution;
    }
    public Integer getLuck() {
        return this.luck;
    }
    
    public void setLuck(Integer luck) {
        this.luck = luck;
    }
    public Integer getGender() {
        return this.gender;
    }
    
    public void setGender(Integer gender) {
        this.gender = gender;
    }
    public Integer getGold() {
        return this.gold;
    }
    
    public void setGold(Integer gold) {
        this.gold = gold;
    }
    public String getTypeClass() {
        return this.typeClass;
    }
    
    public void setTypeClass(String typeclass) {
        this.typeClass = typeclass;
    }
    public String getHistory() {
        return this.history;
    }
    
    public void setHistory(String history) {
        this.history = history;
    }
    public Integer getEvasion() {
        return this.evasion;
    }
    
    public void setEvasion(Integer evasion) {
        this.evasion = evasion;
    }
    public Integer getAccuracy() {
        return this.accuracy;
    }
    
    public void setAccuracy(Integer accuracy) {
        this.accuracy = accuracy;
    }
    public Integer getCritical() {
        return this.critical;
    }
    
    public void setCritical(Integer critical) {
        this.critical = critical;
    }
    public Integer getInitiative() {
        return this.initiative;
    }
    
    public void setInitiative(Integer initiative) {
        this.initiative = initiative;
    }
    public String getMinIonIds() {
        return this.minIonIds;
    }
    
    public void setMinIonIds(String minIonIds) {
        this.minIonIds = minIonIds;
    }
    public String getIntervalminmaxminions() {
        return this.intervalMinMaxMinIons;
    }
    
    public void setIntervalMinMaxMinIons(String intervalMinMaxMinIons) {
        this.intervalMinMaxMinIons = intervalMinMaxMinIons;
    }
    public Integer getRewardGold() {
        return this.rewardGold;
    }
    
    public void setRewardgold(Integer rewardGold) {
        this.rewardGold = rewardGold;
    }
    public Integer getRewardExperience() {
        return this.rewardExperience;
    }
    
    public void setRewardexperience(Integer rewardExperience) {
        this.rewardExperience = rewardExperience;
    }
    public Integer getTerraInTypeId() {
        return this.terraInTypeId;
    }
    
    public void setTerraInTypeId(Integer terraintypeid) {
        this.terraInTypeId = terraintypeid;
    }
    public Integer getImagemFaceId() {
        return this.imagemFaceId;
    }
    
    public void setImagemFaceId(Integer imagemfaceid) {
        this.imagemFaceId = imagemfaceid;
    }
    public Integer getImagemBattlerId() {
        return this.imagemBattlerId;
    }
    
    public void setImagemBattlerId(Integer imagembattlerid) {
        this.imagemBattlerId = imagembattlerid;
    }
    public Integer getAnimationObjectmMovementId() {
        return this.animationObjectMovementId;
    }
    
    public void setAnimationobjectmovementid(Integer animationobjectmovementid) {
        this.animationObjectMovementId = animationobjectmovementid;
    }




}



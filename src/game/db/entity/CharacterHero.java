/**
 * =========================================== <br>
 * PROJETO PARALLAX - Engine de Jogos 2D e RPG <br>
 * Data de alteração do arquivo: 30/10/2012 <br>
 * ===========================================
 * <P>
 * 
 * A classe <b>CharacterHero</b> (Personagem) é a classe responsavel em gerenciar os personagens.
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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class CharacterHero {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Resolvable(colName="ID")
    private Integer id;
    @Resolvable(colName="Class ID")
    private Integer classeId;
    @Resolvable(colName="Name")
    private String name;
    @Resolvable(colName="Class Type")
    private String typeClass;
    @Resolvable(colName="Waypoint")
    private String wayPoint;
    @Resolvable(colName="History")
    private String history;
    @Resolvable(colName="Accuracy")
    private Integer accuracy;
    @Resolvable(colName="Action Point")
    private Integer actionPoint;
    @Resolvable(colName="Moviment ID")
    private Integer animationObjectMovementId;
    @Resolvable(colName="Constitution")
    private Integer constitution;
    @Resolvable(colName="Critical")
    private Integer critical;
    @Resolvable(colName="Dexterity")
    private Integer dexterity;
    @Resolvable(colName="Evasion")
    private Integer evasion;
    @Resolvable(colName="Experience")
    private Integer experience;
    @Resolvable(colName="Gender")
    private Integer gender;
    @Resolvable(colName="Gold")
    private Integer gold;
    @Resolvable(colName="HP")
    private Integer hp;
    @Resolvable(colName="Image Battler ID")
    private Integer imagemBattlerId;
    @Resolvable(colName="Image Face ID")
    private Integer imagemFaceId;
    @Resolvable(colName="Initiative")
    private Integer initiative;
    @Resolvable(colName="intelligence")
    private Integer intelligence;
    @Resolvable(colName="Level")
    private Integer level;
    @Resolvable(colName="Luck")
    private Integer luck;
    @Resolvable(colName="Strength")
    private Integer strength;
    @Resolvable(colName="Terrain Type ID")
    private Integer terraInTypeId;
    @Resolvable(colName="Total Bonus Level")
    private Integer totalBonusLevel;
    

    public Integer getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Integer accuracy) {
        this.accuracy = accuracy;
    }

    public Integer getActionPoint() {
        return actionPoint;
    }

    public void setActionPoint(Integer actionPoint) {
        this.actionPoint = actionPoint;
    }

    public Integer getAnimationObjectMovementId() {
        return animationObjectMovementId;
    }

    public void setAnimationObjectMovementId(Integer animationObjectMovementId) {
        this.animationObjectMovementId = animationObjectMovementId;
    }

    public Integer getClasseId() {
        return classeId;
    }

    public void setClasseId(Integer classeId) {
        this.classeId = classeId;
    }

    public Integer getConstitution() {
        return constitution;
    }

    public void setConstitution(Integer constitution) {
        this.constitution = constitution;
    }

    public Integer getCritical() {
        return critical;
    }

    public void setCritical(Integer critical) {
        this.critical = critical;
    }

    public Integer getDexterity() {
        return dexterity;
    }

    public void setDexterity(Integer dexterity) {
        this.dexterity = dexterity;
    }

    public Integer getEvasion() {
        return evasion;
    }

    public void setEvasion(Integer evasion) {
        this.evasion = evasion;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getGold() {
        return gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getImagemBattlerId() {
        return imagemBattlerId;
    }

    public void setImagemBattlerId(Integer imagemBattlerId) {
        this.imagemBattlerId = imagemBattlerId;
    }

    public Integer getImagemFaceId() {
       
        return imagemFaceId;
    }

    public void setImagemFaceId(Integer imagemFaceId) {
        this.imagemFaceId = imagemFaceId;
    }

    public Integer getInitiative() {
        return initiative;
    }

    public void setInitiative(Integer initiative) {
        this.initiative = initiative;
    }

    public Integer getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(Integer intelligence) {
        this.intelligence = intelligence;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getLuck() {
        return luck;
    }

    public void setLuck(Integer luck) {
        this.luck = luck;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Integer getTerraInTypeId() {
        return terraInTypeId;
    }

    public void setTerraInTypeId(Integer terraInTypeId) {
        this.terraInTypeId = terraInTypeId;
    }

    public Integer getTotalBonusLevel() {
        return totalBonusLevel;
    }

    public void setTotalBonusLevel(Integer totalBonusLevel) {
        this.totalBonusLevel = totalBonusLevel;
    }

    public String getTypeClass() {
        return typeClass;
    }

    public void setTypeClass(String typeClass) {
        this.typeClass = typeClass;
    }

    public String getWayPoint() {
        return wayPoint;
    }

    public void setWayPoint(String wayPoint) {
        this.wayPoint = wayPoint;
    }
}

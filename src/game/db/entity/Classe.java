/**
 * =========================================== <br>
 * PROJETO PARALLAX - Engine de Jogos 2D e RPG <br>
 * Data de alteração do arquivo: 30/10/2012 <br>
 * ===========================================
 * <P>
 * 
 * A classe <b>Classe</b> (Classe) é a classe responsavel em gerenciar os tipos de raças (Classes) dentro do jogo.
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
public class Classe{

     @Id
     @GeneratedValue(strategy= GenerationType.IDENTITY)
     @Resolvable
     private Integer id;
     @Resolvable
     private String name;
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
     private Integer accuracy;
     @Resolvable
     private Integer gender;
     @Resolvable
     private Integer gold;
     @Resolvable
     private Integer initiative;
     @Resolvable
     private String typeClass;
     @Resolvable
     private String history;
     @Resolvable
     private Integer evasion;
     @Resolvable
     private Integer critical;
     @Resolvable
     private Integer terrainTypeId;
     @Resolvable
     private Integer imagemFaceId;
     @Resolvable
     private Integer imagemBattlerId;
     @Resolvable
     private Integer animationObjectMovementId;
    
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
        return terrainTypeId;
    }

    public void setTerraInTypeId(Integer terrainTypeId) {
        this.terrainTypeId = terrainTypeId;
    }

    public String getTypeClass() {
        return typeClass;
    }

    public void setTypeClass(String typeClass) {
        this.typeClass = typeClass;
    }  


}



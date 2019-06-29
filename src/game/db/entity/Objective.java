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
public class Objective implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Resolvable
    private int id;
    @Resolvable
    private String name;
    @Resolvable
    private String description;
    @Resolvable
    private Integer targetObjectId;
    @Resolvable
    private Integer amount;
    @Resolvable
    private String typeObjective;
    @Resolvable
    private String messageObjectiveEnd;
    @Resolvable
    private Integer npcEndObjectiveId;
    @Resolvable
    private String actionStartObjective;
    @Resolvable
    private String actionEndObjective;
    @Resolvable
    private Integer questId;
    @Resolvable
    private Integer rewardId;

    public String getActionStartObjective() {
        return actionStartObjective;
    }

    public void setActionStartObjective(String actionStartObjective) {
        this.actionStartObjective = actionStartObjective;
    }

    public String getActionEndObjective() {
        return actionEndObjective;
    }

    public void setActionEndObjective(String actionendObjective) {
        this.actionEndObjective = actionendObjective;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessageObjectiveEnd() {
        return messageObjectiveEnd;
    }

    public void setMessageObjectiveEnd(String messageObjectiveEnd) {
        this.messageObjectiveEnd = messageObjectiveEnd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNpcEndObjectiveId() {
        return npcEndObjectiveId;
    }

    public void setNpcEndObjectiveId(Integer npcEndObjectiveId) {
        this.npcEndObjectiveId = npcEndObjectiveId;
    }

    public Integer getQuestId() {
        return questId;
    }

    public void setQuestId(Integer questId) {
        this.questId = questId;
    }

    public Integer getRewardId() {
        return rewardId;
    }

    public void setRewardId(Integer rewardId) {
        this.rewardId = rewardId;
    }

    public Integer getTargetObjectId() {
        return targetObjectId;
    }

    public void setTargetObjectId(Integer targetObjectId) {
        this.targetObjectId = targetObjectId;
    }

    public String getTypeObjective() {
        return typeObjective;
    }

    public void setTypeObjective(String typeObjective) {
        this.typeObjective = typeObjective;
    }
}
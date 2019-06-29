/**
 * =========================================== <br>
 * PROJETO PARALLAX - Engine de Jogos 2D e RPG <br>
 * Data de alteração do arquivo: 30/10/2012 <br>
 * ===========================================
 * <P>
 * 
 * A classe <b>CharacterHeroObjective</b> (Objetivo do Personagem) é a classe responsavel em gerenciar os objetivos do personagem.
 * 
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
public class CharacterHeroObjective  implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@Resolvable(colName="ID")
@Id
@GeneratedValue(strategy= GenerationType.IDENTITY)
     private int id;
@Resolvable(colName="Check Closed")
     private Integer checkClosed;
@Resolvable(colName="Amount")
     private Integer amount;
@Resolvable(colName="Name")
     private String name;
@Resolvable(colName="Objective ID")
     private Integer objectiveId;
@Resolvable(colName="Character Hero ID")
     private Integer characterHeroId;

	
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Integer getCheckClosed() {
        return this.checkClosed;
    }
    
    public void setCheckClosed(Integer checkclosed) {
        this.checkClosed = checkclosed;
    }
    public Integer getAmount() {
        return this.amount;
    }
    
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public Integer getObjectiveId() {
        return this.objectiveId;
    }
    
    public void setObjectiveId(Integer objectiveId) {
        this.objectiveId = objectiveId;
    }
    public Integer getCharacterHeroId() {
        return this.characterHeroId;
    }
    
    public void setCharacterHeroId(Integer characterHeroId) {
        this.characterHeroId = characterHeroId;
    }




}



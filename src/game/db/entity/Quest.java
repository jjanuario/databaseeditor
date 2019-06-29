/**
 * =========================================== <br>
 * PROJETO PARALLAX - Engine de Jogos 2D e RPG <br>
 * Data de alteração do arquivo: 30/10/2012 <br>
 * ===========================================
 * <P>
 * 
 * A classe <b>Quest</b> (Missao) é a classe responsavel em gerenciar as missoes.
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
public class Quest  implements Serializable {

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
     private String description;
@Resolvable
     private Integer characterLevel;
@Resolvable
     private Integer onlyQuestActivate;
@Resolvable
     private Integer repeatable;
@Resolvable
     private Integer npcId;

   
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
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getCharacterLevel() {
        return this.characterLevel;
    }
    
    public void setCharacterLevel(Integer characterlevel) {
        this.characterLevel = characterlevel;
    }
    public Integer getOnlyquestactivate() {
        return this.onlyQuestActivate;
    }
    
    public void setOnlyquestactivate(Integer onlyquestactivate) {
        this.onlyQuestActivate = onlyquestactivate;
    }
    public Integer getRepeatable() {
        return this.repeatable;
    }
    
    public void setRepeatable(Integer repeatable) {
        this.repeatable = repeatable;
    }
    public Integer getNpcid() {
        return this.npcId;
    }
    
    public void setNpcid(Integer npcid) {
        this.npcId = npcid;
    }




}



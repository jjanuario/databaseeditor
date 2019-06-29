/**
 * =========================================== <br>
 * PROJETO PARALLAX - Engine de Jogos 2D e RPG <br>
 * Data de alteração do arquivo: 30/10/2012 <br>
 * ===========================================
 * <P>
 * 
 * A classe <b>CharacterHeroSkill</b> (Habilidade do Personagem) é a classe responsavel em gerenciar as habilidades do Personagem.
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

@Entity(name="CHARACTERHERO_SKILL")
public class CharacterHeroSkill implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Integer getCharacterHeroId() {
        return characterHeroId;
    }

    public void setCharacterHeroId(Integer characterHeroId) {
        this.characterHeroId = characterHeroId;
    }

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Resolvable(colName="Character Hero ID")        
    Integer characterHeroId;
    @Resolvable(colName="Skill ID")        
    Integer skillId;
    
    
}

/**
 * =========================================== <br>
 * PROJETO PARALLAX - Engine de Jogos 2D e RPG <br>
 * Data de alteração do arquivo: 30/10/2012 <br>
 * ===========================================
 * <P>
 * 
 * A classe <b>SkillStatusEffect</b> (Efeito do Status da habilidade)
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


@Entity(name="SKILL_STATUSEFFECT")
public class SkillStatusEffect  implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy= GenerationType.IDENTITY)
@Resolvable(colName="Skill Id")
     private int skillId;
@Resolvable(colName="Status Effect Id")
     private int statusEffectId;

    public SkillStatusEffect() {
    }

    public SkillStatusEffect(int skillid, int statuseffectid) {
       this.skillId = skillid;
       this.statusEffectId = statuseffectid;
    }
   
    public int getSkillid() {
        return this.skillId;
    }
    
    public void setSkillId(int skillid) {
        this.skillId = skillid;
    }
    public int getStatusEffectId() {
        return this.statusEffectId;
    }
    
    public void setStatusEffectId(int statuseffectid) {
        this.statusEffectId = statuseffectid;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SkillStatusEffect) ) return false;
		 SkillStatusEffect castOther = ( SkillStatusEffect ) other; 
         
		 return (this.getSkillid()==castOther.getSkillid())
 && (this.getStatusEffectId()==castOther.getStatusEffectId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getSkillid();
         result = 37 * result + this.getStatusEffectId();
         return result;
   }   


}



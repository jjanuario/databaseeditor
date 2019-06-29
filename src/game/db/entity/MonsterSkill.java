/**
 * =========================================== <br>
 * PROJETO PARALLAX - Engine de Jogos 2D e RPG <br>
 * Data de alteração do arquivo: 30/10/2012 <br>
 * ===========================================
 * <P>
 * 
 * A classe <b>MonsterSkill</b> (Habilidade dos Adversarios) é a classe responsavel em gerenciar as habilidades do oponetes dentro do jogo.
 * 
 * @see <a
 *      href="http://www.einformacao.com.br/parallax/component/content/article/35-conteudo-site/86-documentos-para-leitura">Documentos
 *      para Leitura</a>
 * @author <a href="mailto:jeferson.januario@gmail.com">Jeferson Januario</a>
 * @version 1.0.1
 * @since Existe desde a versão 1.0.1 do projeto
 */package game.db.entity;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.towel.el.annotation.Resolvable;




/**
 * MonsterSkillId generated by hbm2java
 */
@Entity(name="MONSTER_SKILL")
public class MonsterSkill  implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@Resolvable(colName="Monster ID")
@Id
     private int monsterId;
@Resolvable(colName="Skill ID")
    @Id
     private int skillId;

    public MonsterSkill() {
    }

    public MonsterSkill(int monsterid, int skillid) {
       this.monsterId = monsterid;
       this.skillId = skillid;
    }
   
    public int getMonsterId() {
        return this.monsterId;
    }
    
    public void setMonsterId(int monsterid) {
        this.monsterId = monsterid;
    }
    public int getSkillId() {
        return this.skillId;
    }
    
    public void setSkillId(int skillid) {
        this.skillId = skillid;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof MonsterSkill) ) return false;
		 MonsterSkill castOther = ( MonsterSkill ) other; 
         
		 return (this.getMonsterId()==castOther.getMonsterId())
 && (this.getSkillId()==castOther.getSkillId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getMonsterId();
         result = 37 * result + this.getSkillId();
         return result;
   }   


}



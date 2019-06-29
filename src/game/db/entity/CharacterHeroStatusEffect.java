package game.db.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.towel.el.annotation.Resolvable;


/**
 * =========================================== <br>
 * PROJETO PARALLAX - Engine de Jogos 2D e RPG <br>
 * Data de alteração do arquivo: 30/10/2012 <br>
 * ===========================================
 * <P>
 * 
 * A classe <b>CharacterHeroStatusEffect</b> 
 * Manipula os Status dos Skills
 * <p>
 * 
 * @see <a
 *      href="http://www.einformacao.com.br/parallax/component/content/article/35-conteudo-site/86-documentos-para-leitura">Documentos
 *      para Leitura</a>
 * @author <a href="mailto:jeferson.januario@gmail.com">Jeferson Januario</a>
 * @version 1.0.1
 * @since Existe desde a versão 1.0.1 do projeto
 */
@Entity(name="CHARACTERHERO_STATUSEFFECT")
public class CharacterHeroStatusEffect  implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@Id

@Resolvable(colName="Character Hero ID")
     private int characterHeroId;
@Id
@Resolvable(colName="Status Effect ID")

     private int statusEffectId;

    public CharacterHeroStatusEffect() {
    }

    public CharacterHeroStatusEffect(int characterheroid, int statuseffectid) {
       this.characterHeroId = characterheroid;
       this.statusEffectId = statuseffectid;
    }
   
    public int getCharacterHeroId() {
        return this.characterHeroId;
    }
    
    public void setCharacterHeroId(int characterheroid) {
        this.characterHeroId = characterheroid;
    }
    public int getStatusEffectId() {
        return this.statusEffectId;
    }
    
    public void setStatusEffectId(int statuseffectid) {
        this.statusEffectId = statuseffectid;
    }


    @Override
   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof CharacterHeroStatusEffect) ) return false;
		 CharacterHeroStatusEffect castOther = ( CharacterHeroStatusEffect ) other; 
         
		 return (this.getCharacterHeroId()==castOther.getCharacterHeroId())
 && (this.getStatusEffectId()==castOther.getStatusEffectId());
   }
   
    @Override
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getCharacterHeroId();
         result = 37 * result + this.getStatusEffectId();
         return result;
   }   


}



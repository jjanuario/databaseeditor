/**
 * =========================================== <br>
 * PROJETO PARALLAX - Engine de Jogos 2D e RPG <br>
 * Data de alteração do arquivo: 30/10/2012 <br>
 * ===========================================
 * <P>
 * 
 * A classe <b>TypeDamage</b> (Tipo de Dano) é a classe responsavel em gerenciar o tipo de dano ou nao causado por uma habilidade.
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
public class TypeDamage  implements Serializable {

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
     private String mainAttribute;
@Resolvable
     private String actionType;
@Resolvable
     private String actionEffect;

    public TypeDamage() {
    }

	
    public TypeDamage(int id) {
        this.id = id;
    }
    public TypeDamage(int id, String name, String mainattribute, String actiontype, String actioneffect) {
       this.id = id;
       this.name = name;
       this.mainAttribute = mainattribute;
       this.actionType = actiontype;
       this.actionEffect = actioneffect;
    }
   
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
    public String getMainAttribute() {
        return this.mainAttribute;
    }
    
    public void setMainAttribute(String mainattribute) {
        this.mainAttribute = mainattribute;
    }
    public String getActionType() {
        return this.actionType;
    }
    
    public void setActionType(String actiontype) {
        this.actionType = actiontype;
    }
    public String getActionEffect() {
        return this.actionEffect;
    }
    
    public void setActionEffect(String actioneffect) {
        this.actionEffect = actioneffect;
    }




}



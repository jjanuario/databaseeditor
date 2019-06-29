/**
 * =========================================== <br>
 * PROJETO PARALLAX - Engine de Jogos 2D e RPG <br>
 * Data de alteração do arquivo: 30/10/2012 <br>
 * ===========================================
 * <P>
 * 
 * A classe <b>Npc</b> (Personagem não jogável) é a classe responsavel em gerenciar os Npcs.
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
public class Npc  implements java.io.Serializable {

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
     private Integer imagemFaceId;
    @Resolvable
     private Integer animationObjectMovimentId;

   
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
    public Integer getImagemFaceId() {
        return this.imagemFaceId;
    }
    
    public void setImagemFaceId(Integer imagemfaceid) {
        this.imagemFaceId = imagemfaceid;
    }
    public Integer getAnimationObjectMovimentId() {
        return this.animationObjectMovimentId;
    }
    
    public void setAnimationObjectMovementId(Integer animationobjectmovementid) {
        this.animationObjectMovimentId = animationobjectmovementid;
    }




}



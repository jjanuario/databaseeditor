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
public class Imagem implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Resolvable
     private int id;
    @Resolvable
     private String name;
    @Resolvable
     private String fileName;
    @Resolvable
     private Integer spriteWidth;
    @Resolvable
     private Integer spriteHeight;
    @Resolvable
     private Integer spriteStartPosX;
    @Resolvable
     private Integer spriteStartPosY;
    @Resolvable
     private String typeImage;
 
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
    public String getFilename() {
        return this.fileName;
    }
    
    public void setFileName(String filename) {
        this.fileName = filename;
    }
    public Integer getSpriteWidth() {
        return this.spriteWidth;
    }
    
    public void setSpriteWidth(Integer spritewidth) {
        this.spriteWidth = spritewidth;
    }
    public Integer getSpriteHeight() {
        return this.spriteHeight;
    }
    
    public void setSpriteHeight(Integer spriteheight) {
        this.spriteHeight = spriteheight;
    }
    public Integer getSpriteStartPosX() {
        return this.spriteStartPosX;
    }
    public void setSpriteStartPosX(Integer spriteStartPosX){
        this.spriteStartPosX = spriteStartPosX;
    }
    public void setSpriteStartPosY(Integer spritestartposy) {
        this.spriteStartPosY = spritestartposy;
    }
    public Integer getSpriteStartPosY() {
        return this.spriteStartPosY;
    }
    
       public String getTypeImage() {
        return this.typeImage;
    }
    
    public void setTypeImage(String typeimage) {
        this.typeImage = typeimage;
    }
}



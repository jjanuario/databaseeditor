/**
 * =========================================== <br>
 * PROJETO PARALLAX - Engine de Jogos 2D e RPG <br>
 * Data de alteração do arquivo: 30/10/2012 <br>
 * ===========================================
 * <P>
 * 
 * A classe <b>AnimationObject</b> (Animação dos Objetos) é a classe responsavel em gerenciar as animações do objetos contidos no jogo.
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
public class AnimationObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Resolvable(colName = "id")
    private Integer id ;
    @Resolvable(colName = "Name")
    private String name;
    @Resolvable(colName = "File Name")
    private String fileName;
    @Resolvable(colName = "Image Type")
    private String typeImage;
    @Resolvable(colName = "Sprite Width")
    private Integer spriteWidth;
    @Resolvable(colName = "Sprite Height")
    private Integer spriteHeight;
    @Resolvable(colName = "Sprite Start Pos X")
    private Integer spriteStartPosX;
    @Resolvable(colName = "Sprite Start Pos Y")
    private Integer spriteStartPosY;
    @Resolvable(colName = "Animation Total Column")
    private Integer animationTotalColumn;
    @Resolvable(colName = "Animation Total Row")
    private Integer animationTotalRow;
    @Resolvable(colName = "DurationAnimationMS")
    private Integer durationAnimationMS;

    public Integer getAnimationTotalColumn() {
        return animationTotalColumn;
    }

    public void setAnimationTotalColumn(Integer animationTotalColumn) {
        this.animationTotalColumn = animationTotalColumn;
    }

    public Integer getAnimationTotalRow() {
        return animationTotalRow;
    }

    public void setAnimationTotalRow(Integer animationTotalRow) {
        this.animationTotalRow = animationTotalRow;
    }

    public Integer getDurationAnimationMS() {
        return durationAnimationMS;
    }

    public void setDurationAnimationMS(Integer durationAnimationMS) {
        this.durationAnimationMS = durationAnimationMS;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSpriteHeight() {
        return spriteHeight;
    }

    public void setSpriteHeight(Integer spriteHeight) {
        this.spriteHeight = spriteHeight;
    }

    public Integer getSpriteStartPosX() {
        return spriteStartPosX;
    }

    public void setSpriteStartPosX(Integer spriteStartPosX) {
        this.spriteStartPosX = spriteStartPosX;
    }

    public Integer getSpriteStartPosY() {
        return spriteStartPosY;
    }

    public void setSpriteStartPosY(Integer spriteStartPosY) {
        this.spriteStartPosY = spriteStartPosY;
    }

    public Integer getSpriteWidth() {
        return spriteWidth;
    }

    public void setSpriteWidth(Integer spriteWidth) {
        this.spriteWidth = spriteWidth;
    }

    public String getTypeImage() {
        return typeImage;
    }

    public void setTypeImage(String typeImage) {
        this.typeImage = typeImage;
    }
}

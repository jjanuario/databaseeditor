/**
 * =========================================== <br>
 * PROJETO PARALLAX - Engine de Jogos 2D e RPG <br>
 * Data de alteração do arquivo: 30/10/2012 <br>
 * ===========================================
 * <P>
 * 
 * A classe <b>Skill</b> (Habilidade) é a classe responsavel em gerenciar as habilidades dos personagesn dentro do jogo.
 * 
 * @see <a
 *      href="http://www.einformacao.com.br/parallax/component/content/article/35-conteudo-site/86-documentos-para-leitura">Documentos
 *      para Leitura</a>
 * @author <a href="mailto:jeferson.januario@gmail.com">Jeferson Januario</a>
 * @version 1.0.1
 * @since Existe desde a versão 1.0.1 do projeto
 */
package game.db.entity;
// Generated Jun 20, 2012 11:13:19 PM by Hibernate Tools 3.2.1.GA

import com.towel.el.annotation.Resolvable;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Skill implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Resolvable
    private int id;
    @Resolvable
    private String name;
    @Resolvable
    private String description;
    @Resolvable
    private String resume;
    @Resolvable
    private Integer actionPoint;
    @Resolvable
    private Integer power;
    @Resolvable
    private String target;
    @Resolvable
    private Integer typeDamageId;
    @Resolvable
    private Integer imagemId;
    @Resolvable
    private Integer animationObjectId;
    @Resolvable
    private Integer classeId;
    @Resolvable
    private Integer area;
    @Resolvable
    private Integer levelLearning;

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

    public String getResume() {
        return this.resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public Integer getActionPoint() {
        return this.actionPoint;
    }

    public void setActionPoint(Integer actionpoint) {
        this.actionPoint = actionpoint;
    }

    public Integer getPower() {
        return this.power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public String getTarget() {
        return this.target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Integer getTypeDamageId() {
        return this.typeDamageId;
    }

    public void setTypeDamageId(Integer typedamageid) {
        this.typeDamageId = typedamageid;
    }

    public Integer getImagemId() {
        return this.imagemId;
    }

    public void setImagemId(Integer imagemid) {
        this.imagemId = imagemid;
    }

    public Integer getAnimationObjectId() {
        return this.animationObjectId;
    }

    public void setAnimationObjectId(Integer animationobjectid) {
        this.animationObjectId = animationobjectid;
    }

    public Integer getClasseId() {
        return this.classeId;
    }

    public void setClasseId(Integer classeid) {
        this.classeId = classeid;
    }

    public Integer getArea() {
        return this.area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getLevelLearning() {
        return this.levelLearning;
    }

    public void setLevelLearning(Integer levellearning) {
        this.levelLearning = levellearning;
    }
}

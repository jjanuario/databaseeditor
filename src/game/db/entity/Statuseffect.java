/**
 * =========================================== <br>
 * PROJETO PARALLAX - Engine de Jogos 2D e RPG <br>
 * Data de alteração do arquivo: 30/10/2012 <br>
 * ===========================================
 * <P>
 * 
 * A classe <b>Statuseffect</b> (Efeito do Status)
 * 
 * @see <a
 *      href="http://www.einformacao.com.br/parallax/component/content/article/35-conteudo-site/86-documentos-para-leitura">Documentos
 *      para Leitura</a>
 * @author <a href="mailto:jeferson.januario@gmail.com">Jeferson Januario</a>
 * @version 1.0.1
 * @since Existe desde a versão 1.0.1 do projeto
 */
package game.db.entity;

import java.io.Serializable;

public class Statuseffect  implements Serializable {


	private static final long serialVersionUID = 1L;
	private int id;
     private String name;
     private String typestatus;
     private Integer priority;
     private Integer power;
     private String typeeffect;
     private Integer chanceofsuccesspercent;
     private Integer chancetoworkinturnpercent;
     private Integer duration;
     private String onactive;
     private Integer infinite;
     private String activatedby;
     private String effectattributes;
     private String effectpower;
     private Integer imagemid;

    public Statuseffect() {
    }

	
    public Statuseffect(int id) {
        this.id = id;
    }
    public Statuseffect(int id, String name, String typestatus, Integer priority, Integer power, String typeeffect, Integer chanceofsuccesspercent, Integer chancetoworkinturnpercent, Integer duration, String onactive, Integer infinite, String activatedby, String effectattributes, String effectpower, Integer imagemid) {
       this.id = id;
       this.name = name;
       this.typestatus = typestatus;
       this.priority = priority;
       this.power = power;
       this.typeeffect = typeeffect;
       this.chanceofsuccesspercent = chanceofsuccesspercent;
       this.chancetoworkinturnpercent = chancetoworkinturnpercent;
       this.duration = duration;
       this.onactive = onactive;
       this.infinite = infinite;
       this.activatedby = activatedby;
       this.effectattributes = effectattributes;
       this.effectpower = effectpower;
       this.imagemid = imagemid;
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
    public String getTypestatus() {
        return this.typestatus;
    }
    
    public void setTypestatus(String typestatus) {
        this.typestatus = typestatus;
    }
    public Integer getPriority() {
        return this.priority;
    }
    
    public void setPriority(Integer priority) {
        this.priority = priority;
    }
    public Integer getPower() {
        return this.power;
    }
    
    public void setPower(Integer power) {
        this.power = power;
    }
    public String getTypeeffect() {
        return this.typeeffect;
    }
    
    public void setTypeeffect(String typeeffect) {
        this.typeeffect = typeeffect;
    }
    public Integer getChanceofsuccesspercent() {
        return this.chanceofsuccesspercent;
    }
    
    public void setChanceofsuccesspercent(Integer chanceofsuccesspercent) {
        this.chanceofsuccesspercent = chanceofsuccesspercent;
    }
    public Integer getChancetoworkinturnpercent() {
        return this.chancetoworkinturnpercent;
    }
    
    public void setChancetoworkinturnpercent(Integer chancetoworkinturnpercent) {
        this.chancetoworkinturnpercent = chancetoworkinturnpercent;
    }
    public Integer getDuration() {
        return this.duration;
    }
    
    public void setDuration(Integer duration) {
        this.duration = duration;
    }
    public String getOnactive() {
        return this.onactive;
    }
    
    public void setOnactive(String onactive) {
        this.onactive = onactive;
    }
    public Integer getInfinite() {
        return this.infinite;
    }
    
    public void setInfinite(Integer infinite) {
        this.infinite = infinite;
    }
    public String getActivatedby() {
        return this.activatedby;
    }
    
    public void setActivatedby(String activatedby) {
        this.activatedby = activatedby;
    }
    public String getEffectattributes() {
        return this.effectattributes;
    }
    
    public void setEffectattributes(String effectattributes) {
        this.effectattributes = effectattributes;
    }
    public String getEffectpower() {
        return this.effectpower;
    }
    
    public void setEffectpower(String effectpower) {
        this.effectpower = effectpower;
    }
    public Integer getImagemid() {
        return this.imagemid;
    }
    
    public void setImagemid(Integer imagemid) {
        this.imagemid = imagemid;
    }




}



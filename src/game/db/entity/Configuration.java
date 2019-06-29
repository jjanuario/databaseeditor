/**
 * =========================================== <br>
 * PROJETO PARALLAX - Engine de Jogos 2D e RPG <br>
 * Data de alteração do arquivo: 30/10/2012 <br>
 * ===========================================
 * <P>
 * 
 * A classe <b>Configuration</b> (Configuração) é a classe responsavel em gerenciar as configurações e conversoes de apresentação e modo do jogo.
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
public class Configuration  implements Serializable {

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
     private String fileNameLanguage;
    @Resolvable
     private Double volumeTheme;
    @Resolvable
     private Integer enableMusicTheme;
    @Resolvable
     private Double volumeThemeBattle;
    @Resolvable
     private Integer enableMusicThemeBattle;
    @Resolvable
     private Integer enableSound;
    @Resolvable
     private String mainMap;
    @Resolvable
     private Integer gameFrameWidth;
    @Resolvable
     private Integer gameFrameHeight;
    @Resolvable
     private Integer gameTargetFrameRate;
    @Resolvable
     private Integer gameDebug;
    @Resolvable
     private Integer gameFullScreen;
    @Resolvable
     private Integer gameVSYNC;
    @Resolvable
     private Integer timeDayMS;
    
    
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
    public String getFileNameLanguage() {
        return this.fileNameLanguage;
    }
    
    public void setFileNameLanguage(String filenamelanguage) {
        this.fileNameLanguage = filenamelanguage;
    }
    public Double getVolumeTheme() {
        return this.volumeTheme;
    }
    
    public void setVolumeTheme(Double volumetheme) {
        this.volumeTheme = volumetheme;
    }
    public Integer getEnableMusicTheme() {
        return this.enableMusicTheme;
    }
    
    public void setEnableMusicTheme(Integer enablemusictheme) {
        this.enableMusicTheme = enablemusictheme;
    }
    public Double getVolumeThemeBattle() {
        return this.volumeThemeBattle;
    }
    
    public void setVolumeThemeBattle(Double volumethemebattle) {
        this.volumeThemeBattle = volumethemebattle;
    }
    public Integer getEnableMusicThemeBattle() {
        return this.enableMusicThemeBattle;
    }
    
    public void setEnableMusicThemeBattle(Integer enablemusicthemebattle) {
        this.enableMusicThemeBattle = enablemusicthemebattle;
    }
    public Integer getEnableSound() {
        return this.enableSound;
    }
    
    public void setEnablesound(Integer enablesound) {
        this.enableSound = enablesound;
    }
    public String getMainMap() {
        return this.mainMap;
    }
    
    public void setMainmap(String mainmap) {
        this.mainMap = mainmap;
    }
    public Integer getGameFrameWidth() {
        return this.gameFrameWidth;
    }
    
    public void setGameFrameWidth(Integer gameframewidth) {
        this.gameFrameWidth = gameframewidth;
    }
    public Integer getGameFrameHeight() {
        return this.gameFrameHeight;
    }
    
    public void setGameFrameHeight(Integer gameframeheight) {
        this.gameFrameHeight = gameframeheight;
    }
    public Integer getGameTargetFrameRate() {
        return this.gameTargetFrameRate;
    }
    
    public void setGameTargetFrameRate(Integer gametargetframerate) {
        this.gameTargetFrameRate = gametargetframerate;
    }
    public Integer getGameDebug() {
        return this.gameDebug;
    }
    
    public void setGameDebug(Integer gamedebug) {
        this.gameDebug = gamedebug;
    }
    public Integer getGameFullScreen() {
        return this.gameFullScreen;
    }
    
    public void setGameFullScreen(Integer gamefullscreen) {
        this.gameFullScreen = gamefullscreen;
    }
    public Integer getGameVSYNC() {
        return this.gameVSYNC;
    }
    
    public void setGameVSYNC(Integer gamevsync) {
        this.gameVSYNC = gamevsync;
    }
    public Integer getTimeDayMS() {
        return this.timeDayMS;
    }
    
    public void setTimeDayMS(Integer timedayms) {
        this.timeDayMS = timedayms;
    }

}



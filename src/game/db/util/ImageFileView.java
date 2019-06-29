/**
 * =========================================== <br>
 * PROJETO PARALLAX - Engine de Jogos 2D e RPG <br>
 * Data de alteração do arquivo: 30/10/2012 <br>
 * ===========================================
 * <P>
 * 
 * A classe <b>ImageFileView</b> 
 * Cria um painel com um preview da imagem selecionada no JFileChoose
 * <p>
 * 
 * @see <a
 *      href="http://www.einformacao.com.br/parallax/component/content/article/35-conteudo-site/86-documentos-para-leitura">Documentos
 *      para Leitura</a>
 * @author <a href="mailto:jeferson.januario@gmail.com">Jeferson Januario</a>
 * @version 1.0.1
 * @since Existe desde a versão 1.0.1 do projeto
 */
package game.db.util;

import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileView;
 
/* ImageFileView.java is used by FileChooserDemo2.java. */
public class ImageFileView extends FileView {
    ImageIcon jpgIcon = Utils.createImageIcon("images/jpgIcon.gif");
    ImageIcon pngIcon = Utils.createImageIcon("images/pngIcon.png");
 
    public String getName(File f) {
        return null; //let the L&F FileView figure this out
    }
 
    public String getDescription(File f) {
        return null; //let the L&F FileView figure this out
    }
 
    public Boolean isTraversable(File f) {
        return null; //let the L&F FileView figure this out
    }
 
    public String getTypeDescription(File f) {
        String extension = Utils.getExtension(f);
        String type = null;
 
        if (extension != null) {
            if (extension.equals(Utils.jpeg) ||
                extension.equals(Utils.jpg)) {
                type = "JPEG Image";
            } else if (extension.equals(Utils.png)){
                type = "PNG Image";
            }
        }
        return type;
    }
 
    public Icon getIcon(File f) {
        String extension = Utils.getExtension(f);
        Icon icon = null;
 
        if (extension != null) {
            if (extension.equals(Utils.jpeg) ||
                extension.equals(Utils.jpg)) {
                icon = jpgIcon;
            } else if (extension.equals(Utils.png)) {
                icon = pngIcon;
            }
        }
        return icon;
    }
}

/**
 * =========================================== <br>
 * PROJETO PARALLAX - Engine de Jogos 2D e RPG <br>
 * Data de alteração do arquivo: 30/10/2012 <br>
 * ===========================================
 * <P>
 * 
 * A classe <b>Sound</b> (Som) é a classe responsavel em gerenciar os sons causados por efeitos no mapa.
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
public class Sound  implements Serializable {

 
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy= GenerationType.IDENTITY)
@Resolvable(colName="ID")
     private int id;
@Resolvable(colName="Name")
     private String name;
@Resolvable(colName="File Name")
     private String fileName;

   
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
    public String getFileName() {
        return this.fileName;
    }
    
    public void setFileName(String filename) {
        this.fileName = filename;
    }




}



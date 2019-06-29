/**
 * =========================================== <br>
 * PROJETO PARALLAX - Engine de Jogos 2D e RPG <br>
 * Data de alteração do arquivo: 30/10/2012 <br>
 * ===========================================
 * <P>
 * 
 * A classe <b>LanguageUtil</b>
 * manipula as strings para internalização.
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

public class LanguageUtil {
     private static Language languageFactory;
 
    static {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
        	if(languageFactory == null){
            languageFactory = new Language();
        	}
        	
        	
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial languageFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 /**
  * 
  * @return Retorna uma instancia da classe Language.
  */
    public static Language getLanguageFactory() {
        return languageFactory;
    }
 
}



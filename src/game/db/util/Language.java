
package game.db.util;

/**
 * =========================================== <br>
 * PROJETO PARALLAX - Engine de Jogos 2D e RPG <br>
 * Data de alteração do arquivo: 30/10/2012 <br>
 * ===========================================
 * <P>
 * 
 * A classe <b>Language</b> (Idioma)
 * manipula o idioma, baseado na escolha no configuration ou sendo informado pelo sistema operacional.
 * <p>
 * 
 * @see <a
 *      href="http://www.einformacao.com.br/parallax/component/content/article/35-conteudo-site/86-documentos-para-leitura">Documentos
 *      para Leitura</a>
 * @author <a href="mailto:jeferson.januario@gmail.com">Jeferson Januario</a>
 * @version 1.0.1
 * @since Existe desde a versão 1.0.1 do projeto
 */

import java.util.Locale;
import java.util.ResourceBundle;

public class Language{
    private String cod = "";
    private String pais = "";
        public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
    public Locale getLocaleDefault(String en, String US ){
        if(en.isEmpty() || US.isEmpty()){
            return java.util.Locale.getDefault();
        }
        return new Locale(en, US);
           
       
    }
    /**
     * 
     * @param key Chave usada para recuperar uma string Ex. chave "label.name"
     * @return Retorna a string contida na chave informada Ex. "label.name=Name:" neste caso informando a chave 'label.name' a string retornada seria Name:
     */
       public String getMessage(String key) {
       final String baseName ="game.db.util.resources/MessagesBundle";
    try{
        ResourceBundle messages = ResourceBundle.getBundle(baseName, getLocaleDefault(cod, pais ));
            return messages.getString(key);
    }catch(Exception ex){
       System.out.println(getMessage("error.language")+": '"+key+"'");
       return getMessage("error.keynotfound")+": == '" + key+"'";
      
    }

       }
   }

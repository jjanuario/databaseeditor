package game.db.dao;

import game.db.entity.TypeDamage;
import game.db.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 * =========================================== <br>
 * PROJETO PARALLAX - Data Base Editor <br>
 * Data de alteração do arquivo: 30/10/2012 <br>
 * ===========================================
 * <P>
 * 
 * A classe <b>TypeDamageDao</b> (Tipo de dano)
 * Acessa os dados dos objetos no banco.
 * <p>
 * 
 * @see <a
 *      href="http://www.einformacao.com.br/parallax/component/content/article/35-conteudo-site/86-documentos-para-leitura">Documentos
 *      para Leitura</a>
 * @author <a href="mailto:jeferson.januario@gmail.com">Jeferson Januario</a>
 * @version 1.0.1
 * @since Existe desde a versão 1.0.1 do projeto
 */
public class TypeDamageDao {
         Session session = HibernateUtil.getSessionFactory().openSession();
         List<String> list = new ArrayList<>();
    
         /**
          *@param saveOrUpDate() 
          *Salva ou altera um objeto no banco.
          */
         public void saveOrUpDate(TypeDamage TypeDamage) {
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(TypeDamage);
        tx.commit();
    }
         /**
          *@param remove() 
          *Remove um objeto armazenado no banco.
          */
    public void remove(TypeDamage rTypeDamage) {
       Transaction tx = session.beginTransaction();
        session.delete(rTypeDamage);
        tx.commit();
    }
    /**
     *@return lista() 
     *Retorna uma lista de todos os TypeDamage (Tipos de dano) contidos no banco.
     */
    @SuppressWarnings("unchecked")
	public List<TypeDamage> lista() {
        return session.createCriteria(TypeDamage.class).list();
    }
    /**
     *@return ActionType 
     *Retorna a lista de todos os ActionType (Tipo de Açoes) possiveis a serem executadas pelo personagem.
     */
    public List<String> getActionType() {
      list.clear();
      list.add("damage");
      list.add("effect");
      return list;
    }
    /**
     *@return MainAttribute
     *Retorna uma lista com todos os MainAttribute (Atributo principal), possiveis para engine utilizar como base de calculo.
     *para mais informações veja a classe CharacterHeroObjective no projeto da engine.
     */
    public List<String> getMainAttribute() {
     list.clear();
     list.add("");
     list.add("strength");
     list.add("dexterity");
     list.add("intelligence");
     return list;
    }
    /**
     *@return ActionEffect 
     *Retorna uma lista com todos os ActionEffect (Efeitos das Açoes) possiveis de serem executadas pela engine.
     */
    public List<String> getActionEffect() {
     list.clear();
     list.add("");
     list.add("value");
     list.add("percent");
     return list;
    }

}

/**
 * =========================================== <br>
 * PROJETO PARALLAX - Data Base Editor <br>
 * Data de alteração do arquivo: 30/10/2012 <br>
 * ===========================================
 * <P>
 * 
 * A classe <b>CharacterHeroStatusEffectDao</b> (CharacterHeroStatusEffectDao)
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
package game.db.dao;


import game.db.entity.CharacterHeroStatusEffect;
import game.db.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class CharacterHeroStatusEffectDao {
     Session session = HibernateUtil.getSessionFactory().openSession();

@SuppressWarnings("unchecked")
public List<CharacterHeroStatusEffect> lista(){
    return session.createCriteria(CharacterHeroStatusEffect.class).list();
}
    public void saveOrUpDate(CharacterHeroStatusEffect characterHeroStatusEffect) {
       Transaction tx = session.beginTransaction();
       session.saveOrUpdate(characterHeroStatusEffect);
       tx.commit();
    }

    public void remove(CharacterHeroStatusEffect rCharacterHeroStatusEffect) {
       Transaction tx = session.beginTransaction();
       session.delete(rCharacterHeroStatusEffect);
       tx.commit();
    }

    @SuppressWarnings("unchecked")
	public List<Integer> getCharacterHeroId() {
      return session.createSQLQuery("select ID from CHARACTERHERO").list();
    }

    @SuppressWarnings("unchecked")
	public List<Integer> getStatusEffectId() {
      return session.createSQLQuery("select ID from STATUSEFFECT").list();
    }


}

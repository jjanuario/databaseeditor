package game.db.dao;

import game.db.entity.CharacterHeroSkill;
import game.db.util.HibernateUtil;
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
 * A classe <b>CharacterHeroSkillDao</b> (Habilidade do Personagem)
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
public class CharacterHeroSkillDao {
    private final Session session = HibernateUtil.getSessionFactory().openSession();
    /**
     *@param saveOrUpDate() 
     *Salva ou altera um objeto no banco.
     */
    public void saveOrUpDate(CharacterHeroSkill CharacterHeroSkill) {
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(CharacterHeroSkill);
        tx.commit();
    }
    /**
     *@param remove() 
     *Remove um objeto armazenado no banco.
     */
    public void remove(CharacterHeroSkill chs) {
        Transaction tx = session.beginTransaction();
        session.delete(chs);
        tx.commit();
    }
    /**
     *@return lista() 
     *Retorna a lista de todos os CharacterHeroSkill contidos no banco
     */
    @SuppressWarnings("unchecked")
	public List<CharacterHeroSkill> lista() {
        return session.createCriteria(CharacterHeroSkill.class).list();
    }
    /**
     *@return getCharacterHeroId() 
     *Retorna uma lista com todos os Ids dos CharacterHero contidos no banco
     */
    @SuppressWarnings("unchecked")
	public List<Integer> getCharacterHeroId() {
      return session.createSQLQuery("select CharacterHero.ID from CharacterHero").list();
    }
    /**
     *@return getSkillId() 
     *Retorna uma lista com todos os Ids dos Skills (habilidades) contidas no banco.
     */
    @SuppressWarnings("unchecked")
	public List<Integer> getSkillId() {
         return session.createSQLQuery("select ID from SKILL").list();
    }
}

package game.db.dao;

import game.db.entity.CharacterHeroObjective;
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
 * A classe <b>CharacterHeroObjectiveDao</b> (Objetivo do Personagem)
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
public class CharacterHeroObjectiveDao {
private final Session session = HibernateUtil.getSessionFactory().openSession();
/**
 *@param saveOrUpDate() 
 *Salva ou altera um objeto no banco.
 */
    public void saveOrUpDate(CharacterHeroObjective characterHeroObjective) {
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(characterHeroObjective);
        tx.commit();
    }
    /**
     *@param remove() 
     *Remove um objeto armazenado no banco.
     */
    public void remove(CharacterHeroObjective rao) {
        Transaction tx = session.beginTransaction();
        session.delete(rao);
        tx.commit();
    }
    /**
     *@return lista() 
     *Retorna uma lista com todos os CharacterHeroObjective (Objetivo do personagem) contidos no banco
     */
    @SuppressWarnings("unchecked")
	public List<CharacterHeroObjective> lista() {
        return session.createCriteria(CharacterHeroObjective.class).list();
    }
    /**
     *@return getCharacterHeroId() 
     *Retorna uma lista de todos os Ids dos CharacterHero (Personagens) contidos no banco.
     */
    @SuppressWarnings("unchecked")
	public List<Integer> getCharacterHeroId() {
           return session.createSQLQuery("select CHARACTERHERO.ID from CHARACTERHERO").list();
    }
    /**
     *@return getObjectiveId() 
     *Retorna uma lista de todos os Ids dos Objective (Objetivos) contidos no banco.
     */
    @SuppressWarnings("unchecked")
	public List<Integer> getObjectiveId() {
          return session.createSQLQuery("select OBJECTIVE.ID from OBJECTIVE").list();
    }
    
}

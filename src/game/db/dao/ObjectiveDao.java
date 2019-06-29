package game.db.dao;

import game.db.entity.Objective;
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
 * A classe <b>ObjectiveDao</b> (Objectivo)
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
public class ObjectiveDao {
Session session = HibernateUtil.getSessionFactory().openSession();
/**
 *@return lista() 
 *Retorna uma lista com todos os Objective (Objetivo) contidos no banco
 */
@SuppressWarnings("unchecked")
public List<Objective> lista(){
    return session.createCriteria(Objective.class).list();
}
/**
 *@return getTargetObjectiveId() 
 *Retorna uma lista com todos os Ids dos NPCs (Personagens nao jogaveis) para ser o alvo de um deerminado objetivo.
 *
 */
    @SuppressWarnings("unchecked")
	public List<Integer> getTargetObjectiveId() {
        return session.createSQLQuery("select ID from NPC").list();
    }
    /**
     *@return getNpcEndObjectiveId() 
     *Retorna uma lista com todos os Ids dos NPCs (Personagens nao jogaveis) para finalizar o objetivo.
     */
    @SuppressWarnings("unchecked")
	public List<Integer> getNpcEndObjectiveId() {
         return session.createSQLQuery("select ID from NPC").list();
    }
    /**
     *@return getQuestId() 
     *Retorna uma lista com todos os Ids das Quests (MIssoes) contidas no banco.
     */
    @SuppressWarnings("unchecked")
	public List<Integer> getQuestId() {
       return session.createSQLQuery("select ID from QUEST").list();
    }
    /**
     *@return getRewardId() 
     *Retorna uma lista com todos os Ids das Rewards (Recompensas) cadastradas no banco.
     */
    @SuppressWarnings("unchecked")
	public List<Integer> getRewardId() {
         return session.createSQLQuery("select ID from REWARD").list();
    }
    /**
     *@param saveOrUpDate() 
     *Salva ou altera um objeto no banco.
     */
    public void saveOrUpDate(Objective objective) {
       Transaction tx = session.beginTransaction();
       session.saveOrUpdate(objective);
       tx.commit();
    }
    /**
     *@param remove() 
     *Remove um objeto armazenado no banco.
     */
    public void remove(Objective rObjective) {
       Transaction tx = session.beginTransaction();
       session.delete(rObjective);
       tx.commit();
    }
	/**
	 * @return
	 */
	public List<String> getTypeObjective() {
		List<String> list = new ArrayList<String>();
	    list.add("killMonster");
        list.add("speakNpc");
        list.add("goLocation");
        list.add("questActivate");
		return list;
	}
}

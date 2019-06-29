package game.db.dao;

import game.db.entity.Quest;
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
 * A classe <b>QuestDao</b> (Missao)
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
public class QuestDao {
       Session session = HibernateUtil.getSessionFactory().openSession();
    public void saveOrUpDate(Quest Quest) {
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(Quest);
        tx.commit();
    }

    public void remove(Quest rQuest) {
       Transaction tx = session.beginTransaction();
        session.delete(rQuest);
        tx.commit();
    }

    @SuppressWarnings("unchecked")
	public List<Quest> lista() {
        return session.createCriteria(Quest.class).list();
    }
    /**
     * 
     * @return getNPCId
     * Retorna uma lista contendo todos os Ids dos NPCs cadastrados.
     */
    @SuppressWarnings("unchecked")
	public List<Integer> getNpcId(){
        return session.createSQLQuery("select NPC.ID from NPC").list();
        
    }
}

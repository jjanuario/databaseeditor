package game.db.dao;

import game.db.entity.Reward;
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
 * A classe <b>RewardDao</b> (Recompensa)
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
public class RewardDao {
    Session session = HibernateUtil.getSessionFactory().openSession();
    public void saveOrUpDate(Reward reward) {
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(reward);
        tx.commit();
    }

    public void remove(Reward rReward) {
       Transaction tx = session.beginTransaction();
        session.delete(rReward);
        tx.commit();
    }

    @SuppressWarnings("unchecked")
	public List<Reward> lista() {
        return session.createCriteria(Reward.class).list();
    }

    
    
}

package game.db.dao;

import game.db.entity.MonsterSkill;
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
 * A classe <b>MonsterSkillDao</b> (Hanilidade do Monstro)
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
public class MonsterSkillDao {
       private final Session session = HibernateUtil.getSessionFactory().openSession();
    
    public void saveOrUpDate(MonsterSkill MonsterSkill) {
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(MonsterSkill);
        tx.commit();
    }

    public void remove(MonsterSkill monsterSkill) {
        Transaction tx = session.beginTransaction();
        session.delete(monsterSkill);
        tx.commit();
    }

    @SuppressWarnings("unchecked")
	public List<MonsterSkill> lista() {
        return session.createCriteria(MonsterSkill.class).list();
    }
    /**
     *@return getSkillId() 
     *Retorna uma lista com todos os Ids dos Skill (habilidades) contidos no banco.
     */
       @SuppressWarnings("unchecked")
	public List<Integer> getSkillId() {
         return session.createSQLQuery("select ID from SKILL").list();
    }
       /**
        *@return getMonsterId() 
        *Retorna uma lista com todos os Ids dos Monsters (Monstros) contidos no banco.
        */
        @SuppressWarnings("unchecked")
		public List<Integer> getMonsterId() {
         return session.createSQLQuery("select ID from MONSTER").list();
    }
}

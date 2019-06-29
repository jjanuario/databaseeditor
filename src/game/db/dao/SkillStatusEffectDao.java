package game.db.dao;

import game.db.entity.SkillStatusEffect;
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
 * A classe <b>SkillStatusEffectDao</b> (Efeito causado pela Habilidade)
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
public class SkillStatusEffectDao {
    Session session = HibernateUtil.getSessionFactory().openSession();

@SuppressWarnings("unchecked")
public List<SkillStatusEffect> lista(){
    return session.createCriteria(SkillStatusEffect.class).list();
}
    public void saveOrUpDate(SkillStatusEffect skillStatusEffectId) {
       Transaction tx = session.beginTransaction();
       session.saveOrUpdate(skillStatusEffectId);
       tx.commit();
    }

    public void remove(SkillStatusEffect rSkillStatusEffectId) {
       Transaction tx = session.beginTransaction();
       session.delete(rSkillStatusEffectId);
       tx.commit();
    }

    
	@SuppressWarnings("unchecked")
	public List<Integer> getSkillId() {
      return session.createSQLQuery("select ID from SKILL").list();
    }

    @SuppressWarnings("unchecked")
	public List<Integer> getStatusEffectId() {
      return session.createSQLQuery("select ID from STATUSEFFECT").list();
    }



}

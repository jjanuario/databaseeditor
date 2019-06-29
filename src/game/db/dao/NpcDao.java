package game.db.dao;

import game.db.entity.Npc;
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
 * A classe <b>NpcDao</b> (Personagem nao Jogavel)
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
public class NpcDao {
Session session = HibernateUtil.getSessionFactory().openSession();
    public void saveOrUpDate(Npc npc) {
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(npc);
        tx.commit();
    }

    public void remove(Npc rnpc) {
       Transaction tx = session.beginTransaction();
        session.delete(rnpc);
        tx.commit();
    }

    @SuppressWarnings("unchecked")
	public List<Npc> lista() {
        return session.createCriteria(Npc.class).list();
    }
/**
 * 
 * @return getImagemFaceId 
 * Retorna uma lista contendo todos os Ids da Classe imagem onde o tipo seja face (rosto).
 */
    
    @SuppressWarnings("unchecked")
	public List<Integer> getImagemFaceId() {
        return session.createSQLQuery("select IMAGEM.ID from IMAGEM where TYPEIMAGE='face'").list();
    }
    /**
     * 
     * @return getAnimationObjectMovimentId 
     * Retorna uma lista contendo todos os Ids da Classe getAnimationObject onde o tipo seja 'moviment' (movimento).
     */
    @SuppressWarnings("unchecked")
	public List<Integer> getAnimationObjectMovimentId() {
        return session.createSQLQuery("select ANIMATIONOBJECT.ID from ANIMATIONOBJECT where TYPEIMAGE='moviment'").list();
    }
    
}

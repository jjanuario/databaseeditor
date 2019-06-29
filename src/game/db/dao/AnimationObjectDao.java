package game.db.dao;

import game.db.entity.AnimationObject;
import game.db.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * =========================================== <br>
 * PROJETO PARALLAX - Data Base Editor <br>
 * Data de alteração do arquivo: 30/10/2012 <br>
 * ===========================================
 * <P>
 * 
 * A classe <b>AnimationObjectDao</b> (Objetos Animados)
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
public class AnimationObjectDao {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
  
    public AnimationObjectDao(){
        
              
    }
    /**
     *@return lista() 
     *Retorna a lista de todos os AnimationObject contidos no banco
     */
    
    @SuppressWarnings("unchecked")
	public List<AnimationObject> lista(){
              
       return sessionFactory.openSession().createCriteria(AnimationObject.class).list();
    }
    /**
     *@param remove() 
     *Remove um objeto armazenado no banco.
     */
    public void remove(AnimationObject animationObject){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
       session.delete(animationObject);
        tx.commit();
    }
    /**
     *@param saveOrUpDate() 
     *Salva ou altera um objeto no banco.
     */
    public void saveOrUpDate(AnimationObject ao) {
        Session session = sessionFactory.openSession();
        
		Transaction tx = session.beginTransaction();
                session.saveOrUpdate(ao);
		tx.commit();
    }
   
}

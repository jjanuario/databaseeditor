package game.db.dao;

import game.db.entity.Classe;
import game.db.util.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * =========================================== <br>
 * PROJETO PARALLAX - Data Base Editor <br>
 * Data de alteração do arquivo: 30/10/2012 <br>
 * ===========================================
 * <P>
 * 
 * A classe <b>ClasseDao</b> (ClasseDao)
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
public class ClasseDao {
    private final Session session = HibernateUtil.getSessionFactory().openSession();

    @SuppressWarnings("unchecked")
	public List<Classe> lista() {
        
        return session.createCriteria(Classe.class).list();
    }

    @SuppressWarnings("unchecked")
	public List<Integer> getAnimationObjectMovimentId() {
       
      return session.createSQLQuery("select ANIMATIONOBJECT.ID from ANIMATIONOBJECT WHERE TYPEIMAGE='moviment'").list();
    }

    @SuppressWarnings("unchecked")
	public List<Integer> getImagemBattlerId() {
        return session.createSQLQuery("select IMAGEM.ID from IMAGEM WHERE TYPEIMAGE='battle'").list();
    }

    
    @SuppressWarnings("unchecked")
	public List<Integer> getImagemFaceId() {
       return session.createSQLQuery("select IMAGEM.ID from IMAGEM WHERE TYPEIMAGE='face'").list();
    }

    public void saveOrUpDate(Classe classe) {
       Transaction tx = session.beginTransaction();
       session.saveOrUpdate(classe);
       tx.commit();
    }

     public void remove(List<Classe> list) {
        Transaction tx = session.beginTransaction();
        try{
       for(int i=0; list.size() > i; i++){
        session.delete(list.get(i));
       }}catch(HibernateException he){
           new Throwable(he);
       }
       tx.commit();
    }
    
}

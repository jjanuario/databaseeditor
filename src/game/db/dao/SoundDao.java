package game.db.dao;

import game.db.entity.Sound;
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
 * A classe <b>SoundDao</b> (Som)
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
public class SoundDao {
      private Session session = HibernateUtil.getSessionFactory().openSession();
      /**
       *@return lista() 
       *Retorna uma lista com todos os Sounds (sons) contidos no banco.
       */
      @SuppressWarnings("unchecked")
	public List<Sound> lista() {
        return session.createCriteria(Sound.class).list();
    }
      /**
       *@param remove() 
       *Remove um objeto armazenado no banco.
       */
    public void remove(Sound sound) {
Transaction tx = session.beginTransaction();
session.delete(sound);
tx.commit();
}
    /**
     *@param saveOrUpDate() 
     *Salva ou altera um objeto no banco.
     */
    public void saveOrUpDate(Sound sound) {
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(sound);
        tx.commit();
        
    }
}

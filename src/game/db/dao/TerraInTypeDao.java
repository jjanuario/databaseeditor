package game.db.dao;

import game.db.entity.TerraInType;
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
 * A classe <b>TerraInTypeDao</b> (Tipo da terra)
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
public class TerraInTypeDao {
     Session session = HibernateUtil.getSessionFactory().openSession();
     /**
      *@param saveOrUpDate() 
      *Salva ou altera um objeto no banco.
      */
     public void saveOrUpDate(TerraInType TerraInType) {
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(TerraInType);
        tx.commit();
    }
    /**
     *@param remove() 
     *Remove um objeto armazenado no banco.
     */
    public void remove(TerraInType rTerraInType) {
       Transaction tx = session.beginTransaction();
        session.delete(rTerraInType);
        tx.commit();
    }
    /**
     *@return lista() 
     *Retorna uma lista de todos os TerraInType (Tipo da terra) contidos no banco
     */
    @SuppressWarnings("unchecked")
	public List<TerraInType> lista() {
        return session.createCriteria(TerraInType.class).list();
    }

    
}

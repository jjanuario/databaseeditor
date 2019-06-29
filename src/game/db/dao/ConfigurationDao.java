package game.db.dao;

import game.db.entity.Configuration;
import game.db.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * =========================================== <br>
 * PROJETO PARALLAX - Data Base Editor <br>
 * Data de alteração do arquivo: 30/10/2012 <br>
 * ===========================================
 * <P>
 * 
 * A classe <b>ConfigurationDao</b> (Configurações)
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
public class ConfigurationDao {
Session session = HibernateUtil.getSessionFactory().openSession();
    public Configuration getConfiguration() {
        return (Configuration) session.createCriteria(Configuration.class).uniqueResult();
    }

    public void upDate(Configuration configuration) {
         Transaction tx = session.beginTransaction();
        session.update(configuration);
        tx.commit();
    }
    
}

package game.db.dao;

import game.db.entity.Monster;
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
 * A classe <b>MonsterDao</b> (MonsterDao)
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
public class MonsterDao {
    Session session = HibernateUtil.getSessionFactory().openSession();   
    @SuppressWarnings("unchecked")
	public List<Monster> lista(){
   
        return session.createCriteria(Monster.class).list();
    }

    public void saveOrUpDate(Monster monster) {
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(monster);
        tx.commit();
    }

    public void remove(List<Monster> list) {
        Transaction tx = session.beginTransaction();
        for (int index =0; list.size() > index; index++) {
             session.delete(list.get(index));
        }
            tx.commit();
    }

/**
 * 
 * @return getAnimationObjectMovimentId
 * Retorna uma lista com todos os Ids de Animações onde o typo seja 'moviment'.
 */
    @SuppressWarnings("unchecked")
	public List<Integer> getAnimationObjectMovimentId() {
        return session.createSQLQuery("select AnimationObject.ID from AnimationObject where TypeImage ='moviment'").list();
        
    }
    /**
     * 
     * @return getImagemBattlerId
     * Retorna uma lista com todos os Ids de Imagens onde o tipo seja 'battle'.
     */
    @SuppressWarnings("unchecked")
	public List<Integer> getImagemBattlerId() {
       return session.createSQLQuery("select IMAGEM.ID from Imagem where TypeImage ='battle'").list();
      
    }
    /**
     * 
     * @return getTerraInTypeId
     * Retorna uma lista com todos os Ids de TerraIn Type (tipo da terra).
     */
    @SuppressWarnings("unchecked")
	public List<Integer> getTerraInTypeId() {
       return session.createSQLQuery("select TerraInType.ID from TerraInType").list();
    }
    /**
     * 
     * @return getImagemFaceId
     * Retorna uma lista com todos os Ids de Imagens onde o tipo seja 'face'.
     */
    @SuppressWarnings("unchecked")
	public List<Integer> getImagemFaceId() {
        return session.createSQLQuery("select Imagem.ID from Imagem where TypeImage='face'").list();
    }
    /**
     * 
     * @return getClasse
     * Retorna uma lista com todos os nomes das classes existentes no jogo.
     */
    @SuppressWarnings("unchecked")
	public List<String> getClasse() {
        
        return session.createSQLQuery("select CLASSE.TYPECLASS from CLASSE").list();
    }
    
}

package game.db.dao;

import game.db.entity.CharacterHero;
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
 * A classe <b>CharacterHeroDao</b> (Personagem)
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
public class CharacterHeroDao {
	Session session = HibernateUtil.getSessionFactory().openSession();
    /**
     *@return lista() 
     *Retorna a lista de todos os CharacterHero contidos no banco
     */
    
	@SuppressWarnings("unchecked")
	public List<CharacterHero> lista() {
		try {
			return session.createCriteria(CharacterHero.class).list();
		} catch (HibernateException he) {
			he.printStackTrace();
		}
		return null;
	}
	  /**
     *@param saveOrUpDate() 
     *Salva ou altera um objeto no banco.
     */
	public void saveOrUpDate(CharacterHero characterHero) {
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(characterHero);
		tx.commit();
	}
	  /**
     *@param remove() 
     *Remove um objeto armazenado no banco.
     */
	public void remove(List<CharacterHero> list) {
		Transaction tx = session.beginTransaction();
		for (int index = 0; list.size() > index; index++) {
			session.delete(list.get(index));
		}
		tx.commit();
	}

	@SuppressWarnings("unchecked")
	public List<String> getTypeClass() {

		return session.createSQLQuery("select Classe.TypeClass from Classe")
				.list();

	}
	@SuppressWarnings("unchecked")
	public List<Integer> getAnimationObjectMovimentId() {
		return session
				.createSQLQuery(
						"select AnimationObject.ID from AnimationObject where TypeImage ='moviment'")
				.list();

	}

	@SuppressWarnings("unchecked")
	public List<Integer> getImagemBattlerId() {
		return session.createSQLQuery(
				"select IMAGEM.ID from Imagem where TypeImage ='battle'")
				.list();

	}

	@SuppressWarnings("unchecked")
	public List<Integer> getTerraInTypeId() {
		return session.createSQLQuery("select ID from TERRAINTYPE")
				.list();
	}

	@SuppressWarnings("unchecked")
	public List<Integer> getImagemFaceId() {
		return session.createSQLQuery(
				"select ID from IMAGEM where TypeImage='face'")
				.list();
	}

	@SuppressWarnings("unchecked")
	public List<Integer> getClasseId() {

		return session.createSQLQuery("select CLASSE.ID from CLASSE")
				.list();
	}

}

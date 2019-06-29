package game.db.dao;

import game.db.entity.Skill;
import game.db.util.HibernateUtil;
import java.util.ArrayList;
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
 * A classe <b>SkillDao</b> (Habilidades)
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
public class SkillDao {
    Session session = HibernateUtil.getSessionFactory().openSession();
    /**
     *@return lista() 
     *Retorna uma lista com todos os Skill (Habilidades) contidos no banco
     */
@SuppressWarnings("unchecked")
public List<Skill> lista(){
    return session.createCriteria(Skill.class).list();
}
/**
 *@param saveOrUpDate() 
 *Salva ou altera um objeto no banco.
 */
    public void saveOrUpDate(Skill Skill) {
       Transaction tx = session.beginTransaction();
       session.saveOrUpdate(Skill);
       tx.commit();
    }
    /**
     *@param remove() 
     *Remove um objeto armazenado no banco.
     */
    public void remove(Skill rSkill) {
       Transaction tx = session.beginTransaction();
       session.delete(rSkill);
       tx.commit();
    }
    /**
     *@return getTypeDamageId() 
     *Retorna uma lista de todos os Ids dos TypeDamage (Tipo de dano) contidos no banco.
     */
    @SuppressWarnings("unchecked")
	public List<Integer> getTypeDamageId() {
      return session.createSQLQuery("select ID from TYPEDAMAGE").list();
    }
    /**
     *@return getAnimationObjectId() 
     *Retorna uma lista de todos os Ids dos AnimationObject (Objetos Animados) contidos no banco.
     */
    @SuppressWarnings("unchecked")
	public List<Integer> getAnimationObjectId() {
return session.createSQLQuery("select ID from ANIMATIONOBJECT").list();
    }
    /**
     *@return getClasseId() 
     *Retorna uma lista de todos os Ids dos Classe (Classes,ex. Warrior,Guerreiro,Ninja ) contidos no banco.
     */
    @SuppressWarnings("unchecked")
	public List<Integer> getClasseId() {
        return session.createSQLQuery("select ID from CLASSE").list();
    }
    /**
     *@return getImagemId() 
     *Retorna uma lista de todos os Ids das Imagens cadastradas no banco.
     */
    @SuppressWarnings("unchecked")
	public List<Integer> getImagemId() {
      return session.createSQLQuery("select ID from IMAGEM").list();
    }
    /**
     *@return getTarget() 
     *Retorna uma lista de string com todas as possibilidades de alvo da habilidade.
     */
    public List<String> getTarget() {
       List<String> list = new ArrayList<String>();
        list.add("enemy");
        list.add("self");
        return list;
    }


   
}

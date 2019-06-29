/**
 * =========================================== <br>
 * PROJETO PARALLAX - Engine de Jogos 2D e RPG <br>
 * Data de alteração do arquivo: 30/10/2012 <br>
 * ===========================================
 * <P>
 * 
 * A classe <b>TerraInType</b> (Tipo da terra) é a classe responsavel em gerenciar o tipo do terreno jogavel.
 * 
 * @see <a
 *      href="http://www.einformacao.com.br/parallax/component/content/article/35-conteudo-site/86-documentos-para-leitura">Documentos
 *      para Leitura</a>
 * @author <a href="mailto:jeferson.januario@gmail.com">Jeferson Januario</a>
 * @version 1.0.1
 * @since Existe desde a versão 1.0.1 do projeto
 */
package game.db.entity;

import com.towel.el.annotation.Resolvable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TerraInType implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Resolvable
	private int id;
	@Resolvable
	private String name;
	@Resolvable
	private Integer canWalk;
	@Resolvable
	private Integer canSwim;
	@Resolvable
	private Integer canFly;
	@Resolvable
	private Integer pvm;

	public TerraInType() {
	}

	public TerraInType(int id) {
		this.id = id;
	}

	public TerraInType(int id, String name, Integer canwalk, Integer canswim,
			Integer canfly, Integer pvm) {
		this.id = id;
		this.name = name;
		this.canWalk = canwalk;
		this.canSwim = canswim;
		this.canFly = canfly;
		this.pvm = pvm;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCanWalk() {
		return this.canWalk;
	}

	public void setCanWalk(Integer canwalk) {
		this.canWalk = canwalk;
	}

	public Integer getCanswim() {
		return this.canSwim;
	}

	public void setCanSwim(Integer canswim) {
		this.canSwim = canswim;
	}

	public Integer getCanfly() {
		return this.canFly;
	}

	public void setCanFly(Integer canfly) {
		this.canFly = canfly;
	}

	public Integer getPvm() {
		return this.pvm;
	}

	public void setPvm(Integer pvm) {
		this.pvm = pvm;
	}

}

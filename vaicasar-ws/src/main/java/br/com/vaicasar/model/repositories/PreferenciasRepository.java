package br.com.vaicasar.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.vaicasar.model.entity.Categoria;
import br.com.vaicasar.model.entity.Loja;
import br.com.vaicasar.model.entity.Preferencias;

public interface PreferenciasRepository extends JpaRepository<Preferencias, Long> {

	@Query(value = "SELECT pre "
			+ " FROM Preferencias pre "
			+ " WHERE pre.id = :id ")
	public Preferencias obterPorId(@Param("id") Long id);
	
	@Query(value = "SELECT pre "
			+ " FROM Preferencias pre "
			+ " INNER JOIN pre.pessoa pes "
			+ " INNER JOIN pre.loja lo "
			+ " WHERE pes.id = :idPessoa "
			+ " AND lo.id = :idLoja ")
	public Preferencias obterParaRemoverLoja(@Param("idPessoa") Long idPessoa, @Param("idLoja") Long idLoja);
	
	@Query(value = "SELECT lo "
			+ " FROM Preferencias pre "
			+ " INNER JOIN pre.loja lo "
			+ " INNER JOIN lo.categoria ca "
			+ " WHERE ca.id = :idCategoria ")
	public List<Loja> obterPorCategoria(@Param("idCategoria") Long idCategoria);
	
	@Query(value = "SELECT DISTINCT ca "
			+ " FROM Preferencias pre "
			+ " INNER JOIN pre.loja lo "
			+ " INNER JOIN lo.categoria ca "
			+ " INNER JOIN pre.pessoa pe "
			+ " WHERE pe.id = :idPessoa ")
	public List<Categoria> obterCategoriasDaListaPorPessoa(@Param("idPessoa") Long idPessoa);
	
	@Query(value = "SELECT pre.id "
			+ " FROM Preferencias pre "
			+ " INNER JOIN pre.pessoa pe "
			+ " WHERE pe.id = :idPessoa ")
	public List<Long> obterIdsPreferencias(@Param("idPessoa") Long idPessoa);
	
	@Modifying
	@Query(value = "DELETE FROM Preferencias WHERE id IN(:idsPreferencias) ")
	public Integer limparLista(@Param("idsPreferencias") List<Long> idsPreferencias);
	
	@Query(value = "SELECT COUNT(pre) "
			+ " FROM Preferencias pre "
			+ " INNER JOIN pre.pessoa pes "
			+ " INNER JOIN pre.loja lo "
			+ " WHERE pes.id = :idPessoa "
			+ " AND lo.id = :idLoja ")
	public Integer lojaAdicionada(@Param("idPessoa") Long idPessoa, @Param("idLoja") Long idLoja);
	
}

package ar.com.clovinn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.clovinn.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

	Optional<Producto> findByCodigo(String codigo);

}

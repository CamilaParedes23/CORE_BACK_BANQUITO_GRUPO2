package com.banquito.core.transactions.repository;

import com.banquito.core.transactions.enums.TipoMovimientoEnum;
import com.banquito.core.transactions.model.TransaccionInstitucional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TransaccionInstitucionalRepository extends JpaRepository<TransaccionInstitucional, Long> {

    @Query("""
            SELECT t
            FROM TransaccionInstitucional t
            WHERE t.uuidGrupoOperacion = :uuidGrupoOperacion
              AND t.referenciaExterna = :referenciaExterna
              AND t.subtipoTransaccion.codigo = :codigoSubtipo
              AND t.tipoMovimiento = :tipoMovimiento
            ORDER BY t.id DESC
            """)
    List<TransaccionInstitucional> findLiquidacionInstitucionalPorGrupoReferenciaSubtipoTipo(
            @Param("uuidGrupoOperacion") UUID uuidGrupoOperacion,
            @Param("referenciaExterna") String referenciaExterna,
            @Param("codigoSubtipo") String codigoSubtipo,
            @Param("tipoMovimiento") TipoMovimientoEnum tipoMovimiento
    );
}

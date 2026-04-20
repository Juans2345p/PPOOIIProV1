package lab02.demo.Repositoies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lab02.demo.Entities.*;
import java.util.List;


@Repository
public interface VehiculoDocumentoRepository extends JpaRepository<VehiculoDocumento, Long>{
    List<VehiculoDocumento> findByDocumento_Id(Long documentoId);
    List<VehiculoDocumento> findByDocumento_Nombre(String nombreDoc);
    List<VehiculoDocumento> findByEstado(String estado);
    List<VehiculoDocumento> findByVehiculo_Id(Long vehiculoId);
}

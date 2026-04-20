package lab02.demo.Services;

import lab02.demo.Repositoies.*;
import lab02.demo.Entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehiculoDocumentoService {

    @Autowired
    private VehiculoDocumentoRepository vehiculoDocumentoRepository;

    // REQUERIMIENTO: Servicio que permita agregar documentos asociados a un vehículo
    public VehiculoDocumento asignarDocumento(Vehiculo vehiculo, Documento documento, String fechaExp, String fechaVen) {
        VehiculoDocumento relacion = new VehiculoDocumento();
        relacion.setVehiculo(vehiculo);
        relacion.setDocumento(documento);
        
        // REQUERIMIENTO: Estado inicial siempre "En Verificación"
        relacion.setEstado("En Verificación");
        
        // Aquí convertirías los Strings a LocalDate
        // relacion.setFechaExpedicion(...);
        
        return vehiculoDocumentoRepository.save(relacion);
    }

    // REQUERIMIENTO: Buscar por estado del documento
    public List<Vehiculo> obtenerVehiculosPorEstadoDoc(String estado) {
        return vehiculoDocumentoRepository.findByEstado(estado)
                .stream()
                .map(VehiculoDocumento::getVehiculo)
                .distinct()
                .collect(Collectors.toList());
    }

    // REQUERIMIENTO: Buscar por tipo de documento en común
    public List<Vehiculo> obtenerVehiculosPorTipoDoc(Long documentoId) {
        return vehiculoDocumentoRepository.findByDocumento_Id(documentoId)
                .stream()
                .map(VehiculoDocumento::getVehiculo)
                .distinct()
                .collect(Collectors.toList());
    }

    //Ver aqui
    @Autowired
    private VehiculoRepository vehiculoRepository;
    @Transactional
    public VehiculoDocumento agregarDocumentoAVehiculo(Long vehiculoId, VehiculoDocumento nuevaRelacion) {
        // 1. Verificar que el vehículo existe
        Vehiculo vehiculo = vehiculoRepository.findById(vehiculoId)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado con ID: " + vehiculoId));

        // 2. Vincular el vehículo a la relación
        nuevaRelacion.setVehiculo(vehiculo);

        // 3. REQUERIMIENTO: Estado inicial siempre "En Verificación"
        nuevaRelacion.setEstado("En Verificación");

        return vehiculoDocumentoRepository.save(nuevaRelacion);
    }
}
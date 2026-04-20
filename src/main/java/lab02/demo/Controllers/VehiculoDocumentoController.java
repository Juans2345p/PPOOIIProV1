package lab02.demo.Controllers;

import lab02.demo.Entities.*;
import lab02.demo.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vehiculo-documentos")
public class VehiculoDocumentoController {

    @Autowired
    private VehiculoDocumentoService vdService;

    // REQUERIMIENTO: Buscar vehículos según el estado (Habilitado, Vencido, etc.)
    @GetMapping("/estado/{estado}")
    public List<Vehiculo> buscarPorEstado(@PathVariable String estado) {
        if(estado.equals("0"))
            return vdService.obtenerVehiculosPorEstadoDoc("En Verificación");
        else
            return vdService.obtenerVehiculosPorEstadoDoc(estado);
    }

    // REQUERIMIENTO: Buscar vehículos que tengan en común un tipo de documento
    @GetMapping("/tipo-documento/{id}")
    public List<Vehiculo> buscarPorTipoDocumento(@PathVariable Long id) {
        return vdService.obtenerVehiculosPorTipoDoc(id);
    }

    // REQUERIMIENTO: Servicio para agregar documentos a un vehículo
    @PostMapping("/asignar")
    public String asignarDocumento(@RequestBody Map<String, Object> datos) {
        // Lógica para recibir vehiculoId, documentoId y fechas
        return "Documento asignado exitosamente con estado 'En Verificación'";
    }

    @Autowired
    private VehiculoDocumentoService service;

    @PostMapping("/vehiculo/{id}")
    public ResponseEntity<VehiculoDocumento> agregar(@PathVariable Long id, @RequestBody VehiculoDocumento relacion) {
        VehiculoDocumento guardado = service.agregarDocumentoAVehiculo(id, relacion);
        return new ResponseEntity<>(guardado, HttpStatus.CREATED);
    }


    //CRUD
}
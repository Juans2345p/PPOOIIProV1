package lab02.demo.Controllers;

import lab02.demo.Entities.*;
import lab02.demo.Services.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    // REQUERIMIENTO: POST con documentos obligatorios y estado inicial "En Verificación"
    @PostMapping
    public ResponseEntity<Vehiculo> crear(@RequestBody Vehiculo nuevoVehiculo) {
        // Llamamos al servicio para que ejecute la lógica de negocio
        Vehiculo guardado = vehiculoService.guardarVehiculo(nuevoVehiculo);

        // Retornamos el objeto guardado con el estado 201 (Created)
        return new ResponseEntity<>(guardado, HttpStatus.CREATED);
    }

    // REQUERIMIENTO: Buscar por placa
    @GetMapping("/placa/{placa}")
    public ResponseEntity<Vehiculo> buscarPorPlaca(@PathVariable String placa) {
        return ResponseEntity.ok(vehiculoService.buscarPorPlaca(placa));
    }

    // REQUERIMIENTO: Buscar por tipo (auto/moto)
    @GetMapping("/tipo/{tipo}")
    public List<Vehiculo> buscarPorTipo(@PathVariable String tipo) {
        if(tipo.equals("0"))
            return vehiculoService.buscarPorTipo("auto");
        else
            return vehiculoService.buscarPorTipo(tipo);
    }
}
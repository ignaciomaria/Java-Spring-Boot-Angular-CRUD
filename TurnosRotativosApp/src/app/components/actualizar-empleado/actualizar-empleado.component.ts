import { Component, Inject } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { EmpleadoServiceService } from '../service/empleado.service.service';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-actualizar-empleado',
  templateUrl: './actualizar-empleado.component.html',
  styleUrls: ['./actualizar-empleado.component.css']
})
export class ActualizarEmpleadoComponent {

    
  empleadoForm: FormGroup;

  nombreYApellidoPattern = new RegExp(/^[ A-ZÑa-zñáéíóúÁÉÍÓÚ/'/`]+$/);
  
  constructor(private empleadoService: EmpleadoServiceService,
              private formB: FormBuilder,
              @Inject(MAT_DIALOG_DATA) public idEmpleado:number,
              private _snackBar: MatSnackBar){
    this.empleadoForm = formB.group({
      nroDocumento: new FormControl('', [Validators.min(12000000), Validators.max(99999999)]),
      nombre: new FormControl('', [Validators.pattern(this.nombreYApellidoPattern)]),
      apellido: new FormControl('', [Validators.pattern(this.nombreYApellidoPattern)]),
      email: new FormControl('', [Validators.pattern('^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@' 
                                                  + '[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$')]),
      fechaNacimiento: new FormControl(''),
      fechaIngreso: new FormControl(''),
    });  
  }

  ngOnInit(): void {}

  onSubmit(){
    this.empleadoService.updateEmpleado(this.idEmpleado, this.empleadoForm.value).subscribe({
      next: () =>{
        this._snackBar.open('Empleado actualizado con exito', 'OK',{duration: 3000});
      }
    });
  }
}

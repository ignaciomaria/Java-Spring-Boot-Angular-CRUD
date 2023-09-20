import { Component, OnInit } from '@angular/core';
import { EmpleadoServiceService } from '../service/empleado.service.service';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-crear-empleado',
  templateUrl: './crear-empleado.component.html',
  styleUrls: ['./crear-empleado.component.css']
})
export class CrearEmpleadoComponent implements OnInit{
  
  empleadoForm: FormGroup;

  nombreYApellidoPattern = new RegExp(/^[ A-ZÑa-zñáéíóúÁÉÍÓÚ/'/`]+$/);

  constructor(private empleadoService: EmpleadoServiceService,
              private formB: FormBuilder,
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

  ngOnInit(): void {
    
  }

  onSubmit(){
    this.empleadoService.addEmpleado(this.empleadoForm.value).subscribe({
      next: () =>{
        this._snackBar.open('Empleado registrado con exito', 'OK',{duration: 3000});
        console.log('ok');
      },
      error: (error) =>{
        console.log('Error: ', error);
      }
    });
  }
}

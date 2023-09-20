import { Component, Inject } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { EmpleadoServiceService } from '../service/empleado.service.service';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-actualizar-empleado',
  templateUrl: './actualizar-empleado.component.html',
  styleUrls: ['./actualizar-empleado.component.css']
})
export class ActualizarEmpleadoComponent {

    
  empleadoForm: FormGroup;

  constructor(private empleadoService: EmpleadoServiceService,
              private formB: FormBuilder,
              @Inject(MAT_DIALOG_DATA) public idEmpleado:number){
    this.empleadoForm = formB.group({
      nroDocumento: new FormControl(''),
      nombre: new FormControl(''),
      apellido: new FormControl(''),
      email: new FormControl(''),
      fechaNacimiento: new FormControl(''),
      fechaIngreso: new FormControl(''),
    });  
  }

  ngOnInit(): void {
    
  }
  /*
  onSubmitCrear(){
    this.empleadoService.addEmpleado(this.empleadoForm.value).subscribe({});
  }*/

  onSubmit(){
    this.empleadoService.updateEmpleado(this.idEmpleado, this.empleadoForm.value).subscribe({});
  }
}

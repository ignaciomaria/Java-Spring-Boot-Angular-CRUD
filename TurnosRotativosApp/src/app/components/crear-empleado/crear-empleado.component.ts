import { Component, OnInit } from '@angular/core';
import { EmpleadoServiceService } from '../service/empleado.service.service';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-crear-empleado',
  templateUrl: './crear-empleado.component.html',
  styleUrls: ['./crear-empleado.component.css']
})
export class CrearEmpleadoComponent implements OnInit{
  
  empleadoForm: FormGroup;

  constructor(private empleadoService: EmpleadoServiceService,
              private formB: FormBuilder){
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

  onSubmit(){
    this.empleadoService.addEmpleado(this.empleadoForm.value).subscribe({});
  }
}

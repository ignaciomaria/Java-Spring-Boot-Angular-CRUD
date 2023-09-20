import { Component, Inject } from '@angular/core';
import { EmpleadoServiceService } from '../service/empleado.service.service';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-eliminar-empleado',
  templateUrl: './eliminar-empleado.component.html',
  styleUrls: ['./eliminar-empleado.component.css']
})
export class EliminarEmpleadoComponent {

  constructor(private empleadoService: EmpleadoServiceService,
              @Inject(MAT_DIALOG_DATA) public idEmpleado:number,
              private _snackBar: MatSnackBar){}

  deleteEmpleado(){
    this.empleadoService.deleteEmpleado(this.idEmpleado).subscribe({
      next: () =>{
        this._snackBar.open('Empleado eliminado' , 'OK',{duration: 3000});
      }
    });
  }
}

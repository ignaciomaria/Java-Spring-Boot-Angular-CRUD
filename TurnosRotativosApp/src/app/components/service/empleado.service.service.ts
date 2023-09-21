import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable, throwError } from 'rxjs';
import { environment } from 'src/app/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EmpleadoServiceService {
  
  apiUrl:string = environment.apiTurnosRotativos; //url de backend
  
  constructor(private HTTP: HttpClient,
              private _snackBar: MatSnackBar) { }

  //Agregar empleado
  addEmpleado(empleado: any): Observable<any>{ //any permite asignar cualquier tipo de valor
    return this.HTTP.post(this.apiUrl, empleado);
  }
  //Traer todos los empleados
  getAllEmpleados(): Observable<any>{
    return this.HTTP.get(this.apiUrl);
  }
  //Actualizar empleado
  updateEmpleado(id:number, empleado:any): Observable<any>{
    return this.HTTP.put(`${this.apiUrl}/${id}`, empleado);
  }
  //Eliminar empleado
  deleteEmpleado(id:number): Observable<any>{
    return this.HTTP.delete(`${this.apiUrl}/${id}`);
  }

  //Informa el tipo de error al usuario
  errorHandler(error: HttpErrorResponse){
    if(error instanceof HttpErrorResponse){
      if(error instanceof ErrorEvent){
        console.log('Error del cliente');
      }else{
        console.log('Error del servidor');
        alert(error.error.message);
      }
    }else{
      console.log('Otro error');
    }
    return throwError(()=>error); 
  }
}

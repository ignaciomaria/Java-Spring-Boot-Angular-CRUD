import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/app/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EmpleadoServiceService {
  
  apiUrl:string = environment.apiTurnosRotativos; //url de backend
  
  constructor(private HTTP: HttpClient) { }

  //Agregar empleado
  addEmpleado(empleado: any): Observable<any>{ //any permite asignar cualquier tipo de valor
    return this.HTTP.post(this.apiUrl, empleado).pipe(
      catchError((error) =>{
        console.log(error);
        return this.errorHandler(error);
      })
    );
  }
  //Traer todos los empleados
  getAllEmpleados(): Observable<any>{
    return this.HTTP.get(this.apiUrl);
  }
  //Actualizar empleado
  updateEmpleado(id:number, empleado:any): Observable<any>{
    return this.HTTP.put(`${this.apiUrl}/${id}`, empleado).pipe(
      catchError((error) =>{
        console.log(error);
        return this.errorHandler(error);
      })
    );
  }
  //Eliminar empleado
  deleteEmpleado(id:number): Observable<any>{
    return this.HTTP.delete(`${this.apiUrl}/${id}`);
  }

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

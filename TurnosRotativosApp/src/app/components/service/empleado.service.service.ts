import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/app/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EmpleadoServiceService {
  
  apiUrl:string = environment.apiTurnosRotativos; //url de backend
  
  constructor(private HTTP: HttpClient) { }

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
}

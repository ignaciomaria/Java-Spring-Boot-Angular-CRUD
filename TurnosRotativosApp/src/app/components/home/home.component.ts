import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Empleado } from 'src/app/models/empleado_model';
import { EmpleadoServiceService } from '../service/empleado.service.service';
import { CrearEmpleadoComponent } from '../crear-empleado/crear-empleado.component';
import { ActualizarEmpleadoComponent } from '../actualizar-empleado/actualizar-empleado.component';
import { MatDialog } from '@angular/material/dialog';
import { EliminarEmpleadoComponent } from '../eliminar-empleado/eliminar-empleado.component';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  displayedColumns: string[] = [
    'id', 
    'nombre', 
    'apellido', 
    'nroDocumento', 
    'email', 
    'fechaNacimiento', 
    'fechaIngreso',
    'fechaCreacion',
    'acciones'];
  
  dataSource = new MatTableDataSource<Empleado>([]);

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  
  constructor(private empleadoService: EmpleadoServiceService,
              private dialogF: MatDialog){}

  ngOnInit(): void{
    this.getAll();
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  agregarEmpleado(){
    this.dialogF.open(CrearEmpleadoComponent, {height: 'auto', width: '600px', disableClose: true}).afterClosed().subscribe({
      next:() => {
        this.getAll();
      }
    })
    
  }

  actualizarEmpleado(id:number){
    this.dialogF.open(ActualizarEmpleadoComponent,{height: 'auto', width: '600px', disableClose: true, data:id}).afterClosed().subscribe({
      next:() => {
        this.getAll();
      },
    });  
  }

  eliminarEmpleado(id:number){
    this.dialogF.open(EliminarEmpleadoComponent, {width: '250px', data:id}).afterClosed().subscribe({
      next:() =>{
        this.getAll();
      }
    });
  }

  getAll(){
    this.empleadoService.getAllEmpleados().subscribe({
      next: (empl: Empleado[]) => {
        this.dataSource.data = empl;
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
        console.log(empl);
      }
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}

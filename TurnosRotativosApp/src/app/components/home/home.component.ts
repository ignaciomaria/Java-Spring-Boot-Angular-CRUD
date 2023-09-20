import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Empleado } from 'src/app/models/empleado_model';
import { EmpleadoServiceService } from '../service/empleado.service.service';
import { CrearEmpleadoComponent } from '../crear-empleado/crear-empleado.component';
import { ActualizarEmpleadoComponent } from '../actualizar-empleado/actualizar-empleado.component';
import { MatDialog } from '@angular/material/dialog';

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
    this.dialogF.open(CrearEmpleadoComponent, {height: 'auto', width: '600px'}).afterClosed().subscribe({
      next:() => {
        this.getAll();
        console.log('Agregar');
      }
    })
    
  }

  actualizarEmpleado(id:number){
    this.dialogF.open(ActualizarEmpleadoComponent,{height: 'auto', width: '600px', data:id}).afterClosed().subscribe({
      next:() => {
        this.getAll();
        console.log('Editar');
      }
    });  
  }

  eliminarEmpleado(id:number){
    this.empleadoService.deleteEmpleado(id).subscribe({
      next:() =>{
        this.getAll();
        console.log('Eliminar');
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
      },
      error: (e) =>{
        console.log(e);
        this.dataSource.data = [];
      }
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    /*if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }*/
  }
}

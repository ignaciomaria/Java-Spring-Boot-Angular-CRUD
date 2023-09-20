import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SharedModule } from './components/shared/shared.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeComponent } from './components/home/home.component';
import { HttpClientModule } from '@angular/common/http';
import { CrearEmpleadoComponent } from './components/crear-empleado/crear-empleado.component';
import { ReactiveFormsModule } from '@angular/forms';
import { ActualizarEmpleadoComponent } from './components/actualizar-empleado/actualizar-empleado.component';
import { EliminarEmpleadoComponent } from './components/eliminar-empleado/eliminar-empleado.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    CrearEmpleadoComponent,
    ActualizarEmpleadoComponent,
    EliminarEmpleadoComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    SharedModule,
    BrowserAnimationsModule, 
    HttpClientModule,
    ReactiveFormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

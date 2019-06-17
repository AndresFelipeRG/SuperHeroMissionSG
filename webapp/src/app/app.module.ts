import { AddSuperHeroComponent } from './add-superhero/add-superhero.component';
import { SuperHeroComponent } from './superhero/superhero.component';
import { SuperHeroService } from './superhero/superhero.service';
import { HttpClientModule }    from '@angular/common/http';
import { MissionService } from './mission/mission.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {MissionComponent} from './mission/mission.component';
import {AddMissionComponent} from './add-mission/add-mission.component';
import { ReactiveFormsModule } from '@angular/forms';
@NgModule({
  declarations: [
    AppComponent, MissionComponent, AddMissionComponent, SuperHeroComponent, AddSuperHeroComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [MissionService, SuperHeroService],
  bootstrap: [AppComponent]
})
export class AppModule { }

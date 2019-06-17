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
import {FormsModule} from '@angular/forms';
import { MissionDetailsComponent } from './mission-details/mission-details.component';
@NgModule({
  declarations: [
    AppComponent, MissionComponent, AddMissionComponent, SuperHeroComponent, AddSuperHeroComponent,
    MissionDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [MissionService, SuperHeroService],
  bootstrap: [AppComponent]
})
export class AppModule { }

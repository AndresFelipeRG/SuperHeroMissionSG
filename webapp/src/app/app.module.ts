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
    AppComponent, MissionComponent, AddMissionComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [MissionService],
  bootstrap: [AppComponent]
})
export class AppModule { }

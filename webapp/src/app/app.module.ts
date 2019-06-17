import { HttpClientModule }    from '@angular/common/http';
import { MissionService } from './mission/mission.service';

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {MissionComponent} from './mission/mission.component';
@NgModule({
  declarations: [
    AppComponent, MissionComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [MissionService],
  bootstrap: [AppComponent]
})
export class AppModule { }

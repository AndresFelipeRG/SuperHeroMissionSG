
import { MissionComponent } from './mission/mission.component';
import { AddMissionComponent } from './add-mission/add-mission.component';
import { SuperHeroComponent} from './superhero/superhero.component';
import { AddSuperHeroComponent } from './add-superhero/add-superhero.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  {path: 'missions', component: MissionComponent},
  {path: 'createMission', component: AddMissionComponent},
  {path: 'superheroes', component: SuperHeroComponent},
  {path: 'createSuperHero', component: AddSuperHeroComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

import { MissionDetailsComponent } from './mission-details/mission-details.component';
import { SuperHeroDetailsComponent} from './superhero-details/superhero-details.component';
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
  {path: 'createSuperHero', component: AddSuperHeroComponent},
  {path: 'missionDetails/:missionName/:superHeroName/:isCompleted/:isDeleted', component: MissionDetailsComponent},
  {path: 'superHeroDetails/:firstName/:lastName/:missionName/:superHeroName', component: SuperHeroDetailsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

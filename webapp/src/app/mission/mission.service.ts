import { MissionDetailsComponent } from './../mission-details/mission-details.component';

import 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Mission} from './mission';
import {HttpParams} from "@angular/common/http";

@Injectable()
export class MissionService{

  private missionsUrl: string;
  private createMissionUrl: string;
  private deleteMissionUrl: string;
  private updateMissionUrl: string;

  constructor(private http: HttpClient, private route: ActivatedRoute, private router: Router){
    this.missionsUrl = 'http://localhost:8085/getAllMissions';
    this.createMissionUrl  = 'http://localhost:8085/createMission';
    this.deleteMissionUrl  = 'http://localhost:8085/deleteMission';
    this.updateMissionUrl = 'http://localhost:8085/updateMission';
  }

  public getAllMissions(): Observable<Mission[]>{
      return this.http.get<Mission[]>(this.missionsUrl);
  }
  public saveMission(mission: Mission) {
    return this.http.post<Mission>(this.createMissionUrl,  mission);
  }
  public deleteMission(mission: Mission){
     return this.http.post<String> (this.deleteMissionUrl, mission);
  }
  public updateMission(mission: Mission){
    return this.http.post<String> (this.updateMissionUrl, mission);
  }
  public navigateToMissions(){
    this.router.navigate(['/missions'])
  }
  public navigateToDetails(mission: Mission){
    this.router.navigate(['/missionDetails', mission.missionName, mission.superHeroName, mission.isCompleted, mission.isDeleted])
  }

}
